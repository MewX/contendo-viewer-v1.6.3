/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
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
/*     */ 
/*     */ 
/*     */ final class AttributeValueTemplate
/*     */   extends AttributeValue
/*     */ {
/*     */   public AttributeValueTemplate(String value, Parser parser, SyntaxTreeNode parent) {
/*  46 */     setParent(parent);
/*  47 */     setParser(parser);
/*  48 */     if (check(value, parser)) {
/*  49 */       parseAVTemplate(0, value, parser);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseAVTemplate(int start, String text, Parser parser) {
/*  56 */     if (text == null) {
/*     */       return;
/*     */     }
/*  59 */     int open = start - 2;
/*     */     do {
/*  61 */       open = text.indexOf('{', open + 2);
/*     */     }
/*  63 */     while (open != -1 && open < text.length() - 1 && text.charAt(open + 1) == '{');
/*     */ 
/*     */     
/*  66 */     if (open != -1) {
/*     */       
/*  68 */       int close = open - 2;
/*     */       do {
/*  70 */         close = text.indexOf('}', close + 2);
/*     */       }
/*  72 */       while (close != -1 && close < text.length() - 1 && text.charAt(close + 1) == '}');
/*     */ 
/*     */ 
/*     */       
/*  76 */       if (open > start) {
/*  77 */         String str = removeDuplicateBraces(text.substring(start, open));
/*  78 */         addElement(new LiteralExpr(str));
/*     */       } 
/*     */       
/*  81 */       if (close > open + 1) {
/*  82 */         String str = text.substring(open + 1, close);
/*  83 */         str = removeDuplicateBraces(text.substring(open + 1, close));
/*  84 */         addElement(parser.parseExpression(this, str));
/*     */       } 
/*     */       
/*  87 */       parseAVTemplate(close + 1, text, parser);
/*     */     
/*     */     }
/*  90 */     else if (start < text.length()) {
/*     */       
/*  92 */       String str = removeDuplicateBraces(text.substring(start));
/*  93 */       addElement(new LiteralExpr(str));
/*     */     } 
/*     */   }
/*     */   
/*     */   public String removeDuplicateBraces(String orig) {
/*  98 */     String result = orig;
/*     */     
/*     */     int index;
/* 101 */     while ((index = result.indexOf("{{")) != -1) {
/* 102 */       result = result.substring(0, index) + result.substring(index + 1, result.length());
/*     */     }
/*     */ 
/*     */     
/* 106 */     while ((index = result.indexOf("}}")) != -1) {
/* 107 */       result = result.substring(0, index) + result.substring(index + 1, result.length());
/*     */     }
/*     */ 
/*     */     
/* 111 */     return result;
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 115 */     Vector contents = getContents();
/* 116 */     int n = contents.size();
/* 117 */     for (int i = 0; i < n; i++) {
/* 118 */       Expression exp = contents.elementAt(i);
/* 119 */       if (!exp.typeCheck(stable).identicalTo(Type.String)) {
/* 120 */         contents.setElementAt(new CastExpr(exp, Type.String), i);
/*     */       }
/*     */     } 
/* 123 */     return this._type = Type.String;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 127 */     StringBuffer buffer = new StringBuffer("AVT:[");
/* 128 */     int count = elementCount();
/* 129 */     for (int i = 0; i < count; i++) {
/* 130 */       buffer.append(elementAt(i).toString());
/* 131 */       if (i < count - 1)
/* 132 */         buffer.append(' '); 
/*     */     } 
/* 134 */     return buffer.append(']').toString();
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 138 */     if (elementCount() == 1) {
/* 139 */       Expression exp = (Expression)elementAt(0);
/* 140 */       exp.translate(classGen, methodGen);
/*     */     } else {
/*     */       
/* 143 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 144 */       InstructionList il = methodGen.getInstructionList();
/* 145 */       int initBuffer = cpg.addMethodref("java.lang.StringBuffer", "<init>", "()V");
/*     */       
/* 147 */       INVOKEVIRTUAL iNVOKEVIRTUAL = new INVOKEVIRTUAL(cpg.addMethodref("java.lang.StringBuffer", "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       int toString = cpg.addMethodref("java.lang.StringBuffer", "toString", "()Ljava/lang/String;");
/*     */ 
/*     */       
/* 156 */       il.append((Instruction)new NEW(cpg.addClass("java.lang.StringBuffer")));
/* 157 */       il.append((Instruction)InstructionConstants.DUP);
/* 158 */       il.append((Instruction)new INVOKESPECIAL(initBuffer));
/*     */       
/* 160 */       Enumeration elements = elements();
/* 161 */       while (elements.hasMoreElements()) {
/* 162 */         Expression exp = elements.nextElement();
/* 163 */         exp.translate(classGen, methodGen);
/* 164 */         il.append((Instruction)iNVOKEVIRTUAL);
/*     */       } 
/* 166 */       il.append((Instruction)new INVOKEVIRTUAL(toString));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean check(String value, Parser parser) {
/* 172 */     if (value == null) return true;
/*     */     
/* 174 */     char[] chars = value.toCharArray();
/* 175 */     int level = 0;
/* 176 */     for (int i = 0; i < chars.length; ) {
/* 177 */       switch (chars[i]) {
/*     */         case '{':
/* 179 */           if (i + 1 == chars.length || chars[i + 1] != '{') {
/* 180 */             level++; break;
/*     */           } 
/* 182 */           i++;
/*     */           break;
/*     */         case '}':
/* 185 */           if (i + 1 == chars.length || chars[i + 1] != '}') {
/* 186 */             level--; break;
/*     */           } 
/* 188 */           i++; break;
/*     */         default:
/*     */           i++;
/*     */           continue;
/*     */       } 
/* 193 */       switch (level) {
/*     */         case 0:
/*     */         case 1:
/*     */         
/*     */       } 
/* 198 */       reportError(getParent(), parser, "ATTR_VAL_TEMPLATE_ERR", value);
/*     */       
/* 200 */       return false;
/*     */     } 
/*     */     
/* 203 */     if (level != 0) {
/* 204 */       reportError(getParent(), parser, "ATTR_VAL_TEMPLATE_ERR", value);
/*     */       
/* 206 */       return false;
/*     */     } 
/* 208 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/AttributeValueTemplate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */