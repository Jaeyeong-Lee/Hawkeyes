package com.samsung.database.table;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertSame;

class EmployeeTableTest {

    private static EmployeeTable originEmployeeTable;

    @BeforeAll
    static void singletonInsertion() {
        originEmployeeTable = EmployeeTable.getInstance();
    }

    @RepeatedTest(10)
    void syncSingleton() {
        EmployeeTable recallEmployeeTable = EmployeeTable.getInstance();
        assertSame(originEmployeeTable, recallEmployeeTable);
    }

    @RepeatedTest(10)
    void asyncSingleton() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            Callable<EmployeeTable> callable = new AsyncEmployeeTable();
            Future<EmployeeTable> recallEmployeeTable = executor.submit(callable);
            assertSame(originEmployeeTable, recallEmployeeTable.get());
        }
        executor.shutdown();
    }

    public class AsyncEmployeeTable implements Callable<EmployeeTable> {

        @Override
        public EmployeeTable call() {
            return EmployeeTable.getInstance();
        }
    }
}