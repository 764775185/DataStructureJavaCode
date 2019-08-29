package sort;

public class MergeSort {

	public static void mergeSort(int[] arr) {
		mSort(arr, 0, arr.length-1);
	}
	
	private static void mSort(int[] arr, int left, int right) {
		if(left < right) {
			int center = (left + right)/2;
			mSort(arr, left, center);
			mSort(arr, center+1, right);
			merge(arr, left, center, right);
		}
	}
	
	private static void merge(int[] arr, int left, int center, int right) {
		int[] tmpArr = new int[arr.length];
		int i = left;
		int j = center+1;
		int k = left;
		while(i <= center && j <= right) {
			if(arr[i] <= arr[j]) {
				tmpArr[k++] = arr[i++];
			}
			else {
				tmpArr[k++] = arr[j++];
			}
		}
		while(i <= center) {
			tmpArr[k++] = arr[i++];
		}
		while(j <= right) {
			tmpArr[k++] = arr[j++];
		}
		
		int tmp = left;
		while(tmp <= right) {
			arr[tmp] = tmpArr[tmp++];
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			30,49,21,30,16,9,5
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		mergeSort(arr);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}
}
