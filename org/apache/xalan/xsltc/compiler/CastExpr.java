/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.IF_ICMPNE;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.SIPUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MultiHashtable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ final class CastExpr
/*     */   extends Expression
/*     */ {
/*     */   private final Expression _left;
/*  50 */   private static MultiHashtable InternalTypeMap = new MultiHashtable();
/*     */ 
/*     */   
/*     */   static {
/*  54 */     InternalTypeMap.put(Type.Boolean, Type.Boolean);
/*  55 */     InternalTypeMap.put(Type.Boolean, Type.Real);
/*  56 */     InternalTypeMap.put(Type.Boolean, Type.String);
/*  57 */     InternalTypeMap.put(Type.Boolean, Type.Reference);
/*  58 */     InternalTypeMap.put(Type.Boolean, Type.Object);
/*     */     
/*  60 */     InternalTypeMap.put(Type.Real, Type.Real);
/*  61 */     InternalTypeMap.put(Type.Real, Type.Int);
/*  62 */     InternalTypeMap.put(Type.Real, Type.Boolean);
/*  63 */     InternalTypeMap.put(Type.Real, Type.String);
/*  64 */     InternalTypeMap.put(Type.Real, Type.Reference);
/*  65 */     InternalTypeMap.put(Type.Real, Type.Object);
/*     */     
/*  67 */     InternalTypeMap.put(Type.Int, Type.Int);
/*  68 */     InternalTypeMap.put(Type.Int, Type.Real);
/*  69 */     InternalTypeMap.put(Type.Int, Type.Boolean);
/*  70 */     InternalTypeMap.put(Type.Int, Type.String);
/*  71 */     InternalTypeMap.put(Type.Int, Type.Reference);
/*  72 */     InternalTypeMap.put(Type.Int, Type.Object);
/*     */     
/*  74 */     InternalTypeMap.put(Type.String, Type.String);
/*  75 */     InternalTypeMap.put(Type.String, Type.Boolean);
/*  76 */     InternalTypeMap.put(Type.String, Type.Real);
/*  77 */     InternalTypeMap.put(Type.String, Type.Reference);
/*  78 */     InternalTypeMap.put(Type.String, Type.Object);
/*  79 */     InternalTypeMap.put(Type.String, Type.ObjectString);
/*     */     
/*  81 */     InternalTypeMap.put(Type.NodeSet, Type.NodeSet);
/*  82 */     InternalTypeMap.put(Type.NodeSet, Type.Boolean);
/*  83 */     InternalTypeMap.put(Type.NodeSet, Type.Real);
/*  84 */     InternalTypeMap.put(Type.NodeSet, Type.String);
/*  85 */     InternalTypeMap.put(Type.NodeSet, Type.Node);
/*  86 */     InternalTypeMap.put(Type.NodeSet, Type.Reference);
/*  87 */     InternalTypeMap.put(Type.NodeSet, Type.Object);
/*     */     
/*  89 */     InternalTypeMap.put(Type.Node, Type.Node);
/*  90 */     InternalTypeMap.put(Type.Node, Type.Boolean);
/*  91 */     InternalTypeMap.put(Type.Node, Type.Real);
/*  92 */     InternalTypeMap.put(Type.Node, Type.String);
/*  93 */     InternalTypeMap.put(Type.Node, Type.NodeSet);
/*  94 */     InternalTypeMap.put(Type.Node, Type.Reference);
/*  95 */     InternalTypeMap.put(Type.Node, Type.Object);
/*     */     
/*  97 */     InternalTypeMap.put(Type.ResultTree, Type.ResultTree);
/*  98 */     InternalTypeMap.put(Type.ResultTree, Type.Boolean);
/*  99 */     InternalTypeMap.put(Type.ResultTree, Type.Real);
/* 100 */     InternalTypeMap.put(Type.ResultTree, Type.String);
/* 101 */     InternalTypeMap.put(Type.ResultTree, Type.NodeSet);
/* 102 */     InternalTypeMap.put(Type.ResultTree, Type.Reference);
/* 103 */     InternalTypeMap.put(Type.ResultTree, Type.Object);
/*     */     
/* 105 */     InternalTypeMap.put(Type.Reference, Type.Reference);
/* 106 */     InternalTypeMap.put(Type.Reference, Type.Boolean);
/* 107 */     InternalTypeMap.put(Type.Reference, Type.Int);
/* 108 */     InternalTypeMap.put(Type.Reference, Type.Real);
/* 109 */     InternalTypeMap.put(Type.Reference, Type.String);
/* 110 */     InternalTypeMap.put(Type.Reference, Type.Node);
/* 111 */     InternalTypeMap.put(Type.Reference, Type.NodeSet);
/* 112 */     InternalTypeMap.put(Type.Reference, Type.ResultTree);
/* 113 */     InternalTypeMap.put(Type.Reference, Type.Object);
/*     */     
/* 115 */     InternalTypeMap.put(Type.Object, Type.String);
/* 116 */     InternalTypeMap.put(Type.ObjectString, Type.String);
/*     */     
/* 118 */     InternalTypeMap.put(Type.Void, Type.String);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _typeTest = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public CastExpr(Expression left, Type type) throws TypeCheckError {
/* 128 */     this._left = left;
/* 129 */     this._type = type;
/*     */     
/* 131 */     if (this._left instanceof Step && this._type == Type.Boolean) {
/* 132 */       Step step = (Step)this._left;
/* 133 */       if (step.getAxis() == 13 && step.getNodeType() != -1) {
/* 134 */         this._typeTest = true;
/*     */       }
/*     */     } 
/*     */     
/* 138 */     setParser(left.getParser());
/* 139 */     setParent(left.getParent());
/* 140 */     left.setParent(this);
/* 141 */     typeCheck(left.getParser().getSymbolTable());
/*     */   }
/*     */   
/*     */   public Expression getExpr() {
/* 145 */     return this._left;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPositionCall() {
/* 153 */     return this._left.hasPositionCall();
/*     */   }
/*     */   
/*     */   public boolean hasLastCall() {
/* 157 */     return this._left.hasLastCall();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 161 */     return "cast(" + this._left + ", " + this._type + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 171 */     Type tleft = this._left.getType();
/* 172 */     if (tleft == null) {
/* 173 */       tleft = this._left.typeCheck(stable);
/*     */     }
/* 175 */     if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/* 176 */       tleft = Type.Node;
/*     */     }
/* 178 */     else if (tleft instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/* 179 */       tleft = Type.ResultTree;
/*     */     } 
/* 181 */     if (InternalTypeMap.maps(tleft, this._type) != null) {
/* 182 */       return this._type;
/*     */     }
/*     */     
/* 185 */     throw new TypeCheckError(new ErrorMsg("DATA_CONVERSION_ERR", tleft.toString(), this._type.toString()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/* 192 */     Type ltype = this._left.getType();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     if (this._typeTest) {
/* 198 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 199 */       InstructionList il = methodGen.getInstructionList();
/*     */       
/* 201 */       int idx = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
/*     */ 
/*     */       
/* 204 */       il.append((Instruction)new SIPUSH((short)((Step)this._left).getNodeType()));
/* 205 */       il.append(methodGen.loadDOM());
/* 206 */       il.append(methodGen.loadContextNode());
/* 207 */       il.append((Instruction)new INVOKEINTERFACE(idx, 2));
/* 208 */       this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IF_ICMPNE(null)));
/*     */     }
/*     */     else {
/*     */       
/* 212 */       this._left.translate(classGen, methodGen);
/* 213 */       if (this._type != ltype) {
/* 214 */         this._left.startIterator(classGen, methodGen);
/* 215 */         if (this._type instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 216 */           FlowList fl = ltype.translateToDesynthesized(classGen, methodGen, this._type);
/*     */           
/* 218 */           if (fl != null) {
/* 219 */             this._falseList.append(fl);
/*     */           }
/*     */         } else {
/*     */           
/* 223 */           ltype.translateTo(classGen, methodGen, this._type);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 230 */     Type ltype = this._left.getType();
/* 231 */     this._left.translate(classGen, methodGen);
/* 232 */     if (!this._type.identicalTo(ltype)) {
/* 233 */       this._left.startIterator(classGen, methodGen);
/* 234 */       ltype.translateTo(classGen, methodGen, this._type);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/CastExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */