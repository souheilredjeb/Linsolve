package com.linsolve.test;


import org.junit.jupiter.api.Test;
import java.util.HashMap;

import com.linsolve.linearproblemmain.SolvingLinearProblemMain;

public class SolvingLinearProblemMainSinglePhaseDoublePhaseTest
{
	private SolvingLinearProblemMain slpm=null;
	private HashMap<Integer,Double> solution_computed=new HashMap<Integer,Double>();
	private HashMap<Integer,Double> real_base_coefficient_solution=new HashMap<Integer,Double>();
	
	@Test public void t_001()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input1="Maximize Z= 2x1 + x2"+
				"subject to"+
				"x2<=10,"+
				"2x1+5x2<=60,"+
				"x1+x2<=18,"+
				"3x1+x2<=44,"+
				"non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input1);
			solution_computed=(HashMap<Integer, Double>) slpm.getSspmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(2, 5.0);
		real_base_coefficient_solution.put(3,9.000000000000004);
		real_base_coefficient_solution.put(1, 5.0);
		real_base_coefficient_solution.put(0, 13.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
	}
	
	@Test public void t_002()
	{
		solution_computed=new HashMap<Integer,Double>();		
		String input2="Maximize Z= 10x1+20x2"+
				"subject to"+
				"-x1+2x2 <=15,"+
				"x1+x2<=12,"+
				"5x1+3x2<=45,"+ 
				"non negative constraints";
		
		try
		{
			slpm = new SolvingLinearProblemMain(input2);
			solution_computed=(HashMap<Integer, Double>) slpm.getSspmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(1, 9.0);
		real_base_coefficient_solution.put(0,3.0);
		real_base_coefficient_solution.put(4, 3.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
	}
	
	
	
	@Test public void t_003()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input3="Minimize Z=3x1-6x2"
				+"subject to"
				+"-x1-2x2 <=1 ,"
				+"-2x1-x2 <=0 ,"
				+"-x1+x2 <=1,"
				+"-x1 +4x2 <=13 ,"
				+"4x1-x2 <=23,"
				+ "non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input3);
			solution_computed=(HashMap<Integer, Double>) slpm.getSspmin().enumerate_based_variables_values();		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(2, 12.0);
		real_base_coefficient_solution.put(3,10.0);
		real_base_coefficient_solution.put(1, 4.0);
		real_base_coefficient_solution.put(0, 3.0);
		real_base_coefficient_solution.put(6, 15.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
	}
	
	@Test public void t_004()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input4="Maximize Z=500x1+300x2"
				+"subject to"
				+"15x1+5x2<=300,"
				+"10x1 +6x2<=240,"
				+"8x1+12x2<=450,"
				+"non negative constraints";	
		try
		{
			slpm = new SolvingLinearProblemMain(input4);
			solution_computed=(HashMap<Integer, Double>) slpm.getSspmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(0, 15.0);
		real_base_coefficient_solution.put(1,14.999999999999998);
		real_base_coefficient_solution.put(4, 150.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));	
	}
	
	@Test public void t_005()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input5 ="Maximize Z=5x1+7x2"
						+"subject to"
						+"-2x1+x2>=1,"
						+"x1-2x2>=1,"
						+"non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input5);
			solution_computed=(HashMap<Integer, Double>) slpm.getSdpmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(4, 1.0);
		real_base_coefficient_solution.put(5, 1.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));	
			
	}
	
	@Test public void t_006()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input6="Maximize Z=15x1+20x2"
				+"subject to"
				+"x1+2x2>=10,"
				+"2x1-3x2<=6,"
				+"x1+x2>=6,"			
				+"non negative constraints";

		try
		{
			slpm = new SolvingLinearProblemMain(input6);
			solution_computed=(HashMap<Integer, Double>) slpm.getSdpmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(1, 4.0);
		real_base_coefficient_solution.put(5, 14.0);
		real_base_coefficient_solution.put(0, 2.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));		
	}
	
	@Test public void t_007()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input7="Minimize Z=3x1+2x2"+
				"subject to"+
				"2x1+3x2>=12,"+
				"2x1+x2>=8,"+
				"x1 +2x2<=12,"+
				"2x1+3x2<=12,"+
				"non negative constraints";
		
		String input77="Minimize Z=3x1+2x2"+
				"subject to"+
				"x1 +2x2<=12,"+
				"2x1+3x2<=12,"+
				"2x1+x2>=8,"+
				"2x1+3x2>=12,"+
				
				
				"non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input7);
			solution_computed=(HashMap<Integer, Double>) slpm.getSdpmin().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(0, 3.0);
		real_base_coefficient_solution.put(1,2.0);
		real_base_coefficient_solution.put(4, 5.0);
		real_base_coefficient_solution.put(7, 0.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));

	}
	
	@Test public void t_008()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input8="Minimize Z=40x1+50x2"
				+"subject to"
				+"2x1+3x2>=30,"
				+"x1+x2>=12,"
				+"2x1+x2>=20,"
				+"non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input8);
			solution_computed=(HashMap<Integer, Double>) slpm.getSdpmin().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(3, 0.5);
		real_base_coefficient_solution.put(1,5.0);
		real_base_coefficient_solution.put(0,7.5);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));	
	}
	
	@Test public void t_009()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input9="Maximize Z=x1+2x2"
			    +"subject to"
			    +"x1<=2,"
			    +"x2<=2,"
			    +"x1+x2<=3,"
			    +"non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input9);
			solution_computed=(HashMap<Integer, Double>) slpm.getSspmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(2,1.0);
		real_base_coefficient_solution.put(1,2.0);
		real_base_coefficient_solution.put(0,1.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		
	}
	
	@Test public void t_010()
	{
		solution_computed=new HashMap<Integer,Double>();	
		String input10="Maximize Z= 3x1+2x2"
						+"subject to"
						+"2x1+x2<=6,"
						+"x1+2x2<=6,"
						+"non negative constraints";
		
		try
		{
			slpm = new SolvingLinearProblemMain(input10);
			solution_computed=(HashMap<Integer, Double>) slpm.getSspmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(0,2.0);
		real_base_coefficient_solution.put(1,2.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));	
	}
	
	@Test public void t_011()
	{
		solution_computed=new HashMap<Integer,Double>();
		
		String input11="Maximize Z=x1+2x2"
						+"subject to"
						+"x1+3x2<=8,"
						+"x1+x2<=4,"
						+"non negative constraints";
		
		try
		{
			slpm = new SolvingLinearProblemMain(input11);
			solution_computed=(HashMap<Integer, Double>) slpm.getSspmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	@Test public void t_012()
	{
		solution_computed=new HashMap<Integer,Double>();
		
		String input12="Maximize Z=3x1+2x2"
					   +"subject to"
					   +"x1<=4,"
					   +"x1+3x2<=15,"
					   +"2x1+x2<=10,"
					   +"non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input12);
			solution_computed=(HashMap<Integer,Double>) slpm.getSspmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(0,3.0);
		real_base_coefficient_solution.put(2,1.0);
		real_base_coefficient_solution.put(1,4.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));	
	}
	
	
	@Test public void t_013()
	{
		solution_computed=new HashMap<Integer,Double>();	
		String input13="Maximize Z=2x1+3x2"
					+"subject to"
					+"-3x1+x2<=1,"
					+"4x1+2x2<=20,"
					+"4x1-x2<=10,"
					+"-x1+2x2<=5,"
					+"non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input13);
			solution_computed=(HashMap<Integer,Double>) slpm.getSspmax().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(1,4.000000000000001);
		real_base_coefficient_solution.put(2,6.0);
		real_base_coefficient_solution.put(4,2.0000000000000018);
		real_base_coefficient_solution.put(0,3.0000000000000004);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));	
		
	}
	
	@Test public void t_014()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input14="Minimize Z=5x1+7x2"
					  +"subject to"
					  +"2x1+3x2>=42,"
					  +"3x1+4x2>=60,"
					  +"x1+x2 >=18,"
					  +"non negative constraints";
		
		try
		{
			slpm = new SolvingLinearProblemMain(input14);
			solution_computed=(HashMap<Integer,Double>) slpm.getSdpmin().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(1,6.000000000000001);
		real_base_coefficient_solution.put(0,11.999999999999998);
		real_base_coefficient_solution.put(3,1.3322676295501882E-15);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));	
					  
	}
		
	@Test public void t_015()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input27="Minimize Z=3x1+2x2+4x3"
				+ "subject to"
				+ "3x1+3x2+5x3>=120,"
				+ "2x1+x2+3x3=60,"
				+ "non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input27);
			solution_computed=(HashMap<Integer, Double>) slpm.getSdpmin().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(1,14.999999999999998 );
		real_base_coefficient_solution.put(2,15.0);
		real_base_coefficient_solution.put(5, 0.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
	}
	
	@Test public void t_016()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input28="Minimize Z=3x1+2x2+7x3"
				+ "subject to"
				+ "-x1+x2>=10,"
				+ "2x1-x2+x3>=10,"
				+ "-x1+x2<=10,"
				+ "non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input28);
			solution_computed=(HashMap<Integer, Double>) slpm.getSdpmin().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(1,30.0 );
		real_base_coefficient_solution.put(0,20.0);
		real_base_coefficient_solution.put(7, 0.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
	}
	
	
	
	@Test public void t_017()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input30="Minimize  Z=3x1+2x2+x3"
				+ "subject to"
				+ "3x1+x2+x3>=10,"
				+ "x1+x2=7,"
				+ "non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input30);
			solution_computed=(HashMap<Integer, Double>) slpm.getSdpmin().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	    real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(0, 1.5000000000000004);
		real_base_coefficient_solution.put(1,5.499999999999999);
		real_base_coefficient_solution.put(7, 0.0);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
	}
	
	@Test public void t_018()
	{
		solution_computed=new HashMap<Integer,Double>();
		String input34="Minimize Z=2x1+3x2+x3"+
				"subject to"+
				"-x1+4x2+2x3>=8,"+
				"3x1+2x2>=6,"+
				"non negative constraints";
		try
		{
			slpm = new SolvingLinearProblemMain(input34);
			solution_computed=(HashMap<Integer, Double>) slpm.getSdpmin().enumerate_based_variables_values();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		real_base_coefficient_solution=new HashMap<Integer,Double>();
		real_base_coefficient_solution.put(0, 4.0/7);
		real_base_coefficient_solution.put(1, 15.0/7);
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
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
		for(int i=0 ; i<vector.length;i++)
		{
			System.out.print(vector[i]);
			System.out.print("|");
		}
		System.out.println();
	}

}
