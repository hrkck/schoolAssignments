package kingsheep.team.hkuecu;

import kingsheep.*;


public class Wolf extends UzhShortNameCreature {

    public Wolf(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);
    }

    protected void think(Type map[][]) {

        Node myself = objective(map, "WOLF1").get(0);
        Node sheep = objective(map, "SHEEP2").get(0);

        Node targetNode = (Node) aStar(map, myself, sheep, false);
        move = getMove(myself, targetNode);
    }
}
