// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncItemCategoryGroupHistory.java

package risk.category;


// Referenced classes of package risk.category:
//            JuncItemCategoryGroupHistory

public class SimpleJuncItemCategoryGroupHistory
    implements JuncItemCategoryGroupHistory
{

    public SimpleJuncItemCategoryGroupHistory()
    {
    }

    public int getGroup_id()
    {
        return group_id;
    }

    public void setGroup_id(int group_id)
    {
        this.group_id = group_id;
    }

    public int getHistory_id()
    {
        return history_id;
    }

    public void setHistory_id(int history_id)
    {
        this.history_id = history_id;
    }

    private int group_id;
    private int history_id;
}
