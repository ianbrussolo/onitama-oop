public class GameImpl implements Game {

    private Player redPlayer; // jogador das cartas vermelhas
    private Player bluePlayer; // jogador das cartas azuis
    private Spot[][] board; // matriz de posicoes
    private Card tableCard; // carta da mesa
    private Card[] cards; // cartas no jogo

    private boolean redTurn;

    /**
     * Construtor que inicia o jogo com as informações básicas
     */
    public GameImpl() {
        this.board = Spot.createBoard(5);
        this.cards = Card.createCards();

        this.redPlayer = new Player("Red", Color.RED, this.cards[0], this.cards[1]);
        this.bluePlayer = new Player("Blue", Color.BLUE, this.cards[2], this.cards[3]);

        this.tableCard = this.cards[4];

        if (this.cards[4].getColor().equals(Color.RED)) {
            redTurn = true;
        } else
            redTurn = false;

    }

    public Color getSpotColor(Position position) {
        return this.board[position.getRow()][position.getCol()].getColor();
    }

    public Piece getPiece(Position position) {
        return this.board[position.getRow()][position.getCol()].getPiece();

    }

    public Card getTableCard() {
        return tableCard;

    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getBluePlayer() {
        return bluePlayer;
    }

    /**
     * Método que move uma peça
     * 
     * @param card       A carta de movimento que será usada
     * 
     * @param cardMove   Para onde ele irá movimentar
     * 
     * @param currentPos A posição da peça que ira ser movida
     * 
     * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador
     *                                        fazer um movimento
     * @exception IllegalMovementException    Caso uma peça seja movida para fora do
     *                                        tabuleiro ou para uma posição onde já
     *                                        tem uma peça da mesma cor
     * @exception InvalidCardException        Caso uma carta que não está na mão do
     *                                        jogador seja usada
     * @exception InvalidPieceException       Caso uma peça que não está no
     *                                        tabuleiro seja usada
     */

    void makeMove(Card card, Position cardMove, Position currentPos)
            throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException {

        // Verificar qual o jogador
        Player currentPlayer = redTurn ? redPlayer : bluePlayer;

        // verifica se é o jogador da vez

        if (!board[currentPos.getRow()][currentPos.getCol()].getColor().equals(currentPlayer.getPieceColor())) {
            throw new IncorrectTurnOrderException("Não é a vez desse jogador.");
        }

        // verifica se a carta é valida
        if (!card.equals(currentPlayer.getCards()[0]) || !card.equals(currentPlayer.getCards()[1])) {
            throw new InvalidCardException("O jogador nao possui essa carta");
        }

        // //peca invalida
        // if(!board[currentPos.getRow()][currentPos.getCol()].getPiece().getColor().equals(currentPlayer.getPieceColor())){
        // throw new
        // }

        // verifica se a peca a ser movida é valida
        if (!board[currentPos.getRow()][currentPos.getCol()].getPiece().Alive()) {
            throw new InvalidPieceException("Peça fora do tabulero");
        }

        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();

        int destRow = currentRow + cardMove.getRow();
        int destCol = currentCol + cardMove.getCol();

        if ((destRow < 0 || destRow > 4) || (destCol < 0 || destCol > 4)) {
            throw new IllegalMovementException("O jogador nao possui essa carta");
        }

        // SO MOVER COM O CAPTURE
        Piece currentPiece = board[currentPos.getRow()][currentPos.getCol()].getPiece();
        Spot oldSpot = board[currentRow][currentCol];

        board[destRow][destCol].occupySpot(currentPiece);
        oldSpot.releaseSpot();
    }

    

    boolean checkVictory(Color color) {

        // mestre ganha
    }

    void printBoard() {

    }
}
