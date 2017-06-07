package composants.entitees;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

public class RabbitMQConnector {

	private static final String RABBIT_MQ_HOST = "rabbitmq";
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
		connection.close();
	}

	public Question getLastQuestion(Connection connection) throws IOException,
			TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		System.out.println("get last question");
		ObjectReader or = new ObjectMapper().reader(Question.class);
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);

		channel.basicQos(1);
		GetResponse response = channel.basicGet(QUEUE_NAME,true);
		String result = null;
		if(response != null)
			 result = new String(response.getBody());

		if(result != null){
			q = or.readValue(result);
		}
		else
			q = null;

		channel.close();
		connection.close();

		return q;
	}

}
