// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UtilityList.java

package com.algorithm.demo.algorithm.SKYFUP_B;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ca.pfv.spmf.algorithms.skylineFrequentUtilitypatterns.SFUPMinerUgmaxB:
//            Element

class UtilityList
{

    public UtilityList(int item)
    {
        sumIutils = 0;
        sumRutils = 0;
        elements = new ArrayList();
        this.item = item;
    }

    public void addElement(Element element)
    {
        sumIutils += element.iutils;
        sumRutils += element.rutils;
        elements.add(element);
    }

    int item;
    int sumIutils;
    int sumRutils;
    List elements;
}
