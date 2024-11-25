import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

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

// Class Keranjang
class Keranjang {
    private ArrayList<Barang> barangList;

    public Keranjang() {
        barangList = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        barangList.add(barang);
    }

    public ArrayList<Barang> getBarangList() {
        return barangList;
    }

    public double totalHarga() {
        return barangList.stream().mapToDouble(Barang::getHarga).sum();
    }
}

// Main GUI untuk CustomerDriver
public class CustomerDriver {
    private JFrame frame;
    private DefaultListModel<Barang> barangModel;
    private DefaultListModel<Barang> keranjangModel;
    private Keranjang keranjang;
    private ArrayList<Barang> daftarBarang;

    public CustomerDriver() {
        // Inisialisasi data
        keranjang = new Keranjang();
        daftarBarang = new ArrayList<>();

        // Baca data barang dari file
        bacaBarangDariFile("barang.txt");

        // GUI Setup
        frame = new JFrame("Customer Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Customer Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel List Barang
        barangModel = new DefaultListModel<>();
        for (Barang barang : daftarBarang) {
            barangModel.addElement(barang);
        }

        JList<Barang> barangList = new JList<>(barangModel);
        barangList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane barangScrollPane = new JScrollPane(barangList);
        JPanel barangPanel = new JPanel(new BorderLayout());
        barangPanel.setBorder(BorderFactory.createTitledBorder("List Barang"));
        barangPanel.add(barangScrollPane, BorderLayout.CENTER);

        // Panel Keranjang
        keranjangModel = new DefaultListModel<>();
        JList<Barang> keranjangList = new JList<>(keranjangModel);
        keranjangList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane keranjangScrollPane = new JScrollPane(keranjangList);
        JPanel keranjangPanel = new JPanel(new BorderLayout());
        keranjangPanel.setBorder(BorderFactory.createTitledBorder("Keranjang"));
        keranjangPanel.add(keranjangScrollPane, BorderLayout.CENTER);

        // Panel tombol
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        JButton tambahButton = new JButton("Tambah ke Keranjang");
        JButton checkoutButton = new JButton("Checkout");
        JButton metodePembayaranButton = new JButton("Pilih Pembayaran");

        buttonPanel.add(tambahButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(metodePembayaranButton);

        // Panel bawah
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(keranjangPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Tambahkan ke frame
        mainPanel.add(barangPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);

        // Action Listener
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Barang selectedBarang = barangList.getSelectedValue();
                if (selectedBarang != null) {
                    keranjang.tambahBarang(selectedBarang);
                    keranjangModel.addElement(selectedBarang);
                    JOptionPane.showMessageDialog(frame, "Barang berhasil ditambahkan ke keranjang!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Pilih barang terlebih dahulu!");
                }
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keranjang.getBarangList().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Keranjang kosong! Tambahkan barang terlebih dahulu.");
                } else {
                    double total = keranjang.totalHarga();
                    JOptionPane.showMessageDialog(frame, "Total belanja: Rp " + total);
                }
            }
        });

        metodePembayaranButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] metodePembayaran = {"QRIS", "Bank Transfer", "COD"};
                String pilihan = (String) JOptionPane.showInputDialog(frame, "Pilih metode pembayaran:",
                        "Metode Pembayaran", JOptionPane.PLAIN_MESSAGE, null, metodePembayaran, metodePembayaran[0]);

                if (pilihan != null) {
                    JOptionPane.showMessageDialog(frame, "Anda memilih metode pembayaran: " + pilihan);
                }
            }
        });

        frame.setVisible(true);
    }

    // Method untuk membaca barang dari file
    private void bacaBarangDariFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String nama = parts[0];
                double harga = Double.parseDouble(parts[1]);
                daftarBarang.add(new Barang(nama, harga));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Gagal membaca file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CustomerDriver();
    }
}
