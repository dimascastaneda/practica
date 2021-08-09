package matriculacion.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the revision database table.
 * 
 */
@Entity
@Table(name="revision")
@NamedQuery(name="Revision.findAll", query="SELECT r FROM Revision r")
public class Revision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_revision", unique=true, nullable=false)
	private Integer idRevision;

	@Column(name="anio_revision")
	private Integer anioRevision;

	@Column(length=100)
	private String observaciones;

	@Column(name="paso_revision")
	private Boolean pasoRevision;

	@Column(precision=4, scale=2)
	private BigDecimal puntaje;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente clienteBean;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="vehiculo")
	private Vehiculo vehiculoBean;

	public Revision() {
	}

	public Integer getIdRevision() {
		return this.idRevision;
	}

	public void setIdRevision(Integer idRevision) {
		this.idRevision = idRevision;
	}

	public Integer getAnioRevision() {
		return this.anioRevision;
	}

	public void setAnioRevision(Integer anioRevision) {
		this.anioRevision = anioRevision;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getPasoRevision() {
		return this.pasoRevision;
	}

	public void setPasoRevision(Boolean pasoRevision) {
		this.pasoRevision = pasoRevision;
	}

	public BigDecimal getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(BigDecimal puntaje) {
		this.puntaje = puntaje;
	}

	public Cliente getClienteBean() {
		return this.clienteBean;
	}

	public void setClienteBean(Cliente clienteBean) {
		this.clienteBean = clienteBean;
	}

	public Vehiculo getVehiculoBean() {
		return this.vehiculoBean;
	}

	public void setVehiculoBean(Vehiculo vehiculoBean) {
		this.vehiculoBean = vehiculoBean;
	}

}