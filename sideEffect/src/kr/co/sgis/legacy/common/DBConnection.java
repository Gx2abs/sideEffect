// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBConnection.java

package kr.co.sgis.legacy.common;

import java.io.PrintStream;
import java.sql.*;

public class DBConnection
{

    public DBConnection()
    {
    }

    public static Connection getConnection()
    {
        Connection con = null;
        try
        {
            String dbURL = "jdbc:tibero:thin:@172.16.0.60:8629:mditac";
            String dbUNM = "sideeffect";
            String dbUPW = "mditac1202";
            Driver myDriver = (Driver)Class.forName("com.tmax.tibero.jdbc.TbDriver").newInstance();
            if(myDriver == null)
                System.out.println("DBConnection. null connexion");
            con = DriverManager.getConnection(dbURL, dbUNM, dbUPW);
            con.setAutoCommit(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
