/*    */ package net.a.a.e.d;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import javax.xml.namespace.NamespaceContext;
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
/*    */ public class f
/*    */   implements NamespaceContext
/*    */ {
/*    */   private final String a;
/*    */   private final String b;
/*    */   private final NamespaceContext c;
/*    */   
/*    */   public f(@Nonnull String paramString1, @Nonnull String paramString2, @Nullable NamespaceContext paramNamespaceContext) {
/* 37 */     this.a = paramString1;
/* 38 */     this.b = paramString2;
/* 39 */     this.c = paramNamespaceContext;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNamespaceURI(String paramString) {
/*    */     String str;
/* 45 */     if (this.a.equals(paramString)) {
/* 46 */       str = this.b;
/* 47 */     } else if (this.c == null) {
/* 48 */       str = "";
/*    */     } else {
/* 50 */       str = this.c.getNamespaceURI(paramString);
/*    */     } 
/* 52 */     return str;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPrefix(String paramString) {
/*    */     String str;
/* 58 */     if (this.b.equals(paramString)) {
/* 59 */       str = this.a;
/* 60 */     } else if (this.c == null) {
/* 61 */       str = "";
/*    */     } else {
/* 63 */       str = this.c.getPrefix(paramString);
/*    */     } 
/* 65 */     return str;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Iterator<String> getPrefixes(String paramString) {
/*    */     Iterator<String> iterator;
/* 72 */     if (this.b.equals(paramString)) {
/* 73 */       iterator = Collections.<String>singleton(this.a).iterator();
/* 74 */     } else if (this.c == null) {
/* 75 */       iterator = Collections.EMPTY_LIST.iterator();
/*    */     } else {
/* 77 */       iterator = this.c.getPrefixes(paramString);
/*    */     } 
/* 79 */     return iterator;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */