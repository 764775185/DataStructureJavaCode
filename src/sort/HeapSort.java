package sort;

public class HeapSort {
	public static void heapSort(int[] arr) {
		int len = arr.length;
		//n-1次建堆，并将每次堆顶放置在当前堆排数组的末尾
		for(int i = 0; i < len-1 ; i++) {
			buildMaxHeap(arr, len-1-i);
			swap(arr, 0 ,len-1-i);
			System.out.println(java.util.Arrays.toString(arr));
		}
	}
	
	private static void buildMaxHeap(int[] arr, int lastIndex){
		for(int i = (lastIndex-1)/2; i>=0 ;i-- ) {
			int k = i;
			while(k*2+1 <= lastIndex) {
				int biggerchild =k*2+1;
				if(biggerchild<lastIndex) {
					if(arr[biggerchild] < arr[biggerchild+1])
						biggerchild++;
				}
				if(arr[k] < arr[biggerchild]) {
					swap(arr, k, biggerchild);
					k=biggerchild;
				}
				else break;
			}
		}
	}
	private static void swap(int[] arr, int i, int j ) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			21,30,49,30,16,9,5
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		System.out.println("排序过程：");
		heapSort(arr);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}
}
