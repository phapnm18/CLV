package com.clt.apps.opus.practice4.event;

import com.clt.apps.opus.practice4.vo.CarrierVO;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.support.layer.event.EventSupport;

public class DouTrn004Event extends EventSupport {

	CarrierVO carrierVO = null;
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
