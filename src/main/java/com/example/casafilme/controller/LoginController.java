package com.example.casafilme.controller;

import com.example.casafilme.model.User;
import com.example.casafilme.model.repository.BazaDateFilme;
import com.example.casafilme.view.administrator.Administrator;
import com.example.casafilme.view.angajat.Angajat;
import com.example.casafilme.view.login.Login;
import com.example.casafilme.view.manager.Manager;

public class LoginController {
    private BazaDateFilme bazaDateFilme;
    private Login loginView;

    public LoginController(BazaDateFilme bazaDateFilme,Login loginView ) {
        this.bazaDateFilme = bazaDateFilme;
        this.loginView=loginView;
    }

    public boolean autentificare() {
        String username=loginView.getUsername();
        String password=loginView.getPassword();
        return bazaDateFilme.autentificareUser(username, password);
    }

    public void showLoginRes(boolean suc){
        bazaDateFilme.showLoginResult(suc);
    }

    public void afisarePaginaUtilizator() {
        String username=loginView.getUsername();
        String password=loginView.getPassword();
        User userAuth= bazaDateFilme.getAuthenticatedUser(username,password);
        if(userAuth!=null) {
            switch (userAuth.getTip()) {
                case Angajat:
                    Angajat angajat = new Angajat();
                    break;
                case Manager:
                    Manager manager = new Manager(bazaDateFilme.connection);
                    break;
                case Administrator:
                    Administrator administrator = new Administrator();
                    break;
                default:
                    System.out.println("Tip de utilizator invalid");
            }
        }
        else{
            System.out.println("Logare nereusita");
        }
    }
}
