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
            // Static IP = 14.139.183.243
            // Local IP = 192.168.11.21  ---- Not Sure
            String hostname = "14.139.183.243";	// If PostgreSQL is running on some other machine enter the IP address of the machine here
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
                  PreparedStatement pstmt;
                  //PreparedStatement pstmt=conn.prepareStatement("select course.title from course,takes where takes.ID = ? and"
                  //                                                      +" course.course_id=takes.course_id");
                  //pstmt =conn.prepareStatement("select * from track where stcode1 = ? and stcode2 = ?");

                  //pstmt.setString(1, args[0]);
                 // pstmt.setString(2, args[1]);
                 // ResultSet rs = pstmt.executeQuery();
                  //boolean flag= false;
                  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                  System.out.print("Enter query:");
                  String query = br.readLine();
                  int n = 0;
                  char array[] = query.toCharArray();
                  pstmt =conn.prepareStatement(query);
                  for (int i = 0; i < array.length ; i++) {
                        if(array[i] == '?') {
                              n++;
                              System.out.print("Enter Parameter("+n+") : ");
                              String param = br.readLine();
                              pstmt.setString(n,param);
                        }
                  }
                  ResultSet rs = pstmt.executeQuery();
                  ResultSetMetaData rsmd = rs.getMetaData();
                  int count = rsmd.getColumnCount();
                  while(rs.next()) {
                        for (int i = 1; i <= count; i++) {
                              System.out.print(rs.getString(i)+"\t");
                        }
                        System.out.println("");

                  }

/*                  for (int i = 0; i < 10 ; i++) {
                  System.out.println("Enter Parameter(i:");
                  if(rs.next()) {
                        pstmt =conn.prepareStatement("update track set distance = ? where stcode1 = ? and stcode2 = ?");
                        pstmt.setInt(1, Integer.parseInt(args[2]));
                        pstmt.setString(2, args[0]);
                        pstmt.setString(3, args[1]);
                        pstmt.executeUpdate();
                        System.out.println("Updated");

                  }
                  else {
                        pstmt =conn.prepareStatement("insert into track values(?,?,?)");
                        pstmt.setString(1, args[0]);
                        pstmt.setString(2, args[1]);
                        pstmt.setInt(3, Integer.parseInt(args[2]));
                        pstmt.executeUpdate();
                        System.out.println("Inserted");
                  }


 */
                  conn.close();

            }
            catch (SQLException sqle ) {
                  System.out.println(sqle);
                  System.exit(1);
            }
            catch (Exception e) {
                  System.out.println("Check your input error :"+e);
                  e.printStackTrace();
                  System.exit(1);
            }
      }
}
