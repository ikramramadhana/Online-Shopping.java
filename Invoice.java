import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Invoice {
    private Transaksi transaksi; // Tambahkan atribut Transaksi
    private List<Barang> keranjang;
    private double totalHarga;
    private String metodePembayaran;
    private String id;

    // Constructor baru untuk menerima objek Transaksi
    public Invoice(Transaksi transaksi) {
        this.transaksi = transaksi;
        this.keranjang = transaksi.getBarang(); // Ambil barang dari transaksi
        this.totalHarga = 0;
        this.metodePembayaran = "";
        this.id = "INV" + System.currentTimeMillis();
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public double getTotal() {
        return totalHarga;
    }

    public String getId() {
        return id;
    }

    public void printInvoice() {
        System.out.println("=== Invoice Pembelian ===");
        for (Barang barang : transaksi.getBarang()) {
            double subtotal = barang.getHarga() * barang.getJumlah();
            totalHarga += subtotal;
            System.out.printf("ID Barang: %s\n", barang.getIdBarang());
            System.out.printf("Nama Barang: %s\n", barang.getNama());
            System.out.printf("Jumlah: %d\n", barang.getJumlah());
            System.out.printf("Total Harga: %.2f\n", subtotal);
            System.out.println("=========================");
        }

        System.out.printf("Total Pembelian: %.2f\n", totalHarga);
        System.out.println("=========================");
    }

    public void saveRiwayatTransaksi() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("riwayat.txt", true))) {
            int nomorTransaksi = getNomorTransaksi();
            // Menggunakan metode getRandomCustomerName() dari Transaksi
            String namaCustomer = transaksi.getRandomCustomerName(); // Mendapatkan nama customer secara acak
            writer.write("Transaksi #" + nomorTransaksi + " | Customer: " + namaCustomer + " = ");
            
            for (Barang barang : keranjang) {
                writer.write(barang.getNama() + ", ");
            }
            writer.write("Metode Pembayaran: " + metodePembayaran + ", ");
            double totalPembelian = 0.0;
            for (Barang barang : keranjang) {
                totalPembelian += barang.getHarga() * barang.getJumlah();
            }
            writer.write("Total Pembelian: " + totalPembelian + "\n");
            writer.write("================================================================================================\n");
            System.out.println("Transaksi berhasil disimpan.");
        } catch (IOException e) {
            System.out.println("Gagal menulis riwayat transaksi: " + e.getMessage());
        }
    }
    
    private int getNomorTransaksi() {
        int nomorTransaksi = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader("riwayat.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Transaksi #")) {
                    String[] parts = line.split(":");
                    String transaksiId = parts[0].replaceAll("[^0-9]", "");
                    int id = Integer.parseInt(transaksiId);
                    if (id >= nomorTransaksi) {
                        nomorTransaksi = id + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca riwayat transaksi: " + e.getMessage());
        }
        return nomorTransaksi;
    }
}
