/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apinvoice;
import java.sql.*;
/**
 *
 * @author Joy Bebe
 */
public class DbCon {
    public void DB(){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDrver");
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-JOY; DatabaseName=AppMartInvoice; IntegratedSecurity=true");
        }
        catch(Exception e){
            
        }
    }
}
