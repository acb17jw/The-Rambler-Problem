 /**
   * RunRamblersSearch.java
   *
   * @author <a href="mailto: "Phil Green</a>
   * 2013 version

   run a map traversal

 */

import sheffield.*;
import java.util.*;

public class RunRamblersSearch {

  public static void main(String[] arg) {

    // create an EasyWriter

    EasyWriter screen = new EasyWriter();

    Carta map1= new Carta();
    map1.mapFromFile("tmc.pgm");
    // screen.println(map1.toString());
    //screen.println(map1.getLinks("Start"));
    Coords goal = new Coords(2,5);
    Coords start = new Coords(10,0);
    RamblersSearch searcher = new RamblersSearch(map1,goal);
    SearchState initState = (SearchState) new RamblersState(start, 0, 0);

    //change from search1 - specify strategy
    //String res_df = searcher.runSearch(initState, "breadthFirst");
    //screen.println(res_df);
    //String res_bf = searcher.runSearch(initState, "depthFirst");
    //screen.println(res_bf);
    String res_bb = searcher.runSearch(initState, "AStar");
    screen.println(res_bb);
  }
}










