/*     */ package org.apache.pdfbox.rendering;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
/*     */ import org.apache.pdfbox.util.Matrix;
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
/*     */ class TilingPaintFactory
/*     */ {
/*     */   private final PageDrawer drawer;
/*  38 */   private final Map<TilingPaintParameter, WeakReference<Paint>> weakCache = new WeakHashMap<TilingPaintParameter, WeakReference<Paint>>();
/*     */ 
/*     */ 
/*     */   
/*     */   TilingPaintFactory(PageDrawer drawer) {
/*  43 */     this.drawer = drawer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   Paint create(PDTilingPattern pattern, PDColorSpace colorSpace, PDColor color, AffineTransform xform) throws IOException {
/*  49 */     Paint paint = null;
/*     */     
/*  51 */     TilingPaintParameter tilingPaintParameter = new TilingPaintParameter(this.drawer.getInitialMatrix(), pattern.getCOSObject(), colorSpace, color, xform);
/*  52 */     WeakReference<Paint> weakRef = this.weakCache.get(tilingPaintParameter);
/*  53 */     if (weakRef != null)
/*     */     {
/*     */       
/*  56 */       paint = weakRef.get();
/*     */     }
/*  58 */     if (paint == null) {
/*     */       
/*  60 */       paint = new TilingPaint(this.drawer, pattern, colorSpace, color, xform);
/*  61 */       this.weakCache.put(tilingPaintParameter, new WeakReference<Paint>(paint));
/*     */     } 
/*  63 */     return paint;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class TilingPaintParameter
/*     */   {
/*     */     private final Matrix matrix;
/*     */     
/*     */     private final COSDictionary patternDict;
/*     */     
/*     */     private final PDColorSpace colorSpace;
/*     */     
/*     */     private final PDColor color;
/*     */     private final AffineTransform xform;
/*     */     
/*     */     private TilingPaintParameter(Matrix matrix, COSDictionary patternDict, PDColorSpace colorSpace, PDColor color, AffineTransform xform) {
/*  79 */       this.matrix = matrix.clone();
/*  80 */       this.patternDict = patternDict;
/*  81 */       this.colorSpace = colorSpace;
/*  82 */       this.color = color;
/*  83 */       this.xform = xform;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/*  91 */       if (this == obj)
/*     */       {
/*  93 */         return true;
/*     */       }
/*  95 */       if (obj == null)
/*     */       {
/*  97 */         return false;
/*     */       }
/*  99 */       if (getClass() != obj.getClass())
/*     */       {
/* 101 */         return false;
/*     */       }
/* 103 */       TilingPaintParameter other = (TilingPaintParameter)obj;
/* 104 */       if (this.matrix != other.matrix && (this.matrix == null || !this.matrix.equals(other.matrix)))
/*     */       {
/* 106 */         return false;
/*     */       }
/* 108 */       if (this.patternDict != other.patternDict && (this.patternDict == null || !this.patternDict.equals(other.patternDict)))
/*     */       {
/* 110 */         return false;
/*     */       }
/* 112 */       if (this.colorSpace != other.colorSpace && (this.colorSpace == null || !this.colorSpace.equals(other.colorSpace)))
/*     */       {
/* 114 */         return false;
/*     */       }
/* 116 */       if (this.color == null && other.color != null)
/*     */       {
/* 118 */         return false;
/*     */       }
/* 120 */       if (this.color != null && other.color == null)
/*     */       {
/* 122 */         return false;
/*     */       }
/* 124 */       if (this.color != null && this.color.getColorSpace() != other.color.getColorSpace())
/*     */       {
/* 126 */         return false;
/*     */       }
/*     */       
/*     */       try {
/* 130 */         if (this.color != other.color && this.color.toRGB() != other.color.toRGB())
/*     */         {
/* 132 */           return false;
/*     */         }
/*     */       }
/* 135 */       catch (IOException ex) {
/*     */         
/* 137 */         return false;
/*     */       } 
/* 139 */       return (this.xform == other.xform || (this.xform != null && this.xform.equals(other.xform)));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 145 */       int hash = 7;
/* 146 */       hash = 23 * hash + ((this.matrix != null) ? this.matrix.hashCode() : 0);
/* 147 */       hash = 23 * hash + ((this.patternDict != null) ? this.patternDict.hashCode() : 0);
/* 148 */       hash = 23 * hash + ((this.colorSpace != null) ? this.colorSpace.hashCode() : 0);
/* 149 */       hash = 23 * hash + ((this.color != null) ? this.color.hashCode() : 0);
/* 150 */       hash = 23 * hash + ((this.xform != null) ? this.xform.hashCode() : 0);
/* 151 */       return hash;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 157 */       return "TilingPaintParameter{matrix=" + this.matrix + ", pattern=" + this.patternDict + ", colorSpace=" + this.colorSpace + ", color=" + this.color + ", xform=" + this.xform + '}';
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/TilingPaintFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */