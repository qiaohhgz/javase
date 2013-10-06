package com.jim.bean;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/28/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonTableBean {
    private HashSet<PersonBean> personBeans;

    public HashSet<PersonBean> getPersonBeans() {
        return personBeans;
    }

    public void setPersonBeans(HashSet<PersonBean> personBeans) {
        this.personBeans = personBeans;
    }
}
