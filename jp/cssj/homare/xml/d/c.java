/*    */ package jp.cssj.homare.xml.d;
/*    */ 
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import javax.xml.transform.Source;
/*    */ import javax.xml.transform.stream.StreamSource;
/*    */ import jp.cssj.e.b;
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
/*    */ public final class c
/*    */ {
/*    */   public static Source a(b source) throws IOException {
/*    */     StreamSource traxSource;
/* 23 */     if (source.i()) {
/* 24 */       traxSource = new StreamSource(source.j(), source.d().toString());
/*    */     } else {
/* 26 */       traxSource = new StreamSource(source.h(), source.d().toString());
/*    */     } 
/* 28 */     return traxSource;
/*    */   }
/*    */   
/*    */   public static StreamSource b(b source) throws IOException {
/*    */     StreamSource streamSource;
/* 33 */     if (source.i()) {
/* 34 */       streamSource = new StreamSource(new BufferedReader(source.j()));
/*    */     } else {
/* 36 */       streamSource = new StreamSource(new BufferedInputStream(source.h()));
/*    */     } 
/* 38 */     streamSource.setSystemId(source.d().toString());
/* 39 */     return streamSource;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/d/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */