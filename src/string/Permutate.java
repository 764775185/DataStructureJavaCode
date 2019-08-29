package string;

public class Permutate {

	public static int total = 0;
	public static void arrange(char[] cArr , int begin , int len) {
		if(begin == len-1) {
			for(int i=0; i<len; i++) {
				System.out.print(cArr[i]+" ");
			}
			System.out.println("");
			total++;
		}
		else {
			for(int i=begin; i<len; i++) { //控制第一个字符的选取
				swap(cArr, begin, i);
				arrange(cArr, begin+1, len);  //控制下一位字符的选取，并递归下去
				swap(cArr, begin, i);
			}
		}
	}
	private static void swap(char[] c, int i, int j) {
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}
	
	public static void main(String[] args) {
		char[] cArr = new char[]{'a','b','c','d'};
		arrange(cArr, 0, cArr.length);
		System.out.println(total);
	}
}
