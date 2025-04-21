package src;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("=== RPG DE TURNOS ===");
        System.out.println("Bem-vindo ao mundo de Eldoria!");
        System.out.print("Digite o nome do seu personagem: ");
        String nomeJogador = scanner.nextLine();
        
        Jogador jogador = new Jogador(nomeJogador);
        
        System.out.println("\nOlá, " + jogador.getNome() + "! Você é um aventureiro em busca do tesouro perdido");
        System.out.println("da Floresta Proibida. Mas cuidado, criaturas perigosas habitam esse lugar!\n");
        
        int combatesVencidos = 0;
        boolean continuarJogando = true;
        
        while (continuarJogando && jogador.estaVivo()) {
            System.out.println("\nVocê avança pela floresta...");
            esperarEnter(scanner);
            
            // 30% de chance de não encontrar inimigo
            if (random.nextInt(10) < 3) {
                System.out.println("Nenhum inimigo à vista. Você continua sua jornada...");
                continue;
            }
            
            Inimigo inimigo;
            if (random.nextBoolean()) {
                inimigo = new Goblin();
            } else {
                inimigo = new Orc();
            }
            
            System.out.println("Um " + inimigo.getNome() + " selvagem aparece!");
            
            Combate combate = new Combate(jogador, inimigo);
            boolean vitoria = combate.iniciar();
            
            if (vitoria) {
                combatesVencidos++;
                
                if (combatesVencidos % 3 == 0) {
                    System.out.println("\nVocê encontrou uma poção de cura!");
                    System.out.println("(Agora você pode usar poções durante o combate)");
                }
                
                System.out.println("\nDeseja continuar explorando a floresta? (s/n)");
                String escolha = scanner.next();
                continuarJogando = escolha.equalsIgnoreCase("s");
            } else {
                continuarJogando = false;
            }
        }
        
        if (jogador.estaVivo()) {
            System.out.println("\n=== FIM DA JORNADA ===");
            System.out.println(jogador.getNome() + " retorna à cidade após " + combatesVencidos + " combates vencidos!");
            System.out.println("Nível final: " + jogador.getNivel());
        } else {
            System.out.println("\n=== GAME OVER ===");
            System.out.println("Sua jornada termina aqui...");
        }
        
        scanner.close();
    }
    
    private static void esperarEnter(Scanner scanner) {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
}