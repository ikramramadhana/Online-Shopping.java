public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity; // Tambahkan atribut quantity

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter untuk ID
    public int getId() {
        return id;
    }

    // Getter dan Setter untuk name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter dan Setter untuk price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter dan Setter untuk quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + name + ", Harga: Rp " + price + ", Stok: " + quantity;
    }
}
