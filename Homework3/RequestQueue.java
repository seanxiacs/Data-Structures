package Homework3;

/**
 * RequestQueue extends the ArrayList class to hold the queue of elevator requests.
 * @author Sean Xia, SBU ID 113181409, R30
 */

import java.util.ArrayList;

public class RequestQueue extends ArrayList<Request> {
    /**
     * Default constructor of the RequestQueue class.
     */
    public RequestQueue() {

    }

    /**
     * This method adds the request to the end of the ArrayList.
     * @param req
     */
    public void enqueue(Request req) {
        add(req);
    }

    /**
     * This method removes the Request from the front of the ArrayList.
     * @return remove(0)
     */
    public Request dequeue() {
        return remove(0);
    }
}
