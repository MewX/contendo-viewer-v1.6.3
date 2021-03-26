/*    */ package org.apache.batik.anim.timing;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Trace
/*    */ {
/*    */   private static int level;
/*    */   private static boolean enabled = false;
/*    */   
/*    */   public static void enter(Object o, String fn, Object[] args) {
/* 34 */     if (enabled) {
/* 35 */       System.err.print("LOG\t"); int i;
/* 36 */       for (i = 0; i < level; i++) {
/* 37 */         System.err.print("  ");
/*    */       }
/* 39 */       if (fn == null) {
/* 40 */         System.err.print("new " + o.getClass().getName() + "(");
/*    */       } else {
/* 42 */         System.err.print(o + "." + fn + "(");
/*    */       } 
/* 44 */       if (args != null) {
/* 45 */         System.err.print(args[0]);
/* 46 */         for (i = 1; i < args.length; i++) {
/* 47 */           System.err.print(", " + args[i]);
/*    */         }
/*    */       } 
/* 50 */       System.err.println(")");
/*    */     } 
/* 52 */     level++;
/*    */   }
/*    */   
/*    */   public static void exit() {
/* 56 */     level--;
/*    */   }
/*    */   
/*    */   public static void print(String s) {
/* 60 */     if (enabled) {
/* 61 */       System.err.print("LOG\t");
/* 62 */       for (int i = 0; i < level; i++) {
/* 63 */         System.err.print("  ");
/*    */       }
/* 65 */       System.err.println(s);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/Trace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */