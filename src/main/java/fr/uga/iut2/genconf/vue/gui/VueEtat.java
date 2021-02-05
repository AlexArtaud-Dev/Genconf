package fr.uga.iut2.genconf.vue.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VueEtat extends JPanel {
    private final GUI gui;
    private final JLabel msgEtat;
    private final JButton buttonRetour;

    public VueEtat(GUI gui) {
        this.gui = gui;

        this.msgEtat = new JLabel("");
        this.msgEtat.setHorizontalAlignment(JLabel.CENTER);
        this.buttonRetour = new JButton("Retour au menu");

        this.buttonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.controleur.consulterConferences();
            }
        });
        this.setLayout(new java.awt.BorderLayout());
        this.add(this.msgEtat, java.awt.BorderLayout.CENTER);
        this.add(buttonRetour, BorderLayout.SOUTH);
    }

    public void setEtat(String message) {
        this.msgEtat.setText(message);
    }
}
