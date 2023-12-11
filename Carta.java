public class Carta {
  private int numero;

  // Construtor de uma carta, recebe como parãmetro o número da carta
  public Carta(int numero) {
    this.numero = numero;
  }

  public int getNumero() {
    return numero;
  }

  // Calcula a pontuacao da carta dado o numero dela
  public int calcularPontuacao() {
    int pontuacao = 1;

    if (numero % 10 == 5) {
      pontuacao += 1;
    }

    if (numero % 10 == 0) {
      pontuacao += 2;
    }

    String numeroString = String.valueOf(numero); // Converte o número para uma string
    if (numeroString.length() == 2 && numeroString.charAt(0) == numeroString.charAt(1)) {
      pontuacao += 4;
    } // Verifica se o número tem dois digitos e se eles são iguais

    return pontuacao;
  }
}