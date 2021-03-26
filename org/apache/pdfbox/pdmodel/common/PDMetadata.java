/*    */ package org.apache.pdfbox.pdmodel.common;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.cos.COSStream;
/*    */ import org.apache.pdfbox.pdmodel.PDDocument;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PDMetadata
/*    */   extends PDStream
/*    */ {
/*    */   public PDMetadata(PDDocument document) {
/* 44 */     super(document);
/* 45 */     getCOSObject().setName(COSName.TYPE, "Metadata");
/* 46 */     getCOSObject().setName(COSName.SUBTYPE, "XML");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDMetadata(PDDocument doc, InputStream str) throws IOException {
/* 59 */     super(doc, str);
/* 60 */     getCOSObject().setName(COSName.TYPE, "Metadata");
/* 61 */     getCOSObject().setName(COSName.SUBTYPE, "XML");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDMetadata(COSStream str) {
/* 71 */     super(str);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream exportXMPMetadata() throws IOException {
/* 84 */     return (InputStream)createInputStream();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void importXMPMetadata(byte[] xmp) throws IOException {
/* 97 */     OutputStream os = createOutputStream();
/* 98 */     os.write(xmp);
/* 99 */     os.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */