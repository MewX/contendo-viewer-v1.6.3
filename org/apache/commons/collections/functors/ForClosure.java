/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ForClosure
/*     */   implements Serializable, Closure
/*     */ {
/*     */   static final long serialVersionUID = -1190120533393621674L;
/*     */   private final int iCount;
/*     */   private final Closure iClosure;
/*     */   
/*     */   public static Closure getInstance(int count, Closure closure) {
/*  51 */     if (count <= 0 || closure == null) {
/*  52 */       return NOPClosure.INSTANCE;
/*     */     }
/*  54 */     if (count == 1) {
/*  55 */       return closure;
/*     */     }
/*  57 */     return new ForClosure(count, closure);
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
/*     */   public ForClosure(int count, Closure closure) {
/*  69 */     this.iCount = count;
/*  70 */     this.iClosure = closure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(Object input) {
/*  79 */     for (int i = 0; i < this.iCount; i++) {
/*  80 */       this.iClosure.execute(input);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure getClosure() {
/*  91 */     return this.iClosure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 101 */     return this.iCount;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/ForClosure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */