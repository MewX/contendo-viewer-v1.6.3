package org.apache.xmlgraphics.util.dijkstra;

public interface Edge {
  Vertex getStart();
  
  Vertex getEnd();
  
  int getPenalty();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/dijkstra/Edge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */