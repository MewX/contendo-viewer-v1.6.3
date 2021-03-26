/*     */ package c.a.d.a;
/*     */ 
/*     */ import c.a.d.a;
/*     */ import c.a.f.f;
/*     */ import com.github.jaiimageio.jpeg2000.impl.BitsPerComponentBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.Box;
/*     */ import com.github.jaiimageio.jpeg2000.impl.ChannelDefinitionBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.ColorSpecificationBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.ComponentMappingBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.DataEntryURLBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.FileTypeBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.HeaderBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KMetadata;
/*     */ import com.github.jaiimageio.jpeg2000.impl.PaletteBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.ResolutionBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.SignatureBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.UUIDBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.UUIDListBox;
/*     */ import com.github.jaiimageio.jpeg2000.impl.XMLBox;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements a
/*     */ {
/*     */   private f E;
/*     */   private Vector F;
/*     */   private Vector G;
/*  96 */   private ColorModel H = null;
/*     */ 
/*     */   
/*     */   private J2KMetadata I;
/*     */   
/*     */   private int J;
/*     */   
/*     */   private int K;
/*     */   
/*     */   private int L;
/*     */   
/*     */   private int M;
/*     */   
/*     */   private int N;
/*     */   
/*     */   private int O;
/*     */   
/*     */   private int P;
/*     */   
/*     */   private byte[] Q;
/*     */   
/*     */   private byte[][] R;
/*     */   
/*     */   private byte[] S;
/*     */   
/*     */   private short[] T;
/*     */   
/*     */   private byte[] U;
/*     */   
/*     */   private byte[] V;
/*     */   
/*     */   private short[] W;
/*     */   
/*     */   private short[] X;
/*     */   
/*     */   private short[] Y;
/*     */   
/*     */   private int Z;
/*     */   
/*     */   private ICC_Profile aa;
/*     */ 
/*     */   
/*     */   public a(f in, J2KMetadata metadata) {
/* 139 */     this.E = in;
/* 140 */     this.I = metadata;
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
/*     */   public void a() throws IOException, EOFException {
/* 157 */     int foundCodeStreamBoxes = 0;
/*     */ 
/*     */     
/* 160 */     long longLength = 0L;
/*     */ 
/*     */     
/* 163 */     boolean jp2HeaderBoxFound = false;
/* 164 */     boolean lastBoxFound = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 172 */       int pos = this.E.getPos();
/*     */ 
/*     */ 
/*     */       
/* 176 */       if (this.E.readInt() != 12 || this.E
/* 177 */         .readInt() != 1783636000 || this.E
/* 178 */         .readInt() != 218793738) {
/* 179 */         this.E.seek(pos);
/*     */         
/* 181 */         short marker = this.E.readShort();
/* 182 */         if (marker != -177) {
/* 183 */           throw new Error("File is neither valid JP2 file nor valid JPEG 2000 codestream");
/*     */         }
/* 185 */         this.E.seek(pos);
/* 186 */         if (this.F == null)
/* 187 */           this.F = new Vector(); 
/* 188 */         this.F.addElement(new Integer(pos));
/*     */         
/*     */         return;
/*     */       } 
/* 192 */       if (this.I != null) {
/* 193 */         this.I.addNode((Box)new SignatureBox());
/*     */       }
/*     */       
/* 196 */       while (!lastBoxFound) {
/* 197 */         pos = this.E.getPos();
/* 198 */         int length = this.E.readInt();
/* 199 */         if (pos + length == this.E.length()) {
/* 200 */           lastBoxFound = true;
/*     */         }
/* 202 */         int box = this.E.readInt();
/* 203 */         if (length == 0)
/* 204 */         { lastBoxFound = true;
/* 205 */           length = this.E.length() - this.E.getPos(); }
/* 206 */         else { if (length == 1) {
/* 207 */             longLength = this.E.readLong();
/* 208 */             throw new IOException("File too long.");
/* 209 */           }  longLength = 0L; }
/*     */         
/* 211 */         pos = this.E.getPos();
/* 212 */         length -= 8;
/*     */         
/* 214 */         switch (box) {
/*     */           case 1718909296:
/* 216 */             a(length + 8, longLength);
/*     */             break;
/*     */           case 1785737827:
/* 219 */             if (!jp2HeaderBoxFound) {
/* 220 */               throw new Error("Invalid JP2 file: JP2Header box not found before Contiguous codestream box ");
/*     */             }
/*     */             
/* 223 */             b(length + 8, longLength);
/*     */             break;
/*     */           case 1785737832:
/* 226 */             if (jp2HeaderBoxFound) {
/* 227 */               throw new Error("Invalid JP2 file: Multiple JP2Header boxes found");
/*     */             }
/* 229 */             a(length + 8);
/* 230 */             jp2HeaderBoxFound = true;
/* 231 */             length = 0;
/*     */             break;
/*     */           case 1768449138:
/* 234 */             b(length);
/*     */             break;
/*     */           case 1685074537:
/* 237 */             c(length);
/*     */             break;
/*     */           case 2020437024:
/* 240 */             d(length);
/*     */             break;
/*     */           case 1969843814:
/* 243 */             length = 0;
/*     */             break;
/*     */           case 1970628964:
/* 246 */             f(length);
/*     */             break;
/*     */           case 1969451892:
/* 249 */             g(length);
/*     */             break;
/*     */           case 1970433056:
/* 252 */             e(length);
/*     */             break;
/*     */           case 1885564018:
/* 255 */             h(length + 8);
/*     */             break;
/*     */           case 1651532643:
/* 258 */             k(length);
/*     */             break;
/*     */           case 1668112752:
/* 261 */             i(length);
/*     */             break;
/*     */           case 1668246642:
/* 264 */             l(length);
/*     */             break;
/*     */           case 1667523942:
/* 267 */             j(length);
/*     */             break;
/*     */           case 1919251232:
/* 270 */             length = 0;
/*     */             break;
/*     */           case 1919251299:
/*     */           case 1919251300:
/* 274 */             a(box, length);
/*     */             break;
/*     */           default:
/* 277 */             if (this.I != null) {
/* 278 */               byte[] data = new byte[length];
/* 279 */               this.E.readFully(data, 0, length);
/* 280 */               this.I.addNode(new Box(length + 8, box, longLength, data));
/*     */             } 
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 286 */         if (!lastBoxFound)
/* 287 */           this.E.seek(pos + length); 
/*     */       } 
/* 289 */     } catch (EOFException e) {
/* 290 */       throw new Error("EOF reached before finding Contiguous Codestream Box");
/*     */     } 
/*     */ 
/*     */     
/* 294 */     if (this.F.size() == 0)
/*     */     {
/* 296 */       throw new Error("Invalid JP2 file: Contiguous codestream box missing");
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
/*     */   
/*     */   public boolean a(int length, long longLength) throws IOException, EOFException {
/* 315 */     boolean foundComp = false;
/*     */ 
/*     */     
/* 318 */     if (length == 1) {
/* 319 */       longLength = this.E.readLong();
/* 320 */       throw new IOException("File too long.");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 325 */     if (this.E.readInt() != 1785737760) {
/* 326 */       return false;
/*     */     }
/*     */     
/* 329 */     int minorVersion = this.E.readInt();
/*     */ 
/*     */ 
/*     */     
/* 333 */     int nComp = (length - 16) / 4;
/* 334 */     int[] comp = new int[nComp];
/* 335 */     for (int i = 0; i < nComp; i++) {
/* 336 */       comp[i] = this.E.readInt(); if (this.E.readInt() == 1785737760)
/* 337 */         foundComp = true; 
/*     */     } 
/* 339 */     if (!foundComp) {
/* 340 */       return false;
/*     */     }
/* 342 */     if (this.I != null) {
/* 343 */       this.I.addNode((Box)new FileTypeBox(1785737760, minorVersion, comp));
/*     */     }
/* 345 */     return true;
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
/*     */   
/*     */   public boolean a(int length) throws IOException, EOFException {
/* 367 */     if (length == 0) {
/* 368 */       throw new Error("Zero-length of JP2Header Box");
/*     */     }
/*     */     
/* 371 */     return true;
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
/*     */   public boolean b(int length) throws IOException, EOFException {
/* 387 */     if (length == 0) {
/* 388 */       throw new Error("Zero-length of JP2Header Box");
/*     */     }
/*     */ 
/*     */     
/* 392 */     this.K = this.E.readInt();
/* 393 */     this.J = this.E.readInt();
/* 394 */     this.L = this.E.readShort();
/* 395 */     this.M = this.E.readByte();
/*     */     
/* 397 */     this.N = this.E.readByte();
/* 398 */     this.O = this.E.readByte();
/* 399 */     this.P = this.E.readByte();
/*     */     
/* 401 */     if (this.I != null)
/*     */     {
/* 403 */       this.I.addNode((Box)new HeaderBox(this.K, this.J, this.L, this.M, this.N, this.O, this.P));
/*     */     }
/*     */ 
/*     */     
/* 407 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(int length, long longLength) throws IOException, EOFException {
/* 432 */     int ccpos = this.E.getPos();
/*     */     
/* 434 */     if (this.F == null)
/* 435 */       this.F = new Vector(); 
/* 436 */     this.F.addElement(new Integer(ccpos));
/*     */ 
/*     */     
/* 439 */     if (this.G == null)
/* 440 */       this.G = new Vector(); 
/* 441 */     this.G.addElement(new Integer(length));
/*     */     
/* 443 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(int length) throws IOException {
/* 450 */     if (this.I != null) {
/* 451 */       byte[] data = new byte[length];
/* 452 */       this.E.readFully(data, 0, length);
/* 453 */       this.I.addNode(new Box(length + 8, 1785737833, data));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(int length) throws IOException {
/* 461 */     if (this.I != null) {
/* 462 */       byte[] data = new byte[length];
/* 463 */       this.E.readFully(data, 0, length);
/* 464 */       this.I.addNode((Box)new XMLBox(data));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(int length) throws IOException {
/* 472 */     if (this.I != null) {
/* 473 */       byte[] data = new byte[length];
/* 474 */       this.E.readFully(data, 0, length);
/* 475 */       this.I.addNode((Box)new DataEntryURLBox(data));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void f(int length) throws IOException {
/* 483 */     if (this.I != null) {
/* 484 */       byte[] data = new byte[length];
/* 485 */       this.E.readFully(data, 0, length);
/* 486 */       this.I.addNode((Box)new UUIDBox(data));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(int length) throws IOException {
/* 494 */     if (this.I != null) {
/* 495 */       byte[] data = new byte[length];
/* 496 */       this.E.readFully(data, 0, length);
/* 497 */       this.I.addNode((Box)new UUIDListBox(data));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void h(int length) throws IOException {
/* 504 */     int pos = this.E.getPos();
/*     */     
/* 506 */     int lutSize = this.E.readShort();
/* 507 */     int numComp = this.E.readByte();
/* 508 */     this.S = new byte[numComp];
/*     */     
/* 510 */     for (int i = 0; i < numComp; i++) {
/* 511 */       this.S[i] = this.E.readByte();
/*     */     }
/*     */     
/* 514 */     this.R = new byte[numComp][lutSize];
/*     */     
/* 516 */     for (int n = 0; n < lutSize; n++) {
/* 517 */       for (int c = 0; c < numComp; c++) {
/* 518 */         int depth = 1 + (this.S[c] & Byte.MAX_VALUE);
/* 519 */         if (depth > 32)
/* 520 */           depth = 32; 
/* 521 */         int numBytes = depth + 7 >> 3;
/* 522 */         int mask = (1 << depth) - 1;
/* 523 */         byte[] buf = new byte[numBytes];
/* 524 */         this.E.readFully(buf, 0, numBytes);
/*     */         
/* 526 */         int val = 0;
/*     */         
/* 528 */         for (int k = 0; k < numBytes; k++) {
/* 529 */           val = buf[k] + (val << 8);
/*     */         }
/* 531 */         this.R[c][n] = (byte)val;
/*     */       } 
/*     */     } 
/* 534 */     if (this.I != null) {
/* 535 */       this.I.addNode((Box)new PaletteBox(length, this.S, this.R));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void i(int length) throws IOException {
/* 542 */     int num = length / 4;
/*     */     
/* 544 */     this.T = new short[num];
/* 545 */     this.U = new byte[num];
/* 546 */     this.V = new byte[num];
/*     */     
/* 548 */     for (int i = 0; i < num; i++) {
/* 549 */       this.T[i] = this.E.readShort();
/* 550 */       this.U[i] = this.E.readByte();
/* 551 */       this.V[i] = this.E.readByte();
/*     */     } 
/*     */     
/* 554 */     if (this.I != null) {
/* 555 */       this.I.addNode((Box)new ComponentMappingBox(this.T, this.U, this.V));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void j(int length) throws IOException {
/* 566 */     int num = this.E.readShort();
/* 567 */     this.W = new short[num];
/* 568 */     this.X = new short[num];
/* 569 */     this.Y = new short[num];
/*     */     
/* 571 */     for (int i = 0; i < num; i++) {
/* 572 */       this.W[i] = this.E.readShort();
/* 573 */       this.X[i] = this.E.readShort();
/* 574 */       this.Y[i] = this.E.readShort();
/*     */     } 
/* 576 */     if (this.I != null) {
/* 577 */       this.I.addNode((Box)new ChannelDefinitionBox(this.W, this.X, this.Y));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void k(int length) throws IOException {
/* 584 */     this.Q = new byte[length];
/* 585 */     this.E.readFully(this.Q, 0, length);
/*     */     
/* 587 */     if (this.I != null) {
/* 588 */       this.I.addNode((Box)new BitsPerComponentBox(this.Q));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void l(int length) throws IOException {
/* 596 */     byte method = this.E.readByte();
/*     */ 
/*     */     
/* 599 */     byte prec = this.E.readByte();
/*     */ 
/*     */     
/* 602 */     byte approx = this.E.readByte();
/*     */     
/* 604 */     if (method == 2) {
/* 605 */       byte[] data = new byte[length - 3];
/* 606 */       this.E.readFully(data, 0, data.length);
/* 607 */       this.aa = ICC_Profile.getInstance(data);
/*     */     } else {
/* 609 */       this.Z = this.E.readInt();
/*     */     } 
/* 611 */     if (this.I != null) {
/* 612 */       this.I.addNode((Box)new ColorSpecificationBox(method, prec, approx, this.Z, this.aa));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int type, int length) throws IOException {
/* 621 */     byte[] data = new byte[length];
/* 622 */     this.E.readFully(data, 0, length);
/* 623 */     if (this.I != null) {
/* 624 */       this.I.addNode((Box)new ResolutionBox(type, data));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long[] b() {
/* 635 */     int size = this.F.size();
/* 636 */     long[] pos = new long[size];
/* 637 */     for (int i = 0; i < size; i++)
/* 638 */       pos[i] = ((Integer)this.F.elementAt(i)).longValue(); 
/* 639 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int c() {
/* 649 */     return ((Integer)this.F.elementAt(0)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int d() {
/* 659 */     return ((Integer)this.G.elementAt(0)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel e() {
/* 669 */     if (this.R != null && this.L == 1) {
/* 670 */       int numComp = this.R.length;
/*     */       
/* 672 */       int maxDepth = 1 + (this.M & 0x7F);
/*     */       
/* 674 */       if (this.V == null) {
/* 675 */         this.V = new byte[numComp];
/* 676 */         for (int i = 0; i < numComp; i++)
/* 677 */           this.V[i] = (byte)i; 
/*     */       } 
/* 679 */       if (numComp == 3) {
/* 680 */         this.H = new IndexColorModel(maxDepth, (this.R[0]).length, this.R[this.V[0]], this.R[this.V[1]], this.R[this.V[2]]);
/*     */ 
/*     */       
/*     */       }
/* 684 */       else if (numComp == 4) {
/* 685 */         this.H = new IndexColorModel(maxDepth, (this.R[0]).length, this.R[this.V[0]], this.R[this.V[1]], this.R[this.V[2]], this.R[this.V[3]]);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 690 */     else if (this.W != null) {
/* 691 */       boolean hasAlpha = false;
/* 692 */       int alphaChannel = this.L - 1;
/*     */       
/* 694 */       for (int i = 0; i < this.W.length; i++) {
/* 695 */         if (this.X[i] == 1 && this.W[i] == alphaChannel) {
/* 696 */           hasAlpha = true;
/*     */         }
/*     */       } 
/* 699 */       boolean[] isPremultiplied = { false };
/*     */       
/* 701 */       if (hasAlpha) {
/* 702 */         isPremultiplied = new boolean[alphaChannel];
/*     */         int k;
/* 704 */         for (k = 0; k < alphaChannel; k++) {
/* 705 */           isPremultiplied[k] = false;
/*     */         }
/* 707 */         for (k = 0; k < this.W.length; k++) {
/* 708 */           if (this.X[k] == 2) {
/* 709 */             isPremultiplied[this.Y[k] - 1] = true;
/*     */           }
/*     */         } 
/* 712 */         for (k = 1; k < alphaChannel; k++) {
/* 713 */           isPremultiplied[0] = isPremultiplied[0] & isPremultiplied[k];
/*     */         }
/*     */       } 
/* 716 */       ColorSpace cs = null;
/* 717 */       if (this.aa != null) {
/* 718 */         cs = new ICC_ColorSpace(this.aa);
/* 719 */       } else if (this.Z == 16) {
/* 720 */         cs = ColorSpace.getInstance(1000);
/* 721 */       } else if (this.Z == 17) {
/* 722 */         cs = ColorSpace.getInstance(1003);
/* 723 */       } else if (this.Z == 18) {
/* 724 */         cs = ColorSpace.getInstance(1002);
/*     */       } 
/* 726 */       int[] bits = new int[this.L];
/* 727 */       for (int j = 0; j < this.L; j++) {
/* 728 */         if (this.Q != null) {
/* 729 */           bits[j] = (this.Q[j] & Byte.MAX_VALUE) + 1;
/*     */         } else {
/* 731 */           bits[j] = (this.M & 0x7F) + 1;
/*     */         } 
/* 733 */       }  int maxBitDepth = 1 + (this.M & 0x7F);
/* 734 */       boolean isSigned = ((this.M & 0x80) == 128);
/* 735 */       if (this.Q != null) {
/* 736 */         isSigned = ((this.Q[0] & 0x80) == 128);
/*     */       }
/* 738 */       if (this.Q != null)
/* 739 */         for (int k = 0; k < this.L; k++) {
/* 740 */           if (bits[k] > maxBitDepth)
/* 741 */             maxBitDepth = bits[k]; 
/*     */         }  
/* 743 */       int type = -1;
/*     */       
/* 745 */       if (maxBitDepth <= 8) {
/* 746 */         type = 0;
/* 747 */       } else if (maxBitDepth <= 16) {
/* 748 */         type = isSigned ? 2 : 1;
/* 749 */       } else if (maxBitDepth <= 32) {
/* 750 */         type = 3;
/*     */       } 
/* 752 */       if (type == -1) {
/* 753 */         return null;
/*     */       }
/* 755 */       if (cs != null) {
/* 756 */         this.H = new ComponentColorModel(cs, bits, hasAlpha, isPremultiplied[0], hasAlpha ? 3 : 1, type);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 764 */     return this.H;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/d/a/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */