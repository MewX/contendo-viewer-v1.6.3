/*     */ package jp.cssj.sakae.pdf.c.a;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.sakae.e.c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class l
/*     */   implements Serializable
/*     */ {
/*     */   private static final long a = 0L;
/*  18 */   private static final m[] b = new m[0];
/*     */ 
/*     */ 
/*     */   
/*     */   private final short c;
/*     */ 
/*     */ 
/*     */   
/*     */   private final m[] d;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public l(short defaultWidth, m[] widths) {
/*  32 */     this.c = defaultWidth;
/*  33 */     this.d = (widths == null) ? b : widths;
/*     */   }
/*     */   
/*     */   public l(short defaultWidth) {
/*  37 */     this(defaultWidth, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short a() {
/*  46 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public m[] b() {
/*  55 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short a(int gid) {
/*  65 */     for (int i = 0; i < this.d.length; i++) {
/*  66 */       m width = this.d[i];
/*  67 */       if (width.a() <= gid && width.b() >= gid) {
/*  68 */         return width.a(gid);
/*     */       }
/*     */     } 
/*  71 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static l a(short[] widths) {
/*  81 */     List<m> list = new ArrayList<>();
/*  82 */     c widthCounts = new c();
/*  83 */     short[] runWidths = new short[255];
/*     */     
/*  85 */     int position = 0;
/*  86 */     int startCid = -1;
/*  87 */     boolean run = false;
/*  88 */     for (int cid = 0; cid < widths.length; cid++) {
/*  89 */       short advance = widths[cid];
/*     */       
/*  91 */       if (advance == Short.MIN_VALUE) {
/*  92 */         if (position == 0) {
/*     */           continue;
/*     */         }
/*  95 */         advance = runWidths[position - 1];
/*     */       } 
/*     */       
/*  98 */       int count = widthCounts.b(advance);
/*  99 */       count++;
/* 100 */       widthCounts.a(advance, count);
/*     */       
/* 102 */       if (startCid == -1) {
/*     */         
/* 104 */         startCid = cid;
/* 105 */         runWidths[position++] = advance;
/*     */       
/*     */       }
/* 108 */       else if (runWidths[position - 1] == advance) {
/*     */         
/* 110 */         run = true;
/*     */       }
/* 112 */       else if (startCid == cid - 1 || (!run && position < runWidths.length)) {
/*     */         
/* 114 */         runWidths[position++] = advance;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 119 */         short[] temp = new short[position];
/* 120 */         System.arraycopy(runWidths, 0, temp, 0, position);
/* 121 */         list.add(new m(startCid, cid - 1, temp));
/* 122 */         startCid = cid;
/* 123 */         runWidths[0] = advance;
/* 124 */         position = 1;
/* 125 */         run = false;
/*     */       }  continue;
/* 127 */     }  if (startCid != -1) {
/* 128 */       short[] temp = new short[position];
/* 129 */       System.arraycopy(runWidths, 0, temp, 0, position);
/* 130 */       list.add(new m(startCid, widths.length - 1, temp));
/*     */     } 
/* 132 */     short defaultWidth = 0;
/* 133 */     if (!widthCounts.d()) {
/* 134 */       int maxCount = 0; short s;
/* 135 */       for (s = 0; s < widthCounts.b(); s = (short)(s + 1)) {
/* 136 */         int count = widthCounts.b(s);
/* 137 */         if (count > maxCount) {
/* 138 */           defaultWidth = s;
/* 139 */           maxCount = count;
/*     */         } 
/*     */       } 
/*     */     } 
/* 143 */     List<m> newList = new ArrayList<>();
/* 144 */     for (int i = 0; i < list.size(); i++) {
/* 145 */       m width = list.get(i);
/* 146 */       if (width.c.length != 1 || width.c[0] != defaultWidth)
/*     */       {
/*     */         
/* 149 */         newList.add(width); } 
/*     */     } 
/* 151 */     return new l(defaultWidth, newList.<m>toArray(new m[newList.size()]));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 155 */     StringBuffer buff = new StringBuffer();
/* 156 */     buff.append(this.c).append('\n');
/* 157 */     for (int i = 0; i < this.d.length; i++) {
/* 158 */       buff.append(this.d[i]).append('\n');
/*     */     }
/* 160 */     return buff.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */