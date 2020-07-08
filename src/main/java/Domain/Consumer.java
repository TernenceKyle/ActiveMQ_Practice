package Domain;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        Connection connection = null;
        Session session = null;
        Destination destination;
        MessageConsumer consumer = null;
        try {
             connection = factory.createConnection();
             connection.start();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("Mail Send");
            consumer = session.createConsumer(destination);
            for (int i = 0; i < 2; i++) {
                Message receive = consumer.receive();
            }
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            consumer.close();
            session.close();
            connection.close();
        }
    }
}
