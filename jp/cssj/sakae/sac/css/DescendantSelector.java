package jp.cssj.sakae.sac.css;

public interface DescendantSelector extends Selector {
  Selector getAncestorSelector();
  
  SimpleSelector getSimpleSelector();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/DescendantSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */