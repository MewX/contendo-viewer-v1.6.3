/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.contentstream.operator.Operator;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdfparser.PDFStreamParser;
/*     */ import org.apache.pdfbox.pdmodel.PDPageContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
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
/*     */ class PDDefaultAppearanceString
/*     */ {
/*     */   private static final float DEFAULT_FONT_SIZE = 12.0F;
/*     */   private final PDResources defaultResources;
/*     */   private COSName fontName;
/*     */   private PDFont font;
/*  63 */   private float fontSize = 12.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDColor fontColor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDDefaultAppearanceString(COSString defaultAppearance, PDResources defaultResources) throws IOException {
/*  75 */     if (defaultAppearance == null)
/*     */     {
/*  77 */       throw new IllegalArgumentException("/DA is a required entry");
/*     */     }
/*     */     
/*  80 */     if (defaultResources == null)
/*     */     {
/*  82 */       throw new IllegalArgumentException("/DR is a required entry");
/*     */     }
/*     */     
/*  85 */     this.defaultResources = defaultResources;
/*  86 */     processAppearanceStringOperators(defaultAppearance.getBytes());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processAppearanceStringOperators(byte[] content) throws IOException {
/*  97 */     List<COSBase> arguments = new ArrayList<COSBase>();
/*  98 */     PDFStreamParser parser = new PDFStreamParser(content);
/*  99 */     Object token = parser.parseNextToken();
/* 100 */     while (token != null) {
/*     */       
/* 102 */       if (token instanceof COSObject) {
/*     */         
/* 104 */         arguments.add(((COSObject)token).getObject());
/*     */       }
/* 106 */       else if (token instanceof Operator) {
/*     */         
/* 108 */         processOperator((Operator)token, arguments);
/* 109 */         arguments = new ArrayList<COSBase>();
/*     */       }
/*     */       else {
/*     */         
/* 113 */         arguments.add((COSBase)token);
/*     */       } 
/* 115 */       token = parser.parseNextToken();
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
/*     */   private void processOperator(Operator operator, List<COSBase> operands) throws IOException {
/* 128 */     String name = operator.getName();
/*     */     
/* 130 */     if ("Tf".equals(name)) {
/*     */       
/* 132 */       processSetFont(operands);
/*     */     }
/* 134 */     else if ("g".equals(name)) {
/*     */       
/* 136 */       processSetFontColor(operands);
/*     */     }
/* 138 */     else if ("rg".equals(name)) {
/*     */       
/* 140 */       processSetFontColor(operands);
/*     */     }
/* 142 */     else if ("k".equals(name)) {
/*     */       
/* 144 */       processSetFontColor(operands);
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
/*     */   private void processSetFont(List<COSBase> operands) throws IOException {
/* 156 */     if (operands.size() < 2)
/*     */     {
/* 158 */       throw new IOException("Missing operands for set font operator " + Arrays.toString(operands.toArray()));
/*     */     }
/*     */     
/* 161 */     COSBase base0 = operands.get(0);
/* 162 */     COSBase base1 = operands.get(1);
/* 163 */     if (!(base0 instanceof COSName)) {
/*     */       return;
/*     */     }
/*     */     
/* 167 */     if (!(base1 instanceof COSNumber)) {
/*     */       return;
/*     */     }
/*     */     
/* 171 */     COSName fontName = (COSName)base0;
/*     */     
/* 173 */     PDFont font = this.defaultResources.getFont(fontName);
/* 174 */     float fontSize = ((COSNumber)base1).floatValue();
/*     */ 
/*     */     
/* 177 */     if (font == null)
/*     */     {
/* 179 */       throw new IOException("Could not find font: /" + fontName.getName());
/*     */     }
/* 181 */     setFontName(fontName);
/* 182 */     setFont(font);
/* 183 */     setFontSize(fontSize);
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
/*     */   private void processSetFontColor(List<COSBase> operands) throws IOException {
/*     */     PDDeviceGray pDDeviceGray;
/*     */     PDDeviceRGB pDDeviceRGB;
/*     */     PDDeviceCMYK pDDeviceCMYK;
/* 198 */     switch (operands.size()) {
/*     */       
/*     */       case 1:
/* 201 */         pDDeviceGray = PDDeviceGray.INSTANCE;
/*     */         break;
/*     */       case 3:
/* 204 */         pDDeviceRGB = PDDeviceRGB.INSTANCE;
/*     */         break;
/*     */       case 4:
/* 207 */         pDDeviceCMYK = PDDeviceCMYK.INSTANCE;
/*     */         break;
/*     */       default:
/* 210 */         throw new IOException("Missing operands for set non stroking color operator " + Arrays.toString(operands.toArray()));
/*     */     } 
/* 212 */     COSArray array = new COSArray();
/* 213 */     array.addAll(operands);
/* 214 */     setFontColor(new PDColor(array, (PDColorSpace)pDDeviceCMYK));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   COSName getFontName() {
/* 224 */     return this.fontName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFontName(COSName fontName) {
/* 234 */     this.fontName = fontName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDFont getFont() throws IOException {
/* 242 */     return this.font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFont(PDFont font) {
/* 252 */     this.font = font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFontSize() {
/* 260 */     return this.fontSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFontSize(float fontSize) {
/* 270 */     this.fontSize = fontSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDColor getFontColor() {
/* 278 */     return this.fontColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFontColor(PDColor fontColor) {
/* 288 */     this.fontColor = fontColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void writeTo(PDPageContentStream contents, float zeroFontSize) throws IOException {
/* 296 */     float fontSize = getFontSize();
/* 297 */     if (fontSize == 0.0F)
/*     */     {
/* 299 */       fontSize = zeroFontSize;
/*     */     }
/* 301 */     contents.setFont(getFont(), fontSize);
/*     */     
/* 303 */     if (getFontColor() != null)
/*     */     {
/* 305 */       contents.setNonStrokingColor(getFontColor());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void copyNeededResourcesTo(PDAppearanceStream appearanceStream) throws IOException {
/* 316 */     PDResources streamResources = appearanceStream.getResources();
/* 317 */     if (streamResources == null) {
/*     */       
/* 319 */       streamResources = new PDResources();
/* 320 */       appearanceStream.setResources(streamResources);
/*     */     } 
/*     */     
/* 323 */     if (streamResources.getFont(this.fontName) == null)
/*     */     {
/* 325 */       streamResources.put(this.fontName, getFont());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDDefaultAppearanceString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */