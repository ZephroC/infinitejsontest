package net.geekemporium.infinijson;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by Iain Walsh on 14/09/15.
 */
public class Event {

    public int hour,minute;
    public String date;
    public String uri;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
