package com.linsolve.linearproblemmain;

import com.linsolve.doublephaselinearproblem.BuildingAugmentedMatrixDoublePhase;
import com.linsolve.doublephaselinearproblem.SolvingAuxiliaryProblemDoublePhase;
import com.linsolve.doublephaselinearproblem.SolvingDoublePhaseMaximize;
import com.linsolve.doublephaselinearproblem.SolvingDoublePhaseMinimize;
import com.linsolve.lexicallinearproblem.converter.ConvertingLexicalLinearProblem;
import com.linsolve.lexicallinearproblem.converter.components.AlgebraicLinearProblem;
import com.linsolve.lexicallinearproblem.converter.components.ExtremumEnum;
import com.linsolve.lexicallinearproblem.converter.components.PhaseEnum;
import com.linsolve.lexicallinearproblem.validator.ValidatingLexicalLinearProblem;
import com.linsolve.singlephaselinearproblem.BuildingAugmentedMatrixSinglePhase;
import com.linsolve.singlephaselinearproblem.SolvingSinglePhaseMaximize;
import com.linsolve.singlephaselinearproblem.SolvingSinglePhaseMinimize;

public class SolvingLexicalLinearProblemMain 
{
	
	private String linear_problem;
	private String linear_problem_formatted;
	private ConvertingLexicalLinearProblem cllp;
	private AlgebraicLinearProblem alp;
	private BuildingAugmentedMatrixSinglePhase bamsp;
	private SolvingSinglePhaseMaximize sspmax;
	private SolvingSinglePhaseMinimize sspmin;
	private	BuildingAugmentedMatrixDoublePhase bamdp;
	private	SolvingAuxiliaryProblemDoublePhase sapdp;
	private SolvingDoublePhaseMaximize sdpmax;
	private SolvingDoublePhaseMinimize sdpmin;
	private ValidatingLexicalLinearProblem vllp;
	private Boolean valid;

	public SolvingLexicalLinearProblemMain() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	public void solvingLinearProblemMainAfterValidator(String linear_problem_formatted) throws Exception
	{
		
		this.linear_problem_formatted = linear_problem_formatted;
		this.vllp= new ValidatingLexicalLinearProblem(linear_problem_formatted);
		this.valid=this.vllp.getValid_linear_problem();
		if(valid)
		{
					this.linear_problem = linear_problem_formatted
					        .lines() 
					        .map(String::strip) 
					        .reduce((line1, line2) -> line1 + line2) 
					        .orElse("");
					this.cllp=new ConvertingLexicalLinearProblem(this.linear_problem);
					this.alp= new AlgebraicLinearProblem();
					this.alp=this.cllp.convertLexicalLinearToAlgebraicLinearProblem();
					this.alp.display_alp();
					if(this.alp.getExtrema().equals(ExtremumEnum.MAXIMIZE))
					{
						if(this.alp.getPhase().equals(PhaseEnum.SINGLE_PHASE))
						{
						   this.bamsp= new BuildingAugmentedMatrixSinglePhase(this.alp.getInitial_matrix(),this.alp.getB_vector(),this.alp.getC_vector());
						   this.sspmax= new SolvingSinglePhaseMaximize(bamsp);
						   this.sspmax.apply_simplex_single_phase();
						   this.sspmax.compute_goal_function();
						   this.sspmax.enumerate_based_variables_values();	
						}
						else if(this.alp.getPhase().equals(PhaseEnum.DOUBLE_PHASE))
						{
							bamdp= new BuildingAugmentedMatrixDoublePhase(alp.getInitial_matrix(), alp.getB_vector(), alp.getC_vector());
							sapdp= new SolvingAuxiliaryProblemDoublePhase(bamdp);
							sapdp.apply_simplex_phase_I();
							sapdp.reevaluate_augmented_matrix();
							sapdp.reevaluate_base_phase_II();
							sapdp.reevaluate_zj_cj_phase_II();
							sdpmax = new SolvingDoublePhaseMaximize(sapdp);
							sdpmax.apply_simplex_phase_II();
							sdpmax.compute_goal_function();
							sdpmax.enumerate_based_variables_values();
						}	
					}
					else if(this.alp.getExtrema().equals(ExtremumEnum.MINIMIZE))
					{
						if(this.alp.getPhase().equals(PhaseEnum.SINGLE_PHASE))
						{
							 this.bamsp= new BuildingAugmentedMatrixSinglePhase(this.alp.getInitial_matrix(),this.alp.getB_vector(),this.alp.getC_vector());
							 this.sspmin=new SolvingSinglePhaseMinimize(bamsp);
							 this.sspmin.apply_simplex_single_phase();
							 this.sspmin.compute_goal_function();
							 this.sspmin.enumerate_based_variables_values();		
						}
						else if(this.alp.getPhase().equals(PhaseEnum.DOUBLE_PHASE))
						{
							bamdp= new BuildingAugmentedMatrixDoublePhase(alp.getInitial_matrix(), alp.getB_vector(), alp.getC_vector());
							sapdp= new SolvingAuxiliaryProblemDoublePhase(bamdp);
							sapdp.apply_simplex_phase_I();
							sapdp.reevaluate_augmented_matrix();	
							sapdp.reevaluate_base_phase_II();
							sapdp.reevaluate_zj_cj_phase_II();
							sdpmin= new SolvingDoublePhaseMinimize(sapdp);
							sdpmin.apply_simplex_phase_II();
							sdpmin.compute_goal_function();
							sdpmin.enumerate_based_variables_values();	
						}
					}
		}
		
	}

	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		String linear_problem_formatted_1 = """
		        Maximize Z = 7x1 + 4x2
		        subject to
		        x2 <= 20,
		        x1 + 3x2 <= 50,
		        4x1 + 2x2 <= 60,
		        x1 <= 25,
		        non negative constraints
		        """;
		
		SolvingLexicalLinearProblemMain sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_1);
		System.out.println(sllpm.getValid());
		
		String linear_problem_formatted_2="""
				Maximize Z = 2x1 + x2
				subject to
				x2 <= 10,
				2x1 + 5x2 <= 60,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints""";
		
	    sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_2);
		System.out.println(sllpm.getValid());
		
		String linear_problem_formatted_3="""
				Maximize Z = 4x1 + 3x2
				subject to
				x1 <=  12,
				3x1 + x2 <=  30,
				2x1 + 5x2 <=  50,
				x1 + x2 <=  20,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_3);
		System.out.println(sllpm.getValid());
		String linear_problem_formatted_4="""
				Maximize Z = 3x1 + 4x2
				subject to
				x2 <= 20,
				x1 + 3x2 <= 50,
				4x1 + 2x2 <= 60,
				2x1 + x2 <= 30,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_4);
		System.out.println(sllpm.getValid());
		String linear_problem_formatted_5=
				"""
				Minimize Z = 3x1 + 5x2
				subject to
				x1 <= 10,
				2x1 + 4x2 <= 50,
				3x1 + x2 <= 25,
				x2 <= 15,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_5);
		System.out.println(sllpm.getValid());
		
		String linear_problem_formatted_6=
				"""
				Maximize Z = 2x1 + 3x2
				subject to
				x1 <= 12,
				3x1 + x2 <= 35,
				2x1 + 5x2 <= 60,
				x2 <= 10,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_6);
		System.out.println(sllpm.getValid());
		
		String linear_problem_formatted_7=		
				"""
				Minimize Z = x1 + x2
				subject to
				x1 + 2x2 <= 20,
				3x1 + x2 <= 25,
				x1 + 4x2 <= 40,
				x2 <= 15,
				non negative constraints
				""";
		
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_7);
		System.out.println(sllpm.getValid());
		
		
		String linear_problem_formatted_8=		
				"""
				Maximize Z = 6x1 + 2x2
				subject to
				x1 <= 15,
				x2 <= 10,
				3x1 + 2x2 <= 45,
				2x1 + x2 <= 30,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_8);
		System.out.println(sllpm.getValid());
		String linear_problem_formatted_9=
				"""
				Maximize Z = 7x1 + 4x2
				subject to
				x2 <= 20,
				x1 + 3x2 <= 50,
				4x1 + 2x2 <= 60,
				x1 <= 25,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_9);
		System.out.println(sllpm.getValid());
		String linear_problem_formatted_10=
				"""
				Minimize Z = 4x1 + 6x2
				subject to
				x1 + x2 <= 30,
				3x1 + x2 <= 50,
				2x1 + 4x2 <= 80,
				x1 <= 20,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_10);
		System.out.println(sllpm.getValid());
		String linear_problem_formatted_11=
				"""
				Maximize Z = 5x1 + 3x2
				subject to
				x1 <= 18,
				x2 <= 14,
				3x1 + x2 <= 45,
				2x1 + 5x2 <= 65,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_11);
		System.out.println(sllpm.getValid());
		String linear_problem_formatted_12=
				"""
				Maximize Z = 2x1 + x2
				subject to
				x1 + 5x2 <=,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_12);
		System.out.println(sllpm.getValid());
		String linear_problem_formatted_13=
				"""
				Maximize Z = 2x1 + x2
				subject to
				2x1 + 5x2 <= sixty,
				x1 + x2 <= 18,
				3x1 + x2 <= 44,
				non negative constraints
				""";
		sllpm=new SolvingLexicalLinearProblemMain();
		sllpm.solvingLinearProblemMainAfterValidator(linear_problem_formatted_13);
		System.out.println(sllpm.getValid());			
	}



	public String getLinear_problem() {
		return linear_problem;
	}



	public void setLinear_problem(String linear_problem) {
		this.linear_problem = linear_problem;
	}



	public String getLinear_problem_formatted() {
		return linear_problem_formatted;
	}



	public void setLinear_problem_formatted(String linear_problem_formatted) {
		this.linear_problem_formatted = linear_problem_formatted;
	}



	public ConvertingLexicalLinearProblem getCllp() {
		return cllp;
	}



	public void setCllp(ConvertingLexicalLinearProblem cllp) {
		this.cllp = cllp;
	}



	public AlgebraicLinearProblem getAlp() {
		return alp;
	}



	public void setAlp(AlgebraicLinearProblem alp) {
		this.alp = alp;
	}



	public BuildingAugmentedMatrixSinglePhase getBamsp() {
		return bamsp;
	}



	public void setBamsp(BuildingAugmentedMatrixSinglePhase bamsp) {
		this.bamsp = bamsp;
	}



	public SolvingSinglePhaseMaximize getSspmax() {
		return sspmax;
	}



	public void setSspmax(SolvingSinglePhaseMaximize sspmax) {
		this.sspmax = sspmax;
	}



	public SolvingSinglePhaseMinimize getSspmin() {
		return sspmin;
	}



	public void setSspmin(SolvingSinglePhaseMinimize sspmin) {
		this.sspmin = sspmin;
	}



	public BuildingAugmentedMatrixDoublePhase getBamdp() {
		return bamdp;
	}



	public void setBamdp(BuildingAugmentedMatrixDoublePhase bamdp) {
		this.bamdp = bamdp;
	}



	public SolvingAuxiliaryProblemDoublePhase getSapdp() {
		return sapdp;
	}



	public void setSapdp(SolvingAuxiliaryProblemDoublePhase sapdp) {
		this.sapdp = sapdp;
	}



	public SolvingDoublePhaseMaximize getSdpmax() {
		return sdpmax;
	}



	public void setSdpmax(SolvingDoublePhaseMaximize sdpmax) {
		this.sdpmax = sdpmax;
	}



	public SolvingDoublePhaseMinimize getSdpmin() {
		return sdpmin;
	}



	public void setSdpmin(SolvingDoublePhaseMinimize sdpmin) {
		this.sdpmin = sdpmin;
	}



	public ValidatingLexicalLinearProblem getVllp() {
		return vllp;
	}



	public void setVllp(ValidatingLexicalLinearProblem vllp) {
		this.vllp = vllp;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	
	

}
