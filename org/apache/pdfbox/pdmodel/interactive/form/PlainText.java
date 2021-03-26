/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.text.BreakIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ class PlainText
/*     */ {
/*     */   private static final float FONTSCALE = 1000.0F;
/*     */   private final List<Paragraph> paragraphs;
/*     */   
/*     */   PlainText(String textValue) {
/*  54 */     List<String> parts = Arrays.asList(textValue.replaceAll("\t", " ").split("\\r\\n|\\n|\\r|\\u2028|\\u2029"));
/*  55 */     this.paragraphs = new ArrayList<Paragraph>();
/*  56 */     for (String part : parts) {
/*     */ 
/*     */       
/*  59 */       if (part.length() == 0)
/*     */       {
/*  61 */         part = " ";
/*     */       }
/*  63 */       this.paragraphs.add(new Paragraph(part));
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
/*     */   PlainText(List<String> listValue) {
/*  77 */     this.paragraphs = new ArrayList<Paragraph>();
/*  78 */     for (String part : listValue)
/*     */     {
/*  80 */       this.paragraphs.add(new Paragraph(part));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   List<Paragraph> getParagraphs() {
/*  91 */     return this.paragraphs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class TextAttribute
/*     */     extends AttributedCharacterIterator.Attribute
/*     */   {
/*     */     private static final long serialVersionUID = -3138885145941283005L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     public static final AttributedCharacterIterator.Attribute WIDTH = new TextAttribute("width");
/*     */ 
/*     */     
/*     */     protected TextAttribute(String name) {
/* 115 */       super(name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Paragraph
/*     */   {
/*     */     private final String textContent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Paragraph(String text) {
/* 135 */       this.textContent = text;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String getText() {
/* 145 */       return this.textContent;
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
/*     */     
/*     */     List<PlainText.Line> getLines(PDFont font, float fontSize, float width) throws IOException {
/* 159 */       BreakIterator iterator = BreakIterator.getLineInstance();
/* 160 */       iterator.setText(this.textContent);
/*     */       
/* 162 */       float scale = fontSize / 1000.0F;
/*     */       
/* 164 */       int start = iterator.first();
/* 165 */       int end = iterator.next();
/* 166 */       float lineWidth = 0.0F;
/*     */       
/* 168 */       List<PlainText.Line> textLines = new ArrayList<PlainText.Line>();
/* 169 */       PlainText.Line textLine = new PlainText.Line();
/*     */       
/* 171 */       while (end != -1) {
/*     */         
/* 173 */         String word = this.textContent.substring(start, end);
/* 174 */         float wordWidth = font.getStringWidth(word) * scale;
/*     */         
/* 176 */         lineWidth += wordWidth;
/*     */ 
/*     */         
/* 179 */         if (lineWidth >= width && Character.isWhitespace(word.charAt(word.length() - 1))) {
/*     */           
/* 181 */           float whitespaceWidth = font.getStringWidth(word.substring(word.length() - 1)) * scale;
/* 182 */           lineWidth -= whitespaceWidth;
/*     */         } 
/*     */         
/* 185 */         if (lineWidth >= width) {
/*     */           
/* 187 */           textLine.setWidth(textLine.calculateWidth(font, fontSize));
/* 188 */           textLines.add(textLine);
/* 189 */           textLine = new PlainText.Line();
/* 190 */           lineWidth = font.getStringWidth(word) * scale;
/*     */         } 
/*     */         
/* 193 */         AttributedString as = new AttributedString(word);
/* 194 */         as.addAttribute(PlainText.TextAttribute.WIDTH, Float.valueOf(wordWidth));
/* 195 */         PlainText.Word wordInstance = new PlainText.Word(word);
/* 196 */         wordInstance.setAttributes(as);
/* 197 */         textLine.addWord(wordInstance);
/* 198 */         start = end;
/* 199 */         end = iterator.next();
/*     */       } 
/* 201 */       textLine.setWidth(textLine.calculateWidth(font, fontSize));
/* 202 */       textLines.add(textLine);
/* 203 */       return textLines;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Line
/*     */   {
/* 212 */     private final List<PlainText.Word> words = new ArrayList<PlainText.Word>();
/*     */     
/*     */     private float lineWidth;
/*     */     
/*     */     float getWidth() {
/* 217 */       return this.lineWidth;
/*     */     }
/*     */ 
/*     */     
/*     */     void setWidth(float width) {
/* 222 */       this.lineWidth = width;
/*     */     }
/*     */ 
/*     */     
/*     */     float calculateWidth(PDFont font, float fontSize) throws IOException {
/* 227 */       float scale = fontSize / 1000.0F;
/* 228 */       float calculatedWidth = 0.0F;
/* 229 */       for (PlainText.Word word : this.words) {
/*     */         
/* 231 */         calculatedWidth += ((Float)word
/* 232 */           .getAttributes().getIterator().getAttribute(PlainText.TextAttribute.WIDTH)).floatValue();
/* 233 */         String text = word.getText();
/* 234 */         if (this.words.indexOf(word) == this.words.size() - 1 && Character.isWhitespace(text.charAt(text.length() - 1))) {
/*     */           
/* 236 */           float whitespaceWidth = font.getStringWidth(text.substring(text.length() - 1)) * scale;
/* 237 */           calculatedWidth -= whitespaceWidth;
/*     */         } 
/*     */       } 
/* 240 */       return calculatedWidth;
/*     */     }
/*     */ 
/*     */     
/*     */     List<PlainText.Word> getWords() {
/* 245 */       return this.words;
/*     */     }
/*     */ 
/*     */     
/*     */     float getInterWordSpacing(float width) {
/* 250 */       return (width - this.lineWidth) / (this.words.size() - 1);
/*     */     }
/*     */ 
/*     */     
/*     */     void addWord(PlainText.Word word) {
/* 255 */       this.words.add(word);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Word
/*     */   {
/*     */     private AttributedString attributedString;
/*     */ 
/*     */     
/*     */     private final String textContent;
/*     */ 
/*     */ 
/*     */     
/*     */     Word(String text) {
/* 272 */       this.textContent = text;
/*     */     }
/*     */ 
/*     */     
/*     */     String getText() {
/* 277 */       return this.textContent;
/*     */     }
/*     */ 
/*     */     
/*     */     AttributedString getAttributes() {
/* 282 */       return this.attributedString;
/*     */     }
/*     */ 
/*     */     
/*     */     void setAttributes(AttributedString as) {
/* 287 */       this.attributedString = as;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PlainText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */