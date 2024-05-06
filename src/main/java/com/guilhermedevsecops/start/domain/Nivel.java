package com.guilhermedevsecops.start.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "niveis")
@Data
@NoArgsConstructor
public class Nivel {
    @Id
    private Long id_nivel;
    private String nivel;
    private Long bonus;
}
