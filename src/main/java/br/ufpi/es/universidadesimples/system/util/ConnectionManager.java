package br.ufpi.es.universidadesimples.system.util;

import java.sql.*;


/**
 * @author Armando
 *
 */
public class ConnectionManager {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    /**
     * 
     * @return
     */
    public static Statement reservaStatement() {
       openConnection();
            try {
                statement = connection.createStatement();
            } catch(SQLException sqle) {
                        System.out.println("SQLException => ConnectionManager: " + sqle.getMessage());
            }
            return statement;
    }

    /**
     * Atenção, este método fecha o Statement!
     */
    public synchronized static void liberaStatement() {
        try {
            if (statement != null) statement.close();
            } catch(SQLException sqle) {
                    System.out.println("Erro de BD => ConnectionManager" + sqle.getMessage());
            }
        }

     /**
     * Atenção, este método fecha o Statement!
     */
    public synchronized static void liberaPreparedStatement() {
          try {
            if (preparedStatement != null)
              preparedStatement.close();
          }
          catch (SQLException sqle) {
            System.out.println("Erro de BD => ConnectionManager" + sqle.getMessage());
          }
    }

    /**
     * 
     */
    public static void liberaRecursos() {
        try {
            if (statement != null) statement.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (Exception ex) {}
        try {
            connection.close();
        } catch (Exception ex) {}
    }

    /**
     * 
     * @param sql
     * @return
     */
    public static PreparedStatement reservaPreparedStatement(String sql) {
       openConnection();
           try {
               preparedStatement = connection.prepareStatement(sql);
           } catch(SQLException sqle) {
                       System.out.println("SQLException => ConnectionManager: " + sqle.getMessage());
           }
           return preparedStatement;
    }

    /**
     * 
     */
    private static void openConnection() {
        if (connection == null) {
            try {

              String nomeDriver = "com.mysql.jdbc.Driver";
              String url = "jdbc:mysql://localhost/sistemaescolar";
              String user = "root";
              String pwd = "root";


                Class.forName(nomeDriver);
                connection = DriverManager.getConnection(url, user, pwd);
            } catch(SQLException sqle) {
                        System.out.println("SQLException => ConnectionManager: " + sqle.getMessage());
                } catch(ClassNotFoundException cnfe) {
                        System.out.println("Driver nao encontrado => ConnectionManager: " + cnfe.getMessage());
                    }
            }
      }
}