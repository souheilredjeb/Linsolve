package com.linsolve.lexicallinearproblem.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.linsolve.lexicallinearproblem.validator.components.Monome;
import com.linsolve.lexicallinearproblem.validator.exception.InputCheckerEnum;
import com.linsolve.lexicallinearproblem.validator.exception.InputValidatorException;

public class ValidatingObjectiveFunction 
{
	private String line;
	private Boolean valid_objective_function;
	
	public ValidatingObjectiveFunction() 
	{
		super();
	}

	public ValidatingObjectiveFunction(String line) 
	{
		this.line=line;
		this.valid_objective_function=null;
	}
	
	public void validatingObjectiveFunctionExtrema()
	{	
		line =line.replaceAll(" ", "");
		line=line.toUpperCase();									
		if(!(line.startsWith("MAXIMIZE") || line.startsWith("MINIMIZE")))
		{
			 try 
			 {
		        throw new InputValidatorException(InputCheckerEnum.OBJECTIVE_FUNCTION_EXTREMA_NOT_RECOGNIZED);
		     }
			 catch (InputValidatorException e)
			 {
		            System.out.println(e.getMessage());
		            this.valid_objective_function=Boolean.FALSE;       
			 }
		}
		else
		{
			if(line.startsWith("MAXIMIZE"))
			{	
				this.line=this.line.replace("MAXIMIZE", "");
			}
			else
			{
				this.line=this.line.replace("MINIMIZE", "");
			}
		}
	}
	
	public void validatingObjectiveFunctionVar()
	{
		if(line.startsWith("Z="))
		{
			this.line=this.line.replace("Z=", "");
		}
		else
		{
			 try 
			 {
				 throw new InputValidatorException(InputCheckerEnum.OBJECTIVE_FUNCTION_VAR_NOT_RECOGNIZED);
			 } 
			 catch (InputValidatorException e) 
			 {
				 System.out.println( e.getMessage());
				 this.valid_objective_function=Boolean.FALSE;
			 }
		}
	}
	
	public void validatingObjectiveFunctionExpression()
	{
		String[] t1;		
		this.line=this.line.replace("-", "+-");
		t1=this.line.split("\\+");
		for(int i=0;i<t1.length;i++)
		{
			if(t1[i].startsWith("X"))
			{
				t1[i]="1.0"+t1[i];
			}
			if(t1[i]!=null)
			{
				try
				{
					this.parsedXi(t1[i]);
				}
				catch (InputValidatorException e)
				{
					 System.out.println("Error: " + e.getMessage());
					 this.valid_objective_function=Boolean.FALSE;
				}
				
			}		
		}		
	}
	
	private Monome parsedXi(String input)
	{
		String regex = "(-?[\\d.]+)X(\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) 
		{
	       ;
		}
		else
		{
			  System.out.println("Input does not match the required pattern.");
			  try 
				 {
				  	throw new InputValidatorException(InputCheckerEnum.OBJECTIVE_FUNCTION_EXPRESSION_MALFORMATION);
				 } 
				 catch (InputValidatorException e) 
				 {
					 System.out.println("Error: " + e.getMessage());
					 this.valid_objective_function=Boolean.FALSE;	   
				 }	  
		}
		return null;
	}
	
	public void validateObjectiveFunction()
	{
		this.validatingObjectiveFunctionExtrema();
		this.validatingObjectiveFunctionVar();
		this.validatingObjectiveFunctionExpression();
		if(this.valid_objective_function==null)
		{
			this.valid_objective_function=Boolean.TRUE;
		}
	}
	
	public String getLine() 
	{
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public Boolean getValid_objective_function() {
		return valid_objective_function;
	}

	public void setValid_objective_function(Boolean valid_objective_function) {
		this.valid_objective_function = valid_objective_function;
	}

}
