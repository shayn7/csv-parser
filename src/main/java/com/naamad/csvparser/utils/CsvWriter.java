package com.naamad.csvparser.utils;

import com.naamad.csvparser.dto.PlayerResponse;
import com.naamad.csvparser.model.Player;
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
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CsvWriter {

    public List<PlayerResponse> createFile(List<PlayerResponse> players, List<Player> playerList) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/playersInfo.csv"));
        StatefulBeanToCsv<PlayerResponse> csvWriter = getPlayerResponseStatefulBeanToCsv(writer);
        Map<String, String> map = convertListToMap(playerList);
        List<PlayerResponse> listOfPlayers = new ArrayList<>();

        for (PlayerResponse playerResponse : players) {
            PlayerResponse player = getPlayerResponse(map, playerResponse);
            listOfPlayers.add(player);
        }
        csvWriter.write(listOfPlayers);
        writer.close();
        return listOfPlayers;
    }

    private Map<String, String> convertListToMap(List<Player> playerList) {
        return playerList
                .stream()
                .collect(Collectors.toMap(Player::getId, Player::getNick_name));
    }

    private PlayerResponse getPlayerResponse(Map<String, String> map, PlayerResponse playerResponse) {
        return PlayerResponse.builder()
                .id(playerResponse.getId())
                .first_name(playerResponse.getFirst_name())
                .last_name(playerResponse.getLast_name())
                .nick_name(map.get(String.valueOf(playerResponse.getId())))
                .height_feet(playerResponse.getHeight_feet())
                .height_inches(playerResponse.getHeight_inches())
                .weight_pounds(playerResponse.getWeight_pounds())
                .position(playerResponse.getPosition())
                .team(playerResponse.getTeam())
                .build();
    }

    private StatefulBeanToCsv<PlayerResponse> getPlayerResponseStatefulBeanToCsv(Writer writer) {
        return new StatefulBeanToCsvBuilder<PlayerResponse>(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                .withOrderedResults(false)
                .build();
    }
}
