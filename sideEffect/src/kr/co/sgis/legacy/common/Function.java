// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Function.java

package kr.co.sgis.legacy.common;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;

public class Function
{

    public Function()
    {
    }

    public static String fnScSDate2()
    {
        String rtnDate = "";
        rtnDate = (new StringBuilder(String.valueOf(fnToday(6)))).append("01").toString();
        return rtnDate;
    }

    public static String fnAdminMsg(String url, String AdminYn, String PageMode)
    {
        String rtnStr = "";
        if(AdminYn.equals("N"))
        {
            rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("<script language='javascript'>").toString();
            rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("  alert('\uB85C\uADF8\uC778 \uC815\uBCF4\uAC00 \uC18C\uC2E4\uB418\uC5C8\uAC70\uB098 \uAD00\uB9AC\uC790 \uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');").toString();
            if(PageMode.equals("POPUP"))
                rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("  self.close();").toString();
            else
                rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("  location.href='").append(url).append("mySingle?id=login';").toString();
            rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("</script>").toString();
        }
        return rtnStr;
    }

    public static String fnLoginMsg(String url, String LoginYn, String pgMode)
    {
        String rtnStr = "";
        if(LoginYn.equals("N"))
        {
            rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("<script language='javascript'>").toString();
            rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("  alert('\uC2DC\uAC04\uC774 \uC9C0\uB098\uC11C \uB85C\uADF8\uC544\uC6C3 \uB418\uC5C8\uC2B5\uB2C8\uB2E4.');").toString();
            if(pgMode.equals("POPUP"))
                rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("  self.close();").toString();
            else
                rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("  location.href='").append(url).append("member?id=login';").toString();
            rtnStr = (new StringBuilder(String.valueOf(rtnStr))).append("</script>").toString();
        }
        return rtnStr;
    }

    public static int fnFceil(int totalNum, int listNum)
    {
        int totalPage = 0;
        if(totalNum % listNum == 0)
            totalPage = totalNum / listNum;
        else
            totalPage = totalNum / listNum + 1;
        return totalPage;
    }

    public static String fnCount(int pg, int totalNum, int listNum)
    {
        String rtnCount = "";
        if(totalNum > 0)
        {
            rtnCount = (new StringBuilder("Total: <font color=\"#0000FF\">")).append(fnComma(totalNum)).append("</font> ").toString();
            rtnCount = (new StringBuilder(String.valueOf(rtnCount))).append("[<font color=\"#BF0000\">").append(pg).append("</font>/").append(fnFceil(totalNum, listNum)).append("]").toString();
        } else
        {
            rtnCount = "Total: <font color=\"BF0000\">0</font> ";
        }
        return rtnCount;
    }

    public static int fnUploadMaxsize(String str)
    {
        int Maxsize = 2;
        if(str.equals("member"))
            Maxsize = 1;
        else
        if(str.equals("mailling"))
            Maxsize = 10;
        return Maxsize;
    }

    public static String fnUploadPath(String str)
    {
        str = nullChk(str, "");
        if(!str.equals(""))
            return (new StringBuilder(String.valueOf(UPLOAD_PATH))).append(File.separator).append(str).toString();
        else
            return UPLOAD_PATH;
    }

    public static String fnUploadPath(String str, String fname)
    {
        str = nullChk(str, "");
        fname = nullChk(fname, "");
        String UploadPath = "";
        if(!fname.equals(""))
            UploadPath = (new StringBuilder(String.valueOf(fname.substring(0, 4)))).append(File.separator).append(fname.substring(4, 6)).toString();
        if(!str.equals(""))
            return (new StringBuilder(String.valueOf(UPLOAD_PATH))).append(File.separator).append(str).append(File.separator).append(UploadPath).toString();
        else
            return UPLOAD_PATH;
    }

    public static String fnUploadPath(String str, String flag, String fname)
    {
        str = nullChk(str, "");
        fname = nullChk(fname, "");
        String UploadPath = "";
        if(!fname.equals(""))
            if(!flag.equals(""))
                UploadPath = (new StringBuilder(String.valueOf(flag))).append(File.separator).append(fname.substring(0, 4)).append(File.separator).append(fname.substring(4, 6)).toString();
            else
                UploadPath = (new StringBuilder(String.valueOf(fname.substring(0, 4)))).append(File.separator).append(fname.substring(4, 6)).toString();
        if(!str.equals(""))
            return (new StringBuilder(String.valueOf(UPLOAD_PATH))).append(File.separator).append(str).append(File.separator).append(UploadPath).toString();
        else
            return UPLOAD_PATH;
    }

    public static String fnUploadPath1(String str, String flag, String fname)
    {
        str = nullChk(str, "");
        fname = nullChk(fname, "");
        String UploadPath = "";
        flag = "1";
        if(!fname.equals(""))
            if(!flag.equals(""))
                UploadPath = (new StringBuilder(String.valueOf(flag))).append(File.separator).append(fname.substring(0, 4)).append(File.separator).append(fname.substring(4, 6)).toString();
            else
                UploadPath = (new StringBuilder(String.valueOf(fname.substring(0, 4)))).append(File.separator).append(fname.substring(4, 6)).toString();
        if(!str.equals(""))
            return (new StringBuilder(String.valueOf(UPLOAD_PATH))).append(File.separator).append(str).append(File.separator).append(UploadPath).toString();
        else
            return UPLOAD_PATH;
    }

    public static String fnUploadUrl(String str)
    {
        str = nullChk(str, "");
        if(!str.equals(""))
            return (new StringBuilder(String.valueOf(UPLOAD_URL))).append("/").append(str).toString();
        else
            return UPLOAD_URL;
    }

    public static String fnUploadUrl(String str, String fname)
    {
        str = nullChk(str, "");
        fname = nullChk(fname, "");
        String UploadUrl = "";
        if(!fname.equals(""))
            UploadUrl = (new StringBuilder(String.valueOf(fname.substring(0, 4)))).append("/").append(fname.substring(4, 6)).toString();
        if(!str.equals(""))
            return (new StringBuilder(String.valueOf(UPLOAD_URL))).append("/").append(str).append("/").append(UploadUrl).toString();
        else
            return UPLOAD_URL;
    }

    public static String fnUploadUrl(String str, String flag, String fname)
    {
        str = nullChk(str, "");
        fname = nullChk(fname, "");
        String UploadUrl = "";
        if(!fname.equals(""))
            if(!flag.equals(""))
                UploadUrl = (new StringBuilder(String.valueOf(flag))).append("/").append(fname.substring(0, 4)).append("/").append(fname.substring(4, 6)).toString();
            else
                UploadUrl = (new StringBuilder(String.valueOf(fname.substring(0, 4)))).append("/").append(fname.substring(4, 6)).toString();
        if(!str.equals(""))
            return (new StringBuilder(String.valueOf(UPLOAD_URL))).append("/").append(str).append("/").append(UploadUrl).toString();
        else
            return UPLOAD_URL;
    }

    public static String fnFolderChk(String path)
    {
        File f = new File(path);
        if(!f.exists())
            f.mkdir();
        return "";
    }

    public static int fileCopy(String s, String t)
    {
        File f1 = new File(s);
        int i = 0;
        try
        {
            if(f1.exists())
            {
                FileInputStream fin = new FileInputStream(s);
                FileOutputStream fout = new FileOutputStream(t);
                byte buffer[] = new byte[1024];
                int j;
                while((j = fin.read(buffer)) >= 0) 
                    fout.write(buffer, 0, j);
                fout.close();
                fin.close();
            } else
            {
                i = 1;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
        return i;
    }

    public static String fileRead(String filePath) {
		FileReader fin = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			fin = new FileReader(filePath);
			in = new BufferedReader(fin);
			String readData = in.readLine(); 
			while ( readData != null){ 
				sb.append(readData).append("\r\n"); 
				readData = in.readLine(); 
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fin != null)
					fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return sb.toString();
	}

    public static String getExt(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
            return "";
        StringTokenizer stn = new StringTokenizer(str, ".");
        String file_exe;
        for(file_exe = ""; stn.hasMoreTokens(); file_exe = stn.nextToken());
        return file_exe;
    }

    public static String getIcon(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
            return "";
        String strImg = getExt(str);
        String file_exe = "";
        if(strImg.equals("hwp") || strImg.equals("doc") || strImg.equals("pdf") || strImg.equals("ppt") || strImg.equals("xls") || strImg.equals("pptx") || strImg.equals("xlsx") || strImg.equals("docx"))
            file_exe = (new StringBuilder("ic_")).append(strImg).append(".gif").toString();
        else
        if(strImg.equals("jpg") || strImg.equals("gif") || strImg.equals("bmp"))
            file_exe = "ic_img.gif";
        else
            file_exe = "ic_txt.gif";
        return file_exe;
    }

    public static String fnImgChk(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
            return "";
        String strExt = getExt(str);
        String fnChk = "N";
        if(strExt.equals("gif") || strExt.equals("jpg") || strExt.equals("gif"))
            fnChk = "Y";
        return fnChk;
    }

    public static String convertFilesize(double size)
    {
        String result = (new StringBuilder(String.valueOf(size))).append("Byte").toString();
        if(size >= 1073741824D)
            result = (new StringBuilder(String.valueOf((double)Math.round((size * 100D) / 1073741824D) / 100D))).append("GB").toString();
        else
        if(size >= 1048576D)
            result = (new StringBuilder(String.valueOf((double)Math.round((size * 100D) / 1048576D) / 100D))).append("MB").toString();
        else
        if(size >= 1024D)
            result = (new StringBuilder(String.valueOf((double)Math.round((size * 100D) / 1024D) / 100D))).append("KB").toString();
        return result;
    }

    public static int rtnLen(String str)
    {
        if(str == null)
            return 0;
        int strlen = 0;
        StringBuffer rtnStrBuf = new StringBuffer();
        for(int j = 0; j < str.length(); j++)
        {
            char c = str.charAt(j);
            if(c < '\uAC00' || '\uD7A3' < c)
                strlen++;
            else
                strlen += 2;
        }

        return strlen;
    }

    public static String cutStr(String str, int limit)
    {
        if(limit < 4 || str == null)
            return str;
        int strlen = 0;
        StringBuffer rtnStrBuf = new StringBuffer();
        for(int j = 0; j < str.length(); j++)
        {
            char c = str.charAt(j);
            if(c < '\uAC00' || '\uD7A3' < c)
                strlen++;
            else
                strlen += 2;
            rtnStrBuf.append(c);
            if(strlen <= limit - 3)
                continue;
            rtnStrBuf.append("..");
            break;
        }

        return rtnStrBuf.toString();
    }

    public static String cut(String str, int limit)
    {
        if(limit < 4 || str == null)
            return str;
        int strlen = 0;
        StringBuffer rtnStrBuf = new StringBuffer();
        for(int j = 0; j < str.length(); j++)
        {
            char c = str.charAt(j);
            if(c < '\uAC00' || '\uD7A3' < c)
                strlen++;
            else
                strlen += 2;
            rtnStrBuf.append(c);
            if(strlen > limit - 3)
                break;
        }

        return rtnStrBuf.toString();
    }

    public static String nullCk(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
            str = "";
        else
            str = str.trim();
        return str;
    }

    public static String nullChk(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
            str = "";
        else
            str = fnTagOff(str.trim());
        return str;
    }

    public static String nullChk(String str, String result)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            return result;
        } else
        {
            str = sqlInjection(str.trim());
            return str;
        }
    }

    public static String stripXSS(String value)
    {
        String cleanValue = null;
        if(value != null)
        {
            cleanValue = Normalizer.normalize(value, java.text.Normalizer.Form.NFD);
            cleanValue = cleanValue.replaceAll("<p>", "<br/>");
            cleanValue = cleanValue.replaceAll("< p>", "<br/>");
            cleanValue = cleanValue.replaceAll("< p> ", "<br/>");
            cleanValue = cleanValue.replaceAll("</p>", "");
            cleanValue = cleanValue.replaceAll("< /p>", "");
            cleanValue = cleanValue.replaceAll("< /p> ", "");
            cleanValue = sqlInjection(cleanValue);
        }
        return cleanValue;
    }

    public static String sqlInjection(String value)
    {
        String cleanValue = value;
        cleanValue = cleanValue.replaceAll("<p>", "<br/>");
        cleanValue = cleanValue.replaceAll("< p>", "<br/>");
        cleanValue = cleanValue.replaceAll("< p> ", "<br/>");
        cleanValue = cleanValue.replaceAll("</p>", "");
        cleanValue = cleanValue.replaceAll("< /p>", "");
        cleanValue = cleanValue.replaceAll("< /p> ", "");
        cleanValue = cleanValue.replaceAll("where", "");
        cleanValue = cleanValue.replaceAll("'", "");
        cleanValue = cleanValue.replaceAll("--", "");
        cleanValue = cleanValue.replaceAll("--, #", " ");
        cleanValue = cleanValue.replaceAll("/* */", " ");
        cleanValue = cleanValue.replaceAll("' or 1=1--", " ");
        cleanValue = cleanValue.replaceAll("union", " ");
        cleanValue = cleanValue.replaceAll("drop", " ");
        cleanValue = cleanValue.replaceAll("execute", " ");
        cleanValue = cleanValue.replaceAll("boot", " ");
        cleanValue = cleanValue.replaceAll("-1 or", " ");
        cleanValue = cleanValue.replaceAll("-1' or", " ");
        cleanValue = cleanValue.replaceAll("../", " ");
        cleanValue = cleanValue.replaceAll("1=1", " ");
        cleanValue = cleanValue.replaceAll("--", " ");
        cleanValue = cleanValue.replaceAll("\"", " ");
        cleanValue = cleanValue.replaceAll("\n", " ");
        cleanValue = cleanValue.replaceAll("\r", " ");
        cleanValue = cleanValue.replaceAll("\r\n", " ");
        cleanValue = cleanValue.replaceAll("\\\"", " ");
        cleanValue = cleanValue.replaceAll("[?]", " ");
        return cleanValue;
    }

    public static int nullChk(String str, int result)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            return result;
        } else
        {
            str = fnTagOff(str.trim());
            return Integer.parseInt(str);
        }
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int day)
    {
        try
        {
            value = URLEncoder.encode(value, "euc-kr");
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(0x15180 * day);
            response.addCookie(cookie);
        }
        catch(UnsupportedEncodingException ue)
        {
            ue.printStackTrace();
        }
    }

    public static void setCookie(HttpServletResponse response, String name, String value, String uri, int day)
    {
        try
        {
            value = URLEncoder.encode(value, "euc-kr");
            Cookie cookie = new Cookie(name, value);
            cookie.setPath(uri);
            cookie.setMaxAge(0x15180 * day);
            response.addCookie(cookie);
        }
        catch(UnsupportedEncodingException ue)
        {
            ue.printStackTrace();
        }
    }

    public static void setCookieDomain(HttpServletResponse response, String name, String value, String path, String domain)
    {
        try
        {
            value = URLEncoder.encode(value, "euc-kr");
            Cookie cookie = new Cookie(name, value);
            cookie.setPath(path);
            cookie.setDomain(domain);
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
        catch(UnsupportedEncodingException ue)
        {
            ue.printStackTrace();
        }
    }

    public static void setDeleteCookie(HttpServletResponse response, String name, String value)
    {
        try
        {
            value = URLEncoder.encode(value, "euc-kr");
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        catch(UnsupportedEncodingException ue)
        {
            ue.printStackTrace();
        }
    }

    public static String getCookie(HttpServletRequest request, String cookieName)
    {
        String value = "";
        try
        {
            Cookie cookies[] = request.getCookies();
            for(int i = 0; i < cookies.length; i++)
            {
                if(!cookieName.equals(cookies[i].getName()))
                    continue;
                value = URLDecoder.decode(cookies[i].getValue(), "euc-kr");
                break;
            }

        }
        catch(UnsupportedEncodingException ue)
        {
            ue.printStackTrace();
        }
        return value;
    }

    public static String toEng(String kor)
    {
        if(kor == null)
            return null;
        try
        {
            return new String(kor.getBytes("EUC-KR"), "ISO8859_1");
        }
        catch(Exception e)
        {
            return kor;
        }
    }

    public static String toKor(String eng)
    {
        if(eng == null)
            return null;
        try
        {
            return eng;
        }
        catch(Exception e)
        {
            return eng;
        }
    }

    public static String getReplace(String str, String rep, String tok)
    {
        String retStr = "";
        if(str == null)
            return "";
        if(str.equals(""))
            return "";
        int i = 0;
        for(int j = 0; (j = str.indexOf(rep, i)) > -1;)
        {
            retStr = (new StringBuilder(String.valueOf(retStr))).append(str.substring(i, j)).append(tok).toString();
            i = j + rep.length();
        }

        return str.indexOf(rep) != -1 ? (new StringBuilder(String.valueOf(retStr))).append(str.substring(str.lastIndexOf(rep) + rep.length(), str.length())).toString() : str;
    }

    public static void responseWrite(HttpServletResponse response, String str)
    {
        PrintWriter out = null;
        try
        {
            response.setContentType("text/html;charset=euc-kr");
            out = response.getWriter();
            out.print(str);
            out.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static String convertAddr(String str)
    {
        StringBuffer result = new StringBuffer();
        if(str != null && str.length() == 6)
        {
            result.append(str.substring(0, 3)).append("-").append(str.substring(3));
            return result.toString();
        } else
        {
            return nullChk(str);
        }
    }

    public static String convertSaupja(String str)
    {
        StringBuffer result = new StringBuffer();
        if(str != null && str.length() == 10)
        {
            result.append(str.substring(0, 3)).append("-").append(str.substring(3, 5)).append("-").append(str.substring(5));
            return result.toString();
        } else
        {
            return nullChk(str);
        }
    }

    public static String fnTelMake(String str)
    {
        if(!str.equals("") && str.length() >= 9)
        {
            str = str.replaceAll("-", "");
            if(str.substring(0, 2).equals("02"))
            {
                if(str.length() == 9)
                    str = (new StringBuilder(String.valueOf(str.substring(0, 2)))).append("-").append(str.substring(2, 5)).append("-").append(str.substring(5, 9)).toString();
                else
                if(str.length() == 10)
                    str = (new StringBuilder(String.valueOf(str.substring(0, 2)))).append("-").append(str.substring(2, 6)).append("-").append(str.substring(6, 10)).toString();
                else
                    str = str;
            } else
            if(str.length() == 10)
                str = (new StringBuilder(String.valueOf(str.substring(0, 3)))).append("-").append(str.substring(3, 6)).append("-").append(str.substring(6, 10)).toString();
            else
            if(str.length() == 11)
                str = (new StringBuilder(String.valueOf(str.substring(0, 3)))).append("-").append(str.substring(3, 7)).append("-").append(str.substring(7, 11)).toString();
            else
                str = str;
        } else
        {
            str = str;
        }
        return str;
    }

    public static String fnComma(String num)
    {
        String code = num;
        if(code.length() == 0)
            return "0";
        String rcode = "";
        int dat_num = code.length();
        for(int i = dat_num - 1; i >= 0; i--)
        {
            if(dat_num - i - 1 != 0 && (dat_num - i - 1) % 3 == 0 && code.charAt(i) != '-' && code.charAt(i + 1) != '.')
                rcode = (new StringBuilder(",")).append(rcode).toString();
            rcode = (new StringBuilder(String.valueOf(code.charAt(i)))).append(rcode).toString();
        }

        return rcode;
    }

    public static String fnComma(int num)
    {
        String code = toString(num);
        if(code.length() == 0)
            return "0";
        String rcode = "";
        int dat_num = code.length();
        for(int i = dat_num - 1; i >= 0; i--)
        {
            if(dat_num - i - 1 != 0 && (dat_num - i - 1) % 3 == 0 && code.charAt(i) != '-' && code.charAt(i + 1) != '.')
                rcode = (new StringBuilder(",")).append(rcode).toString();
            rcode = (new StringBuilder(String.valueOf(code.charAt(i)))).append(rcode).toString();
        }

        return rcode;
    }

    public static String fnLpad(String str, int len, String addStr)
    {
        String result = "";
        if(str == null || str.equals("null") || str.equals(""))
            return result;
        result = str;
        for(int i = result.length(); i < len; i++)
            result = (new StringBuilder(String.valueOf(addStr))).append(result).toString();

        return result;
    }

    public static String fnLpad(int str, int len, String addStr)
    {
        String result = toString(str);
        for(int i = result.length(); i < len; i++)
            result = (new StringBuilder(String.valueOf(addStr))).append(result).toString();

        return result;
    }

    public static void writeLog(String path, String flag, String pgm, String msg) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		Calendar cal = Calendar.getInstance();
		String timeZone = cal.getTimeZone().getDisplayName(true,TimeZone.SHORT);
		if(timeZone.equals("GMT")) {
		   cal.add(Calendar.HOUR_OF_DAY,9); // 한국시간으로 지정(GMT+9)
        }
		String today = sdf.format(cal.getTime());
		PrintStream ps = null;
		FileOutputStream fos = null;
		File log = null;
		//D:\project\test01\errorLog\2007\10\log_20071017
		String upPath1 = path + "errorLog";
		String upPath2 = upPath1 + File.separator + Function.fnToday(4);
		String upPath3 = upPath2 + File.separator + Function.fnToday(6).substring(4,6);
		Function.fnFolderChk(upPath1); //패키지별 폴더생성
		Function.fnFolderChk(upPath2); //년도별 폴더생성
		Function.fnFolderChk(upPath3); //월별 폴더생성
		String upPath = upPath3 + File.separator; //최종 업로드 경로
		String filePath = upPath + "log_"+ Function.fnToday(8);
		//System.out.println("upPath="+upPath);
		
		try {
			while(true) {
				log = new File(filePath + ".log");
				log.createNewFile();
				if(log.length() > 5*1024*1024) { //기존 로그파일 크기가 5M가 넘으면
					File old_log = new File(filePath +".old"); // 만약 이미 old가 있다면 지운다.
					old_log.delete();
					log.renameTo(new File(filePath +".old"));
				} else {
					break;
				}
			}
			fos = new FileOutputStream(filePath +".log", true);
			ps = new PrintStream(fos, true); // Auto Flush..
			ps.print("["+today+"]");
			ps.print("["+flag+"]");
			ps.print("\t");
			ps.print("["+pgm+"]");
			ps.print("\t");
			ps.println("["+msg+"]");
       } catch (IOException ioe) {
          ioe.printStackTrace();
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          ps.close();
       }
    }

    public static String fnToday(int wishLen)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String timeZone = cal.getTimeZone().getDisplayName(true, 0);
        if(timeZone.equals("GMT"))
            cal.add(11, 9);
        String today = sdf.format(cal.getTime());
        today = today.substring(0, wishLen);
        return today;
    }

    public static String fnDate(String a, String b, int len)
    {
        if(a == null || a.equals("null") || a.equals(""))
            a = "";
        else
        if(len == 14)
            a = (new StringBuilder(String.valueOf(a.substring(0, 4)))).append(b).append(a.substring(4, 6)).append(b).append(a.substring(6, 8)).append(" ").append(a.substring(8, 10)).append(":").append(a.substring(10, 12)).append(":").append(a.substring(12, 14)).toString();
        else
        if(len == 12)
            a = (new StringBuilder(String.valueOf(a.substring(0, 4)))).append(b).append(a.substring(4, 6)).append(b).append(a.substring(6, 8)).append(" ").append(a.substring(8, 10)).append(":").append(a.substring(10, 12)).toString();
        else
        if(len == 10)
            a = (new StringBuilder(String.valueOf(a.substring(0, 4)))).append(b).append(a.substring(4, 6)).append(b).append(a.substring(6, 8)).append(" ").append(a.substring(8, 10)).toString();
        else
        if(len == 8)
            a = (new StringBuilder(String.valueOf(a.substring(0, 4)))).append(b).append(a.substring(4, 6)).append(b).append(a.substring(6, 8)).toString();
        else
        if(len == 6)
            a = (new StringBuilder(String.valueOf(a.substring(2, 4)))).append(b).append(a.substring(4, 6)).append(b).append(a.substring(6, 8)).toString();
        else
        if(len == 4)
            a = (new StringBuilder(String.valueOf(a.substring(4, 6)))).append(b).append(a.substring(6, 8)).toString();
        return a;
    }

    public static String fnDate(String a, String b)
    {
        if(a == null || a.equals("null") || a.equals(""))
            a = "";
        else
            a = (new StringBuilder(String.valueOf(a.substring(0, 4)))).append(b).append(a.substring(4, 6)).append(b).append(a.substring(6, 8)).toString();
        return a;
    }

    public static String fnTime(String time, int len)
    {
        if(time == null || time.equals("null") || time.equals(""))
            time = "";
        else
        if(len == 4)
            time = (new StringBuilder(String.valueOf(time.substring(0, 2)))).append(":").append(time.substring(2, 4)).toString();
        else
        if(len == 6)
            time = (new StringBuilder(String.valueOf(time.substring(0, 2)))).append(":").append(time.substring(2, 4)).append(":").append(time.substring(4, 6)).toString();
        return time;
    }

    public static int fnDay(String date)
    {
        int rtnDay = 0;
        if(date == null || date.equals("null") || date.equals(""))
            rtnDay = 0;
        else
            rtnDay = toNumber(date.substring(6, 8));
        return rtnDay;
    }

    public static String fnDelDate(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            str = "";
        } else
        {
            str = str.replaceAll("-", "");
            str = str.replaceAll("/", "");
        }
        return str;
    }

    public static String fnZeroAdd(int i)
    {
        String result = null;
        if(i < 10)
            result = (new StringBuilder("0")).append(i).toString();
        else
            result = (new StringBuilder()).append(i).toString();
        return result;
    }

    public static String fnZeroAdd(String i)
    {
        String result = null;
        int j = toNumber(i);
        if(j < 10)
            result = (new StringBuilder("0")).append(j).toString();
        else
            result = (new StringBuilder()).append(j).toString();
        return result;
    }

    public static String convertDate(String str)
    {
        StringBuffer result = new StringBuffer();
        if(str != null && str.length() == 8)
        {
            result.append(str.substring(0, 4)).append("\uB144 ").append(str.substring(4, 6)).append("\uC6D4 ").append(str.substring(6)).append("\uC77C");
            return result.toString();
        } else
        {
            return nullChk(str);
        }
    }

    public static String fnMonthAdd(String strDate, int wishNum)
    {
        Date sDate = new Date(Integer.parseInt(strDate.substring(0, 4)) - 1900, (Integer.parseInt(strDate.substring(4, 6)) - 1) + wishNum, Integer.parseInt(strDate.substring(6, 8)));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String sSpot_date = sdf.format(sDate);
        return sSpot_date;
    }

    public static String fnDayAdd(String strDate, int wishNum)
    {
        Date sDate = new Date(Integer.parseInt(strDate.substring(0, 4)) - 1900, Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6, 8)) + wishNum);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String sSpot_date = sdf.format(sDate);
        return sSpot_date;
    }

    public static String fnHourAdd(String strDate, int wishNum)
    {
        Date sDate = new Date(Integer.parseInt(strDate.substring(0, 4)) - 1900, Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6, 8)), Integer.parseInt(strDate.substring(8, 10)) + wishNum, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String sSpot_date = sdf.format(sDate);
        return sSpot_date;
    }

    public static String fnSubstr(String str, int len)
    {
        if(str == null || str.equals("null") || str.equals(""))
            str = "";
        else
        if(str.length() > len)
            str = str.substring(0, len);
        return str;
    }

    public static String IsSelected(String str1, String str2)
    {
        str1 = nullChk(str1, "");
        str2 = nullChk(str2, "");
        if(str1.equals(str2))
            return "selected";
        else
            return "";
    }

    public static String IsSelected(int str1, int str2)
    {
        if(str1 == str2)
            return "selected";
        else
            return "";
    }

    public static String IsChecked(String str1, String str2)
    {
        str1 = nullChk(str1, "");
        str2 = nullChk(str2, "");
        if(str1.equals(str2))
            return "checked";
        else
            return "";
    }

    public static String IsChecked(int str1, int str2)
    {
        if(str1 == str2)
            return "checked";
        else
            return "";
    }

    public static String removeTag(String str)
    {
        String lowStr = str.toLowerCase();
        String orgStr = str;
        StringBuffer rplStr = new StringBuffer();
        if(str == null || str.equals(""))
            return "";
        String tag[] = new String[31];
        tag[0] = "<script";
        tag[1] = "&ltscript";
        tag[2] = "&lt;script";
        tag[3] = "</script";
        tag[4] = "&lt/script";
        tag[5] = "&lt;/script";
        tag[6] = "<iframe";
        tag[7] = "&ltiframe";
        tag[8] = "&lt;iframe";
        tag[9] = "</iframe";
        tag[10] = "&lt/iframe";
        tag[11] = "&lt;iframe";
        tag[12] = "<object";
        tag[13] = "&ltobject";
        tag[14] = "&lt;object";
        tag[15] = "</object";
        tag[16] = "&lt/object";
        tag[17] = "&lt;/object";
        tag[18] = "<embed1";
        tag[19] = "&ltembed1";
        tag[20] = "&lt;embed1";
        tag[21] = "</embed1";
        tag[22] = "&lt/embed1";
        tag[23] = "&lt;embed1";
        tag[24] = "<meta";
        tag[25] = "&ltmeta";
        tag[26] = "&lt;meta";
        tag[27] = "</meta";
        tag[28] = "&lt/meta";
        tag[29] = "&lt;/meta";
        tag[30] = "onload";
        String targetTag[] = new String[31];
        targetTag[0] = "<zscript";
        targetTag[1] = "&ltzscript";
        targetTag[2] = "&lt;zscript";
        targetTag[3] = "</zscript";
        targetTag[4] = "&lt/zscript";
        targetTag[5] = "&lt;/zscript";
        targetTag[6] = "<ziframe";
        targetTag[7] = "&ltziframe";
        targetTag[8] = "&lt;ziframe";
        targetTag[9] = "</ziframe";
        targetTag[10] = "&lt/ziframe";
        targetTag[11] = "&lt;/ziframe";
        targetTag[12] = "<zobject";
        targetTag[13] = "&ltzobject";
        targetTag[14] = "&lt;zobject";
        targetTag[15] = "</zobject";
        targetTag[16] = "&lt/zobject";
        targetTag[17] = "&lt;/zobject";
        targetTag[18] = "<zembed";
        targetTag[19] = "&ltzembed";
        targetTag[20] = "&lt;zembed";
        targetTag[21] = "</zembed";
        targetTag[22] = "&lt/zembed";
        targetTag[23] = "&lt;/zembed";
        targetTag[24] = "<zmeta";
        targetTag[25] = "&ltzmeta";
        targetTag[26] = "&lt;zmeta";
        targetTag[27] = "</zmeta";
        targetTag[28] = "&lt/zmeta";
        targetTag[29] = "&lt;/zmeta";
        targetTag[30] = "zonload";
        int preIdx = 0;
        int curIdx = 0;
        for(int i = 0; i < tag.length; i++)
        {
            preIdx = 0;
            for(curIdx = 0; (curIdx = lowStr.indexOf(tag[i], curIdx)) >= 0;)
            {
                rplStr.append(orgStr.substring(preIdx, curIdx));
                rplStr.append(targetTag[i]);
                curIdx += tag[i].length();
                preIdx = curIdx;
            }

            rplStr.append(orgStr.substring(preIdx));
            orgStr = rplStr.toString();
            lowStr = orgStr.toLowerCase();
            rplStr.setLength(0);
        }

        return orgStr;
    }

    public static String fnTagOff(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            str = "";
        } else
        {
            str = str.trim();
            str = str.replaceAll("\"", "&quot;");
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("'", "\u2019");
        }
        return str;
    }

    public static String fnTagOn(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            str = "";
        } else
        {
            str = str.trim();
            str = str.replaceAll("&quot;", "\"");
            str = str.replaceAll("&lt;", "<");
            str = str.replaceAll("&gt;", ">");
            str = str.replaceAll("\u2019", "'");
            str = str.replaceAll("<div>", "<br>");
            str = str.replaceAll("</div>", "<br>");
            str = str.replaceAll("<br>", "<br>");
            str = removeTag(str);
        }
        return str;
    }

    public static String fnPtagOff(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            str = "";
        } else
        {
            str = str.trim();
            str = fnTagOn(str);
            str = str.replaceAll("<P>", "");
            str = str.replaceAll("</P>", "");
        }
        return str;
    }

    public static String fnPToBr(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            str = "";
        } else
        {
            str = str.trim();
            str = fnTagOn(str);
            str = str.replaceAll("<P>", "");
            str = str.replaceAll("</P>", "<br>");
        }
        return str;
    }

    public static String fnEnterToBr(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            str = "";
        } else
        {
            str = str.trim();
            str = fnTagOn(str);
            str = str.replaceAll("\n", "<br>");
        }
        return str;
    }

    public static int toNumber(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
            return 0;
        else
            return Integer.parseInt(str);
    }

    public static double toDouble(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
            return 0.0D;
        else
            return Double.parseDouble(str);
    }

    public static String toString(int str)
    {
        return String.valueOf(str);
    }

    public static String randomString(int len)
    {
        Random random = new Random();
        char alphaNum[] = {
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 
            'a', 'b', 'c', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 
            'v', 'w', 'x', 'y', 'z'
        };
        String str = "";
        for(int j = 0; j < len; j++)
            str = (new StringBuilder(String.valueOf(str))).append(alphaNum[random.nextInt(35)]).toString();

        return str;
    }

    public static String randomAlpha(int len)
    {
        Random random = new Random();
        char alphaNum[] = {
            'a', 'b', 'c', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 
            'v', 'w', 'x', 'y', 'z'
        };
        String str = "";
        for(int j = 0; j < len; j++)
            str = (new StringBuilder(String.valueOf(str))).append(alphaNum[random.nextInt(25)]).toString();

        return str;
    }

    public static String randomNumber(int len)
    {
        Random random = new Random();
        char alphaNum[] = {
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
        };
        String str = "";
        for(int j = 0; j < len; j++)
            str = (new StringBuilder(String.valueOf(str))).append(alphaNum[random.nextInt(10)]).toString();

        return str;
    }

    private static String toHex(byte hash[])
    {
        StringBuffer buf = new StringBuffer(hash.length * 2);
        for(int i = 0; i < hash.length; i++)
        {
            int intVal = hash[i] & 0xff;
            if(intVal < 16)
                buf.append("0");
            buf.append(Integer.toHexString(intVal));
        }

        return buf.toString();
    }

    public static String getMD5String(byte b[])
    {
        String javaPackageMD5 = "";
        try
        {
            byte javaDigest[] = MessageDigest.getInstance("MD5").digest(b);
            javaPackageMD5 = toHex(javaDigest);
        }
        catch(Exception exception) { }
        return javaPackageMD5;
    }

    public static String getMD5(String str)
    {
        byte byteString[] = str.getBytes();
        return getMD5String(byteString);
    }

    public static String getMD5(int str)
    {
        return getMD5(toString(str));
    }

    public static String fnURLEncode(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            str = "";
        } else
        {
            str = str.replaceAll("=", "%3D");
            str = str.replaceAll("&", "%26");
        }
        return str;
    }

    public static String fnLogAction(String code)
    {
        String rtnStr = "";
        if(code.equals("C"))
            rtnStr = "<font color=\"forestgreen\">\u5360\uC3D9\uC619\u5360\uC3D9\uC619</font>";
        else
        if(code.equals("L"))
            rtnStr = "<font color=\"#8c008c\">\u5360\uC3D9\uC619\uD68C</font>";
        else
        if(code.equals("I"))
            rtnStr = "<font color=\"blue\">\u5360\uC3D9\uC619\u5360\uFFFD/font>";
        else
        if(code.equals("U"))
            rtnStr = "<font color=\"darkorange\">\u5360\uC3D9\uC619\u5360\uC3D9\uC619</font>";
        else
        if(code.equals("D"))
            rtnStr = "<font color=\"red\">\u5360\uC3D9\uC619\u5360\uC3D9\uC619</font>";
        else
        if(code.equals("F"))
            rtnStr = "<font color=\"#666600\">\u5360\uC3D9\uC619\u5360\uC3D9\uC619</font>";
        else
            rtnStr = "<font color=\"black\">\u5360\uC3D9\uC619\uD0C0</font>";
        return rtnStr;
    }

    public static String fnAdmGrade(String grade)
    {
        String rtnStr = "";
        if(grade.equals("S"))
            rtnStr = "<font color=\"#8c008c\">\u5360\uC2DC\uC3D9\uC619\u5360\uC31C\uACE4\uC619\u5360\uC3D9\uC619</font>";
        else
        if(grade.equals("A"))
            rtnStr = "<font color=\"forestgreen\">\u5360\uC3D9\uC619\u5360\uC3D9\uC619</font>";
        else
        if(grade.equals("M"))
            rtnStr = "<font color=\"darkorange\">\u5360\uC3D9\uC619\u5360\uC3D9\uC619\u5360\uFFFD/font>";
        else
        if(grade.equals("D"))
            rtnStr = "<font color=\"blue\">\u5360\uC3D9\uC619\u5360\uFFFD/font>";
        else
            rtnStr = "<font color=\"black\">\u5360\uC3D9\uC619\uD0C0</font>";
        return rtnStr;
    }

    public static String fnHourSelected(String code)
    {
        StringBuffer rtnStr = null;
        rtnStr = new StringBuffer();
        rtnStr.append((new StringBuilder("<option value=\"00\" ")).append(IsSelected(code, "00")).append(">0\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"01\" ")).append(IsSelected(code, "01")).append(">1\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"02\" ")).append(IsSelected(code, "02")).append(">2\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"03\" ")).append(IsSelected(code, "03")).append(">3\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"04\" ")).append(IsSelected(code, "04")).append(">4\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"05\" ")).append(IsSelected(code, "05")).append(">5\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"06\" ")).append(IsSelected(code, "06")).append(">6\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"07\" ")).append(IsSelected(code, "07")).append(">7\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"08\" ")).append(IsSelected(code, "08")).append(">8\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"09\" ")).append(IsSelected(code, "09")).append(">9\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"10\" ")).append(IsSelected(code, "10")).append(">10\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"11\" ")).append(IsSelected(code, "11")).append(">11\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"12\" ")).append(IsSelected(code, "12")).append(">12\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"13\" ")).append(IsSelected(code, "13")).append(">13\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"14\" ")).append(IsSelected(code, "14")).append(">14\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"15\" ")).append(IsSelected(code, "15")).append(">15\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"16\" ")).append(IsSelected(code, "16")).append(">16\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"17\" ")).append(IsSelected(code, "17")).append(">17\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"18\" ")).append(IsSelected(code, "18")).append(">18\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"19\" ")).append(IsSelected(code, "19")).append(">19\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"20\" ")).append(IsSelected(code, "20")).append(">20\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"21\" ")).append(IsSelected(code, "21")).append(">21\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"22\" ")).append(IsSelected(code, "22")).append(">22\uC2DC</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"23\" ")).append(IsSelected(code, "23")).append(">23\uC2DC</option>").toString());
        return rtnStr.toString();
    }

    public static String fnMinuteSelected(String code)
    {
        StringBuffer rtnStr = null;
        rtnStr = new StringBuffer();
        rtnStr.append((new StringBuilder("<option value=\"00\" ")).append(IsSelected(code, "00")).append(">00\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"05\" ")).append(IsSelected(code, "05")).append(">05\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"10\" ")).append(IsSelected(code, "10")).append(">10\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"15\" ")).append(IsSelected(code, "15")).append(">15\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"20\" ")).append(IsSelected(code, "20")).append(">20\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"25\" ")).append(IsSelected(code, "25")).append(">25\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"30\" ")).append(IsSelected(code, "30")).append(">30\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"35\" ")).append(IsSelected(code, "35")).append(">35\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"40\" ")).append(IsSelected(code, "40")).append(">40\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"45\" ")).append(IsSelected(code, "45")).append(">45\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"50\" ")).append(IsSelected(code, "50")).append(">50\uBD84</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"55\" ")).append(IsSelected(code, "55")).append(">55\uBD84</option>").toString());
        return rtnStr.toString();
    }

    public static String fnView(String code)
    {
        String rtnStr = "";
        if(code.equals("Y"))
            rtnStr = "<font color=\"black\">\uBCF4\uC784</font>";
        else
        if(code.equals("N"))
            rtnStr = "<font color=\"red\">\uC548\uBCF4\uC784</font>";
        else
            rtnStr = "";
        return rtnStr;
    }

    public static String fnViewSelected(String code)
    {
        StringBuffer rtnStr = null;
        rtnStr = new StringBuffer();
        rtnStr.append((new StringBuilder("<option value=\"Y\" ")).append(IsSelected(code, "Y")).append(">\uBCF4\uC784</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"N\" ")).append(IsSelected(code, "N")).append(">\uC548\uBCF4\uC784</option>").toString());
        return rtnStr.toString();
    }

    public static String fnViewRadio(String code)
    {
        StringBuffer rtnStr = null;
        rtnStr = new StringBuffer();
        rtnStr.append((new StringBuilder("<input type=\"radio\" name=\"car_view\" value=\"Y\" style=\"border:0\" ")).append(IsChecked(code, "Y")).append(">\uBCF4\uC784&nbsp;&nbsp;").toString());
        rtnStr.append((new StringBuilder("<input type=\"radio\" name=\"car_view\" value=\"N\" style=\"border:0\" ")).append(IsChecked(code, "N")).append(">\uC548\uBCF4\uC784").toString());
        return rtnStr.toString();
    }

    public static String fnState(String code)
    {
        String rtnStr = "";
        if(code.equals("0"))
            rtnStr = "<font color=\"blue\">\uC2E0\uCCAD</font>";
        else
        if(code.equals("1"))
            rtnStr = "<font color=\"forestgreen\">\uC9C4\uD589</font>";
        else
        if(code.equals("2"))
            rtnStr = "<font color=\"black\">\uBC1C\uAE09</font>";
        else
        if(code.equals("3"))
            rtnStr = "<font color=\"purple\">\uBC18\uB824</font>";
        else
            rtnStr = "";
        return rtnStr;
    }

    public static String fnStateSelected(String code)
    {
        StringBuffer rtnStr = null;
        rtnStr = new StringBuffer();
        rtnStr.append((new StringBuilder("<option value=\"0\" ")).append(IsSelected(code, "0")).append(">\uC2E0\uCCAD</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"1\" ")).append(IsSelected(code, "1")).append(">\uC9C4\uD589</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"2\" ")).append(IsSelected(code, "2")).append(">\uBC1C\uAE09</option>").toString());
        rtnStr.append((new StringBuilder("<option value=\"3\" ")).append(IsSelected(code, "3")).append(">\uBC18\uB824</option>").toString());
        return rtnStr.toString();
    }

    public static String fnStateRadio(String code)
    {
        StringBuffer rtnStr = null;
        rtnStr = new StringBuffer();
        rtnStr.append((new StringBuilder("<input type=\"radio\" name=\"car_state\" value=\"0\" style=\"border:0\" ")).append(IsChecked(code, "0")).append(">\uC2E0\uCCAD&nbsp;&nbsp;").toString());
        rtnStr.append((new StringBuilder("<input type=\"radio\" name=\"car_state\" value=\"1\" style=\"border:0\" ")).append(IsChecked(code, "1")).append(">\uC9C4\uD589&nbsp;&nbsp;").toString());
        rtnStr.append((new StringBuilder("<input type=\"radio\" name=\"car_state\" value=\"2\" style=\"border:0\" ")).append(IsChecked(code, "2")).append(">\uBC1C\uAE09&nbsp;&nbsp;").toString());
        rtnStr.append((new StringBuilder("<input type=\"radio\" name=\"car_state\" value=\"3\" style=\"border:0\" ")).append(IsChecked(code, "3")).append(">\uBC18\uB824").toString());
        return rtnStr.toString();
    }

    public static void log( String pgm, String msg) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		Calendar cal = Calendar.getInstance();
		String timeZone = cal.getTimeZone().getDisplayName(true,TimeZone.SHORT);
		if(timeZone.equals("GMT")) {
		   cal.add(Calendar.HOUR_OF_DAY,9);  // 占싼깍옙占시곤옙占쏙옙占쏙옙 占쏙옙占쏙옙(GMT+9)
	    }
		String path="";
		String today = sdf.format(cal.getTime());
		PrintStream ps = null;
		FileOutputStream fos = null;
		File log = null;
		//D:\project\test01\errorLog\2007\10\log_20071017
		String upPath1 = path + "errorLog";
		String upPath2 = upPath1 + File.separator + Function.fnToday(4);
		String upPath3 = upPath2 + File.separator + Function.fnToday(6).substring(4,6);
		Function.fnFolderChk(upPath1); //占쏙옙키占쏙옙占쏙옙 占쏙옙占쏙옙占�
		Function.fnFolderChk(upPath2); //占썩도占쏙옙 占쏙옙占쏙옙占�
		Function.fnFolderChk(upPath3); //占쏙옙 占쏙옙占쏙옙占�
		String upPath = upPath3 + File.separator; //占쏙옙占쏙옙 占쏙옙占싸듸옙 占쏙옙占�
		String filePath = upPath + "log_"+ Function.fnToday(8);
		//System.out.println("upPath="+upPath);
		
		try {
			while(true) {
				log = new File(filePath + ".log");
				log.createNewFile();
				if(log.length() > 5*1024*1024) { //占쏙옙占쏙옙 占싸깍옙占쏙옙占쏙옙 크占썩가 5M占쏙옙 占쏙옙占쏙옙占쏙옙
					File old_log = new File(filePath +".old"); // 占쏙옙占쏙옙 占싱뱄옙 old占쏙옙 占쌍다몌옙 占쏙옙占쏙옙占�
					old_log.delete();
					log.renameTo(new File(filePath +".old"));
				} else {
					break;
				}
			}
			fos = new FileOutputStream(filePath +".log", true);
			ps = new PrintStream(fos, true); // Auto Flush..
			ps.print("["+today+"]");
			ps.print("\t");
			ps.print("["+pgm+"]");
			ps.print("\t");
			ps.println("["+msg+"]");
	   } catch (IOException ioe) {
	      ioe.printStackTrace();
	   } catch (Exception e) {
	      e.printStackTrace();
	   } finally {
	      ps.close();
	   }
	}

    public static String nullChk(Object obj)
    {
        String str = "";
        try
        {
            if(obj != null)
            {
                str = (String)obj;
                str = fnTagOff(str.trim());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return str;
    }

    public static int nullChk(Object obj, int replacement)
    {
        int returnValue = 0;
        String tmpString = "";
        try
        {
            if(obj != null)
            {
                tmpString = (String)obj;
                returnValue = Integer.parseInt(tmpString);
            }
        }
        catch(Exception e)
        {
            System.out.println("nullChk(Object obj, int replacement) has received an invalid argument ");
        }
        return returnValue;
    }

    public static List nullChk(String str[], int defaultVal)
    {
        int returnValue = 0;
        String tmpString = "";
        int tmpId = 0;
        List il = new ArrayList();
        try
        {
            if(str != null && str.length > 0)
            {
                String as[];
                int j = (as = str).length;
                for(int i = 0; i < j; i++)
                {
                    String s = as[i];
                    try
                    {
                        System.out.println((new StringBuilder("s : ")).append(s).toString());
                        tmpId = nullChk(s, defaultVal);
                        il.add(Integer.valueOf(tmpId));
                    }
                    catch(Exception exception) { }
                }

            }
        }
        catch(Exception e)
        {
            System.out.println("nullChk(String[] str, int defaultVal) has received an invalid argument ");
        }
        return il;
    }

    public static List nullChk(String str[])
    {
        int returnValue = 0;
        String tmpString = "";
        List sl = new ArrayList();
        try
        {
            if(str != null && str.length > 0)
            {
                String as[];
                int j = (as = str).length;
                for(int i = 0; i < j; i++)
                {
                    String s = as[i];
                    try
                    {
                        tmpString = nullChk(s);
                        sl.add(tmpString);
                    }
                    catch(Exception exception) { }
                }

            }
        }
        catch(Exception e)
        {
            System.out.println("nullChk(Object obj, int replacement) has received an invalid argument ");
        }
        return sl;
    }

    public static int nullChk(Integer i, int replacement)
    {
        int returnValue = 0;
        try
        {
            if(i != null)
                returnValue = i.intValue();
            else
                returnValue = replacement;
        }
        catch(Exception e)
        {
            System.out.println("nullChk(Object obj, int replacement) has received an invalid argument ");
        }
        return returnValue;
    }

    public static String nullChk(Object obj, String result)
    {
        String str = "";
        try
        {
            if(obj != null)
            {
                if(obj instanceof String)
                    str = (String)obj;
                else
                if(obj instanceof Integer)
                {
                    Integer tmp = (Integer)obj;
                    str = Integer.toString(tmp.intValue());
                } else
                {
                    str = (String)obj;
                }
                str = fnTagOff(str.trim());
            } else
            {
                str = result;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return str;
    }

    public static Long nullChk(String str, Long result)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            return result;
        } else
        {
            str = fnTagOff(str.trim());
            return Long.valueOf(Long.parseLong(str));
        }
    }

    public static String URLEncoderUTF8(String str)
    {
        if(str != null || !str.equals("null") || !str.equals(""))
            try
            {
                str = URLEncoder.encode(str, "UTF-8");
            }
            catch(UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        return str;
    }

    public static String SYSTEMID;
    public static String SITE_NAME = "TEST\u5360\uC2DC\uC3D9\uC619\u5360\uC3D9\uC619";
    public static String SITE_URL;
    public static String MARSTER_INFO = "";
    public static String MARSTER_MAIL = "";
    public static String mailer_path = "D:\\wwwKMS\\mailerV3\\mailfolder\\";
    public static String UPLOAD_PATH = "upload";
    public static String UPLOAD_URL;
    public static String UPLOAD_FileExtOK = "hwp,gul,doc,xls,ppt,zip,jpg,gif,swf,txt,bmp,xlsx,pptx";
    public static String UPLOAD_ImgExtOK = "jpg,gif";
    public static int fnPageNum = 10;
    public static String fnScSDate = "20070101";

    static 
    {
        SYSTEMID = "test";
        SITE_URL = (new StringBuilder("/")).append(SYSTEMID).append("/").toString();
        UPLOAD_URL = (new StringBuilder(String.valueOf(SITE_URL))).append("upload").toString();
    }
}
