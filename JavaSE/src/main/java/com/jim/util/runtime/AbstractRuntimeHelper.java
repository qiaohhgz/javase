package com.jim.util.runtime;

import common.Logger;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/20/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractRuntimeHelper {
    protected static final Logger log = Logger.getLogger(AbstractRuntimeHelper.class);

    public void execute(String cmd) throws Exception {
        execute(cmd, System.out);
    }

    public void execute(String cmd, PrintStream out) throws IOException {
        Process process = null;
        try {
            Runtime rt = Runtime.getRuntime();
            process = rt.exec("cmd.exe");
            process = rt.exec(cmd);
            log.info(String.format("Command is started.[%s]", cmd));
            print(process.getInputStream(), out);
        } catch (IOException ex) {
            if (null != process) {
                log.info(String.format("Command is stop.[%s]", cmd));
                process.destroy();
            }
            throw ex;
        } finally {
            if (null != process) {
                log.info(String.format("Command is stop.[%s]", cmd));
                process.destroy();
            }
        }
    }

    public void print(InputStream in, PrintStream out) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String msg;
        try {
            while ((msg = br.readLine()) != null) {
                out.println(msg);
            }
        } catch (IOException ex) {
            if (br != null) {
                log.info(String.format("Close buffered reader."));
                br.close();
            }
            throw ex;
        } finally {
            if (br != null) {
                log.info(String.format("Close buffered reader."));
                br.close();
            }
        }
    }
}
