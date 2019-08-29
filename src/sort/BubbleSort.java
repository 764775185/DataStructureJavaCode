package sort;

public class BubbleSort {
	//n-1趟比较，且每一趟中进行j、j+1交换，比跳跃的交换方法（i、j交换)更稳定，某一趟不存在交换操作则算法结束
	public static void bubbleSort(int[] arr) {
		int len = arr.length;
		for(int i = 0; i<len-1; i++) {
			boolean flag = false;
			for(int j=0; j<len-1-i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
					flag = true;
				}
			}
			System.out.println(java.util.Arrays.toString(arr));
			if(!flag) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[]{
			21,30,49,30,16,9,5
		};
		System.out.println("排序之前：\n"+java.util.Arrays.toString(arr));
		System.out.println("排序过程：");
		bubbleSort(arr);
		System.out.println("排序之后：\n"+java.util.Arrays.toString(arr));
	}
}
