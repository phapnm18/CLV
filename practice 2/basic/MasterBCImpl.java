package com.clt.apps.opus.dou.doutraining.codemgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.dou.doutraining.codemgmt.integration.MasterDBDAO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.DetailVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.MasterVO;
import com.clt.apps.opus.dou.doutraining.errmsgmgmt.vo.ErrMsgVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class MasterBCImpl extends BasicCommandSupport implements MasterBC{
	private transient MasterDBDAO dbDao = null;

	public MasterBCImpl() {
		dbDao = new MasterDBDAO();
	}

	@Override
	public List<MasterVO> searchCodeMaster(MasterVO masterVO) throws EventException {
		try {
			return dbDao.searchMasterVO(masterVO);
			
		}catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	@Override
	public void manageCodeMaster(MasterVO[] masterVO, SignOnUserAccount account) throws EventException {
		
	try
		{
		List<MasterVO> insertVoList = new ArrayList<MasterVO>();
		List<MasterVO> updateVoList = new ArrayList<MasterVO>();
		List<MasterVO> deleteVoList = new ArrayList<MasterVO>();
		for ( int i=0; i<masterVO .length; i++ ) {
			if ( masterVO[i].getIbflag().equals("I")){
				
				List<MasterVO> listMaster = dbDao.searchValidate();
				
				for(MasterVO mst: listMaster){
					if(masterVO[i].getIntgCdId().equals(mst.getIntgCdId())){
						throw new DAOException(new ErrorHandler("ERR54321").getMessage());
					}
					
				}
				
				masterVO[i].setUpdUsrId(account.getUsr_id());
				masterVO[i].setCreUsrId(account.getUsr_id());
				insertVoList.add(masterVO[i]);

			} else if ( masterVO[i].getIbflag().equals("U")){
				masterVO[i].setUpdUsrId(account.getUsr_id());
				updateVoList.add(masterVO[i]);
			} else if ( masterVO[i].getIbflag().equals("D")){
				deleteVoList.add(masterVO[i]);
			}
		}
		
		if ( insertVoList.size() > 0 ) {
			dbDao.addmanageMasterS(insertVoList);
		}
		
		if ( updateVoList.size() > 0 ) {
			dbDao.modifymanageMasterS(updateVoList);
		}
		
		if ( deleteVoList.size() > 0 ) {
			dbDao.removemanageMasterS(deleteVoList);
		}
	} catch(DAOException ex) {
		throw new EventException(new ErrorHandler(ex).getMessage(),ex);
	} catch (Exception ex) {
		throw new EventException(new ErrorHandler(ex).getMessage(),ex);
	}
	}
	public List<DetailVO> searchCodeDetail(DetailVO detailVO) throws EventException {
		try {
			return dbDao.searchDetailVO(detailVO);
			
		}catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}

	@Override
	public void manageCodeDetail(DetailVO[] detailVO, SignOnUserAccount account)throws EventException {
		try
		{
		List<DetailVO> insertVoList = new ArrayList<DetailVO>();
		List<DetailVO> updateVoList = new ArrayList<DetailVO>();
		List<DetailVO> deleteVoList = new ArrayList<DetailVO>();
		for ( int i=0; i<detailVO .length; i++ ) {
			if ( detailVO[i].getIbflag().equals("I")){
				detailVO[i].setCreUsrId(account.getUsr_id());
				detailVO[i].setUpdUsrId(account.getUsr_id());
				insertVoList.add(detailVO[i]);
			} else if (detailVO[i].getIbflag().equals("U")){
				detailVO[i].setUpdUsrId(account.getUsr_id());
				updateVoList.add(detailVO[i]);
			} else if ( detailVO[i].getIbflag().equals("D")){
				deleteVoList.add(detailVO[i]);
			}
		}
		
		if ( insertVoList.size() > 0 ) {
			dbDao.addmanageDetailS(insertVoList);
		}
		
		if ( updateVoList.size() > 0 ) {
			dbDao.modifymanageDetailS(updateVoList);
		}
		
		if ( deleteVoList.size() > 0 ) {
			dbDao.removemanageDetailS(deleteVoList);
		}
	} catch(DAOException ex) {
		throw new EventException(new ErrorHandler(ex).getMessage(),ex);
	} catch (Exception ex) {
		throw new EventException(new ErrorHandler(ex).getMessage(),ex);
	}
		
	}

}


