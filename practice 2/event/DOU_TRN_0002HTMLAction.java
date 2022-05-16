package com.clt.apps.opus.dou.doutraining.codemgmt.event;

import java.awt.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.dou.doutraining.codemgmt.event.DouTrn0002Event;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.DetailVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.MasterVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class DOU_TRN_0002HTMLAction extends HTMLActionSupport{
	private static final long serialVersionUID = 1L;

	public DOU_TRN_0002HTMLAction() {};
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		DouTrn0002Event event = new DouTrn0002Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setMasterVOS((MasterVO[])getVOs(request, MasterVO.class,""));
			MasterVO masterVO = new MasterVO();		
			
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			event.setMasterVO((MasterVO)getVO(request, MasterVO.class));
			MasterVO mstVO = new MasterVO();
			mstVO.setOwnrSubSysCd(JSPUtil.getParameter(request, "s_ownr_sub_sys_cd", ""));
			mstVO.setIntgCdId(JSPUtil.getParameter(request, "s_intg_cd_id",""));
			event.setMasterVO(mstVO);
		}
		else if(command.isCommand(FormCommand.SEARCH01)) {
			event.setDetailVO((DetailVO)getVO(request, DetailVO.class));
			DetailVO dtlVO = new DetailVO();
			dtlVO.setIntgCdId(JSPUtil.getParameter(request, "codeid",""));
			event.setDetailVO(dtlVO);
		}
		else if(command.isCommand(FormCommand.MULTI01)){
			event.setDetailVOS((DetailVO[])getVOs(request, DetailVO.class,""));
		}

		return  event;
	}
	
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}
