package com.example.casafilme.controller;

import com.example.casafilme.model.Film;
import com.example.casafilme.model.repository.FilmRepo;
import com.example.casafilme.view.angajat.AngajatPersistenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AngajatPersistentaController {
    private FilmRepo filmRepo;
    private AngajatPersistenta angajatPersistentaView;

    public AngajatPersistentaController(FilmRepo filmRepo, AngajatPersistenta angajatPersistentaView) {
        this.filmRepo = filmRepo;
        this.angajatPersistentaView = angajatPersistentaView;
    }

    public void addFilm() {
        String titlu = angajatPersistentaView.getTitluTextFieldValue();
        String tip = angajatPersistentaView.getTipComboBoxValue();
        String categorie = angajatPersistentaView.getCategorieComboBoxValue();
        int an = angajatPersistentaView.getAnTextFieldValue();
        String regizor = angajatPersistentaView.getRegizorTextFieldValue();
        int durata = angajatPersistentaView.getDurataTextFieldValue();

        Film film = new Film(titlu, Film.Tip.valueOf(tip), Film.Categorie.valueOf(categorie), an, regizor, durata);
        filmRepo.addFilm(film);
    }

    public void updateFilm() {
        int id = angajatPersistentaView.getIdValue();
        Film filmToUpdate = filmRepo.getFilmById(id);
        if (filmToUpdate != null) {
            filmToUpdate.setTitlu(angajatPersistentaView.getTitluTextFieldValue());
            filmToUpdate.setTip(Film.Tip.valueOf(angajatPersistentaView.getTipComboBoxValue()));
            filmToUpdate.setCategorie(Film.Categorie.valueOf(angajatPersistentaView.getCategorieComboBoxValue()));
            filmToUpdate.setAn(angajatPersistentaView.getAnTextFieldValue());
            filmToUpdate.setRegizor(angajatPersistentaView.getRegizorTextFieldValue());
            filmToUpdate.setDurata(angajatPersistentaView.getDurataTextFieldValue());
            filmRepo.updateFilm(filmToUpdate);
        } else {
            JOptionPane.showMessageDialog(null, "Filmul cu ID-ul specificat nu a fost găsit.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteFilm() {
        int id = angajatPersistentaView.getIdValue();
        filmRepo.deleteFilm(id);
    }

    public void findFilmById() {
        int id = angajatPersistentaView.getIdValue();
        Film film = filmRepo.getFilmById(id);
        DefaultTableModel model = angajatPersistentaView.convertFilmeToTableModel(List.of(film));
        angajatPersistentaView.setTableModel(model);
    }

    public void searchFilmByTitle(){
        String titlu=angajatPersistentaView.getByTitlu();
        List<Film> filme=filmRepo.searchFilmByTitlu(titlu);
        DefaultTableModel model = angajatPersistentaView.convertFilmeToTableModel(filme);
        angajatPersistentaView.setTableModel(model);
    }

}
