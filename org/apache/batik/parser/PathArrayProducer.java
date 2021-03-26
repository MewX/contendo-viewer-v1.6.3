/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathArrayProducer
/*     */   implements PathHandler
/*     */ {
/*     */   protected LinkedList ps;
/*     */   protected float[] p;
/*     */   protected LinkedList cs;
/*     */   protected short[] c;
/*     */   protected int cindex;
/*     */   protected int pindex;
/*     */   protected int ccount;
/*     */   protected int pcount;
/*     */   
/*     */   public short[] getPathCommands() {
/*  79 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getPathParameters() {
/*  86 */     return this.p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPath() throws ParseException {
/*  95 */     this.cs = new LinkedList();
/*  96 */     this.c = new short[11];
/*  97 */     this.ps = new LinkedList();
/*  98 */     this.p = new float[11];
/*  99 */     this.ccount = 0;
/* 100 */     this.pcount = 0;
/* 101 */     this.cindex = 0;
/* 102 */     this.pindex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void movetoRel(float x, float y) throws ParseException {
/* 109 */     command((short)3);
/* 110 */     param(x);
/* 111 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void movetoAbs(float x, float y) throws ParseException {
/* 118 */     command((short)2);
/* 119 */     param(x);
/* 120 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closePath() throws ParseException {
/* 127 */     command((short)1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoRel(float x, float y) throws ParseException {
/* 134 */     command((short)5);
/* 135 */     param(x);
/* 136 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoAbs(float x, float y) throws ParseException {
/* 143 */     command((short)4);
/* 144 */     param(x);
/* 145 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoHorizontalRel(float x) throws ParseException {
/* 152 */     command((short)13);
/* 153 */     param(x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoHorizontalAbs(float x) throws ParseException {
/* 160 */     command((short)12);
/* 161 */     param(x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoVerticalRel(float y) throws ParseException {
/* 168 */     command((short)15);
/* 169 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linetoVerticalAbs(float y) throws ParseException {
/* 176 */     command((short)14);
/* 177 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoCubicRel(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {
/* 187 */     command((short)7);
/* 188 */     param(x1);
/* 189 */     param(y1);
/* 190 */     param(x2);
/* 191 */     param(y2);
/* 192 */     param(x);
/* 193 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoCubicAbs(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {
/* 203 */     command((short)6);
/* 204 */     param(x1);
/* 205 */     param(y1);
/* 206 */     param(x2);
/* 207 */     param(y2);
/* 208 */     param(x);
/* 209 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoCubicSmoothRel(float x2, float y2, float x, float y) throws ParseException {
/* 218 */     command((short)17);
/* 219 */     param(x2);
/* 220 */     param(y2);
/* 221 */     param(x);
/* 222 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoCubicSmoothAbs(float x2, float y2, float x, float y) throws ParseException {
/* 231 */     command((short)16);
/* 232 */     param(x2);
/* 233 */     param(y2);
/* 234 */     param(x);
/* 235 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoQuadraticRel(float x1, float y1, float x, float y) throws ParseException {
/* 244 */     command((short)9);
/* 245 */     param(x1);
/* 246 */     param(y1);
/* 247 */     param(x);
/* 248 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoQuadraticAbs(float x1, float y1, float x, float y) throws ParseException {
/* 257 */     command((short)8);
/* 258 */     param(x1);
/* 259 */     param(y1);
/* 260 */     param(x);
/* 261 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoQuadraticSmoothRel(float x, float y) throws ParseException {
/* 269 */     command((short)19);
/* 270 */     param(x);
/* 271 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void curvetoQuadraticSmoothAbs(float x, float y) throws ParseException {
/* 279 */     command((short)18);
/* 280 */     param(x);
/* 281 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void arcRel(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {
/* 292 */     command((short)11);
/* 293 */     param(rx);
/* 294 */     param(ry);
/* 295 */     param(xAxisRotation);
/* 296 */     param(largeArcFlag ? 1.0F : 0.0F);
/* 297 */     param(sweepFlag ? 1.0F : 0.0F);
/* 298 */     param(x);
/* 299 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void arcAbs(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {
/* 310 */     command((short)10);
/* 311 */     param(rx);
/* 312 */     param(ry);
/* 313 */     param(xAxisRotation);
/* 314 */     param(largeArcFlag ? 1.0F : 0.0F);
/* 315 */     param(sweepFlag ? 1.0F : 0.0F);
/* 316 */     param(x);
/* 317 */     param(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void command(short val) throws ParseException {
/* 324 */     if (this.cindex == this.c.length) {
/* 325 */       this.cs.add(this.c);
/* 326 */       this.c = new short[this.c.length * 2 + 1];
/* 327 */       this.cindex = 0;
/*     */     } 
/* 329 */     this.c[this.cindex++] = val;
/* 330 */     this.ccount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void param(float val) throws ParseException {
/* 337 */     if (this.pindex == this.p.length) {
/* 338 */       this.ps.add(this.p);
/* 339 */       this.p = new float[this.p.length * 2 + 1];
/* 340 */       this.pindex = 0;
/*     */     } 
/* 342 */     this.p[this.pindex++] = val;
/* 343 */     this.pcount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPath() throws ParseException {
/* 350 */     short[] allCommands = new short[this.ccount];
/* 351 */     int pos = 0;
/* 352 */     Iterator<short[]> it = this.cs.iterator();
/* 353 */     while (it.hasNext()) {
/* 354 */       short[] a = it.next();
/* 355 */       System.arraycopy(a, 0, allCommands, pos, a.length);
/* 356 */       pos += a.length;
/*     */     } 
/* 358 */     System.arraycopy(this.c, 0, allCommands, pos, this.cindex);
/* 359 */     this.cs.clear();
/* 360 */     this.c = allCommands;
/*     */     
/* 362 */     float[] allParams = new float[this.pcount];
/* 363 */     pos = 0;
/* 364 */     it = (Iterator)this.ps.iterator();
/* 365 */     while (it.hasNext()) {
/* 366 */       float[] a = (float[])it.next();
/* 367 */       System.arraycopy(a, 0, allParams, pos, a.length);
/* 368 */       pos += a.length;
/*     */     } 
/* 370 */     System.arraycopy(this.p, 0, allParams, pos, this.pindex);
/* 371 */     this.ps.clear();
/* 372 */     this.p = allParams;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/PathArrayProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */