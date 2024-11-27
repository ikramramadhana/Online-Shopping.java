import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> barang;

    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    public void tambahBarang(Barang barangBaru) {
        barang.add(barangBaru);
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public void kosongkanKeranjang() {
        barang.clear();
    }
}
