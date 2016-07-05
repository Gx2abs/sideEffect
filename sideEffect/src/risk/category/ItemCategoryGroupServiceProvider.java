// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemCategoryGroupServiceProvider.java

package risk.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface ItemCategoryGroupServiceProvider
{

    public abstract ModelAndView listItemCategoryGroup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView createItemCategoryGroupPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView createItemCategoryGroup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView readItemCategoryGroup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateItemCategoryGroupPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateItemCategoryGroup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteItemCategoryGroup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteItemCategoryGroupHistory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView ItemCategoryGroupPopup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView ItemCategoryGroupPopupC(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView revisionItemCategoryGroupPop(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView revisionItemCategoryGroup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView readItemCategoryGroupBack(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateItemCategoryGroupBackPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateItemCategoryGroupBack(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteItemCategoryGroupBack(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView startDateCheck(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView getItemCategoryGroup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);
}
