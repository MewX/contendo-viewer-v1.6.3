/*     */ package org.apache.batik.ext.awt.image.codec.png;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ class ChunkStream
/*     */   extends OutputStream
/*     */   implements DataOutput
/*     */ {
/*     */   private String type;
/*     */   private ByteArrayOutputStream baos;
/*     */   private DataOutputStream dos;
/*     */   
/*     */   ChunkStream(String type) throws IOException {
/*  81 */     this.type = type;
/*     */     
/*  83 */     this.baos = new ByteArrayOutputStream();
/*  84 */     this.dos = new DataOutputStream(this.baos);
/*     */   }
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/*  88 */     this.dos.write(b);
/*     */   }
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/*  92 */     this.dos.write(b, off, len);
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/*  96 */     this.dos.write(b);
/*     */   }
/*     */   
/*     */   public void writeBoolean(boolean v) throws IOException {
/* 100 */     this.dos.writeBoolean(v);
/*     */   }
/*     */   
/*     */   public void writeByte(int v) throws IOException {
/* 104 */     this.dos.writeByte(v);
/*     */   }
/*     */   
/*     */   public void writeBytes(String s) throws IOException {
/* 108 */     this.dos.writeBytes(s);
/*     */   }
/*     */   
/*     */   public void writeChar(int v) throws IOException {
/* 112 */     this.dos.writeChar(v);
/*     */   }
/*     */   
/*     */   public void writeChars(String s) throws IOException {
/* 116 */     this.dos.writeChars(s);
/*     */   }
/*     */   
/*     */   public void writeDouble(double v) throws IOException {
/* 120 */     this.dos.writeDouble(v);
/*     */   }
/*     */   
/*     */   public void writeFloat(float v) throws IOException {
/* 124 */     this.dos.writeFloat(v);
/*     */   }
/*     */   
/*     */   public void writeInt(int v) throws IOException {
/* 128 */     this.dos.writeInt(v);
/*     */   }
/*     */   
/*     */   public void writeLong(long v) throws IOException {
/* 132 */     this.dos.writeLong(v);
/*     */   }
/*     */   
/*     */   public void writeShort(int v) throws IOException {
/* 136 */     this.dos.writeShort(v);
/*     */   }
/*     */   
/*     */   public void writeUTF(String str) throws IOException {
/* 140 */     this.dos.writeUTF(str);
/*     */   }
/*     */   
/*     */   public void writeToStream(DataOutputStream output) throws IOException {
/* 144 */     byte[] typeSignature = new byte[4];
/* 145 */     typeSignature[0] = (byte)this.type.charAt(0);
/* 146 */     typeSignature[1] = (byte)this.type.charAt(1);
/* 147 */     typeSignature[2] = (byte)this.type.charAt(2);
/* 148 */     typeSignature[3] = (byte)this.type.charAt(3);
/*     */     
/* 150 */     this.dos.flush();
/* 151 */     this.baos.flush();
/*     */     
/* 153 */     byte[] data = this.baos.toByteArray();
/* 154 */     int len = data.length;
/*     */     
/* 156 */     output.writeInt(len);
/* 157 */     output.write(typeSignature);
/* 158 */     output.write(data, 0, len);
/*     */     
/* 160 */     int crc = -1;
/* 161 */     crc = CRC.updateCRC(crc, typeSignature, 0, 4);
/* 162 */     crc = CRC.updateCRC(crc, data, 0, len);
/* 163 */     output.writeInt(crc ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 174 */     if (this.baos != null) {
/* 175 */       this.baos.close();
/* 176 */       this.baos = null;
/*     */     } 
/* 178 */     if (this.dos != null) {
/* 179 */       this.dos.close();
/* 180 */       this.dos = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/ChunkStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */