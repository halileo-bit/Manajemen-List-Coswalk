import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminPage extends JFrame {
    private JTable tablePeserta;
    private DefaultTableModel tableModel;

    public AdminPage() {
        initComponents();
        setTitle("Admin Panel - Coswalk Management System");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 123, 255));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        // Left side - Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);

        JLabel lblIcon = new JLabel("👑");
        lblIcon.setFont(new Font("Segoe UI", Font.PLAIN, 28));

        JLabel lblTitle = new JLabel("ADMIN PANEL");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);

        titlePanel.add(lblIcon);
        titlePanel.add(lblTitle);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        // Right side - Info
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.setOpaque(false);

        JLabel lblTotal = new JLabel("Total Peserta: " + DatabaseManager.getTotalPeserta());
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel lblDb = new JLabel("Database: database.txt");
        lblDb.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDb.setForeground(Color.WHITE);
        lblDb.setHorizontalAlignment(SwingConstants.RIGHT);

        infoPanel.add(lblTotal);
        infoPanel.add(lblDb);
        headerPanel.add(infoPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        // Label judul tabel
        JLabel lblTableTitle = new JLabel("📋 DAFTAR PESERTA COSWALK");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPanel.add(lblTableTitle, BorderLayout.NORTH);

        // Tabel data peserta
        String[] columns = {"No", "Nama Cosplayer", "Nama Karakter", "Asal Series"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablePeserta = new JTable(tableModel);
        tablePeserta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablePeserta.setRowHeight(35);
        tablePeserta.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablePeserta.getTableHeader().setBackground(new Color(52, 58, 64));
        tablePeserta.getTableHeader().setForeground(Color.WHITE);
        tablePeserta.setSelectionBackground(new Color(220, 240, 255));

        // Enable sorting
        tablePeserta.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(tablePeserta);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218), 1));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        // Button Hapus Data
        JButton btnHapus = new JButton("🗑️ Hapus Data Peserta");
        btnHapus.setBackground(new Color(220, 53, 69));
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnHapus.setPreferredSize(new Dimension(200, 45));
        btnHapus.setToolTipText("Buka halaman untuk menghapus data peserta");
        btnHapus.addActionListener(e -> openHapusPage());

        // Button Refresh
        JButton btnRefresh = new JButton("🔄 Refresh Data");
        btnRefresh.setBackground(new Color(108, 117, 125));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setPreferredSize(new Dimension(150, 45));
        btnRefresh.addActionListener(e -> refreshTable());

        // Button Backup Database
        JButton btnBackup = new JButton("💾 Backup Database");
        btnBackup.setBackground(new Color(255, 193, 7));
        btnBackup.setForeground(Color.BLACK);
        btnBackup.setPreferredSize(new Dimension(180, 45));
        btnBackup.addActionListener(e -> {
            DatabaseManager.backupDatabase();
            JOptionPane.showMessageDialog(this,
                    "Backup database berhasil dibuat!\nFile: database_backup.txt",
                    "Backup Success", JOptionPane.INFORMATION_MESSAGE);
        });

        // Button Logout
        JButton btnLogout = new JButton("🚪 Logout");
        btnLogout.setBackground(new Color(40, 167, 69));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setPreferredSize(new Dimension(150, 45));
        btnLogout.addActionListener(e -> logout());

        buttonPanel.add(btnHapus);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnBackup);
        buttonPanel.add(btnLogout);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        int i = 1;
        for (Cosplayer c : DatabaseManager.getDaftarPeserta()) {
            tableModel.addRow(new Object[]{
                    i++,
                    c.getNamaCosplayer(),
                    c.getNamaKarakter(),
                    c.getAsalSeries()
            });
        }
    }

    private void openHapusPage() {
        dispose();
        new HapusDataPage();
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin logout dari Admin Panel?", "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginPage();
        }
    }
}