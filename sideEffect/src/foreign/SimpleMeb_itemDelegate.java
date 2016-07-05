// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMeb_itemDelegate.java

package foreign;

import abstraction.SimpleDelegate;
import java.io.PrintStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import org.springframework.web.servlet.ModelAndView;
import safety.renewal.company.SimpleCompany1;
import safety.renewal.sgi.item.ItemType;
import safety.renewal.sgi.item.SimpleItem1;

// Referenced classes of package foreign:
//            Meb_itemDAO, Meb_item

public class SimpleMeb_itemDelegate extends SimpleDelegate
{

    public SimpleMeb_itemDelegate()
    {
    }

    public Meb_itemDAO getMeb_itemDAO()
    {
        return meb_itemDAO;
    }

    public void setMeb_itemDAO(Meb_itemDAO meb_itemDAO)
    {
        this.meb_itemDAO = meb_itemDAO;
    }

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
    {
        List l = meb_itemDAO.list();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/view/jsp/meb_item/meb_itemList.jsp");
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView namedTest1(HttpServletRequest request, HttpServletResponse response)
    {
        List l = meb_itemDAO.namedList();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/view/jsp/meb_item/meb_itemList.jsp");
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView namedTest2(HttpServletRequest request, HttpServletResponse response)
    {
        List l = meb_itemDAO.namedList2();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/view/jsp/meb_item/meb_itemList.jsp");
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView manufImportEntpNameList(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int limit = pg * 10;
        long total = meb_itemDAO.manufCount(new Meb_item());
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameList.total  ")).append(total).toString());
        List l = null;
        l = meb_itemDAO.manufImportEntpNameList(pg, limit);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, "meb_item.do?action=manufImportEntpNameList", "");
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameList.limit  ")).append(limit).toString());
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameList.limit-10  ")).append(limit - 10).toString());
        mav.setViewName("/view/jsp/meb_item/manufImportEntpNameList1.jsp");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView getManufImportEntpNameList(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int limit = pg * 10;
        long total = meb_itemDAO.getManufCount(new SimpleCompany1());
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameList.total  ")).append(total).toString());
        List l = null;
        l = meb_itemDAO.getManufImportEntpNameList(pg, limit);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, "meb_item.do?action=getManufImportEntpNameList", "");
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameList.limit  ")).append(limit).toString());
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameList.limit-10  ")).append(limit - 10).toString());
        mav.setViewName("/view/jsp/meb_item/manufImportEntpNameList1_1.jsp");
        mav.addObject("titleImage", "view/style/images/title/sub03_05_01.jpg");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView manufImportEntpNameSearch1(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int limit = pg * 10;
        String sv = Function.nullChk(request.getParameter("sv"), "");
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameSearch1.sv  ")).append(sv).toString());
        String sv2 = Function.nullChk(request.getParameter("sv2"), "");
        String sv3 = Function.nullChk(request.getParameter("sv3"), "");
        String sv4 = Function.nullChk(request.getParameter("sv4"), "");
        long total = meb_itemDAO.manufCount1(new Meb_item(), sv);
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameSearch1().total  ")).append(total).toString());
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, "meb_item.do?action=manufImportEntpNameSearch1", "", sv, sv2, sv3, sv4);
        List l = null;
        if(sv.length() > 1)
            l = manufImportEntpNameSearch1(pg, limit, sv);
        mav.setViewName("/view/jsp/meb_item/manufImportEntpNameList2.jsp");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView manufImportEntpNameSearch2(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int limit = pg * 10;
        String sv = Function.nullChk(request.getParameter("sv"), "");
        String sv2 = Function.nullChk(request.getParameter("sv2"), "");
        String sv3 = Function.nullChk(request.getParameter("sv3"), "");
        String sv4 = Function.nullChk(request.getParameter("sv4"), "");
        long total = meb_itemDAO.manufCount2(new Meb_item(), sv, sv2);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, "meb_item.do?action=manufImportEntpNameSearch2", "", sv, sv2, sv3, sv4);
        List l = null;
        if(sv2.length() > 1)
            l = manufImportEntpNameSearch2(pg, limit, sv, sv2);
        if(sv.length() <= 1)
            l = meb_itemDAO.getManufImportEntpNameList(pg, limit);
        mav.setViewName("/view/jsp/meb_item/manufImportEntpNameList3.jsp");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView manufImportEntpNameSearch3(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int limit = pg * 10;
        String sv = Function.nullChk(request.getParameter("sv"), "");
        String sv2 = Function.nullChk(request.getParameter("sv2"), "");
        String sv3 = Function.nullChk(request.getParameter("sv3"), "");
        String sv4 = Function.nullChk(request.getParameter("sv4"), "");
        long total = meb_itemDAO.manufCount3(new Meb_item(), sv, sv2, sv3);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, "meb_item.do?action=manufImportEntpNameSearch3v", "", sv, sv2, sv3, sv4);
        List l = null;
        if(sv3.length() > 1)
            l = manufImportEntpNameSearch3(pg, limit, sv, sv2, sv3);
        mav.setViewName("/view/jsp/meb_item/manufImportEntpNameList4.jsp");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        return mav;
    }

    public List manufImportEntpNameSearch1(int pg, int limit, String sv)
    {
        List l = meb_itemDAO.manufImportEntpNameSearch1(pg, limit, sv);
        return l;
    }

    public List manufImportEntpNameSearch2(int pg, int limit, String sv, String sv2)
    {
        List l = meb_itemDAO.manufImportEntpNameSearch2(pg, limit, sv, sv2);
        return l;
    }

    public List manufImportEntpNameSearch3(int pg, int limit, String sv, String sv2, String sv3)
    {
        List l = meb_itemDAO.manufImportEntpNameSearch3(pg, limit, sv, sv2, sv3);
        return l;
    }

    public ModelAndView entp_name_search(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        String sv = Function.nullChk(request.getParameter("sv"), "");
        int limit = pg * 10;
        long total = meb_itemDAO.entpNameSearchCount(new SimpleCompany1(), sv);
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].entp_name_search.total  ")).append(total).toString());
        List l = null;
        l = meb_itemDAO.entpNameSearchList(pg, limit, sv);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, (new StringBuilder("meb_item.do?action=entp_name_search&sv=")).append(sv).toString(), "");
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].entp_name_search.limit  ")).append(limit).toString());
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].entp_name_search.limit-10  ")).append(limit - 10).toString());
        mav.setViewName("/view/jsp/meb_item/entpNameList.jsp");
        mav.addObject("titleImage", "view/style/images/title/sub03_05_01.jpg");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView item_list(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int seq = Function.nullChk(request.getParameter("seq"), 1);
        String sv = Function.nullChk(request.getParameter("sv"), "");
        String sv2 = Function.nullChk(request.getParameter("sv2"), "");
        String sv3 = Function.nullChk(request.getParameter("sv3"), "");
        String sv4 = Function.nullChk(request.getParameter("sv4"), "");
        String entp_name = Function.nullChk(request.getParameter("entp_name"), "");
        int limit = pg * 10;
        long total = meb_itemDAO.item_listCount(new SimpleItem1(), pg, limit, sv, seq, entp_name);
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].item_list.total  ")).append(total).toString());
        List l = null;
        l = meb_itemDAO.item_list(pg, limit, sv, seq, entp_name);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, (new StringBuilder("meb_item.do?action=item_list&seq=")).append(seq).append("&entp_name=").append(entp_name).toString(), "");
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].item_list.limit  ")).append(limit).toString());
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].item_list.limit-10  ")).append(limit - 10).toString());
        mav.setViewName("/view/jsp/meb_item/itemList.jsp");
        mav.addObject("titleImage", "view/style/images/title/sub03_05_01.jpg");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView item_search(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int seq = Function.nullChk(request.getParameter("seq"), 1);
        int itemSeq = Function.nullChk(request.getParameter("itemSeq"), 1);
        String sv = Function.nullChk(request.getParameter("sv"), "");
        String sv2 = Function.nullChk(request.getParameter("sv2"), "");
        String entp_name = Function.nullChk(request.getParameter("entp_name"), "");
        int limit = pg * 10;
        long total = meb_itemDAO.item_searchCount(new SimpleItem1(), pg, limit, sv, sv2, seq, entp_name);
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].item_search.total  ")).append(total).toString());
        List l = null;
        l = meb_itemDAO.item_search(pg, limit, sv2, seq, entp_name);
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].item_search.l.size()  ")).append(l.size()).toString());
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, (new StringBuilder("meb_item.do?action=item_search&sv=")).append(sv).append("&seq=").append(seq).toString(), "");
        mav.setViewName("/view/jsp/meb_item/itemList.jsp");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        return mav;
    }

    public ModelAndView meb_type(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int seq = Function.nullChk(request.getParameter("seq"), 1);
        int itemSeq = Function.nullChk(request.getParameter("itemSeq"), 1);
        String sv = Function.nullChk(request.getParameter("sv"), "");
        String sv2 = Function.nullChk(request.getParameter("sv2"), "");
        String sv3 = Function.nullChk(request.getParameter("sv3"), "");
        String entp_name = Function.nullChk(request.getParameter("entp_name"), "");
        String item_name = Function.nullChk(request.getParameter("item_name"), "");
        int limit = pg * 10;
        long total = 0L;
        List l = null;
        String itemName = "";
        try
        {
            total = meb_itemDAO.searchCount(new SimpleItem1(), sv3, itemSeq);
            System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].meb_type.total  ")).append(total).toString());
            l = meb_itemDAO.search(pg, limit, itemSeq, sv3);
            if(l.size() > 0)
            {
                Iterator itr = ((SimpleItem1)l.get(0)).getItem_type().iterator();
                itemName = ((ItemType)itr.next()).getType_name();
                mav.addObject("mebTypeInfoList", ((SimpleItem1)l.get(0)).getItem_type());
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println((new StringBuilder("\uD615\uBA85\uAC80\uC0C9 IndexOutOfBoundsException  ")).append(e.getMessage()).toString());
        }
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, (new StringBuilder("meb_item.do?action=meb_type&itemSeq=")).append(itemSeq).append("&sv3=").append(sv3).append("&entp_name=").append(entp_name).append("&item_name=").append(item_name).toString(), "");
        mav.setViewName("/view/jsp/meb_item/list.jsp");
        mav.addObject("titleImage", "view/style/images/title/sub03_05_01.jpg");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pg", Integer.valueOf(pg));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("list", l);
        mav.addObject("itemSeq", Integer.valueOf(itemSeq));
        mav.addObject("item_name", item_name);
        return mav;
    }

    private Meb_itemDAO meb_itemDAO;
}
