import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Client {
    private final static String QUEUE_NAME = "numbers";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for(int i=1; i<=50; i++) {
            String message = Integer.toString(i);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("Sent '" + message + "'");
            Thread.sleep(1000);
        }
        channel.close();
        connection.close();
    }
}
