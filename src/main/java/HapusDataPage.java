import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HapusDataPage extends JFrame {
    private JTable tablePeserta;
    private DefaultTableModel tableModel;
    private JLabel lblSelectedInfo;

    public HapusDataPage() {
        initComponents();
        setTitle("Hapus Data Peserta - Admin Panel");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(220, 53, 69));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);

        JLabel lblTitle = new JLabel("HAPUS DATA PESERTA");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        titlePanel.add(lblTitle);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        lblSelectedInfo = new JLabel("Pilih data dari tabel di bawah");
        lblSelectedInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSelectedInfo.setForeground(Color.WHITE);
        lblSelectedInfo.setHorizontalAlignment(SwingConstants.RIGHT);
        headerPanel.add(lblSelectedInfo, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        JPanel warningPanel = new JPanel(new BorderLayout());
        warningPanel.setBackground(new Color(255, 243, 205));
        warningPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 193, 7), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        contentPanel.add(warningPanel, BorderLayout.NORTH);

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
        tablePeserta.setSelectionBackground(new Color(255, 220, 220));

        tablePeserta.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablePeserta.getSelectedRow();
                if (selectedRow != -1) {
                    String nama = (String) tableModel.getValueAt(selectedRow, 1);
                    String karakter = (String) tableModel.getValueAt(selectedRow, 2);
                    lblSelectedInfo.setText("Terpilih: " + nama + " - " + karakter);
                } else {
                    lblSelectedInfo.setText("Pilih data dari tabel di bawah");
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablePeserta);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218), 1));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnHapus = new JButton("HAPUS DATA TERPILIH");
        btnHapus.setBackground(new Color(220, 53, 69));
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnHapus.addActionListener(e -> hapusData());

        JButton btnHapusSemua = new JButton("Hapus Semua");
        btnHapusSemua.setBackground(new Color(108, 117, 125));
        btnHapusSemua.setForeground(Color.WHITE);
        btnHapusSemua.addActionListener(e -> hapusSemua());

        JButton btnRefresh = new JButton("Refresh Tabel");
        btnRefresh.setBackground(new Color(40, 167, 69));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.addActionListener(e -> refreshTable());

        JButton btnKembali = new JButton("↩Kembali");
        btnKembali.setBackground(new Color(0, 123, 255));
        btnKembali.setForeground(Color.WHITE);
        btnKembali.addActionListener(e -> kembali());

        buttonPanel.add(btnHapus);
        buttonPanel.add(btnHapusSemua);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnKembali);

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
        lblSelectedInfo.setText("Pilih data dari tabel di bawah");
        tablePeserta.clearSelection();
    }

    private void hapusData() {
        int selectedRow = tablePeserta.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Silakan pilih data yang akan dihapus terlebih dahulu!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String namaPeserta = (String) tableModel.getValueAt(selectedRow, 1);
        String karakter = (String) tableModel.getValueAt(selectedRow, 2);
        String series = (String) tableModel.getValueAt(selectedRow, 3);

        int confirm = JOptionPane.showConfirmDialog(this,
                "<html><b>Konfirmasi Hapus Data:</b><br><br>" +
                        "Nama: " + namaPeserta + "<br>" +
                        "Karakter: " + karakter + "<br>" +
                        "Series: " + series + "<br><br>",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            DatabaseManager.removeCosplayer(selectedRow);
            JOptionPane.showMessageDialog(this,
                    "Data berhasil dihapus dari database!\n",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        }
    }

    private void hapusSemua() {
        if (DatabaseManager.getTotalPeserta() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Tidak ada data yang dapat dihapus!",
                    "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Semua data akan dihapus dari database.txt" ,
                "Konfirmasi Hapus Semua",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            DatabaseManager.getDaftarPeserta().clear();
            DatabaseManager.saveData();
            JOptionPane.showMessageDialog(this,
                    "Semua data berhasil dihapus!\n",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        }
    }

    private void kembali() {
        dispose();
        new AdminPage();
    }
}