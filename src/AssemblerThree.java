/**
 * CS 506 Assembler Project Part 2 
 * Prof. Nemes
 * @author Mark Caruso
 * This version of the assembler reads in a file containing
 * assembly language, assemblers it into machine code, 
 * then executes the program using the and array from the
 * LineOfMachineCode class. 
 */
import java.io.*;
import java.util.Scanner;

public class AssemblerThree {

	public static void main(String[] args) throws IOException {
		// initialize an array of type LineOfCode
		LineOfCode[] inputProgram = new LineOfCode[32];
		// Initialize an array of type LineOfMachineCode
		LineOfMachineCode[] instructions = new LineOfMachineCode[32];
		String[] opcodes = {"LDA", "ADD", "SUB", "STA", "MPY", "DIV", 
				"INP", "OUT", "JMP", "JMI" }; 
		String[] tokens = new String[80];
		String line = "";
		
		// prepare to read program from file
		File filename = new File("MysteryProgram_0.txt");
		Scanner filescan = new Scanner(filename);
		Scanner input = new Scanner(System.in);
		int numOfLines = 0; 
		
		// Read the file containing the program one line at a time.
		// instantiate a LineOfCode Object for each line read in
		// and assign line tokens to the fields of the new object.
		while(filescan.hasNext()){
			line = filescan.nextLine();
			String comments = " ";
			// instantiate a LineOfCode object with empty fields
			inputProgram[numOfLines] = new LineOfCode();
			
			if(!line.isEmpty()){
			tokens = line.split("\\s+");
			
			// if comments exits, create a comments string to holed them
			// and assign the comments to the appropriate instance variable
				if(tokens.length > 3 ){
					for(int i = 3; i < tokens.length; i++ )						
					comments += tokens[i] + " ";	
				}else{
					comments = " "; 
				}
			inputProgram[numOfLines].setComments(comments);
			
			// check each token from the line and assign it to 
			// its appropriate instance variable
			if(tokens[1].equals("DC")){
				inputProgram[numOfLines].setOpcode(tokens[1]);
				inputProgram[numOfLines].setLabel(tokens[0]);
				inputProgram[numOfLines].setOperand(tokens[2]);
			}else if(tokens[1].equals("DL")){
				inputProgram[numOfLines].setOpcode(tokens[1]);
				inputProgram[numOfLines].setLabel(tokens[0]);
			}else{// Big Else			
				
					for(int x = 0;x < opcodes.length; x++){
						if(opcodes[x].equals(tokens[0])){
						inputProgram[numOfLines].setOpcode(tokens[0]);
						inputProgram[numOfLines].setOperand(tokens[1]);
						}
						if(opcodes[x].equals(tokens[1])){
							inputProgram[numOfLines].setOpcode(tokens[1]);
							inputProgram[numOfLines].setLabel(tokens[0]);
							inputProgram[numOfLines].setOperand(tokens[2]);				
						}
				} //for loop
			
			} //Big Else
				
			
				numOfLines++;
			} 	//Super If	
			
		} // while, 
		
		
		// finished reading the file
		filescan.close();
		
		// Assemble machine code out of each LineOfCode object 
		// from the inputProgram array.  
		for(int i = 0; i < numOfLines; i++){
			instructions[i] = new LineOfMachineCode();
			// handle pseudo-opcodes if they are present in the line
			if(inputProgram[i].getOpcode().equals("DL"))
				instructions[i].setOperand(0);
			else if(inputProgram[i].getOpcode().equals("DC"))
					instructions[i].setOperand(Integer.parseInt(inputProgram[i].getOperand()));
			else{
				// helper variables 
				int opc = 0;
				int op = 0;
				for(int j = 0; j < opcodes.length;j++){
					if(inputProgram[i].getOpcode().equals(opcodes[j])){
						// assign opcode in program to correct index of opcode array
						opc = j; 
						instructions[i].setOpcode(opc);
					}
				}
				
				for(int k = 0; k < numOfLines; k++){
					if(inputProgram[k].getLabel().equals(inputProgram[i].getOperand()))
					{
						// assign memory location of label to operand
						op = k; 
						instructions[i].setOperand(op);
					}
				}
				
				//if opcode == 0 which is LDA how to handle it
				// handle operand greater than 9 ex. 12
				if(opc == 0 && op > 9)
					//System.out.print(opc + "" + op );
					instructions[i].setOpcode(opc);
				
				//else if(opc == 0 && op < 10)
					//System.out.print(opc + "0" + op  + "\n");
			else{	
				//System.out.print( (opc * 100 + op)  + "\n");
				instructions[i].setOpcode(opc);
				instructions[i].setOperand(op);
				}
			} // Big else
			
			
			// At this point we have everything converted to machine code
			// Option I - have another for loop start here to begin execution of lineOfMachineCode
		}// outer for 
		
		// Vars used during execution Execute the program
		int pc = 0; 				
		int store = 0; 
		int opcode = 0;		 
		int value = 0;  
		int accumulator = 0; 			
		// Program execution
		while( pc < numOfLines){

				opcode = instructions[pc].getOpcode();
			
				if(opcode == 8){
				pc = instructions[pc].getOperand();
				opcode = instructions[pc].getOpcode();
				}else if(opcode == 9 && accumulator < 0 ){
					pc = instructions[pc].getOperand();
					opcode = instructions[pc].getOpcode();
				}
				
				
			switch(opcode){
			case 0: // LDA 
				if(inputProgram[pc].getOpcode().equals("DC"))
				accumulator = instructions[pc].getOperand();
				else if(inputProgram[pc].getOpcode().equals("DL"))
					accumulator = instructions[pc].getOperand();
				break;
			
			case 1: //ADD
					accumulator += value;
				break;
			case 2: //SUB
					accumulator -= value;
				break;
			case 3: // STA
				store = accumulator; 
				break;
			case 4: // MPY
					accumulator *= value;
				break;
			case 5: // DIV
					accumulator /= value;
				break;
			case 6: //INP
				System.out.println("Enter an Integer");
				value = input.nextInt();
				break;
			case 7: // OUT
				System.out.println(store);
				break;
			default:
				System.out.println("Invalid opcode");
				break;
			}
			
			pc++;
		}// while
		System.out.println("Total Lines:  " + numOfLines);
	} //main


} //Assembler
