/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.util.Charsets;
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
/*     */ public class PDActionURI
/*     */   extends PDAction
/*     */ {
/*     */   public static final String SUB_TYPE = "URI";
/*     */   
/*     */   public PDActionURI() {
/*  43 */     setSubType("URI");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDActionURI(COSDictionary a) {
/*  53 */     super(a);
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
/*     */   @Deprecated
/*     */   public String getS() {
/*  66 */     return this.action.getNameAsString(COSName.S);
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
/*     */   @Deprecated
/*     */   public void setS(String s) {
/*  79 */     this.action.setName(COSName.S, s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURI() {
/*  90 */     COSBase base = this.action.getDictionaryObject(COSName.URI);
/*  91 */     if (base instanceof COSString) {
/*     */       
/*  93 */       byte[] bytes = ((COSString)base).getBytes();
/*  94 */       if (bytes.length >= 2) {
/*     */ 
/*     */         
/*  97 */         if ((bytes[0] & 0xFF) == 254 && (bytes[1] & 0xFF) == 255)
/*     */         {
/*  99 */           return this.action.getString(COSName.URI);
/*     */         }
/*     */         
/* 102 */         if ((bytes[0] & 0xFF) == 255 && (bytes[1] & 0xFF) == 254)
/*     */         {
/* 104 */           return this.action.getString(COSName.URI);
/*     */         }
/*     */       } 
/* 107 */       return new String(bytes, Charsets.UTF_8);
/*     */     } 
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURI(String uri) {
/* 120 */     this.action.setString(COSName.URI, uri);
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
/*     */   public boolean shouldTrackMousePosition() {
/* 134 */     return this.action.getBoolean("IsMap", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrackMousePosition(boolean value) {
/* 145 */     this.action.setBoolean("IsMap", value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionURI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */