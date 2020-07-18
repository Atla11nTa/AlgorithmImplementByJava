package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * 题目：最小值栈
 */
public class Problem155 {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    public Problem155() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(minStack.peek(), x));
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
        minStack.pop();
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek();
    }
}
