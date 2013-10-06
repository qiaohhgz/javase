package com.jim.designpattern.abstractfactory.v1;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/14/13
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class Fish {
    private Image lImage;
    private Image rImage;
    private Icon animalIcon;
    private int picNum;

    public Fish(Image lImage, Image rImage, Icon animalIcon, int picNum) {
        this.lImage = lImage;
        this.rImage = rImage;
        this.animalIcon = animalIcon;
        this.picNum = picNum;
    }

    public Fish() {
    }

    public Image getlImage() {
        return lImage;
    }

    public void setlImage(Image lImage) {
        this.lImage = lImage;
    }

    public Image getrImage() {
        return rImage;
    }

    public void setrImage(Image rImage) {
        this.rImage = rImage;
    }

    public Icon getAnimalIcon() {
        return animalIcon;
    }

    public void setAnimalIcon(Icon animalIcon) {
        this.animalIcon = animalIcon;
    }

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }
}
