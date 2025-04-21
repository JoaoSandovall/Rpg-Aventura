package src;

import java.util.Random;

public class Orc extends Inimigo {
    public Orc() {
        super("Orc", 80, 12, 8, 50);
    }
    
    @Override
    public int escolherAcao() {
        Random random = new Random();
        int escolha = random.nextInt(10);
        return escolha < 6 ? 1 : (escolha < 9 ? 2 : 3);
    }
    
    @Override
    public void defender() {
        super.defender();  // Executa a defesa básica da classe Inimigo
        this.defesa += 5;  // Bônus adicional para o Orc
        System.out.println("O Orc reforça sua defesa!");
    }
    
    public void curar() {
        int cura = (int)(this.pontosVidaMaximos * 0.2);
        this.pontosVida = Math.min(this.pontosVida + cura, this.pontosVidaMaximos);
        System.out.println(this.nome + " se cura em " + cura + " pontos de vida!");
    }
}