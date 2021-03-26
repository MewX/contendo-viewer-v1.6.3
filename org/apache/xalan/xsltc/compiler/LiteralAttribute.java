/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ import org.apache.xml.serializer.ElemDesc;
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
/*     */ final class LiteralAttribute
/*     */   extends Instruction
/*     */ {
/*     */   private final String _name;
/*     */   private final AttributeValue _value;
/*     */   
/*     */   public LiteralAttribute(String name, String value, Parser parser) {
/*  51 */     this._name = name;
/*  52 */     this._value = AttributeValue.create(this, value, parser);
/*     */   }
/*     */   
/*     */   public void display(int indent) {
/*  56 */     indent(indent);
/*  57 */     Util.println("LiteralAttribute name=" + this._name + " value=" + this._value);
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  61 */     this._value.typeCheck(stable);
/*  62 */     typeCheckContents(stable);
/*  63 */     return Type.Void;
/*     */   }
/*     */   
/*     */   protected boolean contextDependent() {
/*  67 */     return this._value.contextDependent();
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  71 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  72 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/*  75 */     il.append(methodGen.loadHandler());
/*     */     
/*  77 */     il.append((CompoundInstruction)new PUSH(cpg, this._name));
/*     */     
/*  79 */     this._value.translate(classGen, methodGen);
/*     */ 
/*     */ 
/*     */     
/*  83 */     SyntaxTreeNode parent = getParent();
/*  84 */     if (parent instanceof LiteralElement && ((LiteralElement)parent).allAttributesUnique()) {
/*     */ 
/*     */       
/*  87 */       int flags = 0;
/*  88 */       boolean isHTMLAttrEmpty = false;
/*  89 */       ElemDesc elemDesc = ((LiteralElement)parent).getElemDesc();
/*     */ 
/*     */       
/*  92 */       if (elemDesc != null) {
/*  93 */         if (elemDesc.isAttrFlagSet(this._name, 4)) {
/*  94 */           flags |= 0x2;
/*  95 */           isHTMLAttrEmpty = true;
/*     */         }
/*  97 */         else if (elemDesc.isAttrFlagSet(this._name, 2)) {
/*  98 */           flags |= 0x4;
/*     */         } 
/*     */       }
/*     */       
/* 102 */       if (this._value instanceof SimpleAttributeValue) {
/* 103 */         String attrValue = ((SimpleAttributeValue)this._value).toString();
/*     */         
/* 105 */         if (!hasBadChars(attrValue) && !isHTMLAttrEmpty) {
/* 106 */           flags |= 0x1;
/*     */         }
/*     */       } 
/*     */       
/* 110 */       il.append((CompoundInstruction)new PUSH(cpg, flags));
/* 111 */       il.append(methodGen.uniqueAttribute());
/*     */     }
/*     */     else {
/*     */       
/* 115 */       il.append(methodGen.attribute());
/*     */     } 
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
/*     */   private boolean hasBadChars(String value) {
/* 128 */     char[] chars = value.toCharArray();
/* 129 */     int size = chars.length;
/* 130 */     for (int i = 0; i < size; i++) {
/* 131 */       char ch = chars[i];
/* 132 */       if (ch < ' ' || '~' < ch || ch == '<' || ch == '>' || ch == '&' || ch == '"')
/* 133 */         return true; 
/*     */     } 
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 142 */     return this._name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeValue getValue() {
/* 149 */     return this._value;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/LiteralAttribute.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */