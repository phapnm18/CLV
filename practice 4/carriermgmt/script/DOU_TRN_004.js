/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_004.js
*@FileTitle : Carrier Joint Operation Management
*Open Issues : No
*Change history :Change comment
*@LastModifyDate : 2022.06.07
*@LastModifier :NguyenMinhPhap
*@LastVersion : 1.0
* 1.0 Creation
=========================================================*/
	var sheetObjects=new Array();
	var comboObjects = new Array();
	var comboCnt = 0;
	var sheetCnt=0;
	var rowcount=0;
	var checkCnt=0;
	var comboCnt=0;
	
	var ROWMARK="|";
	var FIELDMARK="=";
	
	var rdObjects=new Array();
	var rdCnt=0;	
	
	// Event handler when click button.
	document.onclick=processButtonClick;
	/**
	 * This function handler button.
	 * */ 
	function processButtonClick(){
		var sheetObject1=sheetObjects[0];
		var formObject=document.form;
		try {
			// Get event name which corresponding to button.
			var srcName=ComGetEvent("name");
			switch(srcName) {
			// Retrieve button
			case "btn_Retrieve":
				doActionIBSheet(sheetObject1,formObject,IBSEARCH);
				break; 
			// New button
			case "btn_New":
				sheetObject1.RemoveAll();
				break;
			// Save button
			case "btn_Save":
            	doActionIBSheet(sheetObject1,formObject,IBSAVE);
            	break;
            // Row Add button
			case "btn_Add":
            	doActionIBSheet(sheetObject1,formObject,IBINSERT);
            	break;
            // Row Delete button
			case "btn_Delete":
            	doActionIBSheet(sheetObject1,formObject,IBDELETE);
            	break;
            // Down Excel button
			case "btn_DownExcel":
				doActionIBSheet(sheetObject1, formObject, IBDOWNEXCEL);
				break;
			// Calendar button date from.
			case "btn_calendar_dt_fr": 
				var cal=new ComCalendar();
				cal.select(formObject.s_cre_dt_fm, 'yyyy-MM-dd');
				break;
			// Calendar button date to.
			case "btn_calendar_dt_to": 
				var cal=new ComCalendar();
				cal.select(formObject.s_cre_dt_to, 'yyyy-MM-dd');
				break;	
			} // end switch
	}
	catch(e) {
			if( e == "[object Error]") {
				ComShowMessage(OBJECT_ERROR);
			} else {
				ComShowMessage(e.message);
			}
		}
	}
	/**
	 * Registering IBSheet Object as list adding process for list in case of needing
	 * batch processing with other items defining list on the top of source.
	 * 
	 * @param sheet_obj: String - sheet name.
	 * */
	function setSheetObject(sheet_obj){
		sheetObjects[sheetCnt++]=sheet_obj;
	}
	/**
	 *  This function calls a common function that sets the default settings of the sheet,
	 *  It is the first called area when file *jsp onload event.
	 * */
	function loadPage(){
		// Generate dopdownlist data combo box.
		for ( var k = 0; k < comboObjects.length; k++) {
			initCombo(comboObjects[k], k + 1);
		}
		s_carrier.SetSelectIndex(0,0);
		// Generate Grid Layout.
		for(i = 0; i < sheetObjects.length; i++) {
			ComConfigSheet(sheetObjects[i]);
			initSheet(sheetObjects[i], i + 1);
			ComEndConfigSheet(sheetObjects[i]);
		}
		// Search data after loading finish
		doActionIBSheet(sheetObjects[0],document.form,IBSEARCH);
		
		s_cre_dt_fm.disabled = true;
		s_cre_dt_to.disabled = true;
		initControl();
	}
	function setComboObject(combo_obj) {
		comboObjects[comboCnt++] = combo_obj;
	}
	/**
	 * This function that define the basic properties of the combo.
	 * 
	 * @param comboObj:    IBSheet Object.
	 * @param comboNo :    Number of IBMultiCombo Object.
	 * */
	function initCombo(comboObj, comboNo) {
		var formObj = document.form
		switch (comboNo) {
		case 1:
			with (comboObj) {
				SetMultiSelect(1);
		        SetDropHeight(200);
			}
			// Split all components to array.
			var comboItems = Combo.split("|");
			// Then add content to combo.
			addComboItem(comboObj, comboItems);
			
		}
	}
	/**
	 * This function adding data to combo field.
	 * Web IBMultiCombo object.InsertItem(Index, Text, Code);
	 * 
	 * @param comboObj:   - IBMultiCombo Object name.
	 * @param comboItems: - Item ComboText.
	 */
	function addComboItem(comboObj, comboItems) {
		for (var i=0 ; i < comboItems.length ; i++) {
			var comboItem=comboItems[i].split(",");
			if(comboItem.length == 1){
				comboObj.InsertItem(i, comboItem[0], comboItem[0]);
			}
			else
			{
				comboObj.InsertItem(i, comboItem[0] + "|" + comboItem[1], comboItem[1]);
			}
		}   		
	}
	/**
	 * This function check options of check box.
	 * Event fires when Carrier check box is clicked, if multiple selection is used.
	 * GetItemCheck(): Select a specific item in the check box as an index .
	 * GetItemCount(): Return the total number of columns.
	 * SetItemCheck(): Get the status of item selection.
	 * 
	 * @param comboObj:  - IBMultiCombo Object name.
	 * @param index:     - Index value of the clicked item.
	 * @param code:      - Code value of the clicked item.
	 * */	
	function s_carrier_OnCheckClick(Index, Code, Checked) {
		var count = s_carrier.GetItemCount();
		var checkSelectCount = 0;
		if (Code == 0){
			var bChk = s_carrier.GetItemCheck(Code);
	        if(bChk){
	            for(var i=1 ; i < count ; i++) {
	            	s_carrier.SetItemCheck(i,false);
	            }
	        }
		}else {
	        var bChk=s_carrier.GetItemCheck(Code);
	        if (bChk) {
	        	s_carrier.SetItemCheck(0,false);
	        }
	    }

		for (var i = 0; i < count; i++){
			if (s_carrier.GetItemCheck(i))
				checkSelectCount += 1;
		}
		 if(checkSelectCount == 0) {
			 s_carrier.SetItemCheck(0,true,false);
		 }

	}
	function sheet1_OnPopupClick(sheetObj,col,row)
	{
		 var s1 = sheetObjects[0].ColSaveName(row);
		 switch(s1){
		 case "trd_cd":
			// This function open the pop-up
				// url : the url of the popup to be called
				// width: the width of the popup
				// height: the height of the popup
				// func: func return data to parent window
				// display: whether column of the grid in popup is hidden (1: visible, 0: invisible) 
				// bModal: whether the popup is modal (default: false)
			 ComOpenPopup('/opuscntr/COM_COM_0012.do',870, 570, 'setTrd', '1,0,1,1,1,1,1',true, col, row);
			 break;
		 case "jo_crr_cd":
			ComOpenPopup('/opuscntr/COM_ENS_0N1.do',870, 570, 'setCrrCd', '1,0,1,1,1',true, col, row);
			break;
		 case "vndr_seq":
			ComOpenPopup('/opuscntr/COM_COM_0007.do',870, 570, 'setVdrCd', '1,0,1,1,1,1,1,1',true);
			break;
		 case "cust_cnt_cd":
		 case "cust_seq":
			ComOpenPopup('/opuscntr/CUSTOMER.do',870, 570, 'setCust', '1,0,1,1,1,1,1', true, col, row);
			break;
			
		 }
	}
	function setTrd(aryPopupData, row, col){
		var sheetObject=sheetObjects[0];
		sheetObject.SetCellValue(sheetObject.GetSelectRow(),"trd_cd",aryPopupData[0][3]);
	}
	
	function setCrrCd(aryPopupData, row, col){
		var sheetObject=sheetObjects[0];
		sheetObject.SetCellValue(sheetObject.GetSelectRow(),"jo_crr_cd",aryPopupData[0][3]);
	}
	
	function setVdrCd(aryPopupData, row, col){
		var sheetObject=sheetObjects[0];
		sheetObject.SetCellValue(sheetObject.GetSelectRow(),"vndr_seq",aryPopupData[0][2]);
	}
	function setCust(aryPopupData, row, col){
		var sheetObject=sheetObjects[0];
		sheetObject.SetCellValue(sheetObject.GetSelectRow(),"cust_cnt_cd",aryPopupData[0][2]);
		sheetObject.SetCellValue(sheetObject.GetSelectRow(),"cust_seq",aryPopupData[0][3]);
	}
	
	/**
	 * This function initSheet define the basic properties of the sheet on the screen.
	 * 
	 * @param sheetObj: IBSheet Object.
	 * @param sheetNo:  Number of IBSheet Object.
	 * */
	function initSheet(sheetObj,sheetNo) {
		var cnt=0;
		switch(sheetObj.id) {
		case "sheet1":      //sheet1 init
			with(sheetObj){
				  /**
				 	* setting for header
				 	* 
				 	* @param Text:  String of texts to display in header,adjoined by "|"
				 	* @param Align: String How to align header text, value default: "Center"
				 	* */
			      var HeadTitle1="STS|Chk|Carrier|Rev.Lane|Vendor Code|Custommer Code|Custommer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
			      var headers = [ { Text:HeadTitle1, Align:"Center"} ];
			      var headCount=ComCountHeadTitle(HeadTitle1);
			      /**
					 * This function SetConfig Configure how to fetch initialized sheet, location of frozen rows or columns and other basic configurations.
					 * 
					 * @param SearchMode:   Is where you can configure search mode by selecting one from General, Paging,LazyLoad or real-time server processing modes, 
					 *                      the default value is: 0 load all data|1 load by page mode|2 is lazy load.
					 * @param Page:         Number of rows to display in one page (default:20).
					 * @param MergeSheet:   Is where you can configure merge styles, The default value is: 0 no merge|1 merge for all|5 merge only header.
					 * @param DataRowMerge: Whether to allow horizontal merge of the entire row, The default value is: 0
					 * */
			      SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0} );
			      /**
					 * info set information for sheet.
					 * 
					 * @param Sort:        Whether to allow sorting by clicking on the header, value default is: 1 yes|0 no.
					 * @param ColMove:     Whether to allow column movement in header, value default is: 1 yes|0 no.
					 * @param HeaderCheck: Whether the CheckAll in the header is checked, value default: 1 yes|0 no.
					 * @param ColResize:   Whether to allow resizing of column width, value default: 1 yes| 0 no.
					 * */
			      var info    = { Sort:1, ColMove:0, HeaderCheck:0, ColResize:1 };
			      /**
					 * This function define header of Grid, can define the header title and function using this method.
					 * 
					 * @param headers: Make header list.
					 * @param info:    Set information for sheet.
					 * */
			      InitHeaders(headers, info);
			      /**
					 * configure for each column
					 * 
					 * @param Type:       String  - Column data type, this is Required.
					 * @param Hidden:     Boolean - Whether a column is hidden, value: 1 hide|0 show.
					 * @param Width:      Number  - Column width.
					 * @param Align:      String  - Data alignment.
					 * @param ColMerge:   Boolean - whether to allow vertical merge for data columns, value: 1 yes|0 no.
					 * @param SaveName:   String  - Can be used to configure the parameter names to use when saving data.
					 * @param KeyField:   Boolean - Whether to make a data cell a required field, value: 1 required| 0 not required.
					 * @param UpdateEdit: Boolean - Can be used to configure editable of data the transaction status of which is Search, value: 1 yes|0 no.
					 * @param InsertEdit: Boolean - can be used to configure editable of data the transaction status of which is Insert, value: 1 yes|0 no.
					 * @param EditLen:    Number  - Can be used to configure the maximum number of characters to allow for a piece of data.
					 * */
			      var cols = [ 
			             {Type:"Status",    Hidden:1,  Width:0,      Align:"Center",     SaveName:"ibflag" },
			             {Type:"DelCheck",  Hidden:0,  Width:0,      Align:"Center",     SaveName:"del_check" },
			             {Type:"Popup",     Hidden:0,  Width:100,    Align:"Center",     SaveName:"jo_crr_cd",     KeyField:1,  UpdateEdit:0,   InsertEdit:1,	AcceptKeys:"E", InputCaseSensitive:1,	EditLen: 3 },
			             {Type:"Combo",     Hidden:0,  Width:100,    Align:"Center",     SaveName:"rlane_cd",      KeyField:1, 	UpdateEdit:0,   InsertEdit:1,  	EditLen: 5,		ComboText: Rlane,	    ComboCode: Rlane},
			             {Type:"Popup",     Hidden:0,  Width:120,    Align:"Center",     SaveName:"vndr_seq",      KeyField:1, 	UpdateEdit:1, 	InsertEdit:1,	AcceptKeys:"N", EditLen: 6 },
			             {Type:"Popup",     Hidden:0,  Width:80,     Align:"Center",     SaveName:"cust_cnt_cd",   KeyField:1,  UpdateEdit:1, 	InsertEdit:1,	AcceptKeys:"E", InputCaseSensitive:1,	EditLen: 2 },
			             {Type:"Popup",     Hidden:0,  Width:80,     Align:"Center",     SaveName:"cust_seq",  	   KeyField:1, 	UpdateEdit:1, 	InsertEdit:1,	EditLen: 6 },
			             {Type:"Popup",     Hidden:0,  Width:80,     Align:"Center",     SaveName:"trd_cd",   	   KeyField:0, 	UpdateEdit:1, 	InsertEdit:1,	AcceptKeys:"E", InputCaseSensitive:1,	EditLen: 3 },
			             {Type:"Combo",     Hidden:0,  Width:150,    Align:"Center",     SaveName:"delt_flg",      KeyField:0, 	UpdateEdit:1, 	InsertEdit:1, 	ComboCode:"N|Y",  ComboText:"N|Y" },
			             {Type:"Text",      Hidden:0,  Width:120,    Align:"Center",     SaveName:"cre_dt",   	   KeyField:0, 	UpdateEdit:0, 	InsertEdit:0 },
			             {Type:"Text",      Hidden:0,  Width:100,    Align:"Center",     SaveName:"cre_usr_id",    KeyField:0, 	UpdateEdit:0, 	InsertEdit:0 },
			             {Type:"Text",      Hidden:0,  Width:120,    Align:"Center",     SaveName:"upd_dt",		   KeyField:0, 	UpdateEdit:0,	InsertEdit:0 },
			             {Type:"Text",      Hidden:0,  Width:100,    Align:"Center",     SaveName:"upd_usr_id",    KeyField:0, 	UpdateEdit:0,	InsertEdit:0 }
			             
			             ];
			           
			             InitColumns(cols);
			             /**
			 			 * This function Configure overall editable before initial load.
			 			 * The value: 1 editable|0 no editable.
			 			 * */
					     SetEditable(1);
					     /**
						 * Check or configure whether to display waiting image during processing.
					     * The value: 0 no waiting image.
					     * */
					     SetWaitImageVisible(0);
					     resizeSheet();
			}
			break;
		}
	}
	/**
	 *  This function defines the transaction logic between the user interface and the server of IBSheet.
	 *  
	 *  @param sheetObj:  IBSSheet Object.
	 *  @param formObj :  Form object.
	 *  @param sAction :  Action Code (e.g. IBSEARCH, IBSAVE, IBDELETE, IBDOWNEXCEL).
	 * */
	function doActionIBSheet(sheetObj,formObj,sAction) {
		switch(sAction) {
			// Retrieve button event.
			case IBSEARCH:    
				ComOpenWait(true);
				formObj.f_cmd.value = SEARCH; 
				sheetObj.DoSearch("DOU_TRN_004GS.do", FormQueryString(formObj)); 	
				ComOpenWait(false);
				break;
			// Save data button event.
			case IBSAVE: 
				formObj.f_cmd.value = MULTI;
				sheetObj.DoSave("DOU_TRN_004GS.do", FormQueryString(formObj));
				break;
			// Row Add button event
			case IBINSERT: 
				sheetObj.DataInsert();
				break;
			// Row Delete button event
			case IBDELETE: //Row Delete button event
				for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {

					if(sheetObj.GetCellValue(i, "del_check") == 1){
						sheetObj.SetRowHidden(i, 1);
						sheetObj.SetRowStatus(i,"D");
					}
				}
				break;
			// Down excel button event
			case IBDOWNEXCEL: 
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
	
	/**
	 * This function handling when sheet1 on change.
	 * Event fires when the cell editing is completed and the previous value has been updated.
	 * ColSaveName(): Check the SaveName set in InitColumns function that corresponds to Index of a particular column.
	 * GetSearchData(): Call search page, complete search and return search result data in string.
	 * 
	 * @param sheetObj : Object  - Object sheet.
	 * @param Row      : Long    - Row index of the cell.
	 * @param Col      : Long    - Column index of the cell.
	 * @param Value    : string  - Updated value.
	 * @param OldValue : string  - Value before update.
	 * @param RaiseFlag: Integer - Event fire option, value: 0 manual editing|1 method|2 paste.
	 * */
	function sheet1_OnChange(sheetObj, Row, Col, Value, OldValue, RaiseFlag){
		var formObj=document.form ;
		var colName=sheetObj.ColSaveName(Col);
		
		if(colName == "jo_crr_cd" || colName == "rlane_cd"){
			if(sheetObj.GetCellValue(Row,"jo_crr_cd") != "" && sheetObj.GetCellValue(Row,"rlane_cd") != ""){
				//check on UI
				if(sheetObj.ColValueDup("jo_crr_cd|rlane_cd") > -1){
					ComShowCodeMessage("COM12115", "Carrier Code and Rev.Lane");
					sheetObj.SetCellValue(Row, Col,OldValue,0);
					sheetObj.SelectCell(Row, Col);
					return;
				}		
				//check on Service side
				formObj.f_cmd.value	= COMMAND01;
				var sParam			= FormQueryString(formObj) + "&jo_crr_cd=" + sheetObj.GetCellValue(Row,"jo_crr_cd") + "&rlane_cd=" + sheetObj.GetCellValue(Row,"rlane_cd");
				var sXml 			= sheetObj.GetSearchData("DOU_TRN_004GS.do", sParam, {sync:1});	
				var flag			= ComGetEtcData(sXml, "ISEXIST");
				if(flag == 'Y'){
					ComShowCodeMessage("COM12115","Carrier Code and Rev.Lane");
					sheetObj.SetCellValue(Row, Col,OldValue,0);
					sheetObj.SelectCell(Row, Col);
				}
			}
		}	
	}
	
    function resizeSheet(){
 		ComResizeSheet(sheetObjects[0])
 	}
    
    function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
    	ComOpenWait(false);
    }
    
    function initControl(){
    	document.getElementById('s_vndr_seq').addEventListener('keypress', function() {ComKeyOnlyNumber(this );});
    }
    
    function sheet1_OnSaveEnd(){ 
    	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
    }
