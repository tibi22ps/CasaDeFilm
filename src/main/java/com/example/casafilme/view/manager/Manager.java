package com.example.casafilme.view.manager;

import com.example.casafilme.Language.Language;
import com.example.casafilme.model.Film;
import com.example.casafilme.controller.ManagerController;
import com.example.casafilme.model.repository.FilmRepo;
import com.example.casafilme.view.InelarGraphic;
import com.example.casafilme.view.LinearGraphic;
import com.example.casafilme.view.RadialGraphic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class Manager extends JFrame {
    private JButton sortareAnButton;
    private JPanel ManagerPage;
    private JButton sortareTipButton;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField anTextField;
    private JButton saveXMLButton;
    private JButton saveDOCButton;
    private JButton saveCSVButton;
    private JButton saveJSONButton;
    private JButton searchTitleButton;
    private JTextField textField1;
    private JComboBox languageBox;
    private JLabel managerPageLabel;
    private JLabel filtrareLabel;
    private JLabel tipLabel;
    private JLabel categorieLabel;
    private JLabel anLabel;
    private JButton graficeButton;
    private ManagerController managerController;
    private Language language;


    public Manager(Connection connection) {
        this.managerController = new ManagerController(new FilmRepo(connection), this);
        this.language=new Language();
        managerUI();
        actionListeners();
    }
    private void managerUI() {
        JFrame jFrame2 = new JFrame();
        jFrame2.setSize(800, 500);
        jFrame2.setContentPane(ManagerPage);
        jFrame2.setVisible(true);
        jFrame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void actionListeners(){
        sortareTipButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                managerController.sortareFilmByTip();
            }
        });
        sortareAnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerController.sortareFilmByAn();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTip=(String)comboBox1.getSelectedItem();
                managerController.filtrareFilmByTip(Film.Tip.valueOf(selectedTip));
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategorie=(String) comboBox2.getSelectedItem();
                managerController.filtrareFilmByCategorie(Film.Categorie.valueOf(selectedCategorie));
            }
        });
        anTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String an=(String)anTextField.getText();
                managerController.filtrareFilmByAn(Integer.parseInt(an));
            }
        });
        saveJSONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {managerController.salvareFilmeCaJSON();
            }
        });
        saveCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerController.salvareFilmeCaCSV();
            }
        });
        saveDOCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerController.salvareFilmeCaDoc();
            }
        });
        saveXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerController.salvareFilmeCaXML();
            }
        });
        searchTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titlu=textField1.getText();
                managerController.cautareByTitlu(titlu);
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
        graficeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinearGraphic linearGraphic=new LinearGraphic();
                InelarGraphic inelarGraphic=new InelarGraphic();
                RadialGraphic radialGraphic=new RadialGraphic();
            }
        });
    }
    public void setTableModel(DefaultTableModel model) {
        table1.setModel(model);
    }


    public DefaultTableModel convertFilmeToTableModel(List<Film> filme) {
        String[] columnNames = {"ID", "Titlu", "Tip", "Categorie", "An", "Regizor", "Durata"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Film film : filme) {
            Object[] rowData = {
                    film.getId(),
                    film.getTitlu(),
                    film.getTip(),
                    film.getCategorie(),
                    film.getAn(),
                    film.getRegizor(),
                    film.getDurata()
            };
            model.addRow(rowData);
        }
        return model;
    }

    public void updateLanguage() {
        managerPageLabel.setText(language.getLanguageText("Angajat.label.angajatPageLabel"));
        sortareTipButton.setText(language.getLanguageText("Angajat.button.sortareTipButton"));
        sortareAnButton.setText(language.getLanguageText("Angajat.button.sortareAnButton"));
        filtrareLabel.setText(language.getLanguageText("Angajat.label.filtrareLabel"));
        tipLabel.setText(language.getLanguageText("Angajat.label.tipLabel"));
        categorieLabel.setText(language.getLanguageText("Angajat.label.categorieLabel"));
        anLabel.setText(language.getLanguageText("Angajat.label.anLabel"));
        searchTitleButton.setText(language.getLanguageText("AngajatPeristenta.button.searchTitleButton"));
        graficeButton.setText(language.getLanguageText("Manager.button.graficeButton"));
    }
}

