// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleRemoteIOServiceProvider.java

package system.io;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class SimpleRemoteIOServiceProvider
{

    public SimpleRemoteIOServiceProvider()
    {
    }

    public boolean send(String httpDestination, String fileName, HttpServletRequest request)
    {
        boolean jobResult = false;
        byte data[] = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(httpDestination);
        MultipartEntity mpEntity = new MultipartEntity();
        ByteArrayBody bab = new ByteArrayBody(data, fileName);
        mpEntity.addPart("binaryBody", bab);
        post.setEntity(mpEntity);
        try
        {
            HttpResponse response = httpClient.execute(post);
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity);
            if(responseString != null && !"".equals(responseString) && Integer.parseInt(responseString) > 0)
                jobResult = true;
        }
        catch(ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return jobResult;
    }
}
