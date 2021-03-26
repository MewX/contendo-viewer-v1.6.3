/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
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
/*    */ public class SVGStylingAttributes
/*    */   implements SVGSyntax
/*    */ {
/* 32 */   static Set attrSet = new HashSet();
/*    */   
/*    */   static {
/* 35 */     attrSet.add("clip-path");
/* 36 */     attrSet.add("color-interpolation");
/* 37 */     attrSet.add("color-rendering");
/* 38 */     attrSet.add("enable-background");
/* 39 */     attrSet.add("fill");
/* 40 */     attrSet.add("fill-opacity");
/* 41 */     attrSet.add("fill-rule");
/* 42 */     attrSet.add("filter");
/* 43 */     attrSet.add("flood-color");
/* 44 */     attrSet.add("flood-opacity");
/* 45 */     attrSet.add("font-family");
/* 46 */     attrSet.add("font-size");
/* 47 */     attrSet.add("font-weight");
/* 48 */     attrSet.add("font-style");
/* 49 */     attrSet.add("image-rendering");
/* 50 */     attrSet.add("mask");
/* 51 */     attrSet.add("opacity");
/* 52 */     attrSet.add("shape-rendering");
/* 53 */     attrSet.add("stop-color");
/* 54 */     attrSet.add("stop-opacity");
/* 55 */     attrSet.add("stroke");
/* 56 */     attrSet.add("stroke-opacity");
/* 57 */     attrSet.add("stroke-dasharray");
/* 58 */     attrSet.add("stroke-dashoffset");
/* 59 */     attrSet.add("stroke-linecap");
/* 60 */     attrSet.add("stroke-linejoin");
/* 61 */     attrSet.add("stroke-miterlimit");
/* 62 */     attrSet.add("stroke-width");
/* 63 */     attrSet.add("text-rendering");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public static final Set set = attrSet;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGStylingAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */