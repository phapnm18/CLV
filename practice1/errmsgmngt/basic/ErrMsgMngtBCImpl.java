/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMngtBCImpl.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.integration.ErrMsgMngtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.vo.ErrMsgVO;

//	declare class BCImpl inherit BC
public class ErrMsgMngtBCImpl extends BasicCommandSupport implements ErrMsgMngtBC {

	// Database Access Object
	private transient ErrMsgMngtDBDAO dbDao = null;

	//	function declaration
	public ErrMsgMngtBCImpl() {
		dbDao = new ErrMsgMngtDBDAO();
	}
	//	defines the function used to search
	public List<ErrMsgVO> SearchErrMsgVO(ErrMsgVO errMsgVO) throws EventException {
		//	declare a try-catch block function for exception handling	
		try {
			//	return list data ErrMsgVO
			return dbDao.SearchErrMsgVO(errMsgVO);
			//	catch ErrorHandler	
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			//	catch ErrorHandler
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	// 	defines the function used to manage: C, U, D	
	public void ManageErrMsgVO(ErrMsgVO[] errMsgVO, SignOnUserAccount account) throws EventException{
		//	declare a try-catch block function for exception handling
		try {
			//	declare and create lists containing elements
			List<ErrMsgVO> insertVoList = new ArrayList<ErrMsgVO>();
			List<ErrMsgVO> updateVoList = new ArrayList<ErrMsgVO>();
			List<ErrMsgVO> deleteVoList = new ArrayList<ErrMsgVO>();
			//	filter all elements in array			
			for ( int i=0; i<errMsgVO .length; i++ ) {
				if ( errMsgVO[i].getIbflag().equals("I")){
					//	return the correct ibflag elements to the set I
					insertVoList.add(errMsgVO[i]);
				} else if ( errMsgVO[i].getIbflag().equals("U")){
					//	return the correct ibflag elements to the set U
					updateVoList.add(errMsgVO[i]);
				} else if ( errMsgVO[i].getIbflag().equals("D")){
					//	return the correct ibflag elements to the set D
					deleteVoList.add(errMsgVO[i]);
				}
//				
				errMsgVO[i].setCreUsrId(account.getUsr_id());
				errMsgVO[i].setUpdUsrId(account.getUsr_id());
			}
				
				for (ErrMsgVO serrmsgVO: errMsgVO)
				{
					if(!Pattern.matches("^[A-Z]{3}[1-9]{5}$",serrmsgVO.getErrMsgCd()))
						throw new DAOException (new ErrorHandler("ERR12345").getMessage());
						
				}    
//			
//		
			//	check the size of the array and then execute events add to the DB
			if ( insertVoList.size() > 0 ) {
				dbDao.addManageErrMsgVOS(insertVoList);
			}
			//	check the size of the array and then execute events modify to the DB
			if ( updateVoList.size() > 0 ) {
				dbDao.modifyManageErrMsgVOS(updateVoList);
			}
			//	check the size of the array and then execute events delete to the DB
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeManageErrMsgVOS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}