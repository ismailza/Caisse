package ma.fstm.ilisi.caisse.metier.bo;

public class PaiementEspece extends Paiement {
    private float montant;
    private float reste;

    public PaiementEspece(Vente vente, float montant) {
        super(vente);
        this.montant = montant;
        this.reste = montant - vente.getTotal();
    }

    public float getReste() {
        return reste;
    }

    @Override
    boolean payer() {
        return false;
    }
}
