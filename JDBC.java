package sqlquery;

import java.sql.*;


public class JDBC 
{
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://127.0.0.1/ksiegarnia";

   //  Database credentials
   static final String USER = "username";
   static final String PASS = "password";
   
   public static void main(String[] args) 
   {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();

      String sql = "SELECT idksiazki, imieautora, nazwiskoautora, cena FROM ksiazki";
      ResultSet rs = stmt.executeQuery(sql);
      
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int idksiazki  = rs.getInt("idksiazki");
         String imieautora = rs.getString("imieautora");
         String nazwiskoautora = rs.getString("nazwiskoautora");
         double cena = rs.getDouble("cena");

         //Display values
         System.out.print("idksiazki: " + idksiazki);
         System.out.print(", imieautora: " + imieautora);
         System.out.print(", nazwiskoautora: " + nazwiskoautora);
         System.out.println(", cena: " + cena);
      }
      rs.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end JDBCExample