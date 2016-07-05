// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemDelegate.java

package safety.renewal.sgi.item;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package safety.renewal.sgi.item:
//            ItemServiceProvider

public class SimpleItemDelegate
{

    public SimpleItemDelegate()
    {
    }

    public ItemServiceProvider getItemServiceProvider()
    {
        return ItemServiceProvider;
    }

    public void setItemServiceProvider(ItemServiceProvider publicationServiceProvider)
    {
        ItemServiceProvider = publicationServiceProvider;
    }

    public ModelAndView test(HttpServletRequest request, HttpServletResponse response)
    {
        return null;
    }

    public ModelAndView listItem(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.listItem(request, response);
    }

    public ModelAndView createItemPage(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.createItemPage(request, response);
    }

    public ModelAndView createItem(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.createItem(request, response);
    }

    public ModelAndView readItem(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.readItem(request, response);
    }

    public ModelAndView updateItem(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.updateItem(request, response);
    }

    public ModelAndView updateItemPage(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.updateItemPage(request, response);
    }

    public ModelAndView deleteItem(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.deleteItem(request, response);
    }

    public ModelAndView deleteItemHistory(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.deleteItemHistory(request, response);
    }

    public ModelAndView deleteItemType(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.deleteItemType(request, response);
    }

    public ModelAndView listCompanyPop(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.listCompanyPop(request, response);
    }

    public ModelAndView listItemCategoryPop(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.listItemCategoryPop(request, response);
    }

    public ModelAndView ProductChk(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        return ItemServiceProvider.ProductChk(request, response);
    }

    public ModelAndView listItemPopup(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.listItemPopup(request, response);
    }

    public ModelAndView listItemPop(HttpServletRequest request, HttpServletResponse response)
    {
        return ItemServiceProvider.listItemPop(request, response);
    }

    private ItemServiceProvider ItemServiceProvider;
}
