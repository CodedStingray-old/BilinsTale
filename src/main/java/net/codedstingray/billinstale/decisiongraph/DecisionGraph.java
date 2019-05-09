package net.codedstingray.billinstale.decisiongraph;

import net.codedstingray.billinstale.GameController;
import net.codedstingray.billinstale.api.IMessageDisplay;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DecisionGraph {

    private GraphNode startNode;
    private Set<GraphNode> finalNodes = new HashSet<>();

    private HashMap<Integer, GraphNode> allNodes;

    public GraphNode createNode(int id, String message) {
        GraphNode node = new GraphNode(id, message);
        allNodes.put(id, node);
        return node;
    }

    //getters
    public GraphNode getNode(int id) {
        return allNodes.get(id);
    }

    public GraphNode getStartNode() {
        return startNode;
    }

    public Set<GraphNode> getFinalNodes() {
        return finalNodes;
    }

    //setters
    public void setStartNode(GraphNode node) {
        if(!allNodes.values().contains(node))
            throw new IllegalArgumentException("Node " + node.getId() + " is not in the graph and can not be set as start node");

        startNode = node;
    }

    public void setFinalNodes(Collection<GraphNode> nodes) {
        for(GraphNode node: nodes) {
            if(!allNodes.values().contains(node))
                throw new IllegalArgumentException("Node " + node.getId() + " is not in the graph and can not be set as final node");
        }

        finalNodes.addAll(nodes);
    }

    public void addFinalNode(GraphNode node) {
        if(!allNodes.values().contains(node))
            throw new IllegalArgumentException("Node " + node.getId() + " is not in the graph and can not be set as final node");

        finalNodes.add(node);
    }

    //functionalities
    public boolean validateGraph() {
        boolean valid = true;

        for(GraphNode node: allNodes.values()) {
            //check if start and endnodes are set
            if(startNode == null) {
                GameController.get().messageDisplay.displayMessage(IMessageDisplay.MessageType.ERROR,
                        "No starting node set");
                valid = false;
            }
            if(finalNodes.size() == 0) {
                GameController.get().messageDisplay.displayMessage(IMessageDisplay.MessageType.ERROR,
                        "No final nodes set");
                valid = false;
            }

            //check for nodes that have no continuing nodes but are also not set as a final node
            // (to avoid unintended endpoints)
            if(node.getOptions().length == 0 && !finalNodes.contains(node)) {
                GameController.get().messageDisplay.displayMessage(IMessageDisplay.MessageType.ERROR,
                        "Node " + node.getId() + " has no continuing nodes but is also not set as a final node");
                valid = false;
            }

            //TODO: check if node is reachable from start
        }

        return valid;
    }
}
