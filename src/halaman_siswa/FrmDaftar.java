/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package halaman_siswa;

import Koneksi.kon;
import aplikasi_perpustakaan.FrmLogin;
import com.sun.glass.events.KeyEvent;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class FrmDaftar extends javax.swing.JFrame {
    String sql;
    ResultSet rs;
    ResultSet rs2;
    Statement s;
    Statement s1;
    Connection conn = Koneksi.kon.conn();
    
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
         if (Rlaki.isSelected()){
            Jenis = Rlaki.getActionCommand();
        }else if (Rprem.isSelected()){
            Jenis = Rprem.getActionCommand();
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
                FrmLogin n = new FrmLogin();
                this.setVisible(false);
                n.setVisible(true);
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());   
        }
        }
        }
    
    public void bersih(){
                    Tname.setText("");
                    Thp.setText("");
                    Tpass.setText("");
                    Rjk.clearSelection();
                    tkelas.setText("");
                    Thp.setText("");
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
                        rs2 = s.executeQuery(sql);
                        if(rs2.next()){
                            String id = rs2.getString("kode").substring(3);
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
         //generate kode siswa
//         public void autokodesiswa(){
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
//                        rs2 = s.executeQuery(sql);
//                        if(rs2.next()){
//                            String id = rs2.getString("kode").substring(3);
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
    
       public FrmDaftar() {
        initComponents();
        autokodeuser();
//        autokodesiswa();
        tid.setEditable(false);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Rjk = new javax.swing.ButtonGroup();
        bodypanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Thp = new javax.swing.JTextField();
        Bdaftar = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        minimsize = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Rlaki = new javax.swing.JRadioButton();
        Rprem = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Tname = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        tkelas = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tnis = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        tid = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Tuser = new javax.swing.JTextField();
        Tpass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bodypanel.setBackground(new java.awt.Color(255, 255, 255));
        bodypanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        bodypanel.setDoubleBuffered(false);
        bodypanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bodypanelMouseDragged(evt);
            }
        });
        bodypanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bodypanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bodypanelMousePressed(evt);
            }
        });
        bodypanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("No Hp");
        bodypanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 102, -1));

        Thp.setFont(new java.awt.Font("MV Boli", 1, 14)); // NOI18N
        Thp.setForeground(new java.awt.Color(0, 51, 153));
        Thp.setToolTipText("Masukan Username");
        Thp.setBorder(null);
        Thp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThpKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ThpKeyTyped(evt);
            }
        });
        bodypanel.add(Thp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 336, -1));

        Bdaftar.setBackground(new java.awt.Color(0, 51, 153));
        Bdaftar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BdaftarMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Sign Up");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BdaftarLayout = new javax.swing.GroupLayout(Bdaftar);
        Bdaftar.setLayout(BdaftarLayout);
        BdaftarLayout.setHorizontalGroup(
            BdaftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );
        BdaftarLayout.setVerticalGroup(
            BdaftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BdaftarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bodypanel.add(Bdaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 330, -1));

        jLabel9.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("Username");
        bodypanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 274, 102, -1));

        jLabel10.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Sign Up");
        bodypanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 1, -1, -1));

        exit.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 18)); // NOI18N
        exit.setForeground(new java.awt.Color(0, 51, 153));
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("X");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        bodypanel.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 1, 21, -1));

        minimsize.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 18)); // NOI18N
        minimsize.setForeground(new java.awt.Color(0, 51, 153));
        minimsize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimsize.setText("-");
        minimsize.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        minimsize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimsizeMouseClicked(evt);
            }
        });
        bodypanel.add(minimsize, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1, 21, -1));
        bodypanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 48, -1, -1));

        jLabel11.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("Name");
        bodypanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 201, 100, -1));

        jLabel8.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("Jenis Kelamin");
        bodypanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        Rlaki.setBackground(new java.awt.Color(255, 255, 255));
        Rjk.add(Rlaki);
        Rlaki.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Rlaki.setForeground(new java.awt.Color(0, 51, 153));
        Rlaki.setText("Laki-Laki");
        Rlaki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RlakiKeyPressed(evt);
            }
        });
        bodypanel.add(Rlaki, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        Rprem.setBackground(new java.awt.Color(255, 255, 255));
        Rjk.add(Rprem);
        Rprem.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Rprem.setForeground(new java.awt.Color(0, 51, 153));
        Rprem.setText("Prempuan");
        Rprem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RpremKeyPressed(evt);
            }
        });
        bodypanel.add(Rprem, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, -1, -1));

        jLabel13.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 153));
        jLabel13.setText("*Minimal 8 karakter");
        bodypanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 426, -1, 11));

        jLabel14.setFont(new java.awt.Font("MV Boli", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 153));
        jLabel14.setText("*Maksimal 13 karakter");
        bodypanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));
        bodypanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 263, 324, 4));

        Tname.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Tname.setForeground(new java.awt.Color(0, 51, 153));
        Tname.setBorder(null);
        Tname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnameKeyPressed(evt);
            }
        });
        bodypanel.add(Tname, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 233, 324, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 51, 153));
        bodypanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 338, 324, 4));

        jSeparator4.setForeground(new java.awt.Color(0, 51, 153));
        bodypanel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 320, -1));

        jSeparator5.setForeground(new java.awt.Color(0, 51, 153));
        bodypanel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 417, 324, -1));

        jLabel16.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 153));
        jLabel16.setText("Password");
        bodypanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 349, 102, -1));

        tkelas.setFont(new java.awt.Font("MV Boli", 1, 14)); // NOI18N
        tkelas.setForeground(new java.awt.Color(0, 51, 153));
        tkelas.setToolTipText("Masukan Username");
        tkelas.setBorder(null);
        tkelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tkelasKeyPressed(evt);
            }
        });
        bodypanel.add(tkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 336, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 51, 153));
        bodypanel.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 320, 10));

        jLabel12.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 153));
        jLabel12.setText("Kelas");
        bodypanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 102, -1));

        jLabel17.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 153));
        jLabel17.setText("NIS");
        bodypanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 128, 100, -1));

        tnis.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        tnis.setForeground(new java.awt.Color(0, 51, 153));
        tnis.setBorder(null);
        bodypanel.add(tnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 160, 324, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 51, 153));
        bodypanel.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 190, 324, 4));

        jSeparator7.setForeground(new java.awt.Color(0, 51, 153));
        bodypanel.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 117, 324, 4));

        tid.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        tid.setForeground(new java.awt.Color(0, 51, 153));
        tid.setBorder(null);
        bodypanel.add(tid, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 87, 324, -1));

        jLabel18.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 153));
        jLabel18.setText("Id User");
        bodypanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 55, 100, -1));

        Tuser.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Tuser.setForeground(new java.awt.Color(0, 51, 153));
        Tuser.setToolTipText("Masukan Username");
        Tuser.setBorder(null);
        Tuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TuserKeyPressed(evt);
            }
        });
        bodypanel.add(Tuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 308, 324, -1));

        Tpass.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        Tpass.setForeground(new java.awt.Color(0, 51, 153));
        Tpass.setBorder(null);
        Tpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpassKeyPressed(evt);
            }
        });
        bodypanel.add(Tpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 386, 324, -1));

        getContentPane().add(bodypanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 760));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jLabel15jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15jLabel1MouseClicked
        // TODO add your handling code here:
        Daftar();
    }//GEN-LAST:event_jLabel15jLabel1MouseClicked

    private void BdaftarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BdaftarMouseClicked
        // TODO add your handling code here:
        Daftar();
    }//GEN-LAST:event_BdaftarMouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        FrmLogin n = new FrmLogin();
        this.setVisible(false);
        n.setVisible(true);
    }//GEN-LAST:event_exitMouseClicked

    private void minimsizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimsizeMouseClicked
        // TODO add your handling code here:
        this.setState(1);
    }//GEN-LAST:event_minimsizeMouseClicked

    int x,y;
    
    private void RlakiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RlakiKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_RlakiKeyPressed

    private void RpremKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RpremKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_RpremKeyPressed

    private void TnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_TnameKeyPressed

    private void tkelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkelasKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tkelasKeyPressed

    private void TuserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TuserKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_TuserKeyPressed

    private void TpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpassKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Daftar();
        }
    }//GEN-LAST:event_TpassKeyPressed

    private void bodypanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bodypanelMouseDragged
        // TODO add your handling code here:
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_bodypanelMouseDragged

    private void bodypanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bodypanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bodypanelMouseClicked

    private void bodypanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bodypanelMousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_bodypanelMousePressed

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
            java.util.logging.Logger.getLogger(FrmDaftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDaftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDaftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDaftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDaftar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bdaftar;
    private javax.swing.ButtonGroup Rjk;
    private javax.swing.JRadioButton Rlaki;
    private javax.swing.JRadioButton Rprem;
    private javax.swing.JTextField Thp;
    private javax.swing.JTextField Tname;
    private javax.swing.JPasswordField Tpass;
    private javax.swing.JTextField Tuser;
    private javax.swing.JPanel bodypanel;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel minimsize;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tkelas;
    private javax.swing.JTextField tnis;
    // End of variables declaration//GEN-END:variables
}
