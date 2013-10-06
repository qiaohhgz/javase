package com.jim.socket;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 8/27/13
 * Time: 12:10 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCloseable implements Closeable {
    List<Closeable> streams = new ArrayList<Closeable>();

    @Override
    public void close() throws IOException {
        for (Closeable stream : streams) {
            if (null != stream) {
                stream.close();
                System.out.println("close " + stream.getClass());
            }
        }
    }

    protected void addStream(Closeable stream) {
        this.streams.add(stream);
    }
}
