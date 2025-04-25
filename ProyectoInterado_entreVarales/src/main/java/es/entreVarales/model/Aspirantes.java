package es.entreVarales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Aspirantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aspirantes {

    @Id
    @Column(nullable = false, length = 9)
    private String dniAspirante;

    @Column(nullable = false)
    private String nombreAspirante;

    @Column(nullable = false)
    private String apellidoAspirante;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_paso", nullable = false)
    private Paso paso;

    @Column(nullable = false)
    private Integer numTrabajadera;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Altura.TipoAltura tipoAltura;
}

