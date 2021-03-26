/*    */ package org.apache.xalan.xsltc;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProcessorVersion
/*    */ {
/* 39 */   private static int MAJOR = 1;
/* 40 */   private static int MINOR = 0;
/* 41 */   private static int DELTA = 0;
/*    */   
/*    */   public static void main(String[] args) {
/* 44 */     System.out.println("XSLTC version " + MAJOR + "." + MINOR + ((DELTA > 0) ? ("." + DELTA) : ""));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/ProcessorVersion.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */