package org.apache.xmlgraphics.util.dijkstra;

import java.util.Iterator;

public interface EdgeDirectory {
  int getPenalty(Vertex paramVertex1, Vertex paramVertex2);
  
  Iterator getDestinations(Vertex paramVertex);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/dijkstra/EdgeDirectory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */