/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
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
/*     */ public class PDTargetDirectory
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dict;
/*     */   
/*     */   public PDTargetDirectory() {
/*  43 */     this.dict = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTargetDirectory(COSDictionary dictionary) {
/*  53 */     this.dict = dictionary;
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
/*  64 */     return this.dict;
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
/*     */   public COSName getRelationship() {
/*  77 */     COSBase base = this.dict.getItem(COSName.R);
/*  78 */     if (base instanceof COSName)
/*     */     {
/*  80 */       return (COSName)base;
/*     */     }
/*  82 */     return null;
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
/*     */   public void setRelationship(COSName relationship) {
/*  96 */     if (!COSName.P.equals(relationship) && !COSName.C.equals(relationship))
/*     */     {
/*  98 */       throw new IllegalArgumentException("The only valid are P or C, not " + relationship.getName());
/*     */     }
/* 100 */     this.dict.setItem(COSName.R, (COSBase)relationship);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFilename() {
/* 111 */     return this.dict.getString(COSName.N);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilename(String filename) {
/* 122 */     this.dict.setString(COSName.N, filename);
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
/*     */   public PDTargetDirectory getTargetDirectory() {
/* 134 */     COSBase base = this.dict.getDictionaryObject(COSName.T);
/* 135 */     if (base instanceof COSDictionary)
/*     */     {
/* 137 */       return new PDTargetDirectory((COSDictionary)base);
/*     */     }
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetDirectory(PDTargetDirectory targetDirectory) {
/* 150 */     this.dict.setItem(COSName.T, targetDirectory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPageNumber() {
/* 161 */     COSBase base = this.dict.getDictionaryObject(COSName.P);
/* 162 */     if (base instanceof COSInteger)
/*     */     {
/* 164 */       return ((COSInteger)base).intValue();
/*     */     }
/* 166 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPageNumber(int pageNumber) {
/* 177 */     if (pageNumber < 0) {
/*     */       
/* 179 */       this.dict.removeItem(COSName.P);
/*     */     }
/*     */     else {
/*     */       
/* 183 */       this.dict.setInt(COSName.P, pageNumber);
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
/*     */   public PDNamedDestination getNamedDestination() {
/* 195 */     COSBase base = this.dict.getDictionaryObject(COSName.P);
/* 196 */     if (base instanceof COSString)
/*     */     {
/* 198 */       return new PDNamedDestination((COSString)base);
/*     */     }
/* 200 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNamedDestination(PDNamedDestination dest) {
/* 211 */     if (dest == null) {
/*     */       
/* 213 */       this.dict.removeItem(COSName.P);
/*     */     }
/*     */     else {
/*     */       
/* 217 */       this.dict.setItem(COSName.P, (COSObjectable)dest);
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
/*     */   public int getAnnotationIndex() {
/* 229 */     COSBase base = this.dict.getDictionaryObject(COSName.A);
/* 230 */     if (base instanceof COSInteger)
/*     */     {
/* 232 */       return ((COSInteger)base).intValue();
/*     */     }
/* 234 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnnotationIndex(int index) {
/* 245 */     if (index < 0) {
/*     */       
/* 247 */       this.dict.removeItem(COSName.A);
/*     */     }
/*     */     else {
/*     */       
/* 251 */       this.dict.setInt(COSName.A, index);
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
/*     */   public String getAnnotationName() {
/* 264 */     COSBase base = this.dict.getDictionaryObject(COSName.A);
/* 265 */     if (base instanceof COSString)
/*     */     {
/* 267 */       return ((COSString)base).getString();
/*     */     }
/* 269 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnnotationName(String name) {
/* 279 */     this.dict.setString(COSName.A, name);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDTargetDirectory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */