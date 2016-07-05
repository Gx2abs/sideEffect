// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncItemImportHistory.java

package risk.item.importoutput;


// Referenced classes of package risk.item.importoutput:
//            JuncItemImportHistory

public class SimpleJuncItemImportHistory
    implements JuncItemImportHistory
{

    public SimpleJuncItemImportHistory()
    {
    }

    public int getImport_id()
    {
        return import_id;
    }

    public void setImport_id(int import_id)
    {
        this.import_id = import_id;
    }

    public int getHistory_id()
    {
        return history_id;
    }

    public void setHistory_id(int history_id)
    {
        this.history_id = history_id;
    }

    private int import_id;
    private int history_id;
}
