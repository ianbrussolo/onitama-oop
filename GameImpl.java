public class GameImpl implements Game {

    

    Color getSpotColor(Position position) {
        return position.getColor();
    }

    Piece getPiece(Position position) {

    }

    Card getTableCard() {

    }

    Player getRedPlayer() {

    }

    Player getBluePlayer() {

    }

    void makeMove(Piece piece, Card card, Position position) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException {

    }

    boolean checkVictory(Color color) {

    }

    void printBoard() {

    }
}
