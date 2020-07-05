package TopK;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * 通过插入排序实现找最大K个数
 * 首先对前K个数按升序排序得到数组resArr[]。
 * 然后遍历剩余N-K个数，如果arr[i]大于resArr[0]，就用数i替换掉resArr[0]，然后对resArr[0]重新排序，重新排序采用插入排序，resArr[1...k-1]是有序的
 * 最终resArr里保存的就是最大的K个数
 * 更新最大值数组的时间复杂度为O(k)，总的时间复杂度O(N*k)
 */
public class InsertSortImp {
    //这个函数
    public static void InsertSort(int[] arr){
        int temp = arr[0];
        for(int i=1;i<=arr.length;i++){
            if(i<arr.length&&arr[i]<temp){
                arr[i-1] = arr[i];
            }else {
                arr[i-1] = temp;
                break;
            }
        }
    }

    // 建一个小根堆
    public static void HeapInit(int[] arr){
        for(int i=arr.length-1;i>=1;i--){
            while(i != 0){
                int parent = (i-1)/2;
                if(arr[i]<arr[parent]){
                    swap(arr,parent,i);
                    i = parent;
                }else
                    break;
            }
        }
    }
    public static void swap(int[] arr,int parent,int index){
        int temp = arr[parent];
        arr[parent] = arr[index];
        arr[index] = temp;
    }
    // 堆重建
    public static void HeapRebuild(int[] arr){
        int parent = 0;
        int child = 2 * parent+1;
        while(child<arr.length){
            if(child+1<arr.length && arr[child+1]<arr[child]){
                child++;
            }
            if(arr[child]<arr[parent]){
                swap(arr,parent,child);
                parent = child;
                child = 2*parent+1;
            }else
                break;
        }
    }

    public static void main(String ...args){
        int[] array = {2,1,3,4,5,10,9,8,7,6,20,12,2};
        int k=5;
        System.out.println(Arrays.toString(array));
        int[] resArr = Arrays.copyOfRange(array,0,k);
        //Arrays.sort(resArr);
        HeapInit(resArr);
        System.out.println(Arrays.toString(resArr));
        for(int i=k;i<array.length;i++){
            if(array[i]>resArr[0]){
                resArr[0] = array[i];
                //InsertSort(resArr);
                HeapRebuild(resArr);
            }
        }
        System.out.println("Top"+k+":"+Arrays.toString(resArr));
    }
}
