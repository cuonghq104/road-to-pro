public class QuickSort extends Sort{

    protected QuickSort(int size, int[] arr) {
        super(size, arr);
    }

    void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    int partition(int left, int right) {
        int pivot = right;
        int wall = left - 1;

        for (int i = left; i < right; i++) {
            if (arr[i] < arr[pivot]) {
                wall++;
                if (wall != i) {
                    swap(wall, i);
                }
            }
        }

        int tmp = arr[pivot];
        for (int i = pivot; i > wall; i--) {
            arr[i] = arr[i - 1];
        }
        arr[wall + 1] = tmp;
        return wall + 1;
    }

    @Override
    void sort(int left, int right) {
        if (left < right) {
            int partition = partition(left, right);
            sort(left, partition - 1);
            sort(partition + 1, right);
        }
    }
}
