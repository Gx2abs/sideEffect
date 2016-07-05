// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Sideeffect.java

package sideeffect;

import changelog.Changelog;

// Referenced classes of package sideeffect:
//            SideeffectCode

public interface Sideeffect
{

    public abstract int getId();

    public abstract void setId(int i);

    public abstract SideeffectCode getSideeffectCode();

    public abstract void setSideeffectCode(SideeffectCode sideeffectcode);

    public abstract int getCurrentLevel();

    public abstract void setCurrentLevel(int i);

    public abstract int[] getLevelHierarchy();

    public abstract void setLevelHierarchy(int ai[]);

    public abstract int getFDACode();

    public abstract void setFDACode(int i);

    public abstract String getFDASourcePTKOR();

    public abstract void setFDASourcePTKOR(String s);

    public abstract String getFDASourcePTENG();

    public abstract void setFDASourcePTENG(String s);

    public abstract String getFDASourceDefinitionKOR();

    public abstract void setFDASourceDefinitionKOR(String s);

    public abstract String getFDASourceDefinitionENG();

    public abstract void setFDASourceDefinitionENG(String s);

    public abstract String getNCItDefinitionKORfinal();

    public abstract void setNCItDefinitionKORfinal(String s);

    public abstract String getNCItDefinition();

    public abstract void setNCItDefinition(String s);

    public abstract Changelog getChangelog();

    public abstract void setChangelog(Changelog changelog);
}
