package com.jim.download.v4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/5/13
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadWithBreakPoint extends AbstractDownloadWithBreakPointTemplate {

    @Override
    protected void printHeader(Map<String, List<String>> headerFields) {
        logger.info("========== Response Headers ==========");
        for (String key : headerFields.keySet()) {
            List<String> values = headerFields.get(key);
            String[] strings = values.toArray(new String[values.size()]);
            String value = Arrays.toString(strings);
            logger.info(String.format("key = %s value = %s", key, value));
        }
        logger.info("======================================");
    }
}
