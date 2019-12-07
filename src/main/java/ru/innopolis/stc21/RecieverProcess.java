package ru.innopolis.stc21;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.innopolis.stc21.med.service.EntityForJSONofNN;

import javax.sql.DataSource;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class RecieverProcess {
    @Autowired
    private Environment env;

    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-174-129-254-235.compute-1.amazonaws.com:5432/df45v8tae7v2n4");
        dataSource.setUsername("bcrdfyygoqyztw");
        dataSource.setPassword("09ff62cf764c10ddfd021aac651e301f32645e4365cb1caf5a4f958b18fd58ac");
        return dataSource;
    }

    public void putToBase(String message) throws SQLException, IOException {
        Reader reader = new StringReader(message);
        ObjectMapper mapper = new ObjectMapper();
        EntityForJSONofNN entityForJSON = mapper.readValue(reader, EntityForJSONofNN.class);

        Long id;
        String result;
        String accuracy;

        id = entityForJSON.getId();
        result = entityForJSON.getResult();
        accuracy = entityForJSON.getAccuracy();

        try (java.sql.Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medical_history SET neiro_diagtose=?, accuracy=? WHERE id=?")) {
                preparedStatement.setString(1, result);
                preparedStatement.setString(2, accuracy);
                preparedStatement.setLong(3, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public   RecieverProcess() throws IOException, TimeoutException {
      /*  try (InputStream input = new FileInputStream("path/to/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));*/

        String QUEUE_NAME = "tojava";
        ConnectionFactory factory = new ConnectionFactory();

     /*   factory.setUsername(env.getProperty("rabbit.user"));
        factory.setPassword(env.getProperty("rabbit.password"));
        factory.setHost(env.getProperty("rabbit.host"));
        factory.setPort(Integer.parseInt(env.getProperty("rabbit.port")));
        factory.setVirtualHost(env.getProperty("rabbit.virtualHost"));*/

        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("176.99.11.75");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        final String[] message = new String[1];
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                message[0] = new String(delivery.getBody(), "UTF-8");
                try {
                    putToBase(message[0]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Received ".concat(message[0]));
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });
        //return message[0];
    }
}
