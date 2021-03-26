/*     */ package org.apache.bcel.verifier.statics;
/*     */ 
/*     */ import org.apache.bcel.Repository;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ import org.apache.bcel.verifier.PassVerifier;
/*     */ import org.apache.bcel.verifier.VerificationResult;
/*     */ import org.apache.bcel.verifier.Verifier;
/*     */ import org.apache.bcel.verifier.exc.LoadingException;
/*     */ import org.apache.bcel.verifier.exc.Utility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Pass1Verifier
/*     */   extends PassVerifier
/*     */ {
/*     */   private JavaClass jc;
/*     */   private Verifier myOwner;
/*     */   
/*     */   private JavaClass getJavaClass() {
/*  88 */     if (this.jc == null) {
/*  89 */       this.jc = Repository.lookupClass(this.myOwner.getClassName());
/*     */     }
/*  91 */     return this.jc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Pass1Verifier(Verifier owner) {
/* 100 */     this.myOwner = owner;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerificationResult do_verify() {
/*     */     JavaClass jc;
/*     */     try {
/* 164 */       jc = getJavaClass();
/*     */       
/* 166 */       if (jc != null)
/*     */       {
/* 168 */         if (!this.myOwner.getClassName().equals(jc.getClassName()))
/*     */         {
/*     */           
/* 171 */           throw new LoadingException("Wrong name: the internal name of the .class file '" + jc.getClassName() + "' does not match the file's name '" + this.myOwner.getClassName() + "'.");
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (LoadingException e) {
/*     */       
/* 177 */       return new VerificationResult(2, e.getMessage());
/*     */     }
/*     */     catch (ClassFormatError e) {
/*     */       
/* 181 */       return new VerificationResult(2, e.getMessage());
/*     */     
/*     */     }
/*     */     catch (RuntimeException e) {
/*     */       
/* 186 */       return new VerificationResult(2, "Parsing via BCEL did not succeed. " + e.getClass().getName() + " occured:\n" + Utility.getStackTrace(e));
/*     */     } 
/*     */     
/* 189 */     if (jc != null) {
/* 190 */       return VerificationResult.VR_OK;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 195 */     return new VerificationResult(2, "Repository.lookup() failed. FILE NOT FOUND?");
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
/*     */   public String[] getMessages() {
/* 211 */     return super.getMessages();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/statics/Pass1Verifier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */