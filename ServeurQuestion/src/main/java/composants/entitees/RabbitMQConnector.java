package composants.entitees;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class RabbitMQConnector {

	private static final String RABBIT_MQ_HOST = "localhost";
	private static final String QUEUE_NAME = "QuestionLine";
	private static final boolean durable = true;
	private Question q;

	public Question getQ() {
		return q;
	}

	public void setQ(Question q) {
		this.q = q;
	}

	public static Connection getConnection() throws IOException,
			TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(RABBIT_MQ_HOST);
		return factory.newConnection();
	}

	public static void sendQuestionToQueue(Connection connection,
			Question question) throws IOException, TimeoutException {
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		// open channel & queue
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

		// publish
		channel.basicPublish("", QUEUE_NAME,
				MessageProperties.PERSISTENT_TEXT_PLAIN,
				ow.writeValueAsBytes(question));

		// close everything
		channel.close();
	}

	public Question getLastQuestion(Connection connection) throws IOException,
			TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		// Question q = null;
		System.out.println("get last question");
		ObjectReader or = new ObjectMapper().reader(Question.class);
		Channel channel = connection.createChannel();
		channel.basicQos(1);
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		long nbMessage = channel.messageCount(QUEUE_NAME);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, consumer);
		
		System.out.println("nombre message : " + nbMessage);
		if (nbMessage >= 1) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			int n = channel.queueDeclarePassive(QUEUE_NAME).getMessageCount();
			System.out.println(n);
			if (delivery != null) {
				byte[] bs = delivery.getBody();
				System.out.println(new String(bs));
				q = (Question) or.readValue(new String(bs));

				// String message= new String(delivery.getBody());
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
				// System.out.println("[x] Received '"+message);
			} else {
				q = null;
			}
		}

		// channel.basicConsume(QUEUE_NAME, false, consumer);
		// Thread.sleep(1000);
		channel.close();

		return q;
	}
}
