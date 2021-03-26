/*     */ package org.apache.pdfbox.util.filetypedetector;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.util.Charsets;
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
/*     */ public final class FileTypeDetector
/*     */ {
/*  41 */   private static final ByteTrie<FileType> root = new ByteTrie<FileType>(); static {
/*  42 */     root.setDefaultValue(FileType.UNKNOWN);
/*     */ 
/*     */ 
/*     */     
/*  46 */     root.addPath(FileType.JPEG, new byte[][] { { -1, -40 } });
/*  47 */     root.addPath(FileType.TIFF, new byte[][] { "II".getBytes(Charsets.ISO_8859_1), { 42, 0 } });
/*  48 */     root.addPath(FileType.TIFF, new byte[][] { "MM".getBytes(Charsets.ISO_8859_1), { 0, 42 } });
/*  49 */     root.addPath(FileType.PSD, new byte[][] { "8BPS".getBytes(Charsets.ISO_8859_1) });
/*  50 */     root.addPath(FileType.PNG, new byte[][] { { -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82 } });
/*     */     
/*  52 */     root.addPath(FileType.BMP, new byte[][] { "BM".getBytes(Charsets.ISO_8859_1) });
/*  53 */     root.addPath(FileType.GIF, new byte[][] { "GIF87a".getBytes(Charsets.ISO_8859_1) });
/*  54 */     root.addPath(FileType.GIF, new byte[][] { "GIF89a".getBytes(Charsets.ISO_8859_1) });
/*  55 */     root.addPath(FileType.ICO, new byte[][] { { 0, 0, 1, 0 } });
/*     */     
/*  57 */     root.addPath(FileType.PCX, new byte[][] { { 10, 0, 1 } });
/*  58 */     root.addPath(FileType.PCX, new byte[][] { { 10, 2, 1 } });
/*  59 */     root.addPath(FileType.PCX, new byte[][] { { 10, 3, 1 } });
/*  60 */     root.addPath(FileType.PCX, new byte[][] { { 10, 5, 1 } });
/*  61 */     root.addPath(FileType.RIFF, new byte[][] { "RIFF".getBytes(Charsets.ISO_8859_1) });
/*     */ 
/*     */ 
/*     */     
/*  65 */     root.addPath(FileType.CRW, new byte[][] { "II".getBytes(Charsets.ISO_8859_1), { 26, 0, 0, 0 }, "HEAPCCDR".getBytes(Charsets.ISO_8859_1) });
/*  66 */     root.addPath(FileType.CR2, new byte[][] { "II".getBytes(Charsets.ISO_8859_1), { 42, 0, 16, 0, 0, 0, 67, 82 } });
/*  67 */     root.addPath(FileType.NEF, new byte[][] { "MM".getBytes(Charsets.ISO_8859_1), { 0, 42, 0, 0, 0, Byte.MIN_VALUE, 0 } });
/*  68 */     root.addPath(FileType.ORF, new byte[][] { "IIRO".getBytes(Charsets.ISO_8859_1), { 8, 0 } });
/*  69 */     root.addPath(FileType.ORF, new byte[][] { "IIRS".getBytes(Charsets.ISO_8859_1), { 8, 0 } });
/*  70 */     root.addPath(FileType.RAF, new byte[][] { "FUJIFILMCCD-RAW".getBytes(Charsets.ISO_8859_1) });
/*  71 */     root.addPath(FileType.RW2, new byte[][] { "II".getBytes(Charsets.ISO_8859_1), { 85, 0 } });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FileType detectFileType(BufferedInputStream inputStream) throws IOException {
/*  92 */     if (!inputStream.markSupported())
/*     */     {
/*  94 */       throw new IOException("Stream must support mark/reset");
/*     */     }
/*     */     
/*  97 */     int maxByteCount = root.getMaxDepth();
/*     */     
/*  99 */     inputStream.mark(maxByteCount);
/*     */     
/* 101 */     byte[] bytes = new byte[maxByteCount];
/* 102 */     int bytesRead = inputStream.read(bytes);
/*     */     
/* 104 */     if (bytesRead == -1)
/*     */     {
/* 106 */       throw new IOException("Stream ended before file's magic number could be determined.");
/*     */     }
/*     */     
/* 109 */     inputStream.reset();
/*     */ 
/*     */     
/* 112 */     return root.find(bytes);
/*     */   }
/*     */ 
/*     */   
/*     */   public static FileType detectFileType(byte[] fileBytes) throws IOException {
/* 117 */     return root.find(fileBytes);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/filetypedetector/FileTypeDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */