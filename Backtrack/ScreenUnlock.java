package Backtrack;
import java.util.*;

/**
 * 题目： https://www.nowcoder.com/questionTerminal/c552248efdbd41a18d35b7a2329f7ad8?toCommentId=6336299
 * 分析：典型的回溯。
 * 注意点：
 * 对于Pos类，若是使用a.equals(b)，记得自己实现equals方法。
 * 其次，使用HashSet<Pos>保存时，因为所有操作都会执行HashCode函数，但是这里不能使用Object类的，得自己重写一个，使用Objects.hash(x,y)。
 * Object类的hash函数是通过
 * 对象地址产生。
 */

public class ScreenUnlock {
    public static int pathSum = 0;
    static class Pos{
        int x;
        int y;
        Pos(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public int Solution(int m,int n){
        //异常处理
        if(m > n || m < 0 || m > 9){
            return 0;
        }
        if(n>9)
            n=9;
        //保存访问情况
        int[][] map = new int[3][3];
        while(m <= n){
            //从9个点中选择一个起点
            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++){
                    for(int k=0;k<map.length;k++)
                        Arrays.fill(map[k],0);
                    Pos start = new Pos(i,j);
                    map[start.x][start.y] = 1;
                    BackTrack(start,m,1,map);
                }
            m++;
        }
        return pathSum;
    }

    public void BackTrack(Pos start, int maxLen,int pathLen, int[][] map){
        if(pathLen == maxLen){
            pathSum++;
            return;
        }else {
            //遍历9个点
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    Pos newStart = new Pos(i, j);
                    //检验新的键是否符合要求
                    if (canVisit(map, start, newStart)) {
                        map[newStart.x][newStart.y] = 1;
                        //回溯
                        BackTrack(newStart, maxLen,pathLen+1,map);
                        map[newStart.x][newStart.y] = 0;
                    }
                }
            }
        }
    }

    public boolean canVisit(int[][] map,Pos curPos, Pos newPos){
        //首先新键和当前键不能相同
        if(!(curPos.x == newPos.x && curPos.y == newPos.y)){
            //两个键之间存在中间键
            if((curPos.x-newPos.x)%2 == 0 &&(curPos.y-newPos.y)%2 == 0){
                Pos mid = new Pos((curPos.x+newPos.x)/2,(curPos.y+newPos.y)/2);
                //中间键已经访问过了
                if(map[mid.x][mid.y] == 1){
                    return map[newPos.x][newPos.y] == 0;
                }else
                    return false;
            }else {
                //其他情况只要是之前没访问过的键都行
                return map[newPos.x][newPos.y] == 0;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ScreenUnlock so = new ScreenUnlock();
        System.out.println(so.Solution(9,9));
    }
}
//{0, 9, 56, 320, 1624, 7152, 26016, 72912, 140704, 140704 };
//public class ScreenUnlock {
//    public static int pathSum = 0;
//    static class Pos{
//        int x;
//        int y;
//        Pos(int x,int y){
//            this.x = x;
//            this.y = y;
//        }
//        @Override
//        public boolean equals(Object obj){
//            if(obj instanceof Pos){
//                Pos pos = (Pos) obj;
//                return this.x == pos.x && this.y == pos.y;
//            }else{
//                return false;
//            }
//        }
//        @Override
//        public int hashCode(){
//            return Objects.hash(x,y);
//        }
//    }
//    public int Solution(int m,int n){
//        if(m > n || m < 0 || m > 9){
//            return 0;
//        }
//        if(n>9)
//            n=9;
//        while(m <= n){
//            //从9个点中选择一个起点
//            for(int i=0;i<3;i++)
//                for(int j=0;j<3;j++){
//                    Pos start = new Pos(i,j);
//                    Set<Pos> pathSet = new HashSet<>();
//                    pathSet.add(start);
//                    BackTrack(start,m,pathSet);
//                }
//            m++;
//        }
//        return pathSum;
//    }
//
//    public void BackTrack(Pos start, int nodeCount, Set<Pos> pathSet){
//        if(pathSet.size() == nodeCount){
//            pathSum++;
//            return;
//        }else {
//            //遍历9个点
//            for (int i = 0; i <= 2; i++) {
//                for (int j = 0; j <= 2; j++) {
//                    Pos newStart = new Pos(i, j);
//                    if (canVisit(pathSet, start, newStart)) {
//                        pathSet.add(newStart);
//                        BackTrack(newStart, nodeCount, pathSet);
//                        pathSet.remove(newStart);
//                    }
//                }
//            }
//        }
//    }
//
//    //检验是否可以访问
//    public boolean canVisit(Set<Pos> pathSet,Pos curPos, Pos newPos){
//        //首先新点旧点不能完全相同
//        if(!curPos.equals(newPos)){
//            //两个点之间有中间点，那么一定要中间点已经访问过了
//            if((curPos.x-newPos.x)%2 == 0 &&(curPos.y-newPos.y)%2 == 0){
//                Pos mid = new Pos((curPos.x+newPos.x)/2,(curPos.y+newPos.y)/2);
//                if(pathSet.contains(mid)){
//                    return !pathSet.contains(newPos);
//                }else
//                    return false;
//            }
//            return !pathSet.contains(newPos);
//        }
//        return false;
//    }
//
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        ScreenUnlock so = new ScreenUnlock();
//        int m = in.nextInt();
//        int n = in.nextInt();
//        System.out.println(so.Solution(m,n));
//    }
//}

