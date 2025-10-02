import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DVK {
    private DVKE<Company> ankerV;
    private DVKE<Company> ankerR;
    private long anz;

    public DVK() {
        this.ankerV = null;
        this.ankerR = null;
        this.anz    = 0;
    }

    // --- Grundoperationen ---
    public void addCompany(Company newCompany) {
        if (ankerV == null) {
            ankerV = newCompany;
            ankerR = newCompany;
        } else {
            ankerR.setN(newCompany);
            newCompany.setV(ankerR);
            ankerR = newCompany;
        }
        anz++;
    }

    public ArrayList<Company> toArrayList() {
        ArrayList<Company> list = new ArrayList<>();
        DVKE<Company> cur = ankerV;
        while (cur != null) {
            list.add(cur.getData());
            cur = cur.getN();
        }
        return list;
    }

    public static DVK fromArrayList(ArrayList<Company> comps) {
        DVK dvk = new DVK();
        for (Company c : comps) {
            dvk.addCompany(c);
        }
        return dvk;
    }

    // --- CSV-Import ---
    public boolean readCompaniesFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // erste Zeile = Header überspringen
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] t = line.split(Config.DELIMITER, -1);
                long   idx = Long.parseLong(t[0]);
                String org = t[1], name = t[2], web = t[3],
                        ctr = t[4], desc = t[5], ind = t[7];
                int    fou = Integer.parseInt(t[6]),
                        num = Integer.parseInt(t[8]);
                Company c = new Company(idx, org, name, web, ctr, desc, fou, ind, num);
                addCompany(c);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void chooseAndLoadCsvFile(Scanner scanner) {
        System.out.println("1) Daten1.csv (100 000 Datensätze)");
        System.out.println("2) Daten2.csv (1 000 000 Datensätze)");
        System.out.print("Auswahl: ");
        int w = scanner.nextInt(); scanner.nextLine();
        String file = w == 1 ? "Daten1.csv"
                : w == 2 ? "Daten2.csv"
                : null;
        if (file == null) {
            System.out.println("Ungültige Auswahl"); return;
        }
        // Liste zurücksetzen
        ankerV = ankerR = null;
        anz = 0;
        if (readCompaniesFromCsv(file)) {
            System.out.println("Daten geladen: " + anz + " Einträge");
        } else {
            System.out.println("Fehler beim Laden");
        }
    }

    public void displayFirstNElements(int n) {
        DVKE<Company> cur = ankerV;
        int cnt = 0;
        while (cur != null && cnt < n) {
            System.out.println(cur.getData());
            cur = cur.getN();
            cnt++;
        }
    }

    public void sortAndSaveCompanies(Scanner scanner) {
        String[] methods = {
                "bubblesort","selectionsort","insertionsort",
                "quicksort","mergesort","heapsort"
        };
        System.out.println("Wählen Sie ein Sortierverfahren:");
        for (int i = 0; i < methods.length; i++) {
            System.out.println((i+1) + ") " + methods[i]);
        }
        System.out.print("Auswahl: ");
        int w = scanner.nextInt(); scanner.nextLine();
        if (w < 1 || w > methods.length) {
            System.out.println("Ungültige Auswahl"); return;
        }
        String method = methods[w-1];
        ArrayList<Company> unsort = toArrayList();
        long start = System.nanoTime();
        ArrayList<Company> sorted;
        switch (method) {
            case "bubblesort":    sorted = SortierAlgorithmen.bubbleSort(unsort);    break;
            case "selectionsort": sorted = SortierAlgorithmen.selectionSort(unsort); break;
            case "insertionsort": sorted = SortierAlgorithmen.insertionSort(unsort); break;
            case "quicksort":     sorted = SortierAlgorithmen.quickSort(unsort);     break;
            case "mergesort":     sorted = SortierAlgorithmen.mergeSort(unsort);     break;
            case "heapsort":      sorted = SortierAlgorithmen.heapSort(unsort);      break;
            default:              sorted = unsort;                                   break;
        }
        long end = System.nanoTime();
        long ms  = TimeUnit.NANOSECONDS.toMillis(end - start);
        System.out.println("Dauer: " + ms + " ms");
        saveSortedDataToCsv(sorted, method);
    }

    public void saveSortedDataToCsv(ArrayList<Company> sorted, String method) {
        String ts = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String out = "Sortiert_" + ts + "_" + method + ".csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            bw.write(Company.getCsvHeader());
            bw.newLine();
            for (Company c : sorted) {
                bw.write(c.toString());
                bw.newLine();
            }
            System.out.println("Gespeichert: " + out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
