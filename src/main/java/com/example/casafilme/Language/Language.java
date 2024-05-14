package com.example.casafilme.Language;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class Language {
        private List<Observer> observers = new ArrayList<>();
        private String currentLanguage = "ro"; //default
        private Map<String, String> languageTextMap = new HashMap<>();

        public Language() {
            loadLanguage(currentLanguage);
        }

        public void setLanguage(String language) {
            if (!language.equals(currentLanguage)) {
                loadLanguage(language);
                notifyObservers();
            }
        }

        private void loadLanguage(String language) {
            String filePath = getLanguageFilePath(language);
            try {
                loadLanguageTextMap(filePath);
                currentLanguage = language;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String getLanguageFilePath(String language) {
            return "src/main/java/com/example/casafilme/Language/" + language.toLowerCase() + ".txt";
        }

        private void loadLanguageTextMap(String filePath) throws IOException {
            languageTextMap.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        languageTextMap.put(parts[0].trim(), parts[1].trim());
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Nu s-a putut găsi fișierul: " + filePath);
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Eroare la citirea din fișierul: " + filePath);
                e.printStackTrace();
            }
        }

        private void notifyObservers() {
            for (Observer observer : observers) {
                observer.updateLanguage();
            }
        }

        public String getLanguageText(String key) {
            return languageTextMap.getOrDefault(key, "404 " + key);
        }
    }
