/*     */ package org.apache.xmlgraphics.util.dijkstra;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ public class DefaultEdgeDirectory
/*     */   implements EdgeDirectory
/*     */ {
/*  32 */   private Map edges = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEdge(Edge edge) {
/*  40 */     Map<Object, Object> directEdges = (Map)this.edges.get(edge.getStart());
/*  41 */     if (directEdges == null) {
/*  42 */       directEdges = new HashMap<Object, Object>();
/*  43 */       this.edges.put(edge.getStart(), directEdges);
/*     */     } 
/*  45 */     directEdges.put(edge.getEnd(), edge);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPenalty(Vertex start, Vertex end) {
/*  50 */     Map edgeMap = (Map)this.edges.get(start);
/*  51 */     if (edgeMap != null) {
/*  52 */       Edge route = (Edge)edgeMap.get(end);
/*  53 */       if (route != null) {
/*  54 */         int penalty = route.getPenalty();
/*  55 */         if (penalty < 0) {
/*  56 */           throw new IllegalStateException("Penalty must not be negative");
/*     */         }
/*  58 */         return penalty;
/*     */       } 
/*     */     } 
/*  61 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator getDestinations(Vertex origin) {
/*  66 */     Map directRoutes = (Map)this.edges.get(origin);
/*  67 */     if (directRoutes != null) {
/*  68 */       Iterator iter = directRoutes.keySet().iterator();
/*  69 */       return iter;
/*     */     } 
/*  71 */     return Collections.EMPTY_LIST.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getEdges(Vertex origin) {
/*  80 */     Map directRoutes = (Map)this.edges.get(origin);
/*  81 */     if (directRoutes != null) {
/*  82 */       Iterator iter = directRoutes.values().iterator();
/*  83 */       return iter;
/*     */     } 
/*  85 */     return Collections.EMPTY_LIST.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Edge getBestEdge(Vertex start, Vertex end) {
/*  95 */     Edge best = null;
/*  96 */     Iterator<Edge> iter = getEdges(start);
/*  97 */     while (iter.hasNext()) {
/*  98 */       Edge edge = iter.next();
/*  99 */       if (edge.getEnd().equals(end) && (
/* 100 */         best == null || edge.getPenalty() < best.getPenalty())) {
/* 101 */         best = edge;
/*     */       }
/*     */     } 
/*     */     
/* 105 */     return best;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/dijkstra/DefaultEdgeDirectory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */