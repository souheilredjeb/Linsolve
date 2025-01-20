package com.linsolve.lexicallinearproblem.scanner;

import java.util.ArrayList;

import com.linsolve.lexicallinearproblem.scanner.components.ConsoleInputReading;

public class ReadingLexicalLinearProblem 
{
	private ConsoleInputReading cir;
	private ArrayList<String> lines;

	public ReadingLexicalLinearProblem()
	{
		this.cir= new ConsoleInputReading();
		this.lines=new ArrayList<String>();
		this.lines=cir.reading_lexical_linear_problem();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ArrayList<String> getLines() {
		return lines;
	}

	public void setLines(ArrayList<String> lines) {
		this.lines = lines;
	}
	
	

}
