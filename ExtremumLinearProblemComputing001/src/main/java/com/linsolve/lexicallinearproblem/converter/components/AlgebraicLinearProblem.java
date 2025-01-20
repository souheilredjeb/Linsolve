package com.linsolve.lexicallinearproblem.converter.components;

public class AlgebraicLinearProblem
{
	private ExtremumEnum extrema;
	private Double[][] initial_matrix;
	private Double[] b_vector;
	private Double[] c_vector;
	private PhaseEnum phase;

	public AlgebraicLinearProblem()
	{

	}
	
	public AlgebraicLinearProblem(ExtremumEnum extrema, Double[][] initial_matrix, Double[] b_vector, Double[] c_vector,PhaseEnum phase ) 
	{
		super();
		this.initial_matrix = initial_matrix;
		this.b_vector = b_vector;
		this.c_vector = c_vector;
		this.phase=phase;
		this.extrema=extrema;
	}
	
	

	public void display_alp()
	{
		System.out.println();
		System.out.println(this.extrema.getLibelle());
		System.out.println();
		System.out.println(this.phase.getLabel());
		System.out.println();
		System.out.println("c_vector");
		System.out.println();
		display(this.c_vector);
		System.out.println();
		System.out.println("b_vector");
		System.out.println();
		display(this.b_vector);
		System.out.println();
		System.out.println("Initial Matrix");
		System.out.println();
		display(this.initial_matrix);
		return;
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
				System.out.print("|");
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
			System.out.print("|");
		}
		System.out.println();
	}
	
	public Double[][] getInitial_matrix() {
		return initial_matrix;
	}

	public void setInitial_matrix(Double[][] initial_matrix) {
		this.initial_matrix = initial_matrix;
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

	public ExtremumEnum getExtrema() {
		return extrema;
	}

	public void setExtrema(ExtremumEnum extrema) {
		this.extrema = extrema;
	}

	public PhaseEnum getPhase() {
		return phase;
	}

	public void setPhase(PhaseEnum phase) {
		this.phase = phase;
	}
}
