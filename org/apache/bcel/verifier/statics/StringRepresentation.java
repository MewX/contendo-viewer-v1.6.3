/*     */ package org.apache.bcel.verifier.statics;
/*     */ 
/*     */ import org.apache.bcel.classfile.Code;
/*     */ import org.apache.bcel.classfile.CodeException;
/*     */ import org.apache.bcel.classfile.ConstantClass;
/*     */ import org.apache.bcel.classfile.ConstantDouble;
/*     */ import org.apache.bcel.classfile.ConstantFieldref;
/*     */ import org.apache.bcel.classfile.ConstantFloat;
/*     */ import org.apache.bcel.classfile.ConstantInteger;
/*     */ import org.apache.bcel.classfile.ConstantInterfaceMethodref;
/*     */ import org.apache.bcel.classfile.ConstantLong;
/*     */ import org.apache.bcel.classfile.ConstantMethodref;
/*     */ import org.apache.bcel.classfile.ConstantNameAndType;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.ConstantString;
/*     */ import org.apache.bcel.classfile.ConstantUtf8;
/*     */ import org.apache.bcel.classfile.ConstantValue;
/*     */ import org.apache.bcel.classfile.Deprecated;
/*     */ import org.apache.bcel.classfile.EmptyVisitor;
/*     */ import org.apache.bcel.classfile.ExceptionTable;
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.classfile.InnerClass;
/*     */ import org.apache.bcel.classfile.InnerClasses;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ import org.apache.bcel.classfile.LineNumber;
/*     */ import org.apache.bcel.classfile.LineNumberTable;
/*     */ import org.apache.bcel.classfile.LocalVariable;
/*     */ import org.apache.bcel.classfile.LocalVariableTable;
/*     */ import org.apache.bcel.classfile.Method;
/*     */ import org.apache.bcel.classfile.Node;
/*     */ import org.apache.bcel.classfile.SourceFile;
/*     */ import org.apache.bcel.classfile.Synthetic;
/*     */ import org.apache.bcel.classfile.Unknown;
/*     */ import org.apache.bcel.classfile.Visitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringRepresentation
/*     */   extends EmptyVisitor
/*     */   implements Visitor
/*     */ {
/*     */   private String tostring;
/*     */   
/*     */   public StringRepresentation(Node n) {
/*  83 */     n.accept(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  89 */     return this.tostring;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String toString(Node obj) {
/*     */     String str;
/*     */     try {
/*  99 */       str = obj.toString();
/*     */     } catch (RuntimeException e) {
/*     */       
/* 102 */       String s = obj.getClass().getName();
/* 103 */       s = s.substring(s.lastIndexOf(".") + 1);
/* 104 */       str = "<<" + s + ">>";
/*     */     } 
/* 106 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitCode(Code obj) {
/* 116 */     this.tostring = "<CODE>";
/*     */   }
/*     */   public void visitCodeException(CodeException obj) {
/* 119 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantClass(ConstantClass obj) {
/* 122 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantDouble(ConstantDouble obj) {
/* 125 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantFieldref(ConstantFieldref obj) {
/* 128 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantFloat(ConstantFloat obj) {
/* 131 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantInteger(ConstantInteger obj) {
/* 134 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantInterfaceMethodref(ConstantInterfaceMethodref obj) {
/* 137 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantLong(ConstantLong obj) {
/* 140 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantMethodref(ConstantMethodref obj) {
/* 143 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantNameAndType(ConstantNameAndType obj) {
/* 146 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantPool(ConstantPool obj) {
/* 149 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantString(ConstantString obj) {
/* 152 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantUtf8(ConstantUtf8 obj) {
/* 155 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitConstantValue(ConstantValue obj) {
/* 158 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitDeprecated(Deprecated obj) {
/* 161 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitExceptionTable(ExceptionTable obj) {
/* 164 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitField(Field obj) {
/* 167 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitInnerClass(InnerClass obj) {
/* 170 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitInnerClasses(InnerClasses obj) {
/* 173 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitJavaClass(JavaClass obj) {
/* 176 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitLineNumber(LineNumber obj) {
/* 179 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitLineNumberTable(LineNumberTable obj) {
/* 182 */     this.tostring = "<LineNumberTable: " + toString((Node)obj) + ">";
/*     */   }
/*     */   public void visitLocalVariable(LocalVariable obj) {
/* 185 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitLocalVariableTable(LocalVariableTable obj) {
/* 188 */     this.tostring = "<LocalVariableTable: " + toString((Node)obj) + ">";
/*     */   }
/*     */   public void visitMethod(Method obj) {
/* 191 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitSourceFile(SourceFile obj) {
/* 194 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitSynthetic(Synthetic obj) {
/* 197 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */   public void visitUnknown(Unknown obj) {
/* 200 */     this.tostring = toString((Node)obj);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/statics/StringRepresentation.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */