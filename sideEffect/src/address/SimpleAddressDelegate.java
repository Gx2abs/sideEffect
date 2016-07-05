// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAddressDelegate.java

package address;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package address:
//            SimpleAddressSeoul, AddressDAO, SimpleAddressGwangju, SimpleAddressDaegu, 
//            SimpleAddressDaejeon, SimpleAddressIncheon, SimpleAddressBusan, SimpleAddressUlsan, 
//            SimpleAddressGangwon, SimpleAddressGyeonggi, SimpleAddressGyeongsang_s, SimpleAddressGyeongsang_n, 
//            SimpleAddressJeolla_s, SimpleAddressJeolla_n, SimpleAddressChungcheong_s, SimpleAddressChungcheong_n, 
//            SimpleAddressSejong, Address

public class SimpleAddressDelegate
{

    public SimpleAddressDelegate()
    {
    }

    public AddressDAO getAddressDAO()
    {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO)
    {
        this.addressDAO = addressDAO;
    }

    public ModelAndView pop(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        mav = new ModelAndView();
        String inputName = Function.nullChk(request.getParameter("inputName"), "");
        mav.setViewName("/view/jsp/address/address.jsp");
        mav.addObject("inputName", inputName);
        return mav;
    }

    public ModelAndView popCompany(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        mav = new ModelAndView();
        mav.setViewName("/view/jsp/address/addressCompany.jsp");
        return mav;
    }

    public ModelAndView search(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        mav = new ModelAndView();
        String gubun = Function.nullChk(request.getParameter("gubun"), "");
        String key = Function.nullChk(request.getParameter("key"), "");
        if(gubun.equals(Integer.valueOf(1)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(2)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(3)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(4)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(5)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(6)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(7)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(8)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(9)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(10)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(11)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(12)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(13)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(14)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(15)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(16)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        } else
        if(gubun.equals(Integer.valueOf(17)))
        {
            java.util.List list = addressDAO.search(SimpleAddressSeoul.class, key);
            mav.addObject("list", list);
        }
        mav.setViewName("/view/jsp/address/address.jsp");
        return mav;
    }

    public ModelAndView searchCompany(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        int gubun = Function.nullChk(request.getParameter("gubun"), 0);
        String key = Function.nullChk(request.getParameter("key"), "");
        Page page = new Page();
        long total = 0L;
        Address sa = null;
        switch(gubun)
        {
        case 1: // '\001'
            sa = new SimpleAddressSeoul();
            break;

        case 2: // '\002'
            sa = new SimpleAddressGwangju();
            break;

        case 3: // '\003'
            sa = new SimpleAddressDaegu();
            break;

        case 4: // '\004'
            sa = new SimpleAddressDaejeon();
            break;

        case 5: // '\005'
            sa = new SimpleAddressIncheon();
            break;

        case 6: // '\006'
            sa = new SimpleAddressBusan();
            break;

        case 7: // '\007'
            sa = new SimpleAddressUlsan();
            break;

        case 8: // '\b'
            sa = new SimpleAddressGangwon();
            break;

        case 9: // '\t'
            sa = new SimpleAddressGyeonggi();
            break;

        case 10: // '\n'
            sa = new SimpleAddressGyeongsang_s();
            break;

        case 11: // '\013'
            sa = new SimpleAddressGyeongsang_n();
            break;

        case 12: // '\f'
            sa = new SimpleAddressJeolla_s();
            break;

        case 13: // '\r'
            sa = new SimpleAddressJeolla_n();
            break;

        case 14: // '\016'
            sa = new SimpleAddressChungcheong_s();
            break;

        case 15: // '\017'
            sa = new SimpleAddressChungcheong_n();
            break;

        case 16: // '\020'
            sa = new SimpleAddressSejong();
            break;

        case 17: // '\021'
            sa = new SimpleAddressGwangju();
            break;

        default:
            sa = null;
            break;
        }
        java.util.List list = addressDAO.search(sa, key, pg, 14);
        total = addressDAO.count(sa, key);
        String pageString = page.pageList((int)total, 14, pg, "address.do?action=popCompany", "", request);
        mav.setViewName("/view/jsp/address/addressCompany.jsp");
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("list", list);
        mav.addObject("pageString", pageString);
        return mav;
    }

    private AddressDAO addressDAO;
}
