import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CurrentLinkQueueTest  implements Runnable {

    private ThreadPoolExecutor offMessagePushExecutor = null;
    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue();


    public boolean initialize() {
        this.offMessagePushExecutor = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(), 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(),
                DefaultThreadFactory.getInstance("offMessagePush-group"), new ThreadPoolExecutor.DiscardOldestPolicy());
        this.offMessagePushExecutor.submit(this);
        return false;
    }



    public static void main(String[] args) throws InterruptedException {
        CurrentLinkQueueTest currentLinkQueueTest = new CurrentLinkQueueTest();
        currentLinkQueueTest.initialize();
        for (int i = 1; i <= 20; i++) {
            Thread.sleep(1000l);
            currentLinkQueueTest.addMessage(i);
        }
        Thread.sleep(1000l);
        for (int i = 1; i <=5; i++) {
            currentLinkQueueTest.addMessage(i);
        }


    }


    public void addMessage(Integer num) {
            this.queue.offer(num);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Set<Integer> messages = null;
                int count = 0;
                messages = new HashSet();
                Integer nums;
                System.out.println("队列大小===》"+this.queue.size());
                if (null != (nums = (Integer)this.queue.take())) {
                    System.out.println(nums);
                    messages.add(nums);
                    ++count;
                }
                while(true) {
                    if ((nums = (Integer)this.queue.poll()) != null) {
                        System.out.println("队列大小1===》"+this.queue.size());
                        System.out.println(nums);
                        if (null != nums) {
                            messages.add(nums);
                            ++count;
                        }
                        if (20 >= count) {
                            continue;
                        }
                        count = 0;
                    }
                    if (0 < count && !messages.isEmpty()) {
                        System.out.println("ssss");
                    }
                    break;
                }
            } catch (Exception var5) {
            }
        }



    }
}
