

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.practice4.event.DouTrn004Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	DouTrn004Event  event = null;					//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			// Errors from server.
	String strErrMsg = "";
	int rowCount	 = 0;	
					
	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";
	
	String strUsr_id		= "";
	String strUsr_nm		= "";
	String carrier 			= "";
	String rlane 			= "";
	

	try {

		event = (DouTrn004Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}
		
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		  carrier = eventResponse.getETCData("carriers");
		  rlane   = eventResponse.getETCData("rlanes");

	}catch(Exception e) {
		System.out.println(e.toString());
	}
%>

<script type="text/javascript">
	var Combo = "ALL | <%=carrier%>";
	var Rlane =       " |<%=rlane%>";
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		} // end if
		loadPage();
	}
</script>

<body  onLoad="setupPage();">
<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">
<input type="hidden" name="value_partner">
<div class="page_title_area clear">
		<h2 class="page_title"><button type="button"><span id="title">PRACTICE 4</span></button></h2>

	</div>
	    <!-- opus_design_btn(S) -->
	    <div class="opus_design_btn"><!--
	    --><button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!--
	    --><button type="button" class="btn_accent" name="btn_New"   id="btn_New">New</button><!--
	    --><button type="button" class="btn_accent" name="btn_Save" id="btn_Save">Save</button><!--
	    --><button type="button" class="btn_accent" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button>
    </div>
		<div class="wrap_search">
		<div class="opus_design_inquiry">
			<table>
				<tbody>
					<tr>
						<th width="50">Carrier</th>
						<td width="120">
							<script type="text/javascript">ComComboObject('s_carrier', 1, 120, 1, 0, 0);</script>
						</td>

						<th width="50">Vendor</th>
						<td width="120">
							<input type="text" style="width: 120px;"class="input" value="" name="s_vndr_seq" id="s_vndr_seq"    placeholder="  Max 6 Numbers">
						</td>

						<th width="100">Create Date</th>
						<td>	<input type="text" style="width: 120px; text-align: center;" name="s_cre_dt_fm" id="s_cre_dt_fm" placeholder="YYYY-MM-DD"><!--  
							--><button type="button" class="calendar ir" name="btn_calendar_dt_fr" id="btn_calendar_dt_fr"></button> ~ 
								<input type="text" style="width: 120px; text-align: center;" name="s_cre_dt_to" id="s_cre_dt_to" placeholder="YYYY-MM-DD"><!-- 
							--><button type="button" class="calendar ir" name="btn_calendar_dt_to" id="btn_calendar_dt_to"></button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

<div class="wrap_result">
		<div class="opus_design_grid">
			<div class="opus_design_btn">
				<button type="button" class="btn_accent" name="btn_Delete" id="btn_Delete">Row Delete</button><!-- 
				--><button type="button" class="btn_accent" name="btn_Add" id="btn_Add">Row Add</button>
			</div>
			<script language="javascript">
				ComSheetObject('sheet1');
			</script>
		</div>
	</div>
</form>
</body>