/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.UnitProcessor;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class UnitProcessor
/*     */   extends UnitProcessor
/*     */ {
/*     */   public static UnitProcessor.Context createContext(BridgeContext ctx, Element e) {
/*  44 */     return new DefaultContext(ctx, e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgHorizontalCoordinateToObjectBoundingBox(String s, String attr, UnitProcessor.Context ctx) {
/*  63 */     return svgToObjectBoundingBox(s, attr, (short)2, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgVerticalCoordinateToObjectBoundingBox(String s, String attr, UnitProcessor.Context ctx) {
/*  78 */     return svgToObjectBoundingBox(s, attr, (short)1, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgOtherCoordinateToObjectBoundingBox(String s, String attr, UnitProcessor.Context ctx) {
/*  93 */     return svgToObjectBoundingBox(s, attr, (short)0, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgHorizontalLengthToObjectBoundingBox(String s, String attr, UnitProcessor.Context ctx) {
/* 108 */     return svgLengthToObjectBoundingBox(s, attr, (short)2, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgVerticalLengthToObjectBoundingBox(String s, String attr, UnitProcessor.Context ctx) {
/* 123 */     return svgLengthToObjectBoundingBox(s, attr, (short)1, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgOtherLengthToObjectBoundingBox(String s, String attr, UnitProcessor.Context ctx) {
/* 138 */     return svgLengthToObjectBoundingBox(s, attr, (short)0, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgLengthToObjectBoundingBox(String s, String attr, short d, UnitProcessor.Context ctx) {
/* 154 */     float v = svgToObjectBoundingBox(s, attr, d, ctx);
/* 155 */     if (v < 0.0F) {
/* 156 */       throw new BridgeException(getBridgeContext(ctx), ctx.getElement(), "length.negative", new Object[] { attr, s });
/*     */     }
/*     */ 
/*     */     
/* 160 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgToObjectBoundingBox(String s, String attr, short d, UnitProcessor.Context ctx) {
/*     */     try {
/* 177 */       return UnitProcessor.svgToObjectBoundingBox(s, attr, d, ctx);
/*     */     }
/* 179 */     catch (ParseException pEx) {
/* 180 */       throw new BridgeException(getBridgeContext(ctx), ctx.getElement(), pEx, "attribute.malformed", new Object[] { attr, s, pEx });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgHorizontalLengthToUserSpace(String s, String attr, UnitProcessor.Context ctx) {
/* 203 */     return svgLengthToUserSpace(s, attr, (short)2, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgVerticalLengthToUserSpace(String s, String attr, UnitProcessor.Context ctx) {
/* 217 */     return svgLengthToUserSpace(s, attr, (short)1, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgOtherLengthToUserSpace(String s, String attr, UnitProcessor.Context ctx) {
/* 231 */     return svgLengthToUserSpace(s, attr, (short)0, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgHorizontalCoordinateToUserSpace(String s, String attr, UnitProcessor.Context ctx) {
/* 244 */     return svgToUserSpace(s, attr, (short)2, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgVerticalCoordinateToUserSpace(String s, String attr, UnitProcessor.Context ctx) {
/* 257 */     return svgToUserSpace(s, attr, (short)1, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgOtherCoordinateToUserSpace(String s, String attr, UnitProcessor.Context ctx) {
/* 270 */     return svgToUserSpace(s, attr, (short)0, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgLengthToUserSpace(String s, String attr, short d, UnitProcessor.Context ctx) {
/* 286 */     float v = svgToUserSpace(s, attr, d, ctx);
/* 287 */     if (v < 0.0F) {
/* 288 */       throw new BridgeException(getBridgeContext(ctx), ctx.getElement(), "length.negative", new Object[] { attr, s });
/*     */     }
/*     */ 
/*     */     
/* 292 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgToUserSpace(String s, String attr, short d, UnitProcessor.Context ctx) {
/*     */     try {
/* 310 */       return UnitProcessor.svgToUserSpace(s, attr, d, ctx);
/*     */     }
/* 312 */     catch (ParseException pEx) {
/* 313 */       throw new BridgeException(getBridgeContext(ctx), ctx.getElement(), pEx, "attribute.malformed", new Object[] { attr, s, pEx });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static BridgeContext getBridgeContext(UnitProcessor.Context ctx) {
/* 325 */     if (ctx instanceof DefaultContext) {
/* 326 */       return ((DefaultContext)ctx).ctx;
/*     */     }
/* 328 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DefaultContext
/*     */     implements UnitProcessor.Context
/*     */   {
/*     */     protected Element e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected BridgeContext ctx;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DefaultContext(BridgeContext ctx, Element e) {
/* 352 */       this.ctx = ctx;
/* 353 */       this.e = e;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Element getElement() {
/* 360 */       return this.e;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getPixelUnitToMillimeter() {
/* 367 */       return this.ctx.getUserAgent().getPixelUnitToMillimeter();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getPixelToMM() {
/* 376 */       return getPixelUnitToMillimeter();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getFontSize() {
/* 384 */       return CSSUtilities.getComputedStyle(this.e, 22).getFloatValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getXHeight() {
/* 392 */       return 0.5F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getViewportWidth() {
/* 399 */       return this.ctx.getViewport(this.e).getWidth();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getViewportHeight() {
/* 406 */       return this.ctx.getViewport(this.e).getHeight();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/UnitProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */