// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncItemOutputHistory.java

package risk.item.importoutput;


// Referenced classes of package risk.item.importoutput:
//            JuncItemOutputHistory

public class SimpleJuncItemOutputHistory
    implements JuncItemOutputHistory
{

    public SimpleJuncItemOutputHistory()
    {
    }

    public int getOutput_id()
    {
        return output_id;
    }

    public void setOutput_id(int output_id)
    {
        this.output_id = output_id;
    }

    public int getHistory_id()
    {
        return history_id;
    }

    public void setHistory_id(int history_id)
    {
        this.history_id = history_id;
    }

    private int output_id;
    private int history_id;
}
