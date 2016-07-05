// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAttachmentDelegate.java

package system.io;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import kr.co.sgis.legacy.common.Function;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package system.io:
//            AttachmentDAO

public class SimpleAttachmentDelegate
{

    public SimpleAttachmentDelegate()
    {
    }

    public AttachmentDAO getAttachmentDAO()
    {
        return attachmentDAO;
    }

    public void setAttachmentDAO(AttachmentDAO attachmentDAO)
    {
        this.attachmentDAO = attachmentDAO;
    }

    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        System.out.println("AttachmentDelegate.upload() ! ");
        boolean isMultipartRequest = ServletFileUpload.isMultipartContent(request);
        if(isMultipartRequest)
        {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = request.getSession().getServletContext();
            File repository = (File)servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List list;
            try
            {
                list = upload.parseRequest(request);
            }
            catch(FileUploadException e)
            {
                e.printStackTrace();
            }
        }
        return mav;
    }

    public ModelAndView setUploadSize(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        System.out.println("AttachmentDelegate.setUploadSize() ! ");
        return mav;
    }

    public ModelAndView fileDown(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        System.out.println("[SimpleAttachmentDelegate].fileDown()");
        try
        {
            mav.setViewName("/view/jsp/common/multiFileDownload.jsp");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return mav;
    }

    public ModelAndView fileDownloadAll(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("[SimpleAttachmentDelegate].fileDownloadAll().");
        ModelAndView mav = null;
        try
        {
            int articleId = Function.nullChk(request.getParameter("articleId"), 0);
            int type = Function.nullChk(request.getParameter("type"), 0);
            List fileList = attachmentDAO.get(articleId, type);
            System.out.println((new StringBuilder("[SimpleAttachmentDelegate].fileDownloadAll().fileList.size()  ")).append(fileList.size()).toString());
            mav = new ModelAndView();
            mav.setViewName("/view/jsp/common/multiFileDownload2.jsp");
            mav.addObject("attachment", fileList);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return mav;
    }

    private AttachmentDAO attachmentDAO;
}
