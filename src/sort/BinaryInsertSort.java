package sort;

public class BinaryInsertSort {

	public static void binaryInsertSort(int[] arr) {
		int len = arr.length;
		for(int i=1; i<len; i++) {
			int temp = arr[i];
			int low = 0;
			int high = i-1;
			while(low<=high) {
				int mid = (low+high)/2;
				if(arr[mid]>temp) high = mid-1;
				else low = mid+1;
			}
			for(int j=i; j>low;j--) {
				arr[j] = arr[j-1];
			}
			//“整体搬家”后插入的索引位置！！！
			//temp是插入到low索引，即j索引处！！！
			arr[low]=temp;
			System.out.println(java.util.Arrays.toString(arr));
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			30,49,21,30,16,9,5
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		System.out.println("排序过程：");
		binaryInsertSort(arr);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}

}
