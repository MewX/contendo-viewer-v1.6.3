/*    */ package org.apache.pdfbox.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
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
/*    */ final class RunLengthDecodeFilter
/*    */   extends Filter
/*    */ {
/* 34 */   private static final Log LOG = LogFactory.getLog(RunLengthDecodeFilter.class);
/*    */ 
/*    */   
/*    */   private static final int RUN_LENGTH_EOD = 128;
/*    */ 
/*    */ 
/*    */   
/*    */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/* 42 */     byte[] buffer = new byte[128]; int dupAmount;
/* 43 */     while ((dupAmount = encoded.read()) != -1 && dupAmount != 128) {
/*    */       
/* 45 */       if (dupAmount <= 127) {
/*    */         
/* 47 */         int amountToCopy = dupAmount + 1;
/*    */         
/* 49 */         while (amountToCopy > 0) {
/*    */           
/* 51 */           int compressedRead = encoded.read(buffer, 0, amountToCopy);
/*    */           
/* 53 */           if (compressedRead == -1) {
/*    */             break;
/*    */           }
/*    */           
/* 57 */           decoded.write(buffer, 0, compressedRead);
/* 58 */           amountToCopy -= compressedRead;
/*    */         } 
/*    */         
/*    */         continue;
/*    */       } 
/* 63 */       int dupByte = encoded.read();
/*    */       
/* 65 */       if (dupByte == -1) {
/*    */         break;
/*    */       }
/*    */       
/* 69 */       for (int i = 0; i < 257 - dupAmount; i++)
/*    */       {
/* 71 */         decoded.write(dupByte);
/*    */       }
/*    */     } 
/*    */     
/* 75 */     return new DecodeResult(parameters);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 82 */     LOG.warn("RunLengthDecodeFilter.encode is not implemented yet, skipping this stream.");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/RunLengthDecodeFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */