import java.io.*;
import java.util.ArrayList;

public class ListBarang {
    private ArrayList<Barang> barang;
    private final String fileName = "barang.txt";

    public ListBarang() {
        this.barang = new ArrayList<>();
        loadBarangFromFile();
    }

    private void loadBarangFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String idBarang = data[0];
                    String nama = data[1];
                    int stok = Integer.parseInt(data[2]);
                    double harga = Double.parseDouble(data[3]);
                    barang.add(new Barang(idBarang, nama, stok, harga));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file barang.txt");
        }
    }

    private void saveBarangToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Barang b : barang) {
                writer.write(b.getIdBarang() + "," + b.getNama() + "," + b.getStok() + "," + b.getHarga() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Gagal menulis ke file barang.txt");
        }
    }

    public void tambahBarang(Barang barangBaru) {
        barang.add(barangBaru);
        saveBarangToFile();
    }

    public void hapusBarang(String namaBarang) {
        barang.removeIf(b -> b.getNama().equalsIgnoreCase(namaBarang));
        saveBarangToFile();
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public Barang cariBarangById(String idBarang) {
        for (Barang barang : barang) {
            if (barang.getIdBarang().equalsIgnoreCase(idBarang)) {
                return barang;
            }
        }
        return null;
    }

    public void updateStokBarang(String idBarang, int stokBaru) {
        Barang barang = cariBarangById(idBarang);
        if (barang != null) {
            barang.kurangiStok(stokBaru);
            saveBarangToFile();
        }
    }

    public boolean isIdBarangExist(String idBarang) {
        for (Barang barang : barang) {
            if (barang.getIdBarang().equalsIgnoreCase(idBarang)) {
                return true;
            }
        }
        return false;
    }
}
