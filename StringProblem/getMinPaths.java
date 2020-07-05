package StringProblem;

import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 题目：字符串路径转换问题
 * 题目描述： 给定两个字符串start和end，给定一个字符串List，List中无重复字符串，且包含end字符串，不包含start字符串，所有字符都是小写。
 * 输出所有从start到end的最小变换路径，要求每次变换只能变换一个字符，而且变换后的字符串必须存在于List中。
 * 举例： start = "abc" end = "aef" List={"aec","abf,"aef","acc"}
 * 最小变换路径： "abc"->"aec"->"aef"
 *              "abc"->"abf"->"aef"
 * 解题思路：
 * 1. 首先把start也加入到list中，然后建立一个Map，key是节点，Value是从节点经一次变换得到的新节点，根据这个实际就建立了一个图
 * 2. 问题就转变为求解图中start节点到end节点的最短路径问题。
 * 3. 图的遍历有两种方式DFS和BFS，其中BFS适合得到遍历路径，而DFS适合计算最短距离，因为DFS是按广度推进的，而BFS是按深度推进，有入栈出栈过程。
 * 4. 根据DFS和BFS的特性，首先想到利用BFS得到所有的路径，然后把最短的输出，但是遍历需要用visited记录已经访问的节点，若想输出多条路径，必然
 *  存在重复的节点访问，所以不能记录已经访问的节点，但是这样的话，遍历就很难终止。
 * 5. 所以换个思路，首先通过DFS计算得到每个节点距离start的最小距离，然后进行BFS遍历，遍历过程中根据最短距离表，每次深度访问都是按着最短距离的
 * 方向推进，最终得到结果。
 * 6. 这个问题的解法即用到了DFS也用到了BFS，很综合
 *
 */
public class getMinPaths {
    /**
     * 从列表中找到属于key的next的字符串，添加列表中返回, 即找到wordSet中与key只相差一个字符的字符串
     * 关于求nextList的思路有两种：
     * 1. 遍历wordSet，与对比是否n-1个字符一致，这样的时间复杂度为 O(N*key.length)，所以当wordSet元素很多时效率低
     * 2. 这里采用一种更稳定的做法，依次把key的每个字符变成'a'-'z'，然后查找wordSet中是否存在，存在就说明是key的next。由于HashSet的查找
     * 效率很高，为O(1)，所以这种做法的时间复杂度是O(key.length)
     */
    private static ArrayList<String> getNext(String key,Set<String> wordSet){
        ArrayList<String> nextList = new ArrayList<>();
        char[] keyArr = key.toCharArray();
        for (char changeChar = 'a'; changeChar <= 'z'; changeChar++) {
            for (int i = 0; i < keyArr.length; i++) {
                // 如果变换前后一致就不要变换了
                if(changeChar == keyArr[i])
                    continue;
                char temp = keyArr[i];
                keyArr[i] = changeChar;
                String newKey = String.copyValueOf(keyArr);
                if(wordSet.contains(newKey))
                    nextList.add(newKey);
                //别忘了还原
                keyArr[i] = temp;
            }
        }
        return nextList;
    }
    //为list中的每一个字符串生成一个next列表
    private static HashMap<String, ArrayList<String>> getNexts(List<String> list){
        Set<String> wordSet = new HashSet<>(list);
        HashMap<String, ArrayList<String>> nextMap = new HashMap<>(wordSet.size());
        for(var e:wordSet){
            nextMap.put(e, getNext(e,wordSet));
        }
        return nextMap;
    }

    /**
     * 广度优先遍历BFS，解出源点到每个节点的最短距离
     */
    private static HashMap<String, Integer> BFS(HashMap<String,ArrayList<String>> nextMap, List<List<String>> pathList, String start){
        //存储每个节点到起始节点的最短距离
        HashMap<String ,Integer> minDistances = new HashMap<>();
        //广度优先算法的队列
        Queue<String> pathQueue = new LinkedList<>();
        //记录某个节点是否已经访问
        HashSet<String> visited = new HashSet<>();
        pathQueue.offer(start);
        minDistances.put(start,0);
        visited.add(start);
        while (!pathQueue.isEmpty()){
            String currentNode = pathQueue.poll();
            for (var next : nextMap.get(currentNode)) {
                //没访问过的节点入队。
                if (!visited.contains(next)) {
                    pathQueue.offer(next);
                    visited.add(next);
                    minDistances.put(next,minDistances.get(currentNode)+1);
                }
            }
        }
        return minDistances;
    }

    /**
     * 深度优先遍历DFS,递归解法，根据源点到每个节点的最短距离，依据DFS求解出最短路径
     * 有点类似回溯的递归解法，只是每个节点的下次回溯从自己的next中选择，而不是从一个集
     */
    private static void DFS(HashMap<String,ArrayList<String>> nextMap,HashMap<String,Integer> minDistances,
                            LinkedList<String> path,List<List<String>> pathList, String cur,String end){
        path.add(cur);
        if(cur.equals(end)){
            //深复制
            pathList.add(new LinkedList<String>(path));
        }
        else {
            for(var next:nextMap.get(cur)){
                //判断从next到start的最短路径是否是经过cur，如果是才继续深度遍历，若不是，说明start到next的最短路径不是经过cur到达的
                if(minDistances.get(next) == minDistances.get(cur)+1){
                    DFS(nextMap,minDistances,path,pathList,next,end);
                }
            }
        }
        path.pollLast();
    }


    public static List<List<String>> Solution(List<String> list,String start,String end){
        List<List<String>> minPathsList = new ArrayList<>();
        //将起点也添加进去
        list.add(start);
        //获得列表每个字符串的next字符串Map
        HashMap<String, ArrayList<String>> nextMap = getNexts(list);
        //得到了nextMap，就从start开始执行广度优先遍历算法，得到每个节点到起始节点的最短距离
        HashMap<String,Integer> minDistances = BFS(nextMap,minPathsList,start);
        DFS(nextMap,minDistances,new LinkedList<String>(),minPathsList,start,end);
        return minPathsList;
    }


    public static void main(String[] args) {
        List<String> list  = new ArrayList<>(Arrays.asList("aec","abf","aef","acc"));
        String start = "abc";
        String end = "aef";
        List<List<String>> minPathsList = Solution(list,start,end);
        System.out.println(minPathsList.toString());
    }
}
