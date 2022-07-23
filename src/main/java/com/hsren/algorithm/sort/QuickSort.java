package com.hsren.algorithm.sort;

/**
 * 快速排序
 *    快速排序是由东尼·霍尔提出的一种高效的排序算法，简称快排。
 *    它的算法思想并不复杂，可以用 3 个步骤 6 个字来概括：选基、分割、递归。
 *    扩充成一句话就是：首先挑选基准值；然后分割数组，把小于基准值的元素放到基准值前面，
 *    大于基准值的元素放到基准值后面；最后递归地对小于基准值的子序列和大于基准值的子序列进行排序。
 */
public class QuickSort {

  private void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // [ ... elem < pivot ... | ... elem >= pivot ... | unprocessed elements ]
  //                          i                       j
  private int lomutoPartition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low;
    for (int j = low; j < high; ++j) {
      if (arr[j] < pivot) {
        swap(arr, i, j);
        ++i;
      }
    }
    swap(arr, i, high);
    return i;
  }

  // lomuto partition 的另一种实现，可以把最后的 swap 合并到循环中。
  private int lomutoPartition2(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low;
    for (int j = low; j <= high; ++j) {
      if (arr[j] <= pivot) {
        swap(arr, i, j);
        ++i;
      }
    }
    return i-1;
  }

  private void lomutoSort(int[] arr, int low, int high) {
    if (low < high) {
      int k = lomutoPartition(arr, low, high);
      lomutoSort(arr, low, k-1);
      lomutoSort(arr, k+1, high);
    }
  }

  private int hoarePartitionDoWhile(int[] arr, int low, int high) {
    int pivot = arr[low + (high-low)/2];
    int i = low-1, j = high+1;
    while (true) {
      do {
        ++i;
      } while (arr[i] < pivot);
      do {
        --j;
      } while (arr[j] > pivot);
      if (i >= j) return j;
      swap(arr, i, j);
    }
  }

  // [ ... elem <= pivot ... | unprocessed elements | ... elem >= pivot ... ]
  //                         i                      j
  private int hoarePartition(int[] arr, int low, int high) {
    int pivot = arr[low + (high-low)/2];
    int i = low, j = high;
    while (true) {
      while (arr[i] < pivot) ++i;
      while (arr[j] > pivot) --j;
      if (i >= j) return j;
      swap(arr, i++, j--);
    }
  }

  private void hoareSort(int[] arr, int low, int high) {
    if (low < high) {
      int k = hoarePartition(arr, low, high);
      hoareSort(arr, low, k);
      hoareSort(arr, k+1, high);
    }
  }

  // Time: O(n*log(n)), Space: O(n)
  public void lomutoSort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    lomutoSort(arr, 0, arr.length-1);
  }

  // Time: O(n*log(n)), Space: O(n)
  public void hoareSort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    hoareSort(arr, 0, arr.length-1);
  }

}