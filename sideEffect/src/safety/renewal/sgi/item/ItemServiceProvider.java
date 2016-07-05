// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemServiceProvider.java

package safety.renewal.sgi.item;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface ItemServiceProvider
{

    public abstract ModelAndView listItem(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView createItemPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView createItem(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView readItem(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateItemPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateItem(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteItem(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteItemHistory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteItemType(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView listCompanyPop(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView listItemCategoryPop(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView ProductChk(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException;

    public abstract ModelAndView listItemPopup(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView listItemPop(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);
}
