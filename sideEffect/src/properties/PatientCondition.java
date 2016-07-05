// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatientCondition.java

package properties;

import abstraction.IDValuePair;

public interface PatientCondition
    extends IDValuePair
{

    public abstract Integer getParentId();

    public abstract void setParentId(Integer integer);

    public abstract Integer getDepthLevel();

    public abstract void setDepthLevel(Integer integer);

    public abstract String getName();

    public abstract void setName(String s);

    public abstract String getNcitDefinitionKor();

    public abstract void setNcitDefinitionKor(String s);

    public abstract String getFdaCode();

    public abstract void setFdaCode(String s);

    public abstract String getFdaSourcePtKor();

    public abstract void setFdaSourcePtKor(String s);

    public abstract String getFdaSourceDefinition();

    public abstract void setFdaSourceDefinition(String s);

    public abstract String getFdaSourceDefinitionKor();

    public abstract void setFdaSourceDefinitionKor(String s);

    public abstract String getNcitDefinition();

    public abstract void setNcitDefinition(String s);

    public abstract String getNciCode();

    public abstract void setNciCode(String s);
}
