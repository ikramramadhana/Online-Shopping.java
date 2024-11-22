import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerDriver {
    private Customer customer;
    private ListBarang listBarang;

    public CustomerDriver(Customer customer, ListBarang listBarang) {
        this.customer = customer;
        this.listBarang = listBarang;
    }

    // Metode untuk menjalankan menu customer berbasis GUI
    public void run() {
        Keranjang keranjang = customer.getKeranjang();
        boolean running = true;

        while (running) {
            String[] options = {"Lihat Barang", "Tambah ke Keranjang", "Checkout", "Lihat History", "Keluar"};
            int pilihan = JOptionPane.showOptionDialog(null, "Pilih Aksi Customer:", "Menu Customer",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (pilihan) {
                case 0:
                    lihatBarang();
                    break;
                case 1:
                    tambahKeKeranjang(keranjang);
                    break;
                case 2:
                    checkout(keranjang);
                    break;
                case 3:
                    lihatHistoryBelanja();
                    break;
                case 4:
                    running = false;
                    JOptionPane.showMessageDialog(null, "Keluar dari menu Customer.");
                    break;
                default:
                    break;
            }
        }
    }

    // Metode GUI untuk melihat daftar barang
    private void lihatBarang() {
        List<Barang> barangList = listBarang.getBarangList();
        StringBuilder sb = new StringBuilder("=== Daftar Barang ===\n");
        for (Barang barang : barangList) {
            sb.append(barang).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        JOptionPane.showMessageDialog(null, scrollPane, "Lihat Barang", JOptionPane.INFORMATION_MESSAGE);
    }

    // Metode GUI untuk menambahkan barang ke keranjang
    private void tambahKeKeranjang(Keranjang keranjang) {
        String namaBarang = JOptionPane.showInputDialog(null, "Masukkan Nama Barang yang ingin dibeli:");
        if (namaBarang != null && !namaBarang.isEmpty()) {
            for (Barang barang : listBarang.getBarangList()) {
                if (barang.getNama().equalsIgnoreCase(namaBarang)) {
                    keranjang.tambahBarang(barang);
                    JOptionPane.showMessageDialog(null, "Barang ditambahkan ke keranjang.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Barang tidak ditemukan.");
        }
    }

    // Metode GUI untuk checkout
    private void checkout(Keranjang keranjang) {
        String[] metodePembayaran = {"QRIS", "Bank", "COD"};
        int metode = JOptionPane.showOptionDialog(null, "Pilih Metode Pembayaran:", "Checkout",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, metodePembayaran, metodePembayaran[0]);

        Pembayaran pembayaran = null;

        switch (metode) {
            case 0:
                pembayaran = new QRIS("QRIS");
                break;
            case 1:
                pembayaran = new Bank("Bank");
                break;
            case 2:
                pembayaran = new COD("COD");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Metode pembayaran tidak valid.");
                return;
        }

        if (pembayaran != null) {
            pembayaran.prosesPembayaran();
            
            // Mengurangi stok barang
            for (Barang barang : keranjang.getBarangKeranjang().keySet()) {
                int jumlah = keranjang.getBarangKeranjang().get(barang);
                boolean stokDikurangi = listBarang.kurangiStok(barang.getNama(), jumlah);
                if (!stokDikurangi) {
                    JOptionPane.showMessageDialog(null, "Gagal mengurangi stok barang: " + barang.getNama());
                }
            }

            // Membuat invoice dan menambahkannya ke riwayat transaksi customer
            String invoice = "INV" + (customer.getHistory().size() + 1);
            customer.addInvoice(invoice);
            JOptionPane.showMessageDialog(null, "Pembayaran berhasil. Invoice: " + invoice);

            // Mengosongkan keranjang
            keranjang.kosongkanKeranjang();
        } else {
            JOptionPane.showMessageDialog(null, "Pembayaran gagal dilakukan.");
        }
    }

    // Metode GUI untuk melihat riwayat belanja
    private void lihatHistoryBelanja() {
        List<String> history = customer.getHistory();
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Belum ada transaksi yang selesai.");
        } else {
            StringBuilder sb = new StringBuilder("=== Riwayat Belanja ===\n");
            for (String invoice : history) {
                sb.append(invoice).append("\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(null, scrollPane, "History Belanja", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
