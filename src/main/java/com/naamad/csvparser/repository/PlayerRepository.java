package com.naamad.csvparser.repository;

import com.naamad.csvparser.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
}
