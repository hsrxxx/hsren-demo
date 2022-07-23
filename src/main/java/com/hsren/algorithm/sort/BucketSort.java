package com.hsren.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序
 *    桶排序是一种稳定的排序算法。
 *    它的工作原理是将序列中的元素分布到一定数量的桶内，然后分别对每个桶内的元素进行排序，
 *    最后再将各个桶内的有序子序列放回原始序列中。对于单个桶内的元素排序，我们可以使用别的排序算法，
 *    也可以递归使用桶排序。一般来说，对于单个桶内的元素，使用插入排序算法对它们进行排序。
 *
 *    桶排序适用于数据是均匀分布的情况，这样可以让分布到各个桶内的元素数量相当。而不是被集中分配到其中一个桶或几个桶。
 */
public class BucketSort {

  private void insertionSort(List<Integer> arr) {
    if (arr == null || arr.size() == 0) return;
    for (int i = 1; i < arr.size(); ++i) {
      int cur = arr.get(i);
      int j = i - 1;
      while (j >= 0 && arr.get(j) > cur) {
        arr.set(j+1, arr.get(j));
        --j;
      }
      arr.set(j+1, cur);
    }
  }

  // 每个桶的大小，由于桶内使用插入排序，因此桶的大小使用一个较小值会比较高效。
  //
  // 一般来说，当处理的数组大小在 5-15 时，使用插入排序往往会比快排或归并更高效。
  // 因此在桶排序中，我们尽量让单个桶内的元素个数是在 5-15 个之间，这样可以用插入排序高效地完成桶内排序。
  // 参考链接：https://algs4.cs.princeton.edu/23quicksort/
  // 参考段落：
  // Cutoff to insertion sort. As with mergesort,
  // it pays to switch to insertion sort for tiny arrays.
  // The optimum value of the cutoff is system-dependent,
  // but any value between 5 and 15 is likely to work well in most situations.
  private int bucketSize;

  public BucketSort(int bucketSize) {
    this.bucketSize = bucketSize;
  }

  // Time(avg): O(n+k), Time(worst): O(n^2), Space: O(n)
  public void sort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int max = arr[0], min = arr[0];
    for (int num: arr) {
      if (num > max) max = num;
      if (num < min) min = num;
    }

    int bucketCount = arr.length / bucketSize;
    List<List<Integer>> buckets = new ArrayList<>(bucketCount);
    for (int i = 0; i < bucketCount; ++i)
      buckets.add(new ArrayList<>());

    for (int num: arr) {
      int idx = (int)((num - min) / (max - min + 1.0) * bucketCount);
      buckets.get(idx).add(num);
    }

    int idx = 0;
    for (List<Integer> bucket: buckets) {
      insertionSort(bucket);
      for (int num: bucket)
        arr[idx++] = num;
    }
  }

}