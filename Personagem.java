import java.util.Random;
import java.util.Scanner;

public class Personagem{
  //variáveis de instância(objeto)
  private String nome;
  private int energia;
  private int fome;
  private int sono;
  private VetorDinamico inventario;
  private static final String[] ITENS_POSSIVEIS = {
    "Javali selvagem", "amora", "batata frita", "cogumelo", "mel"
  };


  public String getNome() {
    return nome;
  }

  //esse é o construtor padrão
  //criado automaticamente pelo compilador, ainda que não seja escrito explicitamente
  Personagem(){
    nome = nomear_personagem();
    energia = 10;
    fome = 0;
    sono = 0;
    inventario = new VetorDinamico();
    itens_iniciais();
  }

  //construtor personalizado
  //o que viabiliza a sua existência é a sobrecarga de construtores
  Personagem( int energia, int fome, int sono){
    this();
    if (energia >= 0 && energia <= 10)
      this.energia = energia;
    if (fome >= 0 && fome <= 10)
      this.fome = fome;
    if (sono >= 0 && sono <= 10)
      this.sono = sono;
  }

  private String nomear_personagem() {
    System.out.println("Digite um nome para o(a) personagem:");
    Scanner leituraTeclado = new Scanner(System.in);
    String nome = leituraTeclado.nextLine();
    return nome;
  }

   void itens_iniciais(){
    for (int i = 0; i < 4; i++) {
      int aleatorio  = (int) (Math.random() * 5);
      String item = ITENS_POSSIVEIS[aleatorio];
      inventario.adicionar(item);
    }{

    }
  }


  private void cacar(){
    if(energia >= 2){
      System.out.printf("%s esta cacando...\n", nome);
      energia -= 2; // energia = energia - 2;
      int aleatorio = (int) (Math.random() * 5);
      String item = ITENS_POSSIVEIS[aleatorio];
      inventario.adicionar(item);
      System.out.printf("%s encontrou um(a) %s!\n", nome, item);
    }
    else{
      System.out.printf("%s sem energia para cacar...\n", nome);
    }
    verificarMorte();
    fome = Math.min(fome + 1, 10);
    //resolver com o ternário
    sono = sono < 10 ? sono + 1 : sono;
  }

  private void comer() {
    if (inventario.estaVazio()) {
      System.out.printf("%s não tem itens para comer\n", nome);
    }
    else {
      String itemComido = inventario.removerNoFinal();
      System.out.printf("%s está comendo %s\n", nome, itemComido);
      fome = Math.max(fome - 1, 0);
      energia = Math.min(energia + 1, 10);
      }
      verificarMorte();
  }

  private void dormir(){
    if(sono >= 1){
      System.out.printf("%s esta dormindo...\n", nome);
      sono -= 1;
      energia = Math.min(energia + 1, 10);
    }
    else{
      System.out.printf("%s sem sono...\n", nome);
    }
    verificarMorte();
  }

  public void atacar(Personagem outro) {
    outro.energia--;
    if (outro.energia <= 0) {
      outro.verificarMorte();
    }
  }


  public boolean verificarMorte() {
    if (energia <= 0) {
      return true;
    }
    return false;
  }


  public void realizarAcao(Personagem personagem){
    int acao = (int) (Math.random() * 3);
    switch (acao) {
      case 0:
        personagem.dormir();
        break;
      case 1:
        personagem.cacar();
        break;
      case 2:
        personagem.comer();
        break;
    }
  }



  @Override
  public String toString() {
    StringBuilder descricao = new StringBuilder();
    descricao.append(String.format(
            "Info. sobre %s: \n- Energia: %d \n- Fome: %d \n- Sono: %d \n- Inventario: ",
            nome, energia, fome, sono
    ));

    // Obtém os elementos do inventário
    String[] elementos = inventario.getElementos();

    // Percorre os elementos do inventário e adiciona cada um à descrição
    for (int i = 0; i < inventario.tamanho(); i++) {
      descricao.append(elementos[i]);
      if (i < inventario.tamanho() - 1) {
        descricao.append(", ");
      }
    }

    descricao.append(" ");

    return descricao.toString();
  }

}