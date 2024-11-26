import java.util.Scanner;

public class CustomerDriver {
    private Customer customer;
    private ListBarang listBarang;
    private Scanner scanner;

    public CustomerDriver(Customer customer, ListBarang listBarang) {
        this.customer = customer;
        this.listBarang = listBarang;
        this.scanner = new Scanner(System.in); // Initialize scanner once
    }

    public void start() {
        while (true) {
            System.out.println("=== Menu Customer ===");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah Ke Keranjang");
            System.out.println("3. Checkout");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (pilihan) {
                case 1:
                    lihatBarang();
                    break;
                case 2:
                    tambahKeKeranjang();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    System.out.println("Keluar dari menu customer.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void lihatBarang() {
        System.out.println("=== Daftar Barang ===");
        // Menampilkan header tabel
        System.out.println("+-----------+----------------------+----------+-------------+");
        System.out.printf("| %-9s | %-20s | %-8s | %-11s |\n", "ID Barang", "Nama Barang", "Stok", "Harga");
        System.out.println("+-----------+----------------------+----------+-------------+");
        
        // Menampilkan data barang dalam tabel
        for (Barang barang : listBarang.getBarang()) {
            System.out.printf("| %-9s | %-20s | %-8d | %-11.2f |\n",
                    barang.getIdBarang(),
                    barang.getNama(),
                    barang.getStok(),
                    barang.getHarga());
        }
        
        // Menampilkan baris penutup tabel
        System.out.println("+-----------+----------------------+----------+-------------+");
    }

    private void tambahKeKeranjang() {
        System.out.print("Masukkan ID Barang: ");
        String idBarang = scanner.nextLine();
        Barang barang = listBarang.cariBarangById(idBarang);
        if (barang != null) {
            System.out.print("Masukkan jumlah barang: ");
            int jumlah = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (barang.getStok() >= jumlah) {
                customer.tambahKeKeranjang(barang, jumlah);
                barang.kurangiStok(jumlah);
                System.out.println("Barang berhasil ditambahkan ke keranjang.");
            } else {
                System.out.println("Stok tidak mencukupi.");
            }
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    private void checkout() {
        // Membuat invoice baru untuk keranjang customer
        Invoice invoice = new Invoice(customer.getKeranjang());

        // Mencetak invoice
        invoice.printInvoice();

        // Konfirmasi pembayaran
        System.out.print("Apakah Anda ingin lanjut ke pembayaran? (y/n): ");
        String konfirmasi = scanner.nextLine();
        
        if (konfirmasi.equalsIgnoreCase("y")) {
            tampilkanMetodePembayaran(invoice);
        } else {
            System.out.println("Checkout dibatalkan.");
        }
    }

    private void tampilkanMetodePembayaran(Invoice invoice) {
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. QRIS");
        System.out.println("2. Bank Transfer");
        System.out.println("3. COD");
        System.out.print("Pilih metode pembayaran: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Pembayaran pembayaran = null;

        // Tentukan metode pembayaran berdasarkan pilihan pengguna
        switch (pilihan) {
            case 1:
                pembayaran = new QRIS(invoice.getId());  // Create a QRIS payment object
                break;
            case 2:
                pembayaran = new Bank(invoice.getId());  // Create a BankTransfer payment object
                break;
            case 3:
                pembayaran = new COD(invoice.getId());  // Create a COD payment object
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        // Process the payment using the selected payment method
        pembayaran.prosesPembayaran();

        // Set metode pembayaran pada invoice
        invoice.setMetodePembayaran(pembayaran.getClass().getSimpleName()); // Save payment method name to invoice

        // Simulate saving the transaction history after successful payment
        invoice.saveRiwayatTransaksi();

        // After payment, clear the cart
        customer.kosongkanKeranjang();
    }
}
