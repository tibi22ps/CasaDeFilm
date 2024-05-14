package com.example.casafilme.view.administrator;

import com.example.casafilme.Language.Language;
import com.example.casafilme.model.User;
import com.example.casafilme.controller.AdministratorController;
import com.example.casafilme.model.repository.BazaDateFilme;
import com.example.casafilme.model.repository.UserRepo;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Administrator extends JFrame {

    private JButton searchUserButton;
    private JButton addUserButton;
    private JButton updateUserButton;
    private JButton deleteUserButton;
    private JButton viewUserListButton;
    private JPanel AdministratorPage;
    private JTable table1;
    private JTextField byUsernameTextField;
    private JComboBox comboBox1;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JComboBox comboBox2;
    private JComboBox languageBox;
    private JLabel administratorPageLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel tipLabel;
    private JLabel byTipLabel;
    private JLabel byUsernameLabel;
    private AdministratorController administratorController;
    private Language language;


    public Administrator() {
        administratorController=new AdministratorController(new UserRepo(new BazaDateFilme().getConnection()),this);
        this.language=new Language();
        administratorUI();
        actionListeneri();
    }

        private void administratorUI(){
            JFrame jFrame2=new JFrame();
            jFrame2.setSize(1000,800);
            jFrame2.setContentPane(AdministratorPage);
            jFrame2.setVisible(true);
            jFrame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        private void actionListeneri(){
            viewUserListButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    administratorController.getAllUsers();
                }
            });
            addUserButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    administratorController.addUser();
                }
            });
            searchUserButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    administratorController.getUserByUsername();
                }
            });
            updateUserButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    administratorController.updateUser();
                }
            });
            deleteUserButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    administratorController.deleteUser();
                }
            });
            comboBox2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    administratorController.filterUsersByTip();
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

    public String getByUsername(){
        return byUsernameTextField.getText();
    }

    public String getUsername(){
        return usernameTextField.getText();
    }

    public String  getComboBox2() {
        return comboBox2.getSelectedItem().toString();
    }

    public String getPassword(){
        return passwordTextField.getText();
    }

    public String getTip(){
        return comboBox1.getSelectedItem().toString();
    }

    public DefaultTableModel convertUserToTableModel(List<User> useri) {
        String[] columnNames = {"Username", "Password", "Tip"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (User user : useri) {
            Object[] rowData = {
                    user.getUsername(),
                    user.getPassword(),
                    user.getTip()
            };
            model.addRow(rowData);
        }
        return model;
    }
public void setTableModel(DefaultTableModel model) {
    table1.setModel(model);
}
    public void updateLanguage() {
        administratorPageLabel.setText(language.getLanguageText("Administrator.label.administratorPageLabel"));
        usernameLabel.setText(language.getLanguageText("Administrator.label.usernameLabel"));
        tipLabel.setText(language.getLanguageText("Administrator.label.tipLabel"));
        passwordLabel.setText(language.getLanguageText("Administrator.label.passwordLabel"));
        byTipLabel.setText(language.getLanguageText("Administrator.label.byTipLabel"));
        searchUserButton.setText(language.getLanguageText("Administrator.button.searchUserButton"));
        updateUserButton.setText(language.getLanguageText("Administrator.button.updateUserButton"));
        deleteUserButton.setText(language.getLanguageText("Administrator.button.deleteUserButton"));
        byUsernameLabel.setText(language.getLanguageText("Administrator.label.byUsernameLabel"));
        addUserButton.setText(language.getLanguageText("Administrator.button.addUserButton"));
        viewUserListButton.setText(language.getLanguageText("Administrator.button.viewUserListButton"));
    }
}

