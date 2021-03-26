/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETSTATIC;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
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
/*     */ final class Text
/*     */   extends Instruction
/*     */ {
/*     */   private String _text;
/*     */   private boolean _escaping = true;
/*     */   private boolean _ignore = false;
/*     */   private boolean _textElement = false;
/*     */   
/*     */   public Text() {
/*  47 */     this._textElement = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Text(String text) {
/*  55 */     this._text = text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getText() {
/*  63 */     return this._text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setText(String text) {
/*  72 */     if (this._text == null) {
/*  73 */       this._text = text;
/*     */     } else {
/*  75 */       this._text += text;
/*     */     } 
/*     */   }
/*     */   public void display(int indent) {
/*  79 */     indent(indent);
/*  80 */     Util.println("Text");
/*  81 */     indent(indent + 4);
/*  82 */     Util.println(this._text);
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  86 */     String str = getAttribute("disable-output-escaping");
/*  87 */     if (str != null && str.equals("yes")) this._escaping = false;
/*     */     
/*  89 */     parseChildren(parser);
/*     */     
/*  91 */     if (this._text == null)
/*  92 */     { if (this._textElement) {
/*  93 */         this._text = "";
/*     */       } else {
/*     */         
/*  96 */         this._ignore = true;
/*     */       }
/*     */        }
/*  99 */     else if (this._textElement)
/* 100 */     { if (this._text.length() == 0) this._ignore = true;
/*     */        }
/* 102 */     else if (getParent() instanceof LiteralElement)
/* 103 */     { LiteralElement element = (LiteralElement)getParent();
/* 104 */       String space = element.getAttribute("xml:space");
/* 105 */       if ((space == null || !space.equals("preserve")) && 
/* 106 */         this._text.trim().length() == 0) this._ignore = true;
/*     */        }
/*     */     
/* 109 */     else if (this._text.trim().length() == 0) { this._ignore = true; }
/*     */   
/*     */   }
/*     */   
/*     */   public void ignore() {
/* 114 */     this._ignore = true;
/*     */   }
/*     */   
/*     */   public boolean isIgnore() {
/* 118 */     return this._ignore;
/*     */   }
/*     */   
/*     */   public boolean isTextElement() {
/* 122 */     return this._textElement;
/*     */   }
/*     */   
/*     */   protected boolean contextDependent() {
/* 126 */     return false;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 130 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 131 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 133 */     if (!this._ignore) {
/*     */       
/* 135 */       int esc = cpg.addInterfaceMethodref("org/apache/xml/serializer/SerializationHandler", "setEscaping", "(Z)Z");
/*     */       
/* 137 */       if (!this._escaping) {
/* 138 */         il.append(methodGen.loadHandler());
/* 139 */         il.append((CompoundInstruction)new PUSH(cpg, false));
/* 140 */         il.append((Instruction)new INVOKEINTERFACE(esc, 2));
/*     */       } 
/*     */       
/* 143 */       il.append(methodGen.loadHandler());
/*     */ 
/*     */ 
/*     */       
/* 147 */       if (!canLoadAsArrayOffsetLength()) {
/* 148 */         int characters = cpg.addInterfaceMethodref("org/apache/xml/serializer/SerializationHandler", "characters", "(Ljava/lang/String;)V");
/*     */ 
/*     */         
/* 151 */         il.append((CompoundInstruction)new PUSH(cpg, this._text));
/* 152 */         il.append((Instruction)new INVOKEINTERFACE(characters, 2));
/*     */       } else {
/* 154 */         int characters = cpg.addInterfaceMethodref("org/apache/xml/serializer/SerializationHandler", "characters", "([CII)V");
/*     */ 
/*     */         
/* 157 */         loadAsArrayOffsetLength(classGen, methodGen);
/* 158 */         il.append((Instruction)new INVOKEINTERFACE(characters, 4));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 163 */       if (!this._escaping) {
/* 164 */         il.append(methodGen.loadHandler());
/* 165 */         il.append((Instruction)InstructionConstants.SWAP);
/* 166 */         il.append((Instruction)new INVOKEINTERFACE(esc, 2));
/* 167 */         il.append((Instruction)InstructionConstants.POP);
/*     */       } 
/*     */     } 
/* 170 */     translateContents(classGen, methodGen);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canLoadAsArrayOffsetLength() {
/* 188 */     return (this._text.length() <= 21845);
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
/*     */ 
/*     */   
/*     */   public void loadAsArrayOffsetLength(ClassGenerator classGen, MethodGenerator methodGen) {
/* 202 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 203 */     InstructionList il = methodGen.getInstructionList();
/* 204 */     XSLTC xsltc = classGen.getParser().getXSLTC();
/*     */ 
/*     */ 
/*     */     
/* 208 */     int offset = xsltc.addCharacterData(this._text);
/* 209 */     int length = this._text.length();
/* 210 */     String charDataFieldName = "_scharData" + (xsltc.getCharacterDataCount() - 1);
/*     */ 
/*     */     
/* 213 */     il.append((Instruction)new GETSTATIC(cpg.addFieldref(xsltc.getClassName(), charDataFieldName, "[C")));
/*     */ 
/*     */     
/* 216 */     il.append((CompoundInstruction)new PUSH(cpg, offset));
/* 217 */     il.append((CompoundInstruction)new PUSH(cpg, this._text.length()));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Text.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */