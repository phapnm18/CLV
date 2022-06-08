package com.clt.apps.opus.practice4;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.basic.ErrMsgMngtBC;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.basic.ErrMsgMngtBCImpl;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.event.Practice1Event;
import com.clt.apps.opus.money.basic.MoneyBC;
import com.clt.apps.opus.money.basic.MoneyBCImpl;
import com.clt.apps.opus.money.event.DouTrn0003Event;
import com.clt.apps.opus.money.vo.SummaryVO;
import com.clt.apps.opus.practice4.basic.CarrierBC;
import com.clt.apps.opus.practice4.basic.CarrierBCImpl;
import com.clt.apps.opus.practice4.event.CustomerEvent;
import com.clt.apps.opus.practice4.event.DouTrn004Event;
import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.apps.opus.practice4.vo.CustomerVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class Practice4SC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	public void doStart() {
		log.debug("Practice4SC 시작");
		try {
			// Once in the comment --> login check part
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	public void doEnd() {
		log.debug("Practice4SC 종료");
	}

	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// Which part to use if SC handles multiple events
		if (e.getEventName().equalsIgnoreCase("DouTrn004Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchCarrier(e); 
			} else if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initData();
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageCarrier(e);
			} else if (e.getFormCommand().isCommand(FormCommand.COMMAND01)) {
				eventResponse = checkDuplicate(e);
			}
		}
		if (e.getEventName().equalsIgnoreCase("CustomerEvent")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {
				eventResponse = searchCustomer(e);
			}
		}
		return eventResponse;

	}

	private EventResponse searchCustomer(Event e) {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierBC command = new CarrierBCImpl();
		CustomerEvent event = (CustomerEvent) e;
		List<CustomerVO> listCustomer = command.searchCustomer(event.getCustomerVO());
		eventResponse.setRsVoList(listCustomer);
		return eventResponse;
	}

	private EventResponse initData() throws EventException {
		// PDTO(Data Transfer Objng Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierBC command = new CarrierBCImpl();

		try {
			List<CarrierVO> listcrr = command.searchCarrierCombo();
			StringBuilder carrierBuilder = new StringBuilder();
			if (null != listcrr && listcrr.size() > 0) {
				for (int i = 0; i < listcrr.size(); i++) {
					carrierBuilder.append(listcrr.get(i).getJoCrrCd());
					if (i < listcrr.size() - 1) {
						carrierBuilder.append("|");
					}
				}
			}
			eventResponse.setETCData("carriers", carrierBuilder.toString());

			List<CarrierVO> listlane = command.searchRLaneCombo();
			StringBuilder laneBuilder = new StringBuilder();
			if (listlane != null && listlane.size() > 0) {
				for (int i = 0; i < listlane.size(); i++) {
					laneBuilder.append(listlane.get(i).getRlaneCd());
					if (i < listlane.size() - 1) {
						laneBuilder.append("|");
					}
				}
			}

			eventResponse.setETCData("rlanes", laneBuilder.toString());

		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;

	}

	private EventResponse searchCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn004Event event = (DouTrn004Event) e;
		CarrierBC command = new CarrierBCImpl();

		try {
			List<CarrierVO> list = command.searchCarrier(event.getCarrierVO());
			eventResponse.setRsVoList(list);
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	private EventResponse searchCarrierCombo(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn004Event event = (DouTrn004Event) e;
		CarrierBC command = new CarrierBCImpl();

		try {
			List<CarrierVO> list = command.searchCarrierCombo();
			eventResponse.setRsVoList(list);
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	private EventResponse manageCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn004Event event = (DouTrn004Event) e;
		CarrierBC command = new CarrierBCImpl();
		try {
			begin();
			command.manageCarrier(event.getCarrierVOS(), account);
			eventResponse.setUserMessage(new ErrorHandler("BKG06071").getUserMessage());
			commit();
		} catch (EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}
	
	private EventResponse checkDuplicate(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn004Event event = (DouTrn004Event) e;
		CarrierBC command = new CarrierBCImpl();

		try {
			int numcheck = command.checkDuplicate(event.getCarrierVO());
			eventResponse.setETCData("ISEXIST", numcheck > 0 ? "Y" : "N");;
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

}
