/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierBCImpl.java
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.08
*@LastModifier : NguyenMinhPhap
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.practice4.basic;
import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.practice4.basic.CarrierBC;
import com.clt.apps.opus.practice4.integration.CarrierDBDAO;
import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.apps.opus.practice4.vo.CustomerVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Carrier Business Logic Command Interface<br>
 *
 * @author Nguyen Minh Phap
 * @since J2EE 1.6
 */
public class CarrierBCImpl extends BasicCommandSupport implements CarrierBC {

	// Database Access Object
	private transient CarrierDBDAO dbDao = null;
	

		public  CarrierBCImpl() {
			dbDao = new CarrierDBDAO();
		}
		/**
		 * This method search list data for Grid.
		 * 
		 * @param CarrierJooVO carrierJooVO
		 * @return List<CarrierJooVO>
		 * @exception EventException
		 */
		public List<CarrierVO> searchCarrier(CarrierVO carrierVO) throws EventException {

			try {
				return dbDao.searchCarrier(carrierVO);
				//	catch ErrorHandler	
			} catch(DAOException ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
				//	catch ErrorHandler
			} catch (Exception ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}
			
		}
		
		/**
		 * this method for searching Carrier Code list, it's used drop down list
		 * 
		 * @param CarrierVO carrierVO
		 * @return List<CarrierJooVO>
		 * @exception EventException
		 */
		public List<CarrierVO> searchCarrierCombo() throws EventException {

			try {

				return dbDao.searchCarrierCombo();
				//	catch ErrorHandler	
			} catch(DAOException ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
				//	catch ErrorHandler
			} catch (Exception ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}
			
		}
		
		/**
		 *This is a method make actions(save,modify,remove). 
		 * 
		 * @param CarrierVO[] carrierVO
		 * @param account SignOnUserAccount
		 * @exception EventException
		 */
		public void manageCarrier(CarrierVO[] carrierVO, SignOnUserAccount account) throws EventException{
			try {

				List<CarrierVO> insertVoList = new ArrayList<CarrierVO>();
				List<CarrierVO> updateVoList = new ArrayList<CarrierVO>();
				List<CarrierVO> deleteVoList = new ArrayList<CarrierVO>();
				StringBuilder dups = new StringBuilder();
				int count = 0;
							
				for ( int i=0; i<carrierVO.length; i++ ) {
					if ( carrierVO[i].getIbflag().equals("I")){
						if (checkDuplicate (carrierVO[i]) >= 1){
							dups.append(carrierVO[i].getJoCrrCd() + "," + carrierVO[i].getRlaneCd());
							if (i < carrierVO .length - 1){
								dups.append("],[");
							}
							count++;
						}
						if (i == carrierVO.length-1){
							if (count > 0){
							throw new DAOException(new ErrorHandler("ERR00001").getMessage());
						}
						else {
							insertVoList.add(carrierVO[i]);
						}

					} else if ( carrierVO[i].getIbflag().equals("U")){
						updateVoList.add(carrierVO[i]);

					} else if ( carrierVO[i].getIbflag().equals("D")){
						deleteVoList.add(carrierVO[i]);
					}
				
					carrierVO[i].setCreUsrId(account.getUsr_id());
					carrierVO[i].setUpdUsrId(account.getUsr_id());
				}
						

				if ( insertVoList.size() > 0 ) {
					dbDao.addManageCarrier(insertVoList);
				}

				if ( updateVoList.size() > 0 ) {
					dbDao.updateManageCarrier(updateVoList);
				}

				if ( deleteVoList.size() > 0 ) {
					dbDao.deleteManageCarrier(deleteVoList);
				}
				}
			} catch(DAOException ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			} catch (Exception ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}
		}

		/**
		 * this method for searching RLane Code list, it's used drop down list
		 * 
		 * @param CarrierVO carrierVO
		 * @return List<CarrierVO>
		 * @exception EventException
		 */
		public List<CarrierVO> searchRLaneCombo() throws EventException {
			try {

				return dbDao.searchRLaneCombo();
				//	catch ErrorHandler	
			} catch(DAOException ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
				//	catch ErrorHandler
			} catch (Exception ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}
			
		}
		/**
		 * this method for searching customer Code list
		 * 
		 * @param CarrierVO carrierVO
		 * @return List<CarrierVO>
		 * @exception EventException
		 */
		public List<CustomerVO> searchCustomer(CustomerVO customerVO) {
			List<CustomerVO> list =null;
			try {
				list = dbDao.searchCustomer(customerVO);
			} catch (DAOException e) {
			
				e.printStackTrace();
			}

			return list;
		}
		
		/**
		 * this method for searching check duplicate
		 * 
		 * @param CarrierVO carrierVO
		 * @exception EventException
		 */
		public int checkDuplicate(CarrierVO carrierVO) throws EventException {
			try {

				return dbDao.checkDuplicate(carrierVO);
				//	catch ErrorHandler	
			} catch(DAOException ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
				//	catch ErrorHandler
			} catch (Exception ex) {
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}
			
		}
}