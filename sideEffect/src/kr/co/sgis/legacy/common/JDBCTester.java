// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JDBCTester.java

package kr.co.sgis.legacy.common;

import foreign.Mea_class_no;
import properties.MeaClassNoHandler;
import properties.SimpleIsInUse;
import properties.item.SimpleItemCodeType;
import properties.item.SimpleTraceability;

public class JDBCTester
{

    public JDBCTester()
    {
    }

    public static void main(String args[])
    {
        Mea_class_no found = null;
        found = new Mea_class_no();
        SimpleTraceability sit = new SimpleTraceability();
        sit.setId(1L);
        found.setTraceability(sit);
        SimpleIsInUse siiu = new SimpleIsInUse();
        siiu.setId(1L);
        found.setIsInUse(siiu);
        found.setClass_kor_name("\u314E\u314E\u314E\u314E111");
        found.setClass_cont("cont!");
        found.setClass_eng_name("Eng name111");
        found.setMea_class_no("test2");
        found.setGrade("1");
        SimpleItemCodeType sct = new SimpleItemCodeType();
        sct.setId(1L);
        found.setCode_age(sct);
        MeaClassNoHandler.updateMeaClassNo2(found);
    }
}
