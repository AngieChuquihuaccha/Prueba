package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Prenda;

public class PrendaImpl extends Conexion implements ICRUD<Prenda> {

    @Override
    public void guardar(Prenda prenda) throws Exception {
        try {
            String sql = "insert into prenda"
                    + " (NOMPRE,TALLPRE,PRECPRE,STOPRE,IDCAT, ESTPRE, COLPRE , MARPRE)"
                    + " values (?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, prenda.getNombre());
            ps.setString(2, prenda.getTalla());
            ps.setString(3, prenda.getPrecio());
            ps.setInt(4, prenda.getStock());
            ps.setInt(5, prenda.getCategoria());
            ps.setString(6, "A");
            ps.setString(7, prenda.getColor());
            ps.setString(8, prenda.getMarca());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PrendaD/registrar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Prenda prenda) throws Exception {
        try {
            String sql = "update prenda set NOMPRE=?, TALLPRE=?, PRECPRE=?, STOPRE=?, IDCAT=?, ESTPRE=?, COLPRE=?, MARPRE=? where IDPREN=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, prenda.getNombre());
            ps.setString(2, prenda.getTalla());
            ps.setString(3, prenda.getPrecio());
            ps.setInt(4, prenda.getStock());
            ps.setInt(5, prenda.getCategoria());
            ps.setString(6, prenda.getEstado());
            ps.setString(7, prenda.getColor());
            ps.setString(8, prenda.getMarca());
             ps.setInt(9, prenda.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PrendaD/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Prenda prenda) throws Exception {
        try {
            String sql = "update prenda set ESTPRE='I' where idpre=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, prenda.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en PrendaD/eliminar: " + e.getMessage());
        }
    }

    @Override
    public List<Prenda> listarTodos() throws Exception {
        List<Prenda> lista = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from prenda";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = (ResultSet) ps.executeQuery();

            while (rs.next()) {
                Prenda pre = new Prenda();
               // pre.setCodigo(rs.getInt("IDPRE"));
                pre.setNombre(rs.getString("NOMPRE"));
                pre.setTalla(rs.getString("TALLPRE"));
                pre.setPrecio(rs.getString("PRECPRE"));
                pre.setStock(rs.getInt("STOPRE"));
                pre.setCategoria(rs.getInt("IDCAT"));
                //pre.setEstado(rs.getString("ESTPRE"));
                pre.setColor(rs.getString("COLPRE"));
                pre.setMarca(rs.getString("MARPRE"));
                lista.add(pre);
            }
        } catch (Exception e) {
            System.out.println("Error en PrendaD/eliminar: " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return lista;
    }

}
