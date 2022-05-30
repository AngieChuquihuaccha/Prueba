package controller;

import dao.ClienteImpl;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Cliente;

@Named(value ="clienteC")
@SessionScoped
public class ClienteC implements Serializable{
    private Cliente cliente;
    ClienteImpl dao;
    private List<Cliente> lstCliente;
    
    
    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
    }
    
    
    public void registrar() throws Exception {
        try {
            dao.guardar(cliente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar ClienteC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cliente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar ClienteC/modificar: " + e.getMessage());
        }
    }

    public void eliminar(Cliente pre) throws Exception {
        try {
            dao.eliminar(pre);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar ClienteC/modificar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstCliente = dao.listarTodos();
        } catch (Exception e) { 
            System.out.println("Error en listar ClienteC/modificar: " + e.getMessage());
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteImpl getDao() {
        return dao;
    }

    public void setDao(ClienteImpl dao) {
        this.dao = dao;
    }

    public List<Cliente> getLstCliente() {
        return lstCliente;
    }

    public void setLstCliente(List<Cliente> lstCliente) {
        this.lstCliente = lstCliente;
    }

  
    
    
    
}
