/*     */ package org.apache.pdfbox.text;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Stack;
/*     */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*     */ import org.apache.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequence;
/*     */ import org.apache.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequenceWithProperties;
/*     */ import org.apache.pdfbox.contentstream.operator.markedcontent.DrawObject;
/*     */ import org.apache.pdfbox.contentstream.operator.markedcontent.EndMarkedContentSequence;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
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
/*     */ public class PDFMarkedContentExtractor
/*     */   extends LegacyPDFStreamEngine
/*     */ {
/*     */   private final boolean suppressDuplicateOverlappingText = true;
/*  43 */   private final List<PDMarkedContent> markedContents = new ArrayList<PDMarkedContent>();
/*  44 */   private final Stack<PDMarkedContent> currentMarkedContents = new Stack<PDMarkedContent>();
/*  45 */   private final Map<String, List<TextPosition>> characterListMapping = new HashMap<String, List<TextPosition>>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFMarkedContentExtractor() throws IOException {
/*  52 */     this(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFMarkedContentExtractor(String encoding) throws IOException {
/*  62 */     addOperator((OperatorProcessor)new BeginMarkedContentSequenceWithProperties());
/*  63 */     addOperator((OperatorProcessor)new BeginMarkedContentSequence());
/*  64 */     addOperator((OperatorProcessor)new EndMarkedContentSequence());
/*  65 */     addOperator((OperatorProcessor)new DrawObject());
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
/*     */   private boolean within(float first, float second, float variance) {
/*  79 */     return (second > first - variance && second < first + variance);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void beginMarkedContentSequence(COSName tag, COSDictionary properties) {
/*  85 */     PDMarkedContent markedContent = PDMarkedContent.create(tag, properties);
/*  86 */     if (this.currentMarkedContents.isEmpty()) {
/*     */       
/*  88 */       this.markedContents.add(markedContent);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  93 */       PDMarkedContent currentMarkedContent = this.currentMarkedContents.peek();
/*  94 */       if (currentMarkedContent != null)
/*     */       {
/*  96 */         currentMarkedContent.addMarkedContent(markedContent);
/*     */       }
/*     */     } 
/*  99 */     this.currentMarkedContents.push(markedContent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endMarkedContentSequence() {
/* 105 */     if (!this.currentMarkedContents.isEmpty())
/*     */     {
/* 107 */       this.currentMarkedContents.pop();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void xobject(PDXObject xobject) {
/* 113 */     if (!this.currentMarkedContents.isEmpty())
/*     */     {
/* 115 */       ((PDMarkedContent)this.currentMarkedContents.peek()).addXObject(xobject);
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
/*     */   protected void processTextPosition(TextPosition text) {
/* 129 */     boolean showCharacter = true;
/* 130 */     getClass();
/*     */     
/* 132 */     showCharacter = false;
/* 133 */     String textCharacter = text.getUnicode();
/* 134 */     float textX = text.getX();
/* 135 */     float textY = text.getY();
/* 136 */     List<TextPosition> sameTextCharacters = this.characterListMapping.get(textCharacter);
/* 137 */     if (sameTextCharacters == null) {
/*     */       
/* 139 */       sameTextCharacters = new ArrayList<TextPosition>();
/* 140 */       this.characterListMapping.put(textCharacter, sameTextCharacters);
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
/*     */     
/* 154 */     boolean suppressCharacter = false;
/* 155 */     float tolerance = text.getWidth() / textCharacter.length() / 3.0F;
/* 156 */     for (TextPosition sameTextCharacter : sameTextCharacters) {
/*     */       
/* 158 */       TextPosition character = sameTextCharacter;
/* 159 */       String charCharacter = character.getUnicode();
/* 160 */       float charX = character.getX();
/* 161 */       float charY = character.getY();
/*     */       
/* 163 */       if (charCharacter != null && 
/*     */         
/* 165 */         within(charX, textX, tolerance) && 
/* 166 */         within(charY, textY, tolerance)) {
/*     */ 
/*     */ 
/*     */         
/* 170 */         suppressCharacter = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 174 */     if (!suppressCharacter) {
/*     */       
/* 176 */       sameTextCharacters.add(text);
/* 177 */       showCharacter = true;
/*     */     } 
/*     */ 
/*     */     
/* 181 */     if (showCharacter) {
/*     */       
/* 183 */       List<TextPosition> textList = new ArrayList<TextPosition>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 191 */       if (textList.isEmpty()) {
/*     */         
/* 193 */         textList.add(text);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 201 */         TextPosition previousTextPosition = textList.get(textList.size() - 1);
/* 202 */         if (text.isDiacritic() && previousTextPosition.contains(text)) {
/*     */           
/* 204 */           previousTextPosition.mergeDiacritic(text);
/*     */ 
/*     */         
/*     */         }
/* 208 */         else if (previousTextPosition.isDiacritic() && text.contains(previousTextPosition)) {
/*     */           
/* 210 */           text.mergeDiacritic(previousTextPosition);
/* 211 */           textList.remove(textList.size() - 1);
/* 212 */           textList.add(text);
/*     */         }
/*     */         else {
/*     */           
/* 216 */           textList.add(text);
/*     */         } 
/*     */       } 
/* 219 */       if (!this.currentMarkedContents.isEmpty())
/*     */       {
/* 221 */         ((PDMarkedContent)this.currentMarkedContents.peek()).addText(text);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PDMarkedContent> getMarkedContents() {
/* 228 */     return this.markedContents;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/text/PDFMarkedContentExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */