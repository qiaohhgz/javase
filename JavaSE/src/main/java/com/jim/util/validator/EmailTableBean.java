package com.jim.util.validator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/30/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailTableBean {
    private String identifier;
    private String label;
    private List<EmailBean> items;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<EmailBean> getItems() {
        return items;
    }

    public void setItems(List<EmailBean> items) {
        this.items = items;
    }
}
