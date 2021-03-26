/*     */ package org.apache.xpath;
/*     */ 
/*     */ import org.apache.xpath.axes.LocPathIterator;
/*     */ import org.apache.xpath.axes.UnionPathIterator;
/*     */ import org.apache.xpath.functions.Function;
/*     */ import org.apache.xpath.objects.XNumber;
/*     */ import org.apache.xpath.objects.XString;
/*     */ import org.apache.xpath.operations.Operation;
/*     */ import org.apache.xpath.operations.UnaryOperation;
/*     */ import org.apache.xpath.operations.Variable;
/*     */ import org.apache.xpath.patterns.NodeTest;
/*     */ import org.apache.xpath.patterns.StepPattern;
/*     */ import org.apache.xpath.patterns.UnionPattern;
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
/*     */ public class XPathVisitor
/*     */ {
/*     */   public boolean visitLocationPath(ExpressionOwner owner, LocPathIterator path) {
/*  60 */     return true;
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
/*     */   public boolean visitUnionPath(ExpressionOwner owner, UnionPathIterator path) {
/*  72 */     return true;
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
/*     */   public boolean visitStep(ExpressionOwner owner, NodeTest step) {
/*  84 */     return true;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean visitPredicate(ExpressionOwner owner, Expression pred) {
/*  99 */     return true;
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
/*     */   public boolean visitBinaryOperation(ExpressionOwner owner, Operation op) {
/* 111 */     return true;
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
/*     */   public boolean visitUnaryOperation(ExpressionOwner owner, UnaryOperation op) {
/* 123 */     return true;
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
/*     */   public boolean visitVariableRef(ExpressionOwner owner, Variable var) {
/* 135 */     return true;
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
/*     */   public boolean visitFunction(ExpressionOwner owner, Function func) {
/* 147 */     return true;
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
/*     */   public boolean visitMatchPattern(ExpressionOwner owner, StepPattern pattern) {
/* 159 */     return true;
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
/*     */   public boolean visitUnionPattern(ExpressionOwner owner, UnionPattern pattern) {
/* 171 */     return true;
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
/*     */   public boolean visitStringLiteral(ExpressionOwner owner, XString str) {
/* 183 */     return true;
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
/*     */   
/*     */   public boolean visitNumberLiteral(ExpressionOwner owner, XNumber num) {
/* 196 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/XPathVisitor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */