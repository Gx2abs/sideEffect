// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryGroupDelegate.java

package risk.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package risk.category:
//            ItemCategoryGroupServiceProvider

public class SimpleItemCategoryGroupDelegate
{

    public SimpleItemCategoryGroupDelegate()
    {
    }

    public ItemCategoryGroupServiceProvider getItemCategoryGroupServiceProvider()
    {
        return ItemCategoryGroupServiceProvider;
    }

    public void setItemCategoryGroupServiceProvider(ItemCategoryGroupServiceProvider itemCategoryGroupServiceProvider)
    {
        ItemCategoryGroupServiceProvider = itemCategoryGroupServiceProvider;
    }

    public ModelAndView listItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.listItemCategoryGroup(request, response);
    }

    public ModelAndView createItemCategoryGroupPage(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.createItemCategoryGroupPage(request, response);
    }

    public ModelAndView createItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.createItemCategoryGroup(request, response);
    }

    public ModelAndView readItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.readItemCategoryGroup(request, response);
    }

    public ModelAndView updateItemCategoryGroupPage(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.updateItemCategoryGroupPage(request, response);
    }

    public ModelAndView updateItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.updateItemCategoryGroup(request, response);
    }

    public ModelAndView deleteItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.deleteItemCategoryGroup(request, response);
    }

    public ModelAndView deleteItemCategoryGroupHistory(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.deleteItemCategoryGroupHistory(request, response);
    }

    public ModelAndView ItemCategoryGroupPopup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.ItemCategoryGroupPopup(request, response);
    }

    public ModelAndView ItemCategoryGroupPopupC(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.ItemCategoryGroupPopupC(request, response);
    }

    public ModelAndView revisionItemCategoryGroupPop(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.revisionItemCategoryGroupPop(request, response);
    }

    public ModelAndView revisionItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.revisionItemCategoryGroup(request, response);
    }

    public ModelAndView readItemCategoryGroupBack(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.readItemCategoryGroupBack(request, response);
    }

    public ModelAndView updateItemCategoryGroupBackPage(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.updateItemCategoryGroupBackPage(request, response);
    }

    public ModelAndView updateItemCategoryGroupBack(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.updateItemCategoryGroupBack(request, response);
    }

    public ModelAndView deleteItemCategoryGroupBack(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.deleteItemCategoryGroupBack(request, response);
    }

    public ModelAndView startDateCheck(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.startDateCheck(request, response);
    }

    public ModelAndView getItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemCategoryGroupServiceProvider.getItemCategoryGroup(request, response);
    }

    private ItemCategoryGroupServiceProvider ItemCategoryGroupServiceProvider;
}
