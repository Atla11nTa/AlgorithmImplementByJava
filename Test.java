import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.TreeSet;

public class Test {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    public static ArrayList<Integer> getLeftView(TreeNode root){
        if(root == null){
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int toBeVisit=1;
        int nextLevel=0;
        TreeNode cur;
        ArrayList<Integer> resList = new ArrayList<>();
        int theLastLevel = 0;
        while(!queue.isEmpty()){
            cur = queue.poll();
            // 每层第一个节点
            if(nextLevel == 0){
                resList.add(cur.val);
            }
            if(cur.left!=null){
                queue.offer(cur.left);
                nextLevel++;
            }
            if(cur.right!=null){
                queue.offer(cur.right);
                nextLevel++;
            }
            toBeVisit--;
            if (queue.isEmpty()) {
                break;
            }
            // 一层访问结束
            if(toBeVisit==0){
                toBeVisit = nextLevel;
                theLastLevel = nextLevel;
                nextLevel=0;
            }
        }
        while (theLastLevel > 1) {
            resList.remove(resList.size() - 1);
            theLastLevel--;
        }
        return resList;
    }
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        System.out.print(getLeftView(node1).toString());
    }
}
