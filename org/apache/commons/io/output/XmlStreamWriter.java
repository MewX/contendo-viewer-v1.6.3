/*     */ package org.apache.commons.io.output;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.io.input.XmlStreamReader;
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
/*     */ public class XmlStreamWriter
/*     */   extends Writer
/*     */ {
/*     */   private static final int BUFFER_SIZE = 4096;
/*     */   private final OutputStream out;
/*     */   private final String defaultEncoding;
/*  46 */   private StringWriter xmlPrologWriter = new StringWriter(4096);
/*     */ 
/*     */ 
/*     */   
/*     */   private Writer writer;
/*     */ 
/*     */ 
/*     */   
/*     */   private String encoding;
/*     */ 
/*     */ 
/*     */   
/*     */   public XmlStreamWriter(OutputStream out) {
/*  59 */     this(out, (String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XmlStreamWriter(OutputStream out, String defaultEncoding) {
/*  70 */     this.out = out;
/*  71 */     this.defaultEncoding = (defaultEncoding != null) ? defaultEncoding : "UTF-8";
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
/*     */   public XmlStreamWriter(File file) throws FileNotFoundException {
/*  83 */     this(file, (String)null);
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
/*     */   public XmlStreamWriter(File file, String defaultEncoding) throws FileNotFoundException {
/*  96 */     this(new FileOutputStream(file), defaultEncoding);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncoding() {
/* 105 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultEncoding() {
/* 114 */     return this.defaultEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 124 */     if (this.writer == null) {
/* 125 */       this.encoding = this.defaultEncoding;
/* 126 */       this.writer = new OutputStreamWriter(this.out, this.encoding);
/* 127 */       this.writer.write(this.xmlPrologWriter.toString());
/*     */     } 
/* 129 */     this.writer.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 139 */     if (this.writer != null) {
/* 140 */       this.writer.flush();
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
/*     */   private void detectEncoding(char[] cbuf, int off, int len) throws IOException {
/* 154 */     int size = len;
/* 155 */     StringBuffer xmlProlog = this.xmlPrologWriter.getBuffer();
/* 156 */     if (xmlProlog.length() + len > 4096) {
/* 157 */       size = 4096 - xmlProlog.length();
/*     */     }
/* 159 */     this.xmlPrologWriter.write(cbuf, off, size);
/*     */ 
/*     */     
/* 162 */     if (xmlProlog.length() >= 5) {
/* 163 */       if (xmlProlog.substring(0, 5).equals("<?xml")) {
/*     */         
/* 165 */         int xmlPrologEnd = xmlProlog.indexOf("?>");
/* 166 */         if (xmlPrologEnd > 0) {
/*     */           
/* 168 */           Matcher m = ENCODING_PATTERN.matcher(xmlProlog.substring(0, xmlPrologEnd));
/*     */           
/* 170 */           if (m.find()) {
/* 171 */             this.encoding = m.group(1).toUpperCase();
/* 172 */             this.encoding = this.encoding.substring(1, this.encoding.length() - 1);
/*     */           }
/*     */           else {
/*     */             
/* 176 */             this.encoding = this.defaultEncoding;
/*     */           }
/*     */         
/* 179 */         } else if (xmlProlog.length() >= 4096) {
/*     */ 
/*     */           
/* 182 */           this.encoding = this.defaultEncoding;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 187 */         this.encoding = this.defaultEncoding;
/*     */       } 
/* 189 */       if (this.encoding != null) {
/*     */         
/* 191 */         this.xmlPrologWriter = null;
/* 192 */         this.writer = new OutputStreamWriter(this.out, this.encoding);
/* 193 */         this.writer.write(xmlProlog.toString());
/* 194 */         if (len > size) {
/* 195 */           this.writer.write(cbuf, off + size, len - size);
/*     */         }
/*     */       } 
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
/*     */   public void write(char[] cbuf, int off, int len) throws IOException {
/* 211 */     if (this.xmlPrologWriter != null) {
/* 212 */       detectEncoding(cbuf, off, len);
/*     */     } else {
/* 214 */       this.writer.write(cbuf, off, len);
/*     */     } 
/*     */   }
/*     */   
/* 218 */   static final Pattern ENCODING_PATTERN = XmlStreamReader.ENCODING_PATTERN;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/output/XmlStreamWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */