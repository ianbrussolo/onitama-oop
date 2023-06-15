/**
 * Classe que contém informações das cartas
 */
public class Card {

    private String name;
    private Color color;
    private Position[] positions;

    /**
     * Construtor que define os principais atributos de uma cara
     * @param name Nome da carta
     * @param color Cor da carta
     * @param positions Todas as posições relativas de movimento
     */
    public Card(String name, Color color, Position[] positions) {
        this.name = name;
        this.color = color;
        this.positions = positions;
    }

    /**
     * Método que devolve o nome da carta
     * @return String que contém o nome da carta
     */
    public String getName() {
        return name;
    }



    /**
     * Método que devolve a cor da carta
     * @return Enum Color que contém a cor da carta
     */
    public Color getColor() {
        return color;
    }

    /**
     * Método que devolve todas as possíveis posições relativas de movimento.
     * A posição atual da peça é o ponto de origem (0,0). Uma carta possui as possíveis posições de movimento em relação ao ponto de origem.
     * @return Um array de Position contendo todas as possíveis posições de movimento em relação ao ponto de origem
     */
    public Position[] getPositions() {
        Position positions[] = new Position[13];

        positions[0] = new Position(1, 0);
        positions[1] = new Position(-1, 0);
        positions[2] = new Position(0, 1);
        positions[3] = new Position(0, -1);
        positions[4] = new Position(1, 1);
        positions[5] = new Position(1, -1);
        positions[6] = new Position(-1, 1);
        positions[7] = new Position(-1, -1);
        positions[8] = new Position(0, 2);
        positions[9] = new Position(0, -2);
        positions[10] = new Position(2, 0);
        positions[11] = new Position(1, 2);
        positions[12] = new Position(1, -2);

        return positions;
    }

    /**
     * Método que cria todas as cartas do jogo, embaralha-as e devolve as 5 que serão utilizadas na partida.
     * @return Vetor de cartas com todas as cartas do jogo
     */
    public static Card[] createCards() {
        



        
    }
