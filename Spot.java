/**
 * Classe contendo ações e informações sobre cada espaço (quadrado) no tabuleiro
 * 
 * Obs que há uma sobrecarga de métodos spot, para criar espaço com peça e
 * cor(templo), posicao com peça e sem cor e posicao sem peca e sem cor
 */
public class Spot {

    private Piece piece; // conteins color, master, alive
    private Position pos;
    private Color color;

    /**
     * Construtor para espaços com peça e com cor
     * 
     * @param piece Peça que inicia nesse espaço do tabuleiro
     * @param pos   Posição do espaço no tabuleiro
     * @param color Cor do espaço no tabuleiro (Templo)
     */
    public Spot(Piece piece, Position pos, Color color) {
        this.piece = piece;
        this.pos = pos;
        this.color = color;
    }

    /**
     * Construtor para espaços com peça e sem cor
     * 
     * @param piece Peça que inicia nesse espaço do tabuleiro
     * @param pos   Posição do espaço no tabuleiro
     */
    public Spot(Piece piece, Position pos) {
        this.piece = piece;
        this.pos = pos;
    }

    /**
     * Construtor para espaços sem peça e sem cor
     * 
     * @param pos Posição do espaço no tabuleiro
     */
    public Spot(Position pos) {
        this.pos = pos;
    }

    /**
     * Método que devolve a posição (coordenadas) do espaço
     * 
     * @return Objeto Position contendo a posição (coordenadas) do espaço
     */
    public Position getPosition() {
        return pos;
    }

    /**
     * Método que devolve a peça contida neste espaço
     * 
     * @return Objeto Piece caso tenha uma peça ou null caso o espaço esteja vazio
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Método que devolve a cor do espaço
     * 
     * @return Enum Color com a cor do espaço. Caso o espaço não tenha cor, o valor
     *         do enum será NONE
     */
    public Color getColor() {
        return color;
    }

    // /**
    // * Método que verifica se a posição é válida no tabuleiro
    // *
    // * @return Um booleano que indica se o objeto representa uma posição válida no
    // * tabuleiro
    // */
    // public boolean isValid() {

    // int row = pos.row;
    // int col = pos.col;

    // // Verifica se as coordenadas estão dentro dos limites do tabuleiro
    // retangular
    // // 5x5

    // if (row >= 0 && row < 5 && col >= 0 && col < 5) {
    // return true;
    // } else {
    // return false;
    // }

    // }

    /**
     * Método que ocupa o espaço atual com a peça passada
     * 
     * @param piece A peça para ocupar este espaço
     * @exception IllegalMovementException Caso o espaço já esteja ocupado por uma
     *                                     peça da mesma cor
     */
    protected void occupySpot(Piece piece) throws IllegalMovementException {
        // ocupado //peça a ser ocupada da mesma cor
        if (this.piece != null) {
            if (piece.getColor() == this.piece.getColor()) {
                throw new IllegalMovementException("O espaço já está ocupado por uma peça da mesma cor.");
                //captura de peca
            } else{
              this.piece.capturePiece();
            }
            // local apto a ser ocupado
            this.piece = piece;
        }
    }

    /**
     * Método que "libera" o espaço atual, ou seja, deixa-o vazio
     */
    protected void releaseSpot() {
        this.piece = null;
        this.color = Color.NONE;
    }
}