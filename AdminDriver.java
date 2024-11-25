import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Class Barang
class Barang {
    private static int counter = 1; // Counter untuk ID Barang
    private int id;
    private String nama;
    private double harga;
    private int stok;

    public Barang(String nama, double harga, int stok) {
        this.id = counter++;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}

// Class Transaksi
class Transaksi {
    private String pelanggan;
    private Barang barang;
    private int jumlah;
    private double totalHarga;

    public Transaksi(String pelanggan, Barang barang, int jumlah) {
        this.pelanggan = pelanggan;
        this.barang = barang;
        this.jumlah = jumlah;
        this.totalHarga = jumlah * barang.getHarga();
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getTotalHarga() {
        return totalHarga;
    }
}

// Class GradientPanel untuk background gradien
class GradientPanel extends JPanel {
    private Color color1;
    private Color color2;

    public GradientPanel(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}

// Class AdminDriver dengan GUI menggunakan JTable
public class AdminDriver extends JFrame {
    private List<Barang> listBarang;
    private List<Transaksi> listTransaksi; 
    private DefaultTableModel tableModel;

    public AdminDriver() {
        setTitle("Kelompok 11 - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        listBarang = new ArrayList<>();
        listTransaksi = new ArrayList<>();
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama Barang", "Harga Barang", "Stok"}, 0);

        // Panel utama dengan background gradient biru
        GradientPanel mainPanel = new GradientPanel(new Color(135, 206, 235), new Color(25, 25, 112));
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Header
        JLabel headerLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(20, 10, 20, 10));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Konten utama
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Tabel Barang
        JTable barangTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(barangTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Panel tombol di bawah tabel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setOpaque(false);

        JButton addButton = new JButton("Tambah Barang");
        JButton deleteButton = new JButton("Hapus Barang");
        JButton editButton = new JButton("Edit Barang");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        footerPanel.setOpaque(false);
        JButton transaksiButton = new JButton("Kelola Transaksi");
        JButton logoutButton = new JButton("Logout");
        footerPanel.add(transaksiButton);
        footerPanel.add(logoutButton);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // Event untuk tombol Tambah Barang
        addButton.addActionListener(e -> {
            String nama = JOptionPane.showInputDialog(this, "Masukkan nama barang:");
            if (nama == null || nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama barang tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hargaStr = JOptionPane.showInputDialog(this, "Masukkan harga barang:");
            String stokStr = JOptionPane.showInputDialog(this, "Masukkan stok barang:");

            if (hargaStr == null || hargaStr.isEmpty() || stokStr == null || stokStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Harga dan stok barang tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double harga = Double.parseDouble(hargaStr);
                int stok = Integer.parseInt(stokStr);

                Barang barang = new Barang(nama, harga, stok);
                listBarang.add(barang);
                tableModel.addRow(new Object[]{barang.getId(), barang.getNama(), barang.getHarga(), barang.getStok()});
                JOptionPane.showMessageDialog(this, "Barang berhasil ditambahkan!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Harga dan stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Event untuk tombol Hapus Barang
        deleteButton.addActionListener(e -> {
            int selectedRow = barangTable.getSelectedRow();
            if (selectedRow != -1) {
                listBarang.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Barang berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih barang yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Event untuk tombol Edit Barang
        editButton.addActionListener(e -> {
            int selectedRow = barangTable.getSelectedRow();
            if (selectedRow != -1) {
                Barang barang = listBarang.get(selectedRow);

                String newNama = JOptionPane.showInputDialog(this, "Masukkan nama baru:", barang.getNama());
                if (newNama == null || newNama.isEmpty()) return;

                String newHargaStr = JOptionPane.showInputDialog(this, "Masukkan harga baru:", barang.getHarga());
                String newStokStr = JOptionPane.showInputDialog(this, "Masukkan stok baru:", barang.getStok());

                if (newHargaStr == null || newStokStr == null || newHargaStr.isEmpty() || newStokStr.isEmpty()) return;

                try {
                    double newHarga = Double.parseDouble(newHargaStr);
                    int newStok = Integer.parseInt(newStokStr);

                    barang.setNama(newNama);
                    barang.setHarga(newHarga);
                    barang.setStok(newStok);

                    tableModel.setValueAt(newNama, selectedRow, 1);
                    tableModel.setValueAt(newHarga, selectedRow, 2);
                    tableModel.setValueAt(newStok, selectedRow, 3);

                    JOptionPane.showMessageDialog(this, "Barang berhasil diedit!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Harga dan stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih barang yang ingin diedit!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Event untuk tombol Logout
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
    }

    private void kelolaTransaksi() {
        JDialog transaksiDialog = new JDialog(this, "Kelola Transaksi", true);
        transaksiDialog.setSize(600, 400);
        transaksiDialog.setLocationRelativeTo(this);

        DefaultTableModel transaksiTableModel = new DefaultTableModel(
                new String[]{"Nama Pelanggan", "Nama Barang", "Jumlah", "Total Harga"}, 0
        );

        for (Transaksi transaksi : listTransaksi) {
            transaksiTableModel.addRow(new Object[]{
                    transaksi.getPelanggan(),
                    transaksi.getBarang().getNama(),
                    transaksi.getJumlah(),
                    transaksi.getTotalHarga()
            });
        }

        JTable transaksiTable = new JTable(transaksiTableModel);
        JScrollPane transaksiScrollPane = new JScrollPane(transaksiTable);

        transaksiDialog.setLayout(new BorderLayout());
        transaksiDialog.add(transaksiScrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> transaksiDialog.dispose());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(closeButton);
        transaksiDialog.add(bottomPanel, BorderLayout.SOUTH);

        transaksiDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminDriver admin = new AdminDriver();
            admin.setVisible(true);
        });
    }
}
