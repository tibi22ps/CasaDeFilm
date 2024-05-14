package com.example.casafilme.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.casafilme.model.Film;
import com.example.casafilme.model.repository.BazaDateFilme;
import com.example.casafilme.model.repository.FilmRepo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class LinearGraphic extends JFrame {
    List<Film> filme=new ArrayList<>();
    public LinearGraphic() {
        FilmRepo filmRepo=new FilmRepo(new BazaDateFilme().getConnection());
        filme=filmRepo.getAllFilms();
        setTitle("Grafic Linear");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        Map<Film.Categorie, Long> countByCategory = filme.stream()
                .collect(Collectors.groupingBy(Film::getCategorie, Collectors.counting()));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Film.Categorie, Long> entry : countByCategory.entrySet()) {
            dataset.addValue(entry.getValue(), "Filme", entry.getKey().toString());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Numar de Filme pe Categorii",
                "Categorie",
                "Numar de Filme",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        contentPane.add(chartPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
