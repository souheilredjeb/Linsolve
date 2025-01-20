package com.linsolve.lexicallinearproblem.converter.components;

public enum PhaseEnum 
{
	
	SINGLE_PHASE(1,"SinglePhase"),
	DOUBLE_PHASE(-1,"DoublePhase");	
	private int code;
	private String label;
	private PhaseEnum(int code, String label) {
		this.code = code;
		this.label = label;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	

}
