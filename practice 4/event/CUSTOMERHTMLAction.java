package com.clt.apps.opus.practice4.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.vo.ErrMsgVO;
import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.apps.opus.practice4.vo.CustomerVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class CUSTOMERHTMLAction extends HTMLActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		CustomerEvent event = new CustomerEvent();
		if(command.isCommand(FormCommand.SEARCH02)) {
			CustomerVO customerVO = new CustomerVO();
			customerVO.setCustCntCd(JSPUtil.getParameter(request, "s_cust_cnt_cd"));
			customerVO.setCustSeq(JSPUtil.getParameter(request, "s_cust_seq"));
			event.setCustomerVO(customerVO);
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
