/*    */ package org.apache.batik.swing;
/*    */ 
/*    */ import java.awt.Image;
/*    */ import java.beans.SimpleBeanInfo;
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
/*    */ 
/*    */ public class JSVGCanvasBeanInfo
/*    */   extends SimpleBeanInfo
/*    */ {
/* 48 */   protected Image iconColor16x16 = loadImage("resources/batikColor16x16.gif");
/* 49 */   protected Image iconMono16x16 = loadImage("resources/batikMono16x16.gif");
/* 50 */   protected Image iconColor32x32 = loadImage("resources/batikColor32x32.gif");
/* 51 */   protected Image iconMono32x32 = loadImage("resources/batikMono32x32.gif");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Image getIcon(int iconType) {
/* 58 */     switch (iconType) {
/*    */       case 1:
/* 60 */         return this.iconColor16x16;
/*    */       case 3:
/* 62 */         return this.iconMono16x16;
/*    */       case 2:
/* 64 */         return this.iconColor32x32;
/*    */       case 4:
/* 66 */         return this.iconMono32x32;
/*    */     } 
/* 68 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/JSVGCanvasBeanInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */