/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE1HTMLAction.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice1.errmsgmngt.event;

import javax.servlet.http.HttpServletRequest;

import com.asprise.util.tiff.e;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.vo.ErrMsgVO;

/**
 * HTTP Parser<br>
 * - Parsing the Value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.practice1 screen as a Java variable<br>
 * - Parsing information is converted into Event, put in request and executed by Practice1SC<br>
 * - Set EventResponse to request to send execution result from Practice1SC to View (JSP)<br>
 * @author Phap Nguyen
 * @see Practice1Event 
 * @since J2EE 1.6
 */

public class PRACTICE1HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	
//	 Create a PRACTICE1HTMLAction object
	
	public PRACTICE1HTMLAction() {}	

	/**
	* Parsing the HTML DOM object's Value as a Java variable<br>
	* Parsing the information of HttpRequst as Practice1Event and setting it in the request<br>
	* @param request HttpServletRequest HttpRequest
	* @return Event An object that implements the Event interface.
	* @exception HTMLActionException
	 */
//	declare function perfom
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		//	create event in Practice1 to call request
    	FormCommand command = FormCommand.fromRequest(request);
		Practice1Event event = new Practice1Event();
		// Which part to use if SC handles multiple events	
		if(command.isCommand(FormCommand.MULTI)) {
			//	request object from class and assign the value
			event.setErrMsgVOS((ErrMsgVO[])getVOs(request, ErrMsgVO .class,""));
			ErrMsgVO errVO = new ErrMsgVO();
			
			
		}
		//	check if the command belongs to SEARCH or not
		else if(command.isCommand(FormCommand.SEARCH)) {
			//	request object from class and assign the value
			ErrMsgVO errVO = new ErrMsgVO();	
			//	assign values after taking parameter values associated with objects
			errVO.setErrMsgCd(JSPUtil.getParameter(request, "s_err_msg_cd",""));
			errVO.setErrMsg(JSPUtil.getParameter(request, "s_err_msg", ""));
			event.setErrMsgVO(errVO);
		}

		return  event;
	}
	

	/**
	* Saving the value of the task scenario execution result in the attribute of HttpRequest<br>
	* Setting the ResultSet that transmits the execution result from ServiceCommand to View (JSP) in the request<br>
	* @param request HttpServletRequest HttpRequest
	* @param eventResponse An object that implements the EventResponse interface.
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Save HttpRequest parsing result value in HttpRequest attribute<br>
	* HttpRequest parsing result value set in request<br>
	* @param request HttpServletRequest HttpRequest
	* @param event An object that implements the Event interface.
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}