package ma.fstm.ilisi.caisse.presentation;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    private final JLabel designation;
    private final JLabel price;
    private final JLabel total;

    public Display() {
        super("Caisse | Afficheur");
        setSize(600, 100);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(1, 3));
        designation = new JLabel("", SwingConstants.CENTER);
        price = new JLabel("Prix Unitaire: 00 DH", SwingConstants.CENTER);
        total = new JLabel("Prix Total: 00 DH", SwingConstants.CENTER);

        panel.add(designation);

        JPanel pricesPanel = new JPanel(new GridLayout(2, 1));
        pricesPanel.add(price);
        pricesPanel.add(total);

        panel.add(pricesPanel);

        this.add(panel, BorderLayout.CENTER);
    }

    public void setDesignation(String designation) {
        this.designation.setText(designation);
    }

    public void setPrice(float price) {
        this.price.setText(String.format("Prix Unitaire: %.2f DH", price));
    }

    public void setTotal(float total) {
        this.total.setText(String.format("Prix Total: %.2f DH", total));
    }

    public void finVente(float total, float montant, float reste) {
        this.designation.setText(String.format("Total: %.2f", total));
        this.price.setText(String.format("Montant: %.2f", montant));
        this.total.setText(String.format("Reste: %.2f", reste));
    }
}
