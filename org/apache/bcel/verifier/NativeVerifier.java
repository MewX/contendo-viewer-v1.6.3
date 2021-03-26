/*     */ package org.apache.bcel.verifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class NativeVerifier
/*     */ {
/*     */   public static void main(String[] args) {
/*  79 */     if (args.length != 1) {
/*  80 */       System.out.println("Verifier front-end: need exactly one argument.");
/*  81 */       System.exit(1);
/*     */     } 
/*     */     
/*  84 */     int dotclasspos = args[0].lastIndexOf(".class");
/*  85 */     if (dotclasspos != -1) args[0] = args[0].substring(0, dotclasspos); 
/*  86 */     args[0] = args[0].replace('/', '.');
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  91 */       Class.forName(args[0]);
/*     */     } catch (ExceptionInInitializerError eiie) {
/*     */       
/*  94 */       System.out.println("NativeVerifier: ExceptionInInitializerError encountered on '" + args[0] + "'.");
/*  95 */       System.out.println(eiie);
/*  96 */       System.exit(1);
/*     */     } catch (LinkageError le) {
/*     */       
/*  99 */       System.out.println("NativeVerifier: LinkageError encountered on '" + args[0] + "'.");
/* 100 */       System.out.println(le);
/* 101 */       System.exit(1);
/*     */     } catch (ClassNotFoundException cnfe) {
/*     */       
/* 104 */       System.out.println("NativeVerifier: FILE NOT FOUND: '" + args[0] + "'.");
/* 105 */       System.exit(1);
/*     */     } catch (Throwable t) {
/*     */       
/* 108 */       System.out.println("NativeVerifier: Unspecified verification error on'" + args[0] + "'.");
/* 109 */       System.exit(1);
/*     */     } 
/*     */     
/* 112 */     System.out.println("NativeVerifier: Class file '" + args[0] + "' seems to be okay.");
/* 113 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/NativeVerifier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */