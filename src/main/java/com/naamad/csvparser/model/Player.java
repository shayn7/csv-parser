package com.naamad.csvparser.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "player")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {


    @CsvBindByName
    @Id
    private String id;
    @CsvBindByName(column = "nickname")
    private String nick_name;
    private String first_name;
    private String last_name;
    private String position;
    private int height_feet;
    private int height_inches;
    private int weight_pounds;
    private Team team;

}
