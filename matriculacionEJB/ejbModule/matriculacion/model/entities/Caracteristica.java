package matriculacion.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the caracteristica database table.
 * 
 */
@Entity
@Table(name="caracteristica")
@NamedQuery(name="Caracteristica.findAll", query="SELECT c FROM Caracteristica c")
public class Caracteristica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_caracteristica", unique=true, nullable=false)
	private Integer idCaracteristica;

	@Column(name="nombre_caracteristica", length=30)
	private String nombreCaracteristica;

	//bi-directional many-to-one association to Puntaje
	@OneToMany(mappedBy="caracteristicaBean")
	private List<Puntaje> puntajes;

	public Caracteristica() {
	}

	public Integer getIdCaracteristica() {
		return this.idCaracteristica;
	}

	public void setIdCaracteristica(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public String getNombreCaracteristica() {
		return this.nombreCaracteristica;
	}

	public void setNombreCaracteristica(String nombreCaracteristica) {
		this.nombreCaracteristica = nombreCaracteristica;
	}

	public List<Puntaje> getPuntajes() {
		return this.puntajes;
	}

	public void setPuntajes(List<Puntaje> puntajes) {
		this.puntajes = puntajes;
	}

	public Puntaje addPuntaje(Puntaje puntaje) {
		getPuntajes().add(puntaje);
		puntaje.setCaracteristicaBean(this);

		return puntaje;
	}

	public Puntaje removePuntaje(Puntaje puntaje) {
		getPuntajes().remove(puntaje);
		puntaje.setCaracteristicaBean(null);

		return puntaje;
	}

}