/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETSTATIC;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xml.utils.XMLChar;
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
/*     */ final class DecimalFormatting
/*     */   extends TopLevelElement
/*     */ {
/*     */   private static final String DFS_CLASS = "java.text.DecimalFormatSymbols";
/*     */   private static final String DFS_SIG = "Ljava/text/DecimalFormatSymbols;";
/*  46 */   private QName _name = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  52 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  60 */     String name = getAttribute("name");
/*  61 */     if (name.length() > 0 && 
/*  62 */       !XMLChar.isValidQName(name)) {
/*  63 */       ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
/*  64 */       parser.reportError(3, err);
/*     */     } 
/*     */     
/*  67 */     this._name = parser.getQNameIgnoreDefaultNs(name);
/*  68 */     if (this._name == null) {
/*  69 */       this._name = parser.getQNameIgnoreDefaultNs("");
/*     */     }
/*     */ 
/*     */     
/*  73 */     SymbolTable stable = parser.getSymbolTable();
/*  74 */     if (stable.getDecimalFormatting(this._name) != null) {
/*  75 */       reportWarning(this, parser, "SYMBOLS_REDEF_ERR", this._name.toString());
/*     */     }
/*     */     else {
/*     */       
/*  79 */       stable.addDecimalFormatting(this._name, this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  89 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  90 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */ 
/*     */     
/*  94 */     int init = cpg.addMethodref("java.text.DecimalFormatSymbols", "<init>", "(Ljava/util/Locale;)V");
/*     */ 
/*     */ 
/*     */     
/*  98 */     il.append(classGen.loadTranslet());
/*  99 */     il.append((CompoundInstruction)new PUSH(cpg, this._name.toString()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     il.append((Instruction)new NEW(cpg.addClass("java.text.DecimalFormatSymbols")));
/* 106 */     il.append((Instruction)InstructionConstants.DUP);
/* 107 */     il.append((Instruction)new GETSTATIC(cpg.addFieldref("java.util.Locale", "US", "Ljava/util/Locale;")));
/*     */     
/* 109 */     il.append((Instruction)new INVOKESPECIAL(init));
/*     */     
/* 111 */     String tmp = getAttribute("NaN");
/* 112 */     if (tmp == null || tmp.equals("")) {
/* 113 */       int nan = cpg.addMethodref("java.text.DecimalFormatSymbols", "setNaN", "(Ljava/lang/String;)V");
/*     */       
/* 115 */       il.append((Instruction)InstructionConstants.DUP);
/* 116 */       il.append((CompoundInstruction)new PUSH(cpg, "NaN"));
/* 117 */       il.append((Instruction)new INVOKEVIRTUAL(nan));
/*     */     } 
/*     */     
/* 120 */     tmp = getAttribute("infinity");
/* 121 */     if (tmp == null || tmp.equals("")) {
/* 122 */       int inf = cpg.addMethodref("java.text.DecimalFormatSymbols", "setInfinity", "(Ljava/lang/String;)V");
/*     */ 
/*     */       
/* 125 */       il.append((Instruction)InstructionConstants.DUP);
/* 126 */       il.append((CompoundInstruction)new PUSH(cpg, "Infinity"));
/* 127 */       il.append((Instruction)new INVOKEVIRTUAL(inf));
/*     */     } 
/*     */     
/* 130 */     int nAttributes = this._attributes.getLength();
/* 131 */     for (int i = 0; i < nAttributes; i++) {
/* 132 */       String name = this._attributes.getQName(i);
/* 133 */       String value = this._attributes.getValue(i);
/*     */       
/* 135 */       boolean valid = true;
/* 136 */       int method = 0;
/*     */       
/* 138 */       if (name.equals("decimal-separator")) {
/*     */         
/* 140 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setDecimalSeparator", "(C)V");
/*     */       
/*     */       }
/* 143 */       else if (name.equals("grouping-separator")) {
/* 144 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setGroupingSeparator", "(C)V");
/*     */       
/*     */       }
/* 147 */       else if (name.equals("minus-sign")) {
/* 148 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setMinusSign", "(C)V");
/*     */       
/*     */       }
/* 151 */       else if (name.equals("percent")) {
/* 152 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setPercent", "(C)V");
/*     */       
/*     */       }
/* 155 */       else if (name.equals("per-mille")) {
/* 156 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setPerMill", "(C)V");
/*     */       
/*     */       }
/* 159 */       else if (name.equals("zero-digit")) {
/* 160 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setZeroDigit", "(C)V");
/*     */       
/*     */       }
/* 163 */       else if (name.equals("digit")) {
/* 164 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setDigit", "(C)V");
/*     */       
/*     */       }
/* 167 */       else if (name.equals("pattern-separator")) {
/* 168 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setPatternSeparator", "(C)V");
/*     */       
/*     */       }
/* 171 */       else if (name.equals("NaN")) {
/* 172 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setNaN", "(Ljava/lang/String;)V");
/*     */         
/* 174 */         il.append((Instruction)InstructionConstants.DUP);
/* 175 */         il.append((CompoundInstruction)new PUSH(cpg, value));
/* 176 */         il.append((Instruction)new INVOKEVIRTUAL(method));
/* 177 */         valid = false;
/*     */       }
/* 179 */       else if (name.equals("infinity")) {
/* 180 */         method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setInfinity", "(Ljava/lang/String;)V");
/*     */ 
/*     */         
/* 183 */         il.append((Instruction)InstructionConstants.DUP);
/* 184 */         il.append((CompoundInstruction)new PUSH(cpg, value));
/* 185 */         il.append((Instruction)new INVOKEVIRTUAL(method));
/* 186 */         valid = false;
/*     */       } else {
/*     */         
/* 189 */         valid = false;
/*     */       } 
/*     */       
/* 192 */       if (valid) {
/* 193 */         il.append((Instruction)InstructionConstants.DUP);
/* 194 */         il.append((CompoundInstruction)new PUSH(cpg, value.charAt(0)));
/* 195 */         il.append((Instruction)new INVOKEVIRTUAL(method));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 200 */     int put = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addDecimalFormat", "(Ljava/lang/String;Ljava/text/DecimalFormatSymbols;)V");
/*     */ 
/*     */     
/* 203 */     il.append((Instruction)new INVOKEVIRTUAL(put));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void translateDefaultDFS(ClassGenerator classGen, MethodGenerator methodGen) {
/* 215 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 216 */     InstructionList il = methodGen.getInstructionList();
/* 217 */     int init = cpg.addMethodref("java.text.DecimalFormatSymbols", "<init>", "(Ljava/util/Locale;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     il.append(classGen.loadTranslet());
/* 223 */     il.append((CompoundInstruction)new PUSH(cpg, ""));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     il.append((Instruction)new NEW(cpg.addClass("java.text.DecimalFormatSymbols")));
/* 231 */     il.append((Instruction)InstructionConstants.DUP);
/* 232 */     il.append((Instruction)new GETSTATIC(cpg.addFieldref("java.util.Locale", "US", "Ljava/util/Locale;")));
/*     */     
/* 234 */     il.append((Instruction)new INVOKESPECIAL(init));
/*     */     
/* 236 */     int nan = cpg.addMethodref("java.text.DecimalFormatSymbols", "setNaN", "(Ljava/lang/String;)V");
/*     */     
/* 238 */     il.append((Instruction)InstructionConstants.DUP);
/* 239 */     il.append((CompoundInstruction)new PUSH(cpg, "NaN"));
/* 240 */     il.append((Instruction)new INVOKEVIRTUAL(nan));
/*     */     
/* 242 */     int inf = cpg.addMethodref("java.text.DecimalFormatSymbols", "setInfinity", "(Ljava/lang/String;)V");
/*     */ 
/*     */     
/* 245 */     il.append((Instruction)InstructionConstants.DUP);
/* 246 */     il.append((CompoundInstruction)new PUSH(cpg, "Infinity"));
/* 247 */     il.append((Instruction)new INVOKEVIRTUAL(inf));
/*     */     
/* 249 */     int put = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addDecimalFormat", "(Ljava/lang/String;Ljava/text/DecimalFormatSymbols;)V");
/*     */ 
/*     */     
/* 252 */     il.append((Instruction)new INVOKEVIRTUAL(put));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/DecimalFormatting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */