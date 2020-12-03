/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package halaman_admin;

import Koneksi.kon;
import com.sun.glass.events.KeyEvent;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class DataSiswa extends javax.swing.JInternalFrame {

    Connection conn = Koneksi.kon.conn();
    Statement s;
    Statement s2;
    ResultSet rs;
    Statement s1;
    ResultSet rs1;
    String sql1;
    DefaultTableModel model;
    public DataSiswa() {
        initComponents();
        judul();
        autokodeuser();
        tampildtata();
        tid.setEditable(false);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    public void judul(){
        Object[] judul = {"NIS","id_user","Nama","username","password","Jenis Kelamin","Status","kelas","Telpon"};
        model = new DefaultTableModel(null,judul){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        TblSiswa.setModel(model);
    }
    
    public void judul_cari(){
        Object[] judul = {"NIS","id_user","Nama","username","password","Jenis Kelamin","Status","kelas","Telpon"};
        model = new DefaultTableModel(null,judul){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        TblSiswa.setModel(model);
    }
    
    private void tampildtata(){
        try{
            s = conn.createStatement();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            rs = s.executeQuery("SELECT username,password,siswa.* FROM `user_login` INNER JOIN siswa ON user_login.id_user=siswa.id_user");
            while(rs.next()){
                Object [] data = {
                    rs.getString("NIS"),
                    rs.getString("id_user"),
                    rs.getString("nama_siswa"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("kelamin"),
                    rs.getString("status"),
                    rs.getString("kelas"),
                    rs.getString("no_hp"),
                };
                model.addRow(data);
                model.getRowCount();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Boolean kosong(){
        return (tid.getText().equals("") || tnis.getText().equals("") || Tname.getText().equals("") || Tpass.getText().equals("")  || 
                (Rjk.getSelection() == null) || Thp.getText().equals("") || tkelas.getText().equals(""));
    }
    
    
    public void Daftar(){
        String status ="Tidak Pinjam";
        String nis = tnis.getText();
        String nama = Tname.getText();
        String username = Tuser.getText();
        String password = Tpass.getText();
        String akses = "siswa";
        String Jenis = "";
         if (Rlaki1.isSelected()){
            Jenis = "Laki-Laki";
        }else if (Rprem.isSelected()){
            Jenis = "Perempuan";
        }
            
        if (kosong()) {
            JOptionPane.showMessageDialog(null, "Mohon masukan data untuk di input","PERHATIAN", JOptionPane.WARNING_MESSAGE);
        }else if(Tpass.getText().length()<8){
            JOptionPane.showMessageDialog(null, "Password minimal 8 karakter !","Kesalahan", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
           Connection conn = kon.conn();
           Statement s = conn.createStatement();
           rs = s.executeQuery("SELECT * FROM user_login WHERE username='"+username+"'or password='"+password+"'");
            if  (rs.next()){
                
            JOptionPane.showMessageDialog(null, "Data sudah Ada coba masukan lagi","PERHATIAN",JOptionPane.WARNING_MESSAGE);
                   bersih();
            }else{
                String sql = "insert into user_login values('"+tid.getText()+"','"+nama+"','"+username+"','"+password+"','"+akses+"')";
                String sql1 = "insert into siswa values('"+nis+"','"+tid.getText()+"','"+nama+"','"+Jenis+"','"+status+"','"+tkelas.getText()+"','"+Thp.getText()+"')";
                s = conn.prepareStatement(sql);
                s1 = conn.prepareStatement(sql1);
                s.executeUpdate(sql);
                s1.executeUpdate(sql1);
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIDAFTARKAN","SUKSES",JOptionPane.INFORMATION_MESSAGE);
                autokodeuser();
                tampildtata();
                bersih();
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());   
        }
        }
        }
    
     public void bersih(){
                    Tname.setText("");
                    tnis.setText("");
                    Thp.setText("");
                    Tuser.setText("");
                    Tpass.setText("");
                    Rjk.clearSelection();
                    tkelas.setText("");
                    Thp.setText("");
                    Tuser.setEnabled(true);
                    Tpass.setEnabled(true);
                    Tname.requestFocus();
    }
     public void autokodeuser(){
         try{
            String sql;
            sql = "SELECT COUNT(id_user) as jumlah FROM user_login";
            s = conn.createStatement();
            rs = s.executeQuery(sql);
            if (rs.next()) {
                String jumlah = rs.getString("jumlah");
                int jumint = Integer.parseInt(String.valueOf(jumlah));
                if (jumint > 0) {
                    s = conn.createStatement();
                        sql = "SELECT MAX(id_user) AS kode FROM user_login";
                        rs = s.executeQuery(sql);
                        if(rs.next()){
                            String id = rs.getString("kode").substring(3);
                            String kode = String.valueOf(Integer.parseInt(id) + 1);
                            if(kode.length() == 1){
                               tid.setText("USR00"+kode);
                            }else if(kode.length() == 2){
                                tid.setText("USR00"+kode);
                            }else
                                tid.setText("USR"+kode);
                        }
                }else{
                    tid.setText("USR001");
                }
            }
        }catch(SQLException e){
            
        }
    }
     
//     public void autokodesiswa(){
//         try{
//            String sql;
//            sql = "SELECT COUNT(id_siswa) as jumlah FROM siswa";
//            s = conn.createStatement();
//            rs = s.executeQuery(sql);
//            if (rs.next()) {
//                String jumlah = rs.getString("jumlah");
//                int jumint = Integer.parseInt(String.valueOf(jumlah));
//                if (jumint > 0) {
//                    s = conn.createStatement();
//                        sql = "SELECT MAX(id_siswa) AS kode FROM siswa";
//                        rs = s.executeQuery(sql);
//                        if(rs.next()){
//                            String id = rs.getString("kode").substring(3);
//                            String kode = String.valueOf(Integer.parseInt(id) + 1);
//                            if(kode.length() == 1){
//                               tnis.setText("SWA00"+kode);
//                            }else if(kode.length() == 2){
//                                tnis.setText("SWA00"+kode);
//                            }else
//                                tnis.setText("SWA"+kode);
//                        }
//                }else{
//                    tnis.setText("SWA001");
//                }
//            }
//        }catch(SQLException e){
//            
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Rjk = new javax.swing.ButtonGroup();
        BodyPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tkelas = new javax.swing.JTextField();
        tid = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Tuser = new javax.swing.JTextField();
        Tname = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        Tpass = new javax.swing.JPasswordField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Rprem = new javax.swing.JRadioButton();
        Rlaki1 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        tnis = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        Thp = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblSiswa = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        edit = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        reset = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1150, 710));
        setPreferredSize(new java.awt.Dimension(1320, 710));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BodyPane.setBackground(new java.awt.Color(255, 255, 255));
        BodyPane.setPreferredSize(new java.awt.Dimension(1320, 710));
        BodyPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("MV Boli", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Data Siswa");
        BodyPane.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel9.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("Username");
        BodyPane.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, -1, -1));

        jLabel17.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 153));
        jLabel17.setText("No Hp");
        BodyPane.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        tkelas.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        tkelas.setForeground(new java.awt.Color(0, 51, 153));
        tkelas.setBorder(null);
        tkelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tkelasKeyPressed(evt);
            }
        });
        BodyPane.add(tkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 280, -1));

        tid.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        tid.setForeground(new java.awt.Color(0, 51, 153));
        tid.setBorder(null);
        tid.setOpaque(false);
        BodyPane.add(tid, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 280, -1));

        jLabel11.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("Name");
        BodyPane.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel18.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 153));
        jLabel18.setText("Id User");
        BodyPane.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        Tuser.setFont(new java.awt.Font("MV Boli", 1, 14)); // NOI18N
        Tuser.setForeground(new java.awt.Color(0, 51, 153));
        Tuser.setToolTipText("Masukan Username");
        Tuser.setBorder(null);
        Tuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TuserActionPerformed(evt);
            }
        });
        Tuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TuserKeyPressed(evt);
            }
        });
        BodyPane.add(Tuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 280, -1));

        Tname.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Tname.setForeground(new java.awt.Color(0, 51, 153));
        Tname.setBorder(null);
        Tname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnameKeyPressed(evt);
            }
        });
        BodyPane.add(Tname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 280, -1));

        jLabel16.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 153));
        jLabel16.setText("Password");
        BodyPane.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));
        BodyPane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 280, 10));
        BodyPane.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 280, 10));
        BodyPane.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 280, 10));
        BodyPane.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 280, 10));

        Tpass.setBorder(null);
        Tpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpassKeyPressed(evt);
            }
        });
        BodyPane.add(Tpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 280, 30));
        BodyPane.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 280, 10));

        jLabel13.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 153));
        jLabel13.setText("*Minimal 8 karakter");
        BodyPane.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, -1, -1));

        jLabel8.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("Jenis Kelamin");
        BodyPane.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        Rprem.setBackground(new java.awt.Color(255, 255, 255));
        Rjk.add(Rprem);
        Rprem.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Rprem.setForeground(new java.awt.Color(0, 51, 153));
        Rprem.setText("Perempuan");
        Rprem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RpremKeyPressed(evt);
            }
        });
        BodyPane.add(Rprem, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        Rlaki1.setBackground(new java.awt.Color(255, 255, 255));
        Rjk.add(Rlaki1);
        Rlaki1.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Rlaki1.setForeground(new java.awt.Color(0, 51, 153));
        Rlaki1.setText("Laki-Laki");
        Rlaki1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Rlaki1KeyPressed(evt);
            }
        });
        BodyPane.add(Rlaki1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel19.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 153));
        jLabel19.setText("NIS");
        BodyPane.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        tnis.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        tnis.setForeground(new java.awt.Color(0, 51, 153));
        tnis.setBorder(null);
        tnis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnisActionPerformed(evt);
            }
        });
        BodyPane.add(tnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 280, -1));
        BodyPane.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 280, 10));

        jLabel20.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 153));
        jLabel20.setText("Kelas");
        BodyPane.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        Thp.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Thp.setForeground(new java.awt.Color(0, 51, 153));
        Thp.setBorder(null);
        Thp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThpKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ThpKeyTyped(evt);
            }
        });
        BodyPane.add(Thp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 280, -1));
        BodyPane.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 280, 10));

        delete.setBackground(new java.awt.Color(0, 51, 153));
        delete.setFont(new java.awt.Font("MV Boli", 0, 16)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Hapus");
        delete.setBorder(null);
        delete.setContentAreaFilled(false);
        delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        delete.setOpaque(true);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        BodyPane.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, 140, 40));

        TblSiswa.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        TblSiswa.setForeground(new java.awt.Color(0, 51, 153));
        TblSiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblSiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblSiswa);

        BodyPane.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 700, 430));

        tcari.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tcari.setForeground(new java.awt.Color(0, 51, 153));
        tcari.setBorder(null);
        tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcariActionPerformed(evt);
            }
        });
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcariKeyPressed(evt);
            }
        });
        BodyPane.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 320, 30));

        jSeparator8.setForeground(new java.awt.Color(0, 51, 153));
        BodyPane.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 80, 320, 10));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon pack/magnifying-glass (1).png"))); // NOI18N
        BodyPane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 50, -1, -1));

        edit.setBackground(new java.awt.Color(0, 51, 153));
        edit.setFont(new java.awt.Font("MV Boli", 0, 16)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("edit");
        edit.setBorder(null);
        edit.setContentAreaFilled(false);
        edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        edit.setOpaque(true);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        BodyPane.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 130, 40));

        simpan.setBackground(new java.awt.Color(0, 51, 153));
        simpan.setFont(new java.awt.Font("MV Boli", 0, 16)); // NOI18N
        simpan.setForeground(new java.awt.Color(255, 255, 255));
        simpan.setText("Simpan");
        simpan.setBorder(null);
        simpan.setContentAreaFilled(false);
        simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpan.setOpaque(true);
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        BodyPane.add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 140, 40));

        reset.setBackground(new java.awt.Color(0, 51, 153));
        reset.setFont(new java.awt.Font("MV Boli", 0, 16)); // NOI18N
        reset.setForeground(new java.awt.Color(255, 255, 255));
        reset.setText("Reset");
        reset.setBorder(null);
        reset.setContentAreaFilled(false);
        reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reset.setOpaque(true);
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        BodyPane.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 130, 40));

        getContentPane().add(BodyPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
     
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formInternalFrameActivated

    private void tkelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkelasKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_tkelasKeyPressed

    private void TuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TuserActionPerformed

    private void TuserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TuserKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_TuserKeyPressed

    private void TnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_TnameKeyPressed

    private void TpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpassKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_TpassKeyPressed

    private void RpremKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RpremKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_RpremKeyPressed

    private void Rlaki1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Rlaki1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Rlaki1KeyPressed

    private void tnisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnisActionPerformed

    private void ThpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThpKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_ThpKeyPressed

    private void ThpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThpKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar()) || Thp.getText().length()>=13){
            evt.consume();
        }
    }//GEN-LAST:event_ThpKeyTyped

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        if(!tnis.getText().equals("")){
            try {
            int baris = TblSiswa.getSelectedRow();
            String stat = "Pinjam";
            int jawab;
            if(stat.equals(TblSiswa.getValueAt(baris, 6))){
                JOptionPane.showMessageDialog(null, "Tidak dapat menghapus selama dalam masa peminjaman");
                Tuser.setEditable(true);
                Tpass.setEditable(true);
                bersih();
                autokodeuser();
            }else if((jawab = JOptionPane.showConfirmDialog(null,"Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION)) == 0){
                String sql = "DELETE FROM siswa WHERE NIS='"+ tnis.getText() +"'";
                String sql1 = "DELETE FROM user_login WHERE id_user='"+ tid.getText() +"'";
                s = conn.createStatement();
                s1 = conn.createStatement();
                s.executeUpdate(sql);
                s1.executeUpdate(sql1);
                JOptionPane.showMessageDialog(null,"Berhasil di hapus");
                tnis.setEditable(true);
                Tuser.setEditable(true);
                Tpass.setEditable(true);
                bersih();
                tampildtata();
            }
            
            } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
            bersih();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Pilih ID untuk data yang ingin di hapus!");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        String JK = null;
        if (Rlaki1.isSelected()) {
            JK = "Laki-Laki";
        }else if(Rprem.isSelected()){
            JK = "Perempuan";
        }
        if(kosong()){
            JOptionPane.showMessageDialog(null, "Harap Lengkapi Data !","Kesalahan", JOptionPane.ERROR_MESSAGE);
        }else if(Tpass.getText().length()<8){
            JOptionPane.showMessageDialog(null, "Password minimal 8 karakter !","Kesalahan", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
            s2 = conn.createStatement();
            String sql2 = "UPDATE siswa SET nama_siswa='"+ Tname.getText() +"',kelamin='"+JK+"',kelas='"+tkelas.getText()+"',no_hp='"+Thp.getText()+"' WHERE NIS='"+ tnis.getText()+"'";
            s2.executeUpdate(sql2);
            JOptionPane.showMessageDialog(null,"Berhasil di ubah");
            tnis.setEditable(true);
            Tuser.setEditable(true);
            Tpass.setEditable(true);
            autokodeuser();
            bersih();
            tampildtata();
            } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
            bersih();
            }
        }
        
    }//GEN-LAST:event_editActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        Daftar();
    }//GEN-LAST:event_simpanActionPerformed

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed
        // TODO add your handling code here:
        if(tcari.getText().length()>0){
            try {
            s = conn.createStatement();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            String cr = tcari.getText();
            rs = s.executeQuery("SELECT * FROM user_login INNER JOIN siswa ON user_login.id_user = siswa.id_user WHERE NIS like '%"+ cr +"%'or nama_siswa like '%"+
                    cr +"%'or kelamin like '%"+ cr +"%' or no_hp like '%"+ cr +"%' or kelas like '%"+ cr +"%' or status like '%"+ cr +"%' ORDER BY NIS DESC" );
             while (rs.next()){
                    judul_cari();
                    Object[] data ={
                    rs.getString("NIS"),
                    rs.getString("nama_siswa"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("kelamin"),
                    rs.getString("status"),
                    rs.getString("kelas"),
                    rs.getString("no_hp"),
                    };
                    model.addRow(data);
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else{
            judul();
            tampildtata();   
        }
        
    }//GEN-LAST:event_tcariKeyPressed

    private void TblSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblSiswaMouseClicked
        // TODO add your handling code here:
        int baris = TblSiswa.getSelectedRow();
        tid.setEditable(false);
        tnis.setEditable(false);
        delete.setEnabled(true);
        edit.setEnabled(true);
        tnis.setText(TblSiswa.getModel().getValueAt(baris, 0).toString());
        tid.setText(TblSiswa.getModel().getValueAt(baris, 1).toString());
        Tname.setText(TblSiswa.getModel().getValueAt(baris, 2).toString());
        Tuser.setText(TblSiswa.getModel().getValueAt(baris, 3).toString());
        Tpass.setText(TblSiswa.getModel().getValueAt(baris, 4).toString());
        Thp.setText(TblSiswa.getModel().getValueAt(baris, 8).toString());
        tkelas.setText(TblSiswa.getModel().getValueAt(baris, 7).toString());
        switch(model.getValueAt(TblSiswa.getSelectedRow(), 5).toString()){
            case "Laki-Laki" :
                Rlaki1.setSelected(true);
                break;
            case "Perempuan" :
                Rprem.setSelected(true);
                break;
        }
    }//GEN-LAST:event_TblSiswaMouseClicked

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here
        bersih();
        tnis.setEditable(true);
        Tuser.setEditable(true);
        Tpass.setEditable(true);
    }//GEN-LAST:event_resetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BodyPane;
    private javax.swing.ButtonGroup Rjk;
    private javax.swing.JRadioButton Rlaki1;
    private javax.swing.JRadioButton Rprem;
    private javax.swing.JTable TblSiswa;
    private javax.swing.JTextField Thp;
    private javax.swing.JTextField Tname;
    private javax.swing.JPasswordField Tpass;
    private javax.swing.JTextField Tuser;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JButton reset;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tkelas;
    private javax.swing.JTextField tnis;
    // End of variables declaration//GEN-END:variables
}
