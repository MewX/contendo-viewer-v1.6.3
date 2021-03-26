/*     */ package org.apache.http.nio.entity;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import org.apache.http.entity.AbstractHttpEntity;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.ContentEncoderChannel;
/*     */ import org.apache.http.nio.FileContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.util.Args;
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
/*     */ public class NFileEntity
/*     */   extends AbstractHttpEntity
/*     */   implements HttpAsyncContentProducer, ProducingNHttpEntity
/*     */ {
/*     */   private final File file;
/*     */   private RandomAccessFile accessfile;
/*     */   private FileChannel fileChannel;
/*  61 */   private long idx = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean useFileChannels;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NFileEntity(File file, ContentType contentType, boolean useFileChannels) {
/*  78 */     Args.notNull(file, "File");
/*  79 */     this.file = file;
/*  80 */     this.useFileChannels = useFileChannels;
/*  81 */     if (contentType != null) {
/*  82 */       setContentType(contentType.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NFileEntity(File file) {
/*  90 */     Args.notNull(file, "File");
/*  91 */     this.file = file;
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
/*     */   public NFileEntity(File file, ContentType contentType) {
/* 103 */     this(file, contentType, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public NFileEntity(File file, String contentType, boolean useFileChannels) {
/* 111 */     Args.notNull(file, "File");
/* 112 */     this.file = file;
/* 113 */     this.useFileChannels = useFileChannels;
/* 114 */     setContentType(contentType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public NFileEntity(File file, String contentType) {
/* 122 */     this(file, contentType, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 132 */     if (this.accessfile != null) {
/* 133 */       this.accessfile.close();
/*     */     }
/* 135 */     this.accessfile = null;
/* 136 */     this.fileChannel = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void finish() throws IOException {
/* 147 */     close();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/* 152 */     return this.file.length();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepeatable() {
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/*     */     long transferred;
/* 163 */     if (this.accessfile == null) {
/* 164 */       this.accessfile = new RandomAccessFile(this.file, "r");
/*     */     }
/* 166 */     if (this.fileChannel == null) {
/* 167 */       this.fileChannel = this.accessfile.getChannel();
/* 168 */       this.idx = 0L;
/*     */     } 
/*     */ 
/*     */     
/* 172 */     if (this.useFileChannels && encoder instanceof FileContentEncoder) {
/* 173 */       transferred = ((FileContentEncoder)encoder).transfer(this.fileChannel, this.idx, Long.MAX_VALUE);
/*     */     } else {
/*     */       
/* 176 */       transferred = this.fileChannel.transferTo(this.idx, Long.MAX_VALUE, (WritableByteChannel)new ContentEncoderChannel(encoder));
/*     */     } 
/*     */     
/* 179 */     if (transferred > 0L) {
/* 180 */       this.idx += transferred;
/*     */     }
/* 182 */     if (this.idx >= this.fileChannel.size()) {
/* 183 */       encoder.complete();
/* 184 */       close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStreaming() {
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getContent() throws IOException {
/* 195 */     return new FileInputStream(this.file);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream outStream) throws IOException {
/* 200 */     Args.notNull(outStream, "Output stream");
/* 201 */     InputStream inStream = new FileInputStream(this.file);
/*     */     try {
/* 203 */       byte[] tmp = new byte[4096];
/*     */       int l;
/* 205 */       while ((l = inStream.read(tmp)) != -1) {
/* 206 */         outStream.write(tmp, 0, l);
/*     */       }
/* 208 */       outStream.flush();
/*     */     } finally {
/* 210 */       inStream.close();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/NFileEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */