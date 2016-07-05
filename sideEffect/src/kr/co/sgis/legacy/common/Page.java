// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Page.java

package kr.co.sgis.legacy.common;

import java.io.PrintStream;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

// Referenced classes of package kr.co.sgis.legacy.common:
//            Function

public class Page
{

    public Page()
    {
        intPageNum = Function.fnPageNum;
        imageContext = "view/style/images/system/";
    }

    public String pageList(int totalNum, int listnum, int curPage, String linkPage, String htmlPath)
    {
        String pageList = "";
        String allPageList = "";
        String subPageList = "";
        String pageNumberDivision = "<span style='border-left: 1px solid grey;height:10px;'></span>";
        int startNum = ((curPage - 1) / intPageNum) * intPageNum + 1;
        int endNum = (((curPage - 1) + intPageNum) / intPageNum) * intPageNum;
        int totalPage;
        if(totalNum % listnum == 0)
            totalPage = totalNum / listnum;
        else
            totalPage = totalNum / listnum + 1;
        if(endNum > totalPage)
            endNum = totalPage;
        if(totalNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=1' onFocus='this.blur();'><img src='").append(imageContext).append("/btn_prev_02.gif' align='absmiddle' border='0'></a>&nbsp;").toString();
        if(totalPage > intPageNum && curPage - intPageNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(Integer.toString(startNum - intPageNum)).append("' onFocus='this.blur();'><img src='").append(imageContext).append("/btn_prev_01.gif' align='absmiddle' border='0'></a>&nbsp;").toString();
        if(totalPage > 1)
        {
            for(int i = startNum; i <= endNum; i++)
            {
                int chkPage = i;
                subPageList = "";
                if(i == endNum)
                    pageNumberDivision = "";
                if(chkPage == curPage)
                    subPageList = (new StringBuilder("<b><font class='pageStringCurrent'>")).append(Integer.toString(i)).append("</font></b>&nbsp;").append(pageNumberDivision).append("&nbsp;&nbsp;").toString();
                else
                    subPageList = (new StringBuilder("<a href='")).append(linkPage).append("&pg=").append(Integer.toString(i)).append("' onFocus='this.blur();'>").append("<font class='pageString'>").append(Integer.toString(i)).append("</font>").append("</a>&nbsp;&nbsp;").append(pageNumberDivision).append("&nbsp;").toString();
                chkPage++;
                allPageList = (new StringBuilder(String.valueOf(allPageList))).append(subPageList).toString();
            }

            pageList = (new StringBuilder(String.valueOf(pageList))).append(allPageList).toString();
        } else
        {
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<b><font class='pageStringCurrent'>1</font></b>").toString();
            if(totalNum > 0)
                pageList = (new StringBuilder(String.valueOf(pageList))).append("&nbsp;").toString();
        }
        if(totalPage > intPageNum && totalPage >= startNum + intPageNum)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(Integer.toString(startNum + intPageNum)).append("' onFocus='this.blur();'> <img src='").append(htmlPath).append(imageContext).append("btn_next_01.gif' align='absmiddle' border='0'></a>&nbsp;&nbsp;&nbsp;").toString();
        if(totalNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(totalPage).append("' onFocus='this.blur();'><img src='").append(imageContext).append("btn_next_02.gif'").append("align='absmiddle' border='0'></a>&nbsp;").toString();
        return pageList;
    }

    public String pageList(int totalNum, int pageNem, int listnum, int curPage, String linkPage, String htmlPath)
    {
        String pageList = "<table align='center'><tr>";
        String allPageList = "";
        String subPageList = "";
        int startNum = ((curPage - 1) / intPageNum) * intPageNum + 1;
        int endNum = (((curPage - 1) + intPageNum) / intPageNum) * intPageNum;
        int totalPage;
        if(totalNum % listnum == 0)
            totalPage = totalNum / listnum;
        else
            totalPage = totalNum / listnum + 1;
        if(endNum > totalPage)
            endNum = totalPage;
        if(totalNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<td><a href ='").append(linkPage).append("&pg=1' onFocus='this.blur();'><img src='").append(htmlPath).append(imageContext).append("/bt_prev2.gif' align='absmiddle' width='7' height='5' border='0'> </a></td>").toString();
        if(totalPage > intPageNum && curPage - intPageNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<td><a href ='").append(linkPage).append("&pg=").append(Integer.toString(startNum - intPageNum)).append("' onFocus='this.blur();'><img src='").append(htmlPath).append(imageContext).append("/bt_prev.gif' align='absmiddle' width='3' height='5' border='0'> </a></td>").toString();
        if(totalPage > 1)
        {
            for(int i = startNum; i <= endNum; i++)
            {
                int chkPage = i;
                subPageList = "";
                if(chkPage == curPage)
                {
                    subPageList = (new StringBuilder("<td><b><font class='pageStringCurrent'>")).append(Integer.toString(i)).append("</font></b></td>").toString();
                    System.out.println("1");
                } else
                {
                    subPageList = (new StringBuilder("<td><a href='")).append(linkPage).append("&pg=").append(Integer.toString(i)).append("' onFocus='this.blur();' class='pageString'>").append(Integer.toString(i)).append("</a></td>").toString();
                }
                chkPage++;
                allPageList = (new StringBuilder(String.valueOf(allPageList))).append(subPageList).toString();
            }

            pageList = (new StringBuilder(String.valueOf(pageList))).append(allPageList).toString();
        } else
        {
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<td><b><font class='pageStringCurrent'>1</font></b></td>").toString();
        }
        if(totalPage > intPageNum && totalPage >= startNum + intPageNum)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<td><a href ='").append(linkPage).append("&pg=").append(Integer.toString(startNum + intPageNum)).append("' onFocus='this.blur();'> <img src='").append(htmlPath).append(imageContext).append("/bt_next_01.gif' align='absmiddle' width='3' height='5' border='0'></a></td>").toString();
        if(totalNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<td><a href ='").append(linkPage).append("&pg=").append(totalPage).append("' onFocus='this.blur();'><img src='").append(htmlPath).append(imageContext).append("/bt_next_02.gif' align='absmiddle' width='8' height='5' border='0'></a></td>").toString();
        pageList = (new StringBuilder(String.valueOf(pageList))).append("</tr></table>").toString();
        return pageList;
    }

    public String pageList(int totalNum, int listnum, int curPage, String linkPage, String htmlPath, HttpServletRequest request)
    {
        String pageList = "";
        String allPageList = "";
        String subPageList = "";
        linkPage = "?";
        Enumeration parameterNames = request.getParameterNames();
        String param = "";
        String parameterName = "";
        while(parameterNames.hasMoreElements()) 
        {
            parameterName = (String)parameterNames.nextElement();
            if(!parameterName.equals("pg"))
            {
                param = request.getParameter(parameterName);
                linkPage = (new StringBuilder(String.valueOf(linkPage))).append(parameterName).append("=").append(param).append("&").toString();
            }
        }
        String pageNumberDivision = "<span style='border-left: 1px solid grey;height:10px;'></span>";
        int startNum = ((curPage - 1) / intPageNum) * intPageNum + 1;
        int endNum = (((curPage - 1) + intPageNum) / intPageNum) * intPageNum;
        int totalPage;
        if(totalNum % listnum == 0)
            totalPage = totalNum / listnum;
        else
            totalPage = totalNum / listnum + 1;
        if(endNum > totalPage)
            endNum = totalPage;
        if(totalNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=1' onFocus='this.blur();'><img src='").append(imageContext).append("/btn_prev_02.gif' align='absmiddle' border='0'></a>&nbsp;").toString();
        if(totalPage > intPageNum && curPage - intPageNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(Integer.toString(startNum - intPageNum)).append("' onFocus='this.blur();'><img src='").append(imageContext).append("/btn_prev_01.gif' align='absmiddle' border='0'></a>&nbsp;").toString();
        if(totalPage > 1)
        {
            for(int i = startNum; i <= endNum; i++)
            {
                int chkPage = i;
                subPageList = "";
                if(i == endNum)
                    pageNumberDivision = "";
                if(chkPage == curPage)
                    subPageList = (new StringBuilder("<b><font class='pageStringCurrent'>")).append(Integer.toString(i)).append("</font></b>&nbsp;").append(pageNumberDivision).append("&nbsp;&nbsp;").toString();
                else
                    subPageList = (new StringBuilder("<a href='")).append(linkPage).append("&pg=").append(Integer.toString(i)).append("' onFocus='this.blur();'>").append("<font class='pageString'>").append(Integer.toString(i)).append("</font>").append("</a>&nbsp;&nbsp;").append(pageNumberDivision).append("&nbsp;").toString();
                chkPage++;
                allPageList = (new StringBuilder(String.valueOf(allPageList))).append(subPageList).toString();
            }

            pageList = (new StringBuilder(String.valueOf(pageList))).append(allPageList).toString();
        } else
        {
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<b><font class='pageStringCurrent'>1</font></b>").toString();
            if(totalNum > 0)
                pageList = (new StringBuilder(String.valueOf(pageList))).append("&nbsp;").toString();
        }
        if(totalPage > intPageNum && totalPage >= startNum + intPageNum)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(Integer.toString(startNum + intPageNum)).append("' onFocus='this.blur();'> <img src='").append(htmlPath).append(imageContext).append("btn_next_01.gif' align='absmiddle' border='0'></a>&nbsp;&nbsp;&nbsp;").toString();
        if(totalNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(totalPage).append("' onFocus='this.blur();'><img src='").append(imageContext).append("btn_next_02.gif'").append("align='absmiddle' border='0'></a>&nbsp;").toString();
        return pageList;
    }

    public String pageList(int totalNum, int listnum, int curPage, String linkPage, String htmlPath, String sv, String sv2, 
            String sv3, String sv4)
    {
        System.out.println((new StringBuilder("[Page].totalNum  ")).append(totalNum).toString());
        System.out.println((new StringBuilder("[Page].listnum  ")).append(listnum).toString());
        System.out.println((new StringBuilder("[Page].curPage  ")).append(curPage).toString());
        System.out.println((new StringBuilder("[Page].linkPage  ")).append(linkPage).toString());
        System.out.println((new StringBuilder("[Page].htmlPath  ")).append(htmlPath).toString());
        System.out.println((new StringBuilder("[Page].sv  ")).append(sv).toString());
        String pageList = "";
        String allPageList = "";
        String subPageList = "";
        String pageNumberDivision = "<span style='border-left: 1px solid grey;height:10px;'></span>";
        int startNum = ((curPage - 1) / intPageNum) * intPageNum + 1;
        int endNum = (((curPage - 1) + intPageNum) / intPageNum) * intPageNum;
        int totalPage;
        if(totalNum % listnum == 0)
            totalPage = totalNum / listnum;
        else
            totalPage = totalNum / listnum + 1;
        if(endNum > totalPage)
            endNum = totalPage;
        if(totalNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=1&sv=").append(sv).append("&sv2=").append(sv2).append("&sv3=").append(sv3).append("&sv4=").append(sv4).append("' onFocus='this.blur();'><img src='").append(imageContext).append("/btn_prev_02.gif' align='absmiddle' border='0'></a>&nbsp;").toString();
        if(totalPage > intPageNum && curPage - intPageNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(Integer.toString(startNum - intPageNum)).append("&sv=").append(sv).append("&sv2=").append(sv2).append("&sv3=").append(sv3).append("&sv4=").append(sv4).append("' onFocus='this.blur();'><img src='").append(imageContext).append("/btn_prev_01.gif' align='absmiddle' border='0'></a>&nbsp;").toString();
        if(totalPage > 1)
        {
            for(int i = startNum; i <= endNum; i++)
            {
                int chkPage = i;
                subPageList = "";
                if(i == endNum)
                    pageNumberDivision = "";
                if(chkPage == curPage)
                    subPageList = (new StringBuilder("<b><font class='pageStringCurrent'>")).append(Integer.toString(i)).append("</font></b>&nbsp;").append(pageNumberDivision).append("&nbsp;&nbsp;").toString();
                else
                    subPageList = (new StringBuilder("<a href='")).append(linkPage).append("&pg=").append(Integer.toString(i)).append("&sv=").append(sv).append("&sv2=").append(sv2).append("&sv3=").append(sv3).append("&sv4=").append(sv4).append("' onFocus='this.blur();'>").append("<font class='pageString'>").append(Integer.toString(i)).append("</font>").append("</a>&nbsp;&nbsp;").append(pageNumberDivision).append("&nbsp;").toString();
                chkPage++;
                allPageList = (new StringBuilder(String.valueOf(allPageList))).append(subPageList).toString();
            }

            pageList = (new StringBuilder(String.valueOf(pageList))).append(allPageList).toString();
        } else
        {
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<b><font class='pageStringCurrent'>1</font></b>").toString();
            if(totalNum > 0)
                pageList = (new StringBuilder(String.valueOf(pageList))).append("&nbsp;").toString();
        }
        if(totalPage > intPageNum && totalPage >= startNum + intPageNum)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(Integer.toString(startNum + intPageNum)).append("&sv=").append(sv).append("&sv2=").append(sv2).append("&sv3=").append(sv3).append("&sv4=").append(sv4).append("' onFocus='this.blur();'> <img src='").append(htmlPath).append(imageContext).append("btn_next_01.gif' align='absmiddle' border='0'></a>&nbsp;&nbsp;&nbsp;").toString();
        if(totalNum > 0)
            pageList = (new StringBuilder(String.valueOf(pageList))).append("<a href ='").append(linkPage).append("&pg=").append(totalPage).append("&sv=").append(sv).append("&sv2=").append(sv2).append("&sv3=").append(sv3).append("&sv4=").append(sv4).append("' onFocus='this.blur();'><img src='").append(imageContext).append("btn_next_02.gif'").append("align='absmiddle' border='0'></a>&nbsp;").toString();
        return pageList;
    }

    int intPageNum;
    String imageContext;
}
