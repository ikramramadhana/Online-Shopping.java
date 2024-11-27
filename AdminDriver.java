import java.io.*;
import java.util.Scanner;

public class AdminDriver {
    private Admin admin;
    private ListBarang listBarang;

    public AdminDriver(Admin admin, ListBarang listBarang) {
        this.admin = admin;
        this.listBarang = listBarang;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Menu Admin ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Lihat Barang");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahBarang(scanner);
                    break;
                case 2:
                    hapusBarang(scanner);
                    break;
                case 3:
                    lihatBarangTabel();
                    break;
                case 4:
                    lihatRiwayatTransaksi();
                    break;
                case 5:
                    System.out.println("Keluar dari menu admin.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tambahBarang(Scanner scanner) {
        System.out.print("ID Barang: ");
        String idBarang = scanner.nextLine();

        if (listBarang.isIdBarangExist(idBarang)) {
            System.out.println("ID Barang sudah ada. Silakan masukkan ID yang berbeda.");
            return;
        }

        System.out.print("Nama Barang: ");
        String namaBarang = scanner.nextLine();
        System.out.print("Harga Barang: ");
        double hargaBarang = scanner.nextDouble();
        System.out.print("Stok Barang: ");
        int stokBarang = scanner.nextInt();
        scanner.nextLine();

        Barang barangBaru = new Barang(idBarang, namaBarang, stokBarang, hargaBarang);
        listBarang.tambahBarang(barangBaru);
        System.out.println("Barang berhasil ditambahkan!");
    }

    private void hapusBarang(Scanner scanner) {
        System.out.print("Masukkan Nama Barang yang ingin dihapus: ");
        String namaBarang = scanner.nextLine();
        listBarang.hapusBarang(namaBarang);
        System.out.println("Barang berhasil dihapus!");
    }

    private void lihatBarangTabel() {
        System.out.println("=== Daftar Barang ===");
        System.out.println("+-----------+----------------------+----------+-------------+");
        System.out.printf("| %-9s | %-20s | %-8s | %-11s |\n", "ID Barang", "Nama Barang", "Stok", "Harga");
        System.out.println("+-----------+----------------------+----------+-------------+");
        for (Barang barang : listBarang.getBarang()) {
            System.out.printf("| %-9s | %-20s | %-8d | %-11.2f |\n",
                    barang.getIdBarang(),
                    barang.getNama(),
                    barang.getStok(),
                    barang.getHarga());
        }
        System.out.println("+-----------+----------------------+----------+-------------+");
    }

    private void lihatRiwayatTransaksi() {
        System.out.println("=== Riwayat Transaksi ===");
        try (BufferedReader reader = new BufferedReader(new FileReader("riwayat.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca riwayat transaksi: " + e.getMessage());
        }
    }
}
