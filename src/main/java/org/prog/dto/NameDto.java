package org.prog.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NameDto {
    private String title;
    private String first;
    private String last;
}
