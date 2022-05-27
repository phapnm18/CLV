/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMngtDBDAO.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice1.errmsgmngt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.basic.ErrMsgMngtBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.practice1.errmsgmngt.vo.ErrMsgVO;


/**
 * @author Phap Nguyen
 * @see ErrMsgMngtBCImpl 참조
 * @since J2EE 1.6
 */
public class ErrMsgMngtDBDAO extends DBDAOSupport {

	/**
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	 //	 declare function searchErrMsgVO
	public List<ErrMsgVO> searchErrMsgVO(ErrMsgVO errMsgVO) throws DAOException {
		// assign the default value of null to 2 variables
		DBRowSet dbRowset = null;
		List<ErrMsgVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		// exception handling for arrays	
		try{
			
			if(errMsgVO != null){
				// assign a value to mapVO 
				Map<String, String> mapVO = errMsgVO .getColumnValues();
				// put values in mapVO into param & velparam
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}  
			// execute queries in ErrMsgMgmtDBDAOErrMsgVORSQL with param and velParam	
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMngtDBDAOErrMsgVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, ErrMsgVO .class);
					
		} catch(SQLException se) {
			// throw console.log a message error 
			log.error(se.getMessage(),se);
			// throw EventException
			throw new DAOException(new ErrorHandler(se).getMessage());
			// handling errors for mapvo
		} catch(Exception ex) {
			// throw console.log a message error
			log.error(ex.getMessage(),ex);
			// throw EventException
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}

		return list;
	}
	 /**
	 * [addmanageErrMsgVO] to add ErrMsgVO.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @exception DAOException
	 * @exception Exception
	 */
	public void addManageErrMsgVO(ErrMsgVO errMsgVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		//	exception handling for arrays
		try {
			// assign a value to mapVO
			Map<String, String> mapVO = errMsgVO .getColumnValues();
			// put values in mapVO into param & velparam
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			// create new SQLExecute
			SQLExecuter sqlExe = new SQLExecuter("");
			// execute queries in ErrMsgMngtDBDAOErrMsgVOCSQL with param and velParam
			int result = sqlExe.executeUpdate((ISQLTemplate)new ErrMsgMngtDBDAOErrMsgVOCSQL(), param, velParam);
			// EXECUTE_FAILED: The constant indicating that an error occured while executing a batch statement.
			// if result = execute_failed throw error			
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
			
		} catch(SQLException se) {
			// throw console.log a message error
			log.error(se.getMessage(),se);
			// throw EventException
			throw new DAOException(new ErrorHandler(se).getMessage());
			// handling errors for mapvo
		} catch(Exception ex) {
			// throw console.log a message error
			log.error(ex.getMessage(),ex);
			// throw EventException
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
	}
	
	 //	declare function modifyManageErrMsgVO
	public int modifyManageErrMsgVO(ErrMsgVO errMsgVO) throws DAOException,Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		// declare the value result
		int result = 0;
		// exception handling for arrays
		try {
			// assign a value to mapVO
			Map<String, String> mapVO = errMsgVO .getColumnValues();
			// put values in mapVO into param & velparam
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			// create new SQLExecute
			SQLExecuter sqlExe = new SQLExecuter("");
			// execute queries in ErrMsgMngtDBDAOErrMsgVOUSQL with param and velParam
			result = sqlExe.executeUpdate((ISQLTemplate)new ErrMsgMngtDBDAOErrMsgVOUSQL(), param, velParam);
			// EXECUTE_FAILED: The constant indicating that an error occured while executing a batch statement.
			// if result = execute_failed throw error
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		
		} catch(SQLException se) {
			// throw console.log a message error
			log.error(se.getMessage(),se);
			// throw EventException
			throw new DAOException(new ErrorHandler(se).getMessage());
			// handling errors for mapvo
		} catch(Exception ex) {
			// throw console.log a message error
			log.error(ex.getMessage(),ex);
			// throw EventException
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		// return results after processing is done
		return result;
	}
	

	/**
	 * [addmanageErrMsgVOS] to add ErrMsgVOs.<br>
	 * 
	 * @param List<ErrMsgVO> errMsgVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addManageErrMsgVOS(List<ErrMsgVO> errMsgVO) throws DAOException,Exception {
		// declare the array insCnt
		int insCnt[] = null;
		
		try {
			// create new SQLExecuter variable to execute query
			SQLExecuter sqlExe = new SQLExecuter("");
			// check if list not null, execute query to save add new changes in database
			if(errMsgVO .size() > 0){
				// execute queries in ErrMsgMngtDBDAOErrMsgVOCSQL with param and velParam
				insCnt = sqlExe.executeBatch((ISQLTemplate)new ErrMsgMngtDBDAOErrMsgVOCSQL(), errMsgVO,null);
				//	iterate the elements in the array		
				for(int i = 0; i < insCnt.length; i++){
					// EXECUTE_FAILED: The constant indicating that an error occured while executing a batch statement.
					// if result = execute_failed throw error
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
			
		} catch(SQLException se) {
			//	throw console.log a message error
			log.error(se.getMessage(),se);
			//	throw EventException
			throw new DAOException(new ErrorHandler(se).getMessage());
			//	handling errors
		} catch(Exception ex) {
			//	throw console.log a message error
			log.error(ex.getMessage(),ex);
			//	throw EventException
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		//	return array
		return insCnt;
	}
	/**
	 * [modifymanageErrMsgVOS] to update ErrMsgVOs.<br>
	 * 
	 * @param List<ErrMsgVO> errMsgVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifyManageErrMsgVOS(List<ErrMsgVO> errMsgVO) throws DAOException,Exception {
		//	declare the array updCnt
		int updCnt[] = null;
		//	exception handling for arrays
		try {
			//	create new SQLExecute
			SQLExecuter sqlExe = new SQLExecuter("");
			//	check the size of the array
			if(errMsgVO .size() > 0){
				// execute queries in ErrMsgMngtDBDAOErrMsgVOUSQL with param and velParam
				updCnt = sqlExe.executeBatch((ISQLTemplate)new ErrMsgMngtDBDAOErrMsgVOUSQL(), errMsgVO,null);
				//	iterate the elements in the array
				for(int i = 0; i < updCnt.length; i++){
					// EXECUTE_FAILED: The constant indicating that an error occured while executing a batch statement.
					// if result = execute_failed throw error
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		
		} catch(SQLException se) {
			//	throw console.log a message error
			log.error(se.getMessage(),se);
			//	throw EventException
			throw new DAOException(new ErrorHandler(se).getMessage());
			//	handling errors
		} catch(Exception ex) {
			//	throw console.log a message error
			log.error(ex.getMessage(),ex);
			//	throw EventException
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		//	return array
		return updCnt;
	}
	
	/**
	 * [removemanageErrMsgVOS] to remove ErrMsgVO.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removeManageErrMsgVOS(List<ErrMsgVO> errMsgVO) throws DAOException,Exception {
		//	declare the array delCnt
		int delCnt[] = null;
		//	exception handling for arrays
		try {
			// create new SQLExecuter variable to execute query
			SQLExecuter sqlExe = new SQLExecuter("");
			// check if list not null, execute query to save add new changes in database
			if(errMsgVO .size() > 0){
				// execute queries in ErrMsgMngtDBDAOErrMsgVODSQL with param and velParam
				delCnt = sqlExe.executeBatch((ISQLTemplate)new ErrMsgMngtDBDAOErrMsgVODSQL(), errMsgVO,null);
				//	iterate the elements in the array
				for(int i = 0; i < delCnt.length; i++){
					// EXECUTE_FAILED: The constant indicating that an error occured while executing a batch statement.
					// if result = execute_failed throw error
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		
		} catch(SQLException se) {
			//	throw console.log a message error
			log.error(se.getMessage(),se);
			//	throw EventException
			throw new DAOException(new ErrorHandler(se).getMessage());
			//	handling errors
		} catch(Exception ex) {
			//	throw console.log a message error
			log.error(ex.getMessage(),ex);
			//	throw EventException
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
	
		return delCnt;
	}
	/**
	 * [duplicateErrMsgCd] to check duplicate err_msg_cd.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	
	public int duplicateErrMsgCd(ErrMsgVO errMsgVO) throws DAOException,Exception{
		// create and define a null DBRowSet to store query result.
		DBRowSet dbRowset = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		int count = 0;
		try{
			Map<String, String> mapVO = errMsgVO .getColumnValues();

			param.put("err_msg_cd",errMsgVO.getErrMsgCd());

			// execute queries in ErrMsgMngtDBDAOErrMsgDuplicateRSQL with param and velParam
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMngtDBDAOErrMsgDuplicateRSQL(), param, null);
			while (dbRowset.next()){
				String countE = dbRowset.getString(1);
				count = Integer.parseInt(countE);
			}
			System.out.println(count);
		}
		catch(SQLException se) {
			//show error in console with error message
			log.error(se.getMessage(),se);
			//throw EventException
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			//show error in console with error message
			log.error(ex.getMessage(),ex);
			//throw EventException
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return count;
	}
	
}
