package com.jim.util;

import org.apache.log4j.Logger;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Utils {
    private final static Logger log = Logger.getLogger(Utils.class);

    public static int RandomValueWithinRange(int a, int b) {
        int temp = 0;
        try {
            if (a > b) {
                temp = new Random().nextInt(a - b);
                return temp + b;
            } else {
                temp = new Random().nextInt(b - a);
                return temp + a;
            }
        } catch (Exception e) {
            return temp + a;
        }
    }

    public static double RandomValueWithinRange(double a, double b) {
        double temp = 0;
        try {
            if (a > b) {
                temp = new Random().nextDouble() * (a - b);
                return temp + b;
            } else {
                temp = new Random().nextDouble() * (b - a);
                return temp + a;
            }
        } catch (Exception e) {
            return temp + a;
        }
    }

    @SuppressWarnings("unchecked")
    public static Object getObjectByProbability(TreeMap objects) {

        int randomValue = RandomValueWithinRange(1, 101);

        Iterator iter = objects.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();

            if (randomValue <= (Integer) key) {
                return val;
            }
        }

        return objects.get(objects.lastKey());
    }

    public static int[] randomAverageAllocate(int total, int size, int variation) {
        if (size > total) size = total;
        if (size <= 0 || total <= 0) return new int[0];
        int[] array = new int[size];
        int i = 0;
        int average = Math.round(total / size);
        if (variation > average) variation = average;
        Random r = new Random();
        int sum = 0;
        while (i < size / 2 && sum < total) {
            int var = r.nextInt(variation + 1);
            array[i] = average - var;
            array[size - i - 1] = average + var;
            sum += array[i] + array[size - i - 1];
            i++;
        }
        if (size % 2 == 0) {
            array[size / 2] = array[size / 2] + total - sum;
        } else {
            array[size / 2] = total - sum;
        }

        return array;
    }

    public static void main(String[] args) throws Exception {
        int[] a = randomAverageAllocate(31, 5, 6);
        for (int i = 0; i < a.length; i++) {
            log.info(a[i]);
        }
    }

    /**
     * 通过坐标上的两个点得一个路径点组
     *
     * @param p1
     * @param p2
     * @return
     */
    public static List<Point> parsePath(Point p1, Point p2, int size) {
        List<Point> list = new ArrayList<Point>();
        int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
        double l = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); //距离
        if (l == 0) {
            for (int i = 0; i < size - 2; i++) {
                list.add(p1);
            }
            return list;
        }
        double t1 = x2 - x1;
        double t2 = y2 - y1;
        double a = Math.atan(t1 / t2);
        if (x2 - x1 > 0 && y2 - y1 > 0) {
            log.info("一象限");
        } else if (x2 - x1 < 0 && y2 - y1 > 0) {
            log.info("二象限");
        } else if (x2 - x1 < 0 && y2 - y1 < 0) {
            log.info("三象限");
            a = Math.PI + a;
        } else if (x2 - x1 > 0 && y2 - y1 < 0) {
            log.info("四象限");
            a = Math.PI + a;
        } else if (y2 == y1 && x2 > x1) {
            log.info("正常水平线");
        } else if (y2 == y1 && x2 < x1) {
            a = Math.PI + Math.PI / 2;
            log.info("反正常水平线");
        } else if (x1 == x2 && y2 > y1) {
            log.info("正常垂直线");
        } else if (x1 == x2 && y2 < y1) {
            a = Math.PI;
            log.info("反正常垂直线");
        }
        //计算得到一个
        double splitLen = l / size;
        double x = 0, y = 0;

        int len = 0;
        for (int i = 0; i < size; i++) {
            len += splitLen;
            x = x1 + len * Math.sin(a);
            y = y1 + len * Math.cos(a);
            list.add(new Point((int) x, (int) y));
        }
        log.info("点数：" + ((l / splitLen) + 2));
        return list;
    }
}
