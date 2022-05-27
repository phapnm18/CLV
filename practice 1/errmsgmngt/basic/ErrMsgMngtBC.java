/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMngtBC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice1.errmsgmngt.basic;

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.vo.ErrMsgVO;

/**
 * ALPS-Practice1 Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Practice1<br>
 *
 * @author Phap Nguyen
 * @since J2EE 1.6
 */

public interface errMsgMngtBC {

	/**
	* [Act] for [Business Target].<br>
	* @param ErrMsgVO errMsgVO
	* @return List<ErrMsgVO>
	* @exception EventException
	*/
	public List<ErrMsgVO> searchErrMsgVO(ErrMsgVO errMsgVO) throws EventException;
	
	/**
	* [Act] for [Business Target].<br>
	* @param ErrMsgVO[] errMsgVO
	* @param account SignOnUserAccount
	* @exception EventException
	 */
	public void manageErrMsgVO(ErrMsgVO[] errMsgVO,SignOnUserAccount account) throws EventException;
}
