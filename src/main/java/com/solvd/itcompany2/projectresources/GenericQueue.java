package com.solvd.itcompany2.projectresources;

import java.util.*;

public class GenericQueue<T> extends LinkedList<T> implements List<T> {

    public void resolve() {
        this.remove();
        System.out.println("\nFinished! Next item:\n" + this.peek());
    }
}