package composants.entitees;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMQConnector {

	private static final String RABBIT_MQ_HOST = "localhost";
	private static final String QUEUE_NAME = "QuestionLine";
	private static final boolean durable = true;

	public static Connection getConnection() throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(RABBIT_MQ_HOST);
		return factory.newConnection();
	}
	
	public static void sendQuestionToQueue(Connection connection, Question question) throws IOException, TimeoutException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		// open channel & queue
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		
		// publish
		channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
				ow.writeValueAsBytes(question));
		
		// close everything
		channel.close();
		connection.close();
	}
}
