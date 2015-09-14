package net.geekemporium.infinijson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.*;

/**
 * Created by Iain Walsh on 14/09/15.
 */
public class DataConsumer implements Runnable {

    private InputStream input = null;

    public void setInputStream(InputStream input) {
        this.input = input;
    }

    private JsonFactory jsonFactory;

    public void run() {
        if(input==null)
            return;
        jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            JsonParser parser = jsonFactory.createParser(input);
            if(parser.nextToken() == JsonToken.START_OBJECT &&
                    parser.nextToken() ==JsonToken.FIELD_NAME &&
                parser.nextToken()==JsonToken.START_ARRAY) {

                    while(parser.nextToken() == JsonToken.START_OBJECT) {
                        Event event = objectMapper.readValue(parser, Event.class);
                        System.out.println(event);
                    }
                parser.nextToken();
                parser.nextToken();
            }
            else {
                System.out.println("Wrong order!");
            }
            input.close();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//        char[] line = new char[64];
//        int read;
//            while( (read = reader.read(line)) != -1) {
//                 System.out.println(new String(line, 0, read));
//            }
//            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
