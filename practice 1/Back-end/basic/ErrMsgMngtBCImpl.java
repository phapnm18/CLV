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

/**
 * ALPS-Practice1 Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Practice1<br>
 *
 * @author Phap Nguyen
 * @since J2EE 1.6
 */
public class ErrMsgMngtBCImpl extends BasicCommandSupport implements ErrMsgMngtBC {

	// Database Access Object
	private transient ErrMsgMngtDBDAO dbDao = null;

	/**
	 * function constructor ErrMsgMngtBCImpl<br>
	 * To initialize ErrMsgMngtDBDAO<br>
	 */
	public ErrMsgMngtBCImpl() {
		dbDao = new ErrMsgMngtDBDAO();
	}
	
	/**
	 *  [checkDuplicateErrMsg] to check duplicate err_msg_cd.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public int checkDuplicateErrMsg(ErrMsgVO errMsgVO) throws EventException {
		try {
			// return count
			return dbDao.duplicateErrMsgCd(errMsgVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 *  [searchErrMsgVO] to get a list of ErrMsgVO.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
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
	/**
	 * [manageErrMsgVO] to save the change(add, delete, update) in database.<br>
	 * 
	 * @param ErrMsgVO[] errMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void ManageErrMsgVO(ErrMsgVO[] errMsgVO, SignOnUserAccount account) throws EventException{
		//	declare a try-catch block function for exception handling
		try {
			// storage list ErrMsgVO to insert
			List<ErrMsgVO> insertVoList = new ArrayList<ErrMsgVO>();
			// storage list ErrMsgVO to update
			List<ErrMsgVO> updateVoList = new ArrayList<ErrMsgVO>();
			// storage list ErrMsgVO to delete
			List<ErrMsgVO> deleteVoList = new ArrayList<ErrMsgVO>();
						
			for ( int i=0; i<errMsgVO .length; i++ ) {
				// Find and add new errMsgVO to insertVoList
				if ( errMsgVO[i].getIbflag().equals("I")){
					//	check duplicate 
					if (checkDuplicateErrMsg(errMsgVO[i]) >= 1){
						throw new DAOException(new ErrorHandler("ERR00001").getMessage());
					}
					else {
						insertVoList.add(errMsgVO[i]);
					}
				// Find and add new errMsgVO to updateVoList
				} else if ( errMsgVO[i].getIbflag().equals("U")){
					updateVoList.add(errMsgVO[i]);
				// Find and add new errMsgVO to deleteVoList
				} else if ( errMsgVO[i].getIbflag().equals("D")){
					deleteVoList.add(errMsgVO[i]);
				}
//				
				errMsgVO[i].setCreUsrId(account.getUsr_id());
				errMsgVO[i].setUpdUsrId(account.getUsr_id());
			}
					
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
	/*Check duplicate Msg_code, 
	 * if duplicate, return false, else return true
	 * */
	public boolean checkDuplicateMsgCd(ErrMsgVO errMsg ,List<ErrMsgVO> errList){
		for(ErrMsgVO err : errList){
			if (err.getErrMsgCd().equals(errMsg.getErrMsgCd())){
				return false;
			}
		}
		return true;
	}
	
}