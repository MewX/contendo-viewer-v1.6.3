/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDActionFactory
/*     */ {
/*     */   public static PDAction createAction(COSDictionary action) {
/*  48 */     PDAction retval = null;
/*  49 */     if (action != null) {
/*     */       
/*  51 */       String type = action.getNameAsString(COSName.S);
/*  52 */       if ("JavaScript".equals(type)) {
/*     */         
/*  54 */         retval = new PDActionJavaScript(action);
/*     */       }
/*  56 */       else if ("GoTo".equals(type)) {
/*     */         
/*  58 */         retval = new PDActionGoTo(action);
/*     */       }
/*  60 */       else if ("Launch".equals(type)) {
/*     */         
/*  62 */         retval = new PDActionLaunch(action);
/*     */       }
/*  64 */       else if ("GoToR".equals(type)) {
/*     */         
/*  66 */         retval = new PDActionRemoteGoTo(action);
/*     */       }
/*  68 */       else if ("URI".equals(type)) {
/*     */         
/*  70 */         retval = new PDActionURI(action);
/*     */       }
/*  72 */       else if ("Named".equals(type)) {
/*     */         
/*  74 */         retval = new PDActionNamed(action);
/*     */       }
/*  76 */       else if ("Sound".equals(type)) {
/*     */         
/*  78 */         retval = new PDActionSound(action);
/*     */       }
/*  80 */       else if ("Movie".equals(type)) {
/*     */         
/*  82 */         retval = new PDActionMovie(action);
/*     */       }
/*  84 */       else if ("ImportData".equals(type)) {
/*     */         
/*  86 */         retval = new PDActionImportData(action);
/*     */       }
/*  88 */       else if ("ResetForm".equals(type)) {
/*     */         
/*  90 */         retval = new PDActionResetForm(action);
/*     */       }
/*  92 */       else if ("Hide".equals(type)) {
/*     */         
/*  94 */         retval = new PDActionHide(action);
/*     */       }
/*  96 */       else if ("SubmitForm".equals(type)) {
/*     */         
/*  98 */         retval = new PDActionSubmitForm(action);
/*     */       }
/* 100 */       else if ("Thread".equals(type)) {
/*     */         
/* 102 */         retval = new PDActionThread(action);
/*     */       }
/* 104 */       else if ("GoToE".equals(type)) {
/*     */         
/* 106 */         retval = new PDActionEmbeddedGoTo(action);
/*     */       } 
/*     */     } 
/* 109 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */