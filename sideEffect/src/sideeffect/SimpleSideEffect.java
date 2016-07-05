// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideEffect.java

package sideeffect;

import changelog.Changelog;
import statistics.property.ReportDate;

// Referenced classes of package sideeffect:
//            Sideeffect, SideeffectCode

public class SimpleSideEffect
    implements Sideeffect
{

    public SimpleSideEffect()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public SideeffectCode getSideeffectCode()
    {
        return sideeffectCode;
    }

    public void setSideeffectCode(SideeffectCode sideeffectCode)
    {
        this.sideeffectCode = sideeffectCode;
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel)
    {
        this.currentLevel = currentLevel;
    }

    public int[] getLevelHierarchy()
    {
        return levelHierarchy;
    }

    public void setLevelHierarchy(int levelHierarchy[])
    {
        this.levelHierarchy = levelHierarchy;
    }

    public int getFDACode()
    {
        return FDACode;
    }

    public void setFDACode(int fDACode)
    {
        FDACode = fDACode;
    }

    public String getFDASourcePTKOR()
    {
        return FDASourcePTKOR;
    }

    public void setFDASourcePTKOR(String fDASourcePTKOR)
    {
        FDASourcePTKOR = fDASourcePTKOR;
    }

    public String getFDASourcePTENG()
    {
        return FDASourcePTENG;
    }

    public void setFDASourcePTENG(String fDASourcePTENG)
    {
        FDASourcePTENG = fDASourcePTENG;
    }

    public String getFDASourceDefinitionKOR()
    {
        return FDASourceDefinitionKOR;
    }

    public void setFDASourceDefinitionKOR(String fDASourceDefinitionKOR)
    {
        FDASourceDefinitionKOR = fDASourceDefinitionKOR;
    }

    public String getFDASourceDefinitionENG()
    {
        return FDASourceDefinitionENG;
    }

    public void setFDASourceDefinitionENG(String fDASourceDefinitionENG)
    {
        FDASourceDefinitionENG = fDASourceDefinitionENG;
    }

    public String getNCItDefinitionKORfinal()
    {
        return NCItDefinitionKORfinal;
    }

    public void setNCItDefinitionKORfinal(String nCItDefinitionKORfinal)
    {
        NCItDefinitionKORfinal = nCItDefinitionKORfinal;
    }

    public String getNCItDefinition()
    {
        return NCItDefinition;
    }

    public void setNCItDefinition(String nCItDefinition)
    {
        NCItDefinition = nCItDefinition;
    }

    public Changelog getChangelog()
    {
        return changelog;
    }

    public void setChangelog(Changelog changelog)
    {
        this.changelog = changelog;
    }

    public ReportDate getReportYM()
    {
        return reportYM;
    }

    public void setReportYM(ReportDate reportYM)
    {
        this.reportYM = reportYM;
    }

    private int id;
    private SideeffectCode sideeffectCode;
    private int currentLevel;
    private int levelHierarchy[];
    private int FDACode;
    private String FDASourcePTKOR;
    private String FDASourcePTENG;
    private String FDASourceDefinitionKOR;
    private String FDASourceDefinitionENG;
    private String NCItDefinitionKORfinal;
    private String NCItDefinition;
    private Changelog changelog;
    private ReportDate reportYM;
}
