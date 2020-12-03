/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package halaman_siswa;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.plaf.basic.BasicInternalFrameUI;
public class DataPeminjaman extends javax.swing.JInternalFrame {
    Statement s;
    ResultSet rs;
    Connection conn = Koneksi.kon.conn();
    DefaultTableModel model;
    
    public DataPeminjaman() {
        initComponents();
        judul_peminjaman();
        tampildata_peminjam();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
    }
    public void judul_peminjaman(){
        Object[] judul = {"No Pinjman","NIS","Nama","Id Buku","Judul","Tanggal Pinjam","Tanggal Harus Kembali, Jemlah, Ket"};
        model = new DefaultTableModel(null,judul){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblpeminjaman.setModel(model);
    }
    
    public void tampildata_peminjam(){
        try{
            s = conn.createStatement();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            rs = s.executeQuery("SELECT * FROM peminjaman ORDER BY nama DESC");
            while(rs.next()){
                Object [] data = {
                    rs.getString("no_pinjam"),
                    rs.getString("NIS"),
                    rs.getString("nama"),
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("tgl_pinjam"),
                    rs.getString("tgl_hrskembali"),
                    rs.getString("jumlah"),
                    rs.getString("keterangan"),
                };
                model.addRow(data);
                model.getRowCount();
                
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpeminjaman = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        bresfresh = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(990, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(990, 630));

        jLabel1.setFont(new java.awt.Font("MV Boli", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Data Peminjaman Buku");

        tblpeminjaman.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tblpeminjaman.setForeground(new java.awt.Color(0, 51, 153));
        tblpeminjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblpeminjaman);

        tcari.setFont(new java.awt.Font("MV Boli", 0, 13)); // NOI18N
        tcari.setForeground(new java.awt.Color(0, 51, 153));
        tcari.setBorder(null);
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcariKeyPressed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));

        bresfresh.setBackground(new java.awt.Color(0, 51, 153));
        bresfresh.setFont(new java.awt.Font("MV Boli", 0, 16)); // NOI18N
        bresfresh.setForeground(new java.awt.Color(255, 255, 255));
        bresfresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon pack/reload.png"))); // NOI18N
        bresfresh.setText("Resfresh Table");
        bresfresh.setBorder(null);
        bresfresh.setContentAreaFilled(false);
        bresfresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bresfresh.setOpaque(true);
        bresfresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bresfreshActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon pack/magnifying-glass (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(704, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(bresfresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bresfresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bresfreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bresfreshActionPerformed
        // TODO add your handling code here:
        tampildata_peminjam();
    }//GEN-LAST:event_bresfreshActionPerformed

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed
        // TODO add your handling code here:
        try {
            s = conn.createStatement();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            String cr = tcari.getText();
            rs = s.executeQuery("SELECT * FROM peminjaman WHERE no_pinjam like '%"+ cr +"%' or NIS like '%"+ cr 
                    +"%' or nama like '%"+ cr +"%' or id_buku like '%"+ cr +"%' or judul like '%"+ cr +"%' ORDER BY no_pinjam DESC" );
             while (rs.next()){
                    Object[] data ={
                    rs.getString("no_pinjam"),
                    rs.getString("NIS"),
                    rs.getString("nama"),
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("tgl_pinjam"),
                    rs.getString("tgl_hrskembali"),
                    rs.getString("jumlah"),
                    rs.getString("keterangan"),
                    };
                    model.addRow(data);
                    }
//              if(tcari.getText().length()<1){
//                 tampildata_peminjam();
//             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tcariKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bresfresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblpeminjaman;
    private javax.swing.JTextField tcari;
    // End of variables declaration//GEN-END:variables
}
