package com.example.commerce.catalog.core.infrastructure.integration;

import java.net.Socket;

import org.springframework.kafka.test.rule.KafkaEmbedded;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IntegratedKafkaServer {

	private static final int KAFKA_SERVER_PORT = 9092;
	
	public static void startIfNoServerIsRunning() {
		if(!serverListening("localhost", KAFKA_SERVER_PORT)) {
			KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "test");
			embeddedKafka.setKafkaPorts(KAFKA_SERVER_PORT);
			try {
				embeddedKafka.before();
				log.info("Server started on port " + KAFKA_SERVER_PORT);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			log.info("Server is already running");
		}
	}
	
	private static boolean serverListening(String host, int port)
	{
	    Socket s = null;
	    try
	    {
	        s = new Socket(host, port);
	        return true;
	    }
	    catch (Exception e)
	    {
	        return false;
	    }
	    finally
	    {
	        if(s != null)
	            try {s.close();}
	            catch(Exception e){}
	    }
	}
}
