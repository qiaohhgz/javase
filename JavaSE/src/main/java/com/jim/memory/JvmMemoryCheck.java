package com.jim.memory;

import common.Logger;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.Formatter;
import java.util.ListIterator;

/**
 * This servlet is used by to check JVM memory.  It logs the actual
 * memory numbers as a side effect of running.
 */
public class JvmMemoryCheck {
    protected static final Logger log = Logger.getLogger(JvmMemoryCheck.class);

    private static final long serialVersionUID = 1L;

    private final float m_heapMemoryUsedBound = 90;
    private final float m_permGenMemoryUsedBound = 90;
    private final float m_percentMultiplier = 100;

    private static final String PERM_GEN = "PERM GEN";

    /**
     * prints memory usage.
     */
    public void check() {

        //Begin assembling the content
        StringBuilder msgString = new StringBuilder();
        MemoryUsage heapMemoryUsed = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

        long memUsed = heapMemoryUsed.getUsed();
        long memMax = heapMemoryUsed.getMax();
        float percentHeapMemInUse = (float) memUsed / (float) memMax;

        msgString.append("Heap Memory: ");
        msgString.append((percentHeapMemInUse >= m_heapMemoryUsedBound) ? "WARNING  " : "OK  ");

        StringBuilder logString = new StringBuilder();
        Formatter formatter = new Formatter(logString);
        formatter.format("Heap Memory:%d%% (used=%d,max=%d) ",
                (int) (percentHeapMemInUse * m_percentMultiplier), memUsed, memMax);

        msgString.append("Perm Gen Memory: ");

        MemoryUsage permGenMemoryUsed = null;
        ListIterator lstIter = ManagementFactory.getMemoryPoolMXBeans().listIterator();
        while (lstIter.hasNext()) {
            MemoryPoolMXBean memPoolMXBean = (MemoryPoolMXBean) lstIter.next();
            if (memPoolMXBean.getName().toUpperCase().indexOf(PERM_GEN) > -1) {
                permGenMemoryUsed = memPoolMXBean.getUsage();
            }
        }
        memUsed = permGenMemoryUsed.getUsed();
        memMax = permGenMemoryUsed.getMax();
        float percentPermMemInUse = (float) memUsed / (float) memMax;
        if (permGenMemoryUsed == null) {
            msgString.append("WARNING");
        } else {
            msgString.append((percentPermMemInUse >= m_permGenMemoryUsedBound) ? "WARNING" : "OK");
        }

        formatter.format("Perm Gen Memory:%d%% (used=%d,max=%d) ",
                (int) (percentPermMemInUse * m_percentMultiplier), memUsed, memMax);
        log.info(logString + "\r\n");
        log.info(msgString.toString() + "\r\n");
        if (((int) (percentHeapMemInUse * m_percentMultiplier)) > 80) {
            log.error("Heap memory usage is high " + memUsed, new Exception());
        }
    }
}
