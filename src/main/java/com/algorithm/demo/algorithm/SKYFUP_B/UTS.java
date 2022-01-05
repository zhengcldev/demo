// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgoSFUPMinerUgmaxB.java

package com.algorithm.demo.algorithm.SKYFUP_B;

import java.io.*;
import java.util.*;

// Referenced classes of package ca.pfv.spmf.algorithms.skylineFrequentUtilitypatterns.SFUPMinerUgmaxB:
//            UtilityList, Element, QueueItemset, Skyline

public class UTS
{
	Map<Integer, Integer> mapItemToTempU;
	Map<Integer, Integer> mapItemToTempTWU;
	Map<Integer, Integer> mapItemToTempF;
	int MUS=0;
	int temp_f=0;
    class Pair
    {

        int item;
        int utility;
        final UTS this$0;

        Pair()
        {
            this$0 = UTS.this;
            //super();
            item = 0;
            utility = 0;
        }
    }


    public UTS()
    {
        maxMemory = 0.0D;
        startTimestamp = 0L;
        endTimestamp = 0L;
        sfupCount = 0;
        searchCount = 0;
        queue = new ArrayDeque();
        writer = null;
    }

    public void runAlgorithm(String input, String output)
        throws IOException
    {
        BufferedReader myInput;
        maxMemory = 0.0D;
        startTimestamp = System.currentTimeMillis();
        writer = new BufferedWriter(new FileWriter(output));
        mapItemToTWU = new HashMap();
        mapItemToTempU = new HashMap<Integer, Integer>();
		mapItemToTempTWU = new HashMap<Integer, Integer>();
		mapItemToTempF = new HashMap<Integer, Integer>();
		List<Integer> remove_list=new ArrayList<Integer>();
        myInput = null;
        
        //BufferedReader myInput = null;
		
		//int max_len=0;
		try {
			String thisLine;
			// prepare the object for reading the file
			myInput = new BufferedReader(new InputStreamReader( new FileInputStream(new File(input))));
			// for each line (transaction) until the end of file
			while ((thisLine = myInput.readLine()) != null) {
				// if the line is  a comment, is  empty or is a
				// kind of metadata
				if (thisLine.isEmpty() == true ||
						thisLine.charAt(0) == '#' || thisLine.charAt(0) == '%'
								|| thisLine.charAt(0) == '@') {
					continue;
				}
				
				// split the transaction according to the : separator
				String split[] = thisLine.split(":"); 
				// the first part is the list of items
				String items[] = split[0].split(" "); 
				String utilities[] = split[2].split(" ");
				// for each item, we add the transaction utility to its TWU
				int transactionUtility = Integer.parseInt(split[1]);
				for(int i=0; i <items.length; i++){
					// convert item to integer		
					//if(items.length>max_len) {
					//	max_len=items.length;
					//}
					Integer item = Integer.parseInt(items[i]);
					Integer utility = Integer.parseInt(utilities[i]);
					 
					// get the current TWU of that item                                           
					Integer u = mapItemToTempU.get(item);
					Integer f = mapItemToTempF.get(item);
					Integer twu = mapItemToTempTWU.get(item);
					// add the utility of the item in the current transaction to its twu
					u = (u == null)? 
							utility : u + utility;				
					mapItemToTempU.put(item, u);
					
					f = (f == null)? 
							1 : f + 1;				
					mapItemToTempF.put(item, f);
					
					
					twu = (twu == null)? 
							transactionUtility : twu + transactionUtility;				
					mapItemToTempTWU.put(item, twu);
				}
			}
			//find the utility of maximum frequence of 1-item
			for(Integer item : mapItemToTempF.keySet()){
				   int f =(int) mapItemToTempF.get(item);
				   int u=(int)mapItemToTempU.get(item);
				   if(f>temp_f) {
						temp_f=f;
						MUS=u;
					}
				   else if(f==temp_f&&u>MUS) {
					   MUS=u;
				   }
			}
		
			//System.out.println(MUS);
			//System.out.println(Arrays.toString(umax));
			
			for(Integer item : mapItemToTempTWU.keySet()){
				   int twu =(int) mapItemToTempTWU.get(item);
				   if(twu<MUS) {
					   remove_list.add(item);
					}
			}
			System.out.println(remove_list.toString());
									
		} catch (Exception e) {
			// catches exception if error while reading the input file
			e.printStackTrace();
		}finally {   
			if(myInput != null){
				myInput.close();
			}
	    }
        
        
        try
        {
            myInput = new BufferedReader(new InputStreamReader(new FileInputStream(new File(input))));
            String thisLine;
            while((thisLine = myInput.readLine()) != null) 
                if(!thisLine.isEmpty() && thisLine.charAt(0) != '#' && thisLine.charAt(0) != '%' && thisLine.charAt(0) != '@')
                {
                    String split[] = thisLine.split(":");
                    String items[] = split[0].split(" ");
                    int transactionUtility = Integer.parseInt(split[1]);
                    for(int i = 0; i < items.length; i++)
                    {
                        Integer item = Integer.valueOf(Integer.parseInt(items[i]));
                        Integer twu = (Integer)mapItemToTWU.get(item);
                        if(!remove_list.contains(item)) {
        					// add the utility of the item in the current transaction to its twu
        						twu = (twu == null)? transactionUtility : twu + transactionUtility;
        						mapItemToTWU.put(item, twu);
        					}
        					else
        					{
        						continue;
        					}
                    }

                }
          //  break MISSING_BLOCK_LABEL_277;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(myInput != null)
            myInput.close();
      //  break MISSING_BLOCK_LABEL_285;
        Exception exception;
        //exception;
        if(myInput != null)
            myInput.close();
       // throw exception;
        if(myInput != null)
            myInput.close();
        List listOfUtilityLists;
        Map mapItemToUtilityList;
        int tid;
        listOfUtilityLists = new ArrayList();
        mapItemToUtilityList = new HashMap();
        UtilityList uList;
        for(Iterator iterator = mapItemToTWU.keySet().iterator(); iterator.hasNext(); listOfUtilityLists.add(uList))
        {
            Integer item = (Integer)iterator.next();
            uList = new UtilityList(item.intValue());
            mapItemToUtilityList.put(item, uList);
        }

        Collections.sort(listOfUtilityLists, new Comparator() {

            public int compare(UtilityList o1, UtilityList o2)
            {
                return compareItems(o1.item, o2.item);
            }

            public int compare(Object obj, Object obj1)
            {
                return compare((UtilityList)obj, (UtilityList)obj1);
            }

            final UTS this$0;

            
            {
                this$0 = UTS.this;
               // super();
            }
        }
);
        tid = 0;
        try
        {
            myInput = new BufferedReader(new InputStreamReader(new FileInputStream(new File(input))));
            String thisLine;
            while((thisLine = myInput.readLine()) != null) 
                if(!thisLine.isEmpty() && thisLine.charAt(0) != '#' && thisLine.charAt(0) != '%' && thisLine.charAt(0) != '@')
                {
                    String split[] = thisLine.split(":");
                    String items[] = split[0].split(" ");
                    String utilityValues[] = split[2].split(" ");
                    int remainingUtility = 0;
                    List revisedTransaction = new ArrayList();
                    for(int i = 0; i < items.length; i++)
                    {
                        Pair pair = new Pair();
                        pair.item = Integer.parseInt(items[i]);
                        pair.utility = Integer.parseInt(utilityValues[i]);
                 
                        if(!remove_list.contains(pair.item)) {
        					// add it
        					revisedTransaction.add(pair);
        					remainingUtility += pair.utility;
        					//item_count=item_count+1;
        				
        				}
        					else
        					{
        						continue;
        					}
                    }

                    Collections.sort(revisedTransaction, new Comparator() {

                        public int compare(Pair o1, Pair o2)
                        {
                            return compareItems(o1.item, o2.item);
                        }

                        public int compare(Object obj, Object obj1)
                        {
                            return compare((Pair)obj, (Pair)obj1);
                        }

                        final UTS this$0;

            
            {
                this$0 = UTS.this;
              //  super();
            }
                    }
);
                    UtilityList utilityListOfItem;
                    Element element;
                    for(Iterator iterator1 = revisedTransaction.iterator(); iterator1.hasNext(); utilityListOfItem.addElement(element))
                    {
                        Pair pair = (Pair)iterator1.next();
                        remainingUtility -= pair.utility;
                        utilityListOfItem = (UtilityList)mapItemToUtilityList.get(Integer.valueOf(pair.item));
                        element = new Element(tid, pair.utility, remainingUtility);
                    }

                    tid++;
                }
          //  break MISSING_BLOCK_LABEL_738;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(myInput != null)
            myInput.close();
      //  break MISSING_BLOCK_LABEL_746;
        Exception exception1;
       // exception1;
        if(myInput != null)
            myInput.close();
       // throw exception1;
        if(myInput != null)
            myInput.close();
        checkMemory();
        int uGmax[] = new int[tid + 1];
        List skylineList = new ArrayList();
        SFUPMiner(new int[0], null, listOfUtilityLists, skylineList, uGmax);
        writeOut(skylineList);
        checkMemory();
        writer.close();
        endTimestamp = System.currentTimeMillis();
        return;
    }

    private int compareItems(int item1, int item2)
    {
        int compare = ((Integer)mapItemToTWU.get(Integer.valueOf(item1))).intValue() - ((Integer)mapItemToTWU.get(Integer.valueOf(item2))).intValue();
        return compare != 0 ? compare : item1 - item2;
    }

    private void SFUPMiner(int prefix[], UtilityList pUL, List ULs, List skylineList, int uGmax[])
        throws IOException
    {
        scan(prefix, pUL, ULs, skylineList, uGmax);
        extend(skylineList, uGmax);
        for(; !queue.isEmpty(); extend(skylineList, uGmax))
            while(!queue.isEmpty() && !((QueueItemset)queue.peek()).visited) 
            {
                QueueItemset tempItemset = (QueueItemset)queue.poll();
                scan(tempItemset.prefix, tempItemset.pUL, tempItemset.ULs, skylineList, uGmax);
            }

    }

    private void scan(int prefix[], UtilityList pUL, List ULs, List skylineList, int uGmax[])
        throws IOException
    {
        for(int i = 0; i < ULs.size(); i++)
        {
            searchCount++;
            UtilityList X = (UtilityList)ULs.get(i);
            int temp = X.elements.size();
            if(X.sumIutils >= uGmax[temp] && X.sumIutils != 0)
            {
                Skyline tempPoint = new Skyline();
                tempPoint.itemSet = itemSetString(prefix, X.item);
                tempPoint.frequent = temp;
                tempPoint.utility = X.sumIutils;
                judgeSkyline(tempPoint, skylineList, uGmax);
            }
            QueueItemset tempQueItemset = new QueueItemset();
            tempQueItemset.i = i;
            tempQueItemset.prefix = prefix;
            tempQueItemset.pUL = pUL;
            tempQueItemset.ULs = ULs;
            tempQueItemset.visited = true;
            queue.offer(tempQueItemset);
        }

    }

    private void extend(List skylineList, int uGmax[])
        throws IOException
    {
        while(!queue.isEmpty() && ((QueueItemset)queue.peek()).visited) 
        {
            QueueItemset tempItemset = (QueueItemset)queue.poll();
            int prefix[] = tempItemset.prefix;
            UtilityList pUL = tempItemset.pUL;
            List ULs = tempItemset.ULs;
            int i = tempItemset.i;
            UtilityList X = (UtilityList)tempItemset.ULs.get(i);
            int temp = X.elements.size();
            if(X.sumIutils + X.sumRutils >= uGmax[temp] && X.sumIutils + X.sumRutils != 0)
            {
                List exULs = new ArrayList();
                for(int j = i + 1; j < ULs.size(); j++)
                {
                    UtilityList Y = (UtilityList)ULs.get(j);
                    exULs.add(construct(pUL, X, Y));
                }

                int newPrefix[] = new int[prefix.length + 1];
                System.arraycopy(prefix, 0, newPrefix, 0, prefix.length);
                newPrefix[prefix.length] = X.item;
                tempItemset.i = 0;
                tempItemset.prefix = newPrefix;
                tempItemset.pUL = X;
                tempItemset.ULs = exULs;
                tempItemset.visited = false;
                queue.offer(tempItemset);
            }
        }
    }

    private UtilityList construct(UtilityList P, UtilityList px, UtilityList py)
    {
        UtilityList pxyUL = new UtilityList(py.item);
        for(Iterator iterator = px.elements.iterator(); iterator.hasNext();)
        {
            Element ex = (Element)iterator.next();
            Element ey = findElementWithTID(py, ex.tid);
            if(ey != null)
                if(P == null)
                {
                    Element eXY = new Element(ex.tid, ex.iutils + ey.iutils, ey.rutils);
                    pxyUL.addElement(eXY);
                } else
                {
                    Element e = findElementWithTID(P, ex.tid);
                    if(e != null)
                    {
                        Element eXY = new Element(ex.tid, (ex.iutils + ey.iutils) - e.iutils, ey.rutils);
                        pxyUL.addElement(eXY);
                    }
                }
        }

        return pxyUL;
    }

    private Element findElementWithTID(UtilityList ulist, int tid)
    {
        List list = ulist.elements;
        int first = 0;
        for(int last = list.size() - 1; first <= last;)
        {
            int middle = first + last >>> 1;
            if(((Element)list.get(middle)).tid < tid)
                first = middle + 1;
            else
            if(((Element)list.get(middle)).tid > tid)
                last = middle - 1;
            else
                return (Element)list.get(middle);
        }

        return null;
    }

    private String itemSetString(int prefix[], int item)
        throws IOException
    {
        StringBuilder buffer = new StringBuilder();
        for(int i = 0; i < prefix.length; i++)
        {
            buffer.append(prefix[i]);
            buffer.append(' ');
        }

        buffer.append(item);
        return buffer.toString();
    }

    private void writeOut(List skylineList)
        throws IOException
    {
        sfupCount = skylineList.size();
        StringBuilder buffer = new StringBuilder();
        buffer.append("Total skyline frequent-utility itemset: ");
        buffer.append(sfupCount);
        buffer.append('\n');
        for(int i = 0; i < sfupCount; i++)
        {
            buffer.append(((Skyline)skylineList.get(i)).itemSet);
            buffer.append(" #SUP:");
            buffer.append(((Skyline)skylineList.get(i)).frequent);
            buffer.append(" #UTILITY:");
            buffer.append(((Skyline)skylineList.get(i)).utility);
            buffer.append('\n');
        }

        writer.write(buffer.toString());
    }

    private void judgeSkyline(Skyline element, List elements, int uGmax[])
    {
        if(elements.size() == 0)
        {
            elements.add(element);
            for(int j = 0; j <= element.frequent; j++)
                uGmax[j] = element.utility;

        } else
        {
            int i = 0;
            Skyline tempPoint;
            for(tempPoint = (Skyline)elements.get(0); element.frequent > tempPoint.frequent; tempPoint = (Skyline)elements.get(i))
            {
                i++;
                if(elements.size() <= i)
                    break;
            }

            if(i == 0)
            {
                if(element.frequent == tempPoint.frequent)
                {
                    if(element.utility == tempPoint.utility)
                        elements.add(0, element);
                    else
                    if(element.utility > tempPoint.utility)
                    {
                        elements.remove(0);
                        if(elements.size() > 0)
                        {
                            for(Skyline tempPoint1 = (Skyline)elements.get(0); element.frequent == tempPoint1.frequent; tempPoint1 = (Skyline)elements.get(0))
                            {
                                elements.remove(0);
                                if(elements.size() <= 0)
                                    break;
                            }

                        }
                        elements.add(0, element);
                        for(int j = element.frequent; j >= 0; j--)
                            uGmax[j] = element.utility;

                    }
                } else
                if(element.utility > tempPoint.utility)
                {
                    elements.add(0, element);
                    for(int j = 0; j <= element.frequent; j++)
                        uGmax[j] = element.utility;

                }
            } else
            if(i == elements.size())
            {
                if(element.utility >= tempPoint.utility)
                {
                    for(int j = i - 1; j >= 0; j--)
                    {
                        if(element.utility < ((Skyline)elements.get(j)).utility)
                            break;
                        elements.remove(j);
                    }

                }
                elements.add(element);
                for(int j = element.frequent; j >= 0; j--)
                {
                    if(uGmax[j] >= element.utility)
                        break;
                    uGmax[j] = element.utility;
                }

            } else
            {
                Skyline tempPoint1 = (Skyline)elements.get(i - 1);
                if(element.frequent == tempPoint.frequent)
                {
                    if(element.utility > tempPoint.utility)
                    {
                        elements.remove(i);
                        if(elements.size() > i)
                        {
                            for(Skyline tempPoint2 = (Skyline)elements.get(i); element.frequent == tempPoint2.frequent; tempPoint2 = (Skyline)elements.get(i))
                            {
                                elements.remove(i);
                                if(elements.size() <= i)
                                    break;
                            }

                        }
                        elements.add(i, element);
                        for(int j = i - 1; j >= 0; j--)
                        {
                            if(element.utility < ((Skyline)elements.get(j)).utility)
                                break;
                            elements.remove(j);
                        }

                        for(int j = element.frequent; j >= 0; j--)
                        {
                            if(uGmax[j] >= element.utility)
                                break;
                            uGmax[j] = element.utility;
                        }

                    } else
                    if(element.utility == tempPoint.utility)
                        elements.add(i, element);
                } else
                if(element.utility >= tempPoint1.utility)
                {
                    elements.add(i, element);
                    for(int j = i - 1; j >= 0; j--)
                    {
                        if(element.utility < ((Skyline)elements.get(j)).utility)
                            break;
                        elements.remove(j);
                    }

                    for(int j = element.frequent; j >= 0; j--)
                    {
                        if(uGmax[j] >= element.utility)
                            break;
                        uGmax[j] = element.utility;
                    }

                } else
                if(element.utility > tempPoint.utility)
                {
                    elements.add(i, element);
                    for(int j = element.frequent; j >= 0; j--)
                    {
                        if(uGmax[j] >= element.utility)
                            break;
                        uGmax[j] = element.utility;
                    }

                }
            }
        }
    }

    private void checkMemory()
    {
        double currentMemory = (double)(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024D / 1024D;
        if(currentMemory > maxMemory)
            maxMemory = currentMemory;
    }

    public void printStats()
    {
        System.out.println("=============  uGmaxB skyline ALGORITHM - STATS =============");
        System.out.println((new StringBuilder(" Total time ~ ")).append(endTimestamp - startTimestamp).append(" ms").toString());
        System.out.println((new StringBuilder(" Memory ~ ")).append(maxMemory).append(" MB").toString());
        System.out.println((new StringBuilder(" Skyline itemsets count : ")).append(sfupCount).toString());
        System.out.println((new StringBuilder(" Search itemsets count : ")).append(searchCount).toString());
        System.out.println("===================================================");
    }

    double maxMemory;
    long startTimestamp;
    long endTimestamp;
    int sfupCount;
    int searchCount;
    Map mapItemToTWU;
    Queue queue;
    BufferedWriter writer;

}
