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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class DataPengembalian extends javax.swing.JInternalFrame {

    public Statement st;
    public Statement st2;
    public Statement st3;
    public ResultSet rs;
    public ResultSet rs2;
    public ResultSet rs3;
    public DefaultTableModel tabmodel;
    String sql;
    Connection con = Koneksi.kon.conn();
    public DataPengembalian() {
        initComponents();
        
        initComponents();
        judul_pinjam();
        tampildata_pinjam();
        waktu();
        ket.setVisible(false);
        kd_kembali.setEditable(false);
        kd_pinjam.setEditable(false);
        tnis.setEditable(false);
        kd_buku.setEditable(false);
        tgl_pinjam.setEditable(false);
        tgl_kembali.setEditable(false);
        denda_telat.setEditable(false);
        kode_kembali();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
     public void reset(){
        kd_pinjam.setText(null);
        tnis.setText(null);
        kd_buku.setText(null);
        tgl_pinjam.setText(null);
        denda_telat.setText(null);
    }
    
    public Boolean tidak_kosong(){
        return !(kd_kembali.getText().equals("") || kd_pinjam.getText().equals("") || tnis.getText().equals("") || kd_buku.getText().equals("") || tgl_pinjam.getText().equals("") || tgl_kembali.getText().equals("") || denda_telat.getText().equals(""));
    }
    
    
    public void waktu(){
        //Date now = new Date();
        //SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        //String tgl = format.format(now);
        //tgl_kembali.setText(tgl);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        tgl_kembali.setText(df.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, 7);
        String hrs_kembali;
        hrs_kembali=(df.format(cal.getTime()));
    }
    
    public void kode_kembali(){
        int kd = (int)(Math.random()*999999);
        kd_kembali.setText(String.valueOf(kd));
    }
    
    public void hitung_denda(){
//        int denda  = 500;
//        String tgl1 = tgl_hrskembali.getText().toString().substring(8);
//        String tgl2 = tgl_kembali.getText().toString().substring(8);
//        int bayar = (Integer.valueOf(tgl1)-Integer.valueOf(tgl2));
//        if(bayar >0){
//            denda_telat.setText(String.valueOf(bayar*denda));
//        }else{
//            denda_telat.setText(String.valueOf(bayar*0));
//        }


        try {
            int bayar_denda = 500;
            String total;
            DateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal1 = tgl_pinjam.getText();
            Date tglp = (Date)tgl.parse(tanggal1);
            String tanggal2 = tgl_kembali.getText();
            Date tglk = (Date)tgl.parse(tanggal2);
            long dnd = Math.abs(tglp.getTime()-tglk.getTime());
            long telat = (TimeUnit.MILLISECONDS.toDays(dnd));
//            denda_telat.setText(String.valueOf(telat));
            if(telat >7){
                ket.setText("Telat");
                denda_telat.setText(String.valueOf((telat-7)*bayar_denda));
            }else{
                ket.setText("Tidak telat");
                denda_telat.setText(String.valueOf(telat*0));
            }
//            if(telat <0){
//                denda_telat.setText(String.valueOf(telat*0));
//            }else{
//                denda_telat.setText(String.valueOf(telat*bayar_denda));
//            }
            
            
        } catch (ParseException ex) {
            Logger.getLogger(DataPengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Boolean kosong(){
        return (kd_kembali.getText().equals("") || kd_pinjam.getText().equals("") || tnis.getText().equals("") || kd_buku.getText().equals("") || tgl_pinjam.getText().equals("") || tgl_kembali.getText().equals("") || denda_telat.getText().equals(""));
    }
    
    public void judul_pinjam(){
        Object[] judul = {"Kode Pinjam","NIS","Nama","Kode Buku","Judul","Tanggal Pinjam","Tanggal Harus Kembali"};
        tabmodel = new DefaultTableModel(null,judul){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblkembali.setModel(tabmodel);
    }
    
    public void tampildata_pinjam(){
        try{
            st = con.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM peminjaman WHERE keterangan != 'Sudah kembali' ORDER BY DATE(tgl_pinjam)  ASC");
            while(rs.next()){
                Object [] data = {
                    rs.getString("no_pinjam"),
                    rs.getString("NIS"),
                    rs.getString("nama"),
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("tgl_pinjam"),
                    rs.getString("tgl_hrskembali"),
                };
                tabmodel.addRow(data);
                tabmodel.getRowCount();
                
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
        jLabel13 = new javax.swing.JLabel();
        kd_kembali = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        kd_pinjam = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        tnis = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        denda_telat = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        kembali1 = new javax.swing.JButton();
        hilang = new javax.swing.JButton();
        ket = new javax.swing.JTextField();
        kd_buku = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        reset = new javax.swing.JButton();
        perpanjang = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        tgl_pinjam = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        tgl_kembali = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        tcarib = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkembali = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1320, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bodypane.setBackground(new java.awt.Color(255, 255, 255));
        Bodypane.setPreferredSize(new java.awt.Dimension(1320, 710));
        Bodypane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 153));
        jLabel13.setText("Kode Kembali");
        Bodypane.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 42, -1, -1));

        kd_kembali.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        kd_kembali.setForeground(new java.awt.Color(0, 51, 153));
        kd_kembali.setBorder(null);
        Bodypane.add(kd_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 71, 370, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 99, 390, 10));

        jLabel14.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 153));
        jLabel14.setText("Kode Pinjam");
        Bodypane.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 115, -1, -1));

        kd_pinjam.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        kd_pinjam.setForeground(new java.awt.Color(28, 42, 57));
        kd_pinjam.setBorder(null);
        Bodypane.add(kd_pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 144, 370, -1));

        jSeparator7.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 172, 390, 10));

        jLabel15.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 153));
        jLabel15.setText("NIS");
        Bodypane.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 188, -1, -1));

        tnis.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tnis.setForeground(new java.awt.Color(0, 51, 153));
        tnis.setBorder(null);
        tnis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnisActionPerformed(evt);
            }
        });
        Bodypane.add(tnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 217, 370, -1));

        jSeparator8.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 245, 390, 10));

        jLabel17.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 153));
        jLabel17.setText("Kode Buku");
        Bodypane.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 262, -1, -1));

        denda_telat.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        denda_telat.setForeground(new java.awt.Color(0, 51, 153));
        denda_telat.setBorder(null);
        Bodypane.add(denda_telat, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 511, 370, -1));

        jLabel23.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 153));
        jLabel23.setText("Denda");
        Bodypane.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 480, -1, -1));

        jSeparator14.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 540, 390, 10));

        kembali1.setBackground(new java.awt.Color(0, 51, 153));
        kembali1.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        kembali1.setForeground(new java.awt.Color(255, 255, 255));
        kembali1.setText("Kembalikan");
        kembali1.setBorder(null);
        kembali1.setContentAreaFilled(false);
        kembali1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        kembali1.setOpaque(true);
        kembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali1ActionPerformed(evt);
            }
        });
        Bodypane.add(kembali1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 557, 98, 44));

        hilang.setBackground(new java.awt.Color(0, 51, 153));
        hilang.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        hilang.setForeground(new java.awt.Color(255, 255, 255));
        hilang.setText("Buku Hilang");
        hilang.setBorder(null);
        hilang.setContentAreaFilled(false);
        hilang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hilang.setOpaque(true);
        hilang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hilangActionPerformed(evt);
            }
        });
        Bodypane.add(hilang, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 557, 95, 44));
        Bodypane.add(ket, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 13, 393, -1));

        kd_buku.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        kd_buku.setForeground(new java.awt.Color(0, 51, 153));
        kd_buku.setBorder(null);
        Bodypane.add(kd_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 291, 370, -1));

        jSeparator10.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 319, 390, 10));

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
        Bodypane.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 557, 87, 44));

        perpanjang.setBackground(new java.awt.Color(0, 51, 153));
        perpanjang.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        perpanjang.setForeground(new java.awt.Color(255, 255, 255));
        perpanjang.setText("Perpanjang");
        perpanjang.setBorder(null);
        perpanjang.setContentAreaFilled(false);
        perpanjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        perpanjang.setOpaque(true);
        perpanjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perpanjangActionPerformed(evt);
            }
        });
        Bodypane.add(perpanjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 557, 95, 44));

        jLabel20.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 153));
        jLabel20.setText("Tanggal Pinjam");
        Bodypane.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 336, -1, -1));

        tgl_pinjam.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tgl_pinjam.setForeground(new java.awt.Color(0, 51, 153));
        tgl_pinjam.setBorder(null);
        Bodypane.add(tgl_pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 365, 370, -1));

        jSeparator12.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 393, 390, 10));

        jLabel22.setFont(new java.awt.Font("MV Boli", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 153));
        jLabel22.setText("Tanggal Kembali");
        Bodypane.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 409, -1, -1));

        tgl_kembali.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tgl_kembali.setForeground(new java.awt.Color(0, 51, 153));
        tgl_kembali.setBorder(null);
        Bodypane.add(tgl_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 438, 370, -1));

        jSeparator13.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 466, 390, 10));

        tcarib.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tcarib.setForeground(new java.awt.Color(0, 51, 153));
        tcarib.setBorder(null);
        tcarib.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcaribKeyPressed(evt);
            }
        });
        Bodypane.add(tcarib, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 320, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 51, 153));
        Bodypane.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 320, 10));

        tblkembali.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tblkembali.setForeground(new java.awt.Color(0, 51, 153));
        tblkembali.setModel(new javax.swing.table.DefaultTableModel(
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
        tblkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkembaliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblkembali);

        Bodypane.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 67, 890, 534));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon pack/magnifying-glass (1).png"))); // NOI18N
        Bodypane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("MV Boli", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Cari Data Peminjaman");
        Bodypane.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        getContentPane().add(Bodypane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tnisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnisActionPerformed

    private void kembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali1ActionPerformed
        // TODO add your handling code here:
                if(kosong()){
                        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data !","Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }else{
                        try {
                                String sql1 = "INSERT INTO pengembalian VALUES('"+ kd_kembali.getText() +"','"+ kd_pinjam.getText() +"','"+ tnis.getText() +"','"+ kd_buku.getText() +"','"+ tgl_kembali.getText() +"','"+ denda_telat.getText() +"','"+ ket.getText() +"','1')";
                                String sql2 = "UPDATE siswa set status='Tidak pinjam' WHERE NIS='"+ tnis.getText() +"'";
                                String sql3 = "UPDATE peminjaman SET keterangan='Sudah kembali' WHERE no_pinjam='"+ kd_pinjam.getText() +"'";
                                st = con.prepareStatement(sql1);
                                st2 = con.prepareStatement(sql2);
                                st3 = con.prepareStatement(sql3);
                                st.executeUpdate(sql1);
                                st2.executeUpdate(sql2);
                                st3.executeUpdate(sql3);
                                JOptionPane.showMessageDialog(null,"Berhasil Mengembalikan Buku");
                                reset();
                                waktu();
                                kode_kembali();
                                tampildata_pinjam();
                            } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
                                reset();
                            }
            
                    }
    }//GEN-LAST:event_kembali1ActionPerformed

    private void hilangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hilangActionPerformed
        // TODO add your handling code here:
                if(kosong()){
                        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data !","Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }else{
                        int hilang = 200000;
                        denda_telat.setText(String.valueOf(hilang));
                        try {
                                String sql1 = "INSERT INTO pengembalian VALUES('"+ kd_kembali.getText() +"','"+ kd_pinjam.getText() +"','"+ tnis.getText() +"','"+ kd_buku.getText() +"','"+ tgl_kembali.getText() +"','"+ denda_telat.getText() +"','Hilang','0')";
                                String sql2 = "UPDATE siswa set status='Tidak pinjam' WHERE NIS='"+ tnis.getText() +"'";
                                String sql3 = "UPDATE peminjaman SET keterangan='Sudah kembali' WHERE no_pinjam='"+ kd_pinjam.getText() +"'";
                                st = con.prepareStatement(sql1);
                                st2 = con.prepareStatement(sql2);
                                st3 = con.prepareStatement(sql3);
                                st.executeUpdate(sql1);
                                st.executeUpdate(sql2);
                                st.executeUpdate(sql3);
                                JOptionPane.showMessageDialog(null, "Buku hilang dikenakan denda sebesar Rp.'"+ hilang +"'");
                                JOptionPane.showMessageDialog(null,"Berhasil di input");
                                reset();
                                tampildata_pinjam();
                                waktu();
                                kode_kembali();
                            } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
                                reset();
                            }
                    }
    }//GEN-LAST:event_hilangActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_resetActionPerformed

    private void perpanjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perpanjangActionPerformed
        // TODO add your handling code here:
                if(kosong()){
                        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data !","Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }else{
                        try {
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                Calendar cal = Calendar.getInstance();
                                cal.add(Calendar.DAY_OF_MONTH, 7);
                                String hrs_kembali;
                                hrs_kembali=(df.format(cal.getTime()));
                                sql = "UPDATE peminjaman SET tgl_pinjam='"+ tgl_kembali.getText() +"', tgl_hrskembali='"+ hrs_kembali +"',keterangan='Belum kembali' WHERE no_pinjam='"+ kd_pinjam.getText() +"' ";
                                st = con.createStatement();
                                st.executeUpdate(sql);
                                int a = Integer.valueOf(denda_telat.getText());
                                if(a > 0){
                                        JOptionPane.showMessageDialog(null,"Berhasil di perpanjang dan dikenakan denda sebesar Rp."+ denda_telat.getText() +"");
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Berhasil di perpanjang");
                                    }
                                reset();
                                tampildata_pinjam();
                                waktu();
                                kode_kembali();
                            } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
                                reset();
                            }
                    }
    }//GEN-LAST:event_perpanjangActionPerformed

    private void tblkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkembaliMouseClicked
        // TODO add your handling code here:
         int baris = tblkembali.getSelectedRow();
        kd_pinjam.setText(tblkembali.getModel().getValueAt(baris, 0).toString());
        tnis.setText(tblkembali.getModel().getValueAt(baris, 1).toString());
        kd_buku.setText(tblkembali.getModel().getValueAt(baris, 3).toString());
        tgl_pinjam.setText(tblkembali.getModel().getValueAt(baris, 5).toString());
        hitung_denda();
    }//GEN-LAST:event_tblkembaliMouseClicked

    private void tcaribKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcaribKeyPressed
        // TODO add your handling code here:
        try {
            st = con.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            String cr = tcarib.getText();
            rs = st.executeQuery("SELECT * FROM peminjaman WHERE no_pinjam like '%"+ cr +"%' or NIS like '%"+ cr 
                    +"%' or nama like '%"+ cr +"%' or id_buku like '%"+ cr +"%' or judul like '%"+ cr +"%' ORDER BY DATE(tgl_pinjam)=max(tgl_pinjam) ASC" );
             while (rs.next()){
                    Object[] data ={
                     rs.getString("no_pinjam"),
                     rs.getString("NIS"),
                     rs.getString("nama"),
                     rs.getString("id_buku"),
                     rs.getString("judul"),
                     rs.getString("tgl_pinjam"),
                     rs.getString("tgl_hrskembali"),
                    };
                    tabmodel.addRow(data);
                    }
              if(tcarib.getText().length()<1){
                 tampildata_pinjam();
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tcaribKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bodypane;
    private javax.swing.JTextField denda_telat;
    private javax.swing.JButton hilang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField kd_buku;
    private javax.swing.JTextField kd_kembali;
    private javax.swing.JTextField kd_pinjam;
    private javax.swing.JButton kembali1;
    private javax.swing.JTextField ket;
    private javax.swing.JButton perpanjang;
    private javax.swing.JButton reset;
    private javax.swing.JTable tblkembali;
    private javax.swing.JTextField tcarib;
    private javax.swing.JTextField tgl_kembali;
    private javax.swing.JTextField tgl_pinjam;
    private javax.swing.JTextField tnis;
    // End of variables declaration//GEN-END:variables
}
