import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Todos comentários realizados por minha pessoa a fim de facilitar a leitura do código.
// Algumas implementações foram feitas com auxilio de stackoverflow e ferramentas geradores de código 

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Escaneia o número de jogadores
    System.out.println("Informe o número de jogadores (entre 3 e 6): ");
    int numJogadores = scanner.nextInt();
    scanner.nextLine();

    // Cria lista com nomes dos jogadores e inicia loop para escanear nomes dos jogadores
    List<String> nomesJogadores = new ArrayList<>();
    for (int i = 1; i <= numJogadores; i++) {
      System.out.println("Informe o nome do jogador " + i + ": ");
      String nome = scanner.nextLine();
      nomesJogadores.add(nome);
    }

    // Inicia o jogo com os jogadores 
    Jogo jogo = new Jogo(nomesJogadores);
    jogo.iniciar();

    scanner.close();
  }
}