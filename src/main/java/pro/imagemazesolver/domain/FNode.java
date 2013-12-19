/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.domain;

import pro.imagemazesolver.datastructures.NodeList;

/**
 *
 * @author lalli
 */
public class FNode extends Node {

    FNode next;
    FNode prev;
    FNode parent;
    FNode child;
    Node node;
    float Weight;

    public FNode(Node node) {
        super(0, 0, null);

        this.node = node;
    }

    public float getWeight() {
        return node.getWeight();
    }

    public FNode getNext() {
        return next;
    }

    public void setNext(FNode next) {
        this.next = next;
    }

    public FNode getPrev() {
        return prev;
    }

    public void setPrev(FNode prev) {
        this.prev = prev;
    }

    public FNode getParent() {
        return parent;
    }

    public void setParent(FNode parent) {
        this.parent = parent;
    }

    public FNode getChild() {
        return child;
    }

    public void setChild(FNode child) {
        this.child = child;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public NodeList neightbourList() {
        NodeList list = new NodeList();
        list.add(this);
        FNode next = getNext();
        while (next != this) {
            list.add(next);
            next = next.getNext();
        }
        return list;
    }
}
