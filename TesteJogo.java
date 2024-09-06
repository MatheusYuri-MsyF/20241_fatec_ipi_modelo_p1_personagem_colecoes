import java.util.Scanner;

public class TesteJogo {
    public static void main(String[] args) {
        // Cria dois personagens com atributos iniciais.
        Personagem p1 = new Personagem(10, 2, 1);

        System.out.println("\n" + p1.toString() + "\n");

        Personagem p2 = new Personagem(10, 2, 1);

        System.out.println("\n" + p2.toString() + "\n");

        
        //         Criação de um Scanner :
        //-----------------------------------------
        Scanner leTeclado = new Scanner(System.in);
        //-----------------------------------------

        // Inicializa variáveis para controlar o fluxo do jogo e contar mortes e vitórias.
        int contmorreu1 = 1;
        int contvenceu = 0;
        int contmorreu2 = 1;

        int Loop = 1;


        //        Mensagem de inicio do game  +  Aguardo de 5 segundos :
        //-----------------------------------------------------------------------
        
        System.out.println("Preparem-se o jogo vai começar em 5 segundos!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {e.printStackTrace();}

        //-----------------------------------------------------------------------

        // Loop principal do jogo.
        while(Loop == 1){
        // Cada iteração do loop representa um turno do jogo.
        // Os personagens realizam ações até que um deles morra ou ambos morram.

            // Personagem 1
            
            if(p1.verificarMorte() == false) {

                System.out.println("\nVez do(a): " + p1.getNome() + "\n");

                System.out.println("Digite:\n\n 1 para sortear entre --> Cacar | Dormir | Comer \n\n");
                String digiteP1 = leTeclado.nextLine();

                switch (digiteP1) {
                    case "1":
                        p1.realizarAcao(p1);
                        break;
                }

                System.out.println(p1.toString());

            }
            if (p1.verificarMorte() == true && contmorreu1 == 1) {
                contmorreu1 ++;
                System.out.println(p1.getNome() + "  M O R R E U !");
            }
            if (p1.verificarMorte() == true && contvenceu == 0) {
                contvenceu ++;
                System.out.println(p2.getNome() + " CAMPEÂO.  É TETRAAAAA ");
            }

            // Personagem 2

            if(p2.verificarMorte() == false) {

                System.out.println("\nVez do(a): " + p2.getNome() + "\n");

                System.out.println("Digite:\n\n 1 para sortear entre --> Cacar | Dormir | Comer \n\n");
                String digiteP2 = leTeclado.nextLine();

                switch (digiteP2) {
                    case "1":
                        p2.realizarAcao(p2);
                        break;
                }

                System.out.println(p2.toString());
            }
            if (p2.verificarMorte() == true && contmorreu2 == 1) {
                contmorreu2 ++;
                System.out.println(p2.getNome() + "  M O R R E U !");

            }
            if (p2.verificarMorte() == true && contvenceu == 0) {
                contvenceu ++;
                System.out.println(p1.getNome() + " CAMPEÂO.  É TETRAAAAA ");
            }



            // -----------------------------------------------------------------------------------------------------------------------
                
            // Verifica se ambos os personagens morreram e encerra o jogo.
            else if (p1.verificarMorte() == true && p2.verificarMorte() == true) {
            // O jogo termina quando ambos os personagens morrem.
            // Exibe uma mensagem e encerra a aplicação.
                System.out.println("\n\nTODOS MORRERAM! A VIDA É CRUEL. \n[espere 3 segundos...]\n");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {e.printStackTrace();}

                System.exit(0);
            }
            
            // Se ambos os personagens estiverem vivos, realiza um ataque aleatório.
            if(p1.verificarMorte() == false && p2.verificarMorte() == false){
                // Escolhe aleatoriamente um personagem para atacar o outro.
                // Isso simula um combate entre os personagens.
                int sorteio = (int) (Math.random() * 2);
                if(sorteio == 0){
                    p1.atacar(p2);
                    System.out.println(p1.getNome() + " ataca " + p2.getNome());
                }
                else{
                    p2.atacar(p1);
                    System.out.println(p2.getNome() + " ataca " + p1.getNome());
                }
            }

        }

    }
}
