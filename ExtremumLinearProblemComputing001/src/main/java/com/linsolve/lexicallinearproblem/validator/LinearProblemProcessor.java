package com.linsolve.lexicallinearproblem.validator;

import java.util.ArrayList;
import java.util.Arrays;

public class LinearProblemProcessor {

	public LinearProblemProcessor() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String linearProblem = """
		        Maximize Z = 7x1 + 4x2
		        subject to
		        x2 <= 20,
		        x1 + 3x2 <= 50,
		        4x1 + 2x2 <= 60,
		        x1 <= 25,
		        non negative constraints
		        """;
	     ArrayList<String> problemLines = new ArrayList<>(Arrays.asList(linearProblem.split("\n")));
	     for (int i = 0; i < problemLines.size(); i++) {
	            System.out.println("Line " + (i + 1) + ": " + problemLines.get(i));
	        }

	}

}
