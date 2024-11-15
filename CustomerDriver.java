package Driver;

import Akun.Customer;
import Barang.Barang;
import Barang.ListBarang;
import Keranjang.Keranjang;
import Transaksi.Pembayaran;
import Transaksi.Bank;
import Transaksi.QRIS;
import Transaksi.COD;

import java.util.Scanner;

public class CustomerDriver {
    private Customer customer;
    private ListBarang listBarang;

    public CustomerDriver(Customer customer, ListBarang listBarang) {
        this.customer = customer;
        this.listBarang = listBarang;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Keranjang keranjang = customer.getKeranjang();
        boolean running = true;

        while (running) {
            System.out.println("1. Lihat Barang\n2. Tambah ke Keranjang\n3. Checkout\n4. Lihat History\n5. Keluar");
            System.out.print("Pilihan anda: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    listBarang.displayBarang();
                    break;
                case 2:
                    tambahKeKeranjang(scanner, keranjang);
                    break;
                case 3:
                    checkout(scanner, keranjang);
                    break;
                case 4:
                    lihatHistoryBelanja();
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

    private void tambahKeKeranjang(Scanner scanner, Keranjang keranjang) {
        System.out.print("Nama Barang yang ingin dibeli: ");
        String nama = scanner.next();
        for (Barang barang : listBarang.getBarangList()) {
            if (barang.getNama().equalsIgnoreCase(nama)) {
                keranjang.tambahBarang(barang);
                System.out.println("Barang ditambahkan ke keranjang.");
                return;
            }
        }
        System.out.println("Barang tidak ditemukan.");
    }

    private void checkout(Scanner scanner, Keranjang keranjang) {
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. QRIS");
        System.out.println("2. Bank");
        System.out.println("3. COD");
    
        int metode = scanner.nextInt();
        Pembayaran pembayaran = null;
    
        switch (metode) {
            case 1:
                pembayaran = new QRIS("QRIS");
                break;
            case 2:
                pembayaran = new Bank("Bank");
                break;
            case 3:
                pembayaran = new COD("COD");
                break;
            default:
                System.out.println("Metode pembayaran tidak valid.");
                return;
        }
    
        if (pembayaran != null) {
            pembayaran.prosesPembayaran();
            
            // Kurangi stok barang berdasarkan isi keranjang
            for (Barang barang : keranjang.getBarangKeranjang().keySet()) {
                int jumlah = keranjang.getBarangKeranjang().get(barang);
                boolean stokDikurangi = listBarang.kurangiStok(barang.getNama(), jumlah);
                if (!stokDikurangi) {
                    System.out.println("Gagal mengurangi stok barang: " + barang.getNama());
                }
            }
    
            // Membuat invoice dan menambahkannya ke riwayat transaksi customer
            String invoice = "INV" + (customer.getHistory().size() + 1);
            customer.addInvoice(invoice);
            System.out.println("Pembayaran berhasil. Invoice: " + invoice);
    
            // Kosongkan keranjang
            keranjang.kosongkanKeranjang();
        } else {
            System.out.println("Pembayaran gagal dilakukan.");
        }
    }
    

    private void lihatHistoryBelanja() {
        for (String invoice : customer.getHistory()) {
            System.out.println(invoice);
        }
    }
}
