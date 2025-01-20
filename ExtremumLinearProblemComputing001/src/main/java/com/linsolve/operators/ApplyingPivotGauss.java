package com.linsolve.operators;

public class ApplyingPivotGauss 
{
	
	private Integer pivot_line;
	private Integer pivot_column;
	private Double pivot;
	private Double[][] matrix;
	private Integer m;
	private Integer n;
	private Double[][] matrix_reg;

	public ApplyingPivotGauss() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public ApplyingPivotGauss(Integer pivot_line, Integer pivot_column,
			Double[][] matrix) {
		super();
		this.pivot_line = pivot_line;
		this.pivot_column = pivot_column;
		this.matrix = matrix;
		this.m=this.matrix.length;
		this.n=this.matrix[0].length;
		this.matrix_reg=new Double[m][n];
		this.pivot=this.matrix[this.pivot_line][this.pivot_column];
	}
	
	public void apply() 
	{
		for(int l=0;l<this.n;l++)
		{
			this.matrix_reg[this.pivot_line][l]=this.matrix[this.pivot_line][l]/pivot;
		}
		
		for(int k=0;k<this.m;k++)
		{
			if(k!=this.pivot_line)
			{
				for(int l=0;l<this.n;l++)
				{
					
						this.matrix_reg[k][l]=this.matrix[k][l]-this.matrix[this.pivot_line][l]/this.pivot*this.matrix[k][this.pivot_column];
				}		
			}
		}
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

	public Double getPivot() {
		return pivot;
	}

	public void setPivot(Double pivot) {
		this.pivot = pivot;
	}

	public Double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Double[][] matrix) {
		this.matrix = matrix;
	}

	public Integer getM() {
		return m;
	}

	public void setM(Integer m) {
		this.m = m;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public Double[][] getMatrix_reg() {
		return matrix_reg;
	}

	public void setMatrix_reg(Double[][] matrix_reg) {
		this.matrix_reg = matrix_reg;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
