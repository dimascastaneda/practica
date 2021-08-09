package matriculacion.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the puntaje database table.
 * 
 */
@Entity
@Table(name="puntaje")
@NamedQuery(name="Puntaje.findAll", query="SELECT p FROM Puntaje p")
public class Puntaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_puntaje", unique=true, nullable=false)
	private Integer idPuntaje;

	@Column(precision=4, scale=2)
	private BigDecimal puntaje;

	//bi-directional many-to-one association to Caracteristica
	@ManyToOne
	@JoinColumn(name="caracteristica")
	private Caracteristica caracteristicaBean;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="vehiculo")
	private Vehiculo vehiculoBean;

	public Puntaje() {
	}

	public Integer getIdPuntaje() {
		return this.idPuntaje;
	}

	public void setIdPuntaje(Integer idPuntaje) {
		this.idPuntaje = idPuntaje;
	}

	public BigDecimal getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(BigDecimal puntaje) {
		this.puntaje = puntaje;
	}

	public Caracteristica getCaracteristicaBean() {
		return this.caracteristicaBean;
	}

	public void setCaracteristicaBean(Caracteristica caracteristicaBean) {
		this.caracteristicaBean = caracteristicaBean;
	}

	public Vehiculo getVehiculoBean() {
		return this.vehiculoBean;
	}

	public void setVehiculoBean(Vehiculo vehiculoBean) {
		this.vehiculoBean = vehiculoBean;
	}

}