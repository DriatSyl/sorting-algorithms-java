import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DVK dvk = new DVK();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Menü ---");
            System.out.println("1) Verkettete Liste anlegen (CSV einlesen)");
            System.out.println("2) Erste 30 Einträge der unsortierten Liste anzeigen");
            System.out.println("3) Sortierverfahren auswählen und Liste speichern");
            System.out.println("4) Sortierschlüssel festlegen (aktuell: " + Config.SORT_KEY + ")");
            System.out.println("5) Ende");
            System.out.print("Auswahl: ");

            int w = scanner.nextInt(); scanner.nextLine();
            switch (w) {
                case 1: dvk.chooseAndLoadCsvFile(scanner); break;
                case 2: dvk.displayFirstNElements(30);   break;
                case 3: dvk.sortAndSaveCompanies(scanner);break;
                case 4: setSortKey(scanner);             break;
                case 5: running = false;                 break;
                default:System.out.println("Ungültig");  break;
            }
        }

        scanner.close();
    }

    public static void setSortKey(Scanner scanner) {
        String[] keys = {
                "index","organizationID","country",
                "founded","numberOfEmployees","name"
        };
        System.out.println("Wählen Sie einen Sortierschlüssel:");
        for (int i = 0; i < keys.length; i++) {
            System.out.println((i+1) + ") " + keys[i]);
        }
        System.out.print("Auswahl: ");
        int w = scanner.nextInt(); scanner.nextLine();
        if (w >= 1 && w <= keys.length) {
            Config.SORT_KEY = keys[w-1];
            System.out.println("Sortierschlüssel: " + Config.SORT_KEY);
        } else {
            System.out.println("Ungültige Auswahl");
        }
    }
}
