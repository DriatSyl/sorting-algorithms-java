import java.util.ArrayList;

public class SortierAlgorithmen {

    public static ArrayList<Company> bubbleSort(ArrayList<Company> data) {
        ArrayList<Company> list = new ArrayList<>(data);
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    Company tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return list;
    }

    public static ArrayList<Company> selectionSort(ArrayList<Company> data) {
        ArrayList<Company> list = new ArrayList<>(data);
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).compareTo(list.get(min)) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                Company tmp = list.get(i);
                list.set(i, list.get(min));
                list.set(min, tmp);
            }
        }
        return list;
    }

    public static ArrayList<Company> insertionSort(ArrayList<Company> data) {
        ArrayList<Company> list = new ArrayList<>(data);
        for (int i = 1; i < list.size(); i++) {
            Company key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        return list;
    }

    public static ArrayList<Company> quickSort(ArrayList<Company> data) {
        ArrayList<Company> list = new ArrayList<>(data);
        qs(list, 0, list.size() - 1);
        return list;
    }
    private static void qs(ArrayList<Company> a, int l, int r) {
        if (l < r) {
            int p = partition(a, l, r);
            qs(a, l, p - 1);
            qs(a, p + 1, r);
        }
    }
    private static int partition(ArrayList<Company> a, int l, int r) {
        Company pivot = a.get(r);
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (a.get(j).compareTo(pivot) <= 0) {
                i++;
                Company tmp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, tmp);
            }
        }
        Company tmp = a.get(i + 1);
        a.set(i + 1, a.get(r));
        a.set(r, tmp);
        return i + 1;
    }

    public static ArrayList<Company> mergeSort(ArrayList<Company> data) {
        if (data.size() <= 1) return new ArrayList<>(data);
        int m = data.size() / 2;
        ArrayList<Company> left  = mergeSort(new ArrayList<>(data.subList(0, m)));
        ArrayList<Company> right = mergeSort(new ArrayList<>(data.subList(m, data.size())));
        return merge(left, right);
    }
    private static ArrayList<Company> merge(ArrayList<Company> L, ArrayList<Company> R) {
        ArrayList<Company> out = new ArrayList<>();
        int i=0, j=0;
        while (i < L.size() && j < R.size()) {
            if (L.get(i).compareTo(R.get(j)) <= 0) out.add(L.get(i++));
            else                                     out.add(R.get(j++));
        }
        while (i < L.size()) out.add(L.get(i++));
        while (j < R.size()) out.add(R.get(j++));
        return out;
    }

    public static ArrayList<Company> heapSort(ArrayList<Company> data) {
        int n = data.size();
        Company[] arr = data.toArray(new Company[0]);
        // build max-heap
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // sort down
        for (int i = n - 1; i > 0; i--) {
            Company tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
        // zurück in ArrayList
        ArrayList<Company> sorted = new ArrayList<>(n);
        for (Company c : arr) sorted.add(c);
        return sorted;
    }
    private static void heapify(Company[] a, int size, int root) {
        int largest = root;
        int l = 2*root + 1, r = 2*root + 2;
        if (l < size && a[l].compareTo(a[largest]) > 0) largest = l;
        if (r < size && a[r].compareTo(a[largest]) > 0) largest = r;
        if (largest != root) {
            Company tmp = a[root];
            a[root] = a[largest];
            a[largest] = tmp;
            heapify(a, size, largest);
        }
    }
}
