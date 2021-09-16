/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



/**
 *
 * @author asus
 */
public class customer extends javax.swing.JFrame {

    /**
     * Creates new form customer
     */
    public customer() {
        initComponents();
        jButton1.addMouseListener(new customer.SListener());//查询按钮
        jButton2.addMouseListener(new customer.CListener());//计算价格按钮
        jButton4.addMouseListener(new customer.BAAListener());//返回按钮     
        jButton5.addMouseListener(new customer.RListener());//租赁按钮     
        jButton3.addMouseListener(new customer.RESListener());//表2刷新按钮  
        jButton6.addMouseListener(new customer.RESEListener());//表1刷新按钮  
            //打开表一时展示所有数据库表中数据
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select cpl,model,price,addre,contact from car,stores where car.num=stores.num";
                ResultSet rst = stm.executeQuery(sql);
                int a=0;
                while (rst.next()) {
                    jTable1.setValueAt(rst.getString("cpl"), a, 0);
                    jTable1.setValueAt(rst.getString("model"), a, 1);
                    jTable1.setValueAt(rst.getString("price"), a, 2);
                    jTable1.setValueAt(rst.getString("addre"), a, 3);
                    jTable1.setValueAt(rst.getString("contact"), a, 4);
                    a++;
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
            
            //打开表二时展示所有数据库表中数据
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select phone,cpl,leng,expe from lease";
                ResultSet rst = stm.executeQuery(sql);
                int a=0;
                while (rst.next()) {
                    jTable2.setValueAt(rst.getString("phone"), a, 0);
                    jTable2.setValueAt(rst.getString("cpl"), a, 1);
                    jTable2.setValueAt(rst.getString("leng"), a, 2);
                    jTable2.setValueAt(rst.getString("expe"), a, 3);
                    a++;
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
    }

    
      //插入租赁信息
       class RListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "Insert into lease(phone,cpl,leng,expe)  values('" + jTextField5.getText() + "','" + jTextField4.getText() + "','" + jTextField1.getText() + "','" + jTextField2.getText() + "')";
              //  String sql2 = "Insert into lease(cpl) values(select model from car where cpl='" + jTextField4.getText() + "')";
                stm.execute(sql);
              //  stm.execute(sql2);
                JOptionPane.showMessageDialog(null, "租赁成功");

                stm.close();
                cn.close();
                MenuFrame mf = new MenuFrame();
                mf.setVisible(true);
                setVisible(false);
            } catch (ClassNotFoundException | SQLException e1) {
                JOptionPane.showMessageDialog(null, "租赁失败");

                MenuFrame mf = new MenuFrame();
                mf.setVisible(true);
                setVisible(false);
            }
        }
    }
         //刷新表2信息
          class RESListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
                          try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select phone,cpl,leng,expe from lease";
                ResultSet rst = stm.executeQuery(sql);
                int a=0;
                while (rst.next()) {
                    jTable2.setValueAt(rst.getString("phone"), a, 0);
                    jTable2.setValueAt(rst.getString("cpl"), a, 1);
                    jTable2.setValueAt(rst.getString("leng"), a, 2);
                    jTable2.setValueAt(rst.getString("expe"), a, 3);
                    a++;
                }
                setVisible(false);
                setVisible(true);
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
    }
         //刷新表1信息
          class RESEListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
                    try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select cpl,model,price,addre,contact from car,stores where car.num=stores.num";
                ResultSet rst = stm.executeQuery(sql);
                int a=0;
                while (rst.next()) {
                    jTable1.setValueAt(rst.getString("cpl"), a, 0);
                    jTable1.setValueAt(rst.getString("model"), a, 1);
                    jTable1.setValueAt(rst.getString("price"), a, 2);
                    jTable1.setValueAt(rst.getString("addre"), a, 3);
                    jTable1.setValueAt(rst.getString("contact"), a, 4);
                    a++;
                }
                setVisible(false);
                setVisible(true);
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
    }
        //查找信息，根据车辆品牌型号颜色查询
        class SListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select cpl,model,price,addre,contact from car,stores where car.num=stores.num and model like '%" + jTextField3.getText() + "%'";
                ResultSet rs = stm.executeQuery(sql);
                int k = 0;
                while (rs.next()) {
                    jTable1.setValueAt(rs.getString("cpl"), k, 0);
                    jTable1.setValueAt(rs.getString("model"), k, 1);
                    jTable1.setValueAt(rs.getString("price"), k, 2);
                    jTable1.setValueAt(rs.getString("addre"), k, 3);
                    jTable1.setValueAt(rs.getString("contact"), k, 4);
                    k++;
                }
                setVisible(false);
                setVisible(true);
                rs.close();
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
        //计算租赁价格
        class CListener extends MouseAdapter {

        private Object q;
            public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select price from car where cpl='" + jTextField4.getText() + "'";//精确查找
                ResultSet rs = stm.executeQuery(sql);//查询结果存到rs
 //               System.out.println(sql);
                rs.next();
                int p,t,y;
                         p=Integer.parseInt(jTextField1.getText());//读取租赁天数转换为整型
                       
                        t=Integer.parseInt(rs.getString("price"));//读取查询到的价格转换为整型
    //                    System.out.println(t);
   //              t=100;
                  // q.setValueAt(rs.getString("price"));
                        y=t*p;
                       jTextField2.setText(Integer.toString(y));//转换为String输出
   //             jTextField2.setText(String.valueOf(Integer.parseInt(jTextField1.getText())*Integer.parseInt(rs.getString(1))));
                

                
                rs.close();
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        }
            
        }
//定义MenuFrame类
        private static class MenuFrame {

        public MenuFrame() {
        }

        private void setVisible(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    //返回按钮
    class BAAListener extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            Login af = new Login();
            af.setVisible(true);
            setVisible(false);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("汽车租赁系统");

        jInternalFrame2.setVisible(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "车牌号", "车辆品牌型号颜色", "租赁价格", "提车门店地址", "联系电话"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("租赁天数：");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("租赁总价格：");

        jButton1.setText("查询");

        jButton2.setText("计算价格");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("车辆品牌型号颜色信息：");

        jLabel4.setText("车牌号：");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton4.setText("返回");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("租赁");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("刷新");

        jLabel5.setText("手机号：");

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(63, 63, 63)
                        .addComponent(jButton1)
                        .addGap(88, 88, 88)
                        .addComponent(jButton2)
                        .addGap(92, 92, 92)
                        .addComponent(jButton5)
                        .addGap(63, 63, 63)
                        .addComponent(jButton4))
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(62, 62, 62)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("          车辆租赁          ", jInternalFrame2);

        jInternalFrame1.setBackground(jInternalFrame1.getBackground());
        jInternalFrame1.setVisible(true);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "手机号", "车牌号", "租赁天数", "租赁价格"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("刷新");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButton3)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("          租赁历史记录          ", jInternalFrame1);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
