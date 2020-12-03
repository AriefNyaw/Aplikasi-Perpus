/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package halaman_admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class DataPeminjaman extends javax.swing.JInternalFrame {

    Statement st;
    Statement st2;
    ResultSet rs;
    ResultSet rs2;
    DefaultTableModel model;
    DefaultTableModel model1;
    String sql;
    Connection con = Koneksi.kon.conn();
    public DataPeminjaman() {
        initComponents();
        judul_buku();
        judul_anggota();
        tampildata_siswa();
        tampildata_buku();
        waktu();
        Tnopin.setEditable(false);
        tids.setEditable(false);
        tnamas.setEditable(false);
        tidb.setEditable(false);
        tjudul.setEditable(false);
        tgl_pinjam.setEditable(false);
        tgl_hrskembali.setEditable(false);
        kode_pinjam();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    public void reset(){
        tids.setText(null);
        tnamas.setText(null);
        tidb.setText(null);
        tjudul.setText(null);
    }
    
    public Boolean tidak_kosong(){
        return !(Tnopin.getText().equals("") || tids.getText().equals("") || tnamas.getText().equals("") || tidb.getText().equals("") || tjudul.getText().equals("") || tgl_pinjam.getText().equals("") || tgl_hrskembali.getText().equals(""));
    }
    
     public void waktu(){
        //Date now = new Date();
        //SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        //String tgl = format.format(now);
        //tgl_kembali.setText(tgl);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        tgl_pinjam.setText(df.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, 7);
        tgl_hrskembali.setText(df.format(cal.getTime()));
    }
    
    public void kode_pinjam(){
        int kd = (int)(Math.random()*999999);
        Tnopin.setText(String.valueOf(kd));
    }
    
    public Boolean kosong(){
        return (Tnopin.getText().equals("") || tids.getText().equals("") || tnamas.getText().equals("") || tidb.getText().equals("") || tjudul.getText().equals("") || tgl_pinjam.getText().equals("") || tgl_hrskembali.getText().equals(""));
    }
    
    public void judul_buku(){
        Object[] judul = {"Kode","Judul","Penerbit","Tahun Terbit","Kategori","Jumlah"};
        model1 = new DefaultTableModel(null,judul){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblbuku.setModel(model1);
    }
    
    public void judul_anggota(){
        Object[] judul = {"NIS","Nama","Jenis Kelamin","status","Kelas","telpon"};
        model = new DefaultTableModel(null,judul){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblsiswa.setModel(model);
    }
    
    public void tampildata_siswa(){
        try{
            st = con.createStatement();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM siswa ORDER BY status DESC");
            while(rs.next()){
                Object [] data = {
                    rs.getString("NIS"),
                    rs.getString("nama_siswa"),
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
    
    public void tampildata_buku(){
        try{
            st = con.createStatement();
            model1.getDataVector().removeAllElements();
            model1.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM buku ORDER BY jumlah DESC");
            while(rs.next()){
                Object [] data = {
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("penerbit"),
                    rs.getString("tahun_terbit"),
                    rs.getString("kategori"),
                    rs.getString("jumlah"),
                };
                model1.addRow(data);
                model1.getRowCount();
                
            }
        }catch(Exception e){
            e.printStackTrace();
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

        Bodypane = new javax.swing.JPanel();
        tgl_pinjam = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        tnamas = new javax.swing.JTextField();
        reset = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        pinjam = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tidb = new javax.swing.JTextField();
        tgl_hrskembali = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        Tnopin = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        tjudul = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        tids = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsiswa = new javax.swing.JTable();
        tcaris = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        tcarib = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblbuku = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1320, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bodypane.setBackground(new java.awt.Color(255, 255, 255));
        Bodypane.setPreferredSize(new java.awt.Dimension(1320, 710));
        Bodypane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tgl_pinjam.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tgl_pinjam.setForeground(new java.awt.Color(0, 51, 153));
        tgl_pinjam.setBorder(null);
        Bodypane.add(tgl_pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 399, 370, -1));

        jLabel15.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 153));
        jLabel15.setText("Nama Siswa");
        Bodypane.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 182, -1, -1));

        jSeparator11.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 422, 390, 10));

        tnamas.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tnamas.setForeground(new java.awt.Color(0, 51, 153));
        tnamas.setBorder(null);
        tnamas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamasActionPerformed(evt);
            }
        });
        Bodypane.add(tnamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 207, 370, -1));

        reset.setBackground(new java.awt.Color(0, 51, 153));
        reset.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
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
        Bodypane.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 504, 190, 44));

        jSeparator8.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 230, 390, 10));

        pinjam.setBackground(new java.awt.Color(0, 51, 153));
        pinjam.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        pinjam.setForeground(new java.awt.Color(255, 255, 255));
        pinjam.setText("Pinjam");
        pinjam.setBorder(null);
        pinjam.setContentAreaFilled(false);
        pinjam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pinjam.setOpaque(true);
        pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinjamActionPerformed(evt);
            }
        });
        Bodypane.add(pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 504, 180, 44));

        jLabel16.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 153));
        jLabel16.setText("ID Buku");
        Bodypane.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 246, -1, -1));

        jLabel20.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 153));
        jLabel20.setText("Tanggal Harus Kembali");
        Bodypane.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 438, -1, -1));

        tidb.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tidb.setForeground(new java.awt.Color(0, 51, 153));
        tidb.setBorder(null);
        Bodypane.add(tidb, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 271, 370, -1));

        tgl_hrskembali.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tgl_hrskembali.setForeground(new java.awt.Color(0, 51, 153));
        tgl_hrskembali.setBorder(null);
        Bodypane.add(tgl_hrskembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 463, 370, -1));

        jLabel13.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 153));
        jLabel13.setText("No Pinjam");
        Bodypane.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 53, -1, -1));

        jSeparator9.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 294, 390, 10));

        jSeparator12.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 486, 390, 10));

        Tnopin.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        Tnopin.setForeground(new java.awt.Color(0, 51, 153));
        Tnopin.setBorder(null);
        Bodypane.add(Tnopin, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 79, 370, -1));

        jLabel17.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 153));
        jLabel17.setText("Judul Buku");
        Bodypane.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 310, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 102, 390, 10));

        tjudul.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tjudul.setForeground(new java.awt.Color(0, 51, 153));
        tjudul.setBorder(null);
        Bodypane.add(tjudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 335, 370, -1));

        jLabel14.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 153));
        jLabel14.setText("NIS");
        Bodypane.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 118, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 358, 390, 10));

        tids.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tids.setForeground(new java.awt.Color(0, 51, 153));
        tids.setBorder(null);
        Bodypane.add(tids, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 143, 370, -1));

        jLabel18.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 153));
        jLabel18.setText("Tanggal Pinjam");
        Bodypane.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 374, -1, -1));

        jSeparator7.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 166, 390, 10));

        tblsiswa.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tblsiswa.setForeground(new java.awt.Color(0, 51, 153));
        tblsiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tblsiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsiswa);

        Bodypane.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 53, 890, 280));

        tcaris.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tcaris.setForeground(new java.awt.Color(0, 51, 153));
        tcaris.setBorder(null);
        tcaris.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcarisKeyPressed(evt);
            }
        });
        Bodypane.add(tcaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 320, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 320, 10));

        tcarib.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tcarib.setForeground(new java.awt.Color(0, 51, 153));
        tcarib.setBorder(null);
        tcarib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcaribActionPerformed(evt);
            }
        });
        tcarib.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcaribKeyPressed(evt);
            }
        });
        Bodypane.add(tcarib, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 350, 320, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 380, 320, 10));

        tblbuku.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tblbuku.setForeground(new java.awt.Color(0, 51, 153));
        tblbuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tblbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbukuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblbuku);

        Bodypane.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 389, 890, 280));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon pack/magnifying-glass (1).png"))); // NOI18N
        Bodypane.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon pack/magnifying-glass (1).png"))); // NOI18N
        Bodypane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 350, -1, -1));

        jLabel3.setFont(new java.awt.Font("MV Boli", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Data Peminjaman Buku");
        Bodypane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("MV Boli", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Cari Data Buku");
        Bodypane.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, -1, -1));

        jLabel5.setFont(new java.awt.Font("MV Boli", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Cari Data SIswa");
        Bodypane.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        getContentPane().add(Bodypane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tnamasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamasActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
       reset();
    }//GEN-LAST:event_resetActionPerformed

    private void pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinjamActionPerformed
        // TODO add your handling code here:
                if(kosong()){
                        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data !","Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }else{
                        try {
                                String sql = "INSERT INTO peminjaman VALUES('"+ Tnopin.getText() +"','"+ tids.getText() +"','"+ tnamas.getText() +"','"+ tidb.getText() +"', '"+ tjudul.getText() +"','"+ tgl_pinjam.getText() +"','"+ tgl_hrskembali.getText() +"','1','Belum kembali')";
                                String sql2 = "UPDATE siswa set status='Pinjam' WHERE NIS='"+ tids.getText() +"'";
                                st = con.prepareStatement(sql);
                                st2 = con.prepareStatement(sql2);
                                st.executeUpdate(sql);
                                st2.executeUpdate(sql2);
                                JOptionPane.showMessageDialog(null,"Berhasil Meminjam Buku");
                                reset();
                                tampildata_siswa();
                                tampildata_buku();
                                waktu();
                                kode_pinjam();
                            } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
                                reset();
                            }
                    }
    }//GEN-LAST:event_pinjamActionPerformed

    private void tblsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsiswaMouseClicked
        // TODO add your handling code here:
        int baris = tblsiswa.getSelectedRow();
        String stat = "Pinjam";
        
        if(stat.equals(tblsiswa.getValueAt(baris, 3))){
            JOptionPane.showMessageDialog(null, "Sedang dalam masa peminjaman  !","Kesalahan", JOptionPane.ERROR_MESSAGE);  
        }else{
            tids.setText(tblsiswa.getModel().getValueAt(baris, 0).toString());
            tnamas.setText(tblsiswa.getModel().getValueAt(baris, 1).toString());
        }
    }//GEN-LAST:event_tblsiswaMouseClicked

    private void tblbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbukuMouseClicked
        // TODO add your handling code here:
        int baris = tblbuku.getSelectedRow();
        String stok = "0";
        if(stok.equals(tblbuku.getValueAt(baris, 5))){
            JOptionPane.showMessageDialog(null, "Stok buku habis !","Kesalahan", JOptionPane.ERROR_MESSAGE);  
        }else{
            tidb.setText(tblbuku.getModel().getValueAt(baris, 0).toString());
            tjudul.setText(tblbuku.getModel().getValueAt(baris, 1).toString());
        }
    }//GEN-LAST:event_tblbukuMouseClicked

    private void tcaribActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcaribActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcaribActionPerformed

    private void tcarisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcarisKeyPressed
        // TODO add your handling code here:
           try {
            st = con.createStatement();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            String cr = tcaris.getText();
            rs = st.executeQuery("SELECT * FROM siswa WHERE NIS like '%"+ cr +"%'or nama_siswa like '%"+ cr +"%'or kelamin like '%"+ cr +"%' or status like '%"+ cr +"%' or kelas like '%"+ cr +"%' or no_hp like '%"+ cr +"%' ORDER BY status DESC " );
             while (rs.next()){
                    Object[] data ={
                     rs.getString("NIS"),
                    rs.getString("nama_siswa"),
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
    }//GEN-LAST:event_tcarisKeyPressed

    private void tcaribKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcaribKeyPressed
        // TODO add your handling code here:
        try {
            st = con.createStatement();
            model1.getDataVector().removeAllElements();
            model1.fireTableDataChanged();
            String cr = tcarib.getText();
            rs = st.executeQuery("SELECT * FROM buku WHERE id_buku like '%"+ cr +"%'or judul like '%"+ cr +"%'or penerbit like '%"+ cr +"%' or tahun_terbit like '%"+ cr +"%' or kategori like '%"+ cr +"%' or jumlah like '%"+ cr +"%' ORDER BY jumlah DESC " );
             while (rs.next()){
                    Object[] data ={
                     rs.getString("id_buku"),
                     rs.getString("judul"),
                     rs.getString("penerbit"),
                     rs.getString("tahun_terbit"),
                     rs.getString("kategori"),
                     rs.getString("jumlah"),
                    };
                    model1.addRow(data);
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tcaribKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bodypane;
    private javax.swing.JTextField Tnopin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton pinjam;
    private javax.swing.JButton reset;
    private javax.swing.JTable tblbuku;
    private javax.swing.JTable tblsiswa;
    private javax.swing.JTextField tcarib;
    private javax.swing.JTextField tcaris;
    private javax.swing.JTextField tgl_hrskembali;
    private javax.swing.JTextField tgl_pinjam;
    private javax.swing.JTextField tidb;
    private javax.swing.JTextField tids;
    private javax.swing.JTextField tjudul;
    private javax.swing.JTextField tnamas;
    // End of variables declaration//GEN-END:variables
}
