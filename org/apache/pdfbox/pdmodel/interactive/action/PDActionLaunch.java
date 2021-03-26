/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ public class PDActionLaunch
/*     */   extends PDAction
/*     */ {
/*     */   public static final String SUB_TYPE = "Launch";
/*     */   
/*     */   public PDActionLaunch() {
/*  46 */     setSubType("Launch");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDActionLaunch(COSDictionary a) {
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFileSpecification getFile() throws IOException {
/*  72 */     return PDFileSpecification.createFS(getCOSObject().getDictionaryObject(COSName.F));
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
/*     */   public void setFile(PDFileSpecification fs) {
/*  86 */     getCOSObject().setItem(COSName.F, (COSObjectable)fs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDWindowsLaunchParams getWinLaunchParams() {
/*  96 */     COSDictionary win = (COSDictionary)this.action.getDictionaryObject("Win");
/*  97 */     PDWindowsLaunchParams retval = null;
/*  98 */     if (win != null)
/*     */     {
/* 100 */       retval = new PDWindowsLaunchParams(win);
/*     */     }
/* 102 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWinLaunchParams(PDWindowsLaunchParams win) {
/* 112 */     this.action.setItem("Win", win);
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
/*     */   public String getF() {
/* 125 */     return this.action.getString(COSName.F);
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
/*     */   public void setF(String f) {
/* 138 */     this.action.setString(COSName.F, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getD() {
/* 148 */     return this.action.getString(COSName.D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setD(String d) {
/* 158 */     this.action.setString(COSName.D, d);
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
/*     */   public String getO() {
/* 172 */     return this.action.getString(COSName.O);
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
/*     */   public void setO(String o) {
/* 186 */     this.action.setString(COSName.O, o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getP() {
/* 197 */     return this.action.getString(COSName.P);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP(String p) {
/* 208 */     this.action.setString(COSName.P, p);
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
/*     */   @Deprecated
/*     */   public boolean shouldOpenInNewWindow() {
/* 225 */     return this.action.getBoolean(COSName.NEW_WINDOW, true);
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
/* 238 */     this.action.setBoolean(COSName.NEW_WINDOW, value);
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
/* 249 */     if (getCOSObject().getDictionaryObject(COSName.NEW_WINDOW) instanceof COSBoolean) {
/*     */       
/* 251 */       COSBoolean b = (COSBoolean)getCOSObject().getDictionaryObject(COSName.NEW_WINDOW);
/* 252 */       return b.getValue() ? OpenMode.NEW_WINDOW : OpenMode.SAME_WINDOW;
/*     */     } 
/* 254 */     return OpenMode.USER_PREFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpenInNewWindow(OpenMode value) {
/* 264 */     if (null == value) {
/*     */       
/* 266 */       getCOSObject().removeItem(COSName.NEW_WINDOW);
/*     */       return;
/*     */     } 
/* 269 */     switch (value) {
/*     */       
/*     */       case USER_PREFERENCE:
/* 272 */         getCOSObject().removeItem(COSName.NEW_WINDOW);
/*     */         break;
/*     */       case SAME_WINDOW:
/* 275 */         getCOSObject().setBoolean(COSName.NEW_WINDOW, false);
/*     */         break;
/*     */       case NEW_WINDOW:
/* 278 */         getCOSObject().setBoolean(COSName.NEW_WINDOW, true);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionLaunch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */