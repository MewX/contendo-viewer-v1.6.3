/*    */ package net.a.a.i;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import javax.swing.JComponent;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   extends e
/*    */ {
/*    */   public Component.BaselineResizeBehavior getBaselineResizeBehavior(JComponent paramJComponent) {
/* 58 */     switch (((b)paramJComponent).m())
/*    */     { case 1:
/* 60 */         baselineResizeBehavior = Component.BaselineResizeBehavior.CONSTANT_ASCENT;
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
/* 71 */         return baselineResizeBehavior;case 3: baselineResizeBehavior = Component.BaselineResizeBehavior.CONSTANT_DESCENT; return baselineResizeBehavior;case 0: baselineResizeBehavior = Component.BaselineResizeBehavior.CENTER_OFFSET; return baselineResizeBehavior; }  Component.BaselineResizeBehavior baselineResizeBehavior = Component.BaselineResizeBehavior.OTHER; return baselineResizeBehavior;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/i/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */