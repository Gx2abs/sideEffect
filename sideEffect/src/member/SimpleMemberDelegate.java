// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMemberDelegate.java

package member;

import abstraction.SimpleDelegate;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import javax.servlet.http.*;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

// Referenced classes of package member:
//            SimpleMember, MemberDAO, Member

public class SimpleMemberDelegate extends SimpleDelegate
{

    public SimpleMemberDelegate()
    {
        viewContextPath = "/view/jsp/members";
    }

    public MemberDAO getMemberDAO()
    {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAO memberDAO)
    {
        this.memberDAO = memberDAO;
    }

    public ModelAndView listMember(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String titleImg = "view/style/images/title/sub04_01.jpg";
        String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
        String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
        SimpleMember target = new SimpleMember();
        Method methods[] = SimpleMember.class.getDeclaredMethods();
        try
        {
            Method amethod[];
            int j = (amethod = methods).length;
            for(int i = 0; i < j; i++)
            {
                Method m1 = amethod[i];
                if(m1.getName().equals(searchColumn))
                    m1.invoke(target, new Object[] {
                        searchKeyword
                    });
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        List simpleMemberList = memberDAO.list(target, pg, 10);
        long total = memberDAO.count(target);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = "";
        pageString = page.pageList((int)total, 10, pg, (new StringBuilder("members.do?action=listMember&searchColumn=")).append(searchColumn).append("&searchKeyword=").append(searchKeyword).toString(), "");
        System.out.println((new StringBuilder("simpleMemberList.size() : ")).append(simpleMemberList.size()).toString());
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("pageString", pageString);
        mav.addObject("list", simpleMemberList);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img4.jpg");
        mav.addObject("contentName", "/view/jsp/members/membersList1.jsp");
        return mav;
    }

    public ModelAndView createMemberPage(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String titleImg = "view/style/images/title/sub04_01.jpg";
        mav.addObject("contentName", "/view/jsp/members/createMember1.jsp");
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img4.jpg");
        return mav;
    }

    public ModelAndView createMember(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        Member member = buildMember(request);
        if(member != null)
        {
            String encrypted = memberDAO.generateEncrypted(member.getMemberPassword());
            member.setMemberPassword(encrypted);
            memberDAO.create(member);
            mav.addObject("message", "create succeess");
        } else
        {
            mav.addObject("message", "create failure !! ");
        }
        mav.addObject("contentName", "/view/jsp/common/defaultMessageDisplay1.jsp");
        mav.setViewName("members.do?action=listMember");
        return mav;
    }

    public ModelAndView updateMemberPage(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        String titleImg = "view/style/images/title/sub04_01.jpg";
        int articleId = Function.nullChk(request.getParameter("articleId"), 0);
        Member member = new SimpleMember();
        if(articleId > 0)
            member = (Member)memberDAO.read(SimpleMember.class, articleId);
        setDefaultViewSet(mav, request);
        mav.addObject("article", member);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img4.jpg");
        mav.addObject("contentName", "/view/jsp/members/updateMember1.jsp");
        return mav;
    }

    public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        int articleId = Function.nullChk(request.getParameter("articleId"), 0);
        Member member = buildMember(request);
        if(member != null)
        {
            member.setId(articleId);
            String encrypted = memberDAO.generateEncrypted(member.getMemberPassword());
            member.setMemberPassword(encrypted);
            memberDAO.update(member);
            mav.addObject("message", "update succeess");
        } else
        {
            mav.addObject("message", "update failure !! ");
        }
        mav.setViewName((new StringBuilder("members.do?action=readMember&articleId=")).append(articleId).toString());
        return mav;
    }

    public ModelAndView deleteMember(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        int articleId = Function.nullChk(request.getParameter("articleId"), 0);
        if(articleId > 0)
        {
            Member member = new SimpleMember();
            member.setId(articleId);
            memberDAO.delete(member);
        }
        setDefaultViewSet(mav, request);
        mav.setViewName("members.do?action=listMember");
        return mav;
    }

    public Member buildMember(HttpServletRequest request)
    {
        Member member = new SimpleMember();
        String accountName = "";
        String manager = "";
        String organisation = "";
        int privilege = 0;
        String telephoneNumber = "";
        String emailUserName = "";
        String emailDomain = "";
        String clearPassword = "";
        String passwordRepeat = "";
        try
        {
            accountName = Function.nullChk(request.getParameter("accountName"), "");
            manager = Function.nullChk(request.getParameter("manager"), "");
            organisation = Function.nullChk(request.getParameter("organisation"), "");
            privilege = Function.nullChk(request.getParameter("privilege"), 0);
            telephoneNumber = Function.nullChk(request.getParameter("telephoneNumber"), "");
            emailUserName = Function.nullChk(request.getParameter("emailUserName"), "");
            emailDomain = Function.nullChk(request.getParameter("emailDomain"), "");
            clearPassword = Function.nullChk(request.getParameter("password"), "");
            passwordRepeat = Function.nullChk(request.getParameter("passwordRepeat"), "");
            if(passwordRepeat.equals(clearPassword))
            {
                member.setAccountName(accountName);
                member.setManager(manager);
                member.setOrganisation(organisation);
                member.setPhoneNumber(telephoneNumber);
                member.setEmailUserName(emailUserName);
                member.setEmailDomain(emailDomain);
                member.setWholeEmail((new StringBuilder(String.valueOf(emailUserName))).append("@").append(emailDomain).toString());
                member.setPrivilegeId(privilege);
                member.setFirstCreated(new Date());
                member.setMemberPassword(clearPassword);
                if(!member.validate())
                {
                    System.out.println("member validation failure");
                    member = null;
                }
            } else
            {
                System.out.println("The two passwords don't match ! ");
                member = null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return member;
    }

    public ModelAndView readMember(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        String titleImg = "view/style/images/title/sub04_01.jpg";
        int articleId = Function.nullChk(request.getParameter("articleId"), 0);
        Member member = new SimpleMember();
        if(articleId > 0)
            member = (Member)memberDAO.read(SimpleMember.class, articleId);
        setDefaultViewSet(mav, request);
        mav.addObject("article", member);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img4.jpg");
        mav.addObject("contentName", "/view/jsp/members/readMember1.jsp");
        return mav;
    }

    public ModelAndView authenticateMemberPage(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String titleImg = "view/style/images/title/sub00_01.jpg";
        mav.addObject("titleImg", titleImg);
        mav.addObject("leftName", "/view/jsp/template/left.jsp");
        mav.addObject("contentName", "/view/jsp/members/authenticateMember1.jsp");
        return mav;
    }

    public ModelAndView authenticateMember(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String accountName = Function.nullChk(request.getParameter("accountName"), "");
        String clearPassword = Function.nullChk(request.getParameter("password"), "");
        System.out.println((new StringBuilder("accountName := ")).append(accountName).toString());
        System.out.println((new StringBuilder("clearPassword := ")).append(clearPassword).toString());
        if("".equals(accountName) || "".equals(clearPassword))
        {
            RedirectView rv = new RedirectView("members.do?action=authenticateMemberPage");
            mav.setView(rv);
        } else
        {
            Member member = null;
            if((member = memberDAO.authenticate(accountName, clearPassword, 2)) != null)
            {
                HttpSession session = request.getSession();
                session.setAttribute("user", member);
                mav.setViewName("properties.do?action=listItem");
            } else
            {
                mav.setViewName("members.do?action=authenticateMemberPage");
            }
        }
        return mav;
    }

    public ModelAndView logout(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        HttpSession session = request.getSession();
        session.invalidate();
        mav.setViewName("members.do?action=authenticateMemberPage");
        return mav;
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request)
    {
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/members/membersLeft.jsp");
        mav.addObject("rightName", request.getAttribute("rightName"));
        return mav;
    }

    public ModelAndView checkUniqueId(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String idToCheck = Function.nullChk(request.getParameter("idToCheck"), "");
        int result = -1;
        if(!"".equals(idToCheck))
            result = memberDAO.checkUniqueId(idToCheck);
        mav.addObject("message", Integer.valueOf(result));
        mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
        return mav;
    }

    private MemberDAO memberDAO;
    private String viewContextPath;
}
