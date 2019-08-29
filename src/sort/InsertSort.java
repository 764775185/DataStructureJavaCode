package sort;

public class InsertSort {
	public static void insertSort(int[] arr) {
		int len = arr.length;
		for(int i=1; i<len; i++) {
			int temp = arr[i];
			if(arr[i] < arr[i-1]) {
				int j=i-1;
				for(; j>=0 && arr[j]>temp; j--) {
					arr[j+1]=arr[j];
				}
				//“整体搬家”后插入的索引位置！！！
				//temp是插入到j+1索引,不是j索引！！！
				arr[j+1]=temp;
			}
			System.out.println(java.util.Arrays.toString(arr));
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			30,49,21,30,16,9,5
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		System.out.println("排序过程：");
		insertSort(arr);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}
}
