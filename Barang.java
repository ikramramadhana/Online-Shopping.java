public class Barang {
    private String idBarang;
    private String nama;
    private int stok;
    private double harga;
    private int jumlah;  // Jumlah barang di keranjang

    public Barang(String idBarang, String nama, int stok, double harga) {
        this.idBarang = idBarang;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.jumlah = 0;  // Inisialisasi jumlah barang di keranjang
    }

    public String getIdBarang() {
        return idBarang;
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public double getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (jumlah > 0 && this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak cukup atau jumlah yang dimasukkan tidak valid.");
        }
    }

    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok += jumlah;
        } else {
            System.out.println("Jumlah stok yang dimasukkan tidak valid.");
        }
    }

    @Override
    public String toString() {
        return "Barang{idBarang='" + idBarang + "', nama='" + nama + "', stok=" + stok + ", harga=" + harga + ", jumlah=" + jumlah + '}';
    }
}
