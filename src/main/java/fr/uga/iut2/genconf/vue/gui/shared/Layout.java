package fr.uga.iut2.genconf.vue.gui.shared;

import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.vue.gui.GUI;

import javax.swing.*;

public abstract class Layout<T> extends JPanel {

    protected GUI gui;
    protected Controleur controleur;

    protected T model;

    public Layout(GUI gui, Controleur controleur)
    {
        this.gui = gui;
        this.controleur = controleur;


    }

    public void setModel(T model){
        this.model = model;
        this.init();
    }

    private void init(){
        this.removeAll();
        this.initComponents();
    }

    protected abstract void initComponents();
}
