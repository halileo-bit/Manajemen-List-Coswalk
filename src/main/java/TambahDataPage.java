import javax.swing.*;
import java.awt.*;

public class TambahDataPage extends JFrame {
    private JTextField txtNama, txtKarakter, txtSeries;

    public TambahDataPage() {
        initComponents();
        setTitle("Tambah Data Peserta - Peserta Panel");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        JLabel lblIcon = new JLabel("➕");
        lblIcon.setFont(new Font("Segoe UI", Font.PLAIN, 28));

        JLabel lblTitle = new JLabel("TAMBAH DATA PESERTA");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        titlePanel.add(lblIcon);
        titlePanel.add(lblTitle);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        // Right side - Info
        JLabel lblInfo = new JLabel("Isi form dengan lengkap dan benar");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblInfo.setForeground(Color.WHITE);
        lblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
        headerPanel.add(lblInfo, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Title form
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel lblFormTitle = new JLabel("📝 FORM PENDAFTARAN PESERTA COSWALK");
        lblFormTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblFormTitle.setForeground(new Color(0, 123, 255));
        formPanel.add(lblFormTitle, gbc);

        gbc.gridwidth = 1;

        // Nama Cosplayer
        gbc.gridy = 1;
        JLabel lblNama = new JLabel("Nama Cosplayer:");
        lblNama.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblNama, gbc);

        txtNama = new JTextField();
        txtNama.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNama.setPreferredSize(new Dimension(250, 40));
        txtNama.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridx = 1;
        formPanel.add(txtNama, gbc);

        // Nama Karakter
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblKarakter = new JLabel("Nama Karakter:");
        lblKarakter.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblKarakter, gbc);

        txtKarakter = new JTextField();
        txtKarakter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtKarakter.setPreferredSize(new Dimension(250, 40));
        txtKarakter.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridx = 1;
        formPanel.add(txtKarakter, gbc);

        // Asal Series
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblSeries = new JLabel("Asal Series:");
        lblSeries.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblSeries, gbc);

        txtSeries = new JTextField();
        txtSeries.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSeries.setPreferredSize(new Dimension(250, 40));
        txtSeries.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridx = 1;
        formPanel.add(txtSeries, gbc);

        // Info panel
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(new Color(255, 243, 205));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 193, 7), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        JLabel lblInfoText = new JLabel("<html>ℹ️ Data akan disimpan ke <b>database.txt</b>. "
                + "Pastikan data belum terdaftar sebelumnya.</html>");
        lblInfoText.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        infoPanel.add(lblInfoText);

        formPanel.add(infoPanel, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnSimpan = new JButton("💾 Simpan ke Database");
        btnSimpan.setBackground(new Color(40, 167, 69));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSimpan.setPreferredSize(new Dimension(220, 45));
        btnSimpan.addActionListener(e -> simpanData());

        JButton btnKembali = new JButton("↩️ Kembali ke Panel");
        btnKembali.setBackground(new Color(108, 117, 125));
        btnKembali.setForeground(Color.WHITE);
        btnKembali.setPreferredSize(new Dimension(180, 45));
        btnKembali.addActionListener(e -> kembali());

        JButton btnReset = new JButton("🗑️ Reset Form");
        btnReset.setBackground(new Color(255, 193, 7));
        btnReset.setForeground(Color.BLACK);
        btnReset.setPreferredSize(new Dimension(150, 45));
        btnReset.addActionListener(e -> resetForm());

        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnKembali);
        buttonPanel.add(btnReset);

        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(248, 249, 250));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblFooter = new JLabel("Data tersimpan di: database.txt | Total peserta: " +
                DatabaseManager.getTotalPeserta(), SwingConstants.CENTER);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(108, 117, 125));

        footerPanel.add(lblFooter);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Set focus ke field pertama
        txtNama.requestFocus();
    }

    private void simpanData() {
        String nama = txtNama.getText().trim();
        String karakter = txtKarakter.getText().trim();
        String series = txtSeries.getText().trim();

        // Validasi
        if (nama.isEmpty() || karakter.isEmpty() || series.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "❌ Semua field harus diisi!\n" +
                            "Harap lengkapi semua data sebelum disimpan.",
                    "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cek duplikat
        for (Cosplayer c : DatabaseManager.getDaftarPeserta()) {
            if (c.getNamaCosplayer().equalsIgnoreCase(nama) &&
                    c.getNamaKarakter().equalsIgnoreCase(karakter)) {
                JOptionPane.showMessageDialog(this,
                        "❌ Data sudah terdaftar!\n\n" +
                                "Nama: " + c.getNamaCosplayer() + "\n" +
                                "Karakter: " + c.getNamaKarakter() + "\n" +
                                "Series: " + c.getAsalSeries() + "\n\n" +
                                "Silakan gunakan data yang berbeda.",
                        "Duplikat Data", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Konfirmasi sebelum simpan
        int confirm = JOptionPane.showConfirmDialog(this,
                "Konfirmasi data yang akan disimpan:\n\n" +
                        "Nama Cosplayer: " + nama + "\n" +
                        "Nama Karakter: " + karakter + "\n" +
                        "Asal Series: " + series + "\n\n" +
                        "Apakah data sudah benar?",
                "Konfirmasi Simpan",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            // Simpan data
            Cosplayer newCosplayer = new Cosplayer(nama, karakter, series);
            DatabaseManager.addCosplayer(newCosplayer);

            JOptionPane.showMessageDialog(this,
                    "✅ Data berhasil disimpan!\n\n" +
                            "Nama: " + nama + "\n" +
                            "Karakter: " + karakter + "\n" +
                            "Series: " + series + "\n\n" +
                            "Data telah disimpan ke database.txt\n" +
                            "Total peserta sekarang: " + DatabaseManager.getTotalPeserta(),
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);

            resetForm();
        }
    }

    private void resetForm() {
        txtNama.setText("");
        txtKarakter.setText("");
        txtSeries.setText("");
        txtNama.requestFocus();
    }

    private void kembali() {
        dispose();
        new PesertaPage();
    }
}