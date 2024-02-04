package ma.fstm.ilisi.caisse.metier.service;

public class IncorrectAuthException extends Exception {

    public IncorrectAuthException() {
        super("Vous avez expiré vos tentatives pour s'authentifier!!!");
    }
}
