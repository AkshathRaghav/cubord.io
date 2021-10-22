    package net.codejava ;

    import java.sql.*;

    public class Update {
        public static final String jdbc = "" ;
        public static final String username = "" ;
        public static final String password = "" ;
        public static  String getCubeSQL(String name) {
            String s = "" ;

            try {
                Connection conn = DriverManager.getConnection(jdbc, username, password);
                final String queryCheck = "SELECT * from cubes WHERE name = ?";
                final PreparedStatement ps = conn.prepareStatement(queryCheck);
                ps.setString(1, name);
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()) {
                    String sql = "SELECT name,cube "
                            + "FROM cubes "
                            + "WHERE name = ?";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, name);
                    ResultSet result = statement.executeQuery();

                    while (result.next()) {
                        s = result.getString("cube");

                    }
                }
                else { return "Error" ; }
                conn.close();
            }
            catch (SQLException e) {
                return "Error" ;
            }
            return s ;
        }
        public static  String getStoreSQL(String name) {
            String s = "" ;

            try {
                Connection conn = DriverManager.getConnection(jdbc, username, password);
                String sql = "SELECT name,store "
                        + "FROM cubes "
                        + "WHERE name = ?";
                PreparedStatement statement = conn.prepareStatement(sql) ;
                statement.setString(1, name);
                ResultSet result = statement.executeQuery() ;

                while (result.next()) {
                    s = result.getString("store");
                    if ( s != null && s.length() > 50 ) {
                        s = s.substring(0,50) ;
                    }
                }
                conn.close();
            }
            catch (SQLException e) {
                return "Error" ;
            }
            return s ;
        }
        public static  String getsSolvedSQL(String name) {
            String s = "" ;

            try {
                Connection conn = DriverManager.getConnection(jdbc, username, password);
                String sql = "SELECT name,solvedatfirst "
                        + "FROM cubes "
                        + "WHERE name = ?";
                PreparedStatement statement = conn.prepareStatement(sql) ;
                statement.setString(1, name);
                ResultSet result = statement.executeQuery() ;

                while (result.next()) {
                    s = result.getString("solvedatfirst");

                }
                conn.close();
            }
            catch (SQLException e) {
                return "Error" ;
            }
            return s ;
        }
        public static String getTimeSQL(String name, boolean check ) {
            String s = "" ;

            try {
                Connection conn = DriverManager.getConnection(jdbc, username, password);
                String sql = "" ;
                if (check) {
                     sql = "SELECT name,time "
                            + "FROM cubes "
                            + "WHERE name = ?";
                }
                else {
                     sql = "SELECT name,besttime "
                            + "FROM cubes "
                            + "WHERE name = ?";
                }
                PreparedStatement statement = conn.prepareStatement(sql) ;
                statement.setString(1, name);
                ResultSet result = statement.executeQuery() ;

                while (result.next()) {
                    if ( check ) {
                        s = result.getString("time");
                    }
                    else {
                        s = result.getString("besttime");
                    }
                }
                conn.close();
            }
            catch (SQLException e) {
                return "Error" ;
            }
            return s ;
        }
        public static  void setCubeSQL(String name, String store,  String cube) throws Error {

            try {
                Connection conn = DriverManager.getConnection(jdbc, username, password);
                final String queryCheck = "SELECT * from cubes WHERE name = ?";
                final PreparedStatement ps = conn.prepareStatement(queryCheck);
                ps.setString(1, name);
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()) {
                    String SQL = "UPDATE cubes "
                            + "SET cube = ?,store = ?"
                            + "WHERE name = ?";
                    PreparedStatement pstmt = conn.prepareStatement(SQL) ;
                    pstmt.setString(1, cube);
                    pstmt.setString(2, store);
                    pstmt.setString(3, name);
                    int rows = pstmt.executeUpdate();
                }
                else {
                    String sql = "INSERT INTO cubes (name, cube)"
                            + " VALUES ('" + name + "', '" + cube + "')";
                    Statement statement = conn.createStatement();
                    int rows = statement.executeUpdate(sql);
                    if (!(rows > 0)) {
                        throw new Error() ;
                    }
                }
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
                throw new Error() ;
            }
        }
        public static void setTimeSQL(String name, String time, boolean check) {
            String jdbc = "jdbc:postgresql://localhost:5432/Cubord" ;
               String username = "postgres" ;
               String password = "Akshath$$123" ;
            try {

                Connection conn = DriverManager.getConnection(jdbc, username, password);
                String SQL = "" ;
                if (check) {
                     SQL = "UPDATE cubes "
                            + "SET time = ? "
                            + "WHERE name = ?";
                }
                else {
                     SQL = "UPDATE cubes "
                            + "SET besttime = ? "
                            + "WHERE name = ?";
                }
                PreparedStatement pstmt = conn.prepareStatement(SQL) ;
                pstmt.setString(1, time);
                pstmt.setString(2, name);
                int rows = pstmt.executeUpdate();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
                throw new Error() ;
            }
        }

        public static  void setSolvedSQL(String name, String solved) {

            try {
                Connection conn = DriverManager.getConnection(jdbc, username, password);
                String SQL = "UPDATE cubes "
                        + "SET solvedatfirst = ? "
                        + "WHERE name = ?";
                PreparedStatement pstmt = conn.prepareStatement(SQL) ;
                pstmt.setString(1, solved);
                pstmt.setString(2, name);
                int rows = pstmt.executeUpdate();
                conn.close();
            }
            catch (SQLException e) {
                throw new Error() ;
            }
        }


    }
