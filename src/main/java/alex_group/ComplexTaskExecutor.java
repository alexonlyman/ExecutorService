package alex_group;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final ExecutorService executorService;
    private final CyclicBarrier barrier;
    private final ComplexTask complexTask;

    public ComplexTaskExecutor(int count) {
        this.executorService = Executors.newFixedThreadPool(count);
        this.barrier = new CyclicBarrier(count);
        this.complexTask = new ComplexTask();
    }

    public void executeTasks() throws InterruptedException {
        try {
            List<Callable<Void>> tasks = Arrays.asList(
                    () -> {
                        runTask(complexTask::buildWalls);
                        return null;
                    },
                    () -> {
                        runTask(complexTask::buildWindows);
                        return null;
                    },
                    () -> {
                        runTask(complexTask::buildRoof);
                        return null;
                    },
                    () -> {
                        runTask(complexTask::buildDoors);
                        return null;
                    },
                    () -> {
                        runTask(complexTask::buildFloor);
                        return null;
                    }
            );

            List<Future<Void>> futures = executorService.invokeAll(tasks);

            for (Future<Void> future : futures) {
                try {
                    future.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            executorService.shutdown();
        }
    }

    private void runTask(Runnable task) {
        try {
            task.run();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}

