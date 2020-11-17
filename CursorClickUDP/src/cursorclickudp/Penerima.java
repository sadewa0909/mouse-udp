/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursorclickudp;

import java.awt.*;
import java.awt.event.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 *
 * @author IRVAN
 */
public class Penerima {
    public static void main(String[] args) {
        try {
            byte[] buffer = new byte[65];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            DatagramSocket ds = new DatagramSocket(1999);

            while (true) {
                ds.receive(dp);
                byte[] data = dp.getData();
                String message = new String(data, 0, data.length);
                String[] posisi = message.split("\\|");
                int x = Integer.parseInt(posisi[0]);
                int y = Integer.parseInt(posisi[1]);
                String click = posisi[2];
                Robot rbt = new Robot();
                rbt.mouseMove(x, y);
                System.out.println(message);
                if (click.contains("clicked")) {
                    rbt.mousePress(InputEvent.BUTTON1_MASK);
                    rbt.mouseRelease(InputEvent.BUTTON1_MASK);
                    System.out.println("klik pada posisi x=" + x + " | y = " + y);
                }
                System.out.println(message);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}