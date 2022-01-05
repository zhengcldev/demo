// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Skyline.java

package com.algorithm.demo.algorithm.SKYFUP_D;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ca.pfv.spmf.algorithms.skylineFrequentUtilitypatterns.SFUPMinerUgmaxD:
//            Skyline

class SkylineList
{

    SkylineList()
    {
        skylinelist = new ArrayList();
    }

    public Skyline get(int index)
    {
        return (Skyline)skylinelist.get(index);
    }

    public void add(Skyline e)
    {
        skylinelist.add(e);
    }

    public void remove(int index)
    {
        skylinelist.remove(index);
    }

    public int size()
    {
        return skylinelist.size();
    }

    List skylinelist;
}
