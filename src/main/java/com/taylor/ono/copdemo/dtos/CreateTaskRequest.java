package com.taylor.ono.copdemo.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@Data
public class CreateTaskRequest {

    @NotEmpty
    @Length(max = 10)
    private String title;

    @NotNull
    private Date dueDate;

    @Positive
    @Max(5)
    private int priority;

    private String description;

}
