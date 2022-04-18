package com.algorithm.demo.algorithm.SFUI_UF;

import com.algorithm.demo.entity.AlgoResult;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This is an implementation of the "SFUI-UF algorithm" for Mining Skyline
 * Frequent-Utility Itemsets as described in the conference paper :Mining
 * Skyline Frequent-Utility Itemsets with Utility Filtering
 * 
 * @author Wei Song,Chuanlong Zheng,Philippe Fournier-Viger
 *
 */

public class SFUI_UF {

	double maxMemory = 0; // the maximum memory usage
	long startTimestamp = 0; // the time the algorithm started
	long endTimestamp = 0; // the time the algorithm terminated
	int psfupCount = 0; // the number of PSFUP
	int sfupCount = 0; // the number of SFUP generated
	int searchCount = 0; // the number of search patterns
	int MUS = 0;// the minimum utility of SFUIs
	int fMax = 0;// the variable that records the maximum frequency
	// use it to prune the no-possibility item
	Map<Integer, Integer> mapItemToTWU;
	// a map that stores the utility of each item
	Map<Integer, Integer> mapItemToUtility;
	// a map that stores the temporary transaction weighted utility of each item
	Map<Integer, Integer> mapItemToTempTWU;
	// a map that stores the frequence of each item
	Map<Integer, Integer> mapItemToFrequent;
	Map<Integer, Integer> mapItemToTempUtility;
	Map<Integer, Integer> mapItemToFU;

	// this class represent an item and its utility in a transaction
	static class Pair {
		int item = 0;
		int utility = 0;
	}

	public SFUI_UF() {
	}

	public void runSFUI_UF(File input) throws IOException {
		// reset maximum
		maxMemory = 0;
		//parameter initialization
		startTimestamp = System.currentTimeMillis();
		mapItemToTWU = new HashMap<>();
		mapItemToUtility = new HashMap<>();
		mapItemToTempTWU = new HashMap<>();
		mapItemToFrequent = new HashMap<>();
		mapItemToTempUtility = new HashMap<>();
		mapItemToFU = new HashMap<>();
		BufferedReader myInput = null;
		boolean isFirstLine = true;
		int fre = 0;
		int uti = 0;
		String thisLine;
		// first database pass to calculate the mus and the maximal frequency
		try {
			myInput = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
			while ((thisLine = myInput.readLine()) != null) {
				if (thisLine.isEmpty() || thisLine.charAt(0) == '#' || thisLine.charAt(0) == '%'
						|| thisLine.charAt(0) == '@') {
					continue;
				}
				String[] split = thisLine.split(":");
				String[] items = split[0].split(" ");
				String[] utilities = split[2].split(" ");
				// for each item, we add the transaction utility to its TWU
				int transactionUtility = Integer.parseInt(split[1]);
				List<Integer> itemList = new ArrayList<>();
				for (int i = 0; i < items.length; i++) {
					Integer item = Integer.parseInt(items[i]);
					Integer utility = Integer.parseInt(utilities[i]);
					itemList.add(item);
					Integer u = mapItemToUtility.get(item);
					Integer f = mapItemToFrequent.get(item);
					Integer twu = mapItemToTempTWU.get(item);
					// add the utility of the item
					u = (u == null) ? utility : u + utility;
					mapItemToUtility.put(item, u);
					// add the frequency of the item
					f = (f == null) ? 1 : f + 1;
					mapItemToFrequent.put(item, f);
					// add the utility of the item in the current transaction to its twu
					twu = (twu == null) ? transactionUtility : twu + transactionUtility;
					mapItemToTempTWU.put(item, twu);
					if (isFirstLine) {
						uti += utility;
						mapItemToTempUtility.put(item, utility);
					} else {
						if (mapItemToTempUtility.get(item) != null) {
							uti += utility;
							mapItemToTempUtility.put(item, mapItemToTempUtility.get(item) + utility);
						}
					}
				}
				fre++;
				if (isFirstLine) {
					isFirstLine = false;
				} else {
					Iterator<Integer> iter = mapItemToTempUtility.keySet().iterator();
					while (iter.hasNext()) {
						Integer item = iter.next();
						if (!itemList.contains(item)) {
							uti -= mapItemToTempUtility.get(item);
							iter.remove();
						}
					}
				}
				if (mapItemToTempUtility.size() == 0) {
					isFirstLine = true;
					fre = 0;
					uti = 0;
				} else {
					int creMaxUti = mapItemToFU.getOrDefault(fre, 0);
					mapItemToFU.put(fre, Math.max(creMaxUti, uti));
				}
			}
			// find the utility of maximum frequency of 1-item to calculate the MUS
			for (Integer item : mapItemToFrequent.keySet()) {
				int f = mapItemToFrequent.get(item);
				int u = mapItemToUtility.get(item);
				if (f > fMax) {
					fMax = f;
					MUS = u;
				} else if (f == fMax && u > MUS) {
					MUS = u;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (myInput != null) {
				myInput.close();
			}
		}
		// second database pass to calculate the twu of each item
		try {
			myInput = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
			while ((thisLine = myInput.readLine()) != null) {
				if (thisLine.isEmpty() || thisLine.charAt(0) == '#' || thisLine.charAt(0) == '%'
						|| thisLine.charAt(0) == '@') {
					continue;
				}
				String[] split = thisLine.split(":");
				String[] items = split[0].split(" ");
				int transactionUtility = Integer.parseInt(split[1]);
				// for each item, we add the transaction utility to its TWU
				for (String s : items) {
					Integer item = Integer.parseInt(s);
					Integer twu = mapItemToTWU.get(item);
					// add the utility of the item in the current transaction to its twu
					twu = (twu == null) ? transactionUtility : twu + transactionUtility;
					mapItemToTWU.put(item, twu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (myInput != null) {
				myInput.close();
			}
		}
		// create a list to store the utility list of items
		List<UtilityList> UtilityLists = new ArrayList<>();
		// create a map to store the utility list for each item.
		// Key : item Value : utility list associated to that item
		Map<Integer, UtilityList> mapItemToUtilityList = new HashMap<>();
		// For each item
		for (Integer item : mapItemToTWU.keySet()) {
			// create an empty Utility List that we will fill later.
			UtilityList uList = new UtilityList(item);
			mapItemToUtilityList.put(item, uList);
			// add the item to the utility list
			UtilityLists.add(uList);
		}
		// sort the utility list in ascending order
		UtilityLists.sort((o1, o2) -> {
			// compare the TWU of the items
			return compareItems(o1.item, o2.item);
		});
		//transaction id
		int tid = 0;
		// We scan the database a third time to calculate the TWU of each item
		try {
			myInput = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
			while ((thisLine = myInput.readLine()) != null) {
				if (thisLine.isEmpty() || thisLine.charAt(0) == '#' || thisLine.charAt(0) == '%'
						|| thisLine.charAt(0) == '@') {
					continue;
				}
				String[] split = thisLine.split(":");
				String[] items = split[0].split(" ");
				// get the list of utility values corresponding to each item for that transaction
				String[] utilityValues = split[2].split(" ");
				int remainingUtility = 0;
				// Create a list to store items
				List<Pair> revisedTransaction = new ArrayList<>();
				// for each item
				for (int i = 0; i < items.length; i++) {
					Pair pair = new Pair();
					pair.item = Integer.parseInt(items[i]);
					pair.utility = Integer.parseInt(utilityValues[i]);
					// items in the remove list are not considered
					// add it
					revisedTransaction.add(pair);
					remainingUtility += pair.utility;
				}
				revisedTransaction.sort((o1, o2) -> compareItems(o1.item, o2.item));
				// for each item left in the transaction
				for (Pair pair : revisedTransaction) {
					// subtract the utility of this item from the remaining utility
					remainingUtility = remainingUtility - pair.utility;
					// get the utility list of this item
					UtilityList utilityListOfItem = mapItemToUtilityList.get(pair.item);
					// Add a new Element to the utility list of this item corresponding to this transaction
					Element element = new Element(tid, pair.utility, remainingUtility);
					utilityListOfItem.addElement(element);
				}
				tid++; // increase tid number for next transaction
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (myInput != null) {
				myInput.close();
			}
		}
		int[] MUA = new int[fMax + 1];
		// initialize a MUA array to MUS
		for (int i = 1; i < fMax + 1; i++) {
			if (mapItemToFU.get(i) != null && mapItemToFU.get(i) > MUS) {
				for (int j = 1; j <= i; j++) {
					if (MUA[j] <= mapItemToFU.get(i)) {
						MUA[j] = mapItemToFU.get(i);
					}
				}
			} else {
				MUA[i] = MUS;
			}
		}
		UtilityLists.sort((o1, o2) -> {
			// compare the TWU of the items
			return compareItems(o1.item, o2.item);
		});
		UtilityLists = UtilityLists.stream()
				.filter(o -> mapItemToTWU.get(o.item) >= MUA[o.elements.size()])
				.collect(Collectors.toList());
		// check the memory usage
		checkMemory();
		// The list is used to store the current potential skyline frequent-utility patterns (PSFUPs)
		SkylineList[] psfupList = new SkylineList[fMax + 1];
		// The list is used to store the current skyline frequent-utility patterns (SFUPs)
		List<Skyline> skylineList = new ArrayList<>();
		// This method is used to mine all the PSFUPs
		SFUPMiner(new int[0], null, UtilityLists, psfupList, MUA);
		// This method is used to mine all the SFUPs from PSFUPs
		SFUSMiner(skylineList, psfupList);
		// This method is used to write out all the PSFUPs
		writeOut(skylineList);
		psfupCount = getpsfupCount(psfupList);
		checkMemory();
		endTimestamp = System.currentTimeMillis();
	}

	private int compareItems(int item1, int item2) {
		int compare = mapItemToTWU.get(item1) - mapItemToTWU.get(item2);
		// if the same, use the lexical order otherwise use the TWU
		return (compare == 0) ? item1 - item2 : compare;
	}

	/**
	 * This is the recursive method to find all potential skyline frequent-utility
	 * patterns
	 * 
	 * @param prefix      This is the current prefix. Initially, it is empty.
	 * @param pUL         This is the Utility List of the prefix. Initially, it is
	 *                    empty.
	 * @param ULs         The utility lists corresponding to each extension of the
	 *                    prefix.
	 * @param psfupList   Current potential skyline frequent-utility
	 *                    patterns.Initially, it is empty.
	 */
	private void SFUPMiner(int[] prefix, UtilityList pUL, List<UtilityList> ULs, SkylineList[] psfupList,
						   int[] MUA) throws IOException {

		// For each extension X of prefix P
		for (int i = 0; i < ULs.size(); i++) {
			UtilityList X = ULs.get(i);
			// the itemset search space is increased by one per access
			searchCount++;
			// ispass =true indicates that the itemset deserves further consideration
			// temp store the frequency of X
			int temp = X.elements.size();
			// The MUA array is divided into two parts bounded by the frequency of the current item set
			// firstly, consider whether there is a utility on the right hand side of the current frequency
			// that is greater than the utility of the current frequency
			for (int k = temp; k < MUA.length; k++) {
				if (MUA[k] > MUA[temp]) {
					MUA[temp] = MUA[k];
				}
			}
			// then,update all elements to the left of the MUA array
			// with the utility of the updated current frequency
			for (int t = temp - 1; t > 0; t--) {
				if (MUA[t] < MUA[temp]) {
					MUA[t] = MUA[temp];
				} else {
					break;
				}
			}
			// judge whether X is a PSFUP
			// if the utility of X equals to the PSFUP which has same frequency with X, insert X to psfupList
			if (X.sumIutils == MUA[temp] && MUA[temp] != 0) {
				Skyline tempPoint = new Skyline();
				tempPoint.itemSet = itemSetString(prefix, X.item);
				tempPoint.frequent = temp;
				tempPoint.utility = X.sumIutils;
				if (psfupList[temp] == null) {
					SkylineList tempList = new SkylineList();
					tempList.add(tempPoint);
					psfupList[temp] = tempList;
				} else {
					psfupList[temp].add(tempPoint);
				}
			}
			// if the utility of X more than the PSFUP which has same frequency with X, update psfupList
			if (X.sumIutils > MUA[temp]) {
				MUA[temp] = X.sumIutils;
				// if psfupList[temp] is null, insert X to psfupList
				if (psfupList[temp] == null) {
					SkylineList tempList = new SkylineList();
					Skyline tempPoint = new Skyline();
					tempPoint.itemSet = itemSetString(prefix, X.item);
					tempPoint.frequent = temp;
					tempPoint.utility = X.sumIutils;
					tempList.add(tempPoint);
					psfupList[temp] = tempList;
				}
				// if psfupList[temp] is not null, update psfupList[temp]
				else {
					// This is the number of PSFUPs which has same frequency with X.
					int templength = psfupList[temp].size();

					if (templength == 1) {
						psfupList[temp].get(0).itemSet = itemSetString(prefix, X.item);
						psfupList[temp].get(0).utility = X.sumIutils;
					} else {
						for (int j = templength - 1; j > 0; j--) {
							psfupList[temp].remove(j);
						}
						psfupList[temp].get(0).itemSet = itemSetString(prefix, X.item);
						psfupList[temp].get(0).utility = X.sumIutils;
					}
				}
			}
			// If the sum of the remaining utilities for pX is higher than MUA[j], we explore extensions of pX.
			// (this is the pruning condition)
			if (X.sumIutils + X.sumRutils >= MUA[temp] && MUA[temp] != 0) {
				// This list will contain the utility lists of pX extensions.
				List<UtilityList> exULs = new ArrayList<>();
				// For each extension of p appearing after X according to the ascending order
				for (int j = i + 1; j < ULs.size(); j++) {
					UtilityList Y = ULs.get(j);
					// we construct the extension pXY and add it to the list of extensions of pX
					exULs.add(construct(pUL, X, Y));
				}
				// We create new prefix pX
				int[] newPrefix = new int[prefix.length + 1];
				System.arraycopy(prefix, 0, newPrefix, 0, prefix.length);
				newPrefix[prefix.length] = X.item;
				// We make a recursive call to discover all itemsets with the prefix pXY
				SFUPMiner(newPrefix, X, exULs, psfupList, MUA);
			}

		}
	}
	/**
	 * This method constructs the utility list of pXY
	 * 
	 * @param P  : the utility list of prefix P.
	 * @param px : the utility list of pX
	 * @param py : the utility list of pY
	 * @return the utility list of pXY
	 */
	private UtilityList construct(UtilityList P, UtilityList px, UtilityList py) {
		// create an empy utility list for pXY
		UtilityList pxyUL = new UtilityList(py.item);
		// for each element in the utility list of pX
		for (Element ex : px.elements) {
			// do a binary search to find element ey in py with tid = ex.tid
			Element ey = findElementWithTID(py, ex.tid);
			if (ey == null) {
				continue;
			}
			// if the prefix p is null
			if (P == null) {
				// Create the new element
				Element eXY = new Element(ex.tid, ex.iutils + ey.iutils, ey.rutils);
				// add the new element to the utility list of pXY
				pxyUL.addElement(eXY);

			} else {
				// find the element in the utility list of p wih the same tid
				Element e = findElementWithTID(P, ex.tid);
				if (e != null) {
					// Create new element
					Element eXY = new Element(ex.tid, ex.iutils + ey.iutils - e.iutils, ey.rutils);
					// add the new element to the utility list of pXY
					pxyUL.addElement(eXY);
				}
			}
		}
		// return the utility list of pXY.
		return pxyUL;
	}

	/**
	 * Do a binary search to find the element with a given tid in a utility list
	 * 
	 * @param ulist the utility list
	 * @param tid   the tid
	 * @return the element or null if none has the tid.
	 */
	private Element findElementWithTID(UtilityList ulist, int tid) {
		List<Element> list = ulist.elements;

		// perform a binary search to check if the subset appears in level k-1.
		int first = 0;
		int last = list.size() - 1;

		// the binary search
		while (first <= last) {
			int middle = (first + last) >>> 1; // divide by 2

			if (list.get(middle).tid < tid) {
				first = middle + 1; // the itemset compared is larger than the subset according to the lexical order
			} else if (list.get(middle).tid > tid) {
				last = middle - 1; // the itemset compared is smaller than the subset is smaller according to the
									// lexical order
			} else {
				return list.get(middle);
			}
		}
		return null;
	}

	/**
	 * Method to write out itemset name
	 * 
	 * @param prefix This is the current prefix
	 * @param item   This is the new item added after the prefix
	 * @return the itemset name
	 */
	private String itemSetString(int[] prefix, int item) throws IOException {

		// Create a string buffer
		StringBuilder buffer = new StringBuilder();
		// append the prefix
		for (int value : prefix) {
			buffer.append(value);
			buffer.append(' ');
		}
		// append the last item
		buffer.append(item);

		return buffer.toString();

	}

	/**
	 * Method to write skyline frequent-utility itemset to the output file.
	 * 
	 * @param skylineList The list of skyline frequent-utility itemsets
	 */
	private void writeOut(List<Skyline> skylineList) {
		sfupCount = skylineList.size();

		// Create a string buffer
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < sfupCount; i++) {
			buffer.append(skylineList.get(i).itemSet);
			buffer.append(" #SUP:");
			buffer.append(skylineList.get(i).frequent);
			buffer.append(" #UTILITY:");
			buffer.append(skylineList.get(i).utility);
			buffer.append(System.lineSeparator());
			// write to file
			// System.out.println(skylineList.get(i).itemSet+"
			// #SUP:"+skylineList.get(i).frequent+" #UTILITY:"+skylineList.get(i).utility);
		}
	}

	/**
	 * Method to judge whether the PSFUP is a SFUP
	 * 
	 * @param skylineList The skyline frequent-utility itemset list
	 * @param psfupList   The potential skyline frequent-utility itemset list
	 */

	private void SFUSMiner(List<Skyline> skylineList, SkylineList[] psfupList) {
		for (int i = 1; i < psfupList.length; i++) {
			// if temp equals to 0, the value of psfupList[i] is higher than all the value of psfupList[j](j>i)
			int temp = 0;
			// compare psfupList[i] with psfupList[j],(j>i)
			if (psfupList[i] != null) {
				int j = i + 1;
				while (j < psfupList.length) {
					if (psfupList[j] == null) {
						j++;
					} else {
						if (psfupList[i].get(0).utility <= psfupList[j].get(0).utility) {
							temp = 1;
							break;
						} else {
							j++;
						}
					}
				}
				// it temp equals to 0, this PSFUP is a SFUP
				if (temp == 0) {
					for (int k = 0; k < psfupList[i].size(); k++)
						skylineList.add(psfupList[i].get(k));
				}
			}
		}
	}

	/**
	 * Method to get the count of PSFUP.
	 * 
	 * @param psfupList the potential skyline frequent-utility itemset list
	 * @return the count of PSFUPs
	 */
	private int getpsfupCount(SkylineList[] psfupList) {
		for (int i = 1; i < psfupList.length; i++) {
			if (psfupList[i] != null) {
				psfupCount = psfupCount + psfupList[i].size();
			}
		}
		return psfupCount;
	}

	/**
	 * Method to check the memory usage and keep the maximum memory usage.
	 */
	private void checkMemory() {
		// get the current memory usage
		double currentMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024d / 1024d;
		// if higher than the maximum until now
		if (currentMemory > maxMemory) {
			// replace the maximum with the current memory usage
			maxMemory = currentMemory;
		}
	}

	/**
	 * Print statistics about the latest execution to System.out.
	 */
	public AlgoResult getResult() {
		AlgoResult algoResult=new AlgoResult();
		algoResult.setAlgoName("SFUI_UF");
		algoResult.setRunTime(String.format("%.2f", (endTimestamp - startTimestamp)*0.001));
		algoResult.setRunMemory(String.format("%.2f",maxMemory));
		algoResult.setPSFUI(String.valueOf(psfupCount));
		algoResult.setSFUI(String.valueOf(sfupCount));
		algoResult.setSearchSpace(String.valueOf(searchCount));
		return algoResult;
	}
}