package matriculacion.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehiculo database table.
 * 
 */
@Entity
@Table(name="vehiculo")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vehiculo", unique=true, nullable=false, length=8)
	private String idVehiculo;

	@Column(name="anio_modelo")
	private Integer anioModelo;

	@Column(nullable=false, length=20)
	private String color;

	@Column(length=50)
	private String marca;

	@Column(name="modelo_vehiculo", length=50)
	private String modeloVehiculo;

	//bi-directional many-to-one association to Puntaje
	@OneToMany(mappedBy="vehiculoBean")
	private List<Puntaje> puntajes;

	//bi-directional many-to-one association to Revision
	@OneToMany(mappedBy="vehiculoBean")
	private List<Revision> revisions;

	public Vehiculo() {
	}

	public String getIdVehiculo() {
		return this.idVehiculo;
	}

	public void setIdVehiculo(String idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public Integer getAnioModelo() {
		return this.anioModelo;
	}

	public void setAnioModelo(Integer anioModelo) {
		this.anioModelo = anioModelo;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModeloVehiculo() {
		return this.modeloVehiculo;
	}

	public void setModeloVehiculo(String modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}

	public List<Puntaje> getPuntajes() {
		return this.puntajes;
	}

	public void setPuntajes(List<Puntaje> puntajes) {
		this.puntajes = puntajes;
	}

	public Puntaje addPuntaje(Puntaje puntaje) {
		getPuntajes().add(puntaje);
		puntaje.setVehiculoBean(this);

		return puntaje;
	}

	public Puntaje removePuntaje(Puntaje puntaje) {
		getPuntajes().remove(puntaje);
		puntaje.setVehiculoBean(null);

		return puntaje;
	}

	public List<Revision> getRevisions() {
		return this.revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}

	public Revision addRevision(Revision revision) {
		getRevisions().add(revision);
		revision.setVehiculoBean(this);

		return revision;
	}

	public Revision removeRevision(Revision revision) {
		getRevisions().remove(revision);
		revision.setVehiculoBean(null);

		return revision;
	}

}