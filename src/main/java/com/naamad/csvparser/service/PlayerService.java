package com.naamad.csvparser.service;

import com.naamad.csvparser.dto.PlayerResponse;
import com.naamad.csvparser.model.Player;
import com.naamad.csvparser.utils.CsvReader;
import com.naamad.csvparser.utils.CsvWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerService {

    private final WebClient webClient;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;


    public void getPlayers() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {

        List<Player> players = csvReader.parseFile();
        List<PlayerResponse> playersList = new ArrayList<>();

        for (Player p : players){
            PlayerResponse player = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(p.getId())
                            .build())
                    .retrieve()
                    .bodyToMono(PlayerResponse.class)
                    .block();
            playersList.add(player);
        }

        csvWriter.createFile(playersList);
    }
}
