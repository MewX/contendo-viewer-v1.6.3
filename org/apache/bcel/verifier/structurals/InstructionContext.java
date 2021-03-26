package org.apache.bcel.verifier.structurals;

import java.util.ArrayList;
import org.apache.bcel.generic.InstructionHandle;

public interface InstructionContext {
  int getTag();
  
  void setTag(int paramInt);
  
  boolean execute(Frame paramFrame, ArrayList paramArrayList, InstConstraintVisitor paramInstConstraintVisitor, ExecutionVisitor paramExecutionVisitor);
  
  Frame getOutFrame(ArrayList paramArrayList);
  
  InstructionHandle getInstruction();
  
  InstructionContext[] getSuccessors();
  
  ExceptionHandler[] getExceptionHandlers();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/InstructionContext.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */