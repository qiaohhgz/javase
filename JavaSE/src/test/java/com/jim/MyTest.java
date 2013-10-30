package com.jim;

import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/8/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyTest {
    List<String> array = new ArrayList<String>();
    Logger logger = Logger.getLogger(MyTest.class);
    @Before
    public void setUP() {
        array.clear();
        array.add("Cleaning	Fire Damage");//1
        array.add("Cleaning	Flood Damage");//2
        array.add("Cleaning	Water Damage");//3
        array.add("Cleaning	Mold and Mildew Removal");//4
        array.add("Contractors Other	Waterproofing Contractors");//5
        array.add("Contractors Plumbers	Plumber");//6
        array.add("Contractors Roofers	Roofing Contractors");//7
        array.add("Heating & Air Conditioning	Air Conditioning");//8
        array.add("Heating & Air Conditioning	General HVAC");//9
        array.add("Insurance	Insurance");//10
        array.add("Professional Other	Financial Services");//11
        array.add("Professional Other	Accountants");//12
        array.add("Real Estate	Mortgage");//13
        array.add("Cleaning	Carpet Cleaning");//14
        array.add("Contractors Electricians	Electrical Contractors");//15
        array.add("Contractors Exterior	Siding Contractors");//16
        array.add("Contractors Other	Garages");//17
        array.add("Doctors	Chiropractor");//18
        array.add("Locksmiths	Locksmith");//19
        array.add("Moving & Storage	Moving Companies");//20
        array.add("Moving & Storage	Movers");//21
        array.add("Pest Control	Exterminator");//22
        array.add("Legal Other	Bail Bonds");//23
    }

    @Test
    public void test1() {
        String baseSql = "SELECT top 1 h.HeadingId,v.Name verticalName,h.Name headingName,s.Name serviceName " +
                "FROM wr2.[Service] s " +
                "join wr2.Heading h on s.HeadingID = h.HeadingID " +
                "join wr2.Vertical v on h.VerticalID = v.VerticalID " +
                "where h.Name = '#WHERE#'\ngo";
        for (String s : array) {
            logger.debug(baseSql.replaceAll("#WHERE#", s.replaceAll("\t", " - ")));
            logger.info("printList '" + s + "'\n");
        }
    }

    @Test
    public void test2() {
        int row = 0;
        String baseSql = "SELECT top 1 h.HeadingId,v.Name verticalName,h.Name headingName,s.Name serviceName " +
                "FROM wr2.[Service] s " +
                "join wr2.Heading h on s.HeadingID = h.HeadingID " +
                "join wr2.Vertical v on h.VerticalID = v.VerticalID " +
                "where h.Name = '#WHERE#'";
        for (String s : array) {
            row++;
            System.out.println(baseSql.replaceAll("#WHERE#", s.replaceAll("\t", " - ")));
            System.out.println("union");
        }
        System.out.println("row = " + row);
    }

    @Test
    public void test3() {
        System.out.println("use KeywordToolInsertion\n" +
                "go");
        String baseSql = "SELECT top 1 '#A#' as VerticalAndCategory,h.HeadingId,h.Name headingName " +
                "FROM wr2.Heading h " +
                "where h.Name = '#A#'";

        for (String s : array) {
            s = s.replaceAll("\t", " - ");
            System.out.println(baseSql.replaceAll("#A#", s));
        }
        System.out.println("go");
    }

    @Test
    public void testMapGetNull() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("City", 1);
        map.put("State", 2);
        map.put("Country", 3);
        boolean a = map.containsKey(null);
        System.out.println("a = " + a);
        boolean b = map.containsKey("");
        System.out.println("b = " + b);
    }

    @Test
    public void testStringFormat() throws Exception {
        String value = String.format("%s", null, null);
        assertEquals(value, null);
    }

    @Test
    public void testMathRound() throws Exception {
        //TODO MathRound
        assertEquals(Math.round(2.5), 3);
        assertEquals(Math.round(-2.5), -2);
    }

    @Test
    @Ignore
    public void testName() throws Exception {
        Gson gson = new Gson();
        String url = "http://localhost:8081/services/hello/json";
        PostMethod postMethod = new PostMethod(url);
        try {
//            RequestEntity requestEntity = new StringRequestEntity("{result:200}");
//            postMethod.setRequestEntity(requestEntity);
            postMethod.setRequestHeader("Content-Type", "application/json;charset=utf-8");
            HttpClient httpclient = new HttpClient();
            int result = httpclient.executeMethod(postMethod);
            if (result == HttpStatus.SC_OK) {
                InputStream xmlStream = postMethod.getResponseBodyAsStream();
                String json = postMethod.getResponseBodyAsString();
                System.out.println("json = " + json);
                InputStreamReader reader = new InputStreamReader(xmlStream);
                BufferedReader bf = new BufferedReader(reader);
                while (bf.ready()) {
                    System.out.println(bf.readLine());
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
