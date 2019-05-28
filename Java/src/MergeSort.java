import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MergeSort extends Sort{

    protected MergeSort(int size, int[] arr) {
        super(size, arr);
    }

    @Override
    void sort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(left, mid);
            sort(mid + 1, right);

            merge(left, mid, right);
        }
    }

    void merge(int left, int mid, int right) {
        int sizeLeft = mid - left + 1;
        int sizeRight = right - mid;

        int[] tmpLeft = new int[sizeLeft + 1];
        int[] tmpRight = new int[sizeRight + 1];

        for (int i = 1; i <= sizeLeft; i++) tmpLeft[i] = arr[i + left - 1];
        for (int i = 1; i <= sizeRight; i++) tmpRight[i] = arr[mid + i];

        int iLeft = 1;
        int iRight = 1;
        int k = left;

        while (iLeft <= sizeLeft && iRight <= sizeRight) {

            if (tmpLeft[iLeft] <= tmpRight[iRight]) {
                arr[k] = tmpLeft[iLeft];
                iLeft++;
            } else {
                arr[k] = tmpRight[iRight];
                iRight++;
            }
            k++;
        }

        while (iLeft <= sizeLeft) {
            arr[k] = tmpLeft[iLeft];
            k++;
            iLeft++;
        }

        while (iRight <= sizeRight) {
            arr[k] = tmpRight[iRight];
            k++;
            iRight++;
        }

    }

//    public static void main(String[] args) throws FileNotFoundException {
////        arr = new int[]{0, 3, 2, 5, 6, 8, 7, 12, 10, 9};
////        size = arr.length - 1;
//        Scanner sc = new Scanner(new FileInputStream("F:\\Samsung-stc\\Java\\input.txt"));
//        size = sc.nextInt();
//        arr = new int[size + 1];
//        long startTime = System.nanoTime();
//        for (int i = 1; i <= size; i++) {
//            arr[i] = sc.nextInt();
//        }
//        sort(1, size);
//        for (int i = 1; i <= size; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        long endTime = System.nanoTime();
//        System.out.println();
//        System.out.println(((endTime - startTime)));
//    }
}
