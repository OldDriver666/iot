package com.fise.iot.common.mqtt;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MQTTSubscribe {

	@Value("${mqtt.host}") 
	private String host;
	@Value("${mqtt.publish.topic}") 
	private String publish;
	@Value("${mqtt.subscribe.topic}")
	private String subscribe;
	@Value("${mqtt.serverid}") 
	private String serverid;
	@Value("${mqtt.clientid}") 
	private String clientid;
	@Value("${mqtt.username}") 
	private String username;
	@Value("${mqtt.password}") 
	private String password;
	@Value("${mqtt.connect.timeout}") 
	private String timeout;
	@Value("${mqtt.keep.alive}") 
	private String alive;
	@Value("${mqtt.clean.session}") 
	private String session;
	@Value("${mqtt.qos}") 
	private String qos;
	private MqttClient client;
	private MqttConnectOptions options;
	private ScheduledExecutorService scheduler;

	// 重新链接
	public void startReconnect() {
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				if (!client.isConnected()) {
					try {
						client.connect(options);
					} catch (MqttSecurityException e) {
						e.printStackTrace();
					} catch (MqttException e) {
						e.printStackTrace();
					}
				}
			}
		}, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
	}

	private void start() {
		try {
			// host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
			client = new MqttClient(host, clientid, new MemoryPersistence());
			// MQTT的连接设置
			options = new MqttConnectOptions();
			// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
			options.setCleanSession(true);
			// 设置连接的用户名
			options.setUserName(username);
			// 设置连接的密码
			options.setPassword(password.toCharArray());
			// 设置超时时间 单位为秒
			options.setConnectionTimeout(Integer.valueOf(timeout));
			// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(Integer.valueOf(alive));
			// 设置回调
			client.setCallback(new PushCallback());
			MqttTopic topic = client.getTopic(subscribe);
			// setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
			options.setWill(topic, "close".getBytes(), 0, true);

			client.connect(options);
			// 订阅消息
			int[] Qos = { Integer.valueOf(qos) };
			String[] topic1 = { subscribe };
			client.subscribe(topic1, Qos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws MqttException {
		MQTTSubscribe client = new MQTTSubscribe();
		client.start();
	}
}
