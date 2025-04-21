package src;

public abstract class Personagem {
    protected String nome;
    protected int nivel;
    protected int pontosVida;
    protected int pontosVidaMaximos;
    protected int ataque;
    protected int defesa;
    
    public Personagem(String nome, int pontosVida, int ataque, int defesa) {
        this.nome = nome;
        this.nivel = 1;
        this.pontosVida = pontosVida;
        this.pontosVidaMaximos = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
    }
    
    public abstract void atacar(Personagem alvo);
    public abstract void receberDano(int dano);
    
    public boolean estaVivo() {
        return pontosVida > 0;
    }
    
    public void mostrarStatus() {
        System.out.println("----------------------");
        System.out.println(nome + " (Nível " + nivel + ")");
        System.out.println("Vida: " + pontosVida + "/" + pontosVidaMaximos);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("----------------------");
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getPontosVida() { return pontosVida; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    
    public void setPontosVida(int pontosVida) { this.pontosVida = pontosVida; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefesa(int defesa) { this.defesa = defesa; }
}