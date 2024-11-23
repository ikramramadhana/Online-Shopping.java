import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class Barang
class Barang {
  private String nama;
  private double harga;

  public Barang (String nama, double harga) {
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
    return "Barang{" + "nama='" + nama + '\'' + ", harga=" + harga + '}';
  }
}

// Class Transaksi
class Transaksi {
  private String idTransaksi;
  private List<Barang> barangList;
  private double totalHarga;

  public Transaksi (String idTransaksi, List<Barang> barangList) {
    this.idTransaksi = idTransaksi;
    this.barangList = barangList;
    this.totalHarga = hitungTotalHarga();
  }

  private double hitungTotalHarga() {
    double total = 0;
    for (Barang barang : barangList) {
      total += barang.getHarga();
    }
    return total;
  }

  @Override
  public String toString() {
    return "Transaksi{" +
            "idTransaksi='" + idTransaksi + '\'' +
            ", barangList=" + barangList +
            ", totalHarga=" + totalHarga +
            '}';
  }
}

// Class AdminDriver
public class AdminDriver {
  private List<Barang> listBarang;
    private List<Transaksi> listTransaksi;

    public AdminDriver() {
        this.listBarang = new ArrayList<>();
        this.listTransaksi = new ArrayList<>();
    }

    // tambah barang
    public void tambahBarang(String nama, double harga) {
        Barang barang = new Barang(nama, harga);
        listBarang.add(barang);
        System.out.println("Barang berhasil ditambahkan: " + barang);
    }

    // hapus barang
    public void hapusBarang(String namaBarang) {
        boolean isRemoved = listBarang.removeIf(barang -> barang.getNama().equalsIgnoreCase(namaBarang));
        if (isRemoved) {
            System.out.println("Barang dengan nama '" + namaBarang + "' berhasil dihapus.");
        } else {
            System.out.println("Barang dengan nama '" + namaBarang + "' tidak ditemukan.");
        }
    }

    // edit barang
    public void editBarang(String namaLama, String namaBaru, double hargaBaru) {
        for (Barang barang : listBarang) {
            if (barang.getNama().equalsIgnoreCase(namaLama)) {
                listBarang.remove(barang);
                listBarang.add(new Barang(namaBaru, hargaBaru));
                System.out.println("Barang berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Barang dengan nama '" + namaLama + "' tidak ditemukan.");
    }

    // lihat barang
    public void lihatBarang() {
        if (listBarang.isEmpty()) {
            System.out.println("Daftar barang kosong.");
        } else {
            System.out.println("Daftar Barang:");
            for (Barang barang : listBarang) {
                System.out.println("- " + barang);
            }
        }
    }

    // terima transaksi
    public void terimaTransaksi(String idTransaksi, List<Barang> barangList) {
        Transaksi transaksi = new Transaksi(idTransaksi, barangList);
        listTransaksi.add(transaksi);
        System.out.println("Transaksi berhasil diterima: " + transaksi);
    }

    // lihat transaksi
    public void lihatTransaksi() {
        if (listTransaksi.isEmpty()) {
            System.out.println("Tidak ada transaksi.");
        } else {
            System.out.println("Daftar Transaksi:");
            for (Transaksi transaksi : listTransaksi) {
                System.out.println("- " + transaksi);
            }
        }
    }

    public void menuCLI() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Lihat Barang");
            System.out.println("5. Kelola Transaksi");
            System.out.println("6. Lihat Transaksi");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Masukkan nama barang: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan harga barang: ");
                    double harga = scanner.nextDouble();
                    tambahBarang(nama, harga);
                }
                case 2 -> {
                    System.out.print("Masukkan nama barang yang ingin dihapus: ");
                    String nama = scanner.nextLine();
                    hapusBarang(nama);
                }
                case 3 -> {
                    System.out.print("Masukkan nama barang yang ingin diedit: ");
                    String namaLama = scanner.nextLine();
                    System.out.print("Masukkan nama baru: ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan harga baru: ");
                    double hargaBaru = scanner.nextDouble();
                    editBarang(namaLama, namaBaru, hargaBaru);
                }
                case 4 -> lihatBarang();
                case 5 -> {
                    System.out.print("Masukkan ID Transaksi: ");
                    String idTransaksi = scanner.nextLine();
                    List<Barang> barangTransaksi = new ArrayList<>();
                    System.out.println("Masukkan barang untuk transaksi (ketik 'done' jika selesai):");
                    while (true) {
                        System.out.print("Nama barang: ");
                        String namaBarang = scanner.nextLine();
                        if (namaBarang.equalsIgnoreCase("done")) break;

                        Barang barang = listBarang.stream()
                                .filter(b -> b.getNama().equalsIgnoreCase(namaBarang))
                                .findFirst()
                                .orElse(null);

                        if (barang != null) {
                            barangTransaksi.add(barang);
                        } else {
                            System.out.println("Barang tidak ditemukan.");
                        }
                    }
                    terimaTransaksi(idTransaksi, barangTransaksi);
                }
                case 6 -> lihatTransaksi();
                case 7 -> {
                    exit = true;
                    System.out.println("Keluar dari aplikasi.");
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        AdminDriver adminDriver = new AdminDriver();
        adminDriver.menuCLI();
    }
}




  

  


