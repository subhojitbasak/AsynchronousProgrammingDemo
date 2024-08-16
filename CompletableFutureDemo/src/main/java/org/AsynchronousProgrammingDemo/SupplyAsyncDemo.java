package org.AsynchronousProgrammingDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {
    //runAsync() methods do not return anything
    /*
     * This method shows the use of runAsync(Runnable) method in Completable Future.
     * As we are not providing any custom Executor it will use its own ForkJoinPool
     * */
    public Integer calculateFunction(int x, int y) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            int sum = x + y;
//            System.out.println("Sum of " + x + " & " + y + " = " + sum);

            System.out.println("Thread: " + Thread.currentThread().getName());
            return sum;
        });

        return supplyAsyncFuture.get();

    }

    /*
     * This method shows the use of runAsync(Runnable,Executor) method in Completable Future.
     * As we are  providing a custom Executor it will use that one not ForkJoinPool
     * */
    public Integer calculateFunctionWithCustomExecutor(int x, int y) throws ExecutionException, InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        CompletableFuture<Integer> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            int sum = x + y;
//            System.out.println("Sum of " + x + " & " + y + " = " + sum);
            System.out.println("Thread: " + Thread.currentThread().getName());
            return sum;
        }, executor);

        return supplyAsyncFuture.get();

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        System.out.println(".......FROM SUPPLYASYNC DEMO.....");
        Integer res = supplyAsyncDemo.calculateFunction(10, 12);
        System.out.println("Sum of " + 10 + " & " + 12 + " = " + res);
        Integer res2 = supplyAsyncDemo.calculateFunctionWithCustomExecutor(10, 12);
        System.out.println("Sum of " + 10 + " & " + 12 + " = " + res2);
    }
}
