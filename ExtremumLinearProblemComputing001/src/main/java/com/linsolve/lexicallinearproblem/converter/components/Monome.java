package com.linsolve.lexicallinearproblem.converter.components;

public class Monome 
{
	private Integer coefficient_index;
	private Double  coefficient_value;

	public Monome() 
	{
	}
	public Monome(Integer coefficient_index, Double coefficient_value) {
		super();
		this.coefficient_index = coefficient_index;
		this.coefficient_value = coefficient_value;
	}
	public Integer getCoefficient_index() {
		return coefficient_index;
	}
	public void setCoefficient_index(Integer coefficient_index) {
		this.coefficient_index = coefficient_index;
	}
	public Double getCoefficient_value() {
		return coefficient_value;
	}
	public void setCoefficient_value(Double coefficient_value) {
		this.coefficient_value = coefficient_value;
	}
}
