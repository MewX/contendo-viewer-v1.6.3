/*    */ package org.apache.xmlgraphics.util.uri;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.io.StringWriter;
/*    */ import java.io.Writer;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ import org.apache.xmlgraphics.util.WriterOutputStream;
/*    */ import org.apache.xmlgraphics.util.io.Base64EncodeStream;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class DataURLUtil
/*    */ {
/*    */   public static String createDataURL(InputStream in, String mediatype) throws IOException {
/* 48 */     StringWriter writer = new StringWriter();
/* 49 */     writeDataURL(in, mediatype, writer);
/* 50 */     return writer.toString();
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
/*    */   public static void writeDataURL(InputStream in, String mediatype, Writer writer) throws IOException {
/* 62 */     writer.write("data:");
/* 63 */     if (mediatype != null) {
/* 64 */       writer.write(mediatype);
/*    */     }
/* 66 */     writer.write(";base64,");
/* 67 */     Base64EncodeStream out = new Base64EncodeStream((OutputStream)new WriterOutputStream(writer, "US-ASCII"), false);
/*    */     
/* 69 */     IOUtils.copy(in, (OutputStream)out);
/* 70 */     out.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/uri/DataURLUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */