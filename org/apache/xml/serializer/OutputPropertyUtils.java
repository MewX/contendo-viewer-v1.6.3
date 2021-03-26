/*    */ package org.apache.xml.serializer;
/*    */ 
/*    */ import java.util.Properties;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OutputPropertyUtils
/*    */ {
/*    */   public static boolean getBooleanProperty(String key, Properties props) {
/* 46 */     String s = props.getProperty(key);
/*    */     
/* 48 */     if (null == s || !s.equals("yes")) {
/* 49 */       return false;
/*    */     }
/* 51 */     return true;
/*    */   }
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
/*    */   public static int getIntProperty(String key, Properties props) {
/* 69 */     String s = props.getProperty(key);
/*    */     
/* 71 */     if (null == s) {
/* 72 */       return 0;
/*    */     }
/* 74 */     return Integer.parseInt(s);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/OutputPropertyUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */