package com.naamad.csvparser.utils;

import com.naamad.csvparser.model.Player;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvReader {

    public List<Player> parseFile() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/players.csv"));
        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withType(Player.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<Player> players = csvToBean.parse();
        reader.close();
        return players;
    }
}
