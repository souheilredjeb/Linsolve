package com.linsolve.lexicallinearproblem.validator;


import java.util.LinkedHashMap;

public class ValidatingConstraintsSet
{
	private LinkedHashMap<Integer,String> constraints;
	private String[] constrains_converted;
	private ValidatingSingleConstraint vsc;
	private Boolean set_valid;
	
	public ValidatingConstraintsSet(LinkedHashMap<Integer,String> constraints) 
	{
		this.constraints=constraints;
		this.constrains_converted=convertHashMapToArray(constraints);
		this.vsc =new ValidatingSingleConstraint();
		this.set_valid=null;
	}
	
	public void validatingConstraintsSet()
	{
		
		for(int i=0; i<this.constrains_converted.length;i++)
		{
			this.vsc.setLine(constrains_converted[i]);
			this.vsc.setLine_number(i+3);
			this.vsc.validateLexicalSingleConstraint();
			if(this.vsc.getValid().equals(Boolean.FALSE))
				this.set_valid=Boolean.FALSE;
		}
		if(this.set_valid==null)
		{
			this.set_valid=Boolean.TRUE;
		}
		
	}
	
	public static String[] convertHashMapToArray(LinkedHashMap<Integer, String> map) 
	{
        String[] array = new String[map.size()];
        int index = 0;
        for (String value : map.values()) {
            array[index++] = value;
        }
        return array;
    }
	
	
	public Boolean getSet_valid() {
		return set_valid;
	}

	public void setSet_valid(Boolean set_valid) {
		this.set_valid = set_valid;
	}

	public LinkedHashMap<Integer, String> getConstraints() {
		return constraints;
	}

	public void setConstraints(LinkedHashMap<Integer, String> constraints) {
		this.constraints = constraints;
	}
	
	

}
