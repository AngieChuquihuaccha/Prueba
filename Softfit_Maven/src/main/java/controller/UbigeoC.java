package controller;
import dao.UbigeoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Data;
import model.Ubigeo;

@Data

//Notación CDI
@Named(value = "ubigeoC")
//@Dependent
@SessionScoped
public class UbigeoC implements Serializable {

    private Ubigeo ubigeo;
    private UbigeoImpl dao;
    private List<Ubigeo> listadoUbigeo;
 

    public UbigeoC() {
        ubigeo = new Ubigeo();
        dao = new UbigeoImpl();
    }

    public void listar() {
        try {
            listadoUbigeo = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listar ubigeoC " + e.getMessage());
        }
    }

@PostConstruct
    public void construir() {
        listar();
    }
}
    

