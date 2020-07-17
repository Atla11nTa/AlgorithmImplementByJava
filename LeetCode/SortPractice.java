package LeetCode;

import java.util.Arrays;

public class SortPractice {
    static class QuickSort {
        static void patition(int[] nums, int begin, int end) {
            if (begin < end) {
                int tar = nums[begin];
                int left = begin;
                int right = end;
                while (left < right) {
                    while (left < right) {
                        if (nums[right] >= tar) {
                            right--;
                        } else {
                            nums[left] = nums[right];
                            break;
                        }
                    }
//                    left++;
                    while (left < right) {
                        if (nums[left] < tar) {
                            left++;
                        } else {
                            nums[right] = nums[left];
                            break;
                        }
                    }
//                    right--;
                }
                nums[left] = tar;
                patition(nums, begin, left - 1);
                patition(nums, left + 1, end);
            }

        }
        public static void sort(int[] nums) {
            patition(nums, 0, nums.length - 1);
        }
    }

    static class HeapSort {
        static void bottomToUp(int[] nums, int index) {
            int father;
            while (index > 0) {
                father = (index - 1) / 2;
                if (father >= 0 && nums[father] < nums[index]) {
                    int temp = nums[father];
                    nums[father] = nums[index];
                    nums[index] = temp;
                    index = father;
                } else {
                    break;
                }
            }
        }
        static void buildHeap(int[] nums) {
            for (int i = nums.length - 1; i >= 0; i--) {
                bottomToUp(nums, i);
            }
        }

        static void rebuildHeap(int[] nums, int end) {
            int index = 0;
            int child = 2 * index + 1;
            //第一个元素下沉
            while (child < end) {
                if (child + 1 < end && nums[child + 1] > nums[child]) {
                    child++;
                }
                if (nums[child] > nums[index]) {
                    int temp = nums[child];
                    nums[child] = nums[index];
                    nums[index] = temp;
                    index = child;
                    child = 2 * index + 1;
                } else {
                    break;
                }
            }
        }
        static void sort(int[] nums) {
            buildHeap(nums);
            int len = nums.length;
            for (int i = 0; i < nums.length; i++) {
                //与末尾交换
                int temp = nums[len - i - 1];
                nums[len - i - 1] = nums[0];
                nums[0] = temp;
                rebuildHeap(nums, len - i - 1);
            }
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, 3, 1, 9, 7};
//        QuickSort.sort(nums);
        HeapSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
