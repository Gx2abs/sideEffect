// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAttachmentServiceProvider.java

package system.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import kr.co.sgis.legacy.common.Function;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

// Referenced classes of package system.io:
//            AttachmentServiceProvider, SimpleAttachment, Attachment, AttachmentDAO

public class SimpleAttachmentServiceProvider
    implements AttachmentServiceProvider
{

    public SimpleAttachmentServiceProvider()
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

    List upload(HttpServletRequest request, HttpServletResponse response, int returnAddNum)
    {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        int result = 0;
        List addIdArr = new ArrayList();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date current = new Date();
        String date = formater.format(current);
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String fileUploadPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println((new StringBuilder("fileUploadPath  ")).append(fileUploadPath).toString());
        String upPath0 = (new StringBuilder(String.valueOf(fileUploadPath))).append(Function.fnUploadPath("")).toString();
        String upPath1 = (new StringBuilder(String.valueOf(upPath0))).append(File.separator).append("sideEffectReportFile").toString();
        String upPath2 = (new StringBuilder(String.valueOf(upPath1))).append(File.separator).append(year).toString();
        String upPath3 = (new StringBuilder(String.valueOf(upPath2))).append(File.separator).append(month).toString();
        Function.fnFolderChk(upPath0);
        Function.fnFolderChk(upPath1);
        Function.fnFolderChk(upPath2);
        Function.fnFolderChk(upPath3);
        System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].upPath3  ")).append(upPath3).toString());
        String full_url = (new StringBuilder(String.valueOf(File.separator))).append("sideEffectReportFile").append(File.separator).append(year).append(File.separator).append(month).toString();
        Iterator fileNameIter = multipartRequest.getFileNames();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String organizedfilePath = "";
        UUID randomeUUID = null;
        try
        {
            int i = 0;
            while(fileNameIter.hasNext()) 
            {
                String key = (String)fileNameIter.next();
                MultipartFile file = multipartRequest.getFile(key);
                String originaFileName = file.getOriginalFilename();
                String contentType = file.getContentType();
                long fileSize = file.getSize();
                String fileType = "";
                String fileName = multipartRequest.getParameter((new StringBuilder("fileName_")).append(i).toString());
                i++;
                System.out.println((new StringBuilder("[SimpleSideeffectReportDelegate].attachment.fileName  ")).append(fileName).toString());
                originaFileName = originaFileName.toLowerCase();
                System.out.println((new StringBuilder("[SimpleSideeffectReportDelegate].attachment.contentType  ")).append(contentType).toString());
                System.out.println((new StringBuilder("[SimpleSideeffectReportDelegate].attachment.originaFileName  ")).append(originaFileName).toString());
                int listIndex = originaFileName.lastIndexOf(".");
                int fileNameLength = originaFileName.length();
                String extension = originaFileName.substring(listIndex + 1, fileNameLength);
                inputStream = file.getInputStream();
                if(fileSize > 0L)
                {
                    result++;
                    File realUploadDir = new File(fileUploadPath);
                    if(!realUploadDir.exists())
                        realUploadDir.mkdirs();
                    randomeUUID = UUID.randomUUID();
                    String uid = randomeUUID.toString();
                    String strUid = (new StringBuilder(String.valueOf(uid.substring(0, 7)))).append(uid.substring(14, 17)).toString();
                    System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].attachment.strUid  ")).append(strUid).toString());
                    full_url = (new StringBuilder(String.valueOf(full_url))).append("/").append(strUid).append("_").append(file.getOriginalFilename()).toString();
                    System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].create.full_url  ")).append(full_url).toString());
                    organizedfilePath = (new StringBuilder(String.valueOf(fileUploadPath))).append("/").append(strUid).append("_").append(file.getOriginalFilename()).toString();
                    System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].organizedfilePath  ")).append(organizedfilePath).toString());
                    outputStream = new FileOutputStream(organizedfilePath);
                    int readByte = 0;
                    byte buffer[] = new byte[8192];
                    while((readByte = inputStream.read(buffer, 0, 8120)) != -1) 
                    {
                        System.out.println("fileWrire");
                        outputStream.write(buffer, 0, readByte);
                    }
                    Attachment attVO = new SimpleAttachment();
                    attVO.setExtension(extension);
                    attVO.setFullURL(full_url);
                    attVO.setName(file.getOriginalFilename());
                    attVO.setPhysicalPath((new StringBuilder(String.valueOf(strUid))).append("_").append(file.getOriginalFilename()).toString());
                    attVO.setRelativeLogicalPath("");
                    attVO.setTable_id(returnAddNum);
                    attVO.setType(1);
                    attVO.setFileName(fileName);
                    attVO.setLogical_name((new StringBuilder(String.valueOf(strUid))).append("_").append(file.getOriginalFilename()).toString());
                    int returnAddAttachmentId = attachmentDAO.add(attVO);
                    addIdArr.add(Integer.valueOf(returnAddAttachmentId));
                    System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].attachment.returnAddAttachmentId  ")).append(returnAddAttachmentId).toString());
                }
            }
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder(" Exception  ")).append(e.getMessage()).toString());
            e.printStackTrace();
        }
        return addIdArr;
    }

    public double getUploadSize()
    {
        System.out.println("SimpleAttachmentServiceProvider.getUploadSize() ! ");
        double uploadSize = 0.0D;
        try
        {
            uploadSize = attachmentDAO.getUploadFileSize();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return uploadSize;
    }

    private AttachmentDAO attachmentDAO;
}
