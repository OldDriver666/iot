package com.fise.iot.common.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MQTTPublish {

	@Value("${mqtt.host}") 
	private String host;
	@Value("${mqtt.publish.topic}") 
	private String publish;
	@Value("${mqtt.serverid}") 
	private String serverid;
	@Value("${mqtt.username}") 
	private String username;
	@Value("${mqtt.password}") 
	private String password;
	@Value("${mqtt.connect.timeout}") 
	private String timeout;
	@Value("${mqtt.keep.alive}") 
	private String alive;


	public void publishMessage(String topicUrl, String content, Integer qos) throws MqttException {
		MqttClient client = new MqttClient(host, serverid, new MemoryPersistence());
		MqttTopic topic = null;
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
		options.setUserName(username);
		options.setPassword(password.toCharArray());
		// 设置超时时间
		options.setConnectionTimeout(Integer.valueOf(timeout));
		// 设置会话心跳时间
		options.setKeepAliveInterval(Integer.valueOf(alive));
		try {
			client.setCallback(new PushCallback());
			client.connect(options);
			topic = client.getTopic(publish+topicUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MqttMessage message = new MqttMessage();
		message.setQos(qos);
		message.setRetained(true);
		message.setPayload(content.getBytes());
		MqttDeliveryToken token = topic.publish(message);
		token.waitForCompletion();
	}

}
