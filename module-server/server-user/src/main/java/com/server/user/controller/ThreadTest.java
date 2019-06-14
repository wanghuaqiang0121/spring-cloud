package com.server.user.controller;

import java.util.Arrays;
import java.util.List;

public class ThreadTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
	
		List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(
			n -> {
				if (n.equals("Lambdas")) {
					
					System.out.println(n);
				}
			}
		);
		
		//features.forEach(System.out::println);
	}
}
