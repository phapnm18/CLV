/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : SearchDBDAOCarrierVODSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.31
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.31 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.practice4.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Phap Nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierDBDAOSearchVODSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * del
	  * </pre>
	  */
	public CarrierDBDAOSearchVODSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("jo_crr_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("rlane_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.practice4.integration ").append("\n"); 
		query.append("FileName : SearchDBDAOCarrierVODSQL").append("\n"); 
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
		query.append("delete " ).append("\n"); 
		query.append("	from joo_carrier" ).append("\n"); 
		query.append("where jo_crr_cd = @[jo_crr_cd]" ).append("\n"); 
		query.append("  and rlane_cd = @[rlane_cd]" ).append("\n"); 

	}
}