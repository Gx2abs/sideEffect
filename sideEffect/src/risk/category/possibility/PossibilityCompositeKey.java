// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PossibilityCompositeKey.java

package risk.category.possibility;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class PossibilityCompositeKey
    implements Serializable
{

    public PossibilityCompositeKey()
    {
    }

    public long getPossibility_id()
    {
        return possibility_id;
    }

    public void setPossibility_id(long possibility_id)
    {
        this.possibility_id = possibility_id;
    }

    public long getGroup_id()
    {
        return group_id;
    }

    public void setGroup_id(long group_id)
    {
        this.group_id = group_id;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof PossibilityCompositeKey))
        {
            return false;
        } else
        {
            PossibilityCompositeKey castOther = (PossibilityCompositeKey)obj;
            return (new EqualsBuilder()).append(possibility_id, castOther.possibility_id).append(group_id, castOther.group_id).isEquals();
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder()).append(possibility_id).append(group_id).toHashCode();
    }

    static final long serialVersionUID = 0x9bcd9a3c0af9b27L;
    private long possibility_id;
    private long group_id;
}
