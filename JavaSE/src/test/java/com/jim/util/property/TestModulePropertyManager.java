package com.jim.util.property;

import com.jim.util.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/25/13
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestModulePropertyManager {
    @Test
    public void testWork() throws Exception {
        ModulePropertyManager manager = new ModulePropertyManager();
        manager.work("src/test/resources", "stg");
    }

    @Test
    public void testReName() throws Exception {
        ModulePropertyManager manager = new ModulePropertyManager();
        manager.rename("src/test/resources");
    }

    @Test
    public void testWorkAndReName() throws Exception {
        ModulePropertyManager manager = new ModulePropertyManager();
        manager.work("src/test/resources", "stg");
        manager.rename("src/test/resources");
    }

    @Test
    public void testBuildDynamicProperties() throws Exception {
        File file = new File(FileUtils.getFileByClassLoader("salesportaldb.properties"));
        ModulePropertyManager.buildDynamicProperties(file);
    }
}
