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
@Table(name = "Cuerpo_Capataces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuerpoCapataces {

    @Id
    @Column(nullable = false, length = 9)
    private String dniCapataz;

    @Column(nullable = false)
    private String nombreCapataz;

    @Column(nullable = false)
    private String apellidoCapataz;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PuestoCapataz.Puesto puestoCapataz;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_paso", nullable = false)
    private Paso paso;
}

