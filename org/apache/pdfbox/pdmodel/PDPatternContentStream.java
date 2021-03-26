/*    */ package org.apache.pdfbox.pdmodel;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*    */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*    */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*    */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*    */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
/*    */ import org.apache.pdfbox.pdmodel.graphics.image.PDInlineImage;
/*    */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
/*    */ import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
/*    */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*    */ import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
/*    */ import org.apache.pdfbox.util.Matrix;
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
/*    */ public final class PDPatternContentStream
/*    */   extends PDAbstractContentStream
/*    */ {
/*    */   public PDPatternContentStream(PDTilingPattern pattern) throws IOException {
/* 37 */     super(null, pattern.getContentStream().createOutputStream(), pattern.getResources());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDPatternContentStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */