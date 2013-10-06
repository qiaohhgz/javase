package com.jim.download.ibm;

import com.jim.download.R;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/9/13
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMethod {
    public TestMethod() {
        try {
            String sURL = R.URL;
            String sPath = R.LOCAL_DIR;
            String sName = R.LOCAL_NAME;
            int nSpiltter = R.THREAD_NUM;
            SiteInfoBean bean = new SiteInfoBean(sURL, sPath, sName, nSpiltter);
            SiteFileFetch fileFetch = new SiteFileFetch(bean);
            fileFetch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestMethod();
    }
}
