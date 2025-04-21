package src;

import java.util.Random;

public class Goblin extends Inimigo {
    public Goblin() {
        super("Goblin", 50, 8, 5, 30);
    }
    
    @Override
    public int escolherAcao() {
        Random random = new Random();
        // 70% chance de atacar, 30% chance de defender
        return random.nextInt(10) < 7 ? 1 : 2;
    }
    
    @Override
    public void atacar(Personagem alvo) {
        Random random = new Random();
        // 20% chance de ataque crítico
        if (random.nextInt(5) == 0) {
            int dano = this.ataque * 2;
            System.out.println(this.nome + " realiza um ataque crítico em " + alvo.getNome() + " causando " + dano + " de dano!");
            alvo.receberDano(dano);
        } else {
            super.atacar(alvo);
        }
    }
}
