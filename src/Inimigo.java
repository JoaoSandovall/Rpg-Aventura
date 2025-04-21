package src;

public abstract class Inimigo extends Personagem {
    protected int experienciaConcedida;
    
    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int experienciaConcedida) {
        super(nome, pontosVida, ataque, defesa);
        this.experienciaConcedida = experienciaConcedida;
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
    
    public void defender() {
        System.out.println(this.nome + " assume postura defensiva!");
        this.defesa += 3;
    }
    
    public abstract int escolherAcao();
    
    public int getExperienciaConcedida() {
        return experienciaConcedida;
    }
}