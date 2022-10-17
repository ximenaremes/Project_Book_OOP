package org.company;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Fisier {
    public static void writeToFile(List<Carte>ListaCarte) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        File file = new File("src/main/resources/ListaCarte.json");

        try {
            writer.writeValue(file,ListaCarte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Carte> readFromFile() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/ListaCarte.json")));
            List<Carte> subscriptionPlan = mapper.readValue(json, new TypeReference<List<Carte>>(){});
            return subscriptionPlan;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
