public class Bank extends Pembayaran {
    public Bank(String id) {
        super(id); // Panggil konstruktor superclass
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran menggunakan Bank berhasil.");
        System.out.println("ID Transaksi: " + getId());
    }
}
