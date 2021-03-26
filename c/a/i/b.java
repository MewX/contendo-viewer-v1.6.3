/*     */ package c.a.i;
/*     */ 
/*     */ import c.a.f.a;
/*     */ import c.a.f.d;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */ {
/*     */   private boolean a;
/*     */   private boolean b;
/*     */   private boolean c;
/*     */   private boolean d;
/*     */   private int e;
/*     */   private int f;
/*     */   private File g;
/*  87 */   private static int h = 14;
/*     */ 
/*     */   
/*  90 */   private static int i = 16;
/*     */ 
/*     */   
/*     */   private int j;
/*     */ 
/*     */   
/*  96 */   private int[] k = new int[this.e];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer[] l;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] m;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[][][] n;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[][] o;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[][][] p;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[][][] q;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[][][] r;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(File file, int nt, int pptp, boolean ppm, boolean ppt, boolean tempSop, boolean tempEph) {
/* 140 */     this.g = file;
/* 141 */     this.e = nt;
/* 142 */     this.f = pptp;
/* 143 */     this.a = ppm;
/* 144 */     this.b = ppt;
/* 145 */     this.c = tempSop;
/* 146 */     this.d = tempEph;
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
/*     */   public int a() throws IOException {
/* 158 */     int addedHeaderBytes = 0;
/* 159 */     this.k = new int[this.e];
/* 160 */     this.n = new byte[this.e][][];
/* 161 */     this.o = new byte[this.e][];
/* 162 */     this.p = new byte[this.e][][];
/* 163 */     this.q = new byte[this.e][][];
/* 164 */     this.r = new byte[this.e][][];
/*     */ 
/*     */     
/* 167 */     if (!this.a && !this.b && this.f == 0) {
/* 168 */       return 0;
/*     */     }
/*     */     
/* 171 */     a fi = new a(this.g, "rw+");
/*     */     
/* 173 */     addedHeaderBytes -= fi.length();
/*     */ 
/*     */     
/* 176 */     a((d)fi);
/*     */ 
/*     */     
/* 179 */     b((d)fi);
/*     */ 
/*     */     
/* 182 */     fi.close();
/* 183 */     fi = new a(this.g, "rw");
/*     */ 
/*     */     
/* 186 */     b();
/*     */ 
/*     */     
/* 189 */     c((d)fi);
/*     */ 
/*     */     
/* 192 */     fi.flush();
/* 193 */     addedHeaderBytes += fi.length();
/* 194 */     fi.close();
/*     */     
/* 196 */     return addedHeaderBytes;
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
/*     */   private void a(d fi) throws IOException {
/* 209 */     int sop = 0, eph = 0;
/*     */ 
/*     */ 
/*     */     
/* 213 */     Vector<Integer> markPos = new Vector();
/*     */ 
/*     */     
/* 216 */     short marker = (short)fi.readUnsignedShort();
/* 217 */     marker = (short)fi.readUnsignedShort();
/* 218 */     while (marker != -112) {
/* 219 */       int i = fi.getPos();
/* 220 */       int length = fi.readUnsignedShort();
/*     */ 
/*     */ 
/*     */       
/* 224 */       if (marker == -174) {
/* 225 */         int scod = fi.readUnsignedByte();
/* 226 */         if (this.c)
/* 227 */           scod &= 0xFD; 
/* 228 */         if (this.d)
/* 229 */           scod &= 0xFB; 
/* 230 */         fi.seek(i + 2);
/* 231 */         fi.write(scod);
/*     */       } 
/*     */       
/* 234 */       fi.seek(i + length);
/* 235 */       marker = (short)fi.readUnsignedShort();
/*     */     } 
/* 237 */     int pos = fi.getPos();
/* 238 */     fi.seek(pos - 2);
/*     */ 
/*     */     
/* 241 */     for (int t = 0; t < this.e; t++) {
/*     */       
/* 243 */       fi.readUnsignedShort();
/* 244 */       pos = fi.getPos();
/* 245 */       markPos.addElement(new Integer(fi.getPos()));
/* 246 */       fi.readInt();
/* 247 */       int length = fi.readInt();
/* 248 */       fi.readUnsignedShort();
/* 249 */       int tileEnd = pos + length - 2;
/*     */ 
/*     */       
/* 252 */       marker = (short)fi.readUnsignedShort();
/* 253 */       while (marker != -109) {
/* 254 */         pos = fi.getPos();
/* 255 */         length = fi.readUnsignedShort();
/*     */ 
/*     */ 
/*     */         
/* 259 */         if (marker == -174) {
/* 260 */           int scod = fi.readUnsignedByte();
/* 261 */           if (this.c)
/* 262 */             scod &= 0xFD; 
/* 263 */           if (this.d)
/* 264 */             scod &= 0xFB; 
/* 265 */           fi.seek(pos + 2);
/* 266 */           fi.write(scod);
/*     */         } 
/* 268 */         fi.seek(pos + length);
/* 269 */         marker = (short)fi.readUnsignedShort();
/*     */       } 
/*     */ 
/*     */       
/* 273 */       sop = 0;
/* 274 */       eph = 0;
/*     */       
/* 276 */       int i = fi.getPos();
/* 277 */       while (i < tileEnd) {
/* 278 */         int halfMarker = (short)fi.readUnsignedByte();
/* 279 */         if (halfMarker == 255) {
/*     */           
/* 281 */           marker = (short)((halfMarker << 8) + fi.readUnsignedByte());
/* 282 */           i++;
/* 283 */           if (marker == -111) {
/* 284 */             markPos.addElement(new Integer(fi.getPos()));
/* 285 */             this.k[t] = this.k[t] + 1;
/* 286 */             sop++;
/* 287 */             fi.skipBytes(4);
/* 288 */             i += 4;
/*     */           } 
/*     */           
/* 291 */           if (marker == -110) {
/* 292 */             markPos.addElement(new Integer(fi.getPos()));
/* 293 */             eph++;
/*     */           } 
/*     */         } 
/* 296 */         i++;
/*     */       } 
/*     */     } 
/* 299 */     markPos.addElement(new Integer(fi.getPos() + 2));
/* 300 */     this.l = new Integer[markPos.size()];
/* 301 */     markPos.copyInto((Object[])this.l);
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
/*     */   private void b(d fi) throws IOException {
/* 316 */     fi.seek(0);
/* 317 */     int length = this.l[0].intValue() - 2;
/* 318 */     this.m = new byte[length];
/* 319 */     fi.readFully(this.m, 0, length);
/* 320 */     int markIndex = 0;
/*     */     
/* 322 */     for (int t = 0; t < this.e; t++) {
/* 323 */       int prem = this.k[t];
/*     */       
/* 325 */       this.p[t] = new byte[prem][];
/* 326 */       this.q[t] = new byte[prem][];
/* 327 */       this.r[t] = new byte[prem][];
/*     */ 
/*     */ 
/*     */       
/* 331 */       length = this.l[markIndex + 1].intValue() - this.l[markIndex].intValue();
/* 332 */       this.o[t] = new byte[length];
/* 333 */       fi.readFully(this.o[t], 0, length);
/* 334 */       markIndex++;
/*     */       
/* 336 */       for (int p = 0; p < prem; p++) {
/*     */ 
/*     */         
/* 339 */         length = this.l[markIndex + 1].intValue() - this.l[markIndex].intValue();
/*     */         
/* 341 */         if (this.c) {
/* 342 */           length -= 6;
/* 343 */           fi.skipBytes(6);
/*     */         } else {
/*     */           
/* 346 */           length -= 6;
/* 347 */           this.r[t][p] = new byte[6];
/* 348 */           fi.readFully(this.r[t][p], 0, 6);
/*     */         } 
/*     */         
/* 351 */         if (!this.d) {
/* 352 */           length += 2;
/*     */         }
/*     */         
/* 355 */         this.p[t][p] = new byte[length];
/* 356 */         fi.readFully(this.p[t][p], 0, length);
/* 357 */         markIndex++;
/*     */ 
/*     */ 
/*     */         
/* 361 */         length = this.l[markIndex + 1].intValue() - this.l[markIndex].intValue();
/*     */         
/* 363 */         length -= 2;
/* 364 */         if (this.d) {
/* 365 */           fi.skipBytes(2);
/*     */         }
/*     */         
/* 368 */         this.q[t][p] = new byte[length];
/* 369 */         fi.readFully(this.q[t][p], 0, length);
/* 370 */         markIndex++;
/*     */       } 
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
/*     */   private void b() throws IOException {
/* 389 */     ByteArrayOutputStream temp = new ByteArrayOutputStream();
/*     */ 
/*     */ 
/*     */     
/* 393 */     this.n = new byte[this.e][][];
/* 394 */     this.j = 0;
/*     */     
/* 396 */     for (int t = 0; t < this.e; t++) {
/*     */ 
/*     */       
/* 399 */       if (this.f == 0)
/* 400 */         this.f = this.k[t]; 
/* 401 */       int prem = this.k[t];
/* 402 */       int numTileParts = (int)Math.ceil(prem / this.f);
/* 403 */       int numPackets = (this.p[t]).length;
/* 404 */       this.j = (numTileParts > this.j) ? numTileParts : this.j;
/* 405 */       this.n[t] = new byte[numTileParts][];
/*     */ 
/*     */       
/* 408 */       int tppStart = 0;
/* 409 */       int pIndex = 0;
/* 410 */       int p = 0;
/* 411 */       int phIndex = 0;
/* 412 */       for (int tilePart = 0; tilePart < numTileParts; tilePart++) {
/*     */ 
/*     */         
/* 415 */         int nomnp = (this.f > prem) ? prem : this.f;
/* 416 */         int np = nomnp;
/*     */ 
/*     */         
/* 419 */         if (tilePart == 0) {
/*     */           
/* 421 */           temp.write(this.o[t], 0, (this.o[t]).length - 2);
/*     */         }
/*     */         else {
/*     */           
/* 425 */           temp.write(new byte[h - 2], 0, h - 2);
/*     */         } 
/*     */ 
/*     */         
/* 429 */         if (this.b) {
/* 430 */           int pptLength = 3;
/* 431 */           int pptIndex = 0;
/*     */ 
/*     */           
/* 434 */           p = pIndex;
/* 435 */           while (np > 0) {
/* 436 */             int phLength = (this.p[t][p]).length;
/*     */ 
/*     */ 
/*     */             
/* 440 */             if (pptLength + phLength > 65535) {
/* 441 */               temp.write(16777215);
/* 442 */               temp.write(-159);
/* 443 */               temp.write(pptLength >>> 8);
/* 444 */               temp.write(pptLength);
/* 445 */               temp.write(pptIndex++);
/* 446 */               for (int j = pIndex; j < p; j++) {
/* 447 */                 temp.write(this.p[t][j], 0, (this.p[t][j]).length);
/*     */               }
/*     */               
/* 450 */               pptLength = 3;
/* 451 */               pIndex = p;
/*     */             } 
/* 453 */             pptLength += phLength;
/* 454 */             p++;
/* 455 */             np--;
/*     */           } 
/*     */           
/* 458 */           temp.write(16777215);
/* 459 */           temp.write(-159);
/* 460 */           temp.write(pptLength >>> 8);
/* 461 */           temp.write(pptLength);
/* 462 */           temp.write(pptIndex);
/* 463 */           for (int i = pIndex; i < p; i++)
/*     */           {
/* 465 */             temp.write(this.p[t][i], 0, (this.p[t][i]).length);
/*     */           }
/*     */         } 
/*     */         
/* 469 */         pIndex = p;
/* 470 */         np = nomnp;
/*     */ 
/*     */         
/* 473 */         temp.write(16777215);
/* 474 */         temp.write(-109);
/*     */ 
/*     */         
/* 477 */         for (p = tppStart; p < tppStart + np; p++) {
/* 478 */           if (!this.c) {
/* 479 */             temp.write(this.r[t][p], 0, 6);
/*     */           }
/*     */           
/* 482 */           if (!this.a && !this.b) {
/* 483 */             temp.write(this.p[t][p], 0, (this.p[t][p]).length);
/*     */           }
/*     */ 
/*     */           
/* 487 */           temp.write(this.q[t][p], 0, (this.q[t][p]).length);
/*     */         } 
/* 489 */         tppStart += np;
/*     */ 
/*     */         
/* 492 */         byte[] tempByteArr = temp.toByteArray();
/* 493 */         this.n[t][tilePart] = tempByteArr;
/* 494 */         int length = temp.size();
/*     */         
/* 496 */         if (tilePart == 0) {
/*     */           
/* 498 */           tempByteArr[6] = (byte)(length >>> 24);
/* 499 */           tempByteArr[7] = (byte)(length >>> 16);
/* 500 */           tempByteArr[8] = (byte)(length >>> 8);
/* 501 */           tempByteArr[9] = (byte)length;
/* 502 */           tempByteArr[10] = 0;
/* 503 */           tempByteArr[11] = (byte)numTileParts;
/*     */         } else {
/*     */           
/* 506 */           tempByteArr[0] = -1;
/* 507 */           tempByteArr[1] = -112;
/* 508 */           tempByteArr[2] = 0;
/* 509 */           tempByteArr[3] = 10;
/* 510 */           tempByteArr[4] = (byte)(t >> 8);
/* 511 */           tempByteArr[5] = (byte)t;
/* 512 */           tempByteArr[6] = (byte)(length >>> 24);
/* 513 */           tempByteArr[7] = (byte)(length >>> 16);
/* 514 */           tempByteArr[8] = (byte)(length >>> 8);
/* 515 */           tempByteArr[9] = (byte)length;
/* 516 */           tempByteArr[10] = (byte)tilePart;
/* 517 */           tempByteArr[11] = (byte)numTileParts;
/*     */         } 
/* 519 */         temp.reset();
/* 520 */         prem -= np;
/*     */       } 
/*     */     } 
/* 523 */     temp.close();
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
/*     */   private void c(d fi) throws IOException {
/* 536 */     int numTiles = this.n.length;
/* 537 */     int[][] packetHeaderLengths = new int[numTiles][this.j];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 542 */     fi.a(this.m, 0, this.m.length);
/*     */ 
/*     */     
/* 545 */     if (this.a) {
/* 546 */       ByteArrayOutputStream ppmMarkerSegment = new ByteArrayOutputStream();
/*     */ 
/*     */ 
/*     */       
/* 550 */       int ppmIndex = 0;
/*     */ 
/*     */       
/* 553 */       int[] prem = new int[numTiles];
/*     */       
/*     */       int t;
/* 556 */       for (t = 0; t < numTiles; t++) {
/* 557 */         prem[t] = (this.p[t]).length;
/*     */       }
/*     */       int i;
/* 560 */       for (i = 0; i < this.j; i++) {
/* 561 */         for (t = 0; t < numTiles; t++) {
/*     */           
/* 563 */           if ((this.n[t]).length > i) {
/* 564 */             int totNumPackets = (this.p[t]).length;
/*     */             
/* 566 */             int numPackets = (i == (this.n[t]).length - 1) ? prem[t] : this.f;
/*     */ 
/*     */ 
/*     */             
/* 570 */             int pStart = totNumPackets - prem[t];
/* 571 */             int pStop = pStart + numPackets;
/*     */ 
/*     */ 
/*     */             
/* 575 */             for (int p = pStart; p < pStop; p++) {
/* 576 */               packetHeaderLengths[t][i] = packetHeaderLengths[t][i] + (this.p[t][p]).length;
/*     */             }
/*     */             
/* 579 */             prem[t] = prem[t] - numPackets;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 585 */       ppmMarkerSegment.write(16777215);
/* 586 */       ppmMarkerSegment.write(-160);
/* 587 */       ppmMarkerSegment.write(0);
/* 588 */       ppmMarkerSegment.write(0);
/* 589 */       ppmMarkerSegment.write(0);
/* 590 */       int ppmLength = 3;
/* 591 */       ppmIndex++;
/*     */ 
/*     */       
/* 594 */       for (t = 0; t < numTiles; t++) {
/* 595 */         prem[t] = (this.p[t]).length;
/*     */       }
/*     */       
/* 598 */       for (i = 0; i < this.j; i++) {
/* 599 */         for (t = 0; t < numTiles; t++) {
/*     */           
/* 601 */           if ((this.n[t]).length > i) {
/* 602 */             int totNumPackets = (this.p[t]).length;
/*     */ 
/*     */             
/* 605 */             int numPackets = (i == (this.n[t]).length - 1) ? prem[t] : this.f;
/*     */ 
/*     */             
/* 608 */             int pStart = totNumPackets - prem[t];
/* 609 */             int pStop = pStart + numPackets;
/*     */ 
/*     */ 
/*     */             
/* 613 */             if (ppmLength + 4 > 65535) {
/*     */               
/* 615 */               byte[] arrayOfByte = ppmMarkerSegment.toByteArray();
/* 616 */               int k = arrayOfByte.length - 2;
/* 617 */               arrayOfByte[2] = (byte)(k >>> 8);
/* 618 */               arrayOfByte[3] = (byte)k;
/* 619 */               fi.a(arrayOfByte, 0, k + 2);
/*     */ 
/*     */               
/* 622 */               ppmMarkerSegment.reset();
/* 623 */               ppmMarkerSegment.write(16777215);
/* 624 */               ppmMarkerSegment.write(-160);
/* 625 */               ppmMarkerSegment.write(0);
/* 626 */               ppmMarkerSegment.write(0);
/* 627 */               ppmMarkerSegment.write(ppmIndex++);
/* 628 */               ppmLength = 3;
/*     */             } 
/*     */ 
/*     */             
/* 632 */             int j = packetHeaderLengths[t][i];
/* 633 */             ppmMarkerSegment.write(j >>> 24);
/* 634 */             ppmMarkerSegment.write(j >>> 16);
/* 635 */             ppmMarkerSegment.write(j >>> 8);
/* 636 */             ppmMarkerSegment.write(j);
/* 637 */             ppmLength += 4;
/*     */ 
/*     */             
/* 640 */             for (int p = pStart; p < pStop; p++) {
/* 641 */               j = (this.p[t][p]).length;
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 646 */               if (ppmLength + j > 65535) {
/*     */                 
/* 648 */                 byte[] arrayOfByte = ppmMarkerSegment.toByteArray();
/* 649 */                 j = arrayOfByte.length - 2;
/* 650 */                 arrayOfByte[2] = (byte)(j >>> 8);
/* 651 */                 arrayOfByte[3] = (byte)j;
/* 652 */                 fi.a(arrayOfByte, 0, j + 2);
/*     */ 
/*     */                 
/* 655 */                 ppmMarkerSegment.reset();
/* 656 */                 ppmMarkerSegment.write(16777215);
/* 657 */                 ppmMarkerSegment.write(-160);
/* 658 */                 ppmMarkerSegment.write(0);
/* 659 */                 ppmMarkerSegment.write(0);
/* 660 */                 ppmMarkerSegment.write(ppmIndex++);
/* 661 */                 ppmLength = 3;
/*     */               } 
/*     */ 
/*     */               
/* 665 */               ppmMarkerSegment.write(this.p[t][p], 0, (this.p[t][p]).length);
/*     */               
/* 667 */               ppmLength += (this.p[t][p]).length;
/*     */             } 
/* 669 */             prem[t] = prem[t] - numPackets;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 674 */       byte[] temp = ppmMarkerSegment.toByteArray();
/* 675 */       int length = temp.length - 2;
/* 676 */       temp[2] = (byte)(length >>> 8);
/* 677 */       temp[3] = (byte)length;
/* 678 */       fi.a(temp, 0, length + 2);
/*     */     } 
/*     */ 
/*     */     
/* 682 */     for (int tp = 0; tp < this.j; tp++) {
/* 683 */       for (int t = 0; t < this.e; t++) {
/* 684 */         if ((this.n[t]).length >= tp) {
/* 685 */           byte[] temp = this.n[t][tp];
/* 686 */           int length = temp.length;
/* 687 */           fi.a(temp, 0, length);
/*     */         } 
/*     */       } 
/* 690 */     }  fi.writeShort(-39);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/i/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */