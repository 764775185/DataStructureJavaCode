package sort;

public class SelectSort {
	//n-1趟比较，且每一趟中遇到比i位小的数就进行i、j交换【冒泡交换也是这个思想，但只进行j、j+1交换，稳定性】
	public static void selectSort(int[] arr) {
		int len = arr.length;
		for(int i = 0 ; i < len-1;i++) {
			for(int j = i+1 ; j<len; j++) {
				if(arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
			//System.out.print(arr[i]+" ");
		}
		System.out.println("一次性输出选择排序后的数组："+java.util.Arrays.toString(arr));
	}
	
	//n-1趟比较，且每一趟中遇到比i位小的数只记下其索引，最后才进行一次交换
	public static void selectSort2(int[] arr) {
		int len = arr.length;
		for(int i = 0 ; i < len-1;i++) {
			int index = i;
			for(int j = i+1 ; j<len; j++) {
				if(arr[index] > arr[j]) {
					index = j;
				}
			}
			if(index!=i) {
				int temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
			}
		}
		System.out.println("一次性输出选择排序后的数组："+java.util.Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			21,30,49,30,16,9
		};
		//selectSort(arr);
		selectSort2(arr);
	}
}
