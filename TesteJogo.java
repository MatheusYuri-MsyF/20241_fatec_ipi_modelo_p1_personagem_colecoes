public class TesteJogo {
    public static void main(String[] args) {
        var p = new Personagem(10, 4, 2);
        System.out.println(p.toString());
        p.comer();
        System.out.println(p.toString());

        p.comer();
        System.out.println(p.toString());

        p.comer();
        System.out.println(p.toString());

        p.comer();
        System.out.println(p.toString());

        p.cacar();
        p.cacar();
        System.out.println(p.toString());

        p.comer();
        System.out.println(p.toString());

    }
}
