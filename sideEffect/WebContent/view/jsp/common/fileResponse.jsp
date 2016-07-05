<%@ page language="java" contentType="application/octet-stream"  pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%
	//response.setHeader("Content-Type", "file");
//response.setHeader("Content-Type", "application/octet-stream");
	response.setHeader("Content-Disposition", "attachment;filename=\"safety_report_batch.xlsx\"");
	
	 OutputStream o = response.getOutputStream();
	 File f = new File("C:\\Users\\sgis\\Downloads\\safety_report_batch.xlsx");
    InputStream is = new FileInputStream(f);
    byte[] buf = new byte[32 * 1024]; // 32k buffer
    int nRead = 0;
    while( (nRead=is.read(buf)) != -1 ) {
        o.write(buf, 0, nRead);
    }
    is.close();
    o.flush();
    o.close();// *important* to ensure no more jsp output
    
    //return; 
%>