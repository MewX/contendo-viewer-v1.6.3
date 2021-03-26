/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.g2d.TransformStackElement;
/*     */ import org.apache.batik.util.SVGConstants;
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
/*     */ public class SVGGraphicContext
/*     */   implements ErrorConstants, SVGConstants
/*     */ {
/*  37 */   private static final String[] leafOnlyAttributes = new String[] { "opacity", "filter", "clip-path" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private static final String[] defaultValues = new String[] { "1", "none", "none" };
/*     */ 
/*     */ 
/*     */   
/*     */   private Map context;
/*     */ 
/*     */   
/*     */   private Map groupContext;
/*     */ 
/*     */   
/*     */   private Map graphicElementContext;
/*     */ 
/*     */   
/*     */   private TransformStackElement[] transformStack;
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGGraphicContext(Map context, TransformStackElement[] transformStack) {
/*  61 */     if (context == null)
/*  62 */       throw new SVGGraphics2DRuntimeException("context map(s) should not be null"); 
/*  63 */     if (transformStack == null)
/*  64 */       throw new SVGGraphics2DRuntimeException("transformer stack should not be null"); 
/*  65 */     this.context = context;
/*  66 */     this.transformStack = transformStack;
/*  67 */     computeGroupAndGraphicElementContext();
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
/*     */   public SVGGraphicContext(Map groupContext, Map graphicElementContext, TransformStackElement[] transformStack) {
/*  79 */     if (groupContext == null || graphicElementContext == null)
/*  80 */       throw new SVGGraphics2DRuntimeException("context map(s) should not be null"); 
/*  81 */     if (transformStack == null) {
/*  82 */       throw new SVGGraphics2DRuntimeException("transformer stack should not be null");
/*     */     }
/*  84 */     this.groupContext = groupContext;
/*  85 */     this.graphicElementContext = graphicElementContext;
/*  86 */     this.transformStack = transformStack;
/*  87 */     computeContext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getContext() {
/*  95 */     return this.context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getGroupContext() {
/* 102 */     return this.groupContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getGraphicElementContext() {
/* 109 */     return this.graphicElementContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformStackElement[] getTransformStack() {
/* 116 */     return this.transformStack;
/*     */   }
/*     */   
/*     */   private void computeContext() {
/* 120 */     if (this.context != null) {
/*     */       return;
/*     */     }
/* 123 */     this.context = new HashMap<Object, Object>(this.groupContext);
/* 124 */     this.context.putAll(this.graphicElementContext);
/*     */   }
/*     */   
/*     */   private void computeGroupAndGraphicElementContext() {
/* 128 */     if (this.groupContext != null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 134 */     this.groupContext = new HashMap<Object, Object>(this.context);
/* 135 */     this.graphicElementContext = new HashMap<Object, Object>();
/* 136 */     for (int i = 0; i < leafOnlyAttributes.length; i++) {
/* 137 */       Object attrValue = this.groupContext.get(leafOnlyAttributes[i]);
/* 138 */       if (attrValue != null) {
/* 139 */         if (!attrValue.equals(defaultValues[i]))
/* 140 */           this.graphicElementContext.put(leafOnlyAttributes[i], attrValue); 
/* 141 */         this.groupContext.remove(leafOnlyAttributes[i]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGGraphicContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */