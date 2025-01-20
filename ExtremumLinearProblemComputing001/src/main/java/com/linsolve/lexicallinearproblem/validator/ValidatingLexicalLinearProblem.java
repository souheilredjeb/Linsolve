package com.linsolve.lexicallinearproblem.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.linsolve.lexicallinearproblem.scanner.ReadingLexicalLinearProblem;

public class ValidatingLexicalLinearProblem
{
	private  ReadingLexicalLinearProblem rllp;
	private  ArrayList<String> lines;
	private  ValidatingObjectiveFunction vof;
	private  ValidatingConstraintsSet vcss;
	private  Boolean valid_linear_problem;
	private  String linear_problem_formatted;
	
	public ValidatingLexicalLinearProblem() 
	{
		
		this.rllp=new ReadingLexicalLinearProblem();
		this.lines=rllp.getLines();
		LinkedHashMap<Integer,String> constraints=new LinkedHashMap<Integer,String>();
		String constraint=null;
		for(int i=2;i<this.lines.size()-1;i++)
		{
			constraint=lines.get(i).replaceAll(",", "");
			constraints.put(i, constraint);
		}
		this.vof=new ValidatingObjectiveFunction(this.lines.get(0));
		this.vof.validateObjectiveFunction();
		this.vcss= new ValidatingConstraintsSet(constraints);
		this.vcss.validatingConstraintsSet();
		this.valid_linear_problem=this.vof.getValid_objective_function() && this.vcss.getSet_valid();
	}
	
	public ValidatingLexicalLinearProblem(String linear_problem_formatted) {
		this.linear_problem_formatted = linear_problem_formatted;
		this.lines = new ArrayList<>(Arrays.asList(this.linear_problem_formatted.split("\n")));
		LinkedHashMap<Integer,String> constraints=new LinkedHashMap<Integer,String>();
		String constraint=null;
		for(int i=2;i<this.lines.size()-1;i++)
		{
			constraint=lines.get(i).replaceAll(",", "");
			constraints.put(i, constraint);
		}
		this.vof=new ValidatingObjectiveFunction(this.lines.get(0));
		this.vof.validateObjectiveFunction();
		this.vcss= new ValidatingConstraintsSet(constraints);
		this.vcss.validatingConstraintsSet();
		this.valid_linear_problem=this.vof.getValid_objective_function() && this.vcss.getSet_valid();
		
	}









	public static void display_objective_function_expression(HashMap<Integer,Double> exp)
	{
		for (Map.Entry<Integer, Double> entry : exp.entrySet()) 
	    {
	           System.out.print("Coefficient Index :"+entry.getKey()+"##"+"Coefficient value :"+entry.getValue());
	    }
		System.out.println();
	}

	public static void main(String[] args) 
	{
		
	}
	
	public ArrayList<String> getLines() 
	{
		return lines;
	}

	public void setLines(ArrayList<String> lines) 
	{
		this.lines = lines;
	}

	public Boolean getValid_linear_problem() 
	{
		return valid_linear_problem;
	}

	public void setValid_linear_problem(Boolean valid_linear_problem) 
	{
		this.valid_linear_problem = valid_linear_problem;
	}
}
