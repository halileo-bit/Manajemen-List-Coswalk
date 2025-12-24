public class Cosplayer {
    private String namaCosplayer;
    private String namaKarakter;
    private String asalSeries;

    public Cosplayer(String namaCosplayer, String namaKarakter, String asalSeries) {
        this.namaCosplayer = namaCosplayer;
        this.namaKarakter = namaKarakter;
        this.asalSeries = asalSeries;
    }

    public String getNamaCosplayer() { return namaCosplayer; }
    public String getNamaKarakter() { return namaKarakter; }
    public String getAsalSeries() { return asalSeries; }

    public void setNamaCosplayer(String namaCosplayer) { this.namaCosplayer = namaCosplayer; }
    public void setNamaKarakter(String namaKarakter) { this.namaKarakter = namaKarakter; }
    public void setAsalSeries(String asalSeries) { this.asalSeries = asalSeries; }

    // Format untuk disimpan ke file
    public String toFileFormat() {
        return namaCosplayer + ";" + namaKarakter + ";" + asalSeries;
    }

    // Static method untuk membuat objek dari string file
    public static Cosplayer fromFileFormat(String line) {
        String[] parts = line.split(";");
        if (parts.length == 3) {
            return new Cosplayer(parts[0], parts[1], parts[2]);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", namaCosplayer, namaKarakter, asalSeries);
    }
}