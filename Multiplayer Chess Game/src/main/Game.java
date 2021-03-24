/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Mert
 */
public class Game {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        jFrame.setBounds(1, 1, 512, 512);
        jFrame.setUndecorated(true);
        JPanel jPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {

                DrawBoard(g);

            }
        };
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    public static void DrawBoard(Graphics g) {
        boolean isWhite = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isWhite) {
                    g.setColor(new Color(235, 235, 208));
                } else {
                    g.setColor(new Color(119, 148, 85));
                }
                g.fillRect(i * 64, j * 64, 64, 64);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
    }

}
