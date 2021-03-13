import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    public int lerInt(String message, String failMessage) {
        System.out.println(message);
        do {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(failMessage);
            } finally {
                scanner.nextLine();
            }
        } while (true);
    }

    public int[][] popularMatriz(int rowLength, int columnLength) {
        int[][] matriz = new int[rowLength][columnLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                matriz[i][j] = lerInt("Insira um valor para a posição " + (i + 1) + " x " + (j + 1),
                        "Insira um número por favor: ");
            }
        }
        return matriz;
    }

    private void calcularSomaDiagonais(int[][] matriz, int rowLength, int columnLength) {
        int soma = 0;
        int countDiagonal = 0;
        if (matriz != null) {
            if (columnLength == 1) {
                System.out.println("Uma matriz de uma coluna não há diagonais para somar portanto a soma é 0.");
                return;
            }
            if (rowLength == 1) {
                System.out.println("Uma matriz com uma linha a soma de cada uma de suas diagonais é o valor da própria coluna:");
                for (int i = 0; i < columnLength; i++) {
                    countDiagonal++;
                    System.out.println("Soma diagonal " + countDiagonal + ": " + matriz[0][i]);
                }
                return;
            }
            for (int column = 0; column < columnLength; column++) {
                for (int row = 0; row < rowLength; row++) {
                    if (row == 0 && column == 0) {
                        soma += matriz[row][column];
                        if (countDiagonal != 0 && countDiagonal % 2 == 0) {
                            column--;
                        } else {
                            column++;
                        }
                        continue;
                    }
                    soma += matriz[row][column];
                    if (countDiagonal != 0 && countDiagonal % 2 == 0) {
                        column++;
                    } else {
                        if (column > 0) {
                            column--;
                        }
                    }
                }
                countDiagonal++;
                System.out.println("Soma diagonal " + countDiagonal + ": " + soma);
                soma = 0;
                if (countDiagonal == columnLength) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int qtdLinhas = 0;
        int qtdColunas = 0;

        Main main = new Main();
        qtdLinhas = main.lerInt("Insira a quantidade de linhas da matriz:",
                "A quantidade de linhas deve ser um número.\nTente novamente:");
        qtdColunas = main.lerInt("Insira a quantidade de colunas da matriz:",
                "A quantidade de colunas deve ser um número.\nTente novamente:");

        int[][] matriz = main.popularMatriz(qtdLinhas, qtdColunas);
        main.calcularSomaDiagonais(matriz, qtdLinhas, qtdColunas);

    }

}
