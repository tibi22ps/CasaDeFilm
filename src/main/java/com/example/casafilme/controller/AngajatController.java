package com.example.casafilme.controller;

import com.example.casafilme.model.Film;
import com.example.casafilme.model.repository.FilmRepo;
import com.example.casafilme.view.angajat.Angajat;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AngajatController {
    private FilmRepo filmRepo;
    private Angajat angajatView;

    public AngajatController(FilmRepo filmRepo, Angajat angajatView){
        this.filmRepo=filmRepo;
        this.angajatView=angajatView;
    }

    public void sortareFilmByTip(){
        List<Film> films=filmRepo.sortFilmByTip();
        DefaultTableModel model=angajatView.convertFilmeToTableModel(films);
        angajatView.setTableModel(model);
    }

    public void sortareFilmByAn(){
        List<Film> films=filmRepo.sortFilmByAn();
        DefaultTableModel model=angajatView.convertFilmeToTableModel(films);
        angajatView.setTableModel(model);

    }

    public void filtrareFilmByTip(Film.Tip tip){
        List<Film> films=filmRepo.filterFilmByTip(tip);
        DefaultTableModel model=angajatView.convertFilmeToTableModel(films);
        angajatView.setTableModel(model);
    }

    public void filtrareFilmByCategorie(Film.Categorie categorie){
        List<Film> films=filmRepo.filterFilmByCategorie(categorie);
        DefaultTableModel model=angajatView.convertFilmeToTableModel(films);
        angajatView.setTableModel(model);
    }

    public void filtrareFilmByAn(int an){
        List<Film> films=filmRepo.filterFilmByAn(an);
        DefaultTableModel model=angajatView.convertFilmeToTableModel(films);
        angajatView.setTableModel(model);
    }

    public void salvareFilmeCaDoc() {
        filmRepo.saveFilmsAsDoc();
    }

    public void salvareFilmeCaCSV() {
        filmRepo.saveFilmsAsCSV();
    }

    public void salvareFilmeCaJSON() {
        filmRepo.saveFilmsAsJSON();
    }

    public void salvareFilmeCaXML() {
        filmRepo.saveFilmsAsXML();
    }
}
