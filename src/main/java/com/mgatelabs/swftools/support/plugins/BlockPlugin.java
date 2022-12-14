package com.mgatelabs.swftools.support.plugins;

// Exploit Hooks

import com.mgatelabs.swftools.exploit.hook.BlockEditInterface;

// Tag Support
// Util Support

public interface BlockPlugin {
    // Menu Options
    String getMenuName();

    String getMenuPath();

    // Plugin Tests
    boolean isApplicable(int id);

    // All Work Takes Place Here
    boolean work(BlockEditInterface bei);
}