// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleBatchAdapter.java

package system.batch;

import com.hostei.ragnarok.apps.sideeffect.SideEffectReportInjector;
import java.io.*;
import java.util.Properties;

public class SimpleBatchAdapter
    implements Runnable
{

    public static void main(String args[])
    {
        try
        {
            Runtime r = Runtime.getRuntime();
            Process process = r.exec("java -jar C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\sideeffectReportInjector.jar batchJobId=335 excelFilePath=C:\\SGI_Project\\eclipse\\sideeffect_batch.xlsx");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void loadApplicationProperties()
    {
        try
        {
            File f = new File("application.properties");
            FileReader fr = new FileReader(f);
            applicationProperties.load(fr);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String getLibraryContextPath()
    {
        return applicationProperties.get("libraryContextPath").toString();
    }

    public String getTmpIdSeqName()
    {
        return tmpIdSeqName;
    }

    public void setTmpIdSeqName(String tmpIdSeqName)
    {
        this.tmpIdSeqName = tmpIdSeqName;
    }

    public String getTmpIdColumnName()
    {
        return tmpIdColumnName;
    }

    public void setTmpIdColumnName(String tmpIdColumnName)
    {
        this.tmpIdColumnName = tmpIdColumnName;
    }

    public String getSourceTableName()
    {
        return sourceTableName;
    }

    public Long getTableTypeId()
    {
        return tableTypeId;
    }

    public void setTableTypeId(Long tableTypeId)
    {
        if(tableTypeId != null)
            this.tableTypeId = tableTypeId;
    }

    public void setSourceTableName(String sourceTableName)
    {
        this.sourceTableName = sourceTableName;
    }

    public String getBatchGroupId()
    {
        return batchGroupId;
    }

    public void setBatchGroupId(String batchGroupId)
    {
        this.batchGroupId = batchGroupId;
    }

    public String getTargetTableName()
    {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName)
    {
        this.targetTableName = targetTableName;
    }

    public String getIdColumnName()
    {
        return idColumnName;
    }

    public void setIdColumnName(String idColumnName)
    {
        this.idColumnName = idColumnName;
    }

    public String getIdColumnValue()
    {
        return idColumnValue;
    }

    public void setIdColumnValue(String idColumnValue)
    {
        this.idColumnValue = idColumnValue;
    }

    public SimpleBatchAdapter()
    {
        libPath = "";
        filePath = "";
        tableTypeId = Long.valueOf(1L);
    }

    public SimpleBatchAdapter(String libPath)
    {
        this.libPath = "";
        filePath = "";
        tableTypeId = Long.valueOf(1L);
        this.libPath = libPath;
    }

    public SimpleBatchAdapter(String libPath, String filePath)
    {
        this.libPath = "";
        this.filePath = "";
        tableTypeId = Long.valueOf(1L);
        this.libPath = libPath;
        this.filePath = filePath;
    }

    public void run()
    {
        StringBuilder sb = new StringBuilder();
        String commands[] = null;
        try
        {
            System.out.println((new StringBuilder("SimpleBatchAdapter.tableTypeId := ")).append(tableTypeId).toString());
            if(tableTypeId.longValue() == 5L)
                commands = (new String[] {
                    "java", " -jar", " C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\companyExcelBatch.jar", (new StringBuilder(" batchGroupId=")).append(batchGroupId).toString(), (new StringBuilder(" excelFilePath=")).append(filePath).toString(), " prikaz=create"
                });
            else
            if(tableTypeId.longValue() == 7L)
                commands = (new String[] {
                    "java", " -jar", " C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\sideeffectReportInjector.jar", (new StringBuilder(" batchJobId=")).append(batchGroupId).toString(), (new StringBuilder(" excelFilePath=")).append(filePath).toString(), (new StringBuilder(" mode=")).append(mode).toString()
                });
            else
            if(tableTypeId.longValue() == 8L)
                commands = (new String[] {
                    "java", " -jar", " C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\safetyReportManager.jar", (new StringBuilder(" batchJobId=")).append(batchGroupId).toString(), (new StringBuilder(" excelFilePath=")).append(filePath).toString(), (new StringBuilder(" mode=")).append(mode).toString()
                });
            else
                commands = (new String[] {
                    "java", " -jar ", libPath, (new StringBuilder(" jdbc_driver_class=com.tmax.tibero.jdbc.TbDriver db_url=jdbc:tibero:thin:@172.16.0.54:8629:mditac db_user=sideeffect db_password=mditac1202 batch_group_id=")).append(batchGroupId).append(" table_type_id=").append(tableTypeId).append(" log_table_name=sgi_se_batch_failure_log id_column_name=id id_value=").append(tmpIdSeqName).append(" mode=0  batch_job_table_name=sgi_se_batch_job_group table=").append(targetTableName).toString(), (new StringBuilder(" input=")).append(filePath).toString()
                });
            String as[];
            int j = (as = commands).length;
            for(int i = 0; i < j; i++)
            {
                String s = as[i];
                System.out.printf(s, new Object[0]);
                sb.append(s);
            }

            Process process = Runtime.getRuntime().exec(sb.toString());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void apply()
    {
        try
        {
            String commands[] = null;
            if(tableTypeId.longValue() == 5L)
            {
                libPath = "";
                commands = (new String[] {
                    "java", " -jar", libPath, " prikaz=move", (new StringBuilder("batchGroupId=")).append(batchGroupId).toString()
                });
            } else
            {
                commands = (new String[] {
                    "java", " -jar ", libPath, (new StringBuilder(" jdbc_driver_class=com.tmax.tibero.jdbc.TbDriver db_url=jdbc:tibero:thin:@172.16.0.54:8629:mditac db_user=sideeffect db_password=mditac1202 batch_group_id=")).append(batchGroupId).append(" to_table=").append(targetTableName).append(" from_table=").append(sourceTableName).append(" log_table_name=sgi_batch_failure_log mode=1 id_column_name=").append(idColumnName).append(" id_value=").append(idColumnValue).append(" mode=").append(getMode()).append(" command=").append(getCommand()).toString()
                });
            }
            StringBuilder sb = new StringBuilder();
            String as[];
            int j = (as = commands).length;
            for(int i = 0; i < j; i++)
            {
                String s = as[i];
                System.out.printf(s, new Object[0]);
                sb.append(s);
            }

            Process process = Runtime.getRuntime().exec(sb.toString());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void insertSideeffectReport(Integer batchJobId, String filePath)
    {
        SideEffectReportInjector injector = new SideEffectReportInjector();
        injector.inject(batchJobId, filePath);
    }

    public void moveSideeffect()
    {
        try
        {
            String commands[] = {
                "java", " -jar ", libPath, (new StringBuilder(" batchJobId=")).append(getBatchGroupId()).toString()
            };
            StringBuilder sb = new StringBuilder();
            String as[];
            int j = (as = commands).length;
            for(int i = 0; i < j; i++)
            {
                String s = as[i];
                System.out.printf(s, new Object[0]);
                sb.append(s);
            }

            Process process = Runtime.getRuntime().exec(sb.toString());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createBatchJob()
    {
        try
        {
            System.out.println((new StringBuilder("SimpleBatchAdapter.tableTypeId := ")).append(tableTypeId).toString());
            StringBuilder sb = new StringBuilder();
            String commands[] = null;
            Runnable job = null;
            if(tableTypeId.longValue() == 5L)
                commands = (new String[] {
                    "java", " -jar", " C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\companyExcelBatch.jar", (new StringBuilder(" batchGroupId=")).append(batchGroupId).toString(), (new StringBuilder(" excelFilePath=")).append(filePath).toString(), " prikaz=create"
                });
            else
            if(tableTypeId.longValue() == 7L)
                commands = (new String[] {
                    "java", " -jar", " C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\sideeffectReportInjector.jar", (new StringBuilder(" batchJobId=")).append(batchGroupId).toString(), (new StringBuilder(" excelFilePath=")).append(filePath).toString()
                });
            else
            if(tableTypeId.longValue() == 8L)
                commands = (new String[] {
                    "java", " -jar", " C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\safetyInfoExcelManager.jar", (new StringBuilder(" batch_job_id=")).append(batchGroupId).toString(), (new StringBuilder(" input_file=")).append(filePath).append(" command=create").toString()
                });
            else
            if(tableTypeId.longValue() == 6L)
                commands = (new String[] {
                    "java", " -jar ", " C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\itemExcelBatch.jar", (new StringBuilder(" batchJobId=")).append(batchGroupId).append(" tempTable=").append(tableTypeId).append("  actualTable=").append(targetTableName).toString(), (new StringBuilder(" filePath=")).append(filePath).append(" command=create").toString()
                });
            else
                commands = (new String[] {
                    "java", " -jar ", libPath, (new StringBuilder(" jdbc_driver_class=com.tmax.tibero.jdbc.TbDriver db_url=jdbc:tibero:thin:@172.16.0.54:8629:mditac db_user=sideeffect db_password=mditac1202 batch_group_id=")).append(batchGroupId).append(" table_type_id=").append(tableTypeId).append(" log_table_name=sgi_se_batch_failure_log id_column_name=id id_value=").append(tmpIdSeqName).append(" mode=0  batch_job_table_name=sgi_se_batch_job_group table=").append(targetTableName).toString(), (new StringBuilder(" input=")).append(filePath).toString()
                });
            String as[];
            int j = (as = commands).length;
            for(int i = 0; i < j; i++)
            {
                String s = as[i];
                System.out.printf(s, new Object[0]);
                sb.append(s);
            }

            Process p = Runtime.getRuntime().exec(sb.toString());
            Thread worker = new Thread(job);
            worker.run();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public Integer getMode()
    {
        return mode;
    }

    public void setMode(Integer mode)
    {
        this.mode = mode;
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    private String libPath;
    private String filePath;
    private String idColumnName;
    private String idColumnValue;
    private String targetTableName;
    private String sourceTableName;
    private String batchGroupId;
    private Long tableTypeId;
    private String tmpIdColumnName;
    private String tmpIdSeqName;
    private Integer mode;
    private String command;
    private static Properties applicationProperties = null;
    private static String libraryContextPath;

    static 
    {
        System.out.println((new StringBuilder("System.getProperty(user.dir):= ")).append(System.getProperty("user.dir")).toString());
    }
}
