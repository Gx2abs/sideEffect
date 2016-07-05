// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImportOutputDAO.java

package risk.item.importoutput;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import java.util.List;

public interface ImportOutputDAO
    extends CRUDable
{

    public abstract long count(IDValuePair idvaluepair);

    public abstract int clearJunction(String s, String s1, long l);

    public abstract List listEntpImportOutput(IDValuePair idvaluepair, int i, int j, String s, String s1);

    public abstract long listEntpImportOutputCount(IDValuePair idvaluepair, String s, String s1);
}
