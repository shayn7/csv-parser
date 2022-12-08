package com.naamad.csvparser.controller;

import com.naamad.csvparser.dto.PlayerResponse;
import com.naamad.csvparser.service.PlayerService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

//    @GetMapping()
//    @ResponseBody
//    public List<PlayerResponse> getPlayers(HttpServletResponse response){
//        response.setContentType("text/csv");
//        return playerService.getPlayers();
//    }

    @GetMapping(produces = "text/csv")
    public ResponseEntity getPlayers() {
        try {
            playerService.getPlayers();
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            throw new RuntimeException("could not generate csv file",e);
        }
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=playersInfo.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new FileSystemResource("src/main/resources/playersInfo.csv"));
    }
}