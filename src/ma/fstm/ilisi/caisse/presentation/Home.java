package ma.fstm.ilisi.caisse.presentation;

import ma.fstm.ilisi.caisse.controlleur.Caisse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

public class Home extends JFrame {
    private final JTextField referenceField;
    private final JSpinner quantitySpinner;
    private final DefaultTableModel tableModel;
    private final JLabel dateVente;
    private final Caisse caisse;

    public Home(Caisse caisse) {
        super("Caisse | Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.caisse = caisse;

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 120, 5));

        dateVente = new JLabel();
        headerPanel.add(dateVente);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel referenceLabel = new JLabel("Référence:");
        referenceField = new JTextField(20);
        inputPanel.add(referenceLabel);
        inputPanel.add(referenceField);

        JLabel quantityLabel = new JLabel("Quantité:");
        SpinnerNumberModel quantityModel = new SpinnerNumberModel();
        quantityModel.setValue(1);
        quantityModel.setMinimum(1);
        quantitySpinner = new JSpinner(quantityModel);
        quantitySpinner.setPreferredSize(new Dimension(100, 20));
        inputPanel.add(quantityLabel);
        inputPanel.add(quantitySpinner);

        JButton addButton = new JButton("+ Ajouter");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(e -> addArticleHandler(referenceField.getText(), (Integer) quantitySpinner.getValue()));
        referenceField.addActionListener(e -> addArticleHandler(referenceField.getText(), (Integer) quantitySpinner.getValue()));

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 5));

        JButton finishedButton = new JButton("Términer Vente");
        finishedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(finishedButton);
        finishedButton.addActionListener(e -> {
            Float value = null;
            do {
                try {
                    value = Float.parseFloat(JOptionPane.showInputDialog(this, "Introduire montant", "Montant", JOptionPane.INFORMATION_MESSAGE));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(this, "Vérifier le montant que vous avez saisi", "Montant invalide", JOptionPane.ERROR_MESSAGE);
                }
            } while (value == null || !caisse.finVente(value));
        });

        JButton cancelButton = new JButton("Annuler Vente");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(cancelButton);
        cancelButton.addActionListener(e -> {
            caisse.annulerVente();
        });

        String[] columnNames = {"Référence", "Quantité"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setPreferredSize(new Dimension(600, 450));

        add(headerPanel);
        add(inputPanel);
        add(addButton);
        add(scrollPane);
        add(footerPanel);

        pack();
    }

    public void setDateVente(Timestamp dateVente) {
        this.dateVente.setText("Date: " + dateVente.toString());
    }

    public void updateArticleList(Iterator<Map.Entry<String, Integer>> achats) {
        tableModel.setRowCount(0);
        if (achats != null) {
            while (achats.hasNext()) {
                Map.Entry<String, Integer> achat = achats.next();
                tableModel.addRow(new Object[]{achat.getKey(), achat.getValue()});
            }
        }
    }

    private void addArticleHandler(String reference, int quantity) {
        if (!reference.isEmpty()) {
            caisse.enregistrerArticle(reference, quantity);
            referenceField.setText("");
            quantitySpinner.setValue(1);
        } else {
            JOptionPane.showMessageDialog(this, "La référence ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
