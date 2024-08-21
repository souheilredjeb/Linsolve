package com.linsolve.commonoperations.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.linsolve.commonoperations.BuildingAugmentedMatrix;
import com.linsolve.commonoperations.SolvingPhase_II_LP;
import com.linsolve.commonoperations.SolvingPhase_I_LP;

public class SolvingDoublePhaseTest 
{
	
	BuildingAugmentedMatrix bam= null;
	SolvingPhase_I_LP solver_phase_I=null;
	SolvingPhase_II_LP solver_phase_II=null;
	Double z=null;
	HashMap<Integer,Double> map=null;
	
	
	public static Boolean areDoublesEqual(Double d1, Double d2) {
        if (d1 == null || d2 == null) {
            return false;
        }

        // Separate the integer and fractional parts
        long integerPart1 = d1.longValue();
        long integerPart2 = d2.longValue();

        double fractionalPart1 = d1 - integerPart1;
        double fractionalPart2 = d2 - integerPart2;

        // Check if the integer parts are equal
        if (integerPart1 != integerPart2) {
            return false;
        }

        // Format the fractional parts to 5 decimal places
        String fractionalString1 = String.format("%.5f", fractionalPart1).substring(2);
        String fractionalString2 = String.format("%.5f", fractionalPart2).substring(2);
        // Compare the fractional parts
        return fractionalString1.equals(fractionalString2);
    }
    
    public static Boolean areHashMapsEqual(HashMap<Integer, Double> map1, HashMap<Integer, Double> map2) {
        if (map1 == null || map2 == null) {
            return false;
        }
        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<Integer, Double> entry : map1.entrySet()) {
            Integer key = entry.getKey();
            Double value1 = entry.getValue();
            Double value2 = map2.get(key);

            if (value2 == null || !areDoublesEqual(value1, value2)) {
                return false;
            }
        }

        return true;
    }
	public SolvingDoublePhaseTest() {
		// TODO Auto-generated constructor stub
	}
	
    @Test public void t_001() 
	{ 
    	Double[][] initial_matrix1={{1.0,1.0},{4.0,1.0},{2.0,3.0}};
    	Double[] b_vector1= {5.0,-4.0,-3.0};
    	Double[] c_vector1= {8.0,5.0};
    	
    	Double z1 = 40.0;
        HashMap<Integer, Double> map1 = new HashMap<>();
        map1.put(4, 7.0);  
        map1.put(0, 5.0);
        map1.put(3, 16.0);
    	
    	bam= new BuildingAugmentedMatrix(initial_matrix1, b_vector1, c_vector1);
    	solver_phase_I= new SolvingPhase_I_LP(bam);
    	solver_phase_I.reevaluate_base();
		solver_phase_I.reevaluate_zj_cj();
		solver_phase_I.apply_simplex_phase_I();	
	    solver_phase_I.reevaluate_augmented_matrix();
	    solver_phase_I.reevaluate_base_phase_II();
	    solver_phase_I.reevaluate_zj_cj_phase_II();   
	    solver_phase_II= new SolvingPhase_II_LP(solver_phase_I); 
	    try {
			solver_phase_II.apply_simplex_phase_II();
			z=solver_phase_II.compute_goal_function();
			map=(HashMap<Integer, Double>) solver_phase_II.enumerate_based_variables_values();	
			assertTrue(areDoublesEqual(z1,z));
			assertTrue(areHashMapsEqual(map,map1));		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Test public void t_002() 
	{ 
    	
    	Double[][] initial_matrix2={{3.0,1.0},{-2.0,1.0},{1.0,2.0}};
		Double[] b_vector2= {9.0,4.0,-2.0};
		Double[] c_vector2= {3.0,4.0};
		
		Double z1 = 27.000000000000004;
        HashMap<Integer, Double> map1 = new HashMap<>();
        map1.put(4, 11.000000000000002);  
        map1.put(1, 6.000000000000001);
        map1.put(0, 1.0000000000000002);
		
		
		bam= new BuildingAugmentedMatrix(initial_matrix2, b_vector2, c_vector2);
		solver_phase_I= new SolvingPhase_I_LP(bam);
		solver_phase_I.reevaluate_base();
		solver_phase_I.reevaluate_zj_cj();
		solver_phase_I.apply_simplex_phase_I();	
	    solver_phase_I.reevaluate_augmented_matrix();
	    solver_phase_I.reevaluate_base_phase_II();
	    solver_phase_I.reevaluate_zj_cj_phase_II();
	    solver_phase_II= new SolvingPhase_II_LP(solver_phase_I);
	    try {
			solver_phase_II.apply_simplex_phase_II();
			z=solver_phase_II.compute_goal_function();
			map=(HashMap<Integer, Double>) solver_phase_II.enumerate_based_variables_values();	
			assertTrue(areDoublesEqual(z1,z));
			assertTrue(areHashMapsEqual(map,map1));		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


