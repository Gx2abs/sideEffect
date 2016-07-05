// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RiskCompositeKey.java

package risk.category.risk;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class RiskCompositeKey
    implements Serializable
{

    public RiskCompositeKey()
    {
    }

    public long getRisk_id()
    {
        return risk_id;
    }

    public void setRisk_id(long risk_id)
    {
        this.risk_id = risk_id;
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
        if(!(obj instanceof RiskCompositeKey))
        {
            return false;
        } else
        {
            RiskCompositeKey castOther = (RiskCompositeKey)obj;
            return (new EqualsBuilder()).append(risk_id, castOther.risk_id).append(group_id, castOther.group_id).isEquals();
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder()).append(risk_id).append(group_id).toHashCode();
    }

    static final long serialVersionUID = 0x9bcd9a3c0af9b27L;
    private long risk_id;
    private long group_id;
}
