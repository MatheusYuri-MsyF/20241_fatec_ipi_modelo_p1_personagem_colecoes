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

// Retorna o nome do personagem.
  public String getNome() {
    return nome;
  }

  //esse é o construtor padrão
  //criado automaticamente pelo compilador, ainda que não seja escrito explicitamente
  Personagem(){
    nome = nomear_personagem();     // - Nomeia o personagem.
    energia = 10;      // - Inicializa energia, fome e sono com valores padrão.
    fome = 0;      
    sono = 0;      
    inventario = new VetorDinamico(); // - Cria um inventário vazio.
    itens_iniciais(); // - Adiciona itens iniciais ao inventário.
  }

  //construtor personalizado
  //o que viabiliza a sua existência é a sobrecarga de construtores
  Personagem( int energia, int fome, int sono){
    // - Utiliza o construtor padrão para inicializar os atributos.
    // - Valida e atribui os valores de energia, fome e sono,
    //   mantendo-os dentro dos limites permitidos (0 a 10).
    this(); 
    if (energia >= 0 && energia <= 10)
      this.energia = energia;
    if (fome >= 0 && fome <= 10)
      this.fome = fome;
    if (sono >= 0 && sono <= 10)
      this.sono = sono;
  }

  private String nomear_personagem() {
     // Solicita ao usuário que digite um nome para o personagem.
    System.out.println("Digite um nome para o(a) personagem:");
    Scanner leituraTeclado = new Scanner(System.in);
    String nome = leituraTeclado.nextLine();
    return nome;
  }

   void itens_iniciais(){
     // Adiciona 4 itens aleatórios ao inventário inicial do personagem.
    for (int i = 0; i < 4; i++) {
      int aleatorio  = (int) (Math.random() * 5);
      String item = ITENS_POSSIVEIS[aleatorio];
      inventario.adicionar(item);
    }
  }


  private void cacar(){
    // Simula a ação de caçar:
    // - Verifica se o personagem tem energia suficiente para caçar.
    // - Consome energia e adiciona um item aleatório ao inventário.
    // - Aumenta os níveis de fome e sono.
    // - Verifica se o personagem morreu.
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
    // Verifica se o personagem tem itens no inventário para comer.
    // Se tiver, remove o último item do inventário, diminui a fome e aumenta a energia.
    // Caso contrário, exibe uma mensagem indicando que o inventário está vazio.
    // Verifica se o personagem morreu após comer.
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
    // Verifica se o personagem está com sono.
    // Se estiver, diminui o nível de sono e aumenta a energia.
    // Caso contrário, exibe uma mensagem indicando que o personagem não está com sono.
    // Verifica se o personagem morreu após dormir.
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
    // Diminui a energia do outro personagem.
    // Verifica se o outro personagem morreu após o ataque.
    outro.energia--;
    if (outro.energia <= 0) {
      outro.verificarMorte();
    }
  }

  public boolean verificarMorte() {
    // Verifica se a energia do personagem chegou a zero.
    // Retorna true se o personagem estiver morto, false caso contrário.
    if (energia <= 0) {
      return true;
    }
    return false;
  }

  public void realizarAcao(Personagem personagem){
    // Escolhe aleatoriamente uma ação (dormir, caçar ou comer) para o personagem.
    // Chama o método correspondente para executar a ação.
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
    // Constrói uma string que representa as informações do personagem,
    // incluindo nome, energia, fome, sono e itens do inventário.
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
