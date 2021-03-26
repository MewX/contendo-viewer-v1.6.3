/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChainedTransformer
/*     */   implements Serializable, Transformer
/*     */ {
/*     */   static final long serialVersionUID = 3514945074733160196L;
/*     */   private final Transformer[] iTransformers;
/*     */   
/*     */   public static Transformer getInstance(Transformer[] transformers) {
/*  52 */     FunctorUtils.validate(transformers);
/*  53 */     if (transformers.length == 0) {
/*  54 */       return NOPTransformer.INSTANCE;
/*     */     }
/*  56 */     transformers = FunctorUtils.copy(transformers);
/*  57 */     return new ChainedTransformer(transformers);
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
/*     */   public static Transformer getInstance(Collection transformers) {
/*  71 */     if (transformers == null) {
/*  72 */       throw new IllegalArgumentException("Transformer collection must not be null");
/*     */     }
/*  74 */     if (transformers.size() == 0) {
/*  75 */       return NOPTransformer.INSTANCE;
/*     */     }
/*     */     
/*  78 */     Transformer[] cmds = new Transformer[transformers.size()];
/*  79 */     int i = 0;
/*  80 */     for (Iterator it = transformers.iterator(); it.hasNext();) {
/*  81 */       cmds[i++] = it.next();
/*     */     }
/*  83 */     FunctorUtils.validate(cmds);
/*  84 */     return new ChainedTransformer(cmds);
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
/*     */   public static Transformer getInstance(Transformer transformer1, Transformer transformer2) {
/*  96 */     if (transformer1 == null || transformer2 == null) {
/*  97 */       throw new IllegalArgumentException("Transformers must not be null");
/*     */     }
/*  99 */     Transformer[] transformers = { transformer1, transformer2 };
/* 100 */     return new ChainedTransformer(transformers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChainedTransformer(Transformer[] transformers) {
/* 111 */     this.iTransformers = transformers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object transform(Object object) {
/* 121 */     for (int i = 0; i < this.iTransformers.length; i++) {
/* 122 */       object = this.iTransformers[i].transform(object);
/*     */     }
/* 124 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transformer[] getTransformers() {
/* 133 */     return this.iTransformers;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/ChainedTransformer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */