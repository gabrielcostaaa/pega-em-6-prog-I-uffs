import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabuleiro {
  private List<List<Carta>> linhas;
  private List<Jogador> jogadores;

  // Construtor do tabuleiro, recebe como parametro a lista de jogadores, cria o tabuleiro e atribui os jogadores do parametro aos jogadores do tabuleiro
  public Tabuleiro(List<Jogador> jogadores) {
    this.linhas = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      linhas.add(new ArrayList<>());
    }
    this.jogadores = jogadores;
  }

  public List<List<Carta>> getLinhas() {
    return linhas;
  }

  // Função que posiciona a carta no tabuleiro dada as regras do jogo
  public void posicionarCarta(Carta carta) {
    int linhaParaPosicionar = -1;
    int menorDiferenca = Integer.MAX_VALUE; //valor máximo que um inteiro de 32 bits pode armazenar, ter certeza de que qualquer valor que você comparar inicialmente será menor do que Integer.MAX_VALUE

    for (int i = 0; i < linhas.size(); i++) { // Loop que percorre as linhas do tabuleiro
      List<Carta> linhaAtual = linhas.get(i); // Atualiza linha do tabuleiro

      if (linhaAtual.isEmpty()) { // Verifica se a linha está vazia, caso sim ela é a linha para posicionar a carta
        linhaParaPosicionar = i; // Atualiza a linha para posicionar a carta
        break;
      }

      int diferenca = carta.getNumero() - linhaAtual.get(linhaAtual.size() - 1).getNumero(); // Calcula a diferença entre a carta atual e a última carta da linha atual

      if (diferenca > 0 && diferenca < menorDiferenca) { // Verifica se a diferença é maior que 0 e menor que a menor diferença encontrada até então
        menorDiferenca = diferenca; // Atualiza a menor diferença encontrada até então
        linhaParaPosicionar = i; // Atualiza a linha para posicionar a carta
      }
    }

    if (linhaParaPosicionar == -1) {
      // Todas as linhas estão cheias, escolher a linha com a maior última carta
      int maiorUltimaCarta = Integer.MIN_VALUE; // Valor mínimo que um inteiro de 32 bits pode armazenar, ter certeza de que qualquer valor que você comparar inicialmente será maior do que Integer.MIN_VALUE

      for (int i = 0; i < linhas.size(); i++) { // Loop que percorre as linhas do tabuleiro
        int ultimaCarta = linhas.get(i).get(linhas.get(i).size() - 1).getNumero(); // Atualiza a última carta da linha atual
        if (ultimaCarta > maiorUltimaCarta) { // Verifica se a última carta é maior que a maior última
          maiorUltimaCarta = ultimaCarta; // Atualiza a maior última carta
          linhaParaPosicionar = i; // Atualiza a linha para posicionar a carta
        }
      }

      // Coletar todas as cartas da linha escolhida
      List<Carta> cartasColetadas = new ArrayList<>(linhas.get(linhaParaPosicionar));
      for (Jogador jogador : jogadores) {
          jogador.coletarCartas(cartasColetadas);
      }
      linhas.get(linhaParaPosicionar).clear();
    }

    // Adicionar a carta na linha escolhida
    linhas.get(linhaParaPosicionar).add(carta);
  }
}
