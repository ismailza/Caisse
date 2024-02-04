package ma.fstm.ilisi.caisse.controlleur;

import ma.fstm.ilisi.caisse.metier.service.AuthService;
import ma.fstm.ilisi.caisse.metier.service.IncorrectAuthException;
import ma.fstm.ilisi.caisse.presentation.AuthForm;

import javax.swing.*;

public class Caisse {
    private double solde;

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void start(){
        new AuthForm().setVisible(true);
    }

    public void checkAuth(String username, String password) {
        String msg = new AuthService().checkAuth(username, password);
        switch (msg) {
            case "SUCCESS" -> JOptionPane.showMessageDialog(null, "Vous étes authentifié avec succes", "Authentification", JOptionPane.INFORMATION_MESSAGE);
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
}
