/**
 * The line of code class defines a single line
 * of our assembler language program.   
 * @author Caruso
 *
 */
public class LineOfCode {
	private String label;
	private String opcode;
	private String operand;
	private String comments;
	
	// no args default constructor initializes each line of code
	// object with instance variables set to an empty string. 
	public LineOfCode(){
		 label = "";
		 opcode = "";
		operand = "";
		comments = "";
	}
	
	// constructor accepting 4 string parameters
	// which initialize the objects instance variables.
	public LineOfCode(String l, String o, String op, String cmt){
	 label = l;
	 opcode = o;
	operand = op;
	comments = cmt;
	}
	
	// mutator methods to set each instance variable
	public void setLabel(String l){
		label = l; 
	}
	
	public void setOpcode(String opc){
		opcode = opc;
	}
	public void setOperand(String opr){
		operand  = opr; 
	}
	public void setComments(String cm){
		comments = cm;
	}
	// accessor methods to retrieve each field
	public String getLabel(){
		return label;
	}
	
	public String getOpcode(){
		return opcode;
	} 
	
	public String getOperand(){
		return operand; 
	}
	
	public String getComments(){
		return comments;
	}
	
	// Overriding Object() toString(), useful for investigating
	// a line of code object
	public String toString(){
		String result = "";
		result += "The label is: " + label;
		result += "\nThe Opcode is: " + opcode;
		result += "\nThe operand is: " + operand;
		result += "\nThe comment is " + comments;
		
		return result; 
	}
} // LineOfCode
