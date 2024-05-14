package com.example.casafilme.view.images;

import javax.swing.*;

import com.example.casafilme.model.Film;
import com.example.casafilme.model.repository.FilmRepo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Image extends JFrame{
    private JPanel imagePage;
    private JLabel imageLabel;
    private JLabel titleLabel;
    private JButton rightButton;
    private JButton leftButton;
    private List<Film> films;
    private FilmRepo filmRepo;
    private int currentIndex = 0;

    public Image(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
        films=filmRepo.getAllFilms();

        JFrame jFrame=new JFrame();
        jFrame.setSize(1000,800);
        jFrame.setContentPane(imagePage);
        jFrame.setVisible(true);
        setTitle("Film is ART");

        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        updateImage();
        rightButton.addActionListener(this::rightButtonActionPerformed);
        leftButton.addActionListener(this::leftButtonActionPerformed);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void updateImage() {
        if (!films.isEmpty()) {
            Film currentFilm = films.get(currentIndex);
            ImageIcon icon = new ImageIcon(currentFilm.getImage());
            imageLabel.setIcon(icon);
            titleLabel.setText(currentFilm.getTitlu());
            leftButton.setEnabled(currentIndex > 0);
            rightButton.setEnabled(currentIndex < films.size() - 1);
        } else {
            imageLabel.setIcon(null);
            titleLabel.setText("No films available");
            leftButton.setEnabled(false);
            rightButton.setEnabled(false);
        }
    }

    private void rightButtonActionPerformed(ActionEvent e) {
        if (currentIndex < films.size() - 1) {
            currentIndex++;
            updateImage();
        }
    }

    private void leftButtonActionPerformed(ActionEvent e) {
        if (currentIndex > 0) {
            currentIndex--;
            updateImage();
        }
    }

}

