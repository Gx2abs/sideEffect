var isDOM = (document.getElementById ? true : false); 
var isIE4 = ((document.all && !isDOM) ? true : false); 
var isNS4 = (document.layers ? true : false); 
var PageNum; //1depth ǥ��
var subNum;  //2depth ǥ��
var subcount; //2depth Count
function getRef(id) { 
if (isDOM) return document.getElementById(id); 
if (isIE4) return document.all[id]; 
if (isNS4) return document.layers[id]; 
} 

var isNS = navigator.appName == "Netscape"; 

function moveRightEdge(obj,h) {
var height = parseInt (h);
var yMenuFrom, yMenuTo, yOffset, timeoutNextCheck; 
	if (isNS4) { 
		yMenuFrom = divMenu.top; 
		yMenuTo = windows.pageYOffset+0; 
	} else if (isDOM) {
		yMenuFrom = parseInt (document.getElementById(obj).style.top, 10); 
		yMenuTo = (isNS ? window.pageYOffset : document.body.scrollTop)+8;
	}
	if(yMenuTo < height) yMenuTo = height+2; //ó=, '�������� 'ġ
	timeoutNextCheck = 100;  //���ٴϴ� �ӵ�
	if (yMenuFrom != yMenuTo) { 
		yOffset = Math.ceil(Math.abs(yMenuTo - yMenuFrom) / 10); 
		if (yMenuTo < yMenuFrom) 
		yOffset = -yOffset; 
		if (isNS4) divMenu.top += yOffset; 
		else if (isDOM) document.getElementById(obj).style.top = parseInt (document.getElementById(obj).style.top, 10) + yOffset; 
		timeoutNextCheck = 10; 
	} 
	setTimeout ("moveRightEdge('"+obj+"',"+height+")", timeoutNextCheck); 
} 

function tViewMainmenu(vnum,lnum) // 6,6       6,5
{
 var f;
 var m;
 var i;
 for(i=1;i<=lnum;i++){
			if(i == vnum){
				$("#tm_0"+vnum).css("src","/images/topnavi/menu0"+vnum+".jpg");
				$("#ssmenu0"+i).css("display","block");
			}else{
				$("#tm_0"+i).css("src","/images/topnavi/menu0"+vnum+".jpg");
				$("#ssmenu0"+i).css("display","none");
			}
	 }
 $(".sub_td").css("background","url(/images/topnavi/sub_bg.jpg) repeat-x");
}
function tViewSubmenu(vnum,lnum) // this, 6
{
 var i;
 var m;
 var t_m_id; 
 var s_m_id;
 t_m_id = vnum.id.substring(5,6);
//alert("vnum.id.substring(6,7) : " + t_m_id); //6
 s_m_id = vnum.id.substring(8,9);
//alert("vnum.id.substring(8,9) : " + s_m_id); //1

 for(i=1;i<=lnum;i++){
	m = document.getElementById("sub_0" + t_m_id + "_0" + i);
	if(m){
		if(i == s_m_id){
			//m.src = "/images/navi/sub_0" + t_m_id + "_0" + i + "_on.gif";
			//alert("/images/navi/sub_0" + t_m_id + "_0" + i + "_on.gif");
		}else{
			//m.src = "/images/navi/sub_0" + t_m_id + "_0" + i + ".gif";
		}
	}
 }
 
 LeftToViewSubmenu();
}



function LeftToViewSubmenu()
{
	var m;
	var i;

	for(i=1;i<=subcount;i++){ //13
		if( i > 9) {
			zero = "";
		} else {
			zero = "0";
		}

		m = document.getElementById("left_0" + PageNum + "_" + zero +i); //8

		if(m){
			if(i == subNum){ // 1
				m.src = "/images/left_menu/mnu_0" + PageNum + "/left_0" + PageNum + "_" + zero + i + "_on.gif";
				m.style.cursor = "hand";
			}else{
				m.src = "/images/left_menu/mnu_0" + PageNum + "/left_0" + PageNum + "_" + zero + i + ".gif";
			}
		}
	}
}

function tViewLeftmenu(vnum)
{
 var m;
 var i;
 var t_m_id;
 var s_m_id;
 
 //alert("subcount : " + t_m_id);
 t_m_id = vnum.id.substring(6,7);
 //alert("vnum.id.substring(메뉴번호) : " + t_m_id);
 if(t_m_id == 8) {
	 subcount=15;
	 s_m_id = vnum.id.substring(8,10);
 } else {
	 s_m_id = vnum.id.substring(9,10); 
 }
 
 //alert("vnum.id.substring(뒷번호) : " + s_m_id);


 for(i=1;i<=subcount;i++){
	 
	 if( i > 9) {
		 zero = "";
	 } else {
		 zero = "0";
	 }
	 m = document.getElementById("left_0" + t_m_id + "_"+ zero +i);
	if(m){
		if(i == s_m_id){
			m.src = "/images/left_menu/mnu_0" + t_m_id + "/left_0" + t_m_id + "_" + zero + i + "_on.gif";		
			m.style.cursor = "hand";
		}else{
			m.src = "/images/left_menu/mnu_0" + t_m_id + "/left_0" + t_m_id + "_" + zero + i + ".gif";
		}
	}
 } 
 tViewMainmenu(PageNum,6);
 TSubToViewLeftmenu();
}

function TSubToViewLeftmenu()
{
	var i;
	var m;
	if(PageNum == 8) {
		subcount = 15;
	}

	for(i=1;i<=subcount;i++){
		if( i > 9) {
			zero = "0";
		} else {
			zero = "";
		}

		m = document.getElementById("sub_0" + PageNum + "_" + zero + i);

		if(m){
			if(i == subNum){
				m.src = "/images/navi/sub_0" + PageNum + "_" + zero + i + "_on.gif";
			}else{
				m.src = "/images/navi/sub_0" + PageNum + "_" + zero + i + ".gif";
			}
		}
	}
}
/*
PageNum=6;
subNum=2;
subcount=14;
*/

//������ �ε���;�� ���� ������ �޴� ǥ��
function tNowmenu(PageNum,subNum) //8,9   //8,10
{
	var m;
	if(PageNum == 8) {
		tViewMainmenu(6,6);
	} else {
		tViewMainmenu(PageNum,6);
	}

	if(subNum > 9) {
		zero = "";
	} else {
		zero = "0";
	}
	
	m = document.getElementById("left_0" + PageNum + "_"+ zero + subNum);
	//alert("left_0" + PageNum + "_"+ zero + subNum);
	
	if(m){
		m.src = "/images/left_menu/mnu_0" + PageNum + "/left_0" + PageNum + "_"+ zero + subNum + "_on.gif";
		//alert("/images/left_menu/mnu_0" + PageNum + "/left_0" + PageNum + "_"+ zero + subNum + "_on.gif");
	}

	m = document.getElementById("sub_0" + PageNum + "_"+ zero + subNum);
	//alert("sub_0" + PageNum + "_"+ zero + subNum);
	if(m){
		m.src = "/images/navi/sub_0" + PageNum + "_"+ zero + subNum + "_on.gif";
		//alert("/images/navi/sub_0" + PageNum + "_"+ zero + subNum + "_on.gif");
	}
}

function tSubmenuOut()
{
	if(PageNum == 8) {
		tViewMainmenu(6,6);
	} else {
		tViewMainmenu(PageNum,6);
	}
	TSubToViewLeftmenu();
	LeftToViewSubmenu();
}
function tViewInit(vnum,lnum) // 6,6       6,5
{
	try{
		var cnt = 10;
		 for(i=1;i<=cnt;i++){	
			if(vnum == i){
				$("#ssmenu0"+vnum).css("display","block");
				$("#tm_0"+vnum).css("color","#69ff00");
				$("#sub_0"+vnum+"_0"+lnum).css("color","#fff");
				$("#right_sub_0"+vnum+"_0"+lnum).css("color","#5280cb");
			}else{
				$("#ssmenu0"+i).css("display","none");
			}
		 }
		 
		 $("#divMenu").css("position","absolute");
		 //$("#divMenu").css("left",((document.body.clientWidth)/2)+200);
		 $("#divMenu").css("top","300");

		 moveRightEdge("divMenu",150); //150 //66 
	}catch(e){
	}
}

// tNowmenu(PageNum,subNum);
