package com.linsolve.lexicallinearproblem.scanner.components;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputReading
{
	public ConsoleInputReading()
	{
	
	}

	public  ArrayList<String> reading_lexical_linear_problem()
	{
		System.out.print("Please enter your linear problem: ");
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		List<String> lines = new ArrayList<>();
		while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            lines.add(line);
        }
        System.out.println("\nYou entered:");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println("Line " + (i+1) + ": " + lines.get(i));
        } 
        scanner.close();
        return (ArrayList<String>) lines;
	}
	
	public ArrayList<String> reading_lexical_linear_problem_console()
	{
		System.out.print("Please enter your linear problem: ");
		System.out.println();
		Console console = System.console();
		if (console == null) {
            System.out.println("No console available.");
            return null;
        }
		List<String> lines = new ArrayList<>();
		while (true) {
			String line = console.readLine();
            if (line.isEmpty()) {
                break;
            }
            lines.add(line);
        }
		System.out.println("\nYou entered:");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println("Line " + (i + 1) + ": " + lines.get(i));
        }
		return (ArrayList<String>) lines;
	}
	
	public static void main(String[] args)
	{

	}
}
