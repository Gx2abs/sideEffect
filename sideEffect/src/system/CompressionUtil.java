// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompressionUtil.java

package system;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Stack;
import org.apache.commons.compress.archivers.zip.*;

public class CompressionUtil
{

    public CompressionUtil()
    {
    }

    public void unzip(File zippedFile)
        throws IOException
    {
        unzip(zippedFile, Charset.defaultCharset().name());
    }

    public void unzip(File zippedFile, String charsetName)
        throws IOException
    {
        unzip(zippedFile, zippedFile.getParentFile(), charsetName);
    }

    public void unzip(File zippedFile, File destDir)
        throws IOException
    {
        unzip(((InputStream) (new FileInputStream(zippedFile))), destDir, Charset.defaultCharset().name());
    }

    public void unzip(File zippedFile, File destDir, String charsetName)
        throws IOException
    {
        unzip(((InputStream) (new FileInputStream(zippedFile))), destDir, charsetName);
    }

    public void unzip(InputStream is, File destDir)
        throws IOException
    {
        unzip(is, destDir, Charset.defaultCharset().name());
    }

    public void unzip(InputStream is, File destDir, String charsetName)
        throws IOException
    {
        int nWritten = 0;
        byte buf[] = new byte[8192];
        ZipArchiveInputStream zis = new ZipArchiveInputStream(is, charsetName, false);
        ZipArchiveEntry entry;
        while((entry = zis.getNextZipEntry()) != null) 
        {
            String name = entry.getName();
            File target = new File(destDir, name);
            if(entry.isDirectory())
            {
                target.mkdirs();
                debug((new StringBuilder("dir  : ")).append(name).toString());
            } else
            {
                target.createNewFile();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));
                while((nWritten = zis.read(buf)) >= 0) 
                    bos.write(buf, 0, nWritten);
                bos.close();
                debug((new StringBuilder("file : ")).append(name).toString());
            }
        }
        zis.close();
    }

    public void zip(File src)
        throws IOException
    {
        zip(src, Charset.defaultCharset().name(), true);
    }

    public void zip(File src, boolean includeSrc)
        throws IOException
    {
        zip(src, Charset.defaultCharset().name(), includeSrc);
    }

    public void zip(File src, String charSetName, boolean includeSrc)
        throws IOException
    {
        zip(src, src.getParentFile(), charSetName, includeSrc);
    }

    public void zip(File src, OutputStream os)
        throws IOException
    {
        zip(src, os, Charset.defaultCharset().name(), true);
    }

    public void zip(File src, File destDir, String charSetName, boolean includeSrc)
        throws IOException
    {
        String fileName = src.getName();
        if(!src.isDirectory())
        {
            int pos = fileName.lastIndexOf(".");
            if(pos > 0)
                fileName = fileName.substring(0, pos);
        }
        fileName = (new StringBuilder(String.valueOf(fileName))).append(".zip").toString();
        File zippedFile = new File(destDir, fileName);
        if(!zippedFile.exists())
            zippedFile.createNewFile();
        zip(src, ((OutputStream) (new FileOutputStream(zippedFile))), charSetName, includeSrc);
    }

    public void zip(File src, OutputStream os, String charsetName, boolean includeSrc)
        throws IOException
    {
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
        zos.setEncoding(charsetName);
        byte buf[] = new byte[8192];
        Stack stack = new Stack();
        File root;
        if(src.isDirectory())
        {
            if(includeSrc)
            {
                stack.push(src);
                root = src.getParentFile();
            } else
            {
                File fs[] = src.listFiles();
                for(int i = 0; i < fs.length; i++)
                    stack.push(fs[i]);

                root = src;
            }
        } else
        {
            stack.push(src);
            root = src.getParentFile();
        }
        while(!stack.isEmpty()) 
        {
            File f = (File)stack.pop();
            String name = toPath(root, f);
            if(f.isDirectory())
            {
                debug((new StringBuilder("dir  : ")).append(name).toString());
                File fs[] = f.listFiles();
                for(int i = 0; i < fs.length; i++)
                    if(fs[i].isDirectory())
                        stack.push(fs[i]);
                    else
                        stack.add(0, fs[i]);

            } else
            {
                debug((new StringBuilder("file : ")).append(name).toString());
                ZipArchiveEntry ze = new ZipArchiveEntry(name);
                zos.putArchiveEntry(ze);
                FileInputStream fis = new FileInputStream(f);
                int length;
                while((length = fis.read(buf, 0, buf.length)) >= 0) 
                    zos.write(buf, 0, length);
                fis.close();
                zos.closeArchiveEntry();
            }
        }
        zos.close();
    }

    private String toPath(File root, File dir)
    {
        String path = dir.getAbsolutePath();
        path = path.substring(root.getAbsolutePath().length()).replace(File.separatorChar, '/');
        if(path.startsWith("/"))
            path = path.substring(1);
        if(dir.isDirectory() && !path.endsWith("/"))
            path = (new StringBuilder(String.valueOf(path))).append("/").toString();
        return path;
    }

    private static void debug(String msg)
    {
        if(debug)
            System.out.println(msg);
    }

    private static boolean debug = true;

}
