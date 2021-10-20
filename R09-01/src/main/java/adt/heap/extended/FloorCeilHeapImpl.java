package adt.heap.extended;

import java.util.Comparator;

import adt.heap.ComparatorMinHeap;
import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		if(array != null) {
			Integer floor = null;
			for (Integer elemento : array) {
				insert(elemento);
			}
			for (int i = 0; i < array.length; i++) {
				Integer elemento = extractRootElement();
				if (elemento <= numero && floor == null) {
					floor = elemento;
				}
				if (elemento <= numero && elemento > floor) {
					floor = elemento;
				}
			}
			return floor;
		}
		return null;
	}



	@Override
	public Integer ceil(Integer[] array, double numero) {
		if (array != null) {
			Integer ceil = null;
			for (Integer elemento : array) {
				insert(elemento);
			}
			for (int i = 0; i < array.length; i++) {
				Integer elemento = extractRootElement();
				if (elemento >= numero && ceil == null) {
					ceil = elemento;
				}
				if (elemento > numero && elemento < ceil) {
					ceil = elemento;
				}
			}
			return ceil;
		}
		return null;
	}

}
