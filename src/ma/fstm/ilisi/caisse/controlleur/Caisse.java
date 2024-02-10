package ma.fstm.ilisi.caisse.controlleur;

import ma.fstm.ilisi.caisse.metier.bo.Article;
import ma.fstm.ilisi.caisse.metier.bo.Catalogue;
import ma.fstm.ilisi.caisse.metier.bo.Vente;
import ma.fstm.ilisi.caisse.metier.service.AuthService;
import ma.fstm.ilisi.caisse.metier.service.IncorrectAuthException;
import ma.fstm.ilisi.caisse.presentation.AuthForm;
import ma.fstm.ilisi.caisse.presentation.Display;
import ma.fstm.ilisi.caisse.presentation.Home;

import javax.swing.*;

public class Caisse {
    private double solde;
    private Vente venteEncours;
    private Catalogue catalogue = new Catalogue();
    private AuthForm authForm;
    private Home home;
    private Display display;

    public Caisse() {
        this.solde = 1000;
        this.venteEncours = null;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void start(){
        authForm = new AuthForm(this);
        authForm.setVisible(true);
    }

    public void checkAuth(String username, String password) {
        String msg = new AuthService().checkAuth(username, password);
        switch (msg) {
            case "SUCCESS" -> {
                JOptionPane.showMessageDialog(null, "Vous étes authentifié avec succes", "Authentification", JOptionPane.INFORMATION_MESSAGE);
                authForm.dispose();
                home = new Home(this);
                home.setVisible(true);
                display = new Display();
                display.setVisible(true);
            }
            case "FAILED" -> {
                try {
                    throw new IncorrectAuthException();
                } catch (IncorrectAuthException e) {
                    JOptionPane.showMessageDialog(null, "Echec, vous avez dépassé 3 tentative!", "Authentification", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
            default -> JOptionPane.showMessageDialog(null, "login ou mot de passe incorrect! vous avez encore " + msg + " tentative!", "Authentification", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void enregistrerArticle(String reference, int quantite) {
        if (venteEncours == null)
            venteEncours = new Vente();
        Article article = catalogue.chercherArticle(reference);
        venteEncours.ajouter(article, quantite);
        display.setDesignation(article.getDesignation());
        display.setPrice(article.getPrix());
        display.setTotal(venteEncours.getTotal());
        home.updateArticleList(venteEncours.getAchats());
    }

    public void finVente() {
        venteEncours.finirVente();
    }
}
