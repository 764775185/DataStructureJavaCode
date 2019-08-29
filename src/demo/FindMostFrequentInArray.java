package demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.mysql.fabric.xmlrpc.base.Array;

public class FindMostFrequentInArray {

	public static int findMostFrequentOne(int[] a) {
		int result = 0;
		int len = a.length;
		if(len==0) return 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<len; i++) {
			if(map.containsKey(a[i]))
				map.put(a[i], map.get(a[i])+1);
			else
				map.put(a[i], 1);
		}
		
		int most = 0;
	/*	Iterator it = map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			if((Integer)entry.getValue() > most) {
				most = (Integer)entry.getValue();
				result = (Integer)entry.getKey();
			}
		}*/
		Set<Integer> set = map.keySet();
		for(Integer s : set) {
			if(map.get(s) >most) {
				result = s;
				most = map.get(s);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int arr[] = {1,5,4,3,4,4,5,4,5,5,6,6,6,6,6};
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(findMostFrequentOne(arr));
	}
}
