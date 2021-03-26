/*     */ package org.apache.pdfbox.text;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.contentstream.PDFStreamEngine;
/*     */ import org.apache.pdfbox.contentstream.operator.DrawObject;
/*     */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*     */ import org.apache.pdfbox.contentstream.operator.state.Concatenate;
/*     */ import org.apache.pdfbox.contentstream.operator.state.Restore;
/*     */ import org.apache.pdfbox.contentstream.operator.state.Save;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetGraphicsStateParameters;
/*     */ import org.apache.pdfbox.contentstream.operator.state.SetMatrix;
/*     */ import org.apache.pdfbox.contentstream.operator.text.BeginText;
/*     */ import org.apache.pdfbox.contentstream.operator.text.EndText;
/*     */ import org.apache.pdfbox.contentstream.operator.text.MoveText;
/*     */ import org.apache.pdfbox.contentstream.operator.text.MoveTextSetLeading;
/*     */ import org.apache.pdfbox.contentstream.operator.text.NextLine;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetCharSpacing;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetFontAndSize;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetTextHorizontalScaling;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetTextLeading;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetTextRenderingMode;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetTextRise;
/*     */ import org.apache.pdfbox.contentstream.operator.text.SetWordSpacing;
/*     */ import org.apache.pdfbox.contentstream.operator.text.ShowText;
/*     */ import org.apache.pdfbox.contentstream.operator.text.ShowTextAdjusted;
/*     */ import org.apache.pdfbox.contentstream.operator.text.ShowTextLine;
/*     */ import org.apache.pdfbox.contentstream.operator.text.ShowTextLineAndSpace;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.font.PDCIDFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDCIDFontType2;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
/*     */ import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDType0Font;
/*     */ import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.PDGraphicsState;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ import org.apache.pdfbox.util.Vector;
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
/*     */ class LegacyPDFStreamEngine
/*     */   extends PDFStreamEngine
/*     */ {
/*  77 */   private static final Log LOG = LogFactory.getLog(LegacyPDFStreamEngine.class);
/*     */   
/*     */   private int pageRotation;
/*     */   
/*     */   private PDRectangle pageSize;
/*     */   
/*     */   private Matrix translateMatrix;
/*     */   
/*     */   private final GlyphList glyphList;
/*     */ 
/*     */   
/*     */   LegacyPDFStreamEngine() throws IOException {
/*  89 */     addOperator((OperatorProcessor)new BeginText());
/*  90 */     addOperator((OperatorProcessor)new Concatenate());
/*  91 */     addOperator((OperatorProcessor)new DrawObject());
/*  92 */     addOperator((OperatorProcessor)new EndText());
/*  93 */     addOperator((OperatorProcessor)new SetGraphicsStateParameters());
/*  94 */     addOperator((OperatorProcessor)new Save());
/*  95 */     addOperator((OperatorProcessor)new Restore());
/*  96 */     addOperator((OperatorProcessor)new NextLine());
/*  97 */     addOperator((OperatorProcessor)new SetCharSpacing());
/*  98 */     addOperator((OperatorProcessor)new MoveText());
/*  99 */     addOperator((OperatorProcessor)new MoveTextSetLeading());
/* 100 */     addOperator((OperatorProcessor)new SetFontAndSize());
/* 101 */     addOperator((OperatorProcessor)new ShowText());
/* 102 */     addOperator((OperatorProcessor)new ShowTextAdjusted());
/* 103 */     addOperator((OperatorProcessor)new SetTextLeading());
/* 104 */     addOperator((OperatorProcessor)new SetMatrix());
/* 105 */     addOperator((OperatorProcessor)new SetTextRenderingMode());
/* 106 */     addOperator((OperatorProcessor)new SetTextRise());
/* 107 */     addOperator((OperatorProcessor)new SetWordSpacing());
/* 108 */     addOperator((OperatorProcessor)new SetTextHorizontalScaling());
/* 109 */     addOperator((OperatorProcessor)new ShowTextLine());
/* 110 */     addOperator((OperatorProcessor)new ShowTextLineAndSpace());
/*     */ 
/*     */     
/* 113 */     String path = "/org/apache/pdfbox/resources/glyphlist/additional.txt";
/* 114 */     InputStream input = GlyphList.class.getResourceAsStream(path);
/* 115 */     this.glyphList = new GlyphList(GlyphList.getAdobeGlyphList(), input);
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
/*     */   public void processPage(PDPage page) throws IOException {
/* 127 */     this.pageRotation = page.getRotation();
/* 128 */     this.pageSize = page.getCropBox();
/*     */     
/* 130 */     if (this.pageSize.getLowerLeftX() == 0.0F && this.pageSize.getLowerLeftY() == 0.0F) {
/*     */       
/* 132 */       this.translateMatrix = null;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 137 */       this.translateMatrix = Matrix.getTranslateInstance(-this.pageSize.getLowerLeftX(), -this.pageSize.getLowerLeftY());
/*     */     } 
/* 139 */     super.processPage(page);
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
/*     */   protected void showGlyph(Matrix textRenderingMatrix, PDFont font, int code, String unicode, Vector displacement) throws IOException {
/*     */     float height;
/*     */     Matrix translatedTextRenderingMatrix;
/* 156 */     PDGraphicsState state = getGraphicsState();
/* 157 */     Matrix ctm = state.getCurrentTransformationMatrix();
/* 158 */     float fontSize = state.getTextState().getFontSize();
/* 159 */     float horizontalScaling = state.getTextState().getHorizontalScaling() / 100.0F;
/* 160 */     Matrix textMatrix = getTextMatrix();
/*     */     
/* 162 */     BoundingBox bbox = font.getBoundingBox();
/* 163 */     if (bbox.getLowerLeftY() < -32768.0F)
/*     */     {
/*     */ 
/*     */       
/* 167 */       bbox.setLowerLeftY(-(bbox.getLowerLeftY() + 65536.0F));
/*     */     }
/*     */     
/* 170 */     float glyphHeight = bbox.getHeight() / 2.0F;
/*     */ 
/*     */     
/* 173 */     PDFontDescriptor fontDescriptor = font.getFontDescriptor();
/* 174 */     if (fontDescriptor != null) {
/*     */       
/* 176 */       float capHeight = fontDescriptor.getCapHeight();
/* 177 */       if (Float.compare(capHeight, 0.0F) != 0 && (capHeight < glyphHeight || 
/* 178 */         Float.compare(glyphHeight, 0.0F) == 0))
/*     */       {
/* 180 */         glyphHeight = capHeight;
/*     */       }
/*     */ 
/*     */       
/* 184 */       float ascent = fontDescriptor.getAscent();
/* 185 */       float descent = fontDescriptor.getDescent();
/* 186 */       if (capHeight > ascent && ascent > 0.0F && descent < 0.0F && ((ascent - descent) / 2.0F < glyphHeight || 
/* 187 */         Float.compare(glyphHeight, 0.0F) == 0))
/*     */       {
/* 189 */         glyphHeight = (ascent - descent) / 2.0F;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 195 */     if (font instanceof org.apache.pdfbox.pdmodel.font.PDType3Font) {
/*     */       
/* 197 */       height = (font.getFontMatrix().transformPoint(0.0F, glyphHeight)).y;
/*     */     }
/*     */     else {
/*     */       
/* 201 */       height = glyphHeight / 1000.0F;
/*     */     } 
/*     */     
/* 204 */     float displacementX = displacement.getX();
/*     */ 
/*     */ 
/*     */     
/* 208 */     if (font.isVertical()) {
/*     */       
/* 210 */       displacementX = font.getWidth(code) / 1000.0F;
/*     */       
/* 212 */       TrueTypeFont ttf = null;
/* 213 */       if (font instanceof PDTrueTypeFont) {
/*     */         
/* 215 */         ttf = ((PDTrueTypeFont)font).getTrueTypeFont();
/*     */       }
/* 217 */       else if (font instanceof PDType0Font) {
/*     */         
/* 219 */         PDCIDFont cidFont = ((PDType0Font)font).getDescendantFont();
/* 220 */         if (cidFont instanceof PDCIDFontType2)
/*     */         {
/* 222 */           ttf = ((PDCIDFontType2)cidFont).getTrueTypeFont();
/*     */         }
/*     */       } 
/* 225 */       if (ttf != null && ttf.getUnitsPerEm() != 1000)
/*     */       {
/* 227 */         displacementX *= 1000.0F / ttf.getUnitsPerEm();
/*     */       }
/*     */     } 
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
/* 240 */     float tx = displacementX * fontSize * horizontalScaling;
/* 241 */     float ty = displacement.getY() * fontSize;
/*     */ 
/*     */     
/* 244 */     Matrix td = Matrix.getTranslateInstance(tx, ty);
/*     */ 
/*     */     
/* 247 */     Matrix nextTextRenderingMatrix = td.multiply(textMatrix).multiply(ctm);
/* 248 */     float nextX = nextTextRenderingMatrix.getTranslateX();
/* 249 */     float nextY = nextTextRenderingMatrix.getTranslateY();
/*     */ 
/*     */     
/* 252 */     float dxDisplay = nextX - textRenderingMatrix.getTranslateX();
/* 253 */     float dyDisplay = height * textRenderingMatrix.getScalingFactorY();
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
/* 265 */     float glyphSpaceToTextSpaceFactor = 0.001F;
/* 266 */     if (font instanceof org.apache.pdfbox.pdmodel.font.PDType3Font)
/*     */     {
/* 268 */       glyphSpaceToTextSpaceFactor = font.getFontMatrix().getScaleX();
/*     */     }
/*     */     
/* 271 */     float spaceWidthText = 0.0F;
/*     */ 
/*     */     
/*     */     try {
/* 275 */       spaceWidthText = font.getSpaceWidth() * glyphSpaceToTextSpaceFactor;
/*     */     }
/* 277 */     catch (Throwable exception) {
/*     */       
/* 279 */       LOG.warn(exception, exception);
/*     */     } 
/*     */     
/* 282 */     if (spaceWidthText == 0.0F) {
/*     */       
/* 284 */       spaceWidthText = font.getAverageFontWidth() * glyphSpaceToTextSpaceFactor;
/*     */       
/* 286 */       spaceWidthText *= 0.8F;
/*     */     } 
/* 288 */     if (spaceWidthText == 0.0F)
/*     */     {
/* 290 */       spaceWidthText = 1.0F;
/*     */     }
/*     */ 
/*     */     
/* 294 */     float spaceWidthDisplay = spaceWidthText * textRenderingMatrix.getScalingFactorX();
/*     */ 
/*     */     
/* 297 */     unicode = font.toUnicode(code, this.glyphList);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     if (unicode == null)
/*     */     {
/* 304 */       if (font instanceof org.apache.pdfbox.pdmodel.font.PDSimpleFont) {
/*     */         
/* 306 */         char c = (char)code;
/* 307 */         unicode = new String(new char[] { c });
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 319 */     if (this.translateMatrix == null) {
/*     */       
/* 321 */       translatedTextRenderingMatrix = textRenderingMatrix;
/*     */     }
/*     */     else {
/*     */       
/* 325 */       translatedTextRenderingMatrix = Matrix.concatenate(this.translateMatrix, textRenderingMatrix);
/* 326 */       nextX -= this.pageSize.getLowerLeftX();
/* 327 */       nextY -= this.pageSize.getLowerLeftY();
/*     */     } 
/*     */     
/* 330 */     processTextPosition(new TextPosition(this.pageRotation, this.pageSize.getWidth(), this.pageSize
/* 331 */           .getHeight(), translatedTextRenderingMatrix, nextX, nextY, 
/* 332 */           Math.abs(dyDisplay), dxDisplay, 
/* 333 */           Math.abs(spaceWidthDisplay), unicode, new int[] { code }, font, fontSize, 
/* 334 */           (int)(fontSize * textMatrix.getScalingFactorX())));
/*     */   }
/*     */   
/*     */   protected void processTextPosition(TextPosition text) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/text/LegacyPDFStreamEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */