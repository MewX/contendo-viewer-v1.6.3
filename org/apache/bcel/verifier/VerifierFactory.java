/*     */ package org.apache.bcel.verifier;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerifierFactory
/*     */ {
/*  75 */   private static HashMap hashMap = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   private static Vector observers = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Verifier getVerifier(String fully_qualified_classname) {
/*  93 */     fully_qualified_classname = fully_qualified_classname;
/*     */     
/*  95 */     Verifier v = (Verifier)hashMap.get(fully_qualified_classname);
/*  96 */     if (v == null) {
/*  97 */       v = new Verifier(fully_qualified_classname);
/*  98 */       hashMap.put(fully_qualified_classname, v);
/*  99 */       notify(fully_qualified_classname);
/*     */     } 
/*     */     
/* 102 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void notify(String fully_qualified_classname) {
/* 110 */     Iterator i = observers.iterator();
/* 111 */     while (i.hasNext()) {
/* 112 */       VerifierFactoryObserver vfo = i.next();
/* 113 */       vfo.update(fully_qualified_classname);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Verifier[] getVerifiers() {
/* 125 */     Verifier[] vs = new Verifier[hashMap.values().size()];
/* 126 */     return (Verifier[])hashMap.values().toArray((Object[])vs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void attach(VerifierFactoryObserver o) {
/* 133 */     observers.addElement(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void detach(VerifierFactoryObserver o) {
/* 140 */     observers.removeElement(o);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/VerifierFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */