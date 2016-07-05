// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleDelegate.java

package abstraction;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public abstract class SimpleDelegate
{

    public SimpleDelegate()
    {
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav)
    {
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/common/defaultLeft.jsp");
        mav.addObject("rightName", "/view/jsp/members/loginBox.jsp");
        return mav;
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request)
    {
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/common/defaultLeft.jsp");
        mav.addObject("rightName", request.getAttribute("rightName"));
        return mav;
    }
}
