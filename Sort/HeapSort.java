package Sort;

import java.util.Arrays;

/**
 * 实现了堆有关的算法，包括：建堆，从堆中删除元素，堆排序
 */

public class HeapSort {
    //堆
    static class Heap{
        private int[] arr;
        private int heapSize;
        //初始化一个堆
        public Heap(int[] arr){
            this.arr = arr;
            this.heapSize = arr.length;
            //建一个大根堆,从左至右，从下至上调整堆
            for(int i = arr.length-1;i>=0;i--){
                HeapInsert(arr,i);
            }
        }
        // 从尾部插入元素重建堆也是使用这个方法
        private void HeapInsert(int[] arr,int index){
            while (index != 0){
                int parent = (index-1)/2;
                //如果比父节点大就交换，然后继续往上
                if(arr[parent]<arr[index]){
                    swap(arr,parent,index);
                    index = parent;
                }else
                    break;
            }
        }
        // 堆的删除和重建, 用最后一个叶子节点覆盖根节点，然后[0...n-1]这一部分的堆由根下沉重建
        public void Remove(){
            swap(arr,0,heapSize - 1);
            heapSize--;
            int parent = 0;
            int child = 2 * parent +1;
            while (child<heapSize){
                //在子节点中找到一个较大的
                if (child + 1 < heapSize && arr[child + 1] > arr[child]) {
                    child++;
                }
                //将较大的子节点与父节点交换，然后继续下沉
                if (arr[child] > arr[parent]) {
                    swap(arr, child, parent);
                    parent = child;
                    child = parent * 2 + 1;
                } else {
                    break;
                }
            }
        }

        //堆排序: 反复从堆顶取出元素放到数组尾部，实现堆排序
        public void Sort(){
            for(int i = 0;i<arr.length;i++){
                Remove();
            }
        }
        public void swap(int[] arr,int parent,int index){
            int temp = arr[parent];
            arr[parent] = arr[index];
            arr[index] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,4,6};
        Heap heap = new Heap(arr);
        System.out.println(Arrays.toString(arr));
        heap.Sort();
        System.out.println(Arrays.toString(arr));
    }
}
