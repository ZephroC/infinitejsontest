package net.geekemporium.infinijson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Random;

/**
 * Created by Iain Walsh on 14/09/15.
 */
public class DataSource implements Runnable {

    private PipedOutputStream outputStream;
    private PipedInputStream inputStream;
    private ObjectMapper objectMapper;

    public InputStream getInputStream() throws IOException {
        outputStream = new PipedOutputStream();
        inputStream = new PipedInputStream(outputStream,156);
        return inputStream;
    }

    public void run() {
        int numberToMake = 100;
        Random random = new Random();

        objectMapper = new ObjectMapper();
        try {
            outputStream.write("{\"results\":[".getBytes());
            for(int i = 0; i < numberToMake;i++) {
                Event event = new Event();
                event.hour = i % 12;
                event.minute = i % 60;
                event.uri = "http://geekemporium.net/article" + i;
                event.date = "2015/09/14";
                StringBuilder eventStr = new StringBuilder();
                eventStr.append(objectMapper.writeValueAsString(event));
                //System.out.println(eventStr);
                if(i+1 < numberToMake)
                    eventStr.append(",");
                outputStream.write(eventStr.toString().getBytes());
                try {
                    Thread.sleep(100* (random.nextInt(10)+1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            outputStream.write("]}".getBytes());
            outputStream.close();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
