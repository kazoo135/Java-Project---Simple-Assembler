/**
 * This class will be used to represent a single
 * line of machine - in something like an IR. 
 * @author Caruso
 */
public class LineOfMachineCode {
	
	private int opcode;
	private int operand; 
	
	// no args conctructor with instance vars
	//initialized to 0
	public LineOfMachineCode(){
		opcode = 0;
		operand = 0; 
	}
	//two arg constructor taking int params
	// opc and op
	public LineOfMachineCode(int opc, int opr){
		opcode = opc;
		operand = opr; 
	}
	
	//accessor methods
	public int getOperand(){
		return operand;
	}
	
	public int getOpcode(){
		return opcode; 
	}
	
	// mutator method
	public void setOpcode(int opcd){
		opcode = opcd;
	}
	
	public void setOperand(int oprand){
		operand = oprand; 
	}
	
	public String toString(){
		String str = "";
		str += "Opcode at this line " + opcode + "\n";
		str += "Operand at this line " + operand + "\n"; 
		
		return str; 
	}
} // LineOfMachineCode
