package com.example.casafilme.controller;

import com.example.casafilme.model.Film;
import com.example.casafilme.model.repository.FilmRepo;
import com.example.casafilme.view.manager.Manager;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ManagerController {
        private FilmRepo filmRepo;
        private Manager managerView;

        public ManagerController(FilmRepo filmRepo, Manager managerView ){
            this.filmRepo=filmRepo;
            this.managerView =managerView;
        }

        public void sortareFilmByTip(){
            List<Film> films=filmRepo.sortFilmByTip();
            DefaultTableModel model=managerView.convertFilmeToTableModel(films);
            managerView.setTableModel(model);
        }

        public void sortareFilmByAn(){
            List<Film> films=filmRepo.sortFilmByAn();
            DefaultTableModel model=managerView.convertFilmeToTableModel(films);
            managerView.setTableModel(model);
        }

        public void filtrareFilmByTip(Film.Tip tip){
            List<Film> films=filmRepo.filterFilmByTip(tip);
            DefaultTableModel model=managerView.convertFilmeToTableModel(films);
            managerView.setTableModel(model);
        }

        public void filtrareFilmByCategorie(Film.Categorie categorie){
            List<Film> films=filmRepo.filterFilmByCategorie(categorie);
            DefaultTableModel model=managerView.convertFilmeToTableModel(films);
            managerView.setTableModel(model);
        }

        public void filtrareFilmByAn(int an){
            List<Film> films=filmRepo.filterFilmByAn(an);
            DefaultTableModel model=managerView.convertFilmeToTableModel(films);
            managerView.setTableModel(model);
        }

        public void cautareByTitlu(String titlu){
            List<Film> films= filmRepo.searchFilmByTitlu(titlu);
            DefaultTableModel model=managerView.convertFilmeToTableModel(films);
            managerView.setTableModel(model);
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
