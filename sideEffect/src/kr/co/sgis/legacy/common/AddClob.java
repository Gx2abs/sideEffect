// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AddClob.java

package kr.co.sgis.legacy.common;

import java.io.Writer;
import java.sql.*;
import oracle.sql.CLOB;

public class AddClob
{

    public AddClob()
    {
    }

    public static boolean setCLOBContent(Connection conn, String dbTableName, String clobFieldName, String condition, String clobContent)
        throws Exception
    {
        boolean result = false;
        try
        {
            String emptyString = (new StringBuilder("update ")).append(dbTableName).append(" set ").append(clobFieldName).append(" = empty_clob() where ").append(condition).toString();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(emptyString);
            conn.commit();
            stmt.close();
            String selectForUpdateString = (new StringBuilder("select ")).append(clobFieldName).append(" from ").append(dbTableName).append(" where ").append(condition).append(" for update").toString();
            PreparedStatement pstmt = conn.prepareStatement(selectForUpdateString);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                CLOB clob = (CLOB)rs.getClob(1);
                if(clob != null)
                {
                    Writer clobWriter = clob.getCharacterOutputStream();
                    clobWriter.write(clobContent.trim());
                    clobWriter.flush();
                    conn.commit();
                    result = true;
                }
            }
            rs.close();
            pstmt.close();
        }
        catch(Exception e)
        {
            try
            {
                conn.rollback();
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
                throw e2;
            }
            e.printStackTrace();
            throw e;
        }
        finally
        {
            return result;
        }
    }
}
