/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Closure;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChainedClosure
/*     */   implements Serializable, Closure
/*     */ {
/*     */   static final long serialVersionUID = -3520677225766901240L;
/*     */   private final Closure[] iClosures;
/*     */   
/*     */   public static Closure getInstance(Closure[] closures) {
/*  49 */     FunctorUtils.validate(closures);
/*  50 */     if (closures.length == 0) {
/*  51 */       return NOPClosure.INSTANCE;
/*     */     }
/*  53 */     closures = FunctorUtils.copy(closures);
/*  54 */     return new ChainedClosure(closures);
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
/*     */   public static Closure getInstance(Collection closures) {
/*  68 */     if (closures == null) {
/*  69 */       throw new IllegalArgumentException("Closure collection must not be null");
/*     */     }
/*  71 */     if (closures.size() == 0) {
/*  72 */       return NOPClosure.INSTANCE;
/*     */     }
/*     */     
/*  75 */     Closure[] cmds = new Closure[closures.size()];
/*  76 */     int i = 0;
/*  77 */     for (Iterator it = closures.iterator(); it.hasNext();) {
/*  78 */       cmds[i++] = it.next();
/*     */     }
/*  80 */     FunctorUtils.validate(cmds);
/*  81 */     return new ChainedClosure(cmds);
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
/*     */   public static Closure getInstance(Closure closure1, Closure closure2) {
/*  93 */     if (closure1 == null || closure2 == null) {
/*  94 */       throw new IllegalArgumentException("Closures must not be null");
/*     */     }
/*  96 */     Closure[] closures = { closure1, closure2 };
/*  97 */     return new ChainedClosure(closures);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChainedClosure(Closure[] closures) {
/* 108 */     this.iClosures = closures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(Object input) {
/* 117 */     for (int i = 0; i < this.iClosures.length; i++) {
/* 118 */       this.iClosures[i].execute(input);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure[] getClosures() {
/* 128 */     return this.iClosures;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/ChainedClosure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */