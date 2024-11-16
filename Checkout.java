import java.util.List;

public class Checkout {
  private Keranjang keranjang;

  public Checkout(Keranjang keranjang) {
    this.keranjang = keranjang;
  }

  public void lakukanCheckout() {
    list<Barang> barangList = keranjang.getBarangList();
    if (barangList.isEmpty()) {
      System.out.println("Keranjang kosong. Tidak ada yang bisa di-checkout.");
      return;
    }

    System.out.println(Barang dalam keranjang: ");
    double totalHarga = 0;
    for (Barang barang : barangList) {
      System.out.println("- " + barang);
      totalHarga += barang.getHarga();
    }

    System.out.println("Total harga: Rp" + totalHarga);
    System.out.println("Checkout selesai.");
  }
}
