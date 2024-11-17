package Driver;

import Akun.Admin;
import Akun.Customer;
import Barang.Barang;
import Barang.ListBarang;
import java.util.Scanner;

public class AdminDriver {
    private Admin admin;
    private ListBarang listBarang;
    private Customer customer;

    // Konstruktor AdminDriver
    public AdminDriver(Admin admin, ListBarang listBarang, Customer customer) {
        this.admin = admin;
        this.listBarang = listBarang;
        this.customer = customer;
    }

    // Metode untuk menjalankan menu admin
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Lihat Barang");
            System.out.println("4. Lihat History Invoice");
            System.out.println("5. Keluar");
            System.out.print("Pilihan anda: ");

            // Validasi input pilihan
            if (!scanner.hasNextInt()) {
                System.out.println("Input tidak valid! Harap masukkan angka 1-5.");
                scanner.next(); // Buang input yang salah
                continue;
            }

            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahBarang(scanner);
                    break;
                case 2:
                    hapusBarang(scanner);
                    break;
                case 3:
                    listBarang.displayBarang();
                    break;
                case 4:
                    lihatHistoryInvoice();
                    break;
                case 5:
                    running = false;
                    System.out.println("Keluar dari menu Admin.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    // Metode untuk menambahkan barang baru
    private void tambahBarang(Scanner scanner) {
        scanner.nextLine(); // Membersihkan newline

        // Input data barang
        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine(); // Menggunakan nextLine agar input lebih fleksibel

        System.out.print("Harga Barang: ");
        // Validasi input harga
        while (!scanner.hasNextDouble()) {
            System.out.println("Input tidak valid! Masukkan harga dalam bentuk angka.");
            scanner.next(); // Buang input yang salah
        }
        double harga = scanner.nextDouble();

        System.out.print("Stok Barang: ");
        // Validasi input stok
        while (!scanner.hasNextInt()) {
            System.out.println("Input tidak valid! Masukkan stok dalam bentuk angka.");
            scanner.next(); // Buang input yang salah
        }
        int stok = scanner.nextInt();

        // Membuat objek Barang baru
        Barang barang = new Barang("ID" + (listBarang.getBarangList().size() + 1), nama, harga, stok);
        listBarang.tambahBarang(barang);
        System.out.println("Barang berhasil ditambahkan.");
    }

    // Metode untuk menghapus barang berdasarkan ID
    private void hapusBarang(Scanner scanner) {
        scanner.nextLine(); // Membersihkan newline
        System.out.print("ID Barang yang ingin dihapus: ");
        String id = scanner.nextLine();

        // Menghapus barang
        if (listBarang.hapusBarang(id)) {
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    // Metode untuk menampilkan riwayat transaksi customer
    private void lihatHistoryInvoice() {
        System.out.println("\n=== History Transaksi Customer ===");
        // Mengecek apakah customer memiliki riwayat transaksi
        if (customer.getHistory().isEmpty()) {
            System.out.println("Belum ada transaksi yang selesai.");
        } else {
            // Menampilkan riwayat transaksi
            for (String invoice : customer.getHistory()) {
                System.out.println(invoice);
            }
        }
    }
}
