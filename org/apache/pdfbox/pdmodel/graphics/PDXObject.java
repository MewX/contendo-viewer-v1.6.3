/*     */ package org.apache.pdfbox.pdmodel.graphics;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.ResourceCache;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
/*     */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDXObject
/*     */   implements COSObjectable
/*     */ {
/*     */   private final PDStream stream;
/*     */   
/*     */   public static PDXObject createXObject(COSBase base, PDResources resources) throws IOException {
/*  54 */     if (base == null)
/*     */     {
/*     */       
/*  57 */       return null;
/*     */     }
/*     */     
/*  60 */     if (!(base instanceof COSStream))
/*     */     {
/*  62 */       throw new IOException("Unexpected object type: " + base.getClass().getName());
/*     */     }
/*     */     
/*  65 */     COSStream stream = (COSStream)base;
/*  66 */     String subtype = stream.getNameAsString(COSName.SUBTYPE);
/*     */     
/*  68 */     if (COSName.IMAGE.getName().equals(subtype))
/*     */     {
/*  70 */       return (PDXObject)new PDImageXObject(new PDStream(stream), resources);
/*     */     }
/*  72 */     if (COSName.FORM.getName().equals(subtype)) {
/*     */       
/*  74 */       ResourceCache cache = (resources != null) ? resources.getResourceCache() : null;
/*  75 */       COSDictionary group = (COSDictionary)stream.getDictionaryObject(COSName.GROUP);
/*  76 */       if (group != null && COSName.TRANSPARENCY.equals(group.getCOSName(COSName.S)))
/*     */       {
/*  78 */         return (PDXObject)new PDTransparencyGroup(stream, cache);
/*     */       }
/*  80 */       return (PDXObject)new PDFormXObject(stream, cache);
/*     */     } 
/*  82 */     if (COSName.PS.getName().equals(subtype))
/*     */     {
/*  84 */       return new PDPostScriptXObject(stream);
/*     */     }
/*     */ 
/*     */     
/*  88 */     throw new IOException("Invalid XObject Subtype: " + subtype);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDXObject(COSStream stream, COSName subtype) {
/*  99 */     this.stream = new PDStream(stream);
/*     */     
/* 101 */     stream.setName(COSName.TYPE, COSName.XOBJECT.getName());
/* 102 */     stream.setName(COSName.SUBTYPE, subtype.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDXObject(PDStream stream, COSName subtype) {
/* 112 */     this.stream = stream;
/*     */     
/* 114 */     stream.getCOSObject().setName(COSName.TYPE, COSName.XOBJECT.getName());
/* 115 */     stream.getCOSObject().setName(COSName.SUBTYPE, subtype.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDXObject(PDDocument document, COSName subtype) {
/* 125 */     this.stream = new PDStream(document);
/* 126 */     this.stream.getCOSObject().setName(COSName.TYPE, COSName.XOBJECT.getName());
/* 127 */     this.stream.getCOSObject().setName(COSName.SUBTYPE, subtype.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final COSStream getCOSObject() {
/* 137 */     return this.stream.getCOSObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public final COSStream getCOSStream() {
/* 148 */     return this.stream.getCOSObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public final PDStream getPDStream() {
/* 159 */     return this.stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final PDStream getStream() {
/* 168 */     return this.stream;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/PDXObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */