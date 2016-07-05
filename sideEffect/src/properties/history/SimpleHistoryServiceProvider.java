// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleHistoryServiceProvider.java

package properties.history;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import kr.co.sgis.legacy.common.Function;

// Referenced classes of package properties.history:
//            HistoryServiceProvider, History

public class SimpleHistoryServiceProvider
    implements HistoryServiceProvider
{

    public SimpleHistoryServiceProvider()
    {
    }

    public History buildOrEmpty(HttpServletRequest request, Class clas)
    {
        History history = null;
        String lastModified = "";
        String activeFrom = "";
        String historyManager = "";
        String historyDescription = "";
        int historyType = 0;
        try
        {
            if(validate(request))
            {
                activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
                historyManager = Function.nullChk(request.getParameter("historyManager"), "");
                historyType = Function.nullChk(request.getParameter("historyType"), 0);
                historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
                history = (History)clas.newInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date lm = new Date();
                Date af = new Date();
                af = sdf.parse(activeFrom);
                history.setLastModified(lm);
                history.setActiveFrom(af);
                history.setManager(historyManager);
                history.setHistoryDescription(historyDescription);
                history.setHistoryType(historyType);
            } else
            {
                System.out.println("empty history !");
                System.out.println((new StringBuilder("activeFrom := ")).append(activeFrom).toString());
                System.out.println((new StringBuilder("historyManager := ")).append(historyManager).toString());
                System.out.println((new StringBuilder("historyDescription := ")).append(historyDescription).toString());
                System.out.println((new StringBuilder("historyType := ")).append(historyType).toString());
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
            String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
            String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
            int historyType = Function.nullChk(request.getParameter("historyType"), 0);
            String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
            if("".equals(activeFrom) || historyType == 0)
            {
                System.out.println((new StringBuilder("activeFrom : ")).append(activeFrom).toString());
                System.out.println((new StringBuilder("historyType : ")).append(historyType).toString());
            } else
            {
                result = true;
            }
        }
        catch(Exception exception) { }
        System.out.println((new StringBuilder("SimpleCountryServiceProvicer.validate : ")).append(result).toString());
        return result;
    }
}
