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
        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel utama dengan layout null
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);

        // Judul MyCompany
        JLabel titleLabel = new JLabel("MyCompany");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 0, 205));
        titleLabel.setBounds(20, 10, 250, 30);

        // Label dan ComboBox untuk User Type
        JLabel typeLabel = new JLabel("Pilih User");
        typeLabel.setBounds(20, 50, 100, 25);
        
        userTypeCombo = new JComboBox<String>(new String[]{"Admin", "Customers"});
        userTypeCombo.setBounds(20, 75, 250, 25);

        // Username field dengan placeholder
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(20, 105, 100, 25);
        
        usernameField = new PlaceholderTextField("Masukkan username");
        usernameField.setBounds(20, 130, 250, 25);

        // Password field dengan placeholder
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(20, 160, 100, 25);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(20, 185, 250, 25);
        passwordField.setEchoChar((char)0);
        passwordField.setText("Masukkan password");
        passwordField.setForeground(Color.GRAY);
        
        // Menambahkan focus listener untuk password field
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
                    passwordField.setEchoChar((char)0);
                    passwordField.setText("Masukkan password");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(20, 220, 250, 35);

        // Tombol Login dan Cancel
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        loginButton.setPreferredSize(new Dimension(100, 25));
        cancelButton.setPreferredSize(new Dimension(100, 25));
        
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        // Menambahkan komponen ke panel utama
        mainPanel.add(titleLabel);
        mainPanel.add(typeLabel);
        mainPanel.add(userTypeCombo);
        mainPanel.add(userLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passLabel);
        mainPanel.add(passwordField);
        mainPanel.add(buttonPanel);

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
                                    // Tambahkan kode untuk membuka form admin di sini
                                } else if (userType.equals("Customers") && username.equals("customer") && password.equals("customer1")) {
                                    JOptionPane.showMessageDialog(null, "Login Customer Berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    // Tambahkan kode untuk membuka form customer di sini
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