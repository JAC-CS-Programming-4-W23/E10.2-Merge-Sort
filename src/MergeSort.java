/*
 * Copyright (c) 2017 Ian Clement.  All rights reserved.
 */

import java.util.Arrays;

/**
 * Implementation of Merge sort
 */
public class MergeSort {

	public static void main(String[] args) {
		Integer[] values = { 8, 6, 3, 4, 2, 9, 1, 7 };

		System.out.println("Unsorted array: " + Arrays.toString(values));

		sort(values);

		System.out.println("Sorted array:   " + Arrays.toString(values));

		System.out.printf("Binary search:  Element %d is in position %d.\n", 6, binarySearch(values, 6, 0, values.length));
		System.out.printf("Binary search:  Element %d is in position %d.\n", 5, binarySearch(values, 5, 0, values.length));
	}

	public static <T extends Comparable<T>> void sort(T[] elements) {
		// create data buffer to optimize recursion
		T[] buffer = (T[]) new Comparable[elements.length];

		// call mergesort
		sort(elements, 0, elements.length - 1, buffer);
	}

	/**
	 * Mergesort elements between left and right
	 *
	 * @param elements elements to be sorted.
	 * @param left     the leftmost index to sort.
	 * @param right    the rightmost index to sort.
	 * @param buffer   the buffer to use during sorting.
	 */
	private static <T extends Comparable<T>> void sort(T[] elements, int left, int right, T[] buffer) {
		// sort until single cell or empty sub-array
		if (left >= right) {
			return;
		}

		// sort two sub-arrays
		int mid = (left + right) / 2;
		sort(elements, left, mid, buffer);
		sort(elements, mid + 1, right, buffer);

		// merge into buffer
		merge(elements, left, mid, right, buffer);

		// copy portion back into sub-array.
		for (int i = left; i <= right; i++) {
			elements[i] = buffer[i];
		}
	}

	/**
	 * Merge contiguous sub-arrays of a source array into a second array (destination),
	 * specifically: left to mid (inclusive) and mid+1 to right (inclusive)
	 *
	 * @param source      the source array.
	 * @param left        the left-most sub-array index.
	 * @param mid         the midpoint index of the sub-arrays.
	 * @param right       the right-most sub-array index.
	 * @param destination the destination array.
	 */
	private static <T extends Comparable<T>> void merge(T[] source, int left, int mid, int right, T[] destination) {
		int i = left;
		int j = mid + 1;
		int k = left;

		// while there is elements in both sub-arrays to merge
		while ((i <= mid) && (j <= right)) {
			// take the smaller of the two
			if (source[i].compareTo(source[j]) <= 0) {
				destination[k++] = source[i++];
			}
			else {
				destination[k++] = source[j++];
			}
		}
		// merge the remaining element directly
		if (i > mid) {
			while (j <= right) {
				destination[k++] = source[j++];
			}
		}
		else {
			while (i <= mid) {
				destination[k++] = source[i++];
			}
		}
	}

	/**
	 * Searches for the specified target element in the given
	 * sorted array using the binary search algorithm recursively.
	 * The array must be sorted in ascending order according
	 * to the natural ordering of its elements.
	 *
	 * @param <T>      the type of elements in the array
	 * @param elements the sorted array to search in
	 * @param target   the target element to search for
	 * @param start    the starting index of the search range (inclusive)
	 * @param end      the ending index of the search range (inclusive)
	 * @return the index of the target element in the array, or -1 if it is not found
	 */
	public static <T extends Comparable<T>> int binarySearch(T[] elements, T target, int start, int end) {
		// Base case: start index is greater than end index.
		if (start > end) {
			return -1;
		}

		// Calculate the middle index.
		int mid = start + (end - start) / 2;

		// If the middle element is the target, return its index.
		if (elements[mid].equals(target)) {
			return mid;
		}

		// If the target is less than the middle element, search the left half of the array.
		if (elements[mid].compareTo(target) > 0) {
			return binarySearch(elements, target, start, mid - 1);
		}

		// If the target is greater than the middle element, search the right half of the array.
		return binarySearch(elements, target, mid + 1, end);
	}

}
