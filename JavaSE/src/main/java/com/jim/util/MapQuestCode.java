package com.jim.util;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/3/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapQuestCode {
    public static Logger log = Logger.getLogger(MapQuestCode.class);
    private String city;
    private String state;
    private String county;
    private String country;
    private String zip;
    private String imageUrl;
    private String imageAltText;
    private String text;
    private String statusCode;
    private String latitude;
    private String longitude;
    private String mapUrl;

    private String getKey() {
        return "Fmjtd%7Cluub2q6b29%2C2l%3Do5-961l1w";
    }

    public MapQuestCode(String location) throws Exception {
        String geoCodeUrl = "http://www.mapquestapi.com/geocoding/first/address?key=#KEY#&callback=renderGeocode&outFormat=xml&location=";
        geoCodeUrl = geoCodeUrl.replaceAll("#KEY#", getKey());
        log.info("location = " + location);
        String url = geoCodeUrl += URLEncoder.encode(location, "UTF-8");
        log.info("url = " + url);
        URLConnection conn = new URL(url).openConnection();
        conn.connect();
        InputStream in = conn.getInputStream();
        byte[] bs = new byte[1024];
        StringBuffer sb = new StringBuffer();
        for (int len = in.read(bs); len != -1; len = in.read(bs)) {
            sb.append(new String(bs, 0, len));
        }
        in.close();

        String xml = sb.toString();
        log.info("response data = " + xml);

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new InputSource(new StringReader(xml)));

        String locationBaseExpression = "/response/info/copyright/";

        statusCode = getContent(doc, "/response/info/statusCode[1]");
        imageUrl = getContent(doc, locationBaseExpression + "imageUrl[1]");
        imageAltText = getContent(doc, locationBaseExpression + "imageAltText[1]");
        text = getContent(doc, locationBaseExpression + "text[1]");

        locationBaseExpression = "/response/results/result/locations/location[1]/";
        city = getContent(doc, locationBaseExpression + "*[@type='City'][1]");
        state = getContent(doc, locationBaseExpression + "*[@type='State'][1]");
        county = getContent(doc, locationBaseExpression + "*[@type='County'][1]");
        country = getContent(doc, locationBaseExpression + "*[@type='Country'][1]");
        zip = getContent(doc, locationBaseExpression + "postalCode[1]");

        latitude = getContent(doc, locationBaseExpression + "latLng/lat[1]");
        longitude = getContent(doc, locationBaseExpression + "latLng/lng[1]");

        mapUrl = getContent(doc, locationBaseExpression + "mapUrl[1]");

        log.info(this);
    }

    private String getContent(Document doc, String xPathExpression) throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression compile = xPath.compile(xPathExpression);
        String data = (String) compile.evaluate(doc, XPathConstants.STRING);
        return data;
    }

    public boolean isSuccess() {
        return (statusCode != null && statusCode.equalsIgnoreCase("0"));
    }

    @Override
    public String toString() {
        return "MapQuestCode{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                ", zip='" + zip + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageAltText='" + imageAltText + '\'' +
                ", text='" + text + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", mapUrl='" + mapUrl + '\'' +
                '}';
    }
}







