package db;

import java.util.ArrayList;

public class ConnectionPool {

    private static ConnectionPool connection;
    private ArrayList<MySQLConnection> conexiones;

    public static synchronized ConnectionPool getInstance(){
        if(connection == null){
            connection = new ConnectionPool();
        }
        return connection;
    }

    private ConnectionPool(){
        conexiones = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            MySQLConnection sql = new MySQLConnection();
            sql.connect();
            sql.createDatabase();
            conexiones.add(sql);
        }
    }

    public MySQLConnection getConexion(){
        MySQLConnection sql = null;
        boolean finded = false;
        for (int i = 0; i < conexiones.size() && !finded; i++){
            if(!conexiones.get(i).isInUse()){
                sql = conexiones.get(i);
                sql.connect();
                finded = true;
                sql.setInUse(true);
            }
        }
        if(sql == null){
            generateMoreConexions();
            sql = getConexion();
        }
        return sql;
    }

    public void generateMoreConexions(){
        for (int i = 0; i < 5; i++){
            MySQLConnection sql = new MySQLConnection();
            sql.connect();
            sql.createDatabase();
            conexiones.add(sql);
        }
    }
}