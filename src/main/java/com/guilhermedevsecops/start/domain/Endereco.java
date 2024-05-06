package com.guilhermedevsecops.start.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="enderecos")
public class Endereco {
    @Id
    private Long id_endereco;
    private String logradouro;
    private Long numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

}
