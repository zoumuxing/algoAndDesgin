import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadFactory implements ThreadFactory {

    private static Map<String, DefaultThreadFactory> mapOfNameAndThreadFactory = new HashMap();
    private static Map<String, AtomicInteger> mapOfNameAndAtomicInteger = new HashMap();
    private String threadPoolName = null;
    private int priority = 5;

    public static DefaultThreadFactory getInstance(String threadName) {
        return getInstance(threadName, 5);
    }

    public static DefaultThreadFactory getInstance(String threadName, Integer priority) {
        DefaultThreadFactory defaultThreadFactory = (DefaultThreadFactory)mapOfNameAndThreadFactory.get(threadName);
        if (defaultThreadFactory == null) {
            defaultThreadFactory = new DefaultThreadFactory();
            if (priority != null) {
                defaultThreadFactory.priority = priority;
            }

            defaultThreadFactory.setThreadName(threadName);
            mapOfNameAndThreadFactory.put(threadName, defaultThreadFactory);
            mapOfNameAndAtomicInteger.put(threadName, new AtomicInteger());
        }

        return defaultThreadFactory;
    }

    private DefaultThreadFactory() {
    }

    public String getThreadPoolName() {
        return this.threadPoolName;
    }

    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(this.getThreadPoolName() + "-" + ((AtomicInteger)mapOfNameAndAtomicInteger.get(this.getThreadPoolName())).incrementAndGet());
        thread.setPriority(this.priority);
        return thread;
    }

    public void setThreadName(String threadName) {
        this.threadPoolName = threadName;
    }




}
