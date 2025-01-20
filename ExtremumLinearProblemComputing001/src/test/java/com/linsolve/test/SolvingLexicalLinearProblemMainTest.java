package com.linsolve.test;

import org.junit.jupiter.api.Test;

import com.linsolve.linearproblemmain.SolvingLexicalLinearProblemMain;

import java.util.HashMap;

public class SolvingLexicalLinearProblemMainTest
{
	
	private SolvingLexicalLinearProblemMain sllpm =null;
	private Boolean valid=null;
	private HashMap<Integer,Double> solution_computed=new HashMap<Integer,Double>();
	private HashMap<Integer,Double> real_base_coefficient_solution=new HashMap<Integer,Double>();
	
	@Test public void t_001()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_1 = """
				Maximize Z = 7x1 + 4x2
				subject to
				x2 <= 20,
				x1 + 3x2 <= 50,
				4x1 + 2x2 <= 60,
				x1 <= 25,
				non negative constraints
				""";

		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_1);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmax().enumerate_based_variables_values();

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 6.0);
		real_base_coefficient_solution.put(1, 14.0);
		real_base_coefficient_solution.put(0, 8.0);
		real_base_coefficient_solution.put(5, 17.0);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));
	}
	
	
	@Test public void t_002()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_2="""
				Maximize Z = 2x1 + x2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_2);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmax().enumerate_based_variables_values();

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 5.0);
		real_base_coefficient_solution.put(3, 9.000000000000004);
		real_base_coefficient_solution.put(1, 5.0);
		real_base_coefficient_solution.put(0, 13.0);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));
		
	}
	
	@Test public void t_003()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_3="""
				Maximize Z = 4x1 + 3x2
				subject to
				x1 <=  12,
				3x1 + x2 <=  30,
				2x1 + 5x2 <=  50,
				x1 + x2 <=  20,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_3);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmax().enumerate_based_variables_values();

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 4.3076923076923075);
		real_base_coefficient_solution.put(0, 7.6923076923076925);
		real_base_coefficient_solution.put(1, 6.923076923076923);
		real_base_coefficient_solution.put(5, 5.384615384615384);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));
		
	}
	
	@Test public void t_004()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_4=
				"""
				Maximize Z = 3x1 + 4x2
				subject to
				x2 <= 20,
				x1 + 3x2 <= 50,
				4x1 + 2x2 <= 60,
				2x1 + x2 <= 30,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_4);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmax().enumerate_based_variables_values();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 5.999999999999998);
		real_base_coefficient_solution.put(1, 14.000000000000002);
		real_base_coefficient_solution.put(4, 0.0);
		real_base_coefficient_solution.put(0, 7.999999999999999);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));
	}
	
	@Test public void t_005()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_5=
				"""
				Minimize Z = 3x1 + 5x2
				subject to
				x1 <= 10,
				2x1 + 4x2 <= 50,
				3x1 + x2 <= 25,
				x2 <= 15,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_5);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmin().enumerate_based_variables_values();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 10.0);
		real_base_coefficient_solution.put(3, 50.0);
		real_base_coefficient_solution.put(4, 25.0);
		real_base_coefficient_solution.put(5, 15.0);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));
		
	}
	
	
	@Test public void t_006()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_6=
				"""
				Maximize Z = 2x1 + 3x2
				subject to
				x1 <= 12,
				3x1 + x2 <= 35,
				2x1 + 5x2 <= 60,
				x2 <= 10,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_6);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmax().enumerate_based_variables_values();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 3.1538461538461537);
		real_base_coefficient_solution.put(5, 1.5384615384615385);
		real_base_coefficient_solution.put(0, 8.846153846153847);
		real_base_coefficient_solution.put(1, 8.461538461538462);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));
		
	}
	
	@Test public void t_007()
	{
		//variables out of base are equals to 0		
		String linear_problem_formatted_7=		
				"""
				Minimize Z = x1 + x2
				subject to
				x1 + 2x2 <= 20,
				3x1 + x2 <= 25,
				x1 + 4x2 <= 40,
				x2 <= 15,
				non negative constraints
				""";
		
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_7);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmin().enumerate_based_variables_values();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 20.0);
		real_base_coefficient_solution.put(3, 25.0);
		real_base_coefficient_solution.put(4, 40.0);
		real_base_coefficient_solution.put(5, 15.0);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));
		
	}

	@Test public void t_008()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_8=		
				"""
				Maximize Z = 6x1 + 2x2
				subject to
				x1 <= 15,
				x2 <= 10,
				3x1 + 2x2 <= 45,
				2x1 + x2 <= 30,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_8);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmax().enumerate_based_variables_values();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 0.0);
		real_base_coefficient_solution.put(3, 10.0);
		real_base_coefficient_solution.put(4, 0.0);
		real_base_coefficient_solution.put(0, 15.0);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));	
	}
	
	@Test public void t_009()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_9=
				"""
				Maximize Z = 7x1 + 4x2
				subject to
				x2 <= 20,
				x1 + 3x2 <= 50,
				4x1 + 2x2 <= 60,
				x1 <= 25,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_9);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmax().enumerate_based_variables_values();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 6.0);
		real_base_coefficient_solution.put(1, 14.0);
		real_base_coefficient_solution.put(0, 8.0);
		real_base_coefficient_solution.put(5, 17.0);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));	
	}
	
	@Test public void t_010()
	{
				//variables out of base are equals to 0
				String linear_problem_formatted_10=
				"""
				Minimize Z = 4x1 + 6x2
				subject to
				x1 + x2 <= 30,
				3x1 + x2 <= 50,
				2x1 + 4x2 <= 80,
				x1 <= 20,
				non negative constraints
				""";
				
				try 
				{
					sllpm=new SolvingLexicalLinearProblemMain();
					sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_10);
					solution_computed=(HashMap<Integer, Double>) sllpm.getSspmin().enumerate_based_variables_values();
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				real_base_coefficient_solution = new HashMap<Integer, Double>();
				real_base_coefficient_solution.put(2, 30.0);
				real_base_coefficient_solution.put(3, 50.0);
				real_base_coefficient_solution.put(4, 80.0);
				real_base_coefficient_solution.put(5, 20.0);
				valid=sllpm.getValid();
				org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
				org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));	
	}
	
	@Test public void t_011()
	{
			//variables out of base are equals to 0
			String linear_problem_formatted_11=
			"""
			Maximize Z = 5x1 + 3x2
			subject to
			x1 <= 18,
			x2 <= 14,
			3x1 + x2 <= 45,
			2x1 + 5x2 <= 65,
			non negative constraints
			""";
			
			try 
			{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_11);
				solution_computed=(HashMap<Integer, Double>) sllpm.getSspmax().enumerate_based_variables_values();
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			real_base_coefficient_solution = new HashMap<Integer, Double>();
			real_base_coefficient_solution.put(2, 5.692307692307692);
			real_base_coefficient_solution.put(3, 5.923076923076923);
			real_base_coefficient_solution.put(0, 12.307692307692308);
			real_base_coefficient_solution.put(1, 8.076923076923077);
			valid=sllpm.getValid();
			org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
			org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));
	}
	
	@Test public void t_012()
	{
		//variables out of base are equals to 0
		String linear_problem_formatted_12=
				"""
				Minimize Z = 3x1 + x2
				subject to
				x2 <= 12,
				2x1 + 5x2 <= 50,
				x1 + x2 <= 20,
				3x1 + x2 <= 35,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_12);
			solution_computed=(HashMap<Integer, Double>) sllpm.getSspmin().enumerate_based_variables_values();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		real_base_coefficient_solution = new HashMap<Integer, Double>();
		real_base_coefficient_solution.put(2, 12.0);
		real_base_coefficient_solution.put(3, 50.0);
		real_base_coefficient_solution.put(4, 20.0);
		real_base_coefficient_solution.put(5, 35.0);
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(solution_computed.equals(real_base_coefficient_solution));
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.TRUE));	
	}
	
	@Test public void t_013()
	{
		String linear_problem_formatted_13=
				"""
				Maxmize Z = 2x1 + x2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_13);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_014()
	{
		String linear_problem_formatted_14=
				"""
				Minize Z = 2x1 + x2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
			sllpm=new SolvingLexicalLinearProblemMain();
			sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_14);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
		
	}
	
	@Test public void t_015()
	{
		String linear_problem_formatted_15=
				"""
				Maximize Z = 2x + x2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_15);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_016()
	{
		String linear_problem_formatted_16=
				"""
				Maximize Z = 2x1 + x_2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_16);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
				
	}
	
	@Test public void t_017()
	{
		String linear_problem_formatted_17=
				"""
				Maximize Z -> 2x1 + x2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_17);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_018()
	{
		String linear_problem_formatted_18=
				"""
				Maximize Z = 2x1 + x_2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_18);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_019()
	{
		String linear_problem_formatted_19=
				"""
				Maximize Z = 2x1 + x_2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_19);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_020()
	{
		String linear_problem_formatted_20=
				"""
				Maximize Z := 2x1 + x2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_20);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));	
	}
	
	@Test public void t_021()
	{
		String linear_problem_formatted_21=
				"""
				Maximize Z =
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_21);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_022()
	{
		String linear_problem_formatted_22=
				"""
				Maximize Z = 2x1 + x2@
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_22);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_023()
	{
		String linear_problem_formatted_23=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_23);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_024()
	{
		String linear_problem_formatted_24=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_24);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_025()
	{
		String linear_problem_formatted_25=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_25);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_026()
	{
		String linear_problem_formatted_26=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 5x2 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_26);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_027()
	{
		String linear_problem_formatted_27=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + 5x2 => 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_27);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_028()
	{
		String linear_problem_formatted_28=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + 5x2 == 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_28);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_029()
	{
		String linear_problem_formatted_29=
				"""
				Maximize Z = 2x1 + x2
				subject to
				x1 + 5x2 <=,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_29);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	
	
	@Test public void t_030()
	{
		String linear_problem_formatted_30=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + 5x2 <= sixty,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_30);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_031()
	{
		String linear_problem_formatted_31=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + 5x2 ≤ 60#,
				x1 + x2 ≤ 18,
				3x1 + x2 ≤ 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_31);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_032()
	{
				
		String linear_problem_formatted_32=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + 5x2 ≤ @60,
				x1 + x2 ≤ 18,
				3x1 + x2 ≤ 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_32);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
			
	}
	
	@Test public void t_033()
	{
		String linear_problem_formatted_33=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + 5x2 <= --60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_33);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));		
	}
	
	@Test public void t_034()
	{
				String linear_problem_formatted_34=
				"""
				Maximize Z = 2x1 + x2
				subject to
				(2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
				try 
				{
						sllpm=new SolvingLexicalLinearProblemMain();
						sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_34);	
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				valid=sllpm.getValid();
				org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
	@Test public void t_035()
	{
		String linear_problem_formatted_35=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + bx2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				Non-negative constraints
				""";
		try 
		{
				sllpm=new SolvingLexicalLinearProblemMain();
				sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_35);	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		valid=sllpm.getValid();
		org.junit.jupiter.api.Assertions.assertTrue(valid.equals(Boolean.FALSE));
	}
	
		
}
