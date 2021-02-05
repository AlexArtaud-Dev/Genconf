package fr.uga.iut2.genconf.vue.gui;

import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.modele.*;
import fr.uga.iut2.genconf.modele.enums.TypeCommunication;
import fr.uga.iut2.genconf.vue.gui.shared.Layout;
import fr.uga.iut2.genconf.vue.shared.dto.NouvelArticle;
import fr.uga.iut2.genconf.vue.shared.dto.NouvelAuteur;
import fr.uga.iut2.genconf.vue.shared.dto.NouvelUtilisateur;
import fr.uga.iut2.genconf.vue.shared.dto.NouvelleCommunication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class VueAssocierArticle extends Layout<Communication> {
    private final Object[] entetesSession = {"Nom", "Prénom", "Email"};

    private HashMap<String, NouvelAuteur> auteurs;

    public VueAssocierArticle(Controleur controleur, GUI gui) {
        super(gui, controleur);
        auteurs = new HashMap<>();
    }

    public void initComponents() {

        inputNomSpeaker = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        callbackConference = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nomCommunicationInput = new javax.swing.JLabel();
        nomCommunicationInput1 = new javax.swing.JLabel();
        nomCommunicationInput2 = new javax.swing.JLabel();
        nomCommunicationInput3 = new javax.swing.JLabel();
        nomCommunicationInput4 = new javax.swing.JLabel();
        nomCommunicationInput5 = new javax.swing.JLabel();
        nomCommunicationInput6 = new javax.swing.JLabel();
        nomCommunicationInput7 = new javax.swing.JLabel();
        nomCommunicationInput8 = new javax.swing.JLabel();
        inputNomArticle = new javax.swing.JTextField();
        inputLienArticle = new javax.swing.JTextField();
        inputNomAuteur = new javax.swing.JTextField();
        inputPrenomAuteur = new javax.swing.JTextField();
        inputNomSpeaker5 = new javax.swing.JTextField();
        buttonAddAuteur = new javax.swing.JButton();
        nomCommunicationInput9 = new javax.swing.JLabel();
        buttonValider = new javax.swing.JButton();
        buttonRetour = new javax.swing.JButton();
        comboListAuteur = new javax.swing.JComboBox<>();

        inputNomSpeaker.setBackground(new java.awt.Color(255, 255, 255));
        inputNomSpeaker.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        inputNomSpeaker.setForeground(new java.awt.Color(0, 0, 0));

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
        callbackConference.setText("Associer Article" );



        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));

        nomCommunicationInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput.setForeground(new java.awt.Color(0, 0, 0));


        nomCommunicationInput1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput1.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput1.setText("Nom :");

        nomCommunicationInput2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput2.setForeground(new java.awt.Color(0, 0, 0));


        nomCommunicationInput3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        nomCommunicationInput3.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput3.setText("Informations de l'article :");

        nomCommunicationInput4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput4.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput4.setText("Liste d'auteurs ajoutés :");

        nomCommunicationInput5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput5.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput5.setText("Nom :");

        nomCommunicationInput6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        nomCommunicationInput6.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput6.setText("Créer des auteurs");

        nomCommunicationInput7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput7.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput7.setText("Prenom :");

        nomCommunicationInput8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput8.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput8.setText("Email :");

        inputNomArticle.setBackground(new java.awt.Color(255, 255, 255));
        inputNomArticle.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        inputNomArticle.setForeground(new java.awt.Color(0, 0, 0));

        inputLienArticle.setBackground(new java.awt.Color(255, 255, 255));
        inputLienArticle.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        inputLienArticle.setForeground(new java.awt.Color(0, 0, 0));

        inputNomAuteur.setBackground(new java.awt.Color(255, 255, 255));
        inputNomAuteur.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        inputNomAuteur.setForeground(new java.awt.Color(0, 0, 0));

        inputPrenomAuteur.setBackground(new java.awt.Color(255, 255, 255));
        inputPrenomAuteur.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        inputPrenomAuteur.setForeground(new java.awt.Color(0, 0, 0));

        inputNomSpeaker5.setBackground(new java.awt.Color(255, 255, 255));
        inputNomSpeaker5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        inputNomSpeaker5.setForeground(new java.awt.Color(0, 0, 0));

        buttonAddAuteur.setBackground(new java.awt.Color(153, 153, 153));
        buttonAddAuteur.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        buttonAddAuteur.setForeground(new java.awt.Color(0, 0, 0));
        buttonAddAuteur.setText("Ajouter l'auteur");
        buttonAddAuteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboListAuteur.addItem(inputNomAuteur.getText().trim() + " " + inputPrenomAuteur.getText().trim());

                NouvelAuteur infosNouvelAuteur = new NouvelAuteur(
                        inputNomAuteur.getText().trim(),
                        inputPrenomAuteur.getText().trim(),
                        inputNomSpeaker5.getText().trim()

                );
                inputNomAuteur.setText("");
                inputPrenomAuteur.setText("");
                inputNomSpeaker5.setText("");
                auteurs.put(inputNomAuteur.getText().trim(), infosNouvelAuteur);
            }
        });;

        nomCommunicationInput9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput9.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput9.setText("Lien de l'article :");

        buttonValider.setBackground(new java.awt.Color(153, 153, 153));
        buttonValider.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        buttonValider.setForeground(new java.awt.Color(0, 0, 0));
        buttonValider.setText("VALIDER");
        buttonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerActionPerformed(evt);
            }
        });

        buttonRetour.setBackground(new java.awt.Color(153, 153, 153));
        buttonRetour.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        buttonRetour.setForeground(new java.awt.Color(0, 0, 0));
        buttonRetour.setText("RETOUR");
        buttonRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });;

        comboListAuteur.setBackground(new java.awt.Color(255, 255, 255));
        comboListAuteur.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        comboListAuteur.setForeground(new java.awt.Color(0, 0, 0));
       // comboListAuteur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nomCommunicationInput4)
                                                        .addComponent(nomCommunicationInput9)
                                                        .addComponent(nomCommunicationInput1)
                                                        .addComponent(nomCommunicationInput6)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nomCommunicationInput7)
                                                                        .addComponent(nomCommunicationInput5)
                                                                        .addComponent(nomCommunicationInput8))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(inputLienArticle, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                        .addComponent(inputNomArticle, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                        .addComponent(comboListAuteur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(callbackConference, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nomCommunicationInput, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nomCommunicationInput2))
                                                        .addComponent(nomCommunicationInput3))
                                                .addGap(0, 20, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(buttonAddAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(inputNomSpeaker5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(inputNomAuteur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(inputPrenomAuteur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(134, 134, 134))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(buttonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(callbackConference)
                                        .addComponent(nomCommunicationInput)
                                        .addComponent(jLabel5)
                                        .addComponent(nomCommunicationInput2))
                                .addGap(26, 26, 26)
                                .addComponent(nomCommunicationInput3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nomCommunicationInput1)
                                        .addComponent(inputNomArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(nomCommunicationInput9)
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(comboListAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nomCommunicationInput4))
                                                .addGap(24, 24, 24)
                                                .addComponent(nomCommunicationInput6)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(inputNomAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nomCommunicationInput5))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(inputPrenomAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nomCommunicationInput7))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(inputNomSpeaker5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nomCommunicationInput8)))
                                        .addComponent(inputLienArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAddAuteur)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonRetour)
                                        .addComponent(buttonValider))
                                .addContainerGap(80, Short.MAX_VALUE))
        );
    }

    private void creerActionPerformed(java.awt.event.ActionEvent evt) {


        NouvelArticle infosNouvelArticle = new NouvelArticle(
                this.inputNomArticle.getText().trim(),
                this.inputLienArticle.getText().trim(),
                this.auteurs

        );
        controleur.associerArticleCommunication(model.getSession().getConference().getNom(), model.getSession().getNom(), model.getNom(), infosNouvelArticle);
    }

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {
        controleur.consulterCommunication(model.getSession().getConference().getNom(), model.getSession().getNom(), model.getNom());
    }


    private javax.swing.JButton buttonAddAuteur;
    private javax.swing.JButton buttonRetour;
    private javax.swing.JButton buttonValider;
    private javax.swing.JLabel callbackConference;
    private javax.swing.JComboBox<String> comboListAuteur;
    private javax.swing.JTextField inputLienArticle;
    private javax.swing.JTextField inputNomArticle;
    private javax.swing.JTextField inputNomAuteur;
    private javax.swing.JTextField inputNomSpeaker;
    private javax.swing.JTextField inputNomSpeaker5;
    private javax.swing.JTextField inputPrenomAuteur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel nomCommunicationInput;
    private javax.swing.JLabel nomCommunicationInput1;
    private javax.swing.JLabel nomCommunicationInput2;
    private javax.swing.JLabel nomCommunicationInput3;
    private javax.swing.JLabel nomCommunicationInput4;
    private javax.swing.JLabel nomCommunicationInput5;
    private javax.swing.JLabel nomCommunicationInput6;
    private javax.swing.JLabel nomCommunicationInput7;
    private javax.swing.JLabel nomCommunicationInput8;
    private javax.swing.JLabel nomCommunicationInput9;
}
