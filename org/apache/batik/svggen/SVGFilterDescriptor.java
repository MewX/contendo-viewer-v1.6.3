/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGFilterDescriptor
/*    */ {
/*    */   private Element def;
/*    */   private String filterValue;
/*    */   
/*    */   public SVGFilterDescriptor(String filterValue) {
/* 35 */     this.filterValue = filterValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public SVGFilterDescriptor(String filterValue, Element def) {
/* 40 */     this(filterValue);
/* 41 */     this.def = def;
/*    */   }
/*    */   
/*    */   public String getFilterValue() {
/* 45 */     return this.filterValue;
/*    */   }
/*    */   
/*    */   public Element getDef() {
/* 49 */     return this.def;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGFilterDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */