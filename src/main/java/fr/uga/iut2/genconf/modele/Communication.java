package fr.uga.iut2.genconf.modele;

import fr.uga.iut2.genconf.modele.enums.TypeCommunication;
import fr.uga.iut2.genconf.util.extension.CollectionExtension;
import fr.uga.iut2.genconf.util.extension.DateExtension;
import jdk.jshell.execution.Util;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Communication implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private final String nom;
    private final LocalDate dateDebut;
    private final LocalDate dateFin;
    private final TypeCommunication type;
    private String lienVideo;

    private Session session;
    private Map<String, Speaker> speakers;
    private Article article;
    private Utilisateur referent;

    public Communication(Session session, String nom, TypeCommunication type, LocalDate dateDebut, LocalDate dateFin, Utilisateur referent) {
        assert !dateFin.isAfter(dateDebut);

        this.session = session;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.referent = referent;
        referent.addReferents(this);
        this.lienVideo = "";
        this.speakers = new HashMap<>();
    }

    public void setReferent(Utilisateur referent){
        if(this.referent.getEmail().equals(referent.getEmail()))
            return;

        this.referent.removedReferent(this.nom);

        this.referent = referent;
        referent.addReferents(this);

        this.addSpeaker(referent);
    }

    public void setLienVideo(String lienVideo) {
        this.lienVideo = lienVideo;
    }

    public void setArticle(Article article){
        assert !getType().equals(TypeCommunication.Article);

        this.article = article;
    }

    public void addSpeaker(String nom, String prenom, String email){
        assert !this.speakers.containsKey(email);

        speakers.put(email, new Speaker(this, nom, prenom, email));
    }


    public void addSpeaker(Utilisateur utilisateur){
        assert !this.speakers.containsKey(utilisateur.getEmail());

        Speaker speaker = new Speaker(this, utilisateur);

        speakers.put(utilisateur.getEmail(), new Speaker(this, utilisateur));
        utilisateur.addSpeakers(speaker);
    }

    public Session getSession() { return this.session; }

    public Map<String, Speaker> getSpeakers() {
        return speakers;
    }

    public Article getArticle() {
        return article;
    }

    public String getNom(){
        return this.nom;
    }

    public LocalDate getDateDebut(){
        return this.dateDebut;
    }

    public LocalDate getDateFin(){
        return this.dateFin;
    }

    public TypeCommunication getType(){
        return this.type;
    }

    public String getLienVideo(){
        return this.lienVideo;
    }

    public Utilisateur getReferent() {
        return this.referent;
    }

    public boolean isSpeaker(String email)
    {
        return speakers.containsKey(email);
    }

    @Override
    public String toString() {
        return "Nom Communication : " + getNom() + "\n" +
                "Commence le " + DateExtension.getAs_ISO_8061(getDateDebut()) + " et fini le " + DateExtension.getAs_ISO_8061(getDateFin()) + "\n" +
                "Vidéo : " + getLienVideo() + "\n" +
                "Type : " + getType() + "\n" +
                "Article : " + (getArticle() == null ? "aucuns" :getArticle().toString() + "\n") +
                "Speakers : " + speakers.values().stream().map(speaker -> getNom() + " " + speaker.getPrenom()).collect(Collectors.joining(", ")) + "\n" +
                "Référent·e : " + getReferent().getPrenom() + " " + getReferent().getNom();
    }
}
