package 算法课程复习;

public class QuickSortDemo {
    public static void QuickSort(int[] arr, int left, int right) {
        if(left< right){
            int pivotIndex = partition(arr, left, right);
            QuickSort(arr, left, pivotIndex - 1);
            QuickSort(arr, pivotIndex + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int j = left - 1;
        for(int i = left; i < right; i++){
            if(arr[i] < pivot){
                swap(arr,++j, i);
            }
        }
        swap(arr,++j, right);
        return j;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 7, 5, 8, 11, 9};
        QuickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
