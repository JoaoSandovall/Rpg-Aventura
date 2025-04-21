package src;

import java.util.Scanner;

public class Jogador extends Personagem {
    private int experiencia;
    private int experienciaProximoNivel;
    private Scanner scanner;
    
    public Jogador(String nome) {
        super(nome, 100, 15, 10);
        this.experiencia = 0;
        this.experienciaProximoNivel = 100;
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public void atacar(Personagem alvo) {
        int dano = this.ataque;
        System.out.println(this.nome + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public void receberDano(int dano) {
        int danoRecebido = Math.max(dano - this.defesa / 2, 1);
        this.pontosVida -= danoRecebido;
        System.out.println(this.nome + " recebe " + danoRecebido + " de dano!");
        
        if (!estaVivo()) {
            System.out.println(this.nome + " foi derrotado!");
        }
    }
    
    public void ganharExperiencia(int quantidade) {
        this.experiencia += quantidade;
        System.out.println(this.nome + " ganhou " + quantidade + " pontos de experiência!");
        
        if (this.experiencia >= this.experienciaProximoNivel) {
            subirNivel();
        }
    }
    
    private void subirNivel() {
        this.nivel++;
        this.experiencia -= this.experienciaProximoNivel;
        this.experienciaProximoNivel = (int)(this.experienciaProximoNivel * 1.5);
        
        this.pontosVidaMaximos += 20;
        this.pontosVida = this.pontosVidaMaximos;
        this.ataque += 5;
        this.defesa += 3;
        
        System.out.println("----------------------------------");
        System.out.println("PARABÉNS! " + this.nome + " subiu para o nível " + this.nivel + "!");
        System.out.println("Vida máxima aumentada para " + this.pontosVidaMaximos);
        System.out.println("Ataque aumentado para " + this.ataque);
        System.out.println("Defesa aumentada para " + this.defesa);
        System.out.println("----------------------------------");
    }
    
    public int escolherAcao() {
        System.out.println("\nO que você deseja fazer?");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender (reduz dano recebido em 50% no próximo turno)");
        System.out.println("3 - Usar poção de cura (restaura 30% da vida máxima)");
        
        int escolha = scanner.nextInt();
        while (escolha < 1 || escolha > 3) {
            System.out.println("Escolha inválida. Digite 1, 2 ou 3:");
            escolha = scanner.nextInt();
        }
        
        return escolha;
    }
    
    public void defender() {
        System.out.println(this.nome + " assume uma postura defensiva!");
        this.defesa += 5;
    }
    
    public void usarPocao() {
        int cura = (int)(this.pontosVidaMaximos * 0.3);
        this.pontosVida = Math.min(this.pontosVida + cura, this.pontosVidaMaximos);
        System.out.println(this.nome + " usou uma poção e recuperou " + cura + " pontos de vida!");
    }
    
    public void resetarDefesa() {
        this.defesa = 10 + (this.nivel - 1) * 3;
    }
}