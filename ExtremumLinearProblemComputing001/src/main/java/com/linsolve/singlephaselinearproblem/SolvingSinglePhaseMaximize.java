package com.linsolve.singlephaselinearproblem;

import java.util.HashMap;
import java.util.Map;

import com.linsolve.operators.ApplyingPivotGauss;

public class SolvingSinglePhaseMaximize 
{
	private BuildingAugmentedMatrixSinglePhase bam;
	private Integer pivot_line;
	private Integer pivot_column;
	private Double[] b_comparator_vector;
	private Boolean optimality;
	private ApplyingPivotGauss apg;
	private Integer[] base;
	private Double[] c_base;
	
	public SolvingSinglePhaseMaximize() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public SolvingSinglePhaseMaximize(BuildingAugmentedMatrixSinglePhase bam)
	{
		this.bam = bam;
		this.optimality=Boolean.FALSE;
		this.base=new Integer[this.bam.getB_vector().length];
		for(int i=0;i<this.bam.getB_vector().length;i++)
		{
			base[i]=this.bam.getC_vector().length+i;
		}
		this.c_base=new Double[this.bam.getB_vector().length];
		for(int i=0;i<bam.getB_vector().length;i++)
		{
			c_base[i]=this.bam.getC_extended()[base[i]];
		}
		Double[] c2=new Double[this.bam.getB_vector().length];
		for(int i=0;i<this.bam.getB_vector().length;i++)
		{
			c2[i]=this.getBam().getC_extended()[this.bam.getBase()[i]];
		}	
		this.getBam().setC_base(c2);
	}

	public void check_optimality()
	{
		this.optimality=Boolean.TRUE;
		for(int j=0; j< this.bam.getZj_cj().length;j++)
		{
			if(this.bam.getZj_cj()[j] !=null && this.bam.getZj_cj()[j].compareTo(Double.valueOf(0.0))<0)
			{
				this.optimality=Boolean.FALSE;
				break;
			}
		}
	}
	
	public Integer compute_pivot_column() throws Exception
	{
		Integer pivot_column=null;
		Double minimum_value=Double.valueOf(0.0);	
		if(this.optimality.equals(Boolean.FALSE))
		{
			for(int j=0;j<this.bam.getZj_cj().length;j++)
			{	
				if(this.bam.getZj_cj()[j].compareTo(minimum_value)<0)
				{
					pivot_column=j;
					minimum_value=this.getBam().getZj_cj()[j];
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
	
	public void build_b_comporator_vector()
	{
		this.b_comparator_vector= new Double[this.bam.getB_vector().length];	
		for(int i=0; i<this.bam.getB_vector().length;i++)
		{
			 if(this.bam.getAugmented_matrix()[i][this.bam.getAugmented_matrix()[0].length-1]>0 && this.bam.getAugmented_matrix()[i][this.pivot_column]>0)
			 {
				this.b_comparator_vector[i]=this.bam.getAugmented_matrix()[i][this.bam.getAugmented_matrix()[0].length-1]/this.bam.getAugmented_matrix()[i][this.pivot_column];
			 }
		}	
	}
	
	public void compute_pivot_line()
	{
		Double min_comp_vector=Double.MAX_VALUE;
		Integer pivot_line=null;
		for(int i=0; i<this.bam.getB_vector().length;i++)
		{
			if(this.b_comparator_vector[i] !=null && this.b_comparator_vector[i]<=min_comp_vector)
			{
				min_comp_vector=this.b_comparator_vector[i];
				pivot_line=i;
			}		
		}
		this.pivot_line=pivot_line;
	}
	
	public void apply_pivot_gauss()
	{
		this.apg=new ApplyingPivotGauss(this.pivot_line, this.pivot_column, this.bam.getAugmented_matrix());
		this.apg.apply();
		this.bam.setAugmented_matrix(this.apg.getMatrix_reg());
	}
	
	public void reevaluate_base()
	{
		 Double[] c_vector_extended=new Double[bam.getAugmented_matrix()[0].length];
		 for(int i=0;i<c_vector_extended.length;i++)
		 {
			 if(i<this.bam.getC_vector().length)
			 {
				 c_vector_extended[i]=this.getBam().getC_vector()[i];
			 }
			 else
			 {
				 c_vector_extended[i]=0.0;
			 }
			 
		 }
		for(int i=0;i<this.bam.getBase().length;i++)
		{
			if(Integer.valueOf(i).equals(this.pivot_line))
			{
				this.base[i]=this.pivot_column;
				this.c_base[i]=c_vector_extended[this.pivot_column];	
			}
		}
	}
	
	public void reevaluate_zj_cj()
	{
		 Double[] zj__cj=new Double[bam.getAugmented_matrix()[0].length];
		 Double[] c_vector_extended=new Double[bam.getAugmented_matrix()[0].length];
		 for(int i=0;i<c_vector_extended.length;i++)
		 {
			 if(i<this.bam.getC_vector().length)
			 {
				 c_vector_extended[i]=this.getBam().getC_vector()[i];
			 }
			 else
			 {
				 c_vector_extended[i]=0.0;
			 }
			 
		 }
		 for(int j=0;j<c_vector_extended.length;j++)
		 {
			 
			 zj__cj[j]=	0.0;			
		 }
		 
		 for(int j=0;j<c_vector_extended.length;j++) 
		 { 
			 for(int i=0;i<this.bam.getC_base().length;i++)
			 { 
				 zj__cj[j]+=this.c_base[i]*this.bam.getAugmented_matrix()[i][j];
			 } 
		} 
		 
		 for(int j=0;j<c_vector_extended.length;j++) 
		 {
			 zj__cj[j]=zj__cj[j]-c_vector_extended[j];
		 }	 
		 this.bam.setZj_cj(zj__cj);	 
	}
	
	public void apply_simplex_single_phase() throws Exception {
		do
		{
			this.check_optimality();
			if(this.optimality.equals(Boolean.FALSE))
			{
				this.compute_pivot_column();
				this.build_b_comporator_vector();
				this.compute_pivot_line();
				this.apply_pivot_gauss();
				this.reevaluate_base();
				this.reevaluate_zj_cj();
			}
		}
		while(this.optimality.equals(Boolean.FALSE));
	}
	
	public Double compute_goal_function() throws Exception
	{
		Double z=0.0;
		Double[] b_augmented_matrix=new Double[this.bam.getB_vector().length];
		for(int i=0;i<this.getBam().getB_vector().length;i++)
		{
			b_augmented_matrix[i]=this.bam.getAugmented_matrix()[i][this.getBam().getAugmented_matrix()[0].length-1];
		}
		
		for(int i=0;i<this.getBam().getB_vector().length;i++)
		{
			z=z+this.c_base[i]*b_augmented_matrix[i];
		}
		System.out.println("Z Function : "+z);
		return z;
	}
	
	public Map<Integer, Double> enumerate_based_variables_values()
	{
		Map<Integer, Double> hm= new HashMap<>();
		for(int i=0;i<this.getBam().getB_vector().length;i++)
		{
			hm.put(this.base[i], this.getBam().getAugmented_matrix()[i][this.getBam().getAugmented_matrix()[0].length-1]);
			System.out.println("Var x"+this.base[i]+" "+this.getBam().getAugmented_matrix()[i][this.getBam().getAugmented_matrix()[0].length-1]+" ");
		}	
		return hm;
	}

	public static void main(String[] args) throws Exception 
	{
			
	}

	public BuildingAugmentedMatrixSinglePhase getBam() {
		return bam;
	}



	public void setBam(BuildingAugmentedMatrixSinglePhase bam) {
		this.bam = bam;
	}



	public Integer getPivot_line() {
		return pivot_line;
	}



	public void setPivot_line(Integer pivot_line) {
		this.pivot_line = pivot_line;
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



	public Boolean getOptimality() {
		return optimality;
	}



	public void setOptimality(Boolean optimality) {
		this.optimality = optimality;
	}



	public ApplyingPivotGauss getApg() {
		return apg;
	}



	public void setApg(ApplyingPivotGauss apg) {
		this.apg = apg;
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
	
	
	

}
