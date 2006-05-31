package org.netbeans.graph.examples;

import org.openide.util.HelpCtx;
import org.openide.util.actions.CallableSystemAction;

public final class RunExampleAction extends CallableSystemAction {

    public void performAction() {
        RunDialog.main(null);
    }

    public String getName() {
        return "&Graph Library Example";
    }

    public String iconResource() {
        return "org/netbeans/graph/examples/control/resources/port.gif";
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

//    protected void initialize() {
//        super.initialize();
//    }

    protected boolean asynchronous() {
        return false;
    }

}
