package com.linsolve.commonoperations;

import java.util.HashMap;

public class Phase_I_Phase_II_Test
{

	public Phase_I_Phase_II_Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		
		/*Double[][] initial_matrix={{1.0,1.0},{4.0,1.0},{2.0,3.0}};
		Double[] b_vector= {5.0,-4.0,-3.0};
		Double[] c_vector= {8.0,5.0};*/
		
		/*Double[][] initial_matrix={{3.0,1.0},{-2.0,1.0},{1.0,2.0}};
		Double[] b_vector= {9.0,4.0,-2.0};
		Double[] c_vector= {3.0,4.0};
		
		
		Double[][] initial_matrix1={{3.0,1.0},{-2.0,1.0},{1.0,2.0}};
		Double[] b_vector1= {9.0,4.0,-2.0};
		Double[] c_vector1= {3.0,4.0};
		*
		*
		*
		*
		*
		*
		*/
		
		
		Double[][] initial_matrix1={{1.0,1.0},{4.0,1.0},{2.0,3.0}};
		Double[] b_vector1= {5.0,-4.0,-3.0};
		Double[] c_vector1= {8.0,5.0};
		
		BuildingAugmentedMatrix bam= new BuildingAugmentedMatrix(initial_matrix1, b_vector1, c_vector1);
		SolvingPhase_I_LP solver_phase_I= new SolvingPhase_I_LP(bam);
		solver_phase_I.reevaluate_base();
		solver_phase_I.reevaluate_zj_cj();
		solver_phase_I.apply_simplex_phase_I();	
	    solver_phase_I.reevaluate_augmented_matrix();
	    solver_phase_I.reevaluate_base_phase_II();
	    solver_phase_I.reevaluate_zj_cj_phase_II();
	    display(solver_phase_I.getAugmented_matrix());    
	    SolvingPhase_II_LP solver_phase_II= new SolvingPhase_II_LP(solver_phase_I);  
	   // solver_phase_II.apply_simplex_phase_II(); 
	    solver_phase_II.apply_simplex_phase_II();
	    display(solver_phase_II.getZj_cj_phase_II());
	    display(solver_phase_II.getBase());
	    display(solver_phase_II.getAugmented_matrix_phase_II());
	    solver_phase_II.compute_goal_function();
	    solver_phase_II.enumerate_based_variables_values();	
	    

		Double[][] initial_matrix2={{3.0,1.0},{-2.0,1.0},{1.0,2.0}};
		Double[] b_vector2= {9.0,4.0,-2.0};
		Double[] c_vector2= {3.0,4.0};
		
		bam= new BuildingAugmentedMatrix(initial_matrix2, b_vector2, c_vector2);
		solver_phase_I= new SolvingPhase_I_LP(bam);
		solver_phase_I.reevaluate_base();
		solver_phase_I.reevaluate_zj_cj();
		solver_phase_I.apply_simplex_phase_I();	
	    solver_phase_I.reevaluate_augmented_matrix();
	    solver_phase_I.reevaluate_base_phase_II();
	    solver_phase_I.reevaluate_zj_cj_phase_II();
	    display(solver_phase_I.getAugmented_matrix());    
	     solver_phase_II= new SolvingPhase_II_LP(solver_phase_I);  
	   // solver_phase_II.apply_simplex_phase_II(); 
	    solver_phase_II.apply_simplex_phase_II();
	    display(solver_phase_II.getZj_cj_phase_II());
	    display(solver_phase_II.getBase());
	    display(solver_phase_II.getAugmented_matrix_phase_II());	
	    solver_phase_II.compute_goal_function();
	    solver_phase_II.enumerate_based_variables_values();	
	}
	
	public static void display(Double[][] matrix)
	{
		System.out.println();
		for(int i=0; i<matrix.length; i++)
		{
			for(int j=0;j<matrix[0].length;j++)
			{
				System.out.print(matrix[i][j]);
				System.out.print("##");
			}
			System.out.println();
		}
	}
	
	public static void display(Object[] vector)
	{
		for(int i=0 ; i<vector.length;i++)
		{
			System.out.print(vector[i]);
			System.out.print("##");
		}
		System.out.println();
	}

}
