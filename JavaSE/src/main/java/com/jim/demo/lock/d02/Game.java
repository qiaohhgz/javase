package com.jim.demo.lock.d02;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game implements Runnable {
    private Set<Athlete> players = new HashSet<Athlete>();

    @Override
    public void run() {
        try {
            ready();
            for (int i = 3; i > 0; i--) {
                System.out.println("Ready " + i + "......");
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("Go!");
            go();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void addPlayer(Athlete one) {
        players.add(one);
    }

    public void removePlayer(Athlete one) {
        players.remove(one);
    }

    public Collection<Athlete> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public synchronized void prepare(Athlete athlete) throws InterruptedException {
        System.out.println(athlete + " ready!");
        wait();//等待
        System.out.println(athlete + " go!");
    }

    public synchronized void go() {
        notifyAll();
    }

    public void ready() {
        Iterator<Athlete> iterator = getPlayers().iterator();
        while (iterator.hasNext()) {
            new Thread(iterator.next()).start();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        for (int i = 0; i < 10; i++) {
            game.addPlayer(new Athlete(i, game));
        }
        new Thread(game).start();
    }
}
