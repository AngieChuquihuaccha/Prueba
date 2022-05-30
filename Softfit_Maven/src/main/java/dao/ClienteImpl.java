package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void guardar(Cliente cliente) throws Exception {
        try {
            String sql = "insert into cliente"
                    + " (NOMCLI,APECLI,DNICLI,CELCLI,EMACLI,DOMCLI,ESTCLI,CODUBI)"
                    + " values (?,?,?,?,?,?,?,?) ";
            try (PreparedStatement ps = ClienteImpl.conectar().prepareStatement(sql)) {

                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellidos());
                ps.setString(3, cliente.getDni());
                ps.setString(4, cliente.getCelular());
                ps.setString(5, cliente.getCorreo());
                ps.setString(6, cliente.getDomicilio());
                ps.setString(7, "A");
                ps.setString(8, cliente.getUbicacion());
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en ClienteD/registrar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Cliente cliente) throws Exception {

        String sql = "update cliente set NOMCLI=?, APECLI=?, DNICLI=?, CELCLI=?, ESTCLI=?, EMACLI=?, DOMCLI=?, CODUBI=? where IDCLI=?";
        try (PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getCelular());
            ps.setString(5, "A");
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getDomicilio());
            ps.setString(8, cliente.getUbicacion());
            ps.setInt(9, cliente.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en ClienteD/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Cliente cliente) throws Exception {
        try {
            String sql = "update cliente set ESTCLI='I' where idcli=?";
            PreparedStatement ps = ClienteImpl.conectar().prepareStatement(sql);
            ps.setInt(1, cliente.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en ClienteD/eliminar: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> listarTodos() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from cliente where ESTCLI ='A'";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente usu = new Cliente();
                usu.setCodigo(rs.getInt("IDCLI"));
                usu.setNombre(rs.getString("NOMCLI"));
                usu.setApellidos(rs.getString("APECLI"));
                usu.setDni(rs.getString("DNICLI"));
                usu.setCelular(rs.getString("CELCLI"));
                usu.setEstado(rs.getString("ESTCLI"));
                usu.setCorreo(rs.getString("EMACLI"));
                usu.setUbicacion(rs.getString("CODUBI"));
                usu.setDomicilio(rs.getString("DOMCLI"));
                lista.add(usu);
            }
        } catch (Exception e) {
            System.out.println("");
        } finally {
            this.cerrarCnx();
        }
        return lista;
    }

    public void cambiarEstado(Cliente cliente) throws Exception {
        try {
            String sql = "update paciente set ESTCLI=? where IDCLI=?";
            try (PreparedStatement ps = ClienteImpl.conectar().prepareStatement(sql)) {
                ps.setString(1, cliente.getEstado());
                ps.setInt(2, cliente.getCodigo());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en ClienteD/cambiarEstado: " + e.getMessage());
        }
    }

}
