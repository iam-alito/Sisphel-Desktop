package sisphel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Ed_Usuario {

    Conexion con = new Conexion();

    void editar(String cod, String user, String pass, String nombre, String grupo, String permi, String ID) {
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea modificar los datos actuales?");
        if (confirmar == JOptionPane.YES_OPTION) {

            Connection c = null;
            try {
                c = con.conexion();
                String sql = ("UPDATE usuarios SET Codigo=?, Usuario=?, Contraseña=?, Nombre=?, Grupo=?, Permiso=? WHERE ID=?");
                PreparedStatement psql = c.prepareStatement(sql);
                psql.setString(1, cod);
                psql.setString(2, user);
                psql.setString(3, pass);
                psql.setString(4, nombre);
                psql.setString(5, grupo);
                psql.setString(6, permi);
                psql.setString(7, ID);

                if (psql.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Operación Exitosa \nLos datos se han modificado correctamente", "SISPHEL - [Editar Usuario]", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/img/check.png"));

                } else {
                    JOptionPane.showMessageDialog(null, "Operación Fallida\nError al modificar la información", "SISPHEL - [Modificar Equipo]", JOptionPane.ERROR_MESSAGE, new ImageIcon("src/img/error.png"));

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "***ERROR***\nNo se ha podido realizar la actualización de los datos" + e, "SISPHEL - [ERROR]", JOptionPane.ERROR_MESSAGE, new ImageIcon("src/img/error.png"));

            } finally {
                try {

                    if (c != null) {
                        c.close();
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al Cerrar La Conexión:\n" + e, "Error En La Operacion", JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    }
}
