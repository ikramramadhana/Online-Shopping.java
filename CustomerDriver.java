import java.util.Scanner;

public class CustomerDriver {
    private Customer customer;
    private ListBarang listBarang;
    private Transaksi transaksi;
    private Scanner scanner;

    public CustomerDriver(Customer customer, ListBarang listBarang) {
        this.customer = customer;
        this.listBarang = listBarang;
        this.scanner = new Scanner(System.in);
        this.transaksi = null; 
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
            scanner.nextLine(); 

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
        System.out.println("+-----------+----------------------+----------+-------------+");
        System.out.printf("| %-9s | %-20s | %-8s | %-11s |\n", "ID Barang", "Nama Barang", "Stok", "Harga");
        System.out.println("+-----------+----------------------+----------+-------------+");

        // Menampilkan barang dalam format tabel
        for (Barang barang : listBarang.getBarang()) {
            System.out.printf("| %-9s | %-20s | %-8d | %-11.2f |\n",
                    barang.getIdBarang(), barang.getNama(), barang.getStok(), barang.getHarga());
        }

        System.out.println("+-----------+----------------------+----------+-------------+");
    }

    private void tambahKeKeranjang() {
        System.out.print("Masukkan ID Barang: ");
        String idBarang = scanner.nextLine();
        Barang barang = listBarang.cariBarangById(idBarang);
        if (barang != null) {
            System.out.print("Masukkan jumlah barang: ");
            int jumlah = scanner.nextInt();
            scanner.nextLine(); // Consume newline
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
        if (customer.getKeranjang().isEmpty()) {
            System.out.println("Keranjang kosong, tidak dapat melakukan checkout.");
            return;
        }

        // Membuat transaksi berdasarkan keranjang customer
        transaksi = new Transaksi(customer, customer.getKeranjang());

        // Mencetak transaksi
        transaksi.printTransaksi();

        // Membuat invoice dari transaksi
        Invoice invoice = new Invoice(transaksi);

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
        scanner.nextLine(); // Consume newline

        Pembayaran pembayaran = null;

        // Tentukan metode pembayaran berdasarkan pilihan
        switch (pilihan) {
            case 1:
                pembayaran = new QRIS(invoice.getId());
                break;
            case 2:
                pembayaran = new Bank(invoice.getId());
                break;
            case 3:
                pembayaran = new COD(invoice.getId());
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        // Proses pembayaran
        pembayaran.prosesPembayaran();
        invoice.setMetodePembayaran(pembayaran.getClass().getSimpleName());
        invoice.saveRiwayatTransaksi();
        customer.kosongkanKeranjang();
    }
}
