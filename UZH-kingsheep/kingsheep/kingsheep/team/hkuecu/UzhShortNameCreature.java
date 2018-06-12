package kingsheep.team.hkuecu;

import kingsheep.Creature;
import kingsheep.Simulator;
import kingsheep.Type;

import java.util.ArrayList;
import java.util.Collections;

public abstract class UzhShortNameCreature extends Creature {

    protected UzhShortNameCreature(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);
    }


    /**
     * A Star Algorithm to find a path between two given coordinate points (Nodes) in a grid-based Map.
     * It is based on diagonal distance calculation, which result in zig-zag shaped paths.
     * It returns only the first step (First Node) of the discovered path. Since the game is turn-based, no full
     * path is needed.
     * If no solution is found, it returns the starting point, which leaves the player nothing to do but wait.
     *
     * @param map Grid-based game map.
     * @param startNode Starting point. Must be Node object.
     * @param goalNode Target point. Must be a Node object.
     * @return Only the initial step (Node object) of the discovered path.
     */
    protected Object aStar(Type map[][], Node startNode, Node goalNode, boolean returnDistance) {
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();
        openList.add(startNode);

        Node currentNode;
        while (!openList.isEmpty()) {
            currentNode = returnLowestFScore(openList);
            openList.remove(currentNode);
            closedList.add(currentNode);

            // if a solution is found
            if (currentNode.compareCoordinates(goalNode)) {
                goalNode.setParentNode(currentNode.getParentNode());
                break;
            }
            for (Node neighbor : currentNode.returnNeighbors(map)) {
                if (containsNode(closedList, neighbor) || isItInvalid(map, neighbor)) {
                    continue;
                }
                if (!containsNode(openList, neighbor)) {
                    // Construct a path
                    neighbor.setAllCosts(neighbor.costSoFar(), diagonalDistance(neighbor, goalNode));
                    openList.add(neighbor);
                }
                if (containsNode(openList, neighbor)) {
                    neighbor = returnNode(openList, neighbor);
                    if (neighbor.getGDistance() < currentNode.getGDistance()) {
                        // Re-construct the path if a better one is found
                        neighbor.setParentNode(currentNode.getParentNode());
                        neighbor.setAllCosts(neighbor.costSoFar(), diagonalDistance(neighbor, goalNode));
                    }
                }
            }
        }
        // if no solution found !BOUNDED TO DEATH!
        if (openList.isEmpty()){
            return constructPath(startNode, returnDistance);
        }
        return constructPath(goalNode, returnDistance);
    }

    /**
     * Given a "type" name as String and the grid-based map, it arranges necessary object creation schedule,
     * and returns all discovered coordinates with the specified type, as handy Node objects.
     *
     * @param map Grid-based game map.
     * @param type Name of a Type object as String literal.
     * @return An ArrayList of all coordinates with given type, as Node objects.
     */
    protected ArrayList<Node> objective(Type map[][], String type) {
        ArrayList<Square> objectiveSquares = locateType(map, type);
        ArrayList<Node> objectiveNodes = new ArrayList<>();
        for (Square item : objectiveSquares) {
            objectiveNodes.add(new Node(item, null, returnType(map, item)));
        }
        return objectiveNodes;
    }

    /**
     * Given two Node objects, this function returns the required direction of movement to get from one to the other.
     * It returns the direction as a Move object, so its return can be used directly to move the player.
     * Those Node objects has to be adjacent. Since in each turn one step is taken by the player, the case where the nodes are not
     * adjacent does not sense.
     * The coordinates systems conventions written by me is a mess. That's why the directions might now make sense. So one has to try to ignore
     * it when reading this function. (But it is working!)
     *
     * @param start Current Node.
     * @param target Target Node.
     * @return The required step as a Move object.
     */
    protected Move getMove(Node start, Node target) {
        int tx = target.getX();
        int ty = target.getY();

        int sx = start.getX();
        int sy = start.getY();

        Move move = Move.WAIT;

        if (sx == tx && sy - 1 == ty) {
            move = Move.LEFT;
        }
        if (sx == tx && sy + 1 == ty) {
            move = Move.RIGHT;
        }
        if (sx - 1 == tx && sy == ty) {
            move = Move.UP;
        }
        if (sx + 1 == tx && sy == ty) {
            move = Move.DOWN;
        }
        return move;
    }

    /**
     * This function is dramatically important for the Sheep to go for the secure food.
     * It calculates the distance between the enemy Wolf and the given List object
     * and returns the most distant object in the List. This is considered as the most secure food to go.
     *
     * @param dangerNode The Node to avoid.
     * @param nodes List of Nodes to evaluate.
     * @return The most distant Node object in the List.
     */
    protected Node returnMostSecureNode(Type[][] map, Node dangerNode, ArrayList<Node> nodes) {
        double maximum = Double.MIN_VALUE;
        Node mostSecureNode = new Node(new Square(-1, -1), null, null);
        for (Node node : nodes) {
            double distant = (int) aStar(map, dangerNode, node, true);
            if (maximum < distant) {
                maximum = distant;
                mostSecureNode = node;
            }
        }
        return mostSecureNode;
    }

    /**
     * This function traces back any given Node object, taking into account their parent Nodes.
     * When a parent Node finally returns a null parent, the function terminates.
     * Sometimes the given initial Node has no Parent and this incident yields to and exception. See below how it is handled.
     * It also reverses the path, in order to be able to return the start to end path, since it constructs the path from the end.
     * This behaviour can be understood by A Star algorithm's implementation.
     *
     * @param endNode The Node to trace back to its root.
     * @return The final Node reached.
     */
    protected Object constructPath(Node endNode, boolean returnDistance) {
        ArrayList<Node> thePath = new ArrayList<>();
        thePath.add(endNode);

        Node parentNode = endNode.getParentNode();
        while (parentNode != null) {
            thePath.add(parentNode);
            parentNode = parentNode.getParentNode();
        }
        Collections.reverse(thePath);
        if(returnDistance){
            return thePath.size();
        }else{
            try {
                return thePath.get(1);
            } catch (IndexOutOfBoundsException e) {
                return thePath.get(0);
            }
        }
    }

    /**
     * Given a List of Nodes, it returns the one with the lowest F Score.
     * F Score is a variable of Node object.
     * Returns null Node object if nothing found.
     *
     * @param list List of Node Objects.
     * @return The Node object with the Lowest F Score.
     */
    protected Node returnLowestFScore(ArrayList<Node> list) {
        Node lowestNode = new Node(null, null, null);
        double minimum = Double.MAX_VALUE;
        for (Node item : list) {
            if (minimum > item.getFDistance()) {
                minimum = item.getFDistance();
                lowestNode = item;
            }
        }
        return lowestNode;
    }

    /**
     * Calculates distance between given two Node objects.
     * Manhattan distance is used to have a path with lowest number of turns.
     * It basically draws perpendicular lines to one Node to another.
     *
     * @param n1 Any Node Object.
     * @param n2 Any Node Object.
     * @return The Manhattan distance.
     */
    protected double manhattanDistance(Node n1, Node n2) {
        // Using Manhattan Distance
        int dx = Math.abs(n1.getX() - n2.getX());
        int dy = Math.abs(n1.getY() - n2.getY());
        return (dx + dy);
    }

    /**
     * Calculates distance between given two Node objects.
     * Diagonal distance is used to have zig-zag shaped paths.
     * It always draws a straight line between Nodes.
     *
     * @param n1 Any Node object.
     * @param n2 Any Node object.
     * @return The Diagonal distance.
     */
    protected double diagonalDistance(Node n1, Node n2) {
        // Using diagonal distance
        int dx = Math.abs(n1.getX() - n2.getX());
        int dy = Math.abs(n1.getY() - n2.getY());
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * On the grid-based game map, it returns the coordinates of all locations that satisfy the type as handy Square objects.
     * Returns an empty List if nothing is found.
     *
     * @param map Grid-based game map.
     * @param type Name of a Type object as String literal.
     * @return List of discovered Squares.
     */
    protected ArrayList<Square> locateType(Type map[][], String type) {
        ArrayList<Square> squares = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // Set their coordinates
                String mapType = map[i][j].name();
                if (mapType.equals(type)) {
                    squares.add(new Square(i, j));
                }
            }
        }
        return squares;
    }

    /**
     * This is a dramatically important function to identify not walkable the coordinates.
     * It returns truth value of "True" if the given Node object is not walkable.
     * It uses basic Type objects to check whether is walkable or not.
     * Also, it uses exception handling to figure out whether the coordinate is out of map or not.
     *
     * @param map Grid-based game map.
     * @param node Any Node object.
     * @return Truth value of the Node wheter it is walkable or not.
     */
    protected boolean isItInvalid(Type map[][], Node node) {
        try {
            Type whoIs = map[node.getX()][node.getY()];
            if (!isSheep()) {
                if (whoIs == Type.FENCE || whoIs == Type.WOLF2 || whoIs == Type.SHEEP1) {
                    return true;
                }
            }
            if (isSheep()) {
                if (whoIs == Type.FENCE || whoIs == Type.WOLF2 || whoIs == Type.SHEEP2 || whoIs == Type.WOLF1) {
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
        return false;
    }

    /**
     * This small function checks whether a Node object actually exists or not.
     * Several times in code a Node object is created but not given sensible coordinates.
     * This decision made such a function necessary.
     *
     * @param node Any Node object.
     * @return Truth value.
     */
    protected boolean doesNodeExist(Node node) {
        return node.getY() == -1 && node.getX() == -1;
    }

    /**
     * This function is handy when it comes to figure out the Type of the Square object in the map.
     * With that, a Square object also reveals what exactly it is.
     * Returns a Fence type if the coordinates is out of bounds. So the player perceives the Square as invalid.
     *
     * @param map Grid-based game map.
     * @param square Any Square object.
     * @return Type of the Square.
     */
    protected Type returnType(Type map[][], Square square) {
        try {
            return map[square.getX()][square.getY()];
        } catch (ArrayIndexOutOfBoundsException e) {
            return Type.FENCE;
        }
    }

    /**
     * This function is highly required in order to beat the mysterious pointer handling behavior of Java.
     * Using the function
     * "anyArrayList.contains(Object o)"
     * does not return the intended results because Node objects can vary, even if their coordinates are same.
     * So this function compares the coordinates, in order to figure out if the given Node exists in the List.
     *
     * @param theList List of Nodes.
     * @param theNode A Node to search.
     * @return Truth value.
     */
    public boolean containsNode(ArrayList<Node> theList, Node theNode) {
        for (Node node : theList) {
            if (node.compareCoordinates(theNode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * For the same reasons mentioned in function "containsNode", this function is required to return a Node object from a List.
     *
     * @param theList List of Nodes.
     * @param theNode A Node to return.
     * @return Node object if found.
     */
    public Node returnNode(ArrayList<Node> theList, Node theNode) {
        for (Node node : theList) {
            if (node.compareCoordinates(theNode)) {
                return node;
            }
        }
        return null;
    }

    /**
     * A function required for debugging purposes.
     * @param map Grid-based game map.
     * @param nodesToMark A list of Nodes to mark on the Map.
     */
    protected void printMap(Type map[][], ArrayList<Node> nodesToMark) {
        System.out.println();
        boolean isIt = false;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (Node item : nodesToMark) {
                    isIt = item.getX() == j && item.getY() == i;
                    if (isIt) {
                        System.out.print('X');
                        break;
                    }
                }
                if (!isIt) {
                    System.out.print(returnTypeChar(map[i][j]));
                }
            }
            System.out.println();
        }
        System.out.print("-----------------------\n");
        return;
    }

    /**
     * A function for debugging purpose
     * @param type
     * @return
     */
    protected char returnTypeChar(Type type) {
        char s = 'n';
        if (type == Type.EMPTY) {
            s = '.';
        }
        if (type == Type.GRASS) {
            s = 'g';
        }
        if (type == Type.FENCE) {
            s = '#';
        }
        if (type == Type.RHUBARB) {
            s = 'r';
        }
        if (type == Type.SHEEP1) {
            s = '1';
        }
        if (type == Type.SHEEP2) {
            s = '3';
        }
        if (type == Type.WOLF1) {
            s = '2';
        }
        if (type == Type.WOLF2) {
            s = '4';
        }
        return s;
    }

    /**
     * A function for debugging purposes.
     * @param it List of Nodes to print coordinates.
     */
    protected void printPath(ArrayList<Node> it) {
        System.out.println(it.size() + " steps to go: ");
        for (Node node : it) {
            System.out.print(node.toString());
        }
        System.out.println("\n-----------------------");
    }

    /**
     * A function for debugging purposes.
     * @param map Grid-based game map.
     * @param node Any Node object.
     */
    protected void printNode(Type map[][], Node node) {
        int x = node.getX();
        int y = node.getY();
        System.out.println(map[x][y].name() + ", at coordinates: " + node.toString());
        return;
    }

    public String getNickname() {
        return "BlackGull";
    }

    /**
     * SQUARE OBJECT
     *
     * This object simply holds the information of coordinates for the Node objects.
     */
    class Square {
        private int x, y;

        protected Square(int x, int y) {
            this.x = x;
            this.y = y;
        }

        protected int getX() {
            return this.x;
        }

        protected void setX(int x) {
            this.x = x;
        }

        protected int getY() {
            return this.y;
        }

        protected void setY(int y) {
            this.y = y;
        }

        protected void printCoordinates() {
            System.out.println("Coordinates:\t" + this.x + this.y);
        }
    }

    /**
     * NODE OBJECT
     *
     * The game map is divided to blank square grids and a more functional object definition is required to express the map.
     * This object satisfies this necessity.
     */
    class Node {
        private Square selfSquare;
        private Node parentNode;
        private double gDistance;
        private double fDistance;
        private double hDistance;
        private Type kind;

        protected Node(Square sS, Node pN, Type k) {
            this.selfSquare = sS;
            this.parentNode = pN;
            this.kind = k;
        }

        protected void calculateFDistance() {
            this.fDistance = this.gDistance + this.hDistance;
        }

        protected void setAllCosts(double gD, double hD) {
            this.gDistance = gD;
            this.hDistance = hD;
            this.fDistance = this.gDistance + this.hDistance;
        }

        protected Square getSelfSquare() {
            return this.selfSquare;
        }

        protected void setSelfSquare(Square rS) {
            this.selfSquare = rS;
        }

        protected Node getParentNode() {
            return this.parentNode;
        }

        protected void setParentNode(Node pN) {
            this.parentNode = pN;
        }

        protected double getGDistance() {
            return this.gDistance;
        }

        protected void setGDistance(double gD) {
            this.gDistance = gD;
        }

        protected double getFDistance() {
            return this.fDistance;
        }

        protected void setFDistance(double fD) {
            this.fDistance = fD;
        }

        protected double getHDistance() {
            return this.hDistance;
        }

        protected void setHDistance(double hD) {
            this.hDistance = hD;
        }

        protected Type getKind() {
            return this.kind;
        }

        protected void setKind(Type k) {
            this.kind = k;
        }

        // SelfSquare's coordinates:
        protected int getX() {
            return this.selfSquare.getX();
        }

        protected void setX(int x) {
            this.selfSquare.setX(x);
        }

        protected int getY() {
            return this.selfSquare.getY();
        }

        protected void setY(int y) {
            this.selfSquare.setY(y);
        }

        protected boolean compareCoordinates(Node otherNode) {
            boolean testDx = this.getSelfSquare().getX() == otherNode.getSelfSquare().getX();
            boolean testDy = this.getSelfSquare().getY() == otherNode.getSelfSquare().getY();

            return testDx && testDy;
        }

        /**
         * Return adjacent Nodes of itself.
         * @param map Grid-based game map.
         * @return List of adjacent Nodes.
         */
        protected ArrayList<Node> returnNeighbors(Type map[][]) {
            ArrayList<Square> neighborSquareList = new ArrayList<>();
            ArrayList<Node> neighborNodeList = new ArrayList<>();

            neighborSquareList.add(new Square(this.getX(), this.getY() - 1));
            neighborSquareList.add(new Square(this.getX(), this.getY() + 1));
            neighborSquareList.add(new Square(this.getX() - 1, this.getY()));
            neighborSquareList.add(new Square(this.getX() + 1, this.getY()));

            for (Square item : neighborSquareList) {
                neighborNodeList.add(new Node(item, this, returnType(map, item)));
            }
            return neighborNodeList;
        }

        /**
         * This functions is using the same method as "constructPath" function.
         * It figures out the cost needed to get to itself, tracing back the parent Nodes to the first one.
         * @return
         */
        protected double costSoFar() {
            double costSoFar = 0;
            Node parentNode = this.getParentNode();
            while (parentNode.getParentNode() != null) {
                costSoFar += 1;
                parentNode = parentNode.getParentNode();
            }
            return costSoFar;
        }

        /**
         * toString function. YAAAYY! Documentation finally ends!
         *
         * @return Returns a nice nice String object.
         */
        public String toString() {
            String iceIceBaby = "<" + this.getX() + "," + this.getY() + ">";
            return iceIceBaby;
        }
    }
}
