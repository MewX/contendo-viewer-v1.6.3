package org.apache.bcel.generic;

public interface InstructionTargeter {
  boolean containsTarget(InstructionHandle paramInstructionHandle);
  
  void updateTarget(InstructionHandle paramInstructionHandle1, InstructionHandle paramInstructionHandle2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/InstructionTargeter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */