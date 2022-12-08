package com.naamad.csvparser.dto;

import com.naamad.csvparser.model.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {

    private int id;
    private String nick_name;
    private String first_name;
    private String last_name;
    private String position;
    private int height_feet;
    private int height_inches;
    private int weight_pounds;
    private Team team;
}
