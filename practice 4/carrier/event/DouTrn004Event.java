/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DouTrn004Event.java
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.08
*@LastModifier : NguyenMinhPhap
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.practice4.event;

import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.support.layer.event.EventSupport;

/**
 * @author Nguyen Minh Phap
 * @see DOU_TRN_004HTMLAction 
 * @since J2EE 1.6
 */
public class DouTrn004Event extends EventSupport {
	/** Table Value Object */
	CarrierVO carrierVO = null;
	
	/** Table Value Object Multi Data */
	CarrierVO[] carrierVOs = null;
	
	public void setCarrierVOS(CarrierVO[] vOs) {
		this.carrierVOs = vOs;		
	}
	public void setCarrierVO(CarrierVO vo) {
		this.carrierVO = vo;		
	}
	public CarrierVO[] getCarrierVOS() {
		return carrierVOs;
	}
	public CarrierVO getCarrierVO() {
		return carrierVO;
	}
	
	
}
