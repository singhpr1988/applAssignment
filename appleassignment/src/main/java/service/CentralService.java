package service;

import processor.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prateekraj on 15/6/20.
 */
public class CentralService {

    private Map<String, Node> allNodes = new HashMap<String, Node>();

    public Map<String, Node> fetchAllNodesMap() {
        return this.allNodes;
    }
}
