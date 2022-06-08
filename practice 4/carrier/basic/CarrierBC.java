/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierBC.java
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.08
*@LastModifier : NguyenMinhPhap
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.practice4.basic;
import java.util.List;

import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.apps.opus.practice4.vo.CustomerVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Carrier Business Logic Command Interface<br>
 *
 * @author Nguyen Minh Phap
 * @since J2EE 1.6
 */
public interface CarrierBC {
	/**
	 * This method search list data for Grid.
	 * 
	 * @param CarrierVO	carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	List<CarrierVO> searchCarrier(CarrierVO carrierVO) throws EventException;
	
	/**
	 * This method for searching Carrier Code list for list
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	List<CarrierVO> searchCarrierCombo() throws EventException;
	
	/**
	 * This is a method make actions(save,modify,remove). 
	 * 
	 * @param CarrierVO[] carrierVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manageCarrier(CarrierVO[] carrierVOS, SignOnUserAccount account) throws EventException;

	/**
	 * This method for searching RD Lane Code list for dropdown list
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	List<CarrierVO> searchRLaneCombo()throws EventException;

	/**
	 * search customer code for validation check
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	List<CustomerVO> searchCustomer(CustomerVO customerVO);
	
	/**
	 * This method for searching RD Lane Code list for validatecheck
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	int checkDuplicate(CarrierVO carrierVO) throws EventException;
	
}
