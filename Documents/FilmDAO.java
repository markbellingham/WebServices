import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;


public class FilmDAO {
    
    Film oneFilm = null;
    Connection conn = null;
    Statement stmt = null;
    public static final String userid = "ENTER_YOUR_MYSQL_USERID_HERE";
    public static final String userpass = "ENTER_YOUR_MYSQL_PASSWORD_HERE";
    public FilmDAO() {}

    
    private void openConnection(){
        // loading jdbc driver for mysql
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception e) { System.out.println(e); }

        // connecting to database
        try{
            // connection string for demos database, username demos, password demos
            conn = DriverManager.getConnection
            ("jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:3306/"+
                 userid + "?user=" + userid + "&password="+userpass);
            stmt = conn.createStatement();
        } catch(SQLException se) { System.out.println(se); }       
    }
    private void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Film getNextFilm(ResultSet rs){
        Film thisFilm=null;
        try {
            thisFilm = new Film(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("year"),
                    rs.getString("director"),
                    rs.getString("stars"),
                    rs.getString("review"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return thisFilm;        
    }
    
    
    
   public ArrayList<Film> getAllFilms(){
       
        ArrayList<Film> allFilms = new ArrayList<Film>();
        openConnection();
        
        // Create select statement and execute it
        try{
            String selectSQL = "select * from nick_student.films";
            ResultSet rs1 = stmt.executeQuery(selectSQL);
        // Retrieve the results
            while(rs1.next()){
                oneFilm = getNextFilm(rs1);
                allFilms.add(oneFilm);
           }

            stmt.close();
            closeConnection();
        } catch(SQLException se) { System.out.println(se); }

       return allFilms;
   }

   public Film getFilmByID(int id){
       
        openConnection();
        oneFilm=null;
        // Create select statement and execute it
        try{
            String selectSQL = "select * from nick_student.films where id="+id;
            ResultSet rs1 = stmt.executeQuery(selectSQL);
        // Retrieve the results
            while(rs1.next()){
                oneFilm = getNextFilm(rs1);
            }

            stmt.close();
            closeConnection();
        } catch(SQLException se) { System.out.println(se); }

       return oneFilm;
   }
   
   
   
   
}
