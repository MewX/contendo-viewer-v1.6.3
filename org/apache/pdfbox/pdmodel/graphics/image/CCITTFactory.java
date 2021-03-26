/*     */ package org.apache.pdfbox.pdmodel.graphics.image;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageOutputStream;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.filter.Filter;
/*     */ import org.apache.pdfbox.filter.FilterFactory;
/*     */ import org.apache.pdfbox.io.RandomAccess;
/*     */ import org.apache.pdfbox.io.RandomAccessBuffer;
/*     */ import org.apache.pdfbox.io.RandomAccessFile;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
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
/*     */ public final class CCITTFactory
/*     */ {
/*     */   public static PDImageXObject createFromImage(PDDocument document, BufferedImage image) throws IOException {
/*  63 */     if (image.getType() != 12 && image.getColorModel().getPixelSize() != 1)
/*     */     {
/*  65 */       throw new IllegalArgumentException("Only 1-bit b/w images supported");
/*     */     }
/*     */     
/*  68 */     int height = image.getHeight();
/*  69 */     int width = image.getWidth();
/*     */     
/*  71 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  72 */     MemoryCacheImageOutputStream mcios = new MemoryCacheImageOutputStream(bos);
/*     */     
/*  74 */     for (int y = 0; y < height; y++) {
/*     */       
/*  76 */       for (int x = 0; x < width; x++)
/*     */       {
/*     */         
/*  79 */         mcios.writeBits((image.getRGB(x, y) & 0x1 ^ 0xFFFFFFFF), 1);
/*     */       }
/*  81 */       if (mcios.getBitOffset() != 0)
/*     */       {
/*  83 */         mcios.writeBits(0L, 8 - mcios.getBitOffset());
/*     */       }
/*     */     } 
/*  86 */     mcios.flush();
/*  87 */     mcios.close();
/*     */     
/*  89 */     return prepareImageXObject(document, bos.toByteArray(), width, height, (PDColorSpace)PDDeviceGray.INSTANCE);
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
/*     */   public static PDImageXObject createFromByteArray(PDDocument document, byte[] byteArray) throws IOException {
/* 109 */     return createFromByteArray(document, byteArray, 0);
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
/*     */   public static PDImageXObject createFromByteArray(PDDocument document, byte[] byteArray, int number) throws IOException {
/* 130 */     RandomAccessBuffer randomAccessBuffer = new RandomAccessBuffer(byteArray);
/*     */     
/*     */     try {
/* 133 */       return createFromRandomAccessImpl(document, (RandomAccess)randomAccessBuffer, number);
/*     */     }
/*     */     finally {
/*     */       
/* 137 */       randomAccessBuffer.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static PDImageXObject prepareImageXObject(PDDocument document, byte[] byteArray, int width, int height, PDColorSpace initColorSpace) throws IOException {
/* 145 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */     
/* 147 */     Filter filter = FilterFactory.INSTANCE.getFilter(COSName.CCITTFAX_DECODE);
/* 148 */     COSDictionary dict = new COSDictionary();
/* 149 */     dict.setInt(COSName.COLUMNS, width);
/* 150 */     dict.setInt(COSName.ROWS, height);
/* 151 */     filter.encode(new ByteArrayInputStream(byteArray), baos, dict, 0);
/*     */     
/* 153 */     ByteArrayInputStream encodedByteStream = new ByteArrayInputStream(baos.toByteArray());
/* 154 */     PDImageXObject image = new PDImageXObject(document, encodedByteStream, (COSBase)COSName.CCITTFAX_DECODE, width, height, 1, initColorSpace);
/*     */     
/* 156 */     dict.setInt(COSName.K, -1);
/* 157 */     image.getCOSObject().setItem(COSName.DECODE_PARMS, (COSBase)dict);
/* 158 */     return image;
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
/*     */   @Deprecated
/*     */   public static PDImageXObject createFromRandomAccess(PDDocument document, RandomAccess reader) throws IOException {
/* 176 */     return createFromRandomAccessImpl(document, reader, 0);
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
/*     */   @Deprecated
/*     */   public static PDImageXObject createFromRandomAccess(PDDocument document, RandomAccess reader, int number) throws IOException {
/* 195 */     return createFromRandomAccessImpl(document, reader, number);
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
/*     */   public static PDImageXObject createFromFile(PDDocument document, File file) throws IOException {
/* 214 */     return createFromFile(document, file, 0);
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
/*     */   public static PDImageXObject createFromFile(PDDocument document, File file, int number) throws IOException {
/* 234 */     RandomAccessFile raf = new RandomAccessFile(file, "r");
/*     */     
/*     */     try {
/* 237 */       return createFromRandomAccessImpl(document, (RandomAccess)raf, number);
/*     */     }
/*     */     finally {
/*     */       
/* 241 */       raf.close();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static PDImageXObject createFromRandomAccessImpl(PDDocument document, RandomAccess reader, int number) throws IOException {
/* 259 */     COSDictionary decodeParms = new COSDictionary();
/* 260 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 261 */     extractFromTiff(reader, bos, decodeParms, number);
/* 262 */     if (bos.size() == 0)
/*     */     {
/* 264 */       return null;
/*     */     }
/* 266 */     ByteArrayInputStream encodedByteStream = new ByteArrayInputStream(bos.toByteArray());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 271 */     PDImageXObject pdImage = new PDImageXObject(document, encodedByteStream, (COSBase)COSName.CCITTFAX_DECODE, decodeParms.getInt(COSName.COLUMNS), decodeParms.getInt(COSName.ROWS), 1, (PDColorSpace)PDDeviceGray.INSTANCE);
/*     */ 
/*     */ 
/*     */     
/* 275 */     COSStream cOSStream = pdImage.getCOSObject();
/* 276 */     cOSStream.setItem(COSName.DECODE_PARMS, (COSBase)decodeParms);
/* 277 */     return pdImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void extractFromTiff(RandomAccess reader, OutputStream os, COSDictionary params, int number) throws IOException {
/*     */     try {
/* 287 */       reader.seek(0L);
/* 288 */       char endianess = (char)reader.read();
/* 289 */       if ((char)reader.read() != endianess)
/*     */       {
/* 291 */         throw new IOException("Not a valid tiff file");
/*     */       }
/*     */       
/* 294 */       if (endianess != 'M' && endianess != 'I')
/*     */       {
/* 296 */         throw new IOException("Not a valid tiff file");
/*     */       }
/* 298 */       int magicNumber = readshort(endianess, reader);
/* 299 */       if (magicNumber != 42)
/*     */       {
/* 301 */         throw new IOException("Not a valid tiff file");
/*     */       }
/*     */ 
/*     */       
/* 305 */       int address = readlong(endianess, reader);
/* 306 */       reader.seek(address);
/*     */ 
/*     */ 
/*     */       
/* 310 */       for (int i = 0; i < number; i++) {
/*     */         
/* 312 */         int m = readshort(endianess, reader);
/* 313 */         if (m > 50)
/*     */         {
/* 315 */           throw new IOException("Not a valid tiff file");
/*     */         }
/* 317 */         reader.seek((address + 2 + m * 12));
/* 318 */         address = readlong(endianess, reader);
/* 319 */         if (address == 0) {
/*     */           return;
/*     */         }
/*     */         
/* 323 */         reader.seek(address);
/*     */       } 
/*     */       
/* 326 */       int numtags = readshort(endianess, reader);
/*     */ 
/*     */ 
/*     */       
/* 330 */       if (numtags > 50)
/*     */       {
/* 332 */         throw new IOException("Not a valid tiff file");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 341 */       int k = -1000;
/*     */       
/* 343 */       int dataoffset = 0;
/* 344 */       int datalength = 0;
/*     */       
/* 346 */       for (int j = 0; j < numtags; j++) {
/*     */         
/* 348 */         int val, tag = readshort(endianess, reader);
/* 349 */         int type = readshort(endianess, reader);
/* 350 */         int count = readlong(endianess, reader);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 355 */         switch (type) {
/*     */           
/*     */           case 1:
/* 358 */             val = reader.read();
/* 359 */             reader.read();
/* 360 */             reader.read();
/* 361 */             reader.read();
/*     */             break;
/*     */           case 3:
/* 364 */             val = readshort(endianess, reader);
/* 365 */             reader.read();
/* 366 */             reader.read();
/*     */             break;
/*     */           default:
/* 369 */             val = readlong(endianess, reader);
/*     */             break;
/*     */         } 
/* 372 */         switch (tag) {
/*     */ 
/*     */           
/*     */           case 256:
/* 376 */             params.setInt(COSName.COLUMNS, val);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 257:
/* 381 */             params.setInt(COSName.ROWS, val);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 259:
/* 386 */             if (val == 4)
/*     */             {
/* 388 */               k = -1;
/*     */             }
/* 390 */             if (val == 3)
/*     */             {
/* 392 */               k = 0;
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 262:
/* 398 */             if (val == 1)
/*     */             {
/* 400 */               params.setBoolean(COSName.BLACK_IS_1, true);
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 266:
/* 406 */             if (val != 1)
/*     */             {
/* 408 */               throw new IOException("FillOrder " + val + " is not supported");
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 273:
/* 414 */             if (count == 1)
/*     */             {
/* 416 */               dataoffset = val;
/*     */             }
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 274:
/* 423 */             if (val != 1)
/*     */             {
/* 425 */               throw new IOException("Orientation " + val + " is not supported");
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 279:
/* 431 */             if (count == 1)
/*     */             {
/* 433 */               datalength = val;
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 292:
/* 439 */             if ((val & 0x1) != 0)
/*     */             {
/*     */               
/* 442 */               k = 50;
/*     */             }
/*     */             
/* 445 */             if ((val & 0x4) != 0)
/*     */             {
/* 447 */               throw new IOException("CCITT Group 3 'uncompressed mode' is not supported");
/*     */             }
/* 449 */             if ((val & 0x2) != 0)
/*     */             {
/* 451 */               throw new IOException("CCITT Group 3 'fill bits before EOL' is not supported");
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 324:
/* 457 */             if (count == 1)
/*     */             {
/* 459 */               dataoffset = val;
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 325:
/* 465 */             if (count == 1)
/*     */             {
/* 467 */               datalength = val;
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 478 */       if (k == -1000)
/*     */       {
/* 480 */         throw new IOException("First image in tiff is not CCITT T4 or T6 compressed");
/*     */       }
/* 482 */       if (dataoffset == 0)
/*     */       {
/* 484 */         throw new IOException("First image in tiff is not a single tile/strip");
/*     */       }
/*     */       
/* 487 */       params.setInt(COSName.K, k);
/*     */       
/* 489 */       reader.seek(dataoffset);
/*     */       
/* 491 */       byte[] buf = new byte[8192];
/*     */       int amountRead;
/* 493 */       while ((amountRead = reader.read(buf, 0, Math.min(8192, datalength))) > 0)
/*     */       {
/* 495 */         datalength -= amountRead;
/* 496 */         os.write(buf, 0, amountRead);
/*     */       }
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 502 */       os.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int readshort(char endianess, RandomAccess raf) throws IOException {
/* 508 */     if (endianess == 'I')
/*     */     {
/* 510 */       return raf.read() | raf.read() << 8;
/*     */     }
/* 512 */     return raf.read() << 8 | raf.read();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int readlong(char endianess, RandomAccess raf) throws IOException {
/* 517 */     if (endianess == 'I')
/*     */     {
/* 519 */       return raf.read() | raf.read() << 8 | raf.read() << 16 | raf.read() << 24;
/*     */     }
/* 521 */     return raf.read() << 24 | raf.read() << 16 | raf.read() << 8 | raf.read();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/image/CCITTFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */