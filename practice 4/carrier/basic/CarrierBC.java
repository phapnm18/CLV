package com.clt.apps.opus.practice4.basic;
import java.util.List;

import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.apps.opus.practice4.vo.CustomerVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;


public interface CarrierBC {
	
	List<CarrierVO> searchCarrier(CarrierVO carrierVO) throws EventException;
	
	List<CarrierVO> searchCarrierCombo() throws EventException;

	public void manageCarrier(CarrierVO[] carrierVOS, SignOnUserAccount account) throws EventException;

	List<CarrierVO> searchRLaneCombo()throws EventException;

	List<CustomerVO> searchCustomer(CustomerVO customerVO);
	
	int checkDuplicate(CarrierVO carrierVO) throws EventException;
	
}
