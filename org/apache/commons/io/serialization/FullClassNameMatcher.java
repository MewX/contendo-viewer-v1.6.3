/*    */ package org.apache.commons.io.serialization;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
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
/*    */ final class FullClassNameMatcher
/*    */   implements ClassNameMatcher
/*    */ {
/*    */   private final Set<String> classesSet;
/*    */   
/*    */   public FullClassNameMatcher(String... classes) {
/* 42 */     this.classesSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(classes)));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(String className) {
/* 47 */     return this.classesSet.contains(className);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/serialization/FullClassNameMatcher.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */