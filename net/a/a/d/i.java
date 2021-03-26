/*     */ package net.a.a.d;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.a.a.e.d.a;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.freehep.graphicsbase.util.export.ExportFileType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class i
/*     */ {
/*  42 */   private static final Log a = LogFactory.getLog(i.class);
/*     */   
/*  44 */   private static final Map<String, String> b = new HashMap<String, String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(f paramf) {
/*  59 */     for (Map.Entry<String, String> entry : b
/*  60 */       .entrySet()) {
/*     */ 
/*     */       
/*     */       try {
/*  64 */         Class<ExportFileType> clazz = a.a().a((String)entry.getKey());
/*     */         
/*  66 */         ExportFileType exportFileType = clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
/*     */ 
/*     */         
/*  69 */         Class<?> clazz1 = a.a().a((String)entry.getValue());
/*  70 */         a(paramf, exportFileType, clazz1);
/*     */       }
/*  72 */       catch (NoSuchMethodException noSuchMethodException) {
/*  73 */         a.debug(noSuchMethodException);
/*  74 */       } catch (ClassNotFoundException classNotFoundException) {
/*  75 */         a.debug(classNotFoundException);
/*  76 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  77 */         a.debug(illegalArgumentException);
/*  78 */       } catch (SecurityException securityException) {
/*  79 */         a.debug(securityException);
/*  80 */       } catch (InstantiationException instantiationException) {
/*  81 */         a.debug(instantiationException);
/*  82 */       } catch (IllegalAccessException illegalAccessException) {
/*  83 */         a.debug(illegalAccessException);
/*  84 */       } catch (InvocationTargetException invocationTargetException) {
/*  85 */         a.debug(invocationTargetException);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(f paramf, ExportFileType paramExportFileType, Class<?> paramClass) throws NoSuchMethodException {
/*  93 */     g g = new g(paramClass);
/*     */     
/*  95 */     for (String str : paramExportFileType.getMIMETypes()) {
/*  96 */       for (String str1 : paramExportFileType.getExtensions()) {
/*  97 */         paramf.a(str, str1, false);
/*     */       }
/*  99 */       paramf.a(str, g, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   static {
/* 104 */     b.put("org.freehep.graphicsio.emf.EMFExportFileType", "org.freehep.graphicsio.emf.EMFGraphics2D");
/*     */ 
/*     */     
/* 107 */     b.put("org.freehep.graphicsio.gif.GIFExportFileType", "org.freehep.graphicsio.gif.GIFGraphics2D");
/*     */ 
/*     */     
/* 110 */     b.put("org.freehep.graphicsio.pdf.PDFExportFileType", "org.freehep.graphicsio.pdf.PDFGraphics2D");
/*     */ 
/*     */     
/* 113 */     b.put("org.freehep.graphicsio.ps.PSExportFileType", "org.freehep.graphicsio.ps.PSGraphics2D");
/*     */ 
/*     */     
/* 116 */     b.put("org.freehep.graphicsio.svg.SVGExportFileType", "org.freehep.graphicsio.svg.SVGGraphics2D");
/*     */ 
/*     */     
/* 119 */     b.put("org.freehep.graphicsio.swf.SWFExportFileType", "org.freehep.graphicsio.swf.SWFGraphics2D");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */