/*    */ package org.apache.commons.io.serialization;
/*    */ 
/*    */ import java.util.regex.Pattern;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class RegexpClassNameMatcher
/*    */   implements ClassNameMatcher
/*    */ {
/*    */   private final Pattern pattern;
/*    */   
/*    */   public RegexpClassNameMatcher(String regex) {
/* 39 */     this(Pattern.compile(regex));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RegexpClassNameMatcher(Pattern pattern) {
/* 49 */     if (pattern == null) {
/* 50 */       throw new IllegalArgumentException("Null pattern");
/*    */     }
/* 52 */     this.pattern = pattern;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(String className) {
/* 57 */     return this.pattern.matcher(className).matches();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/serialization/RegexpClassNameMatcher.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */