package org.apache.bcel.classfile;

public interface Visitor {
  void visitCode(Code paramCode);
  
  void visitCodeException(CodeException paramCodeException);
  
  void visitConstantClass(ConstantClass paramConstantClass);
  
  void visitConstantDouble(ConstantDouble paramConstantDouble);
  
  void visitConstantFieldref(ConstantFieldref paramConstantFieldref);
  
  void visitConstantFloat(ConstantFloat paramConstantFloat);
  
  void visitConstantInteger(ConstantInteger paramConstantInteger);
  
  void visitConstantInterfaceMethodref(ConstantInterfaceMethodref paramConstantInterfaceMethodref);
  
  void visitConstantLong(ConstantLong paramConstantLong);
  
  void visitConstantMethodref(ConstantMethodref paramConstantMethodref);
  
  void visitConstantNameAndType(ConstantNameAndType paramConstantNameAndType);
  
  void visitConstantPool(ConstantPool paramConstantPool);
  
  void visitConstantString(ConstantString paramConstantString);
  
  void visitConstantUtf8(ConstantUtf8 paramConstantUtf8);
  
  void visitConstantValue(ConstantValue paramConstantValue);
  
  void visitDeprecated(Deprecated paramDeprecated);
  
  void visitExceptionTable(ExceptionTable paramExceptionTable);
  
  void visitField(Field paramField);
  
  void visitInnerClass(InnerClass paramInnerClass);
  
  void visitInnerClasses(InnerClasses paramInnerClasses);
  
  void visitJavaClass(JavaClass paramJavaClass);
  
  void visitLineNumber(LineNumber paramLineNumber);
  
  void visitLineNumberTable(LineNumberTable paramLineNumberTable);
  
  void visitLocalVariable(LocalVariable paramLocalVariable);
  
  void visitLocalVariableTable(LocalVariableTable paramLocalVariableTable);
  
  void visitMethod(Method paramMethod);
  
  void visitSourceFile(SourceFile paramSourceFile);
  
  void visitSynthetic(Synthetic paramSynthetic);
  
  void visitUnknown(Unknown paramUnknown);
  
  void visitStackMap(StackMap paramStackMap);
  
  void visitStackMapEntry(StackMapEntry paramStackMapEntry);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Visitor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */