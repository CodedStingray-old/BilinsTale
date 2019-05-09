package net.codedstingray.billinstale.decisiongraph;

import java.util.Arrays;

public class GraphNode {

    private int id;

    /**
     * The message displayed before the options of this node
     */
    private String message;
    private GraphNode.GraphOption[] options;

    GraphNode(int id, String message) {
        this.id = id;
        this.message = message;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public GraphOption[] getOptions() {
        return options;
    }

    //setters
    public void setOptions(GraphOption... options) {
        if(this.options != null && !Arrays.equals(this.options, new GraphOption[0])) {
            throw new IllegalStateException("Node options already set; Node id: " + id);
        }

        this.options = options;
    }

    public static class GraphOption {
        /**
         * The text of this option
         */
        public final String optionText;
        /**
         * The Node this option points to.
         */
        public final GraphNode nextNode;

        public GraphOption(String optionText, GraphNode nextNode) {
            this.optionText = optionText;
            this.nextNode = nextNode;
        }
    }
}
