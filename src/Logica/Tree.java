/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.io.File;

/**
 *
 * @author Me
 */
class TreeNode {

    String value;
    ArrayList<TreeNode> children;

    TreeNode(String newData) {
        children = new ArrayList<>();
        value = newData;
    }
}

public class Tree {

    private TreeNode root;
    GraphViz graphBuilder = new GraphViz();
    public int treeNodesCounter = 0;
    public int nullsCounter = 0;
    public ArrayList<String> visitedNodes = new ArrayList<>();

    public Tree() {
        treeNodesCounter = 0;
        root = null;
        graphBuilder.addln(graphBuilder.start_graph());
    }

    public TreeNode findRecursive(String data) {
        return (findRecursive(root, data));
    }

    //Methods overload
    TreeNode result = null;

    private TreeNode findRecursive(TreeNode node, String lookedVaue) {
        if (node == null) {
            result = null;
        }

        if (lookedVaue.equalsIgnoreCase(node.value)) {
            result = node;
        }

        for (TreeNode tmp : node.children) {
            findRecursive(tmp, lookedVaue);
        }

        return result;
    }

    public void insertTreeNode(String value, String father) {
        root = insertTreeNode(root, value, father);
    }

    private TreeNode insertTreeNode(TreeNode node, String value, String father) {
        if (node == null) {
            node = new TreeNode(value);
        } else {
            TreeNode nodeFather = findRecursive(father);
            if (nodeFather != null) {
                nodeFather.children.add(new TreeNode(value));
            } else {
                node.children.add(new TreeNode(value));
            }
        }
        return node;
    }

    //Return node number, looking it as its value
    public int findTreeNode(String valueTreeNode) {
        return visitedNodes.indexOf(valueTreeNode);
    }

    public String finalizeGraph() {
        String rta = "";
        try {
            System.out.println("");
            graphBuilder.addln(graphBuilder.end_graph());
            System.out.println(graphBuilder.getDotSource());
            String type = "gif";
            File out = new File(System.getProperty("user.home")+"/tree." + type);
            if (out.exists()) {
                out.delete();
            }
            rta = out.toString();
            graphBuilder.writeGraphToFile(graphBuilder.getGraph(graphBuilder.getDotSource(), type), out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    private void findTreeNodes(TreeNode node, Tree tree) {
        if (node != null) {
            String nullValue = "null";
            //Node cration
            graphBuilder.addln(String.format(
                    "%s [ label=<%s> ]", treeNodesCounter, node.value));
            visitedNodes.add(node.value);
            treeNodesCounter++;

            for (TreeNode tmp : node.children) {
                if (tmp == null) {
                    nullValue = "null" + nullsCounter;
                    nullsCounter++;
                    graphBuilder.addln(String.format("%d[shape=point]", nullValue));
                }
                findTreeNodes(tmp, tree);
            }
        }
    }
    static int newNullsCounter = 0;

    public void asignFatherToSon(TreeNode node, Tree tree) {
        if (node != null) {
            for (TreeNode tmp : node.children) {
                if (tmp != null) //Adding the left child to the graph
                {
                    graphBuilder.addln(String.format(
                            "%s -> %s", findTreeNode(node.value), findTreeNode(tmp.value)));
                } else {
                    graphBuilder.addln(String.format(
                            "%s -> %s", findTreeNode(node.value), "null" + newNullsCounter++));
                }
                asignFatherToSon(tmp, tree);
            }
        }
    }

    public String graphTree(Tree tree) {
        String rta = "";
        graphBuilder.addln("ordering = out");
        findTreeNodes(root, tree);
        asignFatherToSon(root, tree);
        rta = finalizeGraph();
        return rta;
    }

    static int size = 0;

    public int getSize(TreeNode node, Tree tree) {
        if (node != null) {
            size++;
            for (TreeNode tmp : node.children) {
                getSize(tmp, tree);
            }
        }
        return size;
    }

    public static void main(String[] args) {
        // TODO code application logic here                                        
        Tree tree1 = new Tree();
        
        tree1.insertTreeNode("Abuelo", "");
               
        tree1.insertTreeNode("Hijo", "");

        tree1.insertTreeNode("Nieto", "Hijo");
        tree1.insertTreeNode("Otro nieto", "Hijo");

        tree1.graphTree(tree1);
    }

}
