package sort;

public class QuickSort {
	
	private static void swap(int[] arr, int i, int j ) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void quickSort(int[] arr) {
		subSort(arr, 0 ,arr.length-1);
	}
	
	public static void subSort(int[] arr, int start, int end) {
		if(start < end) {
			int base = arr[start];
			int i = start+1;
			int j = end;
			//注意细节
			while(true) {
				while(i<end && arr[i]<=base) i++;
				while(j>start && arr[j]>base) j--;
				if(i<j) swap(arr, i, j);
				else break;
			}
			swap(arr, start, j);
			subSort(arr, start, j-1);
			subSort(arr, j+1, end);
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			21,30,49,30,16,9,5
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		quickSort(arr);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}
}
