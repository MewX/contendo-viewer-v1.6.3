/*     */ package org.apache.pdfbox.pdmodel.graphics.state;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.function.PDFunction;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDSoftMask
/*     */   implements COSObjectable
/*     */ {
/*     */   public static PDSoftMask create(COSBase dictionary) {
/*  47 */     if (dictionary instanceof COSName) {
/*     */       
/*  49 */       if (COSName.NONE.equals(dictionary))
/*     */       {
/*  51 */         return null;
/*     */       }
/*     */ 
/*     */       
/*  55 */       LOG.warn("Invalid SMask " + dictionary);
/*  56 */       return null;
/*     */     } 
/*     */     
/*  59 */     if (dictionary instanceof COSDictionary)
/*     */     {
/*  61 */       return new PDSoftMask((COSDictionary)dictionary);
/*     */     }
/*     */ 
/*     */     
/*  65 */     LOG.warn("Invalid SMask " + dictionary);
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */   
/*  70 */   private static final Log LOG = LogFactory.getLog(PDSoftMask.class);
/*     */   
/*     */   private final COSDictionary dictionary;
/*  73 */   private COSName subType = null;
/*  74 */   private PDTransparencyGroup group = null;
/*  75 */   private COSArray backdropColor = null;
/*  76 */   private PDFunction transferFunction = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Matrix ctm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSoftMask(COSDictionary dictionary) {
/*  90 */     this.dictionary = dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  96 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getSubType() {
/* 104 */     if (this.subType == null)
/*     */     {
/* 106 */       this.subType = (COSName)getCOSObject().getDictionaryObject(COSName.S);
/*     */     }
/* 108 */     return this.subType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTransparencyGroup getGroup() throws IOException {
/* 119 */     if (this.group == null) {
/*     */       
/* 121 */       COSBase cosGroup = getCOSObject().getDictionaryObject(COSName.G);
/* 122 */       if (cosGroup != null) {
/*     */         
/* 124 */         PDXObject x = PDXObject.createXObject(cosGroup, null);
/* 125 */         if (x instanceof PDTransparencyGroup)
/*     */         {
/* 127 */           this.group = (PDTransparencyGroup)x;
/*     */         }
/*     */       } 
/*     */     } 
/* 131 */     return this.group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getBackdropColor() {
/* 139 */     if (this.backdropColor == null)
/*     */     {
/* 141 */       this.backdropColor = (COSArray)getCOSObject().getDictionaryObject(COSName.BC);
/*     */     }
/* 143 */     return this.backdropColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFunction getTransferFunction() throws IOException {
/* 152 */     if (this.transferFunction == null) {
/*     */       
/* 154 */       COSBase cosTF = getCOSObject().getDictionaryObject(COSName.TR);
/* 155 */       if (cosTF != null)
/*     */       {
/* 157 */         this.transferFunction = PDFunction.create(cosTF);
/*     */       }
/*     */     } 
/* 160 */     return this.transferFunction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setInitialTransformationMatrix(Matrix ctm) {
/* 170 */     this.ctm = ctm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getInitialTransformationMatrix() {
/* 180 */     return this.ctm;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/state/PDSoftMask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */