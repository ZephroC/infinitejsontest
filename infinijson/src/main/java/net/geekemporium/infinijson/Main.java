package net.geekemporium.infinijson;

import java.io.IOException;

/**
 * Created by Iain Walsh on 14/09/15.
 */
public class Main {

    public static void main(String[] args) {
        DataSource source = new DataSource();
        DataConsumer consumer = new DataConsumer();
        try {
            consumer.setInputStream(source.getInputStream());
            (new Thread(source)).start();
            (new Thread(consumer)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
