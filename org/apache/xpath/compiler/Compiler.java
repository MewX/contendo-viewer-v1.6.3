/*      */ package org.apache.xpath.compiler;
/*      */ 
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xml.dtm.DTMIterator;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xml.utils.QName;
/*      */ import org.apache.xml.utils.SAXSourceLocator;
/*      */ import org.apache.xpath.Expression;
/*      */ import org.apache.xpath.axes.UnionPathIterator;
/*      */ import org.apache.xpath.axes.WalkerFactory;
/*      */ import org.apache.xpath.functions.FuncExtFunction;
/*      */ import org.apache.xpath.functions.Function;
/*      */ import org.apache.xpath.functions.WrongNumberArgsException;
/*      */ import org.apache.xpath.operations.And;
/*      */ import org.apache.xpath.operations.Bool;
/*      */ import org.apache.xpath.operations.Div;
/*      */ import org.apache.xpath.operations.Equals;
/*      */ import org.apache.xpath.operations.Gt;
/*      */ import org.apache.xpath.operations.Gte;
/*      */ import org.apache.xpath.operations.Lt;
/*      */ import org.apache.xpath.operations.Lte;
/*      */ import org.apache.xpath.operations.Minus;
/*      */ import org.apache.xpath.operations.Mod;
/*      */ import org.apache.xpath.operations.Mult;
/*      */ import org.apache.xpath.operations.Neg;
/*      */ import org.apache.xpath.operations.NotEquals;
/*      */ import org.apache.xpath.operations.Number;
/*      */ import org.apache.xpath.operations.Operation;
/*      */ import org.apache.xpath.operations.Or;
/*      */ import org.apache.xpath.operations.Plus;
/*      */ import org.apache.xpath.operations.String;
/*      */ import org.apache.xpath.operations.UnaryOperation;
/*      */ import org.apache.xpath.operations.Variable;
/*      */ import org.apache.xpath.patterns.FunctionPattern;
/*      */ import org.apache.xpath.patterns.StepPattern;
/*      */ import org.apache.xpath.patterns.UnionPattern;
/*      */ import org.apache.xpath.res.XPATHMessages;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Compiler
/*      */   extends OpMap
/*      */ {
/*      */   private int locPathDepth;
/*      */   private static final boolean DEBUG = false;
/*      */   
/*      */   public Compiler(ErrorListener errorHandler, SourceLocator locator) {
/*  609 */     this.locPathDepth = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1216 */     this.m_currentPrefixResolver = null; this.m_errorHandler = errorHandler; this.m_locator = locator; } public Expression compile(int opPos) throws TransformerException { int op = getOp(opPos); Expression expr = null; switch (op) { case 1: expr = compile(opPos + 2); return expr;case 2: expr = or(opPos); return expr;case 3: expr = and(opPos); return expr;case 4: expr = notequals(opPos); return expr;case 5: expr = equals(opPos); return expr;case 6: expr = lte(opPos); return expr;case 7: expr = lt(opPos); return expr;case 8: expr = gte(opPos); return expr;case 9: expr = gt(opPos); return expr;case 10: expr = plus(opPos); return expr;case 11: expr = minus(opPos); return expr;case 12: expr = mult(opPos); return expr;case 13: expr = div(opPos); return expr;case 14: expr = mod(opPos); return expr;case 16: expr = neg(opPos); return expr;case 17: expr = string(opPos); return expr;case 18: expr = bool(opPos); return expr;case 19: expr = number(opPos); return expr;case 20: expr = union(opPos); return expr;case 21: expr = literal(opPos); return expr;case 22: expr = variable(opPos); return expr;case 23: expr = group(opPos); return expr;case 27: expr = numberlit(opPos); return expr;case 26: expr = arg(opPos); return expr;case 24: expr = compileExtension(opPos); return expr;case 25: expr = compileFunction(opPos); return expr;case 28: expr = locationPath(opPos); return expr;case 29: expr = null; return expr;case 30: expr = matchPattern(opPos + 2); return expr;case 31: expr = locationPathPattern(opPos); return expr;case 15: error("ER_UNKNOWN_OPCODE", new Object[] { "quo" }); return expr; }  error("ER_UNKNOWN_OPCODE", new Object[] { Integer.toString(getOp(opPos)) }); return expr; } private Expression compileOperation(Operation operation, int opPos) throws TransformerException { int leftPos = OpMap.getFirstChildPos(opPos); int rightPos = getNextOpPos(leftPos); operation.setLeftRight(compile(leftPos), compile(rightPos)); return (Expression)operation; } private Expression compileUnary(UnaryOperation unary, int opPos) throws TransformerException { int rightPos = OpMap.getFirstChildPos(opPos); unary.setRight(compile(rightPos)); return (Expression)unary; } protected Expression or(int opPos) throws TransformerException { return compileOperation((Operation)new Or(), opPos); } protected Expression and(int opPos) throws TransformerException { return compileOperation((Operation)new And(), opPos); } protected Expression notequals(int opPos) throws TransformerException { return compileOperation((Operation)new NotEquals(), opPos); } protected Expression equals(int opPos) throws TransformerException { return compileOperation((Operation)new Equals(), opPos); } protected Expression lte(int opPos) throws TransformerException { return compileOperation((Operation)new Lte(), opPos); } protected Expression lt(int opPos) throws TransformerException { return compileOperation((Operation)new Lt(), opPos); } protected Expression gte(int opPos) throws TransformerException { return compileOperation((Operation)new Gte(), opPos); } protected Expression gt(int opPos) throws TransformerException { return compileOperation((Operation)new Gt(), opPos); } protected Expression plus(int opPos) throws TransformerException { return compileOperation((Operation)new Plus(), opPos); } public Compiler() { this.locPathDepth = -1; this.m_currentPrefixResolver = null; this.m_errorHandler = null; this.m_locator = null; }
/*      */   protected Expression minus(int opPos) throws TransformerException { return compileOperation((Operation)new Minus(), opPos); }
/*      */   protected Expression mult(int opPos) throws TransformerException { return compileOperation((Operation)new Mult(), opPos); }
/*      */   protected Expression div(int opPos) throws TransformerException { return compileOperation((Operation)new Div(), opPos); }
/*      */   protected Expression mod(int opPos) throws TransformerException { return compileOperation((Operation)new Mod(), opPos); }
/*      */   protected Expression neg(int opPos) throws TransformerException { return compileUnary((UnaryOperation)new Neg(), opPos); }
/*      */   protected Expression string(int opPos) throws TransformerException { return compileUnary((UnaryOperation)new String(), opPos); }
/*      */   protected Expression bool(int opPos) throws TransformerException { return compileUnary((UnaryOperation)new Bool(), opPos); }
/*      */   protected Expression number(int opPos) throws TransformerException { return compileUnary((UnaryOperation)new Number(), opPos); }
/* 1225 */   protected Expression literal(int opPos) { opPos = OpMap.getFirstChildPos(opPos); return (Expression)getTokenQueue().elementAt(getOp(opPos)); } protected Expression numberlit(int opPos) { opPos = OpMap.getFirstChildPos(opPos); return (Expression)getTokenQueue().elementAt(getOp(opPos)); } protected Expression variable(int opPos) throws TransformerException { Variable var = new Variable(); opPos = OpMap.getFirstChildPos(opPos); int nsPos = getOp(opPos); String namespace = (-2 == nsPos) ? null : (String)getTokenQueue().elementAt(nsPos); String localname = (String)getTokenQueue().elementAt(getOp(opPos + 1)); QName qname = new QName(namespace, localname); var.setQName(qname); return (Expression)var; } public PrefixResolver getNamespaceContext() { return this.m_currentPrefixResolver; } protected Expression group(int opPos) throws TransformerException { return compile(opPos + 2); } protected Expression arg(int opPos) throws TransformerException { return compile(opPos + 2); }
/*      */   protected Expression union(int opPos) throws TransformerException { this.locPathDepth++; try { return (Expression)UnionPathIterator.createUnionIterator(this, opPos); } finally { this.locPathDepth--; }  }
/*      */   public int getLocationPathDepth() { return this.locPathDepth; }
/*      */   public Expression locationPath(int opPos) throws TransformerException { this.locPathDepth++; try { DTMIterator iter = WalkerFactory.newDTMIterator(this, opPos, (this.locPathDepth == 0)); return (Expression)iter; } finally { this.locPathDepth--; }  }
/*      */   public Expression predicate(int opPos) throws TransformerException { return compile(opPos + 2); }
/*      */   protected Expression matchPattern(int opPos) throws TransformerException { this.locPathDepth++; try { int nextOpPos = opPos; int i; for (i = 0; getOp(nextOpPos) == 31; i++) nextOpPos = getNextOpPos(nextOpPos);  if (i == 1) return compile(opPos);  UnionPattern up = new UnionPattern(); StepPattern[] patterns = new StepPattern[i]; for (i = 0; getOp(opPos) == 31; i++) { nextOpPos = getNextOpPos(opPos); patterns[i] = (StepPattern)compile(opPos); opPos = nextOpPos; }  up.setPatterns(patterns); return (Expression)up; } finally { this.locPathDepth--; }  }
/*      */   public Expression locationPathPattern(int opPos) throws TransformerException { opPos = OpMap.getFirstChildPos(opPos); return (Expression)stepPattern(opPos, 0, null); }
/*      */   public int getWhatToShow(int opPos) { int axesType = getOp(opPos); int testType = getOp(opPos + 3); switch (testType) { case 1030: return 128;case 1031: return 12;case 1032: return 64;case 1033: switch (axesType) { case 49: return 4096;case 39: case 51: return 2;case 38: case 42: case 48: return -1; }  if (getOp(0) == 30) return -1283;  return -3;case 35: return 1280;case 1034: return 65536;case 34: switch (axesType) { case 49: return 4096;case 39: case 51: return 2;case 52: case 53: return 1; }  return 1; }  return -1; }
/*      */   protected StepPattern stepPattern(int opPos, int stepCount, StepPattern ancestorPattern) throws TransformerException { FunctionPattern functionPattern; StepPattern stepPattern1; int argLen, what, startOpPos = opPos; int stepType = getOp(opPos); if (-1 == stepType) return null;  boolean addMagicSelf = true; int endStep = getNextOpPos(opPos); switch (stepType) { case 25: addMagicSelf = false; argLen = getOp(opPos + 1); functionPattern = new FunctionPattern(compileFunction(opPos), 10, 3); break;case 50: addMagicSelf = false; argLen = getArgLengthOfStep(opPos); opPos = OpMap.getFirstChildPosOfStep(opPos); stepPattern1 = new StepPattern(1280, 10, 3); break;case 51: argLen = getArgLengthOfStep(opPos); opPos = OpMap.getFirstChildPosOfStep(opPos); stepPattern1 = new StepPattern(2, getStepNS(startOpPos), getStepLocalName(startOpPos), 10, 2); break;case 52: argLen = getArgLengthOfStep(opPos); opPos = OpMap.getFirstChildPosOfStep(opPos); what = getWhatToShow(startOpPos); if (1280 == what) addMagicSelf = false;  stepPattern1 = new StepPattern(getWhatToShow(startOpPos), getStepNS(startOpPos), getStepLocalName(startOpPos), 0, 3); break;case 53: argLen = getArgLengthOfStep(opPos); opPos = OpMap.getFirstChildPosOfStep(opPos); stepPattern1 = new StepPattern(getWhatToShow(startOpPos), getStepNS(startOpPos), getStepLocalName(startOpPos), 10, 3); break;default: error("ER_UNKNOWN_MATCH_OPERATION", null); return null; }  stepPattern1.setPredicates(getCompiledPredicates(opPos + argLen)); if (null != ancestorPattern) stepPattern1.setRelativePathPattern(ancestorPattern);  StepPattern relativePathPattern = stepPattern(endStep, stepCount + 1, stepPattern1); return (null != relativePathPattern) ? relativePathPattern : stepPattern1; }
/*      */   public Expression[] getCompiledPredicates(int opPos) throws TransformerException { int count = countPredicates(opPos); if (count > 0) { Expression[] predicates = new Expression[count]; compilePredicates(opPos, predicates); return predicates; }  return null; }
/* 1235 */   public void setNamespaceContext(PrefixResolver pr) { this.m_currentPrefixResolver = pr; }
/*      */ 
/*      */   
/*      */   public int countPredicates(int opPos) throws TransformerException {
/*      */     int count = 0;
/*      */     while (29 == getOp(opPos)) {
/*      */       count++;
/*      */       opPos = getNextOpPos(opPos);
/*      */     } 
/*      */     return count;
/*      */   }
/*      */   
/*      */   private void compilePredicates(int opPos, Expression[] predicates) throws TransformerException {
/*      */     for (int i = 0; 29 == getOp(opPos); i++) {
/*      */       predicates[i] = predicate(opPos);
/*      */       opPos = getNextOpPos(opPos);
/*      */     } 
/*      */   }
/*      */   
/*      */   Expression compileFunction(int opPos) throws TransformerException {
/*      */     int endFunc = opPos + getOp(opPos + 1) - 1;
/*      */     opPos = OpMap.getFirstChildPos(opPos);
/*      */     int funcID = getOp(opPos);
/*      */     opPos++;
/*      */     if (-1 != funcID) {
/*      */       Function func = FunctionTable.getFunction(funcID);
/*      */       func.postCompileStep(this);
/*      */       try {
/*      */         int i = 0;
/*      */         for (int p = opPos; p < endFunc; p = getNextOpPos(p), i++)
/*      */           func.setArg(compile(p), i); 
/*      */         func.checkNumberArgs(i);
/*      */       } catch (WrongNumberArgsException wnae) {
/*      */         String name = FunctionTable.m_functions[funcID].getName();
/*      */         this.m_errorHandler.fatalError(new TransformerException(XPATHMessages.createXPATHMessage("ER_ONLY_ALLOWS", new Object[] { name, wnae.getMessage() }), this.m_locator));
/*      */       } 
/*      */       return (Expression)func;
/*      */     } 
/*      */     error("ER_FUNCTION_TOKEN_NOT_FOUND", null);
/*      */     return null;
/*      */   }
/*      */   
/*      */   private static long s_nextMethodId = 0L;
/*      */   private PrefixResolver m_currentPrefixResolver;
/*      */   ErrorListener m_errorHandler;
/*      */   SourceLocator m_locator;
/*      */   
/*      */   private synchronized long getNextMethodId() {
/*      */     if (s_nextMethodId == Long.MAX_VALUE)
/*      */       s_nextMethodId = 0L; 
/*      */     return s_nextMethodId++;
/*      */   }
/*      */   
/*      */   private Expression compileExtension(int opPos) throws TransformerException {
/*      */     int endExtFunc = opPos + getOp(opPos + 1) - 1;
/*      */     opPos = OpMap.getFirstChildPos(opPos);
/*      */     String ns = (String)getTokenQueue().elementAt(getOp(opPos));
/*      */     opPos++;
/*      */     String funcName = (String)getTokenQueue().elementAt(getOp(opPos));
/*      */     opPos++;
/*      */     FuncExtFunction funcExtFunction = new FuncExtFunction(ns, funcName, String.valueOf(getNextMethodId()));
/*      */     try {
/*      */       int i = 0;
/*      */       while (opPos < endExtFunc) {
/*      */         int nextOpPos = getNextOpPos(opPos);
/*      */         funcExtFunction.setArg(compile(opPos), i);
/*      */         opPos = nextOpPos;
/*      */         i++;
/*      */       } 
/*      */     } catch (WrongNumberArgsException wrongNumberArgsException) {}
/*      */     return (Expression)funcExtFunction;
/*      */   }
/*      */   
/*      */   public void warn(String msg, Object[] args) throws TransformerException {
/*      */     String fmsg = XPATHMessages.createXPATHWarning(msg, args);
/*      */     if (null != this.m_errorHandler) {
/*      */       this.m_errorHandler.warning(new TransformerException(fmsg, this.m_locator));
/*      */     } else {
/*      */       System.out.println(fmsg + "; file " + this.m_locator.getSystemId() + "; line " + this.m_locator.getLineNumber() + "; column " + this.m_locator.getColumnNumber());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void assertion(boolean b, String msg) {
/*      */     if (!b) {
/*      */       String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { msg });
/*      */       throw new RuntimeException(fMsg);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void error(String msg, Object[] args) throws TransformerException {
/*      */     String fmsg = XPATHMessages.createXPATHMessage(msg, args);
/*      */     if (null != this.m_errorHandler) {
/*      */       this.m_errorHandler.fatalError(new TransformerException(fmsg, this.m_locator));
/*      */     } else {
/*      */       throw new TransformerException(fmsg, (SAXSourceLocator)this.m_locator);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/compiler/Compiler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */