package com.naamad.csvparser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private int id;
    private String abbreviation;
    private String city;
    private String conference;
    private String division;
    private String full_name;
    private String name;

}
