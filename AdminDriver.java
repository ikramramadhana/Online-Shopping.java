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

    public AdminDriver(Admin admin, ListBarang listBarang, Customer customer) {
        this.admin = admin;
        this.listBarang = listBarang;
        this.customer = customer;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Tambah Barang\n2. Hapus Barang\n3. Lihat Barang\n4. Lihat History Invoice\n5. Keluar");
            System.out.print("Pilihan anda: ");
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
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    private void tambahBarang(Scanner scanner) {
        System.out.print("Nama Barang: ");
        String nama = scanner.next();
        System.out.print("Harga Barang: ");
        double harga = scanner.nextDouble();
        System.out.print("Stok Barang: ");
        int stok = scanner.nextInt();
        Barang barang = new Barang("ID" + (listBarang.getBarangList().size() + 1), nama, harga, stok);
        listBarang.tambahBarang(barang);
        System.out.println("Barang berhasil ditambahkan.");
    }

    private void hapusBarang(Scanner scanner) {
        System.out.print("ID Barang yang ingin dihapus: ");
        String id = scanner.next();
        if (listBarang.hapusBarang(id)) {
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    private void lihatHistoryInvoice() {
        System.out.println("History Transaksi Customer:");
        for (String invoice : customer.getHistory()) {
            System.out.println(invoice);
        }
    }
}
