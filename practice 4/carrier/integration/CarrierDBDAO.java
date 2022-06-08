package com.clt.apps.opus.practice4.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.practice4.integration.CarrierDBDAOSearchVORSQL;
import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.apps.opus.practice4.vo.CustomerVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class CarrierDBDAO extends DBDAOSupport{

	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchCarrier(CarrierVO carrierVO) throws DAOException{
		DBRowSet dbRowset = null;
		List<CarrierVO> list = new ArrayList();
		
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();
	
		try{
			 if(carrierVO != null){
				 Map<String, String> mapVO = carrierVO.getColumnValues();
				 List<String> obj_list_no = new ArrayList<>();
				 if(null != carrierVO.getJoCrrCd()){
					 String[] carriercombo = carrierVO.getJoCrrCd().split(",");
					 for(int i = 0; i < carriercombo.length; i++){
						 obj_list_no.add(carriercombo[i]);
						 }
					 }
				 param.putAll(mapVO);
				 param.put("obj_list_no", obj_list_no);
					
				 velParam.putAll(mapVO);
				 velParam.put("obj_list_no", obj_list_no);
			 } 
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierDBDAOSearchVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierVO .class);	
		}
		catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());	
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchCarrierCombo()throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		
		try{
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierDBDAOCarrierComboVORSQL(), null, null);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierVO .class);		
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {	
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchRLaneCombo()throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = new ArrayList();
		
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();
		try{	
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierDBDAORLaneComboVORSQL(), null, null);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierVO .class);
		}
		catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	public int[] addManageCarrier(List<CarrierVO> carrierVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierVO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierDBDAOSearchVOCSQL(), carrierVO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	
	public int[] deleteManageCarrier(List<CarrierVO> carrierVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierDBDAOSearchVODSQL(), carrierVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	
	public int[] updateManageCarrier(List<CarrierVO> carrierVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierDBDAOSearchVODSQL(), carrierVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}

	@SuppressWarnings("unchecked")
	public List<CustomerVO> searchCustomer(CustomerVO customerVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CustomerVO> list = new ArrayList();
		
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();
		try{
			
			if(customerVO != null){
				
				Map<String, String> mapVO = customerVO.getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CustomerDBDAOCustomerVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CustomerVO.class);
			}		
		} catch(SQLException se) {
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	public int checkDuplicate(CarrierVO carrierVO)throws DAOException {
		DBRowSet dbRowset = null;
		Map<String, Object> param = new HashMap<String, Object>();
		int count = 0;
		try {
			Map<String, String> mapVO = carrierVO.getColumnValues();
			param.putAll(mapVO);
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierDBDAOCheckDuplicateRSQL(), param, null);
			while (dbRowset.next()){
				String countE = dbRowset.getString(1);
				count = Integer.parseInt(countE);
			}
		}
		catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return count;
	}

}
