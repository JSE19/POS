/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apinvoice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Joy Bebe
 */

public class InvoiceGenerator {
Connection con;
PreparedStatement pst;
ResultSet rs;
int count=0;
String invoiceID = "";
    public String invoiceIDGenerator(){
		long decimalNumber=System.nanoTime();
		String strBaseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		int mod = 0;
		while(decimalNumber!= 0){
            mod=(int) (decimalNumber % 36);
            invoiceID=strBaseDigits.substring(mod,mod+1)+invoiceID;
            decimalNumber=decimalNumber/36;
        }
		return invoiceID;

	}
     public void Connection(){
    try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-JOY; DatabaseName=AppMartInvoice; IntegratedSecurity=true");
        }
        catch(Exception e){
            
        }
     }
    public void writeToFile(int CusId, String InvId, String CusEmail ){
        String separator = System.getProperty("line.separator");
        CusId = 0;
        String TransactionTime = new String();
        String TransactionDate = new String();
        String Item = new String();
        int  Quantity =0;
        float UnitPrice=0;
        float Total=0;
        float FinalTotal=0;
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-JOY; DatabaseName=AppMartInvoice; IntegratedSecurity=true");
            String sqladd = "\"select TransactionTime, TransactionDate, Item,ItemQuantity,Total, CustomerId from KeepTransaction where CustomerId='" +CusId + "'";
            pst = con.prepareStatement(sqladd);
            rs = pst.executeQuery();
            while(rs.next()){
                TransactionTime = rs.getString("TransactionTime");
                TransactionDate = rs.getString("TransactionDate");
                Item = rs.getString("Item");
                Quantity = rs.getInt("ItemQuantity");
                Total = rs.getFloat("Total");
                CusId = rs.getInt("CustomerId");
            }
            String Sum = "select Sum(Total)  from KeepTransaction where CustomerId ='" + CusId + "'";
                pst = con.prepareStatement(Sum);
                rs = pst.executeQuery();
                
                
                while (rs.next()) {
             float c = rs.getFloat(1);
             FinalTotal = FinalTotal + c;
 
        }
                
                String row = "select Count(Total) from KeepTransaction where CustomerId ='" + CusId+"'";
                pst = con.prepareStatement(row);
                rs = pst.executeQuery();
                while(rs.next()){
                    count = rs.getInt(1);
                }
                
                File file = new File(invoiceID);
                file.createNewFile();
			Writer writer = new BufferedWriter(new FileWriter(file));
			writer.write(separator+" Invoice ID: "+ CusId);
			writer.write(separator+" Company Name: Sales Company");
			writer.write(separator+"------------------------------------------------------------------------------------------------------------------");
			writer.write(separator+" Transaction Time.: "+TransactionTime);
			writer.write(separator+" Transaction Date.: "+TransactionDate);
                        writer.write(separator+" CustomerEmail.: "+CusEmail);
			
                        
                            
                        writer.write(separator+"------------------------------------------------------------------------------------------------------------------");
			writer.write(separator+"Item:" + Item);
			writer.write(separator+"Unit Price"+ UnitPrice);
			writer.write(separator+"Quantity: "+ Quantity);
                        writer.write(separator+" Total.: "+Total);
                        
                        
			writer.write(separator+"------------------------------------------------------------------------------------------------------------------");
			writer.write(separator+" Final Total .: "+FinalTotal);

			writer.flush();
			con.close();
        }
        catch(Exception e){
            
        }
    }
    
}

