/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice1SC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice1;

import java.util.List;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.basic.ErrMsgMngtBC;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.basic.ErrMsgMngtBCImpl;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.event.Practice1Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.vo.ErrMsgVO;

/**
 * ALPS-Practice1 Business Logic ServiceCommand 
 * - Process business transaction for ALPS-Practice1.
 * @author Phap Nguyen
 * @see ErrMsgMngtDBDAO
 * @since J2EE 1.6
 */
//	Declare a Practice1SC class that inherits SC
public class Practice1SC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * Practice1 system task scenario precedent work<br>
	 * Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("Practice1SC 시작");
		try {
			// Once in the comment --> login check part
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}
  
	/**
	 * Practice1 system work scenario finishing work<br>
	 * Release related internal objects when the work scenario is finished<br>
	 */
	public void doEnd() {
		log.debug("Practice1SC 종료");
	}

	/**
	 * Carry out business scenarios for each event<br>
	 * Branch processing of all events occurring in ALPS-Practice1 system work<br>
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// Which part to use if SC handles multiple events
		if (e.getEventName().equalsIgnoreCase("Practice1Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = SearchErrMsgVO(e); 
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = ManageErrMsgVO(e);
			}
		}
		return eventResponse;
	}
	/*
	* Practice1 : [Event]<br>
	* [Act] for [Business Target].<br>
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse SearchErrMsgVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice1Event event = (Practice1Event)e;
		ErrMsgMngtBC command = new ErrMsgMngtBCImpl();

		try{
			List<ErrMsgVO> list = command.SearchErrMsgVO(event.getErrMsgVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	* Practice1 : [Event]<br>
	* [Act] for [Business Target].<br>
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse ManageErrMsgVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice1Event event = (Practice1Event)e;
		ErrMsgMngtBC command = new ErrMsgMngtBCImpl();
		try{
			begin();
			command.ManageErrMsgVO(event.getErrMsgVOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
}