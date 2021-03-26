/*     */ package jp.cssj.homare.b.a.c;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import jp.cssj.homare.b.f.a;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.c.b;
/*     */ import jp.cssj.sakae.c.c.f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */ {
/*  23 */   public static final g a = new g(null, null);
/*     */ 
/*     */ 
/*     */   
/*     */   private final b b;
/*     */ 
/*     */ 
/*     */   
/*     */   private final h c;
/*     */ 
/*     */ 
/*     */   
/*     */   public static g a(b backgroundColor, h backgroundImage) {
/*  36 */     if (backgroundColor == null && backgroundImage == null) {
/*  37 */       return a;
/*     */     }
/*  39 */     return new g(backgroundColor, backgroundImage);
/*     */   }
/*     */   
/*     */   private g(b backgroundColor, h backgroundImage) {
/*  43 */     this.b = backgroundColor;
/*  44 */     this.c = backgroundImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a() {
/*  53 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h b() {
/*  62 */     return this.c;
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
/*     */   public void a(b gc, double x, double y, double width, double height, double pbLeft, double pbTop, double pbRight, double pbBottom, A border) throws c {
/*     */     Shape shape;
/*  82 */     if (border == null) {
/*  83 */       shape = new Rectangle2D.Double(x, y, width, height);
/*     */     } else {
/*  85 */       shape = a.a.a(border, x, y, width, height);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (this.b != null) {
/*     */       
/*  97 */       gc.d();
/*  98 */       gc.b(this.b);
/*  99 */       gc.b(shape);
/* 100 */       gc.e();
/*     */     } 
/* 102 */     if (this.c != null) {
/*     */       
/* 104 */       double imageWidth, imageHeight, paddingWidth = width - pbLeft - pbRight;
/* 105 */       double paddingHeight = height - pbTop - pbBottom;
/*     */ 
/*     */ 
/*     */       
/* 109 */       m size = this.c.k;
/* 110 */       switch (size.a()) {
/*     */         case 1:
/* 112 */           imageWidth = size.c();
/*     */           break;
/*     */         case 2:
/* 115 */           imageWidth = size.c() * paddingWidth;
/*     */           break;
/*     */         
/*     */         default:
/* 119 */           throw new IllegalStateException();
/*     */       } 
/* 121 */       switch (size.b()) {
/*     */         case 1:
/* 123 */           imageHeight = size.d();
/*     */           break;
/*     */         case 2:
/* 126 */           imageHeight = size.d() * paddingHeight;
/*     */           break;
/*     */         
/*     */         default:
/* 130 */           throw new IllegalStateException();
/*     */       } 
/*     */       
/* 133 */       if (imageWidth > 0.0D && imageHeight > 0.0D) {
/* 134 */         double tx, ty; AffineTransform at; f pattern; Rectangle2D rect; double offX = pbLeft;
/* 135 */         double offY = pbTop;
/* 136 */         if (this.c.i == 1) {
/*     */           
/* 138 */           offX -= x;
/* 139 */           offY -= y;
/*     */         } 
/*     */ 
/*     */         
/* 143 */         w pos = this.c.j;
/* 144 */         switch (pos.a()) {
/*     */           case 1:
/* 146 */             offX += pos.c();
/*     */             break;
/*     */           case 2:
/* 149 */             offX += pos.c() * (paddingWidth - imageWidth);
/*     */             break;
/*     */           
/*     */           default:
/* 153 */             throw new IllegalStateException();
/*     */         } 
/* 155 */         switch (pos.b()) {
/*     */           case 1:
/* 157 */             offY += pos.d();
/*     */             break;
/*     */           case 2:
/* 160 */             offY += pos.d() * (paddingHeight - imageHeight);
/*     */             break;
/*     */           
/*     */           default:
/* 164 */             throw new IllegalStateException();
/*     */         } 
/*     */         
/* 167 */         double sx = imageWidth / this.c.g.a();
/* 168 */         double sy = imageHeight / this.c.g.b();
/*     */ 
/*     */         
/* 171 */         gc.d();
/* 172 */         gc.a(shape);
/*     */         
/* 174 */         switch (this.c.h) {
/*     */           
/*     */           case 0:
/* 177 */             tx = x + offX;
/* 178 */             ty = y + offY;
/* 179 */             at = new AffineTransform(sx, 0.0D, 0.0D, sy, tx, ty);
/* 180 */             gc.d();
/* 181 */             gc.a(at);
/* 182 */             gc.a(this.c.g);
/* 183 */             gc.e();
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 1:
/* 189 */             gc.d();
/* 190 */             tx = (x + offX) % imageWidth;
/* 191 */             ty = y + offY;
/* 192 */             at = AffineTransform.getTranslateInstance(tx, ty);
/* 193 */             at.scale(sx, sy);
/*     */             
/* 195 */             pattern = new f(this.c.g, at);
/* 196 */             gc.b(pattern);
/* 197 */             rect = new Rectangle2D.Double(x, ty, width, imageHeight);
/* 198 */             gc.b(rect);
/* 199 */             gc.e();
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 205 */             gc.d();
/* 206 */             tx = x + offX;
/* 207 */             ty = (y + offY) % imageHeight;
/* 208 */             at = AffineTransform.getTranslateInstance(tx, ty);
/* 209 */             at.scale(sx, sy);
/*     */             
/* 211 */             pattern = new f(this.c.g, at);
/* 212 */             gc.b(pattern);
/* 213 */             rect = new Rectangle2D.Double(tx, y, imageWidth, height);
/* 214 */             gc.b(rect);
/* 215 */             gc.e();
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 3:
/* 221 */             gc.d();
/* 222 */             tx = (x + offX) % imageWidth;
/* 223 */             ty = (y + offY) % imageHeight;
/* 224 */             at = AffineTransform.getTranslateInstance(tx, ty);
/* 225 */             at.scale(sx, sy);
/*     */             
/* 227 */             pattern = new f(this.c.g, at);
/* 228 */             gc.b(pattern);
/* 229 */             rect = new Rectangle2D.Double(x, y, width, height);
/* 230 */             gc.b(rect);
/* 231 */             gc.e();
/*     */             break;
/*     */ 
/*     */           
/*     */           default:
/* 236 */             throw new IllegalStateException();
/*     */         } 
/* 238 */         gc.e();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean c() {
/* 249 */     return (a() != null || b() != null);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 253 */     return super.toString() + "[color=" + a() + ",image=" + b() + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */