/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apinvoice;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Joy Bebe
 */
public class ItemsSales extends javax.swing.JFrame {

    /**
     * Creates new form ItemsSales
     */
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int count =0;
    int timerun=0;
    InvoiceGenerator inv = new InvoiceGenerator();
    public ItemsSales( ) {
        initComponents();
        FillCombo();
        tblAddedList.setVisible(false);
        
        new Thread(){
            
            public void run(){
                while(timerun ==0){
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int AMPM = cal.get(Calendar.AM_PM);
        String dayNight ="";
        if(AMPM ==1){
            dayNight ="PM";
        }
        else{
            dayNight = "AM";
        }
        txtTransDate.setText(""+ year + "/" +(month+1) + "/" + day+ " " );
        
        
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        txtTransTime.setText("" + hour + ":"+(minute)+ ":" + second+ " "+dayNight);
            }
            }
        }.start();
    }
    
   // DB CONNECTION STRING
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
           Connection();
            String sqladd = "select TransactionTime, TransactionDate, Item,ItemQuantity,Total, CustomerId from KeepTransaction where CustomerId='" +CusId + "'";
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
                
                File file = new File(InvId);
                file.createNewFile();
			Writer writer = new BufferedWriter(new FileWriter(file));
			writer.write(separator+" Invoice ID: "+ CusId);
			writer.write(separator+" Company Name: Sales Company");
			writer.write(separator+"------------------------------------------------------------------------------------------------------------------");
			writer.write(separator+" Transaction Time.: "+TransactionTime);
			writer.write(separator+" Transaction Date.: "+TransactionDate);
                        writer.write(separator+" CustomerEmail.: "+CusEmail);
                        writer.write(separator+"------------------------------------------------------------------------------------------------------------------"); 
			writer.write(separator+"------------------------------------------------------------------------------------------------------------------");
			writer.write(separator+" Final Total .: "+FinalTotal);

			writer.flush();
			con.close();
        }
        catch(Exception e){
            
        }
    }
    
        public void close(){
        WindowEvent winclose = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winclose);
    }
 private void UpdateTable(){
        try{
        String sql = "select * from KeepTransaction ";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        tblAddedList.setModel(DbUtils.resultSetToTableModel(rs));
    }
        catch(Exception e){
            
        }
 }
    private void FillCombo(){
        try{
            Connection();
            String sql = "Select * from Items";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String name = rs.getString("ItemName");
                cboItemList.addItem(name);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTransTime = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        cboItemList = new javax.swing.JComboBox();
        txtTransDate = new javax.swing.JTextField();
        txtCusId = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAddItems = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAddedList = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSum = new javax.swing.JTextField();
        btnCreateCus = new javax.swing.JButton();
        btnCreateCus1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transaction");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTransTime.setEditable(false);
        getContentPane().add(txtTransTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 200, -1));

        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });
        getContentPane().add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 200, -1));

        txtPrice.setEditable(false);
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 200, -1));

        cboItemList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboItemListItemStateChanged(evt);
            }
        });
        cboItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboItemListMouseReleased(evt);
            }
        });
        cboItemList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboItemListActionPerformed(evt);
            }
        });
        getContentPane().add(cboItemList, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 200, -1));

        txtTransDate.setEditable(false);
        getContentPane().add(txtTransDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 200, -1));

        txtCusId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCusIdKeyReleased(evt);
            }
        });
        getContentPane().add(txtCusId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 200, -1));
        getContentPane().add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 18, 103, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CustomerId");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 100, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Transaction Date");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 120, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Transaction Time");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 120, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Item");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 60, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 80, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Unit Price");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 90, -1));

        btnAddItems.setBackground(new java.awt.Color(204, 102, 0));
        btnAddItems.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btnAddItems.setForeground(new java.awt.Color(255, 255, 255));
        btnAddItems.setText("Add");
        btnAddItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemsActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 80, -1));

        jButton3.setBackground(new java.awt.Color(204, 102, 0));
        jButton3.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Invoice");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 100, -1));

        tblAddedList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TransactionDate", "TransactionTime", "Item", "ItemQuantity", "UnitPrice", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblAddedList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 550, 100));

        jLabel7.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 70, -1));

        txtTotal.setEditable(false);
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 200, -1));

        btnClear.setBackground(new java.awt.Color(204, 102, 0));
        btnClear.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 90, -1));

        jButton2.setBackground(new java.awt.Color(204, 102, 0));
        jButton2.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, -1, 30));

        txtEmail.setEditable(false);
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 170, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/apinvoice/493113084-612x612.jpg"))); // NOI18N
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 410));

        txtSum.setEditable(false);
        txtSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumActionPerformed(evt);
            }
        });
        getContentPane().add(txtSum, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 420, 90, 30));

        btnCreateCus.setBackground(new java.awt.Color(204, 102, 0));
        btnCreateCus.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btnCreateCus.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateCus.setText("Add Customer");
        btnCreateCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateCusActionPerformed(evt);
            }
        });
        getContentPane().add(btnCreateCus, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 120, 30));

        btnCreateCus1.setBackground(new java.awt.Color(204, 102, 0));
        btnCreateCus1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btnCreateCus1.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateCus1.setText("Add Items");
        btnCreateCus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateCus1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCreateCus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 120, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cboItemListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboItemListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboItemListActionPerformed

    private void btnAddItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemsActionPerformed
        // TODO add your handling code here:
        Connection();
        try{
            String sql = "Insert into KeepTransaction (TransactionTime,TransactionDate,Item,ItemQuantity,UnitPrice,Total,UserId,CustomerId)Values(?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1,txtTransTime.getText());
            pst.setString(2,txtTransDate.getText());
            pst.setString(3,cboItemList.getSelectedItem().toString());
            pst.setString(4,txtQuantity.getText());
            pst.setString(5,txtPrice.getText());
            pst.setString(6,txtTotal.getText());
            pst.setString(7,txtUser.getText());
            pst.setString(8,txtCusId.getText());
            
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Successfully Added");
            String id = txtCusId.getText();
            String sqladd = "select Item,ItemQuantity,Total, CustomerId,UnitPrice from KeepTransaction where CustomerId='" +id + "'";
            pst = con.prepareStatement(sqladd);
            rs = pst.executeQuery();
            tblAddedList.setModel(DbUtils.resultSetToTableModel(rs));
            tblAddedList.setVisible(true);
            //txtQuantity.setText("");
            //txtTotal.setText("");
            
            String Sum = "select Sum(Total)  from KeepTransaction where CustomerId ='"+ id +"'";
                pst = con.prepareStatement(Sum);
                rs = pst.executeQuery();
                
                int sum=0;
                while (rs.next()) {
             int c = rs.getInt(1);
             sum = sum + c;
    }
                txtSum.setText(""+sum);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
    }
        
    }//GEN-LAST:event_btnAddItemsActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here: 
        txtCusId.setText("");
        txtQuantity.setText("");
        txtTotal.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        close();
        Login objLog = new Login();
        objLog.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboItemListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboItemListItemStateChanged
        // TODO add your handling code here:
        /*
       try{
        String get = cboItemList.getSelectedItem().toString();
        String getprice= "Select UnitPrice from Items where ItemName= '" + get+"'";
        pst = con.prepareStatement(getprice);
      rs = pst.executeQuery();
      if(rs.next()){
          String Item = rs.getString("UnitPrice");
          txtPrice.setText(Item);
      }    
    }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
      }*/
    }//GEN-LAST:event_cboItemListItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

           String TransactionTime = new String();
                String TransactionDate = new String();
                String Item = new String();
                float UnitPrice=0;
                int Quantity = 0;
                float Total = 0;
                int CusI = 0;
        int num = -1;
                        ArrayList<Integer> arNumber = new ArrayList<Integer>();
                        for(int x = 0; x < 10; x++)
            {
                      arNumber.add(x);
}
                        Collections.shuffle(arNumber);

                        String strNum = "";
                        for(int i = 0; i < 4; i++)
                       strNum = strNum + arNumber.get(new Random().nextInt(10));

                        num = Integer.parseInt(strNum);
                        
                 String CusId = txtCusId.getText();
                 String CusEmail = txtEmail.getText();
                 
                String separator = System.getProperty("line.separator");
        
        
        try{
           Connection();
            String sqladd = "select TransactionTime, TransactionDate, Item,ItemQuantity,Total, CustomerId,UnitPrice from KeepTransaction where CustomerId='" +CusId + "'";
            pst = con.prepareStatement(sqladd);
            rs = pst.executeQuery();
            while(rs.next()){
               TransactionTime = rs.getString("TransactionTime");
                TransactionDate = rs.getString("TransactionDate");
                Item = rs.getString("Item");
                UnitPrice =rs.getFloat("UnitPrice");
                Quantity = rs.getInt("ItemQuantity");
                Total = rs.getFloat("Total");
                 CusI = rs.getInt("CustomerId");
            }
            String Sum = "select Sum(Total)  from KeepTransaction where CustomerId ='" + CusId + "'";
                pst = con.prepareStatement(Sum);
                rs = pst.executeQuery();
                
                float FinalTotal=0;
                while (rs.next()) {
             float c = rs.getFloat(1);
             FinalTotal = FinalTotal + c;
             
             File file = new File("temp6.pdf");
                file.createNewFile();
                Writer writer = new BufferedWriter(new FileWriter(file));
             String row = "select Count(Total) from KeepTransaction where CustomerId ='" + CusId+"'";
                pst = con.prepareStatement(row);
                rs = pst.executeQuery();
                while(rs.next()){
                    count = rs.getInt(1);  
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
                }
                
                
			con.close();
             
 
        }
                
                
        }
        catch(Exception e){
            
        }
        
        if(txtCusId.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"No field should be empty");
        }
        else{
            Connection();
            try{
               // String cus = txtCusId.getText();
                TableModel model1 = tblAddedList.getModel();
                
                int indexs[]=tblAddedList.getSelectedRows();
                Object[] row = new Object[6];
                
                Invoice objInvoice = new Invoice();
                DefaultTableModel model2 = (DefaultTableModel)objInvoice.tblInvoice.getModel();
                
                for (int i =0; i < indexs.length; i++){
                    row[0] = model1.getValueAt(indexs[i],0);
                    row[1] = model1.getValueAt(indexs[i],1);
                    row[2] = model1.getValueAt(indexs[i],2);
                    row[3] = model1.getValueAt(indexs[i],3);
                    row[4] = model1.getValueAt(indexs[i],4);
                    
                    
                    model2.addRow(row);
                }
                
  
                
                close();
                objInvoice.setVisible(true);
              
        
                
                
            }
            catch(Exception e){
                
            }
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // Populaing Total
        float getPrice = Float.parseFloat(txtPrice.getText());
        float Quantity = Float.parseFloat(txtQuantity.getText());
        float Total = getPrice * Quantity;
        txtTotal.setText(""+Total);
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void cboItemListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboItemListMouseReleased
        // TODO add your handling code here:
        try{
        String get = cboItemList.getSelectedItem().toString();
        String getprice= "Select UnitPrice from Items where ItemName= '" + get+"'";
        pst = con.prepareStatement(getprice);
      rs = pst.executeQuery();
      if(rs.next()){
          String Item = rs.getString("UnitPrice");
          txtPrice.setText(Item);
      }    
    }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
    }//GEN-LAST:event_cboItemListMouseReleased

    private void txtCusIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCusIdKeyReleased
        // TODO add your handling code here:
        try{
            Connection();
            String get = txtCusId.getText();
            String getEmail= "Select Email from CustomerReg where CustomerId= '" + get+"'";
        pst = con.prepareStatement(getEmail);
      rs = pst.executeQuery();
      if(rs.next()){
          String Item = rs.getString("Email");
          txtEmail.setText(Item);
      }    
        }
        catch(Exception e){
            
        }
    }//GEN-LAST:event_txtCusIdKeyReleased

    private void btnCreateCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateCusActionPerformed
        // TODO add your handling code here:
        close();
        RegisterClient reg = new RegisterClient();
        reg.setVisible(true);
    }//GEN-LAST:event_btnCreateCusActionPerformed

    private void txtSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSumActionPerformed

    private void btnCreateCus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateCus1ActionPerformed
        // TODO add your handling code here:
        close();
        AddItems objAdd = new AddItems();
        objAdd.setVisible(true);
    }//GEN-LAST:event_btnCreateCus1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ItemsSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemsSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemsSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemsSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItemsSales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItems;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreateCus;
    private javax.swing.JButton btnCreateCus1;
    private javax.swing.JComboBox cboItemList;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAddedList;
    private javax.swing.JTextField txtCusId;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSum;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTransDate;
    private javax.swing.JTextField txtTransTime;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
