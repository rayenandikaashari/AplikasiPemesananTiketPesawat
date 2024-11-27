/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasipemesanantiketpesawat;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PemesananTiket extends javax.swing.JFrame {

    private javax.swing.JTextField name;
    private javax.swing.JTextField email;
    private javax.swing.JTextField password;

    String jam, tgl, bln, thn;
    
    public void jam() {
        if (jam1.isSelected()) {
            jam = jam1.getText();
        } else if (jam2.isSelected()) {
            jam = jam2.getText();
        } else if (jam3.isSelected()) {
            jam = jam3.getText();
        } else if (jam4.isSelected()) {
            jam = jam4.getText();
        }
    }
    
    private String getNextId() {
    String nextId = "KRW-01";
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT id_penerbangan FROM pemesanan ORDER BY id_penerbangan DESC LIMIT 1";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String lastId = rs.getString("id_penerbangan");
            String[] parts = lastId.split("-"); 
            int number = Integer.parseInt(parts[1]);
            nextId = "KRW-" + String.format("%02d", number + 1);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal mendapatkan ID penerbangan.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return nextId;
}


    
    public void cetak(){
        String tgl = tanggal.getSelectedItem().toString() + " " +
                 bulan.getSelectedItem().toString() + " " +
                 tahun.getSelectedItem().toString();

    if (nama.getText().isEmpty() || id.getText().isEmpty() || jumlah.getText().isEmpty() || 
        tgl.contains("= Tanggal =")) {
        JOptionPane.showMessageDialog(null, "Semua data harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String data = "Nama: " + nama.getText() +
                  "\nID Penerbangan: " + id.getText() +
                  "\nMaskapai: " + maskapai.getSelectedItem().toString() +
                  "\nDestinasi: " + destinasi.getSelectedItem().toString() +
                  "\nTanggal Keberangkatan: " + tgl +
                  "\nJam Keberangkatan: " + jam +
                  "\nJumlah Kursi: " + jumlah.getText() +
                  "\nTotal Harga: Rp " + total.getText();

    JOptionPane.showMessageDialog(null, "Berikut data pesanan Anda:\n\n" + data, "Data Pesanan", JOptionPane.INFORMATION_MESSAGE);

    int h = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin mencetak tiket?", "Konfirmasi Cetak", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    if (h == JOptionPane.YES_OPTION) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO pemesanan (nama, id_penerbangan, maskapai, destinasi, tanggal_keberangkatan, jam_keberangkatan, jumlah_kursi, total_harga) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nama.getText());
            stmt.setString(2, id.getText());
            stmt.setString(3, maskapai.getSelectedItem().toString());
            stmt.setString(4, destinasi.getSelectedItem().toString());
            stmt.setString(5, tgl);
            stmt.setString(6, jam);
            stmt.setInt(7, Integer.parseInt(jumlah.getText()));
            stmt.setInt(8, Integer.parseInt(total.getText()));

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke database.", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                saveToFile(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data ke database.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Proses cetak dibatalkan.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
}

private void saveToFile(String data) {
    try (FileWriter writer = new FileWriter("Tiket_" + id.getText() + ".txt")) {
        writer.write("===== Data Pesanan Tiket =====\n");
        writer.write(data);
        writer.write("\n================================\n");
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke file.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Gagal menyimpan data ke file.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    public PemesananTiket() {
        initComponents();
        
        id.setText(getNextId());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        checkbox1 = new java.awt.Checkbox();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        scrollPane1 = new java.awt.ScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        maskapai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        destinasi = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jam1 = new javax.swing.JRadioButton();
        jam2 = new javax.swing.JRadioButton();
        jam3 = new javax.swing.JRadioButton();
        jam4 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        check = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        cetak = new javax.swing.JButton();
        jumlah = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tanggal = new javax.swing.JComboBox<>();
        bulan = new javax.swing.JComboBox<>();
        tahun = new javax.swing.JComboBox<>();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        checkbox1.setLabel("checkbox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 638));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setForeground(new java.awt.Color(0, 102, 204));
        jPanel5.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pemesanan Tiket Pesawat");
        jLabel1.setAlignmentY(0.0F);
        jPanel5.add(jLabel1);
        jLabel1.setBounds(286, 21, 219, 25);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setForeground(new java.awt.Color(0, 102, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 900));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 900));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID Penerbangan");

        id.setEditable(false);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Maskapai");

        maskapai.setBackground(new java.awt.Color(204, 204, 204));
        maskapai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "= Pilih Maskapai =", "Garuda Indonesia", "Air Asia", "Lion Air" }));
        maskapai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maskapaiActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Destinasi");

        destinasi.setBackground(new java.awt.Color(204, 204, 204));
        destinasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=Pilih Destinasi=", "Karawang-Jakarta", "Karawang-Bandung", "Karawang-Bekasi", "Karawang-Tangerang" }));
        destinasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinasiActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jam Keberangkatan");

        jam1.setBackground(new java.awt.Color(0, 102, 153));
        buttonGroup1.add(jam1);
        jam1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jam1.setForeground(new java.awt.Color(255, 255, 255));
        jam1.setText("03:00");
        jam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jam1ActionPerformed(evt);
            }
        });

        jam2.setBackground(new java.awt.Color(0, 102, 153));
        buttonGroup1.add(jam2);
        jam2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jam2.setForeground(new java.awt.Color(255, 255, 255));
        jam2.setText("09:00");
        jam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jam2ActionPerformed(evt);
            }
        });

        jam3.setBackground(new java.awt.Color(0, 102, 153));
        buttonGroup1.add(jam3);
        jam3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jam3.setForeground(new java.awt.Color(255, 255, 255));
        jam3.setText("15:00");
        jam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jam3ActionPerformed(evt);
            }
        });

        jam4.setBackground(new java.awt.Color(0, 102, 153));
        buttonGroup1.add(jam4);
        jam4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jam4.setForeground(new java.awt.Color(255, 255, 255));
        jam4.setText("20:00");
        jam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jam4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Harga");

        harga.setEditable(false);
        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jumlah Kursi");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total Harga");

        total.setEditable(false);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        check.setBackground(new java.awt.Color(255, 255, 254));
        check.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        check.setForeground(new java.awt.Color(0, 153, 204));
        check.setText("CHECK");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });

        exit.setBackground(new java.awt.Color(255, 102, 102));
        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        cetak.setBackground(new java.awt.Color(51, 255, 51));
        cetak.setText("CETAK");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tanggal Keberangkatan");

        tanggal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "= Tanggal =", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });

        bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "= Bulan =", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "Sepetember", "Oktober", "November", "Desember" }));
        bulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bulanActionPerformed(evt);
            }
        });

        tahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "= Tahun =", "2024", "2025" }));
        tahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tahunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(destinasi, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maskapai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jam1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jam2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jam3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jam4))
                            .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check)))
                        .addContainerGap(220, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(exit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cetak)
                        .addGap(128, 128, 128))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(maskapai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(destinasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jam1)
                            .addComponent(jam2)
                            .addComponent(jam3)
                            .addComponent(jam4))
                        .addGap(18, 18, 18)
                        .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(412, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel1);
        jPanel1.setBounds(0, 70, 790, 530);

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 600));

        setSize(new java.awt.Dimension(800, 607));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void maskapaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maskapaiActionPerformed
        if(maskapai.getSelectedItem().equals("Garuda Indonesia")){
            harga.setText("700000");
        }
        else if(maskapai.getSelectedItem().equals("Air Asia")){
            harga.setText("600000");
        }
        else if(maskapai.getSelectedItem().equals("Lion Air")){
            harga.setText("550000");
        }
        else{
            harga.setText("0");
        }
    }//GEN-LAST:event_maskapaiActionPerformed

    private void destinasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destinasiActionPerformed

    private void jam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jam1ActionPerformed
        jam();
    }//GEN-LAST:event_jam1ActionPerformed

    private void jam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jam2ActionPerformed
        jam();
    }//GEN-LAST:event_jam2ActionPerformed

    private void jam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jam3ActionPerformed
        jam();
    }//GEN-LAST:event_jam3ActionPerformed

    private void jam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jam4ActionPerformed
        jam();
    }//GEN-LAST:event_jam4ActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        int hargaa = Integer.parseInt(harga.getText());
        int hargab = Integer.parseInt(jumlah.getText());
        total.setText(Integer.toString(hargaa*hargab));
    }//GEN-LAST:event_checkActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        cetak();
    }//GEN-LAST:event_cetakActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalActionPerformed

    private void bulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bulanActionPerformed

    private void tahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tahunActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bulan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton cetak;
    private javax.swing.JButton check;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JComboBox<String> destinasi;
    private javax.swing.JButton exit;
    private javax.swing.JTextField harga;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jam1;
    private javax.swing.JRadioButton jam2;
    private javax.swing.JRadioButton jam3;
    private javax.swing.JRadioButton jam4;
    private javax.swing.JTextField jumlah;
    private javax.swing.JComboBox<String> maskapai;
    private javax.swing.JTextField nama;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JComboBox<String> tahun;
    private javax.swing.JComboBox<String> tanggal;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
