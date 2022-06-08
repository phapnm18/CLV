/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierComboDBDAOCarrierVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.01
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.01 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.practice4.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Phap Nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierDBDAOCarrierComboVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * select
	  * </pre>
	  */
	public CarrierDBDAOCarrierComboVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.practice4.integration").append("\n"); 
		query.append("FileName : CarrierComboDBDAOCarrierVORSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("select distinct(jo_crr_cd)" ).append("\n"); 
		query.append("from joo_carrier" ).append("\n"); 

	}
}