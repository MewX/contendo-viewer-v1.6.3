/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
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
/*     */ final class FormatNumberCall
/*     */   extends FunctionCall
/*     */ {
/*     */   private Expression _value;
/*     */   private Expression _format;
/*     */   private Expression _name;
/*  45 */   private QName _resolvedQName = null;
/*     */   
/*     */   public FormatNumberCall(QName fname, Vector arguments) {
/*  48 */     super(fname, arguments);
/*  49 */     this._value = argument(0);
/*  50 */     this._format = argument(1);
/*  51 */     this._name = (argumentCount() == 3) ? argument(2) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  57 */     getStylesheet().numberFormattingUsed();
/*     */     
/*  59 */     Type tvalue = this._value.typeCheck(stable);
/*  60 */     if (!(tvalue instanceof org.apache.xalan.xsltc.compiler.util.RealType)) {
/*  61 */       this._value = new CastExpr(this._value, Type.Real);
/*     */     }
/*  63 */     Type tformat = this._format.typeCheck(stable);
/*  64 */     if (!(tformat instanceof org.apache.xalan.xsltc.compiler.util.StringType)) {
/*  65 */       this._format = new CastExpr(this._format, Type.String);
/*     */     }
/*  67 */     if (argumentCount() == 3) {
/*  68 */       Type tname = this._name.typeCheck(stable);
/*     */       
/*  70 */       if (this._name instanceof LiteralExpr) {
/*  71 */         LiteralExpr literal = (LiteralExpr)this._name;
/*  72 */         this._resolvedQName = getParser().getQNameIgnoreDefaultNs(literal.getValue());
/*     */       
/*     */       }
/*  75 */       else if (!(tname instanceof org.apache.xalan.xsltc.compiler.util.StringType)) {
/*  76 */         this._name = new CastExpr(this._name, Type.String);
/*     */       } 
/*     */     } 
/*  79 */     return this._type = Type.String;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  83 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  84 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/*  86 */     this._value.translate(classGen, methodGen);
/*  87 */     this._format.translate(classGen, methodGen);
/*     */     
/*  89 */     int fn3arg = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "formatNumber", "(DLjava/lang/String;Ljava/text/DecimalFormat;)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     int get = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "getDecimalFormat", "(Ljava/lang/String;)Ljava/text/DecimalFormat;");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     il.append(classGen.loadTranslet());
/* 100 */     if (this._name == null) {
/* 101 */       il.append((CompoundInstruction)new PUSH(cpg, ""));
/*     */     }
/* 103 */     else if (this._resolvedQName != null) {
/* 104 */       il.append((CompoundInstruction)new PUSH(cpg, this._resolvedQName.toString()));
/*     */     } else {
/*     */       
/* 107 */       this._name.translate(classGen, methodGen);
/*     */     } 
/* 109 */     il.append((Instruction)new INVOKEVIRTUAL(get));
/* 110 */     il.append((Instruction)new INVOKESTATIC(fn3arg));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/FormatNumberCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */