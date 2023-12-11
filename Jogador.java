import java.util.ArrayList;
import java.util.List;

public class Jogador {
  private String nome;
  private List<Carta> mao;
  private List<Carta> cartasColetadas;

  // Construtor de um jogador, coloca o nome ao jogador, a lista de cartas e lista de cartas coletadas para contar na pontuação final
  public Jogador(String nome) {
    this.nome = nome;
    this.mao = new ArrayList<>();
    this.cartasColetadas = new ArrayList<>();
  }

  public String getNome() {
    return nome;
  }

  public List<Carta> getMao() {
    return mao;
  }

  public List<Carta> getCartasColetadas() {
    return cartasColetadas;
  }

  public void adicionarCartaNaMao(Carta carta) {
    mao.add(carta);
  }

  public void removerCartaDaMao(Carta carta) {
    mao.remove(carta);
  }

  public void coletarCartas(List<Carta> cartas) {
    cartasColetadas.addAll(cartas);
  }

  public void imprimirMao() {
    System.out.println("\nMão de " + nome + ":\n");
    for (Carta carta : mao) {
      System.out.print(carta.getNumero() + " ");
    }
    System.out.println();
  }
}