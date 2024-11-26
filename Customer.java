import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String username;
    private List<Barang> keranjang;

    public Customer(String username) {
        this.username = username;
        this.keranjang = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Barang> getKeranjang() {
        return keranjang;
    }

    public void tambahKeKeranjang(Barang barang, int jumlah) {
        for (Barang b : keranjang) {
            if (b.getIdBarang().equals(barang.getIdBarang())) {
                b.setJumlah(b.getJumlah() + jumlah);
                return;
            }
        }
        barang.setJumlah(jumlah);
        keranjang.add(barang);
    }

    public void kosongkanKeranjang() {
        keranjang.clear();
    }
}
