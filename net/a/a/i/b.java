/*     */ package net.a.a.i;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.SwingConstants;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import net.a.a.c.c;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.d;
/*     */ import net.a.a.e;
/*     */ import net.a.a.e.b.a;
/*     */ import net.a.a.f;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class b
/*     */   extends JComponent
/*     */   implements SwingConstants
/*     */ {
/*     */   private static final String a = ",";
/*  71 */   private static final Log b = LogFactory.getLog(b.class);
/*     */ 
/*     */   
/*     */   private static final long c = 1L;
/*     */   
/*     */   private Node d;
/*     */   
/*  78 */   private int e = 0;
/*     */   
/*  80 */   private final f f = (f)new c(
/*  81 */       c.a());
/*     */   
/*  83 */   private int g = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final a h;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b() {
/*  94 */     this((a)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(a parama) {
/* 104 */     this.h = parama;
/*     */     
/* 106 */     d d = new d(this);
/*     */     
/* 108 */     addMouseListener(d);
/*     */     
/* 110 */     updateUI();
/* 111 */     o();
/* 112 */     a((Node)new a());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a a() {
/* 121 */     return this.h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void o() {
/* 128 */     String str = j().split(",")[0];
/*     */     
/* 130 */     float f1 = f();
/* 131 */     super.setFont(new Font(str, 0, (int)f1));
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
/*     */   public String b() {
/* 144 */     return e.a(c(), false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node c() {
/* 152 */     return this.d;
/*     */   }
/*     */   
/*     */   private static String a(List<String> paramList) {
/* 156 */     boolean bool = true;
/* 157 */     StringBuilder stringBuilder = new StringBuilder();
/* 158 */     for (String str : paramList) {
/* 159 */       if (bool) {
/* 160 */         bool = false;
/*     */       } else {
/* 162 */         stringBuilder.append(",");
/*     */       } 
/* 164 */       stringBuilder.append(str);
/*     */     } 
/* 166 */     return stringBuilder.toString();
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
/*     */   public String d() {
/* 179 */     return a((List<String>)this.f
/* 180 */         .a(d.p));
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
/*     */   public String e() {
/* 192 */     return a((List<String>)this.f
/* 193 */         .a(d.o));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float f() {
/* 200 */     return ((Float)this.f.a(d.b)).floatValue();
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
/*     */   public String g() {
/* 212 */     return a((List<String>)this.f
/* 213 */         .a(d.m));
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
/*     */   public String h() {
/* 225 */     return a((List<String>)this.f
/* 226 */         .a(d.k));
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
/*     */   public String i() {
/* 238 */     return a((List<String>)this.f
/* 239 */         .a(d.n));
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
/*     */   public String j() {
/* 251 */     return a((List<String>)this.f
/* 252 */         .a(d.l));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getForeground() {
/* 258 */     return (Color)this.f.a(d.i);
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
/*     */   public int k() {
/* 273 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public e l() {
/* 280 */     return (e)this.ui;
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
/*     */   public int m() {
/* 294 */     return this.g;
/*     */   }
/*     */   
/*     */   private void p() {
/* 298 */     repaint();
/* 299 */     revalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackground(Color paramColor) {
/* 305 */     super.setBackground(paramColor);
/* 306 */     p();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String paramString) {
/*     */     try {
/* 317 */       Document document = d.a(paramString);
/* 318 */       a a1 = net.a.a.b.a().a(document, true, true);
/*     */       
/* 320 */       a((Node)a1);
/* 321 */     } catch (SAXException sAXException) {
/* 322 */       throw new IllegalArgumentException(sAXException);
/* 323 */     } catch (ParserConfigurationException parserConfigurationException) {
/* 324 */       throw new IllegalArgumentException(parserConfigurationException);
/* 325 */     } catch (IOException iOException) {
/* 326 */       throw new IllegalArgumentException(iOException);
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
/*     */   public void a(boolean paramBoolean) {
/* 338 */     a(d.g, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Node paramNode) {
/* 346 */     Node node = this.d;
/* 347 */     firePropertyChange("document", node, paramNode);
/* 348 */     this.d = paramNode;
/* 349 */     if (paramNode != node) {
/* 350 */       revalidate();
/* 351 */       repaint();
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
/*     */   
/*     */   @Deprecated
/*     */   public void setFont(Font paramFont) {
/* 372 */     super.setFont(paramFont);
/* 373 */     a(paramFont.getSize2D());
/* 374 */     g(paramFont.getFamily() + "," + 
/* 375 */         j());
/*     */   }
/*     */   
/*     */   private List<String> h(String paramString) {
/* 379 */     return Arrays.asList(paramString.split(","));
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
/*     */   public void b(String paramString) {
/* 392 */     a(d.p, 
/* 393 */         h(paramString));
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
/*     */   public void c(String paramString) {
/* 405 */     a(d.o, h(paramString));
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
/*     */   public void a(d paramd, Object paramObject) {
/* 417 */     a(Collections.singletonMap(paramd, paramObject));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Map<d, Object> paramMap) {
/* 427 */     for (Map.Entry<d, Object> entry : paramMap.entrySet()) {
/* 428 */       d d = (d)entry.getKey();
/* 429 */       Object object = this.f.a(d);
/* 430 */       this.f.a(d, entry.getValue());
/* 431 */       firePropertyChange(d.name(), object, this.f
/* 432 */           .a(d));
/*     */     } 
/* 434 */     revalidate();
/* 435 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float paramFloat) {
/* 445 */     a(d.b, Float.valueOf(paramFloat));
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
/*     */   public void d(String paramString) {
/* 457 */     a(d.m, 
/* 458 */         h(paramString));
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
/*     */   public void e(String paramString) {
/* 470 */     a(d.k, 
/* 471 */         h(paramString));
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
/*     */   public void f(String paramString) {
/* 483 */     a(d.n, h(paramString));
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
/*     */   public void g(String paramString) {
/* 495 */     a(d.l, h(paramString));
/* 496 */     o();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForeground(Color paramColor) {
/* 502 */     super.setForeground(paramColor);
/* 503 */     a(d.i, paramColor);
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
/*     */   public void a(int paramInt) {
/* 519 */     this.e = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpaque(boolean paramBoolean) {
/* 525 */     super.setOpaque(paramBoolean);
/* 526 */     p();
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
/*     */   public void b(int paramInt) {
/* 541 */     this.g = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateUI() {
/* 547 */     if (UIManager.get(getUIClassID()) == null) {
/* 548 */       setUI(new f());
/*     */     } else {
/* 550 */       setUI(UIManager.getUI(this));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public f n() {
/* 558 */     return this.f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(int paramInt1, int paramInt2) {
/* 565 */     super.setSize(paramInt1, paramInt2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/i/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */