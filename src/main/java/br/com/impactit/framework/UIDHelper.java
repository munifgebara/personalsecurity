package br.com.impactit.framework;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UIDHelper {

    private static long lastTime;

    private static String myMac;

    public static synchronized String getUID() {
        long time = System.currentTimeMillis() * 100;
        while (time <= lastTime) {
            time++;
        }
        lastTime = time;
        return "" + time;
    }

    public static void main(String args[]) {
        for (int i = 0; i < 1000; i++) {
            System.out.println("----->" + getUID());
        }

    }
}
