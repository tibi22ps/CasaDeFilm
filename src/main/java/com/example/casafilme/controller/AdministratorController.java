package com.example.casafilme.controller;

import com.example.casafilme.model.User;
import com.example.casafilme.model.repository.UserRepo;
import com.example.casafilme.view.administrator.Administrator;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class AdministratorController {
    private UserRepo userRepo;
    private Administrator administratorView;

    public AdministratorController(UserRepo userRepo,Administrator administratorView){
        this.userRepo=userRepo;
        this.administratorView=administratorView;
    }

    public void addUser() {
        User user=new User(administratorView.getUsername(), administratorView.getPassword(), User.Tip.valueOf(administratorView.getTip()));
        boolean success = userRepo.addUser(user);
        if (success) {
            System.out.println("User adăugat cu succes!");
        } else {
            System.out.println("Eroare la adăugarea utilizatorului.");
        }
    }

    public void updateUser() {
        User newuser=new User(administratorView.getByUsername(), administratorView.getPassword(), User.Tip.valueOf(administratorView.getTip()));
        boolean success = userRepo.updateUser(newuser);
        if (success) {
            System.out.println("User actualizat cu succes!");
        } else {
            System.out.println("Eroare la actualizarea utilizatorului.");
        }
    }

    public void deleteUser() {

        boolean success = userRepo.deleteUser(administratorView.getByUsername());
        if (success) {
            System.out.println("User șters cu succes!");
        } else {
            System.out.println("Eroare la ștergerea utilizatorului.");
        }
    }

    public void getAllUsers() {
        List<User> users = userRepo.getAllUsers();
        DefaultTableModel model=administratorView.convertUserToTableModel(users);
        administratorView.setTableModel(model);

    }

    public void getUserByUsername() {
        List<User> users=new ArrayList<>();
        String username=administratorView.getByUsername();
        if(username!=null) {
            User user = userRepo.getUserByUsername(administratorView.getByUsername());
            users.add(user);
            DefaultTableModel model = administratorView.convertUserToTableModel(users);
            administratorView.setTableModel(model);
        }
        else{
            System.out.println("Userul nu exista!");
        }
    }

    public void filterUsersByTip() {
        String tip = administratorView.getComboBox2();
        List<User> users = userRepo.filterUsersByTip(tip);
        DefaultTableModel model=administratorView.convertUserToTableModel(users);
        administratorView.setTableModel(model);
    }
}
