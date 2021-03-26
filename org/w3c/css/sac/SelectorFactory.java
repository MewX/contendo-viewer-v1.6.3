package org.w3c.css.sac;

public interface SelectorFactory {
  ConditionalSelector createConditionalSelector(SimpleSelector paramSimpleSelector, Condition paramCondition) throws CSSException;
  
  SimpleSelector createAnyNodeSelector() throws CSSException;
  
  SimpleSelector createRootNodeSelector() throws CSSException;
  
  NegativeSelector createNegativeSelector(SimpleSelector paramSimpleSelector) throws CSSException;
  
  ElementSelector createElementSelector(String paramString1, String paramString2) throws CSSException;
  
  CharacterDataSelector createTextNodeSelector(String paramString) throws CSSException;
  
  CharacterDataSelector createCDataSectionSelector(String paramString) throws CSSException;
  
  ProcessingInstructionSelector createProcessingInstructionSelector(String paramString1, String paramString2) throws CSSException;
  
  CharacterDataSelector createCommentSelector(String paramString) throws CSSException;
  
  ElementSelector createPseudoElementSelector(String paramString1, String paramString2) throws CSSException;
  
  DescendantSelector createDescendantSelector(Selector paramSelector, SimpleSelector paramSimpleSelector) throws CSSException;
  
  DescendantSelector createChildSelector(Selector paramSelector, SimpleSelector paramSimpleSelector) throws CSSException;
  
  SiblingSelector createDirectAdjacentSelector(short paramShort, Selector paramSelector, SimpleSelector paramSimpleSelector) throws CSSException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/SelectorFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */