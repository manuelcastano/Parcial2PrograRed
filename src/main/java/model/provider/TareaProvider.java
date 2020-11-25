package model.provider;

import db.ConnectionPool;
import db.MySQLConnection;
import entity.Tarea;
import model.dto.TareaDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TareaProvider {

    public void insertTarea(Tarea tarea){
        MySQLConnection connection = ConnectionPool.getInstance().getConexion();
        String sql = "INSERT INTO tareasCastano(descripcion, fecha, seccion) VALUES('$descripcion', $fecha, $seccion)".replace("$descripcion", tarea.getDescripcion()).replace("$fecha", tarea.getFecha()+"").replace("$seccion", tarea.getSeccion()+"");
        connection.executeSQL(sql);
    }

    public Tarea mapFromDTO(TareaDTO tareaDTO){
        Tarea tarea = new Tarea();
        tarea.setId(tareaDTO.getId());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setFecha(tareaDTO.getFecha());
        tarea.setSeccion(tareaDTO.getSeccion());
        return tarea;
    }

    public ArrayList<TareaDTO> getAllTareas() {
        ArrayList<TareaDTO> tareas = new ArrayList<>();
        MySQLConnection connection = ConnectionPool.getInstance().getConexion();
        String sql = "SELECT id, descripcion, fecha, seccion FROM tareasCastano";
        ResultSet rs = connection.query(sql);
        try {
            while(rs.next()){
                tareas.add(new TareaDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.disconnect();
        return tareas;
    }

    public boolean deleteTarea(int id){
        MySQLConnection connection = new MySQLConnection();
        String sql = "DELETE FROM tareasCastano where id="+id;
        return connection.executeSQL(sql);
    }

    public boolean changeSeccion(int id, int seccion) {
        MySQLConnection connection = new MySQLConnection();
        String sql = "UPDATE tareasCastano SET seccion = '$seccion' where id="+id;
        sql = sql.replace("$seccion", seccion+"");
        return connection.executeSQL(sql);
    }
}
