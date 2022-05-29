/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMngtDBDAOErrMsgVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.18
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.18 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice1.errmsgmngt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Phap Nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */
//	declare class ErrMsgMngtDBDAOErrMsgVORSQL implements ISQLTemplate
public class ErrMsgMngtDBDAOErrMsgVORSQL implements ISQLTemplate{
	//	query string declaration
	private StringBuffer query = new StringBuffer();
	//	create a logger named log to log
	Logger log =Logger.getLogger(this.getClass());

	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  *    
	  * </pre>
	  */
	//	declare function ErrMsgMngtDBDAOErrMsgVORSQL
	public ErrMsgMngtDBDAOErrMsgVORSQL(){
		//	call the function seQuery
		setQuery();
		//	create a params by hashmap
		params = new HashMap<String,String[]>();
		//	create a temporary string
		String tmp = null;
		//	create a temporary array
		String[] arrTmp = null;
		//	definition for string
		tmp = java.sql.Types.VARCHAR + ",N";
		//	separate tmp array by comma
		arrTmp = tmp.split(",");
		//	check the length of the array
		if(arrTmp.length !=2){
			//	throw an exception stating that the array is not valid
			throw new IllegalArgumentException();
		}
		//	pass value to params
		params.put("err_msg",new String[]{arrTmp[0],arrTmp[1]});
		//	definition for string
		tmp = java.sql.Types.VARCHAR + ",N";
		//	separate tmp array by comma
		arrTmp = tmp.split(",");
		//	check the length of the array
		if(arrTmp.length !=2){
			//	throw an exception stating that the array is not valid
			throw new IllegalArgumentException();
		}
		//	pass value to params
		params.put("err_msg_cd",new String[]{arrTmp[0],arrTmp[1]});
		//	add value to current string
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.practice1.errmsgmngt.integration").append("\n"); 
		query.append("FileName : ErrMsgMngtDBDAOErrMsgVORSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	//	create a function getSQL
	public String getSQL(){
		//	returns the query into query
		return query.toString();
	}
	//	create function getParams
	public HashMap<String,String[]> getParams() {
		//	returns params
		return params;
	}

	/**
	 * Query creation
	 */
	public void setQuery(){
		query.append("select " ).append("\n"); 
		query.append("    err_msg_cd," ).append("\n"); 
		query.append("    err_tp_cd," ).append("\n"); 
		query.append("    err_lvl_cd," ).append("\n"); 
		query.append("    err_msg," ).append("\n"); 
		query.append("    err_desc," ).append("\n"); 
		query.append("    cre_usr_id," ).append("\n"); 
		query.append("    upd_usr_id" ).append("\n"); 
		query.append("from com_err_msg" ).append("\n"); 
		query.append("where 1 = 1" ).append("\n"); 
		query.append("#if (${err_msg_cd} != '')" ).append("\n"); 
		query.append("AND err_msg_cd LIKE '%'||@[err_msg_cd]||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${err_msg} != '')" ).append("\n"); 
		query.append("AND UPPER(err_msg) LIKE '%'||UPPER(@[err_msg])||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 

	}
}