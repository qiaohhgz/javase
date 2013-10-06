package com.jim.util;

import org.junit.Test;

import java.awt.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 6/24/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestUtils {
    @Test
    public void testParsePath() throws Exception {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(100, 50);
        List<Point> points = Utils.parsePath(p1, p2, 10);
        for (Point p : points) {
            System.out.println("x = " + p.getX() + " y = " + p.getY());
        }
    }

    @Test
    public void testRandomValueWithinRange() throws Exception {

    }

    @Test
    public void testGetObjectByProbability() throws Exception {

    }

    @Test
    public void testRandomAverageAllocate() throws Exception {

    }
}
