/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.sort;

/**
 * 快速排序
 *
 * @Author: cuiwy
 * @Date: 2019/4/9 17:33
 * @version: v1.0.0
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {0,946, 30, 82, 90, 56, 17, 95, 15, 40};
        sort(array);
        for (int a : array) {
            System.out.print(a + " ");
        }
    }

    public static void quickSort(int [] arr,int left,int right) {
        int pivot = 0;
        if(left < right) {
            pivot = partition(arr,left,right);
            quickSort(arr,left,pivot-1);
            quickSort(arr,pivot+1,right);
        }
    }

    private static int partition(int[] arr,int left,int right) {

        int key = arr[left];
        while(left < right) {
            while(left < right && arr[right] >= key) {
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= key) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = key;
        return left;

    }

    private static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快排
     *
     * @param array
     * @param i
     * @param j
     */
    private static void quickSort2(int[] array, int i, int j) {
        if (i >= j) {
            return;
        }
        int low = i;
        int height = j;
        int center = array[low];
        while (low < height) {
            //右边
            while (low < height) {
                if (array[height] > center) {
                    swap(array, low, height);
                    break;
                }
                height--;
            }
            //左边
            while (low < height) {
                if (array[low] > center) {
                    swap(array, low, height);
                    break;
                }
                low++;
            }
        }
        quickSort(array, i, low - 1);
        quickSort(array, low + 1, j);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
