// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAccountServiceProvider.java

package account;

import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package account:
//            AccountServiceProvider, AccountDAO

public class SimpleAccountServiceProvider
    implements AccountServiceProvider
{

    public SimpleAccountServiceProvider()
    {
        keyString = "!@#$";
    }

    public AccountDAO getAccountDAO()
    {
        return accountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO)
    {
        this.accountDAO = accountDAO;
    }

    public boolean authenticate(String id, String password)
    {
        boolean isAuthentic = false;
        try
        {
            isAuthentic = accountDAO.authenticate(id, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return isAuthentic;
    }

    private SecretKeySpec getKey()
        throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        byte salt[] = "choose a better salt".getBytes();
        int iterations = 10000;
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey tmp = factory.generateSecret(new PBEKeySpec(keyString.toCharArray(), salt, iterations, 128));
        SecretKeySpec key = new SecretKeySpec(tmp.getEncoded(), "AES");
        return key;
    }

    public String encrypt(String clearText)
    {
        String encrypted = "";
        try
        {
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(1, getKey());
            byte encryptedBytes[] = aes.doFinal(clearText.getBytes());
            System.out.println((new StringBuilder("encryptedBytes:")).append(encryptedBytes).toString());
            encrypted = new String(encryptedBytes);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return encrypted;
    }

    public String decrypt(String encrypted)
    {
        String decrypted = "";
        try
        {
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(2, getKey());
            byte encryptedBytes[] = encrypted.getBytes();
            byte decryptedBytes[] = aes.doFinal(encryptedBytes);
            decrypted = new String(decryptedBytes);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return decrypted;
    }

    private String keyString;
    private AccountDAO accountDAO;
}
