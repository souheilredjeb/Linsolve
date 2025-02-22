package com.linsolve.doublephaselinearproblem;
import com.linsolve.lexicallinearproblem.converter.ConvertingLexicalLinearProblem;
import java.util.ArrayList;
import java.util.Arrays;


public class BuildingAugmentedMatrixDoublePhase
{
	
	private Double[][] initial_matrix;
	private Double[][] augmented_matrix;
	private Double[][] augmented_matrix_phase_II;
	private Double[] b_vector;
	private Double[] c_vector;
	private Integer[] base;
	private Integer[] base_I;
	private Double[] c_base;
	private Double[] c_base_I;
	private Double[] c_extended_I;
	private Double[] c_extended_II;
	private Double[] zj_cj;
	private int augmented_n=0;
	private Integer negative_mult;
	private Integer positive_mult;
	private Integer II_phase_I_phase;
	private Integer size;
	private ArrayList<Integer> indice;
	
	
	public BuildingAugmentedMatrixDoublePhase()
	{
		// TODO Auto-generated constructor stub
		
		
	}
	
	

	public BuildingAugmentedMatrixDoublePhase(Double[][] initial_matrix, Double[] b_vector, Double[] c_vector) 
	{
	
		super();
		this.initial_matrix = initial_matrix;
		this.b_vector = b_vector;
		this.c_vector = c_vector;
		this.positive_mult=0;
		this.negative_mult=0;
		this.augmented_n=this.c_vector.length;
		for(int i=0;i<this.b_vector.length;i++)
		{	
			if(this.b_vector[i]<0)
			{
				negative_mult++;
				augmented_n+=2;
			
			}
			else
			{
				augmented_n+=1;
				positive_mult++;
			}
		}
		this.base=new Integer[this.b_vector.length];
		this.c_base=new Double[this.b_vector.length];
		this.augmented_matrix=new Double[this.b_vector.length][augmented_n+1];
		this.c_extended_I=new Double[augmented_n];
		for(int i=0;i<this.b_vector.length;i++)
		{
			for(int j=0;j<this.initial_matrix[0].length;j++)
			{
				this.augmented_matrix[i][j]=this.initial_matrix[i][j];
				this.c_extended_I[j]=0.0;
			}
			for(int j=this.initial_matrix[0].length;j<this.initial_matrix[0].length+this.negative_mult+this.positive_mult;j++)
			{	
				
					if(this.b_vector[i]<0.0)
					{
						if(j==this.initial_matrix[0].length+i)
						{
							
								this.augmented_matrix[i][j]=-1.0;
								this.base[i]=j+negative_mult;
								this.augmented_matrix[i][j+negative_mult]=1.0;
								this.c_extended_I[j+negative_mult]=1.0;
						}
					
					}
					else if(this.b_vector[i]>0)
					{	
						if(j==this.initial_matrix[0].length+i)
						{
							this.augmented_matrix[i][j+this.negative_mult]=1.0;
							this.base[i]=j+this.negative_mult;
						}	
					}	
			}
		}
		this.c_base=new Double[this.b_vector.length];
		for(int i=0; i<this.c_base.length;i++)
		{
			this.c_base[i]=this.c_extended_I[this.base[i]];
		}
		for(int i=0; i<this.c_base.length;i++)
		{
			if(this.c_base[i]==null)
			this.c_base[i]=0.0;
		}
		for(int i=0;i<this.b_vector.length;i++)
		{
			for(int j=0;j<this.augmented_matrix[0].length-1;j++)
			{	
				if(this.augmented_matrix[i][j]==null)
				{
					this.augmented_matrix[i][j]=0.0;
				}	
			}
		}
		for(int i=0;i<this.c_extended_I.length;i++)
		{
			if(this.c_extended_I[i]==null)
			{
				this.c_extended_I[i]=0.0;
			}			
		}
		for(int i=0;i<this.b_vector.length;i++)
		{
				if(this.b_vector[i]<0)
				{
					this.augmented_matrix[i][this.augmented_matrix[0].length-1]=-this.b_vector[i];
							
				}
				else
				{
					this.augmented_matrix[i][this.augmented_matrix[0].length-1]=this.b_vector[i];
				}	
		}	
	    this.indice=new ArrayList<Integer>();
	    this.size=this.augmented_matrix[0].length;
	    for(int i=0;i<this.b_vector.length;i++)
		{	
			for( int j=0; j<this.augmented_matrix[0].length;j++)
			{
					if(j>(this.initial_matrix[0].length-1) && this.augmented_matrix[i][j].equals(-1.0)	&& this.augmented_matrix[i][j+this.negative_mult].equals(1.0))
					{
						size--;	
					}
			}	
		}	
	    this.augmented_matrix_phase_II= new Double[this.b_vector.length][size];
	    for(int i=0;i<this.b_vector.length;i++)
		{			
	    	for( int j=0; j<this.augmented_matrix[0].length;j++)
			{		
	    		if(j>(this.initial_matrix[0].length-1) && (this.augmented_matrix[i][j].equals(-1.0)) && ((j+this.negative_mult)<this.augmented_matrix[0].length)&&( this.augmented_matrix[i][j+this.negative_mult].equals(1.0)))
				{
					indice.add(j+this.negative_mult);
				}				
			}
	    	for( int j=0; j<this.augmented_matrix[0].length-1;j++)
			{
	    		if(i<this.augmented_matrix_phase_II.length && j<this.augmented_matrix_phase_II[0].length &&!indice.contains(j))
				{
					this.augmented_matrix_phase_II[i][j]=this.augmented_matrix[i][j];
				}
	    		else if(i<this.augmented_matrix_phase_II.length && j<this.augmented_matrix_phase_II[0].length && j+this.negative_mult<this.augmented_matrix[0].length)
	    		{
	    			this.augmented_matrix_phase_II[i][j]=this.augmented_matrix[i][j+this.negative_mult];
	    		}
			}
	    	this.augmented_matrix_phase_II[i][size-1]=this.augmented_matrix[i][this.augmented_matrix[0].length-1];
		}
	    this.c_extended_II=new Double[augmented_n];
	    for(int j=0;j<this.c_extended_II.length;j++)
		{
			if(j<this.c_vector.length)
			{
				this.c_extended_II[j]=this.c_vector[j];
			}
			
			if(j>this.c_vector.length-1+2*this.negative_mult)
			{
				this.c_extended_II[j]=1.0;
			}
			else
			{
				this.c_extended_II[j]=0.0;
			}
		}	
	   zj_cj = new Double[this.augmented_matrix[0].length];
	    Arrays.fill(zj_cj, 0.0);
	    for (int j = 0; j < this.c_extended_I.length; j++)
	    {
	        for (int i = 0; i < this.c_base.length; i++) {
	            zj_cj[j] += this.c_base[i] * this.augmented_matrix[i][j];
	        }
	        zj_cj[j] -= this.c_extended_I[j];
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
		for(int i=0 ; i<vector.length;i++)
		{
			System.out.print(vector[i]);
			System.out.print("##");
		}
		System.out.println();
	}

	public static void main(String[] args) 
	{
		String input66="Maximize Z=15x1+20x2"
				+"subject to"
				+"x1+2x2>=10,"
				+"x1+x2>=6,"
				+"2x1-3x2<=6,"	
				+"non negative constraints";
		
		
		
		String input6="Maximize Z=15x1+20x2"
				+"subject to"
				+"2x1-3x2<=6,"
				+"x1+2x2>=10,"
				+"x1+x2>=6,"
				+"non negative constraints";
				
				
		ConvertingLexicalLinearProblem cllp= new ConvertingLexicalLinearProblem(input6);
		cllp.convertLexicalLinearToAlgebraicLinearProblem();
		
		BuildingAugmentedMatrixDoublePhase bamdp=new BuildingAugmentedMatrixDoublePhase(cllp.getAlp().getInitial_matrix(), cllp.getAlp().getB_vector(), cllp.getAlp().getC_vector());
		bamdp.getAugmented_matrix();
		display(bamdp.getAugmented_matrix());
		System.out.println("----------------------------------------------");
		cllp= new ConvertingLexicalLinearProblem(input66);
		cllp.convertLexicalLinearToAlgebraicLinearProblem();
	
	    bamdp=new BuildingAugmentedMatrixDoublePhase(cllp.getAlp().getInitial_matrix(), cllp.getAlp().getB_vector(), cllp.getAlp().getC_vector());
		bamdp.getAugmented_matrix();
		display(bamdp.getAugmented_matrix());
	}
	
	
	
	public Integer[] getBase_I()
	{
		return base_I;
	}
	
	public void setBase_I(Integer[] base_I) {
		this.base_I = base_I;
	}

	public Double[] getC_base_I() {
		return c_base_I;
	}

	public void setC_base_I(Double[] c_base_I) {
		this.c_base_I = c_base_I;
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

	public void setAugmented_matrix(Double[][] augmented_matrix)
	{
		this.augmented_matrix = augmented_matrix;
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

	public Integer getNegative_mult() {
		return negative_mult;
	}

	public void setNegative_mult(Integer negative_mult) {
		this.negative_mult = negative_mult;
	}

	public Integer getPositive_mult() {
		return positive_mult;
	}

	public void setPositive_mult(Integer positive_mult) {
		this.positive_mult = positive_mult;
	}

	public Integer getII_phase_I_phase() {
		return II_phase_I_phase;
	}

	public void setII_phase_I_phase(Integer iI_phase_I_phase) {
		II_phase_I_phase = iI_phase_I_phase;
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
