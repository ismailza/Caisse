package ma.fstm.ilisi.caisse.controlleur;

import ma.fstm.ilisi.caisse.metier.bo.Article;
import ma.fstm.ilisi.caisse.metier.bo.Magasin;
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
    private Magasin magasin;
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
        magasin = Magasin.getInstance();
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
        if (venteEncours == null) {
            venteEncours = new Vente();
            home.setDateVente(venteEncours.getDatetime());
        }
        Article article = magasin.chercherArticle(reference);
        venteEncours.ajouter(article, quantite);
        display.setDesignation(article.getDesignation());
        display.setPrice(article.getPrix());
        display.setTotal(venteEncours.getTotal());
        home.updateArticleList(venteEncours.getAchats());
    }

    public boolean finVente(float montant) {
        if (montant < venteEncours.getTotal())
            return false;
        venteEncours.finirVente();
        display.finVente(venteEncours.getTotal(), montant, montant - venteEncours.getTotal());
        if (JOptionPane.showConfirmDialog(home, "Payer la vente", "Paiement", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION)
            payerVente();
        return true;
    }

    public void payerVente() {
        if (venteEncours.payerSomme()) {
            solde += venteEncours.getTotal();
            magasin.historiser(venteEncours);
            venteEncours = null;
        }
    }

    public void annulerVente() {
        if (JOptionPane.showConfirmDialog(home, "Vous voulez vraiment annuler cette vente !?", "Annuler Vente", JOptionPane.YES_NO_CANCEL_OPTION) != JOptionPane.YES_OPTION)
            return;
        this.venteEncours = null;
        display.setDesignation("");
        display.setPrice(0);
        display.setTotal(0);
        home.updateArticleList(null);
    }
}
