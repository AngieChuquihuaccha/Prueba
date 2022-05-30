package dao;

import dao.Conexion;
import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Vendedor;

public class VendedorImpl extends Conexion implements ICRUD<Vendedor> {

    @Override
    public void guardar(Vendedor vendedor) throws Exception {
        try {
            String sql = "insert into vendedor"
                    + " (NOMVEND,APEVEND,CELVEND,ESTVEND, DNIVEND,EMAVEND,DOMVEND,CODUBI)"
                    + " values (?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getApellidos());
            ps.setString(3, vendedor.getCelular());
            ps.setString(4, "A");
            ps.setString(5, vendedor.getDni());
            ps.setString(6, vendedor.getEmail());
            ps.setString(7, vendedor.getDomicilio());
            ps.setString(8, vendedor.getCODUBI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en VendedorD/registrar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Vendedor vendedor) throws Exception {
        try {
            String sql = "update vendedor set NOMVEND=?, APEVEND=?, CELVEND=?, ESTVEND=?, DNIVEND =?, EMAVEND=?, DOMVEND=?, CODUBI=? where IDVEND=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getApellidos());
            ps.setString(3, vendedor.getCelular());
            ps.setString(4, "A");
            ps.setString(5, vendedor.getDni());
            ps.setString(6, vendedor.getEmail());
            ps.setString(7, vendedor.getDomicilio());
            ps.setString(8, vendedor.getCODUBI());
            ps.setInt(9, vendedor.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en VendedorD/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Vendedor vendedor) throws Exception {
        try {
            String sql = "update vendedor set ESTVEND='I' where IDVEND=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, vendedor.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en VendedorD/eliminar: " + e.getMessage());
        }
    }

    //@Override
    public List<Vendedor> listarTodos(int tipo) throws Exception {
        List<Vendedor> lista = new ArrayList<>();
        ResultSet rs;
        String sql = "";

        switch (tipo) {
            case 1:
                sql = "SELECT * FROM VENDEDOR WHERE ESTVEND='A' ORDER BY IDVEND desc";
                break;
            case 2:
                sql = "SELECT * FROM VENDEDOR WHERE ESTVEND='I' ORDER BY IDVEND desc";
                break;
            case 3:
                sql = "SELECT * FROM VENDEDOR ORDER BY IDVEND desc";
                break;
        }
        //String sql = "select * from usuario where ESTUSU ='A' order by IDUSU desc";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Vendedor vend = new Vendedor();
                vend.setNombre(rs.getString("NOMVEND"));
                vend.setApellidos(rs.getString("APEVEND"));
                vend.setCelular(rs.getString("CELVEND"));
                vend.setDni(rs.getString("DNIVEND"));
                vend.setEmail(rs.getString("EMAVEND"));
                vend.setDomicilio(rs.getString("DOMVEND"));
                vend.setCODUBI(rs.getString("CODUBI"));
                lista.add(vend);
            }
        } catch (Exception e) {
            System.out.println("Error en VendedorD/listar: " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return lista;
    }

    public void cambiarEstado(Vendedor vendedor) throws Exception {
        try {
            String sql = "update vendedor set ESTVEND=? where IDVEND=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, vendedor.getEstado());
            ps.setInt(2, vendedor.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en VendedorD/cambiarEstado: " + e.getMessage());
        }
    }
    
    public boolean existe(Vendedor vendedor, List<Vendedor> listaVendedor) {
        for (Vendedor vend : listaVendedor) {
            if (vendedor.getDni().equals(vend.getDni())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Vendedor> listarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
