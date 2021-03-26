/*     */ package org.apache.pdfbox.pdmodel.graphics.state;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDFontSetting;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
/*     */ import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
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
/*     */ public class PDExtendedGraphicsState
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dict;
/*     */   
/*     */   public PDExtendedGraphicsState() {
/*  46 */     this.dict = new COSDictionary();
/*  47 */     this.dict.setItem(COSName.TYPE, (COSBase)COSName.EXT_G_STATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDExtendedGraphicsState(COSDictionary dictionary) {
/*  57 */     this.dict = dictionary;
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
/*     */   public void copyIntoGraphicsState(PDGraphicsState gs) throws IOException {
/*  69 */     for (COSName key : this.dict.keySet()) {
/*     */       
/*  71 */       if (key.equals(COSName.LW)) {
/*     */         
/*  73 */         gs.setLineWidth(defaultIfNull(getLineWidth(), 1.0F)); continue;
/*     */       } 
/*  75 */       if (key.equals(COSName.LC)) {
/*     */         
/*  77 */         gs.setLineCap(getLineCapStyle()); continue;
/*     */       } 
/*  79 */       if (key.equals(COSName.LJ)) {
/*     */         
/*  81 */         gs.setLineJoin(getLineJoinStyle()); continue;
/*     */       } 
/*  83 */       if (key.equals(COSName.ML)) {
/*     */         
/*  85 */         gs.setMiterLimit(defaultIfNull(getMiterLimit(), 10.0F)); continue;
/*     */       } 
/*  87 */       if (key.equals(COSName.D)) {
/*     */         
/*  89 */         gs.setLineDashPattern(getLineDashPattern()); continue;
/*     */       } 
/*  91 */       if (key.equals(COSName.RI)) {
/*     */         
/*  93 */         gs.setRenderingIntent(getRenderingIntent()); continue;
/*     */       } 
/*  95 */       if (key.equals(COSName.OPM)) {
/*     */         
/*  97 */         gs.setOverprintMode(defaultIfNull(getOverprintMode(), 0.0F)); continue;
/*     */       } 
/*  99 */       if (key.equals(COSName.OP)) {
/*     */         
/* 101 */         gs.setOverprint(getStrokingOverprintControl()); continue;
/*     */       } 
/* 103 */       if (key.equals(COSName.OP_NS)) {
/*     */         
/* 105 */         gs.setNonStrokingOverprint(getNonStrokingOverprintControl()); continue;
/*     */       } 
/* 107 */       if (key.equals(COSName.FONT)) {
/*     */         
/* 109 */         PDFontSetting setting = getFontSetting();
/* 110 */         if (setting != null) {
/*     */           
/* 112 */           gs.getTextState().setFont(setting.getFont());
/* 113 */           gs.getTextState().setFontSize(setting.getFontSize());
/*     */         }  continue;
/*     */       } 
/* 116 */       if (key.equals(COSName.FL)) {
/*     */         
/* 118 */         gs.setFlatness(defaultIfNull(getFlatnessTolerance(), 1.0F)); continue;
/*     */       } 
/* 120 */       if (key.equals(COSName.SM)) {
/*     */         
/* 122 */         gs.setSmoothness(defaultIfNull(getSmoothnessTolerance(), 0.0F)); continue;
/*     */       } 
/* 124 */       if (key.equals(COSName.SA)) {
/*     */         
/* 126 */         gs.setStrokeAdjustment(getAutomaticStrokeAdjustment()); continue;
/*     */       } 
/* 128 */       if (key.equals(COSName.CA)) {
/*     */         
/* 130 */         gs.setAlphaConstant(defaultIfNull(getStrokingAlphaConstant(), 1.0F)); continue;
/*     */       } 
/* 132 */       if (key.equals(COSName.CA_NS)) {
/*     */         
/* 134 */         gs.setNonStrokeAlphaConstant(defaultIfNull(getNonStrokingAlphaConstant(), 1.0F)); continue;
/*     */       } 
/* 136 */       if (key.equals(COSName.AIS)) {
/*     */         
/* 138 */         gs.setAlphaSource(getAlphaSourceFlag()); continue;
/*     */       } 
/* 140 */       if (key.equals(COSName.TK)) {
/*     */         
/* 142 */         gs.getTextState().setKnockoutFlag(getTextKnockoutFlag()); continue;
/*     */       } 
/* 144 */       if (key.equals(COSName.SMASK)) {
/*     */         
/* 146 */         PDSoftMask softmask = getSoftMask();
/* 147 */         if (softmask != null)
/*     */         {
/*     */ 
/*     */           
/* 151 */           softmask.setInitialTransformationMatrix(gs.getCurrentTransformationMatrix().clone());
/*     */         }
/* 153 */         gs.setSoftMask(softmask); continue;
/*     */       } 
/* 155 */       if (key.equals(COSName.BM)) {
/*     */         
/* 157 */         gs.setBlendMode(getBlendMode()); continue;
/*     */       } 
/* 159 */       if (key.equals(COSName.TR)) {
/*     */         
/* 161 */         if (this.dict.containsKey(COSName.TR2)) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 167 */         gs.setTransfer(getTransfer()); continue;
/*     */       } 
/* 169 */       if (key.equals(COSName.TR2))
/*     */       {
/* 171 */         gs.setTransfer(getTransfer2());
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
/*     */   private float defaultIfNull(Float _value, float _default) {
/* 189 */     return (_value != null) ? _value.floatValue() : _default;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 200 */     return this.dict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getLineWidth() {
/* 210 */     return getFloatItem(COSName.LW);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineWidth(Float width) {
/* 220 */     setFloatItem(COSName.LW, width);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineCapStyle() {
/* 230 */     return this.dict.getInt(COSName.LC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineCapStyle(int style) {
/* 240 */     this.dict.setInt(COSName.LC, style);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineJoinStyle() {
/* 250 */     return this.dict.getInt(COSName.LJ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineJoinStyle(int style) {
/* 260 */     this.dict.setInt(COSName.LJ, style);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getMiterLimit() {
/* 271 */     return getFloatItem(COSName.ML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMiterLimit(Float miterLimit) {
/* 281 */     setFloatItem(COSName.ML, miterLimit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDLineDashPattern getLineDashPattern() {
/* 291 */     PDLineDashPattern retval = null;
/* 292 */     COSBase dp = this.dict.getDictionaryObject(COSName.D);
/* 293 */     if (dp instanceof COSArray && ((COSArray)dp).size() == 2) {
/*     */       
/* 295 */       COSBase dashArray = ((COSArray)dp).getObject(0);
/* 296 */       COSBase phase = ((COSArray)dp).getObject(1);
/* 297 */       if (dashArray instanceof COSArray && phase instanceof COSNumber)
/*     */       {
/* 299 */         retval = new PDLineDashPattern((COSArray)dashArray, ((COSNumber)phase).intValue());
/*     */       }
/*     */     } 
/* 302 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineDashPattern(PDLineDashPattern dashPattern) {
/* 312 */     this.dict.setItem(COSName.D, dashPattern.getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingIntent getRenderingIntent() {
/* 322 */     String ri = this.dict.getNameAsString("RI");
/* 323 */     if (ri != null)
/*     */     {
/* 325 */       return RenderingIntent.fromString(ri);
/*     */     }
/*     */ 
/*     */     
/* 329 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingIntent(String ri) {
/* 340 */     this.dict.setName("RI", ri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getStrokingOverprintControl() {
/* 350 */     return this.dict.getBoolean(COSName.OP, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrokingOverprintControl(boolean op) {
/* 360 */     this.dict.setBoolean(COSName.OP, op);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getNonStrokingOverprintControl() {
/* 371 */     return this.dict.getBoolean(COSName.OP_NS, getStrokingOverprintControl());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonStrokingOverprintControl(boolean op) {
/* 381 */     this.dict.setBoolean(COSName.OP_NS, op);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getOverprintMode() {
/* 391 */     return getFloatItem(COSName.OPM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOverprintMode(Float overprintMode) {
/* 401 */     setFloatItem(COSName.OPM, overprintMode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFontSetting getFontSetting() {
/* 411 */     PDFontSetting setting = null;
/* 412 */     COSBase base = this.dict.getDictionaryObject(COSName.FONT);
/* 413 */     if (base instanceof COSArray) {
/*     */       
/* 415 */       COSArray font = (COSArray)base;
/* 416 */       setting = new PDFontSetting(font);
/*     */     } 
/* 418 */     return setting;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontSetting(PDFontSetting fs) {
/* 428 */     this.dict.setItem(COSName.FONT, (COSObjectable)fs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getFlatnessTolerance() {
/* 438 */     return getFloatItem(COSName.FL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlatnessTolerance(Float flatness) {
/* 448 */     setFloatItem(COSName.FL, flatness);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getSmoothnessTolerance() {
/* 458 */     return getFloatItem(COSName.SM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSmoothnessTolerance(Float smoothness) {
/* 468 */     setFloatItem(COSName.SM, smoothness);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAutomaticStrokeAdjustment() {
/* 478 */     return this.dict.getBoolean(COSName.SA, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAutomaticStrokeAdjustment(boolean sa) {
/* 488 */     this.dict.setBoolean(COSName.SA, sa);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getStrokingAlphaConstant() {
/* 498 */     return getFloatItem(COSName.CA);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrokingAlphaConstant(Float alpha) {
/* 508 */     setFloatItem(COSName.CA, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getNonStrokingAlphaConstant() {
/* 518 */     return getFloatItem(COSName.CA_NS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonStrokingAlphaConstant(Float alpha) {
/* 528 */     setFloatItem(COSName.CA_NS, alpha);
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
/*     */   public boolean getAlphaSourceFlag() {
/* 540 */     return this.dict.getBoolean(COSName.AIS, false);
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
/*     */   public void setAlphaSourceFlag(boolean alpha) {
/* 552 */     this.dict.setBoolean(COSName.AIS, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlendMode getBlendMode() {
/* 562 */     return BlendMode.getInstance(this.dict.getDictionaryObject(COSName.BM));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlendMode(BlendMode bm) {
/* 572 */     this.dict.setItem(COSName.BM, (COSBase)BlendMode.getCOSName(bm));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSoftMask getSoftMask() {
/* 582 */     if (!this.dict.containsKey(COSName.SMASK))
/*     */     {
/* 584 */       return null;
/*     */     }
/* 586 */     return PDSoftMask.create(this.dict.getDictionaryObject(COSName.SMASK));
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
/*     */   public boolean getTextKnockoutFlag() {
/* 598 */     return this.dict.getBoolean(COSName.TK, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextKnockoutFlag(boolean tk) {
/* 608 */     this.dict.setBoolean(COSName.TK, tk);
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
/*     */   private Float getFloatItem(COSName key) {
/* 620 */     Float retval = null;
/* 621 */     COSBase base = this.dict.getDictionaryObject(key);
/* 622 */     if (base instanceof COSNumber) {
/*     */       
/* 624 */       COSNumber value = (COSNumber)base;
/* 625 */       retval = Float.valueOf(value.floatValue());
/*     */     } 
/* 627 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setFloatItem(COSName key, Float value) {
/* 638 */     if (value == null) {
/*     */       
/* 640 */       this.dict.removeItem(key);
/*     */     }
/*     */     else {
/*     */       
/* 644 */       this.dict.setItem(key, (COSBase)new COSFloat(value.floatValue()));
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
/*     */   public COSBase getTransfer() {
/* 658 */     COSBase base = this.dict.getDictionaryObject(COSName.TR);
/* 659 */     if (base instanceof COSArray && ((COSArray)base).size() != 4)
/*     */     {
/* 661 */       return null;
/*     */     }
/* 663 */     return base;
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
/*     */   public void setTransfer(COSBase transfer) {
/* 676 */     this.dict.setItem(COSName.TR, transfer);
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
/*     */   public COSBase getTransfer2() {
/* 690 */     COSBase base = this.dict.getDictionaryObject(COSName.TR2);
/* 691 */     if (base instanceof COSArray && ((COSArray)base).size() != 4)
/*     */     {
/* 693 */       return null;
/*     */     }
/* 695 */     return base;
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
/*     */   public void setTransfer2(COSBase transfer2) {
/* 709 */     this.dict.setItem(COSName.TR2, transfer2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/state/PDExtendedGraphicsState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */