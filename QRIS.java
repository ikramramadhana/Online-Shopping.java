public class QRIS extends Pembayaran {
    public QRIS(String id) {
        super(id); // Panggil konstruktor superclass
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran menggunakan QRIS berhasil.");
        System.out.println("ID Transaksi: " + getId());
    }
}
