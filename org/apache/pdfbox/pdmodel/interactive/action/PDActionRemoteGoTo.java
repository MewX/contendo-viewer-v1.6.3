/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
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
/*     */ public class PDActionRemoteGoTo
/*     */   extends PDAction
/*     */ {
/*     */   public static final String SUB_TYPE = "GoToR";
/*     */   
/*     */   public PDActionRemoteGoTo() {
/*  46 */     setSubType("GoToR");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDActionRemoteGoTo(COSDictionary a) {
/*  56 */     super(a);
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
/*  69 */     return this.action.getNameAsString(COSName.S);
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
/*  82 */     this.action.setName(COSName.S, s);
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
/*     */   public PDFileSpecification getFile() throws IOException {
/*  94 */     return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFile(PDFileSpecification fs) {
/* 104 */     this.action.setItem(COSName.F, (COSObjectable)fs);
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
/*     */   public COSBase getD() {
/* 120 */     return this.action.getDictionaryObject(COSName.D);
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
/*     */   public void setD(COSBase d) {
/* 136 */     this.action.setItem(COSName.D, d);
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
/*     */   @Deprecated
/*     */   public boolean shouldOpenInNewWindow() {
/* 152 */     return this.action.getBoolean(COSName.NEW_WINDOW, true);
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
/*     */   public void setOpenInNewWindow(boolean value) {
/* 165 */     this.action.setBoolean(COSName.NEW_WINDOW, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OpenMode getOpenInNewWindow() {
/* 176 */     if (getCOSObject().getDictionaryObject(COSName.NEW_WINDOW) instanceof COSBoolean) {
/*     */       
/* 178 */       COSBoolean b = (COSBoolean)getCOSObject().getDictionaryObject(COSName.NEW_WINDOW);
/* 179 */       return b.getValue() ? OpenMode.NEW_WINDOW : OpenMode.SAME_WINDOW;
/*     */     } 
/* 181 */     return OpenMode.USER_PREFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpenInNewWindow(OpenMode value) {
/* 191 */     if (null == value) {
/*     */       
/* 193 */       getCOSObject().removeItem(COSName.NEW_WINDOW);
/*     */       return;
/*     */     } 
/* 196 */     switch (value) {
/*     */       
/*     */       case USER_PREFERENCE:
/* 199 */         getCOSObject().removeItem(COSName.NEW_WINDOW);
/*     */         break;
/*     */       case SAME_WINDOW:
/* 202 */         getCOSObject().setBoolean(COSName.NEW_WINDOW, false);
/*     */         break;
/*     */       case NEW_WINDOW:
/* 205 */         getCOSObject().setBoolean(COSName.NEW_WINDOW, true);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionRemoteGoTo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */