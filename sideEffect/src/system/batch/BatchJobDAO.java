// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BatchJobDAO.java

package system.batch;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import java.util.List;

public interface BatchJobDAO
    extends CRUDable
{

    public abstract long count(IDValuePair idvaluepair);

    public abstract List list(Object obj);

    public abstract List list(Object obj, int i, int j);

    public abstract long count(Object obj);

    public abstract Object createAndReturn(Object obj);
}
