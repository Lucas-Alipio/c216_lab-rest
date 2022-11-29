package br.inatel.labs.labmqtt.client;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class SensorTemperaturaSubscriber {
	
	public static void main(String[] args) throws MqttException {
		
		//1. Cria o subscriber
		String subscriberId = UUID.randomUUID().toString();
		IMqttClient subscriber = new MqttClient( MyConstants.URI_BROKER, subscriberId );
		
		//2. Connecta
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		subscriber.connect(options);
		
		//3. recebe mensagem
		System.out.println("Recebendo msg...");
		subscriber.subscribe(MyConstants.TOPIC_1, (topic, msg) -> {
			byte[] payload = msg.getPayload();
			System.out.println("Payload Recebido: " + new String(payload, "UTF-8"));
			System.out.println("Topico Recebido: " + topic);
		});
		
		//4. desconecta
		//subscriber.disconnect();
	}
}
