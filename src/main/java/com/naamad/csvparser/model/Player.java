package com.naamad.csvparser.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @CsvBindByName
    private String id;
    @CsvBindByName(column = "nickname")
    private String nick_name;

}
