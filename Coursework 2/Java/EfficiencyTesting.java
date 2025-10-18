import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EfficiencyTesting {

    public static void main(String[] args) {
        
        int[] sizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000};

        
        for (int size : sizes) {
            ArrayList<Integer> randomList = generateRandomList(size);
            ArrayList<Integer> sortedList = generateSortedList(size);
            ArrayList<Integer> reverseSortedList = generateReverseSortedList(size);

            
            long startTime = System.currentTimeMillis();
            quickSort(randomList, 0, randomList.size() - 1);
            long endTime = System.currentTimeMillis();
            long randomTime = endTime - startTime;

            
            startTime = System.currentTimeMillis();
            quickSort(sortedList, 0, sortedList.size() - 1);
            endTime = System.currentTimeMillis();
            long sortedTime = endTime - startTime;

            
            startTime = System.currentTimeMillis();
            quickSort(reverseSortedList, 0, reverseSortedList.size() - 1);
            endTime = System.currentTimeMillis();
            long reverseSortedTime = endTime - startTime;

            
            System.out.println("List size: " + size);
            System.out.println("Random list time: " + randomTime + " ms");
            System.out.println("Sorted list time: " + sortedTime + " ms");
            System.out.println("Reverse sorted list time: " + reverseSortedTime + " ms");
            System.out.println();
        }
    }

    
    public static ArrayList<Integer> generateRandomList(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            list.add(rand.nextInt());
        }
        return list;
    }

    
    public static ArrayList<Integer> generateSortedList(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    
    public static ArrayList<Integer> generateReverseSortedList(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            list.add(i);
        }
        return list;
    }

    
    public static void quickSort(ArrayList<Integer> arr, int low, int high) {
        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            int pi = partition(arr, low, high);

            if (pi - 1 > low) {
                stack[++top] = low;
                stack[++top] = pi - 1;
            }

            if (pi + 1 < high) {
                stack[++top] = pi + 1;
                stack[++top] = high;
            }
        }
    }

    public static int partition(ArrayList<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j) <= pivot) {
                i++;
                Collections.swap(arr, i, j);
            }
        }
        Collections.swap(arr, i + 1, high);
        return i + 1;
    }
}