package com.linsolve.commonoperations;

import java.util.ArrayList;

import com.linsolve.operators.ApplyingPivotGauss;

public class SolvingPhase_I_LP
{
	
	private BuildingAugmentedMatrix bam;
	private Boolean optimality;
	private Integer pivot_column;
	private Double[] b_comparator_vector;
	private Integer pivot_line;
	private ApplyingPivotGauss apg;
	private Integer[] base;
	private Double[] c_base;
	private Double[] c_extended_I;
	private  Double[] c_extended_II;
	private Double[] b_vector;
	private Double[] c_vector;
	private Double[] zj_cj;
	private Double[] zj_cj_phase_II;
	private Double[][] augmented_matrix;
	private  Double[][] augmented_matrix_phase_II;
	private  Integer size;
	private  ArrayList<Integer> indice ;

	public SolvingPhase_I_LP()
	{
		// TODO Auto-generated constructor stub
	}
	
	

	public SolvingPhase_I_LP(BuildingAugmentedMatrix bam) {
		super();
		this.bam = bam;
		this.zj_cj=new Double[this.bam.getAugmented_matrix()[0].length];
		this.augmented_matrix=new Double[this.bam.getAugmented_matrix().length][this.bam.getAugmented_matrix()[0].length];
		for(int i =0;i<this.bam.getAugmented_matrix().length;i++)
		{
			for(int j=0;j<this.bam.getAugmented_matrix()[0].length;j++)
			{
				this.augmented_matrix[i][j]=this.bam.getAugmented_matrix()[i][j];
			}
		}
		
		this.c_extended_I=new Double[this.bam.getAugmented_n()];
		for(int i=0;i<this.bam.getAugmented_n();i++)
		{
			this.c_extended_I[i]=this.bam.getC_extended_I()[i];
		}
		this.base=new Integer[this.bam.getBase().length];
		for(int i=0;i<this.base.length;i++)
		{
			this.base[i]=this.bam.getBase()[i];
		}
		this.c_base= new Double[this.bam.getC_base().length];
		for(int i=0;i<this.c_base.length;i++)
		{
			this.c_base[i]=this.bam.getC_base()[i];
		}
		 for(int j=0;j<this.bam.getZj_cj().length;j++)
		 {
			zj_cj[j]=0.0;			
		 }
		 for(int j=0;j< this.bam.getZj_cj().length;j++) 
		 { 
			 for(int i=0;i<c_base.length;i++)
			 {
				zj_cj[j]=zj_cj[j]+this.bam.getC_base()[i]*this.augmented_matrix[i][j];
			 } 
		 } 
		 for(int j=0;(j<this.bam.getZj_cj().length) && (j<this.bam.getC_extended_I().length);j++) 
		 {
			 	zj_cj[j]=zj_cj[j]-this.bam.getC_extended_I()[j];
		 }
		 this.size=this.bam.getSize();
		 this.indice=new ArrayList<Integer>();
		 this.indice=this.bam.getIndice();
		 this.augmented_matrix_phase_II= new Double[this.bam.getB_vector().length][this.getBam().getSize()];
		 this.c_extended_II=new Double[this.augmented_matrix_phase_II[0].length-1];
		 this.c_extended_II=this.bam.getC_extended_II();
		 this.zj_cj_phase_II=new Double[this.augmented_matrix_phase_II[0].length-1];
	}
	
	
	public Boolean check_optimality()
	{
		optimality=Boolean.FALSE;
		for(int j=0; j<this.bam.getZj_cj().length-1;j++)
		{
			if(this.zj_cj[j] !=null && this.zj_cj[j].compareTo(Double.valueOf(0.0))>0)
			{
				optimality=Boolean.TRUE;
				break;
			}
		}
		System.out.println(optimality);
		return optimality;	
	}
	
	public Integer compute_pivot_column() 
	{
		Integer pivot_column=null;
		Double maximum_value=Double.valueOf(0.0);	
		if(this.optimality.equals(Boolean.TRUE))
		{
			for(int j=0;j<this.bam.getZj_cj().length-1;j++)
			{	
				if(this.zj_cj[j].compareTo(maximum_value)>0)
				{
					pivot_column=j;
					maximum_value=this.getZj_cj()[j];
				}
			}
			this.pivot_column=pivot_column;
		}
		return pivot_column;	
	}
	
	public  void build_b_comporator_vector()
	{
		this.b_comparator_vector= new Double[this.bam.getB_vector().length];	
		for(int i=0; i<this.bam.getB_vector().length;i++)
		{
			if(augmented_matrix[i][augmented_matrix[0].length-1]>=0 && augmented_matrix[i][pivot_column]>0)
			{
				b_comparator_vector[i]=augmented_matrix[i][augmented_matrix[0].length-1]/augmented_matrix[i][pivot_column];
			}
		}	
	}
	
	public  Integer compute_pivot_line()
	{
		Double min_comp_vector=Double.MAX_VALUE;
		Integer pivot_line=null;
		for(int i=0; i<this.bam.getB_vector().length;i++)
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
		this.apg=new ApplyingPivotGauss(this.pivot_line, this.pivot_column, this.augmented_matrix);
		this.apg.apply();
		this.augmented_matrix=this.apg.getMatrix_reg();
	}
	
	public  void reevaluate_base()
	{
		for(int i=0;i<this.bam.getBase().length;i++)
		{
			if(Integer.valueOf(i).equals(pivot_line))
			{
				this.base[i]=pivot_column;
				this.c_base[i]=this.c_extended_I[pivot_column];
			}
		}
	}
	
	public  void reevaluate_zj_cj()
	{
		 for(int j=0;j<this.bam.getC_extended_I().length;j++)
		 {
			zj_cj[j]=0.0;			
		 }
		 for(int j=0;j< this.bam.getC_extended_I().length;j++) 
		 { 
			 for(int i=0;i<c_base.length;i++)
			 {
				zj_cj[j]=zj_cj[j]+c_base[i]*this.augmented_matrix[i][j];
			 } 
		} 
		 for(int j=0;j< this.bam.getC_extended_I().length;j++) 
		 {
			 	zj_cj[j]=zj_cj[j]-this.c_extended_I[j];
		 }
	}
	
	
	public void apply_simplex_phase_I()
	{
		do
		{
			this.check_optimality();
			if(this.optimality.equals(Boolean.TRUE))
			{
				this.compute_pivot_column();
				this.build_b_comporator_vector();
				this.compute_pivot_line();
				this.apply_pivot_gauss();
				this.reevaluate_base();
				this.reevaluate_zj_cj();
			}
		}
		while(!this.optimality.equals(Boolean.FALSE));
	}
	
	public void reevaluate_augmented_matrix()
	{	
		augmented_matrix_phase_II= new Double[this.bam.getB_vector().length][size];
		for(int i=0;i<this.bam.getB_vector().length;i++)
		{					
			for( int j=0; j<augmented_matrix[0].length-1;j++)
			{
				if(i<augmented_matrix_phase_II.length && j<augmented_matrix_phase_II[0].length && !indice.contains(j))
				{
					augmented_matrix_phase_II[i][j]=augmented_matrix[i][j];
				}
			}
			augmented_matrix_phase_II[i][size-1]=augmented_matrix[i][augmented_matrix[0].length-1];
		}
	}
	
	public void reevaluate_base_phase_II()
	{
		for(int i=0;i<this.bam.getBase().length;i++)
		{
			this.c_base[i]=this.bam.getC_extended_II()[base[i]];
		}
	}
	
	public void reevaluate_zj_cj_phase_II()
	{
		for(int j=0;j<this.bam.getC_extended_II().length;j++)
		 {
			zj_cj_phase_II[j]=0.0;			
		 }
		 for(int j=0;j< this.bam.getC_extended_II().length-1;j++) 
		 { 
			 for(int i=0;i<c_base.length;i++)
			 {
				 zj_cj_phase_II[j]=zj_cj[j]+c_base[i]*augmented_matrix_phase_II[i][j];
			 } 
		} 
		 for(int j=0;j<this.bam.getC_extended_II().length-1;j++) 
		 {
			 zj_cj_phase_II[j]=zj_cj_phase_II[j]-this.c_extended_II[j];
		 }
	
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public BuildingAugmentedMatrix getBam() {
		return bam;
	}



	public void setBam(BuildingAugmentedMatrix bam) {
		this.bam = bam;
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



	public Double[] getC_extended_I() {
		return c_extended_I;
	}



	public void setC_extended_I(Double[] c_extended_I) {
		this.c_extended_I = c_extended_I;
	}



	public Double[] getC_extended_II() {
		return c_extended_II;
	}



	public void setC_extended_II(Double[] c_extended_II) {
		this.c_extended_II = c_extended_II;
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



	public Double[] getZj_cj() {
		return zj_cj;
	}



	public void setZj_cj(Double[] zj_cj) {
		this.zj_cj = zj_cj;
	}



	public Double[][] getAugmented_matrix() {
		return augmented_matrix;
	}



	public void setAugmented_matrix(Double[][] augmented_matrix) {
		this.augmented_matrix = augmented_matrix;
	}



	public Double[][] getAugmented_matrix_phase_II() {
		return augmented_matrix_phase_II;
	}



	public void setAugmented_matrix_phase_II(Double[][] augmented_matrix_phase_II) {
		this.augmented_matrix_phase_II = augmented_matrix_phase_II;
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



	public Double[] getZj_cj_phase_II() {
		return zj_cj_phase_II;
	}



	public void setZj_cj_phase_II(Double[] zj_cj_phase_II) {
		this.zj_cj_phase_II = zj_cj_phase_II;
	}



	

}
