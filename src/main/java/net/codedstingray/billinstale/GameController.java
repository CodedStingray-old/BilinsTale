package net.codedstingray.billinstale;

import net.codedstingray.billinstale.api.IMessageDisplay;
import net.codedstingray.billinstale.api.IPlayerInputHandler;
import net.codedstingray.billinstale.api.IPlayerOptionsDisplay;
import net.codedstingray.billinstale.decisiongraph.DecisionGraph;
import net.codedstingray.billinstale.decisiongraph.GraphNode;
import net.codedstingray.billinstale.decisiongraph.GraphNode.GraphOption;

public class GameController {

    public final IMessageDisplay messageDisplay;
    public final IPlayerOptionsDisplay playerOptionsDisplay;
    public final IPlayerInputHandler playerInputHandler;

    private boolean running;
    private DecisionGraph decisionGraph;
    private GraphNode currentNode;

    private GameController(IMessageDisplay messageDisplay
            , IPlayerOptionsDisplay playerOptionsDisplay
            , IPlayerInputHandler playerInputHandler) {

        this.messageDisplay = messageDisplay;
        this.playerOptionsDisplay = playerOptionsDisplay;
        this.playerInputHandler = playerInputHandler;
    }

    void start() {
        init();
        gameLoop();
    }

    private void init() {
        System.out.println("Building decision graph...");
        decisionGraph = new DecisionGraph();

        //reding in the file will look very similar to this in structure
        GraphNode start = decisionGraph.createNode(0, "This is the start message.");
        GraphNode node1 = decisionGraph.createNode(1, "This is node 1");
        GraphNode node2 = decisionGraph.createNode(2, "This is node 2");
        GraphNode node3 = decisionGraph.createNode(3, "This is node 3");
        GraphNode node4 = decisionGraph.createNode(4, "This is node 4");
        GraphNode node5 = decisionGraph.createNode(5, "This is node 5");
        GraphNode node6 = decisionGraph.createNode(6, "This is node 6");
        GraphNode final1 = decisionGraph.createNode(7, "This is final node 1");
        GraphNode final2 = decisionGraph.createNode(8, "This is final node 2");

        decisionGraph.setStartNode(start);
        decisionGraph.addFinalNode(final1);
        decisionGraph.addFinalNode(final2);

        start.setOptions(
                new GraphOption("Choose node 1", node1),
                new GraphOption("Choose node 2", node2)
        );

        node1.setOptions(
                new GraphOption("Choose node 3", node3),
                new GraphOption("Choose node 4", node4)
        );
        node2.setOptions(
                new GraphOption("Choose node 4", node4),
                new GraphOption("Choose node 5", node5),
                new GraphOption("Choose node 6", node6)
        );

        node3.setOptions(
                new GraphOption("Choose node 5", node5)
        );
        node4.setOptions(
                new GraphOption("Choose final node 1", final1),
                new GraphOption("Choose node 3", node3)
        );
        node5.setOptions(
                new GraphOption("Choose node 4", node4),
                new GraphOption("Choose node 2", node2),
                new GraphOption("Choose final node 2", final2)
        );
        node6.setOptions(
                new GraphOption("Choose final node 1", final1),
                new GraphOption("Choose final node 2", final2)
        );

        System.out.println("Decision graph built successfully");

        currentNode = decisionGraph.getStartNode();
    }

    private void gameLoop() {
        running = true;

        while(running) {
            //display current node's message
            messageDisplay.displayMessage(IMessageDisplay.MessageType.COMMUNICATION, currentNode.getMessage());

            //TODO: clean this up (it's working but rather ugly)
            if(!decisionGraph.isFinalNode(currentNode)) {
                //current node is not a final node, so we ask the player for input on where to go next
                playerOptionsDisplay.displayPlayerOptions(currentNode.getOptions());

                boolean validInput = false;
                while(!validInput) {
                    IPlayerInputHandler.PlayerInput input = playerInputHandler.getPlayerInput();
                    if (input == IPlayerInputHandler.PlayerInput.EXIT) {
                        //player requested to exit. Exit is always a valid input
                        exit();
                        validInput = true;
                    } else {
                        int actionCode = input.actionCode;

                        if(actionCode >= 0 && actionCode < currentNode.getOptions().length) {
                            //we got a valid input, so move to the corresponding node
                            currentNode = currentNode.getOptions()[actionCode].nextNode;
                            validInput = true;
                        } else {
                            //we're out of bounds - invalid input
                            messageDisplay.displayMessage(
                                    IMessageDisplay.MessageType.WARNING,
                                    "Invalid input. Provide a number between (including) 0 and "
                                            + (currentNode.getOptions().length - 1) + " or type \"exit\" to exit the game."
                            );
                        }
                    }
                }
            } else {
                //final node
                messageDisplay.displayMessage(
                        IMessageDisplay.MessageType.COMMUNICATION,
                        "Congratulations! You finished the game. This is one of "
                                + decisionGraph.getFinalNodes().size() + " endings. Play again to unlock another ending!"
                );

                playerOptionsDisplay.displayPlayerOptions("Play again");

                boolean validInput = false;
                while(!validInput) {
                    IPlayerInputHandler.PlayerInput input = playerInputHandler.getPlayerInput();

                    switch(input) {
                        case EXIT:
                            exit();
                            validInput = true;
                            break;
                        case ONE:
                            currentNode = decisionGraph.getStartNode();
                            validInput = true;
                            break;
                        default:
                            messageDisplay.displayMessage(
                                    IMessageDisplay.MessageType.WARNING,
                                    "Invalid input. Type 0 to play again or type \"exit\" to exit the game."
                            );
                    }
                }
            }
        }
    }

    private void exit() {
        //TODO: confirmation/saving
        running = false;
    }

    //Singleton
    private static GameController instance;

    public static GameController create(IMessageDisplay messageDisplay
            , IPlayerOptionsDisplay playerOptionsDisplay
            , IPlayerInputHandler playerInputHandler) {

        if(instance != null)
            throw new IllegalStateException("Instance of GameController already exists");

        return instance = new GameController(messageDisplay, playerOptionsDisplay, playerInputHandler);
    }

    public static GameController get() {
        return instance;
    }
}
