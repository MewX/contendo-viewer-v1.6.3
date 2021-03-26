/*    */ package com.levigo.jbig2.decoder.huffman;
/*    */ 
/*    */ import com.levigo.jbig2.io.SubInputStream;
/*    */ import com.levigo.jbig2.segments.Table;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
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
/*    */ public class EncodedTable
/*    */   extends HuffmanTable
/*    */ {
/*    */   private Table table;
/*    */   
/*    */   public EncodedTable(Table paramTable) throws IOException {
/* 35 */     this.table = paramTable;
/* 36 */     parseTable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void parseTable() throws IOException {
/* 41 */     SubInputStream subInputStream = this.table.getSubInputStream();
/*    */     
/* 43 */     ArrayList<HuffmanTable.Code> arrayList = new ArrayList();
/*    */ 
/*    */     
/* 46 */     int k = this.table.getHtLow();
/*    */ 
/*    */     
/* 49 */     while (k < this.table.getHtHigh()) {
/* 50 */       int m = (int)subInputStream.readBits(this.table.getHtPS());
/* 51 */       int n = (int)subInputStream.readBits(this.table.getHtRS());
/* 52 */       int i1 = k;
/*    */       
/* 54 */       arrayList.add(new HuffmanTable.Code(m, n, i1, false));
/*    */       
/* 56 */       k += 1 << n;
/*    */     } 
/*    */ 
/*    */     
/* 60 */     int i = (int)subInputStream.readBits(this.table.getHtPS());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     byte b = 32;
/* 69 */     int j = this.table.getHtHigh() - 1;
/* 70 */     arrayList.add(new HuffmanTable.Code(i, b, j, true));
/*    */ 
/*    */ 
/*    */     
/* 74 */     i = (int)subInputStream.readBits(this.table.getHtPS());
/*    */ 
/*    */     
/* 77 */     b = 32;
/* 78 */     j = this.table.getHtHigh();
/* 79 */     arrayList.add(new HuffmanTable.Code(i, b, j, false));
/*    */ 
/*    */     
/* 82 */     if (this.table.getHtOOB() == 1) {
/* 83 */       i = (int)subInputStream.readBits(this.table.getHtPS());
/* 84 */       arrayList.add(new HuffmanTable.Code(i, -1, -1, false));
/*    */     } 
/*    */     
/* 87 */     System.out.println(codeTableToString(arrayList));
/*    */     
/* 89 */     initTree(arrayList);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/huffman/EncodedTable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */