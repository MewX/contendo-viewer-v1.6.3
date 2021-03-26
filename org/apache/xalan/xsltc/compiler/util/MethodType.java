/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.Type;
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
/*     */ public final class MethodType
/*     */   extends Type
/*     */ {
/*     */   private final Type _resultType;
/*     */   private final Vector _argsType;
/*     */   
/*     */   public MethodType(Type resultType) {
/*  33 */     this._argsType = null;
/*  34 */     this._resultType = resultType;
/*     */   }
/*     */   
/*     */   public MethodType(Type resultType, Type arg1) {
/*  38 */     if (arg1 != Type.Void) {
/*  39 */       this._argsType = new Vector();
/*  40 */       this._argsType.addElement(arg1);
/*     */     } else {
/*     */       
/*  43 */       this._argsType = null;
/*     */     } 
/*  45 */     this._resultType = resultType;
/*     */   }
/*     */   
/*     */   public MethodType(Type resultType, Type arg1, Type arg2) {
/*  49 */     this._argsType = new Vector(2);
/*  50 */     this._argsType.addElement(arg1);
/*  51 */     this._argsType.addElement(arg2);
/*  52 */     this._resultType = resultType;
/*     */   }
/*     */   
/*     */   public MethodType(Type resultType, Type arg1, Type arg2, Type arg3) {
/*  56 */     this._argsType = new Vector(3);
/*  57 */     this._argsType.addElement(arg1);
/*  58 */     this._argsType.addElement(arg2);
/*  59 */     this._argsType.addElement(arg3);
/*  60 */     this._resultType = resultType;
/*     */   }
/*     */   
/*     */   public MethodType(Type resultType, Vector argsType) {
/*  64 */     this._resultType = resultType;
/*  65 */     this._argsType = (argsType.size() > 0) ? argsType : null;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  69 */     StringBuffer result = new StringBuffer("method{");
/*  70 */     if (this._argsType != null) {
/*  71 */       int count = this._argsType.size();
/*  72 */       for (int i = 0; i < count; i++) {
/*  73 */         result.append(this._argsType.elementAt(i));
/*  74 */         if (i != count - 1) result.append(',');
/*     */       
/*     */       } 
/*     */     } else {
/*  78 */       result.append("void");
/*     */     } 
/*  80 */     result.append('}');
/*  81 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  85 */     return toSignature("");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toSignature(String lastArgSig) {
/*  93 */     StringBuffer buffer = new StringBuffer();
/*  94 */     buffer.append('(');
/*  95 */     if (this._argsType != null) {
/*  96 */       int n = this._argsType.size();
/*  97 */       for (int i = 0; i < n; i++) {
/*  98 */         buffer.append(((Type)this._argsType.elementAt(i)).toSignature());
/*     */       }
/*     */     } 
/* 101 */     return buffer.append(lastArgSig).append(')').append(this._resultType.toSignature()).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type toJCType() {
/* 109 */     return null;
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/* 113 */     boolean result = false;
/* 114 */     if (other instanceof MethodType) {
/* 115 */       MethodType temp = (MethodType)other;
/* 116 */       if (this._resultType.identicalTo(temp._resultType)) {
/* 117 */         int len = argsCount();
/* 118 */         result = (len == temp.argsCount());
/* 119 */         for (int i = 0; i < len && result; i++) {
/* 120 */           Type arg1 = this._argsType.elementAt(i);
/* 121 */           Type arg2 = temp._argsType.elementAt(i);
/* 122 */           result = arg1.identicalTo(arg2);
/*     */         } 
/*     */       } 
/*     */     } 
/* 126 */     return result;
/*     */   }
/*     */   
/*     */   public int distanceTo(Type other) {
/* 130 */     int result = Integer.MAX_VALUE;
/* 131 */     if (other instanceof MethodType) {
/* 132 */       MethodType mtype = (MethodType)other;
/* 133 */       if (this._argsType != null) {
/* 134 */         int len = this._argsType.size();
/* 135 */         if (len == mtype._argsType.size()) {
/* 136 */           result = 0;
/* 137 */           for (int i = 0; i < len; i++) {
/* 138 */             Type arg1 = this._argsType.elementAt(i);
/* 139 */             Type arg2 = mtype._argsType.elementAt(i);
/* 140 */             int temp = arg1.distanceTo(arg2);
/* 141 */             if (temp == Integer.MAX_VALUE) {
/* 142 */               result = temp;
/*     */               
/*     */               break;
/*     */             } 
/* 146 */             result += arg1.distanceTo(arg2);
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 151 */       } else if (mtype._argsType == null) {
/* 152 */         result = 0;
/*     */       } 
/*     */     } 
/* 155 */     return result;
/*     */   }
/*     */   
/*     */   public Type resultType() {
/* 159 */     return this._resultType;
/*     */   }
/*     */   
/*     */   public Vector argsType() {
/* 163 */     return this._argsType;
/*     */   }
/*     */   
/*     */   public int argsCount() {
/* 167 */     return (this._argsType == null) ? 0 : this._argsType.size();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/MethodType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */