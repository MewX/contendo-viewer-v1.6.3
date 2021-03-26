/*     */ package jp.cssj.sakae.pdf.tools;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.io.File;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.d.a;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.e.e;
/*     */ import jp.cssj.sakae.pdf.c.a.b.b;
/*     */ import jp.cssj.sakae.pdf.c.a.b.d;
/*     */ import jp.cssj.sakae.pdf.c.a.c;
/*     */ import jp.cssj.sakae.pdf.c.a.f;
/*     */ import jp.cssj.sakae.pdf.c.a.l;
/*     */ import net.zamasoft.a.b.N;
/*     */ import net.zamasoft.a.b.ah;
/*     */ import net.zamasoft.a.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WArrayTool
/*     */ {
/*     */   public static void main(String[] args) throws Exception {
/*     */     d d;
/*     */     l warray;
/*  31 */     if (args.length < 3) {
/*  32 */       System.out.println("WArrayTool cmap encoding font [ttc-index]");
/*     */       return;
/*     */     } 
/*  35 */     String pCmap = args[0];
/*  36 */     String pEncoding = args[1];
/*  37 */     String pFont = args[2];
/*  38 */     int index = 0;
/*  39 */     if (args.length >= 4) {
/*  40 */       index = Integer.parseInt(args[3]);
/*     */     }
/*  42 */     boolean ttf = (pFont.toLowerCase().endsWith(".ttf") || pFont.toLowerCase().endsWith(".ttc"));
/*     */     
/*  44 */     File cmapFile = new File(pCmap);
/*  45 */     f cmap = new f((b)new a(cmapFile), pEncoding);
/*     */ 
/*     */ 
/*     */     
/*  49 */     if (ttf) {
/*  50 */       File ttFile = new File(pFont);
/*  51 */       b b = new b(ttFile, index, (byte)1);
/*     */     } else {
/*  53 */       Font font = Font.decode(pFont);
/*  54 */       d = new d(font);
/*     */     } 
/*     */     
/*  57 */     b bbox = d.f();
/*  58 */     System.out.println("FontName: " + d.d());
/*  59 */     System.out.println("BBox: " + bbox.a + ' ' + bbox.b + ' ' + bbox.c + ' ' + bbox.d);
/*  60 */     System.out.println("Ascent: " + d.g());
/*  61 */     System.out.println("Descent: " + d.i());
/*  62 */     System.out.println("CapHeight: " + d.h());
/*  63 */     System.out.println("XHeight: " + d.l());
/*     */ 
/*     */     
/*  66 */     if (ttf) {
/*  67 */       warray = a((b)d, cmap);
/*     */     } else {
/*  69 */       warray = a(d, cmap);
/*     */     } 
/*     */     
/*  72 */     System.out.println("WArray:");
/*  73 */     System.out.println((warray.b()).length);
/*  74 */     System.out.println(warray);
/*     */   }
/*     */   
/*     */   private static l a(b fs, f cmap) {
/*  78 */     d ttfFont = fs.o();
/*  79 */     N os2 = (N)ttfFont.a(1330851634);
/*  80 */     ah hmtx = (ah)ttfFont.a(1752003704);
/*  81 */     System.out.println("FamilyClass: " + Integer.toHexString(os2.q()));
/*  82 */     System.out.println("PANOSE-1: " + os2.r());
/*  83 */     short upm = fs.q();
/*     */     
/*  85 */     e cidToAdvance = new e(-32768);
/*  86 */     c ct = cmap.a();
/*  87 */     for (int i = 0; i < ct.c(); i++) {
/*  88 */       if (ct.b(i)) {
/*     */ 
/*     */         
/*  91 */         int gid = fs.r().a(i);
/*  92 */         if (gid != 0) {
/*  93 */           int cid = ct.a(i);
/*  94 */           short advance = (short)(hmtx.a(gid) * 1000 / upm);
/*  95 */           cidToAdvance.a(cid, advance);
/*     */         } 
/*     */       } 
/*  98 */     }  short[] widths = cidToAdvance.a();
/*  99 */     l warray = l.a(widths);
/* 100 */     return warray;
/*     */   }
/*     */   
/*     */   private static l a(d fs, f cmap) {
/* 104 */     e cidToAdvance = new e(-32768);
/* 105 */     c ct = cmap.a();
/* 106 */     for (int i = 0; i < ct.c(); i++) {
/* 107 */       if (ct.b(i)) {
/*     */ 
/*     */         
/* 110 */         int cid = ct.a(i);
/* 111 */         int gid = fs.b(i);
/* 112 */         short advance = fs.c(gid);
/* 113 */         cidToAdvance.a(cid, advance);
/*     */       } 
/* 115 */     }  short[] widths = cidToAdvance.a();
/* 116 */     l warray = l.a(widths);
/* 117 */     return warray;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/tools/WArrayTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */