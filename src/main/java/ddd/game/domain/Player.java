package ddd.game.domain;

import java.util.UUID;

public record Player(Player.Id id) {

    public record Id(UUID id) {
    }

}
