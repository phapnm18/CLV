/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMngtDBDAOErrMsgVOCSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.15
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.15 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice1.errmsgmngt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Phap Nguyen
 * @see DAO  
 * @since J2EE 1.6
 */
//	declare class ErrMsgMngtDBDAOErrMsgVOCSQL
public class ErrMsgMngtDBDAOErrMsgVOCSQL implements ISQLTemplate{
	//	create a private stringbuffer query,which can change the length 
	private StringBuffer query = new StringBuffer();
	//	create a logger named log to log
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  */
	//	declare function ErrMsgMngtDBDAOErrMsgVOCSQL
	public ErrMsgMngtDBDAOErrMsgVOCSQL(){
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
		params.put("err_tp_cd",new String[]{arrTmp[0],arrTmp[1]});
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
		params.put("err_lvl_cd",new String[]{arrTmp[0],arrTmp[1]});
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
		params.put("upd_usr_id",new String[]{arrTmp[0],arrTmp[1]});
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
		params.put("err_desc",new String[]{arrTmp[0],arrTmp[1]});
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
		params.put("cre_usr_id",new String[]{arrTmp[0],arrTmp[1]});
		//	add value to current string
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.practice1.errmsgmngt.integration").append("\n"); 
		query.append("FileName : ErrMsgMngtDBDAOErrMsgVOCSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	//	create a function getSQL
	public String getSQL(){
		//	returns a string of the query
		return query.toString();
	}
	//	create function getParams
	public HashMap<String,String[]> getParams() {
		//		returns params
		return params;
	}

	/**
	 * Query creation
	 */
	public void setQuery(){
		query.append("INSERT INTO com_err_msg (err_msg_cd,lang_tp_cd,err_lvl_cd,err_tp_cd,err_msg,err_desc,cre_usr_id,cre_dt,upd_usr_id,upd_dt)" ).append("\n"); 
		query.append("VALUES (@[err_msg_cd],'ENG',@[err_lvl_cd],@[err_tp_cd],@[err_msg],@[err_desc],@[cre_usr_id],sysdate,@[upd_usr_id],sysdate)" ).append("\n"); 

	}
}