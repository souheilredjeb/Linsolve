package com.linsolve.lexicallinearproblem.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.linsolve.lexicallinearproblem.converter.components.AlgebraicLinearProblem;
import com.linsolve.lexicallinearproblem.converter.components.ExtremumEnum;
import com.linsolve.lexicallinearproblem.converter.components.Monome;
import com.linsolve.lexicallinearproblem.converter.components.PhaseEnum;

public class ConvertingLexicalLinearProblem 
{
	private String lexical_linear_problem;
	private String extremum_lexical;	
	private String objective_function_lexical;
	private String constraints_lexical_expressions;
	private String single_constraint_lexical_expression;	
	private Double[] c_vector=null;
	private Double[] b_vector=null;
	private Double[][] initial_matrix=null;;
	private ExtremumEnum extrema=null;;
	private PhaseEnum phase=null;
	private HashMap<Integer,Double> expression = new HashMap<Integer,Double>();
	private ArrayList<HashMap<Integer,Double>> expressions=new ArrayList<HashMap<Integer,Double>>();
	private ArrayList<Double> b_arraylist=new ArrayList<Double>();
	private AlgebraicLinearProblem alp;
	
	public ConvertingLexicalLinearProblem() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public ConvertingLexicalLinearProblem(String lexical_linear_problem) {
		super();
		this.lexical_linear_problem = lexical_linear_problem;
		this.lexical_linear_problem=this.lexical_linear_problem.replaceAll(" ", "");
		this.alp=new AlgebraicLinearProblem();
		
	}
	
	public Boolean subjecttoKeywordIsPresent()
	{
		this.lexical_linear_problem =this.lexical_linear_problem.replaceAll(" ", "");
		return this.lexical_linear_problem.contains("subjectto");
	}
	
	
	public void formulateLexicalObjectiveFunctionToCheck()
	{
		String[] t1=new String[2];	
		if(this.subjecttoKeywordIsPresent())
		{
			t1=this.lexical_linear_problem.split("subjectto");
			this.objective_function_lexical=t1[0];
			this.constraints_lexical_expressions=t1[1];
		}
		 return ;
	}
	
	
	public void convertLexicalObjectiveFunction()
	{
		String[] t0=null;
		String[] t1=null;	
		Monome m=new Monome();
		HashMap<Integer,Double> exp=new HashMap<Integer,Double>();
		if(this.objective_function_lexical.toUpperCase().startsWith("MAXIMIZE"))
		{
			this.extrema=ExtremumEnum.MAXIMIZE;
			this.alp.setExtrema(extrema);
			this.extremum_lexical="MAXIMIZE";
			this.objective_function_lexical=this.objective_function_lexical.toUpperCase().replace("MAXIMIZE", "");
			if(this.objective_function_lexical.contains("="))
			{
				t0=this.objective_function_lexical.split("=");
				this.objective_function_lexical=t0[1];
				this.objective_function_lexical="+"+this.objective_function_lexical;
				this.objective_function_lexical=this.objective_function_lexical.replace("-", "+-");
				t1=this.objective_function_lexical.split("\\+");
				for(int i=1;i<t1.length;i++)
				{
					if(t1[i].startsWith("X"))
					{
						t1[i]="1.0"+t1[i];
					}
					
					if(t1[i]!=null)
					{
						m=this.parsedXi(t1[i]);
						exp.put(m.getCoefficient_index(), m.getCoefficient_value());
					}		
				}			
			}
			this.c_vector= new Double[exp.size()];
		    for (Map.Entry<Integer, Double> entry : exp.entrySet()) 
		    {
		            this.c_vector[entry.getKey()-1]=entry.getValue();
		    }
		}
		else if(this.objective_function_lexical.toUpperCase().startsWith("MINIMIZE"))
		{
			this.extrema=ExtremumEnum.MINIMIZE;
			this.alp.setExtrema(extrema);
			this.extremum_lexical="MINIMIZE";
			this.objective_function_lexical=this.objective_function_lexical.toUpperCase().replace("MINIMIZE", "");
			if(this.objective_function_lexical.contains("="))
			{
				t0=this.objective_function_lexical.split("=");
				this.objective_function_lexical=t0[1];
				this.objective_function_lexical="+"+this.objective_function_lexical;
				this.objective_function_lexical=this.objective_function_lexical.replace("-", "+-");
				
				t1=this.objective_function_lexical.split("\\+");
				for(int i=1;i<t1.length;i++)
				{
					if(t1[i].startsWith("X"))
					{
						t1[i]="1.0"+t1[i];
					}
					if(t1[i].startsWith("-X"))
					{
						t1[i]=t1[i].replace("-X","-1.0X");
					}
					if(t1[i]!=null)
					{
						m=this.parsedXi(t1[i]);
						if(m!=null)
						exp.put(m.getCoefficient_index(), m.getCoefficient_value());
					}		
				}			
			}
			this.c_vector= new Double[exp.size()];
		    for (Map.Entry<Integer, Double> entry : exp.entrySet()) 
		    {
		            this.c_vector[entry.getKey()-1]=entry.getValue();
		    }
		}	
	}
	
	public void convertConstraintsLexicalExpressions()
	{
		String[] t0=this.constraints_lexical_expressions.split(",");
		for(int i=0;i<t0.length;i++)
		{
			this.convertSingleConstraintLexicalExpressions(t0[i]);
		}
		if(this.alp.getPhase()==null)
		{
			this.alp.setPhase(PhaseEnum.SINGLE_PHASE);
		}	
	}
	
	public void convertSingleConstraintLexicalExpressions(String constraint)
	{
		String[] t0;
		String[] t1;		
		Monome monome=new Monome();
	    expression=new HashMap<Integer,Double>();
		if(constraint.contains("<="))
		{
			t0=constraint.split("<=");
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
					monome=this.parsedxi(t1[i]);
					if(monome !=null)
					expression.put(monome.getCoefficient_index(), monome.getCoefficient_value());
				}		
			}
			expressions.add(expression);
			if(t0.length >1 && t0[1] !=null)
			{
				b_arraylist.add(Double.valueOf(t0[1]));	
			}
			else
			{
				;
			}
		}
		else if(constraint.contains(">="))
		{
			this.alp.setPhase(PhaseEnum.DOUBLE_PHASE);
			t0=constraint.split(">=");
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
					monome=this.parsedxi(t1[i]);
					if(monome !=null)
					expression.put(monome.getCoefficient_index(), monome.getCoefficient_value());
				}		
			}
			expressions.add(expression);
			b_arraylist.add(-Double.valueOf(t0[1]));
		}
		else if(constraint.contains("="))
		{
			this.alp.setPhase(PhaseEnum.DOUBLE_PHASE);
			t0=constraint.split("=");
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
					t1[i]="-1.0"+t1[i];
				}
				if(t1[i]!=null)
				{
					monome=this.parsedxi(t1[i]);
					if(monome !=null)
					expression.put(monome.getCoefficient_index(), monome.getCoefficient_value());
				}		
			}
			expressions.add(expression);
			b_arraylist.add(-Double.valueOf(t0[1]));	
			expressions.add(expression);
			b_arraylist.add(Double.valueOf(t0[1]));
		}
		
	}
	
	public AlgebraicLinearProblem convertLexicalLinearToAlgebraicLinearProblem()
	{
		this.formulateLexicalObjectiveFunctionToCheck();
		this.convertLexicalObjectiveFunction();
		this.convertConstraintsLexicalExpressions();
		this.buildAlgebraicLinearProblem();	
		return this.alp;
	}

	

	
	
	
	public void buildAlgebraicLinearProblem()
	{
		Double[] b= new Double[this.b_arraylist.size()];
		for(int i=0; i<this.b_arraylist.size();i++)
		{
			b[i]=this.b_arraylist.get(i);
		}
		this.alp.setB_vector(b);
		this.alp.setC_vector(c_vector);
		Double[][] initial_matrix=new Double[this.b_arraylist.size()][this.c_vector.length];
		for(int i=0;i<initial_matrix.length;i++)
		{
			for(int j=0;j<initial_matrix[0].length;j++)
			{
				initial_matrix[i][j]=0.0;
			}
		}
		
		for(int i=0;i<this.expressions.size();i++)
		{
			HashMap<Integer,Double> hm= this.expressions.get(i);	
			for (Map.Entry<Integer, Double> entry : hm.entrySet()) 
		    {
				initial_matrix[i][entry.getKey()-1]=entry.getValue();
		    }
		}
		this.alp.setInitial_matrix(initial_matrix);
	}
	
	private Monome parsedXi(String input)
	{
		String regex = "(-?[\\d.]+)X(\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		Monome m= null;
		if (matcher.matches()) 
		{
	            // Extract the Double value (group 1) and the Integer value (group 2)
	        Double doubleValue = Double.parseDouble(matcher.group(1));
	        Integer intValue = Integer.parseInt(matcher.group(2));
	        m= new Monome(intValue, doubleValue);
	        return m;
		}
		else
		{
			  System.out.println("Input does not match the required pattern.");
			  return null;
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
	            // Extract the Double value (group 1) and the Integer value (group 2)
	        Double doubleValue = Double.parseDouble(matcher.group(1));
	        Integer intValue = Integer.parseInt(matcher.group(2));
	        m= new Monome(intValue, doubleValue);
	        return m;
		}
		else
		{
			  System.out.println("Input does not match the required pattern.");
			  return null;
		}
	}

	public static void main(String[] args) 
	{

	}
	
	public static void display(Double[][] matrix)
	{
		System.out.println();
		for(int i=0; i<matrix.length; i++)
		{
			for(int j=0;j<matrix[0].length;j++)
			{
				System.out.print(matrix[i][j]);
				System.out.print("|");
			}
			System.out.println();
		}
	}
	public static void display(Object[] vector)
	{
		System.out.println();
		System.out.print("[");
		for(int i=0 ; i<vector.length;i++)
		{
			System.out.print(vector[i]);
			System.out.print("|");
		}
		System.out.print("]");
		System.out.println();
	}
	
	public static void display_b( ArrayList<Double> b_arraylist)
	{
		for(int i=0; i< b_arraylist.size();i++)
		{
			System.out.print(b_arraylist.get(i)+"##");
		}
		System.out.println();
		
	}
	
	public static void display_constraints(ArrayList<HashMap<Integer,Double>> expressions)
	{
		for(int i=0;i<expressions.size();i++)
		{
			display_single_constraint(expressions.get(i));
		}	 	
	}
	
	public static void display_single_constraint(HashMap<Integer,Double> exp)
	{
		for (Map.Entry<Integer, Double> entry : exp.entrySet()) 
	    {
	           System.out.print("Coefficient Index :"+entry.getKey()+"##"+"Coefficient value :"+entry.getValue());
	    }
		System.out.println();
	}
	
	public ArrayList<HashMap<Integer, Double>> getExpressions() {
		return expressions;
	}

	public void setExpressions(ArrayList<HashMap<Integer, Double>> expressions) {
		this.expressions = expressions;
	}

	public ArrayList<Double> getB_arraylist() {
		return b_arraylist;
	}

	public void setB_arraylist(ArrayList<Double> b_arraylist) {
		this.b_arraylist = b_arraylist;
	}

	public String getLexical_linear_problem() {
		return lexical_linear_problem;
	}

	public void setLexical_linear_problem(String lexical_linear_problem) {
		this.lexical_linear_problem = lexical_linear_problem;
	}
	
	public String getExtremum_lexical() {
		return extremum_lexical;
	}

	public void setExtremum_lexical(String extremum_lexical) {
		this.extremum_lexical = extremum_lexical;
	}

	public String getObjective_function_lexical() {
		return objective_function_lexical;
	}

	public void setObjective_function_lexical(String objective_function_lexical) {
		this.objective_function_lexical = objective_function_lexical;
	}

	public String getConstraints_lexical_expressions() {
		return constraints_lexical_expressions;
	}

	public void setConstraints_lexical_expressions(String constraints_lexical_expressions) {
		this.constraints_lexical_expressions = constraints_lexical_expressions;
	}

	public String getSingle_constraint_lexical_expression() {
		return single_constraint_lexical_expression;
	}

	public void setSingle_constraint_lexical_expression(String single_constraint_lexical_expression) {
		this.single_constraint_lexical_expression = single_constraint_lexical_expression;
	}

	public Double[] getC_vector() {
		return c_vector;
	}

	public void setC_vector(Double[] c_vector) {
		this.c_vector = c_vector;
	}

	public Double[] getB_vector() {
		return b_vector;
	}

	public void setB_vector(Double[] b_vector) {
		this.b_vector = b_vector;
	}


	public Double[][] getInitial_matrix() {
		return initial_matrix;
	}

	public void setInitial_matrix(Double[][] initial_matrix) {
		this.initial_matrix = initial_matrix;
	}

	public HashMap<Integer, Double> getExpression() {
		return expression;
	}

	public void setExpression(HashMap<Integer, Double> expression) {
		this.expression = expression;
	}

	public AlgebraicLinearProblem getAlp() {
		return alp;
	}

	public void setAlp(AlgebraicLinearProblem alp) {
		this.alp = alp;
	}

	public ExtremumEnum getExtrema() {
		return extrema;
	}

	public void setExtrema(ExtremumEnum extrema) {
		this.extrema = extrema;
	}

	public PhaseEnum getPhase() {
		return phase;
	}

	public void setPhase(PhaseEnum phase) {
		this.phase = phase;
	}

}
