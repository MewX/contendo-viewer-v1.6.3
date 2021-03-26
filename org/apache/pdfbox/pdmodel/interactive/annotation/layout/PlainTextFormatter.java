/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.layout;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
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
/*     */ public class PlainTextFormatter
/*     */ {
/*     */   private static final int FONTSCALE = 1000;
/*     */   private final AppearanceStyle appearanceStyle;
/*     */   private final boolean wrapLines;
/*     */   private final float width;
/*     */   private final PDAppearanceContentStream contents;
/*     */   private final PlainText textContent;
/*     */   private final TextAlign textAlignment;
/*     */   private float horizontalOffset;
/*     */   private float verticalOffset;
/*     */   
/*     */   enum TextAlign
/*     */   {
/*  40 */     LEFT(0), CENTER(1), RIGHT(2), JUSTIFY(4);
/*     */     
/*     */     private final int alignment;
/*     */ 
/*     */     
/*     */     TextAlign(int alignment) {
/*  46 */       this.alignment = alignment;
/*     */     }
/*     */ 
/*     */     
/*     */     int getTextAlign() {
/*  51 */       return this.alignment;
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
/*     */   public static class Builder
/*     */   {
/*     */     private PDAppearanceContentStream contents;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private AppearanceStyle appearanceStyle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean wrapLines = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     private float width = 0.0F;
/*     */     private PlainText textContent;
/*  94 */     private PlainTextFormatter.TextAlign textAlignment = PlainTextFormatter.TextAlign.LEFT;
/*     */ 
/*     */ 
/*     */     
/*  98 */     private float horizontalOffset = 0.0F;
/*  99 */     private float verticalOffset = 0.0F;
/*     */ 
/*     */     
/*     */     public Builder(PDAppearanceContentStream contents) {
/* 103 */       this.contents = contents;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder style(AppearanceStyle appearanceStyle) {
/* 108 */       this.appearanceStyle = appearanceStyle;
/* 109 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder wrapLines(boolean wrapLines) {
/* 114 */       this.wrapLines = wrapLines;
/* 115 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder width(float width) {
/* 120 */       this.width = width;
/* 121 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder textAlign(int alignment) {
/* 126 */       this.textAlignment = PlainTextFormatter.TextAlign.valueOf(alignment);
/* 127 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder textAlign(PlainTextFormatter.TextAlign alignment) {
/* 132 */       this.textAlignment = alignment;
/* 133 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder text(PlainText textContent) {
/* 139 */       this.textContent = textContent;
/* 140 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder initialOffset(float horizontalOffset, float verticalOffset) {
/* 145 */       this.horizontalOffset = horizontalOffset;
/* 146 */       this.verticalOffset = verticalOffset;
/* 147 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PlainTextFormatter build() {
/* 152 */       return new PlainTextFormatter(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private PlainTextFormatter(Builder builder) {
/* 158 */     this.appearanceStyle = builder.appearanceStyle;
/* 159 */     this.wrapLines = builder.wrapLines;
/* 160 */     this.width = builder.width;
/* 161 */     this.contents = builder.contents;
/* 162 */     this.textContent = builder.textContent;
/* 163 */     this.textAlignment = builder.textAlignment;
/* 164 */     this.horizontalOffset = builder.horizontalOffset;
/* 165 */     this.verticalOffset = builder.verticalOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void format() throws IOException {
/* 175 */     if (this.textContent != null && !this.textContent.getParagraphs().isEmpty()) {
/*     */       
/* 177 */       boolean isFirstParagraph = true;
/* 178 */       for (PlainText.Paragraph paragraph : this.textContent.getParagraphs()) {
/*     */         
/* 180 */         if (this.wrapLines) {
/*     */           
/* 182 */           List<PlainText.Line> lines = paragraph.getLines(this.appearanceStyle
/* 183 */               .getFont(), this.appearanceStyle
/* 184 */               .getFontSize(), this.width);
/*     */ 
/*     */           
/* 187 */           processLines(lines, isFirstParagraph);
/* 188 */           isFirstParagraph = false;
/*     */           
/*     */           continue;
/*     */         } 
/* 192 */         float startOffset = 0.0F;
/*     */ 
/*     */ 
/*     */         
/* 196 */         float lineWidth = this.appearanceStyle.getFont().getStringWidth(paragraph.getText()) * this.appearanceStyle.getFontSize() / 1000.0F;
/*     */         
/* 198 */         if (lineWidth < this.width)
/*     */         {
/* 200 */           switch (this.textAlignment) {
/*     */             
/*     */             case CENTER:
/* 203 */               startOffset = (this.width - lineWidth) / 2.0F;
/*     */               break;
/*     */             case RIGHT:
/* 206 */               startOffset = this.width - lineWidth;
/*     */               break;
/*     */             
/*     */             default:
/* 210 */               startOffset = 0.0F;
/*     */               break;
/*     */           } 
/*     */         }
/* 214 */         this.contents.newLineAtOffset(this.horizontalOffset + startOffset, this.verticalOffset);
/* 215 */         this.contents.showText(paragraph.getText());
/*     */       } 
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
/*     */   private void processLines(List<PlainText.Line> lines, boolean isFirstParagraph) throws IOException {
/* 234 */     float lastPos = 0.0F;
/* 235 */     float startOffset = 0.0F;
/* 236 */     float interWordSpacing = 0.0F;
/*     */     
/* 238 */     for (PlainText.Line line : lines) {
/*     */       
/* 240 */       switch (this.textAlignment) {
/*     */         
/*     */         case CENTER:
/* 243 */           startOffset = (this.width - line.getWidth()) / 2.0F;
/*     */           break;
/*     */         case RIGHT:
/* 246 */           startOffset = this.width - line.getWidth();
/*     */           break;
/*     */         case JUSTIFY:
/* 249 */           if (lines.indexOf(line) != lines.size() - 1)
/*     */           {
/* 251 */             interWordSpacing = line.getInterWordSpacing(this.width);
/*     */           }
/*     */           break;
/*     */         default:
/* 255 */           startOffset = 0.0F;
/*     */           break;
/*     */       } 
/* 258 */       float offset = -lastPos + startOffset + this.horizontalOffset;
/*     */       
/* 260 */       if (lines.indexOf(line) == 0 && isFirstParagraph) {
/*     */         
/* 262 */         this.contents.newLineAtOffset(offset, this.verticalOffset);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 267 */         this.verticalOffset -= this.appearanceStyle.getLeading();
/* 268 */         this.contents.newLineAtOffset(offset, -this.appearanceStyle.getLeading());
/*     */       } 
/*     */       
/* 271 */       lastPos += offset;
/*     */       
/* 273 */       List<PlainText.Word> words = line.getWords();
/* 274 */       for (PlainText.Word word : words) {
/*     */         
/* 276 */         this.contents.showText(word.getText());
/* 277 */         float wordWidth = ((Float)word.getAttributes().getIterator().getAttribute(PlainText.TextAttribute.WIDTH)).floatValue();
/* 278 */         if (words.indexOf(word) != words.size() - 1) {
/*     */           
/* 280 */           this.contents.newLineAtOffset(wordWidth + interWordSpacing, 0.0F);
/* 281 */           lastPos = lastPos + wordWidth + interWordSpacing;
/*     */         } 
/*     */       } 
/*     */     } 
/* 285 */     this.horizontalOffset -= lastPos;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/layout/PlainTextFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */