package fr.uga.iut2.genconf.vue.gui;

import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.modele.*;
import fr.uga.iut2.genconf.modele.enums.TypeCommunication;
import fr.uga.iut2.genconf.vue.gui.shared.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class VueAffichageCommunication extends Layout<Communication> {

    private final Object[] entetesSession = {"Nom", "Début", "Fin", "Type"};
    private Object[][] tablevalues;

    public VueAffichageCommunication(Controleur controleur, GUI gui) {
        super(gui, controleur);
    }

    @Override
    public void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        callbackConference = new javax.swing.JLabel();
        nomCommunicationInput = new javax.swing.JLabel();
        dateFinInput = new javax.swing.JLabel();
        dateDebutInput = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        typeCommunicationInput = new javax.swing.JLabel();
        referentInput = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        speakersInput1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();




        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Calibri", Font.BOLD, 14)); // NOI18N
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
                controleur.consulterConference(model.getSession().getConference().getNom());
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
            callbackConference.setText(model.getSession().getConference().getNom());
        }catch (Exception ignored){

        }

        nomCommunicationInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        nomCommunicationInput.setForeground(new java.awt.Color(0, 0, 0));
        nomCommunicationInput.setText("> " + model.getNom());

        dateFinInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        dateFinInput.setForeground(new java.awt.Color(0, 0, 0));
        dateFinInput.setText(model.getDateFin().toString());

        dateDebutInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        dateDebutInput.setForeground(new java.awt.Color(0, 0, 0));
        dateDebutInput.setText(model.getDateDebut().toString());

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Ajouter un speaker");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.ajouterSpeaker(model.getSession().getConference(), model.getSession(), model);
            }
        });


        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Type de Session :");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Référent :");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Du :");

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Au :");

        typeCommunicationInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        typeCommunicationInput.setForeground(new java.awt.Color(0, 0, 0));
        typeCommunicationInput.setText(model.getType().toString());

        referentInput.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        referentInput.setForeground(new java.awt.Color(0, 0, 0));
        referentInput.setText(model.getReferent().getPrenom() + " " + model.getReferent().getNom());
        referentInput.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                controleur.consulterReferent(model.getSession().getConference().getNom(), model.getSession().getNom(), model.getNom());
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
        jButton3.setText("Associer un article");
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.associerArticleCommunication(model.getSession().getConference(), model.getSession(), model);
            }
        });



        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                controleur.consulterSession(model.getSession().getConference().getNom(), model.getSession().getNom());
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
            jLabel5.setText("> " + model.getSession().getNom());
        }catch (Exception ignored){

        }

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Speakers :");


        speakersInput1.setBackground(new java.awt.Color(255, 255, 255));
        speakersInput1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        speakersInput1.setForeground(new java.awt.Color(0, 0, 0));
        Map<String, Speaker> speakers;
        speakers = model.getSpeakers();
        speakers.forEach((key, value) -> speakersInput1.addItem(value.getNom() + " " + value.getPrenom()));

        Article article = model.getArticle();
        jList1.setVisible(article != null);

        jList1.setBackground(new java.awt.Color(255, 255, 255));
        jList1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.black));
        jList1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jList1.setForeground(new java.awt.Color(0, 0, 0));
        try{
            jList1.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = { "Article : " + article.getNom(), "Par : " + article.getAuteurs().values().stream().map(a -> a.getPrenom() + " " + a.getNom()).collect(Collectors.joining(", ")), article.getLienPdf() };
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });
        }catch (Exception e){
            /*jList1.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = { "Pas d'article pour le moment !" };
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });*/
        }

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(4, 4, 4)
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(callbackConference, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nomCommunicationInput, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(dateDebutInput, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                                                                        .addComponent(dateFinInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(80, 80, 80)
                                                                                .addComponent(speakersInput1)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel13)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(3, 3, 3)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jLabel9)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(typeCommunicationInput))
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jLabel10)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(referentInput, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(callbackConference)
                                        .addComponent(nomCommunicationInput)
                                        .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(dateDebutInput)
                                                        .addComponent(jLabel11))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel12)
                                                        .addComponent(dateFinInput))
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel9)
                                                        .addComponent(typeCommunicationInput))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel10)
                                                        .addComponent(referentInput)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(speakersInput1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3))
                                .addGap(64, 64, 64))
        );
    }

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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomCommunicationInput;
    private javax.swing.JComboBox<String> speakersInput1;
    private javax.swing.JLabel referentInput;
    private javax.swing.JLabel typeCommunicationInput;

    private static Object[] communicationToStringArray(Communication communication) {
        Object[] ligne = {communication.getNom(), communication.getDateDebut().toString(),communication.getDateFin().toString(),communication.getType().toString()};
        return ligne;
    }
}
