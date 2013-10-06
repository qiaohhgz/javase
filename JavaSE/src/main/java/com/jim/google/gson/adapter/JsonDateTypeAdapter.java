package com.jim.google.gson.adapter;

import com.google.gson.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/28/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private static final Logger log = Logger.getLogger(JsonDateTypeAdapter.class);
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private DateFormat format = new SimpleDateFormat();

    public JsonDateTypeAdapter(String pattern) {
        this.format = new SimpleDateFormat(pattern);
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        log.debug("json = " + json);
        log.debug("typeOfT = " + typeOfT);
        log.debug("context = " + context);
        try {
            return format.parse(json.getAsString());
        } catch (ParseException e) {
            log.error("Error while JsonDateTypeAdapter deSerialize method.", e);
            return null;
        }
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        log.debug("src = " + src);
        log.debug("context = " + context);
        String dateString = format.format(src);
        log.debug("dateString = " + dateString);
        return new JsonPrimitive(dateString);
    }
}
