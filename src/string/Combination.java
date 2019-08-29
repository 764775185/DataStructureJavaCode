package string;

public class Combination {

	//采用位图值，即用位操作方法：假设元素原本有：a,b,c三个，则1表示取该元素，0表示不取
	public static void combinationAll(char[] cArr) {
		int n = cArr.length;
		int nbit = 1<<n;
		System.out.println("全组合结果个数为："+ nbit);
		
        for(int i=0 ;i<nbit ; i++) {                        //结果有nbit个。输出结果从数字小到大输出：即输出0,1,2,3,....2^n。
            System.out.print("组合数值  "+i + " 对应编码为： ");
            for(int j=0; j<n ; j++) {                        //每个数二进制最多可以左移n次，即遍历完所有可能的变化新二进制数值了
                int tmp = 1<<j ;        
                if((tmp & i)!=0) {                            //& 表示与。两个位都为1时，结果才为1
                    System.out.print(cArr[j]);
                }
            }
            System.out.println();
        }
	}
	
	//n个元素选m个元素的组合问题的实现，借助Permutate思想，若4取3
	public static void combinationNM(char[] chars) {
		/*char[] subchars = new char[chars.length]; 
		for (int i = 0; i < chars.length; ++i) {
			final int m = i + 1;
			combination(chars, chars.length, m, subchars, m);
		}*/
		
		char[] subchars = new char[chars.length-1];
		for (int i = 0; i < chars.length-1; i++) {
			final int m = i + 1;
			combination(chars, chars.length, m, subchars, m);
		}
	}
	private static void combination(char[] chars, int n, int m, char[] subchars, int subn) {
		if (m == 0) { // 出口
			for (int i = 0; i < subn; i++) {
				System.out.print(subchars[i]);
			}
			System.out.println();
		} else {
			for (int i = n; i >= m; i--) { //对比于Permutate，此处无需swap()的原因，是组合中各字母位置固定
				subchars[m - 1] = chars[i - 1]; 
				combination(chars, i - 1, m - 1, subchars, subn); // 从前i-1个里面选取m-1个进行递归
			}
		}
	}
	
	public static void main(String[] args) {
		char[] cArr = new char[]{'a','b','c','d'};
		//combinationAll(cArr);
		combinationNM(cArr);
	}
}
