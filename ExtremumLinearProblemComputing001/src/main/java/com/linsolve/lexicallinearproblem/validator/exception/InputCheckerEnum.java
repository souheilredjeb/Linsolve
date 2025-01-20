package com.linsolve.lexicallinearproblem.validator.exception;

public enum InputCheckerEnum 
{
	PROBLEM_MALFORMATION_ERROR(0, "Error : Linear Problem Malformation Error!"),
	OBJECTIVE_FUNCTION_MALFORMATION_ERROR(1, "Error : Objective Function Malformation !"),
	OBJECTIVE_FUNCTION_EXTREMA_NOT_RECOGNIZED(2,"Error : Objective Function Extremum not recognized !"),
	OBJECTIVE_FUNCTION_VAR_NOT_RECOGNIZED(3,"Error : Objective Function Variable not recognized !"),
	OBJECTIVE_FUNCTION_EXPRESSION_MALFORMATION(4,"Error : Objective Function Expression Malformation !"),
	CONSTRAINTS_MALFORMATION_ERROR(5, "Error : Constraint Malformation !"),
	CONSTRAINTS_RIGHT_MEMBER_ERROR(6,"Error : Constraint Malformation Right Member does not exist or does not match Double Format Number !"),
	CONSTRAINTS_COMPARING_OPERATOR_ERROR(7,"Error: Constraint Malformation could not Recognize Comparing Operator !"),
	CONSTRAINTS_COMPARING_OPERATOR_REDUNDUNCY(8,"Error : Constraint Malformation Comparing Operator Redunduncy or = Symbol Redunduncy"),
	CONSTRAINTS_LEFT_MEMBER_ERROR(9,"Error : Constraint Malformation Left Member of Inequality does not exist or does not match Double Format Number !"),
	
	;
	 private Integer code;

	 private String message;

	private InputCheckerEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
	 
    

}
