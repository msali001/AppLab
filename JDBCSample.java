import java.io.*;
import java.sql.*;


public class JDBCSample {
      public static void main(String[] args) throws IOException{
            //Load the PostgreSQL JDBC driver class
            try{
                  Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException cnfe){
                  System.out.println("Could not find the JDBC driver!");
                  System.exit(1);
            }

            //Enter the connection details
            String hostname = "192.168.11.21";	// If PostgreSQL is running on some other machine enter the IP address of the machine here
            String username = "lpkd16cs064"; // Enter your PostgreSQL username
            String password = "password"; // Enter your PostgreSQL password
            String dbName = "lpkd16cs064db"; // Enter the name of the database that has the university tables.
            String connectionUrl = "jdbc:postgresql://" + hostname +  "/" + dbName;
            Connection conn = null;

            //Connect to the database
            try {
                  conn = DriverManager.getConnection(connectionUrl,username, password);
                  System.out.println("Connected successfullly");
            } catch (SQLException sqle) {
                  System.out.println("Connection failed");
                  System.out.println(sqle);
            // Uncomment the below line for a more detailed stack trace
            // sqle.printStackTrace();
            System.exit(1);
            }

            //Execute the query and print the results
            try {
            //                    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            //		    System.out.println("Enter a s name:");
            //                  String deptName = br.readLine();
            // select course.titlefrom course,takes where student.id = ?
            //System.out.println(args[0]);
                  PreparedStatement pstmt=conn.prepareStatement("select course.title from course,takes where takes.ID = ? and"
                                                                        +" course.course_id=takes.course_id");
                  pstmt.setString(1, args[0]);
                  ResultSet rs = pstmt.executeQuery();
                  boolean flag= false;
                  while(rs.next()) {
                        String name = rs.getString(1);
                        System.out.println(name);
                        flag = true;
                  }
                  if(flag == false)
                        System.out.println("Not a valid  ID");
                        
                  conn.close();

            }
            catch (SQLException sqle ) {
                  System.out.println(sqle);
                  System.exit(1);
            }
            catch (ArrayIndexOutofBoundException e ) {
                  System.out.println("No Student Id Entered");
                  System.exit(1);
            }
      }
}
