// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleBatchJobTableType.java

package system.batch;

import abstraction.AbstractIDValuePair;

public class SimpleBatchJobTableType extends AbstractIDValuePair
{

    public SimpleBatchJobTableType()
    {
    }

    public String getTmpIdSeqName()
    {
        return tmpIdSeqName;
    }

    public void setTmpIdSeqName(String tmpIdSeqName)
    {
        this.tmpIdSeqName = tmpIdSeqName;
    }

    public String getTemplateFile()
    {
        return templateFile;
    }

    public void setTemplateFile(String templateFile)
    {
        this.templateFile = templateFile;
    }

    public Long getNumberOfColumns()
    {
        return numberOfColumns;
    }

    public void setNumberOfColumns(Long numberOfColumns)
    {
        this.numberOfColumns = numberOfColumns;
    }

    public String getIdSequenceName()
    {
        return idSequenceName;
    }

    public void setIdSequenceName(String idSequenceName)
    {
        this.idSequenceName = idSequenceName;
    }

    public String getTempTableName()
    {
        return tempTableName;
    }

    public void setTempTableName(String tempTableName)
    {
        this.tempTableName = tempTableName;
    }

    public Class getClazz()
    {
        return clazz;
    }

    public void setClazz(Class clazz)
    {
        this.clazz = clazz;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    private Class clazz;
    private String className;
    private String tableName;
    private String tempTableName;
    private String idSequenceName;
    private Long numberOfColumns;
    private String templateFile;
    private String tmpIdSeqName;
}
