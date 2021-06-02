package indi.rennnhong.staterkit.module.student.web.ui.vo;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class StudentVO {
    private UUID id;
    private String name;
    private Integer age;
}
