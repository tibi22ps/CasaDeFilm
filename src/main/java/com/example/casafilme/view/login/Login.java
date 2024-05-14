package com.example.casafilme.view.login;
import com.example.casafilme.Language.Language;
import com.example.casafilme.controller.LoginController;
import com.example.casafilme.model.repository.BazaDateFilme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Login {
    private JTextField usernameTextField;
    private JPanel LoginPage;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;
    private JComboBox languageBox;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JFrame frame;
    private LoginController loginController;
    private Language language;


    public Login() {
        this.loginController = new LoginController(new BazaDateFilme(), this);
        this.language=new Language();
        loginUI();
        actionListeners();
    }
        private void loginUI() {
            frame=new JFrame();
            frame.setSize(800,500);
            frame.setContentPane(LoginPage);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
        private void actionListeners(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean suc=loginController.autentificare();
                loginController.showLoginRes(suc);
                loginController.afisarePaginaUtilizator();
            }
        });
        languageBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (languageBox.getSelectedItem().toString().equals("en")){
                    language.setLanguage("en");
                    updateLanguage();
                }
                else if (languageBox.getSelectedItem().toString().equals("ro")){
                    language.setLanguage("ro");
                    updateLanguage();
                }
                else{
                    language.setLanguage("fr");
                    updateLanguage();
                }
            }
        });
    }
    public void updateLanguage() {
        loginButton.setText(language.getLanguageText("Login.button.loginButton"));
        usernameLabel.setText(language.getLanguageText("Login.label.usernameLabel"));
        passwordLabel.setText(language.getLanguageText("Login.label.passwordLabel"));
    }


    public String getUsername(){
        return usernameTextField.getText();
    }
    public String getPassword() {
        return new String(passwordPasswordField.getText());
    }
}
