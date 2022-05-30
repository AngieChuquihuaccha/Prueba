package controller;

import dao.VendedorImpl;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Vendedor;
import org.primefaces.PrimeFaces;

@Named(value = "vendedorC")
@SessionScoped
public class VendedorC implements Serializable {

    private Vendedor vendedor;
    private VendedorImpl dao;
    private List<Vendedor> lstVendedor;
    private int tipo = 1;

    public VendedorC() {
        vendedor = new Vendedor();
        dao = new VendedorImpl();
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void registrar() throws Exception {
        try {
            if (!dao.existe(vendedor, lstVendedor)) {
                dao.guardar(vendedor);

                //usuario.clear();
                PrimeFaces.current().ajax().update("form");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
                listar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El DNI existe"));
}
            }catch (Exception e) {
            System.out.println("Error en registrar VendedorC/registrar: " + e.getMessage());
        }
        vendedor = new Vendedor();
        listar();
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(vendedor);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar VendedorC/modificar: " + e.getMessage());
        }
    }

    public void eliminar(Vendedor vend) throws Exception {
        try {
            dao.eliminar(vend);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar VendedorC/modificar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstVendedor = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en listar VendedorC/modificar: " + e.getMessage());
        }
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Vendedor> getLstVendedor() {
        return lstVendedor;
    }

    public void setLstVendedor(List<Vendedor> lstVendedor) {
        this.lstVendedor = lstVendedor;
    }

}
