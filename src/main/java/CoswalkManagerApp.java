import javax.swing.*;

public class CoswalkManagerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseManager.loadData();

                new LoginPage();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error memulai aplikasi: " + e.getMessage(),
                        "Fatal Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}