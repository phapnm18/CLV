/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CustomerEvent.java
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.08
*@LastModifier : NguyenMinhPhap
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.practice4.event;

import com.clt.apps.opus.practice4.vo.CustomerVO;
import com.clt.framework.support.layer.event.EventSupport;

/**
 * @author Nguyen Minh Phap
 * @see CUSTOMERHTMLAction 
 * @since J2EE 1.6
 */
public class CustomerEvent extends EventSupport {
	

	private static final long serialVersionUID = 1L;
	CustomerVO customerVO = null;

	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	
	
	

}
