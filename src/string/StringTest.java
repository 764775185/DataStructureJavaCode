package string;

import java.util.Scanner;

public class StringTest {

	public static String myTrim(String str) {
		int len = str.length();
		int i = 0;
		int j = len-1;
		while(i<len && str.charAt(i)==' ') i++;
		while(i<=j && str.charAt(j)==' ') j--;
		if(i<=j) return str.substring(i,j+1); //前闭后开
		else return "";
	}
	
	public static String reverseString(String str) {
		char[] strArr = str.toCharArray();
		reverseCharArray(strArr);
		return new String(strArr); //不能用toString()，因为数组重写的这个函数作用不同
	}
	private static void reverseCharArray(char[] strArr) {
		for(int i=0,j=strArr.length-1; i<j; i++,j--) {
			swap(strArr,i,j);
		}
	}
	private static void swap(char[] strArr ,int i, int j) {
		char temp = strArr[i];
		strArr[i] = strArr[j];
		strArr[j] = temp;
	}
	public static String reverseString(String str, int i, int j) {
		char[] strArr = str.toCharArray();
		reverseCharArray(strArr, i, j);
		return new String(strArr); 
	}
	private static void reverseCharArray(char[] strArr, int begin, int end) {
		for(int i=begin,j=end; i<j; i++,j--) {
			swap(strArr,i,j);
		}
	}
	//子串统计规则：kkk算作2个kk
	public static int getSubCount(String str, String sub) {
		int count = 0;
		int index = str.indexOf(sub);
		while(index != -1) {
			count++;
			str = str.substring(index+1);  //若kkk算作1个kk，str = str.substring(index+sub.length());
			index = str.indexOf(sub);
		}
		return count;
	}
	
	//获取两个字符串中最大相同子串
	public static String getMaxSubString(String s1,String s2){
		String max = (s1.length() > s2.length()) ? s1 : s2;
		String min = (max == s1)? s2 : s1;
		for(int t=0; t<min.length(); t++) {
			for(int i=0,j=min.length()-t; j<=min.length(); i++,j++) {
				String temp = min.substring(i, j);
				if(max.contains(temp)) return temp;
			}
		}
		return "";
	}
	
	public static void main(String[] args) {
		//String str = "  123 45 ";
		String strReverse = "clj";
		String strReversePatr = "heoll clj ";
		String strP = "abkkkcdkkefkkksk";
		String strSub = "kk";
		String s1 = "abcwerthellouyiodef";
		String s2 = "cvhellobnm";
		Scanner sc = new Scanner(System.in);//或Scanner sc = new Scanner(new BufferedInputStream(System.in));
		String s = sc.nextLine(); //sc.next()读入一个字符串，以空格分割
			
		System.out.println(myTrim(s));
		System.out.println(reverseString(strReverse));
		System.out.println(reverseString(strReversePatr,2,4));
		System.out.println(getSubCount(strP, strSub));
		System.out.println(getMaxSubString(s1, s2));
	}
}
