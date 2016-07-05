// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCustomParamMethodNameResolver.java

package common;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.mvc.multiaction.ParameterMethodNameResolver;

public class SimpleCustomParamMethodNameResolver extends ParameterMethodNameResolver
{

    public SimpleCustomParamMethodNameResolver()
    {
        paramName = "list";
    }

    public String getHandlerMethodName(HttpServletRequest request)
        throws NoSuchRequestHandlingMethodException
    {
        String name = "";
        try
        {
            System.out.println((new StringBuilder("Trying to get method name with param name : ")).append(paramName).toString());
            name = request.getParameter(paramName);
            if(name == null)
                name = defaultMethod;
            if("".equals(name))
                name = defaultMethod;
            System.out.println((new StringBuilder("trying to return method name of : ")).append(name).toString());
            return name;
        }
        catch(Exception exception)
        {
            return name;
        }
    }

    public void setParamName(String paramName)
    {
        this.paramName = paramName;
    }

    public void setDefaultMethod(String newDefaultMethod)
    {
        System.out.println("Setting Default Method...");
        defaultMethod = newDefaultMethod;
        System.out.println((new StringBuilder("Default Method set to : ")).append(defaultMethod).toString());
    }

    private String paramName;
    private String defaultMethod;
}
