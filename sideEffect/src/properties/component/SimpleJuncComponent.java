// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncComponent.java

package properties.component;


// Referenced classes of package properties.component:
//            JuncComponent

public class SimpleJuncComponent
    implements JuncComponent
{

    public SimpleJuncComponent()
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

    public int getComponent_id()
    {
        return component_id;
    }

    public void setComponent_id(int component_id)
    {
        this.component_id = component_id;
    }

    public int getHistory_id()
    {
        return history_id;
    }

    public void setHistory_id(int history_id)
    {
        this.history_id = history_id;
    }

    private int id;
    private int component_id;
    private int history_id;
}
