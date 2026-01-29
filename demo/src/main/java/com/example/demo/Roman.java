package com.example.demo;
import java.util.*;

public class Roman {

	public int converter(String roman) {
		// I=1,	V=5, X=10, L=50, C=100
		Map<String, Integer> mapping = new HashMap<String, Integer>();
		mapping.put("I", 1);
		mapping.put("V", 5);
		mapping.put("X", 10);
		mapping.put("L", 50);
		mapping.put("C", 100);
		
		int result = 0;
		
		for(int i = 0; i < roman.length(); i++) {
			char ch = roman.charAt(i);
			Integer number = mapping.get(String.valueOf(ch));
			
			System.out.println(ch + " = " + number);
			
			if(number < 5) {
				result += number;
			}
			
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String text = "III"; // 1 to 100
		
		Roman r = new Roman();
		System.out.println(r.converter(text));
	}
	
}
