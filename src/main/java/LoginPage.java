import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private static final String ADMIN_PASSWORD = "p";
    private JPasswordField txtPassword;
    private JRadioButton rbAdmin, rbPeserta;

    public LoginPage() {
        initComponents();
        setTitle("Login - Coswalk Management System");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 123, 255));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("COSWALK MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Sistem Database Peserta Cosplay");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(Color.WHITE);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(lblTitle);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(lblSubtitle);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel lblDbInfo = new JLabel("Database: " + DatabaseManager.getDatabaseInfo());
        lblDbInfo.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblDbInfo.setForeground(new Color(108, 117, 125));
        formPanel.add(lblDbInfo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel lblRole = new JLabel("Login sebagai:");
        lblRole.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblRole, gbc);

        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        rolePanel.setBackground(Color.WHITE);

        ButtonGroup roleGroup = new ButtonGroup();
        rbAdmin = new JRadioButton("Admin");
        rbPeserta = new JRadioButton("Peserta");
        rbPeserta.setSelected(true);

        rbAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rbPeserta.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        roleGroup.add(rbAdmin);
        roleGroup.add(rbPeserta);

        rolePanel.add(rbAdmin);
        rolePanel.add(rbPeserta);

        gbc.gridx = 1;
        formPanel.add(rolePanel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblPass = new JLabel("Password Admin:");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblPass, gbc);

        txtPassword = new JPasswordField(15);
        txtPassword.setEnabled(false);
        gbc.gridx = 1;
        formPanel.add(txtPassword, gbc);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(40, 167, 69));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(120, 40));
        btnLogin.addActionListener(e -> login());

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnLogin, gbc);

        rbAdmin.addActionListener(e -> {
            txtPassword.setEnabled(true);
            txtPassword.requestFocus();
        });
        rbPeserta.addActionListener(e -> {
            txtPassword.setEnabled(false);
            txtPassword.setText("");
        });

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(248, 249, 250));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(mainPanel);
    }

    private void login() {
        if (rbAdmin.isSelected()) {
            String password = new String(txtPassword.getPassword());
            if (password.equals(ADMIN_PASSWORD)) {
                JOptionPane.showMessageDialog(this,
                        "Login berhasil sebagai Admin!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new AdminPage();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Password salah!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                txtPassword.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Login berhasil sebagai Peserta!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new PesertaPage();
        }
    }
}