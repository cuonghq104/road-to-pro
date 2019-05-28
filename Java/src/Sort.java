public abstract class Sort {
    int[] arr;
    int size;

    protected Sort(int size, int[] arr) {
        this.size = size;
        this.arr = arr;
    }

    protected void showArray() {
        for (int i = 1; i <= size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    abstract void sort(int left, int right);
}
