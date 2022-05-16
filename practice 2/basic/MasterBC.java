package com.clt.apps.opus.dou.doutraining.codemgmt.basic;

import java.util.List;

import com.clt.apps.opus.dou.doutraining.codemgmt.vo.DetailVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.MasterVO;
import com.clt.apps.opus.dou.doutraining.errmsgmgmt.vo.ErrMsgVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface MasterBC {
	public abstract List<MasterVO> searchCodeMaster(MasterVO masterVO) throws EventException;
	
	
	public void manageCodeMaster(MasterVO[] masterVO,SignOnUserAccount account) throws EventException;
	
	
	public abstract List<DetailVO> searchCodeDetail(DetailVO detailVO) throws EventException;
	
	
	public void manageCodeDetail(DetailVO[] detailVO,SignOnUserAccount account) throws EventException;

}
