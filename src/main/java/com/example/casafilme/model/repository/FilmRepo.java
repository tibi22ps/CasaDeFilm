package com.example.casafilme.model.repository;
import com.example.casafilme.model.Film;
import com.example.casafilme.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmRepo {
        private Connection connection;

        public FilmRepo(Connection connection) {
            this.connection = connection;
        }

    //Operatii CRUD
    public void addFilm(Film film) {
        try {
            String query = "INSERT INTO film (titlu, tip, categorie, an, regizor, durata) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, film.getTitlu());
            statement.setString(2, film.getTip().name());
            statement.setString(3, film.getCategorie().name());
            statement.setInt(4, film.getAn());
            statement.setString(5, film.getRegizor());
            statement.setInt(6, film.getDurata());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0)
                System.out.println("Film adaugat cu succes!");;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Film neadaugat.");
        }
    }

    public Film getFilmById(int id) {
        Film film = null;
        try {
            String query = "SELECT * FROM film WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String titlu = resultSet.getString("titlu");
                Film.Tip tip = Film.Tip.valueOf(resultSet.getString("tip"));
                Film.Categorie categorie = Film.Categorie.valueOf(resultSet.getString("categorie"));
                int an = resultSet.getInt("an");
                String regizor = resultSet.getString("regizor");
                int durata = resultSet.getInt("durata");

                film = new Film(id, titlu, tip, categorie, an, regizor, durata);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }


    public void updateFilm(Film film) {
        try {
            String query = "UPDATE film SET titlu = ?, tip = ?, categorie = ?, an = ?, regizor = ?, durata = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, film.getTitlu());
            statement.setString(2, film.getTip().name());
            statement.setString(3, film.getCategorie().name());
            statement.setInt(4, film.getAn());
            statement.setString(5, film.getRegizor());
            statement.setInt(6, film.getDurata());
            statement.setInt(7, film.getId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0)
                System.out.println("Film actualizat cu succes!");;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Film neactualizat.");;
        }
    }

    public void deleteFilm(int id) {
        try {
            String query = "DELETE FROM film WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0)
                System.out.println("Film sters cu succes!");;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Film nesters.");;
        }
    }

    public List<Film> sortFilmByTip() {
        List<Film> filme = new ArrayList<>();
        try {
            String query = "SELECT * FROM film ORDER BY tip";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                filme.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }

    public List<Film> sortFilmByAn() {
        List<Film> filme = new ArrayList<>();
        try {
            String query = "SELECT * FROM film ORDER BY an";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                filme.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }


    public List<Film> filterFilmByTip(Film.Tip tip) {
        List<Film> filme = new ArrayList<>();
        try {
            String query = "SELECT * FROM film WHERE tip = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tip.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        tip,
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                filme.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }

    public List<Film> filterFilmByCategorie(Film.Categorie categorie) {
        List<Film> filme = new ArrayList<>();
        try {
            String query = "SELECT * FROM film WHERE categorie = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            if (categorie != null) {
                statement.setString(1, categorie.name());
            } else {
                statement.setString(1, null);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        categorie,
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                filme.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }

    public List<Film> filterFilmByAn(int an) {
        List<Film> filme = new ArrayList<>();
        try {
            String query = "SELECT * FROM film WHERE an = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, an);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        an,
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                filme.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }
    public List<Film> searchFilmByTitlu(String titlu) {
        List<Film> filme = new ArrayList<>();
        try {
            String query = "SELECT * FROM film WHERE titlu = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, titlu);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                filme.add(film);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }

    public void saveFilmsAsDoc() {
        List<Film> films = new ArrayList<>();
        try {
            String query = "SELECT * FROM film";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                films.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching films: " + e.getMessage());
            return;
        }

        String fileName = "films.docx";
        XWPFDocument document = new XWPFDocument();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            for (Film film : films) {
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText("Id: " + film.getId());
                run.addBreak();
                run.setText("Titlu: " + film.getTitlu());
                run.addBreak();
                run.setText("Tip: " + film.getTip());
                run.addBreak();
                run.setText("Categorie: " + film.getCategorie());
                run.addBreak();
                run.setText("An: " + film.getAn());
                run.addBreak();
                run.setText("Regizor: " + film.getRegizor());
                run.addBreak();
                run.setText("Durata: " + film.getDurata());
                run.addBreak();
                run.addBreak();
            }
            document.write(out);
            System.out.println("Doc file saved successfully!");
            Desktop.getDesktop().open(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving Doc file: " + e.getMessage());
        }
    }

    public void saveFilmsAsCSV() {
        List<Film> films = new ArrayList<>();
        try {
            String query = "SELECT * FROM film";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                films.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching films: " + e.getMessage());
        }

        File csvFile = new File("films.csv");
        try (FileWriter writer = new FileWriter("films.csv")) {
            writer.append("Id\n");
            writer.append("Titlu\n");
            writer.append("Tip\n");
            writer.append("Categorie\n");
            writer.append("An\n");
            writer.append("Regizor\n");
            writer.append("Durata\n");

            for (Film film : films) {
                writer.append(film.getId() + "\n");
                writer.append(film.getTitlu() + "\n");
                writer.append(film.getTip() + "\n");
                writer.append(film.getCategorie() + "\n");
                writer.append(film.getAn() + "\n");
                writer.append(film.getRegizor() + "\n");
                writer.append(film.getDurata() + "\n\n");
            }

            System.out.println("CSV file saved successfully!");
            Desktop.getDesktop().open(csvFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error saving CSV file: " + ex.getMessage());
        }
    }


    public void saveFilmsAsJSON() {
        List<Film> films = new ArrayList<>();
        try {
            String query = "SELECT * FROM film";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                films.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching films: " + e.getMessage());
            return;
        }

        String fileName = "films.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(films);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
            System.out.println("JSON file saved successfully!");
            Desktop.getDesktop().open(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving JSON file: " + e.getMessage());
        }
    }

    public void saveFilmsAsXML() {
        String fileName = "films.xml"; // Numele implicit al fișierului
        List<Film> films = new ArrayList<>();
        try {
            String query = "SELECT * FROM film";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("titlu"),
                        Film.Tip.valueOf(resultSet.getString("tip")),
                        Film.Categorie.valueOf(resultSet.getString("categorie")),
                        resultSet.getInt("an"),
                        resultSet.getString("regizor"),
                        resultSet.getInt("durata")
                );
                films.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching films: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
            writer.append("<films>\n");
            for (Film film : films) {
                writer.append("  <film>\n");
                writer.append("    <id>").append(String.valueOf(film.getId())).append("</id>\n");
                writer.append("    <titlu>").append(film.getTitlu()).append("</titlu>\n");
                writer.append("    <tip>").append(film.getTip().name()).append("</tip>\n");
                writer.append("    <categorie>").append(film.getCategorie().name()).append("</categorie>\n");
                writer.append("    <an>").append(String.valueOf(film.getAn())).append("</an>\n");
                writer.append("    <regizor>").append(film.getRegizor()).append("</regizor>\n");
                writer.append("    <durata>").append(String.valueOf(film.getDurata())).append("</durata>\n");
                writer.append("  </film>\n");
            }
            writer.append("</films>\n");
            System.out.println("XML file saved successfully!");
            Desktop.getDesktop().open(new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error saving XML file: " + ex.getMessage());
        }
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try {
            String query = "SELECT * FROM film";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titlu = resultSet.getString("titlu");
                Film.Tip tip = Film.Tip.valueOf(resultSet.getString("tip"));
                Film.Categorie categorie = Film.Categorie.valueOf(resultSet.getString("categorie"));
                int an = resultSet.getInt("an");
                String regizor = resultSet.getString("regizor");
                int durata = resultSet.getInt("durata");
                String image=resultSet.getString("image");
                Film film = new Film(id, titlu, tip, categorie, an, regizor, durata, image);
                films.add(film);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }
}
