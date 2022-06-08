var sheetObjects=new Array();

var sheetCnt=0;

document.onclick=processButtonClick;

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
			case "btn_OK":
				comPopupOK();
				break;
			case "btn_Close":
        		ComClosePopup(); 
        		break;
			}
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
			var HeadTitle = "Select|Country|Country|Sequence|Legacy Customer Name|Short Name|Ofc cd|Loc cd";
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
				
			var cols = [ 
	             {Type	: "Radio",    Hidden : 0, Width : 50,  Align : "Center", SaveName:"radio",          	KeyField : 0, UpdateEdit : 1, InsertEdit : 1}, 	 
	             { Type : "Text", 	  Hidden : 0, Width : 100, Align : "Center", SaveName : "cust_cnt_cd", 		KeyField : 1, UpdateEdit : 0, InsertEdit : 0}, 
	             { Type : "Text", 	  Hidden : 0, Width : 100, Align : "Center", SaveName : "cust_cnt_cd", 		KeyField : 1, UpdateEdit : 0, InsertEdit : 0},
	             { Type : "Text", 	  Hidden : 0, Width : 100, Align : "Center", SaveName : "cust_seq",  		KeyField : 1, UpdateEdit : 0, InsertEdit : 0}, 
	             { Type : "Text",     Hidden : 0, Width : 180, Align : "Center", SaveName : "cust_lgl_eng_nm",  KeyField : 1, UpdateEdit : 0, InsertEdit : 0}, 
	             { Type : "Text",     Hidden : 0, Width : 600, Align : "Center", SaveName : "cust_abbr_nm",     KeyField : 1, UpdateEdit : 0, InsertEdit : 0},
	             { Type : "Text",     Hidden : 0, Width : 600, Align : "Center", SaveName : "ofc_cd", 			KeyField : 1, UpdateEdit : 0, InsertEdit : 0}, 
	             { Type : "Text",     Hidden : 0, Width : 100, Align : "Center", SaveName : "loc_cd", 			KeyField : 1, UpdateEdit : 0, InsertEdit : 0} 
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
//	function initialization doActionIBSheet
function doActionIBSheet(sheetObj,formObj,sAction) {
	sheetObj.ShowDebugMsg(false);

	switch (sAction) {
	case IBSEARCH: // retrieve
		formObj.f_cmd.value = SEARCH02;
		ComOpenWait(true);
		//	perform the search after receiving the corresponding action 	
		sheetObj.DoSearch("CUSTOMERGS.do", FormQueryString(formObj) );
		break;
	case IBSAVE: // retrieve
		if (!validateForm(sheetObj, formObj, sAction)) return;
		formObj.f_cmd.value = MULTI;
		//	perform the save after receiving the corresponding action
		sheetObj.DoSave("CUSTOMERGS.do", FormQueryString(formObj));
		break;

		
	}
}
//	declare function onsearchEnd
function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
 	ComOpenWait(false);
}

    
