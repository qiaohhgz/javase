package com.jim.junit.outside;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/31/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestMyClass.class);
        int failureCount = result.getFailureCount();
        int ignoreCount = result.getIgnoreCount();
        int runCount = result.getRunCount();
        long runTime = result.getRunTime();
        System.out.println("failureCount = " + failureCount);
        System.out.println("ignoreCount = " + ignoreCount);
        System.out.println("runCount = " + runCount);
        System.out.println("runTime = " + runTime);
        List<Failure> failures = result.getFailures();
        for (Failure failure : failures) {
            System.out.println(failure.toString());
        }
    }
}
