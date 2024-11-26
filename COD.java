public class COD extends Pembayaran {
    public COD(String id) {
        super(id); // Panggil konstruktor superclass
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran dengan COD berhasil.");
        System.out.println("ID Transaksi: " + getId());
    }
}
