package com.mgatelabs.swftools.support.tools;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;


public class JMenuItemCreator {
    ActionListener myActionListener;

    public JMenuItemCreator(ActionListener myList) {
        myActionListener = myList;
    }

    public JMenuItem build(String name) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(myActionListener);
        return item;
    }
}