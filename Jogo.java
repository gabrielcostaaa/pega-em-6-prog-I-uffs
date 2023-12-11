import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Jogo {
  private List<Jogador> jogadores;
  private List<Carta> baralho;
  private Tabuleiro tabuleiro;

  // Construtor do jogo
  public Jogo(List<String> nomesJogadores) {

    // Inicializa a lista de jogadores e adiciona os jogadores nesta lista com a lista dos nomes parametrizada no construtor
    this.jogadores = new ArrayList<>();
    for (String nome : nomesJogadores) {
      jogadores.add(new Jogador(nome));
    }

    // Inicializa o baralho das cartas e embaralha
    this.baralho = new ArrayList<>();
    for (int i = 1; i <= 109; i++) {
      baralho.add(new Carta(i));
    }
    Collections.shuffle(baralho);

    // Inicializa o tabuleiro com a lista jogadores de parametro
    this.tabuleiro = new Tabuleiro(jogadores);
  }

  // Função que distribui as cartas para cada jogador
  public void distribuirCartas() {
    for (Jogador jogador : jogadores) {
      for (int i = 0; i < 12; i++) {
        Carta carta = baralho.remove(0); // Remove a carta do topo do baralho
        jogador.adicionarCartaNaMao(carta);
      }
    }
  }

  // Função que inicia o jogo
  public void iniciar() {

    distribuirCartas(); // Distribui as cartas para cada jogador

    for (int rodada = 1; rodada <= 12; rodada++) { // Loop das 12 rodadas
      System.out.println("\nRodada " + rodada + "\n");
      
      exibirTabuleiro();
      exibirPontuacao();
      
      for (Jogador jogador : jogadores) { // Para cada jogador dentro de jogadores


        jogador.imprimirMao(); // Imprime mão do jogador da vez

        System.out.println(jogador.getNome() + ", escolha uma carta para jogar: ");
        int escolha = obterEscolha(jogador); // Atribui a escolha do jogador a variavel escolha
        Carta cartaEscolhida = jogador.getMao().get(escolha); // Vai na mão do jogador e procura pela escolha, pegando a carta da escolha e atribuindo a esta variável
        jogador.removerCartaDaMao(cartaEscolhida); // Remove carta escolhida
        tabuleiro.posicionarCarta(cartaEscolhida); // Posiciona carta escolhida no tabuleiro -essa função foi chata de fazer -
      }
      coletarCartas();
    }
    exibirResultadoFinal();
  }

  private void coletarCartas() {
    List<List<Carta>> linhas = tabuleiro.getLinhas();

    for (List<Carta> linha : linhas) {
      if (linha.size() == 5) {
        int maiorNumero = linha.get(4).getNumero();
        Jogador jogador = null;

        for (Jogador j : jogadores) {
          if (j.getMao().size() > 0 && j.getMao().get(0).getNumero() > maiorNumero) {
            maiorNumero = j.getMao().get(0).getNumero();
            jogador = j;
          }
        }

        if (jogador != null) {
          System.out.println(jogador.getNome() + " coletou as cartas da linha " + (linhas.indexOf(linha) + 1));
          jogador.coletarCartas(linha);
          linha.clear();
        }
      }
    }
  }
  // Função para exibit o tabuleiro nas rodadas
  private void exibirTabuleiro() {
    List<List<Carta>> linhas = tabuleiro.getLinhas();
    for (int i = 0; i < linhas.size(); i++) {
      System.out.print("Linha " + (i + 1) + ": ");
      for (Carta carta : linhas.get(i)) {
        System.out.print(carta.getNumero() + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  // Função para exibir a pontuação dos jogadores durante rodadas
  private void exibirPontuacao() {
    for (Jogador jogador : jogadores) {
      System.out.println(jogador.getNome() + "-> Pontuação: " + calcularPontuacao(jogador) + " pontos\n");
    }
    System.out.println();
  }

  // Função que obtem escolha da carta a ser jogada com a exceção de ser entre 0 e o tamanho da mão do jogador
  private int obterEscolha(Jogador jogador) {
    Scanner scanner = new Scanner(System.in);
    int escolha;

    do {
      System.out.print("\nEscolha uma carta (0 a " + (jogador.getMao().size() - 1) + "): \n");
      escolha = scanner.nextInt();
    } while (escolha < 0 || escolha >= jogador.getMao().size());

    return escolha;
  }


  // Função que exibe o resultado no final do jogo
  private void exibirResultadoFinal() {
    int PontosGanhador = Integer.MAX_VALUE;
    String NomeGanhador = "";
    System.out.println("\n---- Resultado Final ----\n");
    for (Jogador jogador : jogadores) {
      System.out.println(jogador.getNome() + ": Pontuação | " + calcularPontuacao(jogador));
      if (PontosGanhador > calcularPontuacao(jogador)){
        PontosGanhador = calcularPontuacao(jogador);
        NomeGanhador = jogador.getNome();
      }
      System.out.println();
    }
    System.out.println("O ganhador foi " + NomeGanhador);
  }

  // Função que calcula a pontuação de um jogador
  private int calcularPontuacao(Jogador jogador) {
    int pontuacao = 0;

    for (Carta carta : jogador.getCartasColetadas()) {
      pontuacao += carta.calcularPontuacao();
    }

    return pontuacao;
  }
}