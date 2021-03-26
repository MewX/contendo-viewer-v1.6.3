/*     */ package org.apache.xmlgraphics.ps.dsc;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCComment;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentBeginDocument;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentBeginResource;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentBoundingBox;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentDocumentNeededResources;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentDocumentSuppliedResources;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentEndComments;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentEndOfFile;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentHiResBoundingBox;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentIncludeResource;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentLanguageLevel;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentPage;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentPageBoundingBox;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentPageHiResBoundingBox;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentPageResources;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentPages;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCCommentTitle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DSCCommentFactory
/*     */ {
/*  52 */   private static final Map DSC_FACTORIES = new HashMap<Object, Object>();
/*     */   
/*     */   static {
/*  55 */     DSC_FACTORIES.put("EndComments", DSCCommentEndComments.class);
/*     */     
/*  57 */     DSC_FACTORIES.put("BeginResource", DSCCommentBeginResource.class);
/*     */     
/*  59 */     DSC_FACTORIES.put("IncludeResource", DSCCommentIncludeResource.class);
/*     */     
/*  61 */     DSC_FACTORIES.put("PageResources", DSCCommentPageResources.class);
/*     */     
/*  63 */     DSC_FACTORIES.put("BeginDocument", DSCCommentBeginDocument.class);
/*     */     
/*  65 */     DSC_FACTORIES.put("Page", DSCCommentPage.class);
/*     */     
/*  67 */     DSC_FACTORIES.put("Pages", DSCCommentPages.class);
/*     */     
/*  69 */     DSC_FACTORIES.put("BoundingBox", DSCCommentBoundingBox.class);
/*     */     
/*  71 */     DSC_FACTORIES.put("HiResBoundingBox", DSCCommentHiResBoundingBox.class);
/*     */     
/*  73 */     DSC_FACTORIES.put("PageBoundingBox", DSCCommentPageBoundingBox.class);
/*     */     
/*  75 */     DSC_FACTORIES.put("PageHiResBoundingBox", DSCCommentPageHiResBoundingBox.class);
/*     */     
/*  77 */     DSC_FACTORIES.put("LanguageLevel", DSCCommentLanguageLevel.class);
/*     */     
/*  79 */     DSC_FACTORIES.put("DocumentNeededResources", DSCCommentDocumentNeededResources.class);
/*     */     
/*  81 */     DSC_FACTORIES.put("DocumentSuppliedResources", DSCCommentDocumentSuppliedResources.class);
/*     */     
/*  83 */     DSC_FACTORIES.put("Title", DSCCommentTitle.class);
/*     */     
/*  85 */     DSC_FACTORIES.put("EOF", DSCCommentEndOfFile.class);
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
/*     */   public static DSCComment createDSCCommentFor(String name) {
/*  97 */     Class<DSCComment> clazz = (Class)DSC_FACTORIES.get(name);
/*  98 */     if (clazz == null) {
/*  99 */       return null;
/*     */     }
/*     */     try {
/* 102 */       return clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/* 103 */     } catch (InstantiationException e) {
/* 104 */       throw new RuntimeException("Error instantiating instance for '" + name + "': " + e.getMessage());
/*     */     }
/* 106 */     catch (IllegalAccessException e) {
/* 107 */       throw new RuntimeException("Illegal Access error while instantiating instance for '" + name + "': " + e.getMessage());
/*     */     }
/* 109 */     catch (NoSuchMethodException e) {
/* 110 */       throw new RuntimeException(e);
/* 111 */     } catch (InvocationTargetException e) {
/* 112 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/DSCCommentFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */