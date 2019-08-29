package demo;

import com.mysql.jdbc.StringUtils;

public class Test {

	public static void main(String[] args) {
		String A = "http://www.DHgate.com?a=1&b=2&c=3&a=1&b=2&d=4";
		String B = null;
		String [] temp;
		
		temp = A.split("&");
		for(String s :temp) {
			if(StringUtils.isNullOrEmpty(B)) B = s; // 用(B.isEmpty() || B==null)也会空指针异常，因为B.isEmpty()函数的使用已经默认不为空
			if(B.contains(s)) continue;
			B = B +"&"+ s;
		}
		System.out.println(B);
	}
}
