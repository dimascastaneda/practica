package matriculacion.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class BeanMatriculacion implements Serializable {
    private int anio_revision;
    private String observaciones;
    private boolean paso_revision;
    private String id_cliente;
    private String id_vehiculo;
    private int id_caracteristica;
    private double puntaje;
    private List<Clientes> listaClientes;
    private List<Vehiculo> listaVehiculos;
    private List<Revision> listaRevisiones;
    private List<Caracteristica> listaCaracteristicas;
    private List<Puntaje> listaPuntajes;
    private List<Puntaje> lista;

    @EJB
    private ManagerMatriculacion managerMatriculacion;

    

    @PostConstruct
    public void Inicializar() {
        listaCaracteristicas = managerRevision.findAllCaracteristicas();
        listaClientes = managerRevision.findAllClientes();
        listaPuntajes = managerRevision.findAllPuntaje();
        listaRevisiones = managerRevision.findAllMatriculas();
        listaVehiculos = managerRevision.findAllVehiculos();
    }

    public void actionListenerGuardarMatricula() throws Exception {
        lista = managerRevision.registrarNuevaRevision(lista, anio_revision, observaciones, paso_revision, id_cliente, id_vehiculo);
        try {
            listaRevisiones = managerRevision.findAllMatriculas();
            JSFUtil.crearMensajeInfo("Matricula guardado con exito!");
        } catch (Exception e) {
            JSFUtil.crearMensajeError("Error al generar la matricula! "+e.getMessage());
        }
        
    }

    public void actionListenerEliminarPuntaje(int id_puntaje) throws Exception {
        if (managerRevision.eliminarPuntaje(id_puntaje)) {
            JSFUtil.crearMensajeInfo("Puntaje eliminado con exito!");
        } else {
            JSFUtil.crearMensajeError("Error al eliminar puntaje!");
        }
    }

    public void actionListenerAgregarPuntaje() throws Exception {
        lista = managerRevision.calificarCaracteristica(lista, id_caracteristica, puntaje, id_vehiculo);
    }

    public void addMessage() {
        if (paso_revision) {
            JSFUtil.crearMensajeInfo("Seleciono Revision de matricula exitosa!");
        } else {
            JSFUtil.crearMensajeError("Selecciono Revision de matricula con errores. No pasa la matricula!");
        }
    }

    public int getAnio_revision() {
        return anio_revision;
    }

    public void setAnio_revision(int anio_revision) {
        this.anio_revision = anio_revision;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isPaso_revision() {
        return paso_revision;
    }

    public void setPaso_revision(boolean paso_revision) {
        this.paso_revision = paso_revision;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public List<Revision> getListaRevisiones() {
        return listaRevisiones;
    }

    public void setListaRevisiones(List<Revision> listaRevisiones) {
        this.listaRevisiones = listaRevisiones;
    }

    public List<Caracteristica> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public void setListaCaracteristicas(List<Caracteristica> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
    }

    public List<Puntaje> getListaPuntajes() {
        return listaPuntajes;
    }

    public void setListaPuntajes(List<Puntaje> listaPuntajes) {
        this.listaPuntajes = listaPuntajes;
    }

    public List<Puntaje> getLista() {
        return lista;
    }

    public void setLista(List<Puntaje> lista) {
        this.lista = lista;
    }

    public int getId_caracteristica() {
        return id_caracteristica;
    }

    public void setId_caracteristica(int id_caracteristica) {
        this.id_caracteristica = id_caracteristica;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

}
	
}
