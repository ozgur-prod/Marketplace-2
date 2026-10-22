package pgdp.saleuine3.testing;

import org.junit.jupiter.api.Test;
import pgdp.pingulib.datastructures.trees.BST;
import pgdp.saleuine3.Bestelluin;
import pgdp.saleuine3.MarketOrder;
import pgdp.saleuine3.PinguFoodGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class Saleuine3BehaviourTesting {

    // First I tested the run method of bestelluin. I created two bestelluins and I created an ID list which only had
    // the ID 1 in it. So the bestelluin objects only had one ID to insert to the BST. I created two bestelluins with
    // the same BST and the same ID list. In this situation the first bestelluin should add the market order with the
    // corresponding ID to the BST. The second one should not add it again since the order with that ID is already
    // in the BST. I tested if the size of the BST was 1 after I called both run methods. And if that element was an
    // order with the ID 0.
    @Test
    void testBestelluin() throws InterruptedException {

        BST<MarketOrder> bst = new BST<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        Bestelluin bestelluin = new Bestelluin(list,1,bst,"(name)");
        Bestelluin bestelluin2 = new Bestelluin(list,2,bst,"(name2)");

        bestelluin.run();
        bestelluin2.run();

        MarketOrder order = new MarketOrder(1,0);
        assertTrue(bst.contains(order));
        assertEquals(1,bst.size());
    }

    // Here I tested the same method in a similar case. But this time the two bestelluin objects tried to add different
    // market orders with different ID's to the BST. In this situation there should be two market orders with the corresponding
    // IDs in the BST. I tested that.
    @Test
    void testBestelluin2() throws InterruptedException {

        BST<MarketOrder> bst = new BST<>();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list.add(1);
        list2.add(2);

        Bestelluin bestelluin = new Bestelluin(list,2,bst,"(name)");
        Bestelluin bestelluin2 = new Bestelluin(list2,3,bst,"(name2)");

        bestelluin.run();
        bestelluin2.run();

        MarketOrder order = new MarketOrder(1,0);
        MarketOrder order2 = new MarketOrder(2,0);

        assertEquals(2,bst.size());
        assertTrue(bst.contains(order));
        assertTrue(bst.contains(order2));
    }

    // Lastly I checked if the run method updates an already existing market order in a BST. I created a market order with
    // 50 orders and added it to the BST myself. I then created an ID list with only the ID of that market order so that
    // bestelluin only handles that ID. In the end I tested if the run method really updated the order and if the order
    // was more than the initial order (50).
    @Test
    void testBestelluin3() throws InterruptedException {

        BST<MarketOrder> bst = new BST<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        MarketOrder order = new MarketOrder(1,50);
        bst.insert(order);

        Bestelluin bestelluin = new Bestelluin(list,1,bst,"(name)");

        bestelluin.run();

        assertTrue(bst.get(order).getOrdered() > 50);
    }

    // Here I tested the run method of pinguFoodGenerator. I checked if the run method supplies the right amount and
    // updates the supply of the market order and the total supply of itself right.
    @Test
    void testFoodGenerator() throws InterruptedException {
        BST<MarketOrder> bst = new BST<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);

        MarketOrder order = new MarketOrder(5,90);
        bst.insert(order);

        PinguFoodGenerator generator = new PinguFoodGenerator(44,1,bst,list,"(name)");

        generator.run();

        assertEquals(44,generator.getTotalSupply());
        assertEquals(44,bst.get(order).getSupply());
    }

    // Here I tested the method with two pingu food generator objects. I made them supply for the same element of the
    // same BST. In this situation the total supplies of the objects should be separate and special to them. Also the
    // supply attribute of the spcified market order should be updated collectively. I tested these.
    @Test
    void testFoodGenerator2() throws InterruptedException {
        BST<MarketOrder> bst = new BST<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        MarketOrder order = new MarketOrder(0,50);
        bst.insert(order);

        PinguFoodGenerator generator = new PinguFoodGenerator(20,1,bst,list,"(name)");
        PinguFoodGenerator generator2 = new PinguFoodGenerator(30,1,bst,list,"(name2)");

        generator.run();
        generator2.run();

        assertEquals(20,generator.getTotalSupply());
        assertEquals(30,generator2.getTotalSupply());
        assertEquals(50,bst.get(order).getSupply());
    }

    // In the end, I created one pingu food generator object for just one market order in the BST. But I made the supply
    // runs attribute 3 to see if the run method really would supply three times and update the supply attribute of the
    // market order and the totalSupply attribute of itself accordingly.
    @Test
    void testFoodGenerator3() throws InterruptedException {
        BST<MarketOrder> bst = new BST<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        MarketOrder order = new MarketOrder(0,100);
        bst.insert(order);

        PinguFoodGenerator generator = new PinguFoodGenerator(20,3,bst,list,"(name)");

        generator.run();

        assertEquals(60,generator.getTotalSupply());
        assertEquals(60,bst.get(order).getSupply());
    }


}
