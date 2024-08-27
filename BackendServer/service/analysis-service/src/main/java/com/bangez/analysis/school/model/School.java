package com.bangez.analysis.school.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("schools")
public class School {
    @Id
    private String schoolId;

    @CsvBindByName(column = "address")
    private String address;

    @CsvBindByName(column = "homepage")
    private String homepage;

    @CsvBindByName(column = "school_name")
    @Field("school_name")
    private String schoolName;

    @CsvBindByName(column = "school_type")
    @Field("school_type")
    private String schoolType;

}
