package org.apache.commons.collections.functors;

import org.apache.commons.collections.Predicate;

public interface PredicateDecorator extends Predicate {
  Predicate[] getPredicates();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/PredicateDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */