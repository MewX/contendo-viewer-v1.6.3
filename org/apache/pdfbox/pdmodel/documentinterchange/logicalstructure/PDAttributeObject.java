/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDExportFormatAttributeObject;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDPrintFieldAttributeObject;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDTableAttributeObject;
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
/*     */ public abstract class PDAttributeObject
/*     */   extends PDDictionaryWrapper
/*     */ {
/*     */   private PDStructureElement structureElement;
/*     */   
/*     */   public static PDAttributeObject create(COSDictionary dictionary) {
/*  46 */     String owner = dictionary.getNameAsString(COSName.O);
/*  47 */     if ("UserProperties".equals(owner))
/*     */     {
/*  49 */       return new PDUserAttributeObject(dictionary);
/*     */     }
/*  51 */     if ("List".equals(owner))
/*     */     {
/*  53 */       return (PDAttributeObject)new PDListAttributeObject(dictionary);
/*     */     }
/*  55 */     if ("PrintField".equals(owner))
/*     */     {
/*  57 */       return (PDAttributeObject)new PDPrintFieldAttributeObject(dictionary);
/*     */     }
/*  59 */     if ("Table".equals(owner))
/*     */     {
/*  61 */       return (PDAttributeObject)new PDTableAttributeObject(dictionary);
/*     */     }
/*  63 */     if ("Layout".equals(owner))
/*     */     {
/*  65 */       return (PDAttributeObject)new PDLayoutAttributeObject(dictionary);
/*     */     }
/*  67 */     if ("XML-1.00".equals(owner) || "HTML-3.2"
/*  68 */       .equals(owner) || "HTML-4.01"
/*  69 */       .equals(owner) || "OEB-1.00"
/*  70 */       .equals(owner) || "RTF-1.05"
/*  71 */       .equals(owner) || "CSS-1.00"
/*  72 */       .equals(owner) || "CSS-2.00"
/*  73 */       .equals(owner))
/*     */     {
/*  75 */       return (PDAttributeObject)new PDExportFormatAttributeObject(dictionary);
/*     */     }
/*  77 */     return new PDDefaultAttributeObject(dictionary);
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
/*     */   private PDStructureElement getStructureElement() {
/*  89 */     return this.structureElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setStructureElement(PDStructureElement structureElement) {
/*  99 */     this.structureElement = structureElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAttributeObject() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAttributeObject(COSDictionary dictionary) {
/* 117 */     super(dictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOwner() {
/* 128 */     return getCOSObject().getNameAsString(COSName.O);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setOwner(String owner) {
/* 138 */     getCOSObject().setName(COSName.O, owner);
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
/*     */   public boolean isEmpty() {
/* 150 */     return (getCOSObject().size() == 1 && getOwner() != null);
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
/*     */   protected void potentiallyNotifyChanged(COSBase oldBase, COSBase newBase) {
/* 162 */     if (isValueChanged(oldBase, newBase))
/*     */     {
/* 164 */       notifyChanged();
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
/*     */   private boolean isValueChanged(COSBase oldValue, COSBase newValue) {
/* 178 */     if (oldValue == null)
/*     */     {
/* 180 */       return (newValue != null);
/*     */     }
/* 182 */     return !oldValue.equals(newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void notifyChanged() {
/* 191 */     if (getStructureElement() != null)
/*     */     {
/* 193 */       getStructureElement().attributeChanged(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 200 */     return "O=" + getOwner();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String arrayToString(Object[] array) {
/* 211 */     StringBuilder sb = new StringBuilder("[");
/* 212 */     for (int i = 0; i < array.length; i++) {
/*     */       
/* 214 */       if (i > 0)
/*     */       {
/* 216 */         sb.append(", ");
/*     */       }
/* 218 */       sb.append(array[i]);
/*     */     } 
/* 220 */     return sb.append(']').toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String arrayToString(float[] array) {
/* 231 */     StringBuilder sb = new StringBuilder("[");
/* 232 */     for (int i = 0; i < array.length; i++) {
/*     */       
/* 234 */       if (i > 0)
/*     */       {
/* 236 */         sb.append(", ");
/*     */       }
/* 238 */       sb.append(array[i]);
/*     */     } 
/* 240 */     return sb.append(']').toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */