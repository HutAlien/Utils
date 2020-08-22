package com.alien.kernel.utils.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author: alien
 * @date: 2020/7/31 9:44
 * @description:
 */
public class ForkJoinTaskTest {


    /**
     * fork/join 分支/合并框架
     *
     * @Param:
     * @return:
     */
    static class SumTask extends RecursiveTask<Integer> {

        final int start;
        final int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start < 100000) {//计算量小于阈值，则无需拆分
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
            //拆分任务
            SumTask task1 = new SumTask(start, (start + end) / 2);
            SumTask task2 = new SumTask((start + end) / 2 + 1, end);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      /*  long begin = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask task = new SumTask(1, 100000000);
        pool.submit(task);
        System.out.println(task.get());
        long end = System.currentTimeMillis();
        System.out.println(end - begin);*/

       /* long s=System.currentTimeMillis();
        int sum = 0;
        for (int i = 1; i <= 100000000; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - s);*/

        Long add = LongStream.rangeClosed(1, 100000000).parallel().reduce(0, (x, y) -> x + y);
        System.out.println(add);
    }
}
