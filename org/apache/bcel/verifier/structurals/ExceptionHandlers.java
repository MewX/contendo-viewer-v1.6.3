/*     */ package org.apache.bcel.verifier.structurals;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import org.apache.bcel.generic.CodeExceptionGen;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.MethodGen;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExceptionHandlers
/*     */ {
/*  78 */   private Hashtable exceptionhandlers = new Hashtable(); public ExceptionHandlers(MethodGen mg) {
/*  79 */     CodeExceptionGen[] cegs = mg.getExceptionHandlers();
/*  80 */     for (int i = 0; i < cegs.length; i++) {
/*  81 */       ExceptionHandler eh = new ExceptionHandler(cegs[i].getCatchType(), cegs[i].getHandlerPC());
/*  82 */       for (InstructionHandle ih = cegs[i].getStartPC(); ih != cegs[i].getEndPC().getNext(); ih = ih.getNext()) {
/*     */         
/*  84 */         HashSet hs = (HashSet)this.exceptionhandlers.get(ih);
/*  85 */         if (hs == null) {
/*  86 */           hs = new HashSet();
/*  87 */           this.exceptionhandlers.put(ih, hs);
/*     */         } 
/*  89 */         hs.add(eh);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExceptionHandler[] getExceptionHandlers(InstructionHandle ih) {
/*  99 */     HashSet hs = (HashSet)this.exceptionhandlers.get(ih);
/* 100 */     if (hs == null) return new ExceptionHandler[0];
/*     */     
/* 102 */     ExceptionHandler[] ret = new ExceptionHandler[hs.size()];
/* 103 */     return (ExceptionHandler[])hs.toArray((Object[])ret);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/ExceptionHandlers.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */