// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryDelegate.java

package safety.renewal.sgi.category;

import abstraction.SimpleDelegate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package safety.renewal.sgi.category:
//            ItemCategoryServiceProvider

public class SimpleItemCategoryDelegate extends SimpleDelegate
{

    public SimpleItemCategoryDelegate()
    {
    }

    public ItemCategoryServiceProvider getItemCategoryServiceProvider()
    {
        return itemCategoryServiceProvider;
    }

    public void setItemCategoryServiceProvider(ItemCategoryServiceProvider itemCategoryServiceProvider)
    {
        this.itemCategoryServiceProvider = itemCategoryServiceProvider;
    }

    public ModelAndView listItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.listItemCategory(request, response);
    }

    public ModelAndView popItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.popItemCategory(request, response);
    }

    public ModelAndView createItemCategoryPage(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.createItemCategoryPage(request, response);
    }

    public ModelAndView createItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.createItemCategory(request, response);
    }

    public ModelAndView readItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.readItemCategory(request, response);
    }

    public ModelAndView updateItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.updateItemCategory(request, response);
    }

    public ModelAndView updateItemCategoryPage(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.updateItemCategoryPage(request, response);
    }

    public ModelAndView deleteItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.deleteItemCategory(request, response);
    }

    public ModelAndView deleteItemHistory(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.deleteItemHistory(request, response);
    }

    public ModelAndView getChildMCN(HttpServletRequest request, HttpServletResponse respnse)
    {
        return itemCategoryServiceProvider.getChildMCN(request, respnse);
    }

    public ModelAndView isUniqueItemCode(HttpServletRequest request, HttpServletResponse response)
    {
        return itemCategoryServiceProvider.isUniqueItemCode(request, response);
    }

    private ItemCategoryServiceProvider itemCategoryServiceProvider;
}
