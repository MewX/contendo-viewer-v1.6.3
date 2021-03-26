package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.InstructionHandle;

public interface Subroutine {
  InstructionHandle[] getEnteringJsrInstructions();
  
  InstructionHandle getLeavingRET();
  
  InstructionHandle[] getInstructions();
  
  boolean contains(InstructionHandle paramInstructionHandle);
  
  int[] getAccessedLocalsIndices();
  
  int[] getRecursivelyAccessedLocalsIndices();
  
  Subroutine[] subSubs();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/Subroutine.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */