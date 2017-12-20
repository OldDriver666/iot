package mqtt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fise.iot.Application;
import com.fise.iot.common.mqtt.MQTTPublish;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestMQTT {

	@Autowired
	MQTTPublish mqttPublish;
	
	@Test
	public void publish() throws Exception{
		String topicUrl = "test/";
	    String content = "test-message111";
	    Integer qos = 1;
	    mqttPublish.publishMessage(topicUrl, content, qos);
	}
	
//	@Test
//	public void subscribe() throws Exception{
//		String topicUrl = "test/";
//	    String content = "test-message";
//	    Integer qos = 1;
//	    mqttPublish.publishMessage(topicUrl, content, qos);
//	}
}
