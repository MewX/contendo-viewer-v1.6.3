/*    */ package org.apache.xmlgraphics.java2d.color;
/*    */ 
/*    */ import java.awt.Color;
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
/*    */ public final class DefaultColorConverter
/*    */   implements ColorConverter
/*    */ {
/* 32 */   private static final DefaultColorConverter SINGLETON = new DefaultColorConverter();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static DefaultColorConverter getInstance() {
/* 43 */     return SINGLETON;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color convert(Color color) {
/* 48 */     return color;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/DefaultColorConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */