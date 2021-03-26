/*      */ package c.a.a.a;
/*      */ 
/*      */ import c.a.a.d;
/*      */ import c.a.a.e;
/*      */ import c.a.a.g;
/*      */ import c.a.a.h;
/*      */ import c.a.b;
/*      */ import c.a.b.a;
/*      */ import c.a.c.a.d;
/*      */ import c.a.c.f;
/*      */ import c.a.e;
/*      */ import c.a.f;
/*      */ import c.a.f.f;
/*      */ import c.a.g.a.e;
/*      */ import c.a.i.a;
/*      */ import c.a.i.e;
/*      */ import c.a.j.b.i;
/*      */ import c.a.j.e;
/*      */ import com.github.jaiimageio.jpeg2000.impl.J2KImageReadParamJava;
/*      */ import java.awt.Point;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Vector;
/*      */ import javax.imageio.stream.ImageInputStream;
/*      */ import javax.imageio.stream.MemoryCacheImageInputStream;
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
/*      */   extends a
/*      */   implements e, h, f
/*      */ {
/*      */   private boolean ar = true;
/*      */   public e ae;
/*      */   private J2KImageReadParamJava as;
/*      */   private f at;
/*      */   private int au;
/*      */   private int[][] av;
/*      */   private int[] aw;
/*      */   
/*      */   public int i(int t) {
/*  121 */     if (this.av == null || this.av[t] == null) {
/*  122 */       throw new Error("Tile " + t + " not found in input codestream.");
/*      */     }
/*  124 */     return (this.av[t]).length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean ax = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] ay;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][] az;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] aA;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] aB;
/*      */ 
/*      */ 
/*      */   
/*      */   private int aC;
/*      */ 
/*      */ 
/*      */   
/*      */   private double aD;
/*      */ 
/*      */ 
/*      */   
/*      */   private int aE;
/*      */ 
/*      */ 
/*      */   
/*  162 */   private int aF = 0;
/*      */ 
/*      */   
/*      */   private int[][] aG;
/*      */ 
/*      */   
/*      */   private Vector aH;
/*      */ 
/*      */   
/*      */   private boolean aI;
/*      */ 
/*      */   
/*      */   private int aJ;
/*      */ 
/*      */   
/*      */   private int[] aK;
/*      */ 
/*      */   
/*  180 */   private int aL = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] aM;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] aN;
/*      */ 
/*      */   
/*      */   private int aO;
/*      */ 
/*      */   
/*      */   private int[][] aP;
/*      */ 
/*      */   
/*      */   private boolean aQ = false;
/*      */ 
/*      */   
/*      */   private d aR;
/*      */ 
/*      */   
/*      */   private b[][][][][] aS;
/*      */ 
/*      */   
/*      */   private int aT;
/*      */ 
/*      */ 
/*      */   
/*      */   public b[][][][][] q() {
/*  211 */     return this.aS;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean aU = false;
/*      */   
/*      */   long[][] af;
/*      */   
/*      */   int ag;
/*      */   
/*      */   int ah;
/*      */   
/*      */   int ai;
/*      */   
/*      */   int aj;
/*      */   
/*      */   int ak;
/*      */   
/*      */   int al;
/*      */   
/*      */   boolean am;
/*      */   
/*      */   int an;
/*      */   
/*      */   int ao;
/*      */   
/*      */   int ap;
/*      */   
/*      */   int aq;
/*      */   
/*      */   public c(d hd, f ehs, a decSpec, J2KImageReadParamJava j2krparam, boolean cdstrInfo, d hi) throws IOException
/*      */   {
/*  243 */     super(hd, decSpec);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  355 */     this.af = (long[][])null;
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
/*  494 */     this.ag = 0;
/*  495 */     this.ah = 0; this.ai = -1; this.aj = 0; this.ak = 0;
/*  496 */     this.al = 0;
/*  497 */     this.am = false;
/*  498 */     this.an = 0;
/*  499 */     this.ao = this.au;
/*  500 */     this.ap = 0; this.aq = 0; this.as = j2krparam; this.ax = cdstrInfo; this.aR = hi; String strInfo = this.ax ? "Codestream elements information in bytes (offset, total length, header length):\n\n" : null; if (j2krparam.getDecodingRate() == Double.MAX_VALUE) { this.z_ = Integer.MAX_VALUE; }
/*      */     else { this.z_ = (int)(j2krparam.getDecodingRate() * hd.b() * hd.a()) / 8; }
/*      */      this.aI = true; int ncbQuit = -1; if (ncbQuit != -1 && !this.aI)
/*      */       throw new Error("Cannot use -parsing and -ncb_quit condition at the same time.");  this.aT = -1; this.au = this.t_ * this.u_; this.at = ehs; this.ae = new e(decSpec, hd, ehs, this, this.aI, ncbQuit); this.aM = new int[this.au]; this.aN = new int[this.au]; this.aA = new int[this.au]; this.az = new int[this.au][]; this.aP = new int[this.au][]; this.av = new int[this.au][]; this.aK = new int[this.au]; this.aB = new int[this.au]; this.aG = new int[this.au][]; this.aw = new int[this.au]; this.ay = new int[this.au]; hd.b = new int[this.au]; this.aI = this.aI; this.ag = hd.h; this.aE = this.at.getPos() - this.ag; this.aF = this.aE; if (ncbQuit == -1) { this.A_ = this.aE; }
/*      */     else { this.A_ = 0; }
/*      */      if (this.ax)
/*      */       strInfo = strInfo + "Main header length    : " + this.ag + ", " + this.aE + ", " + this.aE + "\n";  if (this.A_ > this.z_)
/*      */       throw new Error("Requested bitrate is too small.");  this.aD = 0.0D; this.aJ = this.au; this.aq = this.ap = this.at.getPos(); if (j2krparam.getResolution() == -1) { this.h = decSpec.g.b(); }
/*      */     else { this.h = j2krparam.getResolution(); if (this.h < 0)
/*      */         throw new IllegalArgumentException("Specified negative resolution level index: " + this.h);  }
/*      */      int mdl = decSpec.g.b(); if (this.h > mdl) { c.a.i.c.b().printmsg(2, "Specified resolution level (" + this.h + ") is larger" + " than the maximum possible. Setting it to " + mdl + " (maximum possible)"); this.h = mdl; }
/*  511 */      r(); } private void j(int tileNum) throws IOException { if (this.af == null) this.at.seek(this.ap); 
/*  512 */     String strInfo = "";
/*  513 */     int ncbQuit = -1;
/*  514 */     boolean isTilePartRead = false;
/*  515 */     boolean isEOFEncountered = false;
/*      */     try {
/*  517 */       int tpNum = 0;
/*  518 */       while (this.aJ != 0 && (this.aN[tileNum] == 0 || this.aK[tileNum] < this.aN[tileNum])) {
/*      */ 
/*      */         
/*  521 */         isTilePartRead = true;
/*      */         
/*  523 */         if (this.af != null) {
/*  524 */           this.at.seek((int)this.af[tileNum][tpNum++]);
/*      */         }
/*  526 */         this.al = this.at.getPos();
/*      */ 
/*      */         
/*      */         try {
/*  530 */           this.ah = t();
/*  531 */           if (this.aQ) {
/*      */             break;
/*      */           }
/*      */           
/*  535 */           this.aj = this.aK[this.ah];
/*  536 */           if (this.ar)
/*      */           {
/*      */             
/*  539 */             this.az[this.ah][this.aj] = this.at.length() - 2 - this.al;
/*      */           }
/*  541 */         } catch (EOFException eOFException) {
/*  542 */           this.av[this.ah][this.aj] = this.at.length();
/*  543 */           throw eOFException;
/*      */         } 
/*      */         
/*  546 */         this.ai = this.at.getPos();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  551 */         if (this.aI && ncbQuit == -1 && 
/*  552 */           this.ai - this.ag > this.z_) {
/*  553 */           this.av[this.ah][this.aj] = this.at.length();
/*  554 */           this.am = true;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/*  560 */         this.av[this.ah][this.aj] = this.ai;
/*  561 */         this.aG[this.ah][this.aj] = this.ai - this.al;
/*      */         
/*  563 */         if (this.ax) {
/*  564 */           strInfo = strInfo + "Tile-part " + this.aj + " of tile " + this.ah + " : " + this.al + ", " + this.az[this.ah][this.aj] + ", " + this.aG[this.ah][this.aj] + "\n";
/*      */         }
/*      */ 
/*      */         
/*  568 */         this.aA[this.ah] = this.aA[this.ah] + this.az[this.ah][this.aj];
/*  569 */         this.aB[this.ah] = this.aB[this.ah] + this.aG[this.ah][this.aj];
/*  570 */         this.aD += this.az[this.ah][this.aj];
/*  571 */         if (this.aI) {
/*  572 */           if (this.A_ + this.az[this.ah][this.aj] > this.z_) {
/*  573 */             this.A_ += this.aG[this.ah][this.aj];
/*  574 */             this.aF += this.aG[this.ah][this.aj];
/*  575 */             this.am = true;
/*  576 */             this.aw[this.ah] = this.aw[this.ah] + this.z_ - this.A_;
/*      */             break;
/*      */           } 
/*  579 */           this.A_ += this.aG[this.ah][this.aj];
/*  580 */           this.aF += this.aG[this.ah][this.aj];
/*  581 */           this.aw[this.ah] = this.aw[this.ah] + this.az[this.ah][this.aj] - this.aG[this.ah][this.aj];
/*      */         }
/*      */         else {
/*      */           
/*  585 */           if (this.A_ + this.aG[this.ah][this.aj] > this.z_) {
/*      */             break;
/*      */           }
/*  588 */           this.A_ += this.aG[this.ah][this.aj];
/*  589 */           this.aF += this.aG[this.ah][this.aj];
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  594 */         if (this.ak == 0) {
/*  595 */           this.aC = this.aG[this.ah][this.aj];
/*      */         }
/*      */         
/*  598 */         this.aK[this.ah] = this.aK[this.ah] + 1;
/*  599 */         int nextMarkerPos = this.al + this.az[this.ah][this.aj];
/*  600 */         if (this.af == null) {
/*  601 */           this.at.seek(nextMarkerPos);
/*      */         }
/*  603 */         if (nextMarkerPos > this.aq) {
/*  604 */           this.aq = nextMarkerPos;
/*      */         }
/*  606 */         this.aJ--;
/*  607 */         this.ao--;
/*  608 */         this.ak++;
/*      */ 
/*      */         
/*  611 */         if (this.ar) {
/*  612 */           if (this.aJ != 0) {
/*  613 */             c.a.i.c.b()
/*  614 */               .printmsg(2, "Some tile-parts have not been found. The codestream may be corrupted.");
/*      */           }
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  620 */     } catch (EOFException eOFException) {
/*  621 */       isEOFEncountered = true;
/*      */       
/*  623 */       if (this.ax) {
/*  624 */         c.a.i.c.b()
/*  625 */           .printmsg(1, strInfo);
/*      */       }
/*  627 */       c.a.i.c.b()
/*  628 */         .printmsg(2, "Codestream truncated in tile " + this.ah);
/*      */ 
/*      */       
/*  631 */       int fileLen = this.at.length();
/*  632 */       if (fileLen < this.z_) {
/*  633 */         this.z_ = fileLen;
/*  634 */         this
/*  635 */           .B_ = this.z_ * 8.0F / this.y_.b() / this.y_.a();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  640 */     if (!isTilePartRead) {
/*      */       return;
/*      */     }
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
/*  674 */     if (!isEOFEncountered) {
/*  675 */       if (this.ax) {
/*  676 */         c.a.i.c.b().printmsg(1, strInfo);
/*      */       }
/*      */       
/*  679 */       if (this.aJ == 0)
/*      */       {
/*      */         
/*  682 */         if (!this.aQ && !this.ar && !this.am) {
/*      */           try {
/*  684 */             int savePos = this.at.getPos();
/*  685 */             this.at.seek(this.aq);
/*  686 */             if (this.at.readShort() != -39) {
/*  687 */               c.a.i.c.b()
/*  688 */                 .printmsg(2, "EOC marker not found. Codestream is corrupted.");
/*      */             }
/*      */             
/*  691 */             this.at.seek(savePos);
/*  692 */           } catch (EOFException eOFException) {
/*  693 */             c.a.i.c.b()
/*  694 */               .printmsg(2, "EOC marker is missing");
/*      */           } 
/*      */         }
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  701 */     if (!this.aI) {
/*  702 */       s();
/*  703 */     } else if (this.aJ == 0 && !isEOFEncountered) {
/*      */       
/*  705 */       if (this.at.getPos() >= this.z_) {
/*  706 */         this.A_ += 2;
/*      */       }
/*      */     } 
/*  709 */     if (this.af == null) this.ap = this.at.getPos();
/*      */ 
/*      */     
/*  712 */     for (int tIdx = 0; tIdx < this.au; tIdx++) {
/*  713 */       this.ay[tIdx] = this.aw[tIdx];
/*  714 */       if (this.ax) {
/*  715 */         c.a.i.c.b()
/*  716 */           .println("" + this.aR.a(tIdx, (this.az[tIdx]).length), 2, 2);
/*      */       }
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void s() throws IOException {
/*  728 */     int stopOff = this.z_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  734 */     if (this.aJ == 0) this.A_ += 2;
/*      */ 
/*      */ 
/*      */     
/*  738 */     if (this.A_ > stopOff) {
/*  739 */       throw new Error("Requested bitrate is too small for parsing");
/*      */     }
/*      */ 
/*      */     
/*  743 */     int rem = stopOff - this.A_;
/*  744 */     int totnByte = rem;
/*  745 */     for (int t = this.au - 1; t > 0; t--) {
/*  746 */       this.aw[t] = (int)(totnByte * this.aA[t] / this.aD); rem -= (int)(totnByte * this.aA[t] / this.aD);
/*      */     } 
/*  748 */     this.aw[0] = rem;
/*      */   } private void r() throws IOException { int savePos = this.at.getPos(); byte[][] tlmSegments = (byte[][])null; int numTLM = 0; try { this.at.seek(this.ag + 2); short marker; while ((marker = this.at.readShort()) != -112) { int markerLength = this.at.readUnsignedShort(); if (marker == -171) { numTLM++; if (tlmSegments == null)
/*      */             tlmSegments = new byte[256][];  int Ztlm = this.at.read(); tlmSegments[Ztlm] = new byte[markerLength - 3]; this.at.readFully(tlmSegments[Ztlm], 0, markerLength - 3); continue; }  this.at.skipBytes(markerLength - 2); }  } catch (IOException iOException) { tlmSegments = (byte[][])null; }  if (tlmSegments != null) { ArrayList[] tlmOffsets = null; long tilePos = (this.ag + this.aE); int tileCounter = 0; for (int itlm = 0; itlm < numTLM; itlm++) { if (tlmSegments[itlm] == null) { tlmOffsets = null; break; }  if (tlmOffsets == null)
/*      */           tlmOffsets = new ArrayList[this.au];  ByteArrayInputStream bais = new ByteArrayInputStream(tlmSegments[itlm]); ImageInputStream iis = new MemoryCacheImageInputStream(bais); try { int Stlm = iis.read(); int ST = Stlm >> 4 & 0x3; int SP = Stlm >> 6 & 0x1; int tlmLength = (tlmSegments[itlm]).length; while (iis.getStreamPosition() < tlmLength) { int tileIndex = tileCounter; switch (ST) { case 1: tileIndex = iis.read(); break;
/*      */               case 2:
/*      */                 tileIndex = iis.readUnsignedShort(); break; }  if (tlmOffsets[tileIndex] == null)
/*      */               tlmOffsets[tileIndex] = new ArrayList();  tlmOffsets[tileIndex].add(new Long(tilePos)); long tileLength = 0L; switch (SP) { case 0:
/*      */                 tileLength = iis.readUnsignedShort(); break;
/*      */               case 1:
/*      */                 tileLength = iis.readUnsignedInt(); break; }  tilePos += tileLength; if (ST == 0)
/*      */               tileCounter++;  }  } catch (IOException iOException) {} }  if (tlmOffsets != null) { this.af = new long[this.au][]; for (int i = 0; i < this.au; i++) { if (tlmOffsets[i] == null) { this.af = (long[][])null; break; }  ArrayList list = tlmOffsets[i]; int count = list.size(); this.af[i] = new long[count]; long[] tpPos = this.af[i]; for (int j = 0; j < count; j++)
/*  759 */             tpPos[j] = ((Long)list.get(j)).longValue();  }  }  }  this.at.seek(savePos); } private int t() throws IOException { d.j ms = this.aR.b();
/*      */ 
/*      */     
/*  762 */     short marker = this.at.readShort();
/*  763 */     if (marker != -112) {
/*  764 */       if (marker == -39) {
/*  765 */         this.aQ = true;
/*  766 */         return -1;
/*      */       } 
/*  768 */       throw new c.a.a.c("SOT tag not found in tile-part start");
/*      */     } 
/*      */ 
/*      */     
/*  772 */     this.aQ = false;
/*      */ 
/*      */     
/*  775 */     int lsot = this.at.readUnsignedShort();
/*  776 */     ms.a = lsot;
/*  777 */     if (lsot != 10) {
/*  778 */       throw new c.a.a.c("Wrong length for SOT marker segment: " + lsot);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  783 */     int tile = this.at.readUnsignedShort();
/*  784 */     ms.b = tile;
/*  785 */     if (tile > 65534) {
/*  786 */       throw new c.a.a.c("Tile index too high in tile-part.");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  791 */     int psot = this.at.readInt();
/*  792 */     ms.c = psot;
/*  793 */     this.ar = !(psot != 0);
/*  794 */     if (psot < 0) {
/*  795 */       throw new f("Tile length larger than maximum supported");
/*      */     }
/*      */ 
/*      */     
/*  799 */     int tilePart = this.at.read();
/*  800 */     ms.d = tilePart;
/*  801 */     if (tilePart != this.aK[tile] || tilePart < 0 || tilePart > 254) {
/*  802 */       throw new c.a.a.c("Out of order tile-part");
/*      */     }
/*      */     
/*  805 */     int nrOfTileParts = this.at.read();
/*  806 */     ms.e = nrOfTileParts;
/*  807 */     this.aR.b.put("t" + tile + "_tp" + tilePart, ms);
/*  808 */     if (nrOfTileParts == 0) {
/*      */       int nExtraTp;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  814 */       if (this.aM[tile] == 0 || this.aM[tile] == this.az.length) {
/*      */ 
/*      */         
/*  817 */         nExtraTp = 2;
/*  818 */         this.aJ++;
/*      */       }
/*      */       else {
/*      */         
/*  822 */         nExtraTp = 1;
/*      */       } 
/*      */       
/*  825 */       this.aM[tile] = this.aM[tile] + nExtraTp;
/*  826 */       nrOfTileParts = this.aM[tile];
/*  827 */       c.a.i.c.b()
/*  828 */         .printmsg(2, "Header of tile-part " + tilePart + " of tile " + tile + ", does not indicate the total" + " number of tile-parts. Assuming that there are " + nrOfTileParts + " tile-parts for this tile.");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  834 */       int[] tmpA = this.az[tile];
/*  835 */       this.az[tile] = new int[nrOfTileParts]; int i;
/*  836 */       for (i = 0; i < nrOfTileParts - nExtraTp; i++) {
/*  837 */         this.az[tile][i] = tmpA[i];
/*      */       }
/*      */       
/*  840 */       tmpA = this.aP[tile];
/*  841 */       this.aP[tile] = new int[nrOfTileParts];
/*  842 */       for (i = 0; i < nrOfTileParts - nExtraTp; i++) {
/*  843 */         this.aP[tile][i] = tmpA[i];
/*      */       }
/*      */ 
/*      */       
/*  847 */       tmpA = this.av[tile];
/*  848 */       this.av[tile] = new int[nrOfTileParts];
/*  849 */       for (i = 0; i < nrOfTileParts - nExtraTp; i++) {
/*  850 */         this.av[tile][i] = tmpA[i];
/*      */       }
/*      */ 
/*      */       
/*  854 */       tmpA = this.aG[tile];
/*  855 */       this.aG[tile] = new int[nrOfTileParts];
/*  856 */       for (i = 0; i < nrOfTileParts - nExtraTp; i++) {
/*  857 */         this.aG[tile][i] = tmpA[i];
/*      */       }
/*      */     } else {
/*      */       
/*  861 */       this.aN[tile] = nrOfTileParts;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  866 */       if (this.aM[tile] == 0)
/*  867 */       { this.aJ += nrOfTileParts - 1;
/*  868 */         this.aM[tile] = nrOfTileParts;
/*  869 */         this.az[tile] = new int[nrOfTileParts];
/*  870 */         this.aP[tile] = new int[nrOfTileParts];
/*  871 */         this.av[tile] = new int[nrOfTileParts];
/*  872 */         this.aG[tile] = new int[nrOfTileParts]; }
/*  873 */       else { if (this.aM[tile] > nrOfTileParts)
/*      */         {
/*  875 */           throw new c.a.a.c("Invalid number of tile-parts in tile " + tile + ": " + nrOfTileParts);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  881 */         this.aJ += nrOfTileParts - this.aM[tile];
/*      */         
/*  883 */         if (this.aM[tile] != nrOfTileParts) {
/*      */ 
/*      */           
/*  886 */           int[] tmpA = this.az[tile];
/*  887 */           this.az[tile] = new int[nrOfTileParts]; int i;
/*  888 */           for (i = 0; i < this.aM[tile] - 1; i++) {
/*  889 */             this.az[tile][i] = tmpA[i];
/*      */           }
/*      */ 
/*      */           
/*  893 */           tmpA = this.aP[tile];
/*  894 */           this.aP[tile] = new int[nrOfTileParts];
/*  895 */           for (i = 0; i < this.aM[tile] - 1; i++) {
/*  896 */             this.aP[tile][i] = tmpA[i];
/*      */           }
/*      */ 
/*      */           
/*  900 */           tmpA = this.av[tile];
/*  901 */           this.av[tile] = new int[nrOfTileParts];
/*  902 */           for (i = 0; i < this.aM[tile] - 1; i++) {
/*  903 */             this.av[tile][i] = tmpA[i];
/*      */           }
/*      */ 
/*      */           
/*  907 */           tmpA = this.aG[tile];
/*  908 */           this.aG[tile] = new int[nrOfTileParts];
/*  909 */           for (i = 0; i < this.aM[tile] - 1; i++) {
/*  910 */             this.aG[tile][i] = tmpA[i];
/*      */           }
/*      */         }  }
/*      */     
/*      */     } 
/*      */ 
/*      */     
/*  917 */     this.y_.n();
/*  918 */     this.y_.b[tile] = nrOfTileParts;
/*      */ 
/*      */     
/*      */     do {
/*  922 */       this.y_.a(this.at.readShort(), this.at, tile, tilePart);
/*  923 */     } while ((this.y_.q() & 0x2000) == 0);
/*      */ 
/*      */     
/*  926 */     this.y_.a(tile, tilePart);
/*      */     
/*  928 */     this.az[tile][tilePart] = psot;
/*      */     
/*  930 */     this.aP[tile][tilePart] = this.aL;
/*  931 */     this.aL++;
/*      */ 
/*      */ 
/*      */     
/*  935 */     this.y_.f(tile);
/*      */     
/*  937 */     return tile; }
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
/*      */   private boolean a(int[][] lys, int lye, int ress, int rese, int comps, int compe) throws IOException {
/*  963 */     int minlys = 10000;
/*  964 */     for (int i = comps; i < compe; i++) {
/*      */       
/*  966 */       if (i < this.f.length)
/*      */       {
/*  968 */         for (int r = ress; r < rese; r++) {
/*  969 */           if (lys[i] != null && r < (lys[i]).length && lys[i][r] < minlys) {
/*  970 */             minlys = lys[i][r];
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*  975 */     int t = e();
/*      */     
/*  977 */     boolean status = false;
/*  978 */     int lastByte = this.av[t][this.aO] + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */ 
/*      */     
/*  981 */     int numLayers = ((Integer)this.a.h.f(t)).intValue();
/*  982 */     int nPrec = 1;
/*      */ 
/*      */     
/*  985 */     String strInfo = this.ax ? ("Tile " + e() + " (tile-part:" + this.aO + "): offset, length, header length\n") : null;
/*      */     
/*  987 */     boolean pph = false;
/*  988 */     if (((Boolean)this.a.r.f(t)).booleanValue()) {
/*  989 */       pph = true;
/*      */     }
/*  991 */     for (int l = minlys; l < lye; l++) {
/*  992 */       for (int r = ress; r < rese; r++) {
/*  993 */         for (int j = comps; j < compe; j++) {
/*      */           
/*  995 */           if (j < this.f.length)
/*      */           {
/*  997 */             if (r < (lys[j]).length && 
/*  998 */               r <= this.f[j])
/*      */             {
/* 1000 */               if (l >= lys[j][r] && l < numLayers) {
/*      */                 
/* 1002 */                 nPrec = this.ae.a(j, r);
/* 1003 */                 for (int p = 0; p < nPrec; p++) {
/* 1004 */                   int start = this.at.getPos();
/*      */ 
/*      */ 
/*      */                   
/* 1008 */                   if (pph) {
/* 1009 */                     this.ae.a(l, r, j, p, this.aS[j][r], this.aw);
/*      */                   }
/*      */ 
/*      */ 
/*      */                   
/* 1014 */                   if (start > lastByte && this.aO < (this.av[t]).length - 1) {
/*      */                     
/* 1016 */                     this.aO++;
/* 1017 */                     this.at.seek(this.av[t][this.aO]);
/* 1018 */                     lastByte = this.at.getPos() + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */                   } 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1024 */                   status = this.ae.a(this.aw, p, j, r);
/*      */                   
/* 1026 */                   if (status) {
/* 1027 */                     if (this.ax) {
/* 1028 */                       c.a.i.c.b()
/* 1029 */                         .printmsg(1, strInfo);
/*      */                     }
/* 1031 */                     return true;
/*      */                   } 
/*      */                   
/* 1034 */                   if (!pph)
/*      */                   {
/* 1036 */                     status = this.ae.a(l, r, j, p, this.aS[j][r], this.aw);
/*      */                   }
/*      */                   
/* 1039 */                   if (status) {
/* 1040 */                     if (this.ax) {
/* 1041 */                       c.a.i.c.b()
/* 1042 */                         .printmsg(1, strInfo);
/*      */                     }
/* 1044 */                     return true;
/*      */                   } 
/*      */ 
/*      */                   
/* 1048 */                   int hlen = this.at.getPos() - start;
/* 1049 */                   this.aH.addElement(new Integer(hlen));
/*      */ 
/*      */                   
/* 1052 */                   status = this.ae.b(l, r, j, p, this.aS[j][r], this.aw);
/* 1053 */                   int plen = this.at.getPos() - start;
/* 1054 */                   if (this.ax) {
/* 1055 */                     strInfo = strInfo + " Pkt l=" + l + ",r=" + r + ",c=" + j + ",p=" + p + ": " + start + ", " + plen + ", " + hlen + "\n";
/*      */                   }
/*      */                   
/* 1058 */                   if (status) {
/* 1059 */                     if (this.ax) {
/* 1060 */                       c.a.i.c.b()
/* 1061 */                         .printmsg(1, strInfo);
/*      */                     }
/* 1063 */                     return true;
/*      */                   } 
/*      */                 } 
/*      */               }  } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 1071 */     if (this.ax) {
/* 1072 */       c.a.i.c.b().printmsg(1, strInfo);
/*      */     }
/* 1074 */     return false;
/*      */   }
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
/*      */   private boolean b(int[][] lys, int lye, int ress, int rese, int comps, int compe) throws IOException {
/* 1099 */     int t = e();
/* 1100 */     boolean status = false;
/* 1101 */     int lastByte = this.av[t][this.aO] + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */ 
/*      */     
/* 1104 */     int minlys = 10000;
/* 1105 */     for (int i = comps; i < compe; i++) {
/*      */       
/* 1107 */       if (i < this.f.length)
/*      */       {
/* 1109 */         for (int j = ress; j < rese; j++) {
/* 1110 */           if (j <= this.f[i] && 
/* 1111 */             lys[i] != null && j < (lys[i]).length && lys[i][j] < minlys) {
/* 1112 */             minlys = lys[i][j];
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1118 */     String strInfo = this.ax ? ("Tile " + e() + " (tile-part:" + this.aO + "): offset, length, header length\n") : null;
/*      */     
/* 1120 */     int numLayers = ((Integer)this.a.h.f(t)).intValue();
/* 1121 */     boolean pph = false;
/* 1122 */     if (((Boolean)this.a.r.f(t)).booleanValue()) {
/* 1123 */       pph = true;
/*      */     }
/* 1125 */     int nPrec = 1;
/*      */ 
/*      */     
/* 1128 */     for (int r = ress; r < rese; r++) {
/* 1129 */       for (int l = minlys; l < lye; l++) {
/* 1130 */         for (int j = comps; j < compe; j++) {
/*      */           
/* 1132 */           if (j < this.f.length)
/*      */           {
/* 1134 */             if (r <= this.f[j] && 
/* 1135 */               r < (lys[j]).length)
/*      */             {
/* 1137 */               if (l >= lys[j][r] && l < numLayers) {
/*      */                 
/* 1139 */                 nPrec = this.ae.a(j, r);
/*      */                 
/* 1141 */                 for (int p = 0; p < nPrec; p++) {
/* 1142 */                   int start = this.at.getPos();
/*      */ 
/*      */ 
/*      */                   
/* 1146 */                   if (pph) {
/* 1147 */                     this.ae.a(l, r, j, p, this.aS[j][r], this.aw);
/*      */                   }
/*      */ 
/*      */ 
/*      */                   
/* 1152 */                   if (start > lastByte && this.aO < (this.av[t]).length - 1) {
/*      */                     
/* 1154 */                     this.aO++;
/* 1155 */                     this.at.seek(this.av[t][this.aO]);
/* 1156 */                     lastByte = this.at.getPos() + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */                   } 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1162 */                   status = this.ae.a(this.aw, p, j, r);
/*      */                   
/* 1164 */                   if (status) {
/* 1165 */                     if (this.ax) {
/* 1166 */                       c.a.i.c.b()
/* 1167 */                         .printmsg(1, strInfo);
/*      */                     }
/* 1169 */                     return true;
/*      */                   } 
/*      */                   
/* 1172 */                   if (!pph)
/*      */                   {
/* 1174 */                     status = this.ae.a(l, r, j, p, this.aS[j][r], this.aw);
/*      */                   }
/*      */                   
/* 1177 */                   if (status) {
/* 1178 */                     if (this.ax) {
/* 1179 */                       c.a.i.c.b()
/* 1180 */                         .printmsg(1, strInfo);
/*      */                     }
/*      */                     
/* 1183 */                     return true;
/*      */                   } 
/*      */ 
/*      */                   
/* 1187 */                   int hlen = this.at.getPos() - start;
/* 1188 */                   this.aH.addElement(new Integer(hlen));
/*      */ 
/*      */                   
/* 1191 */                   status = this.ae.b(l, r, j, p, this.aS[j][r], this.aw);
/* 1192 */                   int plen = this.at.getPos() - start;
/* 1193 */                   if (this.ax) {
/* 1194 */                     strInfo = strInfo + " Pkt l=" + l + ",r=" + r + ",c=" + j + ",p=" + p + ": " + start + ", " + plen + ", " + hlen + "\n";
/*      */                   }
/*      */                   
/* 1197 */                   if (status) {
/* 1198 */                     if (this.ax) {
/* 1199 */                       c.a.i.c.b()
/* 1200 */                         .printmsg(1, strInfo);
/*      */                     }
/*      */                     
/* 1203 */                     return true;
/*      */                   } 
/*      */                 } 
/*      */               }  } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 1211 */     if (this.ax) {
/* 1212 */       c.a.i.c.b().printmsg(1, strInfo);
/*      */     }
/* 1214 */     return false;
/*      */   }
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
/*      */   private boolean c(int[][] lys, int lye, int ress, int rese, int comps, int compe) throws IOException {
/* 1241 */     Point nTiles = b((Point)null);
/* 1242 */     Point tileI = a((Point)null);
/* 1243 */     int x0siz = this.y_.e();
/* 1244 */     int y0siz = this.y_.f();
/* 1245 */     int xsiz = x0siz + this.y_.c();
/* 1246 */     int ysiz = y0siz + this.y_.d();
/* 1247 */     int xt0siz = m();
/* 1248 */     int yt0siz = n();
/* 1249 */     int xtsiz = o();
/* 1250 */     int ytsiz = p();
/* 1251 */     int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/* 1252 */     int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/* 1253 */     int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/* 1254 */     int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1259 */     int t = e();
/*      */ 
/*      */     
/* 1262 */     int gcd_x = 0;
/* 1263 */     int gcd_y = 0;
/* 1264 */     int nPrec = 0;
/* 1265 */     int[][] nextPrec = new int[compe][];
/*      */     
/* 1267 */     int minlys = 100000;
/* 1268 */     int minx = tx1;
/*      */     
/* 1270 */     int miny = ty1;
/*      */     
/* 1272 */     int maxx = tx0;
/* 1273 */     int maxy = ty0;
/*      */     
/* 1275 */     for (int i = comps; i < compe; i++) {
/* 1276 */       for (int j = ress; j < rese; j++) {
/* 1277 */         if (i < this.f.length && 
/* 1278 */           j <= this.f[i]) {
/* 1279 */           nextPrec[i] = new int[this.f[i] + 1];
/* 1280 */           if (lys[i] != null && j < (lys[i]).length && lys[i][j] < minlys) {
/* 1281 */             minlys = lys[i][j];
/*      */           }
/* 1283 */           int p = this.ae.a(i, j) - 1;
/* 1284 */           for (; p >= 0; p--) {
/* 1285 */             g prec = this.ae.c(i, j, p);
/* 1286 */             if (prec.a != tx0) {
/* 1287 */               if (prec.a < minx) minx = prec.a; 
/* 1288 */               if (prec.a > maxx) maxx = prec.a; 
/*      */             } 
/* 1290 */             if (prec.b != ty0) {
/* 1291 */               if (prec.b < miny) miny = prec.b; 
/* 1292 */               if (prec.b > maxy) maxy = prec.b;
/*      */             
/*      */             } 
/* 1295 */             if (nPrec == 0) {
/* 1296 */               gcd_x = prec.c;
/* 1297 */               gcd_y = prec.d;
/*      */             } else {
/* 1299 */               gcd_x = e.b(gcd_x, prec.c);
/* 1300 */               gcd_y = e.b(gcd_y, prec.d);
/*      */             } 
/* 1302 */             nPrec++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1307 */     if (nPrec == 0) {
/* 1308 */       throw new Error("Image cannot have no precinct");
/*      */     }
/*      */     
/* 1311 */     int pyend = (maxy - miny) / gcd_y + 1;
/* 1312 */     int pxend = (maxx - minx) / gcd_x + 1;
/*      */ 
/*      */ 
/*      */     
/* 1316 */     boolean status = false;
/* 1317 */     int lastByte = this.av[t][this.aO] + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */ 
/*      */     
/* 1320 */     int numLayers = ((Integer)this.a.h.f(t)).intValue();
/*      */     
/* 1322 */     String strInfo = this.ax ? ("Tile " + e() + " (tile-part:" + this.aO + "): offset, length, header length\n") : null;
/*      */     
/* 1324 */     boolean pph = false;
/* 1325 */     if (((Boolean)this.a.r.f(t)).booleanValue()) {
/* 1326 */       pph = true;
/*      */     }
/* 1328 */     for (int r = ress; r < rese; r++) {
/* 1329 */       int y = ty0;
/* 1330 */       int x = tx0;
/* 1331 */       for (int py = 0; py <= pyend; py++) {
/* 1332 */         for (int px = 0; px <= pxend; px++) {
/* 1333 */           for (int j = comps; j < compe; j++) {
/* 1334 */             if (j < this.f.length && 
/* 1335 */               r <= this.f[j] && 
/* 1336 */               nextPrec[j][r] < this.ae.a(j, r)) {
/*      */ 
/*      */               
/* 1339 */               g prec = this.ae.c(j, r, nextPrec[j][r]);
/* 1340 */               if (prec.a == x && prec.b == y)
/*      */               
/*      */               { 
/* 1343 */                 for (int l = minlys; l < lye; l++) {
/* 1344 */                   if (r < (lys[j]).length && 
/* 1345 */                     l >= lys[j][r] && l < numLayers) {
/*      */                     
/* 1347 */                     int start = this.at.getPos();
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1352 */                     if (pph) {
/* 1353 */                       this.ae.a(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                     }
/*      */ 
/*      */ 
/*      */                     
/* 1358 */                     if (start > lastByte && this.aO < (this.av[t]).length - 1) {
/*      */                       
/* 1360 */                       this.aO++;
/* 1361 */                       this.at.seek(this.av[t][this.aO]);
/* 1362 */                       lastByte = this.at.getPos() + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */                     } 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1368 */                     status = this.ae.a(this.aw, nextPrec[j][r], j, r);
/*      */ 
/*      */                     
/* 1371 */                     if (status) {
/* 1372 */                       if (this.ax) {
/* 1373 */                         c.a.i.c.b()
/* 1374 */                           .printmsg(1, strInfo);
/*      */                       }
/* 1376 */                       return true;
/*      */                     } 
/*      */                     
/* 1379 */                     if (!pph)
/*      */                     {
/* 1381 */                       status = this.ae.a(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                     }
/*      */ 
/*      */ 
/*      */                     
/* 1386 */                     if (status) {
/* 1387 */                       if (this.ax) {
/* 1388 */                         c.a.i.c.b()
/* 1389 */                           .printmsg(1, strInfo);
/*      */                       }
/* 1391 */                       return true;
/*      */                     } 
/*      */ 
/*      */                     
/* 1395 */                     int hlen = this.at.getPos() - start;
/* 1396 */                     this.aH.addElement(new Integer(hlen));
/*      */ 
/*      */ 
/*      */                     
/* 1400 */                     status = this.ae.b(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                     
/* 1402 */                     int plen = this.at.getPos() - start;
/* 1403 */                     if (this.ax) {
/* 1404 */                       strInfo = strInfo + " Pkt l=" + l + ",r=" + r + ",c=" + j + ",p=" + nextPrec[j][r] + ": " + start + ", " + plen + ", " + hlen + "\n";
/*      */                     }
/*      */ 
/*      */                     
/* 1408 */                     if (status) {
/* 1409 */                       if (this.ax) {
/* 1410 */                         c.a.i.c.b()
/* 1411 */                           .printmsg(1, strInfo);
/*      */                       }
/* 1413 */                       return true;
/*      */                     } 
/*      */                   } 
/* 1416 */                 }  nextPrec[j][r] = nextPrec[j][r] + 1; } 
/*      */             } 
/* 1418 */           }  if (px != pxend) {
/* 1419 */             x = minx + px * gcd_x;
/*      */           } else {
/* 1421 */             x = tx0;
/*      */           } 
/*      */         } 
/* 1424 */         if (py != pyend) {
/* 1425 */           y = miny + py * gcd_y;
/*      */         } else {
/* 1427 */           y = ty0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1432 */     if (this.ax) {
/* 1433 */       c.a.i.c.b().printmsg(1, strInfo);
/*      */     }
/* 1435 */     return false;
/*      */   }
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
/*      */   private boolean d(int[][] lys, int lye, int ress, int rese, int comps, int compe) throws IOException {
/* 1459 */     Point nTiles = b((Point)null);
/* 1460 */     Point tileI = a((Point)null);
/* 1461 */     int x0siz = this.y_.e();
/* 1462 */     int y0siz = this.y_.f();
/* 1463 */     int xsiz = x0siz + this.y_.c();
/* 1464 */     int ysiz = y0siz + this.y_.d();
/* 1465 */     int xt0siz = m();
/* 1466 */     int yt0siz = n();
/* 1467 */     int xtsiz = o();
/* 1468 */     int ytsiz = p();
/* 1469 */     int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/* 1470 */     int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/* 1471 */     int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/* 1472 */     int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1477 */     int t = e();
/*      */ 
/*      */     
/* 1480 */     int gcd_x = 0;
/* 1481 */     int gcd_y = 0;
/* 1482 */     int nPrec = 0;
/* 1483 */     int[][] nextPrec = new int[compe][];
/*      */     
/* 1485 */     int minlys = 100000;
/* 1486 */     int minx = tx1;
/*      */     
/* 1488 */     int miny = ty1;
/*      */     
/* 1490 */     int maxx = tx0;
/* 1491 */     int maxy = ty0;
/*      */     
/* 1493 */     for (int i = comps; i < compe; i++) {
/* 1494 */       for (int r = ress; r < rese; r++) {
/* 1495 */         if (i < this.f.length && 
/* 1496 */           r <= this.f[i]) {
/* 1497 */           nextPrec[i] = new int[this.f[i] + 1];
/* 1498 */           if (lys[i] != null && r < (lys[i]).length && lys[i][r] < minlys) {
/* 1499 */             minlys = lys[i][r];
/*      */           }
/* 1501 */           int p = this.ae.a(i, r) - 1;
/* 1502 */           for (; p >= 0; p--) {
/* 1503 */             g prec = this.ae.c(i, r, p);
/* 1504 */             if (prec.a != tx0) {
/* 1505 */               if (prec.a < minx) minx = prec.a; 
/* 1506 */               if (prec.a > maxx) maxx = prec.a; 
/*      */             } 
/* 1508 */             if (prec.b != ty0) {
/* 1509 */               if (prec.b < miny) miny = prec.b; 
/* 1510 */               if (prec.b > maxy) maxy = prec.b;
/*      */             
/*      */             } 
/* 1513 */             if (nPrec == 0) {
/* 1514 */               gcd_x = prec.c;
/* 1515 */               gcd_y = prec.d;
/*      */             } else {
/* 1517 */               gcd_x = e.b(gcd_x, prec.c);
/* 1518 */               gcd_y = e.b(gcd_y, prec.d);
/*      */             } 
/* 1520 */             nPrec++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1525 */     if (nPrec == 0) {
/* 1526 */       throw new Error("Image cannot have no precinct");
/*      */     }
/*      */     
/* 1529 */     int pyend = (maxy - miny) / gcd_y + 1;
/* 1530 */     int pxend = (maxx - minx) / gcd_x + 1;
/*      */ 
/*      */     
/* 1533 */     boolean status = false;
/* 1534 */     int lastByte = this.av[t][this.aO] + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */ 
/*      */     
/* 1537 */     int numLayers = ((Integer)this.a.h.f(t)).intValue();
/*      */     
/* 1539 */     String strInfo = this.ax ? ("Tile " + e() + " (tile-part:" + this.aO + "): offset, length, header length\n") : null;
/*      */     
/* 1541 */     boolean pph = false;
/* 1542 */     if (((Boolean)this.a.r.f(t)).booleanValue()) {
/* 1543 */       pph = true;
/*      */     }
/*      */     
/* 1546 */     int y = ty0;
/* 1547 */     int x = tx0;
/* 1548 */     for (int py = 0; py <= pyend; py++) {
/* 1549 */       for (int px = 0; px <= pxend; px++) {
/* 1550 */         for (int j = comps; j < compe; j++) {
/* 1551 */           if (j < this.f.length)
/* 1552 */             for (int r = ress; r < rese; r++) {
/* 1553 */               if (r <= this.f[j] && 
/* 1554 */                 nextPrec[j][r] < this.ae.a(j, r)) {
/*      */ 
/*      */                 
/* 1557 */                 g prec = this.ae.c(j, r, nextPrec[j][r]);
/* 1558 */                 if (prec.a == x && prec.b == y)
/*      */                 
/*      */                 { 
/* 1561 */                   for (int l = minlys; l < lye; l++) {
/* 1562 */                     if (r < (lys[j]).length && 
/* 1563 */                       l >= lys[j][r] && l < numLayers) {
/*      */                       
/* 1565 */                       int start = this.at.getPos();
/*      */ 
/*      */ 
/*      */ 
/*      */                       
/* 1570 */                       if (pph) {
/* 1571 */                         this.ae.a(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                       }
/*      */ 
/*      */                       
/* 1575 */                       status = this.ae.a(this.aw, nextPrec[j][r], j, r);
/*      */ 
/*      */                       
/* 1578 */                       if (status) {
/* 1579 */                         if (this.ax) {
/* 1580 */                           c.a.i.c.b()
/* 1581 */                             .printmsg(1, strInfo);
/*      */                         }
/* 1583 */                         return true;
/*      */                       } 
/*      */                       
/* 1586 */                       if (!pph)
/*      */                       {
/* 1588 */                         status = this.ae.a(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                       }
/*      */ 
/*      */ 
/*      */                       
/* 1593 */                       if (status) {
/* 1594 */                         if (this.ax) {
/* 1595 */                           c.a.i.c.b()
/* 1596 */                             .printmsg(1, strInfo);
/*      */                         }
/* 1598 */                         return true;
/*      */                       } 
/*      */ 
/*      */                       
/* 1602 */                       int hlen = this.at.getPos() - start;
/* 1603 */                       this.aH.addElement(new Integer(hlen));
/*      */ 
/*      */                       
/* 1606 */                       status = this.ae.b(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                       
/* 1608 */                       int plen = this.at.getPos() - start;
/* 1609 */                       if (this.ax) {
/* 1610 */                         strInfo = strInfo + " Pkt l=" + l + ",r=" + r + ",c=" + j + ",p=" + nextPrec[j][r] + ": " + start + ", " + plen + ", " + hlen + "\n";
/*      */                       }
/*      */ 
/*      */                       
/* 1614 */                       if (status) {
/* 1615 */                         if (this.ax) {
/* 1616 */                           c.a.i.c.b()
/* 1617 */                             .printmsg(1, strInfo);
/*      */                         }
/* 1619 */                         return true;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/* 1623 */                   nextPrec[j][r] = nextPrec[j][r] + 1; } 
/*      */               } 
/*      */             }  
/* 1626 */         }  if (px != pxend) {
/* 1627 */           x = minx + px * gcd_x;
/*      */         } else {
/* 1629 */           x = tx0;
/*      */         } 
/*      */       } 
/* 1632 */       if (py != pyend) {
/* 1633 */         y = miny + py * gcd_y;
/*      */       } else {
/* 1635 */         y = ty0;
/*      */       } 
/*      */     } 
/*      */     
/* 1639 */     if (this.ax) {
/* 1640 */       c.a.i.c.b().printmsg(1, strInfo);
/*      */     }
/* 1642 */     return false;
/*      */   }
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
/*      */   private boolean e(int[][] lys, int lye, int ress, int rese, int comps, int compe) throws IOException {
/* 1666 */     Point nTiles = b((Point)null);
/* 1667 */     Point tileI = a((Point)null);
/* 1668 */     int x0siz = this.y_.e();
/* 1669 */     int y0siz = this.y_.f();
/* 1670 */     int xsiz = x0siz + this.y_.c();
/* 1671 */     int ysiz = y0siz + this.y_.d();
/* 1672 */     int xt0siz = m();
/* 1673 */     int yt0siz = n();
/* 1674 */     int xtsiz = o();
/* 1675 */     int ytsiz = p();
/* 1676 */     int tx0 = (tileI.x == 0) ? x0siz : (xt0siz + tileI.x * xtsiz);
/* 1677 */     int ty0 = (tileI.y == 0) ? y0siz : (yt0siz + tileI.y * ytsiz);
/* 1678 */     int tx1 = (tileI.x != nTiles.x - 1) ? (xt0siz + (tileI.x + 1) * xtsiz) : xsiz;
/* 1679 */     int ty1 = (tileI.y != nTiles.y - 1) ? (yt0siz + (tileI.y + 1) * ytsiz) : ysiz;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1684 */     int t = e();
/*      */ 
/*      */     
/* 1687 */     int gcd_x = 0;
/* 1688 */     int gcd_y = 0;
/* 1689 */     int nPrec = 0;
/* 1690 */     int[][] nextPrec = new int[compe][];
/*      */     
/* 1692 */     int minlys = 100000;
/* 1693 */     int minx = tx1;
/*      */     
/* 1695 */     int miny = ty1;
/*      */     
/* 1697 */     int maxx = tx0;
/* 1698 */     int maxy = ty0;
/*      */     
/* 1700 */     for (int i = comps; i < compe; i++) {
/* 1701 */       for (int r = ress; r < rese; r++) {
/* 1702 */         if (i < this.f.length && 
/* 1703 */           r <= this.f[i]) {
/* 1704 */           nextPrec[i] = new int[this.f[i] + 1];
/* 1705 */           if (lys[i] != null && r < (lys[i]).length && lys[i][r] < minlys) {
/* 1706 */             minlys = lys[i][r];
/*      */           }
/* 1708 */           int p = this.ae.a(i, r) - 1;
/* 1709 */           for (; p >= 0; p--) {
/* 1710 */             g prec = this.ae.c(i, r, p);
/* 1711 */             if (prec.a != tx0) {
/* 1712 */               if (prec.a < minx) minx = prec.a; 
/* 1713 */               if (prec.a > maxx) maxx = prec.a; 
/*      */             } 
/* 1715 */             if (prec.b != ty0) {
/* 1716 */               if (prec.b < miny) miny = prec.b; 
/* 1717 */               if (prec.b > maxy) maxy = prec.b;
/*      */             
/*      */             } 
/* 1720 */             if (nPrec == 0) {
/* 1721 */               gcd_x = prec.c;
/* 1722 */               gcd_y = prec.d;
/*      */             } else {
/* 1724 */               gcd_x = e.b(gcd_x, prec.c);
/* 1725 */               gcd_y = e.b(gcd_y, prec.d);
/*      */             } 
/* 1727 */             nPrec++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1732 */     if (nPrec == 0) {
/* 1733 */       throw new Error("Image cannot have no precinct");
/*      */     }
/*      */     
/* 1736 */     int pyend = (maxy - miny) / gcd_y + 1;
/* 1737 */     int pxend = (maxx - minx) / gcd_x + 1;
/*      */ 
/*      */     
/* 1740 */     boolean status = false;
/* 1741 */     int lastByte = this.av[t][this.aO] + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */ 
/*      */     
/* 1744 */     int numLayers = ((Integer)this.a.h.f(t)).intValue();
/*      */     
/* 1746 */     String strInfo = this.ax ? ("Tile " + e() + " (tile-part:" + this.aO + "): offset, length, header length\n") : null;
/*      */     
/* 1748 */     boolean pph = false;
/* 1749 */     if (((Boolean)this.a.r.f(t)).booleanValue()) {
/* 1750 */       pph = true;
/*      */     }
/*      */ 
/*      */     
/* 1754 */     for (int j = comps; j < compe; j++) {
/* 1755 */       if (j < this.f.length) {
/* 1756 */         int y = ty0;
/* 1757 */         int x = tx0;
/* 1758 */         for (int py = 0; py <= pyend; py++) {
/* 1759 */           for (int px = 0; px <= pxend; px++) {
/* 1760 */             for (int r = ress; r < rese; r++) {
/* 1761 */               if (r <= this.f[j] && 
/* 1762 */                 nextPrec[j][r] < this.ae.a(j, r)) {
/*      */ 
/*      */                 
/* 1765 */                 g prec = this.ae.c(j, r, nextPrec[j][r]);
/* 1766 */                 if (prec.a == x && prec.b == y)
/*      */                 
/*      */                 { 
/*      */                   
/* 1770 */                   for (int l = minlys; l < lye; l++) {
/* 1771 */                     if (r < (lys[j]).length && 
/* 1772 */                       l >= lys[j][r]) {
/*      */                       
/* 1774 */                       int start = this.at.getPos();
/*      */ 
/*      */ 
/*      */ 
/*      */                       
/* 1779 */                       if (pph) {
/* 1780 */                         this.ae.a(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                       }
/*      */ 
/*      */ 
/*      */                       
/* 1785 */                       if (start > lastByte && this.aO < (this.av[t]).length - 1) {
/*      */                         
/* 1787 */                         this.aO++;
/* 1788 */                         this.at.seek(this.av[t][this.aO]);
/* 1789 */                         lastByte = this.at.getPos() + this.az[t][this.aO] - 1 - this.aG[t][this.aO];
/*      */                       } 
/*      */ 
/*      */ 
/*      */ 
/*      */                       
/* 1795 */                       status = this.ae.a(this.aw, nextPrec[j][r], j, r);
/*      */ 
/*      */                       
/* 1798 */                       if (status) {
/* 1799 */                         if (this.ax) {
/* 1800 */                           c.a.i.c.b()
/* 1801 */                             .printmsg(1, strInfo);
/*      */                         }
/* 1803 */                         return true;
/*      */                       } 
/*      */                       
/* 1806 */                       if (!pph)
/*      */                       {
/* 1808 */                         status = this.ae.a(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                       }
/*      */ 
/*      */ 
/*      */                       
/* 1813 */                       if (status) {
/* 1814 */                         if (this.ax) {
/* 1815 */                           c.a.i.c.b()
/* 1816 */                             .printmsg(1, strInfo);
/*      */                         }
/* 1818 */                         return true;
/*      */                       } 
/*      */ 
/*      */                       
/* 1822 */                       int hlen = this.at.getPos() - start;
/* 1823 */                       this.aH.addElement(new Integer(hlen));
/*      */ 
/*      */                       
/* 1826 */                       status = this.ae.b(l, r, j, nextPrec[j][r], this.aS[j][r], this.aw);
/*      */                       
/* 1828 */                       int plen = this.at.getPos() - start;
/* 1829 */                       if (this.ax) {
/* 1830 */                         strInfo = strInfo + " Pkt l=" + l + ",r=" + r + ",c=" + j + ",p=" + nextPrec[j][r] + ": " + start + ", " + plen + ", " + hlen + "\n";
/*      */                       }
/*      */ 
/*      */                       
/* 1834 */                       if (status) {
/* 1835 */                         if (this.ax) {
/* 1836 */                           c.a.i.c.b()
/* 1837 */                             .printmsg(1, strInfo);
/*      */                         }
/* 1839 */                         return true;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/* 1843 */                   nextPrec[j][r] = nextPrec[j][r] + 1; } 
/*      */               } 
/* 1845 */             }  if (px != pxend) {
/* 1846 */               x = minx + px * gcd_x;
/*      */             } else {
/* 1848 */               x = tx0;
/*      */             } 
/*      */           } 
/* 1851 */           if (py != pyend) {
/* 1852 */             y = miny + py * gcd_y;
/*      */           } else {
/* 1854 */             y = ty0;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1859 */     if (this.ax) {
/* 1860 */       c.a.i.c.b().printmsg(1, strInfo);
/*      */     }
/* 1862 */     return false;
/*      */   }
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
/*      */   private void k(int t) throws IOException {
/* 1880 */     this.aH = new Vector();
/*      */     
/* 1882 */     int oldNBytes = this.aw[t];
/*      */ 
/*      */     
/* 1885 */     int nl = ((Integer)this.a.h.f(t)).intValue();
/*      */ 
/*      */ 
/*      */     
/* 1889 */     if (((Boolean)this.a.r.f(t)).booleanValue()) {
/*      */       
/* 1891 */       ByteArrayInputStream pphbais = this.y_.e(t);
/*      */ 
/*      */       
/* 1894 */       this.aS = this.ae.a(this.g, this.f, nl, this.aS, true, pphbais);
/*      */     } else {
/*      */       
/* 1897 */       this.aS = this.ae.a(this.g, this.f, nl, this.aS, false, (ByteArrayInputStream)null);
/*      */     } 
/*      */ 
/*      */     
/* 1901 */     int[][] pocSpec = (int[][])this.a.l.f(t);
/* 1902 */     int nChg = (pocSpec == null) ? 1 : pocSpec.length;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1908 */     int[][] change = new int[nChg][6];
/* 1909 */     int idx = 0;
/*      */     
/* 1911 */     change[0][1] = 0;
/*      */     
/* 1913 */     if (pocSpec == null) {
/* 1914 */       change[idx][0] = ((Integer)this.a.i.f(t)).intValue();
/*      */       
/* 1916 */       change[idx][1] = nl;
/* 1917 */       change[idx][2] = 0;
/* 1918 */       change[idx][3] = this.a.g.c(t) + 1;
/* 1919 */       change[idx][4] = 0;
/* 1920 */       change[idx][5] = this.g;
/*      */     } else {
/* 1922 */       for (idx = 0; idx < nChg; idx++) {
/* 1923 */         change[idx][0] = pocSpec[idx][5];
/* 1924 */         change[idx][1] = pocSpec[idx][2];
/* 1925 */         change[idx][2] = pocSpec[idx][0];
/* 1926 */         change[idx][3] = pocSpec[idx][3];
/* 1927 */         change[idx][4] = pocSpec[idx][1];
/* 1928 */         change[idx][5] = pocSpec[idx][4];
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1937 */       if ((this.aI && this.av == null) || this.av[t] == null) {
/*      */         return;
/*      */       }
/* 1940 */       this.at.seek(this.av[t][0]);
/* 1941 */     } catch (EOFException eOFException) {
/* 1942 */       c.a.i.c.b()
/* 1943 */         .printmsg(2, "Codestream truncated in tile " + t);
/*      */       
/*      */       return;
/*      */     } 
/* 1947 */     this.aO = 0;
/*      */ 
/*      */ 
/*      */     
/* 1951 */     boolean status = false;
/* 1952 */     int nb = this.aw[t];
/* 1953 */     int[][] lys = new int[this.g][];
/* 1954 */     for (int i = 0; i < this.g; i++) {
/* 1955 */       lys[i] = 
/* 1956 */         new int[((Integer)this.a.g.a(t, i)).intValue() + 1];
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1961 */       for (int chg = 0; chg < nChg; chg++) {
/*      */         
/* 1963 */         int lye = change[chg][1];
/* 1964 */         int ress = change[chg][2];
/* 1965 */         int rese = change[chg][3];
/* 1966 */         int comps = change[chg][4];
/* 1967 */         int compe = change[chg][5];
/*      */         
/* 1969 */         switch (change[chg][0]) {
/*      */           case 0:
/* 1971 */             status = a(lys, lye, ress, rese, comps, compe);
/*      */             break;
/*      */           case 1:
/* 1974 */             status = b(lys, lye, ress, rese, comps, compe);
/*      */             break;
/*      */           case 2:
/* 1977 */             status = c(lys, lye, ress, rese, comps, compe);
/*      */             break;
/*      */           case 3:
/* 1980 */             status = d(lys, lye, ress, rese, comps, compe);
/*      */             break;
/*      */           case 4:
/* 1983 */             status = e(lys, lye, ress, rese, comps, compe);
/*      */             break;
/*      */           default:
/* 1986 */             throw new IllegalArgumentException("Not recognized progression type");
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1991 */         for (int j = comps; j < compe; j++) {
/* 1992 */           if (j < lys.length)
/* 1993 */             for (int r = ress; r < rese; r++) {
/* 1994 */               if (r < (lys[j]).length) {
/* 1995 */                 lys[j][r] = lye;
/*      */               }
/*      */             }  
/*      */         } 
/* 1999 */         if (status || this.aU) {
/*      */           break;
/*      */         }
/*      */       } 
/* 2003 */     } catch (EOFException eOFException) {
/*      */ 
/*      */       
/* 2006 */       throw eOFException;
/*      */     } 
/*      */ 
/*      */     
/* 2010 */     if (this.aI) {
/* 2011 */       this.A_ += nb - this.aw[t];
/*      */ 
/*      */       
/* 2014 */       if (status) {
/* 2015 */         this.aw[t] = 0;
/*      */       }
/* 2017 */     } else if (this.aw[t] < this.aA[t] - this.aB[t]) {
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
/* 2028 */       boolean stopCount = false;
/*      */       
/* 2030 */       int[] pktHeadLen = new int[this.aH.size()];
/* 2031 */       for (int j = this.aH.size() - 1; j >= 0; j--) {
/* 2032 */         pktHeadLen[j] = ((Integer)this.aH.elementAt(j)).intValue();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2037 */       boolean reject = false;
/* 2038 */       for (int l = 0; l < nl; l++) {
/* 2039 */         if (this.aS != null) {
/* 2040 */           int nc = this.aS.length;
/*      */           
/* 2042 */           int mres = 0;
/* 2043 */           for (int k = 0; k < nc; k++) {
/* 2044 */             if (this.aS[k] != null && (this.aS[k]).length > mres)
/* 2045 */               mres = (this.aS[k]).length; 
/*      */           } 
/* 2047 */           for (int r = 0; r < mres; r++) {
/*      */ 
/*      */             
/* 2050 */             int msub = 0;
/* 2051 */             for (int m = 0; m < nc; m++) {
/* 2052 */               if (this.aS[m] != null && this.aS[m][r] != null && (this.aS[m][r]).length > msub)
/*      */               {
/* 2054 */                 msub = (this.aS[m][r]).length; } 
/*      */             } 
/* 2056 */             for (int s = 0; s < msub; s++) {
/*      */               
/* 2058 */               if (r != 0 || s == 0)
/*      */               {
/* 2060 */                 if (r == 0 || s != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 2065 */                   int mnby = 0;
/* 2066 */                   for (int i1 = 0; i1 < nc; i1++) {
/* 2067 */                     if (this.aS[i1] != null && this.aS[i1][r] != null && this.aS[i1][r][s] != null && (this.aS[i1][r][s]).length > mnby)
/*      */                     {
/*      */                       
/* 2070 */                       mnby = (this.aS[i1][r][s]).length; } 
/*      */                   } 
/* 2072 */                   for (int n = 0; n < mnby; n++) {
/*      */                     
/* 2074 */                     int mnbx = 0;
/* 2075 */                     for (int i3 = 0; i3 < nc; i3++) {
/* 2076 */                       if (this.aS[i3] != null && this.aS[i3][r] != null && this.aS[i3][r][s] != null && this.aS[i3][r][s][n] != null && (this.aS[i3][r][s][n]).length > mnbx)
/*      */                       {
/*      */                         
/* 2079 */                         mnbx = (this.aS[i3][r][s][n]).length; } 
/*      */                     } 
/* 2081 */                     for (int i2 = 0; i2 < mnbx; i2++) {
/*      */                       
/* 2083 */                       for (int i4 = 0; i4 < nc; i4++) {
/*      */                         
/* 2085 */                         if (this.aS[i4] != null && this.aS[i4][r] != null && this.aS[i4][r][s] != null && this.aS[i4][r][s][n] != null && this.aS[i4][r][s][n][i2] != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                           
/* 2091 */                           b cb = this.aS[i4][r][s][n][i2];
/*      */ 
/*      */ 
/*      */                           
/* 2095 */                           if (!reject)
/*      */                           {
/*      */                             
/* 2098 */                             if (this.aw[t] < pktHeadLen[cb.k[l]]) {
/*      */                               
/* 2100 */                               stopCount = true;
/*      */ 
/*      */                               
/* 2103 */                               reject = true;
/*      */ 
/*      */                             
/*      */                             }
/* 2107 */                             else if (!stopCount) {
/*      */ 
/*      */ 
/*      */                               
/* 2111 */                               this.aw[t] = this.aw[t] - pktHeadLen[cb.k[l]];
/*      */                               
/* 2113 */                               this.A_ += pktHeadLen[cb.k[l]];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                               
/* 2119 */                               pktHeadLen[cb.k[l]] = 0;
/*      */                             } 
/*      */                           }
/*      */ 
/*      */                           
/* 2124 */                           if (cb.f[l] != 0)
/*      */                           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                             
/* 2132 */                             if (cb.f[l] < this.aw[t] && !reject) {
/*      */                               
/* 2134 */                               this.aw[t] = this.aw[t] - cb.f[l];
/* 2135 */                               this.A_ += cb.f[l];
/*      */                             }
/*      */                             else {
/*      */                               
/* 2139 */                               cb.h[l] = 0; cb.g[l] = 0; cb.f[l] = 0;
/*      */ 
/*      */                               
/* 2142 */                               reject = true;
/*      */                             }  } 
/*      */                         } 
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 }  } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 2154 */       this.A_ += this.aA[t] - this.aB[t];
/* 2155 */       if (t < f() - 1) {
/* 2156 */         this.aw[t + 1] = this.aw[t + 1] + this.aw[t] - this.aA[t] - this.aB[t];
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2163 */     this.aw[t] = oldNBytes;
/*      */   }
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
/*      */   public void c(int x, int y) {
/* 2179 */     if (x < 0 || y < 0 || x >= this.t_ || y >= this.u_) {
/* 2180 */       throw new IllegalArgumentException();
/*      */     }
/* 2182 */     int t = y * this.t_ + x;
/*      */     try {
/* 2184 */       j(t);
/* 2185 */     } catch (IOException iOException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2190 */     if (t == 0) {
/* 2191 */       this.A_ = this.aF;
/* 2192 */       if (!this.aI) {
/* 2193 */         this.A_ += 2;
/*      */       }
/*      */       
/* 2196 */       for (int tIdx = 0; tIdx < this.au; tIdx++) {
/* 2197 */         this.aw[tIdx] = this.ay[tIdx];
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2202 */     this.w_ = x;
/* 2203 */     this.x_ = y;
/*      */     
/* 2205 */     int ctox = (x == 0) ? this.j_ : (this.l_ + x * this.r_);
/* 2206 */     int ctoy = (y == 0) ? this.k_ : (this.m_ + y * this.s_);
/* 2207 */     for (int i = this.g - 1; i >= 0; i--) {
/* 2208 */       this.p_[i] = (ctox + this.y_.c(i) - 1) / this.y_.c(i);
/* 2209 */       this.q_[i] = (ctoy + this.y_.d(i) - 1) / this.y_.d(i);
/* 2210 */       this.n_[i] = (this.l_ + x * this.r_ + this.y_.c(i) - 1) / this.y_.c(i);
/* 2211 */       this.o_[i] = (this.m_ + y * this.s_ + this.y_.d(i) - 1) / this.y_.d(i);
/*      */     } 
/*      */ 
/*      */     
/* 2215 */     this.i = new i[this.g];
/* 2216 */     this.f = new int[this.g];
/* 2217 */     this.b = new boolean[this.g];
/* 2218 */     this.d = new e[this.g];
/* 2219 */     this.c = new int[this.g];
/*      */     
/* 2221 */     for (int j = 0; j < this.g; j++) {
/* 2222 */       this.b[j] = this.a.c.e(t, j);
/* 2223 */       this.d[j] = (e)this.a.d
/* 2224 */         .a(t, j);
/* 2225 */       this.c[j] = ((Integer)this.a.e.a(t, j)).intValue();
/* 2226 */       this.f[j] = ((Integer)this.a.g.a(t, j)).intValue();
/*      */       
/* 2228 */       this.i[j] = new i(
/* 2229 */           a(t, j, this.f[j]), 
/* 2230 */           b(t, j, this.f[j]), 
/* 2231 */           d(j, this.f[j]), e(j, this.f[j]), this.f[j], (e[])this.a.f
/* 2232 */           .f(t, j), (e[])this.a.f
/* 2233 */           .g(t, j));
/* 2234 */       a(j, this.i[j]);
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 2239 */       k(t);
/* 2240 */     } catch (IOException iOException) {
/* 2241 */       iOException.printStackTrace();
/* 2242 */       throw new Error("IO Error when reading tile " + x + " x " + y);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void d() {
/* 2253 */     if (this.w_ == this.t_ - 1 && this.x_ == this.u_ - 1) {
/* 2254 */       throw new e();
/*      */     }
/* 2256 */     if (this.w_ < this.t_ - 1) {
/* 2257 */       c(this.w_ + 1, this.x_);
/*      */     } else {
/*      */       
/* 2260 */       c(0, this.x_ + 1);
/*      */     } 
/*      */   }
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
/*      */   public d a(int j, int m, int n, i sb, int fl, int nl, d ccb) {
/*      */     b rcb;
/* 2316 */     int nts, t = e();
/*      */     
/* 2318 */     int r = sb.h;
/* 2319 */     int s = sb.k;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2324 */     int numLayers = ((Integer)this.a.h.f(t)).intValue();
/* 2325 */     int options = ((Integer)this.a.j.a(t, j)).intValue();
/* 2326 */     if (nl < 0) {
/* 2327 */       nl = numLayers - fl + 1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2332 */     if (this.aT != -1 && fl + nl > this.aT) {
/* 2333 */       nl = this.aT - fl;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2338 */     int maxdl = (f(t, j)).h;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2348 */       rcb = this.aS[j][r][s][m][n];
/*      */       
/* 2350 */       if (fl < 1 || fl > numLayers || fl + nl - 1 > numLayers) {
/* 2351 */         throw new IllegalArgumentException();
/*      */       }
/* 2353 */     } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
/* 2354 */       throw new IllegalArgumentException("Code-block (t:" + t + ", c:" + j + ", r:" + r + ", s:" + s + ", " + m + "x" + n + ") not found in codestream");
/*      */     
/*      */     }
/* 2357 */     catch (NullPointerException nullPointerException) {
/* 2358 */       throw new IllegalArgumentException("Code-block (t:" + t + ", c:" + j + ", r:" + r + ", s:" + s + ", " + m + "x" + n + ") not found in bit stream");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2364 */     if (ccb == null) {
/* 2365 */       ccb = new d();
/*      */     }
/* 2367 */     ccb.b = m;
/* 2368 */     ccb.a = n;
/* 2369 */     ccb.k = 0;
/* 2370 */     ccb.i = 0;
/* 2371 */     ccb.m = 0;
/*      */     
/* 2373 */     if (rcb == null) {
/*      */       
/* 2375 */       ccb.c = 0;
/* 2376 */       ccb.j = false;
/* 2377 */       ccb.g = ccb.h = ccb.e = ccb.f = 0;
/* 2378 */       return ccb;
/*      */     } 
/*      */ 
/*      */     
/* 2382 */     ccb.c = rcb.e;
/* 2383 */     ccb.e = rcb.a;
/* 2384 */     ccb.f = rcb.b;
/* 2385 */     ccb.g = rcb.c;
/* 2386 */     ccb.h = rcb.d;
/* 2387 */     ccb.l = 0;
/*      */ 
/*      */ 
/*      */     
/* 2391 */     int l = 0;
/* 2392 */     while (l < rcb.f.length && rcb.f[l] == 0) {
/* 2393 */       ccb.l += rcb.h[l];
/* 2394 */       l++;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2399 */     for (l = fl - 1; l < fl + nl - 1; l++) {
/* 2400 */       ccb.k++;
/* 2401 */       ccb.i += rcb.f[l];
/* 2402 */       ccb.m += rcb.h[l];
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2407 */     if ((options & 0x4) != 0) {
/*      */ 
/*      */       
/* 2410 */       nts = ccb.m - ccb.l;
/* 2411 */     } else if ((options & 0x1) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2418 */       if (ccb.m <= 10) {
/* 2419 */         nts = 1;
/*      */       } else {
/* 2421 */         nts = 1;
/*      */         
/* 2423 */         for (int k = ccb.l; k < ccb.m; k++) {
/* 2424 */           if (k >= 9) {
/* 2425 */             int passtype = (k + 2) % 3;
/*      */             
/* 2427 */             if (passtype == 1 || passtype == 2)
/*      */             {
/*      */               
/* 2430 */               nts++;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 2437 */       nts = 1;
/*      */     } 
/*      */ 
/*      */     
/* 2441 */     if (ccb.d == null || ccb.d.length < ccb.i) {
/* 2442 */       ccb.d = new byte[ccb.i];
/*      */     }
/*      */ 
/*      */     
/* 2446 */     if (nts > 1 && (ccb.n == null || ccb.n.length < nts)) {
/* 2447 */       ccb.n = new int[nts];
/* 2448 */     } else if (nts > 1 && (options & 0x5) == 1) {
/*      */       
/* 2450 */       a.a(ccb.n, 0);
/*      */     } 
/*      */ 
/*      */     
/* 2454 */     int dataIdx = -1;
/* 2455 */     int tpidx = ccb.l;
/* 2456 */     int ctp = ccb.l;
/*      */     
/* 2458 */     int tsidx = 0;
/*      */ 
/*      */     
/* 2461 */     for (l = fl - 1; l < fl + nl - 1; l++) {
/* 2462 */       ctp += rcb.h[l];
/*      */       
/* 2464 */       if (rcb.f[l] != 0) {
/*      */ 
/*      */         
/*      */         try {
/*      */ 
/*      */           
/* 2470 */           this.at.seek(rcb.g[l]);
/* 2471 */           this.at.readFully(ccb.d, dataIdx + 1, rcb.f[l]);
/* 2472 */           dataIdx += rcb.f[l];
/* 2473 */         } catch (IOException iOException) {
/* 2474 */           b.a(iOException);
/*      */         } 
/*      */ 
/*      */         
/* 2478 */         if (nts != 1)
/* 2479 */           if ((options & 0x4) != 0) {
/*      */             
/* 2481 */             for (int k = 0; tpidx < ctp; k++, tpidx++) {
/* 2482 */               if (rcb.j[l] != null) {
/* 2483 */                 ccb.n[tsidx++] = rcb.j[l][k];
/*      */               } else {
/* 2485 */                 ccb.n[tsidx++] = rcb.f[l];
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             int k;
/* 2490 */             for (k = 0; tpidx < ctp; tpidx++) {
/* 2491 */               if (tpidx >= 9) {
/* 2492 */                 int passtype = (tpidx + 2) % 3;
/*      */                 
/* 2494 */                 if (passtype != 0)
/*      */                 {
/*      */ 
/*      */                   
/* 2498 */                   if (rcb.j[l] != null) {
/* 2499 */                     ccb.n[tsidx++] = ccb.n[tsidx++] + rcb.j[l][k++];
/* 2500 */                     rcb.f[l] = rcb.f[l] - rcb.j[l][k - 1];
/*      */                   } else {
/* 2502 */                     ccb.n[tsidx++] = ccb.n[tsidx++] + rcb.f[l];
/* 2503 */                     rcb.f[l] = 0;
/*      */                   } 
/*      */                 }
/*      */               } 
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2512 */             if (rcb.j[l] != null && k < (rcb.j[l]).length) {
/* 2513 */               ccb.n[tsidx] = ccb.n[tsidx] + rcb.j[l][k];
/* 2514 */               rcb.f[l] = rcb.f[l] - rcb.j[l][k];
/*      */             }
/* 2516 */             else if (tsidx < nts) {
/* 2517 */               ccb.n[tsidx] = ccb.n[tsidx] + rcb.f[l];
/* 2518 */               rcb.f[l] = 0;
/*      */             } 
/*      */           }  
/*      */       } 
/*      */     } 
/* 2523 */     if (nts == 1 && ccb.n != null) {
/* 2524 */       ccb.n[0] = ccb.i;
/*      */     }
/*      */ 
/*      */     
/* 2528 */     int lastlayer = fl + nl - 1;
/* 2529 */     if (lastlayer < numLayers - 1) {
/* 2530 */       for (l = lastlayer + 1; l < numLayers; l++) {
/*      */         
/* 2532 */         if (rcb.f[l] != 0) {
/* 2533 */           ccb.j = true;
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 2538 */     return ccb;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/a/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */