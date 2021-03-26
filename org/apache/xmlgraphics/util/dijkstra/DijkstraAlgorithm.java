/*     */ package org.apache.xmlgraphics.util.dijkstra;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
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
/*     */ public class DijkstraAlgorithm
/*     */ {
/*     */   public static final int INFINITE = 2147483647;
/*     */   
/*  40 */   private final Comparator penaltyComparator = new Comparator() {
/*     */       public int compare(Object left, Object right) {
/*  42 */         int leftPenalty = DijkstraAlgorithm.this.getLowestPenalty((Vertex)left);
/*  43 */         int rightPenalty = DijkstraAlgorithm.this.getLowestPenalty((Vertex)right);
/*  44 */         if (leftPenalty < rightPenalty)
/*  45 */           return -1; 
/*  46 */         if (leftPenalty == rightPenalty) {
/*  47 */           return ((Comparable<Object>)left).compareTo(right);
/*     */         }
/*  49 */         return 1;
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   private EdgeDirectory edgeDirectory;
/*     */ 
/*     */   
/*  58 */   private TreeSet priorityQueue = new TreeSet(this.penaltyComparator);
/*     */ 
/*     */ 
/*     */   
/*  62 */   private Set finishedVertices = new HashSet();
/*     */ 
/*     */ 
/*     */   
/*  66 */   private Map lowestPenalties = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/*  70 */   private Map predecessors = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DijkstraAlgorithm(EdgeDirectory edgeDirectory) {
/*  78 */     this.edgeDirectory = edgeDirectory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getPenalty(Vertex start, Vertex end) {
/*  89 */     return this.edgeDirectory.getPenalty(start, end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator getDestinations(Vertex origin) {
/*  98 */     return this.edgeDirectory.getDestinations(origin);
/*     */   }
/*     */   
/*     */   private void reset() {
/* 102 */     this.finishedVertices.clear();
/* 103 */     this.priorityQueue.clear();
/*     */     
/* 105 */     this.lowestPenalties.clear();
/* 106 */     this.predecessors.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(Vertex start, Vertex destination) {
/* 117 */     if (start == null || destination == null) {
/* 118 */       throw new NullPointerException("start and destination may not be null");
/*     */     }
/*     */     
/* 121 */     reset();
/* 122 */     setShortestDistance(start, 0);
/* 123 */     this.priorityQueue.add(start);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     while (this.priorityQueue.size() > 0) {
/* 130 */       Vertex u = this.priorityQueue.first();
/* 131 */       this.priorityQueue.remove(u);
/*     */       
/* 133 */       if (destination.equals(u)) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 138 */       this.finishedVertices.add(u);
/* 139 */       relax(u);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void relax(Vertex u) {
/* 149 */     Iterator<Vertex> iter = getDestinations(u);
/* 150 */     while (iter.hasNext()) {
/* 151 */       Vertex v = iter.next();
/*     */       
/* 153 */       if (isFinished(v)) {
/*     */         continue;
/*     */       }
/*     */       
/* 157 */       int shortDist = getLowestPenalty(u) + getPenalty(u, v);
/*     */       
/* 159 */       if (shortDist < getLowestPenalty(v)) {
/*     */         
/* 161 */         setShortestDistance(v, shortDist);
/*     */ 
/*     */         
/* 164 */         setPredecessor(v, u);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setPredecessor(Vertex a, Vertex b) {
/* 170 */     this.predecessors.put(a, b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isFinished(Vertex v) {
/* 179 */     return this.finishedVertices.contains(v);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setShortestDistance(Vertex vertex, int distance) {
/* 185 */     this.priorityQueue.remove(vertex);
/*     */ 
/*     */     
/* 188 */     this.lowestPenalties.put(vertex, Integer.valueOf(distance));
/*     */ 
/*     */     
/* 191 */     this.priorityQueue.add(vertex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLowestPenalty(Vertex vertex) {
/* 201 */     Integer d = (Integer)this.lowestPenalties.get(vertex);
/* 202 */     return (d == null) ? Integer.MAX_VALUE : d.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vertex getPredecessor(Vertex vertex) {
/* 212 */     return (Vertex)this.predecessors.get(vertex);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/dijkstra/DijkstraAlgorithm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */