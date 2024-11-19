import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Interface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeCombo;
    private JDialog loadingDialog;

    public Interface() {
        setTitle("Kelompok 11");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Mengatur agar layar fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        setLocationRelativeTo(null); // Jendela tetap di tengah saat tidak fullscreen
        setResizable(false);

        // Panel utama
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(173, 216, 230)); // Warna biru muda

        // Panel putih untuk form login
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setSize(300, 400);
        formPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true)); // Border

        // Menempatkan panel di tengah
        formPanel.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - formPanel.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - formPanel.getHeight()) / 2);

        // Judul Login
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, 20, 200, 30);

        // Label dan ComboBox untuk User Type
        JLabel typeLabel = new JLabel("Pilih User");
        typeLabel.setBounds(25, 70, 250, 25);

        userTypeCombo = new JComboBox<>(new String[]{"Admin", "Customers"});
        userTypeCombo.setBounds(25, 100, 250, 30);

        // Username field dengan placeholder
        JLabel userLabel = new JLabel("User Name");
        userLabel.setBounds(25, 140, 250, 25);

        usernameField = new PlaceholderTextField("Masukkan username");
        usernameField.setBounds(25, 165, 250, 30);

        // Password field dengan placeholder
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(25, 205, 250, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(25, 230, 250, 30);
        passwordField.setEchoChar((char) 0);
        passwordField.setText("Masukkan password");
        passwordField.setForeground(Color.GRAY);

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Masukkan password")) {
                    passwordField.setText("");
                    passwordField.setEchoChar('•');
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("Masukkan password");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });

        // Tombol Login dan Cancel
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(25, 280, 115, 30);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(160, 280, 115, 30);

        // Tambahkan semua elemen ke formPanel
        formPanel.add(titleLabel);
        formPanel.add(typeLabel);
        formPanel.add(userTypeCombo);
        formPanel.add(userLabel);
        formPanel.add(usernameField);
        formPanel.add(passLabel);
        formPanel.add(passwordField);
        formPanel.add(loginButton);
        formPanel.add(cancelButton);

        // Tambahkan formPanel ke mainPanel
        mainPanel.add(formPanel);

        // Membuat dialog loading
        loadingDialog = new JDialog(this, "Loading", true);
        loadingDialog.setSize(200, 100);
        loadingDialog.setLocationRelativeTo(null);
        loadingDialog.setLayout(new FlowLayout());
        JLabel loadingLabel = new JLabel("Logging in...");
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        loadingDialog.add(loadingLabel);
        loadingDialog.add(progressBar);

        // Event handler untuk tombol login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String username = usernameField.getText();
                final String password = new String(passwordField.getPassword());
                final String userType = (String) userTypeCombo.getSelectedItem();

                // Menampilkan dialog loading
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.setVisible(true);
                            }
                        });

                        try {
                            Thread.sleep(2000); // Simulasi loading selama 2 detik
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.setVisible(false);

                                // Validasi login
                                if (userType.equals("Admin") && username.equals("admin") && password.equals("admin1")) {
                                    JOptionPane.showMessageDialog(null, "Login Admin Berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    openMainApplication("Admin");
                                } else if (userType.equals("Customers") && username.equals("customer") && password.equals("customer1")) {
                                    JOptionPane.showMessageDialog(null, "Login Customer Berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    openMainApplication("Customer");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Username atau Password Salah!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        // Event handler untuk tombol cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setContentPane(mainPanel);
    }

    // Kelas PlaceholderTextField
    class PlaceholderTextField extends JTextField {
        private String placeholder;

        public PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder;

            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(placeholder)) {
                        setText("");
                        setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(placeholder);
                        setForeground(Color.GRAY);
                    }
                }
            });

            setText(placeholder);
            setForeground(Color.GRAY);
        }
    }

    // Membuka aplikasi utama
    private void openMainApplication(String userType) {
        JFrame mainApp = new JFrame(userType + " - Main Application");
        mainApp.setSize(800, 600);
        mainApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainApp.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Selamat datang, " + userType, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainApp.add(headerLabel, BorderLayout.NORTH);

        JLabel contentLabel = new JLabel("Ini adalah aplikasi utama Anda.", SwingConstants.CENTER);
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        mainApp.add(contentLabel, BorderLayout.CENTER);

        mainApp.setLocationRelativeTo(null);
        mainApp.setVisible(true);

        this.dispose(); // Menutup login frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Interface login = new Interface();
                login.setVisible(true);
            }
        });
    }
}
