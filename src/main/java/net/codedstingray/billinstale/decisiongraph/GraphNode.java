package net.codedstingray.billinstale.decisiongraph;

public class GraphNode {

    private String message;
    private GraphNode.GraphOption[] options;

    public GraphOption[] getOptions() {
        return options;
    }

    public static class GraphOption {
        public final String optionText;
        public final GraphNode nextNode;

        public GraphOption(String optionText, GraphNode nextNode) {
            this.optionText = optionText;
            this.nextNode = nextNode;
        }
    }
}
