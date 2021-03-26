/*     */ package net.a.a.b;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.c;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.f;
/*     */ import net.a.a.g.c;
/*     */ import org.w3c.dom.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends Component
/*     */ {
/*     */   private static final long a = 1L;
/*     */   private transient c b;
/*     */   private Document c;
/*  59 */   private f d = (f)new c(
/*  60 */       c.a());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(f paramf) {
/*  76 */     this.d = paramf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document a() {
/*  83 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize() {
/*  93 */     if (this.b == null) {
/*  94 */       return new Dimension(1, 1);
/*     */     }
/*  96 */     return new Dimension((int)Math.ceil(this.b.a()), 
/*  97 */         (int)Math.ceil((this.b.b() + 
/*  98 */           (int)Math.ceil(this.b.c()))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 109 */     return getMinimumSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics paramGraphics) {
/* 120 */     super.paint(paramGraphics);
/* 121 */     if (this.b != null) {
/* 122 */       this.b.a((Graphics2D)paramGraphics, 0.0F, (int)Math.ceil(this.b
/* 123 */             .b()));
/*     */     }
/*     */   }
/*     */   
/*     */   private void b() {
/* 128 */     Graphics2D graphics2D = (Graphics2D)getGraphics();
/* 129 */     if (this.c == null || graphics2D == null) {
/* 130 */       this.b = null;
/*     */     } else {
/* 132 */       this.b = new c(this.c, (c)this.d, graphics2D);
/*     */     } 
/* 134 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(boolean paramBoolean) {
/* 144 */     this.d.a(d.g, Boolean.valueOf(paramBoolean));
/* 145 */     b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Document paramDocument) {
/* 153 */     this.c = paramDocument;
/* 154 */     b();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/b/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */