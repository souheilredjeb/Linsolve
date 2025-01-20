package com.linsolve.lexicallinearproblem.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.linsolve.lexicallinearproblem.validator.components.Monome;
import com.linsolve.lexicallinearproblem.validator.exception.InputCheckerEnum;
import com.linsolve.lexicallinearproblem.validator.exception.InputValidatorException;


public class ValidatingSingleConstraint 
{
	private Integer line_number;
	private String line;
	private Boolean valid_constraint;

	public ValidatingSingleConstraint() 
	{
		this.line=null;
		this.valid_constraint=null;
	}

	public ValidatingSingleConstraint(String line) 
	{
		line=line.replaceAll(" ", "");
		this.line=line;
	}
	
	public void validateLexicalSingleConstraint()
	{
		String[] t0=null;
		String[] t1=null;		
		if(line.contains("<=") && !(line.contains(">=")))
		{
			if((line.indexOf("<=")==line.lastIndexOf("<=")) && (line.indexOf("=")==line.lastIndexOf("=")) )
			{	
				t0=line.split("<=");
				if(t0[0]==null)
				{
					try 
					 {
					  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_LEFT_MEMBER_ERROR);
					 } 
					 catch (InputValidatorException e) 
					 {
						 System.out.println(this.line+" "+ e.getMessage());
						 this.valid_constraint=Boolean.FALSE; 
					 }
				}
				else
				{
					t0[0]="+"+t0[0];
					t0[0]=t0[0].replaceAll("-", "+-");
					t1=t0[0].split("\\+");
					for(int i=1;i<t1.length;i++)
					{
						if(t1[i].startsWith("x"))
						{
							t1[i]=t1[i].replace("x", "1.0x");
						}
						if(t1[i].startsWith("-x"))
						{
							t1[i]=t1[i].replace("-x", "-1.0x");			
						}
						if(t1[i]!=null)
						{
							try
							{	
								this.parsedxi(t1[i]);
							}
							catch (InputValidatorException e) 
							{	 
								  System.out.println(this.line+" "+ e.getMessage());
								  this.valid_constraint=Boolean.FALSE;
							}
						}
					}
				}
				if(t0.length >1 && t0[1] !=null)
				{
					try
					{
						Double.valueOf(t0[1]);
					}
					catch(NumberFormatException e)
					{
						System.out.println("Invalid number format: '" + t0[1] + "' is not a valid number.");
						this.valid_constraint=Boolean.FALSE;
					}
				}
				else
				{
					try 
					 {
					  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_RIGHT_MEMBER_ERROR);
					 } 
					 catch (InputValidatorException e) 
					 {
						 System.out.println(this.line+" "+ e.getMessage());
						 this.valid_constraint=Boolean.FALSE;
					 }
				}
			}
			else
			{
				try 
				 {
				  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_COMPARING_OPERATOR_REDUNDUNCY);
				 } 
				 catch (InputValidatorException e) 
				 {
					 System.out.println(this.line+" "+ e.getMessage());
					 this.valid_constraint=Boolean.FALSE; 
				 }
			}
		}
		else if(line.contains(">=")&& !(line.contains("<=")))
		{	
			if(line.indexOf(">=")==line.lastIndexOf(">=") && line.indexOf("=")==line.lastIndexOf("="))
			{
					t0=line.split(">=");
					if(t0[0]==null)
					{
						try 
						 {
						  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_LEFT_MEMBER_ERROR);
						 } 
						 catch (InputValidatorException e) 
						 {
							 System.out.println(this.line+" "+ e.getMessage());
							 this.valid_constraint=Boolean.FALSE;
						 }
					}
					else
					{
						t0[0]="+"+t0[0];
						t0[0]=t0[0].replaceAll("-", "+-");
						t1=t0[0].split("\\+");
						for(int i=1;i<t1.length;i++)
						{
							if(t1[i].startsWith("x"))
							{
								t1[i]=t1[i].replace("x", "1.0x");
							}
							if(t1[i].startsWith("-x"))
							{
								t1[i]=t1[i].replace("x", "-1.0x");
							}
							if(t1[i]!=null)
							{
								try
								{
									this.parsedxi(t1[i]);
								}
								 catch (InputValidatorException e) 
								 {
									  System.out.println(this.line+" "+ e.getMessage());
									  this.valid_constraint=Boolean.FALSE;
								 }
							}		
						}
					}
					if(t0.length >1 && t0[1] !=null)
					{
						try
						{
							Double.valueOf(t0[1]);
						}
						catch(NumberFormatException e)
						{
							System.out.println("Invalid number format: '" + t0[1] + "' is not a valid number.");
							this.valid_constraint=Boolean.FALSE;
						}
					}
					else
					{
						try 
						 {
						  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_RIGHT_MEMBER_ERROR);
						 } 
						 catch (InputValidatorException e) 
						 {
							 System.out.println(this.line+" "+ e.getMessage());
							 this.valid_constraint=Boolean.FALSE;
						 }
					}
			}
			else
			{
				try 
				 {
				  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_COMPARING_OPERATOR_REDUNDUNCY);
				 } 
				 catch (InputValidatorException e) 
				 {
					  System.out.println(this.line+" "+ e.getMessage());
					  this.valid_constraint=Boolean.FALSE;  
				 }
			}
		}
		else if(line.contains("=") &&  !(line.contains("<")||line.contains(">")))
		{
			if(line.indexOf("=")==line.lastIndexOf("="))
			{
					t0=line.split("=");
					if(t0[0]==null)
					{
						try 
						 {
						  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_LEFT_MEMBER_ERROR);
						 } 
						 catch (InputValidatorException e) 
						 {
							 System.out.println(this.line+" "+ e.getMessage());
							 this.valid_constraint=Boolean.FALSE;
						 }
					}
					else
					{
						t0[0]="+"+t0[0];
						t0[0]=t0[0].replaceAll("-", "+-");
						t1=t0[0].split("\\+");
						for(int i=1;i<t1.length;i++)
						{
							if(t1[i].startsWith("x"))
							{
								t1[i]="1"+t1[i];
							}
							if(t1[i].startsWith("-x"))
							{
								t1[i]="-1"+t1[i];
							}
							if(t1[i]!=null)
							{
								try
								{
									this.parsedxi(t1[i]);
								}
								catch(InputValidatorException e)
								{
									System.out.println(this.line+" "+ e.getMessage());
									this.valid_constraint=Boolean.FALSE;
								}
							}		
						}
					}
					if(t0.length >1 && t0[1] !=null)
					{
						try
						{
							Double.valueOf(t0[1]);
						}
						catch(NumberFormatException e)
						{
							System.out.println("Invalid number format: '" + t0[1] + "' is not a valid number.");
							this.valid_constraint=Boolean.FALSE;
						}
					}
					else
					{
						try 
						 {
						  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_RIGHT_MEMBER_ERROR);
						 } 
						 catch (InputValidatorException e) 
						 {
							 System.out.println(this.line+" "+ e.getMessage());
							 this.valid_constraint=Boolean.FALSE;
						 }
					}
			}
			else
			{
				try 
				 {
				  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_COMPARING_OPERATOR_REDUNDUNCY);
				 } 
				 catch (InputValidatorException e) 
				 {
					  System.out.println(this.line+" "+ e.getMessage());
					  this.valid_constraint=Boolean.FALSE; 
				 }
			}
		}
		else
		{
			try 
			 {
			  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_COMPARING_OPERATOR_ERROR);
			 } 
			 catch (InputValidatorException e) 
			 {
				 System.out.println(this.line+" "+ e.getMessage());
				 this.valid_constraint=Boolean.FALSE;
			 }
		}
		if(this.valid_constraint==null)
		{
			this.valid_constraint=Boolean.TRUE;
		}
	}
	
	private Monome parsedxi(String input)
	{
		String regex = "(-?[\\d.]+)x(\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		Monome m= null;
		if (matcher.matches()) 
		{
	        Double doubleValue = Double.parseDouble(matcher.group(1));
	        Integer intValue = Integer.parseInt(matcher.group(2));
	        m= new Monome(intValue, doubleValue);
	        return m;
		}
		else
		{
			  System.out.println("Input does not match the required pattern.");
			  try 
				 {
				  	throw new InputValidatorException(InputCheckerEnum.CONSTRAINTS_MALFORMATION_ERROR);
				 } 
				 catch (InputValidatorException e) 
				 {
					 System.out.println(this.line +"\t"+input+"\t"+e.getMessage());
					 this.valid_constraint=Boolean.FALSE;
				 }
			  return null;
		}
	}
	public String getLine() {
		return line;
	}

	public void setLine(String line) 
	{
		line=line.replaceAll(" ", "");
		this.line = line;
	}

	public Integer getLine_number() {
		return line_number;
	}

	public void setLine_number(Integer line_number) {
		this.line_number = line_number;
	}
	
	public Boolean getValid() {
		return valid_constraint;
	}

	public void setValid(Boolean valid) {
		this.valid_constraint = valid;
	}

}
