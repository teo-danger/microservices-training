package com.dedagroup.springboottraining.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnagraficaDto {

    @NotNull
    @Size(max = 50)
    private String nome;

    @NotNull
    @Size(max = 50)
    private String cognome;

}
