package controller;

import dao.CategoriaImpl;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Categoria;

@Named(value ="categoriaC")
@SessionScoped
public class CategoriaC implements Serializable{
    private Categoria categoria;
    CategoriaImpl dao;
    private List<Categoria> lstCategoria;
    
    public CategoriaC() {
        categoria = new Categoria();
        dao = new CategoriaImpl();
    }
    
    public void registrar() throws Exception {
        try {
            dao.guardar(categoria);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar CategoriaC/registrar: " + e.getMessage());
        }
    }
    
    public void modificar() throws Exception {
        try {
            dao.modificar(categoria);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar CategoriaC/modificar: " + e.getMessage());
        }
    }

    public void eliminar(Categoria cat) throws Exception {
        try {
            dao.eliminar(cat);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar CategoriaC/modificar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstCategoria = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listar CategoriaC/modificar: " + e.getMessage());
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getLstCategoria() {
        return lstCategoria;
    }

    public void setLstCategoria(List<Categoria> lstCategoria) {
        this.lstCategoria = lstCategoria;
    }
    
    
}
