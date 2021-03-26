/*     */ package net.a.a.e.b;
/*     */ 
/*     */ import net.a.a.c;
/*     */ import net.a.a.e.c.a;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLMathElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class d
/*     */   extends a
/*     */   implements MathMLMathElement
/*     */ {
/*     */   public static final String r = "display";
/*     */   public static final String s = "macros";
/*     */   public static final String t = "math";
/*     */   private static final long u = 1L;
/*     */   private static final String v = "mode";
/*     */   private static final String w = "inline";
/*     */   private static final String x = "block";
/*     */   private static final String y = "display";
/*     */   
/*     */   private final class a
/*     */     implements c
/*     */   {
/*     */     private final c b;
/*     */     
/*     */     private a(d this$0, c param1c) {
/*  43 */       this.b = param1c;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object a(net.a.a.c.d param1d) {
/*  48 */       if (net.a.a.c.d.a.equals(param1d)) {
/*  49 */         if ("block".equals(this.a.getDisplay())) {
/*  50 */           object = net.a.a.c.a.a;
/*     */         } else {
/*  52 */           object = net.a.a.c.a.b;
/*     */         } 
/*     */       } else {
/*     */         
/*  56 */         object = this.a.b(this.b).a(param1d);
/*     */       } 
/*  58 */       Object object = a(param1d, object);
/*  59 */       return object;
/*     */     }
/*     */ 
/*     */     
/*     */     private Object a(net.a.a.c.d param1d, Object param1Object) {
/*  64 */       Object object = param1Object;
/*  65 */       String str = this.a.getAttributeNS("http://jeuclid.sf.net/ns/ext", param1d
/*  66 */           .c());
/*  67 */       if (str != null && str.length() > 0) {
/*  68 */         object = param1d.b(str);
/*     */       } else {
/*  70 */         object = b(param1d, object);
/*     */       } 
/*     */       
/*  73 */       return object;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Object b(net.a.a.c.d param1d, Object param1Object) {
/*  79 */       Object object = param1Object;
/*  80 */       String str = this.a.getAttributeNS("http://jeuclid.sf.net/ns/context", param1d
/*  81 */           .c());
/*  82 */       if (str != null && str.length() > 0) {
/*  83 */         object = param1d.b(str);
/*     */       } else {
/*     */         
/*  86 */         String str1 = this.a.getAttributeNS("http://jeuclid.sf.net/ns/context", param1d
/*  87 */             .toString());
/*  88 */         if (str1 != null && str1.length() > 0) {
/*  89 */           object = param1d.b(str1);
/*     */         }
/*     */       } 
/*  92 */       return object;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d(String paramString, AbstractDocument paramAbstractDocument) {
/* 128 */     super(paramString, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 134 */     return (Node)new d(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisplay(String paramString) {
/* 144 */     setAttribute("display", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplay() {
/* 154 */     String str1, str2 = a("display");
/* 155 */     if (str2 == null) {
/* 156 */       if ("display".equalsIgnoreCase(
/* 157 */           a("mode"))) {
/* 158 */         str1 = "block";
/*     */       } else {
/* 160 */         str1 = "inline";
/*     */       }
/*     */     
/* 163 */     } else if ("block".equalsIgnoreCase(str2)) {
/* 164 */       str1 = "block";
/*     */     } else {
/* 166 */       str1 = "inline";
/*     */     } 
/*     */     
/* 169 */     return str1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c a(int paramInt, c paramc) {
/* 176 */     return new a(paramc);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMacros() {
/* 181 */     return a("macros");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMacros(String paramString) {
/* 186 */     setAttribute("macros", paramString);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/b/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */