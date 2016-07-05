// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccountTester.java

package account;

import java.io.PrintStream;

// Referenced classes of package account:
//            SimpleAccountServiceProvider, SimpleCrypto, AccountServiceProvider

public class AccountTester
{

    public AccountTester()
    {
    }

    public static void main(String args[])
    {
        AccountServiceProvider p = new SimpleAccountServiceProvider();
        SimpleCrypto sc = new SimpleCrypto();
        String myPassword = "it's a secret!";
        String encrypted = "";
        try
        {
            encrypted = SimpleCrypto.encrypt("aaa", myPassword);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String decrypted = "";
        try
        {
            decrypted = SimpleCrypto.decrypt("aaa", encrypted);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println((new StringBuilder("clear : ")).append(myPassword).toString());
        System.out.println((new StringBuilder("encrypted : ")).append(encrypted).toString());
        System.out.println((new StringBuilder("decrypted : ")).append(decrypted).toString());
    }
}
