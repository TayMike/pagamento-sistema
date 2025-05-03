package com.pagamento.sistema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    private String cpf;

    @Column(nullable = false)
    private String numeroCartao;

    @Column(nullable = false)
    private String codigoVerificador;

    @Column(nullable = false)
    private Date dataValidadeCartao;

}
