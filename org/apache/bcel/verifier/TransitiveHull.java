/*     */ package org.apache.bcel.verifier;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransitiveHull
/*     */   implements VerifierFactoryObserver
/*     */ {
/*  73 */   private int indent = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(String classname) {
/*  82 */     System.gc();
/*     */     
/*  84 */     for (int i = 0; i < this.indent; i++) {
/*  85 */       System.out.print(" ");
/*     */     }
/*  87 */     System.out.println(classname);
/*  88 */     this.indent++;
/*     */     
/*  90 */     Verifier v = VerifierFactory.getVerifier(classname);
/*     */ 
/*     */     
/*  93 */     VerificationResult vr = v.doPass1();
/*  94 */     if (vr != VerificationResult.VR_OK) {
/*  95 */       System.out.println("Pass 1:\n" + vr);
/*     */     }
/*  97 */     vr = v.doPass2();
/*  98 */     if (vr != VerificationResult.VR_OK) {
/*  99 */       System.out.println("Pass 2:\n" + vr);
/*     */     }
/* 101 */     if (vr == VerificationResult.VR_OK) {
/* 102 */       JavaClass jc = Repository.lookupClass(v.getClassName());
/* 103 */       for (int j = 0; j < (jc.getMethods()).length; j++) {
/* 104 */         vr = v.doPass3a(j);
/* 105 */         if (vr != VerificationResult.VR_OK) {
/* 106 */           System.out.println(v.getClassName() + ", Pass 3a, method " + j + " ['" + jc.getMethods()[j] + "']:\n" + vr);
/*     */         }
/* 108 */         vr = v.doPass3b(j);
/* 109 */         if (vr != VerificationResult.VR_OK) {
/* 110 */           System.out.println(v.getClassName() + ", Pass 3b, method " + j + " ['" + jc.getMethods()[j] + "']:\n" + vr);
/*     */         }
/*     */       } 
/*     */     } 
/* 114 */     this.indent--;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 124 */     if (args.length != 1) {
/* 125 */       System.out.println("Need exactly one argument: The root class to verify.");
/* 126 */       System.exit(1);
/*     */     } 
/*     */     
/* 129 */     int dotclasspos = args[0].lastIndexOf(".class");
/* 130 */     if (dotclasspos != -1) args[0] = args[0].substring(0, dotclasspos); 
/* 131 */     args[0] = args[0].replace('/', '.');
/*     */     
/* 133 */     TransitiveHull th = new TransitiveHull();
/* 134 */     VerifierFactory.attach(th);
/* 135 */     VerifierFactory.getVerifier(args[0]);
/* 136 */     VerifierFactory.detach(th);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/TransitiveHull.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */