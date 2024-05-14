package com.example.casafilme.view;

import javax.swing.*;
import java.awt.*;

import com.example.casafilme.model.Film;
import com.example.casafilme.model.repository.BazaDateFilme;
import com.example.casafilme.model.repository.FilmRepo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

    public class InelarGraphic extends JFrame {
        private List<Film> filme=new ArrayList<>();

        public InelarGraphic() {
            FilmRepo filmRepo=new FilmRepo(new BazaDateFilme().getConnection());
            this.filme = filmRepo.getAllFilms();
            setTitle("Grafic Inelar");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel contentPane = new JPanel(new BorderLayout());
            setContentPane(contentPane);

            Map<Film.Categorie, Long> countByCategory = filme.stream()
                    .collect(Collectors.groupingBy(Film::getCategorie, Collectors.counting()));

            DefaultPieDataset dataset = new DefaultPieDataset();
            for (Map.Entry<Film.Categorie, Long> entry : countByCategory.entrySet()) {
                dataset.setValue(entry.getKey().toString(), entry.getValue());
            }

            JFreeChart chart = ChartFactory.createRingChart(
                    "Numar de Filme pe Categorii",
                    dataset,
                    true, // afișează legenda
                    true,
                    false
            );

            ChartPanel chartPanel = new ChartPanel(chart);
            contentPane.add(chartPanel, BorderLayout.CENTER);

            setVisible(true);
        }
    }
