package com.example.casafilme.view.angajat;

import com.example.casafilme.Language.Language;
import com.example.casafilme.model.Film;
import com.example.casafilme.controller.AngajatController;
import com.example.casafilme.model.repository.BazaDateFilme;
import com.example.casafilme.model.repository.FilmRepo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Angajat extends JFrame{

    private JPanel AngajatPage;
    private JButton sortareAnButton;
    private JButton persistentaFilmeButton;
    private JButton sortareTipButton;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField anTextField;
    private JButton saveCSVButton;
    private JButton saveJSONButton;
    private JButton saveXMLButton;
    private JButton saveDOCButton;
    private JComboBox languageBox;
    private JLabel filtrareLabel;
    private JLabel tipLabel;
    private JLabel categorieLabel;
    private JLabel anLabel;
    private JLabel saveLabel;
    private JLabel angajatPageLabel;
    private JButton picButton;
    private AngajatController angajatController;
    private Language language;
    private Image images;


    public Angajat() {
        this.angajatController = new AngajatController(new FilmRepo(new BazaDateFilme().getConnection()),this);
        this.language=new Language();
        angajatUI();
        actionListeners();
    }
    private void angajatUI() {
        JFrame jFrame2 = new JFrame();
        jFrame2.setSize(1000, 800);
        jFrame2.setContentPane(AngajatPage);
        jFrame2.setVisible(true);
        jFrame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void actionListeners(){
        sortareTipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatController.sortareFilmByTip();
            }
        });
        sortareAnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatController.sortareFilmByAn();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTip = (String) comboBox1.getSelectedItem();
                angajatController.filtrareFilmByTip(Film.Tip.valueOf(selectedTip));
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategorie = (String) comboBox2.getSelectedItem();
                angajatController.filtrareFilmByCategorie(Film.Categorie.valueOf(selectedCategorie));
            }
        });
        anTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String an = (String) anTextField.getText();
                angajatController.filtrareFilmByAn(Integer.parseInt(an));
            }
        });
            persistentaFilmeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AngajatPersistenta angajatPersistenta = new AngajatPersistenta();
            }
        });
        picButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                com.example.casafilme.view.images.Image images = new com.example.casafilme.view.images.Image(new FilmRepo(new BazaDateFilme().getConnection()));
            }
        });
        saveCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatController.salvareFilmeCaCSV();
            }
        });
        saveXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatController.salvareFilmeCaXML();
            }
        });
        saveDOCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatController.salvareFilmeCaDoc();
            }
        });
        saveJSONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatController.salvareFilmeCaJSON();
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

    public void setTableModel(DefaultTableModel model) {
        table1.setModel(model);
    }

    public void updateLanguage() {
        angajatPageLabel.setText(language.getLanguageText("Angajat.label.angajatPageLabel"));
        sortareTipButton.setText(language.getLanguageText("Angajat.button.sortareTipButton"));
        sortareAnButton.setText(language.getLanguageText("Angajat.button.sortareAnButton"));
        filtrareLabel.setText(language.getLanguageText("Angajat.label.filtrareLabel"));
        tipLabel.setText(language.getLanguageText("Angajat.label.tipLabel"));
        categorieLabel.setText(language.getLanguageText("Angajat.label.categorieLabel"));
        anLabel.setText(language.getLanguageText("Angajat.label.anLabel"));
        saveLabel.setText(language.getLanguageText("Angajat.label.saveLabel"));
        persistentaFilmeButton.setText(language.getLanguageText("Angajat.button.persistentaFilmeButton"));

    }

}
