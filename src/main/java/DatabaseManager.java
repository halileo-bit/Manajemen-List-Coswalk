import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String FILE_NAME = "database.txt";
    private static List<Cosplayer> daftarPeserta = new ArrayList<>();

    private DatabaseManager() {}

    public static List<Cosplayer> getDaftarPeserta() {
        return daftarPeserta;
    }

    public static void addCosplayer(Cosplayer cosplayer) {
        daftarPeserta.add(cosplayer);
        saveData();
    }

    public static void removeCosplayer(int index) {
        if (index >= 0 && index < daftarPeserta.size()) {
            daftarPeserta.remove(index);
            saveData();
        }
    }

    public static void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Cosplayer cosplayer : daftarPeserta) {
                writer.write(cosplayer.toFileFormat());
                writer.newLine();
            }
            System.out.println("Data berhasil disimpan ke " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error menyimpan data: " + e.getMessage());
            showErrorDialog("Error menyimpan data ke database.txt");
        }
    }

    public static void loadData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                daftarPeserta.clear();
                String line;
                int lineCount = 0;

                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    line = line.trim();
                    if (!line.isEmpty()) {
                        Cosplayer cosplayer = Cosplayer.fromFileFormat(line);
                        if (cosplayer != null) {
                            daftarPeserta.add(cosplayer);
                        } else {
                            System.err.println("Format error pada baris " + lineCount + ": " + line);
                        }
                    }
                }
                System.out.println("Data berhasil dimuat: " + daftarPeserta.size() + " peserta");
            } catch (IOException e) {
                System.err.println("Error memuat data: " + e.getMessage());
                daftarPeserta = new ArrayList<>();
                showErrorDialog("Error memuat data dari database.txt");
            }
        } else {
            System.out.println("File database.txt tidak ditemukan. Membuat file baru...");
            daftarPeserta = new ArrayList<>();
            // Coba buat file kosong
            try {
                file.createNewFile();
                System.out.println("File database.txt berhasil dibuat");
            } catch (IOException e) {
                System.err.println("Error membuat file: " + e.getMessage());
            }
        }
    }

    public static int getTotalPeserta() {
        return daftarPeserta.size();
    }

    public static String getDatabaseInfo() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            return "database.txt (" + file.length() + " bytes)";
        }
        return "database.txt (tidak ditemukan)";
    }

    public static void backupDatabase() {
        File original = new File(FILE_NAME);
        File backup = new File("database_backup.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(original));
             BufferedWriter writer = new BufferedWriter(new FileWriter(backup))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Backup berhasil dibuat: database_backup.txt");
        } catch (IOException e) {
            System.err.println("Error membuat backup: " + e.getMessage());
        }
    }

    private static void showErrorDialog(String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message, "Database Error", JOptionPane.ERROR_MESSAGE);
        });
    }
}