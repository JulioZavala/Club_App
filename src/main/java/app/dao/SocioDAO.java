package app.dao;

import app.model.Socio;
import app.zelper.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocioDAO extends BaseDAO {

    public Socio save(Socio socio) throws DAOExcepcion {
        String query = "insert into socio(email, nombres, paterno, materno, celular, sexo, direccion) values (?,?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, socio.getEmail());
            stmt.setString(2, socio.getNombres());
            stmt.setString(3, socio.getPaterno());
            stmt.setString(4, socio.getMaterno());
            stmt.setInt(5, socio.getCelular());
            stmt.setInt(6, socio.getSexo());
            stmt.setString(7, socio.getDireccion());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo insertar");
            }
            // Obtener el ultimo id
            int id = 0;
            query = "select last_insert_id()";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            socio.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return socio;
    }

    public Socio get(Socio socio) throws DAOExcepcion {
        Socio item = new Socio();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select * from socio where id=?";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, socio.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                item.setId(rs.getLong("id"));
                item.setEmail(rs.getString("email"));
                item.setNombres(rs.getString("nombres"));
                item.setPaterno(rs.getString("paterno"));
                item.setMaterno(rs.getString("materno"));
                item.setCelular(rs.getInt("celular"));
                item.setSexo(rs.getInt("sexo"));
                item.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return item;
    }

    public void delete(Socio socio) throws DAOExcepcion {

        String query = "delete from socio WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, socio.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }

    public Socio update(Socio socio) throws DAOExcepcion {
        String query = "update socio set email=?,nombres=?,paterno=?,materno=?,celular=?,sexo=?,direccion=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, socio.getEmail());
            stmt.setString(2, socio.getNombres());
            stmt.setString(3, socio.getPaterno());
            stmt.setString(4, socio.getMaterno());
            stmt.setInt(5, socio.getCelular());
            stmt.setInt(6, socio.getSexo());
            stmt.setString(7, socio.getDireccion());
            stmt.setLong(8, socio.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return socio;
    }

    public List<Socio> list() throws DAOExcepcion {
        List<Socio> lista = new ArrayList<Socio>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "select * from socio order by id;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Socio item = new Socio();
                item.setId(rs.getInt("id"));
                item.setEmail(rs.getString("email"));
                item.setNombres(rs.getString("nombres"));
                item.setPaterno(rs.getString("paterno"));
                item.setMaterno(rs.getString("materno"));
                item.setCelular(rs.getInt("celular"));
                item.setSexo(rs.getInt("sexo"));
                item.setDireccion(rs.getString("direccion"));
                lista.add(item);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return lista;
    }
}
