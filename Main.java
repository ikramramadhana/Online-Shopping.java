import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListBarang listBarang = new ListBarang();

        // Dummy data untuk username dan password
        String adminUsername = "admin";
        String adminPassword = "admin123";
        String customerUsername = "customer";
        String customerPassword = "cust123";

        boolean running = true;

        while (running) {
            try {
                System.out.println("=== Sistem Login ===");
                System.out.println("1. Login");
                System.out.println("2. Keluar");
                System.out.print("Pilih opsi: ");
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Buang sisa newline

                if (pilihan == 1) {
                    // Proses login
                    System.out.println("=== Login ===");
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    if (username.equals(adminUsername) && password.equals(adminPassword)) {
                        // Login sebagai admin
                        System.out.println("Login berhasil sebagai Admin.");
                        Admin admin = new Admin(username); // Menggunakan username sebagai ID
                        AdminDriver adminDriver = new AdminDriver(admin, listBarang);
                        adminDriver.start(); // Setelah selesai, kembali ke menu awal
                    } else if (username.equals(customerUsername) && password.equals(customerPassword)) {
                        // Login sebagai customer
                        System.out.println("Login berhasil sebagai Customer.");
                        Customer customer = new Customer(username); // Menggunakan username sebagai ID
                        CustomerDriver customerDriver = new CustomerDriver(customer, listBarang);
                        customerDriver.start(); // Setelah selesai, kembali ke menu awal
                    } else {
                        System.out.println("Username atau password salah!");
                    }
                } else if (pilihan == 2) {
                    // Keluar dari sistem
                    System.out.println("Keluar dari sistem. Sampai jumpa!");
                    running = false; // Mengakhiri *loop*
                } else {
                    // Opsi tidak valid
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (Exception e) {
                // Menangkap kesalahan input (seperti memasukkan huruf saat diminta angka)
                System.out.println("Input tidak valid. Harap masukkan angka!");
                scanner.nextLine(); // Membersihkan input buffer
            }
        }

        scanner.close();
    }
}
