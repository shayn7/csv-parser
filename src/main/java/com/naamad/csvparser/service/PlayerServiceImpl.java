package com.naamad.csvparser.service;

import com.naamad.csvparser.dto.PlayerResponse;
import com.naamad.csvparser.model.Player;
import com.naamad.csvparser.repository.PlayerRepository;
import com.naamad.csvparser.utils.CsvReader;
import com.naamad.csvparser.utils.CsvWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final WebClient webClient;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    private final PlayerRepository playerRepository;


    public void getPlayers() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {

        List<Player> players = csvReader.parseFile();
        List<PlayerResponse> playersList = new ArrayList<>();

        for (Player p : players){
            PlayerResponse player = getPlayerResponse(p);
            playersList.add(player);
        }

        List<PlayerResponse> list = csvWriter.createFile(playersList, players);
        saveListOfPlayers(list.stream().map(this::mapToPlayer).collect(Collectors.toList()));

    }

    @Cacheable(cacheNames = {"playerCache"}, key = "#id")
    @Override
    public PlayerResponse getPlayerById(String id) {
        Optional<Player> player = playerRepository.findById(id);
        if(player.isEmpty()) throw new RuntimeException("player not found");
        return mapToPlayerResponse(player.get());
    }

    private PlayerResponse getPlayerResponse(Player p) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                .path(p.getId())
                .build())
                .retrieve()
                .bodyToMono(PlayerResponse.class)
                .block();
    }

    @Override
    public void saveListOfPlayers(Iterable<Player> playersList) {
        playerRepository.saveAll(playersList);
    }

    private Player mapToPlayer(PlayerResponse playerResponse) {
        return Player
                .builder()
                .id(String .valueOf(playerResponse.getId()))
                .first_name(playerResponse.getFirst_name())
                .last_name(playerResponse.getLast_name())
                .nick_name(playerResponse.getNick_name())
                .height_feet(playerResponse.getHeight_feet())
                .height_inches(playerResponse.getHeight_inches())
                .weight_pounds(playerResponse.getWeight_pounds())
                .position(playerResponse.getPosition())
                .team(playerResponse.getTeam())
                .build();
    }

    private PlayerResponse mapToPlayerResponse(Player player) {
        return PlayerResponse
                .builder()
                .id(Integer.parseInt(player.getId()))
                .first_name(player.getFirst_name())
                .last_name(player.getLast_name())
                .nick_name(player.getNick_name())
                .height_feet(player.getHeight_feet())
                .height_inches(player.getHeight_inches())
                .weight_pounds(player.getWeight_pounds())
                .position(player.getPosition())
                .team(player.getTeam())
                .build();
    }
}
