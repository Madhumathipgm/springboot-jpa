package com.mylearning.springJpa.exception;

public class FindConstraints {

	public static boolean isExceptionUniqueConstrainerFor(String constraint) {
		if(constraint.equalsIgnoreCase("email")) {
			return true;
		}
		else if(constraint.equalsIgnoreCase("card_number")) {
			return true;
		}
		else {
			return false;
		}
	}

}
