package pgdp.saleuine3;

import java.util.ArrayList;

import pgdp.pingulib.datastructures.trees.BST;

public class PinguFoodGenerator implements Runnable {

	private int supplyRuns;
	private int maxSupply;
	private long totalSupply;

	private final BST<MarketOrder> orderDB;
	private final ArrayList<Integer> responsibleIDs;

	private final String name;

	public PinguFoodGenerator(int maxSupply, int supplyRuns, BST<MarketOrder> orderDB, ArrayList<Integer> ids,
			String name) {
		this.maxSupply = maxSupply;
		this.supplyRuns = supplyRuns;
		this.totalSupply = 0;
		this.orderDB = orderDB;
		this.responsibleIDs = ids;
		this.name = name;
	}

	public long getTotalSupply() {
		return totalSupply;
	}

	@Override
	public void run() {
		for (int i = 0; i < supplyRuns; i++){
			for (int j = 0; j < responsibleIDs.size(); j++){
				MarketOrder order = new MarketOrder(responsibleIDs.get(j), 0);
				try {
					totalSupply += orderDB.get(order).supplyMarket(maxSupply);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println(name + " supplied " + totalSupply + ".");
				}
			}
		}
		System.out.println(name + " supplied " + totalSupply + ".");
	}

}
