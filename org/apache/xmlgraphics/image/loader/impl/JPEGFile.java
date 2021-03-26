/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class JPEGFile
/*     */   implements JPEGConstants
/*     */ {
/*  37 */   protected static final Log log = LogFactory.getLog(JPEGFile.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private DataInput in;
/*     */ 
/*     */ 
/*     */   
/*     */   public JPEGFile(ImageInputStream in) {
/*  46 */     this.in = in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JPEGFile(InputStream in) {
/*  54 */     this.in = new DataInputStream(in);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataInput getDataInput() {
/*  62 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readMarkerSegment() throws IOException {
/*     */     int marker;
/*     */     do {
/*  73 */       marker = this.in.readByte() & 0xFF;
/*     */     }
/*  75 */     while (marker != 255);
/*     */ 
/*     */     
/*     */     while (true) {
/*  79 */       int segID = this.in.readByte() & 0xFF;
/*     */       
/*  81 */       if (segID != 255) {
/*  82 */         return segID;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readSegmentLength() throws IOException {
/*  92 */     int reclen = this.in.readUnsignedShort();
/*  93 */     return reclen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skipCurrentMarkerSegment() throws IOException {
/* 102 */     int reclen = readSegmentLength();
/* 103 */     this.in.skipBytes(reclen - 2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/JPEGFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */