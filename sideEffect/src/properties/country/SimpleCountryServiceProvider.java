// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCountryServiceProvider.java

package properties.country;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import kr.co.sgis.legacy.common.Function;

// Referenced classes of package properties.country:
//            CountryServiceProvider, SimpleCountryHistory, CountryHistory

public class SimpleCountryServiceProvider
    implements CountryServiceProvider
{

    public SimpleCountryServiceProvider()
    {
    }

    public CountryHistory buildOrEmpty(HttpServletRequest request, Class clas)
    {
        CountryHistory history = null;
        try
        {
            if(validate(request))
            {
                String lastModified = Function.nullChk(request.getParameter("lastModified"), "");
                String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
                int historyType = Function.nullChk(request.getParameter("historyType"), 0);
                String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
                history = new SimpleCountryHistory();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date lm = new Date();
                lm = sdf.parse(lastModified);
                history.setLastModified(lm);
                history.setActiveFrom(new Date());
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
            String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
            int historyType = Function.nullChk(request.getParameter("historyType"), 0);
            String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
            if("".equals(lastModified) || historyType == 0)
            {
                System.out.println((new StringBuilder("lastModified : ")).append(lastModified).toString());
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
        System.out.println((new StringBuilder("SimpleCountryServiceProvicer.validate : ")).append(result).toString());
        return result;
    }
}
