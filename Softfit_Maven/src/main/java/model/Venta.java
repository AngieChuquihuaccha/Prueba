package model;

import java.util.Date;
import lombok.Data;

@Data
public class Venta {
    private int codigo;
    private Date fechaven;
    private String estado;
    private int idusuario;
    private int idcliente;
    
    
}

 