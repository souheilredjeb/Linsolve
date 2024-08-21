package com.linsolve.commonoperations;

public class BuildingAugmentedMatrix 
{

	private Double[][] initial_matrix;
	private Double[][] augmented_matrix;
	private Double[][] augmented_matrix_reg;
	private Double[] b_vector;
	private Double[] c_vector;
	private Integer[] base;
	private Double[] c_base;
	private Double[] c_augmented;
	private Double[] c_extended;
	private Double[] zj_cj;
	private int augmented_n=0;
	
	
	

	public BuildingAugmentedMatrix(Double[][] initial_matrix, Double[] b_vector, Double[] c_vector) {
		super();
		this.initial_matrix = initial_matrix;
		this.b_vector = b_vector;
		this.c_vector = c_vector;
		this.augmented_n=this.initial_matrix[0].length+this.b_vector.length+1;
		this.base=new Integer[this.b_vector.length];
		this.c_base=new Double[this.b_vector.length];
		this.augmented_matrix=new Double[this.b_vector.length][augmented_n];
		this.c_augmented=new Double[augmented_n-1];
		for(int i=0;i<this.b_vector.length;i++)
		{
			for(int j=0;j<this.initial_matrix[0].length;j++)
			{
				this.augmented_matrix[i][j]=this.initial_matrix[i][j];
				this.c_augmented[j]=0.0;
			}
			for(int j=this.initial_matrix[0].length;j<augmented_n-1;j++)
			{
				this.c_augmented[j]=1.0;		
				if(j==(this.initial_matrix[0].length+i))
				{
					this.augmented_matrix[i][j]=1.0;
					this.base[i]=this.initial_matrix[0].length+i;	
				}
				else
				{
					this.augmented_matrix[i][j]=0.0;
				}
			}
			this.augmented_matrix[i][augmented_n-1]=this.b_vector[i];
			this.c_extended=new Double[this.augmented_matrix[0].length];
			for(int j=0;j<this.augmented_n;j++)
			{
				if(j<this.c_vector.length)
				{
					this.c_extended[j]=this.c_vector[j];
				}
				else
				{
					this.c_extended[j]=0.0;
				}
			}	
		}
		c_base=new Double[this.b_vector.length];
		for(int i=0;i<this.b_vector.length;i++)
		{
			c_base[i]=c_extended[base[i]];
		}
		zj_cj=new Double[this.c_extended.length];
		for(int j=0;j<this.c_extended.length;j++)
		{
			zj_cj[j]=0.0;				
		}	
		for(int j=0;j<this.c_extended.length;j++)
		{
				for(int i=0;i<this.c_base.length;i++)
				{
					zj_cj[j]=zj_cj[j]+this.c_base[i]*this.augmented_matrix[i][j];
				}
		}	
		for(int j=0;j<this.c_extended.length;j++)
		{
			zj_cj[j]=zj_cj[j]-this.c_extended[j];
		}		
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
		System.out.println();
		for(int i=0 ; i<vector.length;i++)
		{
			System.out.print(vector[i]);
			System.out.print("##");
		}
		System.out.println();
	}
	
	public BuildingAugmentedMatrix()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Double[][] m01= {{2.0,1.0,2.0},{3.0,4.0,2.0},{1.0,3.0,2.0}};
		Double[] b01= {4.0,6.0,8.0};
		Double[]  c01= {2.0,4.0,3.0};
		
		BuildingAugmentedMatrix b1= new BuildingAugmentedMatrix(m01, b01, c01);
		display(b1.getAugmented_matrix());
		System.out.println();
		System.out.println();
		display(b1.getZj_cj());
		

	}

	public Double[][] getInitial_matrix() {
		return initial_matrix;
	}

	public void setInitial_matrix(Double[][] initial_matrix) {
		this.initial_matrix = initial_matrix;
	}

	public Double[][] getAugmented_matrix() {
		return augmented_matrix;
	}

	public void setAugmented_matrix(Double[][] augmented_matrix) {
		this.augmented_matrix = augmented_matrix;
	}

	public Double[][] getAugmented_matrix_reg() {
		return augmented_matrix_reg;
	}

	public void setAugmented_matrix_reg(Double[][] augmented_matrix_reg) {
		this.augmented_matrix_reg = augmented_matrix_reg;
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

	public Double[] getC_augmented() {
		return c_augmented;
	}

	public void setC_augmented(Double[] c_augmented) {
		this.c_augmented = c_augmented;
	}

	public Double[] getC_extended() {
		return c_extended;
	}

	public void setC_extended(Double[] c_extended) {
		this.c_extended = c_extended;
	}

	public Double[] getZj_cj() {
		return zj_cj;
	}

	public void setZj_cj(Double[] zj_cj) {
		this.zj_cj = zj_cj;
	}

	public int getAugmented_n() {
		return augmented_n;
	}

	public void setAugmented_n(int augmented_n) {
		this.augmented_n = augmented_n;
	}
	
	
	

}
