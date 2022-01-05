// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueueItemset.java

package com.algorithm.demo.algorithm.SKYFUP_B;

import java.util.List;

// Referenced classes of package ca.pfv.spmf.algorithms.skylineFrequentUtilitypatterns.SFUPMinerUgmaxB:
//            UtilityList

public class QueueItemset
{

    public QueueItemset()
    {
        visited = false;
    }

    int i;
    public int prefix[];
    public UtilityList pUL;
    public List ULs;
    boolean visited;
}
