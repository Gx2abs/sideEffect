// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthenticatorDelegate.java

package system.security;

import abstraction.SimpleDelegate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import kr.co.sgis.legacy.common.Function;
import member.Member;
import member.MemberDAO;
import org.springframework.web.servlet.ModelAndView;

public class AuthenticatorDelegate extends SimpleDelegate
{

    public AuthenticatorDelegate()
    {
    }

    public ModelAndView authenticateMemberPage(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav);
        mav.addObject("contentName", "/view/jsp/members/authenticateMember1.jsp");
        return mav;
    }

    public ModelAndView authenticateMember(HttpServletRequest request, HttpServletResponse respnse)
    {
        respnse.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try
        {
            out = respnse.getWriter();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav);
        String accountName = Function.nullChk(request.getParameter("accountName"), "");
        String clearPassword = Function.nullChk(request.getParameter("password"), "");
        Member member = null;
        if("".equals(accountName) || "".equals(clearPassword))
            mav.setViewName("members.do?action=authenticateMemberPage");
        else
        if((member = memberDAO.authenticate(accountName, clearPassword, 2)) != null)
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", accountName);
            session.setAttribute("user", member);
            mav.setViewName("redirect:sideEffectReport.do?action=list");
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uC544\uC774\uB514\uB098 \uBE44\uBC00\uBC88\uD638\uAC00 \uB9DE\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'members.do?action=logout'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView logout(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav);
        HttpSession session = request.getSession();
        session.invalidate();
        mav.setViewName("members.do?action=authenticateMemberPage");
        return mav;
    }

    public MemberDAO getMemberDAO()
    {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAO memberDAO)
    {
        this.memberDAO = memberDAO;
    }

    private MemberDAO memberDAO;
}
