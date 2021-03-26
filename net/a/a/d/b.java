/*     */ package net.a.a.d;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import net.a.a.e.b.c;
/*     */ import net.a.a.e.d.a;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   implements d
/*     */ {
/*  40 */   private static final Log a = LogFactory.getLog(b.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static DOMImplementation a() {
/*     */     DOMImplementation dOMImplementation;
/*     */     try {
/*  53 */       Class clazz = a.a().a("org.apache.batik.dom.svg.SVGDOMImplementation");
/*     */       
/*  55 */       Method method = clazz.getMethod("getDOMImplementation", new Class[0]);
/*     */       
/*  57 */       dOMImplementation = (DOMImplementation)method.invoke(null, (Object[])null);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  62 */     catch (RuntimeException runtimeException) {
/*     */       
/*  64 */       dOMImplementation = null;
/*  65 */     } catch (LinkageError linkageError) {
/*  66 */       dOMImplementation = null;
/*  67 */     } catch (ClassNotFoundException classNotFoundException) {
/*  68 */       dOMImplementation = null;
/*  69 */     } catch (NoSuchMethodException noSuchMethodException) {
/*  70 */       dOMImplementation = null;
/*  71 */     } catch (IllegalAccessException illegalAccessException) {
/*  72 */       dOMImplementation = null;
/*  73 */     } catch (InvocationTargetException invocationTargetException) {
/*  74 */       dOMImplementation = null;
/*     */     } 
/*  76 */     if (dOMImplementation == null) {
/*  77 */       dOMImplementation = c.a();
/*     */     }
/*  79 */     return dOMImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(f paramf) {
/*     */     try {
/*  90 */       a.a().a("org.apache.batik.svggen.SVGGraphics2D");
/*     */       
/*  92 */       a.debug("Batik detected!");
/*  93 */       paramf
/*  94 */         .a("image/svg+xml", "svg", true);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  99 */       DOMImplementation dOMImplementation = a();
/* 100 */       if (dOMImplementation != null) {
/* 101 */         paramf.a("image/svg+xml", new a(dOMImplementation), true);
/*     */       
/*     */       }
/*     */     }
/* 105 */     catch (ClassNotFoundException classNotFoundException) {
/* 106 */       a.debug("Batik is not in classpath!");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */