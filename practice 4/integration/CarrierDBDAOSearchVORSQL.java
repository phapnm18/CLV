/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : SearchDBDAOCarrierVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.07
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.07 
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

public class CarrierDBDAOSearchVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * select
	  * </pre>
	  */
	public CarrierDBDAOSearchVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("vndr_seq",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("cre_dt_to",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("cre_dt_fr",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.practice4.integration").append("\n"); 
		query.append("FileName : SearchDBDAOCarrierVORSQL").append("\n"); 
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
		query.append("SELECT A.JO_CRR_CD" ).append("\n"); 
		query.append("     , A.RLANE_CD" ).append("\n"); 
		query.append("     , A.VNDR_SEQ" ).append("\n"); 
		query.append("     , A.CUST_CNT_CD" ).append("\n"); 
		query.append("     , A.CUST_SEQ" ).append("\n"); 
		query.append("     , A.TRD_CD" ).append("\n"); 
		query.append("     , A.DELT_FLG" ).append("\n"); 
		query.append("     , TO_CHAR(A.CRE_DT, 'YYYY/MM/DD HH24:MI:SS') AS CRE_DT" ).append("\n"); 
		query.append("     , A.CRE_USR_ID" ).append("\n"); 
		query.append("     , TO_CHAR(A.UPD_DT, 'YYYY/MM/DD HH24:MI:SS') AS UPD_DT" ).append("\n"); 
		query.append("     , A.UPD_USR_ID" ).append("\n"); 
		query.append("  FROM JOO_CARRIER A" ).append("\n"); 
		query.append(" WHERE 1 = 1" ).append("\n"); 
		query.append(" #if (${jo_crr_cd}!='' && ${jo_crr_cd} != 'ALL')" ).append("\n"); 
		query.append("    AND A.JO_CRR_CD    IN ( #foreach($key IN ${obj_list_no})#if($velocityCount < $obj_list_no.size()) '$key', #else '$key' #end #end)" ).append("\n"); 
		query.append("  #end" ).append("\n"); 
		query.append("  #if (${vndr_seq}!='')" ).append("\n"); 
		query.append("    AND A.VNDR_SEQ = @[vndr_seq]" ).append("\n"); 
		query.append("  #end	" ).append("\n"); 
		query.append("  #if (${cre_dt_fr}!='')" ).append("\n"); 
		query.append("    AND A.CRE_DT >=to_date(@[cre_dt_fr],'YYYY-MM-DD')" ).append("\n"); 
		query.append("  #end	" ).append("\n"); 
		query.append("  #if (${cre_dt_to}!='')" ).append("\n"); 
		query.append("    AND A.CRE_DT <= to_date(@[cre_dt_to],'YYYY-MM-DD')" ).append("\n"); 
		query.append("  #end" ).append("\n"); 

	}
}