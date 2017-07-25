package com.kupa.hotel.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import java.util.Random;

/**
 * Created by Mita on 2017/6/15.
 */

public class Thermometer {

    public static final String BROKER_URL = "tcp://192.168.0.103:1883";

    public static final String TOPIC = "do/kupa/message";

    private MqttClient client;


    public Thermometer() {
        try {
            client = new MqttClient(BROKER_URL, MqttClient.generateClientId());
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void start() {

        try {
            client.connect();

            publishTemperature();

            client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void publishTemperature() throws MqttException {
        final MqttTopic temperatureTopic = client.getTopic(TOPIC);

        final int temperature = createRandomNumberBetween(-20, 4);

        final MqttMessage message = new MqttMessage(String.valueOf(temperature).getBytes());
        temperatureTopic.publish(message);

        System.out.println("Published data. Topic: " + temperatureTopic.getName() + "  Message: " + temperature);
    }

    public static int createRandomNumberBetween(int min, int max) {

        return new Random().nextInt(max - min + 1) + min;
    }

    public static void main(String... args) {
        final Thermometer thermometer = new Thermometer();
        thermometer.start();
    }
}
