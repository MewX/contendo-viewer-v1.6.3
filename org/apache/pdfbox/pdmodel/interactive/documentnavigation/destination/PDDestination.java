/*     */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.PDDestinationOrAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDDestination
/*     */   implements PDDestinationOrAction
/*     */ {
/*     */   public static PDDestination create(COSBase base) throws IOException {
/*  48 */     PDDestination retval = null;
/*  49 */     if (base != null)
/*     */     {
/*     */ 
/*     */       
/*  53 */       if (base instanceof COSArray && ((COSArray)base)
/*  54 */         .size() > 1 && ((COSArray)base)
/*  55 */         .getObject(1) instanceof COSName) {
/*     */         
/*  57 */         COSArray array = (COSArray)base;
/*  58 */         COSName type = (COSName)array.getObject(1);
/*  59 */         String typeString = type.getName();
/*  60 */         if (typeString.equals("Fit") || typeString
/*  61 */           .equals("FitB"))
/*     */         {
/*  63 */           retval = new PDPageFitDestination(array);
/*     */         }
/*  65 */         else if (typeString.equals("FitV") || typeString
/*  66 */           .equals("FitBV"))
/*     */         {
/*  68 */           retval = new PDPageFitHeightDestination(array);
/*     */         }
/*  70 */         else if (typeString.equals("FitR"))
/*     */         {
/*  72 */           retval = new PDPageFitRectangleDestination(array);
/*     */         }
/*  74 */         else if (typeString.equals("FitH") || typeString
/*  75 */           .equals("FitBH"))
/*     */         {
/*  77 */           retval = new PDPageFitWidthDestination(array);
/*     */         }
/*  79 */         else if (typeString.equals("XYZ"))
/*     */         {
/*  81 */           retval = new PDPageXYZDestination(array);
/*     */         }
/*     */         else
/*     */         {
/*  85 */           throw new IOException("Unknown destination type: " + type.getName());
/*     */         }
/*     */       
/*  88 */       } else if (base instanceof COSString) {
/*     */         
/*  90 */         retval = new PDNamedDestination((COSString)base);
/*     */       }
/*  92 */       else if (base instanceof COSName) {
/*     */         
/*  94 */         retval = new PDNamedDestination((COSName)base);
/*     */       }
/*     */       else {
/*     */         
/*  98 */         throw new IOException("Error: can't convert to Destination " + base);
/*     */       }  } 
/* 100 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/destination/PDDestination.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */