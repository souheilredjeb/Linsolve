package com.linsolve.commonoperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.linsolve.operators.ApplyingPivotGauss;

public class SolvingPhase_II_LP 
{
	
	private SolvingPhase_I_LP solver_phase_I;
	private  Double[][] augmented_matrix_phase_II;
	private Double[] b_vector;
	private Double[] c_vector;
	private Integer[] base;
	private Double[] c_base;
	private Double[] zj_cj_phase_II;
	private  Double[] c_extended_II;
	private Boolean optimality;
	private Integer pivot_column;
	private Double[] b_comparator_vector;
	private Integer pivot_line;
	private ApplyingPivotGauss apg;
	private  Integer size;
	private  ArrayList<Integer> indice ;
	

	public SolvingPhase_II_LP() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	

	public SolvingPhase_II_LP(SolvingPhase_I_LP solver_phase_I) {
		super();
		this.solver_phase_I = solver_phase_I;
		this.augmented_matrix_phase_II=solver_phase_I.getAugmented_matrix_phase_II();
		this.c_extended_II=solver_phase_I.getC_extended_II();
		this.base=solver_phase_I.getBase();
		this.c_base=solver_phase_I.getC_base();
		this.zj_cj_phase_II=solver_phase_I.getZj_cj_phase_II();
		this.b_vector=this.solver_phase_I.getBam().getB_vector();
	    this.c_vector=this.solver_phase_I.getBam().getC_vector();
	}
	
	public void reevaluate_base_phase_II()
	{
		for(int i=0;i<this.getBase().length;i++)
		{
			this.c_base[i]=this.getC_extended_II()[base[i]];
		}
	}
	
	public void reevaluate_zj_cj_phase_II()
	{
		for(int j=0;j<this.getC_extended_II().length;j++)
		 {
			zj_cj_phase_II[j]=0.0;			
		 }
		 for(int j=0;j< this.getC_extended_II().length-1;j++) 
		 { 
			 for(int i=0;i<c_base.length;i++)
			 {
				 zj_cj_phase_II[j]=zj_cj_phase_II[j]+c_base[i]*augmented_matrix_phase_II[i][j];
			 } 
		} 
		 for(int j=0;j<this.getC_extended_II().length-1;j++) 
		 {
			 zj_cj_phase_II[j]=zj_cj_phase_II[j]-this.c_extended_II[j];
		 }
	
	}

	public Boolean check_optimality()
	{
		this.optimality=Boolean.FALSE;
		for(int j=0; j< this.zj_cj_phase_II.length;j++)
		{
			if(this.zj_cj_phase_II[j] !=null && this.zj_cj_phase_II[j].compareTo(Double.valueOf(0.0))<0)
			{
				this.optimality=Boolean.TRUE;
				break;
			}
		}
		return optimality;
	}

	public Integer compute_pivot_column() throws Exception
	{
		Integer pivot_column=null;
		Double minimum_value=Double.valueOf(0.0);	
		if(this.optimality.equals(Boolean.TRUE))
		{
			for(int j=0;j<this.zj_cj_phase_II.length;j++)
			{	
				if(this.zj_cj_phase_II[j].compareTo(minimum_value)<0)
				{
					pivot_column=j;
					minimum_value=this.zj_cj_phase_II[j];
				}
			}
			this.pivot_column=pivot_column;
		}
		if(this.pivot_column !=null)
		{
			return pivot_column;
		}
		else
		{
			return null;
		}
		
	}
	
	public  void build_b_comporator_vector()
	{
		this.b_comparator_vector= new Double[this.b_vector.length];	
		for(int i=0; i<this.b_vector.length;i++)
		{
			if(augmented_matrix_phase_II[i][augmented_matrix_phase_II[0].length-1]>=0 && augmented_matrix_phase_II[i][pivot_column]>0)
			{
				b_comparator_vector[i]=augmented_matrix_phase_II[i][augmented_matrix_phase_II[0].length-1]/augmented_matrix_phase_II[i][pivot_column];
			}
		}	
	}
	
	public  Integer compute_pivot_line()
	{
		Double min_comp_vector=Double.MAX_VALUE;
		Integer pivot_line=null;
		for(int i=0; i<this.b_vector.length;i++)
		{
			if(b_comparator_vector[i] !=null && b_comparator_vector[i]<min_comp_vector)
			{
				min_comp_vector=b_comparator_vector[i];
				pivot_line=i;
			}		
		}
		this.pivot_line=pivot_line;
		return pivot_line;
	}
	
	public void apply_pivot_gauss()
	{
		if(this.pivot_column !=null && this.pivot_line !=null)
		{
		this.apg=new ApplyingPivotGauss(this.pivot_line, this.pivot_column, this.augmented_matrix_phase_II);
		this.apg.apply();
		this.augmented_matrix_phase_II=this.apg.getMatrix_reg();
		}
	}
	
	public  void reevaluate_base()
	{
		if(this.pivot_column !=null && this.pivot_line !=null)
		{
		for(int i=0;i<this.solver_phase_I.getBase().length;i++)
		{
			if(Integer.valueOf(i).equals(pivot_line))
			{
				this.base[i]=pivot_column;
				this.c_base[i]=this.c_extended_II[pivot_column];
			}
		}
		}
	}
	
	public  void reevaluate_zj_cj()
	{
		if(this.pivot_column !=null && this.pivot_line !=null)
		{
		 for(int j=0;j<this.solver_phase_I.getC_extended_II().length;j++)
		 {
			zj_cj_phase_II[j]=0.0;			
		 }
		 for(int j=0;j< this.solver_phase_I.getC_extended_II().length;j++) 
		 { 
			 for(int i=0;i<c_base.length;i++)
			 {
				zj_cj_phase_II[j]=zj_cj_phase_II[j]+c_base[i]*this.augmented_matrix_phase_II[i][j];
			 } 
		} 
		 for(int j=0;j< this.solver_phase_I.getC_extended_II().length;j++) 
		 {
			 zj_cj_phase_II[j]=zj_cj_phase_II[j]-this.c_extended_II[j];
		 }
		} 
	}
	
	
	public void apply_simplex_phase_II() throws Exception
	{
		while(this.check_optimality())
	    {
		    this.compute_pivot_column();
		    this.build_b_comporator_vector();
		    this.compute_pivot_line();
		    this.apply_pivot_gauss();
		    this.reevaluate_base();
		    this.reevaluate_zj_cj();
	    }
	}
	
	public Double compute_goal_function() throws Exception
	{
		Double z=0.0;
		Double[] b_augmented_matrix=new Double[this.getB_vector().length];
		for(int i=0;i<this.getB_vector().length;i++)
		{
			b_augmented_matrix[i]=this.augmented_matrix_phase_II[i][this.augmented_matrix_phase_II[0].length-1];
		}
		
		for(int i=0;i<this.getB_vector().length;i++)
		{
			z=z+this.c_base[i]*b_augmented_matrix[i];
		}
		System.out.println("Z Function : "+z);
		return z;
	}
	public Map<Integer, Double> enumerate_based_variables_values()
	{
		Map<Integer, Double> hm= new HashMap<>();
		for(int i=0;i<this.getB_vector().length;i++)
		{
			hm.put(this.base[i], this.augmented_matrix_phase_II[i][this.augmented_matrix_phase_II[0].length-1]);
			System.out.println("Var x"+this.base[i]+" "+this.augmented_matrix_phase_II[i][this.augmented_matrix_phase_II[0].length-1]+" ");
		}	
		return hm;
	}
	
	

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

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

	public SolvingPhase_I_LP getSolver_phase_I() {
		return solver_phase_I;
	}

	public void setSolver_phase_I(SolvingPhase_I_LP solver_phase_I) {
		this.solver_phase_I = solver_phase_I;
	}

	public Double[][] getAugmented_matrix_phase_II() {
		return augmented_matrix_phase_II;
	}

	public void setAugmented_matrix_phase_II(Double[][] augmented_matrix_phase_II) {
		this.augmented_matrix_phase_II = augmented_matrix_phase_II;
	}

	public Double[] getB_vector() {
		return b_vector;
	}

	public void setB_vector(Double[] b_vector) {
		this.b_vector = b_vector;
	}

	public Double[] getC_vector() {
		return c_vector;
	}

	public void setC_vector(Double[] c_vector) {
		this.c_vector = c_vector;
	}

	public Integer[] getBase() {
		return base;
	}

	public void setBase(Integer[] base) {
		this.base = base;
	}

	public Double[] getC_base() {
		return c_base;
	}

	public void setC_base(Double[] c_base) {
		this.c_base = c_base;
	}

	public Double[] getZj_cj_phase_II() {
		return zj_cj_phase_II;
	}

	public void setZj_cj_phase_II(Double[] zj_cj_phase_II) {
		this.zj_cj_phase_II = zj_cj_phase_II;
	}

	public Double[] getC_extended_II() {
		return c_extended_II;
	}

	public void setC_extended_II(Double[] c_extended_II) {
		this.c_extended_II = c_extended_II;
	}

	public Boolean getOptimality() {
		return optimality;
	}

	public void setOptimality(Boolean optimality) {
		this.optimality = optimality;
	}

	public Integer getPivot_column() {
		return pivot_column;
	}

	public void setPivot_column(Integer pivot_column) {
		this.pivot_column = pivot_column;
	}

	public Double[] getB_comparator_vector() {
		return b_comparator_vector;
	}

	public void setB_comparator_vector(Double[] b_comparator_vector) {
		this.b_comparator_vector = b_comparator_vector;
	}

	public Integer getPivot_line() {
		return pivot_line;
	}

	public void setPivot_line(Integer pivot_line) {
		this.pivot_line = pivot_line;
	}

	public ApplyingPivotGauss getApg() {
		return apg;
	}

	public void setApg(ApplyingPivotGauss apg) {
		this.apg = apg;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public ArrayList<Integer> getIndice() {
		return indice;
	}

	public void setIndice(ArrayList<Integer> indice) {
		this.indice = indice;
	}
	
	
	

}
