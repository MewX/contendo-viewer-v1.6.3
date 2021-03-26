/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDActionEmbeddedGoTo
/*     */   extends PDAction
/*     */ {
/*     */   public static final String SUB_TYPE = "GoToE";
/*     */   
/*     */   public PDActionEmbeddedGoTo() {
/*  49 */     setSubType("GoToE");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDActionEmbeddedGoTo(COSDictionary a) {
/*  59 */     super(a);
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
/*     */   public PDDestination getDestination() throws IOException {
/*  71 */     return PDDestination.create(getCOSObject().getDictionaryObject(COSName.D));
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
/*     */   public void setDestination(PDDestination d) {
/*  83 */     if (d instanceof PDPageDestination) {
/*     */       
/*  85 */       PDPageDestination pageDest = (PDPageDestination)d;
/*  86 */       COSArray destArray = pageDest.getCOSObject();
/*  87 */       if (destArray.size() >= 1) {
/*     */         
/*  89 */         COSBase page = destArray.getObject(0);
/*  90 */         if (!(page instanceof COSDictionary))
/*     */         {
/*  92 */           throw new IllegalArgumentException("Destination of a GoToE action must be a page dictionary object");
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     getCOSObject().setItem(COSName.D, (COSObjectable)d);
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
/* 109 */     return PDFileSpecification.createFS(getCOSObject().getDictionaryObject(COSName.F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFile(PDFileSpecification fs) {
/* 119 */     getCOSObject().setItem(COSName.F, (COSObjectable)fs);
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
/* 130 */     if (getCOSObject().getDictionaryObject(COSName.NEW_WINDOW) instanceof COSBoolean) {
/*     */       
/* 132 */       COSBoolean b = (COSBoolean)getCOSObject().getDictionaryObject(COSName.NEW_WINDOW);
/* 133 */       return b.getValue() ? OpenMode.NEW_WINDOW : OpenMode.SAME_WINDOW;
/*     */     } 
/* 135 */     return OpenMode.USER_PREFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpenInNewWindow(OpenMode value) {
/* 145 */     if (null == value) {
/*     */       
/* 147 */       getCOSObject().removeItem(COSName.NEW_WINDOW);
/*     */       return;
/*     */     } 
/* 150 */     switch (value) {
/*     */       
/*     */       case USER_PREFERENCE:
/* 153 */         getCOSObject().removeItem(COSName.NEW_WINDOW);
/*     */         break;
/*     */       case SAME_WINDOW:
/* 156 */         getCOSObject().setBoolean(COSName.NEW_WINDOW, false);
/*     */         break;
/*     */       case NEW_WINDOW:
/* 159 */         getCOSObject().setBoolean(COSName.NEW_WINDOW, true);
/*     */         break;
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
/*     */   public PDTargetDirectory getTargetDirectory() {
/* 174 */     COSBase base = getCOSObject().getDictionaryObject(COSName.T);
/* 175 */     if (base instanceof COSDictionary)
/*     */     {
/* 177 */       return new PDTargetDirectory((COSDictionary)base);
/*     */     }
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetDirectory(PDTargetDirectory targetDirectory) {
/* 189 */     getCOSObject().setItem(COSName.T, targetDirectory);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionEmbeddedGoTo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */