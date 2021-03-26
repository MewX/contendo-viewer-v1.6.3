/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*    */ import org.apache.batik.ext.awt.image.spi.DefaultBrokenLinkProvider;
/*    */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ import org.apache.batik.gvt.filter.GraphicsNodeRable8Bit;
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
/*    */ public class SVGBrokenLinkProvider
/*    */   extends DefaultBrokenLinkProvider
/*    */   implements ErrorConstants
/*    */ {
/*    */   public Filter getBrokenLinkImage(Object base, String code, Object[] params) {
/* 59 */     String message = formatMessage(base, code, params);
/* 60 */     Map<Object, Object> props = new HashMap<Object, Object>();
/* 61 */     props.put("org.apache.batik.BrokenLinkImage", message);
/*    */     
/* 63 */     CompositeGraphicsNode cgn = new CompositeGraphicsNode();
/* 64 */     return (Filter)new GraphicsNodeRable8Bit((GraphicsNode)cgn, props);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGBrokenLinkProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */