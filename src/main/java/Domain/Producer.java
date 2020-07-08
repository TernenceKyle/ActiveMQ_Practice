package Domain;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory;
        Connection conn = null;
        Session session = null;
        Destination destination;
        MessageProducer producer;
        try{
            factory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
            conn = factory.createConnection();
            session = conn.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("Mail Send");
            producer = session.createProducer(destination);
            for (int i = 0; i < 5; i++) {
                producer.send(destination,session.createTextMessage("Message - "+i+" : OJBK"));
            }
            session.commit();
        }catch (Exception e)
        {

        }finally {
            session.close();
            conn.close();
        }
    }
}
