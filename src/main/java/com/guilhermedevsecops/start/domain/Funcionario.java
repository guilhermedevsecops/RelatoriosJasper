package com.guilhermedevsecops.start.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
public class Funcionario {
    @Id
    private Long id;
    private LocalDate dataNascimento;
    private byte[] foto;
    private BigDecimal salario;
    private LocalDate data_admissao;
    private LocalDate data_demissao;
    private Long id_endereco;
    private Long id_nivel;
    private Long id_cargo;
}
