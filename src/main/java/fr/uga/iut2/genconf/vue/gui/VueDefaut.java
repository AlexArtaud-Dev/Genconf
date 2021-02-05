package fr.uga.iut2.genconf.vue.gui;

import fr.uga.iut2.genconf.modele.Conference;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.stream.Collectors;


public class VueDefaut extends javax.swing.JPanel {
    private final Object[] entetesSession = {"Conférence", "Date Début", "Date Fin", "Type"};
    private final GUI gui;
    private Map<String, Conference> conferences;

    /**
     * Creates new form VueCreationConference
     */
    public VueDefaut(GUI gui, Map<String, Conference> conferences) {
        this.gui = gui;
        if (conferences==null) {
            this.conferences = new HashMap<>();
        } else {
            this.conferences = conferences;
        }
        // création de l'interface générée
    }

    private void initComponents() {

        this.removeAll(); // On s'assure que le jpanel soit bien vide.

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Conférences");

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setForeground(new java.awt.Color(0, 0, 0));



        Object[][] tablevalues = this.conferences.values().stream().map(VueDefaut::conferenceToStringArray).toArray(Object[][]::new);


        this.jTable1 = new JTable(tablevalues, entetesSession) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };


        jTable1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    int numLigne = jTable1.getSelectedRow();
                    //System.out.println(numLigne);
                    //gui.afficherConference(new ArrayList<>(conferences.values()).get(numLigne));
                    Conference conf = new ArrayList<>(conferences.values()).get(numLigne);
                    gui.controleur.consulterConference(conf.getNom());
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setRowHeight(20);
        jTable1.setShowGrid(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Nouvelle Conférence");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBoutonCreationConference(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addContainerGap())
        );
    }

    private static String convertWithStream(Map<String, ?> map) {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    public void setConferences(Map<String, Conference> conferences) {
        this.conferences = conferences;
        this.initComponents();
    }
    private void actionBoutonCreationConference(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionMenuCreationConference
        this.gui.actionCreerConference();
    }



    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration

    private static Object[] conferenceToStringArray(Conference conference) {
        Object[] ligne = {conference.getNom(), conference.getDateDebut().toString(),conference.getDateFin().toString(), conference.getStatus()};
        return ligne;
    }
}

