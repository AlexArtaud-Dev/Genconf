package fr.uga.iut2.genconf.vue.gui;

import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.modele.Communication;
import fr.uga.iut2.genconf.modele.Conference;
import fr.uga.iut2.genconf.modele.Session;
import fr.uga.iut2.genconf.vue.gui.shared.Layout;
import fr.uga.iut2.genconf.vue.shared.dto.NouveauSpeaker;
import org.apache.commons.validator.routines.EmailValidator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;


public class VueCreationChairman extends Layout<Session> {

    public VueCreationChairman(Controleur controleur, GUI gui) {
        super(gui, controleur);
    }

    public void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        inputEmail = new javax.swing.JTextField();
        inputPrenomSpeaker = new javax.swing.JTextField();
        inputNomSpeaker = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        validerButton = new javax.swing.JButton();

        validerButton.setEnabled(false);
        inputEmail.setForeground(Color.red);

        jLabel1.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new Color(0, 0, 0));


        jLabel3.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new Color(0, 0, 0));


        jLabel4.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new Color(0, 0, 0));


        jLabel10.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setForeground(new Color(0, 0, 0));


        jLabel11.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setForeground(new Color(0, 0, 0));


        setBackground(new Color(204, 204, 204));

        jLabel2.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new Color(0, 0, 0));
        jLabel2.setText("Conférences >");

        jLabel2.addMouseListener(new MouseListener() {
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

        jLabel5.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new Color(0, 0, 0));
        try{
            jLabel5.setText("> " + model.getConference().getNom());
        }catch (Exception ignored){

        }
        jLabel5.addMouseListener(new MouseListener() {
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

        jLabel6.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new Color(0, 0, 0));
        try{
            jLabel6.setText("> " + model.getNom());
        }catch (Exception ignored){

        }
        jLabel6.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                controleur.consulterSession(model.getConference().getNom(), model.getNom());
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

        jLabel12.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel12.setForeground(new Color(0, 0, 0));
        jLabel12.setText("> Ajouter Chairman");


        jLabel13.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel13.setForeground(new Color(0, 0, 0));


        jLabel7.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel7.setForeground(new Color(0, 0, 0));
        jLabel7.setText("Nom :");


        jLabel8.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setForeground(new Color(0, 0, 0));
        jLabel8.setText("Prénom :");


        jLabel9.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new Color(0, 0, 0));
        jLabel9.setText("Email :");

        inputEmail.setBackground(new Color(255, 255, 255));
        inputEmail.setFont(new Font("Calibri", 1, 14)); // NOI18N
        inputEmail.setForeground(new Color(0, 0, 0));
        inputEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validerSaisie(evt);
            }
        });

        inputPrenomSpeaker.setBackground(new Color(255, 255, 255));
        inputPrenomSpeaker.setFont(new Font("Calibri", 1, 14)); // NOI18N
        inputPrenomSpeaker.setForeground(new Color(0, 0, 0));
        inputPrenomSpeaker.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validerSaisie(evt);
            }
        });

        inputNomSpeaker.setBackground(new Color(255, 255, 255));
        inputNomSpeaker.setFont(new Font("Calibri", 1, 14)); // NOI18N
        inputNomSpeaker.setForeground(new Color(0, 0, 0));
        inputNomSpeaker.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validerSaisie(evt);
            }
        });

        jButton1.setBackground(new Color(153, 153, 153));
        jButton1.setFont(new Font("Calibri", 1, 14)); // NOI18N
        jButton1.setForeground(new Color(0, 0, 0));
        jButton1.setText("ANNULER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });

        validerButton.setBackground(new Color(153, 153, 153));
        validerButton.setFont(new Font("Calibri", 1, 14)); // NOI18N
        validerButton.setForeground(new Color(0, 0, 0));
        validerButton.setText("VALIDER");
        validerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addContainerGap(149, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(validerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputPrenomSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputNomSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(79, 79, 79)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(validerButton)
                                        .addComponent(jButton1))
                                .addGap(113, 113, 113))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(101, 101, 101)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel7)
                                                .addComponent(inputNomSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel8)
                                                .addComponent(inputPrenomSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(61, 61, 61)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9))
                                        .addContainerGap(274, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void creerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerActionPerformed
       controleur.ajouterChairManSession(model.getConference().getNom(), model.getNom(),inputEmail.getText().toLowerCase().trim());
    }

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
        controleur.consulterSession(model.getConference().getNom(), model.getNom());
    }//GEN-LAST:event_annulerActionPerformed

    private void validerSaisie(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_validerSaisie
        boolean valide;
        boolean validEmail;
        String nom, prenom;
        EmailValidator validator = EmailValidator.getInstance(false, false);

        nom = this.inputNomSpeaker.getText().trim();
        prenom = this.inputPrenomSpeaker.getText().trim();
        validEmail = validator.isValid(this.inputEmail.getText().trim().toLowerCase());
        this.inputEmail.setForeground(validEmail ? Color.black : Color.red);

        valide = validEmail
                 && (nom != null) && (nom.length() > 0)
                 && (prenom != null) && (prenom.length() > 0);

        this.validerButton.setEnabled(valide);
    }


    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputNomSpeaker;
    private javax.swing.JTextField inputPrenomSpeaker;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton validerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
}
