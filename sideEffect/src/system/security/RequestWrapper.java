// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestWrapper.java

package system.security;

import java.text.Normalizer;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper
{

    public RequestWrapper(HttpServletRequest request)
    {
        super(request);
    }

    public String getParameter(String name)
    {
        String parameter = null;
        String vals[] = (String[])getParameterMap().get(name);
        if(vals != null && vals.length > 0)
            parameter = vals[0];
        if(parameter != null && !parameter.equals("null"))
            parameter = sqlInjection(parameter);
        return parameter;
    }

    public Enumeration getParameterNames()
    {
        return Collections.enumeration(getParameterMap().keySet());
    }

    public String[] getParameterValues(String name)
    {
        return (String[])getParameterMap().get(name);
    }

    private String stripXSS(String value)
    {
        String cleanValue = null;
        if(value != null)
        {
            cleanValue = Normalizer.normalize(value, java.text.Normalizer.Form.NFD);
            cleanValue = cleanValue.replaceAll("<p>", "<br/>");
            cleanValue = cleanValue.replaceAll("< p>", "<br/>");
            cleanValue = cleanValue.replaceAll("< p> ", "<br/>");
            cleanValue = cleanValue.replaceAll("</p>", "");
            cleanValue = cleanValue.replaceAll("< /p>", "");
            cleanValue = cleanValue.replaceAll("< /p> ", "");
            cleanValue = sqlInjection(cleanValue);
        }
        return cleanValue;
    }

    public String sqlInjection(String value)
    {
        String cleanValue = value;
        cleanValue = cleanValue.replaceAll("<p>", "<br/>");
        cleanValue = cleanValue.replaceAll("< p>", "<br/>");
        cleanValue = cleanValue.replaceAll("< p> ", "<br/>");
        cleanValue = cleanValue.replaceAll("</p>", "");
        cleanValue = cleanValue.replaceAll("< /p>", "");
        cleanValue = cleanValue.replaceAll("< /p> ", "");
        cleanValue = cleanValue.replaceAll("where", "");
        cleanValue = cleanValue.replaceAll("'", "");
        cleanValue = cleanValue.replaceAll("--", "");
        cleanValue = cleanValue.replaceAll("--, #", " ");
        cleanValue = cleanValue.replaceAll("/* */", " ");
        cleanValue = cleanValue.replaceAll("' or 1=1--", " ");
        cleanValue = cleanValue.replaceAll("union", " ");
        cleanValue = cleanValue.replaceAll("drop", " ");
        cleanValue = cleanValue.replaceAll("execute", " ");
        cleanValue = cleanValue.replaceAll("boot", " ");
        cleanValue = cleanValue.replaceAll("-1 or", " ");
        cleanValue = cleanValue.replaceAll("-1' or", " ");
        cleanValue = cleanValue.replaceAll("../", " ");
        cleanValue = cleanValue.replaceAll("1=1", " ");
        cleanValue = cleanValue.replaceAll("--", " ");
        cleanValue = cleanValue.replaceAll("\"", " ");
        cleanValue = cleanValue.replaceAll("\n", " ");
        cleanValue = cleanValue.replaceAll("\r", " ");
        cleanValue = cleanValue.replaceAll("\r\n", " ");
        cleanValue = cleanValue.replaceAll("\\\"", " ");
        cleanValue = cleanValue.replaceAll("[?]", " ");
        return cleanValue;
    }

    public String htmlTagReplace(String str)
    {
        if(str == null || str.equals("null") || str.equals(""))
        {
            str = "";
        } else
        {
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("\"", "&quot;");
            str = str.replaceAll("`", "&quot;");
            str = str.replaceAll("/", "&#47;");
            str = str.replaceAll(";", "&#59;");
            str = str.replaceAll("%", "");
            str = str.replaceAll(",", "&#44;");
        }
        return str;
    }
}
