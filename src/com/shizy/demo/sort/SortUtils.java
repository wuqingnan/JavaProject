package com.shizy.demo.sort;

import java.util.Random;

public class SortUtils {

	public static final void quickSort(int[] arr, int start, int end) {
		
		if (start >= end) {
			return;
		}
		
		int key = arr[start];
		int left = start;
		int right = end;
		while(left < right) {
			//先从右向左找第一个大于key的数
			while(left < right && arr[right] >= key) {
				right--;
			}
			arr[left] = arr[right];
			//然后从左向右找第一个小于key的数
			while(left < right && arr[left] <= key) {
				left++;
			}
			arr[right] = arr[left];
		}
		//执行一遍后，把key放到该放的位置
		arr[left] = key;
		quickSort(arr, start, left - 1);
		quickSort(arr, left + 1, end);
	}
	
	public static final void bubbleSort(int[] arr) {
		
		int temp = 0;
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr.length - 1 - i; j++) {
//				if (arr[j] > arr[j+1]) {
//					temp = arr[j];
//					arr[j] = arr[j + 1];
//					arr[j + 1] = temp;
//				}
//			}
//		}
	}
	
	public static final void insertSort(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				} else {
					break;
				}
			}
		}

	}
	
	public static void main(String[] args) {
		
		int[] arr = new int[20];
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(arr.length);
			System.out.print(arr[i] + ", ");
		}
//		SortUtils.quickSort(arr, 0, arr.length - 1);
//		SortUtils.bubbleSort(arr);
		SortUtils.insertSort(arr);
		
		System.out.println("\n排序结果：");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
		
	}
}
