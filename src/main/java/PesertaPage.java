import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PesertaPage extends JFrame {
    private JTable tablePeserta;
    private DefaultTableModel tableModel;

    public PesertaPage() {
        initComponents();
        setTitle("Peserta Panel - Coswalk Management System");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(40, 167, 69));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);

        JLabel lblTitle = new JLabel("PANEL PESERTA");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);

        titlePanel.add(lblTitle);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.setOpaque(false);

        JLabel lblTotal = new JLabel("Total Peserta: " + DatabaseManager.getTotalPeserta());
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        infoPanel.add(lblTotal);
        headerPanel.add(infoPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        JLabel lblTableTitle = new JLabel("DAFTAR PESERTA COSWALK");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPanel.add(lblTableTitle, BorderLayout.NORTH);

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
        tablePeserta.getTableHeader().setBackground(new Color(40, 167, 69));
        tablePeserta.getTableHeader().setForeground(Color.WHITE);
        tablePeserta.setSelectionBackground(new Color(230, 255, 237));

        tablePeserta.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(tablePeserta);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218), 1));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnTambah = new JButton("Tambah Data Peserta");
        btnTambah.setBackground(new Color(0, 123, 255));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTambah.setPreferredSize(new Dimension(200, 45));
        btnTambah.addActionListener(e -> openTambahPage());

        JButton btnRefresh = new JButton("Refresh Data");
        btnRefresh.setBackground(new Color(108, 117, 125));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setPreferredSize(new Dimension(150, 45));
        btnRefresh.addActionListener(e -> refreshTable());

        JButton btnSearch = new JButton("Cari Data");
        btnSearch.setBackground(new Color(255, 193, 7));
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setPreferredSize(new Dimension(150, 45));
        btnSearch.addActionListener(e -> searchData());

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBackground(new Color(220, 53, 69));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setPreferredSize(new Dimension(150, 45));
        btnLogout.addActionListener(e -> logout());

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnSearch);
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

    private void searchData() {
        String searchTerm = JOptionPane.showInputDialog(this,
                "Masukkan nama cosplayer atau karakter yang dicari:",
                "Pencarian Data",
                JOptionPane.QUESTION_MESSAGE);

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            tableModel.setRowCount(0);
            int i = 1;
            String term = searchTerm.toLowerCase();

            for (Cosplayer c : DatabaseManager.getDaftarPeserta()) {
                if (c.getNamaCosplayer().toLowerCase().contains(term) ||
                        c.getNamaKarakter().toLowerCase().contains(term)) {
                    tableModel.addRow(new Object[]{
                            i++,
                            c.getNamaCosplayer(),
                            c.getNamaKarakter(),
                            c.getAsalSeries()
                    });
                }
            }

            if (i == 1) {
                JOptionPane.showMessageDialog(this,
                        "Tidak ditemukan data untuk: " + searchTerm,
                        "Hasil Pencarian",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            }
        }
    }

    private void openTambahPage() {
        dispose();
        new TambahDataPage();
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin logout dari Peserta Panel?", "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginPage();
        }
    }
}