package fr.uga.iut2.genconf.vue.gui;

import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.modele.Communication;
import fr.uga.iut2.genconf.modele.Conference;
import fr.uga.iut2.genconf.modele.Session;
import fr.uga.iut2.genconf.modele.Utilisateur;
import fr.uga.iut2.genconf.vue.gui.shared.Layout;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class VueAffichageSession extends Layout<Session> {

    private final Object[] entetesSession = {"Nom", "Début", "Fin", "Type"};
    private Object[][] tablevalues;

    private ArrayList<Communication> communications;

    public VueAffichageSession(Controleur controleur, GUI gui) {
        super(gui, controleur);
    }

    public void initComponents() {

        this.communications = new ArrayList<>(model.getCommunications().values());

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        callbackConference = new javax.swing.JLabel();
        nomSession = new javax.swing.JLabel();
        dateFinInput = new javax.swing.JLabel();
        dateDebutInput = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        chairmanInput = new javax.swing.JComboBox<>();
        typeSessionInput = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        tablevalues = new Object[model.getCommunications().size()][4];

        Object[][] tablevalues = this.model.getCommunications().values().stream().map(VueAffichageSession::communicationToStringArray).toArray(Object[][]::new);

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

                    Communication com = communications.get(numLigne);
                    controleur.consulterCommunication(model.getConference().getNom(), model.getNom(),com.getNom()); ;

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


        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Conférences >");
        jLabel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                controleur.consulterConferences();

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

        callbackConference.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        callbackConference.setForeground(new java.awt.Color(0, 0, 0));
        callbackConference.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                controleur.consulterConference(model.getConference().getNom());
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
        try{
            callbackConference.setText(model.getConference().getNom());
        }catch (Exception e){

        }


        nomSession.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomSession.setForeground(new java.awt.Color(0, 0, 0));
        nomSession.setText("> " + model.getNom());

        dateFinInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        dateFinInput.setForeground(new java.awt.Color(0, 0, 0));
        dateFinInput.setText(model.getDateFin().toString());

        dateDebutInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        dateDebutInput.setForeground(new java.awt.Color(0, 0, 0));
        dateDebutInput.setText(model.getDateDebut().toString());

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Communications ");

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Ajouter une communication");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.saisirNouvelleCommunication(model.getCommunications().keySet(), model.getConference(), model);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Type de Session :");

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
       // jTable1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setRowHeight(20);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Chairmans : ");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Du :");

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Au :");

        typeSessionInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        typeSessionInput.setForeground(new java.awt.Color(0, 0, 0));
        typeSessionInput.setText(model.getType().toString());

        chairmanInput.setBackground(new java.awt.Color(255, 255, 255));
        chairmanInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        chairmanInput.setForeground(new java.awt.Color(0, 0, 0));
        Map<String, Utilisateur> chairs;
        chairs = model.getChairmans();
        chairs.forEach((key, value) -> chairmanInput.addItem(value.getNom() + " " + value.getPrenom()));
        chairmanInput.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    ArrayList<Utilisateur> chairmans = new ArrayList<>(model.getChairmans().values());
                    int numLigne = chairmanInput.getSelectedIndex();
                    try {
                        Utilisateur util = chairmans.get(numLigne);
                        controleur.consulterUtilisateur(util.getEmail());
                    }
                    catch (Exception ignored){

                    }
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


        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Ajouter un chairman");
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.ajouterChairman(model.getConference(), model);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel9)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(typeSessionInput))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel10)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(chairmanInput)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(callbackConference, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nomSession, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(dateFinInput, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(dateDebutInput, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addContainerGap(159, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addGap(12, 12, 12))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(callbackConference)
                                        .addComponent(nomSession))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateDebutInput)
                                        .addComponent(jLabel11))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateFinInput)
                                        .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(typeSessionInput))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(chairmanInput))
                                .addGap(24, 24, 24)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3))
                                .addGap(28, 28, 28))
        );
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel callbackConference;
    private javax.swing.JLabel dateDebutInput;
    private javax.swing.JLabel dateFinInput;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel nomSession;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> chairmanInput;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel typeSessionInput;

    private static Object[] communicationToStringArray(Communication communication) {
        Object[] ligne = {communication.getNom(), communication.getDateDebut().toString(),communication.getDateFin().toString(),communication.getType().toString()};
        return ligne;
    }


}
