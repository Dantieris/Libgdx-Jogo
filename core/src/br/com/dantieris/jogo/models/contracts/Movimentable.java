package br.com.dantieris.jogo.models.contracts;

import br.com.dantieris.jogo.models.Direction;

public interface Movimentable {
    void setVerticalSpeed(int verticalSpeed);
    int getVerticalSpeed();
    void setHorizontalSpeed(int horizontalSpeed);
    int getHorizontalSpeed();
    void setDirection(Direction direction);
    Direction getDirection();
}
