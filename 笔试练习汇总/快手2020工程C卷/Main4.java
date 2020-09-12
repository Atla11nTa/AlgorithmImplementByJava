package 笔试练习汇总.快手2020工程C卷;

import java.util.*;

public class Main4 {
    static class TreeNode{
        int val;
        TreeNode father;
        List<TreeNode> children;
        boolean hasWater;
        TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
            hasWater = false;
        }
    }

    public static void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        node.hasWater = true;
        List<TreeNode> children = node.children;
        for (TreeNode child : children) {
            dfs(child);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // n个蓄水池
        int n = in.nextInt();
        Map<Integer, TreeNode> hashMap = new HashMap<>(n);
        // n-1条边
        for (int i = 0; i < n - 1; i++) {
            int fatherNum = in.nextInt();
            int childNum = in.nextInt();
            if (fatherNum > childNum) {
                int temp = fatherNum;
                fatherNum = childNum;
                childNum = temp;
            }
            TreeNode fatherNode = hashMap.get(fatherNum);
            if (fatherNode == null) {
                fatherNode = new TreeNode(fatherNum);
                hashMap.put(fatherNum, fatherNode);
            }
            TreeNode childNode = hashMap.get(childNum);
            if (childNode == null) {
                childNode = new TreeNode(childNum);
                hashMap.put(childNum, childNode);
            }
            fatherNode.children.add(childNode);
            childNode.father = fatherNode;
        }
        //操作
        int caseNum = in.nextInt();
        int op;
        // 操作对象
        int opObj;
        for (int i = 0; i < caseNum; i++) {
            op = in.nextInt();
            opObj = in.nextInt();
            // 加水操作,递归加水，深度优先遍历
            if (op == 1) {
                TreeNode curNode = hashMap.get(opObj);
                dfs(curNode);
            }
            // 放水操作
            else if (op == 2) {
                TreeNode curNode = hashMap.get(opObj);
                while (curNode != null) {
                    curNode.hasWater = false;
                    curNode = curNode.father;
                }
            }
            // 查询操作
            else {
                TreeNode curNode = hashMap.get(opObj);
                System.out.println(curNode.hasWater ? 1 : 0);
            }
        }
    }
}
