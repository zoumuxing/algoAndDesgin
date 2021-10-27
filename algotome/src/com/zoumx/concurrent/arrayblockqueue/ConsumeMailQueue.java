package com.zoumx.concurrent.arrayblockqueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;


public class ConsumeMailQueue {
    private static final ILoggerFactory logger = (ILoggerFactory) LoggerFactory.getLogger(ConsumeMailQueue.class);

    IMailService mailService;
    
    @PostConstruct
    public void startThread() {
        ExecutorService e = Executors.newFixedThreadPool(2);// 两个大小的固定线程池
        e.submit(new PollMail(mailService));
        e.submit(new PollMail(mailService));
    }

    class PollMail implements Runnable {
        IMailService mailService;

        public PollMail(IMailService mailService) {
            this.mailService = mailService;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Email mail = MailQueue.getMailQueue().consume();
                    if (mail != null) {
                        //logger.info("剩余邮件总数:{}",MailQueue.getMailQueue().size());
                        mailService.send(mail);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @PreDestroy
    public void stopThread() {
        //logger.info("destroy");
    }
}