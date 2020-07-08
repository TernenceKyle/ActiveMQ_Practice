package Domain;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Subscriber1 {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection conn = null;
        Session session = null;
        Destination destination;
        MessageConsumer subscriber;
        try {
            conn = factory.createConnection();
            conn.start();
            session = conn.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("News");
            subscriber = session.createConsumer(destination);
            subscriber.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage tm = (TextMessage) message;
                    try {
                        System.out.println(tm.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
