import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Class Barang
class Barang {
    private String nama;
    private double harga;

    public Barang(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return nama + " - Rp " + harga;
    }
}

// Class AdminDriver dengan GUI
public class AdminDriver extends JFrame {
    private List<Barang> listBarang;
    private DefaultListModel<String> barangListModel;
    private JList<String> barangList;

    public AdminDriver() {
        setTitle("Kelompok 11 - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        listBarang = new ArrayList<>();
        barangListModel = new DefaultListModel<>();
        barangList = new JList<>(barangListModel);

        // Header
        JLabel headerLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(headerLabel, BorderLayout.NORTH);

        // Panel Konten
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Daftar Barang
        JPanel listPanel = new JPanel(new BorderLayout(10, 10));
        listPanel.setBorder(new TitledBorder("Daftar Barang"));

        JScrollPane scrollPane = new JScrollPane(barangList);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // Tombol di bawah daftar barang
        JPanel listButtonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton deleteButton = new JButton("Hapus Barang");
        JButton editButton = new JButton("Edit Barang");

        listButtonPanel.add(deleteButton);
        listButtonPanel.add(editButton);
        listPanel.add(listButtonPanel, BorderLayout.SOUTH);

        contentPanel.add(listPanel, BorderLayout.CENTER);

        // Form Tambah Barang
        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        formPanel.setBorder(new TitledBorder("Tambah Barang"));

        JLabel nameLabel = new JLabel("Nama Barang:");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Harga Barang:");
        JTextField priceField = new JTextField();

        JButton addButton = new JButton("Tambah Barang");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);
        formPanel.add(addButton);

        contentPanel.add(formPanel, BorderLayout.EAST);

        add(contentPanel, BorderLayout.CENTER);

        // Panel Footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton transaksiButton = new JButton("Kelola Transaksi");
        JButton logoutButton = new JButton("Logout");
        footerPanel.add(transaksiButton);
        footerPanel.add(logoutButton);
        add(footerPanel, BorderLayout.SOUTH);

        // Event untuk tombol Tambah Barang
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = nameField.getText();
                String hargaStr = priceField.getText();

                if (nama.isEmpty() || hargaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminDriver.this, "Nama dan harga harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double harga = Double.parseDouble(hargaStr);
                    Barang barang = new Barang(nama, harga);
                    listBarang.add(barang);
                    barangListModel.addElement(barang.toString());
                    nameField.setText("");
                    priceField.setText("");
                    JOptionPane.showMessageDialog(AdminDriver.this, "Barang berhasil ditambahkan!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AdminDriver.this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Event untuk tombol Hapus Barang
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = barangList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listBarang.remove(selectedIndex);
                    barangListModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(AdminDriver.this, "Barang berhasil dihapus!");
                } else {
                    JOptionPane.showMessageDialog(AdminDriver.this, "Pilih barang yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Event untuk tombol Edit Barang
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = barangList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Barang barang = listBarang.get(selectedIndex);

                    String newNama = JOptionPane.showInputDialog(AdminDriver.this, "Masukkan nama baru:", barang.getNama());
                    String newHargaStr = JOptionPane.showInputDialog(AdminDriver.this, "Masukkan harga baru:", barang.getHarga());

                    if (newNama != null && !newNama.isEmpty() && newHargaStr != null && !newHargaStr.isEmpty()) {
                        try {
                            double newHarga = Double.parseDouble(newHargaStr);
                            barangList.remove(selectedIndex);
                            listBarang.set(selectedIndex, new Barang(newNama, newHarga));
                            barangListModel.set(selectedIndex, newNama + " - Rp " + newHarga);
                            JOptionPane.showMessageDialog(AdminDriver.this, "Barang berhasil diedit!");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(AdminDriver.this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminDriver.this, "Pilih barang yang ingin diedit!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Event untuk tombol Kelola Transaksi
        transaksiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminDriver.this, "Fitur Kelola Transaksi belum diimplementasikan!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Event untuk tombol Logout
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(AdminDriver.this, "Yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose(); // Menutup window Admin
                    Interface login = new Interface();
                    login.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminDriver().setVisible(true);
            }
        });
    }
}
