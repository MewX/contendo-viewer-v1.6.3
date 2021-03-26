/*     */ package org.apache.pdfbox.pdmodel;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.image.PDInlineImage;
/*     */ import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
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
/*     */ public final class PDAppearanceContentStream
/*     */   extends PDAbstractContentStream
/*     */   implements Closeable
/*     */ {
/*     */   public PDAppearanceContentStream(PDAppearanceStream appearance) throws IOException {
/*  47 */     this(appearance, appearance.getStream().createOutputStream());
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
/*     */   public PDAppearanceContentStream(PDAppearanceStream appearance, boolean compress) throws IOException {
/*  60 */     this(appearance, appearance.getStream().createOutputStream(compress ? COSName.FLATE_DECODE : null));
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
/*     */   public PDAppearanceContentStream(PDAppearanceStream appearance, OutputStream outputStream) {
/*  73 */     super(null, outputStream, appearance.getResources());
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
/*     */   public boolean setStrokingColorOnDemand(PDColor color) throws IOException {
/*  89 */     if (color != null) {
/*     */       
/*  91 */       float[] components = color.getComponents();
/*  92 */       if (components.length > 0) {
/*     */         
/*  94 */         setStrokingColor(components);
/*  95 */         return true;
/*     */       } 
/*     */     } 
/*  98 */     return false;
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
/*     */   public void setStrokingColor(float[] components) throws IOException {
/* 111 */     for (float value : components)
/*     */     {
/* 113 */       writeOperand(value);
/*     */     }
/*     */     
/* 116 */     int numComponents = components.length;
/* 117 */     switch (numComponents) {
/*     */       
/*     */       case 1:
/* 120 */         writeOperator("G");
/*     */         break;
/*     */       case 3:
/* 123 */         writeOperator("RG");
/*     */         break;
/*     */       case 4:
/* 126 */         writeOperator("K");
/*     */         break;
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
/*     */   public boolean setNonStrokingColorOnDemand(PDColor color) throws IOException {
/* 148 */     if (color != null) {
/*     */       
/* 150 */       float[] components = color.getComponents();
/* 151 */       if (components.length > 0) {
/*     */         
/* 153 */         setNonStrokingColor(components);
/* 154 */         return true;
/*     */       } 
/*     */     } 
/* 157 */     return false;
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
/*     */   public void setNonStrokingColor(float[] components) throws IOException {
/* 170 */     for (float value : components)
/*     */     {
/* 172 */       writeOperand(value);
/*     */     }
/*     */     
/* 175 */     int numComponents = components.length;
/* 176 */     switch (numComponents) {
/*     */       
/*     */       case 1:
/* 179 */         writeOperator("g");
/*     */         break;
/*     */       case 3:
/* 182 */         writeOperator("rg");
/*     */         break;
/*     */       case 4:
/* 185 */         writeOperator("k");
/*     */         break;
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
/*     */   public void setBorderLine(float lineWidth, PDBorderStyleDictionary bs, COSArray border) throws IOException {
/* 209 */     if (bs != null && bs.getCOSObject().containsKey(COSName.D) && bs
/* 210 */       .getStyle().equals("D")) {
/*     */       
/* 212 */       setLineDashPattern(bs.getDashStyle().getDashArray(), 0.0F);
/*     */     }
/* 214 */     else if (bs == null && border.size() > 3 && border.getObject(3) instanceof COSArray) {
/*     */       
/* 216 */       setLineDashPattern(((COSArray)border.getObject(3)).toFloatArray(), 0.0F);
/*     */     } 
/* 218 */     setLineWidthOnDemand(lineWidth);
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
/*     */   public void setLineWidthOnDemand(float lineWidth) throws IOException {
/* 234 */     if (Math.abs(lineWidth - 1.0F) >= 1.0E-6D)
/*     */     {
/* 236 */       setLineWidth(lineWidth);
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
/*     */   public void drawShape(float lineWidth, boolean hasStroke, boolean hasFill) throws IOException {
/* 255 */     boolean resolvedHasStroke = hasStroke;
/*     */ 
/*     */     
/* 258 */     if (lineWidth < 1.0E-6D)
/*     */     {
/* 260 */       resolvedHasStroke = false;
/*     */     }
/* 262 */     if (hasFill && resolvedHasStroke) {
/*     */       
/* 264 */       fillAndStroke();
/*     */     }
/* 266 */     else if (resolvedHasStroke) {
/*     */       
/* 268 */       stroke();
/*     */     }
/* 270 */     else if (hasFill) {
/*     */       
/* 272 */       fill();
/*     */     }
/*     */     else {
/*     */       
/* 276 */       writeOperator("n");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDAppearanceContentStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */