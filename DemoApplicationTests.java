package com.example.demo;
import com.example.producer.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private Producer producer;

    @Test
	public void contextLoads() {
		final Destination destination = new ActiveMQQueue("myqueue");
		final long timeInterval = 3000;
		Runnable runnable = () -> {
            while (true){

                producer.sendMessage(destination, "current time is :" + new Date());
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
		Thread thread = new Thread(runnable);
		thread.run();
	}

}
