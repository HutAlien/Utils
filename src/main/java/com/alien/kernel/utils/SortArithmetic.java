package com.alien.kernel.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: FengYunJun
 * @Date: 2019/2/18 09:47
 * @Description: 排序算法
 */
@Slf4j
public class SortArithmetic {
    /**
     * 快速排序
     *
     * @param
     * @return
     */
    public static int[] sort(int arr[], int low, int high) {  //每次排好一个基数  把比基数大的移到基数右边  小的移到左边
        int l = low;
        int h = high;
        int key = arr[low];
        while (l < h) {
            while (l < h && arr[h] >= key) {
                h--;
            }
            if (l < h) {
                int temp = arr[l];
                arr[l] = arr[h];
                arr[h] = temp;
                l++;
            }
            while (l < h && arr[l] <= key) {
                l++;
            }
            if (l < h) {
                int temp = arr[l];
                arr[l] = arr[h];
                arr[h] = temp;
                h--;
            }
        }
        if (l > low) {
            sort(arr, low, l - 1);
        }
        if (h < high) {
            sort(arr, l + 1, high);
        }
        return arr;
    }

    /**
     * 冒泡排序
     *
     * @param
     * @return
     */
    public static int[] sort(int arr[]) {    // 每次沉底一个最大的数
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序(不稳定)  每次找到最小的数 依次和前面的位置交换
     *
     * @param
     * @return
     */
    public static int[] chooseSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序(属于稳定排序的一种（通俗地讲，就是两个相等的数不会交换位置)  最快
     *
     * @param
     * @return
     */
    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }





    public static void main(String[] args) {
        int a[] = new int[]{3, 1, 4, 6, 8, 0};
        log.info("sort={}", sort(new int[]{3, 1, 4, 6, 8, 0}, 0, 5));
        log.info("a={}", sort(a));
        log.info("a={}", chooseSort(a));
        log.info("arr={}", insertSort(a));
    }
}
