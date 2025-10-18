import java.util.ArrayList;

public class TestHybridSorting {
    public static void main(String[] args) {
        
        int[] sizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000};

        System.out.println("Testing Hybrid Sorting Algorithm:");

        
        for (int size : sizes) {
            ArrayList<Integer> randomList = generateRandomList(size);
            ArrayList<Integer> sortedList = generateSortedList(size);
            ArrayList<Integer> reverseSortedList = generateReverseSortedList(size);

            
            long randomTime = measureTime(randomList, 10); 

            
            long sortedTime = measureTime(sortedList, 10);

            
            long reverseSortedTime = measureTime(reverseSortedList, 10);

            
            System.out.println("List size: " + size);
            System.out.println("Random list time: " + randomTime + " ms");
            System.out.println("Sorted list time: " + sortedTime + " ms");
            System.out.println("Reverse sorted list time: " + reverseSortedTime + " ms");
            System.out.println();
        }
    }

    
    public static ArrayList<Integer> generateRandomList(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((int)(Math.random() * size));
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

    
    public static long measureTime(ArrayList<Integer> list, int cutoff) {
        long startTime = System.currentTimeMillis();
        hybridSort(list, 0, list.size() - 1, cutoff);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

   
public static void hybridSort(ArrayList<Integer> arr, int low, int high, int cutoff) {
    
    int[] stack = new int[high - low + 1];
    int top = -1;

    stack[++top] = low;
    stack[++top] = high;

    
    while (top >= 0) {
        high = stack[top--];
        low = stack[top--];

        if (high - low + 1 <= cutoff) {
            insertionSort(arr, low, high); 
        } else {
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
}


    public static void insertionSort(ArrayList<Integer> arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= low && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
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
}