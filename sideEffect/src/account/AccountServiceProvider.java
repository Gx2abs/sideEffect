// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccountServiceProvider.java

package account;


// Referenced classes of package account:
//            AccountDAO

public interface AccountServiceProvider
{

    public abstract AccountDAO getAccountDAO();

    public abstract void setAccountDAO(AccountDAO accountdao);

    public abstract boolean authenticate(String s, String s1);

    public abstract String encrypt(String s);

    public abstract String decrypt(String s);
}
