package com.jim.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/31/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class RuleTester {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        File createdFolder = folder.newFolder("newfolder");
        File createdFile = folder.newFile("myfilefile.txt");
        File root = folder.getRoot();
        System.out.println("root.getAbsolutePath() = " + root.getAbsolutePath());
        assertTrue(createdFolder.exists());
        assertTrue(createdFile.exists());
    }
}
