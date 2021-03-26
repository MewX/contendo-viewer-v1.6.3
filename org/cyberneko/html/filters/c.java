/*     */ package org.cyberneko.html.filters;
/*     */ 
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.cyberneko.html.g;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends a
/*     */ {
/*     */   protected static final String a = "http://cyberneko.org/html/features/augmentations";
/*     */   protected static final String b = "http://cyberneko.org/html/properties/filters";
/*     */   
/*     */   public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/*  72 */     if (augs == null || !a(augs)) {
/*  73 */       super.startElement(element, attributes, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/*  80 */     if (augs == null || !a(augs)) {
/*  81 */       super.emptyElement(element, attributes, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(QName element, Augmentations augs) throws XNIException {
/*  88 */     if (augs == null || !a(augs)) {
/*  89 */       super.endElement(element, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean a(Augmentations augs) {
/*  99 */     g info = (g)augs.getItem("http://cyberneko.org/html/features/augmentations");
/* 100 */     return (info != null) ? info.g() : false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/filters/c.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */