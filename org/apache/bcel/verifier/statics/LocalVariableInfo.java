/*     */ package org.apache.bcel.verifier.statics;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.bcel.verifier.exc.LocalVariableInfoInconsistentException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalVariableInfo
/*     */ {
/*  74 */   private Hashtable types = new Hashtable();
/*     */   
/*  76 */   private Hashtable names = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setName(int offset, String name) {
/*  83 */     this.names.put(Integer.toString(offset), name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setType(int offset, Type t) {
/*  90 */     this.types.put(Integer.toString(offset), t);
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
/*     */   public Type getType(int offset) {
/* 102 */     return (Type)this.types.get(Integer.toString(offset));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(int offset) {
/* 113 */     return (String)this.names.get(Integer.toString(offset));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, int startpc, int length, Type t) throws LocalVariableInfoInconsistentException {
/* 121 */     for (int i = startpc; i <= startpc + length; i++) {
/* 122 */       add(i, name, t);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void add(int offset, String name, Type t) throws LocalVariableInfoInconsistentException {
/* 132 */     if (getName(offset) != null && 
/* 133 */       !getName(offset).equals(name)) {
/* 134 */       throw new LocalVariableInfoInconsistentException("At bytecode offset '" + offset + "' a local variable has two different names: '" + getName(offset) + "' and '" + name + "'.");
/*     */     }
/*     */     
/* 137 */     if (getType(offset) != null && 
/* 138 */       !getType(offset).equals(t)) {
/* 139 */       throw new LocalVariableInfoInconsistentException("At bytecode offset '" + offset + "' a local variable has two different types: '" + getType(offset) + "' and '" + t + "'.");
/*     */     }
/*     */     
/* 142 */     setName(offset, name);
/* 143 */     setType(offset, t);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/statics/LocalVariableInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */