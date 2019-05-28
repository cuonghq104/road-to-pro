import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortMain {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("F:\\Samsung-stc\\Java\\input.txt"));
        int size = sc.nextInt();
        int[] arr = new int[size + 1];
        for (int i = 1; i <= size; i++) arr[i] = sc.nextInt();

        long startTime = System.nanoTime();
        Sort sort = new MergeSort(size, arr);
        sort.sort(0, size);
        sort.showArray();
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);
    }
}
