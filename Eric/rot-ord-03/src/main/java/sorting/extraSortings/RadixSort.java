package sorting.extraSortings;

import sorting.AbstractSorting;

/**
 * Stable radix sort. Using counting sort as strategy of sub problems sorting.
 * 
 * @author Eric Breno
 */
public class RadixSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
			radixSort(array, leftIndex, rightIndex);
		}
	}
	
	private void radixSort(Integer[] array, int start, int end) {
		String[] nums = formatNumbers(array, start, end);
		int maxLen = nums[0].length();
		for (int i = maxLen - 1; i >= 0; i--) {
			countingSort(nums, i, start);
		}
		for (int i = start; i <= end; i++)
			array[i] = Integer.parseInt(nums[i]);
	}
	
	private String[] formatNumbers(Integer[] array, int start, int end) {
		String[] aux = new String[end - start + 1];
		int longest = 0;
		for (int i = start; i <= end; i++) {
			String temp = String.format("%d", array[i]);
			longest = Math.max(longest, temp.length());
		}
		for (int i = start; i <= end; i++) {
			aux[i] = String.format("%0" + longest + "d", array[i]);
		}
		return aux;
	}
	
	private void countingSort(String[] array, int index, int startIndex) {
		int[] auxCount = new int[10];
		String[] aux = new String[array.length];
		for (String num : array) {
			int elem = Character.getNumericValue(num.charAt(index));
			auxCount[elem]++;
		}
		for (int i = 1; i < auxCount.length; i++)
			auxCount[i] += auxCount[i - 1];
		for (int i = array.length - 1; i >= 0; i--) {
			int position = Character.getNumericValue(array[i].charAt(index));
			aux[auxCount[position] - 1] = array[i];
			auxCount[position]--;
		}
		copyArray(aux, array, startIndex);
	}
	
	private void copyArray(String[] from, String[] to, int startTo) {
		for (int pointer = 0; pointer < from.length; pointer++, startTo++)
			to[startTo] = from[pointer];
	}
}
