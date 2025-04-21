package src;

import java.util.Random;

public class Combate {
    private Jogador jogador;
    private Inimigo inimigo;
    private Random random;
    
    public Combate(Jogador jogador, Inimigo inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.random = new Random();
    }
    
    public boolean iniciar() {
        System.out.println("\n=== COMBATE INICIADO ===");
        System.out.println(jogador.getNome() + " vs " + inimigo.getNome() + "\n");
        
        // Mostrar status iniciais
        jogador.mostrarStatus();
        inimigo.mostrarStatus();
        
        // Definir ordem de ataque (50% de chance para cada)
        boolean jogadorComeca = random.nextBoolean();
        
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            if (jogadorComeca) {
                turnoJogador();
                if (inimigo.estaVivo()) {
                    turnoInimigo();
                }
            } else {
                turnoInimigo();
                if (jogador.estaVivo()) {
                    turnoJogador();
                }
            }
            
            jogador.resetarDefesa();
            if (inimigo instanceof Orc) {
                ((Orc)inimigo).setDefesa(8);
            } else {
                inimigo.setDefesa(5);
            }
        }
        
        if (jogador.estaVivo()) {
            System.out.println("\n=== VOCÊ VENCEU O COMBATE! ===");
            jogador.ganharExperiencia(inimigo.getExperienciaConcedida());
            return true;
        } else {
            System.out.println("\n=== VOCÊ FOI DERROTADO! ===");
            return false;
        }
    }
    
    private void turnoJogador() {
        System.out.println("\n=== SEU TURNO ===");
        int acao = jogador.escolherAcao();
        
        switch (acao) {
            case 1:
                jogador.atacar(inimigo);
                break;
            case 2:
                jogador.defender();
                break;
            case 3:
                jogador.usarPocao();
                break;
        }
    }
    
    private void turnoInimigo() {
        System.out.println("\n=== TURNO DO " + inimigo.getNome().toUpperCase() + " ===");
        int acao = inimigo.escolherAcao();
        
        switch (acao) {
            case 1:
                inimigo.atacar(jogador);
                break;
            case 2:
                if (inimigo instanceof Orc) {
                    ((Orc)inimigo).defender();
                } else {
                    System.out.println(inimigo.getNome() + " se prepara para se defender!");
                    inimigo.setDefesa(inimigo.getDefesa() + 3);
                }
                break;
            case 3:
                if (inimigo instanceof Orc) {
                    ((Orc)inimigo).curar();
                }
                break;
        }
    }
}