/*     */ package org.apache.bcel.verifier;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.apache.bcel.Repository;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ import org.apache.bcel.verifier.statics.Pass1Verifier;
/*     */ import org.apache.bcel.verifier.statics.Pass2Verifier;
/*     */ import org.apache.bcel.verifier.statics.Pass3aVerifier;
/*     */ import org.apache.bcel.verifier.structurals.Pass3bVerifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Verifier
/*     */ {
/*     */   private final String classname;
/*     */   private Pass1Verifier p1v;
/*     */   private Pass2Verifier p2v;
/*  94 */   private HashMap p3avs = new HashMap();
/*     */   
/*  96 */   private HashMap p3bvs = new HashMap();
/*     */ 
/*     */   
/*     */   public VerificationResult doPass1() {
/* 100 */     if (this.p1v == null) {
/* 101 */       this.p1v = new Pass1Verifier(this);
/*     */     }
/* 103 */     return this.p1v.verify();
/*     */   }
/*     */ 
/*     */   
/*     */   public VerificationResult doPass2() {
/* 108 */     if (this.p2v == null) {
/* 109 */       this.p2v = new Pass2Verifier(this);
/*     */     }
/* 111 */     return this.p2v.verify();
/*     */   }
/*     */ 
/*     */   
/*     */   public VerificationResult doPass3a(int method_no) {
/* 116 */     String key = Integer.toString(method_no);
/*     */     
/* 118 */     Pass3aVerifier p3av = (Pass3aVerifier)this.p3avs.get(key);
/* 119 */     if (this.p3avs.get(key) == null) {
/* 120 */       p3av = new Pass3aVerifier(this, method_no);
/* 121 */       this.p3avs.put(key, p3av);
/*     */     } 
/* 123 */     return p3av.verify();
/*     */   }
/*     */ 
/*     */   
/*     */   public VerificationResult doPass3b(int method_no) {
/* 128 */     String key = Integer.toString(method_no);
/*     */     
/* 130 */     Pass3bVerifier p3bv = (Pass3bVerifier)this.p3bvs.get(key);
/* 131 */     if (this.p3bvs.get(key) == null) {
/* 132 */       p3bv = new Pass3bVerifier(this, method_no);
/* 133 */       this.p3bvs.put(key, p3bv);
/*     */     } 
/* 135 */     return p3bv.verify();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Verifier() {
/* 142 */     this.classname = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Verifier(String fully_qualified_classname) {
/* 151 */     this.classname = fully_qualified_classname;
/* 152 */     flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getClassName() {
/* 163 */     return this.classname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/* 173 */     this.p1v = null;
/* 174 */     this.p2v = null;
/* 175 */     this.p3avs.clear();
/* 176 */     this.p3bvs.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getMessages() {
/* 184 */     ArrayList messages = new ArrayList();
/*     */     
/* 186 */     if (this.p1v != null) {
/* 187 */       String[] p1m = this.p1v.getMessages();
/* 188 */       for (int j = 0; j < p1m.length; j++) {
/* 189 */         messages.add("Pass 1: " + p1m[j]);
/*     */       }
/*     */     } 
/* 192 */     if (this.p2v != null) {
/* 193 */       String[] p2m = this.p2v.getMessages();
/* 194 */       for (int j = 0; j < p2m.length; j++) {
/* 195 */         messages.add("Pass 2: " + p2m[j]);
/*     */       }
/*     */     } 
/* 198 */     Iterator p3as = this.p3avs.values().iterator();
/* 199 */     while (p3as.hasNext()) {
/* 200 */       Pass3aVerifier pv = p3as.next();
/* 201 */       String[] p3am = pv.getMessages();
/* 202 */       int meth = pv.getMethodNo();
/* 203 */       for (int j = 0; j < p3am.length; j++) {
/* 204 */         messages.add("Pass 3a, method " + meth + " ('" + Repository.lookupClass(this.classname).getMethods()[meth] + "'): " + p3am[j]);
/*     */       }
/*     */     } 
/* 207 */     Iterator p3bs = this.p3bvs.values().iterator();
/* 208 */     while (p3bs.hasNext()) {
/* 209 */       Pass3bVerifier pv = p3bs.next();
/* 210 */       String[] p3bm = pv.getMessages();
/* 211 */       int meth = pv.getMethodNo();
/* 212 */       for (int j = 0; j < p3bm.length; j++) {
/* 213 */         messages.add("Pass 3b, method " + meth + " ('" + Repository.lookupClass(this.classname).getMethods()[meth] + "'): " + p3bm[j]);
/*     */       }
/*     */     } 
/*     */     
/* 217 */     String[] ret = new String[messages.size()];
/* 218 */     for (int i = 0; i < messages.size(); i++) {
/* 219 */       ret[i] = messages.get(i);
/*     */     }
/*     */     
/* 222 */     return ret;
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
/*     */   public static void main(String[] args) {
/* 236 */     System.out.println("JustIce by Enver Haase, (C) 2001. http://bcel.sourceforge.net\n");
/* 237 */     for (int k = 0; k < args.length; k++) {
/*     */       
/* 239 */       if (args[k].endsWith(".class")) {
/* 240 */         int dotclasspos = args[k].lastIndexOf(".class");
/* 241 */         if (dotclasspos != -1) args[k] = args[k].substring(0, dotclasspos);
/*     */       
/*     */       } 
/* 244 */       args[k] = args[k].replace('/', '.');
/*     */       
/* 246 */       System.out.println("Now verifiying: " + args[k] + "\n");
/*     */       
/* 248 */       Verifier v = VerifierFactory.getVerifier(args[k]);
/*     */ 
/*     */       
/* 251 */       VerificationResult vr = v.doPass1();
/* 252 */       System.out.println("Pass 1:\n" + vr);
/*     */       
/* 254 */       vr = v.doPass2();
/* 255 */       System.out.println("Pass 2:\n" + vr);
/*     */       
/* 257 */       if (vr == VerificationResult.VR_OK) {
/* 258 */         JavaClass jc = Repository.lookupClass(args[k]);
/* 259 */         for (int i = 0; i < (jc.getMethods()).length; i++) {
/* 260 */           vr = v.doPass3a(i);
/* 261 */           System.out.println("Pass 3a, method " + i + " ['" + jc.getMethods()[i] + "']:\n" + vr);
/*     */           
/* 263 */           vr = v.doPass3b(i);
/* 264 */           System.out.println("Pass 3b, method number " + i + " ['" + jc.getMethods()[i] + "']:\n" + vr);
/*     */         } 
/*     */       } 
/*     */       
/* 268 */       System.out.println("Warnings:");
/* 269 */       String[] warnings = v.getMessages();
/* 270 */       if (warnings.length == 0) System.out.println("<none>"); 
/* 271 */       for (int j = 0; j < warnings.length; j++) {
/* 272 */         System.out.println(warnings[j]);
/*     */       }
/*     */       
/* 275 */       System.out.println("\n");
/*     */ 
/*     */       
/* 278 */       v.flush();
/* 279 */       Repository.clearCache();
/* 280 */       System.gc();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/Verifier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */