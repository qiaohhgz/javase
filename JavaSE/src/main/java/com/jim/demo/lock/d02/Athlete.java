package com.jim.demo.lock.d02;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Athlete implements Runnable {
    private final int id;
    private Game game;

    public Athlete(int id, Game game) {
        this.id = id;
        this.game = game;
    }

    @Override
    public void run() {
        try {
            game.prepare(this);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public String toString() {
        return "Athlete<" + this.id + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Athlete athlete = (Athlete) o;

        if (id != athlete.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
