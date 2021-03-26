/*    */ package org.apache.batik.util;
/*    */ 
/*    */ import java.awt.GraphicsEnvironment;
/*    */ import java.awt.Toolkit;
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
/*    */ public abstract class Platform
/*    */ {
/* 36 */   public static boolean isOSX = System.getProperty("os.name").equals("Mac OS X");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getScreenResolution() {
/* 46 */     if (GraphicsEnvironment.isHeadless()) {
/* 47 */       return 96;
/*    */     }
/* 49 */     return Toolkit.getDefaultToolkit().getScreenResolution();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */