package sort;

import java.util.Arrays;

public class MultiKeyRadixSort {

	public static void radixSort(int[] arr, int radix, int d) {
		int len = arr.length;
		int[] tmpArr = new int[len];
		int[] buckets = new int[radix];
		for(int i=0, rate=1; i<d; i++) {
			Arrays.fill(buckets, 0);
			System.arraycopy(arr, 0, tmpArr, 0, len);
			
			for(int j=0; j<len; j++) {
				int subKey = (tmpArr[j]/rate) % radix;
				buckets[subKey]++;
			}
			for(int j=1; j<radix; j++) {
				buckets[j] = buckets[j] + buckets[j-1];
			}
			
			for(int k=len-1; k>=0; k--) {
				int subKey = (tmpArr[k]/rate) % radix;
				arr[--buckets[subKey]] = tmpArr[k];
			}
			System.out.println("对"+rate+"位上关键字进行桶排序得："+java.util.Arrays.toString(arr));
			rate*=radix;
		}		
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			1100,192,221,12,13
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		System.out.println("排序过程：");
	    radixSort(arr, 10, 4);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}
}
