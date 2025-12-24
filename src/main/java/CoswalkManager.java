import java.util.ArrayList;
import java.util.Scanner;

public class CoswalkManager {
    // ArrayList untuk menyimpan daftar peserta
    private static ArrayList<Cosplayer> daftarPeserta = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            showMenu();
            System.out.print("Pilih menu (1-5): ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    tambahPeserta();
                    break;
                case "2":
                    lihatDaftar();
                    break;
                case "3":
                    updatePeserta();
                    break;
                case "4":
                    hapusPeserta();
                    break;
                case "5":
                    System.out.println("Keluar dari program. Otsukare!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
            System.out.println(); // Baris baru agar rapi
        }
    }

    // Tampilan Menu Utama
    private static void showMenu() {
        System.out.println("======================================");
        System.out.println("    MANAJEMEN DAFTAR PESERTA COSWALK  ");
        System.out.println("======================================");
        System.out.println("1. Tambah Peserta");
        System.out.println("2. Lihat Daftar Peserta");
        System.out.println("3. Edit Data Peserta");
        System.out.println("4. Hapus Peserta");
        System.out.println("5. Keluar");
        System.out.println("======================================");
    }

    // 1. Fitur Tambah Peserta
    private static void tambahPeserta() {
        System.out.println("\n--- Tambah Peserta Baru ---");
        System.out.print("Nama Cosplayer : ");
        String nama = scanner.nextLine();

        System.out.print("Nama Karakter  : ");
        String chara = scanner.nextLine();

        System.out.print("Asal Series    : ");
        String series = scanner.nextLine();

        Cosplayer pesertaBaru = new Cosplayer(nama, chara, series);
        daftarPeserta.add(pesertaBaru);
        System.out.println(">> Berhasil menambahkan peserta!");
    }

    // 2. Fitur Lihat Daftar (Format Tabel)
    private static void lihatDaftar() {
        System.out.println("\n--- Daftar Peserta Coswalk ---");
        if (daftarPeserta.isEmpty()) {
            System.out.println("Belum ada peserta yang mendaftar.");
            return;
        }

        // Header Tabel
        System.out.printf("| %-3s | %-20s | %-20s | %-20s |\n", "No", "Nama Cosplayer", "Karakter", "Series");
        System.out.println("---------------------------------------------------------------------------");

        // Isi Tabel
        for (int i = 0; i < daftarPeserta.size(); i++) {
            Cosplayer p = daftarPeserta.get(i);
            System.out.printf("| %-3d | %-20s | %-20s | %-20s |\n",
                    (i + 1),
                    p.getNamaCosplayer(),
                    p.getNamaKarakter(),
                    p.getAsalSeries());
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    // 3. Fitur Edit Peserta
    private static void updatePeserta() {
        lihatDaftar(); // Tampilkan dulu agar user tahu nomor urutnya
        if (daftarPeserta.isEmpty()) return;

        System.out.print("\nMasukkan Nomor Peserta yang ingin diedit: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index >= 0 && index < daftarPeserta.size()) {
                Cosplayer p = daftarPeserta.get(index);

                System.out.println("Editing: " + p.getNamaCosplayer());
                System.out.print("Nama Baru (Enter skip)    : ");
                String newNama = scanner.nextLine();
                System.out.print("Karakter Baru (Enter skip): ");
                String newChara = scanner.nextLine();
                System.out.print("Series Baru (Enter skip)  : ");
                String newSeries = scanner.nextLine();

                if (!newNama.isEmpty()) p.setNamaCosplayer(newNama);
                if (!newChara.isEmpty()) p.setNamaKarakter(newChara);
                if (!newSeries.isEmpty()) p.setAsalSeries(newSeries);

                System.out.println(">> Data berhasil diupdate!");
            } else {
                System.out.println("Nomor tidak ditemukan!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka!");
        }
    }

    // 4. Fitur Hapus Peserta
    private static void hapusPeserta() {
        lihatDaftar();
        if (daftarPeserta.isEmpty()) return;

        System.out.print("\nMasukkan Nomor Peserta yang akan dihapus: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index >= 0 && index < daftarPeserta.size()) {
                Cosplayer removed = daftarPeserta.remove(index);
                System.out.println(">> Peserta " + removed.getNamaCosplayer() + " berhasil dihapus.");
            } else {
                System.out.println("Nomor tidak ditemukan!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka!");
        }
    }
}
