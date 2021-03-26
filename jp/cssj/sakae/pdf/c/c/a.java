/*     */ package jp.cssj.sakae.pdf.c.c;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.pdf.c.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */ {
/*     */   protected String a;
/*     */   protected d b;
/*     */   private static final boolean c = false;
/*     */   
/*     */   public void a(String subsetName) {
/*  22 */     this.a = subsetName;
/*     */   }
/*     */   
/*     */   public void a(d font) {
/*  26 */     this.b = font;
/*     */   }
/*     */   
/*     */   public void a(OutputStream out) throws IOException {
/*     */     byte[] afterTopDict;
/*  31 */     b cout = new b(out);
/*  32 */     b bbox = this.b.c();
/*  33 */     short defaultWidth = this.b.c(0);
/*     */ 
/*     */     
/*  36 */     cout.a((byte)1, (byte)0, (byte)4, (byte)2);
/*     */ 
/*     */ 
/*     */     
/*  40 */     byte[][] nameIndex = { b.a(this.a) };
/*  41 */     cout.a(nameIndex, (byte)1);
/*     */     
/*  43 */     int offset = cout.a();
/*     */ 
/*     */ 
/*     */     
/*  47 */     try(ByteArrayOutputStream bout1 = new ByteArrayOutputStream(); 
/*  48 */         b cout1 = new b(bout1)) {
/*     */ 
/*     */       
/*  51 */       byte[][] stringIndex = { b.a(this.b.d()), b.a(this.b.e()) };
/*  52 */       cout1.a(stringIndex, (byte)1);
/*     */ 
/*     */       
/*  55 */       cout1.a(0);
/*     */       
/*  57 */       afterTopDict = bout1.toByteArray();
/*     */     } 
/*     */     
/*  60 */     offset += afterTopDict.length;
/*     */     
/*  62 */     try(ByteArrayOutputStream bout1 = new ByteArrayOutputStream(); 
/*  63 */         b cout1 = new b(bout1)) {
/*     */       
/*  65 */       int padding1 = 0;
/*     */ 
/*     */       
/*  68 */       try(ByteArrayOutputStream bout2 = new ByteArrayOutputStream(); 
/*  69 */           b cout2 = new b(bout2)) {
/*     */ 
/*     */         
/*  72 */         cout2.b(0);
/*  73 */         cout2.b(1);
/*  74 */         cout2.c(this.b.f());
/*  75 */         cout2.a(b.R);
/*     */ 
/*     */         
/*  78 */         cout2.c(bbox.a);
/*  79 */         cout2.c(bbox.b);
/*  80 */         cout2.c(bbox.c);
/*  81 */         cout2.c(bbox.d);
/*  82 */         cout2.a(b.f);
/*     */ 
/*     */         
/*  85 */         cout2.c(65535);
/*  86 */         cout2.a(b.V);
/*     */         
/*  88 */         offset += cout2.a();
/*  89 */         offset += 5;
/*  90 */         offset += 6;
/*  91 */         offset += 6;
/*  92 */         offset += 7;
/*  93 */         offset += 7;
/*     */ 
/*     */         
/*  96 */         padding1 += 5 - cout2.c(offset + cout1.a());
/*  97 */         cout2.a(b.o);
/*     */         
/*  99 */         cout1.a((byte)2);
/* 100 */         cout1.a(1);
/* 101 */         cout1.a(this.b.h() - 1);
/*     */ 
/*     */         
/* 104 */         padding1 += 5 - cout2.c(offset + cout1.a());
/* 105 */         cout2.a(b.q);
/*     */         
/* 107 */         List<byte[]> fonts = (List)new ArrayList<>();
/* 108 */         for (int j = 0; j < this.b.g(); j++) {
/* 109 */           short width = this.b.c(j);
/*     */           
/* 111 */           byte[] charString = this.b.f(j);
/* 112 */           if (charString != null) {
/* 113 */             if (defaultWidth != width) {
/* 114 */               ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 115 */               c tout3 = new c(byteArrayOutputStream);
/* 116 */               tout3.a((short)(width - defaultWidth));
/* 117 */               tout3.write(charString);
/* 118 */               tout3.close();
/* 119 */               fonts.add(byteArrayOutputStream.toByteArray());
/*     */             } else {
/* 121 */               fonts.add(charString);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 126 */             Shape shape = this.b.a_(j);
/* 127 */             if (shape == null) {
/* 128 */               fonts.add(c.j);
/*     */             } else {
/*     */               
/* 131 */               try(ByteArrayOutputStream bout3 = new ByteArrayOutputStream(); 
/* 132 */                   c tout3 = new c(byteArrayOutputStream))
/* 133 */               { if (defaultWidth != width) {
/* 134 */                   tout3.a((short)(width - defaultWidth));
/*     */                 }
/*     */                 
/* 137 */                 PathIterator pathIterator = shape.getPathIterator(null);
/* 138 */                 double cx = 0.0D, cy = 0.0D;
/* 139 */                 boolean closed = true;
/* 140 */                 double[] cord = new double[6];
/* 141 */                 while (!pathIterator.isDone()) {
/* 142 */                   double x, x1, xa, y, y1, ya; short dx; double x2, xb; short dy; double y2, yb, d1, xc, d2, yc, d3; short dxa, dya; double d4; short dxb, dyb; double d5; short dxc, dyc; double d6; short s1, s2, s3, s4, s5, s6; int type = pathIterator.currentSegment(cord);
/* 143 */                   switch (type) {
/*     */                     case 0:
/* 145 */                       x = cord[0];
/* 146 */                       y = -cord[1];
/* 147 */                       dx = (short)(int)Math.round(x - cx);
/* 148 */                       dy = (short)(int)Math.round(y - cy);
/* 149 */                       if (dx == 0) {
/* 150 */                         tout3.a(dy);
/* 151 */                         tout3.a(c.c);
/*     */ 
/*     */ 
/*     */                         
/* 155 */                         cy += dy;
/* 156 */                       } else if (dy == 0) {
/* 157 */                         tout3.a(dx);
/* 158 */                         tout3.a(c.o);
/*     */ 
/*     */ 
/*     */                         
/* 162 */                         cx += dx;
/*     */                       } else {
/* 164 */                         tout3.a(dx);
/* 165 */                         tout3.a(dy);
/* 166 */                         tout3.a(c.n);
/*     */ 
/*     */ 
/*     */                         
/* 170 */                         cx += dx;
/* 171 */                         cy += dy;
/*     */                       } 
/* 173 */                       closed = false;
/*     */                       break;
/*     */ 
/*     */                     
/*     */                     case 1:
/* 178 */                       if (closed) {
/* 179 */                         tout3.a((short)0);
/* 180 */                         tout3.a(c.o);
/*     */ 
/*     */ 
/*     */                         
/* 184 */                         closed = false;
/*     */                       } 
/* 186 */                       x = cord[0];
/* 187 */                       y = -cord[1];
/* 188 */                       dx = (short)(int)Math.round(x - cx);
/* 189 */                       dy = (short)(int)Math.round(y - cy);
/* 190 */                       if (dx == 0) {
/* 191 */                         tout3.a(dy);
/* 192 */                         tout3.a(c.f);
/*     */ 
/*     */ 
/*     */                         
/* 196 */                         cy += dy; break;
/* 197 */                       }  if (dy == 0) {
/* 198 */                         tout3.a(dx);
/* 199 */                         tout3.a(c.e);
/*     */ 
/*     */ 
/*     */                         
/* 203 */                         cx += dx; break;
/*     */                       } 
/* 205 */                       tout3.a(dx);
/* 206 */                       tout3.a(dy);
/* 207 */                       tout3.a(c.d);
/*     */ 
/*     */ 
/*     */                       
/* 211 */                       cx += dx;
/* 212 */                       cy += dy;
/*     */                       break;
/*     */ 
/*     */ 
/*     */                     
/*     */                     case 2:
/* 218 */                       if (closed) {
/* 219 */                         tout3.a((short)0);
/* 220 */                         tout3.a(c.o);
/*     */ 
/*     */ 
/*     */                         
/* 224 */                         closed = false;
/*     */                       } 
/* 226 */                       x1 = cord[0];
/* 227 */                       y1 = -cord[1];
/* 228 */                       x2 = cord[2];
/* 229 */                       y2 = -cord[3];
/* 230 */                       d1 = cx + 2.0D * (x1 - cx) / 3.0D;
/* 231 */                       d2 = cy + 2.0D * (y1 - cy) / 3.0D;
/* 232 */                       d3 = d1 + (x2 - cx) / 3.0D;
/* 233 */                       d4 = d2 + (y2 - cy) / 3.0D;
/* 234 */                       d5 = x2;
/* 235 */                       d6 = y2;
/* 236 */                       s1 = (short)(int)Math.round(d1 - cx);
/* 237 */                       s2 = (short)(int)Math.round(d2 - cy);
/* 238 */                       cx += s1;
/* 239 */                       cy += s2;
/* 240 */                       s3 = (short)(int)Math.round(d3 - cx);
/* 241 */                       s4 = (short)(int)Math.round(d4 - cy);
/* 242 */                       cx += s3;
/* 243 */                       cy += s4;
/* 244 */                       s5 = (short)(int)Math.round(d5 - cx);
/* 245 */                       s6 = (short)(int)Math.round(d6 - cy);
/* 246 */                       cx += s5;
/* 247 */                       cy += s6;
/* 248 */                       tout3.a(s1);
/* 249 */                       tout3.a(s2);
/* 250 */                       tout3.a(s3);
/* 251 */                       tout3.a(s4);
/* 252 */                       tout3.a(s5);
/* 253 */                       tout3.a(s6);
/* 254 */                       tout3.a(c.g);
/*     */                       break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     case 3:
/* 263 */                       if (closed) {
/* 264 */                         tout3.a((short)0);
/* 265 */                         tout3.a(c.o);
/*     */ 
/*     */ 
/*     */                         
/* 269 */                         closed = false;
/*     */                       } 
/* 271 */                       xa = cord[0];
/* 272 */                       ya = -cord[1];
/* 273 */                       xb = cord[2];
/* 274 */                       yb = -cord[3];
/* 275 */                       xc = cord[4];
/* 276 */                       yc = -cord[5];
/* 277 */                       dxa = (short)(int)Math.round(xa - cx);
/* 278 */                       dya = (short)(int)Math.round(ya - cy);
/* 279 */                       cx += dxa;
/* 280 */                       cy += dya;
/* 281 */                       dxb = (short)(int)Math.round(xb - cx);
/* 282 */                       dyb = (short)(int)Math.round(yb - cy);
/* 283 */                       cx += dxb;
/* 284 */                       cy += dyb;
/* 285 */                       dxc = (short)(int)Math.round(xc - cx);
/* 286 */                       dyc = (short)(int)Math.round(yc - cy);
/* 287 */                       cx += dxc;
/* 288 */                       cy += dyc;
/* 289 */                       tout3.a(dxa);
/* 290 */                       tout3.a(dya);
/* 291 */                       tout3.a(dxb);
/* 292 */                       tout3.a(dyb);
/* 293 */                       tout3.a(dxc);
/* 294 */                       tout3.a(dyc);
/* 295 */                       tout3.a(c.g);
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 300 */                       cx = xc;
/* 301 */                       cy = yc;
/*     */                       break;
/*     */ 
/*     */                     
/*     */                     case 4:
/* 306 */                       pathIterator.next();
/* 307 */                       if (pathIterator.isDone()) {
/*     */                         break;
/*     */                       }
/* 310 */                       closed = true;
/*     */                       continue;
/*     */                   } 
/* 313 */                   pathIterator.next();
/*     */                 } 
/* 315 */                 tout3.a(c.j);
/* 316 */                 byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
/* 317 */                 fonts.add(arrayOfByte); } 
/*     */             } 
/*     */           } 
/* 320 */         }  cout1.a(fonts.<byte[]>toArray(new byte[fonts.size()][]), (byte)4);
/*     */ 
/*     */         
/* 323 */         padding1 += 5 - cout2.c(offset + cout1.a());
/* 324 */         cout2.a(b.Y);
/*     */         
/* 326 */         cout1.a((byte)3);
/* 327 */         cout1.a(1);
/* 328 */         cout1.a(0);
/* 329 */         cout1.a((byte)0);
/* 330 */         cout1.a((short)this.b.g());
/*     */ 
/*     */         
/* 333 */         padding1 += 5 - cout2.c(offset + cout1.a());
/* 334 */         cout2.a(b.X);
/*     */         
/* 336 */         try(ByteArrayOutputStream bout3 = new ByteArrayOutputStream(); 
/* 337 */             b cout3 = new b(bout3)) {
/*     */           byte[] privateDict;
/*     */ 
/*     */           
/* 341 */           try(ByteArrayOutputStream bout4 = new ByteArrayOutputStream(); 
/* 342 */               b cout4 = new b(bout4)) {
/* 343 */             cout4.c(1);
/* 344 */             cout4.a(b.K);
/*     */             
/* 346 */             cout4.c(defaultWidth);
/* 347 */             cout4.a(b.t);
/*     */             
/* 349 */             cout4.c(defaultWidth);
/* 350 */             cout4.a(b.u);
/*     */             
/* 352 */             privateDict = bout4.toByteArray();
/*     */           } 
/*     */           
/* 355 */           cout3.c(privateDict.length);
/* 356 */           int padding2 = 5 - cout3.c(offset + cout1.a() + 5 + cout3.a() + 6);
/* 357 */           cout3.a(b.r);
/*     */           
/* 359 */           cout1.a(new byte[][] { bout3.toByteArray() }, (byte)1);
/*     */ 
/*     */           
/* 362 */           for (int k = 0; k < padding2; k++) {
/* 363 */             cout1.write(0);
/*     */           }
/*     */           
/* 366 */           cout1.write(privateDict);
/*     */         } 
/*     */         
/* 369 */         cout.a(new byte[][] { bout2.toByteArray() }, (byte)1);
/*     */       } 
/*     */       
/* 372 */       cout.write(afterTopDict);
/*     */ 
/*     */       
/* 375 */       for (int i = 0; i < padding1; i++) {
/* 376 */         cout.write(0);
/*     */       }
/*     */       
/* 379 */       cout.write(bout1.toByteArray());
/*     */     } 
/* 381 */     cout.flush();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */