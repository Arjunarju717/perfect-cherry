package com.perfectcherry.rs.controller;

import java.util.HashMap;import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DuplicateCount {

	public static void main(String[] args) {
		findDuplicate("accommodation");
	}
	
	public static void findDuplicate(String str) {
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i=0; i<str.length(); i++) {
			Character c = str.charAt(i);
			Integer intVal = map.get(str.charAt(i));
			if(intVal == null) {
				map.put(str.charAt(i), 1);
			} else {
				map.put(str.charAt(i), intVal + 1);
			}
		}
		map.entrySet().stream().filter(s-> s.getValue()>1).collect(Collectors.toList()).forEach(System.out:: println);
		//System.out.println(map.entrySet());
	}

}
