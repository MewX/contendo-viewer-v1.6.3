/*    */ package org.apache.batik.gvt.renderer;
/*    */ 
/*    */ import org.apache.batik.util.Platform;
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
/*    */ public class ConcreteImageRendererFactory
/*    */   implements ImageRendererFactory
/*    */ {
/*    */   public Renderer createRenderer() {
/* 35 */     return createStaticImageRenderer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageRenderer createStaticImageRenderer() {
/* 42 */     if (Platform.isOSX)
/* 43 */       return new MacRenderer(); 
/* 44 */     return new StaticRenderer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageRenderer createDynamicImageRenderer() {
/* 51 */     if (Platform.isOSX)
/* 52 */       return new MacRenderer(); 
/* 53 */     return new DynamicRenderer();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/renderer/ConcreteImageRendererFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */