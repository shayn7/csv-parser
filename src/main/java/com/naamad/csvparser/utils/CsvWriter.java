package com.naamad.csvparser.utils;

import com.naamad.csvparser.dto.PlayerResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvWriter {

    public void createFile(List<PlayerResponse> players) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
            Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/playersInfo.csv"));
            StatefulBeanToCsv<PlayerResponse> csvWriter = new StatefulBeanToCsvBuilder<PlayerResponse>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .withOrderedResults(false)
                    .build();

            List<PlayerResponse> listOfPlayers = new ArrayList<>();

            for(PlayerResponse playerResponse : players){

                PlayerResponse player = PlayerResponse.builder()
                        .id(playerResponse.getId())
                        .first_name(playerResponse.getFirst_name())
                        .last_name(playerResponse.getLast_name())
                        .nick_name(playerResponse.getNick_name())
                        .height_feet(playerResponse.getHeight_feet())
                        .height_inches(playerResponse.getHeight_inches())
                        .weight_pounds(playerResponse.getWeight_pounds())
                        .position(playerResponse.getPosition())
                        .team(playerResponse.getTeam())
                        .build();

                listOfPlayers.add(player);
            }
            csvWriter.write(listOfPlayers);
            writer.close();
    }
}
