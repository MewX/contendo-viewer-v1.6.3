/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FunctorException
/*     */   extends RuntimeException
/*     */ {
/*     */   private static final boolean JDK_SUPPORTS_NESTED;
/*     */   private final Throwable rootCause;
/*     */   
/*     */   static {
/*  38 */     boolean flag = false;
/*     */     try {
/*  40 */       Throwable.class.getDeclaredMethod("getCause", new Class[0]);
/*  41 */       flag = true;
/*  42 */     } catch (NoSuchMethodException ex) {
/*  43 */       flag = false;
/*     */     } 
/*  45 */     JDK_SUPPORTS_NESTED = flag;
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
/*     */   public FunctorException() {
/*  59 */     this.rootCause = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FunctorException(String msg) {
/*  69 */     super(msg);
/*  70 */     this.rootCause = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FunctorException(Throwable rootCause) {
/*  81 */     super((rootCause == null) ? null : rootCause.getMessage());
/*  82 */     this.rootCause = rootCause;
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
/*     */   public FunctorException(String msg, Throwable rootCause) {
/*  94 */     super(msg);
/*  95 */     this.rootCause = rootCause;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Throwable getCause() {
/* 104 */     return this.rootCause;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace() {
/* 111 */     printStackTrace(System.err);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace(PrintStream out) {
/* 120 */     synchronized (out) {
/* 121 */       PrintWriter pw = new PrintWriter(out, false);
/* 122 */       printStackTrace(pw);
/*     */       
/* 124 */       pw.flush();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace(PrintWriter out) {
/* 134 */     synchronized (out) {
/* 135 */       super.printStackTrace(out);
/* 136 */       if (this.rootCause != null && !JDK_SUPPORTS_NESTED) {
/* 137 */         out.print("Caused by: ");
/* 138 */         this.rootCause.printStackTrace(out);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/FunctorException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */