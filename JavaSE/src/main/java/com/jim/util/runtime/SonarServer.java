package com.jim.util.runtime;

import common.Logger;

import java.io.File;
import java.text.MessageFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/20/13
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class SonarServer extends AbstractRuntimeHelper {
    protected static final Logger log = Logger.getLogger(SonarServer.class);
    private String sonarHome;

    public void execute() throws Exception {
        String home = getSonarHome();
        if (home == null) {
            home = System.getenv("SONAR_HOME");
        }
        if (home == null) {
            throw new NullPointerException("Sonar home path is null.");
        }
        StringBuffer cmd = new StringBuffer();
        cmd.append("start");
        cmd.append(" ");
        cmd.append(getSonarStartBatFilePath());
        if (cmd == null) {
            throw new NullPointerException("Command is null.");
        }
        super.execute(cmd.toString());
    }

    public String getSonarStartBatFilePath() {
        String sonarHome = getSonarHome();
        log.debug("Sonar Home = " + sonarHome);
        if (!sonarHome.endsWith(File.separator)) {
            sonarHome += File.separator;
        }
        String filePath = MessageFormat.format("{0}bin{1}{2}{1}StartSonar.bat", sonarHome, File.separator, getOS());
        log.info("Sonar Start bat file path = " + filePath);


        return filePath;
    }

    public String getOS() {
        return "windows-x86-64";
    }

    public String getSonarHome() {
        return sonarHome;
    }

    public void setSonarHome(String sonarHome) {
        this.sonarHome = sonarHome;
    }
}
