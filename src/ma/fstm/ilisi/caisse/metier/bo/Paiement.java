package ma.fstm.ilisi.caisse.metier.bo;

public abstract class Paiement {
    protected Vente vente;

    public Paiement(Vente vente) {
        this.vente = vente;
    }

    abstract boolean payer();
}
