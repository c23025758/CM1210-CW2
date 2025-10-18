import java.util.ArrayList;
import java.util.Random;

public class SortingItems {
    public static void main(String[] args) {
        
        ArrayList<Integer> monday = generateItems(10, 1000, 5000);
        ArrayList<Integer> tuesday = generateItems(20, 1000, 5000);
        ArrayList<Integer> wednesday = generateItems(30, 1000, 5000);
        ArrayList<Integer> thursday = generateItems(40, 1000, 5000);
        ArrayList<Integer> friday = generateItems(50, 1000, 5000);
        ArrayList<Integer> saturday = generateItems(60, 1000, 5000);
        ArrayList<Integer> sunday = generateItems(70, 1000, 5000);

        
        System.out.println("Unsorted lists:");
        printFirstNItems(monday, 10);
        printFirstNItems(tuesday, 10);
        printFirstNItems(wednesday, 10);
        printFirstNItems(thursday, 10);
        printFirstNItems(friday, 10);
        printFirstNItems(saturday, 10);
        printFirstNItems(sunday, 10);

        
        quickSort(monday, 0, monday.size() - 1);
        quickSort(tuesday, 0, tuesday.size() - 1);
        quickSort(wednesday, 0, wednesday.size() - 1);
        quickSort(thursday, 0, thursday.size() - 1);
        quickSort(friday, 0, friday.size() - 1);
        quickSort(saturday, 0, saturday.size() - 1);
        quickSort(sunday, 0, sunday.size() - 1);

        
        System.out.println("\nSorted lists:");
        printFirstNItems(monday, 10);
        printFirstNItems(tuesday, 10);
        printFirstNItems(wednesday, 10);
        printFirstNItems(thursday, 10);
        printFirstNItems(friday, 10);
        printFirstNItems(saturday, 10);
        printFirstNItems(sunday, 10);
    }

    
    public static ArrayList<Integer> generateItems(int count, int min, int max) {
        ArrayList<Integer> items = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            items.add(rand.nextInt(max - min + 1) + min);
        }
        return items;
    }

    
    public static void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    
    public static int partition(ArrayList<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j) <= pivot) {
                i++;
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }

    
    public static void printFirstNItems(ArrayList<Integer> arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }
}