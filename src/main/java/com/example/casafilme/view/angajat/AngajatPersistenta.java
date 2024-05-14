package com.example.casafilme.view.angajat;

import com.example.casafilme.Language.Language;
import com.example.casafilme.model.Film;
import com.example.casafilme.controller.AngajatPersistentaController;
import com.example.casafilme.model.repository.BazaDateFilme;
import com.example.casafilme.model.repository.FilmRepo;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AngajatPersistenta extends JFrame {
    private JPanel AngajatPersistentaPage;
    private JTextField titluTextField;
    private JTextField anTextField;
    private JTextField regizorTextField;
    private JTextField durataTextField;
    private JTable table1;
    private JButton addFilmButton;
    private JButton updateFilmButton;
    private JButton findFilmButton;
    private JButton deleteFilmButton;
    private JTextField idTextField;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton searchTitleButton;
    private JComboBox languageBox;
    private JLabel angajatPersistentaPageLabel;
    private JLabel titluLabel;
    private JLabel tipLabel;
    private JLabel anLabel;
    private JLabel categorieLabel;
    private JLabel regizorLabel;
    private JLabel durataLabel;
    private JLabel idTitluLabel;
    private AngajatPersistentaController angajatPersistentaController;
    private Language language;

    public AngajatPersistenta() {
        this.angajatPersistentaController = new AngajatPersistentaController(new FilmRepo(new BazaDateFilme().getConnection()), this);
        this.language=new Language();
        angajatPersistentaUI();
        actionListeneri();
    }
        private void angajatPersistentaUI() {
            JFrame jFrame2 = new JFrame();
            jFrame2.setSize(1000, 800);
            jFrame2.setContentPane(AngajatPersistentaPage);
            jFrame2.setVisible(true);
            jFrame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        private void actionListeneri () {
            addFilmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    angajatPersistentaController.addFilm();
                }
            });
        updateFilmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatPersistentaController.updateFilm();
            }
        });
        findFilmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatPersistentaController.findFilmById();
            }
        });
        deleteFilmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatPersistentaController.deleteFilm();
            }
        });
        searchTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angajatPersistentaController.searchFilmByTitle();
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

    private void setSmallTextField(JTextField textField) {
        int width = 50;
        int height = 30;
        Dimension dim = new Dimension(width, height);
        textField.setPreferredSize(dim);
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

public int getIdValue(){
    return Integer.parseInt(idTextField.getText());
}
public String getByTitlu(){
        return idTextField.getText();
}
    public String getTitluTextFieldValue() {
        return titluTextField.getText();
    }

    public String getTipComboBoxValue() {
        return comboBox1.getSelectedItem().toString();
    }

    public String getCategorieComboBoxValue() {
        return comboBox2.getSelectedItem().toString();
    }

    public int getAnTextFieldValue() {
        try {
            return Integer.parseInt(anTextField.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getRegizorTextFieldValue() {
        return regizorTextField.getText();
    }

    public int getDurataTextFieldValue() {
        try {
            return Integer.parseInt(durataTextField.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTableModel(DefaultTableModel model) {
        table1.setModel(model);
    }

    public void updateLanguage() {
        angajatPersistentaPageLabel.setText(language.getLanguageText("AngajatPeristenta.label.angajatPersistentaPageLabel"));
        titluLabel.setText(language.getLanguageText("AngajatPeristenta.label.titluLabel"));
        tipLabel.setText(language.getLanguageText("AngajatPeristenta.label.tipLabel"));
        anLabel.setText(language.getLanguageText("AngajatPeristenta.label.anLabel"));
        categorieLabel.setText(language.getLanguageText("AngajatPeristenta.label.categorieLabel"));
        regizorLabel.setText(language.getLanguageText("AngajatPeristenta.label.regizorLabel"));
        durataLabel.setText(language.getLanguageText("AngajatPeristenta.label.durataLabel"));
        addFilmButton.setText(language.getLanguageText("AngajatPeristenta.button.addFilmButton"));
        updateFilmButton.setText(language.getLanguageText("AngajatPeristenta.button.updateFilmButton"));
        deleteFilmButton.setText(language.getLanguageText("AngajatPeristenta.button.deleteFilmButton"));
        findFilmButton.setText(language.getLanguageText("AngajatPeristenta.button.findFilmButton"));
        idTitluLabel.setText(language.getLanguageText("AngajatPeristenta.label.idTitluLabel"));
        searchTitleButton.setText(language.getLanguageText("AngajatPeristenta.button.searchTitleButton"));

    }
}
