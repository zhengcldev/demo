package com.algorithm.demo.algorithm.SFUI_UF;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a skyline point, which contains itemSet, frequency and
 * utility
 * 
 * @author Wei Song,Chuanlong Zheng,Philippe Fournier-Viger
 *
 */
public class Skyline {
	String itemSet; // the itemset
	int frequent; // the frequency of itemset
	int utility; // the utility of itemset

}

class SkylineList {
	// skylinelist store different itemsets that have same frequency and same
	// utility.
	List<Skyline> skylinelist = new ArrayList<>();

	public Skyline get(int index) {
		return skylinelist.get(index);
	}

	public void add(Skyline e) {
		skylinelist.add(e);
	}

	public void remove(int index) {
		skylinelist.remove(index);
	}

	public int size() {
		return skylinelist.size();
	}
}
