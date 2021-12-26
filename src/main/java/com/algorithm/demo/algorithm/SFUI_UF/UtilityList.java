package com.algorithm.demo.algorithm.SFUI_UF;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a UtilityList as used by the HUI-Miner algorithm.
 * 
 * @author Wei Song,Chuanlong Zheng,Philippe Fournier-Viger
 *
 */
class UtilityList {
	int item; // the item
	int sumIutils = 0; // the sum of item utilities
	int sumRutils = 0; // the sum of remaining utilities
	List<Element> elements = new ArrayList<>(); // the elements

	/**
	 * Constructor.
	 * 
	 * @param item the item that is used for this utility list
	 */
	public UtilityList(int item) {
		this.item = item;
	}

	/**
	 * Method to add an element to this utility list and update the sums at the same
	 * time.
	 */
	public void addElement(Element element) {
		sumIutils += element.iutils;
		sumRutils += element.rutils;
		elements.add(element);
	}
}
