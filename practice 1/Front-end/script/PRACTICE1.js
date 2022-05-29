/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE1.js
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.13 
* 1.0 Creation
=========================================================*/
/****************************************************************************************
 Event identification code: [Initialization]INIT=0; [input]ADD=1; [Query]SEARCH=2; [List inquiry]SEARCHLIST=3;
[Edit] MODIFY=4; [Delete]REMOVE=5; [Remove list]REMOVELIST=6 [Multiprocessing]MULTI=7
Other extra character constants COMMAND01=11; ~COMMAND20=30;
 ***************************************************************************************/

/*------------------The following code is added to make JSDoc well. ------------------*/
   /**
     * @fileoverview It is a JavaScript file commonly used in work, and calendar-related functions are defined.
     * @author 
     */

    /**
     * @extends 
     * @class PRACTICE1 : Defines the business script used in the screen for generating PRACTICE1.
     */
//initialize the array of sheets Object
   var sheetObjects=new Array();
 //initialize the variable sheetCnt
var sheetCnt=0;
//initiate onclick event (Event fires when user clicks a cell in data area)
document.onclick=processButtonClick;
//initialize function ButtonClick
function processButtonClick() {
	/** *** setting sheet object **** */
	var sheetObject1 = sheetObjects[0];
	/** setting form Object**/
	var formObj = document.form;
	//	exception handling
	try {
		//	declare variable srcName 	
		var srcName = ComGetEvent("name");
		//	checks for null and returns the original value
		if (srcName == null) {
			return;
		}
		//	check all instances of srcName to give the corresponding command block
		switch (srcName) {
				//	take corresponding action with IBSEARCH when the retrieve button is recognized
			case "btn_Retrieve":
				doActionIBSheet(sheetObject1, formObj, IBSEARCH);
				break;
				//	take corresponding action with IBSAVE when the retrieve button is recognized
			case "btn_Save":
				doActionIBSheet(sheetObject1, formObj, IBSAVE);
				break;
				//	take corresponding action with IBDOWNEXCEL when the retrieve button is recognized
			case "btn_DownExcel":
				doActionIBSheet(sheetObject1, formObj, IBDOWNEXCEL);
				break;
				//	take corresponding action with IBINSERT when the retrieve button is recognized
			case "btn_Add":
				doActionIBSheet(sheetObject1, formObj, IBINSERT);
				break;
				
			default :
				break;}
	}
	//	exception handling
	catch (e) {
		if (e == "[object Error]") {
			//	Display a dialog box without lines in brackets
			ComShowCodeMessage('JOO00001');
		} else {
			//	display a dialog box with the corresponding error
			ComShowMessage(e.message);
		}
	}

}
// function loadPage 
function loadPage(){
	//generate Grid Layout
	for (i = 0; i < sheetObjects.length; i++) {
		ComConfigSheet(sheetObjects[i]);
		initSheet(sheetObjects[i], i + 1);
		ComEndConfigSheet(sheetObjects[i]);
	}
	
	//auto search data after loading finish.
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

//	declare function initSheet
function initSheet(sheetObj,sheetNo) {
	//	initialize the variable cnt
	var cnt = 0;
	switch (sheetNo) {
	case 1: // sheet1 init
		with (sheetObj) {
			//	Initialize column headers
			var HeadTitle = "STS|Del|Msg Cd|Msg Type|Msg Level|Message|Description";
			//	initialize the variable that counts titles
			var headCount = ComCountHeadTitle(HeadTitle);
			// (headCount, 0, 0, true);
				/** configure how to fetch initialized sheet, location of frozen rows or
				 *	columns and other basic configurations.
				 *	Configure search mode (Default: 2)
				 *	MergeSheet: Merge type (Default=0)
				 *	Page: Number of rows to display in one page(Default=20)
				 *	DataRowMerge: Whether to allow horizontal merge of the entire row (Default=0)
				 **/
			SetConfig({SearchMode : 2, MergeSheet : 5, Page : 20 , DataRowMerge : 1});
				/**	sort : allows to sort columns in order
				 *	colmove: move and drop data columns
				 *	headercheck: headercheck: check the headers of the columns
				 *	colresize: allow expanding and shrinking data columns
				 **/
			var info = {Sort : 1, ColMove : 1, HeaderCheck : 0, ColResize : 1};
			//	alignment for titles
			var headers = [ { Text : HeadTitle, Align : "Center" }];
			InitHeaders(headers, info);
				/**
				 *	format the columns in the table
				 *	type: data type of the table
				 *	hidden: allows to hide columns or not
				 *	width: the width of the column
				 *	align: align text
				 *	savename: the name saved in the db
				 *	updateedit,insertedit: enable to update or insert
				 *	combotext: displayed when a combo is released
				 *	combocode: displayed after click on combobox
				 **/
			var cols = [ 
	             { Type : "Status",   Hidden : 1, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "ibflag" }, 
	             { Type : "DelCheck", Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "del_chk" }, 
	             { Type : "Text", 	  Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_msg_cd", KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1}, 
	             { Type : "Combo", 	  Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_tp_cd",  KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1, ComboText:"Server|UI|Both" ,  ComboCode:"S|U|B",}, 
	             { Type : "Combo",    Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_lvl_cd", KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1, ComboText:"WARNING|ERR|INFO" ,ComboCode:"E|W|I",}, 
	             { Type : "Text",     Hidden : 0, Width : 600, Align : "Left",   ColMerge : 0, SaveName : "err_msg", 	KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 }, 
	             { Type : "Text",     Hidden : 0, Width : 100, Align : "Left",   ColMerge : 0, SaveName : "err_desc", 	KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1} 
	             ];
			//	define properties of a particular cell from default column property sets.
			InitColumns(cols);
			SetEditable(1);
			SetWaitImageVisible(0);
			resizeSheet();
		}
		break;
	}

}
// declare function resize
function resizeSheet() {
	ComResizeSheet(sheetObjects[0]);
}
//	declare setSheetObject,assign value to array sheetObjects
function setSheetObject(sheet_obj) {
	sheetObjects[sheetCnt++] = sheet_obj;
}
//declare setComboObject,assign value to array comboObjects
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
}
//	function initialization doActionIBSheet
function doActionIBSheet(sheetObj,formObj,sAction) {
	sheetObj.ShowDebugMsg(false);

	switch (sAction) {
	case IBSEARCH: // retrieve
		formObj.f_cmd.value = SEARCH;
		ComOpenWait(true);
		//	perform the search after receiving the corresponding action
		sheetObj.DoSearch("Practice1GS.do", FormQueryString(formObj) );
		break;
	case IBSAVE: // retrieve
		if (!validateForm(sheetObj, formObj, sAction)) return;
		formObj.f_cmd.value = MULTI;
		//	perform the save after receiving the corresponding action
		sheetObj.DoSave("Practice1GS.do", FormQueryString(formObj));
		break;
	case IBINSERT: //Row Add button event
		//	perform the insert after receiving the corresponding action
		sheetObj.DataInsert();
		break;
	case IBDOWNEXCEL: // Downexcel button event
		//	check the rows of the data table
		if(sheetObj.RowCount() < 1){
			ComShowCodeMessage("COM132501");
		}
		else{
			//	perform excel download skip hidden column
			sheetObj.Down2Excel({DownCols : makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
		}
		
		break; 
		
	}
}
//	declare function onsearchEnd
function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
 	ComOpenWait(false);
}

//Handling validate
function validateForm(sheetObj,formObj,sAction){
	var regrex = new RegExp("^[A-Z]{3}[0-9]{5}$");
	for (var i = sheetObj.HeaderRows(); i <= sheetObj.LastRow(); i++){
		if (sheetObj.GetCellValue(i, "ibflag") == 'I' && !regrex.test(sheetObj.GetCellValue(i,"err_msg_cd"))){
			//ComShowMessage('Invalid ErrMsgCd: ErrMsgCd 8 characters are required, the first 3 characters are uppercase letters, the last 5 characters are numbers.');
			ComShowCodeMessage("COM131302", "Message Code");
			return false;
		}
	}
	return true;
}
//	declare function onsaveend
function sheet1_OnSaveEnd(Code,Msg) {
	//	check if msg exists
	if (Msg>=0) {
		//	successful save message
		alert('Save Successfully');
		//	automatic page reload
		doActionIBSheet(sheetObject[0], document.form, IBSEARCH);
		}
	else {
		//	fail save message
		alert('Fail to save');
		}
}
//	main function declaration constructor
    function PRACTICE1() {
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }
    
