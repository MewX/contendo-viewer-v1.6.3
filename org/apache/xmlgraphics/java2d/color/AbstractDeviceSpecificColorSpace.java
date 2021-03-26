/*    */ package org.apache.xmlgraphics.java2d.color;
/*    */ 
/*    */ import java.awt.color.ColorSpace;
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
/*    */ public abstract class AbstractDeviceSpecificColorSpace
/*    */   extends ColorSpace
/*    */ {
/*    */   private static final long serialVersionUID = -4888985582872101875L;
/*    */   
/*    */   protected AbstractDeviceSpecificColorSpace(int type, int numcomponents) {
/* 37 */     super(type, numcomponents);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/AbstractDeviceSpecificColorSpace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */