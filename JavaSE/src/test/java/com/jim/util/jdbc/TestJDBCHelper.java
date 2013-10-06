package com.jim.util.jdbc;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/18/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestJDBCHelper {

    public static final Logger log = Logger.getLogger(TestJDBCHelper.class);

    @Test
    public void testOpenConnection() throws Exception {
        log.info("test open connection 100");

        JDBCHelper.testConnection("net.sourceforge.jtds.jdbc.Driver",
                "jdbc:jtds:sqlserver://localhost:1433;databaseName=dbunit", "sa", "D7#hyron");

        log.info("test open connection 200");

        JDBCHelper.testConnection("net.sourceforge.jtds.jdbc.Driver",
                "jdbc:jtds:sqlserver://172.20.230.90:1433;databaseName=KeywordToolInsertion", "sa", "201209#WRDB");

        log.info("test open connection 300");
    }
}
