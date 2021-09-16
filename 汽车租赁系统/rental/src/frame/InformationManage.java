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
public class InformationManage extends javax.swing.JFrame {

    /**
     * Creates new form CarInformation
     */

    
    public InformationManage() {
        initComponents();
        //表一
        jButton7.addMouseListener(new UPListener());//更新按钮
        jButton6.addMouseListener(new DELListener());//删除按钮
        jButton5.addMouseListener(new ADDListener());//增加按钮
        jButton9.addMouseListener(new BACKListener());//返回按钮
        jButton8.addMouseListener(new SELListener());//查询手机号按钮
        jButton11.addMouseListener(new REListener());//刷新按钮
        jButton13.addMouseListener(new NAListener());//查询姓名按钮
        jButton14.addMouseListener(new CAListener());//查询驾驶证号按钮
        //表二
        jButton1.addMouseListener(new ADListener());//插入按钮
        jButton2.addMouseListener(new DEListener());//删除按钮
        jButton3.addMouseListener(new UListener());//更新按钮
        jButton4.addMouseListener(new SEListener());//查询按钮
        jButton10.addMouseListener(new BAListener());//返回按钮
        jButton12.addMouseListener(new RListener());//刷新按钮
        //表三
        jButton16.addMouseListener(new DELEListener());//表3刷新按钮
        jButton15.addMouseListener(new RESListener());//表3删除按钮
        
            //打开表一时展示所有数据库表中数据
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select phone,name,license from customer";//数据库执行语句查询信息
                ResultSet rst = stm.executeQuery(sql);
                int a = 0;//循环填入表中
                while (rst.next()) {
                    jTable1.setValueAt(rst.getString("phone"), a, 0);
                    jTable1.setValueAt(rst.getString("name"), a, 1);
                    jTable1.setValueAt(rst.getString("license"), a, 2);
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
                String sql = "select cpl,model,price,num from car";//数据库执行语句查询信息
                ResultSet rst = stm.executeQuery(sql);
                int a = 0;//循环填入表中
                while (rst.next()) {
                    jTable2.setValueAt(rst.getString("cpl"), a, 0);
                    jTable2.setValueAt(rst.getString("model"), a, 1);
                    jTable2.setValueAt(rst.getString("price"), a, 2);
                    jTable2.setValueAt(rst.getString("num"), a, 3);
                    a++;
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
            //打开表三时展示所有数据库表中数据
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select phone,cpl,leng,expe from lease";
                ResultSet rst = stm.executeQuery(sql);
                int a=0;
                while (rst.next()) {
                    jTable3.setValueAt(rst.getString("phone"), a, 0);
                    jTable3.setValueAt(rst.getString("cpl"), a, 1);
                    jTable3.setValueAt(rst.getString("leng"), a, 2);
                    jTable3.setValueAt(rst.getString("expe"), a, 3);
                    a++;
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
  
    
    //更新数据，更新客户手机号
        class UPListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "update customer set phone=" + jTextField5.getText() + "where name='" + jTextField6.getText() + "' and license='"+jTextField7.getText()+"'";

                if (stm.executeUpdate(sql) != 0) {
                    JOptionPane.showMessageDialog(null, "数据更新成功");
                } else {
                    JOptionPane.showMessageDialog(null, "数据更新失败");
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
            MenuFrame mf = new MenuFrame();
            mf.setVisible(true);
            setVisible(false);
        }
    }
        
      //插入信息
       class ADDListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "Insert into customer(phone,name,license)  values('" + jTextField5.getText() + "','" + jTextField6.getText() + "','" + jTextField7.getText() + "')";
                stm.execute(sql);
                JOptionPane.showMessageDialog(null, "插入成功");
                stm.close();
                cn.close();
                MenuFrame mf = new MenuFrame();
                mf.setVisible(true);
                setVisible(false);
            } catch (ClassNotFoundException | SQLException e1) {
                JOptionPane.showMessageDialog(null, "该信息已存在，插入失败");
                MenuFrame mf = new MenuFrame();
                mf.setVisible(true);
                setVisible(false);
            }
        }
    }
    
    //删除信息，根据手机号删除信息
       class DELListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "delete from customer where phone='" + jTextField5.getText() + "'";//and license='" + jTextField7.getText() + "'
                if (stm.executeUpdate(sql) != 0) {
                    JOptionPane.showMessageDialog(null, "删除成功");
                } else {
                    JOptionPane.showMessageDialog(null, "该信息不存在，删除失败");
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
            MenuFrame mf = new MenuFrame();
            mf.setVisible(true);
            setVisible(false);
        }
    }
    
    
//定义MenuFrame类
    private static class MenuFrame {

        public MenuFrame() {
        }

        private void setVisible(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.更改生成方法的主体
        }
    }
   
           //根据姓名查找信息
     class CAListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select phone,name,license from customer where license like '%" + jTextField7.getText() + "%'";
                ResultSet rs = stm.executeQuery(sql);
                int k = 0;
                while (rs.next()) {
                    jTable1.setValueAt(rs.getString("phone"), k, 0);
                    jTable1.setValueAt(rs.getString("name"), k, 1);
                    jTable1.setValueAt(rs.getString("license"), k, 2);
                    k++;
                }
                rs.close();
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
    }
    
        //根据姓名查找信息
     class NAListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select phone,name,license from customer where name like '%" + jTextField6.getText() + "%'";
                ResultSet rs = stm.executeQuery(sql);
                int k = 0;
                while (rs.next()) {
                    jTable1.setValueAt(rs.getString("phone"), k, 0);
                    jTable1.setValueAt(rs.getString("name"), k, 1);
                    jTable1.setValueAt(rs.getString("license"), k, 2);
                    k++;
                }
                rs.close();
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
    }
    
    //根据手机号查找信息
     class SELListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select phone,name,license from customer where phone like '%" + jTextField5.getText() + "%'";//精确查找
                ResultSet rs = stm.executeQuery(sql);
                int k = 0;
                while (rs.next()) {
                    jTable1.setValueAt(rs.getString("phone"), k, 0);
                    jTable1.setValueAt(rs.getString("name"), k, 1);
                    jTable1.setValueAt(rs.getString("license"), k, 2);
                    k++;
                }
                rs.close();
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
    }
     //刷新信息
          class REListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
                        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select phone,name,license from customer";//重新从数据库查找
                ResultSet rs = stm.executeQuery(sql);
                int k = 0;
                while (rs.next()) {
                    jTable1.setValueAt(rs.getString("phone"), k, 0);
                    jTable1.setValueAt(rs.getString("name"), k, 1);
                    jTable1.setValueAt(rs.getString("license"), k, 2);
                    k++;
                }
                setVisible(false);
                setVisible(true);
                rs.close();
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
    }
     
//返回登录
    class BACKListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Login af = new Login();
            af.setVisible(true);
            setVisible(false);
        }
    }
/************22222222222222222222222222222222表二22222222222222222222222222222222222222222222*****************************/

    
  
    
    //更新数据，更新价格
        class UListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "update car set price=" + jTextField3.getText() + "where cpl='" + jTextField1.getText() + "' and model='"+jTextField2.getText()+"' and num='"+jTextField4.getText()+"'";
                if (stm.executeUpdate(sql) != 0) {
                    JOptionPane.showMessageDialog(null, "价格更新成功");
                } else {
                    JOptionPane.showMessageDialog(null, "价格更新失败");
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
            MenuFrame mf = new MenuFrame();
            mf.setVisible(true);
            setVisible(false);
        }
    }
      //插入信息
       class ADListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "Insert into car(cpl,model,price,num)  values('" + jTextField1.getText() + "','" + jTextField2.getText() + "','" + jTextField3.getText() + "','" + jTextField4.getText() + "')";
                stm.execute(sql);
                JOptionPane.showMessageDialog(null, "插入成功");

                stm.close();
                cn.close();
                MenuFrame mf = new MenuFrame();
                mf.setVisible(true);
                setVisible(false);
            } catch (ClassNotFoundException | SQLException e1) {
                JOptionPane.showMessageDialog(null, "该信息已存在，插入失败");

                MenuFrame mf = new MenuFrame();
                mf.setVisible(true);
                setVisible(false);
            }
        }
    }
    
    //删除信息，根据车牌号删除信息
       class DEListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "delete from car where cpl='" + jTextField1.getText() + "'";//根据车牌号删除信息and model='" + jTextField2.getText() + "'and price='" + jTextField3.getText() + "'and num='" + jTextField4.getText() + "'
                if (stm.executeUpdate(sql) != 0) {
                    JOptionPane.showMessageDialog(null, "删除成功");
                } else {
                    JOptionPane.showMessageDialog(null, "该信息不存在，删除失败");
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
            MenuFrame mf = new MenuFrame();
            mf.setVisible(true);
            setVisible(false);
        }
    }
    
//查找信息，根据汽车品牌颜色查找信息
     class SEListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "select cpl,model,price,num from car where model like '%" + jTextField2.getText() + "%'";
                ResultSet rs = stm.executeQuery(sql);
                int k = 0;
                while (rs.next()) {
                    jTable2.setValueAt(rs.getString("cpl"), k, 0);
                    jTable2.setValueAt(rs.getString("model"), k, 1);
                    jTable2.setValueAt(rs.getString("price"), k, 2);
                    jTable2.setValueAt(rs.getString("num"), k, 3);
                    k++;
                }
                setVisible(false);
                setVisible(true);
                rs.close();
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
    }
     
 //刷新信息
        class RListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            
                        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
//重新查找车辆信息（刷新）
                String sql = "select cpl,model,price,num from car";//重新从数据库查找
                ResultSet rs = stm.executeQuery(sql);
                int k = 0;
                while (rs.next()) {
                    jTable2.setValueAt(rs.getString("cpl"), k, 0);
                    jTable2.setValueAt(rs.getString("model"), k, 1);
                    jTable2.setValueAt(rs.getString("price"), k, 2);
                    jTable2.setValueAt(rs.getString("num"), k, 3);
                    k++;
                }
                setVisible(false);
                setVisible(true);
                rs.close();
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
                        
        }
    }
  
//返回
    class BAListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Login af = new Login();
            af.setVisible(true);
            setVisible(false);
        }
    }
/******************33333333333333333333333表三333333333333333333333333333*******************************/
    
    //删除信息，根据车牌号删除信息
       class DELEListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=rental";
                Connection cn = DriverManager.getConnection(url, "sa", "123");
                Statement stm = cn.createStatement();
                String sql = "delete from lease where cpl='" + jTextField9.getText() + "'";//根据车牌号删除信息
                if (stm.executeUpdate(sql) != 0) {
                    JOptionPane.showMessageDialog(null, "删除成功");
                } else {
                    JOptionPane.showMessageDialog(null, "该信息不存在，删除失败");
                }
                stm.close();
                cn.close();
            } catch (ClassNotFoundException | SQLException e1) {
            }
            MenuFrame mf = new MenuFrame();
            mf.setVisible(true);
            setVisible(false);
        }
    }
    
           //刷新表3信息
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
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("汽车租赁系统");

        jInternalFrame2.setForeground(new java.awt.Color(255, 102, 102));
        jInternalFrame2.setVisible(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "手机号", "姓名", "驾驶证号"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setText("手 机 号：");

        jLabel6.setText("姓    名：");

        jLabel7.setText("驾驶证号：");

        jButton5.setText("插入");
        jButton5.setMaximumSize(new java.awt.Dimension(87, 35));
        jButton5.setMinimumSize(new java.awt.Dimension(87, 35));

        jButton6.setText("删除");

        jButton7.setText("更新手机号");

        jButton8.setText("查询手机号");

        jButton9.setText("返回");

        jButton11.setText("刷新");

        jButton13.setText("查询姓名");

        jButton14.setText("查询驾驶证号");

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(5, 5, 5)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(4, 4, 4))
                            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14))))
                .addGap(59, 59, 59)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton8)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addGap(5, 5, 5)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton14)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("          客户信息管理          ", jInternalFrame2);

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
                "车牌号", "车辆品牌型号颜色", "价格（天）", "所在门店号"
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

        jButton1.setText("插入");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("删除");

        jButton3.setText("更新");

        jButton4.setText("查询车辆");

        jLabel1.setText("车牌号：");

        jLabel2.setText("车辆信息：");

        jLabel3.setText("价格：");

        jLabel4.setText("门店号：");

        jButton10.setText("返回");

        jButton12.setText("刷新");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton10)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("          汽车信息管理          ", jInternalFrame1);

        jInternalFrame3.setVisible(true);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
                "车辆品牌型号颜色", "车牌号", "租赁天数", "租赁价格"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton15.setText("刷新");

        jButton16.setText("删除");

        jLabel9.setText("车牌号：");

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
            .addGroup(jInternalFrame3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame3Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addGap(49, 49, 49)
                        .addComponent(jButton15))
                    .addGroup(jInternalFrame3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(38, 38, 38)
                .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("          租赁历史记录          ", jInternalFrame3);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(InformationManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformationManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformationManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformationManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformationManage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
