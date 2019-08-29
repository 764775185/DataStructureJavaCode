package sort;

public class ShellSort {
	public static void shellSort(int[] arr) {
		int len = arr.length;
		int h = 1;
		while(h <= len/3) {
			h = h*3+1;
		}
		while(h > 0) {
			System.out.println("当前的h值为："+h);
			for(int i=h; i<len; i++) {
				int temp = arr[i];
				if(arr[i] < arr[i-h]) {
					int j = i-h;
					for(; j>=0 && arr[j]>temp; j-=h) {
						arr[j+h] = arr[j];
					}
					arr[j+h] = temp;
				}
				System.out.println(java.util.Arrays.toString(arr));
			}
			h = (h-1)/3;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{
			9,-16,21,23,-30,-49,21,30,30
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		System.out.println("排序过程：");
		shellSort(arr);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}
}
