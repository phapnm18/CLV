package com.clt.apps.opus.practice4.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.practice4.event.DouTrn004Event;
import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class DOU_TRN_004HTMLAction extends HTMLActionSupport{
	private static final long serialVersionUID = 1L;

	public DOU_TRN_004HTMLAction() {};
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		DouTrn004Event event = new DouTrn004Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			
			event.setCarrierVOS((CarrierVO[])getVOs(request, CarrierVO.class,""));
			
		}

		else if(command.isCommand(FormCommand.SEARCH)) {
			CarrierVO carrierVO = new CarrierVO();
			carrierVO.setJoCrrCd(JSPUtil.getParameter(request, "s_carrier", ""));
			carrierVO.setVndrSeq(JSPUtil.getParameter(request, "s_vndr_seq", ""));
			carrierVO.setCreDtFr(JSPUtil.getParameter(request, "s_cre_dt_fm", ""));
			carrierVO.setCreDtTo(JSPUtil.getParameter(request, "s_cre_dt_to", ""));
			event.setCarrierVO(carrierVO);

			}
		else if (command.isCommand(FormCommand.COMMAND01)){
			event.setCarrierVO((CarrierVO)getVO(request, CarrierVO.class,""));
		}
		
		return event;
		}
		
		public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
			request.setAttribute("EventResponse", eventResponse);
		}

		public void doEnd(HttpServletRequest request, Event event) {
			request.setAttribute("Event", event);
		}
	}


