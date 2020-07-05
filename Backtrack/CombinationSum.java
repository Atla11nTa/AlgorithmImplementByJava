package Backtrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * leetcode地址：https://leetcode.com/problems/combination-sum/description/
 * 问题说明： 从数组中挑选出任意个数的元素，元素之和为目标值
 * 理解回溯的套路和解法，递归法和非递归法
 */

public class CombinationSum {
    public static List<List<Integer>> Solution(List<Integer> candidates,int target){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> pathList = new ArrayList<>();
        //排序，方便剪枝
        candidates.sort(Integer::compareTo);
        //Backtrack(result,pathList,candidates,target,0);
        Backtrack2(result,pathList,candidates,target);
        return result;
    }
    //递归回溯
    public static void Backtrack(List<List<Integer>> result,List<Integer> pathList,List<Integer> candidates, int remain,int start){
        if(remain < 0){
            return;
        }
        if (remain == 0){
            //深复制
            result.add(new ArrayList<>(pathList));
        }else {
            //因为元素的先后次序无关，所以每次从start开始。如果先后次序有关，比如3->4和4->3是不一样的含义，那么每次都从0开始搜索
            for (int i=start;i<candidates.size();i++){
                int candidate = candidates.get(i);
                //已经排序过，所以通过判断可以剪枝操作
                if(candidate>remain){
                    return;
                }
                //进行下一轮搜索，因为每个元素可以多次选择，所以下一轮start还是i。
                pathList.add(candidate);
                Backtrack(result,pathList,candidates,remain-candidate,i);
                //返回上一轮，要把上一次的选择删除。
                pathList.remove(pathList.size()-1);
            }
        }
    }
    //非递归回溯，深度优先搜索
    public static void Backtrack2(List<List<Integer>> result, List<Integer> pathList, List<Integer> candidates, int target){
            int setSize = candidates.size();
            //记录每一轮的选择
            int[] roundChoice = new int[100];
            //每次从集中选择一个作为根
            for(int i=0;i<candidates.size();i++){
                //当前状态
                int currentSum = 0;
                //轮次
                int round=0;
                roundChoice[0] = i;
                //当又回溯到根节点时为结束
                boolean flag = false;
                while (round > 0 || !flag){
                    flag = true;
                    //如果选择超过了备选集，回溯，每次要回溯三个量
                    if(roundChoice[round]>=setSize){
                        round--;
                        currentSum -= pathList.get(round);
                        roundChoice[round]++;
                        continue;
                    }
                    //从备选集中选择一个
                    int choice = candidates.get(roundChoice[round]);
                    //找到一个答案
                    if(currentSum + choice == target){
                        //保存答案
                        pathList.add(round,choice);
                        Integer[] temp = new Integer[round+1];
                        for(int j=0;j<=round;j++){
                            temp[j] = pathList.get(j);
                        }
                        result.add(new ArrayList<>(Arrays.asList(temp)));
                        //回溯，寻找其他答案
                        round--;
                        currentSum -= pathList.get(round);
                        roundChoice[round]++;
                    }
                    //还需要继续往下寻找
                    else if(currentSum + choice<target){
                        // 注意保存路径是按轮次保存
                        currentSum += choice;
                        pathList.add(round,choice);
                        round++;
                        // 为避免重复查找，下一轮的选择应从这一轮的位置开始
                        roundChoice[round] = roundChoice[round-1];
                    }
                    //回溯
                    else {
                        round--;
                        currentSum -= pathList.get(round);
                        roundChoice[round]++;
                    }
                }
            }
    }

    public static void main(String[] args) {
        Integer []arr = new Integer[]{1,7,4,3};
        List<Integer> candidates = new ArrayList<>(Arrays.asList(arr));
        List<List<Integer>> result = Solution(candidates,11);
        System.out.println(result);
    }
}
