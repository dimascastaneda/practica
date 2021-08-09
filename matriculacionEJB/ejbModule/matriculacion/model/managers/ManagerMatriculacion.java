package matriculacion.model.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import matriculacion.model.entities.Caracteristica;
import matriculacion.model.entities.Cliente;
import matriculacion.model.entities.Puntaje;
import matriculacion.model.entities.Revision;
import matriculacion.model.entities.Vehiculo;

/**
 * Session Bean implementation class ManagerMatriculacion
 */
@Stateless
@LocalBean
public class ManagerMatriculacion {

	@PersistenceContext
    private EntityManager entityManager;

    public List<Cliente> findAllClientes() {
        return entityManager.createNamedQuery("Clientes.findAll", Cliente.class).getResultList();
    }

    public List<Vehiculo> findAllVehiculos() {
        return entityManager.createNamedQuery("Vehiculo.findAll", Vehiculo.class).getResultList();
    }

    public List<Revision> findAllMatriculas() {
        return entityManager.createNamedQuery("Revision.findAll", Revision.class).getResultList();
    }

    public List<Caracteristica> findAllCaracteristicas() {
        return entityManager.createNamedQuery("Caracteristica.findAll", Caracteristica.class).getResultList();
    }

    public List<Puntaje> findAllPuntaje() {
        return entityManager.createNamedQuery("Puntaje.findAll", Puntaje.class).getResultList();
    }

    public List<Puntaje> calificarCaracteristica(List<Puntaje> lista, int id_caracteristica, double puntaje, String id_vehiculo) throws Exception {
        Caracteristica caracteristica = entityManager.find(Caracteristica.class, id_caracteristica);
        Vehiculo vehiculo = entityManager.find(Vehiculo.class, id_vehiculo);
        if (caracteristica == null) {
            JSFUtil.crearMensajeWarning("Debe seleccionar la caracteristica!");
        }
        if (lista == null) {
            lista = new ArrayList<Puntaje>();
        }
        Puntaje p = new Puntaje();
        p.setCaracteristicaBean(caracteristica);
        p.setVehiculoBean(vehiculo);
        p.setPuntaje(new BigDecimal(puntaje));
        entityManager.persist(p);
        lista.add(p);
        return lista;
    }

    public boolean eliminarPuntaje(int id_puntaje) {
        boolean resp = false;
        Puntaje puntaje = entityManager.find(Puntaje.class, id_puntaje);
        if (puntaje != null) {
            entityManager.remove(puntaje);
            resp = true;
        }
        return resp;
    }

    public double CalcularPromedioPuntaje(List<Puntaje> lista) {
        double promedioPuntaje = 0;
        if (lista != null) {
            for (Puntaje puntaje : lista) {
                promedioPuntaje += puntaje.getPuntaje().doubleValue();
            }
            return promedioPuntaje / (lista.size());
        } else {
            return 0;
        }
    }

    public List<Puntaje> registrarNuevaRevision(List<Puntaje> lista, int anio_revision, String observaciones, boolean paso_revision, String id_cliente, String id_vehiculo) throws Exception {
        double puntaje = CalcularPromedioPuntaje(lista);
        Cliente cliente = entityManager.find(Cliente.class, id_cliente);
        Vehiculo vehiculo = entityManager.find(Vehiculo.class, id_vehiculo);
        //Revision rev = buscarRevision(id_vehiculo, anio_revision);
        if (puntaje < 7) {
            paso_revision = false;
        } else {
            paso_revision = true;
        }
        if (lista.size()>0) {
            Revision revision = new Revision();
            revision.setAnioRevision(anio_revision);
            revision.setObservaciones(observaciones);
            revision.setPasoRevision(paso_revision);
            revision.setClienteBean(cliente);
            revision.setVehiculoBean(vehiculo);
            revision.setPuntaje(new BigDecimal(puntaje));
            entityManager.persist(revision);
            lista=new ArrayList<Puntaje>();
        }
        else{
            JSFUtil.crearMensajeError("Agregue puntaje a las caracteristicas!!");
        }
        return lista;
    }
}


