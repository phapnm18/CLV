package com.clt.apps.opus.practice4.event;

import com.clt.apps.opus.practice4.vo.CustomerVO;
import com.clt.framework.support.layer.event.EventSupport;

public class CustomerEvent extends EventSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CustomerVO customerVO = null;

	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	
	
	

}
