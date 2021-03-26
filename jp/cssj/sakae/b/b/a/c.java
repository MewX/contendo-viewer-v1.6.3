/*      */ package jp.cssj.sakae.b.b.a;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Point;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.SequenceInputStream;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.TimeZone;
/*      */ import java.util.Vector;
/*      */ import java.util.zip.Inflater;
/*      */ import java.util.zip.InflaterInputStream;
/*      */ import jp.cssj.sakae.e.d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class c
/*      */   extends d
/*      */ {
/*      */   public static final int a = 0;
/*      */   public static final int b = 2;
/*      */   public static final int c = 3;
/*      */   public static final int d = 4;
/*      */   public static final int e = 6;
/*  140 */   private static final String[] w = new String[] { "Grayscale", "Error", "Truecolor", "Index", "Grayscale with alpha", "Error", "Truecolor with alpha" };
/*      */ 
/*      */   
/*      */   public static final int f = 0;
/*      */   
/*      */   public static final int g = 1;
/*      */   
/*      */   public static final int h = 2;
/*      */   
/*      */   public static final int i = 3;
/*      */   
/*      */   public static final int j = 4;
/*      */   
/*  153 */   private int[][] x = new int[][] { null, { 0 }, { 0, 1 }, { 0, 1, 2 }, { 0, 1, 2, 3 } };
/*      */ 
/*      */   
/*      */   private int y;
/*      */ 
/*      */   
/*      */   private int z;
/*      */ 
/*      */   
/*      */   private int A;
/*      */ 
/*      */   
/*      */   private int B;
/*      */   
/*      */   private int C;
/*      */   
/*      */   private int D;
/*      */   
/*      */   private byte[] E;
/*      */   
/*      */   private byte[] F;
/*      */   
/*      */   private byte[] G;
/*      */   
/*      */   private byte[] H;
/*      */   
/*      */   private int I;
/*      */   
/*      */   private int J;
/*      */   
/*      */   private int K;
/*      */   
/*      */   private int L;
/*      */   
/*      */   private int M;
/*      */   
/*      */   private int N;
/*      */   
/*      */   private int O;
/*      */   
/*      */   private int P;
/*      */   
/*  195 */   private int[] Q = null;
/*      */ 
/*      */   
/*      */   private boolean R = false;
/*      */ 
/*      */   
/*      */   private boolean S = false;
/*      */ 
/*      */   
/*      */   private boolean T = false;
/*      */ 
/*      */   
/*      */   private boolean U = false;
/*      */ 
/*      */   
/*      */   private boolean V = false;
/*      */ 
/*      */   
/*      */   private boolean W = false;
/*      */ 
/*      */   
/*      */   private boolean X = true;
/*      */ 
/*      */   
/*  219 */   private double Y = 0.45454999804496765D;
/*      */   
/*  221 */   private double Z = 1.0D;
/*      */   
/*  223 */   private double aa = 2.200000047683716D;
/*      */   
/*  225 */   private double[] ab = null;
/*      */   
/*  227 */   private int ac = -1;
/*      */ 
/*      */   
/*  230 */   private int ad = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ae = 0;
/*      */ 
/*      */   
/*      */   private static final int af = 1;
/*      */ 
/*      */   
/*      */   private static final int ag = 2;
/*      */ 
/*      */   
/*      */   private static final int ah = 3;
/*      */ 
/*      */   
/*      */   private static final int ai = 4;
/*      */ 
/*      */   
/*      */   private static final int aj = 5;
/*      */ 
/*      */   
/*      */   private static final int ak = 6;
/*      */ 
/*      */   
/*      */   private static final int al = 7;
/*      */ 
/*      */   
/*      */   private static final int am = 8;
/*      */ 
/*      */   
/*      */   private static final int an = 9;
/*      */ 
/*      */   
/*      */   private static final int ao = 16;
/*      */ 
/*      */   
/*      */   private static final int ap = 16;
/*      */ 
/*      */   
/*      */   private static final int aq = 17;
/*      */ 
/*      */   
/*      */   private static final int ar = 19;
/*      */ 
/*      */   
/*      */   private static final int as = 22;
/*      */ 
/*      */   
/*  279 */   private Vector<ByteArrayInputStream> at = new Vector<>();
/*      */ 
/*      */   
/*      */   private DataInputStream au;
/*      */   
/*      */   private int av;
/*      */   
/*      */   private int aw;
/*      */   
/*      */   private int ax;
/*      */   
/*  290 */   private int ay = 0;
/*      */   
/*  292 */   private Vector<String> az = new Vector<>();
/*      */   
/*  294 */   private Vector<String> aA = new Vector<>();
/*      */   
/*  296 */   private Vector<String> aB = new Vector<>();
/*      */   
/*  298 */   private Vector<String> aC = new Vector<>();
/*      */   
/*      */   private WritableRaster aD;
/*      */   
/*  302 */   private int[] aE = null;
/*      */   
/*  304 */   private byte[] aF = new byte[8192];
/*      */   
/*      */   private void e(int bits) {
/*  307 */     double exp = this.Z / this.Y * this.aa;
/*  308 */     int numSamples = 1 << bits;
/*  309 */     int maxOutSample = (bits == 16) ? 65535 : 255;
/*      */     
/*  311 */     this.aE = new int[numSamples];
/*  312 */     for (int i = 0; i < numSamples; i++) {
/*  313 */       double gbright = i / (numSamples - 1);
/*  314 */       double gamma = Math.pow(gbright, exp);
/*  315 */       int igamma = (int)(gamma * maxOutSample + 0.5D);
/*  316 */       if (igamma > maxOutSample) {
/*  317 */         igamma = maxOutSample;
/*      */       }
/*  319 */       this.aE[i] = igamma;
/*      */     } 
/*      */   }
/*      */   
/*  323 */   private final byte[][] aG = new byte[][] { null, { 0, -1 }, { 0, 85, -86, -1 }, null, { 0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1 } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  329 */   private int[] aH = null;
/*      */   
/*      */   private void f(int bits) {
/*  332 */     int len = 1 << bits;
/*  333 */     this.aH = new int[len];
/*      */     
/*  335 */     if (this.V) {
/*  336 */       for (int i = 0; i < len; i++) {
/*  337 */         this.aH[i] = this.aE[i];
/*      */       }
/*      */     } else {
/*  340 */       for (int i = 0; i < len; i++) {
/*  341 */         this.aH[i] = this.aG[bits][i];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public c(InputStream stream, b decodeParam) {
/*  348 */     if (!stream.markSupported()) {
/*  349 */       stream = new BufferedInputStream(stream);
/*      */     }
/*  351 */     DataInputStream distream = new DataInputStream(stream);
/*      */     
/*  353 */     if (decodeParam == null) {
/*  354 */       decodeParam = new b();
/*      */     }
/*      */     
/*  357 */     this.R = decodeParam.a();
/*  358 */     this.S = decodeParam.b();
/*  359 */     this.T = decodeParam.c();
/*  360 */     this.W = decodeParam.g();
/*  361 */     if (decodeParam.d()) {
/*  362 */       this.Z = decodeParam.e();
/*  363 */       this.aa = decodeParam.f();
/*  364 */       this.V = true;
/*  365 */       this.T = true;
/*      */     } 
/*      */     
/*  368 */     if (this.X) {
/*  369 */       this.v.put("file_type", "PNG v. 1.0");
/*      */     }
/*      */     
/*      */     try {
/*  373 */       long magic = distream.readLong();
/*  374 */       if (magic != -8552249625308161526L) {
/*  375 */         throw new RuntimeException();
/*      */       }
/*  377 */     } catch (Exception e) {
/*  378 */       throw new RuntimeException(e);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       while (true) {
/*  385 */         String chunkType = a(distream);
/*  386 */         if (chunkType.equals("IHDR")) {
/*  387 */           a a = b(distream);
/*  388 */           a(a); continue;
/*  389 */         }  if (chunkType.equals("PLTE")) {
/*  390 */           a a = b(distream);
/*  391 */           c(a); continue;
/*  392 */         }  if (chunkType.equals("IDAT")) {
/*  393 */           a a = b(distream);
/*  394 */           this.at.add(new ByteArrayInputStream(a.d())); continue;
/*  395 */         }  if (chunkType.equals("IEND")) {
/*  396 */           a a = b(distream);
/*  397 */           b(a); break;
/*      */         } 
/*  399 */         if (chunkType.equals("bKGD")) {
/*  400 */           a a = b(distream);
/*  401 */           d(a); continue;
/*  402 */         }  if (chunkType.equals("cHRM")) {
/*  403 */           a a = b(distream);
/*  404 */           e(a); continue;
/*  405 */         }  if (chunkType.equals("gAMA")) {
/*  406 */           a a = b(distream);
/*  407 */           f(a); continue;
/*  408 */         }  if (chunkType.equals("hIST")) {
/*  409 */           a a = b(distream);
/*  410 */           g(a); continue;
/*  411 */         }  if (chunkType.equals("iCCP")) {
/*  412 */           a a = b(distream);
/*  413 */           h(a); continue;
/*  414 */         }  if (chunkType.equals("pHYs")) {
/*  415 */           a a = b(distream);
/*  416 */           i(a); continue;
/*  417 */         }  if (chunkType.equals("sBIT")) {
/*  418 */           a a = b(distream);
/*  419 */           j(a); continue;
/*  420 */         }  if (chunkType.equals("sRGB")) {
/*  421 */           a a = b(distream);
/*  422 */           k(a); continue;
/*  423 */         }  if (chunkType.equals("tEXt")) {
/*  424 */           a a = b(distream);
/*  425 */           l(a); continue;
/*  426 */         }  if (chunkType.equals("tIME")) {
/*  427 */           a a = b(distream);
/*  428 */           m(a); continue;
/*  429 */         }  if (chunkType.equals("tRNS")) {
/*  430 */           a a = b(distream);
/*  431 */           n(a); continue;
/*  432 */         }  if (chunkType.equals("zTXt")) {
/*  433 */           a a = b(distream);
/*  434 */           o(a); continue;
/*      */         } 
/*  436 */         a chunk = b(distream);
/*      */ 
/*      */         
/*  439 */         String type = chunk.c();
/*  440 */         byte[] data = chunk.d();
/*  441 */         if (this.X) {
/*  442 */           String key = "chunk_" + this.ay++ + ":" + type;
/*  443 */           this.v.put(key.toLowerCase(), data);
/*      */         } 
/*      */       } 
/*  446 */     } catch (Exception e) {
/*  447 */       throw new RuntimeException(e);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  453 */     if (this.Q == null) {
/*  454 */       this.Q = new int[this.aw];
/*  455 */       for (int i = 0; i < this.aw; i++) {
/*  456 */         this.Q[i] = this.y;
/*      */       }
/*      */       
/*  459 */       if (this.X) {
/*  460 */         this.v.put("significant_bits", this.Q);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private static String a(DataInputStream distream) throws IOException {
/*  466 */     distream.mark(8);
/*  467 */     distream.readInt();
/*  468 */     int type = distream.readInt();
/*  469 */     distream.reset();
/*      */     
/*  471 */     String typeString = new String();
/*  472 */     typeString = typeString + (char)(type >> 24);
/*  473 */     typeString = typeString + (char)(type >> 16 & 0xFF);
/*  474 */     typeString = typeString + (char)(type >> 8 & 0xFF);
/*  475 */     typeString = typeString + (char)(type & 0xFF);
/*  476 */     return typeString;
/*      */   }
/*      */   
/*      */   private a b(DataInputStream distream) throws IOException {
/*  480 */     int len, crc, length = distream.readInt();
/*  481 */     int type = distream.readInt();
/*  482 */     int remainder = length;
/*  483 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*      */     
/*  485 */     if (remainder > 0) {
/*  486 */       len = distream.read(this.aF, 0, Math.min(remainder, this.aF.length));
/*  487 */       for (; len != -1 && remainder > 0; len = distream.read(this.aF, 0, Math.min(remainder, this.aF.length))) {
/*  488 */         out.write(this.aF, 0, len);
/*  489 */         remainder -= length;
/*      */       } 
/*      */     } else {
/*  492 */       len = 0;
/*      */     } 
/*  494 */     byte[] data = out.toByteArray();
/*      */     
/*  496 */     if (len != -1) {
/*      */       try {
/*  498 */         crc = distream.readInt();
/*  499 */       } catch (EOFException e) {
/*  500 */         crc = 0;
/*      */       } 
/*      */     } else {
/*  503 */       crc = 0;
/*      */     } 
/*  505 */     return new a(data.length, type, data, crc);
/*      */   }
/*      */   
/*      */   private void a(a chunk) {
/*  509 */     this.o = this.m = chunk.d(0);
/*  510 */     this.p = this.n = chunk.d(4);
/*      */     
/*  512 */     this.y = chunk.b(8);
/*      */     
/*  514 */     if (this.y != 1 && this.y != 2 && this.y != 4 && this.y != 8 && this.y != 16)
/*      */     {
/*      */       
/*  517 */       throw new RuntimeException();
/*      */     }
/*  519 */     this.P = (1 << this.y) - 1;
/*      */     
/*  521 */     this.z = chunk.b(9);
/*      */     
/*  523 */     if (this.z == 2 && this.y < 8)
/*      */     {
/*  525 */       throw new RuntimeException();
/*      */     }
/*      */     
/*  528 */     if (this.z == 3 && this.y == 16)
/*      */     {
/*  530 */       throw new RuntimeException();
/*      */     }
/*      */     
/*  533 */     if (this.z == 4 && this.y < 8)
/*      */     {
/*  535 */       throw new RuntimeException();
/*      */     }
/*      */     
/*  538 */     if (this.z == 6 && this.y < 8)
/*      */     {
/*  540 */       throw new RuntimeException();
/*      */     }
/*      */     
/*  543 */     if (this.X) {
/*  544 */       this.v.put("color_type", w[this.z]);
/*      */     }
/*      */     
/*  547 */     if (this.X) {
/*  548 */       this.v.put("bit_depth", d.a(this.y));
/*      */     }
/*      */     
/*  551 */     if (this.V) {
/*      */       
/*  553 */       double gamma = 0.45454543828964233D * this.aa / this.Z;
/*  554 */       if (this.X) {
/*  555 */         this.v.put("gamma", new Float(gamma));
/*      */       }
/*      */     } 
/*      */     
/*  559 */     this.A = chunk.b(10);
/*  560 */     if (this.A != 0)
/*      */     {
/*  562 */       throw new RuntimeException();
/*      */     }
/*      */     
/*  565 */     this.B = chunk.b(11);
/*  566 */     if (this.B != 0)
/*      */     {
/*  568 */       throw new RuntimeException();
/*      */     }
/*      */     
/*  571 */     this.C = chunk.b(12);
/*  572 */     if (this.C == 0) {
/*  573 */       if (this.X) {
/*  574 */         this.v.put("interlace_method", "None");
/*      */       }
/*  576 */     } else if (this.C == 1) {
/*  577 */       if (this.X) {
/*  578 */         this.v.put("interlace_method", "Adam7");
/*      */       }
/*      */     } else {
/*      */       
/*  582 */       throw new RuntimeException();
/*      */     } 
/*      */     
/*  585 */     this.av = (this.y == 16) ? 2 : 1;
/*      */     
/*  587 */     switch (this.z) {
/*      */       case 0:
/*  589 */         this.aw = 1;
/*  590 */         this.ax = 1;
/*      */         
/*  592 */         if (this.T && this.y < 8) {
/*  593 */           this.ad = 2; break;
/*  594 */         }  if (this.V) {
/*  595 */           this.ad = 1; break;
/*      */         } 
/*  597 */         this.ad = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  602 */         this.aw = 3;
/*  603 */         this.av *= 3;
/*  604 */         this.ax = 3;
/*      */         
/*  606 */         if (this.V) {
/*  607 */           this.ad = 1; break;
/*      */         } 
/*  609 */         this.ad = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*  614 */         this.aw = 1;
/*  615 */         this.av = 1;
/*  616 */         this.ax = this.S ? 3 : 1;
/*      */         
/*  618 */         if (this.S) {
/*  619 */           this.ad = 4; break;
/*      */         } 
/*  621 */         this.ad = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  626 */         this.aw = 2;
/*  627 */         this.av *= 2;
/*      */         
/*  629 */         if (this.R) {
/*  630 */           this.ax = 1;
/*  631 */           this.ad = 8; break;
/*      */         } 
/*  633 */         if (this.V) {
/*  634 */           this.ad = 1;
/*      */         } else {
/*  636 */           this.ad = 0;
/*      */         } 
/*  638 */         if (this.W) {
/*  639 */           this.ad |= 0x10;
/*  640 */           this.ax = 4; break;
/*      */         } 
/*  642 */         this.ax = 2;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/*  648 */         this.aw = 4;
/*  649 */         this.av *= 4;
/*  650 */         this.ax = !this.R ? 4 : 3;
/*      */         
/*  652 */         if (this.R) {
/*  653 */           this.ad = 9; break;
/*  654 */         }  if (this.V) {
/*  655 */           this.ad = 1; break;
/*      */         } 
/*  657 */         this.ad = 0;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void b(a chunk) throws Exception {
/*  665 */     int textLen = this.az.size();
/*  666 */     String[] textArray = new String[2 * textLen];
/*  667 */     for (int i = 0; i < textLen; i++) {
/*  668 */       String key = this.az.elementAt(i);
/*  669 */       String val = this.aA.elementAt(i);
/*  670 */       textArray[2 * i] = key;
/*  671 */       textArray[2 * i + 1] = val;
/*  672 */       if (this.X) {
/*  673 */         String uniqueKey = "text_" + i + ":" + key;
/*  674 */         this.v.put(uniqueKey.toLowerCase(), val);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  679 */     int ztextLen = this.aB.size();
/*  680 */     String[] ztextArray = new String[2 * ztextLen];
/*  681 */     for (int j = 0; j < ztextLen; j++) {
/*  682 */       String key = this.aB.elementAt(j);
/*  683 */       String val = this.aC.elementAt(j);
/*  684 */       ztextArray[2 * j] = key;
/*  685 */       ztextArray[2 * j + 1] = val;
/*  686 */       if (this.X) {
/*  687 */         String uniqueKey = "ztext_" + j + ":" + key;
/*  688 */         this.v.put(uniqueKey.toLowerCase(), val);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  693 */     InputStream seqStream = new SequenceInputStream(this.at.elements());
/*  694 */     InputStream infStream = new InflaterInputStream(seqStream, new Inflater());
/*  695 */     this.au = new DataInputStream(infStream);
/*      */ 
/*      */     
/*  698 */     int depth = this.y;
/*  699 */     if (this.z == 0 && this.y < 8 && this.T) {
/*  700 */       depth = 8;
/*      */     }
/*  702 */     if (this.z == 3 && this.S) {
/*  703 */       depth = 8;
/*      */     }
/*  705 */     int bytesPerRow = (this.ax * this.m * depth + 7) / 8;
/*  706 */     int scanlineStride = (depth == 16) ? (bytesPerRow / 2) : bytesPerRow;
/*      */     
/*  708 */     this.aD = a(this.m, this.n, this.ax, scanlineStride, depth);
/*      */     
/*  710 */     if (this.V && this.aE == null) {
/*  711 */       e(this.y);
/*      */     }
/*  713 */     if (this.ad == 2 || this.ad == 3 || this.ad == 19)
/*      */     {
/*  715 */       f(this.y);
/*      */     }
/*      */     
/*  718 */     a((this.C == 1));
/*  719 */     this.s = this.aD.getSampleModel();
/*      */     
/*  721 */     if (this.z == 3 && !this.S) {
/*  722 */       if (this.U) {
/*  723 */         this.t = new IndexColorModel(this.y, this.D, this.E, this.F, this.G, this.H);
/*      */       } else {
/*      */         
/*  726 */         this.t = new IndexColorModel(this.y, this.D, this.E, this.F, this.G);
/*      */       }
/*      */     
/*  729 */     } else if (this.z == 0 && this.y < 8 && !this.T) {
/*  730 */       byte[] palette = this.aG[this.y];
/*  731 */       this.t = new IndexColorModel(this.y, palette.length, palette, palette, palette);
/*      */     } else {
/*  733 */       this.t = a(this.s);
/*      */     } 
/*      */   }
/*      */   
/*  737 */   private static final int[] aI = new int[] { 8 };
/*      */   
/*  739 */   private static final ComponentColorModel aJ = new ComponentColorModel(
/*  740 */       ColorSpace.getInstance(1003), aI, false, false, 1, 0);
/*      */ 
/*      */   
/*  743 */   private static final int[] aK = new int[] { 8, 8 };
/*      */   
/*  745 */   private static final ComponentColorModel aL = new ComponentColorModel(
/*  746 */       ColorSpace.getInstance(1003), aK, true, false, 3, 0);
/*      */ 
/*      */   
/*  749 */   private static final int[] aM = new int[] { 16 };
/*      */   
/*  751 */   private static final ComponentColorModel aN = new ComponentColorModel(
/*  752 */       ColorSpace.getInstance(1003), aM, false, false, 1, 1);
/*      */ 
/*      */   
/*  755 */   private static final int[] aO = new int[] { 16, 16 };
/*      */   
/*  757 */   private static final ComponentColorModel aP = new ComponentColorModel(
/*  758 */       ColorSpace.getInstance(1003), aO, true, false, 3, 1);
/*      */ 
/*      */   
/*  761 */   private static final int[] aQ = new int[] { 32 };
/*      */   
/*  763 */   private static final ComponentColorModel aR = new ComponentColorModel(
/*  764 */       ColorSpace.getInstance(1003), aQ, false, false, 1, 3);
/*      */ 
/*      */   
/*  767 */   private static final int[] aS = new int[] { 32, 32 };
/*      */   
/*  769 */   private static final ComponentColorModel aT = new ComponentColorModel(
/*  770 */       ColorSpace.getInstance(1003), aS, true, false, 3, 3);
/*      */ 
/*      */   
/*  773 */   private static final int[] aU = new int[] { 8, 8, 8 };
/*      */   
/*  775 */   private static final ComponentColorModel aV = new ComponentColorModel(
/*  776 */       ColorSpace.getInstance(1000), aU, false, false, 1, 0);
/*      */ 
/*      */   
/*  779 */   private static final int[] aW = new int[] { 8, 8, 8, 8 };
/*      */   
/*  781 */   private static final ComponentColorModel aX = new ComponentColorModel(
/*  782 */       ColorSpace.getInstance(1000), aW, true, false, 3, 0);
/*      */ 
/*      */   
/*  785 */   private static final int[] aY = new int[] { 16, 16, 16 };
/*      */   
/*  787 */   private static final ComponentColorModel aZ = new ComponentColorModel(
/*  788 */       ColorSpace.getInstance(1000), aY, false, false, 1, 1);
/*      */ 
/*      */   
/*  791 */   private static final int[] ba = new int[] { 16, 16, 16, 16 };
/*      */   
/*  793 */   private static final ComponentColorModel bb = new ComponentColorModel(
/*  794 */       ColorSpace.getInstance(1000), ba, true, false, 3, 1);
/*      */ 
/*      */   
/*  797 */   private static final int[] bc = new int[] { 32, 32, 32 };
/*      */   
/*  799 */   private static final ComponentColorModel bd = new ComponentColorModel(
/*  800 */       ColorSpace.getInstance(1000), bc, false, false, 1, 3);
/*      */ 
/*      */   
/*  803 */   private static final int[] be = new int[] { 32, 32, 32, 32 };
/*      */   
/*  805 */   private static final ComponentColorModel bf = new ComponentColorModel(
/*  806 */       ColorSpace.getInstance(1000), be, true, false, 3, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ColorModel a(SampleModel sm) {
/*  819 */     int type = sm.getDataType();
/*  820 */     int bands = sm.getNumBands();
/*  821 */     ComponentColorModel cm = null;
/*      */     
/*  823 */     if (type == 0) {
/*  824 */       switch (bands) {
/*      */         case 1:
/*  826 */           cm = aJ;
/*      */           break;
/*      */         case 2:
/*  829 */           cm = aL;
/*      */           break;
/*      */         case 3:
/*  832 */           cm = aV;
/*      */           break;
/*      */         case 4:
/*  835 */           cm = aX;
/*      */           break;
/*      */       } 
/*  838 */     } else if (type == 1) {
/*  839 */       switch (bands) {
/*      */         case 1:
/*  841 */           cm = aN;
/*      */           break;
/*      */         case 2:
/*  844 */           cm = aP;
/*      */           break;
/*      */         case 3:
/*  847 */           cm = aZ;
/*      */           break;
/*      */         case 4:
/*  850 */           cm = bb;
/*      */           break;
/*      */       } 
/*  853 */     } else if (type == 3) {
/*  854 */       switch (bands) {
/*      */         case 1:
/*  856 */           cm = aR;
/*      */           break;
/*      */         case 2:
/*  859 */           cm = aT;
/*      */           break;
/*      */         case 3:
/*  862 */           cm = bd;
/*      */           break;
/*      */         case 4:
/*  865 */           cm = bf;
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/*  870 */     return cm;
/*      */   }
/*      */   
/*      */   private void c(a chunk) {
/*  874 */     this.D = chunk.a() / 3;
/*  875 */     this.E = new byte[this.D];
/*  876 */     this.F = new byte[this.D];
/*  877 */     this.G = new byte[this.D];
/*      */     
/*  879 */     int pltIndex = 0;
/*      */ 
/*      */     
/*  882 */     if (this.V) {
/*  883 */       if (this.aE == null) {
/*  884 */         e((this.y == 16) ? 16 : 8);
/*      */       }
/*      */       
/*  887 */       for (int i = 0; i < this.D; i++) {
/*  888 */         byte r = chunk.a(pltIndex++);
/*  889 */         byte g = chunk.a(pltIndex++);
/*  890 */         byte b = chunk.a(pltIndex++);
/*      */         
/*  892 */         this.E[i] = (byte)this.aE[r & 0xFF];
/*  893 */         this.F[i] = (byte)this.aE[g & 0xFF];
/*  894 */         this.G[i] = (byte)this.aE[b & 0xFF];
/*      */       } 
/*      */     } else {
/*  897 */       for (int i = 0; i < this.D; i++) {
/*  898 */         this.E[i] = chunk.a(pltIndex++);
/*  899 */         this.F[i] = chunk.a(pltIndex++);
/*  900 */         this.G[i] = chunk.a(pltIndex++);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   private void d(a chunk) {
/*      */     int bkgdIndex, bkgdGray, bkgdRGB[];
/*  906 */     switch (this.z) {
/*      */       case 3:
/*  908 */         bkgdIndex = chunk.a(0) & 0xFF;
/*      */         
/*  910 */         this.I = this.E[bkgdIndex] & 0xFF;
/*  911 */         this.J = this.F[bkgdIndex] & 0xFF;
/*  912 */         this.K = this.G[bkgdIndex] & 0xFF;
/*      */         break;
/*      */       
/*      */       case 0:
/*      */       case 4:
/*  917 */         bkgdGray = chunk.c(0);
/*  918 */         this.I = this.J = this.K = bkgdGray;
/*      */         break;
/*      */       
/*      */       case 2:
/*      */       case 6:
/*  923 */         this.I = chunk.c(0);
/*  924 */         this.J = chunk.c(2);
/*  925 */         this.K = chunk.c(4);
/*      */         
/*  927 */         bkgdRGB = new int[3];
/*  928 */         bkgdRGB[0] = this.I;
/*  929 */         bkgdRGB[1] = this.J;
/*  930 */         bkgdRGB[2] = this.K;
/*      */         break;
/*      */     } 
/*      */     
/*  934 */     int r = 0, g = 0, b = 0;
/*  935 */     if (this.y < 8) {
/*  936 */       r = this.aG[this.y][this.I];
/*  937 */       g = this.aG[this.y][this.J];
/*  938 */       b = this.aG[this.y][this.K];
/*  939 */     } else if (this.y == 8) {
/*  940 */       r = this.I;
/*  941 */       g = this.J;
/*  942 */       b = this.K;
/*  943 */     } else if (this.y == 16) {
/*  944 */       r = this.I >> 8;
/*  945 */       g = this.J >> 8;
/*  946 */       b = this.K >> 8;
/*      */     } 
/*  948 */     if (this.X) {
/*  949 */       this.v.put("background_color", new Color(r, g, b));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void e(a chunk) {
/*  955 */     if (this.ac != -1) {
/*      */       return;
/*      */     }
/*      */     
/*  959 */     this.ab = new double[8];
/*  960 */     this.ab[0] = (chunk.d(0) / 100000.0F);
/*  961 */     this.ab[1] = (chunk.d(4) / 100000.0F);
/*  962 */     this.ab[2] = (chunk.d(8) / 100000.0F);
/*  963 */     this.ab[3] = (chunk.d(12) / 100000.0F);
/*  964 */     this.ab[4] = (chunk.d(16) / 100000.0F);
/*  965 */     this.ab[5] = (chunk.d(20) / 100000.0F);
/*  966 */     this.ab[6] = (chunk.d(24) / 100000.0F);
/*  967 */     this.ab[7] = (chunk.d(28) / 100000.0F);
/*      */     
/*  969 */     if (this.X) {
/*  970 */       this.v.put("white_point_x", new Float(this.ab[0]));
/*  971 */       this.v.put("white_point_y", new Float(this.ab[1]));
/*  972 */       this.v.put("red_x", new Float(this.ab[2]));
/*  973 */       this.v.put("red_y", new Float(this.ab[3]));
/*  974 */       this.v.put("green_x", new Float(this.ab[4]));
/*  975 */       this.v.put("green_y", new Float(this.ab[5]));
/*  976 */       this.v.put("blue_x", new Float(this.ab[6]));
/*  977 */       this.v.put("blue_y", new Float(this.ab[7]));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void f(a chunk) {
/*  983 */     if (this.ac != -1) {
/*      */       return;
/*      */     }
/*      */     
/*  987 */     this.Y = (chunk.d(0) / 100000.0F);
/*      */     
/*  989 */     double exp = this.V ? (this.aa / this.Z) : 1.0D;
/*  990 */     if (this.X) {
/*  991 */       this.v.put("gamma", new Float(this.Y * exp));
/*      */     }
/*      */   }
/*      */   
/*      */   private void g(a chunk) {
/*  996 */     if (this.E == null) {
/*  997 */       throw new RuntimeException();
/*      */     }
/*      */     
/* 1000 */     int length = this.E.length;
/* 1001 */     int[] hist = new int[length];
/* 1002 */     for (int i = 0; i < length; i++) {
/* 1003 */       hist[i] = chunk.c(2 * i);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void h(a chunk) {
/* 1011 */     int textIndex = 0;
/* 1012 */     while (chunk.a(textIndex++) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void i(a chunk) {
/* 1018 */     int xPixelsPerUnit = chunk.d(0);
/* 1019 */     int yPixelsPerUnit = chunk.d(4);
/* 1020 */     int unitSpecifier = chunk.b(8);
/*      */     
/* 1022 */     if (this.X) {
/* 1023 */       this.v.put("x_pixels_per_unit", d.a(xPixelsPerUnit));
/* 1024 */       this.v.put("y_pixels_per_unit", d.a(yPixelsPerUnit));
/* 1025 */       this.v.put("pixel_aspect_ratio", new Float(xPixelsPerUnit / yPixelsPerUnit));
/* 1026 */       if (unitSpecifier == 1) {
/* 1027 */         this.v.put("pixel_units", "Meters");
/* 1028 */       } else if (unitSpecifier != 0) {
/*      */         
/* 1030 */         throw new RuntimeException();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void j(a chunk) {
/* 1036 */     if (this.z == 3) {
/* 1037 */       this.Q = new int[3];
/*      */     } else {
/* 1039 */       this.Q = new int[this.aw];
/*      */     } 
/* 1041 */     for (int i = 0; i < this.Q.length; i++) {
/* 1042 */       int bits = chunk.a(i);
/* 1043 */       int depth = (this.z == 3) ? 8 : this.y;
/* 1044 */       if (bits <= 0 || bits > depth)
/*      */       {
/*      */         
/* 1047 */         throw new RuntimeException();
/*      */       }
/* 1049 */       this.Q[i] = bits;
/*      */     } 
/*      */     
/* 1052 */     if (this.X) {
/* 1053 */       this.v.put("significant_bits", this.Q);
/*      */     }
/*      */   }
/*      */   
/*      */   private void k(a chunk) {
/* 1058 */     this.ac = chunk.a(0);
/*      */ 
/*      */ 
/*      */     
/* 1062 */     this.Y = 0.45454999804496765D;
/*      */     
/* 1064 */     this.ab = new double[8];
/* 1065 */     this.ab[0] = 3.127000093460083D;
/* 1066 */     this.ab[1] = 3.2899999618530273D;
/* 1067 */     this.ab[2] = 6.400000095367432D;
/* 1068 */     this.ab[3] = 3.299999952316284D;
/* 1069 */     this.ab[4] = 3.0D;
/* 1070 */     this.ab[5] = 6.0D;
/* 1071 */     this.ab[6] = 1.5D;
/* 1072 */     this.ab[7] = 0.6000000238418579D;
/*      */     
/* 1074 */     if (this.V) {
/*      */       
/* 1076 */       double gamma = this.Y * this.aa / this.Z;
/* 1077 */       if (this.X) {
/* 1078 */         this.v.put("gamma", new Float(gamma));
/* 1079 */         this.v.put("white_point_x", new Float(this.ab[0]));
/* 1080 */         this.v.put("white_point_y", new Float(this.ab[1]));
/* 1081 */         this.v.put("red_x", new Float(this.ab[2]));
/* 1082 */         this.v.put("red_y", new Float(this.ab[3]));
/* 1083 */         this.v.put("green_x", new Float(this.ab[4]));
/* 1084 */         this.v.put("green_y", new Float(this.ab[5]));
/* 1085 */         this.v.put("blue_x", new Float(this.ab[6]));
/* 1086 */         this.v.put("blue_y", new Float(this.ab[7]));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void l(a chunk) {
/* 1092 */     String key = new String();
/* 1093 */     String value = new String();
/*      */ 
/*      */     
/* 1096 */     int textIndex = 0; byte b;
/* 1097 */     while ((b = chunk.a(textIndex++)) != 0) {
/* 1098 */       key = key + (char)b;
/*      */     }
/*      */     
/* 1101 */     for (int i = textIndex; i < chunk.a(); i++) {
/* 1102 */       value = value + (char)chunk.a(i);
/*      */     }
/*      */     
/* 1105 */     this.az.add(key);
/* 1106 */     this.aA.add(value);
/*      */   }
/*      */   
/*      */   private void m(a chunk) {
/* 1110 */     int year = chunk.c(0);
/* 1111 */     int month = chunk.b(2) - 1;
/* 1112 */     int day = chunk.b(3);
/* 1113 */     int hour = chunk.b(4);
/* 1114 */     int minute = chunk.b(5);
/* 1115 */     int second = chunk.b(6);
/*      */     
/* 1117 */     TimeZone gmt = TimeZone.getTimeZone("GMT");
/*      */     
/* 1119 */     GregorianCalendar cal = new GregorianCalendar(gmt);
/* 1120 */     cal.set(year, month, day, hour, minute, second);
/* 1121 */     Date date = cal.getTime();
/*      */     
/* 1123 */     if (this.X) {
/* 1124 */       this.v.put("timestamp", date);
/*      */     }
/*      */   }
/*      */   
/*      */   private void n(a chunk) {
/* 1129 */     if (this.z == 3) {
/* 1130 */       int entries = chunk.a();
/* 1131 */       if (entries > this.D)
/*      */       {
/* 1133 */         throw new RuntimeException();
/*      */       }
/*      */ 
/*      */       
/* 1137 */       this.H = new byte[this.D]; int i;
/* 1138 */       for (i = 0; i < entries; i++) {
/* 1139 */         this.H[i] = chunk.a(i);
/*      */       }
/*      */ 
/*      */       
/* 1143 */       for (i = entries; i < this.D; i++) {
/* 1144 */         this.H[i] = -1;
/*      */       }
/*      */       
/* 1147 */       if (!this.R) {
/* 1148 */         if (this.S) {
/* 1149 */           this.ad = 5;
/* 1150 */           this.ax = 4;
/*      */         } else {
/* 1152 */           this.U = true;
/*      */         } 
/*      */       }
/* 1155 */     } else if (this.z == 0) {
/* 1156 */       this.L = chunk.c(0);
/*      */       
/* 1158 */       if (!this.R) {
/* 1159 */         if (this.y < 8) {
/* 1160 */           this.T = true;
/* 1161 */           this.P = 255;
/* 1162 */           this.ad = 3;
/*      */         } else {
/* 1164 */           this.ad = 6;
/*      */         } 
/*      */         
/* 1167 */         if (this.W) {
/* 1168 */           this.ax = 4;
/* 1169 */           this.ad |= 0x10;
/*      */         } else {
/* 1171 */           this.ax = 2;
/*      */         } 
/*      */       } 
/* 1174 */     } else if (this.z == 2) {
/* 1175 */       this.M = chunk.c(0);
/* 1176 */       this.N = chunk.c(2);
/* 1177 */       this.O = chunk.c(4);
/*      */       
/* 1179 */       if (!this.R) {
/* 1180 */         this.ax = 4;
/* 1181 */         this.ad = 7;
/*      */       } 
/* 1183 */     } else if (this.z == 4 || this.z == 6) {
/*      */       
/* 1185 */       throw new RuntimeException();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void o(a chunk) throws IOException {
/* 1190 */     String key = new String();
/* 1191 */     String value = new String();
/*      */ 
/*      */     
/* 1194 */     int textIndex = 0; byte b;
/* 1195 */     while ((b = chunk.a(textIndex++)) != 0) {
/* 1196 */       key = key + (char)b;
/*      */     }
/* 1198 */     chunk.a(textIndex++);
/*      */     
/* 1200 */     int length = chunk.a() - textIndex;
/* 1201 */     byte[] data = chunk.d();
/* 1202 */     InputStream cis = new ByteArrayInputStream(data, textIndex, length);
/* 1203 */     InputStream iis = new InflaterInputStream(cis);
/*      */     
/*      */     int i;
/* 1206 */     while ((i = iis.read()) != -1) {
/* 1207 */       value = value + (char)i;
/*      */     }
/*      */     
/* 1210 */     this.aB.add(key);
/* 1211 */     this.aC.add(value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private WritableRaster a(int width, int height, int bands, int scanlineStride, int bitDepth) {
/* 1217 */     WritableRaster ras = null;
/* 1218 */     Point origin = new Point(0, 0);
/* 1219 */     if (bitDepth < 8 && bands == 1) {
/* 1220 */       DataBuffer dataBuffer = new DataBufferByte(height * scanlineStride);
/* 1221 */       ras = Raster.createPackedRaster(dataBuffer, width, height, bitDepth, origin);
/* 1222 */     } else if (bitDepth <= 8) {
/* 1223 */       DataBuffer dataBuffer = new DataBufferByte(height * scanlineStride);
/* 1224 */       ras = Raster.createInterleavedRaster(dataBuffer, width, height, scanlineStride, bands, this.x[bands], origin);
/*      */     } else {
/*      */       
/* 1227 */       DataBuffer dataBuffer = new DataBufferUShort(height * scanlineStride);
/* 1228 */       ras = Raster.createInterleavedRaster(dataBuffer, width, height, scanlineStride, bands, this.x[bands], origin);
/*      */     } 
/*      */ 
/*      */     
/* 1232 */     return ras;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void a(byte[] curr, int count, int bpp) {
/* 1238 */     for (int i = bpp; i < count; i++) {
/*      */ 
/*      */       
/* 1241 */       int val = curr[i] & 0xFF;
/* 1242 */       val += curr[i - bpp] & 0xFF;
/*      */       
/* 1244 */       curr[i] = (byte)val;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void a(byte[] curr, byte[] prev, int count) {
/* 1249 */     for (int i = 0; i < count; i++) {
/* 1250 */       int raw = curr[i] & 0xFF;
/* 1251 */       int prior = prev[i] & 0xFF;
/*      */       
/* 1253 */       curr[i] = (byte)(raw + prior);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void a(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1260 */     for (i = 0; i < bpp; i++) {
/* 1261 */       int raw = curr[i] & 0xFF;
/* 1262 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1264 */       curr[i] = (byte)(raw + priorRow / 2);
/*      */     } 
/*      */     
/* 1267 */     for (i = bpp; i < count; i++) {
/* 1268 */       int raw = curr[i] & 0xFF;
/* 1269 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1270 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1272 */       curr[i] = (byte)(raw + (priorPixel + priorRow) / 2);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int e(int a, int b, int i) {
/* 1277 */     int p = a + b - i;
/* 1278 */     int pa = Math.abs(p - a);
/* 1279 */     int pb = Math.abs(p - b);
/* 1280 */     int pc = Math.abs(p - i);
/*      */     
/* 1282 */     if (pa <= pb && pa <= pc)
/* 1283 */       return a; 
/* 1284 */     if (pb <= pc) {
/* 1285 */       return b;
/*      */     }
/* 1287 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void b(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1294 */     for (i = 0; i < bpp; i++) {
/* 1295 */       int raw = curr[i] & 0xFF;
/* 1296 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1298 */       curr[i] = (byte)(raw + priorRow);
/*      */     } 
/*      */     
/* 1301 */     for (i = bpp; i < count; i++) {
/* 1302 */       int raw = curr[i] & 0xFF;
/* 1303 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1304 */       int priorRow = prev[i] & 0xFF;
/* 1305 */       int priorRowPixel = prev[i - bpp] & 0xFF;
/*      */       
/* 1307 */       curr[i] = (byte)(raw + e(priorPixel, priorRow, priorRowPixel));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(int process, Raster src, WritableRaster dst, int xOffset, int step, int y, int width) {
/* 1315 */     int srcX, ps[] = src.getPixel(0, 0, (int[])null);
/* 1316 */     int[] pd = dst.getPixel(0, 0, (int[])null);
/*      */     
/* 1318 */     int dstX = xOffset;
/* 1319 */     switch (process) {
/*      */       case 0:
/* 1321 */         for (srcX = 0; srcX < width; srcX++) {
/* 1322 */           src.getPixel(srcX, 0, ps);
/* 1323 */           dst.setPixel(dstX, y, ps);
/* 1324 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 1:
/* 1329 */         for (srcX = 0; srcX < width; srcX++) {
/* 1330 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1332 */           for (int i = 0; i < this.aw; i++) {
/* 1333 */             int x = ps[i];
/* 1334 */             ps[i] = this.aE[x];
/*      */           } 
/*      */           
/* 1337 */           dst.setPixel(dstX, y, ps);
/* 1338 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 2:
/* 1343 */         for (srcX = 0; srcX < width; srcX++) {
/* 1344 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1346 */           pd[0] = this.aH[ps[0]];
/*      */           
/* 1348 */           dst.setPixel(dstX, y, pd);
/* 1349 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 3:
/* 1354 */         for (srcX = 0; srcX < width; srcX++) {
/* 1355 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1357 */           int val = ps[0];
/* 1358 */           pd[0] = this.aH[val];
/* 1359 */           if (val == this.L) {
/* 1360 */             pd[1] = 0;
/*      */           } else {
/* 1362 */             pd[1] = this.P;
/*      */           } 
/*      */           
/* 1365 */           dst.setPixel(dstX, y, pd);
/* 1366 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 4:
/* 1371 */         for (srcX = 0; srcX < width; srcX++) {
/* 1372 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1374 */           int val = ps[0];
/* 1375 */           pd[0] = this.E[val];
/* 1376 */           pd[1] = this.F[val];
/* 1377 */           pd[2] = this.G[val];
/*      */           
/* 1379 */           dst.setPixel(dstX, y, pd);
/* 1380 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 5:
/* 1385 */         for (srcX = 0; srcX < width; srcX++) {
/* 1386 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1388 */           int val = ps[0];
/* 1389 */           pd[0] = this.E[val];
/* 1390 */           pd[1] = this.F[val];
/* 1391 */           pd[2] = this.G[val];
/* 1392 */           pd[3] = this.H[val];
/*      */           
/* 1394 */           dst.setPixel(dstX, y, pd);
/* 1395 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 6:
/* 1400 */         for (srcX = 0; srcX < width; srcX++) {
/* 1401 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1403 */           int val = ps[0];
/* 1404 */           if (this.V) {
/* 1405 */             val = this.aE[val];
/*      */           }
/* 1407 */           pd[0] = val;
/* 1408 */           if (val == this.L) {
/* 1409 */             pd[1] = 0;
/*      */           } else {
/* 1411 */             pd[1] = this.P;
/*      */           } 
/*      */           
/* 1414 */           dst.setPixel(dstX, y, pd);
/* 1415 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 7:
/* 1420 */         for (srcX = 0; srcX < width; srcX++) {
/* 1421 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1423 */           int r = ps[0];
/* 1424 */           int g = ps[1];
/* 1425 */           int b = ps[2];
/* 1426 */           if (this.V) {
/* 1427 */             pd[0] = this.aE[r];
/* 1428 */             pd[1] = this.aE[g];
/* 1429 */             pd[2] = this.aE[b];
/*      */           } else {
/* 1431 */             pd[0] = r;
/* 1432 */             pd[1] = g;
/* 1433 */             pd[2] = b;
/*      */           } 
/* 1435 */           if (r == this.M && g == this.N && b == this.O) {
/*      */             
/* 1437 */             pd[3] = 0;
/*      */           } else {
/* 1439 */             pd[3] = this.P;
/*      */           } 
/*      */           
/* 1442 */           dst.setPixel(dstX, y, pd);
/* 1443 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 8:
/* 1448 */         for (srcX = 0; srcX < width; srcX++) {
/* 1449 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1451 */           int g = ps[0];
/* 1452 */           if (this.V) {
/* 1453 */             pd[0] = this.aE[g];
/*      */           } else {
/* 1455 */             pd[0] = g;
/*      */           } 
/*      */           
/* 1458 */           dst.setPixel(dstX, y, pd);
/* 1459 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 9:
/* 1464 */         for (srcX = 0; srcX < width; srcX++) {
/* 1465 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1467 */           int r = ps[0];
/* 1468 */           int g = ps[1];
/* 1469 */           int b = ps[2];
/* 1470 */           if (this.V) {
/* 1471 */             pd[0] = this.aE[r];
/* 1472 */             pd[1] = this.aE[g];
/* 1473 */             pd[2] = this.aE[b];
/*      */           } else {
/* 1475 */             pd[0] = r;
/* 1476 */             pd[1] = g;
/* 1477 */             pd[2] = b;
/*      */           } 
/*      */           
/* 1480 */           dst.setPixel(dstX, y, pd);
/* 1481 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 17:
/* 1486 */         for (srcX = 0; srcX < width; srcX++) {
/* 1487 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1489 */           int val = ps[0];
/* 1490 */           int alpha = ps[1];
/* 1491 */           int gamma = this.aE[val];
/* 1492 */           pd[0] = gamma;
/* 1493 */           pd[1] = gamma;
/* 1494 */           pd[2] = gamma;
/* 1495 */           pd[3] = alpha;
/*      */           
/* 1497 */           dst.setPixel(dstX, y, pd);
/* 1498 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 16:
/* 1503 */         for (srcX = 0; srcX < width; srcX++) {
/* 1504 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1506 */           int val = ps[0];
/* 1507 */           int alpha = ps[1];
/* 1508 */           pd[0] = val;
/* 1509 */           pd[1] = val;
/* 1510 */           pd[2] = val;
/* 1511 */           pd[3] = alpha;
/*      */           
/* 1513 */           dst.setPixel(dstX, y, pd);
/* 1514 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 22:
/* 1519 */         for (srcX = 0; srcX < width; srcX++) {
/* 1520 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1522 */           int val = ps[0];
/* 1523 */           if (this.V) {
/* 1524 */             val = this.aE[val];
/*      */           }
/* 1526 */           pd[0] = val;
/* 1527 */           pd[1] = val;
/* 1528 */           pd[2] = val;
/* 1529 */           if (val == this.L) {
/* 1530 */             pd[3] = 0;
/*      */           } else {
/* 1532 */             pd[3] = this.P;
/*      */           } 
/*      */           
/* 1535 */           dst.setPixel(dstX, y, pd);
/* 1536 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 19:
/* 1541 */         for (srcX = 0; srcX < width; srcX++) {
/* 1542 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1544 */           int val = ps[0];
/* 1545 */           int val2 = this.aH[val];
/* 1546 */           pd[0] = val2;
/* 1547 */           pd[1] = val2;
/* 1548 */           pd[2] = val2;
/* 1549 */           if (val == this.L) {
/* 1550 */             pd[3] = 0;
/*      */           } else {
/* 1552 */             pd[3] = this.P;
/*      */           } 
/*      */           
/* 1555 */           dst.setPixel(dstX, y, pd);
/* 1556 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(WritableRaster imRas, int xOffset, int yOffset, int xStep, int yStep, int passWidth, int passHeight) throws IOException {
/* 1567 */     if (passWidth == 0 || passHeight == 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1571 */     int bytesPerRow = (this.aw * passWidth * this.y + 7) / 8;
/* 1572 */     int eltsPerRow = (this.y == 16) ? (bytesPerRow / 2) : bytesPerRow;
/* 1573 */     byte[] curr = new byte[bytesPerRow];
/* 1574 */     byte[] prior = new byte[bytesPerRow];
/*      */ 
/*      */     
/* 1577 */     WritableRaster passRow = a(passWidth, 1, this.aw, eltsPerRow, this.y);
/* 1578 */     DataBuffer dataBuffer = passRow.getDataBuffer();
/* 1579 */     int type = dataBuffer.getDataType();
/* 1580 */     byte[] byteData = null;
/* 1581 */     short[] shortData = null;
/* 1582 */     if (type == 0) {
/* 1583 */       byteData = ((DataBufferByte)dataBuffer).getData();
/*      */     } else {
/* 1585 */       shortData = ((DataBufferUShort)dataBuffer).getData();
/*      */     } 
/*      */     
/*      */     int dstY;
/*      */     
/* 1590 */     for (int srcY = 0; srcY < passHeight; srcY++, dstY += yStep) {
/*      */       
/* 1592 */       int filter = this.au.read();
/* 1593 */       this.au.readFully(curr, 0, bytesPerRow);
/*      */       
/* 1595 */       switch (filter) {
/*      */         case 0:
/*      */           break;
/*      */         case 1:
/* 1599 */           a(curr, bytesPerRow, this.av);
/*      */           break;
/*      */         case 2:
/* 1602 */           a(curr, prior, bytesPerRow);
/*      */           break;
/*      */         case 3:
/* 1605 */           a(curr, prior, bytesPerRow, this.av);
/*      */           break;
/*      */         case 4:
/* 1608 */           b(curr, prior, bytesPerRow, this.av);
/*      */           break;
/*      */         
/*      */         default:
/* 1612 */           throw new RuntimeException();
/*      */       } 
/*      */ 
/*      */       
/* 1616 */       if (this.y < 16) {
/* 1617 */         System.arraycopy(curr, 0, byteData, 0, bytesPerRow);
/*      */       } else {
/* 1619 */         int idx = 0;
/* 1620 */         for (int j = 0; j < eltsPerRow; j++) {
/* 1621 */           shortData[j] = (short)(curr[idx] << 8 | curr[idx + 1] & 0xFF);
/* 1622 */           idx += 2;
/*      */         } 
/*      */       } 
/*      */       
/* 1626 */       a(this.ad, passRow, imRas, xOffset, xStep, dstY, passWidth);
/*      */ 
/*      */       
/* 1629 */       byte[] tmp = prior;
/* 1630 */       prior = curr;
/* 1631 */       curr = tmp;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void a(boolean useInterlacing) throws IOException {
/* 1636 */     if (!useInterlacing) {
/* 1637 */       a(this.aD, 0, 0, 1, 1, this.m, this.n);
/*      */     } else {
/* 1639 */       a(this.aD, 0, 0, 8, 8, (this.m + 7) / 8, (this.n + 7) / 8);
/* 1640 */       a(this.aD, 4, 0, 8, 8, (this.m + 3) / 8, (this.n + 7) / 8);
/* 1641 */       a(this.aD, 0, 4, 4, 8, (this.m + 3) / 4, (this.n + 3) / 8);
/* 1642 */       a(this.aD, 2, 0, 4, 4, (this.m + 1) / 4, (this.n + 3) / 4);
/* 1643 */       a(this.aD, 0, 2, 2, 4, (this.m + 1) / 2, (this.n + 1) / 4);
/* 1644 */       a(this.aD, 1, 0, 2, 2, this.m / 2, (this.n + 1) / 2);
/* 1645 */       a(this.aD, 0, 1, 1, 2, this.m, this.n / 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/* 1652 */     if (tileX != 0 || tileY != 0)
/*      */     {
/* 1654 */       throw new IllegalArgumentException();
/*      */     }
/* 1656 */     return this.aD;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/b/b/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */