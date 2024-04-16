import java.util.Scanner;

public class TesteJogo {
    public static void main(String[] args) {
        Personagem p1 = new Personagem(10, 2, 1);
        Personagem p2 = new Personagem(10, 2, 1);

        //         Criação de um Scanner :
        //-----------------------------------------
        Scanner leTeclado = new Scanner(System.in);
        //-----------------------------------------


        //      Mensagem de inicio do game  +  Aguardo de 5 segundos :
        //-----------------------------------------------------------------------
        System.out.println("Preparem-se o jogo vai começar em 5 segundos!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {e.printStackTrace();}
        //-----------------------------------------------------------------------


        while(true){

            // Ação do personagem 1
            System.out.println("Vez do(a): " + p1.nome + "\n");

            System.out.println("Digite:\n\n 1 para --> Cacar | Dormir | Comer \n\n 2 para --> Atacar o(a) " + p2.nome + "\n\n");
            String digiteP1 = leTeclado.nextLine();

            switch (digiteP1) {
            case "1":
                p1.realizarAcao(p1);
                break;
            case "2":
                //Implementar briga entre os bonecos
                break;
            }
            
            System.out.println(p1.toString());

    //--------------------------------------------------------------------------------------------------------------------

            // Ação do personagem 2
            System.out.println("Vez do(a): " + p2.nome + "\n");

            System.out.println("Digite:\n\n 1 para --> Cacar | Dormir | Comer \n\n 2 para --> Atacar o(a) " + p1.nome + "\n\n");
            String digiteP2 = leTeclado.nextLine();

            switch (digiteP2) {
                case "1":
                    p2.realizarAcao(p2);
                    break;
                case "2":
                    //Implementar briga entre os bonecos
                    break;
                }
            System.out.println(p2.toString());
        }

    }
}