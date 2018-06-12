package kingsheep.team.hkuecu;

import kingsheep.Simulator;
import kingsheep.Type;

import java.util.ArrayList;

public class Sheep extends UzhShortNameCreature {

    public Sheep(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);
    }

    protected void think(Type map[][]) {
        /*
        EMPTY('.'), GRASS('g'), FENCE('#'), RHUBARB('r'),
        SHEEP1('1'), SHEEP2('3'), WOLF1('2'), WOLF2('4')
        */
        Node mySelf = objective(map, "SHEEP1").get(0);
        Node foeWolf = objective(map, "WOLF2").get(0);
        ArrayList<Node> rhubarbes = objective(map, "RHUBARB");
        ArrayList<Node> grasses = objective(map, "GRASS");

        Node mostSecureRhubarb = returnMostSecureNode(map, foeWolf, rhubarbes);
        Node mostSecureGrass = returnMostSecureNode(map, foeWolf, grasses);


        double distanceFromWolf = (int) aStar(map, mySelf, foeWolf, true);

        if (distanceFromWolf > 3) {                     // Objective Zero:   Survive
            if (doesNodeExist(mostSecureRhubarb)) {     // First objective:  Rhubarb
                if (doesNodeExist(mostSecureGrass)) {   // Second objective: Grass
                    move = Move.WAIT;
                } else {    // Second objective: Grass
                    Node targetNode = (Node) aStar(map, mySelf, mostSecureGrass, false);
                    move = getMove(mySelf, targetNode);
                }
            } else {     // First objective:  Rhubarb
                Node targetNode = (Node) aStar(map, mySelf, mostSecureRhubarb, false);
                move = getMove(mySelf, targetNode);
            }
        } else {     // Objective Zero:   Survive
            Node backFront = drawBack(map, mySelf, foeWolf, 5);
            move = getMove(mySelf, backFront);
        }
    }

    /**
     * This function is specific to the Sheep.
     * It identifies the location of the enemy wolf and according to the given parameters,
     * it constructs a circle of safe square.
     * Then, it returns the easiest safe square to run.
     * That makes the Sheep player intensely focused on running away from enemy Wolf, rather than feeding itself.
     *
     * @param map Grid-based game map.
     * @param startNode Location of the Sheep itself.
     * @param dangerNode The location to avoid. Enemy wolf in this case.
     * @param requiredDistance A user specified parameter to tell the Sheep how far away it should run.
     * @return The safest and the easiest Node to go.
     */
    private Node drawBack(Type map[][], Node startNode, Node dangerNode, int requiredDistance) {
        /*
        4 units of distance from center point X, calculated according to Manhattan Distance. [EXAMPLE]
        ........4..........
        .......434.........
        ......43234........
        .....4321234.......
        ....4321X1234......
        .....4321234.......
        ......43234........
        .......434.........
        ........4..........
        */

        // Dangerous coordinates
        int dX = dangerNode.getX();
        int dY = dangerNode.getY();
        int[][] c = new int[][]{{1, 1}, {-1, 1}, {1, -1}, {-1, -1}}; // coefficients

        // Below, it finds the nodes distant enough to be safe AND closest to the startNode
        double minimum = Double.MAX_VALUE;
        Node selectedNode = startNode; // For sake of initializing it

        for (int[] coef : c) {
            for (int delta = 0; delta <= requiredDistance; delta++) {
                // Get nodes distant enough to be safe
                int safeX = dX + coef[0] * Math.abs(delta);
                int safeY = dY + coef[1] * Math.abs(requiredDistance + 1 - delta);
                Square square = new Square(safeX, safeY);
                Node node = new Node(square, null, returnType(map, square));
                if (isItInvalid(map, node)) {
                    continue;
                }
                // Choose easiest node to flee
                if (minimum > diagonalDistance(startNode, node)) {
                    minimum = diagonalDistance(startNode, node);
                    selectedNode = node;
                }

                // VERY DANGEROUS!
                // Uncomment on your own risk!
                // With this, the sheep focuses more on eating even while escaping from the wolf...
                // if(returnType(map, square) == Type.GRASS || returnType(map, square) == Type.RHUBARB){
                //    selectedNode = node;
                //    break;
                // }
            }
        }
        // Now find the path to that selectedNode
        if (selectedNode != startNode){
            Node thePath = (Node) aStar(map, startNode, selectedNode, false);
            return thePath;
        } else{
            return selectedNode;
        }
    }
}
