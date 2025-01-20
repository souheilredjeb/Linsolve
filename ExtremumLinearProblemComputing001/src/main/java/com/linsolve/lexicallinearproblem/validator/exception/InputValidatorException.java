package com.linsolve.lexicallinearproblem.validator.exception;

public class InputValidatorException extends RuntimeException
{

	private static final long serialVersionUID = 3478181457896911666L;
	private Integer code;
	
    public InputValidatorException(InputCheckerEnum inputCheckerEnum) 
    {
        super(inputCheckerEnum.getMessage());
        this.code = inputCheckerEnum.getCode();
    }
    
    public InputValidatorException(Integer code, String message) {
        super(message);
        this.code = code;
    }

	public InputValidatorException() 
	{

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	

}
