// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemCategoryServiceProvider.java

package safety.renewal.sgi.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package safety.renewal.sgi.category:
//            ItemCategoryDAO

public interface ItemCategoryServiceProvider
{

    public abstract ItemCategoryDAO getItemCategoryDAO();

    public abstract void setItemCategoryDAO(ItemCategoryDAO itemcategorydao);

    public abstract ModelAndView listItemCategory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView popItemCategory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView createItemCategoryPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView createItemCategory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView readItemCategory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateItemCategory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateItemCategoryPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteItemCategory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteItemHistory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView getChildMCN(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView isUniqueItemCode(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);
}
