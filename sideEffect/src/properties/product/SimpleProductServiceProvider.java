// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleProductServiceProvider.java

package properties.product;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import kr.co.sgis.legacy.common.Function;

// Referenced classes of package properties.product:
//            ProductServiceProvider, SimpleProductHistory, ProductHistory

public class SimpleProductServiceProvider
    implements ProductServiceProvider
{

    public SimpleProductServiceProvider()
    {
    }

    public ProductHistory buildOrEmpty(HttpServletRequest request)
    {
        ProductHistory history = null;
        try
        {
            if(validate(request))
            {
                String lastModified = Function.nullChk(request.getParameter("lastModified"), "");
                String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
                String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
                int historyType = Function.nullChk(request.getParameter("historyType"), 0);
                String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
                history = new SimpleProductHistory();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date lm = new Date();
                Date af = new Date();
                lm = sdf.parse(lastModified);
                af = sdf.parse(activeFrom);
                history.setLastModified(lm);
                history.setActiveFrom(af);
                history.setManager(historyManager);
                history.setHistoryDescription(historyDescription);
                history.setHistoryType(historyType);
            } else
            {
                System.out.println("empty history !");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return history;
    }

    public boolean validate(HttpServletRequest request)
    {
        boolean result = false;
        try
        {
            String lastModified = Function.nullChk(request.getParameter("lastModified"), "");
            String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
            String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
            int historyType = Function.nullChk(request.getParameter("historyType"), 0);
            String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
            if("".equals(lastModified) || "".equals(activeFrom) || historyType == 0)
            {
                System.out.println((new StringBuilder("lastModified : ")).append(lastModified).toString());
                System.out.println((new StringBuilder("activeFrom : ")).append(activeFrom).toString());
                System.out.println((new StringBuilder("historyType : ")).append(historyType).toString());
            } else
            {
                result = true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println((new StringBuilder("SimpleCompanyServiceProvicer.validate : ")).append(result).toString());
        return result;
    }
}
