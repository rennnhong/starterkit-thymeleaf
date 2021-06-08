package indi.rennnhong.staterkit.module.student.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentDto {

    private UUID id;
    private String name;
    private Integer age;

}
