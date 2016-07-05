// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSystemPropertyServiceProvider.java

package system.property;

import java.io.PrintStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package system.property:
//            SystemPropertyServiceProvider, SimpleSystemProperty, SystemProperty, SystemPropertyDAO

public class SimpleSystemPropertyServiceProvider
    implements SystemPropertyServiceProvider
{

    public SimpleSystemPropertyServiceProvider()
    {
    }

    public SystemPropertyDAO getSystemPropertyDAO()
    {
        return systemPropertyDAO;
    }

    public void setSystemPropertyDAO(SystemPropertyDAO systemPropertyDAO)
    {
        this.systemPropertyDAO = systemPropertyDAO;
    }

    public ModelAndView listSystemProperties(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        setDefaultViewSet(mav, request);
        SystemProperty sc = new SimpleSystemProperty();
        String name = request.getParameter("searchKeyword") != "" ? request.getParameter("searchKeyword") : null;
        sc.setPropertyValue(name);
        long total = systemPropertyDAO.count(sc);
        List dl = null;
        if(total > 0L)
        {
            System.out.println((new StringBuilder("nb of found items : ")).append(total).append(", proceeding to list").toString());
            dl = systemPropertyDAO.list(sc, pg, 10);
        }
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, "system.do?action=listSystemProperties", "", request);
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("list", dl);
        mav.addObject("pageString", pageString);
        mav.addObject("titleImg", "view/style/images/title/sub01_05.gif");
        mav.addObject("titleImage", "view/style/images/title/sign/top_img5.jpg");
        mav.addObject("contentName", "/view/jsp/system/listSystemProperty1.jsp");
        return mav;
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request)
    {
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/system/systemLeft.jsp");
        mav.addObject("rightName", request.getAttribute("rightName"));
        return mav;
    }

    public ModelAndView readSystemProperty(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        abstraction.IDValuePair found = systemPropertyDAO.read(SimpleSystemProperty.class, articleId);
        mav.addObject("article", found);
        mav.addObject("titleImg", "view/style/images/title/sub05_03.jpg");
        mav.addObject("titleImage", "view/style/images/title/sign/top_img5.jpg");
        mav.addObject("contentName", "/view/jsp/system/readSystemProperty1.jsp");
        return mav;
    }

    public ModelAndView updateSystemProperty(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        String titleImg = "view/style/images/title/sub03_07.jpg";
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        if(articleId > -1L)
        {
            SimpleSystemProperty target = (SimpleSystemProperty)systemPropertyDAO.read(SimpleSystemProperty.class, articleId);
            String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
            String propertyName = Function.nullChk(request.getParameter("propertyName"), "");
            System.out.println((new StringBuilder("updateSystemProperty().propertyName := ")).append(propertyName).toString());
            System.out.println((new StringBuilder("updateSystemProperty().propertyValue := ")).append(propertyValue).toString());
            target.setPropertyValue(propertyValue);
            target.setPropertyName(propertyName);
            SimpleSystemProperty prop = new SimpleSystemProperty();
            prop.setId(articleId);
            prop.setPropertyName(propertyName);
            prop.setPropertyValue(propertyValue);
            SimpleSystemProperty merged = (SimpleSystemProperty)systemPropertyDAO.update(prop);
            merged = (SimpleSystemProperty)systemPropertyDAO.read(SimpleSystemProperty.class, articleId);
            setDefaultViewSet(mav, request);
            mav.addObject("article", merged);
            mav.addObject("titleImg", titleImg);
            mav.setViewName((new StringBuilder("system.do?action=readSystemProperty&articleId=")).append(articleId).toString());
        } else
        {
            mav.addObject("message", "invalid articleId received");
            mav.addObject("contentName", "/view/jsp/common/defaultMessageDisplay1.jsp");
        }
        return mav;
    }

    public ModelAndView updateSystemPropertyPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        abstraction.IDValuePair found = systemPropertyDAO.read(SimpleSystemProperty.class, articleId);
        setDefaultViewSet(mav, request);
        mav.addObject("article", found);
        mav.addObject("titleImg", "view/style/images/title/sub05_03.jpg");
        mav.addObject("titleImage", "view/style/images/title/sign/top_img5.jpg");
        mav.addObject("contentName", "/view/jsp/system/updateSystemProperty1.jsp");
        return mav;
    }

    private SystemPropertyDAO systemPropertyDAO;
}
