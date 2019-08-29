package sort;

public class BucketSort {

	public static void bucketSort(int[] arr, int min, int max) {
		int len = arr.length;
		int[] tmpArr = new int[len];
		int[] buckets = new int[max - min];
		for(int i=0; i<len; i++) {
			buckets[arr[i] - min]++;
		}
		System.out.println(java.util.Arrays.toString(buckets));
		
		for(int i=1; i<buckets.length; i++) {
			buckets[i] = buckets[i] + buckets[i-1];
		}
		System.out.println(java.util.Arrays.toString(buckets));
		
		System.arraycopy(arr, 0, tmpArr, 0, len);
		for(int k=len-1; k>=0; k--) {
			arr[--buckets[tmpArr[k]-min]] = tmpArr[k];
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			9,5,-1,8,5,7,3,-3,3,1
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		System.out.println("排序过程：");
		bucketSort(arr, -3, 10);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}
}
