package ma.fstm.ilisi.caisse.presentation;

import ma.fstm.ilisi.caisse.controlleur.Caisse;

import javax.swing.*;
import java.awt.*;

public class AuthForm extends JFrame {
    public AuthForm(Caisse caisse) {
        super("Caisse | Authentification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        add(usernameLabel, gbc);
        add(usernameField, gbc);
        add(passwordLabel, gbc);
        add(passwordField, gbc);
        add(loginButton, gbc);

        loginButton.addActionListener(e -> caisse.checkAuth(usernameField.getText(), new String(passwordField.getPassword())));
    }
}
