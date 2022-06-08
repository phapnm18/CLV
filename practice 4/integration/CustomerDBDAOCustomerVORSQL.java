/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CustomerDBDAOCustomerVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.06 
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

public class CustomerDBDAOCustomerVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Select
	  * </pre>
	  */
	public CustomerDBDAOCustomerVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("cust_seq",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("cust_cnt_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.practice4.integration").append("\n"); 
		query.append("FileName : CustomerDBDAOCustomerVORSQL").append("\n"); 
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
		query.append("SELECT" ).append("\n"); 
		query.append("    mdm_customer.cust_cnt_cd," ).append("\n"); 
		query.append("    mdm_customer.cust_seq," ).append("\n"); 
		query.append("    mdm_customer.cust_lgl_eng_nm," ).append("\n"); 
		query.append("    mdm_customer.cust_abbr_nm," ).append("\n"); 
		query.append("    mdm_customer.ofc_cd," ).append("\n"); 
		query.append("    mdm_customer.loc_cd" ).append("\n"); 
		query.append("FROM" ).append("\n"); 
		query.append("    mdm_customer" ).append("\n"); 
		query.append("where 1 = 1" ).append("\n"); 
		query.append("#if (${cust_cnt_cd} != '')" ).append("\n"); 
		query.append("AND mdm_customer.cust_cnt_cd LIKE '%'||UPPER(@[cust_cnt_cd])||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${cust_seq} != '')" ).append("\n"); 
		query.append("AND UPPER(mdm_customer.cust_seq) LIKE '%'||@[cust_seq]||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 

	}
}