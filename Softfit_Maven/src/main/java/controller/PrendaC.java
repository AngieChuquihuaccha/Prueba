package controller;

import dao.PrendaImpl;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Prenda;

@Named(value ="prendaC")
@SessionScoped
public class PrendaC implements Serializable{
    private Prenda prenda;
    PrendaImpl dao;
    private List<Prenda> lstPrenda;
    
    public PrendaC() {
        prenda = new Prenda();
        dao = new PrendaImpl();
    }
    
    
    public void registrar() throws Exception {
        try {
            dao.guardar(prenda);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar PrendaC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(prenda);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar PrendaC/modificar: " + e.getMessage());
        }
    }

    public void eliminar(Prenda pre) throws Exception {
        try {
            dao.eliminar(pre);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar PrendaC/modificar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstPrenda = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listar PrendaC/modificar: " + e.getMessage());
        }
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public List<Prenda> getLstPrenda() {
        return lstPrenda;
    }

    public void setLstPrenda(List<Prenda> lstPrenda) {
        this.lstPrenda = lstPrenda;
    }

    
    
    
}
