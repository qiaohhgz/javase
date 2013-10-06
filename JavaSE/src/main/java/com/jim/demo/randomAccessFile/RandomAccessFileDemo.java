package com.jim.demo.randomAccessFile;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/4/13
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class RandomAccessFileDemo {
    protected final Logger logger = Logger.getLogger(getClass());

    public void seek(File file) throws Exception {
        RandomAccessFile rdf = new RandomAccessFile(file, "rw");
        try {
            rdf.seek(10);//绝对定位
            rdf.writeBytes("World");
            rdf.seek(0);//绝对定位
            rdf.writeBytes("Hello");
        } finally {
            if (rdf != null) {
                rdf.close();
            }
        }
    }

    public String readSkipString(File file) throws Exception {
        RandomAccessFile rdf = new RandomAccessFile(file, "rw");
        try {
            String before = readString(rdf, 0, 5);
            logger.info("before = " + before);
            String after = readString(rdf, 5, 5);
            logger.info("after = " + after);
            String finalStr = before + after;
            logger.info("finalStr = " + finalStr);
            return finalStr;
        } finally {
            if (rdf != null) {
                rdf.close();
            }
        }
    }


    public String readString(RandomAccessFile rdf, int skipBytes, int len) throws IOException {
        rdf.skipBytes(skipBytes);//相对定位
        byte[] bs = new byte[len];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rdf.readByte();
        }
        return new String(bs);
    }

    public void write(File file) throws Exception {
        //rw 为文件的打开方式(r,w,rw),如果文件不存在,会自动创建
        RandomAccessFile rdf = new RandomAccessFile(file, "rw");
        String name;
        int age;

        try {
            //在文件中,所有的内容都是按照字节存放的,都有固定的保存位置.
            name = "ZhangSan"; //字符串的长度为8
            age = 30; //数字的长度为4
            rdf.writeBytes(name); //将姓名写入文件中
            rdf.writeInt(age); //将年龄写入文件中

            name = "lisi    "; //不够8个字符,用空格补全
            age = 32;
            rdf.writeBytes(name);
            rdf.writeInt(age);

            name = "wangwu  ";
            age = 36;
            rdf.writeBytes(name);
            rdf.writeInt(age);
        } finally {
            if (rdf != null) {
                rdf.close(); //注意要关闭通道
            }
        }
    }

    public List<String> read(File file) throws Exception {
        //以只读的方式打开文件
        RandomAccessFile rdf = new RandomAccessFile(file, "r");

        String name;
        int age;

        try {
            byte[] bytes = new byte[8];
            //跳过一个人的信息
            rdf.skipBytes(12);  //试图跳过n个字节的输入丢弃跳过字节。
            //读取字符串需要一个一个字节的读取
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = rdf.readByte();
            }
            name = new String(bytes); //把byte数组转换成字符串对象
            age = rdf.readInt();
            List<String> msgs = new ArrayList<String>();
            String msg;
            msg = String.format("第二个人的信息是: 姓名-->%s,年龄-->%d", name, age);
            msgs.add(msg);
            logger.info(msg);

            rdf.seek(0); //指针回到文件的开头
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = rdf.readByte();
            }
            name = new String(bytes);
            age = rdf.readInt();
            msg = String.format("第一个人的信息是: 姓名-->%s,年龄-->%d", name, age);
            msgs.add(msg);
            logger.info(msg);

            rdf.skipBytes(12); //空出第二个人的信息
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = rdf.readByte();
            }
            name = new String(bytes);
            age = rdf.readInt();
            msg = String.format("第三个人的信息是: 姓名-->%s,年龄-->%d", name, age);
            msgs.add(msg);
            logger.info(msg);
            return msgs;
        } finally {
            if (rdf != null) {
                rdf.close(); //注意关闭通道
            }
        }
    }
}

