package br.inatel.labs.labmqtt.client;

import java.util.Random;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SensorTemperaturaPublisherLoop {

	public static void main(String[] args) throws MqttException, InterruptedException {

		for (int i = 0; i < 10; i++) {
			
			// 1.criar o publisher
			String publisherId = UUID.randomUUID().toString();
			IMqttClient publisher = new MqttClient(MyConstants.URI_BROKER, publisherId);
			
			// 2.criar a mensagem
			MqttMessage msg = getTemperaturaSolo();
			msg.setQos(0);
			msg.setRetained(true);

			// 3.conecta
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			publisher.connect(options);

			// 4.publica
			publisher.publish(MyConstants.TOPIC_1, msg);

			// 5.Desconecta
			publisher.disconnect();
			
			Thread.sleep(2000);
		}
	}

	private static MqttMessage getTemperaturaSolo() {
		Random r = new Random();
		double temperatura = 80 + r.nextDouble() * 18.3;
		byte[] payload = String.format("T:%04.2f", temperatura).getBytes();
		return new MqttMessage(payload);
	}
}
