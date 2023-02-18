package xyz.gabear.learn.javase;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 主要是ManagementFactory的使用，用来监控JVM等
 */
public class JMXDemo {
    public static void main(String[] args) {
        JMXDemo task = new JMXDemo();
        String threadInfo = task.buildThreadInfo();
        System.out.println(threadInfo);
    }


    private String buildThreadInfo() {
        // 获取线程信息
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        bean.setThreadContentionMonitoringEnabled(true);
        long startTime = System.currentTimeMillis();
        ThreadInfo[] threads = bean.dumpAllThreads(false, false);
        long endTime = System.currentTimeMillis();
        System.out.println("dumpAllThreads time:" + (endTime - startTime));

        long totalStartedThreadCount = bean.getTotalStartedThreadCount();

        int newThreadCount = 0;
        int runnableThreadCount = 0;
        int blockedThreadCount = 0;
        int waitingThreadCount = 0;
        int timedWaitingThreadCount = 0;
        int terminatedThreadCount = 0;
        if (threads != null) {
            for (ThreadInfo threadInfo : threads) {
                if (threadInfo != null) {
                    switch (threadInfo.getThreadState()) {
                        case NEW:
                            newThreadCount++;
                            break;
                        case RUNNABLE:
                            runnableThreadCount++;
                            break;
                        case BLOCKED:
                            blockedThreadCount++;
                            break;
                        case WAITING:
                            waitingThreadCount++;
                            break;
                        case TIMED_WAITING:
                            timedWaitingThreadCount++;
                            break;
                        case TERMINATED:
                            terminatedThreadCount++;
                            break;
                        default:
                            break;
                    }
                } else {
                    terminatedThreadCount++;
                }
            }
        }
        int jBossThreadsCount = countThreadsByPrefix(threads, "http-", "catalina-exec-");
        int jettyThreadCount = countThreadsBySubstring(threads, "qtp");
        int httpThreadCount = jBossThreadsCount + jettyThreadCount;
        int amqpThreadCount = countThreadsByPrefix(threads, "AMQP");
        int zkThreadCount = countThreadsByPrefix(threads, "Curator-", "main-EventThread", "main-SendThread");
        int zebraThreadCount = countThreadsByPrefix(threads, "Dal-", "Zebra-");
        int hystrixThreadCount = countThreadsByPrefix(threads, "hystrix-");
        int nettyThreadCount = countThreadsByPrefix(threads, "New I/O");
        int catThreadCount = countThreadsByPrefix(threads, "cat-");
        int dubboClientThreadCount = countThreadsByPrefix(threads, "DubboClientHandler-");
        int dubboServerThreadCount = countThreadsByPrefix(threads, "DubboServerHandler-");
        int dubboSharedThreadCount = countThreadsByPrefix(threads, "DubboSharedHandler");
        int tigerThreadCount = countThreadsByPrefix(threads, "Tiger");
        return getThreadDump(threads);
    }

    private void buildMemoryInfo() {
        // 获取内存信息
        MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = bean.getHeapMemoryUsage();
        heapMemoryUsage.getUsed();
        heapMemoryUsage.getMax();
        MemoryUsage nonHeapMemoryUsage = bean.getNonHeapMemoryUsage();
        nonHeapMemoryUsage.getUsed();
        nonHeapMemoryUsage.getMax();


        for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            String name = removeSpace(memoryPoolMXBean.getName());
            MemoryUsage memoryUsage = memoryPoolMXBean.getUsage();
            long max = memoryUsage.getMax();
            long used = memoryUsage.getUsed();
        }

        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        heapMemoryUsage.getMax();
        heapMemoryUsage.getInit();
        heapMemoryUsage.getCommitted();
        heapMemoryUsage.getUsed();
        nonHeapMemoryUsage.getMax();
        nonHeapMemoryUsage.getInit();
        nonHeapMemoryUsage.getCommitted();
        nonHeapMemoryUsage.getUsed();

        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        getNioDirectBufferPoolUsed(mbeanServer);
        getNioMappedBufferPoolUsed(mbeanServer);

        // 获取加载的类的信息
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        classLoadingMXBean.getLoadedClassCount();
        classLoadingMXBean.getTotalLoadedClassCount();
        classLoadingMXBean.getUnloadedClassCount();

    }


    private static final String NIO_DIRECT_BUFFER_POOL_MBEAN = "java.nio:type=BufferPool,name=direct";
    private static final String NIO_MAPPED_BUFFER_POOL_MBEAN = "java.nio:type=BufferPool,name=mapped";

    private long getNioDirectBufferPoolUsed(MBeanServer mbeanServer) {
        long directBufferSize = 0;
        try {
            ObjectName directPool = new ObjectName(NIO_DIRECT_BUFFER_POOL_MBEAN);
            directBufferSize = (Long) mbeanServer.getAttribute(directPool, "MemoryUsed");
        } catch (Exception ex) {
        }
        return directBufferSize;
    }

    private long getNioMappedBufferPoolUsed(MBeanServer mbeanServer) {
        long mappedBufferSize = 0;
        try {
            ObjectName directPool = new ObjectName(NIO_MAPPED_BUFFER_POOL_MBEAN);
            mappedBufferSize = (Long) mbeanServer.getAttribute(directPool, "MemoryUsed");
        } catch (Exception ex) {
        }
        return mappedBufferSize;
    }


    private String removeSpace(String name) {
        return name.replaceAll(" ", "");
    }

    private int countThreadsByPrefix(ThreadInfo[] threads, String... prefixes) {
        int count = 0;
        for (ThreadInfo thread : threads) {
            for (String prefix : prefixes) {
                if (thread.getThreadName().toLowerCase().startsWith(prefix.toLowerCase())) {
                    ++count;
                }
            }
        }
        return count;
    }

    private int countThreadsBySubstring(ThreadInfo[] threads, String... substrings) {
        int count = 0;
        for (ThreadInfo thread : threads) {
            for (String str : substrings) {
                if (thread.getThreadName().toLowerCase().contains(str.toLowerCase())) {
                    count++;
                }
            }
        }
        return count;
    }

    private String getThreadDump(ThreadInfo[] threads) {
        StringBuilder sb = new StringBuilder(32768);
        int idx = 1;
        Arrays.sort(threads, Comparator.comparing(ThreadInfo::getThreadName));
        for (ThreadInfo thread : threads) {
            sb.append(idx++).append(": ").append(parseThreadInfo(thread));
        }
        return sb.toString();
    }

    private String parseThreadInfo(ThreadInfo threadInfo) {
        StringBuilder sb = new StringBuilder("\"" + threadInfo.getThreadName() + "\"" +
                " Id=" + threadInfo.getThreadId() + " " +
                threadInfo.getThreadState());
        if (threadInfo.getLockName() != null) {
            sb.append(" on " + threadInfo.getLockName());
        }
        if (threadInfo.getLockOwnerName() != null) {
            sb.append(" owned by \"" + threadInfo.getLockOwnerName() +
                    "\" Id=" + threadInfo.getLockOwnerId());
        }
        if (threadInfo.isSuspended()) {
            sb.append(" (suspended)");
        }
        if (threadInfo.isInNative()) {
            sb.append(" (in native)");
        }
        sb.append('\n');
        int i = 0;
        StackTraceElement[] stackTrace = threadInfo.getStackTrace();
        for (; i < stackTrace.length; i++) {
            StackTraceElement ste = stackTrace[i];
            sb.append("\tat " + ste.toString());
            sb.append('\n');
            if (i == 0 && threadInfo.getLockInfo() != null) {
                Thread.State ts = threadInfo.getThreadState();
                switch (ts) {
                    case BLOCKED:
                        sb.append("\t-  blocked on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    case WAITING:
                        sb.append("\t-  waiting on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    case TIMED_WAITING:
                        sb.append("\t-  waiting on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    default:
                }
            }
            for (MonitorInfo mi : threadInfo.getLockedMonitors()) {
                if (mi.getLockedStackDepth() == i) {
                    sb.append("\t-  locked " + mi);
                    sb.append('\n');
                }
            }
        }
        if (i < stackTrace.length) {
            sb.append("\t...");
            sb.append('\n');
        }

        LockInfo[] locks = threadInfo.getLockedSynchronizers();
        if (locks.length > 0) {
            sb.append("\n\tNumber of locked synchronizers = " + locks.length);
            sb.append('\n');
            for (LockInfo li : locks) {
                sb.append("\t- " + li);
                sb.append('\n');
            }
        }

        sb.append('\n');
        return sb.toString();
    }

}
