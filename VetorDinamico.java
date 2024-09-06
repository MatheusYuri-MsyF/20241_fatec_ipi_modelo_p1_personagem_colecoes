import java.util.Arrays;

public class VetorDinamico {
  //variáveis de instância (cada instância ou objeto tem a sua cópia)
  private int qtde;
  private int cap;
  private String [] elementos;
  //variável de classe (todas as instâncias compartilham este mesmo valor)
  private static final int CAP_MINIMA = 4;
  VetorDinamico(){
    cap = CAP_MINIMA;
    qtde = 0;
    elementos = new String[cap];
  }


  VetorDinamico(int capMinima){
    this();
    double aux = capMinima;
    if (aux > CAP_MINIMA) {
      cap = (int) aux;
      elementos = new String[cap];
    }
  }

  void adicionar(String elemento){
    // Adiciona um elemento ao final do vetor.
        // Se o vetor estiver cheio, redimensiona antes de adicionar.
    if(estaCheio())redimensionar();
    elementos[qtde] = elemento;
    qtde++;
  }

  boolean estaCheio(){
    // Verifica se o vetor está com a capacidade máxima.
        // Retorna true se estiver cheio, false caso contrário.
     return qtde == cap ? true : false;
    
  }
  
  private void redimensionar(){
    // Cria um novo vetor com o dobro da capacidade atual.
        // Copia os elementos do vetor antigo para o novo.
        // Atualiza a capacidade e o ponteiro para o novo vetor.
    String [] aux = new String[cap * 2];
    //copiar todo mundo do vetor elementos para o vetor auxiliar
    for(int i = 0; i < cap; i++){
       aux[i] = elementos[i];
    }
    //ajustar a capacidade para que ela tenha o valor novo, dobrado
    cap *= 2;

    //ajustar o ponteiro elementos para que ele aponte para o novo vetor
    elementos = aux;
    // System.gc();
  }

  void adicionarSemRepeticao(String e){
    // Adiciona um elemento ao vetor, mas somente se ele ainda não existir.
    if(!existe(e)) adicionar(e);
  }

  boolean existe(String e){
    // Verifica se um elemento existe no vetor.
        // Retorna true se o elemento for encontrado, false caso contrário.
    for (int i = 0; i < qtde; i++)
      if (e == elementos[i])
        return true;
    return false;
  }

  int tamanho(){
    // Retorna o número de elementos atualmente no vetor.
    return qtde;
  }

  String removerNoFinal(){
    // Remove e retorna o último elemento do vetor.
        // Se o vetor estiver vazio, retorna null.
    if (estaVazio()) {
      return null;  // Retorna nulo se o vetor está vazio.
    } else {
      String item = elementos[qtde - 1];  // Pega o último item.
      elementos[qtde - 1] = null;  // Remove o item.
      qtde--;  // Reduz a quantidade de elementos.
      return item;  // Retorna o item removido.
    }
  }

  boolean estaVazio(){
    // Verifica se o vetor está vazio.
        // Retorna true se estiver vazio, false caso contrário.
      return qtde == 0 ? true : false;
  }

  boolean estaUmQuartoCheio(){
    // Verifica se o vetor está com pelo menos um quarto da sua capacidade ocupada.
    return cap/2 == qtde ? true : false;
  }

  void reduzirTamanho(){
     // Cria um novo vetor com a metade da capacidade atual.
    // Copia os elementos necessários para o novo vetor.
    // Atualiza a capacidade e o ponteiro para o novo vetor.
    // Essa operação é útil para otimizar a memória quando o vetor está
    // subutilizado, mas deve ser usada com cuidado para evitar realocar
    // o vetor com frequência.
  }

  String[] getElementos(){
    // Retorna uma cópia do array de elementos interno.
        // Isso evita que o chamador modifique diretamente os elementos do vetor.
    return Arrays.copyOf(elementos, cap);
  }

  public String toString(){
    // Retorna uma representação em string do vetor, incluindo a quantidade de elementos,
        // a capacidade e os próprios elementos.
    StringBuilder sb = new StringBuilder("");
    sb.append("Qtde: ").append(qtde);
    sb.append("\n");
    sb.append("Cap: ").append(cap);
    sb.append(qtde > 0 ? "\nElementos: " : "");
    for (int i = 0; i < qtde; i++){
      sb.append(elementos[i]).append(" ");
    }
    return sb.toString();
  }
}
