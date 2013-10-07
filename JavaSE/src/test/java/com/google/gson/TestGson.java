package com.google.gson;

import com.jim.bean.PersonBean;
import com.jim.bean.PersonTableBean;
import com.jim.google.gson.adapter.JsonDateTypeAdapter;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/28/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestGson {

    @Test
    public void testDateToJson() throws Exception {
        String longDate = new GsonBuilder().registerTypeAdapter(Date.class,
                new JsonDateTypeAdapter(JsonDateTypeAdapter.DEFAULT_FORMAT)).create().toJson(new Date());
        System.out.println(longDate);
    }

    @Test
    public void testDateFromJson() throws Exception {
        String dateString = "2013-08-28 14:15:42";
        DateFormat format = new SimpleDateFormat(JsonDateTypeAdapter.DEFAULT_FORMAT);
        System.out.println(format);
        String json = "{id:1000,name:\"zhang\",birthday:\"" + dateString + "\"}";
        PersonBean personBean = new GsonBuilder().registerTypeAdapter(Date.class,
                new JsonDateTypeAdapter(JsonDateTypeAdapter.DEFAULT_FORMAT)).create().fromJson(json, PersonBean.class);
        assertNotNull(personBean);
        assertEquals(personBean.getId(), 1000);
        assertEquals(personBean.getName(), "zhang");
        assertEquals(personBean.getBirthday(), format.parse(dateString));
    }

    @Test
    public void testSetType() throws Exception {
        String data = "{personBeans:[" +
                "{id:\"1000\",name:\"zhang\",birthday:\"2013-08-28 14:15:42\"}," +
                "{id:\"2000\",name:\"li\",birthday:\"2013-08-28 20:15:42\"}" +
                "]}";
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Date.class,
                new JsonDateTypeAdapter(JsonDateTypeAdapter.DEFAULT_FORMAT));
        Gson gson = builder.create();
        PersonTableBean tableBean = gson.fromJson(data, PersonTableBean.class);
        assertNotNull(tableBean);

        HashSet<PersonBean> personBeans = tableBean.getPersonBeans();
        Iterator<PersonBean> iterator = personBeans.iterator();
        PersonBean zhang = iterator.next();
        assertEquals(zhang.getId(), 1000);
        assertEquals(zhang.getName(), "zhang");

        PersonBean li = iterator.next();
        assertEquals(li.getId(), 2000);
        assertEquals(li.getName(), "li");
        System.out.println("Hello");
    }

    @Test
    public void testMapType() throws Exception {
        String data = "{personBeans:[id:\"1000\",name:\"zhang\",birthday:\"Wed Aug 28 11:10:37 PDT 2013\"]}";
        Gson gson = new GsonBuilder().create();
        PersonTableBean tableBean = gson.fromJson(data, PersonTableBean.class);
        assertNotNull(tableBean);
        System.out.println("hello");
    }
}
