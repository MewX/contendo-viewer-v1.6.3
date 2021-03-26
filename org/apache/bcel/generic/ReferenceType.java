/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.Repository;
/*     */ import org.apache.bcel.classfile.JavaClass;
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
/*     */ public class ReferenceType
/*     */   extends Type
/*     */ {
/*     */   protected ReferenceType(byte t, String s) {
/*  68 */     super(t, s);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   ReferenceType() {
/*  74 */     super((byte)14, "<null object>");
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
/*     */   public boolean isCastableTo(Type t) {
/*  86 */     if (equals(Type.NULL)) {
/*  87 */       return true;
/*     */     }
/*  89 */     return isAssignmentCompatibleWith(t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAssignmentCompatibleWith(Type t) {
/* 100 */     if (!(t instanceof ReferenceType)) {
/* 101 */       return false;
/*     */     }
/* 103 */     ReferenceType T = (ReferenceType)t;
/*     */     
/* 105 */     if (equals(Type.NULL)) {
/* 106 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 110 */     if (this instanceof ObjectType && ((ObjectType)this).referencesClass()) {
/*     */ 
/*     */ 
/*     */       
/* 114 */       if (T instanceof ObjectType && ((ObjectType)T).referencesClass()) {
/* 115 */         if (equals(T)) {
/* 116 */           return true;
/*     */         }
/* 118 */         if (Repository.instanceOf(((ObjectType)this).getClassName(), ((ObjectType)T).getClassName()))
/*     */         {
/* 120 */           return true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 125 */       if (T instanceof ObjectType && ((ObjectType)T).referencesInterface() && 
/* 126 */         Repository.implementationOf(((ObjectType)this).getClassName(), ((ObjectType)T).getClassName()))
/*     */       {
/* 128 */         return true;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 134 */     if (this instanceof ObjectType && ((ObjectType)this).referencesInterface()) {
/*     */ 
/*     */       
/* 137 */       if (T instanceof ObjectType && ((ObjectType)T).referencesClass() && 
/* 138 */         T.equals(Type.OBJECT)) return true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 144 */       if (T instanceof ObjectType && ((ObjectType)T).referencesInterface()) {
/* 145 */         if (equals(T)) return true; 
/* 146 */         if (Repository.implementationOf(((ObjectType)this).getClassName(), ((ObjectType)T).getClassName())) {
/* 147 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 154 */     if (this instanceof ArrayType) {
/*     */ 
/*     */       
/* 157 */       if (T instanceof ObjectType && ((ObjectType)T).referencesClass() && 
/* 158 */         T.equals(Type.OBJECT)) return true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       if (T instanceof ArrayType) {
/*     */ 
/*     */         
/* 167 */         Type sc = ((ArrayType)this).getElementType();
/* 168 */         Type tc = ((ArrayType)this).getElementType();
/*     */         
/* 170 */         if (sc instanceof BasicType && tc instanceof BasicType && sc.equals(tc)) {
/* 171 */           return true;
/*     */         }
/*     */ 
/*     */         
/* 175 */         if (tc instanceof ReferenceType && sc instanceof ReferenceType && ((ReferenceType)sc).isAssignmentCompatibleWith(tc)) {
/* 176 */           return true;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       if (T instanceof ObjectType && ((ObjectType)T).referencesInterface())
/* 186 */         for (int ii = 0; ii < Constants.INTERFACES_IMPLEMENTED_BY_ARRAYS.length; ii++) {
/* 187 */           if (T.equals(new ObjectType(Constants.INTERFACES_IMPLEMENTED_BY_ARRAYS[ii]))) return true;
/*     */         
/*     */         }  
/*     */     } 
/* 191 */     return false;
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
/*     */   public ReferenceType firstCommonSuperclass(ReferenceType t) {
/* 207 */     if (equals(Type.NULL)) return t;
/*     */     
/* 209 */     if (t.equals(Type.NULL)) return this;
/*     */     
/* 211 */     if (equals(t)) return this;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     if (this instanceof ArrayType || t instanceof ArrayType) {
/* 220 */       return Type.OBJECT;
/*     */     }
/*     */     
/* 223 */     if ((this instanceof ObjectType && ((ObjectType)this).referencesInterface()) || (t instanceof ObjectType && ((ObjectType)t).referencesInterface()))
/*     */     {
/* 225 */       return Type.OBJECT;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     ObjectType thiz = (ObjectType)this;
/* 233 */     ObjectType other = (ObjectType)t;
/* 234 */     JavaClass[] thiz_sups = Repository.getSuperClasses(thiz.getClassName());
/* 235 */     JavaClass[] other_sups = Repository.getSuperClasses(other.getClassName());
/*     */     
/* 237 */     if (thiz_sups == null || other_sups == null) {
/* 238 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 242 */     JavaClass[] this_sups = new JavaClass[thiz_sups.length + 1];
/* 243 */     JavaClass[] t_sups = new JavaClass[other_sups.length + 1];
/* 244 */     System.arraycopy(thiz_sups, 0, this_sups, 1, thiz_sups.length);
/* 245 */     System.arraycopy(other_sups, 0, t_sups, 1, other_sups.length);
/* 246 */     this_sups[0] = Repository.lookupClass(thiz.getClassName());
/* 247 */     t_sups[0] = Repository.lookupClass(other.getClassName());
/*     */     
/* 249 */     for (int i = 0; i < t_sups.length; i++) {
/* 250 */       for (int j = 0; j < this_sups.length; j++) {
/* 251 */         if (this_sups[j].equals(t_sups[i])) return new ObjectType(this_sups[j].getClassName());
/*     */       
/*     */       } 
/*     */     } 
/*     */     
/* 256 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ReferenceType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */