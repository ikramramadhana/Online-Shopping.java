// // // // // public class Invoice {
// // // // //     private String idBarang;
// // // // //     private String namaBarang;
// // // // //     private int jumlah;
// // // // //     private double totalHarga;

// // // // //     public Invoice(String idBarang, String namaBarang, int jumlah, double totalHarga) {
// // // // //         this.idBarang = idBarang;
// // // // //         this.namaBarang = namaBarang;
// // // // //         this.jumlah = jumlah;
// // // // //         this.totalHarga = totalHarga;
// // // // //     }

// // // // //     public String getIdBarang() {
// // // // //         return idBarang;
// // // // //     }

// // // // //     public String getNamaBarang() {
// // // // //         return namaBarang;
// // // // //     }

// // // // //     public int getJumlah() {
// // // // //         return jumlah;
// // // // //     }

// // // // //     public double getTotalHarga() {
// // // // //         return totalHarga;
// // // // //     }

// // // // //     public void printInvoice() {
// // // // //         System.out.println("=== Invoice Pembelian ===");
// // // // //         System.out.printf("ID Barang: %s\n", idBarang);
// // // // //         System.out.printf("Nama Barang: %s\n", namaBarang);
// // // // //         System.out.printf("Jumlah: %d\n", jumlah);
// // // // //         System.out.printf("Total Harga: %.2f\n", totalHarga);
// // // // //         System.out.println("=========================");
// // // // //     }
// // // // // }
// // // public class Invoice {
// // //     private String idBarang;
// // //     private String namaBarang;
// // //     private int jumlah;
// // //     private double totalHarga;

// // //     public Invoice(String idBarang, String namaBarang, int jumlah, double totalHarga) {
// // //         this.idBarang = idBarang;
// // //         this.namaBarang = namaBarang;
// // //         this.jumlah = jumlah;
// // //         this.totalHarga = totalHarga;
// // //     }

// // //     public void printInvoice() {
// // //         System.out.println("=== Invoice Pembelian ===");
// // //         System.out.printf("ID Barang: %s\n", idBarang);
// // //         System.out.printf("Nama Barang: %s\n", namaBarang);
// // //         System.out.printf("Jumlah: %d\n", jumlah);
// // //         System.out.printf("Total Harga: %.2f\n", totalHarga);
// // //         System.out.println("=========================");
// // //     }
// // // }
// // // // public class Invoice {
// // // //     private String idBarang;
// // // //     private String namaBarang;
// // // //     private int jumlah;
// // // //     private double totalHarga;

// // // //     public Invoice(String idBarang, String namaBarang, int jumlah, double totalHarga) {
// // // //         this.idBarang = idBarang;
// // // //         this.namaBarang = namaBarang;
// // // //         this.jumlah = jumlah;
// // // //         this.totalHarga = totalHarga;
// // // //     }

// // // //     public void printInvoice() {
// // // //         // Tampilan awal Invoice
// // // //         System.out.println("=== Invoice Pembelian ===");
// // // //         System.out.println("+----------------+------------------------+--------+--------------+");
// // // //         System.out.printf("| %-14s | %-22s | %-6s | %-12s |\n", "ID Barang", "Nama Barang", "Jumlah", "Total Harga");
// // // //         System.out.println("+----------------+------------------------+--------+--------------+");
        
// // // //         // Menampilkan detail barang yang dibeli
// // // //         System.out.printf("| %-14s | %-22s | %-6d | %-12.2f |\n", idBarang, namaBarang, jumlah, totalHarga);
        
// // // //         // Tampilan akhir Invoice
// // // //         System.out.println("+----------------+------------------------+--------+--------------+");
// // // //         System.out.println("=========================");
// // // //     }
// // // // }
// // import java.util.List;

// // public class Invoice {
// //     private List<Barang> keranjang;
// //     private double totalHarga;

// //     public Invoice(List<Barang> keranjang) {
// //         this.keranjang = keranjang;
// //         this.totalHarga = 0;
// //     }

// //     public void printInvoice() {
// //         System.out.println("=== Invoice Pembelian ===");
// //         for (Barang barang : keranjang) {
// //             double subtotal = barang.getHarga() * barang.getJumlah();
// //             totalHarga += subtotal;
// //             System.out.printf("ID Barang: %s\n", barang.getIdBarang());
// //             System.out.printf("Nama Barang: %s\n", barang.getNama());
// //             System.out.printf("Jumlah: %d\n", barang.getJumlah());
// //             System.out.printf("Total Harga: %.2f\n", subtotal);
// //             System.out.println("=========================");
// //         }

// //         System.out.printf("Total Pembelian: %.2f\n", totalHarga);
// //         System.out.println("=========================");
// //     }
// // }
// import java.util.List;

// public class Invoice {
//     private List<Barang> keranjang;
//     private double totalHarga;

//     public Invoice(List<Barang> keranjang) {
//         this.keranjang = keranjang;
//         this.totalHarga = 0;
//     }

//     // Menambahkan metode getTotal untuk mendapatkan total harga
//     public double getTotal() {
//         return totalHarga;
//     }

//     public void printInvoice() {
//         System.out.println("=== Invoice Pembelian ===");
//         for (Barang barang : keranjang) {
//             double subtotal = barang.getHarga() * barang.getJumlah();
//             totalHarga += subtotal;
//             System.out.printf("ID Barang: %s\n", barang.getIdBarang());
//             System.out.printf("Nama Barang: %s\n", barang.getNama());
//             System.out.printf("Jumlah: %d\n", barang.getJumlah());
//             System.out.printf("Total Harga: %.2f\n", subtotal);
//             System.out.println("=========================");
//         }

//         System.out.printf("Total Pembelian: %.2f\n", totalHarga);
//         System.out.println("=========================");
//     }
// }
// import java.util.List;
// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.BufferedReader;
// import java.io.FileReader;

// public class Invoice {
//     private List<Barang> keranjang;
//     private double totalHarga;
//     private String metodePembayaran; // Menambahkan field untuk metode pembayaran

//     public Invoice(List<Barang> keranjang) {
//         this.keranjang = keranjang;
//         this.totalHarga = 0;
//         this.metodePembayaran = ""; // Inisialisasi metode pembayaran
//     }

//     // Menambahkan metode set untuk metode pembayaran
//     public void setMetodePembayaran(String metodePembayaran) {
//         this.metodePembayaran = metodePembayaran;
//     }

//     // Menambahkan metode getTotal untuk mendapatkan total harga
//     public double getTotal() {
//         return totalHarga;
//     }

//     public void printInvoice() {
//         System.out.println("=== Invoice Pembelian ===");
//         for (Barang barang : keranjang) {
//             double subtotal = barang.getHarga() * barang.getJumlah();
//             totalHarga += subtotal;
//             System.out.printf("ID Barang: %s\n", barang.getIdBarang());
//             System.out.printf("Nama Barang: %s\n", barang.getNama());
//             System.out.printf("Jumlah: %d\n", barang.getJumlah());
//             System.out.printf("Total Harga: %.2f\n", subtotal);
//             System.out.println("=========================");
//         }

//         System.out.printf("Total Pembelian: %.2f\n", totalHarga);
//         System.out.println("=========================");
//     }

//     // Metode untuk menyimpan riwayat transaksi ke file
//     public void saveRiwayatTransaksi() {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter("riwayat.txt", true))) {
//             int nomorTransaksi = getNomorTransaksi();  // Ambil nomor transaksi
//             writer.write("Transaksi #" + nomorTransaksi + " = ");
            
//             // Menuliskan nama barang
//             for (Barang barang : keranjang) {
//                 writer.write(barang.getNama() + ", ");
//             }
            
//             // Menuliskan metode pembayaran
//             writer.write("Metode Pembayaran: " + metodePembayaran + ", ");
            
//             // Menuliskan total pembelian
//             double totalPembelian = 0.0;
//             for (Barang barang : keranjang) {
//                 totalPembelian += barang.getHarga() * barang.getJumlah();
//             }
//             writer.write("Total Pembelian: " + totalPembelian + "\n");
//             writer.write("=======================================\n");
//             System.out.println("Transaksi berhasil disimpan.");
//         } catch (IOException e) {
//             System.out.println("Gagal menulis riwayat transaksi: " + e.getMessage());
//         }
//     }    

//     // Mendapatkan nomor transaksi berdasarkan isi file
//     private int getNomorTransaksi() {
//         int nomorTransaksi = 1;
//         try (BufferedReader reader = new BufferedReader(new FileReader("riwayat.txt"))) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 if (line.startsWith("Transaksi #")) {
//                     String[] parts = line.split(":");
//                     String transaksiId = parts[0].replaceAll("[^0-9]", "");
//                     int id = Integer.parseInt(transaksiId);
//                     if (id >= nomorTransaksi) {
//                         nomorTransaksi = id + 1;
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("Gagal membaca riwayat transaksi: " + e.getMessage());
//         }
//         return nomorTransaksi;
//     }
// }

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Invoice {
    private List<Barang> keranjang;
    private double totalHarga;
    private String metodePembayaran; // Menambahkan field untuk metode pembayaran
    private String id; // Field untuk ID Invoice

    // Constructor untuk Invoice yang menerima list barang (keranjang)
    public Invoice(List<Barang> keranjang) {
        this.keranjang = keranjang;
        this.totalHarga = 0;
        this.metodePembayaran = ""; // Inisialisasi metode pembayaran
        this.id = "INV" + System.currentTimeMillis(); // Menghasilkan ID unik berdasarkan waktu
    }

    // Menambahkan metode set untuk metode pembayaran
    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    // Menambahkan metode getTotal untuk mendapatkan total harga
    public double getTotal() {
        return totalHarga;
    }

    // Menambahkan getId untuk mendapatkan ID Invoice
    public String getId() {
        return id;
    }

    public void printInvoice() {
        System.out.println("=== Invoice Pembelian ===");
        for (Barang barang : keranjang) {
            double subtotal = barang.getHarga() * barang.getJumlah();
            totalHarga += subtotal;
            System.out.printf("ID Barang: %s\n", barang.getIdBarang());
            System.out.printf("Nama Barang: %s\n", barang.getNama());
            System.out.printf("Jumlah: %d\n", barang.getJumlah());
            System.out.printf("Total Harga: %.2f\n", subtotal);
            System.out.println("=========================");
        }

        System.out.printf("Total Pembelian: %.2f\n", totalHarga);
        System.out.println("=========================");
    }

    // Metode untuk menyimpan riwayat transaksi ke file
    public void saveRiwayatTransaksi() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("riwayat.txt", true))) {
            int nomorTransaksi = getNomorTransaksi();  // Ambil nomor transaksi
            writer.write("Transaksi #" + nomorTransaksi + " = ");
            
            // Menuliskan nama barang
            for (Barang barang : keranjang) {
                writer.write(barang.getNama() + ", ");
            }
            
            // Menuliskan metode pembayaran
            writer.write("Metode Pembayaran: " + metodePembayaran + ", ");
            
            // Menuliskan total pembelian
            double totalPembelian = 0.0;
            for (Barang barang : keranjang) {
                totalPembelian += barang.getHarga() * barang.getJumlah();
            }
            writer.write("Total Pembelian: " + totalPembelian + "\n");
            writer.write("=======================================\n");
            System.out.println("Transaksi berhasil disimpan.");
        } catch (IOException e) {
            System.out.println("Gagal menulis riwayat transaksi: " + e.getMessage());
        }
    }

    // Mendapatkan nomor transaksi berdasarkan isi file
    private int getNomorTransaksi() {
        int nomorTransaksi = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader("riwayat.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Transaksi #")) {
                    String[] parts = line.split(":");
                    String transaksiId = parts[0].replaceAll("[^0-9]", "");
                    int id = Integer.parseInt(transaksiId);
                    if (id >= nomorTransaksi) {
                        nomorTransaksi = id + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca riwayat transaksi: " + e.getMessage());
        }
        return nomorTransaksi;
    }
}
