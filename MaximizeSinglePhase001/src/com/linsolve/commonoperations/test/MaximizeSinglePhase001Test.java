package com.linsolve.commonoperations.test;
import org.junit.jupiter.api.Test;
import com.linsolve.commonoperations.BuildingAugmentedMatrix;
import com.linsolve.commonoperations.SolvingSinglePhaseLPMax;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class MaximizeSinglePhase001Test 
{
	
	private BuildingAugmentedMatrix bam;
    private SolvingSinglePhaseLPMax solver;
    private Double z;
    private HashMap<Integer,Double> map;
    
  
    
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
        System.out.println(fractionalString1);
        String fractionalString2 = String.format("%.5f", fractionalPart2).substring(2);
        System.out.println(fractionalString2);
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
    
    @Test public void t_001() 
	{ 
    	Double[][] m01= {{2.0,1.0,2.0},{3.0,4.0,2.0},{1.0,3.0,2.0}};
		Double[] b01= {4.0,6.0,8.0};
		Double[]  c01= {2.0,4.0,3.0};
		map=new HashMap<Integer,Double>();
		bam= new BuildingAugmentedMatrix( m01, b01, c01);
		solver=new SolvingSinglePhaseLPMax(bam);
		Double z1 = 7.666666666666666;
        HashMap<Integer, Double> map1 = new HashMap<>();
        map1.put(2, 1.6666666666666667);  
        map1.put(1, 0.6666666666666666);
        map1.put(5, 2.6666666666666665);
        try {
			solver.apply_simplex_single_phase();
			z=solver.compute_goal_function();
			map=(HashMap<Integer, Double>) solver.enumerate_based_variables_values();	
			assertTrue(areDoublesEqual(z1,z));
			assertTrue(areHashMapsEqual(map,map1));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
    
    @Test public void t_002() 
   	{ 
    	Double[][] m01= {{7.0,10.0},{8.0,3.0},{1.0,1.0}};
		Double[] b01= {35.0,24.0,4.0};
		Double[]  c01= {7.0,5.0};
		map=new HashMap<Integer,Double>();
		bam= new BuildingAugmentedMatrix( m01, b01, c01);
		solver=new SolvingSinglePhaseLPMax(bam);
		Double z1=25.508474576271187;
		HashMap<Integer, Double> map1 = new HashMap<>();
	    map1.put(1, 1.8983050847457628);  
	    map1.put(0, 2.288135593220339);
	    map1.put(4, -0.18644067796610164);
	    try {
			solver.apply_simplex_single_phase();
			z=solver.compute_goal_function();
			map=(HashMap<Integer, Double>) solver.enumerate_based_variables_values();	
			assertTrue(areDoublesEqual(z1,z));
			assertTrue(areHashMapsEqual(map,map1));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
   	}
 
    @Test public void t_003()
    {
    	Double[][] m01= {{1.0,1.0},{1.0,2.0},{1.0,0.0}};
		Double[] b01= {6.0,10.0,4.0};
		Double[]  c01= {2.0,3.0};
		map=new HashMap<Integer,Double>();
		bam= new BuildingAugmentedMatrix( m01, b01, c01);
		solver=new SolvingSinglePhaseLPMax(bam);
		Double z1=17.0;
		HashMap<Integer, Double> map1 = new HashMap<>();
	    map1.put(2, -1.0);  
	    map1.put(1, 3.0);
	    map1.put(0, 4.0 );
	    try {
			solver.apply_simplex_single_phase();
			z=solver.compute_goal_function();
			map=(HashMap<Integer, Double>) solver.enumerate_based_variables_values();	
			assertTrue(areDoublesEqual(z1,z));
			assertTrue(areHashMapsEqual(map,map1));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    }
  
    @Test public void t_004()
    {
    	Double[][] m01= {{2.0,3.0},{2.0,1.0},{1.0,1.0}};
		Double[] b01= {14.0,8.0,5.0};
		Double[]  c01= {3.0,4.0};
		map=new HashMap<Integer,Double>();
		bam= new BuildingAugmentedMatrix( m01, b01, c01);
		solver=new SolvingSinglePhaseLPMax(bam);
		Double z1=19.5;
		HashMap<Integer, Double> map1 = new HashMap<>();
	    map1.put(1, 3.000000000000001);  
	    map1.put(0, 2.4999999999999996);
	    map1.put(4, -0.5000000000000002);
	    try {
			solver.apply_simplex_single_phase();
			z=solver.compute_goal_function();
			map=(HashMap<Integer, Double>) solver.enumerate_based_variables_values();	
			assertTrue(areDoublesEqual(z1,z));
			assertTrue(areHashMapsEqual(map,map1));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
		
    }
    
    @Test public void t_005()
    {
    	Double[][] m01= {{1.0,-1.0,1.0},{1.0,2.0,4.0},{1.0,6.0,3.0}};
		Double[] b01= {3.0,4.0,6.0};
		Double[]  c01= {1.0,-3.0,2.0};
		bam= new BuildingAugmentedMatrix( m01, b01, c01);
		solver =new SolvingSinglePhaseLPMax(bam);
		Double z1=3.333333333333333;
		HashMap<Integer, Double> map1 = new HashMap<>();
	    map1.put(0, 2.6666666666666665);  
	    map1.put(2, 0.33333333333333337);
	    map1.put(5, 2.3333333333333335);
	    try {
			solver.apply_simplex_single_phase();
			z=solver.compute_goal_function();
			map=(HashMap<Integer, Double>) solver.enumerate_based_variables_values();	
			assertTrue(areDoublesEqual(z1,z));
			assertTrue(areHashMapsEqual(map,map1));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    }	

}
