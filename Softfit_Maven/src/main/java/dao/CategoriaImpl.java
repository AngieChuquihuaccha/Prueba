package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;


public class CategoriaImpl extends Conexion implements ICRUD<Categoria>{

    @Override
    public void guardar(Categoria categoria) throws Exception {
        try {
            String sql = "insert into categoria"
                    + " (NOMCAT, SETCAT)"
                    + " values (?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getSeccion());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en CategoriaD/registrar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Categoria categoria) throws Exception {
        try {
            String sql = "update categoria set NOMCAT=?, SETCAT=? where IDCAT=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getSeccion());
            ps.setInt(3, categoria.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en CategoriaD/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Categoria categoria) throws Exception {
        try {
            String sql = "delete from categoria where idcat=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, categoria.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en CategoriaD/eliminar: " + e.getMessage());
        }
    }

    @Override
    public List<Categoria> listarTodos() throws Exception {
        List<Categoria> lista = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from categoria";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setCodigo(rs.getInt("IDCAT"));
                cat.setNombre(rs.getString("NOMCAT"));
                cat.setSeccion(rs.getString("SETCAT"));
                lista.add(cat);
            }
        } catch (Exception e) {
            System.out.println("");
        } finally {
            this.cerrarCnx();
        }
        return lista;
    }
    
}
