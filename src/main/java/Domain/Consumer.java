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
//                TextMessage receive = (TextMessage)consumer.receive();
//                System.out.println(receive.getText());
                //第二种方式 MQListener
                consumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        TextMessage tm = (TextMessage)message;
                        try {
                            System.out.println(tm.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
//            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
//            consumer.close();
//            session.close();
//            connection.close();
        }
    }
}
