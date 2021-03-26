/*      */ package c.a.a.b;
/*      */ 
/*      */ import c.a.a.e;
/*      */ import c.a.c.b.j;
/*      */ import c.a.c.f;
/*      */ import c.a.e.f;
/*      */ import c.a.e.j;
/*      */ import c.a.f.c;
/*      */ import c.a.g.b.c;
/*      */ import c.a.i.e;
/*      */ import c.a.j.a.a;
/*      */ import c.a.j.a.n;
/*      */ import c.a.j.a.o;
/*      */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*      */ import java.awt.Point;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class d
/*      */   implements e, f
/*      */ {
/*      */   private int ae;
/*      */   private int af;
/*      */   protected int a;
/*      */   private boolean ag = true;
/*  111 */   private String ah = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ByteArrayOutputStream b;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected DataOutputStream c;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected f d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean[] e;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected j f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected n g;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected j h;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected c.a.h.a.d i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected J2KImageWriteParamJava j;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public d(f origsrc, boolean[] isorigsig, n dwt, j tiler, J2KImageWriteParamJava wp, c.a.h.a.d roiSc, j ralloc) {
/*  173 */     if (origsrc.getNumComps() != isorigsig.length) {
/*  174 */       throw new IllegalArgumentException();
/*      */     }
/*  176 */     this.d = origsrc;
/*  177 */     this.e = isorigsig;
/*  178 */     this.g = dwt;
/*  179 */     this.h = tiler;
/*  180 */     this.j = wp;
/*  181 */     this.i = roiSc;
/*  182 */     this.f = ralloc;
/*      */     
/*  184 */     this.b = new ByteArrayOutputStream();
/*  185 */     this.c = new DataOutputStream(this.b);
/*  186 */     this.a = origsrc.getNumComps();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a() {
/*  197 */     this.b.reset();
/*  198 */     this.c = new DataOutputStream(this.b);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected byte[] b() {
/*  207 */     return this.b.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int c() {
/*  216 */     return this.c.size();
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
/*      */   public void a(c out) throws IOException {
/*  228 */     byte[] buf = b();
/*  229 */     int len = c();
/*      */     
/*  231 */     for (int i = 0; i < len; i++) {
/*  232 */       out.writeByte(buf[i]);
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
/*      */   protected int d() {
/*  244 */     return this.b.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(OutputStream out) throws IOException {
/*  253 */     out.write(b(), 0, d());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void g() throws IOException {
/*  261 */     this.c.writeShort(-177);
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
/*      */   private void h() throws IOException {
/*  274 */     this.c.writeShort(-175);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  281 */     int markSegLen = 38 + 3 * this.a;
/*  282 */     this.c.writeShort(markSegLen);
/*      */ 
/*      */     
/*  285 */     this.c.writeShort(0);
/*      */ 
/*      */     
/*  288 */     this.c.writeInt(this.h.getImgWidth() + this.h.getImgULX());
/*      */ 
/*      */     
/*  291 */     this.c.writeInt(this.h.getImgHeight() + this.h.getImgULY());
/*      */ 
/*      */ 
/*      */     
/*  295 */     this.c.writeInt(this.h.getImgULX());
/*      */ 
/*      */ 
/*      */     
/*  299 */     this.c.writeInt(this.h.getImgULY());
/*      */ 
/*      */     
/*  302 */     this.c.writeInt(this.h.getNomTileWidth());
/*      */ 
/*      */     
/*  305 */     this.c.writeInt(this.h.getNomTileHeight());
/*      */     
/*  307 */     Point torig = this.h.a(null);
/*      */ 
/*      */     
/*  310 */     this.c.writeInt(torig.x);
/*      */ 
/*      */ 
/*      */     
/*  314 */     this.c.writeInt(torig.y);
/*      */ 
/*      */     
/*  317 */     this.c.writeShort(this.a);
/*      */ 
/*      */     
/*  320 */     for (int c = 0; c < this.a; c++) {
/*      */ 
/*      */       
/*  323 */       int tmp = this.d.getNomRangeBits(c) - 1;
/*      */       
/*  325 */       tmp |= (this.e[c] ? 1 : 0) << 7;
/*  326 */       this.c.write(tmp);
/*      */ 
/*      */       
/*  329 */       this.c.write(this.h.getCompSubsX(c));
/*      */ 
/*      */       
/*  332 */       this.c.write(this.h.getCompSubsY(c));
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
/*      */   protected void a(boolean mh, int tileIdx) throws IOException {
/*      */     boolean precinctPartitionUsed;
/*      */     c.a.c.d[] prog;
/*  358 */     int mrl = 0, a = 0;
/*  359 */     int ppx = 0, ppy = 0;
/*      */ 
/*      */     
/*  362 */     if (mh) {
/*  363 */       mrl = ((Integer)this.j.getDecompositionLevel().d()).intValue();
/*      */       
/*  365 */       ppx = this.j.getPrecinctPartition().a(-1, -1, mrl);
/*  366 */       ppy = this.j.getPrecinctPartition().b(-1, -1, mrl);
/*  367 */       prog = (c.a.c.d[])this.j.getProgressionType().d();
/*      */     } else {
/*      */       
/*  370 */       mrl = ((Integer)this.j.getDecompositionLevel().f(tileIdx)).intValue();
/*      */       
/*  372 */       ppx = this.j.getPrecinctPartition().a(tileIdx, -1, mrl);
/*  373 */       ppy = this.j.getPrecinctPartition().b(tileIdx, -1, mrl);
/*  374 */       prog = (c.a.c.d[])this.j.getProgressionType().f(tileIdx);
/*      */     } 
/*      */     
/*  377 */     if (ppx != 65535 || ppy != 65535) {
/*      */       
/*  379 */       precinctPartitionUsed = true;
/*      */     } else {
/*      */       
/*  382 */       precinctPartitionUsed = false;
/*      */     } 
/*      */     
/*  385 */     if (precinctPartitionUsed)
/*      */     {
/*      */       
/*  388 */       a = mrl + 1;
/*      */     }
/*      */ 
/*      */     
/*  392 */     this.c.writeShort(-174);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  398 */     int markSegLen = 12 + a;
/*  399 */     this.c.writeShort(markSegLen);
/*      */ 
/*      */     
/*  402 */     int tmp = 0;
/*  403 */     if (precinctPartitionUsed) {
/*  404 */       tmp = 1;
/*      */     }
/*      */     
/*  407 */     if (mh) {
/*  408 */       if (this.j.getSOP().d().toString()
/*  409 */         .equalsIgnoreCase("true")) {
/*  410 */         tmp |= 0x2;
/*      */       
/*      */       }
/*      */     }
/*  414 */     else if (this.j.getSOP().f(tileIdx).toString()
/*  415 */       .equalsIgnoreCase("true")) {
/*  416 */       tmp |= 0x2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  421 */     if (mh) {
/*  422 */       if (this.j.getEPH().d().toString()
/*  423 */         .equalsIgnoreCase("true")) {
/*  424 */         tmp |= 0x4;
/*      */       
/*      */       }
/*      */     }
/*  428 */     else if (this.j.getEPH().f(tileIdx).toString()
/*  429 */       .equalsIgnoreCase("true")) {
/*  430 */       tmp |= 0x4;
/*      */     } 
/*      */     
/*  433 */     if (this.g.a() != 0) tmp |= 0x8; 
/*  434 */     if (this.g.b() != 0) tmp |= 0x10; 
/*  435 */     this.c.write(tmp);
/*      */ 
/*      */ 
/*      */     
/*  439 */     this.c.write((prog[0]).a);
/*      */ 
/*      */     
/*  442 */     this.c.writeShort(this.f.c());
/*      */ 
/*      */ 
/*      */     
/*  446 */     String str = null;
/*  447 */     if (mh) {
/*  448 */       str = (String)this.j.getComponentTransformation().d();
/*      */     } else {
/*  450 */       str = (String)this.j.getComponentTransformation().f(tileIdx);
/*      */     } 
/*  452 */     if (str.equals("none")) {
/*  453 */       this.c.write(0);
/*      */     } else {
/*  455 */       this.c.write(1);
/*      */     } 
/*      */ 
/*      */     
/*  459 */     this.c.write(mrl);
/*      */ 
/*      */     
/*  462 */     if (mh) {
/*      */ 
/*      */       
/*  465 */       tmp = this.j.getCodeBlockSize().a((byte)0, -1, -1);
/*  466 */       this.c.write(e.a(tmp) - 2);
/*      */       
/*  468 */       tmp = this.j.getCodeBlockSize().b((byte)0, -1, -1);
/*  469 */       this.c.write(e.a(tmp) - 2);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  474 */       tmp = this.j.getCodeBlockSize().a((byte)2, tileIdx, -1);
/*  475 */       this.c.write(e.a(tmp) - 2);
/*      */       
/*  477 */       tmp = this.j.getCodeBlockSize().b((byte)2, tileIdx, -1);
/*  478 */       this.c.write(e.a(tmp) - 2);
/*      */     } 
/*      */ 
/*      */     
/*  482 */     tmp = 0;
/*  483 */     if (mh) {
/*      */       
/*  485 */       if (((String)this.j.getBypass().d()).equals("true")) {
/*  486 */         tmp |= 0x1;
/*      */       }
/*      */       
/*  489 */       if (((String)this.j.getResetMQ().d()).equals("true")) {
/*  490 */         tmp |= 0x2;
/*      */       }
/*      */       
/*  493 */       if (((String)this.j.getTerminateOnByte().d()).equals("true")) {
/*  494 */         tmp |= 0x4;
/*      */       }
/*      */       
/*  497 */       if (((String)this.j.getCausalCXInfo().d()).equals("true")) {
/*  498 */         tmp |= 0x8;
/*      */       }
/*      */       
/*  501 */       if (((String)this.j.getMethodForMQTermination().d()).equals("predict")) {
/*  502 */         tmp |= 0x10;
/*      */       }
/*      */       
/*  505 */       if (((String)this.j.getCodeSegSymbol().d()).equals("true")) {
/*  506 */         tmp |= 0x20;
/*      */       
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  512 */       if (((String)this.j.getBypass().f(tileIdx)).equals("true")) {
/*  513 */         tmp |= 0x1;
/*      */       }
/*      */       
/*  516 */       if (((String)this.j.getResetMQ().f(tileIdx)).equals("true")) {
/*  517 */         tmp |= 0x2;
/*      */       }
/*      */       
/*  520 */       if (((String)this.j.getTerminateOnByte().f(tileIdx)).equals("true")) {
/*  521 */         tmp |= 0x4;
/*      */       }
/*      */       
/*  524 */       if (((String)this.j.getCausalCXInfo().f(tileIdx)).equals("true")) {
/*  525 */         tmp |= 0x8;
/*      */       }
/*      */       
/*  528 */       if (((String)this.j.getMethodForMQTermination().f(tileIdx)).equals("predict")) {
/*  529 */         tmp |= 0x10;
/*      */       }
/*      */       
/*  532 */       if (((String)this.j.getCodeSegSymbol().f(tileIdx)).equals("true")) {
/*  533 */         tmp |= 0x20;
/*      */       }
/*      */     } 
/*  536 */     this.c.write(tmp);
/*      */ 
/*      */ 
/*      */     
/*  540 */     if (mh) {
/*  541 */       a[][] filt = (a[][])this.j.getFilters().d();
/*  542 */       this.c.write(filt[0][0].n());
/*      */     } else {
/*  544 */       a[][] filt = (a[][])this.j.getFilters().f(tileIdx);
/*  545 */       this.c.write(filt[0][0].n());
/*      */     } 
/*      */ 
/*      */     
/*  549 */     if (precinctPartitionUsed) {
/*      */ 
/*      */       
/*  552 */       Vector[] v = null;
/*  553 */       if (mh) {
/*  554 */         v = (Vector[])this.j.getPrecinctPartition().d();
/*      */       } else {
/*      */         
/*  557 */         v = (Vector[])this.j.getPrecinctPartition().f(tileIdx);
/*      */       } 
/*  559 */       for (int r = mrl; r >= 0; r--) {
/*  560 */         if (r >= v[1].size()) {
/*      */           
/*  562 */           tmp = ((Integer)v[1].elementAt(v[1].size() - 1)).intValue();
/*      */         } else {
/*      */           
/*  565 */           tmp = ((Integer)v[1].elementAt(r)).intValue();
/*      */         } 
/*  567 */         int yExp = e.a(tmp) << 4 & 0xF0;
/*      */         
/*  569 */         if (r >= v[0].size()) {
/*      */           
/*  571 */           tmp = ((Integer)v[0].elementAt(v[0].size() - 1)).intValue();
/*      */         } else {
/*      */           
/*  574 */           tmp = ((Integer)v[0].elementAt(r)).intValue();
/*      */         } 
/*  576 */         int xExp = e.a(tmp) & 0xF;
/*  577 */         this.c.write(yExp | xExp);
/*      */       } 
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
/*      */   protected void a(boolean mh, int tileIdx, int compIdx) throws IOException {
/*      */     boolean precinctPartitionUsed;
/*  603 */     int mrl = 0, a = 0;
/*  604 */     int ppx = 0, ppy = 0;
/*      */ 
/*      */     
/*  607 */     if (mh) {
/*  608 */       mrl = ((Integer)this.j.getDecompositionLevel().e(compIdx)).intValue();
/*      */       
/*  610 */       ppx = this.j.getPrecinctPartition().a(-1, compIdx, mrl);
/*  611 */       ppy = this.j.getPrecinctPartition().b(-1, compIdx, mrl);
/*  612 */       c.a.c.d[] prog = (c.a.c.d[])this.j.getProgressionType().e(compIdx);
/*      */     }
/*      */     else {
/*      */       
/*  616 */       mrl = ((Integer)this.j.getDecompositionLevel().a(tileIdx, compIdx)).intValue();
/*      */       
/*  618 */       ppx = this.j.getPrecinctPartition().a(tileIdx, compIdx, mrl);
/*  619 */       ppy = this.j.getPrecinctPartition().b(tileIdx, compIdx, mrl);
/*  620 */       c.a.c.d[] prog = (c.a.c.d[])this.j.getProgressionType().a(tileIdx, compIdx);
/*      */     } 
/*      */     
/*  623 */     if (ppx != 65535 || ppy != 65535) {
/*      */       
/*  625 */       precinctPartitionUsed = true;
/*      */     } else {
/*      */       
/*  628 */       precinctPartitionUsed = false;
/*      */     } 
/*  630 */     if (precinctPartitionUsed)
/*      */     {
/*      */       
/*  633 */       a = mrl + 1;
/*      */     }
/*      */ 
/*      */     
/*  637 */     this.c.writeShort(-173);
/*      */ 
/*      */ 
/*      */     
/*  641 */     int markSegLen = 8 + ((this.a < 257) ? 1 : 2) + a;
/*      */ 
/*      */     
/*  644 */     this.c.writeShort(markSegLen);
/*      */ 
/*      */     
/*  647 */     if (this.a < 257) {
/*  648 */       this.c.write(compIdx);
/*      */     } else {
/*      */       
/*  651 */       this.c.writeShort(compIdx);
/*      */     } 
/*      */ 
/*      */     
/*  655 */     int tmp = 0;
/*  656 */     if (precinctPartitionUsed) {
/*  657 */       tmp = 1;
/*      */     }
/*  659 */     this.c.write(tmp);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  665 */     this.c.write(mrl);
/*      */ 
/*      */     
/*  668 */     if (mh) {
/*      */ 
/*      */       
/*  671 */       tmp = this.j.getCodeBlockSize().a((byte)1, -1, compIdx);
/*  672 */       this.c.write(e.a(tmp) - 2);
/*      */       
/*  674 */       tmp = this.j.getCodeBlockSize().b((byte)1, -1, compIdx);
/*  675 */       this.c.write(e.a(tmp) - 2);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  680 */       tmp = this.j.getCodeBlockSize().a((byte)3, tileIdx, compIdx);
/*  681 */       this.c.write(e.a(tmp) - 2);
/*      */       
/*  683 */       tmp = this.j.getCodeBlockSize().b((byte)3, tileIdx, compIdx);
/*  684 */       this.c.write(e.a(tmp) - 2);
/*      */     } 
/*      */ 
/*      */     
/*  688 */     tmp = 0;
/*  689 */     if (mh) {
/*      */       
/*  691 */       if (((String)this.j.getBypass().e(compIdx)).equals("true")) {
/*  692 */         tmp |= 0x1;
/*      */       }
/*      */       
/*  695 */       if (((String)this.j.getResetMQ().e(compIdx))
/*  696 */         .equalsIgnoreCase("true")) {
/*  697 */         tmp |= 0x2;
/*      */       }
/*      */       
/*  700 */       if (((String)this.j.getTerminateOnByte().e(compIdx)).equals("true")) {
/*  701 */         tmp |= 0x4;
/*      */       }
/*      */       
/*  704 */       if (((String)this.j.getCausalCXInfo().e(compIdx)).equals("true")) {
/*  705 */         tmp |= 0x8;
/*      */       }
/*      */       
/*  708 */       if (((String)this.j.getMethodForMQTermination().e(compIdx)).equals("predict")) {
/*  709 */         tmp |= 0x10;
/*      */       }
/*      */       
/*  712 */       if (((String)this.j.getCodeSegSymbol().e(compIdx)).equals("true")) {
/*  713 */         tmp |= 0x20;
/*      */       }
/*      */     } else {
/*      */       
/*  717 */       if (((String)this.j.getBypass().a(tileIdx, compIdx))
/*  718 */         .equals("true")) {
/*  719 */         tmp |= 0x1;
/*      */       }
/*      */       
/*  722 */       if (((String)this.j.getResetMQ().a(tileIdx, compIdx))
/*  723 */         .equals("true")) {
/*  724 */         tmp |= 0x2;
/*      */       }
/*      */       
/*  727 */       if (((String)this.j.getTerminateOnByte().a(tileIdx, compIdx))
/*  728 */         .equals("true")) {
/*  729 */         tmp |= 0x4;
/*      */       }
/*      */       
/*  732 */       if (((String)this.j.getCausalCXInfo().a(tileIdx, compIdx))
/*  733 */         .equals("true")) {
/*  734 */         tmp |= 0x8;
/*      */       }
/*      */       
/*  737 */       if (((String)this.j.getMethodForMQTermination().a(tileIdx, compIdx))
/*  738 */         .equals("predict")) {
/*  739 */         tmp |= 0x10;
/*      */       }
/*      */       
/*  742 */       if (((String)this.j.getCodeSegSymbol().a(tileIdx, compIdx))
/*  743 */         .equals("true")) {
/*  744 */         tmp |= 0x20;
/*      */       }
/*      */     } 
/*  747 */     this.c.write(tmp);
/*      */ 
/*      */ 
/*      */     
/*  751 */     if (mh) {
/*  752 */       a[][] filt = (a[][])this.j.getFilters().e(compIdx);
/*  753 */       this.c.write(filt[0][0].n());
/*      */     } else {
/*  755 */       a[][] filt = (a[][])this.j.getFilters().a(tileIdx, compIdx);
/*  756 */       this.c.write(filt[0][0].n());
/*      */     } 
/*      */ 
/*      */     
/*  760 */     if (precinctPartitionUsed) {
/*      */ 
/*      */       
/*  763 */       Vector[] v = null;
/*  764 */       if (mh) {
/*  765 */         v = (Vector[])this.j.getPrecinctPartition().e(compIdx);
/*      */       } else {
/*      */         
/*  768 */         v = (Vector[])this.j.getPrecinctPartition().a(tileIdx, compIdx);
/*      */       } 
/*  770 */       for (int r = mrl; r >= 0; r--) {
/*  771 */         if (r >= v[1].size()) {
/*      */           
/*  773 */           tmp = ((Integer)v[1].elementAt(v[1].size() - 1)).intValue();
/*      */         } else {
/*      */           
/*  776 */           tmp = ((Integer)v[1].elementAt(r)).intValue();
/*      */         } 
/*  778 */         int yExp = e.a(tmp) << 4 & 0xF0;
/*      */         
/*  780 */         if (r >= v[0].size()) {
/*      */           
/*  782 */           tmp = ((Integer)v[0].elementAt(v[0].size() - 1)).intValue();
/*      */         } else {
/*      */           
/*  785 */           tmp = ((Integer)v[0].elementAt(r)).intValue();
/*      */         } 
/*  787 */         int xExp = e.a(tmp) & 0xF;
/*  788 */         this.c.write(yExp | xExp);
/*      */       } 
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
/*      */   protected void e() throws IOException {
/*      */     float step;
/*      */     o sb;
/*      */     int nqcd, i, k;
/*  804 */     String qType = (String)this.j.getQuantizationType().d();
/*  805 */     float baseStep = ((Float)this.j.getQuantizationStep().d()).floatValue();
/*  806 */     int gb = ((Integer)this.j.getGuardBits().d()).intValue();
/*      */     
/*  808 */     boolean isDerived = qType.equals("derived");
/*  809 */     boolean isReversible = qType.equals("reversible");
/*  810 */     int mrl = ((Integer)this.j.getDecompositionLevel().d()).intValue();
/*      */     
/*  812 */     int nt = this.g.getNumTiles();
/*  813 */     int nc = this.g.getNumComps();
/*      */     
/*  815 */     int[] tcIdx = new int[2];
/*      */     
/*  817 */     boolean notFound = true;
/*  818 */     for (int t = 0; t < nt && notFound; t++) {
/*  819 */       for (int c = 0; c < nc && notFound; c++) {
/*  820 */         int tmpI = ((Integer)this.j.getDecompositionLevel().a(t, c)).intValue();
/*  821 */         String tmpStr = (String)this.j.getQuantizationType().a(t, c);
/*  822 */         if (tmpI == mrl && tmpStr.equals(qType)) {
/*  823 */           tcIdx[0] = t; tcIdx[1] = c;
/*  824 */           notFound = false;
/*      */         } 
/*      */       } 
/*      */     } 
/*  828 */     if (notFound) {
/*  829 */       throw new Error("Default representative for quantization type  and number of decomposition levels not found  in main QCD marker segment. You have found a JJ2000 bug.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  835 */     o sbRoot = this.g.e(tcIdx[0], tcIdx[1]);
/*  836 */     this.ae = this.g.getNomRangeBits(tcIdx[1]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  841 */     int qstyle = isReversible ? 0 : (isDerived ? 1 : 2);
/*      */ 
/*      */ 
/*      */     
/*  845 */     this.c.writeShort(-164);
/*      */ 
/*      */ 
/*      */     
/*  849 */     switch (qstyle) {
/*      */       case 1:
/*  851 */         nqcd = 1;
/*      */         break;
/*      */       
/*      */       case 0:
/*      */       case 2:
/*  856 */         nqcd = 0;
/*      */         
/*  858 */         sb = sbRoot;
/*      */ 
/*      */         
/*  861 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/*  864 */         for (i = 0; i <= mrl; i++) {
/*  865 */           o csb = sb;
/*  866 */           while (csb != null) {
/*  867 */             nqcd++;
/*  868 */             csb = (o)csb.g();
/*      */           } 
/*      */           
/*  871 */           sb = (o)sb.h();
/*      */         } 
/*      */         break;
/*      */       default:
/*  875 */         throw new Error("Internal JJ2000 error");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  880 */     int markSegLen = 3 + (isReversible ? nqcd : (2 * nqcd));
/*      */ 
/*      */     
/*  883 */     this.c.writeShort(markSegLen);
/*      */ 
/*      */     
/*  886 */     this.c.write(qstyle + (gb << 5));
/*      */ 
/*      */     
/*  889 */     switch (qstyle) {
/*      */       case 0:
/*  891 */         sb = sbRoot;
/*  892 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/*  895 */         for (k = 0; k <= mrl; k++) {
/*  896 */           o csb = sb;
/*  897 */           while (csb != null) {
/*  898 */             int tmp = this.ae + csb.j;
/*  899 */             this.c.write(tmp << 3);
/*      */             
/*  901 */             csb = (o)csb.g();
/*      */           } 
/*      */           
/*  904 */           sb = (o)sb.h();
/*      */         } 
/*      */         return;
/*      */       case 1:
/*  908 */         sb = sbRoot;
/*  909 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */ 
/*      */         
/*  913 */         step = baseStep / (1 << sb.g);
/*      */ 
/*      */         
/*  916 */         this.c.writeShort(
/*  917 */             c.a(step));
/*      */         return;
/*      */       case 2:
/*  920 */         sb = sbRoot;
/*  921 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/*  924 */         for (k = 0; k <= mrl; k++) {
/*  925 */           o csb = sb;
/*  926 */           while (csb != null) {
/*      */ 
/*      */             
/*  929 */             step = baseStep / csb.A * (1 << csb.j);
/*      */ 
/*      */             
/*  932 */             this.c.writeShort(
/*  933 */                 c.a(step));
/*      */             
/*  935 */             csb = (o)csb.g();
/*      */           } 
/*      */           
/*  938 */           sb = (o)sb.h();
/*      */         } 
/*      */         return;
/*      */     } 
/*  942 */     throw new Error("Internal JJ2000 error");
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
/*      */   protected void a(int compIdx) throws IOException {
/*      */     int qstyle;
/*      */     float step;
/*      */     o sb;
/*  961 */     int nqcc, i, k, tIdx = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  967 */     int imgnr = this.g.getNomRangeBits(compIdx);
/*  968 */     String qType = (String)this.j.getQuantizationType().e(compIdx);
/*  969 */     float baseStep = ((Float)this.j.getQuantizationStep().e(compIdx)).floatValue();
/*  970 */     int gb = ((Integer)this.j.getGuardBits().e(compIdx)).intValue();
/*      */     
/*  972 */     boolean isReversible = qType.equals("reversible");
/*  973 */     boolean isDerived = qType.equals("derived");
/*      */     
/*  975 */     int mrl = ((Integer)this.j.getDecompositionLevel().e(compIdx)).intValue();
/*      */     
/*  977 */     int nt = this.g.getNumTiles();
/*  978 */     int nc = this.g.getNumComps();
/*      */ 
/*      */     
/*  981 */     boolean notFound = true;
/*  982 */     for (int t = 0; t < nt && notFound; t++) {
/*  983 */       for (int c = 0; c < nc && notFound; c++) {
/*  984 */         int tmpI = ((Integer)this.j.getDecompositionLevel().a(t, c)).intValue();
/*  985 */         String tmpStr = (String)this.j.getQuantizationType().a(t, c);
/*  986 */         if (tmpI == mrl && tmpStr.equals(qType)) {
/*  987 */           tIdx = t;
/*  988 */           notFound = false;
/*      */         } 
/*      */       } 
/*      */     } 
/*  992 */     if (notFound) {
/*  993 */       throw new Error("Default representative for quantization type  and number of decomposition levels not found  in main QCC (c=" + compIdx + ") marker segment. " + "You have found a JJ2000 bug.");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  998 */     o sbRoot = this.g.e(tIdx, compIdx);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1003 */     if (isReversible) {
/* 1004 */       qstyle = 0;
/*      */     }
/* 1006 */     else if (isDerived) {
/* 1007 */       qstyle = 1;
/*      */     } else {
/*      */       
/* 1010 */       qstyle = 2;
/*      */     } 
/*      */ 
/*      */     
/* 1014 */     this.c.writeShort(-163);
/*      */ 
/*      */     
/* 1017 */     switch (qstyle) {
/*      */       case 1:
/* 1019 */         nqcc = 1;
/*      */         break;
/*      */       
/*      */       case 0:
/*      */       case 2:
/* 1024 */         nqcc = 0;
/*      */         
/* 1026 */         sb = sbRoot;
/* 1027 */         mrl = sb.h;
/*      */ 
/*      */         
/* 1030 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/* 1033 */         while (sb.h != 0) {
/* 1034 */           sb = sb.u;
/*      */         }
/*      */ 
/*      */         
/* 1038 */         for (i = 0; i <= mrl; i++) {
/* 1039 */           o sb2 = sb;
/* 1040 */           while (sb2 != null) {
/* 1041 */             nqcc++;
/* 1042 */             sb2 = (o)sb2.g();
/*      */           } 
/*      */           
/* 1045 */           sb = (o)sb.h();
/*      */         } 
/*      */         break;
/*      */       default:
/* 1049 */         throw new Error("Internal JJ2000 error");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1054 */     int markSegLen = 3 + ((this.a < 257) ? 1 : 2) + (isReversible ? nqcc : (2 * nqcc));
/*      */     
/* 1056 */     this.c.writeShort(markSegLen);
/*      */ 
/*      */     
/* 1059 */     if (this.a < 257) {
/* 1060 */       this.c.write(compIdx);
/*      */     } else {
/*      */       
/* 1063 */       this.c.writeShort(compIdx);
/*      */     } 
/*      */ 
/*      */     
/* 1067 */     this.c.write(qstyle + (gb << 5));
/*      */ 
/*      */     
/* 1070 */     switch (qstyle) {
/*      */       
/*      */       case 0:
/* 1073 */         sb = sbRoot;
/* 1074 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/* 1077 */         for (k = 0; k <= mrl; k++) {
/* 1078 */           o sb2 = sb;
/* 1079 */           while (sb2 != null) {
/* 1080 */             int tmp = imgnr + sb2.j;
/* 1081 */             this.c.write(tmp << 3);
/*      */             
/* 1083 */             sb2 = (o)sb2.g();
/*      */           } 
/*      */           
/* 1086 */           sb = (o)sb.h();
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 1:
/* 1091 */         sb = sbRoot;
/* 1092 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */ 
/*      */         
/* 1096 */         step = baseStep / (1 << sb.g);
/*      */ 
/*      */         
/* 1099 */         this.c.writeShort(
/* 1100 */             c.a(step));
/*      */         return;
/*      */       
/*      */       case 2:
/* 1104 */         sb = sbRoot;
/* 1105 */         mrl = sb.h;
/*      */         
/* 1107 */         sb = (o)sb.a(0, 0);
/*      */         
/* 1109 */         for (k = 0; k <= mrl; k++) {
/* 1110 */           o sb2 = sb;
/* 1111 */           while (sb2 != null) {
/*      */ 
/*      */             
/* 1114 */             step = baseStep / sb2.A * (1 << sb2.j);
/*      */ 
/*      */             
/* 1117 */             this.c.writeShort(
/* 1118 */                 c.a(step));
/* 1119 */             sb2 = (o)sb2.g();
/*      */           } 
/*      */           
/* 1122 */           sb = (o)sb.h();
/*      */         } 
/*      */         return;
/*      */     } 
/* 1126 */     throw new Error("Internal JJ2000 error");
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
/*      */   protected void b(int tIdx) throws IOException {
/*      */     float step;
/*      */     o sb;
/*      */     int nqcd, i, k;
/* 1146 */     String qType = (String)this.j.getQuantizationType().f(tIdx);
/* 1147 */     float baseStep = ((Float)this.j.getQuantizationStep().f(tIdx)).floatValue();
/* 1148 */     int mrl = ((Integer)this.j.getDecompositionLevel().f(tIdx)).intValue();
/*      */     
/* 1150 */     int nc = this.g.getNumComps();
/*      */ 
/*      */     
/* 1153 */     boolean notFound = true;
/* 1154 */     int compIdx = 0;
/* 1155 */     for (int c = 0; c < nc && notFound; c++) {
/* 1156 */       int tmpI = ((Integer)this.j.getDecompositionLevel().a(tIdx, c)).intValue();
/* 1157 */       String tmpStr = (String)this.j.getQuantizationStep().a(tIdx, c);
/* 1158 */       if (tmpI == mrl && tmpStr.equals(qType)) {
/* 1159 */         compIdx = c;
/* 1160 */         notFound = false;
/*      */       } 
/*      */     } 
/* 1163 */     if (notFound) {
/* 1164 */       throw new Error("Default representative for quantization type  and number of decomposition levels not found  in tile QCD (t=" + tIdx + ") marker segment. " + "You have found a JJ2000 bug.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1170 */     o sbRoot = this.g.e(tIdx, compIdx);
/* 1171 */     this.af = this.g.getNomRangeBits(compIdx);
/* 1172 */     int gb = ((Integer)this.j.getGuardBits().f(tIdx)).intValue();
/*      */     
/* 1174 */     boolean isDerived = qType.equals("derived");
/* 1175 */     boolean isReversible = qType.equals("reversible");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1180 */     int qstyle = isReversible ? 0 : (isDerived ? 1 : 2);
/*      */ 
/*      */ 
/*      */     
/* 1184 */     this.c.writeShort(-164);
/*      */ 
/*      */     
/* 1187 */     switch (qstyle) {
/*      */       case 1:
/* 1189 */         nqcd = 1;
/*      */         break;
/*      */       
/*      */       case 0:
/*      */       case 2:
/* 1194 */         nqcd = 0;
/*      */         
/* 1196 */         sb = sbRoot;
/*      */ 
/*      */         
/* 1199 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/* 1202 */         for (i = 0; i <= mrl; i++) {
/* 1203 */           o csb = sb;
/* 1204 */           while (csb != null) {
/* 1205 */             nqcd++;
/* 1206 */             csb = (o)csb.g();
/*      */           } 
/*      */           
/* 1209 */           sb = (o)sb.h();
/*      */         } 
/*      */         break;
/*      */       default:
/* 1213 */         throw new Error("Internal JJ2000 error");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1218 */     int markSegLen = 3 + (isReversible ? nqcd : (2 * nqcd));
/*      */ 
/*      */     
/* 1221 */     this.c.writeShort(markSegLen);
/*      */ 
/*      */     
/* 1224 */     this.c.write(qstyle + (gb << 5));
/*      */ 
/*      */     
/* 1227 */     switch (qstyle) {
/*      */       case 0:
/* 1229 */         sb = sbRoot;
/* 1230 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/* 1233 */         for (k = 0; k <= mrl; k++) {
/* 1234 */           o csb = sb;
/* 1235 */           while (csb != null) {
/* 1236 */             int tmp = this.af + csb.j;
/* 1237 */             this.c.write(tmp << 3);
/*      */             
/* 1239 */             csb = (o)csb.g();
/*      */           } 
/*      */           
/* 1242 */           sb = (o)sb.h();
/*      */         } 
/*      */         return;
/*      */       case 1:
/* 1246 */         sb = sbRoot;
/* 1247 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */ 
/*      */         
/* 1251 */         step = baseStep / (1 << sb.g);
/*      */ 
/*      */         
/* 1254 */         this.c.writeShort(
/* 1255 */             c.a(step));
/*      */         return;
/*      */       case 2:
/* 1258 */         sb = sbRoot;
/* 1259 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/* 1262 */         for (k = 0; k <= mrl; k++) {
/* 1263 */           o csb = sb;
/* 1264 */           while (csb != null) {
/*      */ 
/*      */             
/* 1267 */             step = baseStep / csb.A * (1 << csb.j);
/*      */ 
/*      */             
/* 1270 */             this.c.writeShort(
/* 1271 */                 c.a(step));
/*      */             
/* 1273 */             csb = (o)csb.g();
/*      */           } 
/*      */           
/* 1276 */           sb = (o)sb.h();
/*      */         } 
/*      */         return;
/*      */     } 
/* 1280 */     throw new Error("Internal JJ2000 error");
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
/*      */   protected void a(int t, int compIdx) throws IOException {
/*      */     int qstyle;
/*      */     float step;
/*      */     o sb;
/*      */     int nqcc, i, k;
/* 1306 */     o sbRoot = this.g.e(t, compIdx);
/* 1307 */     int imgnr = this.g.getNomRangeBits(compIdx);
/* 1308 */     String qType = (String)this.j.getQuantizationType().a(t, compIdx);
/*      */     
/* 1310 */     float baseStep = ((Float)this.j.getQuantizationStep().a(t, compIdx)).floatValue();
/* 1311 */     int gb = ((Integer)this.j.getGuardBits().a(t, compIdx)).intValue();
/*      */     
/* 1313 */     boolean isReversible = qType.equals("reversible");
/* 1314 */     boolean isDerived = qType.equals("derived");
/*      */     
/* 1316 */     int mrl = ((Integer)this.j.getDecompositionLevel().a(t, compIdx)).intValue();
/*      */ 
/*      */     
/* 1319 */     if (isReversible) {
/* 1320 */       qstyle = 0;
/*      */     }
/* 1322 */     else if (isDerived) {
/* 1323 */       qstyle = 1;
/*      */     } else {
/*      */       
/* 1326 */       qstyle = 2;
/*      */     } 
/*      */ 
/*      */     
/* 1330 */     this.c.writeShort(-163);
/*      */ 
/*      */     
/* 1333 */     switch (qstyle) {
/*      */       case 1:
/* 1335 */         nqcc = 1;
/*      */         break;
/*      */       
/*      */       case 0:
/*      */       case 2:
/* 1340 */         nqcc = 0;
/*      */         
/* 1342 */         sb = sbRoot;
/* 1343 */         mrl = sb.h;
/*      */ 
/*      */         
/* 1346 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/* 1349 */         while (sb.h != 0) {
/* 1350 */           sb = sb.u;
/*      */         }
/*      */ 
/*      */         
/* 1354 */         for (i = 0; i <= mrl; i++) {
/* 1355 */           o sb2 = sb;
/* 1356 */           while (sb2 != null) {
/* 1357 */             nqcc++;
/* 1358 */             sb2 = (o)sb2.g();
/*      */           } 
/*      */           
/* 1361 */           sb = (o)sb.h();
/*      */         } 
/*      */         break;
/*      */       default:
/* 1365 */         throw new Error("Internal JJ2000 error");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1370 */     int markSegLen = 3 + ((this.a < 257) ? 1 : 2) + (isReversible ? nqcc : (2 * nqcc));
/*      */     
/* 1372 */     this.c.writeShort(markSegLen);
/*      */ 
/*      */     
/* 1375 */     if (this.a < 257) {
/* 1376 */       this.c.write(compIdx);
/*      */     } else {
/*      */       
/* 1379 */       this.c.writeShort(compIdx);
/*      */     } 
/*      */ 
/*      */     
/* 1383 */     this.c.write(qstyle + (gb << 5));
/*      */ 
/*      */     
/* 1386 */     switch (qstyle) {
/*      */       
/*      */       case 0:
/* 1389 */         sb = sbRoot;
/* 1390 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */         
/* 1393 */         for (k = 0; k <= mrl; k++) {
/* 1394 */           o sb2 = sb;
/* 1395 */           while (sb2 != null) {
/* 1396 */             int tmp = imgnr + sb2.j;
/* 1397 */             this.c.write(tmp << 3);
/*      */             
/* 1399 */             sb2 = (o)sb2.g();
/*      */           } 
/*      */           
/* 1402 */           sb = (o)sb.h();
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 1:
/* 1407 */         sb = sbRoot;
/* 1408 */         sb = (o)sb.a(0, 0);
/*      */ 
/*      */ 
/*      */         
/* 1412 */         step = baseStep / (1 << sb.g);
/*      */ 
/*      */         
/* 1415 */         this.c.writeShort(
/* 1416 */             c.a(step));
/*      */         return;
/*      */       
/*      */       case 2:
/* 1420 */         sb = sbRoot;
/* 1421 */         mrl = sb.h;
/*      */         
/* 1423 */         sb = (o)sb.a(0, 0);
/*      */         
/* 1425 */         for (k = 0; k <= mrl; k++) {
/* 1426 */           o sb2 = sb;
/* 1427 */           while (sb2 != null) {
/*      */ 
/*      */             
/* 1430 */             step = baseStep / sb2.A * (1 << sb2.j);
/*      */ 
/*      */             
/* 1433 */             this.c.writeShort(
/* 1434 */                 c.a(step));
/* 1435 */             sb2 = (o)sb2.g();
/*      */           } 
/*      */           
/* 1438 */           sb = (o)sb.h();
/*      */         } 
/*      */         return;
/*      */     } 
/* 1442 */     throw new Error("Internal JJ2000 error");
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
/*      */   protected void b(boolean mh, int tileIdx) throws IOException {
/* 1456 */     int markSegLen = 0;
/*      */ 
/*      */ 
/*      */     
/* 1460 */     c.a.c.d[] prog = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1465 */     if (mh) {
/* 1466 */       prog = (c.a.c.d[])this.j.getProgressionType().d();
/*      */     } else {
/*      */       
/* 1469 */       prog = (c.a.c.d[])this.j.getProgressionType().f(tileIdx);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1474 */     int lenCompField = (this.a < 257) ? 1 : 2;
/*      */ 
/*      */     
/* 1477 */     this.c.writeShort(-161);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1482 */     int npoc = prog.length;
/* 1483 */     markSegLen = 2 + npoc * (1 + lenCompField + 2 + 1 + lenCompField + 1);
/* 1484 */     this.c.writeShort(markSegLen);
/*      */ 
/*      */     
/* 1487 */     for (int i = 0; i < npoc; i++) {
/*      */       
/* 1489 */       this.c.write((prog[i]).d);
/*      */       
/* 1491 */       if (lenCompField == 2) {
/* 1492 */         this.c.writeShort((prog[i]).b);
/*      */       } else {
/*      */         
/* 1495 */         this.c.write((prog[i]).b);
/*      */       } 
/*      */       
/* 1498 */       this.c.writeShort((prog[i]).f);
/*      */       
/* 1500 */       this.c.write((prog[i]).e);
/*      */       
/* 1502 */       if (lenCompField == 2) {
/* 1503 */         this.c.writeShort((prog[i]).c);
/*      */       } else {
/*      */         
/* 1506 */         this.c.write((prog[i]).c);
/*      */       } 
/*      */       
/* 1509 */       this.c.write((prog[i]).a);
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
/*      */   public void f() throws IOException {
/* 1528 */     g();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1533 */     h();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1539 */     boolean isEresUsed = ((String)this.j.getTerminateOnByte().d()).equals("predict");
/* 1540 */     a(true, 0);
/*      */ 
/*      */     
/*      */     int i;
/*      */     
/* 1545 */     for (i = 0; i < this.a; i++) {
/*      */       
/* 1547 */       boolean isEresUsedinComp = ((String)this.j.getTerminateOnByte().e(i)).equals("predict");
/* 1548 */       if (this.j.getFilters().g(i) || this.j
/* 1549 */         .getDecompositionLevel().g(i) || this.j
/* 1550 */         .getBypass().g(i) || this.j
/* 1551 */         .getResetMQ().g(i) || this.j
/* 1552 */         .getMethodForMQTermination().g(i) || this.j
/* 1553 */         .getCodeSegSymbol().g(i) || this.j
/* 1554 */         .getCausalCXInfo().g(i) || this.j
/* 1555 */         .getPrecinctPartition().g(i) || this.j
/* 1556 */         .getCodeBlockSize().g(i) || isEresUsed != isEresUsedinComp)
/*      */       {
/*      */         
/* 1559 */         a(true, 0, i);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1565 */     e();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1571 */     for (i = 0; i < this.a; i++) {
/* 1572 */       if (this.g.getNomRangeBits(i) != this.ae || this.j
/* 1573 */         .getQuantizationType().g(i) || this.j
/* 1574 */         .getQuantizationStep().g(i) || this.j
/* 1575 */         .getDecompositionLevel().g(i) || this.j
/* 1576 */         .getGuardBits().g(i)) {
/* 1577 */         a(i);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1584 */     c.a.c.d[] prog = (c.a.c.d[])this.j.getProgressionType().d();
/* 1585 */     if (prog.length > 1) {
/* 1586 */       b(true, 0);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1591 */     i();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void i() throws IOException {
/* 1602 */     if (this.ag) {
/* 1603 */       String str = "Created by: JJ2000 version 4.1";
/*      */ 
/*      */ 
/*      */       
/* 1607 */       this.c.writeShort(-156);
/*      */ 
/*      */       
/* 1610 */       int markSegLen = 4 + str.length();
/* 1611 */       this.c.writeShort(markSegLen);
/*      */ 
/*      */       
/* 1614 */       this.c.writeShort(1);
/*      */       
/* 1616 */       byte[] chars = str.getBytes();
/* 1617 */       for (int i = 0; i < chars.length; i++) {
/* 1618 */         this.c.writeByte(chars[i]);
/*      */       }
/*      */     } 
/*      */     
/* 1622 */     if (this.ah != null) {
/* 1623 */       StringTokenizer stk = new StringTokenizer(this.ah, "#");
/* 1624 */       while (stk.hasMoreTokens()) {
/* 1625 */         String str = stk.nextToken();
/*      */ 
/*      */ 
/*      */         
/* 1629 */         this.c.writeShort(-156);
/*      */ 
/*      */         
/* 1632 */         int markSegLen = 4 + str.length();
/* 1633 */         this.c.writeShort(markSegLen);
/*      */ 
/*      */         
/* 1636 */         this.c.writeShort(1);
/*      */ 
/*      */         
/* 1639 */         byte[] chars = str.getBytes();
/* 1640 */         for (int i = 0; i < chars.length; i++) {
/* 1641 */           this.c.writeByte(chars[i]);
/*      */         }
/*      */       } 
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
/*      */   private void c(int tIdx) throws IOException {
/* 1665 */     for (int i = 0; i < this.a; i++) {
/*      */       
/* 1667 */       this.c.writeShort(-162);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1672 */       int markSegLen = 4 + ((this.a < 257) ? 1 : 2);
/* 1673 */       this.c.writeShort(markSegLen);
/*      */ 
/*      */       
/* 1676 */       if (this.a < 257) {
/* 1677 */         this.c.writeByte(i);
/*      */       } else {
/* 1679 */         this.c.writeShort(i);
/*      */       } 
/*      */       
/* 1682 */       this.c.writeByte(0);
/*      */ 
/*      */       
/* 1685 */       this.c.writeByte(((Integer)this.j.getROIs()
/* 1686 */           .a(tIdx, i)).intValue());
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
/*      */   public void b(int tileLength, int tileIdx) throws IOException {
/* 1704 */     Point numTiles = this.f.getNumTiles(null);
/* 1705 */     this.f.setTile(tileIdx % numTiles.x, tileIdx / numTiles.x);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1711 */     this.c.writeByte(-1);
/* 1712 */     this.c.writeByte(-112);
/*      */ 
/*      */     
/* 1715 */     this.c.writeByte(0);
/* 1716 */     this.c.writeByte(10);
/*      */ 
/*      */     
/* 1719 */     if (tileIdx > 65534) {
/* 1720 */       throw new IllegalArgumentException("Trying to write a tile-part header whose tile index is too high");
/*      */     }
/*      */ 
/*      */     
/* 1724 */     this.c.writeByte(tileIdx >> 8);
/* 1725 */     this.c.writeByte(tileIdx);
/*      */ 
/*      */     
/* 1728 */     int tmp = tileLength;
/* 1729 */     this.c.writeByte(tmp >> 24);
/* 1730 */     this.c.writeByte(tmp >> 16);
/* 1731 */     this.c.writeByte(tmp >> 8);
/* 1732 */     this.c.writeByte(tmp);
/*      */ 
/*      */     
/* 1735 */     this.c.writeByte(0);
/*      */ 
/*      */     
/* 1738 */     this.c.writeByte(1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1744 */     boolean isEresUsed = ((String)this.j.getMethodForMQTermination().d()).equals("predict");
/*      */     
/* 1746 */     boolean isEresUsedInTile = ((String)this.j.getMethodForMQTermination().f(tileIdx)).equals("predict");
/* 1747 */     boolean tileCODwritten = false;
/* 1748 */     if (this.j.getFilters().h(tileIdx) || this.j
/* 1749 */       .getComponentTransformation().h(tileIdx) || this.j
/* 1750 */       .getDecompositionLevel().h(tileIdx) || this.j
/* 1751 */       .getBypass().h(tileIdx) || this.j
/* 1752 */       .getResetMQ().h(tileIdx) || this.j
/* 1753 */       .getTerminateOnByte().h(tileIdx) || this.j
/* 1754 */       .getCausalCXInfo().h(tileIdx) || this.j
/* 1755 */       .getPrecinctPartition().h(tileIdx) || this.j
/* 1756 */       .getSOP().h(tileIdx) || this.j
/* 1757 */       .getCodeSegSymbol().h(tileIdx) || this.j
/* 1758 */       .getProgressionType().h(tileIdx) || this.j
/* 1759 */       .getEPH().h(tileIdx) || this.j
/* 1760 */       .getCodeBlockSize().h(tileIdx) || isEresUsed != isEresUsedInTile) {
/*      */       
/* 1762 */       a(false, tileIdx);
/* 1763 */       tileCODwritten = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1769 */     for (int c = 0; c < this.a; c++) {
/*      */ 
/*      */       
/* 1772 */       boolean isEresUsedInTileComp = ((String)this.j.getMethodForMQTermination().a(tileIdx, c)).equals("predict");
/*      */       
/* 1774 */       if (this.j.getFilters().d(tileIdx, c) || this.j
/* 1775 */         .getDecompositionLevel().d(tileIdx, c) || this.j
/* 1776 */         .getBypass().d(tileIdx, c) || this.j
/* 1777 */         .getResetMQ().d(tileIdx, c) || this.j
/* 1778 */         .getTerminateOnByte().d(tileIdx, c) || this.j
/* 1779 */         .getCausalCXInfo().d(tileIdx, c) || this.j
/* 1780 */         .getPrecinctPartition().d(tileIdx, c) || this.j
/* 1781 */         .getCodeSegSymbol().d(tileIdx, c) || this.j
/* 1782 */         .getCodeBlockSize().d(tileIdx, c) || isEresUsedInTileComp != isEresUsed) {
/*      */         
/* 1784 */         a(false, tileIdx, c);
/*      */       }
/* 1786 */       else if (tileCODwritten && (
/* 1787 */         this.j.getFilters().g(c) || this.j
/* 1788 */         .getDecompositionLevel().g(c) || this.j
/* 1789 */         .getBypass().g(c) || this.j
/* 1790 */         .getResetMQ().g(c) || this.j
/* 1791 */         .getTerminateOnByte().g(c) || this.j
/* 1792 */         .getCodeSegSymbol().g(c) || this.j
/* 1793 */         .getCausalCXInfo().g(c) || this.j
/* 1794 */         .getPrecinctPartition().g(c) || this.j
/* 1795 */         .getCodeBlockSize().g(c) || (this.j
/* 1796 */         .getMethodForMQTermination().g(c) && ((String)this.j
/* 1797 */         .getMethodForMQTermination().e(c)).equals("predict")))) {
/* 1798 */         a(false, tileIdx, c);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1806 */     boolean tileQCDwritten = false;
/* 1807 */     if (this.j.getQuantizationType().h(tileIdx) || this.j
/* 1808 */       .getQuantizationStep().h(tileIdx) || this.j
/* 1809 */       .getDecompositionLevel().h(tileIdx) || this.j
/* 1810 */       .getGuardBits().h(tileIdx)) {
/* 1811 */       b(tileIdx);
/* 1812 */       tileQCDwritten = true;
/*      */     } else {
/* 1814 */       this.af = this.ae;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1820 */     for (int i = 0; i < this.a; i++) {
/* 1821 */       if (this.g.getNomRangeBits(i) != this.af || this.j
/* 1822 */         .getQuantizationType().d(tileIdx, i) || this.j
/* 1823 */         .getQuantizationStep().d(tileIdx, i) || this.j
/* 1824 */         .getDecompositionLevel().d(tileIdx, i) || this.j
/* 1825 */         .getGuardBits().d(tileIdx, i)) {
/* 1826 */         a(tileIdx, i);
/*      */       }
/* 1828 */       else if (tileQCDwritten && (
/* 1829 */         this.j.getQuantizationType().g(i) || this.j
/* 1830 */         .getQuantizationStep().g(i) || this.j
/* 1831 */         .getDecompositionLevel().g(i) || this.j
/* 1832 */         .getGuardBits().g(i))) {
/* 1833 */         a(tileIdx, i);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1841 */     if (this.i.e() && !this.i.d()) {
/* 1842 */       c(tileIdx);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1848 */     if (this.j.getProgressionType().h(tileIdx)) {
/* 1849 */       c.a.c.d[] prog = (c.a.c.d[])this.j.getProgressionType().f(tileIdx);
/* 1850 */       if (prog.length > 1) {
/* 1851 */         b(false, tileIdx);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1857 */     this.c.writeByte(-1);
/* 1858 */     this.c.writeByte(-109);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/b/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */