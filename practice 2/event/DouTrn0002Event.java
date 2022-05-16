package com.clt.apps.opus.dou.doutraining.codemgmt.event;

import com.clt.apps.opus.dou.doutraining.codemgmt.vo.DetailVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.MasterVO;
import com.clt.framework.support.layer.event.EventSupport;

public class DouTrn0002Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	MasterVO masterVO = null;
	MasterVO[] masterVOs = null;
	
	DetailVO detailVO = null;
	DetailVO[] detailVOs = null;
	
	public DouTrn0002Event(){}
	
	public void setMasterVOS(MasterVO[] vOs) {
		this.masterVOs = vOs;		
	}
	public void setMasterVO(MasterVO vo) {
		this.masterVO = vo;		
	};
	public MasterVO[] getMasterVOS() {
		return masterVOs;
	}
	public MasterVO getMasterVO() {
		return masterVO;
	}
	public void setDetailVOS(DetailVO[] vOs) {
		this.detailVOs = vOs;		
	}

	public void setDetailVO(DetailVO vo) {
		this.detailVO= vo;
		
	}
	public DetailVO[] getDetailVOS() {
		return detailVOs;
	}
	
	public DetailVO getDetailVO() {
		return detailVO;
	}
}
