document.onkeydown = function(){
	if(event.keyCode==116){
		event.keyCode = 505;
		location.href = document.location;
		location.reload();
		return false;
	}
}

//오른쪽 마우스버튼 처리
document.oncontextmenu = function () {
	return true;
	try {
		if ( mem_gbn != "SA" ){
			return false;
		}
	}catch(e){
		return false;
	}
};

var json_data;

//Session 확인
function getSession(session_name){
	var returnValue;

	try{
		$.ajax({
			url: "/servlet/ctl/usrCtl?", 
			dataType: "text",
			type : "POST",
			async : false,
			data : {
				"mode":"getSession",
				"session_name":session_name
				},           
			beforeSend: function(xhr){
				//doblockUI();
			},
			success: function(data,textStatus) {	
				if (data == null) {
					returnValue = "";
				}else{
					if (data == "null") {
						returnValue = "";
					}else{					
						returnValue = data;
					}
				}
			},
			error: function(xhr,textStatus){
				alert(xhr.Status);
				returnValue = "";
			},
			complete:function(xhr,textStatus){ 	
				//doUnblockUI();
			}                   
		});
	}catch(e){
		returnValue = "";
	}
	
	return returnValue;
}

function replace(str,oldChar,newChar){
    /*
    *   함수설명  : 문자열에서 특정문자를 다른 문자로 치환한 새로운 문자열을 만든다.
    * str    : 문자열
    * oldChar   : 바꾸기 전의 문자
    * newChar   : 바꿔서 넣을 문자
    */
    var oldstr="";
    
    while(oldstr!=str){
     oldstr=str;
     str=str.replace(oldChar,newChar);
    }
 return str;
}
/*
 *********************************************************************************************************
 * 함수설명  : Null 데이터를 &nbsp 등 사용자 정의 값으로 변경처리
 * input : argSrcData 원 데이터
 * output : argChgData 변경 데이터
 *********************************************************************************************************
 */
function chgNullData(argSrcData, argChgData){
	if ( argSrcData == null ) {
		return argChgData;
	} else if ( argSrcData == '' ) {
		return argChgData;
	}
	return argSrcData;
}

function checkLength(field_name,data,min,max){
	if(!checkNumber(field_name,data)) {
		return false;
	}
	var fieldNumber=parseInt(data);
	if ( !(fieldNumber>=min && fieldNumber<=max) ){
		alert(field_name+" 입력값 길이가 " + parseInt(data)+ " 로 [최소길이:" + min + " ~ 최대길이:" + max + "] 사이의 범위를 벗어나 있습니다.");
		return false;
	}
    return true;
}
function checkLimit(field_name,data,min,max){
	if(!checkNumber(field_name,data)) {
		return false;
	}
	var fieldNumber=parseInt(data);
	if ( !(fieldNumber>=min && fieldNumber<=max) ){
		alert(field_name+" 등록값이 " + parseInt(data)+ " 로 [최소값:" + min + " ~ 최대값:" + max + "] 사이의 범위를 벗어나 있습니다.");
		return false;
	}
    return true;
}
function checkNumber(field_name,data){
	if(data != ""){
		if(!isContainsOnly(data, "0123456789")) {
			alert(field_name+" 은(는) 숫자 외의 문자열을 입력할 수 없습니다.");
			return false;
		}
	}
	return true;
}
function checkString(field_name,data,pattern){
	if(data != ""){
		if(!isContainsOnly(data, pattern)) {
			alert(field_name+" 은(는) \n허용된 문자("+pattern+")외에는 \n입력할 수 없습니다.");
			return false;
		}
	}
	return true;
}
function checkBlank(field_name,data){
	var pattern = /[\s]/g;
	if(data != ""){
        if (pattern.test(data) == true){
			alert(field_name+" 은(는) 공백(띄어쓰기)를 사용하실 수 없습니다.");
			return false;
        }
	}
	return true;
}
function checkSpecialCharacter(field_name,data){
	if(data != ""){
		var pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi; //정규식
		if( pattern.test(data) == true) {
			alert(field_name+" 은(는) 특수문자를 입력할 수 없습니다.");
			return false;
		}
	}
	return true;
}
function checkComplexity(field_name,data){
	if(data != ""){
	    var pattern_cnt = 0;
	    
		var a_pattern = /[a-zA-Z]/; // 알파벳 정규식
		if( a_pattern.test(data) == true) {
			pattern_cnt = pattern_cnt + 1;
		}
		
		var n_pattern = /[0-9]/; // 숫자 정규식
		if( n_pattern.test(data) == true) {
			pattern_cnt = pattern_cnt + 1;
		}
		
		if( pattern_cnt < 2) {
			alert(field_name+"가  작성규칙(영문자+숫자)에 맞지 않습니다.");
			return false;
		}
	}
	return true;
}
function checkDate(field_name,data){
	if(!checkNumber(field_name,data)) {
		return false;
	}
	var year = data.substring(0, 4);
	var month = data.substring(4, 6);
	var day = data.substring(6,8);

    if (year < 1900 || year >2037){
        alert('날짜가 잘못 입력되었습니다. 년도는 1900년에서 2037년까지 입니다.');
        return false;
    }
    if (month <1 || month > 12){
        alert('날짜가 잘못 입력되었습니다. 달은 1월에서 12월까지 입니다.');
        return false;
    }
    if (day < 1 ||  !isValidDay(year, month,day)){
        alert('날짜가 잘못 입력되었습니다. '+ year+ "년 " +month+'월에는 '+ day+'일이 없습니다.');
        return false;
    } 
    return true;
}
function isValidDay(year, month, day) {
   
    var m = parseInt(month,10) - 1;
    var d = parseInt(day,10);

    var end = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        end[1] = 29;
    }
    return (d >= 1 && d <= end[m]);
}
/*
 *********************************************************************************************************
 *   함수설명  : 해당문자열이 지정된 문자들만을 포함하고 있는지 검사한다.
 * str    : 검사할 문자열
 * chars   : 지정된 문자들의 나열
 ***********************************************************************************************************
 */     
function isContainsOnly(str,chars) {
    for (var inx = 0; inx < str.length; inx++) {
       if (chars.indexOf(str.charAt(inx)) == -1)
       return false; 
    }
    return true;
}

/*
* ------------------------------------------------------------------------------------------------- 
* definition.  입력된 param값으로  Validation 체크 Ture,False 값 return
* --------------------------------------------------------------------------------------------------
* param.  checkValidation(field_name,type,obj_name,form_name,check_option_type,check_option_value)
* ---------------------------------------------------------------------------------------------------
* desc. 
* 	field_name:항목명 
* 	type:tag type 
* 	obj_name:tag name
* 	form_name:form name
* 	check_option_type: 'null'-null여부,'number'-0~9 정수값만,'data'-날짜값만 
* 						'limit'-최소값~최대값 허용범위에서만,'length'-최소길이값~최대길이값 허용범위에서만,
* 	check_option_value:'limit'&length 경우 'MinValue:MaxValue'로
* ---------------------------------------------------------------------------------------------------
* return.  boolen 
* --------------------------------------------------------------------------------------------------    
* author.  박준오
* ---------------------------------------------------------------------------------------------------
* ex.
* 	if (checkValidation('폼명','text','FRM_NAME',obj,'null','')== false){return false;}
*	if (checkValidation('폼명','text','FRM_NAME',obj,'length','3:10')== false){return false;}
* -------------------------------------------------------------------------------------------------- 
*/
function checkValidation(field_name,type,obj_name,form_name,check_option_type,check_option_value){

	if ( (type == "select") || (type == "radio") || (type == "checkbox") ){
		var msg = field_name + " 선택하세요.";
	}else{
		var msg = field_name + " 입력하세요.";
	}
	
	var returnValue = true;
	var bTmp = false;
	
	if ( (type == "text") || (type == "password") || (type == "hidden") ){ //20110324 백요한 수정
		$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			if(check_option_type == "null"){
				if($(findElement).val() == ""){
					returnValue = false;
					alert(msg);
					$(findElement).focus();
				}
			}else if(check_option_type == "number"){
				if (checkNumber(field_name,$(findElement).val()) == false){
					returnValue = false;
					$(findElement).focus();
				}
			}else if(check_option_type == "date"){
				var date = replace($(findElement).val(),'-','');
				if (checkDate(field_name,date) == false){
					returnValue = false;
					$(findElement).focus();
				}
			}else if(check_option_type == "string"){ //허용 문자(숫자) 체크			
				    //예:"0123456789abcdefghijklmnopqrstuvwxyz";//숫자,알파벳
					if (checkString(field_name,$(findElement).val(),check_option_value) == false){
						returnValue = false;
						$(findElement).focus();
					}
			}else if(check_option_type == "complexity"){ // 복잡성 (영문자+숫자) 체크
				if (checkComplexity(field_name,$(findElement).val()) == false){
					returnValue = false;
					$(findElement).focus();
				}
				
			}else if(check_option_type == "blank"){ //공백 체크
				if (checkBlank(field_name,$(findElement).val()) == false){
					returnValue = false;
					$(findElement).focus();
				}
			}else{
				
			}	
		});
	}else if (type == "textarea") {
		$(type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			if(check_option_type == "null"){
				if($(findElement).val() == ""){
					returnValue = false;
					alert(msg);
					$(findElement).focus();
				}
			}else if(check_option_type == "length"){
				var s_value = check_option_value.split(":");
				var min = s_value[0];
				var max = s_value[1];
				var data = $(findElement).val(); 

				if (checkLength(field_name,data.length,min,max) == false){
					returnValue = false;
					$(findElement).focus();
				}
			}else{
				
			}
		});
	}else if (type == "select") {
		$(type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			if(check_option_type == "null"){
				if($(findElement).val() == ""){
					returnValue = false;
					alert(msg);
					$(findElement).focus();
				}
			}
		});
	}else if (type == "radio") {
		if(check_option_type == "null"){
			$("input:"+type+"[name='"+obj_name+"']:checked",form_name).each(function(i,findElement){
				bTmp = true;
			});
			
			//메시지 한번만 표시 처리용
			if ( bTmp == false ){
				returnValue = false;
				alert(msg);
				
				$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
					$(findElement).focus();
				});
			}
		}
	}else if (type == "checkbox") {
		if(check_option_type == "null"){
			$("input:"+type+"[name='"+obj_name+"']:checked",form_name).each(function(i,findElement){
				bTmp = true;
			});
			
			//메시지 한번만 표시 처리용
			if ( bTmp == false ){
				returnValue = false;
				alert(msg);

				$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
					$(findElement).focus();
				});
			}
		}
	}else{ 
    
	}
	
	return returnValue;
}

//InternetVersion
function getInternetVersion(ver) { 
	var rv = -1; // Return value assumes failure.      
	var ua = navigator.userAgent;  
	var re = null;
	
	if(ver == "MSIE"){
		re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
	}else{
		re = new RegExp(ver+"/([0-9]{1,}[\.0-9]{0,})");
	}
	
	if (re.exec(ua) != null){ 
		rv = parseFloat(RegExp.$1);
	} 
	
	return rv;  
} 

//브라우저 종류 및 버전확인  
function browserCheck(){ 
	var ver = 0; // 브라우저  버전정보 
	
	if(navigator.appName.charAt(0) == "N"){ 
		if(navigator.userAgent.indexOf("Chrome") != -1){
			ver = getInternetVersion("Chrome");
			//alert("Chrome"+ver+"입니다."); 
		}else if(navigator.userAgent.indexOf("Firefox") != -1){
			ver = getInternetVersion("Firefox");
			//alert("Firefox"+ver+"입니다.");
		}else if(navigator.userAgent.indexOf("Safari") != -1){
			ver = getInternetVersion("Safari");
			//alert("Safari"+ver+"입니다.");
		}
	}else if(navigator.appName.charAt(0) == "M"){
		ver = getInternetVersion("MSIE");		
		//alert("MSIE"+ver+"입니다.");
	}

	return ver;
} 

/*
 *********************************************************************************************************
 *   함수설명  : 문자열에서 왼쪽의 공백을 제거한다.
 * str    : 문자열
 ***********************************************************************************************************
 */
function lTrim(str){
	if (str.length > 0){
		var i;
		i = 0;
		while (str.substring(i,i+1) == ' ' || str.substring(i,i+1) == '　')  i = i + 1;
		return str.substring(i);
	}else{
		return str;
	}
}
/*
 *********************************************************************************************************
 *   함수설명  : 문자열에서 오른쪽의 공백을 제거한다.
 * str    : 문자열
 ***********************************************************************************************************
 */
function rTrim(str){
	if (str.length > 0){
		var i = str.length - 1;
		while (i >= 0 && (str.substring(i,i+1) == ' ' || str.substring(i,i+1) == '　')) i = i - 1;
		return str.substring(0,i+1);
	}else{
		return str;
	}
}
/*
 *********************************************************************************************************
 *   함수설명  : 문자열에서 양쪽의 공백을 제거한다.
 * str    : 문자열
 ***********************************************************************************************************
 */
function trim(str){
	if (str == null){
		return "";
	}else{
		if( str == "" || str.length ==0 ) 
		{
	    	return str; 
		} 
		else
		{
			return(lTrim(rTrim(str)));
		}   
	}
}

function setParam(type,obj_name,form_name,data){

	if (type == "text"){
		$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			$(findElement).attr("value",data);
		});
	}else if (type == "hidden"){ //20110303 백요한 추가
		$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			$(findElement).attr("value",data);
		});
	}else if (type == "password"){ //20110324 백요한 추가
		$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			$(findElement).attr("value",data);
		});
	}else if (type == "textarea") {
		
		$(type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			$(findElement).attr("value",data);
		});
	}else if (type == "select") {
		$(type+"[name='"+obj_name+"']",form_name).removeAttr("selected");
		$(type+"[name='"+obj_name+"']>option[value='"+data+"']",form_name).attr("selected","selected");
	
	}else if (type == "radio") {
		$("input:"+type+"[name='"+obj_name+"']",form_name).removeAttr("checked");
		$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			$("input[name='"+obj_name+"']:radio[value='"+trim(data)+"']",form_name).attr("checked","checked");
		});
	}else if (type == "checkbox") {
		var selectList = data.split(":");
    	$("input:"+type+"[name='"+obj_name+"']",form_name).removeAttr("checked");
    	$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
    	   for (j=0 ; j<selectList.length ; j++){
	   		if ($(findElement).val() == selectList[j]){
	   			$(findElement).attr("checked","checked");
		   	}
    	   }
		});
	}else if (type == "html") {
		$("#"+obj_name+"",form_name).each(function(i,findElement){
			$(findElement).html(data);
		});
	}else{ 
    }    
}

/*
 * object value return
 */
function getParam(type,obj_name,form_name){
    var param = "";
	if (type == "text"){
		$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			param += $(findElement).val();
		});
	}else if (type == "hidden"){
		$("input:"+type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			param += $(findElement).val();
		});
	}else if (type == "textarea") {
		$(type+"[name='"+obj_name+"']",form_name).each(function(i,findElement){
			param += $(findElement).val();
		});
	}else if (type == "select") {
		$(type+"[name='"+obj_name+"']:selected",form_name).each(function(i,findElement){
			param += $(findElement).val();
		});	
	}else if (type == "checkbox") {
		$("input:"+type+"[name='"+obj_name+"']:checked",form_name).each(function(i,findElement){
			if(i != 0){ param += ":";}
			param += $(findElement).val();
		});
	}else if (type == "radio") {
		$("input:"+type+"[name='"+obj_name+"']:checked",form_name).each(function(i,findElement){
			param += $(findElement).val();
		});
	}else if (type == "html") {
		$("#"+obj_name+"",form_name).each(function(i,findElement){
			param += $(findElement).html();
		});
	}	
	return param;
}

//radio enabled, disabled 처리
function setRadioEnDis(pFlag, pName, pFrm, pInitNo, pTcnt){
	for (var i=pInitNo;i<pTcnt;i++){
		if ( pFlag == "E" ){
			try{
				$("#"+pName+i, pFrm).attr("disabled", false);
			}catch(e){
			}
		}else{
			try{
				$("#"+pName+i, pFrm).attr("disabled", true);
			}catch(e){
			}
		}
	}
}

/*
 * checkbox checked 여부에 따라  Y/N return
 */
function getParamYN(obj_name,form_name){
    var param = "N";	
	$("input:checkbox[id='"+obj_name+"']:checked",form_name).each(function(i,findElement){
		param = "Y";
	});	
	return param;
}

/*
* checkbox checked 
*/
function setParamYN(pName, pFrm, pData){
	if ( pData == "Y" ){
		$("#"+pName, pFrm).attr("checked","checked");
	}else{
		$("#"+pName, pFrm).removeAttr("checked");
	}
}

	function isEmpty(pString) {
	    if (pString == null || pString.replace(/ /gi,"") == "") {
	        return true;
	    }
	    return false;
	}

	function js_winOpen(oUrl,oName,oWidth,oHeight,oLeft,oTop,oScroll)
	{
		var oStatus = "toolbar=no,scrollbars="+oScroll+",location=no,directories=no,status=no,menubar=no,resizable=no,width=" + oWidth + ",height = " + oHeight + ",left=" + oLeft + ",top=" + oTop + " ";
		window.open(oUrl,oName,oStatus);
	}

	function js_winOpen_Center(oUrl,oName,oWidth,oHeight,oScroll) {
		
		if(oWidth == ""){oWidth = screen.width;}	
		if(oHeight == ""){oHeight = screen.height;}
		LeftPos = (screen.width) ? (screen.width-oWidth)/2 : 0;
		TopPos = (screen.height) ? (screen.height-oHeight)/2 : 0;
		var oStatus = 'height='+oHeight+',width='+oWidth+',top='+TopPos+',left='+LeftPos+',scrollbars='+oScroll+',resizable=no,location=no,directories=no,status=no,menubar=nos'
		window.open(oUrl,oName,oStatus);	
	}
		
	
	function fn_pop_resizeHeight(winObj)
	{
		var sizeToH = 0;
 
		winH = document.body.scrollHeight;
		sizeToH = winH-document.body.clientHeight;
		
		winObj.resizeBy(0,sizeToH);
	}
	

	function createOption(p_obj_nm,p_arrStr,p_default){
	
		var oSel = document.getElementById(p_obj_nm);
		var vArr, vOptionArr, vValue, vText;
		vArr = p_arrStr.split("|");
		
		oSel.length = 1;
		oSel.options[0] = new Option(':: ���� ::','');
		oSel.options[0].selected = true;
		
		for (i=0; i < vArr.length-1; i++) {
			
			vOptionArr = vArr[i].split("_");
			vValue = vOptionArr[0];
			vText = vOptionArr[1];
			
			oSel.options[i+1] = new Option(vText,vValue);
			
			if (p_default == vValue) oSel.options[i+1].selected = true;
		}
	}	

	function Only_Number()

	{

		if((event.keyCode<48)||(event.keyCode>57))

		{

			event.returnValue=false;

		}

		

	}

	
	function calByte(obj,EngMaxLen) {
		var curText;
		var strLen;
		var byteIs;
		var lastByte;
		var thisChar;
		var escChar;
		var EngMaxLen;	
		var korMaxLen;
		
		korMaxLen = EngMaxLen/2;
		curText = new String(obj.value);
		strLen = curText.length;
		byteIs = 0;
		
		for(i=0; i<strLen; i++) {
			thisChar = curText.charAt(i);
			escChar = escape(thisChar);
			
			if (escChar.length > 4) {
				byteIs += 2;
			}
			else if (thisChar != '\r') {
				byteIs += 1;
			}
						
			if (byteIs > EngMaxLen) {
				alert('♣ 한글 '+korMaxLen+'자, 영문 '+EngMaxLen+'자까지 입력 가능합니다.\n\n♣ 초과된 부분은 자동으로 삭제됩니다.');
				thisText = curText.substring(0, i);
				obj.value = thisText;
				byteIs = lastByte;
				break;
			}
			lastByte = byteIs;
		}		
	}
	

	function all_check(ObjChk){

		var i = 0 ;
		
		if (ObjChk != "[object]")  return;
		var vLen = ObjChk.length;
		var vAllCheck = false ;
		
		if (vLen > 1)
		{
			vAllCheck = true;
			for(i = 0; i < vLen; i++)
				if (ObjChk[i].checked == false && ObjChk[i].disabled == false)
				{
					vAllCheck = false;
					break;
				}
		}
		
		if (vLen >= 2) {
			if(vAllCheck == true)
				for(i=0; i < vLen; i++){
					if (ObjChk[i].disabled == false ) ObjChk[i].checked=false;
				}			
			else
				for(i=0; i < vLen; i++){
					if (ObjChk[i].disabled == false ) ObjChk[i].checked=true;
				}
		}
		else
		{
			if(ObjChk.checked == false && ObjChk.disabled == false )
				ObjChk.checked=true;
			else
				ObjChk.checked=false;			
		}
		
	}
	
	function f_get_sel(p_value, p_obj, p_arr_nm, p_default, p_proc_all)
	{

		var oSel = document.getElementById(p_obj);
		var i = 1 ;
		var selectIndex = 0 ;
		
			
		oSel.length = 1;
		oSel.options[selectIndex] = new Option(':: ���� ::','');
		oSel.options[selectIndex].selected = true;		
		
		
		if (p_arr_nm != null) {	
			
			if (p_proc_all == true && p_value == "") p_value = "all";
			
			for( var ParameterName in p_arr_nm[p_value]){
				
				p_arr = p_arr_nm[p_value][ParameterName].split("||");
				oSel.options[i++] = new Option(p_arr[0], p_arr[1]) ;
				if ( p_default == p_arr[1] ){					
					selectIndex = i-1 ;
				}	
			}
		
			oSel.options[selectIndex].selected = true ;
		}	
	}
	
	function isValidEmail(str) {
		if(str == "") return false;
		var regex = "/[-!#$%&'*+/^_~{}|0-9a-zA-Z]+(.[-!#$%&'*+/^_~{}|0-9a-zA-Z]+)*@[-!#$%&'*+/^_~{}|0-9a-zA-Z]+(.[-!#$%&'*+/^_~{}|0-9a-zA-Z]+)*/";
		if(regex.test(str)) return true;
		else return false;
	}

	 function set_ImageResize(obj, p_maxW, p_maxH){
 
		 var width = parseInt(obj.width);
		 var height = parseInt(obj.height);
		   
		 if(width > parseInt(p_maxW)) {
		   
		  height = parseInt(p_maxW)/width*height;
		  width = parseInt(p_maxW);
		 }
		   
		       if(height > parseInt(p_maxH)){

		       

		           width = parseInt(p_maxH)/height*width;

		           height = parseInt(p_maxH);

		       }
		       
		       obj.width = width;
		       obj.height = height;        
	}


	function set_ImageResize_obj(obj, p_width, p_height){ // img태그를 포함하는 대상obj
		var obj_img = obj.getElementsByTagName("img");
		for(var i=0;i<obj_img.length;i++){
			set_ImageResize(obj_img[i], p_width, p_height);
		}
	}			 
	 
	function commaSplit(srcNumber)

	{

		

		var txtNumber = '' + srcNumber;

		if ( txtNumber == "") 

		{

			return 0

		}else 

		{

			var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');

			var arrNumber = txtNumber.split('.');

			arrNumber[0] += '.';

			do 

			{

				arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');

			} while (rxSplit.test(arrNumber[0]));

			

			if (arrNumber.length > 1) 

			{

				

				return arrNumber.join('');

			}else

			{

				return arrNumber[0].split('.')[0];

			}

		}

	}

	

	function commadel(str)

	{

		while(true)

		{

			index = str.indexOf(",");

			if (index == -1)

			{

				break;

			}else

			{

				len = str.length;

				str = str.substring(0, index) + str.substring(index+1, len);

			}

		}

		return str;

	}

	function Search_Zip(p_form_nm, p_zip1, p_zip2, p_addr1, p_addr2) 	{
		js_winOpen_Center('/common/lib/search_zip_popup.asp?i_form_nm='+p_form_nm,'searchZip','500','315','NO');
	}
	
	function isValidJuminNo(jumin1,jumin2) {
		var IDtot = 0;
		var IDAdd = "234567892345";
		var jumin = jumin1 + jumin2;
		
		if (jumin1.length != 6 || jumin2.length != 7)	{
			return false;
		}
		else {
			for(i = 0; i < 12; i++) {
				IDtot = IDtot + parseInt(jumin.charAt(i)) * parseInt(IDAdd.charAt(i));	
			}
			
			IDtot = 11 - (IDtot % 11);
			
			if (parseInt(jumin.charAt(12)) != (IDtot % 10)) {
				return false;
			}
			else	{
				return true;
			}
		}
	}

	function isValidPhoneNo(str,gubun)
	{ 
		var patPhone = /(02|031|032|033|041|042|043|051|052|053|055|054|061|062|063|064|070)-([0-9]{3,4})-([0-9]{4}$)/ig;	
		var patMobile = /(01[016789])-([1-9]{1}[0-9]{2,3})-([0-9]{4}$)/ig;
		var patMobile2 = /(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4}$)/ig;

	   
		if (gubun =='phone') { 
			
			if (!patPhone.test(str)) {
				return false;  
			} 
				  
		}else if (gubun == 'mobile') {
		
			if (!patMobile.test(str) && !patMobile2.test(str)) {
				return false;
			} 
		}
	        		
		return true;
	}	

	function isField(keyword)	{
		var st_num,key_len;
		st_num = keyword.indexOf(" ");	
		while (st_num != -1)	{
			keyword = keyword.replace(" ", "");
			st_num  = keyword.indexOf(" ");
		}
		key_len = keyword.length;
		return key_len;
	}
	
	function getCookie( name ){ 

		var nameOfCookie = name + "="; 

		var x = 0; 

		while ( x <= document.cookie.length )	{ 

			var y = (x+nameOfCookie.length); 

			if ( document.cookie.substring( x, y ) == nameOfCookie ) { 

				if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 

					endOfCookie = document.cookie.length; 

					return unescape( document.cookie.substring( y, endOfCookie ) ); 

				} 

			x = document.cookie.indexOf( " ", x ) + 1; 

			if ( x == 0 ) 

				break; 

			}

		return ""; 

	}

	function setCookie( name, value, expiredays )	{ 

		var todayDate = new Date();

		todayDate.setDate( todayDate.getDate() + expiredays ); 

		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";" ;

	}
	
	function sub_display(vnum){
		$("#ssmenu0"+vnum).mouseleave(function(){
			$("#ssmenu0"+vnum).css("display","none");
			$(".sub_td").css("background","");
		});
	}
	function move_page2(url, vnum)	{ 
		location.href = url;
		for(var i=1;i<7;i++){
			$("#ssmenu0"+vnum).css("display","none");
		}
		$(".sub_td").css("background","");
	}
	function download(form,fname){
		try{
			var frm = document.getElementById(form);
			frm.action = "/servlet/DownloadServlet?file_name="+encodeURIComponent(fname);
			frm.method = "post";
			frm.target = "_self";
			frm.submit();
		}catch(e){
			
		}
	}
	
	function change_class (element, be, af){
			$(element).removeClass(be);
			$(element).addClass(af);
	}
	
	/* 왼쪽 메뉴명에 onmouse 시 스타일 변경
	 * 파라미터 => pGbn : mouseover, mouseout 구분
	 *           pObj : 선택된 오브젝트
	 */
	function left_menu_change(pGbn, pObj){
		if ( left_menu_id != $(pObj).attr("id") ){
			if ( pGbn == "over" ){
				$(pObj).attr("class", "left_menu_on");
			}else{
				$(pObj).attr("class", "left_menu");
			}
		}
	}

	function move_page_detail(menu_id, data_seq, flag){
		try{
			document.body.focus();
			
			var frameSet = parent.document.getElementById('fra');
			
			if ( flag=="" ) {
				window.parent.frames["contents_frame"].location.href = "/content.jsp";
			}else{
				window.parent.frames["contents_frame"].location.href = menu_id + "?flag="+flag+"&data_seq="+data_seq;
			}
		}catch(e){
			window.parent.frames["contents_frame"].location.href = "/content.jsp";
		}
	}

	function move_page_test(){
		try{
			oEditors.getById['i_contents'].exec('SET_IR', ['']);
		}catch(e){}
		
		try{
			oEditors.getById['HM_CONTENTS'].exec('SET_IR', ['']);
		}catch(e){}
		
		try{
			oEditors.getById['SM_REQ_DESC'].exec('SET_IR', ['']);
		}catch(e){}
		
		try{
			oEditors.getById['FM_CONTENTS'].exec('SET_IR', ['']);
		}catch(e){}
	}
	
	function move_page(menu_id, menu_name){
		if (menu_id == "alt"){
			alert('♧♧  사업 준비중 입니다.');
			return false;
		}
		
		//페이지 변경 메시지 관련 임시 처리
		move_page_test();
		//페이지 변경 메시지 관련 임시 처리
		
		try{
			//if (getSession("user_id") == ""){alert("No login information.\n\nPlease login first.");top.location.href="/";return;}
			
			document.body.focus();
			
			var frameSet = parent.document.getElementById('fra');
			
			if ( menu_name=="" ) {
				window.parent.frames["contents_frame"].location.href = "/content.jsp";
			}else{
				window.parent.frames["contents_frame"].location.href = menu_id;
				
				for(var i=1;i<7;i++){
					//$("#ssmenu0"+vnum).css("display","none");
				}
				//$(".sub_td").css("background","");
			}
		}catch(e){
			window.parent.frames["contents_frame"].location.href = "/content.jsp";
		}
	}
	
	function move_page_self(menu_id, menu_name){
		if (menu_id == "alt"){
			alert('♧♧  사업 준비중 입니다.');
			return false;
		}
		
		try{
			self.location.href = menu_id;
		}catch(e){
			
		}
	}
	
	function move_page_SGI(user_id, URL, menu_name){
		if (user_id == ""){
			alert("로그인 정보가 없습니다.\n\n먼저 로그인을 하십시오.");
			//top.location.href="/";
			window.parent.frames["contents_frame"].location.href = "/jsps/com/login.jsp";
			return;
		}else{	
			try{
		    	$.ajax({
		    		url: "/servlet/ctl/usrCtl2?", 
		    		dataType: "text",
		    		type : "POST",
		    		async : false,
		    		data : {
		    			"mode":"SingleSignOn",
		    			"id":user_id
		    		},             
		    		beforeSend: function(xhr){
	
		    		},
		    		success: function(data,textStatus) {
		    			var evalData = eval("("+ data +")");
		    			
		    			if ( evalData.SSO_UserID != null || evalData.SSO_UserID != '' ){
		    				//alert(URL + "?id=u_list&smode=0&subMenu=1&mm_user_id="+user_id+"&sso="+evalData.SSO_UserID);
		    				window.parent.frames["contents_frame"].location.href = URL + "?id=u_list&smode=0&subMenu=1&mm_user_id="+user_id+"&sso="+evalData.SSO_UserID;
		    			}else{
		    				window.parent.frames["contents_frame"].location.href = "/content.jsp";
		    			}
		    		},
		    		error: function(xhr,textStatus){
		    			alert(xhr.Status);
		    		},
		    		complete:function(xhr,textStatus){

		    		}                   
		    	});
			}catch(e){
				window.parent.frames["contents_frame"].location.href = "/content.jsp";
			}
		}
	}
	
	function move_page_SSO(user_id, URL, menu_name, course_seq, course_yeaer, course_order){
		var course_seq_b = "";
		var course_yeaer_b = "";
		var course_order_b = "";
		
		if ( user_id == "" ){
			if ( menu_name != "" ){
				if (course_seq){
					course_seq_b = course_seq;
					course_yeaer_b = course_yeaer;
					course_order_b = course_order;
				}
				window.open(URL + "?gmpNm=" + menu_name+"&course_seq="+course_seq_b+"&course_yeaer="+course_yeaer_b+"&course_order="+course_order_b, '');
			}else{
				window.open(URL, '');
			}
		}else{	
			try{
		    	$.ajax({
		    		url: "/servlet/ctl/usrCtl2?", 
		    		dataType: "text",
		    		type : "POST",
		    		async : false,
		    		data : {
		    			"mode":"SingleSignOn",
		    			"id":user_id
		    		},             
		    		beforeSend: function(xhr){
	
		    		},
		    		success: function(data,textStatus) {
		    			var evalData = eval("("+ data +")");
		    			
		    			if ( evalData.SSO_UserID != null || evalData.SSO_UserID != '' ){
		    				if ( menu_name != "" ){
		    					window.open(URL + "/sso?user_id="+user_id+"&sso="+evalData.SSO_UserID+"&gmpNm="+menu_name+"&course_seq="+course_seq+"&course_yeaer="+course_yeaer+"&course_order="+course_order, '');
		    				}else{
		    					window.open(URL + "/sso?user_id="+user_id+"&sso="+evalData.SSO_UserID, '');
		    				}
		    			}else{
		    				if ( menu_name != "" ){
		    					window.open(URL + "?gmpNm=" + menu_name+"&course_seq="+course_seq+"&course_yeaer="+course_yeaer+"&course_order="+course_order, '');
		    				}else{
		    					window.open(URL, '');
		    				}
		    			}
		    		},
		    		error: function(xhr,textStatus){
		    			alert(xhr.Status);
		    		},
		    		complete:function(xhr,textStatus){

		    		}                   
		    	});
			}catch(e){
				window.open(URL,'');
			}
		}
	}
	
	function movePage(url, item){
		self.location.href = url;
		item.setAttribute("class","left_menu_on");
	}
	
	function move_page_EDU(user_id, URL, menu_name){
		window.open(URL);
	}
	
	function Left(str, n){
		if (n <= 0)
		    return "";
		else if (n > String(str).length)
		    return str;
		else
		    return String(str).substring(0,n);
		}
		 

	function Right(str, n){
	    if (n <= 0)
	       return "";
	    else if (n > String(str).length)
	       return str;
	    else {
	       var iLen = String(str).length;
	       return String(str).substring(iLen, iLen - n);
	    }
	}
	
	/*
	 * 화면중앙  팝업창 띄우기
	 */
	function openWindow(theURL,winName,myWidth,myHeight,isCenter,scrollGbn,resizeGbn) {
		var oWindow;
		var style;
		
		if(window.screen)if(isCenter)if(isCenter=="true"){
			var myLeft = (screen.width-myWidth)/2;
			var myTop = (screen.height-myHeight)/2;

			style= "'toolbar=no,location=no,directories=no,status=no,menubar=no,width="+myWidth+",height="+myHeight+",top="+myTop+",left="+myLeft+",scrollbars="+scrollGbn+",resizable="+resizeGbn+" '";
		}
		
		oWindow = window.open(theURL,winName,style);
		
		if (oWindow != null) {
			oWindow.focus();
		}

		return oWindow;
	}

	/*
	 * 기술지원 신청서 -> pdf 파일로 다운로드
	 * 작성자 : 백요한
	 * objFormId : submit 폼
	 * objHtmlId : Object ID(사용자 기술지원 신청서 Html) 
	 * objHtmlCSS : 참조 CSS 파일명(ex:basic.css)
	 */
	function doCusSupReport(objFormId,objHtmlId,objHtmlCSS){
		try{
		 	var frm = document.getElementById(objFormId);
	 		//동적생성
	 		var oElement,oElement2;
	 		//html
	 		oElement = document.createElement('input');
	 		oElement.setAttribute('type','hidden');
	 		oElement.setAttribute('id','html_data');
	 		oElement.setAttribute('name','html_data');
	 		frm.appendChild(oElement);
	 		//css
	 		oElement2 = document.createElement('input');
	 		oElement2.setAttribute('type','hidden');
	 		oElement2.setAttribute('id','css_class');
	 		oElement2.setAttribute('name','css_class');
	 		frm.appendChild(oElement2); 		
	 		
	 		document.getElementById("html_data").value = $("#"+objHtmlId,frm).html();
	    	document.getElementById("css_class").value = objHtmlCSS;

			openWindow("","cust_report","850","650","true","yes","yes"); 
			frm.action = "/jsps/manager/cust_supp_report.jsp";
			frm.target = "cust_report";
			frm.method = "post";
			frm.submit();
			
			frm.removeChild(oElement);
			frm.removeChild(oElement2);
		}catch(e){
			alert(e);
		}
	}
	

	//팝업존 버튼 클릭시 동작
	function popupzone_click(post_type, contents, name, board_type){

		if(post_type=="PU" || post_type=="BP"){
			var popwindow = js_winOpen_Center2("/jsps/com/not_pop.jsp?seq="+contents+"&post_type='"+post_type+"'",name,419,485,"NO");
			//if(post_type=="BL") popwindow.document.getElementById("btn_movedtl").onclick();
		}else if(board_type != ""){
			var tUrl = "";
			switch(board_type){
				case "NT" : tUrl = "/jsps/board/brd0101j.jsp"; break;
				case "PR" : tUrl = "/jsps/board/brd0201j.jsp"; break;
				case "FQ" : tUrl = "/jsps/board/brd0301j.jsp"; break;
				case "QA" : tUrl = "/jsps/board/brd0401j.jsp"; break;
				case "PD" : tUrl = "/jsps/board/brd0501j.jsp"; break;
				case "FB" : tUrl = "/jsps/board/brd0601j.jsp"; break;
				default   : tUrl = "" ; break;
			}
			move_page_detail(tUrl, contents, 'view')
		}else{
			window.open("http://" + contents);
		}
		
		//popwindow.document.getElementById("pop_cont").innerHTML = contents;
		
	}	
	
	function move_sub_page(menu_id, menu_name){
		try{
			//if (getSession("user_id") == ""){alert("No login information.\n\nPlease login first.");top.location.href="/";return;}
			
			document.body.focus();
			
			var frameSet = parent.document.getElementById('fra');
			
			if ( menu_name=="" ) {
				window.parent.frames["contents_frame"].location.href = "/content.jsp";
			}else{
				window.parent.frames["contents_frame"].location.href = menu_id;
				
				for(var i=1;i<7;i++){
					//$("#ssmenu0"+vnum).css("display","none");
				}
				//$(".sub_td").css("background","");
			}
		}catch(e){
			window.parent.frames["contents_frame"].location.href = "/content.jsp";
		}
	}
	
	
	function file_reset(){
		var str = "<tr id='orgFLs'></tr>\n"
		str += "<tr>\n<td colspan='2' width='*' >\n";
		str += "<input id='regFL1' name='regFL1' type='file' style='width:480px;'/>\n";
		str += "<span style='cursor:pointer;' onclick=\"javascript:fncAddListFile('insert');\" title='파일추가'>\n";
		str += "<img src='/images/board/nolines_plus.gif' style='vertical-align: middle;'/>파일추가\n";
		str += "</span>&nbsp;\n";
		str += "</td>\n</tr>";
		$("#regfile_tb").html(str);
		
		SEQ=1;
		CUR_FL=1;
	}

