package 笔试练习汇总;

import javafx.beans.binding.ObjectExpression;

import java.util.*;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

class Parent{
    public void sayHello(){
        System.out.println("parent sayHello");
    }
    public void test(){
        System.out.println("parent test");
    }
}
class Child extends Parent{
    public void sayHello(){
        System.out.println("child sayHello");
    }
    public void test1(){
        System.out.println("child test");
    }
}

public class Test implements Cloneable {

    @Override
    public Test clone() throws CloneNotSupportedException {
        Test test = (Test) super.clone();
        return test;
    }
    private static AtomicReference<Thread> owner = new AtomicReference();

    public void test() {
        Thread currentThread = Thread.currentThread();
        if (owner.compareAndSet(null, currentThread)) {
            LockSupport.park();
        }
    }

    public static void main(String[] args) {
        Long num = 0L;
        System.out.println(num.hashCode());
    }
}