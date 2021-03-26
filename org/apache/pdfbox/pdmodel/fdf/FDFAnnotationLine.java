/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class FDFAnnotationLine
/*     */   extends FDFAnnotation
/*     */ {
/*     */   public static final String SUBTYPE = "Line";
/*     */   
/*     */   public FDFAnnotationLine() {
/*  48 */     this.annot.setName(COSName.SUBTYPE, "Line");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationLine(COSDictionary a) {
/*  58 */     super(a);
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
/*     */   public FDFAnnotationLine(Element element) throws IOException {
/*  70 */     super(element);
/*  71 */     this.annot.setName(COSName.SUBTYPE, "Line");
/*     */     
/*  73 */     String startCoords = element.getAttribute("start");
/*  74 */     if (startCoords == null || startCoords.isEmpty())
/*     */     {
/*  76 */       throw new IOException("Error: missing attribute 'start'");
/*     */     }
/*  78 */     String endCoords = element.getAttribute("end");
/*  79 */     if (endCoords == null || endCoords.isEmpty())
/*     */     {
/*  81 */       throw new IOException("Error: missing attribute 'end'");
/*     */     }
/*  83 */     String line = startCoords + "," + endCoords;
/*  84 */     String[] lineValues = line.split(",");
/*  85 */     if (lineValues.length != 4)
/*     */     {
/*  87 */       throw new IOException("Error: wrong amount of line coordinates");
/*     */     }
/*  89 */     float[] values = new float[4];
/*  90 */     for (int i = 0; i < 4; i++)
/*     */     {
/*  92 */       values[i] = Float.parseFloat(lineValues[i]);
/*     */     }
/*  94 */     setLine(values);
/*     */     
/*  96 */     String leaderLine = element.getAttribute("leaderLength");
/*  97 */     if (leaderLine != null && !leaderLine.isEmpty())
/*     */     {
/*  99 */       setLeaderLength(Float.parseFloat(leaderLine));
/*     */     }
/*     */     
/* 102 */     String leaderLineExtension = element.getAttribute("leaderExtend");
/* 103 */     if (leaderLineExtension != null && !leaderLineExtension.isEmpty())
/*     */     {
/* 105 */       setLeaderExtend(Float.parseFloat(leaderLineExtension));
/*     */     }
/*     */     
/* 108 */     String leaderLineOffset = element.getAttribute("leaderOffset");
/* 109 */     if (leaderLineOffset != null && !leaderLineOffset.isEmpty())
/*     */     {
/* 111 */       setLeaderOffset(Float.parseFloat(leaderLineOffset));
/*     */     }
/*     */     
/* 114 */     String startStyle = element.getAttribute("head");
/* 115 */     if (startStyle != null && !startStyle.isEmpty())
/*     */     {
/* 117 */       setStartPointEndingStyle(startStyle);
/*     */     }
/* 119 */     String endStyle = element.getAttribute("tail");
/* 120 */     if (endStyle != null && !endStyle.isEmpty())
/*     */     {
/* 122 */       setEndPointEndingStyle(endStyle);
/*     */     }
/*     */     
/* 125 */     String color = element.getAttribute("interior-color");
/* 126 */     if (color != null && color.length() == 7 && color.charAt(0) == '#') {
/*     */       
/* 128 */       int colorValue = Integer.parseInt(color.substring(1, 7), 16);
/* 129 */       setInteriorColor(new Color(colorValue));
/*     */     } 
/*     */     
/* 132 */     String caption = element.getAttribute("caption");
/* 133 */     if (caption != null && !caption.isEmpty())
/*     */     {
/* 135 */       setCaption("yes".equals(caption));
/*     */     }
/*     */     
/* 138 */     String captionH = element.getAttribute("caption-offset-h");
/* 139 */     if (captionH != null && !captionH.isEmpty())
/*     */     {
/* 141 */       setCaptionHorizontalOffset(Float.parseFloat(captionH));
/*     */     }
/*     */     
/* 144 */     String captionV = element.getAttribute("caption-offset-v");
/* 145 */     if (captionV != null && !captionV.isEmpty())
/*     */     {
/* 147 */       setCaptionVerticalOffset(Float.parseFloat(captionV));
/*     */     }
/*     */     
/* 150 */     String captionStyle = element.getAttribute("caption-style");
/* 151 */     if (captionStyle != null && !captionStyle.isEmpty())
/*     */     {
/* 153 */       setCaptionStyle(captionStyle);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLine(float[] line) {
/* 164 */     COSArray newLine = new COSArray();
/* 165 */     newLine.setFloatArray(line);
/* 166 */     this.annot.setItem(COSName.L, (COSBase)newLine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getLine() {
/* 176 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.L);
/* 177 */     if (array != null)
/*     */     {
/* 179 */       return array.toFloatArray();
/*     */     }
/*     */ 
/*     */     
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartPointEndingStyle(String style) {
/* 194 */     if (style == null)
/*     */     {
/* 196 */       style = "None";
/*     */     }
/* 198 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.LE);
/* 199 */     if (array == null) {
/*     */       
/* 201 */       array = new COSArray();
/* 202 */       array.add((COSBase)COSName.getPDFName(style));
/* 203 */       array.add((COSBase)COSName.getPDFName("None"));
/* 204 */       this.annot.setItem(COSName.LE, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 208 */       array.setName(0, style);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStartPointEndingStyle() {
/* 219 */     String retval = "None";
/* 220 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.LE);
/* 221 */     if (array != null)
/*     */     {
/* 223 */       retval = array.getName(0);
/*     */     }
/*     */     
/* 226 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndPointEndingStyle(String style) {
/* 236 */     if (style == null)
/*     */     {
/* 238 */       style = "None";
/*     */     }
/* 240 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.LE);
/* 241 */     if (array == null) {
/*     */       
/* 243 */       array = new COSArray();
/* 244 */       array.add((COSBase)COSName.getPDFName("None"));
/* 245 */       array.add((COSBase)COSName.getPDFName(style));
/* 246 */       this.annot.setItem(COSName.LE, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 250 */       array.setName(1, style);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndPointEndingStyle() {
/* 261 */     String retval = "None";
/* 262 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.LE);
/* 263 */     if (array != null)
/*     */     {
/* 265 */       retval = array.getName(1);
/*     */     }
/*     */     
/* 268 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInteriorColor(Color color) {
/* 278 */     COSArray array = null;
/* 279 */     if (color != null) {
/*     */       
/* 281 */       float[] colors = color.getRGBColorComponents(null);
/* 282 */       array = new COSArray();
/* 283 */       array.setFloatArray(colors);
/*     */     } 
/* 285 */     this.annot.setItem(COSName.IC, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getInteriorColor() {
/* 295 */     Color retval = null;
/* 296 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.IC);
/* 297 */     if (array != null) {
/*     */       
/* 299 */       float[] rgb = array.toFloatArray();
/* 300 */       if (rgb.length >= 3)
/*     */       {
/* 302 */         retval = new Color(rgb[0], rgb[1], rgb[2]);
/*     */       }
/*     */     } 
/* 305 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaption(boolean cap) {
/* 315 */     this.annot.setBoolean(COSName.CAP, cap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCaption() {
/* 325 */     return this.annot.getBoolean(COSName.CAP, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeaderLength() {
/* 335 */     return this.annot.getFloat(COSName.LL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeaderLength(float leaderLength) {
/* 345 */     this.annot.setFloat(COSName.LL, leaderLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeaderExtend() {
/* 355 */     return this.annot.getFloat(COSName.LLE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeaderExtend(float leaderExtend) {
/* 365 */     this.annot.setFloat(COSName.LLE, leaderExtend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeaderOffset() {
/* 375 */     return this.annot.getFloat(COSName.LLO);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeaderOffset(float leaderOffset) {
/* 385 */     this.annot.setFloat(COSName.LLO, leaderOffset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCaptionStyle() {
/* 395 */     return this.annot.getString(COSName.CP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaptionStyle(String captionStyle) {
/* 405 */     this.annot.setString(COSName.CP, captionStyle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaptionHorizontalOffset(float offset) {
/* 415 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.CO);
/* 416 */     if (array == null) {
/*     */       
/* 418 */       array = new COSArray();
/* 419 */       array.setFloatArray(new float[] { offset, 0.0F });
/* 420 */       this.annot.setItem(COSName.CO, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 424 */       array.set(0, (COSBase)new COSFloat(offset));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCaptionHorizontalOffset() {
/* 435 */     float retval = 0.0F;
/* 436 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.CO);
/* 437 */     if (array != null)
/*     */     {
/* 439 */       retval = array.toFloatArray()[0];
/*     */     }
/*     */     
/* 442 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaptionVerticalOffset(float offset) {
/* 452 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.CO);
/* 453 */     if (array == null) {
/*     */       
/* 455 */       array = new COSArray();
/* 456 */       array.setFloatArray(new float[] { 0.0F, offset });
/* 457 */       this.annot.setItem(COSName.CO, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 461 */       array.set(1, (COSBase)new COSFloat(offset));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCaptionVerticalOffset() {
/* 472 */     float retval = 0.0F;
/* 473 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.CO);
/* 474 */     if (array != null)
/*     */     {
/* 476 */       retval = array.toFloatArray()[1];
/*     */     }
/* 478 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */