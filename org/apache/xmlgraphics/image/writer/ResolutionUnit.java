/*    */ package org.apache.xmlgraphics.image.writer;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public enum ResolutionUnit
/*    */ {
/* 32 */   NONE(1, "None"),
/*    */   
/* 34 */   INCH(2, "Inch"),
/*    */   
/* 36 */   CENTIMETER(3, "Centimeter");
/*    */   
/*    */   static {
/* 39 */     LOOKUP = new HashMap<Integer, ResolutionUnit>();
/*    */ 
/*    */ 
/*    */     
/* 43 */     for (ResolutionUnit unit : EnumSet.<ResolutionUnit>allOf(ResolutionUnit.class))
/* 44 */       LOOKUP.put(Integer.valueOf(unit.getValue()), unit); 
/*    */   }
/*    */   
/*    */   private static final Map<Integer, ResolutionUnit> LOOKUP;
/*    */   private final int value;
/*    */   private final String description;
/*    */   
/*    */   ResolutionUnit(int value, String description) {
/* 52 */     this.value = value;
/* 53 */     this.description = description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getValue() {
/* 62 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 71 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ResolutionUnit get(int value) {
/* 81 */     return LOOKUP.get(Integer.valueOf(value));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/ResolutionUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */