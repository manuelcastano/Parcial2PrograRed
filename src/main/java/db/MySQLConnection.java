package db;

import java.sql.*;

public class MySQLConnection {

    private Connection connection;
    private boolean inUse;

    public MySQLConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            inUse = false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parcial2", "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean createDatabase(){
        boolean success = false;
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS tareasCastano(id INT PRIMARY KEY AUTO_INCREMENT, descripcion VARCHAR(200), fecha BIGINT, seccion INT)");
            success = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return success;
    }

    public boolean executeSQL(String sql){
        boolean success = false;
        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            inUse = false;
            success = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            disconnect();
        }
        return success;
    }

    public ResultSet query(String sql) {
        ResultSet output = null;
        try {
            Statement statement = connection.createStatement();
            output = statement.executeQuery(sql);
            inUse = false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return output;
    }
}
