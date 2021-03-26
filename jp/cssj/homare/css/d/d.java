/*    */ package jp.cssj.homare.css.d;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.b.f.b;
/*    */ import jp.cssj.homare.css.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class d
/*    */ {
/*    */   protected static final byte d = 1;
/*    */   protected static final byte e = 2;
/*    */   protected static final byte f = 3;
/* 20 */   protected final List<Object> g = new ArrayList();
/* 21 */   protected final b h = new b();
/* 22 */   protected int i = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a() {
/* 29 */     return this.i;
/*    */   }
/*    */   
/*    */   public void a(c style) {
/* 33 */     this.h.a((byte)1);
/* 34 */     this.g.add(style);
/* 35 */     this.i++;
/*    */   }
/*    */   
/*    */   public void a(int offset, char[] ch, int off, int len) {
/* 39 */     this.h.a((byte)2);
/* 40 */     char[] chars = new char[len];
/* 41 */     System.arraycopy(ch, off, chars, 0, len);
/* 42 */     this.g.add(Integer.valueOf(offset));
/* 43 */     this.g.add(chars);
/*    */   }
/*    */   
/*    */   public void b(c style) {
/* 47 */     this.h.a((byte)3);
/* 48 */     this.g.add(style);
/* 49 */     this.i--;
/*    */   }
/*    */   
/*    */   public void a(e builder) {
/* 53 */     int j = 0;
/* 54 */     for (int i = 0; i < this.h.b(); i++) {
/* 55 */       c style; int charOffset; char[] chars; switch (this.h.a(i)) {
/*    */         case 1:
/* 57 */           style = (c)this.g.get(j++);
/*    */           
/*    */           while (true) {
/* 60 */             c parentStyle = style.c();
/* 61 */             if (parentStyle != null && parentStyle.g()) {
/* 62 */               style.f();
/*    */               continue;
/*    */             } 
/*    */             break;
/*    */           } 
/* 67 */           builder.a(style);
/*    */           break;
/*    */         
/*    */         case 2:
/* 71 */           charOffset = ((Integer)this.g.get(j++)).intValue();
/* 72 */           chars = (char[])this.g.get(j++);
/* 73 */           builder.a(charOffset, chars, 0, chars.length);
/*    */           break;
/*    */         
/*    */         case 3:
/* 77 */           j++;
/* 78 */           builder.d();
/*    */           break;
/*    */         
/*    */         default:
/* 82 */           throw new IllegalStateException();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/d/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */