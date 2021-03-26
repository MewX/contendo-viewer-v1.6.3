/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.fdf.FDFField;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
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
/*     */ public class PDNonTerminalField
/*     */   extends PDField
/*     */ {
/*  47 */   private static final Log LOG = LogFactory.getLog(PDNonTerminalField.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNonTerminalField(PDAcroForm acroForm) {
/*  56 */     super(acroForm);
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
/*     */   PDNonTerminalField(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  68 */     super(acroForm, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFieldFlags() {
/*  74 */     int retval = 0;
/*  75 */     COSInteger ff = (COSInteger)getCOSObject().getDictionaryObject(COSName.FF);
/*  76 */     if (ff != null)
/*     */     {
/*  78 */       retval = ff.intValue();
/*     */     }
/*     */     
/*  81 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void importFDF(FDFField fdfField) throws IOException {
/*  87 */     super.importFDF(fdfField);
/*     */     
/*  89 */     List<FDFField> fdfKids = fdfField.getKids();
/*  90 */     List<PDField> children = getChildren();
/*  91 */     for (int i = 0; fdfKids != null && i < fdfKids.size(); i++) {
/*     */       
/*  93 */       for (COSObjectable pdKid : children) {
/*     */         
/*  95 */         if (pdKid instanceof PDField) {
/*     */           
/*  97 */           PDField pdChild = (PDField)pdKid;
/*  98 */           FDFField fdfChild = fdfKids.get(i);
/*  99 */           String fdfName = fdfChild.getPartialFieldName();
/* 100 */           if (fdfName != null && fdfName.equals(pdChild.getPartialName()))
/*     */           {
/* 102 */             pdChild.importFDF(fdfChild);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   FDFField exportFDF() throws IOException {
/* 112 */     FDFField fdfField = new FDFField();
/* 113 */     fdfField.setPartialFieldName(getPartialName());
/* 114 */     fdfField.setValue(getValue());
/*     */     
/* 116 */     List<PDField> children = getChildren();
/* 117 */     List<FDFField> fdfChildren = new ArrayList<FDFField>();
/* 118 */     for (PDField child : children)
/*     */     {
/* 120 */       fdfChildren.add(child.exportFDF());
/*     */     }
/* 122 */     fdfField.setKids(fdfChildren);
/*     */     
/* 124 */     return fdfField;
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
/*     */   public List<PDField> getChildren() {
/* 136 */     List<PDField> children = new ArrayList<PDField>();
/* 137 */     COSArray kids = (COSArray)getCOSObject().getDictionaryObject(COSName.KIDS);
/* 138 */     for (int i = 0; i < kids.size(); i++) {
/*     */       
/* 140 */       COSBase kid = kids.getObject(i);
/* 141 */       if (kid instanceof COSDictionary)
/*     */       {
/* 143 */         if (kid.getCOSObject() == getCOSObject()) {
/*     */           
/* 145 */           LOG.warn("Child field is same object as parent");
/*     */         } else {
/*     */           
/* 148 */           PDField field = PDField.fromDictionary(getAcroForm(), (COSDictionary)kid, this);
/* 149 */           if (field != null)
/*     */           {
/* 151 */             children.add(field); } 
/*     */         } 
/*     */       }
/*     */     } 
/* 155 */     return children;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChildren(List<PDField> children) {
/* 165 */     COSArray kidsArray = COSArrayList.converterToCOSArray(children);
/* 166 */     getCOSObject().setItem(COSName.KIDS, (COSBase)kidsArray);
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
/*     */   public String getFieldType() {
/* 178 */     return getCOSObject().getNameAsString(COSName.FT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getValue() {
/* 189 */     return getCOSObject().getDictionaryObject(COSName.V);
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
/*     */   public String getValueAsString() {
/* 201 */     COSBase fieldValue = getCOSObject().getDictionaryObject(COSName.V);
/* 202 */     return (fieldValue != null) ? fieldValue.toString() : "";
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
/*     */   public void setValue(COSBase object) throws IOException {
/* 216 */     getCOSObject().setItem(COSName.V, object);
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
/*     */   public void setValue(String value) throws IOException {
/* 230 */     getCOSObject().setString(COSName.V, value);
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
/*     */   public COSBase getDefaultValue() {
/* 244 */     return getCOSObject().getDictionaryObject(COSName.DV);
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
/*     */   public void setDefaultValue(COSBase value) {
/* 257 */     getCOSObject().setItem(COSName.V, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDAnnotationWidget> getWidgets() {
/* 263 */     List<PDAnnotationWidget> emptyList = Collections.emptyList();
/* 264 */     return Collections.unmodifiableList(emptyList);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDNonTerminalField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */