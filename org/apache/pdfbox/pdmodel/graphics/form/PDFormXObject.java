/*     */ package org.apache.pdfbox.pdmodel.graphics.form;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.pdfbox.contentstream.PDContentStream;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.ResourceCache;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFormXObject
/*     */   extends PDXObject
/*     */   implements PDContentStream
/*     */ {
/*     */   private PDTransparencyGroupAttributes group;
/*     */   private final ResourceCache cache;
/*     */   
/*     */   public PDFormXObject(PDStream stream) {
/*  66 */     super(stream, COSName.FORM);
/*  67 */     this.cache = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormXObject(COSStream stream) {
/*  76 */     super(stream, COSName.FORM);
/*  77 */     this.cache = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormXObject(COSStream stream, ResourceCache cache) {
/*  86 */     super(stream, COSName.FORM);
/*  87 */     this.cache = cache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormXObject(PDDocument document) {
/*  96 */     super(document, COSName.FORM);
/*  97 */     this.cache = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFormType() {
/* 106 */     return getCOSObject().getInt(COSName.FORMTYPE, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormType(int formType) {
/* 115 */     getCOSObject().setInt(COSName.FORMTYPE, formType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTransparencyGroupAttributes getGroup() {
/* 125 */     if (this.group == null) {
/*     */       
/* 127 */       COSDictionary dic = (COSDictionary)getCOSObject().getDictionaryObject(COSName.GROUP);
/* 128 */       if (dic != null)
/*     */       {
/* 130 */         this.group = new PDTransparencyGroupAttributes(dic);
/*     */       }
/*     */     } 
/* 133 */     return this.group;
/*     */   }
/*     */ 
/*     */   
/*     */   public PDStream getContentStream() {
/* 138 */     return new PDStream(getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getContents() throws IOException {
/* 144 */     return (InputStream)getCOSObject().createInputStream();
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
/*     */   public PDResources getResources() {
/* 156 */     COSDictionary resources = getCOSObject().getCOSDictionary(COSName.RESOURCES);
/* 157 */     if (resources != null)
/*     */     {
/* 159 */       return new PDResources(resources, this.cache);
/*     */     }
/* 161 */     if (getCOSObject().containsKey(COSName.RESOURCES))
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 166 */       return new PDResources();
/*     */     }
/* 168 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResources(PDResources resources) {
/* 177 */     getCOSObject().setItem(COSName.RESOURCES, (COSObjectable)resources);
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
/*     */   public PDRectangle getBBox() {
/* 190 */     PDRectangle retval = null;
/* 191 */     COSArray array = (COSArray)getCOSObject().getDictionaryObject(COSName.BBOX);
/* 192 */     if (array != null)
/*     */     {
/* 194 */       retval = new PDRectangle(array);
/*     */     }
/* 196 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBBox(PDRectangle bbox) {
/* 205 */     if (bbox == null) {
/*     */       
/* 207 */       getCOSObject().removeItem(COSName.BBOX);
/*     */     }
/*     */     else {
/*     */       
/* 211 */       getCOSObject().setItem(COSName.BBOX, (COSBase)bbox.getCOSArray());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getMatrix() {
/* 222 */     return Matrix.createMatrix(getCOSObject().getDictionaryObject(COSName.MATRIX));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMatrix(AffineTransform transform) {
/* 231 */     COSArray matrix = new COSArray();
/* 232 */     double[] values = new double[6];
/* 233 */     transform.getMatrix(values);
/* 234 */     for (double v : values)
/*     */     {
/* 236 */       matrix.add((COSBase)new COSFloat((float)v));
/*     */     }
/* 238 */     getCOSObject().setItem(COSName.MATRIX, (COSBase)matrix);
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
/*     */   public int getStructParents() {
/* 250 */     return getCOSObject().getInt(COSName.STRUCT_PARENTS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStructParents(int structParent) {
/* 259 */     getCOSObject().setInt(COSName.STRUCT_PARENTS, structParent);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/form/PDFormXObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */