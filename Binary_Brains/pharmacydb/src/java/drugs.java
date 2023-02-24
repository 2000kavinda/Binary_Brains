import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
@WebServlet("/drugs")
 
public class drugs extends HttpServlet {
    
    Connection con;
    PreparedStatement pst;
    int row;
    
    public void doPost(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/inventory","root","");
            String dname = req.getParameter("dname");
            String manufacturer = req.getParameter("manufacturer");
            String supplier= req.getParameter("supplier");
            String ndc= req.getParameter("ndc");
            String date= req.getParameter("date");
            String count= req.getParameter("count");
            String price= req.getParameter("price");
            
            pst = con.prepareStatement("insert into drugs(id,name,manufacturer,supplier,ndc,date,amount,price)values(?,?,?,?,?,?,?,?) ");
            pst.setString(1, dname);
            pst.setString(2, manufacturer);
            pst.setString(3, supplier);
            pst.setString(3, ndc);
            pst.setString(3, date);
            pst.setString(3, count);
            pst.setString(3, price);
            row = pst.executeUpdate();
            
            out.println("<font color='green'>  Record Addedddd   </font>");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(drugs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
          
             out.println("<font color='red'>  Record Failed   </font>");
        }
    }
  
}
