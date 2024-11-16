import java.util.ArrayList;
import java.util.List;

class Barang {
  private String nama;
  private double harga;

  public Barang(String nama, double harga) {
    this.nama = nama;
    this.harga = harga;
  }

  public String getNama() {
    return nama;
  }

  public double getHarga() {
    return harga;
  }

  @Override 
  public String toString() {
    return nama + " - Rp" + harga;
  }
}

class Keranjang {
  private List<Barang> barangList;

  public Keranjang() {
    barangList = new ArrayList<>();
  }

  public void tambahBarang(Barang barang) {
    barangList.add(barang);
  }

  public List<Barang> getBarangList() {
    return barangList;
  }
}

public class AddToCart {
  private Keranjang keranjang;

  public AddToCart() {
    keranjang = new Keranjang();
  }

  public void tambahBarang(String nama, double harga) {
    Barang barang = new Barang(nama, harga);
    keranjang.tambahBarang(barang);
    System.out.println(barang + " berhasil ditambahkan ke keranjang.");
  }

  public List<Barang> getBarangDalamKeranjang() {
    return keranjang.getBarangList();
  }
}
