/*     */ package net.a.a.e.c.b;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Stack;
/*     */ import net.a.a.e.a;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import net.a.a.g.j;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLEncloseElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends a
/*     */   implements MathMLEncloseElement
/*     */ {
/*     */   public static final String r = "menclose";
/*     */   public static final String s = "notation";
/*     */   private static final String t = "longdiv";
/*     */   
/*     */   private static abstract class a
/*     */     extends net.a.a.e.c.a
/*     */   {
/*     */     private static final long r = 1L;
/*     */     
/*     */     public a(String param1String, AbstractDocument param1AbstractDocument) {
/*  94 */       super(param1String, param1AbstractDocument);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void a(g param1g, net.a.a.g.d param1d, f param1f, net.a.a.c param1c) {
/* 103 */       net.a.a.c c1 = b(param1c);
/* 104 */       Dimension2D dimension2D = c(c1);
/* 105 */       float f1 = (float)dimension2D.getWidth();
/* 106 */       if (f1 > 0.0F) {
/* 107 */         param1g.a((i)getFirstChild()).a(f1, 0.0F, param1f);
/*     */       }
/*     */       
/* 110 */       net.a.a.e.d.c.a(param1g, param1d, (Node)this, param1f, dimension2D, 
/* 111 */           d(c1));
/* 112 */       a(param1d, param1f, b(c1));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Dimension2D c(net.a.a.c param1c) {
/* 123 */       return (Dimension2D)new net.a.a.e.d.b(0.0F, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Dimension2D d(net.a.a.c param1c) {
/* 134 */       return (Dimension2D)new net.a.a.e.d.b(0.0F, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract void a(net.a.a.g.d param1d, f param1f, net.a.a.c param1c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class d
/*     */     extends a
/*     */   {
/*     */     private static final long r = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public d(String param1String, AbstractDocument param1AbstractDocument) {
/* 168 */       super(param1String, param1AbstractDocument);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Node newNode() {
/* 173 */       return (Node)new d(this.nodeName, this.ownerDocument);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Dimension2D c(net.a.a.c param1c) {
/* 179 */       float f = net.a.a.e.d.d.b(param1c) * 2.0F;
/* 180 */       return (Dimension2D)new net.a.a.e.d.b(net.a.a.e.d.a.a.a("0.25em", param1c, null) + f, f);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void a(net.a.a.g.d param1d, f param1f, net.a.a.c param1c) {
/* 190 */       List<j> list = param1d.e();
/* 191 */       list.clear();
/* 192 */       float f1 = net.a.a.e.d.d.b(param1c);
/* 193 */       float f2 = param1d.b(param1f) + f1;
/* 194 */       Color color = (Color)param1c.a(net.a.a.c.d.i);
/* 195 */       list.add(new j(f1, -f2, f1, param1d
/* 196 */             .c(param1f), f1, color));
/* 197 */       list.add(new j(f1, -f2, param1d
/* 198 */             .d(param1f), -f2, f1, color));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class e
/*     */     extends a
/*     */   {
/*     */     private static final long r = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public e(String param1String, AbstractDocument param1AbstractDocument) {
/* 220 */       super(param1String, param1AbstractDocument);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Node newNode() {
/* 225 */       return (Node)new e(this.nodeName, this.ownerDocument);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void a(net.a.a.g.d param1d, f param1f, net.a.a.c param1c) {
/* 233 */       Color color = (Color)param1c.a(net.a.a.c.d.i);
/* 234 */       float f1 = net.a.a.e.d.d.b(param1c);
/* 235 */       param1d.a((net.a.a.g.b)new j(0.0F, param1d
/* 236 */             .c(param1f), param1d.d(param1f), 
/* 237 */             -param1d.b(param1f), f1, color));
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
/*     */   private static final class c
/*     */     extends a
/*     */   {
/*     */     private static final long r = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public c(String param1String, AbstractDocument param1AbstractDocument) {
/* 261 */       super(param1String, param1AbstractDocument);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Node newNode() {
/* 266 */       return (Node)new c(this.nodeName, this.ownerDocument);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void a(net.a.a.g.d param1d, f param1f, net.a.a.c param1c) {
/* 274 */       Color color = (Color)param1c.a(net.a.a.c.d.i);
/* 275 */       float f1 = net.a.a.e.d.d.b(param1c);
/* 276 */       param1d.a((net.a.a.g.b)new j(0.0F, 
/* 277 */             -param1d.b(param1f), param1d.d(param1f), param1d
/* 278 */             .c(param1f), f1, color));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class b
/*     */     extends a
/*     */   {
/*     */     private static final long r = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public b(String param1String, AbstractDocument param1AbstractDocument) {
/* 295 */       super(param1String, param1AbstractDocument);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Node newNode() {
/* 300 */       return (Node)new b(this.nodeName, this.ownerDocument);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Dimension2D c(net.a.a.c param1c) {
/* 306 */       float f = net.a.a.e.d.d.b(param1c) * 2.0F;
/* 307 */       return (Dimension2D)new net.a.a.e.d.b(0.0F, f);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Dimension2D d(net.a.a.c param1c) {
/* 313 */       float f = net.a.a.e.d.d.b(param1c) * 2.0F;
/* 314 */       return (Dimension2D)new net.a.a.e.d.b(f, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void a(net.a.a.g.d param1d, f param1f, net.a.a.c param1c) {
/* 322 */       List<j> list = param1d.e();
/* 323 */       list.clear();
/* 324 */       float f1 = net.a.a.e.d.d.b(param1c);
/* 325 */       float f2 = param1d.b(param1f) + f1;
/* 326 */       Color color = (Color)param1c.a(net.a.a.c.d.i);
/* 327 */       list.add(new j(0.0F, -f2, param1d.d(param1f) - f1, -f2, f1, color));
/*     */       
/* 329 */       list.add(new j(param1d.d(param1f) - f1, -f2, param1d
/* 330 */             .d(param1f) - f1, param1d
/* 331 */             .c(param1f), f1, color));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   private static final Log u = LogFactory.getLog(b.class);
/*     */   
/* 341 */   private static final Map<String, Constructor<?>> v = new HashMap<String, Constructor<?>>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long w = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(String paramString, AbstractDocument paramAbstractDocument) {
/* 354 */     super(paramString, paramAbstractDocument);
/*     */     
/* 356 */     a("notation", "longdiv");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 362 */     return (Node)new b(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNotation() {
/* 369 */     return a("notation");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNotation(String paramString) {
/* 379 */     setAttribute("notation", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<i> a() {
/* 385 */     Stack<Constructor<?>> stack = j();
/* 386 */     net.a.a.e.d d = i();
/* 387 */     d = a(stack, d);
/* 388 */     return (List)Collections.singletonList(d);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private net.a.a.e.d a(Stack<Constructor<?>> paramStack, net.a.a.e.d paramd) {
/* 394 */     net.a.a.e.d d1 = paramd;
/* 395 */     while (!paramStack.isEmpty()) {
/* 396 */       Constructor<net.a.a.e.d> constructor = (Constructor)paramStack.pop();
/*     */       
/*     */       try {
/* 399 */         net.a.a.e.d d2 = constructor.newInstance(new Object[] { "saklsdiwet:menclosechild", this.ownerDocument });
/*     */         
/* 401 */         d2.appendChild((Node)d1);
/* 402 */         d1 = d2;
/* 403 */       } catch (InstantiationException instantiationException) {
/* 404 */         u.warn(instantiationException);
/* 405 */       } catch (IllegalAccessException illegalAccessException) {
/* 406 */         u.warn(illegalAccessException);
/* 407 */       } catch (InvocationTargetException invocationTargetException) {
/* 408 */         u.warn(invocationTargetException);
/*     */       } 
/*     */     } 
/* 411 */     return d1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private net.a.a.e.d i() {
/*     */     net.a.a.e.d d;
/* 422 */     if (e() == 1) {
/* 423 */       d = a(0);
/*     */     } else {
/*     */       
/* 426 */       d = (net.a.a.e.d)this.ownerDocument.createElement("mrow");
/*     */       
/* 428 */       for (Node node : net.a.a.e.d.c.a((Node)this)) {
/* 429 */         d.appendChild(node);
/*     */       }
/*     */     } 
/* 432 */     return d;
/*     */   }
/*     */   
/*     */   private Stack<Constructor<?>> j() {
/* 436 */     String[] arrayOfString = getNotation().split(" ");
/* 437 */     Stack<Constructor> stack = new Stack();
/* 438 */     for (String str : arrayOfString) {
/* 439 */       Constructor constructor = v.get(str
/* 440 */           .toLowerCase(Locale.ENGLISH));
/* 441 */       if (constructor == null) {
/* 442 */         if (str.length() > 0) {
/* 443 */           u.info("Unsupported notation for menclose: " + str);
/*     */         }
/*     */       } else {
/*     */         
/* 447 */         stack.push(constructor);
/*     */       } 
/*     */     } 
/* 450 */     return (Stack)stack;
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/* 455 */       v.put("radical", j.class.getConstructor(new Class[] { String.class, AbstractDocument.class }));
/*     */       
/* 457 */       v.put("longdiv", d.class
/* 458 */           .getConstructor(new Class[] { String.class, AbstractDocument.class }));
/* 459 */       v.put("updiagonalstrike", e.class
/* 460 */           .getConstructor(new Class[] { String.class, AbstractDocument.class }));
/*     */       
/* 462 */       v.put("downdiagonalstrike", c.class
/* 463 */           .getConstructor(new Class[] { String.class, AbstractDocument.class }));
/*     */       
/* 465 */       v.put("actuarial", b.class
/* 466 */           .getConstructor(new Class[] { String.class, AbstractDocument.class }));
/* 467 */     } catch (NoSuchMethodException noSuchMethodException) {
/* 468 */       u.fatal(noSuchMethodException);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */