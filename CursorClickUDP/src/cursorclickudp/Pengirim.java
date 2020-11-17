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
import java.net.InetAddress;
import javax.swing.*;

/**
 *
 * @author IRVAN
 */
class Pengirim extends Frame implements MouseListener {

    static JLabel lbl1, lbl2, lbl3, lbl4;
    static boolean clicked = false;

    Pengirim() {
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("MouseListener");

        jf.setSize(1000, 1000);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();

        jp.setLayout(new FlowLayout());

        lbl1 = new JLabel("no event  ");

        lbl2 = new JLabel("no event  ");

        lbl3 = new JLabel("no event  ");

        lbl4 = new JLabel("no event  ");

        Pengirim pm = new Pengirim();

        jf.addMouseListener(pm);

        jp.add(lbl1);
        jp.add(lbl2);
        jp.add(lbl3);
        jp.add(lbl4);

        jf.add(jp);

        jf.show();

        try {

            PointerInfo pi;
            Point p;
            int x, y;
            InetAddress ia = InetAddress.getByName("192.168.43.207");
            String message = "";
            while (true) {
                pi = MouseInfo.getPointerInfo();
                p = pi.getLocation();
                x = (int) p.getX();
                y = (int) p.getX();
                if (clicked) {
                    message = String.valueOf(x) + "|" + String.valueOf(y) + "|clicked";
                    clicked = false;
                } else {
                    message = String.valueOf(x) + "|" + String.valueOf(y) + "|notClicked";
                }
                byte[] data = message.getBytes();
                DatagramPacket dp = new DatagramPacket(data, data.length, ia, 1999);
                DatagramSocket ds = new DatagramSocket();
                Thread.sleep(100);
                ds.send(dp);
                System.out.println(message);
                lbl4.setText("Mouse Position : X = " + x + " | Y = " + y);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void mousePressed(MouseEvent e) {

        lbl1.setText("mouse pressed at:"
                + e.getX() + " " + e.getY());
        clicked = true;

    }

    public void mouseReleased(MouseEvent e) {

        lbl1.setText("mouse released at:"
                + e.getX() + " " + e.getY());
    }

    public void mouseExited(MouseEvent e) {

        lbl2.setText("mouse exited through:"
                + e.getX() + " " + e.getY());
    }

    public void mouseEntered(MouseEvent e) {

        lbl2.setText("mouse entered at:"
                + e.getX() + " " + e.getY());
    }

    public void mouseClicked(MouseEvent e) {
        lbl3.setText("mouse clicked at:"
                + e.getX() + " "
                + e.getY() + "mouse clicked :" + e.getClickCount());

    }
}
