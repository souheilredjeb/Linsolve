package com.linsolve.linearproblemmain;



import com.linsolve.doublephaselinearproblem.BuildingAugmentedMatrixDoublePhase;
import com.linsolve.doublephaselinearproblem.SolvingAuxiliaryProblemDoublePhase;
import com.linsolve.doublephaselinearproblem.SolvingDoublePhaseMaximize;
import com.linsolve.doublephaselinearproblem.SolvingDoublePhaseMinimize;
import com.linsolve.singlephaselinearproblem.BuildingAugmentedMatrixSinglePhase;
import com.linsolve.singlephaselinearproblem.SolvingSinglePhaseMaximize;
import com.linsolve.singlephaselinearproblem.SolvingSinglePhaseMinimize;
import com.linsolve.lexicallinearproblem.converter.ConvertingLexicalLinearProblem;
import com.linsolve.lexicallinearproblem.converter.components.AlgebraicLinearProblem;
import com.linsolve.lexicallinearproblem.converter.components.ExtremumEnum;
import com.linsolve.lexicallinearproblem.converter.components.PhaseEnum;
import com.linsolve.lexicallinearproblem.validator.ValidatingLexicalLinearProblem;

public class SolvingLinearProblemMain
{
	
	private String linear_problem;
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
	
	
	public SolvingLinearProblemMain() throws Exception
	{
		this.vllp= new ValidatingLexicalLinearProblem();
		if(vllp.getValid_linear_problem())
		{
			this.linear_problem=String.join("",vllp.getLines());
			System.out.println(this.linear_problem);
			this.cllp=new ConvertingLexicalLinearProblem(linear_problem);
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
					display(sapdp.getAugmented_matrix());
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
	
	public SolvingLinearProblemMain(String linear_problem) throws Exception 
	{
		this.linear_problem = linear_problem;
		this.cllp=new ConvertingLexicalLinearProblem(linear_problem);
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
				display(sapdp.getAugmented_matrix());
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
	
	
	
	public static void display(Double[][] matrix)
	{
		System.out.println();
		for(int i=0; i<matrix.length; i++)
		{
			System.out.print("|");
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
		System.out.println("[");
		for(int i=0 ; i<vector.length;i++)
		{
			System.out.print(vector[i]);
			System.out.print("|");
		}
		System.out.println("]");
		System.out.println();
	}

	public String getLinear_problem() {
		return linear_problem;
	}

	public void setLinear_problem(String linear_problem) {
		this.linear_problem = linear_problem;
	}

	public ConvertingLexicalLinearProblem getCllp() 
	{
		return cllp;
	}

	public void setCllp(ConvertingLexicalLinearProblem cllp) 
	{
		this.cllp = cllp;
	}

	public AlgebraicLinearProblem getAlp() 
	{
		return alp;
	}

	public void setAlp(AlgebraicLinearProblem alp)
	{
		this.alp = alp;
	}

	public BuildingAugmentedMatrixSinglePhase getBamsp()
	{
		return bamsp;
	}

	public void setBamsp(BuildingAugmentedMatrixSinglePhase bamsp)
	{
		this.bamsp = bamsp;
	}

	public SolvingSinglePhaseMaximize getSspmax() 
	{
		return sspmax;
	}

	public void setSspmax(SolvingSinglePhaseMaximize sspmax) 
	{
		this.sspmax = sspmax;
	}

	public SolvingSinglePhaseMinimize getSspmin() 
	{
		return sspmin;
	}

	public void setSspmin(SolvingSinglePhaseMinimize sspmin)
	{
		this.sspmin = sspmin;
	}

	public BuildingAugmentedMatrixDoublePhase getBamdp()
	{
		return bamdp;
	}

	public void setBamdp(BuildingAugmentedMatrixDoublePhase bamdp) 
	{
		this.bamdp = bamdp;
	}

	public SolvingAuxiliaryProblemDoublePhase getSapdp()
	{
		return sapdp;
	}

	public void setSapdp(SolvingAuxiliaryProblemDoublePhase sapdp)
	{
		this.sapdp = sapdp;
	}

	public SolvingDoublePhaseMaximize getSdpmax() 
	{
		return sdpmax;
	}

	public void setSdpmax(SolvingDoublePhaseMaximize sdpmax) 
	{
		this.sdpmax = sdpmax;
	}

	public SolvingDoublePhaseMinimize getSdpmin()
	{
		return sdpmin;
	}

	public void setSdpmin(SolvingDoublePhaseMinimize sdpmin) 
	{
		this.sdpmin = sdpmin;
	}
}
