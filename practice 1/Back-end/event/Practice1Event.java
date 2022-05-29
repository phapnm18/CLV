/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice1Event.java
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

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.vo.ErrMsgVO;


/**
 * PDTO (Data Transfer Object including Parameters) for Practice1<br>
 * - Created in Practice1HTMLAction<br>
 * - Used as PDTO delivered to ServiceCommand Layer<br>
 *
 * @author Phap Nguyen
 * @see Practice1HTMLAction
 * @since J2EE 1.6
 */

public class Practice1Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object search condition and single event processing */
	ErrMsgVO errMsgVO = null;
	
	/** Table Value Object Multi Data */
	ErrMsgVO[] errMsgVOs = null;

	public Practice1Event(){}
	//	pass a value to the variable errmsgVO
	public void setErrMsgVO(ErrMsgVO errMsgVO){
		this. errMsgVO = errMsgVO;
	}
	//	pass multiple values to the array errmsgVOs
	public void setErrMsgVOS(ErrMsgVO[] errMsgVOs){
		this. errMsgVOs = errMsgVOs;
	}
	//	returns the value for the variable errmsgVO
	public ErrMsgVO getErrMsgVO(){
		return errMsgVO;
	}
	//	returns the value for the variable errmsgVOs
	public ErrMsgVO[] getErrMsgVOS(){
		return errMsgVOs;
	}

}