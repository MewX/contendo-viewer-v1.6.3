/*     */ package com.sun.jna.platform.mac;
/*     */ 
/*     */ import com.sun.jna.Memory;
/*     */ import com.sun.jna.Pointer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class XAttrUtil
/*     */ {
/*     */   public static List<String> listXAttr(String path) {
/*  37 */     long bufferLength = XAttr.INSTANCE.listxattr(path, null, 0L, 0);
/*     */     
/*  39 */     if (bufferLength < 0L) {
/*  40 */       return null;
/*     */     }
/*  42 */     if (bufferLength == 0L) {
/*  43 */       return new ArrayList<String>(0);
/*     */     }
/*  45 */     Memory valueBuffer = new Memory(bufferLength);
/*  46 */     long valueLength = XAttr.INSTANCE.listxattr(path, (Pointer)valueBuffer, bufferLength, 0);
/*     */     
/*  48 */     if (valueLength < 0L) {
/*  49 */       return null;
/*     */     }
/*  51 */     return decodeStringSequence(valueBuffer.getByteBuffer(0L, valueLength));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getXAttr(String path, String name) {
/*  56 */     long bufferLength = XAttr.INSTANCE.getxattr(path, name, null, 0L, 0, 0);
/*     */     
/*  58 */     if (bufferLength < 0L) {
/*  59 */       return null;
/*     */     }
/*  61 */     if (bufferLength == 0L) {
/*  62 */       return "";
/*     */     }
/*  64 */     Memory valueBuffer = new Memory(bufferLength);
/*  65 */     long valueLength = XAttr.INSTANCE.getxattr(path, name, (Pointer)valueBuffer, bufferLength, 0, 0);
/*     */     
/*  67 */     if (valueLength < 0L) {
/*  68 */       return null;
/*     */     }
/*  70 */     return decodeString(valueBuffer.getByteBuffer(0L, valueLength - 1L));
/*     */   }
/*     */   
/*     */   public static int setXAttr(String path, String name, String value) {
/*  74 */     Memory valueBuffer = encodeString(value);
/*  75 */     return XAttr.INSTANCE.setxattr(path, name, (Pointer)valueBuffer, valueBuffer.size(), 0, 0);
/*     */   }
/*     */   
/*     */   public static int removeXAttr(String path, String name) {
/*  79 */     return XAttr.INSTANCE.removexattr(path, name, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Memory encodeString(String s) {
/*  84 */     byte[] bb = s.getBytes(Charset.forName("UTF-8"));
/*  85 */     Memory valueBuffer = new Memory((bb.length + 1));
/*  86 */     valueBuffer.write(0L, bb, 0, bb.length);
/*  87 */     valueBuffer.setByte(valueBuffer.size() - 1L, (byte)0);
/*  88 */     return valueBuffer;
/*     */   }
/*     */   
/*     */   protected static String decodeString(ByteBuffer bb) {
/*  92 */     return Charset.forName("UTF-8").decode(bb).toString();
/*     */   }
/*     */   
/*     */   protected static List<String> decodeStringSequence(ByteBuffer bb) {
/*  96 */     List<String> names = new ArrayList<String>();
/*     */     
/*  98 */     bb.mark();
/*  99 */     while (bb.hasRemaining()) {
/* 100 */       if (bb.get() == 0) {
/* 101 */         ByteBuffer nameBuffer = (ByteBuffer)bb.duplicate().limit(bb.position() - 1).reset();
/* 102 */         if (nameBuffer.hasRemaining()) {
/* 103 */           names.add(decodeString(nameBuffer));
/*     */         }
/* 105 */         bb.mark();
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     return names;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/mac/XAttrUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */