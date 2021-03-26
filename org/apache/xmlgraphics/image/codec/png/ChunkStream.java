/*     */ package org.apache.xmlgraphics.image.codec.png;
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
/*  93 */     this.type = type;
/*     */     
/*  95 */     this.baos = new ByteArrayOutputStream();
/*  96 */     this.dos = new DataOutputStream(this.baos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 101 */     this.dos.write(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 106 */     this.dos.write(b, off, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 111 */     this.dos.write(b);
/*     */   }
/*     */   
/*     */   public void writeBoolean(boolean v) throws IOException {
/* 115 */     this.dos.writeBoolean(v);
/*     */   }
/*     */   
/*     */   public void writeByte(int v) throws IOException {
/* 119 */     this.dos.writeByte(v);
/*     */   }
/*     */   
/*     */   public void writeBytes(String s) throws IOException {
/* 123 */     this.dos.writeBytes(s);
/*     */   }
/*     */   
/*     */   public void writeChar(int v) throws IOException {
/* 127 */     this.dos.writeChar(v);
/*     */   }
/*     */   
/*     */   public void writeChars(String s) throws IOException {
/* 131 */     this.dos.writeChars(s);
/*     */   }
/*     */   
/*     */   public void writeDouble(double v) throws IOException {
/* 135 */     this.dos.writeDouble(v);
/*     */   }
/*     */   
/*     */   public void writeFloat(float v) throws IOException {
/* 139 */     this.dos.writeFloat(v);
/*     */   }
/*     */   
/*     */   public void writeInt(int v) throws IOException {
/* 143 */     this.dos.writeInt(v);
/*     */   }
/*     */   
/*     */   public void writeLong(long v) throws IOException {
/* 147 */     this.dos.writeLong(v);
/*     */   }
/*     */   
/*     */   public void writeShort(int v) throws IOException {
/* 151 */     this.dos.writeShort(v);
/*     */   }
/*     */   
/*     */   public void writeUTF(String str) throws IOException {
/* 155 */     this.dos.writeUTF(str);
/*     */   }
/*     */   
/*     */   public void writeToStream(DataOutputStream output) throws IOException {
/* 159 */     byte[] typeSignature = new byte[4];
/* 160 */     typeSignature[0] = (byte)this.type.charAt(0);
/* 161 */     typeSignature[1] = (byte)this.type.charAt(1);
/* 162 */     typeSignature[2] = (byte)this.type.charAt(2);
/* 163 */     typeSignature[3] = (byte)this.type.charAt(3);
/*     */     
/* 165 */     this.dos.flush();
/* 166 */     this.baos.flush();
/*     */     
/* 168 */     byte[] data = this.baos.toByteArray();
/* 169 */     int len = data.length;
/*     */     
/* 171 */     output.writeInt(len);
/* 172 */     output.write(typeSignature);
/* 173 */     output.write(data, 0, len);
/*     */     
/* 175 */     int crc = -1;
/* 176 */     crc = CRC.updateCRC(crc, typeSignature, 0, 4);
/* 177 */     crc = CRC.updateCRC(crc, data, 0, len);
/* 178 */     output.writeInt(crc ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 185 */     if (this.baos != null) {
/* 186 */       this.baos.close();
/* 187 */       this.baos = null;
/*     */     } 
/* 189 */     if (this.dos != null) {
/* 190 */       this.dos.close();
/* 191 */       this.dos = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/ChunkStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */