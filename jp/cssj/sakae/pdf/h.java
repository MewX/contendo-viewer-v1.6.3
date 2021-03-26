/*     */ package jp.cssj.sakae.pdf;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import jp.cssj.sakae.pdf.g.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class h
/*     */   extends FilterOutputStream
/*     */ {
/*     */   private boolean a = true;
/*     */   
/*     */   static {
/*  27 */     d = new byte[] { 13, 10 };
/*     */     
/*  29 */     e = new byte[] { 110, 117, 108, 108 };
/*     */     
/*  31 */     f = new byte[] { 116, 114, 117, 101 };
/*     */     
/*  33 */     g = new byte[] { 102, 97, 108, 115, 101 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     i = new DecimalFormat("#.#####");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     j = new byte[] { 92, 110 };
/*     */     
/* 245 */     k = new byte[] { 92, 114 };
/*     */     
/* 247 */     l = new byte[] { 92, 116 };
/*     */     
/* 249 */     m = new byte[] { 92, 98 };
/*     */     
/* 251 */     n = new byte[] { 92, 102 };
/*     */     
/* 253 */     o = new byte[] { 92, 92 };
/*     */     
/* 255 */     p = new byte[] { 92, 40 };
/*     */     
/* 257 */     q = new byte[] { 92, 41 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     r = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
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
/*     */   private ByteBuffer c = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte[] d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte[] e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte[] f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte[] g;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static NumberFormat i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte[] j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte[] k;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte[] l;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte[] m;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte[] n;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte[] o;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte[] p;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte[] q;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final char[] r;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DateFormat s;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h(OutputStream out, String nameEncoding) throws IOException {
/*     */     super(out);
/* 491 */     this.s = new SimpleDateFormat("yyyyMMddHHmmss"); this.b = nameEncoding;
/*     */   } public static class a {
/*     */     public final b a; public final double b; public final double c; public final double d; public a(b pageRef, double x, double y, double zoom) { this.a = pageRef; this.b = x; this.c = y; this.d = zoom; } } public void b(b ref) throws IOException { a(ref.a); a(ref.b); b("R"); } public void a(a dest) throws IOException { i(); b(dest.a); a("XYZ"); a(dest.b); a(dest.c); a(dest.d); j(); } public void a(String name) throws IOException { l(); write(47); byte[] b = a.a(name, this.b); if (b.length <= 0 || b.length > 127) throw new IllegalArgumentException("名前は1から127バイトまでです。");  write(b); } public void b(String name) throws IOException { l(); g(name); } public void f() throws IOException { l(); write(e); } public void a(boolean b) throws IOException { l(); write(b ? f : g); }
/*     */   public void a(int number) throws IOException { l(); g(String.valueOf(number)); }
/*     */   public void a(double number) throws IOException { l(); g(b(number)); }
/*     */   private String b(double number) { String s; if (!h && Double.isInfinite(number)) throw new AssertionError("Infinite number");  if (!h && Double.isNaN(number)) throw new AssertionError("Undefined number");  if (Math.abs(number) > 32767.0D) { if (number >= 0.0D) { number = 32767.0D; } else { number = -32767.0D; }  } else if (Math.abs(number) < 1.52587890625E-5D) { number = 0.0D; }  double round = (int)number; if (number == round) { s = String.valueOf((int)number); } else { s = i.format(number); }  return s; }
/*     */   public boolean b(double a, double b) { return b(a).equals(b(b)); }
/*     */   public void g() throws IOException { f("<<"); }
/*     */   public void h() throws IOException { f(">>"); }
/*     */   public void i() throws IOException { l(); write(91); }
/* 501 */   public void a(long time, TimeZone zone) throws IOException { Date date = new Date(time);
/* 502 */     StringBuffer buff = new StringBuffer();
/* 503 */     buff.append("D:");
/* 504 */     buff.append(this.s.format(date));
/* 505 */     buff.append((zone.getRawOffset() < 0) ? 45 : 43);
/* 506 */     long absOff = Math.abs(zone.getRawOffset());
/* 507 */     String str1 = String.valueOf(absOff / 3600000L);
/* 508 */     if (str1.length() <= 1) {
/* 509 */       buff.append('0');
/*     */     }
/* 511 */     buff.append(str1);
/* 512 */     buff.append('\'');
/* 513 */     String m = String.valueOf(absOff % 3600000L / 60000L);
/* 514 */     if (m.length() <= 1) {
/* 515 */       buff.append('0');
/*     */     }
/* 517 */     buff.append(m);
/* 518 */     buff.append('\'');
/* 519 */     c(buff.toString()); }
/*     */   public void j() throws IOException { l(); write(93); }
/*     */   public void c(String str) throws IOException { l(); write(40); int len = 0; for (int i = 0; i < str.length(); i++) { char c = str.charAt(i); switch (c) { case '\n': len += j.length; write(j); break;case '\r': len += k.length; write(k); break;case '\t': len += l.length; write(l); break;case '\b': len += m.length; write(m); break;case '\f': len += n.length; write(n); break;case '\\': len += o.length; write(o); break;case '(': len += p.length; write(p); break;case ')': len += q.length; write(q); break;default: len++; write(c); break; }  }  write(41); if (len > 65535)
/*     */       throw new IllegalArgumentException("文字列が65535バイトを超えています。");  }
/*     */   private void c(int size) { if (this.c == null || this.c.capacity() < size)
/*     */       this.c = ByteBuffer.allocate(size);  }
/*     */   private void d() throws IOException { this.c.flip(); write(this.c.array(), this.c.arrayOffset(), this.c.limit()); this.c.clear(); }
/*     */   private void a(byte b) { this.c.put(b); }
/*     */   private void b(byte b) { this.c.put((byte)r[b >> 4 & 0xF]); this.c.put((byte)r[b & 0xF]); }
/* 528 */   private void d(int c) { b((byte)(c >> 8 & 0xFF)); b((byte)(c & 0xFF)); } public void k() throws IOException { write(d);
/* 529 */     this.a = true; }
/*     */   private void c(byte b) throws IOException { write(r[b >> 4 & 0xF]); write(r[b & 0xF]); }
/*     */   public void d(String text) throws IOException { l(); c(6 + 4 * text.length()); a((byte)60); b((byte)-2); b((byte)-1); for (int i = 0; i < text.length(); i++) { char c = text.charAt(i); d(c); }  a((byte)62); d(); }
/*     */   public void e(String text) throws IOException { for (int i = 0; i < text.length(); i++) { char c = text.charAt(i); if (c > '') { d(text); return; }  }  c(text); }
/* 533 */   public void a(String[] elements, String encoding) throws IOException { for (int j = 0; j < elements.length; j++) { String text = elements[j]; for (int k = 0; k < text.length(); k++) { char c = text.charAt(k); if (c > '' || c == '/') { l(); write(60); for (int m = 0; m < elements.length; m++) { byte[] name = elements[m].getBytes(encoding); for (int l = 0; l < name.length; l++) { byte d = name[l]; if (d == 47 || d == 92) c((byte)92);  c(d); }  if (m != elements.length - 1) c((byte)47);  }  write(62); return; }  }  }  StringBuffer buff = new StringBuffer(); for (int i = 0; i < elements.length; i++) buff.append(elements[i]).append('/');  buff.deleteCharAt(buff.length() - 1); c(buff.toString()); } public void a(byte[] a, int off, int len) throws IOException { if (len > 65535) throw new IllegalArgumentException("文字列が65535バイトを超えています。");  l(); c(2 + 2 * len); a((byte)60); for (int i = 0; i < len; i++) b(a[i + off]);  a((byte)62); d(); } public void a(int[] a, int off, int len) throws IOException { if (len * 2 > 65535) throw new IllegalArgumentException("文字列が65535バイトを超えています。");  l(); c(2 + 4 * len); a((byte)60); for (int i = 0; i < len; i++) d(a[i + off]);  a((byte)62); d(); } public void b(int a) throws IOException { l(); c(6); a((byte)60); d(a); a((byte)62); d(); } public void l() throws IOException { if (this.a) {
/* 534 */       this.a = false;
/*     */     } else {
/* 536 */       write(32);
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void m() throws IOException {
/* 541 */     if (!this.a) {
/* 542 */       k();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void f(String line) throws IOException {
/* 553 */     m();
/* 554 */     g(line);
/* 555 */     k();
/*     */   }
/*     */   
/*     */   public void g(String str) throws IOException {
/* 559 */     c(str.length());
/* 560 */     for (int i = 0; i < str.length(); i++) {
/* 561 */       a((byte)str.charAt(i));
/*     */     }
/* 563 */     d();
/*     */   }
/*     */   
/*     */   public void write(byte[] buff, int off, int len) throws IOException {
/* 567 */     this.out.write(buff, off, len);
/*     */   }
/*     */   
/*     */   public void write(byte[] buff) throws IOException {
/* 571 */     this.out.write(buff);
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/* 575 */     this.out.write(b);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 579 */     this.out.close();
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/* 583 */     this.out.flush();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */