/*    */ package org.apache.batik.svggen.font.table;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
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
/*    */ public abstract class GlyfDescript
/*    */   extends Program
/*    */   implements GlyphDescription
/*    */ {
/*    */   public static final byte onCurve = 1;
/*    */   public static final byte xShortVector = 2;
/*    */   public static final byte yShortVector = 4;
/*    */   public static final byte repeat = 8;
/*    */   public static final byte xDual = 16;
/*    */   public static final byte yDual = 32;
/*    */   protected GlyfTable parentTable;
/*    */   private int numberOfContours;
/*    */   private short xMin;
/*    */   private short yMin;
/*    */   private short xMax;
/*    */   private short yMax;
/*    */   
/*    */   protected GlyfDescript(GlyfTable parentTable, short numberOfContours, ByteArrayInputStream bais) {
/* 45 */     this.parentTable = parentTable;
/* 46 */     this.numberOfContours = numberOfContours;
/* 47 */     this.xMin = (short)(bais.read() << 8 | bais.read());
/* 48 */     this.yMin = (short)(bais.read() << 8 | bais.read());
/* 49 */     this.xMax = (short)(bais.read() << 8 | bais.read());
/* 50 */     this.yMax = (short)(bais.read() << 8 | bais.read());
/*    */   }
/*    */ 
/*    */   
/*    */   public void resolve() {}
/*    */   
/*    */   public int getNumberOfContours() {
/* 57 */     return this.numberOfContours;
/*    */   }
/*    */   
/*    */   public short getXMaximum() {
/* 61 */     return this.xMax;
/*    */   }
/*    */   
/*    */   public short getXMinimum() {
/* 65 */     return this.xMin;
/*    */   }
/*    */   
/*    */   public short getYMaximum() {
/* 69 */     return this.yMax;
/*    */   }
/*    */   
/*    */   public short getYMinimum() {
/* 73 */     return this.yMin;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/GlyfDescript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */