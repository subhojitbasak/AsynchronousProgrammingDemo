package org.AsynchronousProgrammingDemo;

import java.util.concurrent.*;

public class RunAsyncDemo {
    //runAsync() methods do not return anything
    /*
     * This method shows the use of runAsync(Runnable) method in Completable Future.
     * As we are not providing any custom Executor it will use its own ForkJoinPool
     * */
    public Void calculateFunction(int x, int y) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            int sum = x + y;
            System.out.println("Sum of " + x + " & " + y + " = " + sum);
            System.out.println("Thread: " + Thread.currentThread().getName());
        });

        return runAsyncFuture.get();

    }

    /*
     * This method shows the use of runAsync(Runnable,Executor) method in Completable Future.
     * As we are  providing a custom Executor it will use that one not ForkJoinPool
     * */
    public Void calculateFunctionWithCustomExecutor(int x, int y) throws ExecutionException, InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            int sum = x + y;
            System.out.println("Sum of " + x + " & " + y + " = " + sum);
            System.out.println("Thread: " + Thread.currentThread().getName());
        }, executor);

        return runAsyncFuture.get();

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsyncDemo runAsyncDemo = new RunAsyncDemo();
        System.out.println(".......FROM RUNASYNC DEMO.....");
        runAsyncDemo.calculateFunction(10, 12);
        runAsyncDemo.calculateFunctionWithCustomExecutor(10, 12);
    }
}
