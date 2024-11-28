import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Transaksi {
    private Customer akun; // Relasi dengan Customer
    private List<Barang> barang; // Relasi dengan Barang
    private static final String[] NAMA_RANDOM = {
        "Ikram Al Ghiffari", "Adinda", "Ikram Ramadhana", "Michelle Augen"
    }; // Daftar nama acak

    public Transaksi(Customer akun, List<Barang> barang) {
        this.akun = akun;
        this.barang = new ArrayList<>(barang); // Salin daftar barang dari keranjang
    }

    public Customer getAkun() {
        return akun;
    }

    public List<Barang> getBarang() {
        return barang;
    }

    public String getRandomCustomerName() {
        Random rand = new Random();
        return NAMA_RANDOM[rand.nextInt(NAMA_RANDOM.length)];
    }

    public void printTransaksi() {
        System.out.println("=== Detail Transaksi ===");
        // Menampilkan nama customer secara acak, bukan akun.getUsername()
        System.out.println("Nama Customer: " + getRandomCustomerName());
        for (Barang b : barang) {
            System.out.printf("ID Barang: %s\n", b.getIdBarang()); // Menampilkan hanya ID Barang
        }
        System.out.println("========================");
    }
}
