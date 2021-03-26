/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.util.Charsets;
/*     */ import org.apache.pdfbox.contentstream.operator.Operator;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdfparser.PDFStreamParser;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDType1Font;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderEffectDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.layout.AppearanceStyle;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.layout.PlainText;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.layout.PlainTextFormatter;
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
/*     */ public class PDFreeTextAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  50 */   private static final Log LOG = LogFactory.getLog(PDFreeTextAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDFreeTextAppearanceHandler(PDAnnotation annotation) {
/*  54 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  60 */     generateNormalAppearance();
/*  61 */     generateRolloverAppearance();
/*  62 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  68 */     PDAnnotationMarkup annotation = (PDAnnotationMarkup)getAnnotation();
/*  69 */     float[] pathsArray = new float[0];
/*  70 */     if ("FreeTextCallout".equals(annotation.getIntent())) {
/*     */       
/*  72 */       pathsArray = annotation.getCallout();
/*  73 */       if (pathsArray == null || (pathsArray.length != 4 && pathsArray.length != 6))
/*     */       {
/*  75 */         pathsArray = new float[0];
/*     */       }
/*     */     } 
/*  78 */     AnnotationBorder ab = AnnotationBorder.getAnnotationBorder((PDAnnotation)annotation, annotation.getBorderStyle());
/*     */     
/*  80 */     PDAppearanceContentStream cs = null;
/*     */     try {
/*     */       PDRectangle borderBox;
/*     */       float xOffset, yOffset, clipY;
/*  84 */       cs = getNormalAppearanceAsContentStream(true);
/*     */ 
/*     */       
/*  87 */       boolean hasBackground = cs.setNonStrokingColorOnDemand(annotation.getColor());
/*  88 */       setOpacity(cs, annotation.getConstantOpacity());
/*     */ 
/*     */       
/*  91 */       PDColor strokingColor = extractNonStrokingColor(annotation);
/*  92 */       boolean hasStroke = cs.setStrokingColorOnDemand(strokingColor);
/*     */       
/*  94 */       if (ab.dashArray != null)
/*     */       {
/*  96 */         cs.setLineDashPattern(ab.dashArray, 0.0F);
/*     */       }
/*  98 */       cs.setLineWidth(ab.width);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 103 */       for (int i = 0; i < pathsArray.length / 2; i++) {
/*     */         
/* 105 */         float x = pathsArray[i * 2];
/* 106 */         float y = pathsArray[i * 2 + 1];
/* 107 */         if (i == 0) {
/*     */           
/* 109 */           if (SHORT_STYLES.contains(annotation.getLineEndingStyle())) {
/*     */ 
/*     */ 
/*     */             
/* 113 */             float x1 = pathsArray[2];
/* 114 */             float y1 = pathsArray[3];
/* 115 */             float len = (float)Math.sqrt(Math.pow((x - x1), 2.0D) + Math.pow((y - y1), 2.0D));
/* 116 */             if (Float.compare(len, 0.0F) != 0) {
/*     */               
/* 118 */               x += (x1 - x) / len * ab.width;
/* 119 */               y += (y1 - y) / len * ab.width;
/*     */             } 
/*     */           } 
/* 122 */           cs.moveTo(x, y);
/*     */         }
/*     */         else {
/*     */           
/* 126 */           cs.lineTo(x, y);
/*     */         } 
/*     */       } 
/* 129 */       if (pathsArray.length > 0)
/*     */       {
/* 131 */         cs.stroke();
/*     */       }
/*     */ 
/*     */       
/* 135 */       if ("FreeTextCallout".equals(annotation.getIntent()) && 
/*     */         
/* 137 */         !"None".equals(annotation.getLineEndingStyle()) && pathsArray.length >= 4) {
/*     */ 
/*     */         
/* 140 */         float x2 = pathsArray[2];
/* 141 */         float y2 = pathsArray[3];
/* 142 */         float x1 = pathsArray[0];
/* 143 */         float y1 = pathsArray[1];
/* 144 */         cs.saveGraphicsState();
/* 145 */         if (ANGLED_STYLES.contains(annotation.getLineEndingStyle())) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 152 */           double angle = Math.atan2((y2 - y1), (x2 - x1));
/* 153 */           cs.transform(Matrix.getRotateInstance(angle, x1, y1));
/*     */         }
/*     */         else {
/*     */           
/* 157 */           cs.transform(Matrix.getTranslateInstance(x1, y1));
/*     */         } 
/* 159 */         drawStyle(annotation.getLineEndingStyle(), cs, 0.0F, 0.0F, ab.width, hasStroke, hasBackground, false);
/* 160 */         cs.restoreGraphicsState();
/*     */       } 
/*     */ 
/*     */       
/* 164 */       PDBorderEffectDictionary borderEffect = annotation.getBorderEffect();
/* 165 */       if (borderEffect != null && borderEffect.getStyle().equals("C")) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 170 */         borderBox = applyRectDifferences(getRectangle(), annotation.getRectDifferences());
/*     */ 
/*     */ 
/*     */         
/* 174 */         CloudyBorder cloudyBorder = new CloudyBorder(cs, borderEffect.getIntensity(), ab.width, getRectangle());
/* 175 */         cloudyBorder.createCloudyRectangle(annotation.getRectDifference());
/* 176 */         annotation.setRectangle(cloudyBorder.getRectangle());
/* 177 */         annotation.setRectDifference(cloudyBorder.getRectDifference());
/* 178 */         PDAppearanceStream appearanceStream = annotation.getNormalAppearanceStream();
/* 179 */         appearanceStream.setBBox(cloudyBorder.getBBox());
/* 180 */         appearanceStream.setMatrix(cloudyBorder.getMatrix());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 191 */         borderBox = applyRectDifferences(getRectangle(), annotation.getRectDifferences());
/* 192 */         annotation.getNormalAppearanceStream().setBBox(borderBox);
/*     */ 
/*     */         
/* 195 */         PDRectangle paddedRectangle = getPaddedRectangle(borderBox, ab.width / 2.0F);
/* 196 */         cs.addRect(paddedRectangle.getLowerLeftX(), paddedRectangle.getLowerLeftY(), paddedRectangle
/* 197 */             .getWidth(), paddedRectangle.getHeight());
/*     */       } 
/* 199 */       cs.drawShape(ab.width, hasStroke, hasBackground);
/*     */ 
/*     */ 
/*     */       
/* 203 */       int rotation = annotation.getCOSObject().getInt(COSName.ROTATE, 0);
/* 204 */       cs.transform(Matrix.getRotateInstance(Math.toRadians(rotation), 0.0F, 0.0F));
/*     */ 
/*     */       
/* 207 */       float width = (rotation == 90 || rotation == 270) ? borderBox.getHeight() : borderBox.getWidth();
/*     */ 
/*     */       
/* 210 */       PDType1Font pDType1Font = PDType1Font.HELVETICA;
/*     */       
/* 212 */       float clipWidth = width - ab.width * 4.0F;
/*     */       
/* 214 */       float clipHeight = (rotation == 90 || rotation == 270) ? (borderBox.getWidth() - ab.width * 4.0F) : (borderBox.getHeight() - ab.width * 4.0F);
/* 215 */       float fontSize = extractFontSize(annotation);
/*     */ 
/*     */ 
/*     */       
/* 219 */       float yDelta = 0.7896F;
/* 220 */       switch (rotation) {
/*     */         
/*     */         case 180:
/* 223 */           xOffset = -borderBox.getUpperRightX() + ab.width * 2.0F;
/* 224 */           yOffset = -borderBox.getLowerLeftY() - ab.width * 2.0F - yDelta * fontSize;
/* 225 */           clipY = -borderBox.getUpperRightY() + ab.width * 2.0F;
/*     */           break;
/*     */         case 90:
/* 228 */           xOffset = borderBox.getLowerLeftY() + ab.width * 2.0F;
/* 229 */           yOffset = -borderBox.getLowerLeftX() - ab.width * 2.0F - yDelta * fontSize;
/* 230 */           clipY = -borderBox.getUpperRightX() + ab.width * 2.0F;
/*     */           break;
/*     */         case 270:
/* 233 */           xOffset = -borderBox.getUpperRightY() + ab.width * 2.0F;
/* 234 */           yOffset = borderBox.getUpperRightX() - ab.width * 2.0F - yDelta * fontSize;
/* 235 */           clipY = borderBox.getLowerLeftX() + ab.width * 2.0F;
/*     */           break;
/*     */         
/*     */         default:
/* 239 */           xOffset = borderBox.getLowerLeftX() + ab.width * 2.0F;
/* 240 */           yOffset = borderBox.getUpperRightY() - ab.width * 2.0F - yDelta * fontSize;
/* 241 */           clipY = borderBox.getLowerLeftY() + ab.width * 2.0F;
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 246 */       cs.addRect(xOffset, clipY, clipWidth, clipHeight);
/* 247 */       cs.clip();
/*     */       
/* 249 */       cs.beginText();
/* 250 */       cs.setFont((PDFont)pDType1Font, fontSize);
/* 251 */       cs.setNonStrokingColor(strokingColor.getComponents());
/* 252 */       AppearanceStyle appearanceStyle = new AppearanceStyle();
/* 253 */       appearanceStyle.setFont((PDFont)pDType1Font);
/* 254 */       appearanceStyle.setFontSize(fontSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 263 */       PlainTextFormatter formatter = (new PlainTextFormatter.Builder(cs)).style(appearanceStyle).text(new PlainText(annotation.getContents())).width(width - ab.width * 4.0F).wrapLines(true).initialOffset(xOffset, yOffset).build();
/* 264 */       formatter.format();
/* 265 */       cs.endText();
/*     */ 
/*     */       
/* 268 */       if (pathsArray.length > 0)
/*     */       {
/* 270 */         PDRectangle rect = getRectangle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 277 */         float minX = Float.MAX_VALUE;
/* 278 */         float minY = Float.MAX_VALUE;
/* 279 */         float maxX = Float.MIN_VALUE;
/* 280 */         float maxY = Float.MIN_VALUE;
/* 281 */         for (int j = 0; j < pathsArray.length / 2; j++) {
/*     */           
/* 283 */           float x = pathsArray[j * 2];
/* 284 */           float y = pathsArray[j * 2 + 1];
/* 285 */           minX = Math.min(minX, x);
/* 286 */           minY = Math.min(minY, y);
/* 287 */           maxX = Math.max(maxX, x);
/* 288 */           maxY = Math.max(maxY, y);
/*     */         } 
/*     */         
/* 291 */         rect.setLowerLeftX(Math.min(minX - ab.width * 10.0F, rect.getLowerLeftX()));
/* 292 */         rect.setLowerLeftY(Math.min(minY - ab.width * 10.0F, rect.getLowerLeftY()));
/* 293 */         rect.setUpperRightX(Math.max(maxX + ab.width * 10.0F, rect.getUpperRightX()));
/* 294 */         rect.setUpperRightY(Math.max(maxY + ab.width * 10.0F, rect.getUpperRightY()));
/* 295 */         annotation.setRectangle(rect);
/*     */ 
/*     */         
/* 298 */         annotation.getNormalAppearanceStream().setBBox(getRectangle());
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 303 */     catch (IOException ex) {
/*     */       
/* 305 */       LOG.error(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 309 */       IOUtils.closeQuietly((Closeable)cs);
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
/*     */   private PDColor extractNonStrokingColor(PDAnnotationMarkup annotation) {
/* 321 */     PDColor strokingColor = new PDColor(new float[] { 0.0F }, (PDColorSpace)PDDeviceGray.INSTANCE);
/* 322 */     String defaultAppearance = annotation.getDefaultAppearance();
/* 323 */     if (defaultAppearance == null)
/*     */     {
/* 325 */       return strokingColor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 331 */       PDFStreamParser parser = new PDFStreamParser(defaultAppearance.getBytes(Charsets.US_ASCII));
/* 332 */       COSArray arguments = new COSArray();
/* 333 */       COSArray colors = null;
/* 334 */       Operator graphicOp = null;
/* 335 */       for (Object token = parser.parseNextToken(); token != null; token = parser.parseNextToken()) {
/*     */         
/* 337 */         if (token instanceof COSObject) {
/*     */           
/* 339 */           arguments.add(((COSObject)token).getObject());
/*     */         }
/* 341 */         else if (token instanceof Operator) {
/*     */           
/* 343 */           Operator op = (Operator)token;
/* 344 */           String name = op.getName();
/* 345 */           if ("g".equals(name) || "rg".equals(name) || "k".equals(name)) {
/*     */             
/* 347 */             graphicOp = op;
/* 348 */             colors = arguments;
/*     */           } 
/* 350 */           arguments = new COSArray();
/*     */         }
/*     */         else {
/*     */           
/* 354 */           arguments.add((COSBase)token);
/*     */         } 
/*     */       } 
/* 357 */       if (graphicOp != null) {
/*     */         
/* 359 */         String graphicOpName = graphicOp.getName();
/* 360 */         if ("g".equals(graphicOpName))
/*     */         {
/* 362 */           strokingColor = new PDColor(colors, (PDColorSpace)PDDeviceGray.INSTANCE);
/*     */         }
/* 364 */         else if ("rg".equals(graphicOpName))
/*     */         {
/* 366 */           strokingColor = new PDColor(colors, (PDColorSpace)PDDeviceRGB.INSTANCE);
/*     */         }
/* 368 */         else if ("k".equals(graphicOpName))
/*     */         {
/* 370 */           strokingColor = new PDColor(colors, (PDColorSpace)PDDeviceCMYK.INSTANCE);
/*     */         }
/*     */       
/*     */       } 
/* 374 */     } catch (IOException ex) {
/*     */       
/* 376 */       LOG.warn("Problem parsing /DA, will use default black", ex);
/*     */     } 
/* 378 */     return strokingColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float extractFontSize(PDAnnotationMarkup annotation) {
/* 386 */     String defaultAppearance = annotation.getDefaultAppearance();
/* 387 */     if (defaultAppearance == null)
/*     */     {
/* 389 */       return 10.0F;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 395 */       PDFStreamParser parser = new PDFStreamParser(defaultAppearance.getBytes(Charsets.US_ASCII));
/* 396 */       COSArray arguments = new COSArray();
/* 397 */       COSArray fontArguments = new COSArray();
/* 398 */       for (Object token = parser.parseNextToken(); token != null; token = parser.parseNextToken()) {
/*     */         
/* 400 */         if (token instanceof COSObject) {
/*     */           
/* 402 */           arguments.add(((COSObject)token).getObject());
/*     */         }
/* 404 */         else if (token instanceof Operator) {
/*     */           
/* 406 */           Operator op = (Operator)token;
/* 407 */           String name = op.getName();
/* 408 */           if ("Tf".equals(name))
/*     */           {
/* 410 */             fontArguments = arguments;
/*     */           }
/* 412 */           arguments = new COSArray();
/*     */         }
/*     */         else {
/*     */           
/* 416 */           arguments.add((COSBase)token);
/*     */         } 
/*     */       } 
/* 419 */       if (fontArguments.size() >= 2)
/*     */       {
/* 421 */         COSBase base = fontArguments.get(1);
/* 422 */         if (base instanceof COSNumber)
/*     */         {
/* 424 */           return ((COSNumber)base).floatValue();
/*     */         }
/*     */       }
/*     */     
/* 428 */     } catch (IOException ex) {
/*     */       
/* 430 */       LOG.warn("Problem parsing /DA, will use default 10", ex);
/*     */     } 
/* 432 */     return 10.0F;
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDFreeTextAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */