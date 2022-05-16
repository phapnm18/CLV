/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DetailVO.java
*@FileTitle : DetailVO
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.27  
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.dou.doutraining.codemgmt.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

/**
 * Table Value Ojbect<br>
 * 관련 Event 에서 생성, 서버실행요청시 Data 전달역할을 수행하는 Value Object
 *
 * @author 
 * @since J2EE 1.6
 * @see AbstractValueObject
 */

public class DetailVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;
	
	private Collection<DetailVO> models = new ArrayList<DetailVO>();
	
	/* Column Info */
	private String intgCdId = null;
	/* Column Info */
	private String intgCdValDesc = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String intgCdValDpSeq = null;
	/* Column Info */
	private String intgCdValDpDesc = null;
	/* Column Info */
	private String intgCdValCtnt = null;
	/* Page Number */
	private String pagerows = null;

	/*	테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/*	테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();

	private String creUsrId;

	private String updUsrId;
	
	public DetailVO() {}

	public DetailVO(String ibflag, String pagerows, String intgCdId, String intgCdValCtnt, String intgCdValDpDesc, String intgCdValDesc, String intgCdValDpSeq) {
		this.intgCdId = intgCdId;
		this.intgCdValDesc = intgCdValDesc;
		this.ibflag = ibflag;
		this.intgCdValDpSeq = intgCdValDpSeq;
		this.intgCdValDpDesc = intgCdValDpDesc;
		this.intgCdValCtnt = intgCdValCtnt;
		this.pagerows = pagerows;
	}
	
	/**
	 * 테이블 컬럼에 저장할 값을 Hashtable<"column_name", "value"> 로 반환
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("intg_cd_id", getIntgCdId());
		this.hashColumns.put("intg_cd_val_desc", getIntgCdValDesc());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("intg_cd_val_dp_seq", getIntgCdValDpSeq());
		this.hashColumns.put("intg_cd_val_dp_desc", getIntgCdValDpDesc());
		this.hashColumns.put("intg_cd_val_ctnt", getIntgCdValCtnt());
		this.hashColumns.put("pagerows", getPagerows());
		return this.hashColumns;
	}
	
	/**
	 * 컬럼명에 대응되는 멤버변수명을 저장하여 Hashtable<"column_name", "variable"> 로 반환   
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("intg_cd_id", "intgCdId");
		this.hashFields.put("intg_cd_val_desc", "intgCdValDesc");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("intg_cd_val_dp_seq", "intgCdValDpSeq");
		this.hashFields.put("intg_cd_val_dp_desc", "intgCdValDpDesc");
		this.hashFields.put("intg_cd_val_ctnt", "intgCdValCtnt");
		this.hashFields.put("pagerows", "pagerows");
		return this.hashFields;
	}
	
	/**
	 * Column Info
	 * @return intgCdId
	 */
	public String getIntgCdId() {
		return this.intgCdId;
	}
	
	/**
	 * Column Info
	 * @return intgCdValDesc
	 */
	public String getIntgCdValDesc() {
		return this.intgCdValDesc;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @return ibflag
	 */
	public String getIbflag() {
		return this.ibflag;
	}
	
	/**
	 * Column Info
	 * @return intgCdValDpSeq
	 */
	public String getIntgCdValDpSeq() {
		return this.intgCdValDpSeq;
	}
	
	/**
	 * Column Info
	 * @return intgCdValDpDesc
	 */
	public String getIntgCdValDpDesc() {
		return this.intgCdValDpDesc;
	}
	
	/**
	 * Column Info
	 * @return intgCdValCtnt
	 */
	public String getIntgCdValCtnt() {
		return this.intgCdValCtnt;
	}
	
	/**
	 * Page Number
	 * @return pagerows
	 */
	public String getPagerows() {
		return this.pagerows;
	}
	

	/**
	 * Column Info
	 * @param intgCdId
	 */
	public void setIntgCdId(String intgCdId) {
		this.intgCdId = intgCdId;
	}
	
	/**
	 * Column Info
	 * @param intgCdValDesc
	 */
	public void setIntgCdValDesc(String intgCdValDesc) {
		this.intgCdValDesc = intgCdValDesc;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @param ibflag
	 */
	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}
	
	/**
	 * Column Info
	 * @param intgCdValDpSeq
	 */
	public void setIntgCdValDpSeq(String intgCdValDpSeq) {
		this.intgCdValDpSeq = intgCdValDpSeq;
	}
	
	/**
	 * Column Info
	 * @param intgCdValDpDesc
	 */
	public void setIntgCdValDpDesc(String intgCdValDpDesc) {
		this.intgCdValDpDesc = intgCdValDpDesc;
	}
	
	/**
	 * Column Info
	 * @param intgCdValCtnt
	 */
	public void setIntgCdValCtnt(String intgCdValCtnt) {
		this.intgCdValCtnt = intgCdValCtnt;
	}
	
	/**
	 * Page Number
	 * @param pagerows
	 */
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request) {
		fromRequest(request,"");
	}

	/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request, String prefix) {
		setIntgCdId(JSPUtil.getParameter(request, prefix + "intg_cd_id", ""));
		setIntgCdValDesc(JSPUtil.getParameter(request, prefix + "intg_cd_val_desc", ""));
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setIntgCdValDpSeq(JSPUtil.getParameter(request, prefix + "intg_cd_val_dp_seq", ""));
		setIntgCdValDpDesc(JSPUtil.getParameter(request, prefix + "intg_cd_val_dp_desc", ""));
		setIntgCdValCtnt(JSPUtil.getParameter(request, prefix + "intg_cd_val_ctnt", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
	}

	/**
	 * Request 의 데이터를 VO 배열로 변환하여 반환.
	 * @param request
	 * @return DetailVO[]
	 */
	public DetailVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다. 
	 * @param request
	 * @param prefix
	 * @return DetailVO[]
	 */
	public DetailVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		DetailVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] intgCdId = (JSPUtil.getParameter(request, prefix	+ "intg_cd_id", length));
			String[] intgCdValDesc = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_desc", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] intgCdValDpSeq = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_dp_seq", length));
			String[] intgCdValDpDesc = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_dp_desc", length));
			String[] intgCdValCtnt = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_ctnt", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			
			for (int i = 0; i < length; i++) {
				model = new DetailVO();
				if (intgCdId[i] != null)
					model.setIntgCdId(intgCdId[i]);
				if (intgCdValDesc[i] != null)
					model.setIntgCdValDesc(intgCdValDesc[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (intgCdValDpSeq[i] != null)
					model.setIntgCdValDpSeq(intgCdValDpSeq[i]);
				if (intgCdValDpDesc[i] != null)
					model.setIntgCdValDpDesc(intgCdValDpDesc[i]);
				if (intgCdValCtnt[i] != null)
					model.setIntgCdValCtnt(intgCdValCtnt[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getDetailVOs();
	}

	/**
	 * VO 배열을 반환
	 * @return DetailVO[]
	 */
	public DetailVO[] getDetailVOs(){
		DetailVO[] vos = (DetailVO[])models.toArray(new DetailVO[models.size()]);
		return vos;
	}
	
	/**
	 * VO Class의 내용을 String으로 변환
	 */
	public String toString() {
		   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );
	   }

	/**
	* 포맷팅된 문자열에서 특수문자 제거("-","/",",",":")
	*/
	public void unDataFormat(){
		this.intgCdId = this.intgCdId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDesc = this.intgCdValDesc .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDpSeq = this.intgCdValDpSeq .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDpDesc = this.intgCdValDpDesc .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValCtnt = this.intgCdValCtnt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}

	public void setCreUsrId(String creUsrId) {
		this.creUsrId = creUsrId;
	}

	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
		
	}


	
}
