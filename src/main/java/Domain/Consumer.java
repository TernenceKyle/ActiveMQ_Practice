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
             //不要忘记 消费接收需要调用Connection.Start()
             connection.start();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("Mail Send");
            consumer = session.createConsumer(destination);
            for (int i = 0; i < 2; i++) {
                TextMessage receive = (TextMessage)consumer.receive();
                System.out.println(receive.getText());
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
