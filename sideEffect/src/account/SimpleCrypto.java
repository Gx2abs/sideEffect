// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCrypto.java

package account;

import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class SimpleCrypto
{

    public SimpleCrypto()
    {
    }

    public static String encrypt(String seed, String cleartext)
        throws Exception
    {
        byte rawKey[] = getRawKey(seed.getBytes());
        byte result[] = encrypt(rawKey, cleartext.getBytes());
        return toHex(result);
    }

    public static String decrypt(String seed, String encrypted)
        throws Exception
    {
        byte rawKey[] = getRawKey(seed.getBytes());
        byte enc[] = toByte(encrypted);
        byte result[] = decrypt(rawKey, enc);
        return new String(result);
    }

    private static byte[] getRawKey(byte seed[])
        throws Exception
    {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr);
        SecretKey skey = kgen.generateKey();
        byte raw[] = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte raw[], byte clear[])
        throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, skeySpec);
        byte encrypted[] = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte raw[], byte encrypted[])
        throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, skeySpec);
        byte decrypted[] = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt)
    {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex)
    {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString)
    {
        int len = hexString.length() / 2;
        byte result[] = new byte[len];
        for(int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();

        return result;
    }

    public static String toHex(byte buf[])
    {
        if(buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for(int i = 0; i < buf.length; i++)
            appendHex(result, buf[i]);

        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b)
    {
        sb.append("0123456789ABCDEF".charAt(b >> 4 & 0xf)).append("0123456789ABCDEF".charAt(b & 0xf));
    }

    private static final String HEX = "0123456789ABCDEF";
}
