package com.naamad.csvparser.service;

import com.naamad.csvparser.dto.PlayerResponse;
import com.naamad.csvparser.model.Player;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.Optional;

public interface PlayerService {

    void saveListOfPlayers(Iterable<Player> playersList);

    void getPlayers() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException;

    PlayerResponse getPlayerById(String id);
}
