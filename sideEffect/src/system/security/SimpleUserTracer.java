// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleUserTracer.java

package system.security;

import java.io.PrintStream;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

// Referenced classes of package system.security:
//            UserTracer

public class SimpleUserTracer
    implements UserTracer
{

    public SimpleUserTracer()
    {
    }

    public void doTrace(HttpServletRequest request)
    {
        sb = new StringBuilder();
        sb.append((new StringBuilder("getRemoteAddr: ")).append(request.getRemoteAddr()).append(" \n").toString());
        sb.append((new StringBuilder("getRequestURI: ")).append(request.getRequestURI()).append(" \n").toString());
        sb.append((new StringBuilder("getRequestURL: ")).append(request.getRequestURL()).append(" \n").toString());
        sb.append((new StringBuilder("getServletPath: ")).append(request.getServletPath()).append(" \n").toString());
        sb.append((new StringBuilder("getQueryString: ")).append(request.getQueryString()).append(" \n").toString());
        System.out.println(sb.toString());
    }

    public String analyseUserAction(String queryString)
    {
        return null;
    }

    public String extractTitle(HttpServletRequest request)
    {
        String titles[] = {
            "accountName", "clsssKorName", "name", "title"
        };
        String title = "";
        Enumeration paramNames = request.getParameterNames();
        String nextName = "";
        while(paramNames.hasMoreElements()) 
        {
            nextName = (String)paramNames.nextElement();
            String as[];
            int j = (as = titles).length;
            for(int i = 0; i < j; i++)
            {
                String t = as[i];
                if(nextName.indexOf(t) > -1)
                    title = request.getParameter(nextName);
            }

        }
        return null;
    }

    StringBuilder sb;
}
