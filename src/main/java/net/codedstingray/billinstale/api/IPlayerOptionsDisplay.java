package net.codedstingray.billinstale.api;

import net.codedstingray.billinstale.decisiongraph.GraphNode;

public interface IPlayerOptionsDisplay {

    void displayPlayerOptions(GraphNode.GraphOption... options);

    void displayPlayerOptions(String... options);
}
