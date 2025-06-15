/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalabdetailed;
import com.formdev.flatlaf.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Scanner;

import java.util.List;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        GUI gui = new GUI();
        JFrame frame = new JFrame("Restaurant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setVisible(true);
        JTabbedPane pane = new JTabbedPane();
        frame.add(pane);
        gui.menuBuilder(pane);
        gui.ordersPage(pane, frame);
    }
}
