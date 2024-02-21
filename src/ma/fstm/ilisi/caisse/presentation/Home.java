package ma.fstm.ilisi.caisse.presentation;

import ma.fstm.ilisi.caisse.controlleur.Caisse;
import ma.fstm.ilisi.caisse.metier.bo.LigneVente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.util.Iterator;

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

        String[] columnNames = {"Référence", "Designation", "Prix", "Quantité", "Sous total"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable itemTable = new JTable(tableModel);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemTable.setFillsViewportHeight(true);
        itemTable.setDefaultEditor(Object.class, null);
        itemTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setPreferredSize(new Dimension(600, 450));

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

        JButton cancelProductButton = new JButton("Annuler Produit");
        cancelProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(cancelProductButton);
        cancelProductButton.addActionListener(e -> {
            String reference = JOptionPane.showInputDialog(this, "Entrer reference du produit");
            if (reference != null)
                caisse.annulerProduit(reference);
        });

        JButton updateQuantityButton = new JButton("Modifier quantitée");
        updateQuantityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(updateQuantityButton);
        updateQuantityButton.addActionListener(e -> {
            int row = itemTable.getSelectedRow();
            if (row != -1) {
                int currentQuantity = (Integer) tableModel.getValueAt(row, 3);
                JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(currentQuantity, 1, Integer.MAX_VALUE, 1));
                quantitySpinner.setPreferredSize(new Dimension(100, 20));

                if (JOptionPane.showConfirmDialog(null, quantitySpinner, "Enter New Quantity", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
                    caisse.modifyQuantity((String) tableModel.getValueAt(row, 0), (Integer) quantitySpinner.getValue());
            }
        });

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

    public void updateArticleList(Iterator<LigneVente> ligneVenteIterator) {
        tableModel.setRowCount(0);
        if (ligneVenteIterator != null) {
            while (ligneVenteIterator.hasNext()) {
                LigneVente ldv = ligneVenteIterator.next();
                tableModel.addRow(new Object[]{ldv.getReference(), ldv.getDesignation(), ldv.getPrix_unitaire(), ldv.getQuantite(), ldv.getSubTotal()});
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
