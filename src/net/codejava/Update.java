package net.codejava ;

import java.sql.*;

public class Update {

    public static  String getCubeSQL(String name) {
        String s = "" ;
        String jdbc = "jdbc:postgresql://localhost:5432/Cubord" ;
        String username = "postgres" ;
        String password = "Akshath$$123" ;
        try {
            Connection conn = DriverManager.getConnection(jdbc, username, password);
            String sql = "SELECT name,cube "
                    + "FROM cubes "
                    + "WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql) ;
            statement.setString(1, "Akshath");
            ResultSet result = statement.executeQuery() ;

            while (result.next()) {
                s = result.getString("cube");

            }

            conn.close();

        }
        catch (SQLException e) {
            System.out.println("Error while connecting");
            e.printStackTrace();
        }
        return s ;
    }
    public static  boolean setCubeSQL(String name, String cube) {
        String jdbc = "jdbc:postgresql://localhost:5432/Cubord" ;
        String username = "postgres" ;
        String password = "Akshath$$123" ;
        try {
            Connection conn = DriverManager.getConnection(jdbc, username, password);
            String sql = "INSERT INTO cubes (name, cube)"
                    + " VALUES ('" + name + "', '" + cube + "')" ;
            Statement statement = conn.createStatement() ;
            int rows = statement.executeUpdate(sql);
            if (!(rows>0)) {
                return false ;
            }
            conn.close();
            return true ;
        }
        catch (SQLException e) {

            return false ;
        }
    }
    public static void updateCubeSQL() {}
    public static void main(String[] args)
    {
        System.out.println(getCubeSQL("Jha")) ;
//        System.out.println(setCubeSQL("Jha", "RRRR GGGG OOOO BBBB WWWW YYYY")) ;
    }

}
