<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/view/config/jqUI.jsp"%>

<!-- <div  id="multiUploaderTop"> 
	<div><input type="button" value="add more files" id="buttonAddFile1"></div>
	<div id="multiUploadContainer">
		<div><input type="file" name="attachment" size="70"/>
			 <input type="text" name="fileName_0" size="70"/>
		</div>
	</div>
</div> -->

<div  id="multiUploaderTop"> 
	
	<div id="multiUploadContainer">
		<div><input type="text" name="fileName_0" size="70"/>
			 <input type="file" name="attachment" size="70"/>
			 <img src="view/style/images/btn_tab02.jpg" class="hover1" alt="검색" id="buttonAddFile1">
		</div>
	</div>
</div>

<script type="text/javascript">

var i=0;

	$(document).ready(function(){
		
		initMultiUpload();
	});
	
	function initMultiUpload(){
		$("#buttonAddFile1").bind("click", function(){
			 
			try{
				addNewFileRow();
				
			}catch(exception){
				alert(exception.message);
			}
			
			
		});
	}
	
	/* function addNewFileRow(){
		
		try{
			i++;
			var container = $("#multiUploadContainer");
			var div = document.createElement("div");
		
			var fileInput = document.createElement("input");
			var fileNameInput = document.createElement("input");
			
			$(fileInput).attr("type", "file");
			$(fileInput).attr("name", "attachment_"+i);
			
			$(div).append(fileInput);
			$(container).append(div);
			
			$(fileNameInput).attr("type", "text");
			$(fileNameInput).attr("name", "fileName_"+i);
			
			$(div).append(fileNameInput);
			$(container).append(div);
		}catch(ex){
			alsert(ex.message);
		}
	} */
	
	function addNewFileRow(){
		
		try{
			i++;
			var container = $("#multiUploadContainer");
			var div = document.createElement("div");
		
			var fileInput = document.createElement("input");
			var fileNameInput = document.createElement("input");
			
			$(fileNameInput).attr("type", "text");
			$(fileNameInput).attr("name", "fileName_"+i);
			$(fileNameInput).attr("size", "70");
			
			$(div).append(fileNameInput);
			$(container).append(div);
			
			$(fileInput).attr("type", "file");
			$(fileInput).attr("name", "attachment_"+i);
			$(fileInput).attr("size", "70");
			
			$(div).append(fileInput);
			$(container).append(div);
			
		}catch(ex){
			alsert(ex.message);
		}
	}
</script>