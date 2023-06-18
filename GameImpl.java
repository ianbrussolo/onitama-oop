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

    public void makeMove(Card card, Position cardMove, Position currentPos)
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

        // verifica se a peca a ser movida é valida
        if (!board[currentPos.getRow()][currentPos.getCol()].getPiece().Alive()) {
            throw new InvalidPieceException("Peça fora do tabulero");
        }

        int movRow = cardMove.getRow();
        int movCol = cardMove.getCol();

        // mivimentaçao das pecas azuis sao invertidas no tabuleiro
        if (currentPlayer.getPieceColor().equals(Color.BLUE)) {
            movRow = movRow * (-1);
            movCol = movCol * (-1);
        }

        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();

        int destRow = currentRow + movRow;
        int destCol = currentCol + movCol;

        if ((destRow < 0 || destRow > 4) || (destCol < 0 || destCol > 4)) {
            throw new IllegalMovementException("Movimento excede o tabuleiro");
        }

        // Movimentacao da peca o captura
        Spot finalSpot = board[destRow][destCol];

        finalSpot.occupySpot(board[destRow][destCol].getPiece());

        // libera o espaço antigo
        board[currentPos.getRow()][currentPos.getCol()].releaseSpot();

        // altera a vez do jogador
        redTurn = !redTurn;

        return;

    }

    public boolean checkVictory(Color color) {

        // Verificar se o jogador da cor especificada possui o mestre do oponente
        Player currentPlayer = color.equals(Color.RED) ? redPlayer : bluePlayer;
        Player opponentPlayer = color.equals(Color.RED) ? bluePlayer : redPlayer;

        // Verificar se o mestre do jogador da cor especificada está no templo oposto
        if (currentPlayer.getPieceColor().equals(Color.RED)) {

            if (board[0][2].getPiece().isMaster() || board[0][2].getColor().equals(Color.RED))
                return true; // O mestre do vermelho esta no templo azul
        } else {
            if (board[4][2].getPiece().isMaster() || board[4][2].getColor().equals(Color.BLUE))
                return true; // O mestre do azul esta no templo vermelho
        }

        boolean masterAlive = false;
        boolean thereIsOpponent = false;

        // Verificar se todas as peças do oponente estão capturadas ou bloqueadas
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Piece piece = board[row][col].getPiece();
                if (piece != null) {
                    if (piece.getColor() == opponentPlayer.getPieceColor()) {
                        thereIsOpponent = true; // Ainda existem peças do oponente no tabuleiro
                    }
                    if (piece.isMaster()) {
                        masterAlive = true; // mestre esta vivo
                    }
                }
            }

        }
        // Todas as condições de vitória foram atendidas
        if ((!masterAlive) || (!thereIsOpponent))
            return true;

        return false;
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Spot spot = board[row][col];
                Piece piece = spot.getPiece();

                if (piece != null) {
                    System.out.print(piece.getColor() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println(); // Move to the next line for the next row
        }
    }
}